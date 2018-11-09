package com.example.myrestaurantdeux.myrestuarantdeux.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast erased in lesson plan but I wanted to keep for learning sake.
                //  Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();

                String location = mLocationEditText.getText().toString();

                // Logs data in the logcat if we're wanting to get that information
                //Log.d(TAG, location);

                        //new instance that takes two parameters. Current context (the environment where the code is running),
                // and the activity class
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);

                //we attach data to an intent as a key/value pair
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
