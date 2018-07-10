package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.*
import com.pigutu.app.exception.ErrorCode
import com.pigutu.app.mapper.*
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.shiro.JWTUtil
import org.apache.http.HttpRequest
import org.apache.http.util.TextUtils
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.sql.Date
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/v1/comment")
class CommentController {
    @Autowired
    private lateinit var categoryDao: CategoryDao
    @Autowired
    private lateinit var imageSetListDao: ImageSetListDao
    @Autowired
    private lateinit var imageSetDao: ImageSetDao
    @Autowired
    private lateinit var configDao: ConfigDao
    @Autowired
    private lateinit var upgradeDao: UpgradeDao
    @Autowired
    private lateinit var keywordDao: KeywordDao
    @Autowired
    private lateinit var commentDao: CommentDao
    @Autowired
    private lateinit var collectDao: CollectDao
    @Autowired
    private lateinit var userDao: UserDao
    @Autowired
    private  lateinit var request: HttpServletRequest

    //最新评论,一般第二页开始，因为第一页默认图片详情带了
    @GetMapping("/getComment")
    @ResponseBody
    fun getComment(imageId: Int,page:Int): ResponseReturn {
        var commentList = commentDao!!.selectList(ImmutableMap.of("imageId", imageId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc()))
        for ((index, comment) in commentList.withIndex()) {
            if (comment.fromUser == 0L || comment.fromUser == null) {
                comment.fromUserString = "游客"
            } else {
                var userEntity = userDao!!.selectOne(ImmutableMap.of("id", comment.fromUser) as MutableMap<String, Any>)
                comment.fromUserString = userEntity.name
                comment.icon = userEntity.icon
            }
            if (comment.toUser != 0L && comment.toUser != null) {
                var toUserEntity = userDao!!.selectOne(ImmutableMap.of("id", comment.toUser) as MutableMap<String, Any>)
                comment.toUserString = toUserEntity.name
            }
            commentList[index] = comment
        }
        return ResponseReturn.success(commentList)
    }

    //发送评论
    @PostMapping("/postComment")
    @ResponseBody
    fun postComment(fromUserId: Int, toUserName: String, imageId: Int, content: String): ResponseReturn {
        if (fromUserId < 0) {
            return ResponseReturn.error(ErrorCode.NO_USER)
        }
        if (imageId < 0) {
            return ResponseReturn.error(ErrorCode.NO_IMAGE_ID)
        }
        if (TextUtils.isEmpty(content)) {
            return ResponseReturn.error(ErrorCode.NO_CONTENT)
        }
        var token = request!!.getHeader("token")
        var userId = ""
        if(token != null){
            userId = JWTUtil.getUsername(token)
        }
        var commentEntity = CommentEntity()
        commentEntity.fromUser = fromUserId.toLong()
        if(TextUtils.isEmpty(userId)){
            commentEntity.fromUser = 0
        }else{
            if(userId!=fromUserId.toString()){
                return ResponseReturn.error(401)
            }
        }
        if (!TextUtils.isEmpty(toUserName)) {
            var userEntity = userDao!!.selectOne(ImmutableMap.of("name", toUserName) as MutableMap<String, Any>)
            commentEntity.toUser = userEntity.id
        }else{
            commentEntity.toUser = 0
        }
        commentEntity.imageId = imageId
        commentEntity.content = content
        commentEntity.time = Date(System.currentTimeMillis())
        commentDao.insert(commentEntity)
        return ResponseReturn.success(null)
    }

    //删除评论
    @PostMapping("/deleteComment")
    @ResponseBody
    fun deleteComment(commentId:Long): ResponseReturn {
        var userId = commentDao!!.selectOne(ImmutableMap.of("id",commentId) as MutableMap<String, Any>).fromUser
        if(JWTUtil.getUsername(request!!.getHeader("token"))!=userId.toString()){
            return ResponseReturn.error(ErrorCode.TOKEN_FAIL)
        }
        commentDao.delete(commentId)
        return ResponseReturn.success(null)
    }

    //暂时做成无限增加喜欢的
    @PostMapping("/addCommentLike")
    @ResponseBody
    fun addCommentLike(commentId:Int): ResponseReturn {
        var commentEntity = commentDao!!.selectOne(ImmutableMap.of("id",commentId) as MutableMap<String, Any>)
        commentEntity.likeCount++
        commentDao!!.update(commentEntity)
        return ResponseReturn.success(null)
    }

}