
package com.malkes.weatherforecast.model.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bounds implements Serializable{

    @SerializedName("northeast")
    @Expose
    public Northeast northeast;
    @SerializedName("southwest")
    @Expose
    public Southwest southwest;

}
