package com.malkes.weatherforecast;


import android.app.Application;

import com.malkes.weatherforecast.model.geocoder.City;
import com.orm.SugarContext;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Malkes on 31/01/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest="./src/main/AndroidManifest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SugarTest extends Application implements TestLifecycleApplication {


    private long id;
    private City city;

    @Override
    public void beforeTest(Method method) {
        SugarContext.init(this);
    }

    @Test
    public void test1_insert() throws Exception {
        city = new City("City Teste", "City Teste, CT", Double.MIN_VALUE, Double.MAX_VALUE);
        id = city.save();

        assertTrue(id > 0);
    }

    @Test
    public void test2_list() {
        insertList();
        List<City> list = City.listAll(City.class);
        assertNotNull(list);
        assertTrue(list.size() > 0);
        City city = list.get(0);
        assertNotNull(city.cityName);
        assertNotNull(city.formattedName);
        assertNotNull(city.latitude);
        assertNotNull(city.longitude);
    }

    private void insertList(){
        for (int i = 0; i < 5; i++) {
            City city = new City("City " + i, "City Teste, CT", Double.MIN_VALUE, Double.MAX_VALUE);
            city.save();
        }
    }

    @Override
    public void prepareTest(Object test) {

    }

    @Override
    public void afterTest(Method method) {
        if (id > 0 && city != null) {
            city.delete();
        }
    }
}
