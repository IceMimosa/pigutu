package com.pigutu.app.mapper;

import com.pigutu.app.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryDao {

    @Select("SELECT * FROM category")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "path", column = "path"),
            @Result(property = "title", column = "title"),
            @Result(property = "parameter", column = "parameter")
    })
    List<CategoryEntity> findAll();
}
