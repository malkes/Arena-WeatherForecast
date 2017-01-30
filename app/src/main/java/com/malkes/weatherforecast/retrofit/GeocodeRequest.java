package com.malkes.weatherforecast.retrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.malkes.weatherforecast.BuildConfig;
import com.malkes.weatherforecast.model.geocoder.Geocoder;
import com.malkes.weatherforecast.model.places.SearchPlace;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.PublishSubject;

/**
 * Created by Malkes on 28/01/17.
 */

public class GeocodeRequest {

    public static void fetchGecodebyName(String query, Callback<Geocoder> callback){
        WSInterface apiService = RequestManager.getInstance().getGoogleMapsApiService();
        Call<Geocoder> call = apiService.fetchGeocoderByName(query);
        if (callback != null) {
            call.enqueue(callback);
        }
    }

    public static void searchPlace(String query, Callback<SearchPlace> callback){
        WSInterface apiService = RequestManager.getInstance().getGoogleApiService();
        Call<SearchPlace> call = apiService.searchPlace(query, BuildConfig.GOOGLE_API_KEY);
        if (callback != null) {
            call.enqueue(callback);
        }
    }

    public static void fetchPhotoPlace(final String photoReference, final ImageView imageView, final PublishSubject<Bitmap> publishSubject){
        WSInterface apiService = RequestManager.getInstance().getGoogleApiService();
        Call<ResponseBody> call = apiService.fetchPhotoPlace(photoReference,BuildConfig.GOOGLE_API_KEY,1920,1080);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response != null && response.body() != null){
                    Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                    imageView.setImageBitmap(bm);
                    if(publishSubject != null){
                        publishSubject.onNext(bm);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
