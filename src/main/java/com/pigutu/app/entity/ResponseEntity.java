package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ddsc on 9/22/2016.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseEntity extends BaseModel {
    private static final long serialVersionUID = -6689000889960238081L;

    private int response;
    private String message;
}
