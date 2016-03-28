package com.android.gifts.moga.model;

/**
 * Created by Mohamed Fareed on 3/27/2016.
 */
public class News {
    private int newsID;
    private String title;
    private String content;
    private String date;

    public News(int newsID, String title, String content, String date) {
        this.newsID = newsID;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
