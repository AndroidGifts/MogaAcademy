package com.android.gifts.moga.schedules;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.gifts.moga.API.model.Schedule;
import com.android.gifts.moga.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.ScheduleViewHolder> {
    private Context context;
    private List<Schedule> scheduleList = new ArrayList<>();

    SchedulesAdapter(List<Schedule> scheduleList, Context context) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context)
                .inflate(R.layout.single_schedule_row, parent, false);

        return new ScheduleViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, final int position) {
        final Schedule schedule = scheduleList.get(position);

        /*Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(schedule.getCreatedAt());*/

        String date = schedule.getCreatedAt();
        int TChar = date.indexOf("T");

        String subString = "";

        if (TChar != -1) {
            subString = date.substring(0, TChar);
        }

        holder.scheduleDate.setText(subString);

        Picasso.with(context)
                .load(schedule.getImageUrl())
                .into(holder.scheduleThumb);

        holder.scheduleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleScheduleActivity.class);
                intent.putExtra("scheduleURL", schedule.getImageUrl());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (scheduleList == null) {
            return 0;
        } else {
            return scheduleList.size();
        }
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {
        ImageView scheduleThumb;
        TextView scheduleDate;
        LinearLayout scheduleContainer;

        ScheduleViewHolder(View itemView) {
            super(itemView);

            scheduleContainer = (LinearLayout) itemView.findViewById(R.id.schedule_container);
            scheduleThumb = (ImageView) itemView.findViewById(R.id.schedule_thumbnail);
            scheduleDate = (TextView) itemView.findViewById(R.id.schedule_date);
        }
    }
}
