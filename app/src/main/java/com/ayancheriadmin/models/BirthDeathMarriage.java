package com.ayancheriadmin.models;

/**
 * Created by asnim on 25/07/17.
 */

public class BirthDeathMarriage {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  String date;
    public  String desc;
    public  String title;

    public BirthDeathMarriage() {

    }

    public BirthDeathMarriage(String date, String desc, String title) {
        this.date = date;
        this.desc = desc;
        this.title = title;
    }
}
