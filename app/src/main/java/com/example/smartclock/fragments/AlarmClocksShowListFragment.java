package com.example.smartclock.fragments;


import android.app.AlarmManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartclock.R;
import com.example.smartclock.adapters.AlarmClockListAdapter;
import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;

import java.util.List;

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
        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        view = inflater.inflate(R.layout.fragment_alarm_clocks_show_list, container, false);
        adapter = new AlarmClockListAdapter();
        listView = (ListView)view.findViewById(R.id.AlarmClockList);
        listView.setAdapter(adapter);
        final AlarmClocksShowListViewModel viewModel =
                ViewModelProviders.of(getActivity()).get(AlarmClocksShowListViewModel.class);
        //Create an observer that updates the UI when the observed data source has been changed
        final Observer<List<AlarmClockItem>> listObserver = new Observer<List<AlarmClockItem>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmClockItem> newList) {
                // Update the UI
                list = newList;
                adapter.setData(list);
                adapter.notifyDataSetChanged();
            }
        };
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position=i;
                AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
                alterDiaglog.setView(R.layout.alarmclock_delete_dialog);//加载进去
                final AlertDialog dialog = alterDiaglog.create();
                View layout = inflater.inflate(R.layout.alarmclock_delete_dialog,null);
                dialog.setView(layout);
                Button cancle=(Button)layout.findViewById(R.id.cancle);
                Button remove=(Button)layout.findViewById(R.id.remove);
                cancle.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                remove.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        list.remove(position);
                        dialog.cancel();
                        adapter.notifyDataSetChanged();
                    }

                });
                dialog.show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
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
                        cancle.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });

                        change.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                int h=Integer.valueOf(hour.getText().toString());
                                int m=Integer.valueOf(minute.getText().toString());
                                AlarmClockItem alarmClockItem=list.get(position);
                                alarmClockItem.setHour(h);
                                alarmClockItem.setMinute(m);
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
