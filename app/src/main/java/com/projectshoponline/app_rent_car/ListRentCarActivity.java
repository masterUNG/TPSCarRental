package com.projectshoponline.app_rent_car;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListRentCarActivity extends AppCompatActivity {


    private String startSring, endString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rent_car);


        getValueFromIntent();

//        Create Toolbal
        createToolbal();


//        Create Recyclerview
        createRecyclerview();


    }// Main Method

    private void createToolbal() {
        Toolbar toolbar = findViewById(R.id.toolbarListRentCar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.data_rent_car));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.recycleViewDataRentCar);
        MyConstant myConstant = new MyConstant();

        int timesInt = 1;
        int totalInt = 0;
        ArrayList<String> itemStringArrayList = new ArrayList<>();
        ArrayList<String> nameAnSurStringArrayList = new ArrayList<>();
        ArrayList<String> productNameStringArrayList = new ArrayList<>();
        ArrayList<String> startDateStringArrayList = new ArrayList<>();
        ArrayList<String> amountStringArrayList = new ArrayList<>();

        try{

            GetValueWhere2Column getValueWhere2Column = new GetValueWhere2Column(ListRentCarActivity.this);
            getValueWhere2Column.execute(startSring, endString, myConstant.getUrlGetWhereStartAndEnd());
            String jsonString = getValueWhere2Column.get();
            Log.d("4AugV1", "JSON ==>" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                itemStringArrayList.add(Integer.toString(timesInt));
                timesInt += 1;

                nameAnSurStringArrayList.add(jsonObject.getString("Fname")+" "+jsonObject.getString("Lname"));
                productNameStringArrayList.add(jsonObject.getString("order_product_name"));
                startDateStringArrayList.add(jsonObject.getString("order_stat_time"));
                amountStringArrayList.add(jsonObject.getString("order_product_amount"));


                totalInt = totalInt + changAmountToInger(jsonObject.getString("order_product_amount"));
            }

            Log.d("4AugV1", "Name And Sur ==>"+nameAnSurStringArrayList.toString());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListRentCarActivity.this,
                    LinearLayoutManager.VERTICAL,false);

            recyclerView.setLayoutManager(linearLayoutManager);

            DataRentCarAdapter dataRentCarAdapter = new DataRentCarAdapter(ListRentCarActivity.this,
                    itemStringArrayList, nameAnSurStringArrayList, productNameStringArrayList, startDateStringArrayList, amountStringArrayList);
            recyclerView.setAdapter(dataRentCarAdapter);

            TextView textView = findViewById(R.id.txtShowTotal);
            textView.setText(Integer.toString(totalInt));

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private int changAmountToInger(String amountString) {

        try{

            int amountInt = Integer.parseInt(amountString);
            return amountInt;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    private void getValueFromIntent() {
        startSring = getIntent().getStringExtra("Start");
        endString = getIntent().getStringExtra("End");
        Log.d("4AugV1", "Start==>"+startSring);
        Log.d("4AugV1", "END==>"+endString);
    }
} //Main Class
