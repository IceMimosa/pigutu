package com.pigutu.app.mapper.mybatis;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Desc: 基础类
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 7546099546644277588L;

    @NotNull
    private Long id = 0l;
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
