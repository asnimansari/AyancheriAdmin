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
import com.ayancheriadmin.models.BirthDeathMarriage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by asnim on 16/07/17.
 */

public class BirthDeathMarriageActivity extends AppCompatActivity {
    EditText title,description,date;
    Spinner bloodgroup;
    Button saveBloodGroup;
    DatabaseReference databaseReferece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdw_donor);
        title= (EditText)findViewById(R.id.title);
        description= (EditText)findViewById(R.id.description);
        date= (EditText)findViewById(R.id.date);
        bloodgroup= (Spinner) findViewById(R.id.bloodgroup);
        saveBloodGroup= (Button) findViewById(R.id.saveBloodGroup);
        databaseReferece = CusUtils.getDatabase().getReference();
        ArrayList<String> bloodlist = new ArrayList<>();
        bloodlist.add("Birth");
        bloodlist.add("Death");
        bloodlist.add("Marriage");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.clistitem,bloodlist);
        bloodgroup.setAdapter(arrayAdapter);
        saveBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String bloodgrou = bloodgroup.getSelectedItem().toString();//.toLowerCase().replace("+","p").replace("-","n");
                    databaseReferece.child(bloodgrou).push().setValue(new BirthDeathMarriage(date.getText().toString(),description.getText().toString(),title.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BirthDeathMarriageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                date.setText("");
                                description.setText("");
                                title.setText("");
                            }
                            else{
                                Toast.makeText(BirthDeathMarriageActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        });


    }
}
