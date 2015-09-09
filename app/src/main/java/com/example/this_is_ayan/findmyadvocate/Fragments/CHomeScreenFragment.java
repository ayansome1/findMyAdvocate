package com.example.this_is_ayan.findmyadvocate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.CPagerSlidingTabStrip;

/**
 * Created by collegedunia on 28/7/15.
 */
public class CHomeScreenFragment extends Fragment
{
    ViewPager pager;
    CPagerSlidingTabStrip strip;
    MyFragmentAdapter myFragmentAdapter;
    int screenHeight;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.c_homescreenfragment,container,false);
        strip=(CPagerSlidingTabStrip)view.findViewById(R.id.case_tabs);
        pager=(ViewPager)view.findViewById(R.id.case_viewpager);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);

        screenHeight = metrics.heightPixels;
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        strip.setTabBackground(R.drawable.c_background_tab);
        strip.setIndicatorHeight(0);
        strip.setActivateTextColor(getResources().getColor(R.color.c_profile_activated_text_color));
        strip.setIndicatorColor(getResources().getColor(R.color.c_profile_activated_text_color));
        strip.setShouldExpand(true);
        strip.setTextSize((int) getResources().getDimension(R.dimen.font_mini));
        strip.setActivateTextColor(getResources().getColor(R.color.c_profile_activated_text_color));
        strip.setDeactivateTextColor(getResources().getColor(R.color.c_profile_deactivated_textcolor));
        pager.setOffscreenPageLimit(1);
        myFragmentAdapter=new MyFragmentAdapter(getChildFragmentManager());
        pager.setAdapter(myFragmentAdapter);
        strip.setViewPager(pager);
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter
    {
        String[] pageTitles=getResources().getStringArray(R.array.case_types);

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            if(position==0)
            {
              //  return new CUserProfileAboutFragment();
                return new PostedCasesFragment();
            }
            return new PostCaseFragment();

           // return  new CollegeShortListedFragment();
        }

        @Override
        public int getCount() {
            return pageTitles.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }
    }
}

