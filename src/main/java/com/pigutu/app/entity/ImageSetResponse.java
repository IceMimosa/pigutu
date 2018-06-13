package com.pigutu.app.entity;

import java.util.List;

public class ImageSetResponse {
    private ImageSetListEntity imageSet;
    private List<ImageSetEntity> images;
    public ImageSetResponse(ImageSetListEntity imageSet,List<ImageSetEntity> images){
        this.imageSet=imageSet;
        this.images = images;
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
}
