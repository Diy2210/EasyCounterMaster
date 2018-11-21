package com.example.diy2210.easycounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    protected long Delay = 2000;
    protected ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        imageView = findViewById(R.id.splashImageView);
//        imageView.startAnimation(anim);
        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                // Start Activity.class
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}
