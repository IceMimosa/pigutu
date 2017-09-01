package com.pigutu.app.mapper;

import com.pigutu.app.entity.ImageSetEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageSetDao {

    @Select("SELECT * FROM image_set where all_images_id = #{all_images_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "all_images_id", column = "all_images_id"),
            @Result(property = "url", column = "url")
    })
    List<ImageSetEntity> findAll(@Param("all_images_id") int id);

    @Select("SELECT * FROM image_set where all_images_id = #{all_images_id} limit 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "all_images_id", column = "all_images_id"),
            @Result(property = "url", column = "url")
    })
    ImageSetEntity findCoverImage(@Param("all_images_id") int id);
}
