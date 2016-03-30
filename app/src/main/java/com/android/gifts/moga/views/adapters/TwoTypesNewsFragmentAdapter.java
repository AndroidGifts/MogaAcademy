package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.views.fragments.NewsFragment;

import java.util.List;

/**
 * Created by Mohamed Fareed on 3/31/2016.
 */
public class TwoTypesNewsFragmentAdapter extends FragmentPagerAdapter {
    private List<News> entesabNews;
    private List<News> entezamNews;

    private List<News> newsType4;
    private List<News> newsType5;
    private List<News> newsType6;

    public TwoTypesNewsFragmentAdapter(FragmentManager fm, List<News> entesabNews, List<News> entezamNews) {
        super(fm);
        this.entesabNews = entesabNews;
        this.entezamNews = entezamNews;

        Log.e("FOF", "TwoTypesNewsFragmentAdapter 2 Initialized");
    }

    public TwoTypesNewsFragmentAdapter(FragmentManager fm, List<News> newsType4, List<News> newsType5, List<News> newsType6) {
        super(fm);
        this.newsType4 = newsType4;
        this.newsType5 = newsType5;
        this.newsType6 = newsType6;

        Log.e("FOF", "TwoTypesNewsFragmentAdapter 3 Initialized");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        /*if (position == 0) {
            fragment = NewsFragment.newInstance(entesabNews);
        } else if (position == 1) {
            fragment = NewsFragment.newInstance(entezamNews);
        }*/

        switch (position) {
            case 0:
                fragment = NewsFragment.newInstance(entesabNews); break;
            case 1:
                fragment = NewsFragment.newInstance(entezamNews); break;
            case 2:
                fragment = NewsFragment.newInstance(entesabNews); break;
            case 3:
                fragment = NewsFragment.newInstance(entezamNews); break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}