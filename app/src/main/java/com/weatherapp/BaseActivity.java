package com.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weatherapp.communication.NetworkUtil;
import com.weatherapp.dependencyinjectors.DaggerWeatherForecastDependencyInjector;
import com.weatherapp.dependencyinjectors.WeatherForecastDependencyInjector;

import java.io.File;

/**
 * Created by SAYYAD on 07-12-2017.
 */

public class BaseActivity extends AppCompatActivity {
    WeatherForecastDependencyInjector dependencyInjector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        dependencyInjector = DaggerWeatherForecastDependencyInjector.builder().networkUtil(new NetworkUtil(cacheFile)).build();

    }

    public WeatherForecastDependencyInjector getDependencyInjector() {
        return dependencyInjector;
    }
}
