package com.example.this_is_ayan.findmyadvocate;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by this_is_ayan on 24-09-2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "Fm1sNQpCEuAjXCGkyjWINRThX3WxlxNx5WT4gR2n", "pgLajSQjLSH0Ocwve9r9Gd3HO7mS8GNwzmWtFiFf");
       // ParseUser.enableRevocableSessionInBackground();
       // ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}