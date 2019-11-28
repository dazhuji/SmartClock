package com.example.smartclock;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class SettingUpActivity extends AppCompatActivity {

    private String[] items = {"全选","星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    Bundle bundle = new Bundle();
    private List<String> selectedItems = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingup);
        Button ok = (Button)findViewById(R.id.ok);
        Button cancel = (Button)findViewById(R.id.cancel);

        //为按钮绑定监听器
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
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
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingUpActivity.this.finish();
            }
        });

        //选择重复模式
        ((TableRow)findViewById(R.id.row1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] selected = {false,false,false,false,false,false,false,false};
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingUpActivity.this);
                builder.setTitle("选择响铃日期")
                        .setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which, boolean b) {
                                if(which==0){
                                    if(b) for(int i=1;i<items.length;i++) selected[i]=true;
                                    else  for(int i=1;i<items.length;i++) selected[i]=false;
                                }else{
                                    selected[which]=true;
                                }
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                selectedItems.clear();
                                for(int i=1;i<selected.length;i++){
                                    if(selected[i]) selectedItems.add(items[i]);
                                }
                                TextView date = (TextView)findViewById(R.id.selectedDate);
                                date.setText(selectedItems.toString());
                                bundle.putString("repeatDay", selectedItems.toString());
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).create().show();
            }
        });

        //选择铃声
        ((TableRow)findViewById(R.id.row2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "选择闹钟铃声:");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_ALARM);
                SettingUpActivity.this.startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            if (data != null) {
                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                if (uri != null) {
                    String ringTonePath = uri.getPath();
                    TextView selectedRingtone = (TextView)findViewById(R.id.selectedRingtone);
                    selectedRingtone.setText(ringTonePath);
                    bundle.putString("song", ringTonePath);
                }
            }
        }
    }
}
