package com.tutorial.yalegalleryapp;

/**
 * Created by Katherine on 1/15/17.
 * Class to hold information about an image in its cell
 */
public class CreateList {

    private String image_title;
    private Integer image_id;

    // TODO: image title not needed with Flickr images
    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;
    }

    public Integer getImage_ID() {
        return image_id;
    }

    public void setImage_ID(Integer android_image_url) {
        this.image_id = android_image_url;
    }
}
