package com.pigutu.app.mapper;

import com.google.common.collect.Maps;
import com.pigutu.app.entity.ConfigEntity;
import com.pigutu.app.entity.KeywordEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DBMeta(table = KeywordDao.TABLE)
public interface KeywordDao extends BaseDao<KeywordEntity> {
    String TABLE = "hot_keyword";

}
