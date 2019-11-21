package com.example.smartclock.utils;

import android.content.Context;

import com.example.smartclock.pojo.AlarmClockItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlarmClocksUtil {

    public static List<AlarmClockItem> getClocks(Context context)
    {
        List<AlarmClockItem> list = new ArrayList<AlarmClockItem>();
        try{
            String line;
            StringBuilder sb = new StringBuilder();
            InputStream inputStream =  context.getResources().getAssets().open("AlarmClocks.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = bufferedReader.readLine())!=null){sb.append(line); }
            JSONArray jsonArray = new JSONArray(sb.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                AlarmClockItem clockItem;
                JSONObject jo = jsonArray.getJSONObject(i);
                int hour = jo.getInt("hour");
                int minute = jo.getInt("minute");
                boolean enable = jo.getBoolean("enable");
                boolean autoRepeat = jo.getBoolean("autoRepeat");
                boolean shakeWhileRinging = jo.getBoolean("shakeWhileRinging");
                String song = jo.getString("song");
                String description = jo.getString("description");
                JSONArray days = jo.getJSONArray("repeatDay");
                String[] repeatDays = new String[days.length()];
                for(int j=0;j<days.length();j++){
                    repeatDays[j] = days.getString(j);
                }
                clockItem = new AlarmClockItem(hour, minute, enable, autoRepeat,
                        shakeWhileRinging, repeatDays, song, description);
                list.add(clockItem);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void saveClocks(List<AlarmClockItem> lists)
    {
        //TODO 完成保存配置方法的编写
    }
}
