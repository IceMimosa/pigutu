package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
public class IndexController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    ImageSetDao imageSetDao;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/update/{page}")
    public String all(HttpServletRequest request, Model model, @PathVariable(value = "page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.selectList(
                Maps.newHashMap(),
                new QueryCondition().setPaging(page, TuConfig.pageNumber).setOrderBy(new OrderBy("createTime").desc())
        );

        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "update");
        if (request.getServerName().startsWith("m") || TuConfig.mobileDebug) {
            return "mobile/mIndex";
        }
        return "pc/index";
    }

    @GetMapping("/beauty/{category}/{page}")
    public String findByCategory(HttpServletRequest request, Model model, @PathVariable(value = "category") String category, @PathVariable(value = "page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findByCategory(category, page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.categoryCount(category));
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "beauty/" + category);
        if (request.getServerName().startsWith("m") || TuConfig.mobileDebug) {
            return "mobile/mIndex";
        }
        return "pc/index";
    }


    @GetMapping("/hot/{page}")
    public String hot(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "hot");
        if (request.getServerName().startsWith("m") || TuConfig.mobileDebug) {
            return "mobile/mIndex";
        }
        return "pc/index";
    }

    @GetMapping("/recommend/{page}")
    public String recommend(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "recommend");
        return "pc/index";
    }


    @GetMapping("/index/{page}")
    public String index(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.index(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "index");
        model.addAttribute("mPageUrl", TuConfig.mUrl + "index");
        model.addAttribute("key", "");
        if (request.getServerName().startsWith("m") || TuConfig.mobileDebug) {
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }

    @GetMapping("/search/{page}")
    public String search(HttpServletRequest request, Model model, String key, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        key = TuUtils.stringFilter(key);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(key, (page - 1) * TuConfig.pageNumber);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.searchCount(key));
        model.addAttribute("pageIndex", page);
        model.addAttribute("key", key);
        model.addAttribute("pageUrl", TuConfig.url + "search");
        if (request.getServerName().startsWith("m") || TuConfig.mobileDebug) {
            return "mobile/mIndex";
        }
        return "pc/index";
    }



    @GetMapping("/category")
    public String category(Model model) {
        TuUtils.addCategory(model, categoryDao);
        return "mobile/mCategory";
    }


}