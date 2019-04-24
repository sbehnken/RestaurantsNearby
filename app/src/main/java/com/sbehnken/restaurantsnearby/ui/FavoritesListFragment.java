package com.sbehnken.restaurantsnearby.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbehnken.restaurantsnearby.R;
import com.sbehnken.restaurantsnearby.adapters.RestaurantListAdapter;
import com.sbehnken.restaurantsnearby.models.Restaurant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.sbehnken.restaurantsnearby.RestaurantDetailFragment.RESTAURANTS;


public class FavoritesListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;
    public ArrayList<Restaurant> restaurants = new ArrayList<>();
    private SharedPreferences mSharedPreferences;

    public static final String SHARED_PREFS = "sharedPrefs";

    public FavoritesListFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        mRecyclerView = view.findViewById(R.id.favorite_restaurant_rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFavorites();
    }

    public void getFavorites() {
        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(RESTAURANTS, "");
        Type type = new TypeToken<ArrayList<Restaurant>>() {}.getType();
             List<Restaurant> r = gson.fromJson(json, type);
             restaurants.addAll(r);

        mAdapter = new RestaurantListAdapter(getActivity(), restaurants);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }
        }


