package com.pigutu.app.mapper

import com.pigutu.app.mapper.mybatis.BaseDao
import com.pigutu.app.mapper.mybatis.DBMeta
import com.pigutu.app.xforum.model.Reply
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
@DBMeta(table = "x_reply")
interface XReplyDao : BaseDao<Reply> {
}