package com.weatherapp.weatherforecast;

import com.weatherapp.datamodels.WeatherForecastList;

/**
 * Created by SAYYAD on 07-12-2017.
 */

public interface WeatherForecastView {
    void showProgressDialog();

    void hideProgressDialog();

    void onGetWeatherForecastListSuccess(WeatherForecastList weatherForecastList);

    void onGetWeatherForecastListFailure(String appErrorMessage);
}
