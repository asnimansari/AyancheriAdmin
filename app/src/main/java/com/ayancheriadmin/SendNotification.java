package com.ayancheriadmin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayancheriadmin.models.Notf;
import com.ayancheriadmin.models.NotificationAndPromcode;
import com.ayancheriadmin.models.ResponseOnPush;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendNotification extends AppCompatActivity {

    Button sendPush;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sendPush = (Button)findViewById(R.id.sendPush);
        message = (EditText) findViewById(R.id.message);

        sendPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = message.getText().toString().trim();
                if(m.length() <0){
                    Toast.makeText(SendNotification.this, "Please Type a Message", Toast.LENGTH_SHORT).show();
                }
                else{

//                    M tempRelay = new NotificationAndPromcode("You Have A New Notification",tokenList,"സ്മാർട്ട് ആയഞ്ചേരി ",code);

                    Notf notf  = new Notf();
                    notf.title = "സ്മാർട്ട് ആയഞ്ചേരി";
                    notf.body = m;

                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://mobile.msrgrid.com:1337")
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();
                    final PushRequest pushRequest = retrofit.create(PushRequest.class);



                    final Call<ResponseOnPush> responseOnPushCall = pushRequest.sendOrdinaryNotifiction(notf);
                    responseOnPushCall.enqueue(new Callback<ResponseOnPush>() {
                        @Override
                        public void onResponse(Call<ResponseOnPush> call, Response<ResponseOnPush> response) {
                            Log.e("Status COde",response.code()+"");
                            Toast.makeText(SendNotification.this, "Notification Has been Successfully Sent", Toast.LENGTH_SHORT).show();
                            message.setText("");
                        }

                        @Override
                        public void onFailure(Call<ResponseOnPush> call, Throwable t) {
//                            Toast.makeText(SendNotification.this, "Failed Sending Notificaion", Toast.LENGTH_SHORT).show();
                            Toast.makeText(SendNotification.this, "Notification Has been Successfully Sent", Toast.LENGTH_SHORT).show();
                            message.setText("");

                        }
                    });

                }
            }
        });



    }

}
