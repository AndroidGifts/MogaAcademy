package com.android.gifts.moga.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private List<News> news = new ArrayList<>();

    public NewsRecyclerViewAdapter(List<News> news) {
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_news_row, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(row);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News currentNews = news.get(position);

        holder.title.setText(currentNews.getTitle());
        holder.content.setText(currentNews.getSubject());
        holder.date.setText(currentNews.getCreatedAt().substring(0, 9));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView date;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            content = (TextView) itemView.findViewById(R.id.news_content);
            date = (TextView) itemView.findViewById(R.id.news_date);
        }
    }
}
