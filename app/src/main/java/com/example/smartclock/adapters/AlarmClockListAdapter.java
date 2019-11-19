package com.example.smartclock.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartclock.R;
import com.example.smartclock.entities.AlarmClockItem;

import java.util.List;


class ViewHolder{

    TextView time;
    TextView statue;
    Switch switchStatue;
    ViewHolder(TextView time,TextView statue,Switch switchStatue)
    {
        this.time=time;
        this.statue=statue;
        this.switchStatue=switchStatue;
    }
}

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
        View itemView;
        ViewHolder holder;
        if(convertView==null) {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.alarm_clock_list_item, parent, false);
            TextView time= (TextView)itemView.findViewById(R.id.time);
            TextView statue = (TextView)itemView.findViewById(R.id.statue);
            Switch switchStatue=(Switch)itemView.findViewById(R.id.switchStatue);
            holder=new ViewHolder(time,statue,switchStatue);
            itemView.setTag(holder);
        }else{
            itemView = convertView;
            //取出缓存对象
            holder = (ViewHolder)itemView.getTag();
        }
        AlarmClockItem alarmClockItem=list.get(position);
        holder.time.setText(alarmClockItem.getHour()+":"+alarmClockItem.getMinute());
        if (alarmClockItem.isEnable()){
            holder.statue.setText("闹钟开启");
        }else{
            holder.statue.setText("闹钟关闭");
        }
        holder.switchStatue.setEnabled(alarmClockItem.isEnable());
        return itemView;

    }
}
