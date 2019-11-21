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
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclock.R;
import com.example.smartclock.adapters.AlarmClockListAdapter;
import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmClocksShowListFragment extends Fragment {

    private View view;
    private List<AlarmClockItem> list;

    public AlarmClocksShowListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm_clocks_show_list, container, false);
        final AlarmClocksShowListViewModel viewModel =
                ViewModelProviders.of(getActivity()).get(AlarmClocksShowListViewModel.class);

        //Create an observer that updates the UI when the observed data source has been changed
        final Observer<List<AlarmClockItem>> listObserver = new Observer<List<AlarmClockItem>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmClockItem> newList) {
                // Update the UI
                list = newList;
                AlarmClockListAdapter adapter = new AlarmClockListAdapter(newList);
                ListView list = (ListView)view.findViewById(R.id.AlarmClockList);
                list.setAdapter(adapter);
                Toast.makeText(getContext(), "UI更新成功", Toast.LENGTH_SHORT).show();
            }
        };

        ((ListView)view.findViewById(R.id.AlarmClockList)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        viewModel.getAlarmClockList().observe(this, listObserver);

        return view;
    }

}
