package com.pigutu.app.test;

import com.pigutu.app.utils.AESUtil;
import com.pigutu.app.utils.GsonHelper;
import com.pigutu.app.utils.JitaotuHelper;
import com.pigutu.app.utils.OssHelper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Jitaotu {
    public static void main(String args[]) {
        try {
            String a= "aaaaaaaaaaaaaaaaadfdfdaaaaaaaaaasfddfdfeaaaaadfefsdfddfssssssssssss";
            System.out.println(a.length());
            System.out.println(AESUtil.encrypt(a, Base64.encode("p112g".getBytes("utf-8"))+Base64.encode("p112g".getBytes("utf-8"))).length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*ArrayList<ArrayList<String>> data = JitaotuHelper.getIndexAndTitle("http://www.jitaotu.com/xinggan/");
        for (int i = 0; i < 1; i++) {
            JitaotuHelper.Taotu taotu = JitaotuHelper.getImageSetList("http://www.jitaotu.com/xinggan/" + data.get(0).get(i) + "-all.html");
            for (int index = 0; index < taotu.getImgList().size(); index++) {
                OssHelper.uploadImageUrl(taotu.getImgList().get(0), "img/12212", String.valueOf(index));
            }
        }*/
    }
}
