package com.sbehnken.restaurantsnearby.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sbehnken.restaurantsnearby.BuildConfig;
import com.sbehnken.restaurantsnearby.models.Zipcode;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZipcodeService {

    private ZipcodeInterface zipcodeInterface;

    public ZipcodeService() {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.zipcodeapi.com/rest/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    zipcodeInterface = retrofit.create(ZipcodeInterface.class);
}

   public Call<Zipcode> validateZipcode(String zipcode) {
        return zipcodeInterface.validateZipcode(BuildConfig.ZIPCODE_TOKEN, zipcode);
    }
}
