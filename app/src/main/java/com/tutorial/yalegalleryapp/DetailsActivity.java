package com.tutorial.yalegalleryapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Katherine on 1/15/17.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get extras
        Bundle extras = getIntent().getExtras();
        Bitmap bitmap = extras.getParcelable("imagebitmap");
        String title = extras.getString("title");

        // Set the views
        TextView titleTextView = (TextView) findViewById(R.id.title_details);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image_details);
        imageView.setImageBitmap(bitmap);
    }
}
