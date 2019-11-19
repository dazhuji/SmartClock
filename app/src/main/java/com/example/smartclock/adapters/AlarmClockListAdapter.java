package com.example.smartclock.adapters;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.smartclock.entities.AlarmClockItem;

import java.util.List;

public class AlarmClockListAdapter extends BaseAdapter {

    private List<AlarmClockItem> list;

    public AlarmClockListAdapter(List<AlarmClockItem> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list!=null) return list.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        if(list!=null) return list.get(i);
        else return null;
    }

    @Override
    public long getItemId(int i) {
        if(list!=null) return i;
        else return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO 完成AlarmClockListAdapter的getView方法的编写

        return null;
    }
}
