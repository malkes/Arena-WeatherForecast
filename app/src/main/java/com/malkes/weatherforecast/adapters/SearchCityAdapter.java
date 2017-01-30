package com.malkes.weatherforecast.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.model.geocoder.Result;

import java.util.List;

/**
 * Created by Malkes on 29/01/17.
 */

public class SearchCityAdapter extends BaseAdapter {

    private List<Result> results;

    public SearchCityAdapter(List<Result> results){
        this.results = results;
    }

    @Override
    public int getCount() {
        return results != null ? results.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Result result = results.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_layout,null);
        }

        TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
        TextView tvFormattedName = (TextView) convertView.findViewById(R.id.tvFormattedName);

        tvCity.setText(result.addressComponents.get(0).longName);
        tvFormattedName.setText(result.formattedAddress);


        return convertView;
    }
}
