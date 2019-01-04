package com.example.myrestaurantdeux.myrestuarantdeux.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrestaurantdeux.myrestuarantdeux.R;
import com.example.myrestaurantdeux.myrestuarantdeux.RestaurantDetailActivity;
import com.example.myrestaurantdeux.myrestuarantdeux.models.Restaurant;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mRestaurantImageView;
        private TextView mRestaurantNameTextView;
        private TextView mCategoryTextView;
        private TextView mRatingTextView;
        private Context mContext;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mRestaurantImageView = (ImageView) itemView.findViewById(R.id.restaurantImageView);
            mRestaurantNameTextView = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
            mRatingTextView = (TextView) itemView.findViewById(R.id.ratingTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
            mContext.startActivity(intent);
        }

        public void bindRestaurant(Restaurant restaurant) {
            Picasso.with(mContext).load(restaurant.getImageUrl()).into(mRestaurantImageView);

            mRestaurantNameTextView.setText(restaurant.getName());
            mCategoryTextView.setText(restaurant.getCategories().get(0));
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}
