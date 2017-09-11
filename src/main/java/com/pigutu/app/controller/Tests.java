package com.pigutu.app.controller;

import com.pigutu.app.mapper.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
@RestController
@RequestMapping("/api/test")
public class Tests {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("")
    public void test() {
        System.out.println(categoryDao.select(1L));
    }
}
