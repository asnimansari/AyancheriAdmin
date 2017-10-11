package com.ayancheriadmin.listinactivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by asnim on 16/07/17.
 */

public class VivaranamListActivity extends AppCompatActivity {
    EditText donorname,donornumber;

    Button saveBloodGroup;
    DatabaseReference databaseReferece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivaranam_list);
        donorname= (EditText)findViewById(R.id.donorname);
        donornumber= (EditText)findViewById(R.id.donornumber);

        saveBloodGroup= (Button) findViewById(R.id.saveBloodGroup);
        databaseReferece = CusUtils.getDatabase().getReference().child("Vivaranam");
        ArrayList<String> vivaranamlist = new ArrayList<>();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.clistitem,vivaranamlist);

        saveBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = donorname.getText().toString().trim();
                if(nam.length() != 0){

                    databaseReferece.child(donorname.getText().toString()).child("1").setValue(donornumber.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(VivaranamListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                donorname.setText("");
                                donornumber.setText("");
                            }
                            else{
                                Toast.makeText(VivaranamListActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(VivaranamListActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
