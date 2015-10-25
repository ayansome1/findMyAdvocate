package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.this_is_ayan.findmyadvocate.Activities.LogInSignUp;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewLightFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewMediumFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.ProgressView;
import com.parse.GetCallback;
import com.parse.ParseUser;

/**
 * Created by collegedunia on 28/7/15.
 */
public class MyProfile extends Fragment
{
    MyTextViewLightFont logout;
    MyTextViewMediumFont nameMyTextViewMedium;
    ProgressView progressView;
    ParseUser currentUser;
    FrameLayout progressViewFrameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.my_profile,container,false);
        logout=(MyTextViewLightFont)v.findViewById(R.id.logout);
        progressView=(ProgressView)v.findViewById(R.id.progress_view);
        progressViewFrameLayout=(FrameLayout)v.findViewById(R.id.progress_view_frame_layout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currentUser = ParseUser.getCurrentUser();
                currentUser.logOut();
                Intent intent = new Intent(getActivity(), LogInSignUp.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        nameMyTextViewMedium=(MyTextViewMediumFont)v.findViewById(R.id.name);

        currentUser = ParseUser.getCurrentUser();
        currentUser.fetchInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseuser, com.parse.ParseException e)
            {
                if (e == null)
                {
                    progressView.stop();
                    progressViewFrameLayout.setVisibility(View.GONE);
                    nameMyTextViewMedium.setText(parseuser.getString("name"));
                  //  System.out.println("*********************name is " + parseuser.getString("name") + " email is " + parseuser.getUsername());

                }
                else
                {
                    // Failure!
                }
            }
        });




       return v;
    }





 /*   private void instantiateFrames(int number) {
        for(int i=0 ;i<number;i++)
        {
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v=layoutInflater.inflate(R.layout.c_homescreen_citycard,null,false);
            FrameLayout layout=(FrameLayout)v.findViewById(R.id.image);
            images_product_frame.add(layout);


        }
    }*/


}

