package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.ImageSetListEntity
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

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
        return ResponseReturn.success(imageSetListDao?.findByCategory(category, page, 20))
    }

    //图集详细
    @GetMapping("/detail")
    @ResponseBody
    fun detail(id: Int): ResponseReturn {
        imageSetListDao?.addLikeCount(id)
        return ResponseReturn.success(imageSetDao?.selectList(ImmutableMap.of("allImagesId", id) as Map<String, Any>?))
    }


}