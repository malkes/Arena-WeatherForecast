package com.malkes.weatherforecast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.model.geocoder.City;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malkes on 28/01/17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityItemViewHolder>  {
    private Context context;
    private List<City> items;

    public CitiesAdapter(Context context,List<City> cityList) {
        this.context = context;
        this.items = cityList;
    }

    @Override
    public CityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_layout, null);
        return new CityItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(CityItemViewHolder holder, int position) {

        City item = items.get(position);

        holder.city.setText(item.cityName);
        holder.formattedName.setText(item.formattedName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class CityItemViewHolder extends RecyclerView.ViewHolder {

        public TextView city;
        public TextView formattedName;

        public CityItemViewHolder(View itemView) {
            super(itemView);

            city = (TextView) itemView.findViewById(R.id.tvCity);
            formattedName = (TextView) itemView.findViewById(R.id.tvFormattedName);
        }
    }
}


