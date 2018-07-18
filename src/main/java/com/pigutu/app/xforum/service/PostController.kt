package com.pigutu.app.xforum.service

import com.google.common.collect.ImmutableMap
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.xforum.dao.CommentDao
import com.pigutu.app.xforum.dao.PostDao
import com.pigutu.app.xforum.dao.UserDao
import com.xforum.library.model.PostDetail
import com.xforum.library.model.PostList
import com.xforum.library.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class PostController {
    @Autowired
    private lateinit var userDao:UserDao
    @Autowired
    private lateinit var postDao: PostDao
    @Autowired
    private lateinit var commentDao: CommentDao

    @PostMapping("/signUp")
    fun signUp(user: User){
        userDao.insert(user)
    }

    @PostMapping("/sendPost")
    fun insertPost(postDetail:PostDetail){
        postDao.insert(postDetail)
    }

    @PostMapping("/getPosts")
    fun getPosts(barId:Int,page:Int):ArrayList<PostList>{
        var postDetailList = postDao.selectList(ImmutableMap.of(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        var postLists = ArrayList<PostList>()
        for(postDetail in postDetailList){
            var postList = PostList()
        }
        return postLists
    }

    @PostMapping("/getPost")
    fun getPost(postId:Int):ArrayList<PostList>{
        var postDetailList = postDao.selectList(ImmutableMap.of(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(1, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        var postLists = ArrayList<PostList>()
        for(postDetail in postDetailList){
            var postList = PostList()
        }
        return postLists
    }

    @PostMapping("/getPost")
    fun getPost(barId:Int,postId:Int):ArrayList<PostList>{
        var postDetailList = postDao.selectList(ImmutableMap.of(ImmutableMap.of("bar_id", barId) as MutableMap<String, Any>, QueryCondition().setPaging(1, 20).setOrderBy(OrderBy("id").desc())) as MutableMap<String, Any>)
        var postLists = ArrayList<PostList>()
        for(postDetail in postDetailList){
            var postList = PostList()
        }
        return postLists
    }

    @GetMapping("/getComment")
    fun getComment(commentId:Int){
        var comment = commentDao.selectOne(ImmutableMap.of("id",commentId) as MutableMap<String, Any>)
        var commentList = commentDao.selectList(ImmutableMap.of("parentId",commentId) as MutableMap<String, Any>)

    }

    @GetMapping("/getComments")
    fun getComments(parentId:Int,page:Int){
        var
    }


}