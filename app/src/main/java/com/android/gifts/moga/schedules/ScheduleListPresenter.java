package com.android.gifts.moga.schedules;

import com.android.gifts.moga.API.model.Schedule;

import java.util.List;

class ScheduleListPresenter implements ScheduleListContract.Presenter {
    private ScheduleListContract.Interactor interactor;
    private ScheduleListContract.View view;

    ScheduleListPresenter(ScheduleListContract.Interactor interactor, ScheduleListContract.View view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void getSchedules(int yearId, int typeId, int scheduleType) {
        if (view != null)
            view.setProgress(true);

        interactor.getSchedules(yearId, typeId, scheduleType, new ScheduleListContract.Interactor.GetSchedulesCallback() {
            @Override
            public void success(List<Schedule> scheduleList) {
                view.setProgress(false);
                view.initializeRecyclerView(scheduleList);
            }

            @Override
            public void fail(String error) {
                view.setProgress(false);
                view.setErrorMessage(error);
            }
        });
    }
}
