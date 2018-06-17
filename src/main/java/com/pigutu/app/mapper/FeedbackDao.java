package com.pigutu.app.mapper;

import com.pigutu.app.entity.FeedbackEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@DBMeta(table = FeedbackDao.TABLE)
public interface FeedbackDao extends BaseDao<FeedbackEntity> {
    String TABLE = "feedback";
}
