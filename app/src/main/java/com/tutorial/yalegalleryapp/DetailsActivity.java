package com.tutorial.yalegalleryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Katherine on 1/15/17.
 */
public class DetailsActivity extends AppCompatActivity {
    String TAG = DetailsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_details);

        // Get extras
        Bundle extras = getIntent().getExtras();
        String imageUrl = extras.getString("imageurl");

        // Set the view
        ImageView imageView = (ImageView) findViewById(R.id.image_details);
        Picasso.with(this).load(imageUrl).into(imageView);
    }
}
