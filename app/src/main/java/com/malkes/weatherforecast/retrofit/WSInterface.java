package com.malkes.weatherforecast.retrofit;

import com.malkes.weatherforecast.BuildConfig;
import com.malkes.weatherforecast.model.forecast.Forecast;
import com.malkes.weatherforecast.model.geocoder.Geocoder;
import com.malkes.weatherforecast.model.places.SearchPlace;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.key;

/**
 * Created by Malkes on 28/01/17.
 */

public interface WSInterface {

    @GET("api/geocode/json")
    Call<Geocoder> fetchGeocoderByName(@Query("address") String query);

    @GET(BuildConfig.DARK_SKY_KEY + "/{latitude},{longitude}" )
    Call<Forecast> fetchForecast(@Path("latitude") double latitude,@Path("longitude") double longitude,@Query("exclude") String exclude);

    @GET("place/textsearch/json")
    Call<SearchPlace> searchPlace(@Query("query") String query,@Query("key") String key);

    @GET("place/photo")
    Call<ResponseBody> fetchPhotoPlace(@Query("photoreference") String photoreference,
                                       @Query("key") String key,
                                       @Query("maxheight") int maxheight,
                                       @Query("maxwidth") int maxwidth);
}
