package com.pigutu.app.exception;

import java.util.HashMap;

public class ErrorCode {
    public static int HAS_REGISTER = 20001;
    public static int PWD_SHORT = 20002;
    public static int HAS_COLLECT = 20003;
    public static int NO_COLLECT = 20004;//移除收藏图片时如果没有被收藏
    public static int PWD_ERROR = 20005;

    private static HashMap<Integer, String> message = new HashMap<>();

    static {
        message.put(HAS_REGISTER, "该账号已经被注册");
        message.put(PWD_SHORT, "密码太短");
        message.put(HAS_COLLECT, "该图片已被您收藏");
        message.put(NO_COLLECT, "该图片还未被您收藏");
        message.put(PWD_ERROR, "账号或密码错误");
    }

    public static String getMsg(int code) {
        return message.get(code) == null ? "" : message.get(code);
    }
}
