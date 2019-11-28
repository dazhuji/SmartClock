package com.example.smartclock.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartclock.R;
import com.example.smartclock.pojo.RecordDate;

import java.util.List;


public class RecordAdapter extends BaseAdapter {

    class ViewHolder{
        TextView date;
        TextView time;
        TextView days;

        ViewHolder(TextView date,TextView time,TextView days){
            this.date=date;
            this.time=time;
            this.days=days;
        }

    }

    private ViewHolder holder;
    private View itemView;
    private List<RecordDate> datalist;

    public RecordAdapter(List<RecordDate> data){
        this.datalist=data;
    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_record_item,parent,false);
            TextView date=itemView.findViewById(R.id.date);
            TextView time=itemView.findViewById(R.id.time);
            TextView days=itemView.findViewById(R.id.days);
            holder=new ViewHolder(date,time,days);
            itemView.setTag(holder);
        }else{
            itemView=convertView;
            holder=(ViewHolder)itemView.getTag();
        }
        RecordDate rd=datalist.get(position);
        holder.date.setText(rd.getDate());
        holder.time.setText(rd.getHour()+":"+rd.getMinute());
        holder.days.setText(Integer.valueOf(rd.getDays()).toString());
        return itemView;
    }
}
