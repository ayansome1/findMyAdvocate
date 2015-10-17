package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.app.Dialog;
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
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.this_is_ayan.findmyadvocate.Activities.PostCase;
import com.example.this_is_ayan.findmyadvocate.Adapters.CardAdapter;
import com.example.this_is_ayan.findmyadvocate.Objects.caseObject;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.ConnectionDetector;
import com.example.this_is_ayan.findmyadvocate.Widgets.FloatingActionButton;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.ProgressView;
import com.example.this_is_ayan.findmyadvocate.Widgets.RecyclerItemClickListener;
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
     RecyclerView.Adapter mAdapter;
   public boolean datachanged;
    ProgressView progressView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyTextViewRegularFont startPosting;
    Dialog dialogError;
    MyTextViewRegularFont ok,error;

    InputMethodManager imm;

    ConnectionDetector cd ;

    Boolean isInternetPresent;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view=inflater.inflate(R.layout.c_homescreenfragment,container,false);

        cd = new ConnectionDetector(getActivity());

        progressView=(ProgressView)view.findViewById(R.id.progress_view);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardAdapter(getActivity(),getData());

        startPosting=(MyTextViewRegularFont)view.findViewById(R.id.start_posting);



        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position)
                    {
                       // mRecyclerView.getAdapter().toString();
                     //   mAdapter.getItemId(position);
                      //  mRecyclerView.getChildPosition(view);
                        Toast.makeText(getActivity(), "pos is "+position, Toast.LENGTH_SHORT).show();
                        // do whatever
                    }
                })
        );






     //   mRecyclerView.setAdapter(mAdapter);


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                mAdapter = new CardAdapter(getActivity(), getData());
             //   mRecyclerView.setAdapter(mAdapter);
            }

        });


        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), PostCase.class);
                startActivityForResult(i, 3);// Activity is started with random requestCode 3

            }
        });
        return  view;
    }

    public  List<caseObject> getData()
    {

        //progressView.start();





        final List<caseObject> mItems = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("cases");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> caseList, ParseException e) {

                if (e == null) {
                    int len = caseList.size();
                    for (int i = len - 1; i >= 0; i--) {
                        ParseObject p = caseList.get(i);
                        String caseTitle = p.getString("caseTitle");
                        String caseDescription = p.getString("caseDescription");

                        caseObject cases = new caseObject();
                        cases.setCaseTitle(caseTitle);
                        cases.setCaseDescription(caseDescription);
                        mItems.add(cases);
                    }

                    //  mAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mAdapter);
                    progressView.stop();

                    if(len==0)
                    {
                      //  System.out.println("len is "+len);
                        startPosting.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                       // System.out.println("len is "+len);

                        startPosting.setVisibility(View.INVISIBLE);

                    }



                      mSwipeRefreshLayout.setRefreshing(false);

                }
                else
                {
                    mSwipeRefreshLayout.setRefreshing(false);


                    progressView.stop();

                    isInternetPresent = cd.isConnectingToInternet();
                    if(isInternetPresent==false)
                    {

                        dialogError = new Dialog(getActivity());
                        dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialogError.setContentView(R.layout.dialog_error);
                        dialogError.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                        error = (MyTextViewRegularFont) dialogError.findViewById(R.id.error);
                        error.setText("Please enable your Internet Connection");

                        dialogError.show();
                        ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                dialogError.cancel();


                            }
                        });
                        // if(isInternetPresent==false)
                        //  return null;
                    }
                   // startPosting.setVisibility(View.VISIBLE);


                }

            }
        });

        return mItems;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2)// 2 is sent by PostCase class and it means  refreshing to be done of the recycler view
        {
            progressView.start();
            mAdapter = new CardAdapter(getActivity(),getData());
            mRecyclerView.setAdapter(mAdapter);


        }


    }



}
