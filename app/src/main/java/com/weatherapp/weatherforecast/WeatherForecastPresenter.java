package com.weatherapp.weatherforecast;

import com.weatherapp.communication.WeatherForecastService;
import com.weatherapp.datamodels.WeatherForecastList;

import io.reactivex.Observer;

/**
 * Created by SAYYAD on 07-12-2017.
 */

public class WeatherForecastPresenter {
    private final WeatherForecastService service;
    private final WeatherForecastView view;

    public WeatherForecastPresenter(WeatherForecastService service, WeatherForecastView view) {
        this.service = service;
        this.view = view;
    }

    public void getWeatherForecastList() {
        view.showProgressDialog();

        Observer observer = (Observer) service.getWeatherForecastInfo(new WeatherForecastService.GetWeatherForecastListCallback() {
            @Override
            public void onSuccess(WeatherForecastList weatherForecastListResponse) {
                view.hideProgressDialog();
                view.onGetWeatherForecastListSuccess(weatherForecastListResponse);
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgressDialog();
                view.onGetWeatherForecastListFailure(error.getMessage());
            }
        });
    }
}