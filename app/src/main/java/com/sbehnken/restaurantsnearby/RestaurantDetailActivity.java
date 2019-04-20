package com.sbehnken.restaurantsnearby;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.sbehnken.restaurantsnearby.adapters.RestaurantPagerAdapter;
import com.sbehnken.restaurantsnearby.models.Restaurant;
import com.sbehnken.restaurantsnearby.ui.FavoritesListFragment;

import org.parceler.Parcels;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {
    ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        ViewPager mViewPager = findViewById(R.id.viewPager);

        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        RestaurantPagerAdapter adapterViewPager = new RestaurantPagerAdapter(getSupportFragmentManager(), mRestaurants);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Restaurants Nearby");
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                onBackPressed();
                break;

            case R.id.favorites_item:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(android.R.id.content, new FavoritesListFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }
    }

