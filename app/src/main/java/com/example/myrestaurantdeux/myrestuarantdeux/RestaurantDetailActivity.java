package com.example.myrestaurantdeux.myrestuarantdeux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.example.myrestaurantdeux.myrestuarantdeux.adapters.RestaurantPagerAdapter;
import com.example.myrestaurantdeux.myrestuarantdeux.models.Restaurant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.parceler.Parcels;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RestaurantPagerAdapter adapterViewPager;
    ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        mViewPager = findViewById(R.id.viewPager);

        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new RestaurantPagerAdapter(getSupportFragmentManager(), mRestaurants);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
