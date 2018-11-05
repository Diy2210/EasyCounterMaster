package com.example.diy2210.easycounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {

    CheckBox soundCheckBox;
    CheckBox vibrationCheckBox;
    CheckBox deleteCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundCheckBox = findViewById(R.id.soundCheckBox);
        if (ECApp.sound) {
            soundCheckBox.setChecked(true);
        }

        soundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.sound = isChecked;
            }
        });

        vibrationCheckBox = findViewById(R.id.vibrationCheckBox);
        if (ECApp.vibration) {
            vibrationCheckBox.setChecked(true);
        }

        vibrationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.vibration = isChecked;
            }
        });

        deleteCheckBox = findViewById(R.id.deleteCheckBox);
        if (ECApp.delete) {
            deleteCheckBox.setChecked(true);
        }

        deleteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.delete = isChecked;
            }
        });
    }
}
