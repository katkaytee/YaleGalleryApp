package com.tutorial.yalegalleryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String USER = "12208415@N08"; // User ID for yaleuniversity

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> imageUrls = new ArrayList<>();

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.image_gallery);
        recyclerView.setHasFixedSize(true);

        // Initialize GridLayoutManager
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        final GalleryImageAdapter adapter = new GalleryImageAdapter(
                getApplicationContext(), imageUrls);
        recyclerView.setAdapter(adapter);

        // Build the url we need
        String url = FlickrManager.build_url("flickr.people.getPublicPhotos", USER);
        Log.d(TAG, url);

        // Create Volley RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, new
                Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parse JSON
                            JSONObject photos = response.getJSONObject("photos");
                            JSONArray dataArray = photos.getJSONArray("photo");
                            int thumbnailsCount = dataArray.length();

                            // Go through all the images and get the url for each one
                            for (int i = 0; i < thumbnailsCount; i++) {
                                JSONObject thumbnail = dataArray.getJSONObject(i);
                                String farm = thumbnail.getString("farm");
                                String server = thumbnail.getString("server");
                                String id = thumbnail.getString("id");
                                String secret = thumbnail.getString("secret");

                                String imageUrl = String.format("https://farm%s.staticflickr" +
                                        ".com/%s/%s_%s.jpg", farm, server, id, secret);
                                Log.d(TAG, imageUrl);
                                imageUrls.add(imageUrl);
                            }

                            // Make sure to notify the adapter
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle Error
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "Error");
            }
        });

        requestQueue.add(obreq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();;
        requestQueue.cancelAll(this);
    }
}



