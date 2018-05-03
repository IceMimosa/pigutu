package com.pigutu.app.utils

import java.sql.Date


/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/4/22
 */

class TimeUtils {
    companion object {
        //获取当前系统时间
        fun getNowTime(): Date {
            return java.sql.Date(System.currentTimeMillis())
        }
    }
}