
package com.malkes.weatherforecast.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("time")
    @Expose
    public long time;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
//    @SerializedName("sunriseTime")
//    @Expose
//    public long sunriseTime;
//    @SerializedName("sunsetTime")
//    @Expose
//    public long sunsetTime;
//    @SerializedName("moonPhase")
//    @Expose
//    public double moonPhase;
//    @SerializedName("precipIntensity")
//    @Expose
//    public double precipIntensity;
//    @SerializedName("precipIntensityMax")
//    @Expose
//    public double precipIntensityMax;
//    @SerializedName("precipProbability")
//    @Expose
//    public double precipProbability;
    @SerializedName("temperatureMin")
    @Expose
    public double temperatureMin;
//    @SerializedName("temperatureMinTime")
//    @Expose
//    public double temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    public double temperatureMax;
//    @SerializedName("temperatureMaxTime")
//    @Expose
//    public double temperatureMaxTime;
//    @SerializedName("apparentTemperatureMin")
//    @Expose
//    public double apparentTemperatureMin;
//    @SerializedName("apparentTemperatureMinTime")
//    @Expose
//    public double apparentTemperatureMinTime;
//    @SerializedName("apparentTemperatureMax")
//    @Expose
//    public double apparentTemperatureMax;
//    @SerializedName("apparentTemperatureMaxTime")
//    @Expose
//    public double apparentTemperatureMaxTime;
//    @SerializedName("dewPoint")
//    @Expose
//    public double dewPoint;
    @SerializedName("humidity")
    @Expose
    public double humidity;
//    @SerializedName("windSpeed")
//    @Expose
//    public double windSpeed;
//    @SerializedName("windBearing")
//    @Expose
//    public double windBearing;
//    @SerializedName("visibility")
//    @Expose
//    public double visibility;
//    @SerializedName("cloudCover")
//    @Expose
//    public double cloudCover;
//    @SerializedName("pressure")
//    @Expose
//    public double pressure;
//    @SerializedName("ozone")
//    @Expose
//    public double ozone;
//    @SerializedName("precipIntensityMaxTime")
//    @Expose
//    public double precipIntensityMaxTime;
//    @SerializedName("precipType")
//    @Expose
//    public String precipType;

}
