package com.rushabh.golive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    Switch swSave;
    LinearLayout lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        SharedPrefManager.init(SettingActivity.this);
        bindID();
        swSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Save the data
                    SharedPrefManager.putInt("Data_save", 0);
                    SharedPrefManager.putInt("switchCheck",0);
                } else {
                    //unSave the data
                    SharedPrefManager.putInt("Data_save", 1);
                    SharedPrefManager.putInt("switchCheck",1);

                }
            }
        });
    }

    private void bindID() {
        swSave = findViewById(R.id.swSave);
        lvMain = findViewById(R.id.lvMain);
        setSwitchCheck();

    }

    private void setSwitchCheck() {
        //check weather switch is already check or not
        int isCheck = SharedPrefManager.getInt("switchCheck", 1);
        if (isCheck == 0)
        {
            swSave.setChecked(true);
            SharedPrefManager.putInt("Data_save", 0);
        }else
        {
            swSave.setChecked(false);
            SharedPrefManager.putInt("Data_save", 1);
        }

        setNightMode();
    }


    private void setNightMode() {
        //Change the theme according to sharedPref
        int isDavaSave = SharedPrefManager.getInt("night_mode", 1);
        if (isDavaSave == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            SharedPrefManager.putInt("night_mode", 0);
            lvMain.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            SharedPrefManager.putInt("night_mode", 1);
            lvMain.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }
}