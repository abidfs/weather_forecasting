package com.weatherapp.communication;

import com.weatherapp.datamodels.WeatherForecastList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * This class is used to call rest APIs to load the weather forecasting info
 * Created by SAYYAD on 07-12-2017.
 */
public interface RestAPIs {

    //TODO need to add city and API key as a url param
    @GET("forecast?q=London,uk&appid=a1fa87476f31d73df20f31508cc355aa")
    Observable<WeatherForecastList> getWeatherForecastInfo();
}
