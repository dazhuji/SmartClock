package com.example.smartclock.util;

import android.content.Context;

import com.example.smartclock.entities.ClockItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ClocksUtil {

    public static List<ClockItem> getClocks(Context context)
    {
        List<ClockItem> list = new ArrayList<ClockItem>();
        try{
            String line;
            StringBuilder sb = new StringBuilder();
            InputStream inputStream =  context.getAssets().open("Clocks.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = bufferedReader.readLine())!=null){sb.append(line); }
            JSONArray jsonArray = new JSONArray(sb.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                ClockItem clockItem;
                JSONObject object = new JSONObject(jsonArray.getString(i));
                int hour = object.getInt("hour");
                int minute = object.getInt("minute");
                boolean enable = object.getBoolean("enable");
                boolean autoRepeat = object.getBoolean("autoRepeat");
                boolean shakeWhileRinging = object.getBoolean("shakeWhileRinging");
                String song = object.getString("song");
                String description = object.getString("description");
                JSONArray days = new JSONArray(object.getString("repeatDays"));
                String[] repeatDays = new String[days.length()];
                for(int j=0;j<days.length();j++){
                    repeatDays[j] = days.getString(j);
                }
                clockItem = new ClockItem(hour, minute, enable, autoRepeat,
                        shakeWhileRinging, repeatDays, song, description);
                list.add(clockItem);

            }
        }catch (Exception e)
        {
            return null;
        }
        return list;
    }

    public static void saveClocks(List<ClockItem> lists)
    {
        //TODO 完成保存配置方法的编写
    }
}
