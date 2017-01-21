package com.tutorial.yalegalleryapp;

/**
 * Created by Katherine on 1/16/17.
 * Class used to help get photos from Flickr
 */

public class FlickrManager {
    private static final String API_KEY = BuildConfig.API_KEY;

    private static final String flickr_base = "https://api.flickr.com/services/rest/?&method=";
    private static final String flickr_api_key = "&api_key=";
    private static final String flickr_user_id = "&user_id=";
    private static final String flickr_format_json = "&format=json&nojsoncallback=1";
    private static final String flickr_per_page = "&per_page=500";

    public static String build_url(String method, String user) {
        String url = flickr_base + method + flickr_api_key + API_KEY +
                flickr_user_id + user + flickr_format_json + flickr_per_page;
        return url;

    }
}
