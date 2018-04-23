package com.pigutu.app.exception;

import java.util.HashMap;

public class ErrorCode {
    public static int HAS_REGISTER = 20001;

    private static HashMap<Integer,String> message;

    static {
        message.put(HAS_REGISTER,"该账号已经被注册");
    }
    public static String getMsg(int code){
        return message.get(code);
    }
}
