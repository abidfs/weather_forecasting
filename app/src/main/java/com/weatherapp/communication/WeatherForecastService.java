package com.weatherapp.communication;

import com.weatherapp.datamodels.WeatherForecastList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAYYAD on 07-12-2017.
 */

public class WeatherForecastService {
    private final RestAPIs restAPIs;

    public WeatherForecastService(RestAPIs restAPIs) {
        this.restAPIs = restAPIs;
    }

    public Observer<WeatherForecastList> getWeatherForecastInfo(final GetWeatherForecastListCallback callback) {
        return restAPIs.getWeatherForecastInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WeatherForecastList>>() {
                    @Override
                    public ObservableSource<? extends WeatherForecastList> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribeWith(new Observer<WeatherForecastList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherForecastList weatherForecastList) {
                        callback.onSuccess(weatherForecastList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetWeatherForecastListCallback {
        void onSuccess(WeatherForecastList weatherForecastListResponse);
        void onError(Throwable error);
    }
}
