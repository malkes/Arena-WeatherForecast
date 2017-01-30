package com.malkes.weatherforecast.retrofit;

import com.malkes.weatherforecast.model.forecast.Forecast;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Malkes on 28/01/17.
 */

public class ForecastRequest {
    private static final  String EXCLUDE = "[minutely,hourly,flags]";

    public static void fetchForecast(double latitude,double longitude, Callback<Forecast> callback){
        WSInterface apiService = RequestManager.getInstance().getForecastApiService();
        Call<Forecast> call = apiService.fetchForecast(latitude,longitude,EXCLUDE);
        if (callback != null) {
            call.enqueue(callback);
        }
    }
}
