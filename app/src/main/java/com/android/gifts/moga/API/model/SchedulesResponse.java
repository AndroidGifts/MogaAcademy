
package com.android.gifts.moga.API.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchedulesResponse {

    @SerializedName("Result")
    @Expose
    private com.android.gifts.moga.API.model.Result Result;
    @SerializedName("Schedules")
    @Expose
    private List<Schedule> Schedules = new ArrayList<Schedule>();

    /**
     * 
     * @return
     *     The Result
     */
    public com.android.gifts.moga.API.model.Result getResult() {
        return Result;
    }

    /**
     * 
     * @param Result
     *     The Result
     */
    public void setResult(com.android.gifts.moga.API.model.Result Result) {
        this.Result = Result;
    }

    /**
     * 
     * @return
     *     The Schedules
     */
    public List<Schedule> getSchedules() {
        return Schedules;
    }

    /**
     * 
     * @param Schedules
     *     The Schedules
     */
    public void setSchedules(List<Schedule> Schedules) {
        this.Schedules = Schedules;
    }

}
