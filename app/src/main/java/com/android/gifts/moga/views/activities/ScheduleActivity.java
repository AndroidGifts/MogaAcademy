package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.senab.photoview.PhotoView;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.image_loading_progress_bar);
        assert progressBar != null;
        progressBar.setVisibility(View.VISIBLE);

        PhotoView photoView = (PhotoView) findViewById(R.id.schedule_photo);

        Intent intent = getIntent();
        String activityTitle = intent.getStringExtra("title");
        String scheduleURL = intent.getStringExtra("scheduleURL");

        Log.e("MYLOG", "scheduleURL: " + scheduleURL);

        assert photoView != null;
        Picasso.with(this)
                .load(scheduleURL)
                .into(photoView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        TextView thisTitle = (TextView) findViewById(R.id.toolbar_title);
        assert thisTitle != null;
        thisTitle.setText(activityTitle);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}
