package com.pigutu.app.mapper;


import com.pigutu.app.entity.CommentEntity;
import com.pigutu.app.entity.FeedBackEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@DBMeta(table = CommentDao.TABLE)
public interface CommentDao extends BaseDao<CommentEntity> {
    String TABLE = "comment";
}
