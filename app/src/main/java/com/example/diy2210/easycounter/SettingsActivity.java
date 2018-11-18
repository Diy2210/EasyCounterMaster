package com.example.diy2210.easycounter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private SharedPreferences sharedPref;
    private CheckBox soundCheckBox;
    private CheckBox vibrationCheckBox;
    private CheckBox resetCheckBox;
    private CheckBox timeCheckBox;
    private CheckBox screenCheckBox;
    private CheckBox volumeButtonsCheckBox;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        soundCheckBox = findViewById(R.id.soundCheckBox);
        vibrationCheckBox = findViewById(R.id.vibrationCheckBox);
        resetCheckBox = findViewById(R.id.resetCheckBox);
        timeCheckBox = findViewById(R.id.timeCheckBox);
        screenCheckBox = findViewById(R.id.screenCheckBox);
        volumeButtonsCheckBox = findViewById(R.id.volumeButtonsCheckBox);

        soundCheckBox.setOnCheckedChangeListener(this);
        vibrationCheckBox.setOnCheckedChangeListener(this);
        resetCheckBox.setOnCheckedChangeListener(this);
        timeCheckBox.setOnCheckedChangeListener(this);
        screenCheckBox.setOnCheckedChangeListener(this);
        volumeButtonsCheckBox.setOnCheckedChangeListener(this);

        soundCheckBox.setChecked(sharedPref.getBoolean("soundCheckBox_settings", false));
        vibrationCheckBox.setChecked(sharedPref.getBoolean("vibrationCheckBox_settings", false));
        resetCheckBox.setChecked(sharedPref.getBoolean("resetCheckBox_settings", false));
        timeCheckBox.setChecked(sharedPref.getBoolean("timeCheckBox_settings", false));
        screenCheckBox.setChecked(sharedPref.getBoolean("screenCheckBox_settings", false));
        volumeButtonsCheckBox.setChecked(sharedPref.getBoolean("volumeButtonsCheckBox_settings", false));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.soundCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("soundCheckBox_settings", soundCheckBox.isChecked()).apply();
                ECApp.sound = isChecked;
                break;
            case R.id.vibrationCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("vibrationCheckBox_settings", vibrationCheckBox.isChecked()).apply();
                ECApp.vibration = isChecked;
                break;
            case R.id.resetCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("resetCheckBox_settings", vibrationCheckBox.isChecked()).apply();
                ECApp.reset = isChecked;
                break;
            case R.id.timeCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("timeCheckBox_settings", timeCheckBox.isChecked()).apply();
                ECApp.time = isChecked;
                break;
            case R.id.screenCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("screenCheckBox_settings", screenCheckBox.isChecked()).apply();
                ECApp.screenOn = isChecked;
                break;
            case R.id.volumeButtonsCheckBox:
                editor = sharedPref.edit();
                editor.putBoolean("volumeButtonsCheckBox_settings", volumeButtonsCheckBox.isChecked()).apply();
                ECApp.hardwareButtons = isChecked;
                break;
        }
    }
}
