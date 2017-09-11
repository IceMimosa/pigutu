package com.pigutu.app.mapper.mybatis;

import javax.validation.constraints.NotNull;

/**
 * Desc: 基础类
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
public abstract class BaseModel {

    @NotNull
    private Long id;
    // @NotNull
    // private Date createdAt;
    // @Nullable
    // private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
