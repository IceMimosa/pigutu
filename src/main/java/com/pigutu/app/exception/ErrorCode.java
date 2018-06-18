package com.pigutu.app.exception;

import org.apache.http.util.TextUtils;

import java.util.HashMap;

public class ErrorCode {
    public static int HAS_REGISTER = 20001;
    public static int PWD_SHORT = 20002;
    public static int HAS_COLLECT = 20003;
    public static int NO_COLLECT = 20004;//移除收藏图片时如果没有被收藏
    public static int PWD_ERROR = 20005;
    public static int OLD_PWD_ERROR = 20006;
    public static int NO_USER = 20007;
    public static int NO_IMAGE_ID = 20008;
    public static int NO_CONTENT = 20009;
    public static int TOKEN_FAIL = 20010;

    private static HashMap<Integer, String> message = new HashMap<>();

    static {
        message.put(HAS_REGISTER, "该账号已经被注册");
        message.put(PWD_SHORT, "密码太短");
        message.put(HAS_COLLECT, "该图片已被您收藏");
        message.put(NO_COLLECT, "该图片还未被您收藏");
        message.put(PWD_ERROR, "账号或密码错误");
        message.put(OLD_PWD_ERROR, "旧密码错误");
        message.put(NO_USER, "用户信息异常");
        message.put(NO_IMAGE_ID, "没有该图片");
        message.put(NO_CONTENT, "没有内容");
        message.put(TOKEN_FAIL, "用户token验证失败");
        message.put(401, "请先登录");
        message.put(404, "服务异常");
        message.put(500, "服务异常");
    }

    public static String getMsg(int code) {
        return TextUtils.isEmpty(message.get(code)) ? "未知错误" : message.get(code);
    }
}
