package com.ayancheriadmin.listinactivities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.ayancheriadmin.models.BuisnessCategory;
import com.ayancheriadmin.models.BuisnessList;
import com.ayancheriadmin.models.Events;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class EventListActivity extends AppCompatActivity {
    Button saveData,chooseDate;
    DatabaseReference businessDataBase,buisnessCategoryDB;
    EditText dateEd,timeEd,address,eventName;
    long current_count = 0;
    final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 100;

    private int PLACE_PICKER_REQUEST = 1;

    private DatePicker datePicker;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        businessDataBase = CusUtils.getDatabase().getReference().child("Events");


        dateEd= (EditText)findViewById(R.id.date);
        timeEd= (EditText)findViewById(R.id.time);
        address= (EditText)findViewById(R.id.address);
        eventName= (EditText)findViewById(R.id.eventName);
        saveData = (Button)findViewById(R.id.saveData);
//        dateEd.setOnClickListener(new On);

       businessDataBase.keepSynced(true);
//true        buisnessCategoryDB.keepSynced(true);




        businessDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("DB",dataSnapshot.getChildrenCount()+"");
                current_count = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        dateEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
        timeEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });


       final Events b = new Events();

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_string = eventName.getText().toString().trim();
                if(name_string.length()== 0){
                    Toast.makeText(EventListActivity.this, "Please Enter A Name", Toast.LENGTH_SHORT).show();
                }
                else {
                        b.setName(eventName.getText().toString());
                        b.setAddress(address.getText().toString());
                        b.setDateTime(dateEd.getText().toString() +" "+timeEd.getText().toString());




                        businessDataBase.push().setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(EventListActivity.this, "Completed Adding", Toast.LENGTH_SHORT).show();





                                }
                                else{
                                    Toast.makeText(EventListActivity.this, "Some Error Has Happened", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                }

        });


    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        showDialog(998);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            Date t = new Date();

            return new DatePickerDialog(this,
                    myDateListener,2017 , t.getMonth(), 1);
        }
        else if(id == 998){
            return new TimePickerDialog(EventListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    timeEd.setText(new StringBuilder().append(hourOfDay).append(":").append(minute));

                }
            }, 0, 0, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateEd.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year).toString());
    }

}
