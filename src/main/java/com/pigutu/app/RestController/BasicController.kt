package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.KeywordEntity
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.ConfigDao
import com.pigutu.app.mapper.ImageSetListDao
import com.pigutu.app.mapper.KeywordDao
import com.pigutu.app.mapper.UpgradeDao
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
import org.omg.CORBA.Object
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/5/9
 */

@Controller
@RequestMapping("/v1/basic")
class BasicController {
    @Autowired
    private val configDao: ConfigDao? = null
    @Autowired
    private val keywordDao: KeywordDao? = null
    @Autowired
    private val upgradeDao: UpgradeDao? = null
    @Autowired
    private val imageSetListDao: ImageSetListDao? = null
    //查询更新
    @GetMapping("/queryConfig")
    @ResponseBody
    fun queryConfig(): ResponseReturn {
        var configList = configDao!!.selectAll()
        var mapList:ArrayList<ImmutableMap<String,String>> = ArrayList()
        for(config in configList){
            var map = ImmutableMap.of(config.key,config.value)
            mapList.add(map)
        }
        return ResponseReturn.success(mapList)
    }

    //查询关键词
    @GetMapping("/getKeyword")
    @ResponseBody
    fun getKeyword(page:Int): ResponseReturn {
        return ResponseReturn.success(keywordDao!!.selectList(Maps.newHashMap(), QueryCondition().setPaging(page,20).setOrderBy(OrderBy("count").desc())))
    }

    //查询更新
    @GetMapping("/upgrade")
    @ResponseBody
    fun upgrade(): ResponseReturn {
        var upgradeList = upgradeDao!!.selectAll()
        if (upgradeList == null || upgradeList.size == 0) return ResponseReturn.success(null)
        return ResponseReturn.success(upgradeList[upgradeList.size - 1])
    }

    //搜索
    @GetMapping("/search")
    @ResponseBody
    fun search(keyword:String,page:Int): ResponseReturn {
        var keywordEntity = keywordDao!!.selectOne(ImmutableMap.of("name",keyword) as Map<String,Object>)
        //增加或加1 热门搜索
        if(keywordEntity==null){
            var insertKey = KeywordEntity()
            insertKey.count = 1
            insertKey.name = keyword
            keywordDao.insert(insertKey)
        }else{
            keywordEntity.count = keywordEntity.count!!.plus(1)
            keywordDao.update(keywordEntity)
        }
        var searchImg = imageSetListDao!!.search(keyword,page)
        return ResponseReturn.success(searchImg)
    }
}