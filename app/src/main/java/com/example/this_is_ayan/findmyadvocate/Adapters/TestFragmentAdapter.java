package com.example.this_is_ayan.findmyadvocate.Adapters;

/**
 * Created by this_is_ayan on 03-10-2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.this_is_ayan.findmyadvocate.Fragments.TestFragment;
import com.example.this_is_ayan.findmyadvocate.R;

public class TestFragmentAdapter extends FragmentPagerAdapter  {
    private int[] offerImages = {
            R.drawable.post_a_case,
            R.drawable.get_viewed,
            R.drawable.connect,
    };

    private int mCount = offerImages.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TestFragment(offerImages[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}


