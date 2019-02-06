package com.sbehnken.restaurantsnearby.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.sbehnken.restaurantsnearby.Constants;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    private Button mFindRestaurantsButton;
    private EditText mLocationEditText;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = findViewById(R.id.locationEditText);
        mFindRestaurantsButton = findViewById(R.id.findRestaurantsButton);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        getSupportActionBar().setTitle("Restaurants Nearby");


        mLocationEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(KeyEvent.ACTION_DOWN == 0) {

                    String zipcode = mLocationEditText.getText().toString();
                    if (!(zipcode).equals("")) {
                        addToSharedPreferences(zipcode);
                    }
                }
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    String zipcode = mLocationEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);

                    intent.putExtra("zipcode", zipcode);
                    startActivity(intent);
                    return true;
                }
                return false;
            }

            private void addToSharedPreferences (String zipcode) {
                mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, zipcode).apply();
            }
        });

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v == mFindRestaurantsButton) {

                    String zipcode = mLocationEditText.getText().toString();
                    if(!(zipcode).equals("")) {
                        addToSharedPreferences(zipcode);
                    }
                    Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                    intent.putExtra("zipcode", zipcode);
                    startActivity(intent);
                }
            }

            private void addToSharedPreferences(String zipcode) {
                mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, zipcode).apply();
            }
        });

    }
}

