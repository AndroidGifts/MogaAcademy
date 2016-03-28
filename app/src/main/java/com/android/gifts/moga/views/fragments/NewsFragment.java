package com.android.gifts.moga.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gifts.moga.R;
import com.android.gifts.moga.model.News;
import com.android.gifts.moga.views.adapters.NewsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<News> news;

    public NewsFragment() {
        // Required empty public constructor

        news = new ArrayList<>();
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        // Instantiate RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.news_recyclerview);

        // Instantiate LayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        // Attach the recyclerView to that LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        // Instantiate Custom Adapter with the required data
        recyclerViewAdapter = new NewsRecyclerViewAdapter(news);
        // Attach the recyclerView to that Adapter
        recyclerView.setAdapter(recyclerViewAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }
}
