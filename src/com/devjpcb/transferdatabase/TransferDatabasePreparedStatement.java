package com.devjpcb.transferdatabase;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class TransferDatabasePreparedStatement
  extends TransferDatabaseStatement
  implements PreparedStatement
{
  private PreparedStatement statement;
  
  public TransferDatabasePreparedStatement(TransferDatabaseConnection transferDatabaseConnection, PreparedStatement statement)
  {
    super(transferDatabaseConnection, statement);
    this.statement = statement;
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    return new TransferDatabaseResultSet(this, this.statement.executeQuery());
  }
  
  public int executeUpdate()
    throws SQLException
  {
    return this.statement.executeUpdate();
  }
  
  public void setNull(int parameterIndex, int sqlType)
    throws SQLException
  {
    this.statement.setNull(parameterIndex, sqlType);
  }
  
  public void setNull(int parameterIndex, int sqlType, String typeName)
    throws SQLException
  {
    this.statement.setNull(parameterIndex, sqlType, typeName);
  }
  
  public void setBoolean(int parameterIndex, boolean x)
    throws SQLException
  {
    this.statement.setBoolean(parameterIndex, x);
  }
  
  public void setByte(int parameterIndex, byte x)
    throws SQLException
  {
    this.statement.setByte(parameterIndex, x);
  }
  
  public void setShort(int parameterIndex, short x)
    throws SQLException
  {
    this.statement.setShort(parameterIndex, x);
  }
  
  public void setInt(int parameterIndex, int x)
    throws SQLException
  {
    this.statement.setInt(parameterIndex, x);
  }
  
  public void setLong(int parameterIndex, long x)
    throws SQLException
  {
    this.statement.setLong(parameterIndex, x);
  }
  
  public void setFloat(int parameterIndex, float x)
    throws SQLException
  {
    this.statement.setFloat(parameterIndex, x);
  }
  
  public void setDouble(int parameterIndex, double x)
    throws SQLException
  {
    this.statement.setDouble(parameterIndex, x);
  }
  
  public void setBigDecimal(int parameterIndex, BigDecimal x)
    throws SQLException
  {
    this.statement.setBigDecimal(parameterIndex, x);
  }
  
  public void setString(int parameterIndex, String x)
    throws SQLException
  {
    this.statement.setString(parameterIndex, x);
  }
  
  public void setBytes(int parameterIndex, byte[] x)
    throws SQLException
  {
    this.statement.setBytes(parameterIndex, x);
  }
  
  public void setDate(int parameterIndex, Date x)
    throws SQLException
  {
    this.statement.setDate(parameterIndex, x);
  }
  
  public void setDate(int parameterIndex, Date x, Calendar cal)
    throws SQLException
  {
    this.statement.setDate(parameterIndex, x, cal);
  }
  
  public void setTime(int parameterIndex, Time x)
    throws SQLException
  {
    this.statement.setTime(parameterIndex, x);
  }
  
  public void setTime(int parameterIndex, Time x, Calendar cal)
    throws SQLException
  {
    this.statement.setTime(parameterIndex, x, cal);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x)
    throws SQLException
  {
    this.statement.setTimestamp(parameterIndex, x);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
    throws SQLException
  {
    this.statement.setTimestamp(parameterIndex, x, cal);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    this.statement.setAsciiStream(parameterIndex, x, length);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, long length)
    throws SQLException
  {
    this.statement.setAsciiStream(parameterIndex, x, length);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x)
    throws SQLException
  {
    this.statement.setAsciiStream(parameterIndex, x);
  }
  
  public void setUnicodeStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    this.statement.setUnicodeStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, int length)
    throws SQLException
  {
    this.statement.setBinaryStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, long length)
    throws SQLException
  {
    this.statement.setBinaryStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x)
    throws SQLException
  {
    this.statement.setBinaryStream(parameterIndex, x);
  }
  
  public void clearParameters()
    throws SQLException
  {
    this.statement.clearParameters();
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType)
    throws SQLException
  {
    this.statement.setObject(parameterIndex, x, targetSqlType);
  }
  
  public void setObject(int parameterIndex, Object x)
    throws SQLException
  {
    this.statement.setObject(parameterIndex, x);
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
    throws SQLException
  {
    this.statement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
  }
  
  public boolean execute()
    throws SQLException
  {
    return this.statement.execute();
  }
  
  public void addBatch()
    throws SQLException
  {
    this.statement.addBatch();
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader, int length)
    throws SQLException
  {
    this.statement.setCharacterStream(parameterIndex, reader, length);
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader, long length)
    throws SQLException
  {
    this.statement.setCharacterStream(parameterIndex, reader, length);
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader)
    throws SQLException
  {
    this.statement.setCharacterStream(parameterIndex, reader);
  }
  
  public void setRef(int parameterIndex, Ref x)
    throws SQLException
  {
    this.statement.setRef(parameterIndex, x);
  }
  
  public void setBlob(int parameterIndex, Blob x)
    throws SQLException
  {
    this.statement.setBlob(parameterIndex, x);
  }
  
  public void setBlob(int parameterIndex, InputStream inputStream, long length)
    throws SQLException
  {
    this.statement.setBlob(parameterIndex, inputStream, length);
  }
  
  public void setBlob(int parameterIndex, InputStream inputStream)
    throws SQLException
  {
    this.statement.setBlob(parameterIndex, inputStream);
  }
  
  public void setClob(int parameterIndex, Clob x)
    throws SQLException
  {
    this.statement.setClob(parameterIndex, x);
  }
  
  public void setClob(int parameterIndex, Reader reader, long length)
    throws SQLException
  {
    this.statement.setClob(parameterIndex, reader, length);
  }
  
  public void setClob(int parameterIndex, Reader reader)
    throws SQLException
  {
    this.statement.setClob(parameterIndex, reader);
  }
  
  public void setArray(int parameterIndex, Array x)
    throws SQLException
  {
    this.statement.setArray(parameterIndex, x);
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    return this.statement.getMetaData();
  }
  
  public void setURL(int parameterIndex, URL x)
    throws SQLException
  {
    this.statement.setURL(parameterIndex, x);
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    return this.statement.getParameterMetaData();
  }
  
  public void setRowId(int parameterIndex, RowId x)
    throws SQLException
  {
    this.statement.setRowId(parameterIndex, x);
  }
  
  public void setNString(int parameterIndex, String value)
    throws SQLException
  {
    this.statement.setNString(parameterIndex, value);
  }
  
  public void setNCharacterStream(int parameterIndex, Reader value, long length)
    throws SQLException
  {
    this.statement.setNCharacterStream(parameterIndex, value, length);
  }
  
  public void setNCharacterStream(int parameterIndex, Reader value)
    throws SQLException
  {
    this.statement.setNCharacterStream(parameterIndex, value);
  }
  
  public void setNClob(int parameterIndex, NClob value)
    throws SQLException
  {
    this.statement.setNClob(parameterIndex, value);
  }
  
  public void setNClob(int parameterIndex, Reader reader, long length)
    throws SQLException
  {
    this.statement.setNClob(parameterIndex, reader, length);
  }
  
  public void setNClob(int parameterIndex, Reader reader)
    throws SQLException
  {
    this.statement.setNClob(parameterIndex, reader);
  }
  
  public void setSQLXML(int parameterIndex, SQLXML xmlObject)
    throws SQLException
  {
    this.statement.setSQLXML(parameterIndex, xmlObject);
  }
}
