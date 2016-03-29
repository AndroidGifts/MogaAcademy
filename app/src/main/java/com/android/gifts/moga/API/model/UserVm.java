package com.android.gifts.moga.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserVm {

    @SerializedName("Id")
    @Expose
    private long Id;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("Password")
    @Expose
    private String Password;
    @SerializedName("YearId")
    @Expose
    private long YearId;
    @SerializedName("CreatedAt")
    @Expose
    private String CreatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserVm() {
    }

    /**
     * 
     * @param Name
     * @param Email
     * @param YearId
     * @param Password
     * @param Mobile
     * @param Id
     * @param CreatedAt
     */
    public UserVm(long Id, String Name, String Email, String Mobile, String Password, long YearId, String CreatedAt) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Mobile = Mobile;
        this.Password = Password;
        this.YearId = YearId;
        this.CreatedAt = CreatedAt;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public long getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The Id
     */
    public void setId(long Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * 
     * @param Email
     *     The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * 
     * @return
     *     The Mobile
     */
    public String getMobile() {
        return Mobile;
    }

    /**
     * 
     * @param Mobile
     *     The Mobile
     */
    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    /**
     * 
     * @return
     *     The Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * 
     * @param Password
     *     The Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * 
     * @return
     *     The YearId
     */
    public long getYearId() {
        return YearId;
    }

    /**
     * 
     * @param YearId
     *     The YearId
     */
    public void setYearId(long YearId) {
        this.YearId = YearId;
    }

    /**
     * 
     * @return
     *     The CreatedAt
     */
    public String getCreatedAt() {
        return CreatedAt;
    }

    /**
     * 
     * @param CreatedAt
     *     The CreatedAt
     */
    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

}
