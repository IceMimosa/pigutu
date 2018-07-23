package com.pigutu.app.xforum.service

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.UserDao
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.mapper.XCommentDao
import com.pigutu.app.mapper.XPostDao
import com.pigutu.app.mapper.XReplyDao
import com.pigutu.app.mapper.XUserDao
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
    private lateinit var xUserDao: XUserDao
    @Autowired
    private lateinit var xPostDao: XPostDao
    @Autowired
    private lateinit var xCommentDao: XCommentDao
    @Autowired
    private lateinit var xReplyDao: XReplyDao

    @Autowired
    private lateinit var userDao: UserDao

    private val summaryLength = 100

    @GetMapping("/test")
    fun test() {
        var user = User()
        user.user_nickname = "aa"
        xUserDao.insert(user)
        user = User()
        user.user_nickname = "bb"
        for (i in 0..10) {
            xUserDao.insert(user)
        }
        var post = Post()
        post.content = "新版本即将发布"
        user.id = 2
        post.user = user
        for (i in 0..10) {
            xPostDao.insert(post)
        }
    }

    @PostMapping("/signUp")
    fun signUp(user: User) {
        xUserDao.insert(user)
    }

    @PostMapping("/sendPost")
    fun insertPost(post: Post): ResponseReturn {
        xPostDao.insert(post)
        return ResponseReturn.success(null)
    }

    @PostMapping("/sendComment")
    fun sendComment(comment: Comment) {
        xCommentDao.insert(comment)
    }

    @PostMapping("/sendReply")
    fun sendReply(reply: Reply) {
        xReplyDao.insert(reply)
    }

    @PostMapping("/deletePost")
    fun deletePost(postId: Long) {
        xPostDao.delete(postId)
    }

    @PostMapping("/deleteComment")
    fun deleteComment(commentId: Long) {
        xCommentDao.delete(commentId)
    }

    @PostMapping("/deleteReply")
    fun deleteReply(replyId: Long) {
        xReplyDao.delete(replyId)
    }

    @GetMapping("/getPosts")
    fun getPosts(barId: Int, page: Int): ResponseReturn {
        var postList = xPostDao.selectList(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc()))
        for (post in postList) {
            if (post.content.length > summaryLength) {
                post.content = post.content.substring(0, summaryLength)
            } else {
                post.content = post.content
            }
        }
        return ResponseReturn.success(postList)
    }

    @GetMapping("/getPostDetail")
    fun getPostDetail(postId: Int): ResponseReturn {
        var post = xPostDao.selectOne(ImmutableMap.of("id", postId) as MutableMap<String, Any>)
        return ResponseReturn.success(post)
    }

    @GetMapping("/getComment")
    fun getComment(commentId: Int) {
        var comment = xCommentDao.selectOne(ImmutableMap.of("id", commentId) as MutableMap<String, Any>)
        ResponseReturn.success(comment)
    }

    @GetMapping("/getCommentList")
    fun getComments(postId: Int, page: Int): ResponseReturn {
        var commentList = xCommentDao.selectList(ImmutableMap.of(ImmutableMap.of("post_id", postId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        for ((index, comment) in commentList.withIndex()) {
            var user = xUserDao.selectOne(ImmutableMap.of("id", comment.user.id) as MutableMap<String, Any>)
            commentList[index].user = user
        }
        return ResponseReturn.success(commentList)
    }

    @GetMapping("/getReply")
    fun getReply(commentId: Int, page: Int): ResponseReturn {
        var replys = xReplyDao.selectList(ImmutableMap.of(ImmutableMap.of("comment_id", commentId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        for ((index, reply) in replys.withIndex()) {
            var user = xUserDao.selectOne(ImmutableMap.of("id", reply.user.id) as MutableMap<String, Any>)
            reply.reply_user = user
            replys[index] = reply
        }
        return ResponseReturn.success(replys)
    }

    @GetMapping("/getMyUser")
    fun getUser(): ResponseReturn {
        return ResponseReturn.success(xUserDao.selectOne(ImmutableMap.of("id", "2") as MutableMap<String, Any>))
    }
}