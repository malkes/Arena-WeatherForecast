
package com.malkes.weatherforecast.model.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("viewport")
    @Expose
    public Viewport viewport;

}
