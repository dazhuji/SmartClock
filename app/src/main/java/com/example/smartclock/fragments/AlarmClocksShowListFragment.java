package com.example.smartclock.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smartclock.R;
import com.example.smartclock.adapters.AlarmClockListAdapter;
import com.example.smartclock.entities.AlarmClockItem;
import com.example.smartclock.utils.AlarmClocksUtil;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmClocksShowListFragment extends Fragment {

    private AlarmClocksShowListViewModel viewModel;
    private View view;

    public AlarmClocksShowListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm_clocks_show_list, container, false);
        viewModel = ViewModelProviders.of(this).get(AlarmClocksShowListViewModel.class);

        //Create an observer that updates the UI when the observed data source has been changed
        final Observer<List<AlarmClockItem>> nameObserver = new Observer<List<AlarmClockItem>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmClockItem> newList) {
                // Update the UI
                AlarmClockListAdapter adapter = new AlarmClockListAdapter(newList);
                ListView list = (ListView)view.findViewById(R.id.AlarmClockList);
                list.setAdapter(adapter);
            }
        };

        List<AlarmClockItem> list = AlarmClocksUtil.getClocks(getContext());
        AlarmClockListAdapter adapter = new AlarmClockListAdapter(list);
        viewModel.getAlarmClockList().setValue(list);

        ((ListView)view.findViewById(R.id.AlarmClockList)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

        return view;
    }

}
