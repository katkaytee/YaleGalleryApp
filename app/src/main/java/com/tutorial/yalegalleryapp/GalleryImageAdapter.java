package com.tutorial.yalegalleryapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>{
    private ArrayList<CreateList> galleryList;
    private Context context;

    // Constructor
    public GalleryImageAdapter(Context context, ArrayList<CreateList> galleryList) {
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
                //Toast.makeText(context, "Image", Toast.LENGTH_SHORT).show();
                // Create intent
                Intent intent = new Intent(context, DetailsActivity
                        .class);
                intent.putExtra("title", viewHolder.title.getText());
                intent.putExtra("image", viewHolder.img.getId());

                // Start details activity
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
