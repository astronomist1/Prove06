package com.example.prove06;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class does the main part of the assignment (not the stretch challenge).
 */
public class SingleCityExperimenter {
    String city;
    /**
     * Gets and displays the current conditions and forecast for a given city.
     */
    SingleCityExperimenter (String city) {
        this.city = city;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public float singleCityWeather() {
        WeatherDataLoader loader = new WeatherDataLoader();
        float temp;
        try {
            // This calls
            WeatherConditions conditions = loader.getWeather(city);
            temp = conditions.getMeasurements().get("temp");
            return temp;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public float singleCityForecast() {
        WeatherDataLoader loader = new WeatherDataLoader();
        float temp;
        try {
            // This calls
            WeatherForecast forecast = loader.getForecast(city);
            printForecast(forecast);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void printForecast(WeatherForecast forecast) {
        System.out.println("Forecast information:");

        float temp;

        for (WeatherForecastItem item : forecast.getForecastItems()) {
            String time = item.getDateText();
            temp = item.getMeasurements().get("temp");

            String conditions = "";
            if (item.getDescriptions().size() > 0) {
                conditions = item.getDescriptions().get(0).getDescription();
            }

            float wind = item.getWind().get("speed");

            System.out.println(String.format("Time: %s, Temp: %sF, Conditions: %s, Wind Speed: %smph",
                    time, temp, conditions, wind));
        }
    }


}
