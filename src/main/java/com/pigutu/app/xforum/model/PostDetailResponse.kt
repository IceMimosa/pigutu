package com.pigutu.app.xforum.model

import com.xforum.library.model.Comment
import com.xforum.library.model.PostList

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/7/18
 */
data class PostDetailResponse(val postList:PostList,val commentList:ArrayList<Comment>) {
}