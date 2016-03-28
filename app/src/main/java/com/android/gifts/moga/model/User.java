package com.android.gifts.moga.model;

/**
 * Created by Mohamed Fareed on 3/23/2016.
 */
public class User {
    private int userID;
    private String name;
    private String mail;
    private String mobile;
    private int year;

    public User(int userID, String name, String mail, String mobile, int year) {
        this.userID = userID;
        this.name = name;
        this.mail = mail;
        this.mobile = mobile;
        this.year = year;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
