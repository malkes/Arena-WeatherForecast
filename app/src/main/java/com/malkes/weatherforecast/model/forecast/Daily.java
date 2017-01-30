
package com.malkes.weatherforecast.model.forecast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily {

    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

}
