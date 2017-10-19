package com.pigutu.app.component;

import com.pigutu.app.entity.CategoryEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.utils.TuConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Desc: category 相关处理
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/10/20
 */
@Component
public class CategoryHandler {

    @Autowired
    private TuConfig tuConfig;

    /**
     * 请求域中塞入类目等值信息
     */
    public void addCategory(Model model, CategoryDao categoryDao) {
        List<CategoryEntity> categoryEntities = categoryDao.selectAll();
        model.addAttribute("categorys", categoryEntities);
        model.addAttribute("showUrl", tuConfig.getUrl());
        model.addAttribute("url", tuConfig.getUrl());
        model.addAttribute("mUrl", tuConfig.getMUrl());
        model.addAttribute("key", "");
    }
}
