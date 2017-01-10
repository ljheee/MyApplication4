package com.zovee.lenovo.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/1/3.
 */
public class JsonUtils {

    public static ArrayList<Day> getDays(String jsonString){
        ArrayList<Day> days = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            Log.v("jsonArraydhjytkultlutil",jsonArray.length()+"");
                   for(int i = 0;i < jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("daily");
                        for (int j = 0;j < jsonArray1.length();j++){
                            Day day = new Day();
                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                            day.setDate((String) jsonObject2.get("date"));
                            day.setText_day((String) jsonObject2.get("text_day"));
                            day.setHigh((String) jsonObject2.get("high"));
                            day.setLow((String) jsonObject2.get("low"));
                            days.add(day);
                       }
                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return days;
    }
}
