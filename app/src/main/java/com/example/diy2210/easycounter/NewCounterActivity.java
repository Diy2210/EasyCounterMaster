package com.example.diy2210.easycounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCounterActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, Toolbar.OnMenuItemClickListener {

    private SQLHelper sqlHelper;
    private Integer counter = 0;
    private Integer step = 1;
    private Integer i = 1;
    private TextView titleTV;
    private TextView descTV;
    private TextView valueTV;
    private TextView timeTV;
    private Button plusBtn;
    private Button minusBtn;
    private Button onlyPlusBtn;
    private Button onlyMinusBtn;
    private ConstraintLayout plusMinusConstraintLayout;
    private ConstraintLayout plusConstraintLayout;
    private ConstraintLayout minusConstraintLayout;
    private DateFormat dateFormat;
    private Vibrator vibrator;
    private MediaPlayer mp;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        counter = ECApp.valueInt;
        step = ECApp.step;

        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if (sharedPref.getBoolean("timeCheckBox_settings", false)) {
            Date date = new Date();
            timeTV = findViewById(R.id.timeTV);
            timeTV.setText(dateFormat.format(date));
        }

        titleTV = findViewById(R.id.titleTV);
        if (ECApp.title.isEmpty()) {
            titleTV.setVisibility(View.GONE);
        } else {
            titleTV.setText(ECApp.title);
        }

        descTV = findViewById(R.id.descTV);
        if (ECApp.description.isEmpty()) {
            descTV.setVisibility(View.GONE);
        } else {
            descTV.setText(ECApp.description);
        }

        valueTV = findViewById(R.id.valueTV);
        valueTV.setText(String.valueOf(ECApp.valueInt));

        plusMinusConstraintLayout = findViewById(R.id.plusMinusConstraintLayout);
        plusConstraintLayout = findViewById(R.id.plusConstraintLayout);
        minusConstraintLayout = findViewById(R.id.minusConstraintLayout);

        if (ECApp.counterType == 1) {
            plusMinusConstraintLayout.setVisibility(View.VISIBLE);
            plusConstraintLayout.setVisibility(View.GONE);
            minusConstraintLayout.setVisibility(View.GONE);
        } else if (ECApp.counterType == 2) {
            plusMinusConstraintLayout.setVisibility(View.GONE);
            plusConstraintLayout.setVisibility(View.VISIBLE);
            minusConstraintLayout.setVisibility(View.GONE);
        } else if (ECApp.counterType == 3) {
            plusMinusConstraintLayout.setVisibility(View.GONE);
            plusConstraintLayout.setVisibility(View.GONE);
            minusConstraintLayout.setVisibility(View.VISIBLE);
        }

        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        onlyPlusBtn = findViewById(R.id.onlyPlusBtn);
        onlyMinusBtn = findViewById(R.id.onlyMinusBtn);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        onlyPlusBtn.setOnClickListener(this);
        onlyMinusBtn.setOnClickListener(this);

        if (sharedPref.getBoolean("resetCheckBox_settings", false)) {
            minusBtn.setOnLongClickListener(this);
//            onlyMinusBtn.setOnLongClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plusBtn:
                counterPlus();
                break;
            case R.id.minusBtn:
                counterMinus();
                break;
            case R.id.onlyPlusBtn:
                counterPlus();
                break;
            case R.id.onlyMinusBtn:
                counterMinus();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.minusBtn:
                    AlertRecreate();
                    break;
//                case R.id.onlyMinusBtn:
//                    AlertRecreate();
//                    break;
            }
        return false;
    }

    private void counterPlus() {
        counter++;
//        i = counter + step;
//        ECApp.value = ECApp.valueInt + ECApp.step;
        valueTV.setText(String.valueOf(counter));
        getSharedPref();
    }

    private void counterMinus() {
        counter--;
        valueTV.setText(String.valueOf(counter));
        getSharedPref();
    }

    private void getSharedPref() {
        if (sharedPref.getBoolean("soundCheckBox_settings", false)) {
            mp = MediaPlayer.create(NewCounterActivity.this, R.raw.minus);
            mp.start();
        }
        if (sharedPref.getBoolean("vibrationCheckBox_settings", false)) {
            vibrator.vibrate(100);
        }
        if (sharedPref.getBoolean("timeCheckBox_settings", false)) {
            Date date = new Date();
            timeTV.setText(dateFormat.format(date));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (sharedPref.getBoolean("volumeButtonsCheckBox_settings", false)) {
            if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
                counterMinus();
            }
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (sharedPref.getBoolean("volumeButtonsCheckBox_settings", false)) {
            if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                counterPlus();
            }
        }
        return true;
    }

    private void AlertRecreate() {
        AlertRecreate();
        AlertDialog.Builder builder = new AlertDialog.Builder(NewCounterActivity.this);
        builder.setCancelable(false);
        builder.setMessage(R.string.message_reset_value);
        builder.setPositiveButton(R.string.ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        recreate();
                    }
                })
                .setNegativeButton(R.string.cancel_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                return true;
        }
        return true;
    }
}
