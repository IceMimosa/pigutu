package com.pigutu.app.entity;

import lombok.Data;

/**
 * Created by me675 on 2018/2/17.
 */
@Data
public class ReturnEntity<T> {
    private int returnCode;
    private String msg;
    private T data;
}
