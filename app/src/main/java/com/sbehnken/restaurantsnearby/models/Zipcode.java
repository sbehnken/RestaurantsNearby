package com.sbehnken.restaurantsnearby.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zipcode {

    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("timezone")
    @Expose
    private Timezone timezone;
    @SerializedName("acceptable_city_names")
    @Expose
    private List<AcceptableCityName> acceptableCityNames = null;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public List<AcceptableCityName> getAcceptableCityNames() {
        return acceptableCityNames;
    }

    public void setAcceptableCityNames(List<AcceptableCityName> acceptableCityNames) {
        this.acceptableCityNames = acceptableCityNames;
    }

}
