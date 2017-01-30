
package com.malkes.weatherforecast.model.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lng")
    @Expose
    public double lng;

}
