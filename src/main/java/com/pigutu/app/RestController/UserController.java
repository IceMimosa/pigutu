package com.pigutu.app.RestController;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.*;
import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.mapper.*;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.shiro.JWTUtil;
import com.pigutu.app.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
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
    public ResponseReturn register(String icon, String name, String pwd) {

        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name", name));
        if (userEntity != null) {
            return ResponseReturn.error(ErrorCode.HAS_REGISTER);
        }
        UserEntity insertUser = new UserEntity();
        insertUser.setIcon(icon);
        insertUser.setName(name);
        insertUser.setPwd(pwd);
        insertUser.setPoint(0);
        insertUser.setVip(0);
        userDao.insert(insertUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setIcon(insertUser.getIcon());
        userResponse.setName(insertUser.getName());
        userResponse.setToken(JWTUtil.sign(String.valueOf(insertUser.getId()), pwd));
        userResponse.setUserId(insertUser.getId());
        return ResponseReturn.success(userResponse);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseReturn login(String name, String pwd) {
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name", name));
        if (userEntity == null || !userEntity.getPwd().equals(pwd)) {
            return ResponseReturn.error(ErrorCode.PWD_ERROR);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setIcon(userEntity.getIcon());
        userResponse.setName(userEntity.getName());
        userResponse.setToken(JWTUtil.sign(String.valueOf(userEntity.getId()), pwd));
        userResponse.setUserId(userEntity.getId());
        return ResponseReturn.success(userResponse);
    }

    @PostMapping("/collect")
    @ResponseBody
    public ResponseReturn collect(HttpServletRequest request,String userId,String imageId) {
        String token = request.getHeader("token");
        log.debug("token="+token+",username="+JWTUtil.getUsername(token));
        if (token == null || !JWTUtil.getUsername(token).equals(userId)) {
            return ResponseReturn.error(401);
        }
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("user_id", userId, "image_id", imageId));
        if (collectEntity == null) {
            collectEntity = new CollectEntity();
            collectEntity.setUserId(Long.valueOf(userId));
            collectEntity.setImageId(Long.valueOf(imageId));
            collectEntity.setTime(new Date(System.currentTimeMillis()));
            collectDao.insert(collectEntity);
            return ResponseReturn.success(null);
        } else {
            return ResponseReturn.error(ErrorCode.HAS_COLLECT);
        }
    }

    @PostMapping("/removeCollect")
    @ResponseBody
    public ResponseReturn removeCollect(HttpServletRequest request,String userId, String imageId) {
        String token = request.getHeader("token");
        log.debug("token="+token+",username="+JWTUtil.getUsername(token));
        if (token == null || !JWTUtil.getUsername(token).equals(userId)) {
            return ResponseReturn.error(401);
        }
        CollectEntity collectEntity = collectDao.selectOne(ImmutableMap.of("userId", userId, "imageId", imageId));
        if (collectEntity == null) {
            return ResponseReturn.error(ErrorCode.NO_COLLECT);
        } else {
            collectDao.delete(collectEntity.getId());
            return ResponseReturn.success(null);
        }
    }


    @GetMapping("/getCollect")
    @ResponseBody
    public ResponseReturn getCollect(HttpServletRequest request,String userId, int page) {
        String token = request.getHeader("token");
        log.debug("token="+token+",username="+JWTUtil.getUsername(token));
        if (token == null || !JWTUtil.getUsername(token).equals(userId)) {
            return ResponseReturn.error(401);
        }
        List<CollectEntity> collectEntities = collectDao.selectList(ImmutableMap.of("userId", userId), new QueryCondition().setPaging(page, 60));
        List<Long> longs = new ArrayList<>();
        for (CollectEntity collectEntity : collectEntities) {
            longs.add(collectEntity.getImageId());
        }
        List<ImageSetListEntity> imageSetList = imageSetListDao.selectList(longs);
        if(!TextUtils.isEmpty(userId)){
            for (int i=0;i<imageSetList.size();i++) {
                if (collectDao.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSetList.get(i).getId())) != null) {
                    imageSetList.get(i).setIsLike(1);
                }
            }
        }
        return ResponseReturn.success(imageSetList);
    }


    @PostMapping("/postComment")
    @ResponseBody
    public ResponseReturn comment(int fromUserId, int toUserId, int imageId, String content) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setFromUser(userDao.selectOne(ImmutableMap.of("id", fromUserId)).getName());
        commentEntity.setToUser(userDao.selectOne(ImmutableMap.of("id", toUserId)).getName());
        commentEntity.setImageId(imageId);
        commentEntity.setContent(content);
        commentEntity.setTime(TimeUtils.Companion.getNowTime());
        commentDao.insert(commentEntity);
        return ResponseReturn.success(null);
    }

    @GetMapping("/getComment")
    @ResponseBody
    public ResponseReturn getComment(String imageId) {
        return ResponseReturn.success(commentDao.selectList(ImmutableMap.of("imageId", imageId)));
    }

}
