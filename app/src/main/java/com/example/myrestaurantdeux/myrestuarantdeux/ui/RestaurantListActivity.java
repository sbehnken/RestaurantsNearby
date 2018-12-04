package com.example.myrestaurantdeux.myrestuarantdeux.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
//    private TextView mLocationTextView;
//    private ListView mListView;
    private RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

//    private String[] restaurants = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House",
//            "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell",
//            "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar",
//            "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };

//    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian",
//            "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban",
//            "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1,
//                restaurants, cuisines);
//        mListView.setAdapter(adapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(RestaurantListActivity.this, restaurant, Toast.LENGTH_LONG).show();
//
//            }
//
//        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the restaurants near: " + location);
        getRestaurants(location);


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
