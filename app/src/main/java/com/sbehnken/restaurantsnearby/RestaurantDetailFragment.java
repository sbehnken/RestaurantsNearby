package com.sbehnken.restaurantsnearby;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbehnken.restaurantsnearby.adapters.RestaurantListAdapter;
import com.sbehnken.restaurantsnearby.models.Restaurant;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class RestaurantDetailFragment extends Fragment {
    private Restaurant mRestaurant;

    public List<Restaurant> restaurants = new ArrayList<>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String RESTAURANTS = "restaurants";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static RestaurantDetailFragment newInstance(Restaurant restaurant) {
        RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(restaurant));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    public RestaurantDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestaurant = Parcels.unwrap(getArguments().getParcelable("restaurant"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        ImageView mImageLabel = view.findViewById(R.id.restaurantImageView);
        final TextView mNameLabel = view.findViewById(R.id.restaurantNameTextView);
        TextView mRatingLabel = view.findViewById(R.id.ratingTextView);
        TextView mWebsiteLabel = view.findViewById(R.id.websiteTextView);
        TextView mPhoneLabel = view.findViewById(R.id.phoneTextView);
        TextView mAddressLabel = view.findViewById(R.id.addressTextView);
        TextView mCuisineLabel = view.findViewById(R.id.cuisineTextView);
        Button mSaveRestaurantsButton = view.findViewById(R.id.saveRestaurantButton);
        final ImageButton mFavoritesButtonOff = view.findViewById(R.id.favorite_button_off);
        final ImageButton mFavoritesButtonOn = view.findViewById(R.id.favorite_button_on);


        if (mRestaurant.getImageUrl() != null && !mRestaurant.getImageUrl().isEmpty()) {
            Picasso.with(view.getContext()).load(mRestaurant.getImageUrl()).into(mImageLabel);
        }

        mNameLabel.setText(mRestaurant.getName());
        mCuisineLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
        mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
        mPhoneLabel.setText((mRestaurant.getPhone()));
        mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));

        mSaveRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mFavoritesButtonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavoritesButtonOff.setVisibility(View.INVISIBLE);
                saveData();
                Toast.makeText( getActivity(), "Saved to Favorites", Toast.LENGTH_SHORT).show();
            }
        });

        mFavoritesButtonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavoritesButtonOff.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Removed from Favorites", Toast.LENGTH_SHORT).show();

            }
        });

        mPhoneLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mRestaurant.getPhone()));
                startActivity(intent);
            }
        });

        mWebsiteLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRestaurant.getWebsite()));
                startActivity(browserIntent);
            }
        });

        mAddressLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=" + mRestaurant.getAddress() + mRestaurant.getName());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        return view;
    }

    public void saveData() {
        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(RESTAURANTS, "");
        Type type = new TypeToken<ArrayList<Restaurant>>() {}.getType();
        List<Restaurant> r = gson.fromJson(json, type);
        r.add(mRestaurant);

        String j = gson.toJson(r);
        mEditor.putString(RESTAURANTS, j);
        mEditor.commit();
    }

}
