package com.devjpcb.transferdatabase;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class TransferDatabaseDataSource implements DataSource {
    private TransferDatabaseConnection connection;
    private String tableSchema;

    public TransferDatabaseDataSource(Connection connection) {
	this(connection, null);
    }

    public TransferDatabaseDataSource(Connection connection, String tableSchema) {
	this.connection = new TransferDatabaseConnection(connection);
	this.tableSchema = tableSchema;
    }

    public Connection getConnection() throws SQLException {
	return this.connection;
    }

    public Connection getConnection(String username, String password) throws SQLException {
	throw new UnsupportedOperationException("Not supported by TransferDatabaseDataSource");
    }

    public PrintWriter getLogWriter() throws SQLException {
	return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
    }

    public void setLoginTimeout(int seconds) throws SQLException {
	throw new UnsupportedOperationException("Not supported by TransferDatabaseDataSource");
    }

    public int getLoginTimeout() throws SQLException {
	throw new UnsupportedOperationException("Not supported by TransferDatabaseDataSource");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
	throw new SQLException("TransferDatabaseDataSource is not a wrapper.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
	return false;
    }

    public void closeConnection() throws SQLException {
	if (!this.connection.isClosed()) {
	    this.connection.realClose();
	}
    }

    public String getTableSchema() {
	return this.tableSchema;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
	return null;
    }
}
