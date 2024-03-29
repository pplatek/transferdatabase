package com.devjpcb.transferdatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.PlatformUtils;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.ForeignKey;
import org.apache.ddlutils.model.Reference;
import org.apache.ddlutils.model.Table;

public class TransferDatabase {
    private TransferDatabaseConnection connectionSource;
    private TransferDatabaseConnection connectionDestine;
    private String tableSchemaSource;
    private String tableSchemaDestine;
    private boolean dropTablesFirst;
    private boolean continueOnError;
    private boolean identityOverrideOn;
    private boolean sqlCommentsOn;
    private boolean delimitedIdentifierModeOn;
    private boolean autoCommit;
    private boolean scriptModeOn;
    private Platform platformSource;
    private Platform platformDestine;
    private static Log log = LogFactory.getLog(TransferDatabase.class);

    public static TransferDatabaseConnection createConnection(Connection connection) {
	return new TransferDatabaseConnection(connection);
    }

    public void setConnectionSource(TransferDatabaseConnection connectionSource) {
	this.connectionSource = connectionSource;
    }

    public TransferDatabaseConnection getConnectionSource() {
	return this.connectionSource;
    }

    public void setConnectionDestine(TransferDatabaseConnection connectionDestine) {
	this.connectionDestine = connectionDestine;
    }

    public TransferDatabaseConnection getConnectionDestine() {
	return this.connectionDestine;
    }

    public void setTableSchemaSource(String tableSchemaSource) {
	this.tableSchemaSource = tableSchemaSource;
    }

    public String getTableSchemaSource() {
	return this.tableSchemaSource;
    }

    public void setTableSchemaDestine(String tableSchemaDestine) {
	this.tableSchemaDestine = tableSchemaDestine;
    }

    public String getTableSchemaDestine() {
	return this.tableSchemaDestine;
    }

    public void setDropTablesFirst(boolean dropTablesFirst) {
	this.dropTablesFirst = dropTablesFirst;
    }

    public boolean isDropTablesFirst() {
	return this.dropTablesFirst;
    }

    public void setContinueOnError(boolean continueOnError) {
	this.continueOnError = continueOnError;
    }

    public boolean isContinueOnError() {
	return this.continueOnError;
    }

    public void setIdentityOverrideOn(boolean identityOverrideOn) {
	this.identityOverrideOn = identityOverrideOn;
    }

    public boolean isIdentityOverrideOn() {
	return this.identityOverrideOn;
    }

    public void setSqlCommentsOn(boolean sqlCommentsOn) {
	this.sqlCommentsOn = sqlCommentsOn;
    }

    public boolean isSqlCommentsOn() {
	return this.sqlCommentsOn;
    }

    public void setDelimitedIdentifierModeOn(boolean delimitedIdentifierModeOn) {
	this.delimitedIdentifierModeOn = delimitedIdentifierModeOn;
    }

    public boolean isDelimitedIdentifierModeOn() {
	return this.delimitedIdentifierModeOn;
    }

    public void setAutoCommit(boolean commitEachTable) {
	this.autoCommit = commitEachTable;
    }

    public boolean isAutoCommit() {
	return this.autoCommit;
    }

    public boolean isScriptModeOn() {
        return scriptModeOn;
    }

    public void setScriptModeOn(boolean scriptModeOn) {
        this.scriptModeOn = scriptModeOn;
    }

    protected Platform getPlatformSource() {
	if (this.platformSource == null) {
	    if (this.connectionSource == null) {
		throw new NullPointerException("connectionSource not found");
	    }
	    
	    if (this.tableSchemaSource == null) {
		throw new NullPointerException("tableSchemaSource not found");
	    }
	    
	    TransferDatabaseDataSource dataSource = new TransferDatabaseDataSource(
		    getConnectionSource(), getTableSchemaSource());
	    
	    this.platformSource = PlatformFactory.createNewPlatformInstance(dataSource);
	    this.platformSource.setIdentityOverrideOn(isIdentityOverrideOn());
	    this.platformSource.setSqlCommentsOn(isSqlCommentsOn());
	    this.platformSource.setDelimitedIdentifierModeOn(isDelimitedIdentifierModeOn());
	    this.platformSource.setScriptModeOn(isScriptModeOn());

	    PlatformUtils pUtils = new PlatformUtils();
	    log.info("Platform DatabaseType: " + pUtils.determineDatabaseType(dataSource));
	    log.info("Platform Source: " + platformSource.getClass().getName());
	}
	return this.platformSource;
    }

