package com.malkes.weatherforecast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.model.forecast.Datum;
import com.malkes.weatherforecast.util.FormatUtil;
import com.malkes.weatherforecast.util.ImageUtil;

import java.util.List;
import java.util.Locale;

/**
 * Created by Malkes on 28/01/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder>  {
    private Context context;
    private List<Datum> items;

    public ForecastAdapter(Context context, List<Datum> cityList) {
        this.context = context;
        this.items = cityList;
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, null);
        return new ForecastItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {

        Datum data = items.get(position);

        holder.tvDate.setText(FormatUtil.formatDate(data.time,FormatUtil.PATTERN_DDMM));
        holder.tvHumidity.setText(String.format(Locale.getDefault(), "%d%%",(int) (data.humidity * 100)));

        holder.tvMax.setText(FormatUtil.formatTemperature(data.temperatureMax));
        holder.tvMin.setText(FormatUtil.formatTemperature(data.temperatureMin));

        holder.ivIcon.setImageDrawable(ImageUtil.getDrawableByName(context,data.icon));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ForecastItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvHumidity;
        ImageView ivIcon;
        TextView tvMin;
        TextView tvMax;

        ForecastItemViewHolder(View itemView) {
            super(itemView);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvHumidity = (TextView) itemView.findViewById(R.id.tvHumidity);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvMin = (TextView) itemView.findViewById(R.id.tvMin);
            tvMax = (TextView) itemView.findViewById(R.id.tvMax);
        }
    }
}


