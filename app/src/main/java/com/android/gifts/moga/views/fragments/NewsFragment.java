package com.android.gifts.moga.views.fragments;

import android.content.Intent;
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
import com.android.gifts.moga.API.model.Schedule;
import com.android.gifts.moga.R;
import com.android.gifts.moga.presenter.main.MainPresenter;
import com.android.gifts.moga.presenter.main.MainPresenterImp;
import com.android.gifts.moga.views.activities.ScheduleActivity;
import com.android.gifts.moga.views.adapters.endlessRecycler.EndlessRecyclerViewNewsAdapter;
import com.android.gifts.moga.views.adapters.endlessRecycler.OnLoadMoreListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends Fragment implements NewsFragmentView{
    private RecyclerView recyclerView;

    //private NewsRecyclerViewAdapter recyclerViewAdapter;
    private EndlessRecyclerViewNewsAdapter endlessNewsAdapter;
    private List<News> news;
    int pageIndex = 0;
    int yearId;
    int typeId;

    private Intent intent;
    private View rootView;

    private MainPresenter presenter;
    @Bind(R.id.fragment_progress_bar) ProgressBar progressBar;

    private String scheduleType1, scheduleType2, scheduleType3;

    public NewsFragment() {

    }

    public static NewsFragment newInstance(int yearId, int typeId) {
        NewsFragment fragment = new NewsFragment();

        Bundle args = new Bundle();
        args.putInt("yearId", yearId);
        args.putInt("typeId", typeId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);

        yearId = getArguments().getInt("yearId");
        typeId = getArguments().getInt("typeId");

        presenter = new MainPresenterImp(this, getContext());
        presenter.getNews(pageIndex, 10, yearId, typeId);
        presenter.getSchedules(yearId, typeId);

        intent = new Intent(getActivity(), ScheduleActivity.class);

        // Inflate the layout for this fragment
        return rootView;
    }

    @OnClick(R.id.left_layout)
    public void openLeftSchedule() {
        intent.putExtra("title", "جدول الملازم");
        intent.putExtra("scheduleURL", scheduleType2);
        startActivity(intent);
    }

    @OnClick(R.id.center_layout)
    public void openCenterSchedule() {
        intent.putExtra("title", "مواعيد الشرح");
        intent.putExtra("scheduleURL", scheduleType3);
        startActivity(intent);
    }

    @OnClick(R.id.right_layout)
    public void openRightSchedule() {
        intent.putExtra("title", "جدول المحاضرات و السكاشن");
        intent.putExtra("scheduleURL", scheduleType1);
        startActivity(intent);
    }

    @Override
    public void initializeRecyclerView(List<News> firstNews) {
        news = firstNews;

        // Instantiate RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.news_recyclerview);

        // Instantiate LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // Attach the recyclerView to that LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        endlessNewsAdapter = new EndlessRecyclerViewNewsAdapter(news, recyclerView);

        endlessNewsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                news.add(null);
                endlessNewsAdapter.notifyItemInserted(news.size() - 1);
                presenter.getNews(pageIndex + 1, 10, yearId, typeId);
            }
        });

        recyclerView.setAdapter(endlessNewsAdapter);
    }

    @Override
    public void updateRecyclerView(List<News> updatedNews) {
        news.remove(news.size() - 1);
        endlessNewsAdapter.notifyItemRemoved(news.size());

        for (int i = 0; i < updatedNews.size(); i++) {
            news.add(updatedNews.get(i));
            endlessNewsAdapter.notifyItemInserted(news.size());
        }

        endlessNewsAdapter.setLoaded();
        pageIndex++;
    }

    @Override
    public void initializeSchedules(List<Schedule> schedules) {
        for (int i = 0; i < 3; i++) {
            Log.e("MYLOG", "GOT SCHEDULE NUM: " + i + ", URL" + schedules.get(i).getImageUrl());

            int scheduleType = (int) schedules.get(i).getScheduleType();

            switch (scheduleType) {
                case 1:
                    scheduleType1 = schedules.get(i).getImageUrl();
                    break;
                case 2:
                    scheduleType2 = schedules.get(i).getImageUrl();
                    break;
                case 3:
                    scheduleType3 = schedules.get(i).getImageUrl();
                    break;
            }
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideSpinner() {
        news.remove(news.size() - 1);
        endlessNewsAdapter.notifyItemRemoved(news.size());
    }
}
