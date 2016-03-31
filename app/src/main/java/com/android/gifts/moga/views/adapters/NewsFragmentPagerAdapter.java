package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.views.fragments.NewsFragment;

import java.util.List;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public NewsFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        Log.e("FOF", "NewsFragmentPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void add(List<News> news) {
        fragments.add(NewsFragment.newInstance(news));
        Log.e("FOF", "NewsFragment.newInstance");
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
