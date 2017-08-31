package com.pigutu.app.controller;

import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("imageSetLists", imageSetEntities);
        return "imageSet";
    }

    @GetMapping("/hot")
    public String hot(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }

    @GetMapping("/recommend")
    public String recommend(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }

    @GetMapping("/index/{page}")
    public String index(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> categoryEntities = imageSetListDao.recommendRank(page);
        model.addAttribute("imageSetLists", categoryEntities);
        return "index";
    }

    @GetMapping("/search/{keyword}/{page}")
    public String recommend(Model model, @PathVariable("keyword") String keyword, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(keyword, page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        return "index";
    }

    @GetMapping("/view")
    public String viewImage(){
        return "viewImage";
    }
}