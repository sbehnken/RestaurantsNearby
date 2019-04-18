package com.sbehnken.restaurantsnearby.services;

import com.sbehnken.restaurantsnearby.models.Zipcode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ZipcodeInterface {
    @GET("{api_key}/info.json/{zip_code}/degrees")
    Call<Zipcode> validateZipcode(@Path("api_key") String api_key, @Path("zip_code") String zip_code);

}
