package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_is_ayan.findmyadvocate.Activities.PostCase;
import com.example.this_is_ayan.findmyadvocate.Adapters.CardAdapter;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.FloatingActionButton;

/**
 * Created by collegedunia on 28/7/15.
 */
public class CHomeScreenFragment extends Fragment
{
    FloatingActionButton fab;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.c_homescreenfragment,container,false);


        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(getActivity(),PostCase.class);
                startActivity(i);

            }
        });




        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);



    }


}

