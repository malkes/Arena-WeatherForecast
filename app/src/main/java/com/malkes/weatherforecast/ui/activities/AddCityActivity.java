package com.malkes.weatherforecast.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.adapters.SearchCityAdapter;
import com.malkes.weatherforecast.model.geocoder.Geocoder;
import com.malkes.weatherforecast.model.geocoder.Result;
import com.malkes.weatherforecast.retrofit.GeocodeRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Malkes on 29/01/17.
 */

public class AddCityActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    public static final String EXTRA_RESULT = "extraREsult";

    private EditText etName;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city_layout);
        setupActionbar("Add new city",true);
    }

    @Override
    public void initViews() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        etName = (EditText) findViewById(R.id.edName);
        etName.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s != null && s.length() > 4){
                searchCity(s.toString());
            }

            if(s != null && s.length() == 0){
                listView.setAdapter(null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private synchronized void searchCity(String query){
        progressBar.setVisibility(View.VISIBLE);
        GeocodeRequest.fetchGecodebyName(query, new Callback<Geocoder>() {
            @Override
            public void onResponse(Call<Geocoder> call, Response<Geocoder> response) {
                progressBar.setVisibility(View.GONE);
                if(response != null && response.body() != null && response.body().status.equals("OK")){
                    listView.setAdapter(new SearchCityAdapter(response.body().results));
                }
            }

            @Override
            public void onFailure(Call<Geocoder> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Result result = (Result) parent.getAdapter().getItem(position);
        setResult(RESULT_OK,new Intent().putExtra(EXTRA_RESULT,result));
        finish();
    }
}
