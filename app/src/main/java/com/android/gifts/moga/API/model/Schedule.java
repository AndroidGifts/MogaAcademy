
package com.android.gifts.moga.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("Id")
    @Expose
    private long Id;
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
    @SerializedName("ScheduleType")
    @Expose
    private long ScheduleType;
    @SerializedName("Image")
    @Expose
    private String Image;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;
    @SerializedName("CreatedAt")
    @Expose
    private String CreatedAt;

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
     *     The ScheduleType
     */
    public long getScheduleType() {
        return ScheduleType;
    }

    /**
     * 
     * @param ScheduleType
     *     The ScheduleType
     */
    public void setScheduleType(long ScheduleType) {
        this.ScheduleType = ScheduleType;
    }

    /**
     * 
     * @return
     *     The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * 
     * @param Image
     *     The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     * 
     * @return
     *     The ImageUrl
     */
    public String getImageUrl() {
        return ImageUrl;
    }

    /**
     * 
     * @param ImageUrl
     *     The ImageUrl
     */
    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
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
