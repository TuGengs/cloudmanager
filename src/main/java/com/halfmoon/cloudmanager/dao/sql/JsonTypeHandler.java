package com.halfmoon.cloudmanager.dao.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

@MappedJdbcTypes(JdbcType.CHAR)
public class JsonTypeHandler extends BaseTypeHandler<JsonArray> {
	
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	@Override
	public JsonArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		return gson.fromJson((rs.getString(columnName)), JsonArray.class);
		
	}

	@Override
	public JsonArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return gson.fromJson((rs.getString(columnIndex)), JsonArray.class);
		
	}

	@Override
	public JsonArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		
		return gson.fromJson((cs.getString(columnIndex)), JsonArray.class);
		
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex, JsonArray parameter, JdbcType jdbcType)
			throws SQLException {
		
		ps.setString(parameterIndex, parameter.toString());
		
	}
	
}
