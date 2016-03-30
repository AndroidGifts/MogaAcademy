package com.android.gifts.moga.views.adapters.endlessRecycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import android.widget.TextView;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EndlessRecyclerViewNewsAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    List<News> news = new ArrayList<>();

    private int lastVisibleItem, totalItemCount;
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 2;
    private boolean loading;
    private OnLoadMoreListener loadMoreListener;

    public EndlessRecyclerViewNewsAdapter(List<News> news, RecyclerView recyclerView) {
        this.news = news;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    // When to load more ?? when the total items < lastVisibleID + threshold
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    Log.e("FOF", "Total Items: " + totalItemCount);
                    Log.e("FOF", "last Visible Item: " + lastVisibleItem);

                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.loadMoreListener = onLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return news.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_ITEM) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_news_row, parent, false);
            NewsViewHolder viewHolder = new NewsViewHolder(row);
            return viewHolder;
        } else {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_item, parent, false);
            ProgressViewHolder viewHolder = new ProgressViewHolder(row);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            News singleNews = news.get(position);

            ((NewsViewHolder)holder).title.setText(singleNews.getTitle());
            ((NewsViewHolder)holder).content.setText(singleNews.getSubject());

            ((NewsViewHolder)holder).date.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(singleNews.getCreatedAt()));
        } else {
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }
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

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.recycler_view_progress_bar);
        }
    }
}
