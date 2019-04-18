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

    private Button mFindRestaurantsButton;
    private EditText mLocationEditText;
    private String zipcode;
    private String mRecentAddress;

//    private DatabaseReference mSearchedLocationReference;
//
//    private ValueEventListener mSearchedLocationReferenceListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSearchedLocationReference = FirebaseDatabase.getInstance()
//                .getReference().child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

//        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Zipcodes updated", "zipcode:" + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        mLocationEditText = findViewById(R.id.locationEditText);
        mFindRestaurantsButton = findViewById(R.id.findRestaurantsButton);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference();

//        ref.child("<childNodeName>").setValue("<someValue");

        getSupportActionBar().setTitle("Restaurants Nearby");

        mLocationEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(KeyEvent.ACTION_DOWN == 0) {
//
//                    String zipcode = mLocationEditText.getText().toString();
//                    if (!(zipcode).equals("")) {
//                        addToSharedPreferences(zipcode);
//                    }
//                }

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        zipcode = mLocationEditText.getText().toString();
                        getZipcode(zipcode);

//                        Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
//                        intent.putExtra("zipcode", zipcode);
//                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(v == mFindRestaurantsButton) {


//                    saveLocationToFirebase(zipcode);

//                    if(!(zipcode).equals("")) {
//                        addToSharedPreferences(zipcode);
//                    }

                getZipcode(zipcode);


//                }
            }

//                public void saveLocationToFirebase(String zipcode) {
//                    mSearchedLocationReference.push().setValue(zipcode);
//                }

//            protected void onDestroy() {
//                MainActivity.super.onDestroy();
//                mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
//                }

        });

        }

        private void getZipcode(String word) {
        ZipcodeService zipcodeService = new ZipcodeService();
        zipcodeService.validateZipcode(word).enqueue(new Callback<Zipcode>() {
            @Override
            public void onResponse(Call<Zipcode> call, Response<Zipcode> response) {
                    if(response.code() == 404 || response.code() == 400) {
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
