package com.sbehnken.restaurantsnearby;


import retrofit2.http.PUT;

public class Constants {
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search?term=restaurant";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
    public static final String PREFERENCES_LOCATION_KEY = "zipcode";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String ZIPCODE_TOKEN = BuildConfig.ZIPCODE_TOKEN;
}
