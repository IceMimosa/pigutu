package com.pigutu.app.RestController

import com.pigutu.app.config.GlobalConfig
import com.pigutu.app.entity.FeedbackEntity
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

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
    @Autowired
    private val feedbackDao: FeedbackDao? = null
    @Autowired
    private lateinit var request: HttpServletRequest
    //查询更新
    @GetMapping("/queryConfig")
    @ResponseBody
    fun queryConfig(): ResponseReturn {
        var configList = configDao!!.selectAll()
        var map = HashMap<String,String>()
        var channel =request.getHeader("channel")
        for(config in configList){
            map[config.key] = config.value
            if(config.key == channel){
                GlobalConfig.channelValue = config.value
            }
        }
        return ResponseReturn.successBase64(map)
    }

    //查询更新
    @GetMapping("/upgrade")
    @ResponseBody
    fun upgrade(): ResponseReturn {
        var upgradeList = upgradeDao!!.selectAll()
        if (upgradeList == null || upgradeList.size == 0) return ResponseReturn.success(null)
        return ResponseReturn.success(upgradeList[upgradeList.size - 1])
    }

    //反馈
    @PostMapping("/feedback")
    @ResponseBody
    fun feedback(content:String,contact:String): ResponseReturn {
        var userId = request?.getHeader("userId")
        var feedback = FeedbackEntity()
        feedback.contact = contact
        feedback.content = content
        feedback.userId = userId
        feedbackDao!!.insert(feedback)
        return ResponseReturn.success(null)
    }

    //查询反馈
    @GetMapping("/getFeedback")
    @ResponseBody
    fun getFeedback(): ResponseReturn {
        return ResponseReturn.success(feedbackDao!!.selectAll())
    }
}