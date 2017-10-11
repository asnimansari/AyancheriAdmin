package com.ayancheriadmin.models;

/**
 * Created by Jishnu on 29-Jun-17.
 */

public class SahayamObject {
    String title,description;

    public SahayamObject() {
    }

    public SahayamObject(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
