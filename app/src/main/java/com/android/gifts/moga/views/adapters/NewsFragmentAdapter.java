package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.gifts.moga.views.fragments.NewsFragment;

/**
 * Created by Mohamed Fareed on 3/27/2016.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {
    private int fragmentCount;

    public NewsFragmentAdapter(FragmentManager fm, int fragmentCount) {
        super(fm);
        this.fragmentCount = fragmentCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }
}
