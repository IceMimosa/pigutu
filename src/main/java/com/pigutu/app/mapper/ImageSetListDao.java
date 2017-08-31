package com.pigutu.app.mapper;

import com.pigutu.app.entity.ImageSetListEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageSetListDao {

    @Select({"SELECT * FROM image_set_list order by create_time desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time")
    })
    List<ImageSetListEntity> findAllByPage(@Param("page") int pageStart);

    @Select({"SELECT count(*) FROM image_set_list"})
    int count();

    /**
     * 搜索排行暂时决定用like_count排序
     * @param keyword
     * @param pageStart
     * @return
     */
    @Select({"SELECT * FROM image_set_list where title like '%#{keyword}%' or label like '%#{keyword}%'  order by like_count desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time")
    })
    List<ImageSetListEntity> search(@Param("keyword") String keyword, @Param("page") int pageStart);

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time")
    })
    List<ImageSetListEntity> hotRank(@Param("page") int pageStart);

    @Select({"SELECT * FROM image_set_list where category=#{category} order by create_time desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time")
    })
    List<ImageSetListEntity> findByCategory(@Param("category") String category, @Param("page") int pageStart);

    @Select({"SELECT * FROM image_set_list order by recommend_count desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "view_count", column = "view_count"),
            @Result(property = "recommend_count", column = "recommend_count")
    })
    List<ImageSetListEntity> recommendRank(@Param("page") int pageStart);

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},18"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "view_count", column = "view_count"),
            @Result(property = "recommend_count", column = "recommend_count")
    })
    List<ImageSetListEntity> likeRank(@Param("page") int pageStart);

    @Update({"UPDATE image_set_list set view_count=view_count+1 where id = #{id}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover_url", column = "cover_url"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "img_count", column = "img_count"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "category", column = "category"),
            @Result(property = "label", column = "label"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "view_count", column = "view_count"),
            @Result(property = "recommend_count", column = "recommend_count")
    })
   void addViewCount(@Param("id") int id);
}
