package com.devjpcb.transferdatabase;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class TransferDatabaseCallableStatement extends TransferDatabasePreparedStatement implements CallableStatement {
    private CallableStatement statement;

    public TransferDatabaseCallableStatement(TransferDatabaseConnection transferDatabaseConnection,
	    CallableStatement statement) {
	super(transferDatabaseConnection, statement);
	this.statement = statement;
    }

    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
	this.statement.registerOutParameter(parameterIndex, sqlType);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
	this.statement.registerOutParameter(parameterIndex, sqlType, scale);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
	this.statement.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
	this.statement.registerOutParameter(parameterName, sqlType);
    }

    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
	this.statement.registerOutParameter(parameterName, sqlType, scale);
    }

    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
	this.statement.registerOutParameter(parameterName, sqlType, typeName);
    }

    public boolean wasNull() throws SQLException {
	return this.statement.wasNull();
    }

    public String getString(int parameterIndex) throws SQLException {
	return this.statement.getString(parameterIndex);
    }

    public String getString(String parameterName) throws SQLException {
	return this.statement.getString(parameterName);
    }

    public boolean getBoolean(int parameterIndex) throws SQLException {
	return this.statement.getBoolean(parameterIndex);
    }

    public boolean getBoolean(String parameterName) throws SQLException {
	return this.statement.getBoolean(parameterName);
    }

    public byte getByte(int parameterIndex) throws SQLException {
	return this.statement.getByte(parameterIndex);
    }

    public byte getByte(String parameterName) throws SQLException {
	return this.statement.getByte(parameterName);
    }

    public short getShort(int parameterIndex) throws SQLException {
	return this.statement.getShort(parameterIndex);
    }

    public short getShort(String parameterName) throws SQLException {
	return this.statement.getShort(parameterName);
    }

    public int getInt(int parameterIndex) throws SQLException {
	return this.statement.getInt(parameterIndex);
    }

    public int getInt(String parameterName) throws SQLException {
	return this.statement.getInt(parameterName);
    }

    public long getLong(int parameterIndex) throws SQLException {
	return this.statement.getLong(parameterIndex);
    }

    public long getLong(String parameterName) throws SQLException {
	return this.statement.getLong(parameterName);
    }

    public float getFloat(int parameterIndex) throws SQLException {
	return this.statement.getFloat(parameterIndex);
    }

    public float getFloat(String parameterName) throws SQLException {
	return this.statement.getFloat(parameterName);
    }

    public double getDouble(int parameterIndex) throws SQLException {
	return this.statement.getDouble(parameterIndex);
    }

    public double getDouble(String parameterName) throws SQLException {
	return this.statement.getDouble(parameterName);
    }

    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
	return this.statement.getBigDecimal(parameterIndex, scale);
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
	return this.statement.getBigDecimal(parameterIndex);
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
	return this.statement.getBigDecimal(parameterName);
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
	return this.statement.getBytes(parameterIndex);
    }

    public byte[] getBytes(String parameterName) throws SQLException {
	return this.statement.getBytes(parameterName);
    }

    public Date getDate(int parameterIndex) throws SQLException {
	return this.statement.getDate(parameterIndex);
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
	return this.statement.getDate(parameterIndex, cal);
    }

    public Date getDate(String parameterName) throws SQLException {
	return this.statement.getDate(parameterName);
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
	return this.statement.getDate(parameterName, cal);
    }

    public Time getTime(int parameterIndex) throws SQLException {
	return this.statement.getTime(parameterIndex);
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
	return this.statement.getTime(parameterIndex, cal);
    }

    public Time getTime(String parameterName) throws SQLException {
	return this.statement.getTime(parameterName);
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
	return this.statement.getTime(parameterName, cal);
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
	return this.statement.getTimestamp(parameterIndex);
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
	return this.statement.getTimestamp(parameterIndex, cal);
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
	return this.statement.getTimestamp(parameterName);
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
	return this.statement.getTimestamp(parameterName, cal);
    }

    public Object getObject(int parameterIndex) throws SQLException {
	return this.statement.getObject(parameterIndex);
    }

    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
	return this.statement.getObject(parameterIndex, map);
    }

    public Object getObject(String parameterName) throws SQLException {
	return this.statement.getObject(parameterName);
    }

    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
	return this.statement.getObject(parameterName, map);
    }

    public Ref getRef(int parameterIndex) throws SQLException {
	return this.statement.getRef(parameterIndex);
    }

    public Ref getRef(String parameterName) throws SQLException {
	return this.statement.getRef(parameterName);
    }

    public Blob getBlob(int parameterIndex) throws SQLException {
	return this.statement.getBlob(parameterIndex);
    }

    public Blob getBlob(String parameterName) throws SQLException {
	return this.statement.getBlob(parameterName);
    }

    public Clob getClob(int parameterIndex) throws SQLException {
	return this.statement.getClob(parameterIndex);
    }

    public Clob getClob(String parameterName) throws SQLException {
	return this.statement.getClob(parameterName);
    }

    public Array getArray(int parameterIndex) throws SQLException {
	return this.statement.getArray(parameterIndex);
    }

    public Array getArray(String parameterName) throws SQLException {
	return this.statement.getArray(parameterName);
    }

    public URL getURL(int parameterIndex) throws SQLException {
	return this.statement.getURL(parameterIndex);
    }

    public URL getURL(String parameterName) throws SQLException {
	return this.statement.getURL(parameterName);
    }

    public void setURL(String parameterName, URL val) throws SQLException {
	this.statement.setURL(parameterName, val);
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
	this.statement.setNull(parameterName, sqlType);
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
	this.statement.setNull(parameterName, sqlType, typeName);
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
	this.statement.setBoolean(parameterName, x);
    }

    public void setByte(String parameterName, byte x) throws SQLException {
	this.statement.setByte(parameterName, x);
    }

    public void setShort(String parameterName, short x) throws SQLException {
	this.statement.setShort(parameterName, x);
    }

    public void setInt(String parameterName, int x) throws SQLException {
	this.statement.setInt(parameterName, x);
    }

    public void setLong(String parameterName, long x) throws SQLException {
	this.statement.setLong(parameterName, x);
    }

    public void setFloat(String parameterName, float x) throws SQLException {
	this.statement.setFloat(parameterName, x);
    }

    public void setDouble(String parameterName, double x) throws SQLException {
	this.statement.setDouble(parameterName, x);
    }

    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
	this.statement.setBigDecimal(parameterName, x);
    }

    public void setString(String parameterName, String x) throws SQLException {
	this.statement.setString(parameterName, x);
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
	this.statement.setBytes(parameterName, x);
    }

    public void setDate(String parameterName, Date x) throws SQLException {
	this.statement.setDate(parameterName, x);
    }

    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
	this.statement.setDate(parameterName, x, cal);
    }

    public void setTime(String parameterName, Time x) throws SQLException {
	this.statement.setTime(parameterName, x);
    }

    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
	this.statement.setTime(parameterName, x, cal);
    }

    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
	this.statement.setTimestamp(parameterName, x);
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
	this.statement.setTimestamp(parameterName, x, cal);
    }

    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
	this.statement.setAsciiStream(parameterName, x, length);
    }

    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
	this.statement.setAsciiStream(parameterName, x, length);
    }

    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
	this.statement.setAsciiStream(parameterName, x);
    }

    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
	this.statement.setBinaryStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
	this.statement.setBinaryStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
	this.statement.setBinaryStream(parameterName, x);
    }

    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
	this.statement.setObject(parameterName, x, targetSqlType, scale);
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
	this.statement.setObject(parameterName, x, targetSqlType);
    }

    public void setObject(String parameterName, Object x) throws SQLException {
	this.statement.setObject(parameterName, x);
    }

    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
	this.statement.setCharacterStream(parameterName, reader, length);
    }

    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
	this.statement.setCharacterStream(parameterName, reader, length);
    }

    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
	this.statement.setCharacterStream(parameterName, reader);
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
	return this.statement.getRowId(parameterIndex);
    }

    public RowId getRowId(String parameterName) throws SQLException {
	return this.statement.getRowId(parameterName);
    }

    public void setRowId(String parameterName, RowId x) throws SQLException {
	this.statement.setRowId(parameterName, x);
    }

    public void setNString(String parameterName, String value) throws SQLException {
	this.statement.setNString(parameterName, value);
    }

    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
	this.statement.setNCharacterStream(parameterName, value, length);
    }

    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
	this.statement.setNCharacterStream(parameterName, value);
    }

    public void setNClob(String parameterName, NClob value) throws SQLException {
	this.statement.setNClob(parameterName, value);
    }

    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
	this.statement.setNClob(parameterName, reader, length);
    }

    public void setNClob(String parameterName, Reader reader) throws SQLException {
	this.statement.setNClob(parameterName, reader);
    }

    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
	this.statement.setClob(parameterName, reader, length);
    }

    public void setClob(String parameterName, Clob x) throws SQLException {
	this.statement.setClob(parameterName, x);
    }

    public void setClob(String parameterName, Reader reader) throws SQLException {
	this.statement.setClob(parameterName, reader);
    }

    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
	this.statement.setBlob(parameterName, inputStream, length);
    }

    public void setBlob(String parameterName, Blob x) throws SQLException {
	this.statement.setBlob(parameterName, x);
    }

    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
	this.statement.setBlob(parameterName, inputStream);
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
	return this.statement.getNClob(parameterIndex);
    }

    public NClob getNClob(String parameterName) throws SQLException {
	return this.statement.getNClob(parameterName);
    }

    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
	this.statement.setSQLXML(parameterName, xmlObject);
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
	return this.statement.getSQLXML(parameterIndex);
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
	return this.statement.getSQLXML(parameterName);
    }

    public String getNString(int parameterIndex) throws SQLException {
	return this.statement.getNString(parameterIndex);
    }

    public String getNString(String parameterName) throws SQLException {
	return this.statement.getNString(parameterName);
    }

    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
	return this.statement.getNCharacterStream(parameterIndex);
    }

    public Reader getNCharacterStream(String parameterName) throws SQLException {
	return this.statement.getNCharacterStream(parameterName);
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
	return this.statement.getCharacterStream(parameterIndex);
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
	return this.statement.getCharacterStream(parameterName);
    }

    @Override
    public void closeOnCompletion() throws SQLException {
	this.statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
	return this.statement.isCloseOnCompletion();
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
	return this.statement.getObject(parameterIndex, type);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
	return this.statement.getObject(parameterName, type);
    }
}
