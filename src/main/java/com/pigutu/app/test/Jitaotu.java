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
        DA db = new DB();
        System.out.println(db.a);
    }
}
