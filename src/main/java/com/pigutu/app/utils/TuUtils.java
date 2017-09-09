package com.pigutu.app.utils;


import com.pigutu.app.entity.CategoryEntity;
import com.pigutu.app.mapper.CategoryDao;
import org.springframework.ui.Model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuUtils {
    public static void addCategory(Model model, CategoryDao categoryDao) {
        List<CategoryEntity> categoryEntities = categoryDao.findAll();
        model.addAttribute("categorys", categoryEntities);
        model.addAttribute("showUrl", TuConfig.url);
        model.addAttribute("url", TuConfig.url);
        model.addAttribute("mUrl", TuConfig.mUrl);
        model.addAttribute("key", "");
    }

    /**
     * 过滤一些特殊字符
     */
    public static String stringFilter(String str) {
        String result = "";
        try {
            str = str.replaceAll("\\\\", "");
            String regEx = "[`~!@#$%^&*()+=|{}'.:;'\\[\\]<>/?~@#￥%……&*]";//+号表示空格
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            result = m.replaceAll("").trim();
        } catch (Exception e) {
        }
        return result;
    }
}
