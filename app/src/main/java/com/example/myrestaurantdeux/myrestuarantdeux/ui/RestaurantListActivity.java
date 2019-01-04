package com.example.myrestaurantdeux.myrestuarantdeux.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.example.myrestaurantdeux.myrestuarantdeux.adapters.RestaurantListAdapter;
import com.example.myrestaurantdeux.myrestuarantdeux.models.Restaurant;
import com.example.myrestaurantdeux.myrestuarantdeux.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantListActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mRecyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getRestaurants(location);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case  android.R.id.home:
                finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        public boolean onCreateOptionsMenu(Menu menu) {
            return true;
        }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) { //throws IOException
                restaurants = yelpService.processResults(response);

                RestaurantListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RestaurantListAdapter(getApplicationContext(), restaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

//                        String[] restaurantNames = new String[restaurants.size()];
//                        for(int i = 0; i < restaurantNames.length; i++) {
//                            restaurantNames[i] = restaurants.get(i).getName();
//                        }
//                        ArrayAdapter adapter = new ArrayAdapter(RestaurantListActivity.this,
//                                android.R.layout.simple_list_item_1, restaurantNames);
//                        mListView.setAdapter(adapter);
//
//                        for (Restaurant restaurant : restaurants) {
//                            Log.d(TAG, "Name: " + restaurant.getName());
//                            Log.d(TAG, "Phone: " + restaurant.getPhone());
//                            Log.d(TAG, "Website: " + restaurant.getWebsite());
//                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
//                            Log.d(TAG, "Address " + android.text.TextUtils.join(", ", restaurant.getAddress()));
//                            Log.d(TAG, "Categories " + restaurant.getCategories().toString());
//                        }
                    }
                });
//                try {
//                    String jsonData = response.body().toString();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
//                        restaurants = yelpService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

}
