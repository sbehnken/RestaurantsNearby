package com.example.myrestaurantdeux.myrestuarantdeux;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.example.myrestaurantdeux.myrestuarantdeux.models.Restaurant;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;

public class RestaurantDetailFragment extends Fragment {
    private ImageView mImageLabel;
    private TextView mNameLabel;
    private TextView mCategoriesLabel;
    private TextView mRatingLabel;
    private TextView mWebsiteLabel;
    private TextView mPhoneLabel;
    private TextView mAddressLabel;
    private TextView mSaveRestaurantButton;

    private Restaurant mRestaurant;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        mImageLabel = view.findViewById(R.id.restaurantImageView);
        mNameLabel = view.findViewById(R.id.restaurantNameTextView);
//        mCategoriesLabel = (TextView) view.findViewById(R.id.);
        mRatingLabel = view.findViewById(R.id.ratingTextView);
        mWebsiteLabel = view.findViewById(R.id.websiteTextView);
        mPhoneLabel = view.findViewById(R.id.phoneTextView);
        mAddressLabel = view.findViewById(R.id.addressTextView);
        mSaveRestaurantButton = view.findViewById(R.id.saveRestaurantButton);

        if(mRestaurant.getImageUrl() != null && !mRestaurant.getImageUrl().isEmpty()) {
            Picasso.with(view.getContext()).load(mRestaurant.getImageUrl()).into(mImageLabel);
        }

        mNameLabel.setText(mRestaurant.getName());
//        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
        mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
        mPhoneLabel.setText((mRestaurant.getPhone()));
        mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));

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

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
    }
}
