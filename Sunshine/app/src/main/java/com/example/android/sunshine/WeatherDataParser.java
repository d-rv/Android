package com.example.android.sunshine;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Diamond Ravi on 3/8/2016.
 */
public class WeatherDataParser {

    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
        throws JSONException {
        JSONObject weather = new JSONObject(weatherJsonStr);
        JSONArray days = weather.getJSONArray("list");
        JSONObject dayInfo = days.getJSONObject(dayIndex);
        JSONObject tempInfo = dayInfo.getJSONObject("temp");
        return tempInfo.getDouble("max");
    }

}
