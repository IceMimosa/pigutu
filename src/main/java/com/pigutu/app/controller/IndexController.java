package com.pigutu.app.controller;

import com.google.common.collect.Maps;
import com.pigutu.app.component.CategoryHandler;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ImageSetListDao imageSetListDao;
    @Autowired
    private ImageSetDao imageSetDao;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private TuConfig tuConfig;
    @Autowired
    private CategoryHandler categoryHandler;

    @GetMapping("/update/{page}")
    public String all(HttpServletRequest request, Model model, @PathVariable(value = "page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.selectList(
                Maps.newHashMap(),
                new QueryCondition().setPaging(page, tuConfig.getPageNumber()).setOrderBy(new OrderBy("createTime").desc())
        );

        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "update");
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "update");
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }

    @GetMapping("/beauty/{category}/{page}")
    public String findByCategory(HttpServletRequest request, Model model, @PathVariable(value = "category") String category, @PathVariable(value = "page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findByCategory(category, page, tuConfig.getPageNumber());
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.categoryCount(category));
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "beauty/" + category);
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "beauty/"+category);
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }

    @GetMapping("/hot/{page}")
    public String hot(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank(page, tuConfig.getPageNumber());
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "hot");
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "hot");
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }

    @GetMapping("/recommend/{page}")
    public String recommend(HttpServletRequest request,Model model, @PathVariable("page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page, tuConfig.getPageNumber());
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "recommend");
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "recommend");
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }


    @GetMapping("/index/{page}")
    public String index(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.index(page, tuConfig.getPageNumber());
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "index");
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "index");
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        model.addAttribute("key", "");
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
            return "mobile/mIndex1";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }

    @GetMapping("/search/{page}")
    public String search(HttpServletRequest request, Model model, String key, @PathVariable("page") int page) {
        categoryHandler.addCategory(model, categoryDao);
        key = TuUtils.stringFilter(key);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(key, (page - 1) * tuConfig.getPageNumber());
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.searchCount(key));
        model.addAttribute("pageIndex", page);
        model.addAttribute("key", key);
        model.addAttribute("pageUrl", tuConfig.getUrl() + "search");
        model.addAttribute("mPageUrl", tuConfig.getMUrl() + "search");
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        if (request.getServerName().startsWith("m") || tuConfig.isMobileDebug()) {
            return "mobile/mIndex";
        }
        if (page == 1) {
            return "pc/firstIndex";
        }
        return "pc/index";
    }



    @GetMapping("/category")
    public String category(Model model) {
        categoryHandler.addCategory(model, categoryDao);
        return "mobile/mCategory";
    }


}