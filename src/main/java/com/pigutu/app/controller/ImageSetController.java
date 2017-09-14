package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
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
import java.util.Random;

/**
 * 图集控制器
 */
@Controller
public class ImageSetController {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    ImageSetDao imageSetDao;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/image/{id}")
    public String findImageById(HttpServletRequest request, Model model, @PathVariable(value = "id") int id) {
        TuUtils.addCategory(model, categoryDao);
        imageSetListDao.addViewCount(id);
        List<ImageSetEntity> imageSetEntities = imageSetDao.selectList(ImmutableMap.of("allImagesId", id));
        ImageSetListEntity imageSetListEntity = imageSetListDao.select((long) id);
        model.addAttribute("imageSetListEntity", imageSetListEntity);
        model.addAttribute("imageSetLists", imageSetEntities);
        model.addAttribute("likeRecords", imageSetListDao.getLikeRecord());
        model.addAttribute("id", id);
        if (request.getServerName().startsWith("m")|| TuConfig.mobileDebug) {
            return "mobile/mImageSet";
        }
        return "pc/imageSet";
    }
    @GetMapping("/view")
    public String viewImage(Model model, String imageUrl) {
        model.addAttribute("imageUrl", imageUrl);
        return "pc/viewImage";
    }
    @GetMapping("/like")
    @ResponseBody
    public int addLikeCount(HttpServletRequest request,int id) {
        imageSetListDao.addLikeCount(id);
        String ip = "0";
        try {
            ip = TuUtils.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageSetListEntity imageSetListEntity = imageSetListDao.getLikeRecordCoverUrl(id);
        imageSetListDao.insertLikeRecord(ip,id,imageSetListEntity.getTitle(),imageSetListEntity.getCoverUrl());
        return imageSetListDao.likeCount(id);
    }

    /**
     * 推荐，目标智能推荐，现在随机前100页
     *
     * @param model
     * @return
     */
    @GetMapping("/myrecommend")
    @ResponseBody
    public List<ImageSetListEntity> randomRommend(Model model, int number) {
        Random random = new Random();
        int page = random.nextInt(100);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.myRecommend(page * number, number);
        return imageSetListEntities;
    }

}
