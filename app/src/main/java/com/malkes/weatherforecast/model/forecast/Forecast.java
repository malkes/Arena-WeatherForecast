
package com.malkes.weatherforecast.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("latitude")
    @Expose
    public double latitude;
    @SerializedName("longitude")
    @Expose
    public double longitude;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("offset")
    @Expose
    public int offset;
    @SerializedName("currently")
    @Expose
    public Currently currently;
    @SerializedName("daily")
    @Expose
    public Daily daily;

}
