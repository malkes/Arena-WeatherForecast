
package com.malkes.weatherforecast.model.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geocoder {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("status")
    @Expose
    public String status;

}
