package com.pigutu.app.typeHandler;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.pigutu.app.xforum.dao.XUserDao;
import com.pigutu.app.xforum.model.User;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeHandler extends BaseTypeHandler<User> {
    private Gson gson = new Gson();
    @Autowired
    XUserDao xUserDao;
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, User user, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,user.getId());
    }

    @Override
    public User getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return xUserDao.selectOne(ImmutableMap.of("id",resultSet.getLong(s)));
    }

    @Override
    public User getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return xUserDao.selectOne(ImmutableMap.of("id",resultSet.getLong(i)));
    }

    @Override
    public User getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return xUserDao.selectOne(ImmutableMap.of("id",callableStatement.getLong(i)));
    }
}
