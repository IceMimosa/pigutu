package com.pigutu.app.test;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/6/20
 */
public class DB extends DA implements Cloneable{
    public String a = "db";
    public String getA(){
        return "B";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
