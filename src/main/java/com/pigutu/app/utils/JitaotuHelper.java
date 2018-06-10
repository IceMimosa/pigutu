package com.pigutu.app.utils;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.ImageSetDao;
import com.pigutu.app.mapper.ImageSetListDao;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Slf4j
public class JitaotuHelper {
    /**
     * 外部使用的类
     *
     * @param imageSetListDao
     * @param imageSetDao
     * @param indexUrl        首页url 如 http://www.jitaotu.com/xinggan/
     * @param nextIndexUrl    xinggan
     * @param category
     */
    public static void getJitaotu(ImageSetListDao imageSetListDao, ImageSetDao imageSetDao, String indexUrl, String nextIndexUrl, String category) {
        //0 index 1 title
        ArrayList<ArrayList<String>> data = JitaotuHelper.getIndexAndTitle(indexUrl);
        for (int i = 0; i < data.get(0).size(); i++) {

            log.debug("开始上传图集->" + data.get(0).get(i) + ",title=" + data.get(1).get(i));
            //根据title检测是否存在图片集,如果存在即证明上传完毕了，全部暂停
            if (imageSetListDao.selectOne(ImmutableMap.of("title", data.get(1).get(i))) != null) {
                break;
            }
            JitaotuHelper.Taotu taotu = JitaotuHelper.getImageSetList("http://www.jitaotu.com/" + nextIndexUrl + "/" + data.get(0).get(i) + "-all.html");
            ImageSetListEntity imageSetListEntity = new ImageSetListEntity();
            imageSetListEntity.setCreateTime(new Date(System.currentTimeMillis()));
            imageSetListEntity.setCategory(category);
            imageSetListEntity.setImgCount(taotu.getImgCount());
            imageSetListEntity.setLabel(taotu.getTag());
            imageSetListEntity.setTitle(taotu.getTitle());
            long id = imageSetListDao.insert(imageSetListEntity);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            for (int index = 0; index < taotu.getImgList().size(); index++) {
                String time = String.valueOf(System.currentTimeMillis());
                if (index == 0) {
                    log.debug("cover_url" + df.format(System.currentTimeMillis()) + "/" + id + "/" + time + ".jpg");
                    imageSetListDao.update(id, ImmutableMap.of("cover_url", df.format(System.currentTimeMillis()) + "/" + id + "/" + time + ".jpg"));
                }
                OssHelper.uploadImageUrl(taotu.getImgList().get(index), "img/" + df.format(System.currentTimeMillis()) + "/" + id, time);
                ImageSetEntity imageSetEntity = new ImageSetEntity();
                imageSetEntity.setAllImagesId((int) id);
                imageSetEntity.setUrl(df.format(System.currentTimeMillis()) + "/" + id + "/" + time + ".jpg");
                log.debug("upload image image = " + time);
                imageSetDao.insert(imageSetEntity);
            }
        }
        log.debug("jitaotu end");
    }

    public static ArrayList<ArrayList<String>> getIndexAndTitle(String indexUrl) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> indexList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        String trueTitle;
        int imgCount;
        String title;
        try {
            System.out.println("start jitaotu");
            Document document = Jsoup.connect(indexUrl).userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7")
                    .timeout(10 * 1000).get();
            Elements elements = document.getElementsByClass("post-home home-list1");
            for (Element element : elements) {
                Elements newEle = element.getElementsByClass("img");
                String url = newEle.attr("href");
                System.out.println(url.split("/")[2].split("\\.")[0].trim());
                indexList.add(url.split("/")[2].split("\\.")[0].trim());
                titleList.add(newEle.attr("title"));
            }
            data.add(indexList);
            data.add(titleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Taotu getImageSetList(String url) {
        Taotu taotu = new Taotu();
        ArrayList<String> imgList = new ArrayList<>();
        ArrayList<String> tagList = new ArrayList<>();
        String trueTitle;
        int imgCount;
        String title;
        try {
            System.out.println("start jitaotu");
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7")
                    .timeout(10 * 1000).get();
            Elements elements = document.body().getElementsByClass("picsbox picsboxcenter");
            Elements tagElements = document.body().getElementsByClass("pleft").select("span").select("a");
            Elements imgEle = elements.select("img");
            for (Element element : imgEle) {
                imgList.add(element.attributes().get("data-original").trim());
            }
            for (Element element : tagElements) {
                tagList.add(element.text().trim());
            }
            title = document.select("h1").text().trim();
            title = title.substring(0, title.length() - 1);
            trueTitle = title.split("\\(")[0];
            imgCount = Integer.valueOf(title.split("\\(")[1].substring(0, title.split("\\(")[1].length() - 1));
            //移除最后一个公众号广告
            imgList.remove(imgList.size() - 1);
            String tagString = "";
            for (String tag : tagList) {
                tagString = tagString + tag + ",";
            }
            taotu.setTag(tagString.substring(0, tagString.length() - 1));
            taotu.setImgCount(imgCount);
            taotu.setTitle(trueTitle);
            taotu.setImgList(imgList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taotu;
    }

    @Data
    public static class Taotu {
        ArrayList<String> imgList;
        String title;
        String tag;
        int imgCount;
    }
}
