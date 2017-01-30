
package com.malkes.weatherforecast.model.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Southwest implements Serializable{

    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lng")
    @Expose
    public double lng;

}
