package com.pigutu.app.test;

import com.google.gson.Gson;
import com.pigutu.app.utils.AESUtil;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;

public class Meit {
    static int userNum = 0;
    static int permission1 = 0;
    static int permission5 = 0;
    static Gson gson = new Gson();
    static String aaa = "\"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\"";

    public static void main(String args[]) {
        for (int i = 0; i < 160; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getPermission(i);
        }
        System.out.println("permission 1 = "+permission1);
        System.out.println("permission 5 = "+permission5);
        System.out.println("num = "+userNum);
    }

    public static void getPermission(int page) {
        try {
            String data = Jsoup.connect("http://api.beautypics.cn/v1/users/active?p=" + page).ignoreContentType(true).userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").get().body().text();
            for (MUser mUser : gson.fromJson(a.a.a(data.getBytes()), MResponse.class).data) {
                switch (mUser.permission) {
                    case 1:
                        permission1++;
                        break;
                    case 5:
                        permission5++;
                        break;
                    default:
                        System.out.println(mUser.permission);
                        break;
                }
                userNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class AAA {
        public void aaa(String a) {
            System.out.println(a + "aaa");
        }

        public void aaa(Object a) {
            System.out.println(a + "bbb");
        }
    }
}
