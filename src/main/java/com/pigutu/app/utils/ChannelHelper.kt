package com.pigutu.app.utils

import com.google.common.collect.ImmutableMap
import com.pigutu.app.entity.KeywordEntity
import com.pigutu.app.entity.ResponseReturn
import com.pigutu.app.mapper.ConfigDao
import org.apache.http.util.TextUtils
import javax.servlet.http.HttpServletRequest

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/6/21
 */
object ChannelHelper{
    fun getForChannel(request: HttpServletRequest,configDao: ConfigDao):Boolean{
        var channel = request.getHeader("channel")
        if (!TextUtils.isEmpty(channel)) {
            var configEntity = configDao.selectOne(ImmutableMap.of("key", channel) as MutableMap<String, Any>)
            if (configEntity != null) {
                if(configEntity.value == "1"){
                    return true
                }
            }
        }
        return false
    }

    fun getKeyword(page: Int): ResponseReturn {
        var keyList = ArrayList<KeywordEntity>()
        var key1 = KeywordEntity()
        key1.id=1
        key1.name="明星"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="美女"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="高挑"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="性感"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="可爱"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="萝莉"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="妖娆"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="罗尼"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="图片"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="美图"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="美女图"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="美"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="图"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="感"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="青春"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="清纯"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="清"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="少女"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="风度"
        key1.count=100
        keyList.add(key1)
        key1 = KeywordEntity()
        key1.id=1;
        key1.name="秋色"
        key1.count=100
        keyList.add(key1)
        return ResponseReturn.success(keyList)
    }
}