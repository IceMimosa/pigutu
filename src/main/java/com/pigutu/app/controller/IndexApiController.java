package com.pigutu.app.controller;

import com.google.common.collect.Maps;
import com.pigutu.app.entity.CategoryEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.entity.ReturnEntity;
import com.pigutu.app.entity.UserEntity;
import com.pigutu.app.mapper.CategoryDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.mapper.UserDao;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.utils.TuConfig;
import com.pigutu.app.utils.TuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class IndexApiController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ImageSetListDao imageSetListDao;

    @Autowired
    ImageSetDao imageSetDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private TuConfig tuConfig;

    @GetMapping("/update/{page}")
    @ResponseBody
    public List<ImageSetListEntity> all(@PathVariable(value = "page") int page) {
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.selectList(
                Maps.newHashMap(),
                new QueryCondition().setPaging(page, tuConfig.getPageNumber()).setOrderBy(new OrderBy("createTime").desc())
        );
        return imageSetListEntities;
    }

    @GetMapping("/beauty/{category}/{page}")
    @ResponseBody
    public List<ImageSetListEntity> findByCategory(@PathVariable(value = "category") String category, @PathVariable(value = "page") int page) {
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.findByCategory(category, page, tuConfig.getPageNumber());
        return imageSetListEntities;
    }


    @GetMapping("/hot/{page}")
    @ResponseBody
    public List<ImageSetListEntity> hot(@PathVariable("page") int page) {
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.hotRank(page, tuConfig.getPageNumber());
        return imageSetListEntities;
    }

    @GetMapping("/recommend/{page}")
    @ResponseBody
    public List<ImageSetListEntity> recommend(@PathVariable("page") int page) {
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.recommendRank(page, tuConfig.getPageNumber());
        return imageSetListEntities;
    }


    @GetMapping("/index/{page}")
    @ResponseBody
    public List<ImageSetListEntity> index(@PathVariable("page") int page) {
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.index(page, tuConfig.getPageNumber());
        return imageSetListEntities;
    }

    @GetMapping("/search/{page}")
    @ResponseBody
    public List<ImageSetListEntity> search(String key, @PathVariable("page") int page) {
        key = TuUtils.stringFilter(key);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(key, (page - 1) * tuConfig.getPageNumber());
        return imageSetListEntities;
    }


    @GetMapping("/category")
    @ResponseBody
    public List<CategoryEntity> category(Model model) {
        List<CategoryEntity> categoryEntities = categoryDao.selectAll();
        return categoryEntities;
    }

    @GetMapping("/register")
    @ResponseBody
    public ReturnEntity register(Model model, UserEntity userEntity) {
        userDao.insert(userEntity);
        ReturnEntity returnEntity = new ReturnEntity();
        if(userDao.insert(userEntity)>=0){
            Map<String,String> map = new HashMap<>();
            map.put("token","12");
            returnEntity.setData(map);
            returnEntity.setMsg("success");
            returnEntity.setReturnCode(0);
        }else{
            returnEntity.setData("");
            returnEntity.setMsg("fail");
            returnEntity.setReturnCode(-1);
        }
        return returnEntity;
    }

    @GetMapping("/login")
    @ResponseBody
    public ReturnEntity login(Model model, UserEntity userEntity) {
        userDao.insert(userEntity);
        ReturnEntity returnEntity = new ReturnEntity();
        if(userDao.insert(userEntity)>=0){
            returnEntity.setData("");
            returnEntity.setMsg("success");
            returnEntity.setReturnCode(0);
        }else{
            returnEntity.setData("");
            returnEntity.setMsg("fail");
            returnEntity.setReturnCode(-1);
        }
        return returnEntity;
    }

}