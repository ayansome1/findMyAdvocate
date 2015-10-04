package com.example.this_is_ayan.findmyadvocate.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.this_is_ayan.findmyadvocate.Adapters.TestFragmentAdapter;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.CirclePageIndicator;
import com.example.this_is_ayan.findmyadvocate.Widgets.PageIndicator;


public class LogInSignUp extends FragmentActivity
{
    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

      /*  indicator.setBackgroundColor(0xFFCCCCCC);

        indicator.setRadius(10 * density);
        indicator.setPageColor(0xFF888888);
        indicator.setFillColor(0x880000FF);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(2 * density);*/


    }


}
