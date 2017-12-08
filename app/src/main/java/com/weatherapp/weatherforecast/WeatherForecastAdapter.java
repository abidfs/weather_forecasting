package com.weatherapp.weatherforecast;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.datamodels.WeatherForecastModel;
import com.weatherapp.utils.DateTimeUtils;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SAYYAD on 08-12-2017.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private static final String TAG = WeatherForecastAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<WeatherForecastModel> weatherForecastModelsList;

    public WeatherForecastAdapter(Context context, ArrayList<WeatherForecastModel> weatherForecastModelsList) {
        this.weatherForecastModelsList = weatherForecastModelsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_weather_forecast, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherForecastModel data = weatherForecastModelsList.get(position);

        String convertedDate = DateTimeUtils.convertDateToSpecificFormat(data.getTimestamp(), "yyyy-MM-DD HH:mm:ss", "DD MMM yyyy hh:mm aa");
        holder.tvDate.setText(convertedDate);

        WeatherForecastModel.Temprature temperature = data.getTemprature();
        holder.tvMinTemperature.setText(String.format(context.getString(R.string.minTemperature), temperature.getMin()));
        holder.tvMaxTemperature.setText(String.format(context.getString(R.string.maxTemperature), temperature.getMax()));
        holder.tvHumidity.setText(String.format(context.getString(R.string.humidity), temperature.getHumidity()));

        try {
            ArrayList<WeatherForecastModel.Weather> weather = (ArrayList<WeatherForecastModel.Weather>) data.getWeather();
            holder.tvDescription.setText(String.valueOf(weather.get(0).getDescription()));
        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        if (weatherForecastModelsList != null) {
            return weatherForecastModelsList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvMinTemperature)
        TextView tvMinTemperature;
        @BindView(R.id.tvMaxTemperature)
        TextView tvMaxTemperature;
        @BindView(R.id.tvHumidity)
        TextView tvHumidity;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
