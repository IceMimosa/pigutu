package com.pigutu.app.typeHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringArrayListTypeHandler extends BaseTypeHandler<ArrayList<String>> {
    private Gson gson = new Gson();
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ArrayList<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, gson.toJson(parameter));
    }

    @Override
    public ArrayList<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return gson.fromJson(rs.getString(columnName),new TypeToken<ArrayList<String>>(){}.getType());
    }

    @Override
    public ArrayList<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return gson.fromJson(rs.getString(columnIndex),new TypeToken<ArrayList<String>>(){}.getType());
    }

    @Override
    public ArrayList<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return gson.fromJson(cs.getString(columnIndex),new TypeToken<ArrayList<String>>(){}.getType());
    }
}
