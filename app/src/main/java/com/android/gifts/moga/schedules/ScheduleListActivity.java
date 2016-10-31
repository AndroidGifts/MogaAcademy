package com.android.gifts.moga.schedules;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.gifts.moga.API.model.Schedule;
import com.android.gifts.moga.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScheduleListActivity extends AppCompatActivity implements ScheduleListContract.View {
    @Bind(R.id.schedule_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    CoordinatorLayout container;

    private ProgressDialog pDialog;
    ScheduleListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);

        TextView thisTitle = (TextView) findViewById(R.id.toolbar_title);
        assert thisTitle != null;
        thisTitle.setText(getIntent().getStringExtra("title"));

        presenter = new ScheduleListPresenter(new ScheduleListInteractor(), this);

        presenter.getSchedules(getIntent().getIntExtra("yearId", 1),
                getIntent().getIntExtra("typeId", 1), getIntent().getIntExtra("ScheduleType", 1));
    }

    @Override
    public void initializeRecyclerView(List<Schedule> scheduleList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SchedulesAdapter schedulesAdapter = new SchedulesAdapter(scheduleList, this);
        recyclerView.setAdapter(schedulesAdapter);
    }

    @Override
    public void setProgress(boolean active) {
        if (active) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("جارى التحميل...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        } else {
            pDialog.dismiss();
        }
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Snackbar.make(container, errorMessage,
                Snackbar.LENGTH_LONG).show();
    }
}
