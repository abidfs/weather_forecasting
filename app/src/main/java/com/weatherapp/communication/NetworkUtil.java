package com.weatherapp.communication;

import android.util.Log;

import com.weatherapp.BuildConfig;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by SAYYAD on 07-12-2017.
 */

@Module
public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getName();
    File cacheFile;

    public NetworkUtil(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .build();

                        String requestLog = String.format("Sending request %s on %s %n %s",
                                request.url(), chain.connection(), request.headers());
                        Log.d(TAG, "Request: "+requestLog);
                        Response response = chain.proceed(request);

                        String bodyString = response.body().string();
                        Log.d("TAG","Response: "+bodyString);

                        response= response.newBuilder()
                                .body(ResponseBody.create(response.body().contentType(), bodyString))
                                .build();
                        response.cacheResponse();
                        return response;
                    }
                })
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public RestAPIs providesNetworkService(Retrofit retrofit) {
        return retrofit.create(RestAPIs.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public WeatherForecastService providesService(RestAPIs restAPIs) {
        return new WeatherForecastService(restAPIs);
    }
}
