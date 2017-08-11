package com.ayancheriadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ayancheriadmin.models.Gallery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendCopoun extends AppCompatActivity {
    DatabaseReference databaseReference;

    ArrayList<Gallery> galleryArrayList;
    ArrayList<String> advTitleList;
    ArrayAdapter<String> arrayAdapter;

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_copoun);

        lv = (ListView)findViewById(R.id.lv);

        databaseReference = CusUtils.getDatabase().getReference().child("Gallery");
        databaseReference.keepSynced(true);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                galleryArrayList = new ArrayList<Gallery>();
                advTitleList = new ArrayList<String>();


                Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                for(DataSnapshot c: dataSnapshotIterable){
                    Log.e("C",c.toString());

                    Gallery g = c.getValue(Gallery.class);
                    galleryArrayList.add(g);
                    advTitleList.add(g.link);

                }
                arrayAdapter = new ArrayAdapter<String>(SendCopoun.this,android.R.layout.simple_list_item_1,advTitleList);
                lv.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gallery g = galleryArrayList.get(position);
                Intent i = new Intent(SendCopoun.this,CoupounDetail.class);
                i.putExtra("offer_number",(position+1)+"");
                i.putExtra("offer_name",g.link);
                startActivity(i);


            }});
    }

}
