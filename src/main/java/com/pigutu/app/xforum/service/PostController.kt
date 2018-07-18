package com.pigutu.app.xforum.service

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.xforum.dao.CommentDao
import com.pigutu.app.xforum.dao.PostDao
import com.pigutu.app.xforum.dao.ReplyDao
import com.pigutu.app.xforum.dao.UserDao
import com.pigutu.app.xforum.model.Reply
import com.xforum.library.model.Comment
import com.xforum.library.model.Post
import com.xforum.library.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/post")
class PostController {
    @Autowired
    private lateinit var userDao: UserDao
    @Autowired
    private lateinit var postDao: PostDao
    @Autowired
    private lateinit var commentDao: CommentDao
    @Autowired
    private lateinit var replyDao: ReplyDao

    private val summaryLength = 100

    @PostMapping("/signUp")
    fun signUp(user: User) {
        userDao.insert(user)
    }

    @PostMapping("/sendPost")
    fun insertPost(post: Post) {
        postDao.insert(post)
    }

    @PostMapping("/sendComment")
    fun sendComment(comment: Comment) {
        commentDao.insert(comment)
    }

    @PostMapping("/sendReply")
    fun sendReply(reply: Reply) {
        replyDao.insert(reply)
    }

    @PostMapping("/deletePost")
    fun deletePost(postId: Long) {
        postDao.delete(postId)
    }

    @PostMapping("/deleteComment")
    fun deleteComment(commentId: Long) {
        commentDao.delete(commentId)
    }

    @PostMapping("/deleteReply")
    fun deleteReply(replyId: Long) {
        replyDao.delete(replyId)
    }

    @GetMapping("/getPosts")
    fun getPosts(barId: Int, page: Int): ResponseReturn {
        var postList = postDao.selectList(ImmutableMap.of(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
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
        var post = postDao.selectOne(ImmutableMap.of("id", postId) as MutableMap<String, Any>)
        return ResponseReturn.success(post)
    }

    @GetMapping("/getComment")
    fun getComment(commentId: Int) {
        var comment = commentDao.selectOne(ImmutableMap.of("id", commentId) as MutableMap<String, Any>)
        ResponseReturn.success(comment)
    }

    @GetMapping("/getCommentList")
    fun getComments(postId: Int, page: Int):ResponseReturn {
        var commentList = commentDao.selectList(ImmutableMap.of(ImmutableMap.of("post_id", postId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String,Any>)
        for((index,comment) in commentList.withIndex()){
            var user = userDao.selectOne(ImmutableMap.of("id",comment.comment_user_id) as MutableMap<String, Any>)
            commentList[index].comment_user = user
        }
        return ResponseReturn.success(commentList)
    }

    @GetMapping("/getReply")
    fun getReply(commentId: Int, page: Int):ResponseReturn {
        var replys = replyDao.selectList(ImmutableMap.of(ImmutableMap.of("comment_id", commentId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String,Any>)
        for((index,reply) in replys.withIndex()){
            var user = userDao.selectOne(ImmutableMap.of("id",reply.reply_user_id) as MutableMap<String, Any>)
            reply.reply_user= user
            replys[index] = reply
        }
        return ResponseReturn.success(replys)
    }

}