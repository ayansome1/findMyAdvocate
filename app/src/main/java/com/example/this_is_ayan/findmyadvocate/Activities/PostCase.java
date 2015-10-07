package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.this_is_ayan.findmyadvocate.Objects.cases;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.ProgressView;
import com.example.this_is_ayan.findmyadvocate.Widgets.Switch;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostCase extends ActionBarActivity
{

    Toolbar toolbar;
    Switch switchProfileVisibility;
    com.example.this_is_ayan.findmyadvocate.Widgets.EditText titleEditText,descriptionEditText;
    String title,description;
    boolean titleFilledBoolean,descriptionFilledBoolean;
    ImageView saveImageView;

    MyTextViewRegularFont ok;
    Dialog dialog;
    Context mContext;
    ProgressView progressView;
    KeyListener titleKeyListener,descriptionKeyListener;
    InputMethodManager imm;

    //   Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_case);


        progressView =(ProgressView)findViewById(R.id.progress_view);


        mContext=this;

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
                saveImageView.setVisibility(View.INVISIBLE);
                progressView.start();

                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            /*    titleKeyListener = titleEditText.getKeyListener();
                descriptionKeyListener=descriptionEditText.getKeyListener();*/
                titleEditText.setKeyListener(null);
                descriptionEditText.setKeyListener(null);




              /*  ParseObject cases = new ParseObject("cases");
                cases.put("caseTitle", title);
                cases.put("caseDescription", description);*/
                ParseACL acl = new ParseACL(ParseUser.getCurrentUser());
                acl.setPublicReadAccess(true);

                cases cases = new cases();
                cases.setACL(acl);

                cases.setUser(ParseUser.getCurrentUser());
                cases.put("caseTitle", title);
                cases.put("caseDescription", description);
                //    cases.saveInBackground();


                cases.saveInBackground(new SaveCallback() {

                    public void done(ParseException e) {
                        if (e == null) {
                            progressView.stop();
                            //   saveImageView.setVisibility(View.VISIBLE);
                            // titleEditText.setKeyListener(titleKeyListener);
                            //descriptionEditText.setKeyListener(descriptionKeyListener);

                            dialog = new Dialog(mContext);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setContentView(R.layout.dialog_data_posted);
                            dialog.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.setCancelable(false);

                            ok = (MyTextViewRegularFont) dialog.findViewById(R.id.ok);
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v)
                                {
                                    Intent intent=new Intent();
                                    setResult(2, intent);  // 2 means  refresh needed of the recycler view
                                    finish();

                                }
                            });


                            // saveImageView.setVisibility(View.VISIBLE);


                        } else {
                            //myObjectSaveDidNotSucceed();
                        }
                    }
                });


            }
        });
        switchProfileVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });



    }
    @Override
    public void onBackPressed()
    {

        Intent intent=new Intent();
        setResult(1,intent);  // 1 means no refresh needed of the recycler view
        finish();
    }


}
