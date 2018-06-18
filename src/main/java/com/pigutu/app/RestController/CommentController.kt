package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.*
import com.pigutu.app.exception.ErrorCode
import com.pigutu.app.mapper.*
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
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
    private val categoryDao: CategoryDao? = null
    @Autowired
    private val imageSetListDao: ImageSetListDao? = null
    @Autowired
    private val imageSetDao: ImageSetDao? = null
    @Autowired
    private val configDao: ConfigDao? = null
    @Autowired
    private val upgradeDao: UpgradeDao? = null
    @Autowired
    private val keywordDao: KeywordDao? = null
    @Autowired
    private val commentDao: CommentDao? = null
    @Autowired
    private val collectDao: CollectDao? = null
    @Autowired
    private val userDao: UserDao? = null
    @Autowired
    private val request: HttpServletRequest? = null

    //最新评论,一般第二页开始，因为第一页默认图片详情带了
    @GetMapping("/getComment")
    @ResponseBody
    fun getComment(imageId: Int,page:Int): ResponseReturn {
        return ResponseReturn.success(commentDao!!.selectList(ImmutableMap.of("imageId", imageId) as MutableMap<String, Any>, QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("id").desc())))
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
        var commentEntity = CommentEntity()
        commentEntity.toUser = fromUserId
        if (!TextUtils.isEmpty(toUserName)) {
            var userEntity = userDao!!.selectOne(ImmutableMap.of("name", toUserName) as MutableMap<String, Any>)
            commentEntity.toUser = userEntity.id as Int
        }
        commentEntity.imageId = imageId
        commentEntity.content = content
        commentEntity.time = Date(System.currentTimeMillis())
        return ResponseReturn.success(null)
    }

    //删除评论
    @PostMapping("/deleteComment")
    @ResponseBody
    fun deleteComment(commentId:Int): ResponseReturn {
        var userId = commentDao!!.selectOne(ImmutableMap.of("id",commentId) as MutableMap<String, Any>).fromUser
        if(request!!.getHeader("userId")!=userId.toString()){
            return ResponseReturn.error(ErrorCode.TOKEN_FAIL)
        }
        commentDao.delete(commentId as Long)
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