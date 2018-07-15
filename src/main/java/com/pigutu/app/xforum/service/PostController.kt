package com.pigutu.app.xforum.service

import com.pigutu.app.xforum.dao.CommentDao
import com.pigutu.app.xforum.dao.PostDao
import com.pigutu.app.xforum.dao.UserDao
import com.xforum.library.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
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
        userDao.
    }
}