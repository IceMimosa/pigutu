package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ConfigEntity;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DBMeta(table = ConfigDao.TABLE)
public interface ConfigDao extends BaseDao<ConfigEntity> {
    String TABLE = "config";
    default long insertImageSet(ConfigEntity configEntity){
        return insert(configEntity);
    }

    /**
     *
     */
    default List<ConfigEntity> getConfig() {
        return selectList(
                Maps.newHashMap(),
                new QueryCondition()
        );
    }

}
