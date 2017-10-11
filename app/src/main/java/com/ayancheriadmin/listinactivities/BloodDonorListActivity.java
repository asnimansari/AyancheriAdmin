package com.ayancheriadmin.listinactivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.ayancheriadmin.models.NamePhoneNumberModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by asnim on 16/07/17.
 */

public class BloodDonorListActivity extends AppCompatActivity {
    EditText donorname,donornumber;
    Spinner bloodgroup;
    Button saveBloodGroup;
    DatabaseReference databaseReferece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donor);
        donorname= (EditText)findViewById(R.id.donorname);
        donornumber= (EditText)findViewById(R.id.donornumber);
        bloodgroup= (Spinner) findViewById(R.id.bloodgroup);
        saveBloodGroup= (Button) findViewById(R.id.saveBloodGroup);
        databaseReferece = CusUtils.getDatabase().getReference().child("BloodList");
        ArrayList<String> bloodlist = new ArrayList<>();
        bloodlist.add("A+");
        bloodlist.add("A-");
        bloodlist.add("B+");
        bloodlist.add("B-");
        bloodlist.add("O+");
        bloodlist.add("O-");
        bloodlist.add("AB+");
        bloodlist.add("AB-");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.clistitem,bloodlist);
        bloodgroup.setAdapter(arrayAdapter);
        saveBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = donorname.getText().toString().trim();
                if(nam.length() != 0){
                    String bloodgrou = bloodgroup.getSelectedItem().toString().toLowerCase().replace("+","p").replace("-","n");
                    databaseReferece.child(bloodgrou).push().setValue(new NamePhoneNumberModel(nam,donornumber.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BloodDonorListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                donorname.setText("");
                                donornumber.setText("");
                            }
                            else{
                                Toast.makeText(BloodDonorListActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(BloodDonorListActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
