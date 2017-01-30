package com.malkes.weatherforecast.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.adapters.CitiesAdapter;
import com.malkes.weatherforecast.model.geocoder.AddressComponent;
import com.malkes.weatherforecast.model.geocoder.City;
import com.malkes.weatherforecast.model.geocoder.Result;
import com.malkes.weatherforecast.ui.activities.AddCityActivity;

import java.util.List;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;

/**
 * Created by Malkes on 28/01/17.
 */

public class CitiesFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE = 100;

    private RecyclerView mRecycler;
    private List<City> list;
    private CitiesAdapter adapter;

    public static CitiesFragment newInstance() {
        return new CitiesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("List of cities");

        View root = inflater.inflate(R.layout.cities_fragment, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.recycler);

        root.findViewById(R.id.fab).setOnClickListener(this);

        loadCities();

        return root;
    }

    private void setupRecycler() {
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                mRecycler,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        deleteCity(view);
                    }
                })
                .setIsVertical(false)
                .create();

        mRecycler.setOnTouchListener(listener);
    }

    private void deleteCity(View view){
        int id = mRecycler.getChildLayoutPosition(view);
        City city = list.get(id);
        if(city.delete()){
            list.remove(id);
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(),"City deleted",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadCities() {
        list = City.listAll(City.class);
        if (list != null && list.size() > 0) {
            adapter = new CitiesAdapter(getActivity(), list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecycler.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
            mRecycler.addItemDecoration(dividerItemDecoration);
            mRecycler.setAdapter(adapter);
            setupRecycler();
        }

    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(getActivity(), AddCityActivity.class), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            Result result = (Result) data.getSerializableExtra(AddCityActivity.EXTRA_RESULT);
            saveNewCity(result);
        }
    }

    private void saveNewCity(Result result) {
        AddressComponent addressComponent = result.addressComponents.get(0);
        City city = new City(addressComponent.longName,
                result.formattedAddress,
                result.geometry.location.lng,
                result.geometry.location.lat);

        if (city.save() > 0) {
            Toast.makeText(getActivity(), "City added with success", Toast.LENGTH_SHORT).show();
            updateList(city);
        } else {
            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateList(City city) {
        list.add(city);
        adapter.notifyItemInserted(list.size() - 1);
    }
}
