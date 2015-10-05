package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.this_is_ayan.findmyadvocate.Activities.LogInSignUp;
import com.example.this_is_ayan.findmyadvocate.R;
import com.parse.ParseUser;

/**
 * Created by collegedunia on 28/7/15.
 */
public class MyProfile extends Fragment
{
   public static float heightOfFragmentLayout;
    float linearLayoutHeight;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.my_profile,container,false);

      // images_product_frame=new ArrayList<FrameLayout>();


    /*    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int windowHeight = displayMetrics.heightPixels;
        heightOfFragmentLayout = windowHeight - linearLayoutHeight;*/

        TextView logout=(TextView)v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               ParseUser currentUser = ParseUser.getCurrentUser();
             /*   System.out.println(currentUser);
                if(currentUser == null) {
                    Intent intent = new Intent(getActivity(), LogInSignUp.class);
                    startActivity(intent);
                    getActivity().finish();
                }*/

            //    System.out.println(" c u before logout is "+currentUser);

                currentUser.logOut();
               // currentUser = ParseUser.getCurrentUser();
             //  System.out.println("c u after logout is "+ currentUser);

                Intent intent = new Intent(getActivity(), LogInSignUp.class);
                startActivity(intent);
                getActivity().finish();














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

