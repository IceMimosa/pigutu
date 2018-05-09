package com.pigutu.app.RestController

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.ConfigEntity
import com.pigutu.app.entity.ImageSetListEntity
import com.pigutu.app.entity.Response
import com.pigutu.app.mapper.*
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
}