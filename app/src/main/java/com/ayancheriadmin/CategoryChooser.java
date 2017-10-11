package com.ayancheriadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ayancheriadmin.categoryactivities.BuisnessCategoryActivity;
import com.ayancheriadmin.categoryactivities.DoctorCategoryActivity;
import com.ayancheriadmin.categoryactivities.EducationCategoryActivity;
import com.ayancheriadmin.categoryactivities.FoodCategoryActivity;
import com.ayancheriadmin.categoryactivities.MedicalCategoryActivity;
import com.ayancheriadmin.categoryactivities.WorkerCategoryActivity;
import com.ayancheriadmin.listinactivities.AutoListActivity;
import com.ayancheriadmin.listinactivities.BirthDeathMarriageActivity;
import com.ayancheriadmin.listinactivities.BloodDonorListActivity;
import com.ayancheriadmin.listinactivities.BuisnessListActivity;
import com.ayancheriadmin.listinactivities.DoctorListActivity;
import com.ayancheriadmin.listinactivities.EducationListActivity;
import com.ayancheriadmin.listinactivities.EventListActivity;
import com.ayancheriadmin.listinactivities.FoodListActivity;
import com.ayancheriadmin.listinactivities.GoodsListActivity;
import com.ayancheriadmin.listinactivities.MedicalListActivity;
import com.ayancheriadmin.listinactivities.PuthuvivaramListActivity;
import com.ayancheriadmin.listinactivities.SahayamListActivity;
import com.ayancheriadmin.listinactivities.TaxiListActivity;
import com.ayancheriadmin.listinactivities.VivaranamListActivity;
import com.ayancheriadmin.listinactivities.WorkerListActivity;

public class CategoryChooser extends AppCompatActivity {
    Button buisnessList;
    Button educationList;
    Button bloodList;
    Button buisnessCategories;
    Button educationCategories;
    Button workerCategories;
    Button foodCategories;
    Button medicalCategories;
    Button workerList;
    Button autoList;
    Button goodsList;
    Button eventsList;
    Button foodList;
    Button medicalList;
    Button taxiList;
    Button puthuvivaramList;
    Button doctorCategory;
    Button doctorList;
    Button vivaranamList;
    Button sahayamList;
    Button sendCoupouns;
    Button bdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_chooser);
        buisnessList = (Button)findViewById(R.id.buisnessList);
        buisnessCategories = (Button)findViewById(R.id.buisnessCategories);
        educationCategories = (Button)findViewById(R.id.educationCategories);
        educationList = (Button)findViewById(R.id.educationList);
        bloodList = (Button)findViewById(R.id.bloodList);
        workerCategories = (Button)findViewById(R.id.workerCategories);
        workerList = (Button)findViewById(R.id.workerlist);
        autoList = (Button)findViewById(R.id.autoList);
        goodsList = (Button)findViewById(R.id.goodsList);
        eventsList = (Button)findViewById(R.id.eventsList);
        foodCategories = (Button)findViewById(R.id.foodCategories);
        foodList = (Button)findViewById(R.id.foodList);
        taxiList = (Button)findViewById(R.id.taxiList);
        medicalList = (Button)findViewById(R.id.medicalList);
        medicalCategories = (Button)findViewById(R.id.medicalCategories);
        doctorCategory = (Button)findViewById(R.id.doctorCategories);
        doctorList = (Button)findViewById(R.id.doctorList);
        puthuvivaramList = (Button)findViewById(R.id.puthuvivaramList);
        sahayamList = (Button)findViewById(R.id.sahayamList);
        vivaranamList = (Button)findViewById(R.id.vivaranamList);
        sendCoupouns = (Button)findViewById(R.id.sendCoupouns);
        bdm = (Button)findViewById(R.id.bdm);

        buisnessList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,BuisnessListActivity.class));
                finish();
            }
        });
        educationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,EducationListActivity.class));
                finish();
            }
        });
        buisnessCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,BuisnessCategoryActivity.class));
                finish();
            }
        });
        educationCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,EducationCategoryActivity.class));
                finish();
            }
        });
        bloodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,BloodDonorListActivity.class));
                finish();
            }
        });
        workerCategories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,WorkerCategoryActivity.class));
                        finish();
                    }
                });
        workerList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,WorkerListActivity.class));
                        finish();
                    }
                });
        autoList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,AutoListActivity.class));
                        finish();
                    }
                });
        goodsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,GoodsListActivity.class));
                finish();
            }
        }); eventsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,EventListActivity.class));
                finish();
            }
        }); foodCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,FoodCategoryActivity.class));
                finish();
            }
        });
        foodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,FoodListActivity.class));
                finish();
            }
        });taxiList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,TaxiListActivity.class));
                finish();
            }
        });
        medicalCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,MedicalCategoryActivity.class));
                finish();
            }
        });
        medicalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,MedicalListActivity.class));
                finish();
            }
        });
        puthuvivaramList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,PuthuvivaramListActivity.class));
                        finish();
                    }
                });
        doctorList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,DoctorListActivity.class));
                        finish();
                    }
                });
        doctorCategory.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,DoctorCategoryActivity.class));
                        finish();
                    }
                }); vivaranamList.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CategoryChooser.this,VivaranamListActivity.class));
                        finish();
                    }
                });


        sendCoupouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,SendCopoun.class));
                finish();
            }
        });
        bdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryChooser.this,BirthDeathMarriageActivity.class));
                finish();
           }
        });
        sahayamList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryChooser.this, SahayamListActivity.class));
                finish();
            }
        });


    }
}
