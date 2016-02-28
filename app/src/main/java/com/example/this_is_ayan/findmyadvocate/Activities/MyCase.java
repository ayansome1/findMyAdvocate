package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.ConnectionDetector;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewLightFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.ProgressView;
import com.example.this_is_ayan.findmyadvocate.Widgets.Switch;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MyCase extends AppCompatActivity
{
    Toolbar toolbar;
    MyTextViewRegularFont titleMyTextViewRegular,ok,error;
    MyTextViewLightFont locationMyTextViewLight,caseCategoryMyTextViewLight,descriptionMyTextViewLight;
   // MyTextViewMediumFont ;
    Switch s;
    ProgressView progressView;
    FrameLayout progressViewFrameLayout;
    Dialog dialogError;
    Intent i;

    ScrollView scrollView;
    ConnectionDetector cd ;



    String caseId,title,description,location,category;
    boolean profileVisibility;
    Boolean isInternetPresent;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_case);
        cd = new ConnectionDetector(getApplicationContext());


        scrollView=(ScrollView)findViewById(R.id.scrollView);

        progressView=(ProgressView)findViewById(R.id.progress_view);
        progressViewFrameLayout=(FrameLayout)findViewById(R.id.progress_view_frame_layout);




        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            caseId = extras.getString("caseId");
        }

        titleMyTextViewRegular=(MyTextViewRegularFont)findViewById(R.id.title);
        descriptionMyTextViewLight=(MyTextViewLightFont)findViewById(R.id.description);
        locationMyTextViewLight=(MyTextViewLightFont)findViewById(R.id.location);
        caseCategoryMyTextViewLight=(MyTextViewLightFont)findViewById(R.id.case_category);
        s=(Switch)findViewById(R.id.Switch);
        s.setEnabled(false);
        //s.setClickable(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent==false)
        {
            progressView.stop();

            dialogError = new Dialog(MyCase.this);
            dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialogError.setContentView(R.layout.dialog_error);
            dialogError.getWindow().getAttributes();
            dialogError.setCancelable(false);
            error = (MyTextViewRegularFont) dialogError.findViewById(R.id.error);
            error.setText("Please enable your Internet Connection");

            dialogError.show();
            ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    dialogError.cancel();
                    i = new Intent(MyCase.this, HomeActivity.class);
                    startActivity(i);


                }
            });

        }
        else {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("cases");
            query.getInBackground(caseId, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {

                    if (e == null) {


                        title = object.getString("caseTitle");
                        titleMyTextViewRegular.setText(title);

                        description = object.getString("caseDescription");
                        descriptionMyTextViewLight.setText(description);

                        location = object.getString("caseLocation");
                        locationMyTextViewLight.setText(location);

                        category = object.getString("caseCategory");
                        caseCategoryMyTextViewLight.setText(category);

                        profileVisibility = object.getBoolean("profileVisibility");
                        s.setChecked(profileVisibility);

                        progressView.stop();
                        progressViewFrameLayout.setVisibility(View.GONE);


                    } else {







                    progressView.stop();
                    scrollView.setVisibility(View.GONE);

                    dialogError = new Dialog(MyCase.this);
                    dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialogError.setContentView(R.layout.dialog_error);
                    dialogError.getWindow().getAttributes();
                    dialogError.setCancelable(false);
                    dialogError.show();
                    ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            i = new Intent(MyCase.this, HomeActivity.class);
                            startActivity(i);
                        }
                    });
                    }
                }
            });
        }




    }


}
