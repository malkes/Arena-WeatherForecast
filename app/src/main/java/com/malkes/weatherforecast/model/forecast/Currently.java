
package com.malkes.weatherforecast.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currently {

    @SerializedName("time")
    @Expose
    public long time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("nearestStormDistance")
    @Expose
    public double nearestStormDistance;
    @SerializedName("nearestStormBearing")
    @Expose
    public double nearestStormBearing;
    @SerializedName("precipIntensity")
    @Expose
    public double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    public double precipProbability;
    @SerializedName("temperature")
    @Expose
    public double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    public double apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    public double dewPoint;
    @SerializedName("humidity")
    @Expose
    public double humidity;
    @SerializedName("windSpeed")
    @Expose
    public double windSpeed;
    @SerializedName("windBearing")
    @Expose
    public double windBearing;
    @SerializedName("visibility")
    @Expose
    public double visibility;
    @SerializedName("cloudCover")
    @Expose
    public double cloudCover;
    @SerializedName("pressure")
    @Expose
    public double pressure;
    @SerializedName("ozone")
    @Expose
    public double ozone;

}
