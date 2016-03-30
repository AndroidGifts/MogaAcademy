package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.views.fragments.NewsFragment;

import java.util.List;

/**
 * Created by Mohamed Fareed on 3/27/2016.
 */
public class SecondYearNewsFragmentAdapter extends FragmentPagerAdapter {
    private List<News> entesabNews;
    private List<News> entezamNews;

    public SecondYearNewsFragmentAdapter(FragmentManager fm, List<News> entesabNews, List<News> entezamNews) {
        super(fm);
        this.entesabNews = entesabNews;
        this.entezamNews = entezamNews;

        Log.e("FOF", "SecondYearNewsFragmentAdapter Initialized");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 2) {
            fragment = NewsFragment.newInstance(entesabNews);
        } else if (position == 3) {
            fragment = NewsFragment.newInstance(entezamNews);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
