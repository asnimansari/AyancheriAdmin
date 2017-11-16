package com.ayancheriadmin;

import com.ayancheriadmin.models.Notf;
import com.ayancheriadmin.models.NotificationAndPromcode;
import com.ayancheriadmin.models.ResponseOnPush;
import com.google.android.gms.common.api.Response;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by asnim on 07/07/17.
 */

public interface PushRequest {
    @POST("/ayancheri/sendcoupon")

    Call<ResponseOnPush> sendPushNotificationToIndividuals(@Body NotificationAndPromcode test);

    @POST("x")
    Call<ResponseOnPush> sendOrdinaryNotifiction(@Body Notf notf);
}