    protected Platform getPlatformDestine() {
	if (this.platformDestine == null) {
	    if (this.connectionDestine == null) {
		throw new NullPointerException("connectionDestine not found");
	    }
	    
	    if (this.tableSchemaDestine == null) {
		throw new NullPointerException("tableSchemaDestine not found");
	    }

	    TransferDatabaseDataSource dataSource = new TransferDatabaseDataSource(getConnectionDestine(),
		    getTableSchemaDestine());

	    this.platformDestine = PlatformFactory.createNewPlatformInstance(dataSource);
	    this.platformDestine.setIdentityOverrideOn(isIdentityOverrideOn());
	    this.platformDestine.setSqlCommentsOn(isSqlCommentsOn());
	    this.platformDestine.setDelimitedIdentifierModeOn(isDelimitedIdentifierModeOn());
	    this.platformDestine.setScriptModeOn(isScriptModeOn());

	    PlatformUtils pUtils = new PlatformUtils();
	    log.info("Platform DatabaseType: " + pUtils.determineDatabaseType(dataSource));
	    log.info("Platform Destine: " + platformDestine.getClass().getName());
	}
	return this.platformDestine;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected List<Table> sortTables(Table[] tables) {
	ArrayList<Table> result = new ArrayList<Table>();
	HashSet<Table> processed = new HashSet<Table>();
	ListOrderedMap pending = new ListOrderedMap();

	for (int idx = 0; idx < tables.length; idx++) {
	    Table table = tables[idx];
	    if (table.getForeignKeyCount() == 0) {
		result.add(table);
		processed.add(table);
	    } else {
		HashSet<Table> waitedFor = new HashSet<Table>();
		for (int fkIdx = 0; fkIdx < table.getForeignKeyCount(); fkIdx++) {
		    Table waitedForTable = table.getForeignKey(fkIdx).getForeignTable();
		    if (!table.equals(waitedForTable)) {
			waitedFor.add(waitedForTable);
		    }
		}
		pending.put(table, waitedFor);
	    }
	}

	HashSet<Table> newProcessed = new HashSet<Table>();
	while ((!processed.isEmpty()) && (!pending.isEmpty())) {
	    newProcessed.clear();
	    for (Iterator it = pending.entrySet().iterator(); it.hasNext();) {
		Map.Entry entry = (Map.Entry) it.next();
		Table table = (Table) entry.getKey();

		HashSet<Table> waitedFor = (HashSet<Table>) entry.getValue();

		waitedFor.removeAll(processed);
		if (waitedFor.isEmpty()) {
		    it.remove();
		    result.add(table);
		    newProcessed.add(table);
		}
	    }
	    processed.clear();

	    HashSet<Table> tmp = processed;

	    processed = newProcessed;
	    newProcessed = tmp;
	}

	for (Iterator it = pending.keySet().iterator(); it.hasNext();) {
	    result.add((Table) it.next());
	}

	return result;
    }

    public void copySchemaToDatabase() throws SQLException {
	Database databaseSource = getPlatformSource().readModelFromDatabase("modelSource", null,
		getTableSchemaSource(), null);
	getPlatformDestine().createModel(databaseSource, isDropTablesFirst(), isContinueOnError());
	if (isAutoCommit()) {
	    if (!getPlatformDestine().getDataSource().getConnection().getAutoCommit()) {
		getPlatformDestine().getDataSource().getConnection().commit();
	    }
	}
    }

    public void copySchemaToFile(String filename, boolean append) throws FileNotFoundException,
	    UnsupportedEncodingException, IOException {
	Database databaseSource = getPlatformSource().readModelFromDatabase("modelSource", null,
		getTableSchemaSource(), null);
	String sql = getPlatformDestine().getCreateModelSql(databaseSource, isDropTablesFirst(), isContinueOnError());
	FileOutputStream fos = new FileOutputStream(filename, append);
	Writer writer = new OutputStreamWriter(fos, "UTF8");
	writer.write(sql);
	writer.close();
    }

    public void copySchemaToDDL(String filename) {
	Database databaseSource = getPlatformSource().readModelFromDatabase("modelSource", null,
		getTableSchemaSource(), null);
	DatabaseIO dataIO = new DatabaseIO();
	dataIO.write(databaseSource, filename);
    }

    public void copyDataToDatabase() throws Exception {
	Database databaseSource = getPlatformSource().readModelFromDatabase("modelSource", null,
		getTableSchemaSource(), null);
	Database databaseDestine = getPlatformDestine().readModelFromDatabase("modelDestine", null,
		getTableSchemaDestine(), null);

	List<Table> tables = sortTables(databaseSource.getTables());
	for (Table table : tables) {
	    log.info("Getting Data From " + table.getName() + "\n");

	    String SQL = getQueryFromTable(table);

	    @SuppressWarnings("rawtypes")
	    Iterator it = getPlatformSource().query(databaseSource, SQL, new Table[] { table });

	    log.info("Copying Data To " + table.getName() + "\n");

	    BigInteger total = BigInteger.ZERO;
	    BigInteger error = BigInteger.ZERO;
	    while (it.hasNext()) {
		try {
		    DynaBean tableRowData = (DynaBean) it.next();
		    getPlatformDestine().insert(getPlatformDestine().getDataSource().getConnection(), databaseDestine,
			    tableRowData);
		    total = total.add(BigInteger.ONE);
		} catch (Exception ex) {
		    error = error.add(BigInteger.ONE);
		    if (!isContinueOnError()) {
			throw new Exception(ex.getMessage(), ex.getCause());
		    }
		}
	    }

	    log.info("Total records copied ( " + total + " )\n");
	    log.info("Total records errors ( " + error + " )\n");

	    if (isAutoCommit()) {
		if (!getPlatformDestine().getDataSource().getConnection().getAutoCommit()) {
		    getPlatformDestine().getDataSource().getConnection().commit();
		}
	    }
	}
    }

    public void copyDataToFile(String filename, boolean append) throws IOException {
	Database databaseSource = getPlatformSource().readModelFromDatabase("modelSource", null,
		getTableSchemaSource(), null);

	List<Table> tables = sortTables(databaseSource.getTables());

	FileOutputStream fos = new FileOutputStream(filename, append);
	Writer writer = new OutputStreamWriter(fos, "UTF8");

	for (Table table : tables) {
	    log.info("Getting Data From " + table.getName() + "\n");

	    String SQL = getQueryFromTable(table);

	    @SuppressWarnings("rawtypes")
	    Iterator it = getPlatformSource().query(databaseSource, SQL, new Table[] { table });

	    log.info("Copying Data To " + table.getName() + "\n");
	    while (it.hasNext()) {
		DynaBean tableRowData = (DynaBean) it.next();
		String sql = getPlatformDestine().getInsertSql(databaseSource, tableRowData);
		writer.write(sql);
		writer.write(";\n");
	    }
	}

	writer.close();
	fos.close();
    }

    public void copyDataToDDL(String filename) throws NoSuchMethodException {
	throw new NoSuchMethodException();
    }

    public void copyDDLToDatabase(String filenme) throws SQLException {
	DatabaseIO dataIO = new DatabaseIO();
	Database databaseSource = dataIO.read(filenme);
	getPlatformDestine().createModel(databaseSource, isDropTablesFirst(), isContinueOnError());
	if (isAutoCommit()) {
	    if (!getPlatformDestine().getDataSource().getConnection().getAutoCommit()) {
		getPlatformDestine().getDataSource().getConnection().commit();
	    }
	}
    }

    protected String getQueryFromTable(Table table) {
	StringBuilder query = new StringBuilder();

	int columnsCount = table.getColumnCount();

	query.append("SELECT ");

	for (int index = 0; index < columnsCount; index++) {
	    if (getPlatformSource().isDelimitedIdentifierModeOn()) {
		query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
	    }
	    query.append(table.getColumn(index).getName());
	    if (getPlatformSource().isDelimitedIdentifierModeOn()) {
		query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
	    }
	    if (index < columnsCount - 1) {
		query.append(",");
	    }
	}

	query.append(" FROM ");

	if (getPlatformSource().isDelimitedIdentifierModeOn()) {
	    query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
	}

	query.append(table.getName());

	if (getPlatformSource().isDelimitedIdentifierModeOn()) {
	    query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
	}

	ForeignKey[] foreignKeys = table.getForeignKeys();
	Column[] primaryKeys = table.getPrimaryKeyColumns();

	List<String> orderByColumns = new ArrayList<String>();

	if (foreignKeys.length > 0) {
	    for (int index = 0; index < foreignKeys.length; index++) {
		Reference[] reference = foreignKeys[index].getReferences();
		for (int sub_index = 0; sub_index < reference.length; sub_index++) {
		    String name = reference[sub_index].getLocalColumnName();
		    if (!orderByColumns.contains(name)) {
			orderByColumns.add(name);
		    }
		}
	    }
	}

	if (primaryKeys.length > 0) {
	    for (int index = 0; index < primaryKeys.length; index++) {
		String name = primaryKeys[index].getName();
		if (!orderByColumns.contains(name)) {
		    orderByColumns.add(name);
		}
	    }
	}

	if (orderByColumns.size() > 0) {
	    query.append(" ORDER BY ");
	    for (int index = 0; index < orderByColumns.size(); index++) {
		query.append(getPreNullValueOrder());

		if (getPlatformSource().isDelimitedIdentifierModeOn()) {
		    query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
		}

		query.append((String) orderByColumns.get(index));

		if (getPlatformSource().isDelimitedIdentifierModeOn()) {
		    query.append(getPlatformSource().getPlatformInfo().getDelimiterToken());
		}

		query.append(getPostNullValueOrder());

		if (index < orderByColumns.size() - 1) {
		    query.append(",");
		}
	    }
	}

	log.info("SQL:  " + query + "\n");

	return query.toString();
    }

    protected String getPreNullValueOrder() {
	String preNullValueOrder = "";
	return preNullValueOrder;
    }

    protected String getPostNullValueOrder() {
	String postNullValueOrder = "";
	String platform = getPlatformSource().getName();

	switch (platform) {
	case "PostgreSql":
	case "Oracle":
	case "Oracle9":
	case "Oracle10":
	    postNullValueOrder = " NULLS FIRST";
	}

	return postNullValueOrder;
    }

    public void logInfo(String info) {
	log.info(info);
    }

    public void logWarn(String warn) {
	log.warn(warn);
    }

    public void logError(String error) {
	log.error(error);
    }

    public void logFatal(String fatal) {
	log.fatal(fatal);
    }

    public void logDebug(String debug) {
	log.debug(debug);
    }

    public void logTrace(String trace) {
	log.trace(trace);
    }
}
