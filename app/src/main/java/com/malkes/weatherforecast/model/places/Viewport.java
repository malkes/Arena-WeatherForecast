
package com.malkes.weatherforecast.model.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("northeast")
    @Expose
    public Northeast northeast;
    @SerializedName("southwest")
    @Expose
    public Southwest southwest;

}
