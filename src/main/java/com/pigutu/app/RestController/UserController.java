package com.pigutu.app.RestController;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.*;
import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.mapper.*;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.shiro.JWTUtil;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/account")
public class UserController {
    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    CollectDao collectDao;

    @Autowired
    ConfigDao configDao;


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
        return Response.success(null);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String name, String pwd) {
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name", name));
        if (userEntity == null || !userEntity.getPwd().equals(pwd)) {
            return Response.error(ErrorCode.PWD_ERROR);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setIcon(userEntity.getIcon());
        userResponse.setName(userEntity.getName());
        userResponse.setToken(JWTUtil.sign(String.valueOf(userEntity.getId()), pwd));
        userResponse.setUserId(userEntity.getId());
        return Response.success(userResponse);
    }

    @PostMapping("/collect")
    @ResponseBody
    @RequiresAuthentication
    public void collect(Long userId, Long imageId) {
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("user_id",userId,"image_id",imageId));
        if(collectEntity==null){
            collectEntity = new CollectEntity();
            collectEntity.setUserId(userId);
            collectEntity.setImageId(imageId);
            collectEntity.setTime(new Date(System.currentTimeMillis()));
            collectDao.insert(collectEntity);
            Response.success(null);
        }else{
            Response.error(ErrorCode.HAS_COLLECT);
        }
    }

    @PostMapping("/removeCollect")
    @ResponseBody
    @RequiresAuthentication
    public void removeCollect(String userId, String imageId) {
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("userId",userId,"imageSetId",imageId));
        if(collectEntity==null){
            Response.error(ErrorCode.NO_COLLECT);
        }else{
            collectDao.delete(collectEntity.getId());
            Response.success(null);
        }
    }


    @GetMapping("/getCollect")
    @ResponseBody
    public Response getCollect(String userId,int page) {
        List<CollectEntity> collectEntities = collectDao.selectList(ImmutableMap.of("userId",userId),new QueryCondition().setPaging(page,60));
        List<Long> longs = new ArrayList<>();
        for(CollectEntity collectEntity:collectEntities){
            longs.add(collectEntity.getImageId());
        }
        return Response.success(imageSetListDao.selectList(longs));
    }


    @PostMapping("/postComment")
    @ResponseBody
    public void comment(int fromUserId, int toUserId, int imageId, String content) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setFromUser(userDao.selectOne(ImmutableMap.of("id",fromUserId)).getName());
        commentEntity.setToUser(userDao.selectOne(ImmutableMap.of("id",toUserId)).getName());
        commentEntity.setImageId(imageId);
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
