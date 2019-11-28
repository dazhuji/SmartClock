package com.example.smartclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.smartclock.adapters.RecordAdapter;
import com.example.smartclock.pojo.RecordDate;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private List<RecordDate> data;
    private RecordAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        data = new ArrayList<RecordDate>();
        initData();

        RecordAdapter adapter=new RecordAdapter(data);
        ((ListView)findViewById(R.id.ls)).setAdapter(adapter);
    }

    private void initData() {
        data.add(new RecordDate("2019-11-26",6,30,1));
        data.add(new RecordDate("2019-11-27",8,20,2));
        data.add(new RecordDate("2019-11-28",10,15,3));
    }
}
