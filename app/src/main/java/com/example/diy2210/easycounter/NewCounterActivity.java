package com.example.diy2210.easycounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewCounterActivity extends AppCompatActivity {

    private TextView titleTV;
    private TextView descTV;
    private TextView valueTV;
    private Button plusBtn;
    private Button minusBtn;
    private Vibrator vibrator;
    private MediaPlayer mp;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        counter = ECApp.valueInt;

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

        plusBtn = findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                valueTV.setText(String.valueOf(counter));
                if (ECApp.vibration) {
                    vibrator.vibrate(100);
                }
                if (ECApp.sound) {
                    mp = MediaPlayer.create(NewCounterActivity.this, R.raw.plus);
                    mp.start();
                }
            }
        });

        minusBtn = findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                valueTV.setText(String.valueOf(counter));
                if (ECApp.vibration) {
                    vibrator.vibrate(100);
                }
                if (ECApp.sound) {
                    mp = MediaPlayer.create(NewCounterActivity.this, R.raw.plus);
                    mp.start();
                }
            }
        });

        if (ECApp.delete) {
            minusBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
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
                    return false;
                }
            });
        }
    }
}
