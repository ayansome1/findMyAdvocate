package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.this_is_ayan.findmyadvocate.Adapters.TestFragmentAdapter;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.CirclePageIndicator;
import com.example.this_is_ayan.findmyadvocate.Widgets.PageIndicator;


public class LogInSignUp extends FragmentActivity
{
    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    TextView signupView;
    TextView loginView;
    Context mContext;
    Dialog dialog;

    EditText editTextemailLogin,editTextpasswordLogin, editTextnameSignUp, editTextemailSignUp,editTextPasswordSignUp;

    String nameSignup,emailSignup,passwordSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);
        findviewbyid();
        mContext=this;

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
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
                    public void onClick(View arg0)
                    {
                        nameSignup = editTextnameSignUp.getText().toString();
                        emailSignup = editTextemailSignUp.getText().toString();
                        passwordSignup = editTextPasswordSignUp.getText().toString();

                    }


                });
            }

        });


    }

    private void findviewbyid()
    {
        signupView=(TextView)findViewById(R.id.signup);
        loginView=(TextView)findViewById(R.id.login);


    }







}
