package com.example.diy2210.easycounter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "settings";
    public static final Boolean APP_PREFERENCES_COUNTER = false;
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

        resetCheckBox = findViewById(R.id.resetCheckBox);
        if (ECApp.reset) {
            resetCheckBox.setChecked(true);
        }

        resetCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.reset = isChecked;
            }
        });

        timeCheckBox = findViewById(R.id.timeCheckBox);
        if (ECApp.time) {
            timeCheckBox.setChecked(true);
        }

        timeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.time = isChecked;
            }
        });

        screenCheckBox = findViewById(R.id.screenCheckBox);
        if (ECApp.screenOn) {
            screenCheckBox.setChecked(true);
        }

        screenCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.screenOn = isChecked;
            }
        });

        volumeButtonsCheckBox = findViewById(R.id.volumeButtonsCheckBox);
        if (ECApp.hardwareButtons) {
            volumeButtonsCheckBox.setChecked(true);
        }

        volumeButtonsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ECApp.hardwareButtons = isChecked;
            }
        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), soundCheckBox.isChecked());
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), vibrationCheckBox.isChecked());
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), resetCheckBox.isChecked());
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), timeCheckBox.isChecked());
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), screenCheckBox.isChecked());
//        editor.putBoolean(String.valueOf(APP_PREFERENCES_COUNTER), volumeButtonsCheckBox.isChecked());
//        editor.apply();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (sharedPref.contains(String.valueOf(APP_PREFERENCES_COUNTER))) {
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), soundCheckBox.isChecked());
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), vibrationCheckBox.isChecked());
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), resetCheckBox.isChecked());
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), timeCheckBox.isChecked());
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), screenCheckBox.isChecked());
//            sharedPref.getBoolean(String.valueOf(APP_PREFERENCES_COUNTER), volumeButtonsCheckBox.isChecked());
//        }
//    }
}
