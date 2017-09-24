package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ImageSetEntity;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.utils.TuConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DBMeta(table = ImageSetDao.TABLE)
public interface ImageSetDao extends BaseDao<ImageSetEntity> {
    String TABLE = "image_set";
    default long insertImageSet(ImageSetEntity imageSetEntity){
        return insert(imageSetEntity);
    }

    /**
     *
     */
    default List<ImageSetEntity> getImage(int id,int page) {
        return selectList(
                ImmutableMap.of("allImageId", id),
                new QueryCondition()
                        .setPaging(page, 5)
                        .setOrderBy(new OrderBy("id"))
        );
    }

}
