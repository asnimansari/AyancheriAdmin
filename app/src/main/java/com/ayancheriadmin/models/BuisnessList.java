package com.ayancheriadmin.models;

/**
 * Created by asnim on 15/07/17.
 */

public class BuisnessList {
    public void setAddress(String address) {
        this.address = address;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLg(double lg) {
        this.lg = lg;
    }

    public String address;
    public String hours;
    public String name;
    public String number;
    public double lat;
    public double lg;

}
