package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseModel {
    @Id
    @GeneratedValue
    private int uid;
    private String name;
}