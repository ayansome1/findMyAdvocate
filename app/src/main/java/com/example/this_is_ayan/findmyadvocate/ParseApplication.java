package com.example.this_is_ayan.findmyadvocate;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by this_is_ayan on 24-09-2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "Fm1sNQpCEuAjXCGkyjWINRThX3WxlxNx5WT4gR2n", "pgLajSQjLSH0Ocwve9r9Gd3HO7mS8GNwzmWtFiFf");
    }
}