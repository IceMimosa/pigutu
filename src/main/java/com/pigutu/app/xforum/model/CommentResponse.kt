package com.pigutu.app.xforum.model

import com.xforum.library.model.Comment

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/7/18
 */
data class CommentResponse(val commentDetail: Comment,val commentList:ArrayList<Comment> ) {
}