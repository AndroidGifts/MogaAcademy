package com.android.gifts.moga.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.gifts.moga.model.News;
import com.android.gifts.moga.views.fragments.NewsFragment;

import java.util.List;

/**
 * Created by Mohamed Fareed on 3/27/2016.
 */
public class TwoTypesNewsFragmentAdapter extends FragmentPagerAdapter {
    private List<News> entesabNews;
    private List<News> entezamNews;

    public TwoTypesNewsFragmentAdapter(FragmentManager fm, List<News> entesabNews, List<News> entezamNews) {
        super(fm);
        this.entesabNews = entesabNews;
        this.entezamNews = entezamNews;
    }

    @Override
    public Fragment getItem(int position) {

        /*List<News> news = new ArrayList<>();
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
*/
        Fragment fragment = null;

        if (position == 0) {
            fragment = NewsFragment.newInstance(entesabNews);
        } else if (position == 1) {
            fragment = NewsFragment.newInstance(entezamNews);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
