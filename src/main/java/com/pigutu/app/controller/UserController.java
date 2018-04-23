package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.CommentEntity;
import com.pigutu.app.entity.Response;
import com.pigutu.app.entity.UserEntity;
import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.mapper.CommentDao;
import com.pigutu.app.mapper.UserDao;
import com.pigutu.app.utils.TimeUtils;
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

    @Autowired
    UserDao userDao;

    @PostMapping("/register")
    @ResponseBody
    public Response register(String icon,String name, String pwd){
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name",name));
        if(userEntity == null){
            return Response.error(ErrorCode.HAS_REGISTER);
        }
        UserEntity insertUser = new UserEntity();
        insertUser.setIcon(icon);
        insertUser.setName(name);
        insertUser.setPwd(pwd);
        insertUser.setPoint(0);
        insertUser.setVip(0);
        userDao.insert(insertUser);
        return Response.success(null);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String name,String pwd){
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name",name));
        if(userEntity == null){
            return Response.error(ErrorCode.HAS_REGISTER);
        }
        return Response.success(null);
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
        commentEntity.setTime(TimeUtils.Companion.getNowTime());
        commentDao.insert(commentEntity);
    }

    @GetMapping("/getComment")
    @ResponseBody
    public List<CommentEntity> getComment(String imageId){
        return commentDao.selectList(ImmutableMap.of("imageId",imageId));
    }
}
