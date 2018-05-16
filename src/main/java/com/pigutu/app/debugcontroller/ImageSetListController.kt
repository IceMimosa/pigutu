package com.pigutu.app.debugcontroller

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.ImageSetListEntity
import com.pigutu.app.entity.KeywordEntity
import com.pigutu.app.entity.ResponseReturn
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
@RequestMapping("/de/image")
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
    private val collectDao: CollectDao? = null
    @Autowired
    private val request: HttpServletRequest? = null

    //最新图片
    @GetMapping("/last")
    @ResponseBody
    fun lastImage(page: Int): ResponseReturn {
        var map: HashMap<String, List<ImageSetListEntity>> = HashMap()
        var imageList = ArrayList<ImageSetListEntity>()
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 5067) as Map<String, Any>?))
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 102) as Map<String, Any>?))
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 3205) as Map<String, Any>?))
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 3348) as Map<String, Any>?))
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 3924) as Map<String, Any>?))
        imageList.add(imageSetListDao!!.selectOne(ImmutableMap.of("id", 3078) as Map<String, Any>?))
        map.put("carousel", imageList)
        var imageSetList = imageSetListDao!!.selectList(
                ImmutableMap.of<String, Any>("category", "明星", "category", "清纯", "hide", 0),
                QueryCondition()
                        .setPaging(page, 20)
                        .setOrderBy(OrderBy("createTime").desc()))
        var userId = request!!.getHeader("userId")
        if (!TextUtils.isEmpty(userId)) {
            for ((index, imageSet) in imageSetList.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("category", "明星", "category", "清纯", "userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        map.put("last", imageSetListDao!!.timeDesc(page, 20))
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
        if (!TextUtils.isEmpty(userId)) {
            for ((index, imageSet) in imageSetList!!.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("category", "明星", "category", "清纯", "userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
                    imageSet.isLike = 1
                    imageSetList[index] = imageSet
                }
            }
        }
        return ResponseReturn.success(imageSetList)
    }

    //图集详细
    @GetMapping("/detail")
    @ResponseBody
    fun detail(id: Int): ResponseReturn {
        imageSetListDao?.addLikeCount(id)
        var map: HashMap<String, Object>? = null
        map!!.put("detail", imageSetListDao!!.selectOne(ImmutableMap.of("id", id) as Map<String, Object>) as Object)
        map!!.put("images", imageSetDao?.selectList(ImmutableMap.of("allImagesId", id) as Map<String, Any>?) as Object)
        return ResponseReturn.success(imageSetDao?.selectList(ImmutableMap.of("allImagesId", id) as Map<String, Any>?))
    }

    //搜索
    @GetMapping("/search")
    @ResponseBody
    fun search(keyword: String, page: Int): ResponseReturn {
        var keywordEntity = keywordDao!!.selectOne(ImmutableMap.of("category","明星","category","清纯","name", keyword) as Map<String, Object>)
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
        if (!TextUtils.isEmpty(userId)) {
            for ((index, imageSet) in imageSetList.withIndex()) {
                if (collectDao!!.selectOne(ImmutableMap.of("category","明星","category","清纯","userId", userId, "imageId", imageSet.id) as Map<String, Object>) != null) {
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
        var keyList = ArrayList<KeywordEntity>()
        var key1 = KeywordEntity()
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        key1.id=1;
        key1.name=""
        key1.count=100
        keyList.add(key1)
        return ResponseReturn.success(keyList)
    }


}