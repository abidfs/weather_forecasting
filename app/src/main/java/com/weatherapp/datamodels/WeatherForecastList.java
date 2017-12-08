package com.weatherapp.datamodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by SAYYAD on 07-12-2017.
 */
@Generated("org.jsonschema2pojo")
public class WeatherForecastList {
    @SerializedName("cnt")
    @Expose
    private int count;

    @SerializedName("list")
    @Expose
    private ArrayList<WeatherForecastModel> weatherForecastModelList/* = new ArrayList<WeatherForecastModel>()*/;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private int status;

    public List<WeatherForecastModel> getWeatherForecastModelList() {
        return weatherForecastModelList;
    }

    public void setWeatherForecastModelList(ArrayList<WeatherForecastModel> weatherForecastModelList) {
        this.weatherForecastModelList = weatherForecastModelList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
