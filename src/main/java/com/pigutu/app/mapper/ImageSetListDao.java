package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pigutu.app.entity.ImageSetListEntity;
import com.pigutu.app.entity.LikeRecordEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import com.pigutu.app.mapper.mybatis.OrderBy;
import com.pigutu.app.mapper.mybatis.QueryCondition;
import com.pigutu.app.utils.TuConfig;
import org.apache.ibatis.annotations.*;
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

    @Insert("insert into like_record(ip,all_images_id,title,cover_url,time) values (#{ip},#{id},#{title},#{cover_url},now());")
    long insertLikeRecord(@Param("ip") String ip,@Param("id") int id,@Param("title")String title,@Param("cover_url")String coverUrl);

    @Select("select * from image_set_list where id = #{id}")
    ImageSetListEntity getLikeRecordCoverUrl(@Param("id") int id);

    @Select("select max(id) as id,ip,title,time,all_images_id,cover_url from like_record group by all_images_id order by id desc limit 0,6")
    List<LikeRecordEntity> getLikeRecord();

    @Select({"SELECT * FROM image_set_list order by like_count desc limit  #{page},#{pageNumber}"})
    List<ImageSetListEntity> myRecommend(@Param("page") int pageStart,@Param("pageNumber")int pageNumber);

    @Insert("INSERT INTO image_set_list (`category`, `comment_count`, `cover_url`, `create_time`, `img_count`, `label`, `like_count`, `title`, `view_count`, `recommend_count`) VALUES (#{category},#{comment_count},#{cover_url},now(),#{img_count},#{label},#{like_count},#{title},#{view_count},#{recommend_count})")
    int insertImageSetList(@Param("category")String category,@Param("comment_count")int comment_count,@Param("cover_url")String cover_url,@Param("img_count")int img_count,@Param("label")String label,@Param("like_count")int like_count,@Param("title")String title,@Param("view_count")int view_count,@Param("recommend_count")int recommend_count);

    /*default long insertImageSetList(ImageSetListEntity imageSetListEntity){
        return insert(imageSetListEntity);
    }*/
}
