package com.malkes.weatherforecast.util;

import com.malkes.weatherforecast.model.geocoder.City;

import java.util.List;

/**
 * Created by Malkes on 28/01/17.
 */

public class InitialLoad {

    public static void checkInitialLoad(){
        List<City> list = City.listAll(City.class);
        if(list != null && list.size() == 0){
            populateCities();
        }
    }

    private static void populateCities(){
        new City("Dublin","Dublin, Ireland",-6.2603097,53.3498053).save();
        new City("London","London, UK",-0.1277583,51.5073509).save();
        new City("New York","New York, NY, USA",-74.0059413,40.7127837).save();
        new City("Barcelona","Barcelona, Spain",2.1734035,41.3850639).save();
    }
}
