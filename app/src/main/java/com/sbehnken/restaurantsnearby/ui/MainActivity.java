package com.sbehnken.restaurantsnearby.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sbehnken.restaurantsnearby.R;
import com.sbehnken.restaurantsnearby.models.Zipcode;
import com.sbehnken.restaurantsnearby.services.ZipcodeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mLocationEditText;
    private String zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = findViewById(R.id.locationEditText);
        Button mFindRestaurantsButton = findViewById(R.id.findRestaurantsButton);

        getSupportActionBar().setTitle("Restaurants Nearby");

        mLocationEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    zipcode = mLocationEditText.getText().toString();
                    getZipcode(zipcode);
                    return true;
                }
                return false;
            }
        });

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getZipcode(zipcode);
            }
        });
    }

    private void getZipcode(String word) {
        ZipcodeService zipcodeService = new ZipcodeService();
        zipcodeService.validateZipcode(word).enqueue(new Callback<Zipcode>() {
            @Override
            public void onResponse(Call<Zipcode> call, Response<Zipcode> response) {
                if (response.code() == 404 || response.code() == 400) {
                    Toast.makeText(getApplicationContext(), "Please input real zip code", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                    intent.putExtra("zipcode", zipcode);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Zipcode> call, Throwable t) {
                Log.e("Response fail", t.getMessage());
            }
        });
    }
}
