package com.pigutu.app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FeedBackDao {
    @Insert("insert into feed_back(content) values (#{content});")
     long insertFeedBack(@Param("content") String content);
}
