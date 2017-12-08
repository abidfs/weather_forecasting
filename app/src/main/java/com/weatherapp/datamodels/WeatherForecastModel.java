package com.weatherapp.datamodels;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

/**
 * Created by SAYYAD on 07-12-2017.
 */

@Generated("org.jsonschema2pojo")
public class WeatherForecastModel {
    @SerializedName("dt_txt")
    @Expose
    private String timestamp;

    @SerializedName("main")
    @Expose
    private Temprature temprature;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

   public class Temprature{
        @SerializedName("temp_min")
        @Expose
        private float min;
        @SerializedName("temp_max")
        @Expose
        private float max;
        @SerializedName("humidity")
        @Expose
        private  float humidity;

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

   public class Weather{
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Temprature getTemprature() {
        return temprature;
    }

    public void setTemprature(Temprature temprature) {
        this.temprature = temprature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
