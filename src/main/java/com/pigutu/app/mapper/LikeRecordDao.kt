package com.pigutu.app.mapper

import com.pigutu.app.entity.LikeRecordEntity
import com.pigutu.app.mapper.mybatis.BaseDao
import com.pigutu.app.mapper.mybatis.DBMeta
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
@DBMeta(table = KeywordDao.TABLE)
interface LikeRecordDao:BaseDao<LikeRecordEntity> {
}