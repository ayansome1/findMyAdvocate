package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_is_ayan.findmyadvocate.Activities.PostCase;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.FloatingActionButton;

/**
 * Created by collegedunia on 28/7/15.
 */
public class CHomeScreenFragment extends Fragment
{
    FloatingActionButton fab;
  //  ViewPager pager;
  //  CPagerSlidingTabStrip strip;
 //   MyFragmentAdapter myFragmentAdapter;
    int screenHeight;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.c_homescreenfragment,container,false);
       // strip=(CPagerSlidingTabStrip)view.findViewById(R.id.case_tabs);
      //  pager=(ViewPager)view.findViewById(R.id.case_viewpager);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(getActivity(),PostCase.class);
                startActivity(i);

            }
        });



       // DisplayMetrics metrics = new DisplayMetrics();
       // getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //screenHeight = metrics.heightPixels;
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);



    }


}

