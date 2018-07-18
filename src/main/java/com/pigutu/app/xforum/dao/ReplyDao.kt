package com.pigutu.app.xforum.dao

import com.pigutu.app.mapper.mybatis.BaseDao
import com.pigutu.app.mapper.mybatis.DBMeta
import com.pigutu.app.xforum.model.Reply
import com.xforum.library.model.Comment
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
@DBMeta(table = "x_comment")
interface ReplyDao: BaseDao<Reply> {
}