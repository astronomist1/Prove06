package com.example.prove06;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonCurrentWeather;
    private Button buttonWeatherForecast;
    private TextView textCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCurrentWeather = findViewById(R.id.getTemp);
        buttonWeatherForecast = findViewById(R.id.getForecast);
        textCity = (TextView) findViewById(R.id.editCity);

        buttonCurrentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = textCity.getText().toString();
                startGetTemp(city);
            }
        });

        buttonWeatherForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = textCity.getText().toString();
                startGetForecast(city);
            }
        });
    }

    public void startGetTemp(String city) {
        WeatherThread thread = new WeatherThread(city);
        thread.start();
    }

    public void startGetForecast(String city) {
        ForecastThread thread = new ForecastThread(city);
        thread.start();
    }

    class WeatherThread extends Thread {
        String city;

        WeatherThread(String city) {
            this.city = city;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            SingleCityExperimenter sce = new SingleCityExperimenter(city);
            float temp = sce.singleCityWeather();
            System.out.println("The current weather for " + city + " is: " + temp);

        }
    }

    class ForecastThread extends Thread {
        String city;

        ForecastThread(String city) {
            this.city = city;
        }

        @Override
        public void run() {
            System.out.println("The forecast for " + city + " is: ");
        }
    }
}
