package com.pigutu.app;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tdest {
    public static void main(String args[]){
        int max=20;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);
    }
    public static String stringFilter(String str) {
        String result = "";
        try {
            str = str.replaceAll("\\\\", "");
            String regEx = "[`~!@#$%^&*()+=|{}'.:;'\\[\\]<>/?~@#￥%……&*]";//+号表示空格
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            result = m.replaceAll("").trim();
        } catch (Exception e) {
        }
        return result;
    }
}
