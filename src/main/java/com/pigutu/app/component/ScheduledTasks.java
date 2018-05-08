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
        //0 index 1 title
        ArrayList<ArrayList<String>> data = JitaotuHelper.getIndexAndTitle("http://www.jitaotu.com/xinggan/");
        for (int i = 0; i < data.get(0).size(); i++) {

            log.debug("开始上传图集->"+data.get(0).get(i)+",title="+data.get(1).get(i));
            JitaotuHelper.Taotu taotu = JitaotuHelper.getImageSetList("http://www.jitaotu.com/xinggan/" + data.get(0).get(i) + "-all.html");
            ImageSetListEntity imageSetListEntity = new ImageSetListEntity();
            imageSetListEntity.setCreateTime(new Date(System.currentTimeMillis()));
            imageSetListEntity.setCategory("内衣");
            imageSetListEntity.setImgCount(taotu.getImgCount());
            imageSetListEntity.setLabel(taotu.getTag());
            imageSetListEntity.setTitle(taotu.getTitle());
            long id = 0l;
            ImageSetListEntity imageSetListEntity22 = imageSetListDao.selectOne(ImmutableMap.of("title",data.get(1).get(i)));
            if(imageSetListEntity22==null){
                id = imageSetListDao.insert(imageSetListEntity);
            }else{
                id = imageSetListEntity22.getId();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            for (int index = 0; index < taotu.getImgList().size(); index++) {
                String time = String.valueOf(System.currentTimeMillis());
                if(index == 0){
                    log.debug("cover_url"+df.format(System.currentTimeMillis())+"/"+id+"/"+time+".jpg");
                    imageSetListDao.update(id,ImmutableMap.of("cover_url",df.format(System.currentTimeMillis())+"/"+id+"/"+time+".jpg"));
                }
                OssHelper.uploadImageUrl(taotu.getImgList().get(index), "img/"+df.format(System.currentTimeMillis())+"/"+id, time);
                ImageSetEntity imageSetEntity = new ImageSetEntity();
                imageSetEntity.setAllImagesId((int)id);
                imageSetEntity.setUrl(df.format(System.currentTimeMillis())+"/"+id+"/"+time+".jpg");
                log.debug("upload image image = "+time);
                imageSetDao.insert(imageSetEntity);
            }
        }
        log.debug("jitaotu end");
    }
}
