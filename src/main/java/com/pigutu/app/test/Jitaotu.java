package com.pigutu.app.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Jitaotu {
    public static void main(String args[]){
        ArrayList<String> imgList = new ArrayList<>();
        ArrayList<String> tagList = new ArrayList<>();
        String title;
        try {
            System.out.println("start");
            Document document = Jsoup.connect("http://www.jitaotu.com/xinggan/66048-all.html").userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7")
                    .timeout(10*1000).get();
            Elements elements = document.body().getElementsByClass("picsbox picsboxcenter");
            Elements tagElements = document.body().getElementsByClass("pleft").select("span").select("a");
            Elements imgEle = elements.select("img");
            for(Element element:imgEle){
                imgList.add(element.attributes().get("data-original").trim());
            }
            for(Element element:tagElements){
                tagList.add(element.text().trim());
            }
            title = document.select("h1").text().trim();
            //移除最后一个公众号广告
            imgList.remove(imgList.size()-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
