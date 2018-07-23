package com.pigutu.app.mapper;

import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.xforum.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@DBMeta(table = XUserDao.TABLE)
public interface XUserDao extends BaseDao<User> {
    String TABLE = "x_user";
}