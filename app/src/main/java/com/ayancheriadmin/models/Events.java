package com.ayancheriadmin.models;

/**
 * Created by asnim on 19/07/17.
 */

public class Events {
    public String address;
    public String dateTime;
    public String name;
    public Events(){

    }

    public Events(String address, String dateTime, String name) {
        this.address = address;
        this.dateTime = dateTime;
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }
}
