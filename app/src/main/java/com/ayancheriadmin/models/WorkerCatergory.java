package com.ayancheriadmin.models;

/**
 * Created by asnim on 16/07/17.
 */

public class WorkerCatergory {


    public String name;
    public String link;

    public WorkerCatergory(String name) {
        this.name = name;
        this.link = name.toLowerCase().trim().replace(" ","");
    }
    public WorkerCatergory(){

    }
}
