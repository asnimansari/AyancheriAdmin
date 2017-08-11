package com.ayancheriadmin;

import com.ayancheriadmin.models.NotificationAndPromcode;
import com.ayancheriadmin.models.ResponseOnPush;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by asnim on 07/07/17.
 */

public interface PushRequest {
    @POST("/ayancheri/sendcoupon")

//    @Headers({"Authorization:key=AAAAAvJy_bAI:APA91bHuZD0W5G9kppSVGkz4Hwgn0lyeOheCOQLzlFBUh1A0snfxxhlWg3pe3c_7cceGAvRL9zJnUPyq8drmqj-q9b451GACj1QBkyR-C_6kSxrA6nn57kDxj7SkgScAIeF8f9_Gnbvh"})
    Call<ResponseOnPush> sendPushNotification(@Body NotificationAndPromcode test);
}
