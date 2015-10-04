package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.this_is_ayan.findmyadvocate.R;

public class SplashActivity  extends Activity
{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LogInSignUp.class);
                startActivity(i);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}

