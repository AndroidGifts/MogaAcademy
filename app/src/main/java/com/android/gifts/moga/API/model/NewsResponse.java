package com.android.gifts.moga.API.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("Result")
    @Expose
    private Result Result;
    @SerializedName("News")
    @Expose
    private List<News> News = new ArrayList<News>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public NewsResponse() {
    }

    /**
     * 
     * @param Result
     * @param News
     */
    public NewsResponse(Result Result, List<News> News) {
        this.Result = Result;
        this.News = News;
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
     *     The News
     */
    public List<News> getNews() {
        return News;
    }

    /**
     * 
     * @param News
     *     The News
     */
    public void setNews(List<News> News) {
        this.News = News;
    }

}
