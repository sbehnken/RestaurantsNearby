package com.example.myrestaurantdeux.myrestuarantdeux.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.example.myrestaurantdeux.myrestuarantdeux.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private ArrayList<Restaurant> mRestaurants;
    private Context mContext;

    public RestaurantListAdapter(Context context, ArrayList<Restaurant> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);

        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private ImageView mRestaurantImageView;
        private TextView mNameTextView;
        private TextView mCategoryTextView;
        private TextView mRatingTextView;
        private Context mContext;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mRestaurantImageView = (ImageView) itemView.findViewById(R.id.restaurantImageView);
            //todo figure out why it's restaurantnametextview and notnametextview
            mNameTextView = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
            mRatingTextView = (TextView) itemView.findViewById(R.id.ratingTextView);
        }


        public void bindRestaurant(Restaurant restaurant) {
            Picasso.with(mContext).load(restaurant.getImageUrl()).into(mRestaurantImageView);

            mNameTextView.setText(restaurant.getName());
            mCategoryTextView.setText(restaurant.getCategories().get(0));
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}
