package com.example.diy2210.easycounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SQLHelper sqlHelper;
    private int counter = 0;
    private int counterType = 1;
    private int multipleCounter = 1;
    private TextView valueTV;
    private TextView timeTV;
    private Button plusBtn;
    private Button minusBtn;
    private Button button;
    private Button saveBtn;
    private EditText titleET;
    private EditText descET;
    private EditText counterValueET;
    private EditText stepET;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupMultiple;
    private RadioButton plusMinusRadioBtn;
    private RadioButton plusRadioBtn;
    private RadioButton minusRadioBtn;
    private RadioButton twoRadioBtn;
    private RadioButton fourRadioBtn;
    private AlertDialog dialog;
    private DateFormat dateFormat;
    private Vibrator vibrator;
    private MediaPlayer mp;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getBoolean("screenCheckBox_settings", false)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

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
        timeTV = findViewById(R.id.timeTV);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if (sharedPref.getBoolean("timeCheckBox_settings", false)) {
            Date date = new Date();
            timeTV.setText(dateFormat.format(date));
        }

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

        if (sharedPref.getBoolean("resetCheckBox_settings", false)) {
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

    private void counterPlus() {
        counter++;
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
            mp = MediaPlayer.create(MainActivity.this, R.raw.minus);
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
    public void onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed();
        }
    }

    private boolean closeDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            return true;
        }

        if (id == R.id.action_save) {
//            final String time = "time"+"\n";
            final Date date = new Date();
            final String time = dateFormat.format(date)+"\n";
            final String title = "title";
            final String description = "description";
            final String value = valueTV.getText().toString();
            sqlHelper = new SQLHelper(MainActivity.this);
            sqlHelper.insertCounterDetails(time, title, description, value);
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            radioGroup = dialogView.findViewById(R.id.radioGroup);
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
                    ECApp.step = Integer.parseInt(stepET.getText().toString());
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

//        } else if (id == R.id.nav_create_multipul_counter) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            LayoutInflater inflater = getLayoutInflater();
//            View dialogView = inflater.inflate(R.layout.multipul_counter_item, null);
//
//            builder.setCancelable(true);
//            builder.setView(dialogView);
//
//            radioGroupMultiple = dialogView.findViewById(R.id.radioGroupMultiple);
//            radioGroupMultiple.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                    switch (checkedId) {
//                        case R.id.twoRadioButton:
//                            multipleCounter = 1;
//                            break;
//                        case R.id.fourRadioButton:
//                            multipleCounter = 2;
//                            break;
//                    }
//                }
//            });
//
//            button = dialogView.findViewById(R.id.okBtn);
//            dialog = builder.create();
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ECApp.multipleCounter = multipleCounter;
//                    startActivity(new Intent(MainActivity.this, NewCounterActivity.class));
//                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                    dialog.cancel();
//                }
//            });
//            dialog.show();
//
        } else if (id == R.id.nav_my_counter) {
            startActivity(new Intent(MainActivity.this, MyCountersListActivity.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

