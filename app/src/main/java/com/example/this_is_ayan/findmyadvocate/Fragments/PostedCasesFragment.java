package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_is_ayan.findmyadvocate.R;

/**
 * Created by this_is_ayan on 09-09-2015.
 */
public class PostedCasesFragment  extends Fragment
{



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.posted_cases,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /* CUserProfileAboutAdapter aboutAdapter=new CUserProfileAboutAdapter(getActivity(),R.layout.about_college_qualification,object);
        if(aboutAdapter!=null)
        {
            listViewAbout.setAdapter(aboutAdapter);
        }*/
    }
}
