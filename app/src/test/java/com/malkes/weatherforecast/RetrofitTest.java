package com.malkes.weatherforecast;

import com.malkes.weatherforecast.model.forecast.Datum;
import com.malkes.weatherforecast.model.forecast.Forecast;
import com.malkes.weatherforecast.model.geocoder.AddressComponent;
import com.malkes.weatherforecast.model.geocoder.Geocoder;
import com.malkes.weatherforecast.model.geocoder.Result;
import com.malkes.weatherforecast.model.places.SearchPlace;
import com.malkes.weatherforecast.retrofit.RequestManager;
import com.malkes.weatherforecast.retrofit.WSInterface;
import junit.framework.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Malkes on 31/01/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitTest {

    @Test
    public void test1_fetchGeocode() throws Exception{

        WSInterface apiService = RequestManager.getInstance().getGoogleMapsApiService();
        Call<Geocoder> call = apiService.fetchGeocoderByName("San Francisco");
        Geocoder geocoder = call.execute().body();

        Assert.assertNotNull(geocoder);
        Assert.assertNotNull(geocoder.status);
        Assert.assertEquals("OK",geocoder.status);
        Assert.assertNotNull(geocoder.results);
        Assert.assertTrue(geocoder.results.size() > 0);
        for(Result result : geocoder.results){
            Assert.assertNotNull(result);
            Assert.assertNotNull(result.addressComponents);
            Assert.assertTrue(result.addressComponents.size() > 0);
            AddressComponent addressComponent = result.addressComponents.get(0);
            Assert.assertNotNull(addressComponent);
            Assert.assertNotNull(addressComponent.longName);

            Assert.assertNotNull(result.formattedAddress);

            Assert.assertNotNull(result.geometry);
            Assert.assertNotNull(result.geometry.location);
            Assert.assertTrue(result.geometry.location.lat >= -90 && result.geometry.location.lat <= 90);
            Assert.assertTrue(result.geometry.location.lng >= -180 && result.geometry.location.lng <= 180);
        }
    }

    @Test
    public void test2_fetchForecast() throws Exception{
        final  String EXCLUDE = "[minutely,hourly,flags]";
        double latitude = 37.8267;
        double longitude = -122.4233;
        WSInterface apiService = RequestManager.getInstance().getForecastApiService();
        Call<Forecast> call = apiService.fetchForecast(latitude,longitude,EXCLUDE);
        Forecast forecast = call.execute().body();

        Assert.assertNotNull(forecast);
        Assert.assertNotNull(forecast.currently);
        Assert.assertNotNull(forecast.currently.summary);
        Assert.assertNotNull(forecast.currently.icon);
        Assert.assertTrue(forecast.currently.temperature >= -100 && forecast.currently.temperature <= 100);

        Assert.assertNotNull(forecast.daily);
        Assert.assertNotNull(forecast.daily.data);
        Assert.assertTrue(forecast.daily.data.size() > 0);
        for (Datum data : forecast.daily.data){
            Assert.assertNotNull(data);
            Assert.assertNotNull(data.humidity);
            Assert.assertNotNull(data.icon);
            Assert.assertTrue(data.time > 0);
            Assert.assertTrue(data.temperatureMin >= -100 && data.temperatureMin <= 100);
            Assert.assertTrue(data.temperatureMax >= -100 && data.temperatureMax <= 100);
        }
    }

    @Test
    public void test3_searchPlace() throws Exception{
        WSInterface apiService = RequestManager.getInstance().getGoogleApiService();
        Call<SearchPlace> call = apiService.searchPlace("London", BuildConfig.GOOGLE_API_KEY);
        SearchPlace searchPlace = call.execute().body();

        Assert.assertNotNull(searchPlace);
        Assert.assertNotNull(searchPlace.status);
        Assert.assertEquals("OK",searchPlace.status);
        Assert.assertNotNull(searchPlace.results);
        Assert.assertTrue(searchPlace.results.size() > 0);
        Assert.assertNotNull(searchPlace.results.get(0));
        Assert.assertTrue(searchPlace.results.get(0).photos.size() > 0);
        Assert.assertNotNull(searchPlace.results.get(0).photos.get(0).photoReference);
    }

    @Test
    public void test4_fetchPhtoPLace() throws Exception{
        String ref = "CoQBdwAAAGGSsWrQriMHJVXYf-cHHnGSB404gfaYPJTJ4sogdgad1_--opAFEzhkRuJQL9On6U-mbyBTndbmIEiMaxEkJXg_UPEY-XCKd22Z_Qh-UN-vKdy0wVhIi5y1IbPzD_L7C4sA4W1xjQXZ6AdMUCvcwmG3MJL51tP0zsXbXilbGktWEhCOhOoevWk_Okk4LJIT9pMMGhQrnBntCVb6biBE9audE0qK-GYLqQ";

        WSInterface apiService = RequestManager.getInstance().getGoogleApiService();
        Call<ResponseBody> call = apiService.fetchPhotoPlace(ref,BuildConfig.GOOGLE_API_KEY,1920,1080);
        ResponseBody responseBody =  call.execute().body();

        Assert.assertNotNull(responseBody);
    }
}
