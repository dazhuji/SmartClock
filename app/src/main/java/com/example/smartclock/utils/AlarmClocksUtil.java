package com.example.smartclock.utils;

import android.content.Context;

import com.example.smartclock.pojo.AlarmClockItem;
import org.json.JSONArray;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class AlarmClocksUtil {

    public static List<AlarmClockItem> getClocks(Context context)
    {
        List<AlarmClockItem> list = new ArrayList<AlarmClockItem>();
        try{
            String line;
            StringBuilder sb = new StringBuilder();
            File file = new File(context.getFilesDir(), "AlarmClocks.json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
                //String song = jo.getString("song");
                String song = "";
                String description = jo.getString("description");
                //JSONArray days = jo.getJSONArray("repeatDay");
                String[] repeatDays = new String[3];
                //for(int j=0;j<days.length();j++){
                //    repeatDays[j] = days.getString(j);
                //}
                clockItem = new AlarmClockItem(hour, minute, enable, autoRepeat,
                        shakeWhileRinging, repeatDays, song, description);
                list.add(clockItem);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveClocks(List<AlarmClockItem> lists,Context context)
    {
        //TODO 完成保存配置方法的编写
        try {
            File file = new File(context.getFilesDir(), "AlarmClocks.json");
            StringBuilder sb = new StringBuilder();
            if(!file.exists()){
                file.createNewFile();
            }
            String result = JSON.toJSONString(lists);
            System.out.println(result);
            Writer writer = new FileWriter(file);
            writer.write(result);
            writer.close();
        }catch (Exception e){

        }
    }
}
