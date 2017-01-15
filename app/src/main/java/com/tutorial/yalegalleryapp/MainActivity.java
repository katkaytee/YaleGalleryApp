package com.tutorial.yalegalleryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

//import com.flickr4java.flickr.Flickr;
//import com.flickr4java.flickr.REST;


public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String SECRET = BuildConfig.SECRET;
    private static final String url = "http://api.flickr.com/services/rest/?";

    //Flickr flickr = new Flickr(API_KEY, SECRET, new REST());

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    // TODO: Make these into res strings?
    private String mApiString = "&api_key=" + API_KEY;
    private String mUserIdString = "&user_id=";
    private String mMethodString = "&method=";

    // List of image titles
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12",
            "Img13",
    };

    // List of image ids
    private final Integer image_ids[] = {
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
            R.drawable.touch_icon_0,
    };

    // Go through all the titles and make a CreateList for each to hold
    // image information
    private ArrayList<CreateList> prepareData() {
        ArrayList<CreateList> image = new ArrayList<>();
        for(int i = 0; i < image_titles.length; i++) {
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            image.add(createList);
        }
        return image;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.image_gallery);
        mRecyclerView.setHasFixedSize(true);

        // Initialize GridLayoutManager
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<CreateList> createLists = prepareData();
        GalleryImageAdapter adapter = new GalleryImageAdapter(getApplicationContext(), createLists);
        mRecyclerView.setAdapter(adapter);

        // TODO: figure out
       // String NSID = flickr.people.findByUsername();
    }
}

// TODO:
// Use the url format specified by the API, then use volley to make the
// request using the url



