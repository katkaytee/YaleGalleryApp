package com.tutorial.yalegalleryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Katherine on 1/15/17.
 */
public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder> {
    private ArrayList<ItemList> galleryList;
    private Context context;
    private final String TAG = "GalleryImageAdapter";

    // Constructor
    public GalleryImageAdapter(Context context, ArrayList<ItemList> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public GalleryImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout,
                viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final GalleryImageAdapter.ViewHolder viewHolder, int i) {
        // Fill in the data from the ArrayList
        viewHolder.title.setText(galleryList.get(i).getImage_title());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageResource(galleryList.get(i).getImage_ID());

        // Make the ViewHolder clickable
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Make sure this works
                Toast.makeText(context, "Image", Toast.LENGTH_SHORT).show();
                // Create intent
                Log.d(TAG, "onClick");
                Intent intent = new Intent(context, DetailsActivity
                        .class);
                Log.d(TAG, "intent created");

                // Add extras to intent
                viewHolder.img.buildDrawingCache();
                Bitmap image = viewHolder.img.getDrawingCache();
                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                extras.putString("title", (String) viewHolder.title.getText());
                intent.putExtras(extras);
                Log.d(TAG, "extras added");

                // Start details activity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    // Create a ViewHolder that references layout file
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;
        public ViewHolder(View v) {
            super(v);

            // Bind views to fields in the cell_layout.xml file
            title = (TextView)v.findViewById(R.id.title);
            img = (ImageView)v.findViewById(R.id.img);
        }
    }
}
