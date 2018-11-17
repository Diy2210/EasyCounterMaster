package com.example.diy2210.easycounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private CheckBox soundCheckBox;
    private CheckBox vibrationCheckBox;
    private CheckBox resetCheckBox;
    private CheckBox timeCheckBox;
    private CheckBox screenCheckBox;
    private CheckBox volumeButtonsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        soundCheckBox = findViewById(R.id.soundCheckBox);
        soundCheckBox.setChecked(sharedPref.getBoolean("soundCheckBox_settings", false));
        soundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("soundCheckBox_settings", soundCheckBox.isChecked()).apply();
                ECApp.sound = isChecked;
            }
        });

        vibrationCheckBox = findViewById(R.id.vibrationCheckBox);
        vibrationCheckBox.setChecked(sharedPref.getBoolean("vibrationCheckBox_settings", false));
        vibrationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("vibrationCheckBox_settings", vibrationCheckBox.isChecked()).apply();
                ECApp.vibration = isChecked;
            }
        });

        resetCheckBox = findViewById(R.id.resetCheckBox);
        resetCheckBox.setChecked(sharedPref.getBoolean("resetCheckBox_settings", false));
        resetCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("resetCheckBox_settings", vibrationCheckBox.isChecked()).apply();
                ECApp.reset = isChecked;
            }
        });

        timeCheckBox = findViewById(R.id.timeCheckBox);
        timeCheckBox.setChecked(sharedPref.getBoolean("timeCheckBox_settings", false));
        timeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("timeCheckBox_settings", timeCheckBox.isChecked()).apply();
                ECApp.time = isChecked;
            }
        });

        screenCheckBox = findViewById(R.id.screenCheckBox);
        screenCheckBox.setChecked(sharedPref.getBoolean("screenCheckBox_settings", false));
        screenCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("screenCheckBox_settings", screenCheckBox.isChecked()).apply();
                ECApp.screenOn = isChecked;
            }
        });

        volumeButtonsCheckBox = findViewById(R.id.volumeButtonsCheckBox);
        volumeButtonsCheckBox.setChecked(sharedPref.getBoolean("volumeButtonsCheckBox_settings", false));
        volumeButtonsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("volumeButtonsCheckBox_settings", volumeButtonsCheckBox.isChecked()).apply();
                ECApp.hardwareButtons = isChecked;
            }
        });
    }
}
