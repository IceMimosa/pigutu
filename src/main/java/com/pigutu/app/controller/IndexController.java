package com.pigutu.app.controller;

import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    ImageSetDao imageSetDao;

    @GetMapping("/all/{id}")
    public String all(Model model, @PathVariable(value = "id") int id) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findAllByPage((id - 1) * TuConfig.pageNumber);
        model.addAttribute("imageSetLists", imageSetListEntities);
        int pageCount;
        int count = imageSetListDao.count();
        if (count % 18 == 0) {
            pageCount = count / 18;
        } else {
            pageCount = count / 18 + 1;
        }
        model.addAttribute("pageCount", pageCount);
        return "index";
    }

    @GetMapping("/beauty/{category}/{page}")
    public String findByCategory(Model model, @PathVariable(value = "category") String category, @PathVariable(value = "page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findByCategory(category, page - 1);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }

    @GetMapping("/image/{id}")
    public String findImageById(Model model, @PathVariable(value = "id") int id) {
        TuUtils.addCategory(model, categoryDao);
        imageSetListDao.addViewCount(id);
        List<ImageSetEntity> imageSetEntities = imageSetDao.findAll(id);
        ImageSetListEntity imageSetListEntity = imageSetListDao.getImageSetListEntity(id);
        model.addAttribute("imageSetListEntity", imageSetListEntity);
        model.addAttribute("imageSetLists", imageSetEntities);
        return "imageSet";
    }

    @GetMapping("/hot/{page}")
    public String hot(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }

    @GetMapping("/recommend/{page}")
    public String recommend(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }


    @GetMapping("/index/{page}")
    public String index(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        if(StringUtils.isEmpty(page)){
            page = 1;
        }
        List<ImageSetListEntity> categoryEntities = imageSetListDao.recommendRank(page-1);
        model.addAttribute("imageSetLists", categoryEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        return "index";
    }

    @PostMapping("/search/{page}")
    public String recommend(Model model,String key, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(key, page-1);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount",imageSetListDao.searchCount(key));
        model.addAttribute("pageIndex",page);
        return "search";
    }

    @GetMapping("/view")
    public String viewImage() {
        return "viewImage";
    }
}