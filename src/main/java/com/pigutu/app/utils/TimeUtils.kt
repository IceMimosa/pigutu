package com.pigutu.app.utils

import java.sql.Timestamp
import java.util.*

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/4/22
 */

class TimeUtils {
    companion object {
        //获取当前系统时间
        fun getNowTime(): Timestamp {
            return Timestamp(System.currentTimeMillis())
        }
    }
}