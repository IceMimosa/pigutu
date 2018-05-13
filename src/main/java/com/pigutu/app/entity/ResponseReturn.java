package com.pigutu.app.entity;

import com.pigutu.app.exception.ErrorCode;
import com.pigutu.app.utils.AESUtil;
import com.pigutu.app.utils.GsonHelper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by me675 on 2018/2/17.
 * 响应类
 */
@Slf4j
@Data
public class ResponseReturn {
    private int code;
    private String msg;
    private Object data;

    public ResponseReturn(int code, String msg, Object data){
        this.code=code;
        this.msg=msg;
        this.data = data;
    }

    public static ResponseReturn success(Object data) {
        String base64Str = Base64.encode("p112g".getBytes());
        base64Str = base64Str+base64Str;
        log.debug(base64Str);
        return new ResponseReturn(0,"success", AESUtil.encrypt(GsonHelper.INSTANCE.getGson().toJson(data), base64Str));
    }

    public static ResponseReturn error(int code) {
        return new ResponseReturn(code, ErrorCode.getMsg(code),null);
    }
}
