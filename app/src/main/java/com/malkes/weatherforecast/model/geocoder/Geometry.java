
package com.malkes.weatherforecast.model.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geometry implements Serializable{

    @SerializedName("bounds")
    @Expose
    public Bounds bounds;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("viewport")
    @Expose
    public Viewport viewport;

}
