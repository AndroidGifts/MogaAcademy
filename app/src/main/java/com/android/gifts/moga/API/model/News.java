package com.android.gifts.moga.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("Id")
    @Expose
    private long Id;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Subject")
    @Expose
    private String Subject;
    @SerializedName("YearId")
    @Expose
    private long YearId;
    @SerializedName("YearName")
    @Expose
    private String YearName;
    @SerializedName("TypeId")
    @Expose
    private long TypeId;
    @SerializedName("TypeName")
    @Expose
    private String TypeName;
    @SerializedName("CreatedAt")
    @Expose
    private String CreatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public News() {
    }

    /**
     * 
     * @param TypeId
     * @param Subject
     * @param YearId
     * @param Id
     * @param TypeName
     * @param YearName
     * @param CreatedAt
     * @param Title
     */
    public News(long Id, String Title, String Subject, long YearId, String YearName, long TypeId, String TypeName, String CreatedAt) {
        this.Id = Id;
        this.Title = Title;
        this.Subject = Subject;
        this.YearId = YearId;
        this.YearName = YearName;
        this.TypeId = TypeId;
        this.TypeName = TypeName;
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
     *     The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * 
     * @param Title
     *     The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * 
     * @return
     *     The Subject
     */
    public String getSubject() {
        return Subject;
    }

    /**
     * 
     * @param Subject
     *     The Subject
     */
    public void setSubject(String Subject) {
        this.Subject = Subject;
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
     *     The YearName
     */
    public String getYearName() {
        return YearName;
    }

    /**
     * 
     * @param YearName
     *     The YearName
     */
    public void setYearName(String YearName) {
        this.YearName = YearName;
    }

    /**
     * 
     * @return
     *     The TypeId
     */
    public long getTypeId() {
        return TypeId;
    }

    /**
     * 
     * @param TypeId
     *     The TypeId
     */
    public void setTypeId(long TypeId) {
        this.TypeId = TypeId;
    }

    /**
     * 
     * @return
     *     The TypeName
     */
    public String getTypeName() {
        return TypeName;
    }

    /**
     * 
     * @param TypeName
     *     The TypeName
     */
    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
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
