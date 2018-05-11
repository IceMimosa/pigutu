package com.pigutu.app.component;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.ConfigDao;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import com.pigutu.app.utils.JitaotuHelper;
import com.pigutu.app.utils.OssHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    ImageSetListDao imageSetListDao;
    @Autowired
    ConfigDao configDao;
    @Autowired
    ImageSetDao imageSetDao;
    //10分钟更新一次推荐
    @Scheduled(fixedRate = 1000*60*10)
    public void changeRecommend(){
        Random random = new Random();
        int page = random.nextInt(20);
        List<ImageSetListEntity> imageSetListEntities = imageSetListDao.myRecommend(page * 10, 10);
        for(int i=1;i<=10;i++){
            log.debug(imageSetListEntities.get(i-1).getId()+"---"+imageSetListEntities.size());
            configDao.update(Long.valueOf(i), ImmutableMap.of("value",imageSetListEntities.get(i-1).getId()));
        }
    }

    //一天抓一次jitaotu
    @Scheduled(fixedRate = 1000*60*60*24)
    public void jsoupJitaotu(){
        Random random = new Random();
        int minute = random.nextInt(60);
        //延迟一定分钟，避免太规律
        try {
            Thread.sleep(minute*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/xinggan/","xinggan","内衣");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/qingchun/","qingchun","清纯");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/mote/","mote","内衣");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/xiaohua/","xiaohua","清纯");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/mingxing/","mingxing","明星");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/cosplay/","cosplay","Cosplay");
        JitaotuHelper.getJitaotu(imageSetListDao,imageSetDao,"http://www.jitaotu.com/qipao/","qipao","内衣");
    }
}
