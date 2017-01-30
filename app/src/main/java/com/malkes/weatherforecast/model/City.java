package com.malkes.weatherforecast.model.geocoder;


import com.orm.SugarRecord;


/**
 * Created by Malkes on 28/01/17.
 */


public class City extends SugarRecord{


    public String cityName;
    public String formattedName;
    public double longitude;
    public double latitude;

    public City(){}

    public City(String cityName, String formattedName, double longitude, double latitude) {
        this.cityName = cityName;
        this.formattedName = formattedName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
