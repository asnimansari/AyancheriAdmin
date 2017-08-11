package com.ayancheriadmin.models;

/**
 * Created by asnim on 16/07/17.
 */

public class BuisnessCategory {


    public String name;
    public String link;

    public BuisnessCategory(String name) {
        this.name = name;
        this.link = name.toLowerCase().trim().replace(" ","");
    }
    public BuisnessCategory(){

    }
}
