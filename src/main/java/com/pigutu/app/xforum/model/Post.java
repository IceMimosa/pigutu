package com.pigutu.app.xforum.model;

import com.pigutu.app.annotation.Column;
import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Result;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseModel {
    public User user = new User();

    public int bar_id = 0; // 1美图 2美图吧 3斗图 4斗图吧
    public Date publish_time = new Date(System.currentTimeMillis());//发布时间
    public int status = 0;//帖子状态 0正常 1 封禁
    public int count_read = 0;
    public int count_like = 0;
    public int count_reply = 0;
    public int count_forward = 0;//转发数量
    public String content = ""; //内容zhe
    public Date last_update_time = new Date(System.currentTimeMillis()); //最后更新时间
    public ArrayList<String> image_list = new ArrayList();//显示的图片，最多9张，暂定
}
