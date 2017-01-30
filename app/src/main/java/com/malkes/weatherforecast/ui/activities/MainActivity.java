package com.malkes.weatherforecast.ui.activities;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.malkes.weatherforecast.R;
import com.malkes.weatherforecast.ui.components.AnimatedImageView;
import com.malkes.weatherforecast.ui.fragments.CitiesFragment;
import com.malkes.weatherforecast.ui.fragments.ForecastFragment;
import com.malkes.weatherforecast.util.InitialLoad;
import rx.functions.Action1;


public class MainActivity extends BaseActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private AnimatedImageView ivNavHeader;
    private TextView tvCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialLoad.checkInitialLoad();

        loadFragment(ForecastFragment.newInstance());
    }

    @Override
    public void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(navigationView.getHeaderCount() > 0){
            ivNavHeader = (AnimatedImageView) navigationView.getHeaderView(0).findViewById(R.id.ivHeader);
            tvCityName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.ivCityName);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_manage:
                loadFragment(CitiesFragment.newInstance());
                break;
            case R.id.nav_forecast:
                loadFragment(ForecastFragment.newInstance());
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment){
        startObservable(fragment);
        getFragmentManager().beginTransaction().replace(R.id.content_main2, fragment).commit();
    }

    private void startObservable(Fragment fragment) {
        if(fragment instanceof ForecastFragment){
            ForecastFragment forecastFragment = (ForecastFragment) fragment;
            forecastFragment.getObservableBackground().subscribe(new Action1<Bitmap>() {
                @Override
                public void call(Bitmap bitmap) {
                    changeNavBackground(bitmap);
                }
            });

            forecastFragment.getObservableCity().subscribe(new Action1<String>() {
                @Override
                public void call(String name) {
                    changeCityName(name);
                }
            });
        }
    }

    private void changeCityName(String name) {
        if(tvCityName != null && name != null){
            tvCityName.setText(name);
        }
    }

    private void changeNavBackground(Bitmap bitmap) {
        if(ivNavHeader != null && bitmap != null){
            ivNavHeader.setImageBitmap(bitmap);
        }
    }
}
