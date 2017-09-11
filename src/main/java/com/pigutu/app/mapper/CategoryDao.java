package com.pigutu.app.mapper;

import com.pigutu.app.entity.CategoryEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 不加也可以
@DBMeta(table = CategoryDao.TABLE)
public interface CategoryDao extends BaseDao<CategoryEntity> {
    String TABLE = "category";

    @Select("SELECT * FROM category")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "path", column = "path"),
            @Result(property = "title", column = "title"),
            @Result(property = "parameter", column = "parameter")
    })
    List<CategoryEntity> findAll();
}
