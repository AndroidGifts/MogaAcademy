package com.android.gifts.moga.schedules;

import com.android.gifts.moga.API.model.Schedule;

import java.util.List;

public interface ScheduleListContract {
    interface View {
        void initializeRecyclerView(List<Schedule> scheduleList);

        void setProgress(boolean active);

        void setErrorMessage(String errorMessage);
    }

    interface Presenter {
        void getSchedules(int yearId, int typeId, int scheduleType);
    }

    interface Interactor {
        interface GetSchedulesCallback {
            void success(List<Schedule> scheduleList);

            void fail(String error);
        }

        void getSchedules(int yearId, int typeId, int scheduleType, GetSchedulesCallback callback);
    }
}
