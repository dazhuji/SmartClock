package com.example.smartclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置liveData的值，此时UI得到更新通知
        viewModel.getAlarmClockList().setValue(list);
    }
}
