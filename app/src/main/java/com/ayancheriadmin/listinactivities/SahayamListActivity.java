package com.ayancheriadmin.listinactivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.ayancheriadmin.models.SahayamObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class SahayamListActivity extends AppCompatActivity {

    DatabaseReference databaseReferencel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahayam);

        databaseReferencel = CusUtils.getDatabase().getReference().child("Sahayam");
        final EditText sahayam_title,sahayam_description;
        sahayam_description = (EditText)findViewById(R.id.sahayam_description);
        sahayam_title = (EditText)findViewById(R.id.sahayam_title);

        Button saveSahayam = (Button)findViewById(R.id.saveSahayam);


        saveSahayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = sahayam_title.getText().toString().trim();
                String description = sahayam_description.getText().toString().trim();

                if(title.length() !=0 && description.length() !=0){
                    databaseReferencel.push().setValue(new SahayamObject(title,description)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sahayam_title.setText("");
                            sahayam_description.setText("");
                            Toast.makeText(SahayamListActivity.this, "New Sahayam Info Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(SahayamListActivity.this, "Please Check Title & Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
