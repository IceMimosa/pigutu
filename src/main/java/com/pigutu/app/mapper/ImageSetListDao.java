package com.pigutu.app.mapper;

import com.pigutu.app.entity.ImageSetListEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
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
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "view_count", column = "view_count"),
            @Result(property = "recommend_count", column = "recommend_count")
    })
    List<ImageSetListEntity> findAllByPage(@Param("page") int pageStart);

    @Select({"SELECT count(*) FROM image_set_list"})
    int count();

    @Select({"SELECT count(*) FROM image_set_list where category = #{category}"})
    int categoryCount(@Param("category") String category);

    /**
     * 搜索排行暂时决定用like_count排序
     *
     * @param keyword
     * @param page
     * @return
     */
    @Select({"SELECT * FROM image_set_list where title like '%${keyword}%' or label like '%${keyword}%' order by like_count desc limit  #{page},18"})
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
    List<ImageSetListEntity> search(@Param("keyword") String keyword, @Param("page") int page);

    @Select({"SELECT count(*) FROM image_set_list where title like '%${keyword}%' or label like '%${keyword}%'"})
    int searchCount(@Param("keyword") String keyword);

    /**
     * 热度 采用view——count首页喜欢排行榜
     * @param pageStart
     * @return
     */
    @Select({"SELECT * FROM image_set_list order by view_count desc limit  #{page},18"})
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
    List<ImageSetListEntity> hotRank(@Param("page") int pageStart);

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
    List<ImageSetListEntity> index(@Param("page") int pageStart);

    @Select({"SELECT * FROM image_set_list where category=#{category} order by like_count desc limit  #{page},18"})
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
    List<ImageSetListEntity> findByCategory(@Param("category") String category, @Param("page") int pageStart);

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
    void addViewCount(@Param("id") int id);

    @Update({"UPDATE image_set_list set like_count=like_count+1 where id = #{id}"})
    void addLikeCount(@Param("id") int id);

    @Select({"SELECT like_count FROM image_set_list where id = #{id}"})
    int likeCount(@Param("id") int id);

    @Select({"SELECT * FROM image_set_list where id = #{id}"})
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
    ImageSetListEntity getImageSetListEntity(@Param("id") int id);

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},#{pageNumber}"})
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
    List<ImageSetListEntity> myRecommend(@Param("page") int pageStart,@Param("pageNumber")int pageNumber);

    //@Param("id")int id,@Param("title")int title,@Param("cover_url")int cover_url,@Param("like_count")int like_count,@Param("img_count")int img_count,@Param("comment_count")int comment_count,
    // @Param("category")int category,@Param("label")int label,@Param("create_time")int create_time,@Param("view_count")int view_count,@Param("recommend_count")int recommend_count
    @Insert({"insert into image_set_list values(#{id},#{title},#{cover_url},#{like_count},#{img_count},#{comment_count},#{category},#{label},#{create_time},#{view_count},#{recommend_count})"})
    int insertImageSetList(ImageSetListEntity imageSetListEntity);
}
