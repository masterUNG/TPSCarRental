package com.projectshoponline.app_rent_car;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ListRentCarActivity extends AppCompatActivity {


    private String startSring, endString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rent_car);


        getValueFromIntent();

//        Create Recyclerview
        createRecyclerview();


    }// Main Method

    private void createRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.recycleViewDataRentCar);
        MyConstant myConstant = new MyConstant();

        try{

            GetValueWhere2Column getValueWhere2Column = new GetValueWhere2Column(ListRentCarActivity.this);
            getValueWhere2Column.execute(startSring, endString, myConstant.getUrlGetWhereStartAndEnd());
            String jsonString = getValueWhere2Column.get();
            Log.d("4AugV1", "JSON ==>" + jsonString);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void getValueFromIntent() {
        startSring = getIntent().getStringExtra("Start");
        endString = getIntent().getStringExtra("End");
        Log.d("4AugV1", "Start==>"+startSring);
        Log.d("4AugV1", "END==>"+endString);
    }
} //Main Class
