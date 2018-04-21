package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.CommentEntity;
import com.pigutu.app.mapper.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    CommentDao commentDao;
    public void register(String name,String pwd){

    }

    public void login(String name,String pwd){

    }

    public void collect(String userId,String imageId){

    }

    public void getCollect(String userId){

    }

    @PostMapping("/postComment")
    @ResponseBody
    public void comment(String fromUser,String toUser,String imageId,String content){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setFromUser(fromUser);
        commentEntity.setToUser(toUser);
        commentEntity.setImageId(Integer.valueOf(imageId));
        commentEntity.setContent(content);
        commentDao.insert(commentEntity);
    }

    @GetMapping("/getComment")
    @ResponseBody
    public List<CommentEntity> getComment(String imageId){
        return commentDao.selectList(ImmutableMap.of("imageId",imageId));
    }
}
