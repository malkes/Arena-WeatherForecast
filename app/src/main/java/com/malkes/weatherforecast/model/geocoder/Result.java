
package com.malkes.weatherforecast.model.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable{

    @SerializedName("address_components")
    @Expose
    public List<AddressComponent> addressComponents = null;
    @SerializedName("formatted_address")
    @Expose
    public String formattedAddress;
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("types")
    @Expose
    public List<String> types = null;

}
