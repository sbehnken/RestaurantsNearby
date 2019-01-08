package com.sbehnken.restaurantsnearby.models;

import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant implements Parcelable {

    private String name;
    private String phone;
    private String website;
    private double rating;
    private String imageUrl;
    private ArrayList<String> address;
    private double latitude;
    private double longitude;
    private ArrayList<String> categories;

    public Restaurant(String name, String phone, String website, double rating, String imageUrl,
                      ArrayList<String > address, double latitude, double longitude, ArrayList<String> categories) {

        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    private Restaurant(android.os.Parcel in) {
        name = in.readString();
        phone = in.readString();
        website = in.readString();
        rating = in.readDouble();
        imageUrl = in.readString();
        address = in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
        categories = in.createStringArrayList();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(android.os.Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeDouble(rating);
        dest.writeString(imageUrl);
        dest.writeStringList(address);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeStringList(categories);
    }
}
