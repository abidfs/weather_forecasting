package com.weatherapp.weatherforecast;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.weatherapp.BaseActivity;
import com.weatherapp.R;
import com.weatherapp.communication.WeatherForecastService;
import com.weatherapp.datamodels.WeatherForecastList;
import com.weatherapp.datamodels.WeatherForecastModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends BaseActivity implements WeatherForecastView {
    private static final String TAG = WeatherForecastActivity.class.getName();

    @Inject
    WeatherForecastService service;


    @BindView(R.id.rvWeatherForecast)
    RecyclerView rvWeatherForecast;

    @BindView(R.id.pbWeatherForecast)
    ProgressBar pbWeatherForecast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDependencyInjector().inject(this);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);

        initializeViews();

        WeatherForecastPresenter presenter = new WeatherForecastPresenter(service, this);
        presenter.getWeatherForecastList();
    }

    private void initializeViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWeatherForecast.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rvWeatherForecast.addItemDecoration(divider);
    }

    @Override
    public void showProgressDialog() {
        Log.d(TAG, "showProgressDialog");
        pbWeatherForecast.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        Log.d(TAG, "hideProgressDialog");
        pbWeatherForecast.setVisibility(View.GONE);
    }

    @Override
    public void onGetWeatherForecastListSuccess(WeatherForecastList weatherForecastList) {
        Log.d(TAG, "onGetWeatherForecastListSuccess: " + weatherForecastList);
        WeatherForecastAdapter adapter = new WeatherForecastAdapter(WeatherForecastActivity.this,
                (ArrayList<WeatherForecastModel>) weatherForecastList.getWeatherForecastModelList());
        rvWeatherForecast.setAdapter(adapter);
    }

    @Override
    public void onGetWeatherForecastListFailure(String appErrorMessage) {
        Log.d(TAG, "onGetWeatherForecastListFailure");
    }
}
