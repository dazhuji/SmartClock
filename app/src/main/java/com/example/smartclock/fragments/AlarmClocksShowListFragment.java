package com.example.smartclock.fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartclock.ClockActivity;
import com.example.smartclock.MainActivity;
import com.example.smartclock.R;
import com.example.smartclock.adapters.AlarmClockListAdapter;
import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.utils.AlarmClocksUtil;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.ALARM_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmClocksShowListFragment extends Fragment {

    private View view;
    private AlarmClockListAdapter adapter;
    private ListView listView;
    private List<AlarmClockItem> list;
    private AlarmManager alarmManager;

    public AlarmClocksShowListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        view = inflater.inflate(R.layout.fragment_alarm_clocks_show_list, container, false);
        listView = (ListView)view.findViewById(R.id.AlarmClockList);
        listView.setAdapter(adapter);

        final AlarmClocksShowListViewModel viewModel =
                ViewModelProviders.of(getActivity()).get(AlarmClocksShowListViewModel.class);

        //Create an observer that updates the UI when the observed data source has been changed
        final Observer<List<AlarmClockItem>> listObserver = new Observer<List<AlarmClockItem>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmClockItem> newList) {
                // Update the UI
                //list = newList;
                adapter.setData(list);
                adapter.notifyDataSetChanged();
            }
        };

        //设置删除闹钟监听器
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //初始化对话框
                final int position=i;
                AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
                alterDiaglog.setView(R.layout.alarmclock_delete_dialog);//加载进去
                final AlertDialog dialog = alterDiaglog.create();
                View layout = inflater.inflate(R.layout.alarmclock_delete_dialog,null);
                dialog.setView(layout);
                Button cancel=(Button)layout.findViewById(R.id.cancel);
                Button remove=(Button)layout.findViewById(R.id.remove);

                //为按钮设置监听器
                cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                remove.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        //删除闹钟
                        alarmManager.cancel(AlarmClocksUtil.getIntentMap().
                                get(list.get(position).getId()));
                        list.remove(position);
                        dialog.cancel();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "闹钟已删除", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
                return true;
            }
        });

        //设置修改闹钟时间监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {

                        //初始化对话框
                        final int position=i;
                        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
                        alterDiaglog.setView(R.layout.time_change_dialog);//加载进去
                        final AlertDialog dialog = alterDiaglog.create();
                        View layout = inflater.inflate(R.layout.time_change_dialog,null);
                        dialog.setView(layout);
                        final EditText hour=(EditText)layout.findViewById(R.id.hour);
                        final EditText minute=(EditText )layout.findViewById(R.id.minute);
                        Button cancle=(Button)layout.findViewById(R.id.cancle);
                        Button change=(Button)layout.findViewById(R.id.change);

                        //为按钮设置监听器
                        cancle.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });

                        change.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                //初始化
                                int h=Integer.valueOf(hour.getText().toString());
                                int m=Integer.valueOf(minute.getText().toString());
                                AlarmClockItem alarmClockItem=list.get(position);
                                alarmClockItem.setHour(h);
                                alarmClockItem.setMinute(m);


                                //设置当前时间
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());

                                //根据用户选择的时间来设置Calendar对象
                                c.set(Calendar.HOUR, alarmClockItem.getHour());
                                c.set(Calendar.MINUTE, alarmClockItem.getMinute());

                                //设置AlarmManager在Calendar对应的时间启动Activity
                                alarmManager.cancel(AlarmClocksUtil.getIntentMap().get(
                                        list.get(position).getId()
                                ));
                                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                                        AlarmClocksUtil.getIntentMap().get(list.get(position).getId()));


                                dialog.cancel();
                                adapter.notifyDataSetChanged();
                            }

                        });
                        dialog.show();
                    }
                });
        viewModel.getAlarmClockList().observe(this, listObserver);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
