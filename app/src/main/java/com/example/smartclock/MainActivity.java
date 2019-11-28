package com.example.smartclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.utils.AlarmClocksUtil;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlarmClocksShowListViewModel viewModel;
    private List<AlarmClockItem> list;

    private int idCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        viewModel = ViewModelProviders.of(this).get(AlarmClocksShowListViewModel.class);
        list = AlarmClocksUtil.getClocks(this);

        for(int i=0;i<list.size();i++){
            int temp = Integer.valueOf(list.get(i).getId());
            idCounter = idCounter>temp?idCounter:temp;
        }

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingUpActivity.class);
                startActivityForResult(intent, 0x0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置liveData的值，此时UI得到更新通知
        viewModel.getAlarmClockList().setValue(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存闹钟
        AlarmClocksUtil.saveClocks(list,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==0){
            if(data!=null){
                Bundle bundle = data.getExtras();
                if(bundle!=null){
                    idCounter++;

                    //初始化AlarmClockItem对象
                    AlarmClockItem item = new AlarmClockItem();
                    item.setId(Integer.valueOf(idCounter).toString());
                    item.setHour(bundle.getInt("hour"));
                    item.setMinute(bundle.getInt("minute"));
                    item.setAutoRepeat(bundle.getBoolean("autoRepeat"));
                    item.setShakeWhileRinging(bundle.getBoolean("shakeWhileRinging"));
                    item.setDescription(bundle.getString("description"));
                    item.setEnable(true);

                    //新建PendingIntent
                    AlarmManager Mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(this, ClockActivity.class);

                    PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intent, 0);

                    //将响铃时间设为新创建的闹钟时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    int hour = item.getHour();
                    int min = item.getMinute();
                    calendar.set(Calendar.HOUR, hour);
                    calendar.set(Calendar.MINUTE, min);

                    //启用闹钟
                    try {
                        Mgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        Toast.makeText(MainActivity.this, "设置闹钟成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "设置闹钟失败", Toast.LENGTH_SHORT).show();
                        Log.i("md", "e:"+e.toString());
                    }

                    //将闹钟加入map,list
                    AlarmClocksUtil.getIntentMap().put(item.getId(), alarmIntent);
                    list.add(item);

                    //通知改变视图
                    viewModel.getAlarmClockList().setValue(list);
                }
            }
        }
    }
}
