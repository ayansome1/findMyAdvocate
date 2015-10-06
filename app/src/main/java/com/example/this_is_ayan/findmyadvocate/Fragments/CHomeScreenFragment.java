package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_is_ayan.findmyadvocate.Activities.PostCase;
import com.example.this_is_ayan.findmyadvocate.Adapters.CardAdapter;
import com.example.this_is_ayan.findmyadvocate.Objects.caseObject;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CHomeScreenFragment extends Fragment
{
    FloatingActionButton fab;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    static RecyclerView.Adapter mAdapter;
   public boolean datachanged;


    SwipeRefreshLayout mSwipeRefreshLayout;










    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {





            View view=inflater.inflate(R.layout.c_homescreenfragment,container,false);

      /*  ParseUser currentUser = ParseUser.getCurrentUser();
        System.out.println("in homescreenfragment user is "+currentUser);
        currentUser.logOut();
        System.out.println("in homescreenfragment user is " + currentUser);

        if(currentUser == null) {
            Intent intent = new Intent(getActivity(), LogInSignUp.class);
            startActivity(intent);
            getActivity().finish();
        }*/





        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardAdapter(getActivity(),getData());
        mRecyclerView.setAdapter(mAdapter);



        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter = new CardAdapter(getActivity(), getData());
                mRecyclerView.setAdapter(mAdapter);
            }
        });









        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostCase.class);
                startActivity(i);

            }
        });
        return  view;
    }

    public  List<caseObject> getData()
    {
        final List<caseObject> mItems = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("cases");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> caseList, ParseException e)
            {

                if (e == null)
                {
                    int len = caseList.size();
                    for (int i = len-1; i >=0; i--)
                    {
                        ParseObject p = caseList.get(i);
                        String caseTitle = p.getString("caseTitle");
                        String caseDescription = p.getString("caseDescription");

                        caseObject cases = new caseObject();
                        cases.setCaseTitle(caseTitle);
                        cases.setCaseDescription(caseDescription);
                        mItems.add(cases);
                    }

                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            }
        });

        return mItems;
    }
}
