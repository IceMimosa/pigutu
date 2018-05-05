package com.pigutu.app.mapper;

import com.pigutu.app.entity.CollectEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/4/24
 */
@Mapper
@Repository
@DBMeta(table = CollectDao.TABLE)
public interface CollectDao extends BaseDao<CollectEntity> {
    String TABLE = "collect";
}
