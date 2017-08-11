package com.ayancheriadmin.listinactivities;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.ayancheriadmin.models.BuisnessCategory;
import com.ayancheriadmin.models.BuisnessList;
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

public class DoctorListActivity extends AppCompatActivity {
    Button saveBTN;
     EditText name,phonenumber;//,latitude1, longitude1,hours;
    Spinner categorySpinner;
    DatabaseReference businessDataBase,buisnessCategoryDB;
    long current_count = 0;
    final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 100;
    ArrayList<String> buisness_category_array_list;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        businessDataBase = CusUtils.getDatabase().getReference().child("DoctorList");
        buisnessCategoryDB = CusUtils.getDatabase().getReference().child("Doctor");
        buisnessCategoryDB.keepSynced(true);
        businessDataBase.keepSynced(true);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);



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
        buisnessCategoryDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buisness_category_array_list = new ArrayList<String>();
                buisness_category_array_list.add("None");
                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                for(DataSnapshot child:dataSnapshotIterable){
                    BuisnessCategory category = child.getValue(BuisnessCategory.class);
                    buisness_category_array_list.add(category.name);

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.clistitem,buisness_category_array_list);
                categorySpinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        saveBTN = (Button)findViewById(R.id.save);
//        loadLocation = (Button)findViewById(R.id.loadLocation);
        name = (EditText)findViewById(R.id.name);

        phonenumber = (EditText)findViewById(R.id.phonenumber);

       final BuisnessList b = new BuisnessList();

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_string = name.getText().toString().trim();
                if(name_string.length()== 0){
                    Toast.makeText(DoctorListActivity.this, "Please Enter A Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(current_count != 0 && name.getText().toString()!=""){

                        b.setName(name.getText().toString());
                        b.setNumber(phonenumber.getText().toString());


                        final String current_count_string = current_count+1 +"";

                        businessDataBase.child(new BuisnessCategory(categorySpinner.getSelectedItem().toString()).link).push().setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DoctorListActivity.this, "Completed Adding", Toast.LENGTH_SHORT).show();
                                    current_count ++;
                                    name.setText("");
                                    phonenumber.setText("");

                                    b.setName("");
                                    b.setAddress("");



                                }
                                else{
                                    Toast.makeText(DoctorListActivity.this, "Some Error Has Happened", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                }
            }
        });


    }



}
