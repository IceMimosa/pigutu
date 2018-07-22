package com.pigutu.app.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class ApiIndexEntity {
    @Transient
    private List<ImageSetListEntity> carousel;
    private List<ImageSetListEntity> hot;

    /* private List<ImageSetListEntity> minxing;
     private List<ImageSetListEntity> qincun;
     private List<ImageSetListEntity> yundong;
     private List<ImageSetListEntity> dongman;
     private List<ImageSetListEntity> mote;
     private List<ImageSetListEntity> bijini;
     private List<ImageSetListEntity> neiyi;
     private List<ImageSetListEntity> cosplay;
 */
    private List<Category> categories;

    /* private List<ImageSetListEntity> minxingViewCount;
     private List<ImageSetListEntity> qincunViewCount;
     private List<ImageSetListEntity> yundongViewCount;
     private List<ImageSetListEntity> dongmanViewCount;
     private List<ImageSetListEntity> moteViewCount;
     private List<ImageSetListEntity> bijiniViewCount;
     private List<ImageSetListEntity> neiyiViewCount;
     private List<ImageSetListEntity> cosplayViewCount;*/
    @Data
    public static class Category {
        private String name;
        private List<ImageSetListEntity> categoryItem;
        private List<ImageSetListEntity> categoryViewCount;

        public Category(String name, List<ImageSetListEntity> categoryItem, List<ImageSetListEntity> categoryViewCount) {
            this.name = name;
            this.categoryItem = categoryItem;
            this.categoryViewCount = categoryViewCount;
        }
    }
}
