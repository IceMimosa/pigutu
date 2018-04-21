package com.pigutu.app.component;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.ConfigDao;
import com.pigutu.app.mapper.ImageSetListDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class ScheduledTasks {

    @Autowired
    ImageSetListDao imageSetListDao;
    @Autowired
    ConfigDao configDao;
    //10分钟更新一次推荐
    @Scheduled(fixedRate = 1000*60*10)
    public void changeRecommend(){
        Random random = new Random();
        int page = random.nextInt(20);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.myRecommend(page * 10, 10);
        for(int i=1;i<=10;i++){
            System.out.println(imageSetListEntities.get(i-1).getId()+"---"+imageSetListEntities.size());
            configDao.update(Long.valueOf(i), ImmutableMap.of("value",imageSetListEntities.get(i-1).getId()));
        }
    }
}
