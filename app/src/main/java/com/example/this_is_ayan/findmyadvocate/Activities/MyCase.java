package com.example.this_is_ayan.findmyadvocate.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewLightFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewMediumFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.Switch;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MyCase extends AppCompatActivity
{
    Toolbar toolbar;
    MyTextViewRegularFont descriptionMyTextViewRegular;
    MyTextViewLightFont locationMyTextViewLight,caseCategoryMyTextViewLight;
    MyTextViewMediumFont titleMyTextViewMedium;
    Switch Switch;

    String caseId,title,description,location,category;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_case);



        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            caseId = extras.getString("caseId");
        }

        titleMyTextViewMedium=(MyTextViewMediumFont)findViewById(R.id.title);
        descriptionMyTextViewRegular=(MyTextViewRegularFont)findViewById(R.id.description);
        locationMyTextViewLight=(MyTextViewLightFont)findViewById(R.id.location);
        caseCategoryMyTextViewLight=(MyTextViewLightFont)findViewById(R.id.case_category);
        Switch=(Switch)findViewById(R.id.Switch);


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




        ParseQuery<ParseObject> query = ParseQuery.getQuery("cases");
        query.getInBackground(caseId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null)
                {

                    title = object.getString("caseTitle");
                    titleMyTextViewMedium.setText(title);


                    // object will be your game score
                }
                else
                {
                    // something went wrong
                }
            }
        });




    }


}
