package com.example.smartclock;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartclock.pojo.AlarmClockItem;
import com.example.smartclock.viewmodels.AlarmClocksShowListViewModel;


public class SettingUpActivity extends AppCompatActivity {

    private AlarmClocksShowListViewModel viewModel;
    private Button ok,cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingup);
        ok = (Button)findViewById(R.id.ok);
        cancel = (Button)findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                TimePicker timePicker = (TimePicker)findViewById(R.id.time);
                Switch switch1 = (Switch)findViewById(R.id.switch7);
                Switch switch2 = (Switch)findViewById(R.id.switch8);
                TextView description = (TextView)findViewById(R.id.description);
                bundle.putInt("hour",timePicker.getHour());
                bundle.putInt("minute",timePicker.getMinute());
                bundle.putBoolean("autoRepeat", switch1.isEnabled());
                bundle.putBoolean("shakeWhileRinging", switch2.isEnabled());
                bundle.putString("description", description.getText().toString());
                intent.putExtras(bundle);
                SettingUpActivity.this.setResult(0,intent);
                SettingUpActivity.this.finish();
            }
        });
    }
}
