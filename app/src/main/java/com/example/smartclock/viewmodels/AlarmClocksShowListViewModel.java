package com.example.smartclock.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartclock.entities.AlarmClockItem;

import java.util.List;


public class AlarmClocksShowListViewModel extends ViewModel {
    private MutableLiveData<List<AlarmClockItem>> alarmClockList;

    public MutableLiveData<List<AlarmClockItem>> getAlarmClockList() {
        if(alarmClockList==null){
            alarmClockList = new MutableLiveData<List<AlarmClockItem>>();
        }
        return alarmClockList;
    }
}
