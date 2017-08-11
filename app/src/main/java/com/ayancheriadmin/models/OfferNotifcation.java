package com.ayancheriadmin.models;

/**
 * Created by asnim on 23/07/17.
 */

public class OfferNotifcation {
    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public OfferNotifcation(){

    }

    public  String coupon;
    public  String date;
    public  String title;

}
