package com.android.gifts.moga.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("Code")
    @Expose
    private long Code;
    @SerializedName("Message")
    @Expose
    private String Message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param Message
     * @param Code
     */
    public Result(long Code, String Message) {
        this.Code = Code;
        this.Message = Message;
    }

    /**
     * 
     * @return
     *     The Code
     */
    public long getCode() {
        return Code;
    }

    /**
     * 
     * @param Code
     *     The Code
     */
    public void setCode(long Code) {
        this.Code = Code;
    }

    /**
     * 
     * @return
     *     The Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * 
     * @param Message
     *     The Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

}
