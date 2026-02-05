package com.inclusive.landing.dto;

import com.inclusive.landing.model.Image;

public class ImageDTO {
    
    private String urlImage;
    private String description;

    public ImageDTO(Image image) {
        this.urlImage = image.getUrlImage();
        this.description = image.getDescription();
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
