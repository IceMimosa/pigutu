package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.*;
import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.mapper.CollectDao;
import com.pigutu.app.mapper.CommentDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.mapper.UserDao;
import com.pigutu.app.utils.JwtHelper;
import com.pigutu.app.utils.RedisTokenHelper;
import com.pigutu.app.utils.TimeUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class UserController {
    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    CollectDao collectDao;

    @Resource
    private RedisTokenHelper redisTokenHelper;


    @PostMapping("/register")
    @ResponseBody
    public Response register(String icon, String name, String pwd) {

        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name", name));
        if (userEntity != null) {
            return Response.error(ErrorCode.HAS_REGISTER);
        }
        UserEntity insertUser = new UserEntity();
        insertUser.setIcon(icon);
        insertUser.setName(name);
        insertUser.setPwd(pwd);
        insertUser.setPoint(0);
        insertUser.setVip(0);
        userDao.select(userDao.insert(insertUser)).getId();
        redisTokenHelper.getObject(RedisTokenHelper.KEY_USER);
        redisTokenHelper.save(RedisTokenHelper.KEY_USER, JwtHelper.createJWT(name));
        return Response.success(null);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String userId, String pwd) {
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("id", userId));
        if (userEntity == null || !userEntity.getPwd().equals(pwd)) {
            return Response.error(ErrorCode.HAS_REGISTER);
        }
        return Response.success(userEntity);
    }

    @PostMapping("/getCollect")
    @ResponseBody
    @RequiresAuthentication
    public void collect(String userId, String imageId) {
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("userId",userId,"imageId",imageId));
        if(collectEntity==null){
            collectEntity = new CollectEntity();
            collectEntity.setUserId(userId);
            collectEntity.setImageId(imageId);
            collectEntity.setTime(new Timestamp(System.currentTimeMillis()));
            collectDao.insert(collectEntity);
            Response.success(null);
        }else{
            Response.error(ErrorCode.HAS_COLLECT);
        }
    }

    @PostMapping("/removeCollect")
    @ResponseBody
    public void removeCollect(String userId, String imageId) {
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("userId",userId,"imageId",imageId));
        if(collectEntity==null){
            Response.error(ErrorCode.NO_COLLECT);
        }else{
            collectDao.delete(collectEntity.getId());
            Response.success(null);
        }
    }

    @GetMapping("/getCollect")
    @ResponseBody
    public Response getCollect(String userId) {
        List<CollectEntity> collectEntities = collectDao.selectList(ImmutableMap.of("userId",userId));
        List<Long> longs = new ArrayList<>();
        for(CollectEntity collectEntity:collectEntities){
            longs.add(collectEntity.getId());
        }
        return Response.success(imageSetListDao.selectList(longs));
    }

    @PostMapping("/postComment")
    @ResponseBody
    public void comment(String fromUser, String toUser, String imageId, String content) {
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
    public List<CommentEntity> getComment(String imageId) {
        return commentDao.selectList(ImmutableMap.of("imageId", imageId));
    }
}
