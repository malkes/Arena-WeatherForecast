package com.malkes.weatherforecast.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.adapters.ForecastAdapter;
import com.malkes.weatherforecast.model.forecast.Currently;
import com.malkes.weatherforecast.model.forecast.Datum;
import com.malkes.weatherforecast.model.forecast.Forecast;
import com.malkes.weatherforecast.model.geocoder.City;
import com.malkes.weatherforecast.model.places.SearchPlace;
import com.malkes.weatherforecast.retrofit.ForecastRequest;
import com.malkes.weatherforecast.retrofit.GeocodeRequest;
import com.malkes.weatherforecast.ui.components.AnimatedImageView;
import com.malkes.weatherforecast.ui.components.AnimatedTextView;
import com.malkes.weatherforecast.util.ImageUtil;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.subjects.PublishSubject;

import static com.malkes.weatherforecast.R.id.recycler;


/**
 * Created by Malkes on 29/01/17.
 */

public class ForecastFragment extends Fragment implements  AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecycler;
    private AnimatedImageView animatedImageView;
    private Spinner mSpinner;
    private TextView tvSummary;
    private AnimatedTextView tvTemperature;
    private ImageView ivIcon;
    private SwipeRefreshLayout swipeLayout;
    private City currentCity;

    private final PublishSubject<Bitmap> onUpdateBackground = PublishSubject.create();
    private final PublishSubject<String> onUpdateCity = PublishSubject.create();

    public static ForecastFragment newInstance(){
        return new ForecastFragment();
    }

    public Observable<Bitmap> getObservableBackground(){
        return onUpdateBackground.asObservable();
    }
    public Observable<String> getObservableCity(){
        return onUpdateCity.asObservable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.forecast_fragment,container,false);

        getActivity().setTitle(R.string.title_activity_main);

        initViews(root);

        loadCities();

        return root;
    }

    private void initViews(View root){
        swipeLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);

        tvTemperature = (AnimatedTextView) root.findViewById(R.id.tvTemperature);
        tvSummary = (TextView) root.findViewById(R.id.tvSummary);
        ivIcon = (ImageView) root.findViewById(R.id.ivIcon);


        mRecycler = (RecyclerView) root.findViewById(recycler);
        animatedImageView = (AnimatedImageView) root.findViewById(R.id.ivBackground);
        mSpinner = (Spinner) root.findViewById(R.id.spCity);
        mSpinner.setOnItemSelectedListener(this);
    }

    private void loadCities(){
        List<City> list = City.listAll(City.class);
        if(list != null && list.size() > 0){
            ArrayAdapter<City> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,list);
            mSpinner.setAdapter(adapter);
        }

    }

    private void loadForecast(City city){
        ForecastRequest.fetchForecast(city.latitude, city.longitude, new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                swipeLayout.setRefreshing(false);
                if(response != null && response.body() != null){
                    if(response.body().daily != null && response.body().daily.data.size() > 0){
                        fillHeader(response.body().currently);
                        fillNextDays(response.body().daily.data);
                    }
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d("LM","LM");
            }
        });
    }

    private void fillHeader(Currently currently) {
        tvTemperature.setAnimatedText(currently.temperature);
        ivIcon.setImageDrawable(ImageUtil.getDrawableByName(getActivity(),currently.icon));
        tvSummary.setText(currently.summary);
    }

    private void fillNextDays(List<Datum> list){

        Context context = getActivity();
        mRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        mRecycler.setAdapter(new ForecastAdapter(context,list));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        City city = (City) parent.getAdapter().getItem(position);
        currentCity = city;
        if(onUpdateCity != null){
            onUpdateCity.onNext(city.cityName);
        }
        loadForecast(city);

        loadPhotoCity(city);


    }

    private void loadPhotoCity(City city) {

        GeocodeRequest.searchPlace(city.cityName, new Callback<SearchPlace>() {
            @Override
            public void onResponse(Call<SearchPlace> call, Response<SearchPlace> response) {
                if(response != null && response.body() != null && response.body().results != null && response.body().results.size() > 0){
                    if(response.body().results.get(0).photos != null && response.body().results.get(0).photos.size() > 0){
                        String ref = response.body().results.get(0).photos.get(0).photoReference;
                        GeocodeRequest.fetchPhotoPlace(ref, animatedImageView,onUpdateBackground);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchPlace> call, Throwable t) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRefresh() {
        if(currentCity != null){
            loadForecast(currentCity);
        }else{
            swipeLayout.setRefreshing(false);
        }
    }
}
