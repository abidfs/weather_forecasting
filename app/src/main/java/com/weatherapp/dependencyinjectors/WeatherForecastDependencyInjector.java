package com.weatherapp.dependencyinjectors;

import com.weatherapp.weatherforecast.WeatherForecastActivity;
import com.weatherapp.communication.NetworkUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Interface which will connect between @Modules and @Inject
 * Created by SAYYAD on 07-12-2017.
 */

@Singleton
@Component(modules = {NetworkUtil.class,})
public interface WeatherForecastDependencyInjector {
    void inject(WeatherForecastActivity activity);
}
