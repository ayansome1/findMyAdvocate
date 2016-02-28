package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.this_is_ayan.findmyadvocate.Adapters.TestFragmentAdapter;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.CirclePageIndicator;
import com.example.this_is_ayan.findmyadvocate.Widgets.PageIndicator;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInSignUp extends FragmentActivity {
    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    TextView signupView;
    TextView loginView;
    Context mContext;
    Dialog dialog, dialogLoading;
    EditText editTextemailSignin, editTextpasswordSignin, editTextnameSignUp, editTextemailSignUp, editTextPasswordSignUp;
    String nameSignup, emailSignup, passwordSignup, passwordSignin, emailSignin;
    boolean validation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);
        findviewbyid();
        mContext = this;
        validation = false;
        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);

        signupView.setOnClickListener(new View.OnClickListener()   //nw in this line
        {

            @Override
            public void onClick(View v) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.dialog_signup);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();

                editTextnameSignUp = (EditText) dialog.findViewById(R.id.name_signup);
                editTextemailSignUp = (EditText) dialog.findViewById(R.id.email_signup);
                editTextPasswordSignUp = (EditText) dialog.findViewById(R.id.password_signup);

                dialog.findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View arg0) {
                        nameSignup = editTextnameSignUp.getText().toString();
                        emailSignup = editTextemailSignUp.getText().toString();
                        passwordSignup = editTextPasswordSignUp.getText().toString();
                        validation = false;
                        validation = validateSignupEntries();
                        if (validation == true) {
                            dialogLoading = new Dialog(mContext);
                            dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialogLoading.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialogLoading.setContentView(R.layout.dialog_loading);
                            dialogLoading.getWindow().getAttributes();
                            dialogLoading.show();
                            dialogLoading.setCancelable(false);

                            ParseUser user = new ParseUser();
                            user.put("name", nameSignup);
                            user.setUsername(emailSignup);
                            user.setPassword(passwordSignup);

                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Intent intent = new Intent(LogInSignUp.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        dialogLoading.cancel();
                                        switch (e.getCode()) {
                                            case ParseException.USERNAME_TAKEN:
                                                Toast.makeText(getApplicationContext(), "Sorry, this email has already been taken.", Toast.LENGTH_SHORT).show();
                                                break;

                                            default:
                                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                        }
                    }

                });
            }

        });

        loginView.setOnClickListener(new View.OnClickListener()   //nw in this line
        {

            @Override
            public void onClick(View v) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.dialog_signin);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();
                editTextemailSignin = (EditText) dialog.findViewById(R.id.email_signin);
                editTextpasswordSignin = (EditText) dialog.findViewById(R.id.password_signin);

                dialog.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View arg0) {
                        emailSignin = editTextemailSignin.getText().toString();
                        passwordSignin = editTextpasswordSignin.getText().toString();
                        // validation = false;
                        validation = validateSigninEntries();

                        if (validation == true) {
                            dialogLoading = new Dialog(mContext);
                            dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialogLoading.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialogLoading.setContentView(R.layout.dialog_loading);
                            dialogLoading.getWindow().getAttributes();
                            dialogLoading.show();
                            dialogLoading.setCancelable(false);
                            //  arg0.setEnabled(false);

                            ParseUser.logInInBackground(emailSignin, passwordSignin, new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {
                                        //   dialogLoading.cancel();
                                        Intent intent = new Intent(LogInSignUp.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        dialogLoading.cancel();
                                        switch (e.getCode()) {
                                            default:
                                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }


        });
    }

    private void findviewbyid() {
        signupView = (TextView) findViewById(R.id.signup);
        loginView = (TextView) findViewById(R.id.login);
    }

    public boolean validateSignupEntries() {
        if (!isValidName(nameSignup)) {
            Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidEmail(emailSignup)) {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!isValidPassword(passwordSignup)) {
            Toast.makeText(getApplicationContext(), "Password length must be minimum 6 characters", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    public boolean validateSigninEntries() {
        if ((emailSignin.equals("a") && passwordSignin.equals("a")) || (emailSignin.equals("b") && passwordSignin.equals("b"))) ///remove this line after testing
        {
            return true;
        }

        if (!isValidEmail(emailSignin)) {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!isValidPassword(passwordSignin)) {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    //validating name
    private boolean isValidName(String name) {
        if (name.length() > 2) {
            return true;
        }
        return false;
    }


}
