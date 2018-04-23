package com.pigutu.app.entity;

import com.pigutu.app.exception.ErrorCode;
import lombok.Data;

/**
 * Created by me675 on 2018/2/17.
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
        return new Response(0,"success",data);
    }

    public static Response error(int code) {
        return new Response(code, ErrorCode.getMsg(code),null);
    }
}
