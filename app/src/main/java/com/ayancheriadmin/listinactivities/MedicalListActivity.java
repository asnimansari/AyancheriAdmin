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

public class MedicalListActivity extends AppCompatActivity {
    Button saveBTN,loadLocation;
     EditText name,address,phonenumber,latitude1, longitude1,hours;
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
        setContentView(R.layout.activity_medical_list);
        businessDataBase = CusUtils.getDatabase().getReference().child("MedicalList");
        buisnessCategoryDB = CusUtils.getDatabase().getReference().child("Medical");
        buisnessCategoryDB.keepSynced(true);
        businessDataBase.keepSynced(true);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        requestPermission();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                         .build();


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
        loadLocation = (Button)findViewById(R.id.loadLocation);
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
        phonenumber = (EditText)findViewById(R.id.phonenumber);
        latitude1 = (EditText)findViewById(R.id.latitude);
        longitude1 = (EditText)findViewById(R.id.longitude);
        hours = (EditText)findViewById(R.id.hours);

       final BuisnessList b = new BuisnessList();
        loadLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GPSTracker gpstracker = new GPSTracker(BuisnessListActivity.this);
//                if(gpstracker.canGetLocation()){
//
//                    latitude.setText(gpstracker.getLatitude()+"");
//                    longitude1.setText(gpstracker.getLongitude()+"");
//                }
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MedicalListActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_string = name.getText().toString().trim();
                if(name_string.length()== 0){
                    Toast.makeText(MedicalListActivity.this, "Please Enter A Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(current_count != 0 && name.getText().toString()!=""){

                        b.setName(name.getText().toString());
                        b.setNumber(phonenumber.getText().toString());
                        b.setAddress(address.getText().toString());
                        b.setHours(hours.getText().toString());
                        b.setLat(Double.parseDouble(latitude1.getText().toString()));
                        b.setLg(Double.parseDouble(longitude1.getText().toString()));

                        final String current_count_string = current_count+1 +"";

                        businessDataBase.child(new BuisnessCategory(categorySpinner.getSelectedItem().toString()).link).push().setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MedicalListActivity.this, "Completed Adding", Toast.LENGTH_SHORT).show();
                                    current_count ++;
                                    name.setText("");
                                    phonenumber.setText("");
                                    address.setText("");
                                    hours.setText("");
                                    latitude1.setText("");
                                    longitude1.setText("");
                                    b.setLg(0);
                                    b.setLat(0);
                                    b.setName("");
                                    b.setAddress("");
                                    b.setNumber("");
                                    b.setHours("");


                                }
                                else{
                                    Toast.makeText(MedicalListActivity.this, "Some Error Has Happened", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                }
            }
        });


    }
    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                latitude1.setText(latitude);
                longitude1.setText(longitude);


            }
        }
    }
}
