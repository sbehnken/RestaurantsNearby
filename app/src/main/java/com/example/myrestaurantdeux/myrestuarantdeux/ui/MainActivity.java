package com.example.myrestaurantdeux.myrestuarantdeux.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myrestaurantdeux.myrestuarantdeux.R;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mFindRestaurantsButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);

        mLocationEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    String location = mLocationEditText.getText().toString();

                    //new instance that takes two parameters. Current context (the environment where the code is running),
                    // and the activity class
                    Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);

                    //we attach data to an intent as a key/value pair
                    intent.putExtra("location", location);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        String location = mLocationEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);

                //we attach data to an intent as a key/value pair
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

    }
}

