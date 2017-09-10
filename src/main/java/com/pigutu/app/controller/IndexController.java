package com.pigutu.app.controller;

import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public String findByCategory(HttpServletRequest request, Model model, @PathVariable(value = "category") String category, @PathVariable(value = "page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findByCategory(category, (page - 1) * TuConfig.pageNumber);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.categoryCount(category));
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "beauty/" + category);
        if (request.getServerName().startsWith("m")||TuConfig.mobileDebug) {
            return "mIndex";
        }
        return "index";
    }

    @GetMapping("/image/{id}")
    public String findImageById(HttpServletRequest request, Model model, @PathVariable(value = "id") int id) {
        TuUtils.addCategory(model, categoryDao);
        imageSetListDao.addViewCount(id);
        List<ImageSetEntity> imageSetEntities = imageSetDao.findAll(id);
        ImageSetListEntity imageSetListEntity = imageSetListDao.getImageSetListEntity(id);
        model.addAttribute("imageSetListEntity", imageSetListEntity);
        model.addAttribute("imageSetLists", imageSetEntities);
        model.addAttribute("id", id);
        if (request.getServerName().startsWith("m")||TuConfig.mobileDebug) {
            return "mImageSet";
        }
        return "imageSet";
    }

    @GetMapping("/hot/{page}")
    public String hot(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank((page - 1) * TuConfig.pageNumber);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "hot");
        if (request.getServerName().startsWith("m")||TuConfig.mobileDebug) {
            return "mIndex";
        }
        return "index";
    }

    @GetMapping("/recommend/{page}")
    public String recommend(Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page);
        model.addAttribute("imageSetLists", imageSetListEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "recommend");
        return "index";
    }


    @GetMapping("/index/{page}")
    public String index(HttpServletRequest request, Model model, @PathVariable("page") int page) {
        TuUtils.addCategory(model, categoryDao);
        if (StringUtils.isEmpty(page)) {
            page = 1;
        }
        //String a = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        List<ImageSetListEntity> categoryEntities = imageSetListDao.index((page - 1) * TuConfig.pageNumber);
        model.addAttribute("imageSetLists", categoryEntities);
        model.addAttribute("pageCount", imageSetListDao.count());
        model.addAttribute("pageIndex", page);
        model.addAttribute("pageUrl", TuConfig.url + "index");
        model.addAttribute("mPageUrl", TuConfig.mUrl + "index");
        model.addAttribute("key", "");
        if (request.getServerName().startsWith("m")||TuConfig.mobileDebug) {
            return "mIndex";
        }
        if(page==1){
            return "firstIndex";
        }
        return "index";
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
        if (request.getServerName().startsWith("m")||TuConfig.mobileDebug) {
            return "mIndex";
        }
        return "index";
    }

    @GetMapping("/view")
    public String viewImage(Model model, String imageUrl) {
        model.addAttribute("imageUrl", imageUrl);
        return "viewImage";
    }

    @GetMapping("/category")
    public String category(Model model) {
        TuUtils.addCategory(model, categoryDao);
        return "mCategory";
    }

    @GetMapping("/like")
    @ResponseBody
    public int addLikeCount(int id) {
        imageSetListDao.addLikeCount(id);
        return imageSetListDao.likeCount(id);

    }

    /**
     * 推荐，目标智能推荐，现在随机前100页
     * @param model
     * @return
     */
    @GetMapping("/myrecommend")
    @ResponseBody
    public List<ImageSetListEntity> randomRommend(Model model,int number) {
        Random random = new Random();
        int page = random.nextInt(100);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.myRecommend(page  * number,number);
        return imageSetListEntities;
    }
}