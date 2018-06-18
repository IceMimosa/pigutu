package com.pigutu.app.entity;

import java.util.List;

public class ImageSetResponse {
    private ImageSetListEntity imageSet;
    private List<ImageSetEntity> images;
    private List<CommentEntity> comments;
    public ImageSetResponse(ImageSetListEntity imageSet,List<ImageSetEntity> images,List<CommentEntity> comments){
        this.imageSet=imageSet;
        this.images = images;
        this.comments = comments;
    }

    public ImageSetListEntity getImageSet() {
        return imageSet;
    }

    public void setImageSet(ImageSetListEntity imageSet) {
        this.imageSet = imageSet;
    }

    public List<ImageSetEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageSetEntity> images) {
        this.images = images;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
