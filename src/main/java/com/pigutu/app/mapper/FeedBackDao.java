package com.pigutu.app.mapper;

import com.pigutu.app.entity.FeedBackEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@DBMeta(table = FeedBackDao.TABLE)
public interface FeedBackDao extends BaseDao<FeedBackEntity> {
    String TABLE = "feed_back";

    @Insert("insert into feed_back(content) values (#{content});")
     long insertFeedBack(@Param("content") String content);
}
