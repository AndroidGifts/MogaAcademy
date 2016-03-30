package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.views.fragments.NewsFragment;

import java.util.List;

/**
 * Created by Mohamed Fareed on 3/29/2016.
 */
public class ThreeTypesNewsFragmentAdapter extends FragmentPagerAdapter {
    List<News> khargyaNews;
    List<News> edaraNews;
    List<News> mohasbaNews;

    public ThreeTypesNewsFragmentAdapter(FragmentManager fm, List<News> khargyaNews, List<News> edaraNews, List<News> mohasbaNews) {
        super(fm);
        this.khargyaNews = khargyaNews;
        this.edaraNews = edaraNews;
        this.mohasbaNews = mohasbaNews;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = NewsFragment.newInstance(khargyaNews);
        } else if (position == 1) {
            fragment = NewsFragment.newInstance(edaraNews);
        } else if (position == 2) {
            fragment = NewsFragment.newInstance(mohasbaNews);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
