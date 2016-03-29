
package com.android.gifts.moga.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRegisterResponse {

    @SerializedName("Result")
    @Expose
    private Result Result;
    @SerializedName("UserVm")
    @Expose
    private UserVm UserVm;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginRegisterResponse() {
    }

    /**
     * 
     * @param Result
     * @param UserVm
     */
    public LoginRegisterResponse(Result Result, UserVm UserVm) {
        this.Result = Result;
        this.UserVm = UserVm;
    }

    /**
     * 
     * @return
     *     The Result
     */
    public Result getResult() {
        return Result;
    }

    /**
     * 
     * @param Result
     *     The Result
     */
    public void setResult(Result Result) {
        this.Result = Result;
    }

    /**
     * 
     * @return
     *     The UserVm
     */
    public UserVm getUserVm() {
        return UserVm;
    }

    /**
     * 
     * @param UserVm
     *     The UserVm
     */
    public void setUserVm(UserVm UserVm) {
        this.UserVm = UserVm;
    }

}
