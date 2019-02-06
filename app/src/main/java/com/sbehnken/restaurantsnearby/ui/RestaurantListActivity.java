package com.sbehnken.restaurantsnearby.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.sbehnken.restaurantsnearby.Constants;
import com.sbehnken.restaurantsnearby.adapters.RestaurantListAdapter;
import com.sbehnken.restaurantsnearby.models.Restaurant;
import com.sbehnken.restaurantsnearby.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;

    private RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mRecyclerView = findViewById(R.id.recyclerView);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getRestaurants(mRecentAddress);
        }

        String location = getIntent().getStringExtra("zipcode");
        getRestaurants(location);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Restaurants Nearby");

        }
    }
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                    case  android.R.id.home:
                finish();

                case R.id.favorites_item:
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(android.R.id.content, new FavoritesListFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();

                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        public boolean onCreateOptionsMenu(Menu menu) {
           getMenuInflater().inflate(R.menu.overflow_menu, menu);
          return true;
        }

    private void getRestaurants(String location) {
//        String counter; [defined]
//        counter = "hello"; [assigned]
//        String counter = "hello"; [defined & assigned]
//        [type] [instance variable name] = [value]

       final YelpService yelpService = new YelpService();
//       [definition] = [what it is being defined as]
//                [assigned]
//        [type] [instance variable name] = new [instance of type]
//                                                [using the constructor]

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
                    }
                });
            }
        });
    }
}
