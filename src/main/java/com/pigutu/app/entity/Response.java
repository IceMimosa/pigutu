package com.pigutu.app.entity;

import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.utils.AESUtil;
import com.pigutu.app.utils.GsonHelper;
import lombok.Data;

/**
 * Created by me675 on 2018/2/17.
 * 响应类
 */
@Data
public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data = data;
    }

    public static Response success(Object data) {
        return new Response(0,"success", AESUtil.encrypt(GsonHelper.INSTANCE.getGson().toJson(data),"p112g"));
    }

    public static Response error(int code) {
        return new Response(code, ErrorCode.getMsg(code),null);
    }
}
