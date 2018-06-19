package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.*
import com.pigutu.app.exception.ErrorCode
import com.pigutu.app.mapper.*
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.shiro.JWTUtil
import com.pigutu.app.utils.TimeUtils
import lombok.extern.slf4j.Slf4j
import org.apache.http.util.TextUtils
import org.apache.shiro.authz.annotation.RequiresAuthentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest
import java.sql.Date
import java.util.ArrayList

@Controller
@Slf4j
@RequestMapping("/v1/account")
class UserController {
    @Autowired
    lateinit var commentDao: CommentDao

    @Autowired
    lateinit var userDao: UserDao

    @Autowired
    lateinit var imageSetListDao: ImageSetListDao

    @Autowired
    lateinit var collectDao: CollectDao

    @Autowired
    lateinit var configDao: ConfigDao


    @PostMapping("/register")
    @ResponseBody
    fun register(icon: String, name: String, pwd: String): ResponseReturn {

        val userEntity = userDao!!.selectOne(ImmutableMap.of<String, Any>("name", name))
        if (userEntity != null) {
            return ResponseReturn.error(ErrorCode.HAS_REGISTER)
        }
        val insertUser = UserEntity()
        insertUser.icon = icon
        insertUser.name = name
        insertUser.pwd = pwd
        insertUser.point=0
        insertUser.vip=0
        userDao!!.insert(insertUser)
        val userResponse = UserResponse()
        userResponse.icon = insertUser.icon
        userResponse.name = insertUser.name
        userResponse.token = JWTUtil.sign(insertUser.id.toString(), pwd)
        userResponse.userId = insertUser.id!!
        return ResponseReturn.success(userResponse)
    }

    @PostMapping("/login")
    @ResponseBody
    fun login(name: String, pwd: String): ResponseReturn {
        val userEntity = userDao!!.selectOne(ImmutableMap.of<String, Any>("name", name))
        if (userEntity == null || userEntity.pwd != pwd) {
            return ResponseReturn.error(ErrorCode.PWD_ERROR)
        }
        val userResponse = UserResponse()
        userResponse.icon = userEntity.icon
        userResponse.name = userEntity.name
        userResponse.token = JWTUtil.sign(userEntity.id.toString(), pwd)
        userResponse.userId = userEntity.id!!
        return ResponseReturn.success(userResponse)
    }

    @PostMapping("/collect")
    @ResponseBody
    fun collect(request: HttpServletRequest, userId: String, imageId: String): ResponseReturn {
        val token = request.getHeader("token")
        //log.debug("token=" + token + ",username=" + JWTUtil.getUsername(token))
        if (token == null || JWTUtil.getUsername(token) != userId) {
            return ResponseReturn.error(401)
        }
        var collectEntity: CollectEntity? = collectDao!!.selectOne(ImmutableMap.of<String, Any>("user_id", userId, "image_id", imageId))
        if (collectEntity == null) {
            collectEntity = CollectEntity()
            collectEntity.userId = java.lang.Long.valueOf(userId)
            collectEntity.imageId = java.lang.Long.valueOf(imageId)
            collectEntity.time = Date(System.currentTimeMillis())
            collectDao!!.insert(collectEntity)
            return ResponseReturn.success(null)
        } else {
            return ResponseReturn.error(ErrorCode.HAS_COLLECT)
        }
    }

    @PostMapping("/removeCollect")
    @ResponseBody
    fun removeCollect(request: HttpServletRequest, userId: String, imageId: String): ResponseReturn {
        val token = request.getHeader("token")
        //log.debug("token=" + token + ",username=" + JWTUtil.getUsername(token))
        if (token == null || JWTUtil.getUsername(token) != userId) {
            return ResponseReturn.error(401)
        }
        val collectEntity = collectDao!!.selectOne(ImmutableMap.of<String, Any>("userId", userId, "imageId", imageId))
        if (collectEntity == null) {
            return ResponseReturn.error(ErrorCode.NO_COLLECT)
        } else {
            collectDao!!.delete(collectEntity.id)
            return ResponseReturn.success(null)
        }
    }


    @GetMapping("/getCollect")
    @ResponseBody
    fun getCollect(request: HttpServletRequest, userId: String, page: Int): ResponseReturn {
        val token = request.getHeader("token")
        //log.debug("token=" + token + ",username=" + JWTUtil.getUsername(token))
        if (token == null || JWTUtil.getUsername(token) != userId) {
            return ResponseReturn.error(401)
        }
        val collectEntities = collectDao!!.selectList(ImmutableMap.of<String, Any>("userId", userId), QueryCondition().setPaging(page, 60))
        val longs = ArrayList<Long>()
        for (collectEntity in collectEntities) {
            longs.add(collectEntity.imageId)
        }
        val imageSetList = imageSetListDao!!.selectList(longs)
        if (!TextUtils.isEmpty(userId)) {
            for (i in imageSetList.indices) {
                if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSetList[i].id!!)) != null) {
                    imageSetList[i].isLike = 1
                }
            }
        }
        return ResponseReturn.success(imageSetList)
    }


    /*    @PostMapping("/postComment")
    @ResponseBody
    public ResponseReturn comment(int fromUserId, String toUserName, int imageId, String content) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setFromUser(fromUserId);
        if(TextUtils.isEmpty(toUserName)){
            commentEntity.setToUser(0);
        }else{
            commentEntity.setToUser((Integer.valueOf(userDao.selectOne(ImmutableMap.of("name", toUserName)).getId().toString())));
        }
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
    }*/

    @PostMapping("/changePwd")
    @ResponseBody
    fun changePwd(request: HttpServletRequest, userId: String, oldPwd: String, newPwd: String): ResponseReturn {
        val token = request.getHeader("token")
        //log.debug("token=" + token + ",username=" + JWTUtil.getUsername(token))
        if (token == null || JWTUtil.getUsername(token) != userId) {
            return ResponseReturn.error(401)
        }
        val userEntity = userDao!!.selectOne(ImmutableMap.of<String, Any>("userId", userId))
        if (userEntity.pwd == oldPwd) {
            userDao!!.update(java.lang.Long.valueOf(userId), ImmutableMap.of<String, Any>("pwd", newPwd))
        } else {
            ResponseReturn.error(ErrorCode.OLD_PWD_ERROR)
        }
        return ResponseReturn.success(null)
    }

    @PostMapping("/changeIcon")
    @ResponseBody
    fun changeIcon(userId: Long, userIconUrl: String): ResponseReturn {
        var userEntity = userDao.selectOne(ImmutableMap.of("id",userId) as MutableMap<String, Any>)
        userEntity.icon = userIconUrl
        userDao.update(userEntity)
        return ResponseReturn.success(null)
    }

}
