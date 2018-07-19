package com.pigutu.app.xforum.service

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.xforum.dao.XCommentDao
import com.pigutu.app.xforum.dao.XPostDao
import com.pigutu.app.xforum.dao.XReplyDao
import com.pigutu.app.xforum.dao.XUserDao
import com.pigutu.app.xforum.model.Reply
import com.pigutu.app.xforum.model.Comment
import com.pigutu.app.xforum.model.Post
import com.pigutu.app.xforum.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/v1/post")
@ResponseBody
class PostController {
    @Autowired
    private lateinit var XUserDao: XUserDao
    @Autowired
    private lateinit var XPostDao: XPostDao
    @Autowired
    private lateinit var XCommentDao: XCommentDao
    @Autowired
    private lateinit var XReplyDao: XReplyDao

    private val summaryLength = 100

    @PostMapping("/signUp")
    fun signUp(user: User) {
        XUserDao.insert(user)
    }

    @PostMapping("/sendPost")
    fun insertPost(post: Post):ResponseReturn {
        System.out.print("aaa"+post.image_list)
        XPostDao.insert(post)
        return ResponseReturn.success(null)
    }

    @PostMapping("/sendComment")
    fun sendComment(comment: Comment) {
        XCommentDao.insert(comment)
    }

    @PostMapping("/sendReply")
    fun sendReply(reply: Reply) {
        XReplyDao.insert(reply)
    }

    @PostMapping("/deletePost")
    fun deletePost(postId: Long) {
        XPostDao.delete(postId)
    }

    @PostMapping("/deleteComment")
    fun deleteComment(commentId: Long) {
        XCommentDao.delete(commentId)
    }

    @PostMapping("/deleteReply")
    fun deleteReply(replyId: Long) {
        XReplyDao.delete(replyId)
    }

    @GetMapping("/getPosts")
    fun getPosts(barId: Int, page: Int): ResponseReturn {
        var postList = XPostDao.selectList(ImmutableMap.of(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        for (post in postList) {
            if(post.content.length>summaryLength){
                post.content_summary = post.content.substring(0,summaryLength)
            }else{
                post.content_summary = post.content
            }
            post.user
        }
        return ResponseReturn.success(postList)
    }

    @GetMapping("/getPostDetail")
    fun getPostDetail(postId: Int): ResponseReturn {
        var post = XPostDao.selectOne(ImmutableMap.of("id", postId) as MutableMap<String, Any>)
        return ResponseReturn.success(post)
    }

    @GetMapping("/getComment")
    fun getComment(commentId: Int) {
        var comment = XCommentDao.selectOne(ImmutableMap.of("id", commentId) as MutableMap<String, Any>)
        ResponseReturn.success(comment)
    }

    @GetMapping("/getCommentList")
    fun getComments(postId: Int, page: Int):ResponseReturn {
        var commentList = XCommentDao.selectList(ImmutableMap.of(ImmutableMap.of("post_id", postId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String,Any>)
        for((index,comment) in commentList.withIndex()){
            var user = XUserDao.selectOne(ImmutableMap.of("id",comment.comment_user_id) as MutableMap<String, Any>)
            commentList[index].comment_user = user
        }
        return ResponseReturn.success(commentList)
    }

    @GetMapping("/getReply")
    fun getReply(commentId: Int, page: Int):ResponseReturn {
        var replys = XReplyDao.selectList(ImmutableMap.of(ImmutableMap.of("comment_id", commentId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String,Any>)
        for((index,reply) in replys.withIndex()){
            var user = XUserDao.selectOne(ImmutableMap.of("id",reply.reply_user_id) as MutableMap<String, Any>)
            reply.reply_user= user
            replys[index] = reply
        }
        return ResponseReturn.success(replys)
    }

}