package com.pigutu.app.xforum.dao

import com.pigutu.app.mapper.mybatis.DBMeta
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Mapper
@Repository
@DBMeta(table = "x_post")
interface PostDao {
}