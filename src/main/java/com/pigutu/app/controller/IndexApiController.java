package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.*;
import com.pigutu.app.mapper.*;
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

import java.util.*;

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
    ApiImageSetListDao apiImageSetListDao;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private TuConfig tuConfig;

    @Autowired
    private ConfigDao configDao;

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

    /**
     * 推荐，目标智能推荐，现在随机前100页
     *
     * @return
     */
    @GetMapping("/randomRecommend")
    @ResponseBody
    public List<ImageSetListEntity> randomRommend() {
        Random random = new Random();
        int page = random.nextInt(100);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.myRecommend(page * tuConfig.getRandomRecommend(), tuConfig.getRandomRecommend());
        return imageSetListEntities;
    }

    /**
     * 更新
     *
     * @return
     */
    @GetMapping("/updateImg")
    @ResponseBody
    public PageEntity<ImageSetListEntity> updateImg(int pageNo) {
        PageEntity<ImageSetListEntity> pageEntity = imageSetListDao.pageUpdateImg(pageNo,20);
        return pageEntity;
    }


    @GetMapping("/index")
    @ResponseBody
    public ApiIndexEntity index() {
        int page = 1;
        ApiIndexEntity apiIndexEntity = new ApiIndexEntity();
        List<ImageSetListEntity> minxing = apiImageSetListDao.mingxing(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> qincun = apiImageSetListDao.qincun(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> yundong = apiImageSetListDao.yundong(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> dongman = apiImageSetListDao.dongman(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> mote = apiImageSetListDao.mote(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> bijini = apiImageSetListDao.bijini(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> neiyi = apiImageSetListDao.neiyi(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> cosplay = apiImageSetListDao.cosplay(page, tuConfig.getCategoryPageNumber());
        List<ImageSetListEntity> minxingViewCount = apiImageSetListDao.mingxingViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> qincunViewCount = apiImageSetListDao.qincunViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> yundongViewCount = apiImageSetListDao.yundongViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> dongmanViewCount = apiImageSetListDao.dongmanViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> moteViewCount = apiImageSetListDao.moteViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> bijiniViewCount = apiImageSetListDao.bijiniViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> neiyiViewCount = apiImageSetListDao.neiyiViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ImageSetListEntity> cosplayViewCount = apiImageSetListDao.cosplayViewCount(page, tuConfig.getCategoryViewCountPageNumber());
        List<ApiIndexEntity.Category> categories = new ArrayList<>();

        categories.add(new ApiIndexEntity.Category("明星", minxing, minxingViewCount));
        categories.add(new ApiIndexEntity.Category("清纯", qincun, qincunViewCount));
        categories.add(new ApiIndexEntity.Category("运动", yundong, yundongViewCount));
        categories.add(new ApiIndexEntity.Category("动漫", dongman, dongmanViewCount));
        categories.add(new ApiIndexEntity.Category("模特", mote, moteViewCount));
        categories.add(new ApiIndexEntity.Category("比基尼", bijini, bijiniViewCount));
        categories.add(new ApiIndexEntity.Category("内衣", neiyi, neiyiViewCount));
        categories.add(new ApiIndexEntity.Category("cosplay", cosplay, cosplayViewCount));

        apiIndexEntity.setCategories(categories);

        List<ConfigEntity> configEntities = configDao.getConfig();
        List<ImageSetListEntity> carousel = new ArrayList<>();
        List<ImageSetListEntity> hot = new ArrayList<>();
        for (ConfigEntity configEntity : configEntities) {
            if (configEntity.getKey().equals("c1")) {
                carousel.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("c2")) {
                carousel.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("c3")) {
                carousel.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("c4")) {
                carousel.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot1")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot2")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot3")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot4")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot5")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
            if (configEntity.getKey().equals("hot6")) {
                hot.add(apiImageSetListDao.selectImageList(Integer.valueOf(configEntity.getValue())));
            }
        }
        apiIndexEntity.setCarousel(carousel);
        apiIndexEntity.setHot(hot);
        return apiIndexEntity;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public ApiDetailEntity detail(@PathVariable("id") int id) {
        ApiDetailEntity apiDetailEntity = new ApiDetailEntity();
        Random random = new Random();
        int page = random.nextInt(100);
        List<ImageSetListEntity> recommends = imageSetListDao.myRecommend(page * tuConfig.getRandomRecommend(), tuConfig.getRandomRecommend());
        apiDetailEntity.setRecommends(recommends);
        List<LikeRecordEntity> likes = imageSetListDao.getLikeRecord();
        apiDetailEntity.setLikes(likes);
        List<ImageSetEntity> details = imageSetDao.selectList(ImmutableMap.of("allImagesId", id), new QueryCondition());
        apiDetailEntity.setImageDetail(imageSetListDao.selectOne(ImmutableMap.of("id",id)));
        apiDetailEntity.setDetails(details);
        return apiDetailEntity;
    }

    @GetMapping("/search")
    @ResponseBody
    public PageEntity<ImageSetListEntity> search(String key, int pageNo) {
        key = TuUtils.stringFilter(key);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.search(key, (pageNo - 1) * 20);
        PageEntity<ImageSetListEntity> page = new PageEntity<>();
        int total = imageSetListDao.searchCount(key);
        page.setData(imageSetListEntities);
        page.setTotal(total);
        return page;
    }

    @GetMapping("/category/{category}")
    @ResponseBody
    public PageEntity<ImageSetListEntity> categoryImageSetList(int pageNo,@PathVariable("category") String category) {
        PageEntity<ImageSetListEntity> pageEntity = imageSetListDao.pageCategory(category, pageNo, 20);
        return pageEntity;
    }

    @GetMapping("/category12")
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
        if (userDao.insert(userEntity) >= 0) {
            Map<String, String> map = new HashMap<>();
            map.put("token", "12");
            returnEntity.setData(map);
            returnEntity.setMsg("success");
            returnEntity.setReturnCode(0);
        } else {
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
        if (userDao.insert(userEntity) >= 0) {
            returnEntity.setData("");
            returnEntity.setMsg("success");
            returnEntity.setReturnCode(0);
        } else {
            returnEntity.setData("");
            returnEntity.setMsg("fail");
            returnEntity.setReturnCode(-1);
        }
        return returnEntity;
    }

}