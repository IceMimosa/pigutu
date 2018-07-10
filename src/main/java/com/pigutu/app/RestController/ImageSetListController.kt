package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.debugcontroller.ForStoreController
import com.pigutu.app.entity.*
import com.pigutu.app.mapper.*
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import com.pigutu.app.utils.ChannelHelper
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
    private lateinit var request: HttpServletRequest

    //最新图片
    @GetMapping("/last")
    @ResponseBody
    fun lastImage(page: Int): ResponseReturn {
        var map: HashMap<String, List<ImageSetListEntity>> = HashMap()
        var imageList = ArrayList<ImageSetListEntity>()
        var channel = request.getHeader("channel")
        var forChannel = "0"
        if (!TextUtils.isEmpty(channel)) {
            var configEntity = configDao.selectOne(ImmutableMap.of("key", channel) as MutableMap<String, Any>)
            if (configEntity != null) {
                forChannel = configEntity.value
            }
        }
        configDao!!.config
                .filter { it.key.startsWith("hot") }
                .mapTo(imageList) { imageSetListDao!!.selectOne(ImmutableMap.of("id", it.value) as Map<String, Any>?) }
        map.put("carousel", imageList)
        var imageSetList: List<ImageSetListEntity>
        if (forChannel == "1") {
            imageSetList = imageSetListDao.getForChannelLastList("明星", "清纯", page - 1)
            map.put("carousel", imageSetList.asReversed())
        } else {
            imageSetList = imageSetListDao.timeDesc(page, 20)
        }
        var userId = request.getHeader("userId")
        if (!TextUtils.isEmpty(userId)) {
            for ((index, imageSet) in imageSetList.withIndex()) {
                if (collectDao.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
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
        var categoryList = categoryDao?.selectAll() as ArrayList<CategoryEntity>
        var channel = request.getHeader("channel")
        var forChannel = "0"
        if (!TextUtils.isEmpty(channel)) {
            var configEntity = configDao.selectOne(ImmutableMap.of("key", channel) as MutableMap<String, Any>)
            if (configEntity != null) {
                forChannel = configEntity.value
            }
        }
        if (forChannel == "1") {
            categoryList
                    .filter { it.parameter != "明星" && it.parameter != "清纯" }
                    .forEach {
                        categoryList.remove(it)
                    }
        }
        return ResponseReturn.success(categoryList)
    }

    //分类
    @GetMapping("/category")
    @ResponseBody
    fun category(category: String, page: Int): ResponseReturn {
        var userId = request!!.getHeader("userId")
        var imageSetList = imageSetListDao?.findByCategory(category, page, 20)
        if (!TextUtils.isEmpty(userId)) {
            for ((index, imageSet) in imageSetList!!.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        return ResponseReturn.success(imageSetList)
    }

    //分类
    @GetMapping("/categoryByLikeCount")
    @ResponseBody
    fun categoryByLikeCount(category: String, page: Int): ResponseReturn {
        var userId = request!!.getHeader("userId")
        var imageSetList = imageSetListDao?.findByCategoryByLikeCount(category, page, 20)
        if (!TextUtils.isEmpty(userId)) {
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
    //todo 遗留问题，游客0 代表不是回复的，游客是否可以被回复？？？回复了，游客也收不到，但是显示不好看
    @GetMapping("/detail")
    @ResponseBody
    fun detail(id: Int): ResponseReturn {
        imageSetListDao?.addLikeCount(id)
        var userId = request!!.getHeader("userId")
        var imageSet = imageSetListDao!!.selectOne(ImmutableMap.of("id", id) as MutableMap<String, Any>)
        if (!TextUtils.isEmpty(userId)) {
            if (collectDao!!.selectOne(ImmutableMap.of("userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                imageSet.isLike = 1
            }
        }
        var commentList = commentDao!!.selectList(ImmutableMap.of("imageId", id) as MutableMap<String, Any>, QueryCondition().setPaging(1, 20).setOrderBy(OrderBy("id").desc()))
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
        return ResponseReturn.success(ImageSetResponse(imageSet, imageSetDao?.selectList(ImmutableMap.of("allImagesId", id) as MutableMap<String, Any>), commentList))
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
        var imageSetList:ArrayList<ImageSetListEntity>
        if(ChannelHelper.getForChannel(request,configDao)){
            imageSetList = imageSetListDao!!.searchForChannel(keyword, page)
        }else{
            imageSetList = imageSetListDao!!.search(keyword, page)
        }
        var userId = request!!.getHeader("userId")
        if (!TextUtils.isEmpty(userId)) {
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
        if(ChannelHelper.getForChannel(request,configDao)){
           return ChannelHelper.getKeyword(page)
        }
        return ResponseReturn.success(keywordDao!!.selectList(Maps.newHashMap(), QueryCondition().setPaging(page, 20).setOrderBy(OrderBy("count").desc())))
    }


}