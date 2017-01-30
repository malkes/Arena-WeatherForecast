package com.malkes.weatherforecast;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Malkes on 28/01/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }
}
