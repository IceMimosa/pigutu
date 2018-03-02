package com.pigutu.app.entity;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/3/2
 */
@Data
public class PageEntity<T> {
    private int total;
    private int pageNo;
    private List<T> data;
}
