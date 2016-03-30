package com.android.gifts.moga.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.views.adapters.NewsRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<News> news;

    public NewsFragment() {

    }

    public static NewsFragment newInstance(List<News> news) {
        NewsFragment fragment = new NewsFragment();

        Bundle args = new Bundle();
        Gson gson = new Gson();

        Log.e("FOF", "newInstance Fragment Initialized");
        Log.e("FOF", "news newInstance Fragment: size: " + news.size() + " title: " + news.get(0).getTitle());

        String newsAsString = gson.toJson(news);
        args.putString(Constants.FRAGMENT_NEWS_LIST_KEY, newsAsString);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        // Preparing the Data
        String newsAsString = getArguments().getString(Constants.FRAGMENT_NEWS_LIST_KEY);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<News>>(){}.getType();
        Collection<News> newsList = gson.fromJson(newsAsString, collectionType);
        news = (List<News>) newsList;

        Log.e("FOF", "onCreateView Fragment Initialized");
        Log.e("FOF", "news Fragment: size: " + news.size() + " title: " + news.get(0).getTitle());

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
