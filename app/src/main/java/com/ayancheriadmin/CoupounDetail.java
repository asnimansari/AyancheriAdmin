package com.ayancheriadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ayancheriadmin.models.NotificationAndPromcode;
import com.ayancheriadmin.models.OfferNotifcation;
import com.ayancheriadmin.models.ResponseOnPush;
import com.ayancheriadmin.models.UserModelCClick;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoupounDetail extends AppCompatActivity {
    //String number/number
    String number;// = 0;
    String l;

    TextView offername;
    EditText ccode, title,date;
    Button sendC;

    DatabaseReference databaseReference ;
    DatabaseReference databaseReferenceofUsers ;

    ArrayList<String> userlist;
    ArrayList<String> uidlist;
    ArrayList<UserModelCClick> userModelCClickArrayList;

    ListView userLV;
    ArrayAdapter<String> arrayAdapter;

    Boolean selected_postion_list[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupoun_detail);
        Intent i = getIntent();
        number = i.getStringExtra("offer_number");
        l = i.getStringExtra("offer_name");
        databaseReference = CusUtils.getDatabase().getReference().child("Notifications");
        databaseReference.keepSynced(true);
        Log.e("NUMBER",number);
        Log.e("NAME",l);
        databaseReferenceofUsers = CusUtils.getDatabase().getReference().child("CouponClicks").child(number+"");
        databaseReferenceofUsers.keepSynced(true);
        offername = (TextView)findViewById(R.id.offername);
        ccode = (EditText) findViewById(R.id.ccode);
        title = (EditText) findViewById(R.id.title);
        date = (EditText) findViewById(R.id.date);
        sendC = (Button) findViewById(R.id.sendC);
        userLV = (ListView) findViewById(R.id.userlv);
        userLV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        userLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_postion_list[position] = !selected_postion_list[position];
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://mobile.msgrid.com:1337")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        final PushRequest pushRequest = retrofit.create(PushRequest.class);




        sendC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String code = ccode.getText().toString();
                String title1 = title.getText().toString();
                String t_date = date.getText().toString();
                OfferNotifcation offerNotifcation = new OfferNotifcation();
                offerNotifcation.setCoupon(code);
                offerNotifcation.setTitle(title1);
                offerNotifcation.setDate(t_date);



                ArrayList<String> tokenList = new ArrayList<String>();
                Log.e("CHECK_LIST",selected_postion_list.length+"");
                Log.e("USER_SIZE",userModelCClickArrayList.size()+"");
                Log.e("UID SIZE",uidlist.size()+"");


                    Log.e("SIZE","MATCHES");

                    for(int i = 0;i<selected_postion_list.length;i++){
                        if(selected_postion_list[i] == true){
                            tokenList.add(userModelCClickArrayList.get(i).deviceToken);
                            Log.e("USER",userModelCClickArrayList.get(i).name);
                            databaseReference.child(uidlist.get(i)).push().setValue(offerNotifcation);
                        }
                    }


//                    Toast.makeText(CoupounDetail.this, "SOME ERROR", Toast.LENGTH_SHORT).show();
                ///    return;

                NotificationAndPromcode tempRelay = new NotificationAndPromcode("You Have A New Notification",tokenList,"സ്മാർട്ട് ആയഞ്ചേരി ",code);

                final Call<ResponseOnPush> responseOnPushCall = pushRequest.sendPushNotificationToIndividuals(tempRelay);
                responseOnPushCall.enqueue(new Callback<ResponseOnPush>() {
                    @Override
                    public void onResponse(Call<ResponseOnPush> call, Response<ResponseOnPush> response) {
                        Log.e("Status COde",response.code()+"");
                    }

                    @Override
                    public void onFailure(Call<ResponseOnPush> call, Throwable t) {

                    }
                });

            }
        });

        databaseReferenceofUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userlist = new ArrayList<String>();
                uidlist = new ArrayList<String>();
                userModelCClickArrayList = new ArrayList<UserModelCClick>();
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                for (DataSnapshot dataSnapshot1: dataSnapshotIterable){
                    UserModelCClick t = dataSnapshot1.getValue(UserModelCClick.class);
                    userlist.add(t.name + " " + t.emailid);
                    uidlist.add(dataSnapshot1.getKey());
                    userModelCClickArrayList.add(dataSnapshot1.getValue(UserModelCClick.class));
                    Log.e("KEYS",dataSnapshot1.getKey());


                }
                arrayAdapter = new ArrayAdapter<String>(CoupounDetail.this,android.R.layout.simple_list_item_multiple_choice,userlist);
                userLV.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                selected_postion_list = new Boolean[userlist.size()];
                for(int i = 0;i<selected_postion_list.length;i++){
                    selected_postion_list[i] = false;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
