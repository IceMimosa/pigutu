package com.pigutu.app.utils;


import com.pigutu.app.entity.CategoryEntity;
import com.pigutu.app.mapper.CategoryDao;
import org.springframework.ui.Model;

import java.util.List;

public class TuUtils {
    public static void addCategory(Model model,CategoryDao categoryDao){
        List<CategoryEntity> categoryEntities = categoryDao.findAll();
        model.addAttribute("categorys", categoryEntities);
        model.addAttribute("showUrl", TuConfig.url);
        model.addAttribute("url", TuConfig.url);
        model.addAttribute("mUrl", TuConfig.mUrl);
        model.addAttribute("key", "");
    }
}
