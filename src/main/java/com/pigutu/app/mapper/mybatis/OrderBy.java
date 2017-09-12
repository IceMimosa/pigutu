package com.pigutu.app.mapper.mybatis;

/**
 * Desc: 排序对象
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/13
 */
public class OrderBy {
    private String field;
    private boolean desc;

    public OrderBy(String field) {
        this.field = field;
    }

    public OrderBy asc() {
        this.desc = false;
        return this;
    }

    public OrderBy desc() {
        this.desc = true;
        return this;
    }

    public String getField() {
        return field;
    }

    public boolean isDesc() {
        return desc;
    }
}
