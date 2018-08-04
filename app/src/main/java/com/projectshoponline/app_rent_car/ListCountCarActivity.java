package com.projectshoponline.app_rent_car;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.sql.StatementEvent;

public class ListCountCarActivity extends AppCompatActivity {

    private String startSring, endString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_count_car);



        getValueFromIntent();

//        Create Toolbal
        createToolbal();


//        Create Recyclerview
        createRecyclerview();

    }// Main Method

    private void createRecyclerview() {


        RecyclerView recyclerView = findViewById(R.id.recycleViewCountCar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCountCarActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        int timeInt = 1;
        int totalInt = 0;
        ArrayList<String> itemstringArrayList = new ArrayList<>();
        ArrayList<String> productNameringArrayList = new ArrayList<>();
        ArrayList<String> countstringArrayList = new ArrayList<>();

       try{

           MyConstant myConstant = new MyConstant();
           GetValueWhere2Column getValueWhere2Column = new GetValueWhere2Column(ListCountCarActivity.this);
           getValueWhere2Column.execute(startSring, endString, myConstant.getUrlGetCountCar());
           String jsonString = getValueWhere2Column.get();
           Log.d("4AugV2", "JSON==>" + jsonString);

           JSONArray jsonArray = new JSONArray(jsonString);
           for (int i=0; i<jsonArray.length(); i+=1) {
               JSONObject jsonObject = jsonArray.getJSONObject(i);

               itemstringArrayList.add(Integer.toString(timeInt));
               timeInt += 1;

               productNameringArrayList.add(jsonObject.getString("order_product_name"));
               countstringArrayList.add(jsonObject.getString("COUNT"));

               totalInt = totalInt + changCountToInger(jsonObject.getString("COUNT"));
           }

           CountCarAdapter countCarAdapter = new CountCarAdapter(ListCountCarActivity.this,
                   itemstringArrayList, productNameringArrayList, countstringArrayList);
           recyclerView.setAdapter(countCarAdapter);

           TextView textView = findViewById(R.id.txtShowTotal);
           textView.setText(Integer.toString(totalInt));


       }catch (Exception e){
           e.printStackTrace();
       }


    }

    private int changCountToInger(String count) {
        try{

            int amountInt = Integer.parseInt(count);
            return amountInt;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private void createToolbal() {

        Toolbar toolbar = findViewById(R.id.toolbarListCountCar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.count_car));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getValueFromIntent() {

        startSring = getIntent().getStringExtra("Start");
        endString = getIntent().getStringExtra("End");
        Log.d("4AugV1", "Start==>"+startSring);
        Log.d("4AugV1", "END==>"+endString);

    }

}// Main Class
