package com.ayancheriadmin.models;

import java.util.ArrayList;

/**
 * Created by asnim on 09/07/17.
 */

public class NotificationAndPromcode
{
    public String body;
    public ArrayList<String> to;
    public String title;
    public String promo_code;


    public NotificationAndPromcode(String body, ArrayList<String> to, String title, String promocode) {
        this.body = body;
        this.to = to;
        this.title = title;
        this.promo_code = promocode;
        
    }
    public void addnewRecipient(String to ){
         this.to.add(to);
    }
}
