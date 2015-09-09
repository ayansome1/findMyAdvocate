package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.this_is_ayan.findmyadvocate.R;

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


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int windowHeight = displayMetrics.heightPixels;
        heightOfFragmentLayout = windowHeight - linearLayoutHeight;


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

