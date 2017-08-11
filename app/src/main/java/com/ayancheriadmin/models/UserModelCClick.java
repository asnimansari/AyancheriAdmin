package com.ayancheriadmin.models;

/**
 * Created by asnim on 23/07/17.
 */

public class UserModelCClick {

  public  String  deviceToken;
//            "eZUnwbyyxmI:APA91bF2eBdCzrHzraCi_WDC79Cjo0E_4N6..."
    public  String  emailid;

    public UserModelCClick(String deviceToken, String emailid, String name, Long time, String uid) {
        this.deviceToken = deviceToken;
        this.emailid = emailid;
        this.name = name;
        this.time = time;
        this.uid = uid;
    }
    public UserModelCClick(){

    }

    //            "jishnuastro@gma≥jishnuastroil.com"
    public  String name;
//            "Jishnu Prakash K"
    public  Long time;
//            1500735836869
    public  String uid;
//            "tsS3SdUgwqa5HmgdnJppjjPr51O2"


}
