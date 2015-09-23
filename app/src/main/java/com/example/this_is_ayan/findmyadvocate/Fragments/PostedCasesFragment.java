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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View  view=inflater.inflate(R.layout.posted_cases,container,false);

     //   Parse.enableLocalDatastore(getActivity());
     // Parse.initialize(getActivity(), "Fm1sNQpCEuAjXCGkyjWINRThX3WxlxNx5WT4gR2n", "pgLajSQjLSH0Ocwve9r9Gd3HO7mS8GNwzmWtFiFf");

    /*    ParseObject gs = new ParseObject("Gs");
        gs.put("score", 19);
        gs.put("playerName", "19 p");
        gs.put("cheatMode", true);
        gs.saveInBackground();
        gs = new ParseObject("Gs");
        gs.put("score", 18);
        gs.put("playerName", "18 p");
        gs.put("cheatMode", true);
        gs.saveInBackground();*/

        //   gs.increment("score", 1100);
      //  gameScore.deleteInBackground();

     /*   ParseQuery<ParseObject> query = ParseQuery.getQuery("Gs");
        query.whereEqualTo("playerName", "19 p");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });*/



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
