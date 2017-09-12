package com.pigutu.app.mapper;

import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@DBMeta(table = ImageSetDao.TABLE)
public interface ImageSetDao extends BaseDao<ImageSetEntity> {
    String TABLE = "image_set";

}
