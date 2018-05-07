package com.pigutu.app.test;

import com.pigutu.app.utils.JitaotuHelper;
import com.pigutu.app.utils.OssHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Jitaotu {
    public static void main(String args[]) {
        ArrayList<ArrayList<String>> data = JitaotuHelper.getTitleAndIndex("http://www.jitaotu.com/xinggan/");
        for (int i = 0; i < 1; i++) {
            JitaotuHelper.Taotu taotu = JitaotuHelper.getImageSetList("http://www.jitaotu.com/xinggan/" + data.get(0).get(i) + "-all.html");
            for (int index = 0; index < taotu.getImgList().size(); index++) {
                OssHelper.uploadImageUrl(taotu.getImgList().get(0), "img/12212", String.valueOf(index));
            }
        }
    }
}
