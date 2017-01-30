package com.malkes.weatherforecast.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Malkes on 28/01/17.
 */

public class RequestManager {

    private RequestManager(){}

    private static RequestManager instance;

    public static RequestManager getInstance() {

        if (instance == null) {
            instance = new RequestManager();
        }
        return instance;
    }

    public WSInterface getGoogleApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WSInterface.class);
    }

    public WSInterface getGoogleMapsApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://maps.google.com/maps/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WSInterface.class);
    }

    public WSInterface getForecastApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WSInterface.class);
    }
}
