package com.ayancheriadmin.categoryactivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ayancheriadmin.CusUtils;
import com.ayancheriadmin.R;
import com.ayancheriadmin.models.BuisnessCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerCategoryActivity extends AppCompatActivity {


    Button savenewworkercategory;
    EditText newcategoryed;
    ListView existingCategorylist;

    DatabaseReference databaseReference;
    long current_category_count = 0;
    ArrayList<String> buisnessCategoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_category);
        savenewworkercategory = (Button)findViewById(R.id.savenewworkercategory);
        newcategoryed = (EditText) findViewById(R.id.newcategoryed);
        existingCategorylist = (ListView) findViewById(R.id.existingCategorylist);
        databaseReference = CusUtils.getDatabase().getReference().child("Worker");
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buisnessCategoryArrayList = new ArrayList<String>();
                current_category_count = dataSnapshot.getChildrenCount();

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child:children){
                    BuisnessCategory category = child.getValue(BuisnessCategory.class);
                    buisnessCategoryArrayList.add(category.name);
                    Log.e("C_NAME",category.name);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.clistitem,buisnessCategoryArrayList);
                existingCategorylist.setAdapter(arrayAdapter);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        savenewworkercategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newcagtegory = newcategoryed.getText().toString().trim();
                if (newcagtegory.length() != 0) {
                    BuisnessCategory buisnessCategory = new BuisnessCategory(newcagtegory);
                    databaseReference.child(++current_category_count +"").setValue(buisnessCategory).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(WorkerCategoryActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                newcategoryed.setText("");
                            }
                            else{
                                Toast.makeText(WorkerCategoryActivity.this, "UN Successfull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }

}
