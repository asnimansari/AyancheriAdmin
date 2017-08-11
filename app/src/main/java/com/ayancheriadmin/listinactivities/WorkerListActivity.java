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
import com.ayancheriadmin.models.BloodDonor;
import com.ayancheriadmin.models.BuisnessCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by asnim on 16/07/17.
 */

public class WorkerListActivity extends AppCompatActivity {
    EditText donorname,donornumber;
    Spinner bloodgroup;
    Button saveBloodGroup;
    DatabaseReference databaseReferece;
    DatabaseReference databaseReferece1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);
        donorname= (EditText)findViewById(R.id.donorname);
        donornumber= (EditText)findViewById(R.id.donornumber);
        bloodgroup= (Spinner) findViewById(R.id.bloodgroup);
        saveBloodGroup= (Button) findViewById(R.id.saveBloodGroup);
        databaseReferece = CusUtils.getDatabase().getReference().child("WorkerList");
        databaseReferece1 = CusUtils.getDatabase().getReference().child("Worker");
        final ArrayList<String> bloodlist = new ArrayList<>();
        databaseReferece1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                bloodlist = bloodlistnew ArrayList<String>();

                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                for(DataSnapshot child:dataSnapshotIterable){
                    BuisnessCategory category = child.getValue(BuisnessCategory.class);
                    bloodlist.add(category.name);

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.clistitem,bloodlist);
                bloodgroup.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        saveBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = donorname.getText().toString().trim();
                if(nam.length() != 0){
                    String bloodgrou = bloodgroup.getSelectedItem().toString().toLowerCase().replace(" ","").trim();
                    databaseReferece.child(bloodgrou).push().setValue(new BloodDonor(nam,donornumber.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(WorkerListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                donorname.setText("");
                                donornumber.setText("");
                            }
                            else{
                                Toast.makeText(WorkerListActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(WorkerListActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
