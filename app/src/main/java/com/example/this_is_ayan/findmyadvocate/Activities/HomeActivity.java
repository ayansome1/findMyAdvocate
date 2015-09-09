package com.example.this_is_ayan.findmyadvocate.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.this_is_ayan.findmyadvocate.Fragments.CHomeScreenFragment;
import com.example.this_is_ayan.findmyadvocate.Fragments.MyProfile;
import com.example.this_is_ayan.findmyadvocate.Fragments.Notifications;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.CPagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by this_is_ayan on 29-08-2015.
 */

public class HomeActivity extends AppCompatActivity
{
    ViewPager viewPager;
    CPagerSlidingTabStrip strip;
    //ArrayList<Integer> colors;
    MainScreenFragment mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        viewPager=(ViewPager)findViewById(R.id.c_viewpager);
        strip=(CPagerSlidingTabStrip)findViewById(R.id.c_tabs);

        mAdapter=new MainScreenFragment(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        strip.setTabBackground(R.drawable.c_background_tab);
        strip.setIndicatorHeight(getResources().getInteger(R.integer.c_tab_indicator_height));
        strip.setTabPaddingLeftRight(getResources().getInteger(R.integer.img_tab_padding));
        strip.setActivateTextColor(getResources().getColor(R.color.c_grey_color));
        strip.setIndicatorColor(getResources().getColor(R.color.c_actionbar_backhground));
        strip.setShouldExpand(true);
        strip.setDeactivateTextColor(getResources().getColor(R.color.c_grey_color));
        strip.setViewPager(viewPager);
    }
    int NTABS = 5;
    ArrayList<Fragment> fragments;

    public class MainScreenFragment extends FragmentPagerAdapter implements CPagerSlidingTabStrip.IconTabProvider
    {
        ArrayList<Integer> pageReseourceIds;

        public MainScreenFragment(FragmentManager fm) {
            super(fm);
            pageReseourceIds = new ArrayList<Integer>();
            pageReseourceIds.add(R.drawable.c_home_icon_selector);

            pageReseourceIds.add(R.drawable.c_notification_selector);
            pageReseourceIds.add(R.drawable.c_user_profile_selector);
            fragments = new ArrayList<Fragment>();
        }
        @Override
        public Fragment getItem(int position)
        {
            if(position==0)
            {
                return  new CHomeScreenFragment();
            }
            if(position==1)
            {
                return  new Notifications();
            }
            return  new MyProfile();
        }
        @Override
        public int getCount() {
            return pageReseourceIds.size();
        }
        public void changeHomeIcon(int drawable) {

            pageReseourceIds.set(0, drawable);

            notifyDataSetChanged();

        }

        @Override
        public int getPageIconResId(int position)
        {
            return pageReseourceIds.get(position);
        }
    }

}

