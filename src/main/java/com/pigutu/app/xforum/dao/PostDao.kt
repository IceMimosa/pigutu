package com.pigutu.app.xforum.dao

import com.pigutu.app.mapper.mybatis.BaseDao
import com.pigutu.app.mapper.mybatis.DBMeta
import com.xforum.library.model.Post
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Mapper
@Repository
@DBMeta(table = "x_post")
interface PostDao:BaseDao<Post>{
}