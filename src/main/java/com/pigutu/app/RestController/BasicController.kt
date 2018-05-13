package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import com.pigutu.app.entity.Response
import com.pigutu.app.mapper.ConfigDao
import com.pigutu.app.mapper.KeywordDao
import com.pigutu.app.mapper.UpgradeDao
import com.pigutu.app.mapper.mybatis.OrderBy
import com.pigutu.app.mapper.mybatis.QueryCondition
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
    //查询更新
    @GetMapping("/queryConfig")
    @ResponseBody
    fun queryConfig(): Response {
        var configList = configDao!!.selectAll()
        var mapList:ArrayList<ImmutableMap<String,String>> = ArrayList()
        for(config in configList){
            var map = ImmutableMap.of(config.key,config.value)
            mapList.add(map)
        }
        return Response.success(mapList)
    }

    //查询关键词
    @GetMapping("/getKeyword")
    @ResponseBody
    fun getKeyword(page:Int):Response{
        return Response.success(keywordDao!!.selectList(Maps.newHashMap(), QueryCondition().setPaging(page,20).setOrderBy(OrderBy("count").desc())))
    }

    //查询更新
    @GetMapping("/upgrade")
    @ResponseBody
    fun upgrade(): Response {
        var upgradeList = upgradeDao!!.selectAll()
        if (upgradeList == null || upgradeList.size == 0) return Response.success(null)
        return Response.success(upgradeList[upgradeList.size - 1])
    }
}