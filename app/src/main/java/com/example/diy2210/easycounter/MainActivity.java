package com.example.diy2210.easycounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView valueTV;
    private Button plusBtn;
    private Button minusBtn;
    private int counter = 0;
    private EditText titleET;
    private EditText descET;
    private EditText counterValueET;
    private EditText stepET;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton plusMinusRadioBtn;
    private RadioButton plusRadioBtn;
    private RadioButton minusRadioBtn;
    private int counterType = 1;
    private Vibrator vibrator;
    private MediaPlayer mp;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        valueTV = findViewById(R.id.valueTV);
        valueTV.setText(String.valueOf(counter));

        plusBtn = findViewById(R.id.plusRadioBtn);
        minusBtn = findViewById(R.id.minusRadioBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterPlus();
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterMinus();
            }
        });

        if (ECApp.delete) {
            minusBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    private void counterPlus() {
        counter++;
        valueTV.setText(String.valueOf(counter));
        if (ECApp.vibration) {
            vibrator.vibrate(100);
        }
        if (ECApp.sound) {
            mp = MediaPlayer.create(MainActivity.this, R.raw.plus);
            mp.start();
        }
    }

    private void counterMinus() {
        counter--;
        valueTV.setText(String.valueOf(counter));
        if (ECApp.vibration) {
            vibrator.vibrate(100);
        }
        if (ECApp.sound) {
            mp = MediaPlayer.create(MainActivity.this, R.raw.minus);
            mp.start();
        }
    }

    @Override
        public void onBackPressed () {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == R.id.nav_create_counter) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.new_counter_item, null);

                builder.setCancelable(true);
                builder.setView(dialogView);

                button = dialogView.findViewById(R.id.button);
                titleET = dialogView.findViewById(R.id.titleET);
                descET = dialogView.findViewById(R.id.descET);
                counterValueET = dialogView.findViewById(R.id.counterValueET);
                stepET = dialogView.findViewById(R.id.stepET);
                radioGroup = dialogView.findViewById(R.id.radioGfroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.plusMinusRadioBtn:
                                counterType = 1;
                                break;
                            case R.id.plusRadioBtn:
                                counterType = 2;
                                break;
                            case R.id.minusRadioBtn:
                                counterType = 3;
                                break;
                        }
                    }
                });

                dialog = builder.create();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ECApp.title = titleET.getText().toString();
                        ECApp.description = descET.getText().toString();
                        ECApp.valueInt = Integer.parseInt(counterValueET.getText().toString());
                        ECApp.step = stepET.getText().toString();
                        ECApp.counterType = counterType;

                        // Test empty fields
                        String s = titleET.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            counterValueET.setError("Empty title");
                            counterValueET.requestFocus();
                        }

                        startActivity(new Intent(MainActivity.this, NewCounterActivity.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        dialog.cancel();
                    }
                });
                dialog.show();

            } else if (id == R.id.nav_my_counter) {
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
}

