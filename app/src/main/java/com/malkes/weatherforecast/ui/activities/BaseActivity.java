package com.malkes.weatherforecast.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malkes.weatherforecast.R;


/**
 * Created by Malkes on 29/01/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void initViews();

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
    }

    public void setupActionbar(String title, boolean displayHome) {
        setupActionbar(title, -1, displayHome);
    }
    public void setupActionbar(int title, boolean displayHome) {
        setupActionbar(title, -1, displayHome);
    }

    public void setupActionbar(int title, int icon, boolean displayHome) {
        setupActionbar(getString(title),icon,displayHome);
    }
    public void setupActionbar(String title, int icon, boolean displayHome) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(title);
                actionBar.setDisplayHomeAsUpEnabled(displayHome);
                if (icon != -1)
                    actionBar.setHomeAsUpIndicator(icon);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
