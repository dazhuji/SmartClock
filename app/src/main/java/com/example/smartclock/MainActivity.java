package com.example.smartclock;

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
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置liveData的值，此时UI得到更新通知
        viewModel.getAlarmClockList().setValue(list);
    }
}
