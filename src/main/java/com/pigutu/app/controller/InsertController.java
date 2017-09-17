package com.pigutu.app.controller;

import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InsertController {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    ImageSetDao imageSetDao;

    @GetMapping("/insertImageSetList")
    @ResponseBody
    public int insertImageSetList(ImageSetListEntity imageSetListEntity,String key){
        if(!key.equals("26535989jidsf25")){
            return 0;
        }
        return imageSetListDao.insertImageSetList(imageSetListEntity.getCategory(),imageSetListEntity.getCommentCount(),imageSetListEntity.getCoverUrl(),imageSetListEntity.getImgCount(),imageSetListEntity.getLabel(),
                imageSetListEntity.getLikeCount(),imageSetListEntity.getTitle(),imageSetListEntity.getViewCount(),imageSetListEntity.getRecommendCount());
    }
    @GetMapping("/insertImageSet")
    @ResponseBody
    public long insertImageSet(ImageSetEntity imageSetEntity,String key){
        if(!key.equals("26535989jidsf25")){
            return 0;
        }
        return imageSetDao.insertImageSet(imageSetEntity);
    }
}
