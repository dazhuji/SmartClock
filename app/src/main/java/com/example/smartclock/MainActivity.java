package com.example.smartclock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.utils.AlarmClocksUtil;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;
import com.example.smartclock.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlarmClocksShowListViewModel viewModel;
    private List<AlarmClockItem> list;
    private Button btnAlarmClock,btnClock,btnTimer,btnCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(AlarmClocksShowListViewModel.class);
        list = AlarmClocksUtil.getClocks(this);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==0){
            if(data!=null){
                Bundle bundle = data.getExtras();
                if(bundle!=null){
                    AlarmClockItem item = new AlarmClockItem();
                    item.setHour(bundle.getInt("hour"));
                    item.setMinute(bundle.getInt("minute"));
                    item.setAutoRepeat(bundle.getBoolean("autoRepeat"));
                    item.setShakeWhileRinging(bundle.getBoolean("shakeWhileRinging"));
                    item.setDescription(bundle.getString("description"));
                    item.setEnable(true);
                    list.add(item);
                    viewModel.getAlarmClockList().setValue(list);
                    AlarmClocksUtil.saveClocks(list, this);
                }
            }
        }
    }
}
