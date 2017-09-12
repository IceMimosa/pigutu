package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.utils.TuConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@DBMeta(table = ImageSetListDao.TABLE)
public interface ImageSetListDao extends BaseDao<ImageSetListEntity> {
    String TABLE = "image_set_list";

    // 根据category查询数量
    default int categoryCount(String category) {
        return this.count(ImmutableMap.of("category", category));
    }

    /**
     * 搜索排行暂时决定用like_count排序
     */
    @Select({"SELECT * FROM image_set_list where title like '%${keyword}%' or label like '%${keyword}%' order by like_count desc limit  #{page},18"})
    List<ImageSetListEntity> search(@Param("keyword") String keyword, @Param("page") int page);

    @Select({"SELECT count(*) FROM image_set_list where title like '%${keyword}%' or label like '%${keyword}%'"})
    int searchCount(@Param("keyword") String keyword);

    /**
     * 热度 采用view——count首页喜欢排行榜
     */
    default List<ImageSetListEntity> hotRank(int page) {
        return selectList(
                Maps.newHashMap(),
                new QueryCondition()
                        .setPaging(page, TuConfig.pageNumber)
                        .setOrderBy(new OrderBy("viewCount").desc())
        );
    }

    /**
     * 按照喜欢的数量降序
     */
    default List<ImageSetListEntity> index(int page) {
        return selectList(
                Maps.newHashMap(),
                new QueryCondition()
                        .setPaging(page, TuConfig.pageNumber)
                        .setOrderBy(new OrderBy("likeCount").desc())
        );
    }

    /**
     * 根据类型查询
     */
    default List<ImageSetListEntity> findByCategory(String category, int page) {
        return selectList(
                ImmutableMap.of("category", category),
                new QueryCondition()
                        .setPaging(page, TuConfig.pageNumber)
                        .setOrderBy(new OrderBy("likeCount").desc())
        );
    }

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},18"})
    default List<ImageSetListEntity> recommendRank(@Param("page") int page) {
        return selectList(
                Maps.newHashMap(),
                new QueryCondition()
                        .setPaging(page, TuConfig.pageNumber)
                        .setOrderBy(new OrderBy("likeCount").desc())
        );
    }

    @Update({"UPDATE image_set_list set view_count = view_count + 1 where id = #{id}"})
    void addViewCount(@Param("id") int id);

    @Update({"UPDATE image_set_list set like_count = like_count + 1 where id = #{id}"})
    void addLikeCount(@Param("id") int id);

    @Select({"SELECT like_count FROM image_set_list where id = #{id}"})
    int likeCount(@Param("id") int id);

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},#{pageNumber}"})
    List<ImageSetListEntity> myRecommend(@Param("page") int pageStart,@Param("pageNumber")int pageNumber);
}
