package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.entity.LikeRecordEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DBMeta(table = ApiImageSetListDao.TABLE)
public interface ApiImageSetListDao extends BaseDao<ImageSetListEntity> {
    String TABLE = "image_set_list";

    default ImageSetListEntity selectImageList(int id) {
        return selectOne(
                ImmutableMap.of("id", id)
        );
    }

    default List<ImageSetListEntity> mingxing(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "明星"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }

    default List<ImageSetListEntity> qincun(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "清纯"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }

    default List<ImageSetListEntity> yundong(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "运动"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }
    default List<ImageSetListEntity> dongman(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "动漫"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }
    default List<ImageSetListEntity> mote(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "模特"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }
    default List<ImageSetListEntity> bijini(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "比基尼"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }
    default List<ImageSetListEntity> neiyi(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "内衣"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }
    default List<ImageSetListEntity> cosplay(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "cosplay"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("create_time").desc())
        );
    }

    default List<ImageSetListEntity> mingxingViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "明星"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }

    default List<ImageSetListEntity> qincunViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "清纯"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }

    default List<ImageSetListEntity> yundongViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "运动"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
    default List<ImageSetListEntity> dongmanViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "动漫"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
    default List<ImageSetListEntity> moteViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "模特"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
    default List<ImageSetListEntity> bijiniViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "比基尼"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
    default List<ImageSetListEntity> neiyiViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "内衣"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
    default List<ImageSetListEntity> cosplayViewCount(int page, int pageSize) {
        return selectList(
                ImmutableMap.of("category", "cosplay"),
                new QueryCondition()
                        .setPaging(page, pageSize)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }
}
