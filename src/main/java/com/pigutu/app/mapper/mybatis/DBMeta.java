package com.pigutu.app.mapper.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc: 指定类对应的数据库数据
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DBMeta {

    /**
     * 指定表名
     */
    String table() default "";
}
