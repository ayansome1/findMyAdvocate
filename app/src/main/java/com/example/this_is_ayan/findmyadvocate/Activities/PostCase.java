package com.example.this_is_ayan.findmyadvocate.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.Switch;
import com.parse.ParseObject;

public class PostCase extends ActionBarActivity
{

    Toolbar toolbar;
    Switch switchProfileVisibility;
    com.example.this_is_ayan.findmyadvocate.Widgets.EditText titleEditText,descriptionEditText;
    String title,description;
    boolean titleFilledBoolean,descriptionFilledBoolean;
    ImageView saveImageView;
 //   Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_case);
        titleFilledBoolean=descriptionFilledBoolean=false;
        switchProfileVisibility=(Switch)findViewById(R.id.Switch);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        saveImageView=(ImageView)findViewById(R.id.save);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setTitle("");
        titleEditText=(com.example.this_is_ayan.findmyadvocate.Widgets.EditText)findViewById(R.id.title);
        descriptionEditText=(com.example.this_is_ayan.findmyadvocate.Widgets.EditText)findViewById(R.id.description);

        titleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                title=titleEditText.getText().toString();
                if(title.length() != 0)
                {
                    titleFilledBoolean=true;
                    if(descriptionFilledBoolean==true)
                         saveImageView.setVisibility(View.VISIBLE);
                }
                else
                {
                    titleFilledBoolean=false;
                    saveImageView.setVisibility(View.INVISIBLE);

                }
            }
        });


        descriptionEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                description=descriptionEditText.getText().toString();
                if(description.length() != 0)
                {
                    descriptionFilledBoolean=true;
                    if(titleFilledBoolean==true)
                      saveImageView.setVisibility(View.VISIBLE);
                }
                else
                {
                    descriptionFilledBoolean=false;
                    saveImageView.setVisibility(View.INVISIBLE);

                }
            }
        });



        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Parse.initialize(getApplicationContext(), "Fm1sNQpCEuAjXCGkyjWINRThX3WxlxNx5WT4gR2n", "pgLajSQjLSH0Ocwve9r9Gd3HO7mS8GNwzmWtFiFf");

                ParseObject cases = new ParseObject("cases");
                cases.put("caseTitle", title);
                cases.put("caseDescription", description);

              //  cases.setACL(new ParseACL(ParseUser.getCurrentUser()));
            //    cases.setUser(ParseUser.getCurrentUser());

                cases.saveInBackground();

            }
        });
        switchProfileVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });



    }


}
