package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.*
import com.pigutu.app.mapper.*
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import org.apache.http.HttpRequest
import org.apache.http.util.TextUtils
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/v1/image")
class ImageSetListController {
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
    private val request:HttpServletRequest?=null

    //最新图片
    @GetMapping("/last")
    @ResponseBody
    fun lastImage(page: Int): ResponseReturn {
        var map: HashMap<String, List<ImageSetListEntity>> = HashMap()
        var imageList = ArrayList<ImageSetListEntity>()
        for (config in configDao!!.config) {
            imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", config.value) as Map<String, Any>?))
        }
        map.put("carousel", imageList)
        var imageSetList = imageSetListDao!!.timeDesc(page, 20)
        var userId = request!!.getHeader("userId")
        if(!TextUtils.isEmpty(userId)){
            for ((index, imageSet) in imageSetList.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        map.put("last", imageSetList)
        return ResponseReturn.success(map)
    }

    //全部分类
    @GetMapping("/allCategory")
    @ResponseBody
    fun allCategory(): ResponseReturn {
        return ResponseReturn.success(categoryDao?.selectAll())
    }

    //分类
    @GetMapping("/category")
    @ResponseBody
    fun category(category: String, page: Int): ResponseReturn {
        var userId = request!!.getHeader("userId")
        var imageSetList = imageSetListDao?.findByCategory(category, page, 20)
        if(!TextUtils.isEmpty(userId)){
            for ((index, imageSet) in imageSetList!!.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        return ResponseReturn.success(imageSetList)
    }

    //图集详细,评论数据库存id，用转名字
    @GetMapping("/detail")
    @ResponseBody
    fun detail(id: Int): ResponseReturn {
        imageSetListDao?.addLikeCount(id)
        var userId = request!!.getHeader("userId")
        var imageSet = imageSetListDao!!.selectOne(ImmutableMap.of("id", id) as MutableMap<String, Any>)
        if(!TextUtils.isEmpty(userId)){
            if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                imageSet.isLike = 1
            }
        }
        var commentList = commentDao!!.selectList(ImmutableMap.of("imageId",id) as MutableMap<String, Any>, QueryCondition().setPaging(1, 20).setOrderBy(OrderBy("id").desc()))
        for((index,comment) in commentList.withIndex()){
            var userEntity = userDao!!.selectOne(ImmutableMap.of("id",comment.fromUser) as MutableMap<String, Any>)
            comment.fromUserString = userEntity.name
            comment.icon = userEntity.icon
            if(comment.toUser !=0){
                var toUserEntity = userDao!!.selectOne(ImmutableMap.of("id",comment.toUser) as MutableMap<String, Any>)
                comment.toUserString = toUserEntity.name
            }
            commentList[index] = comment
        }
        return ResponseReturn.success(ImageSetResponse(imageSet,imageSetDao?.selectList(ImmutableMap.of("allImagesId", id)  as MutableMap<String, Any>),commentList))
    }

    //搜索
    @GetMapping("/search")
    @ResponseBody
    fun search(keyword: String, page: Int): ResponseReturn {
        var keywordEntity = keywordDao!!.selectOne(ImmutableMap.of("name", keyword) as Map<String, Object>)
        //热门搜索增加或加1
        if (keywordEntity == null) {
            var insertKey = KeywordEntity()
            insertKey.count = 1
            insertKey.name = keyword
            keywordDao.insert(insertKey)
        } else {
            keywordEntity.count = keywordEntity.count!!.plus(1)
            keywordDao.update(keywordEntity)
        }
        var imageSetList = imageSetListDao!!.search(keyword, page)
        var userId = request!!.getHeader("userId")
        if(!TextUtils.isEmpty(userId)){
            for ((index, imageSet) in imageSetList.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        return ResponseReturn.success(imageSetList)
    }

    //查询关键词
    @GetMapping("/getKeyword")
    @ResponseBody
    fun getKeyword(page: Int): ResponseReturn {
        return ResponseReturn.success(keywordDao!!.selectList(Maps.newHashMap(), QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("count").desc())))
    }


}