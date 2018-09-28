package com.projectshoponline.app_rent_car;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Show_Detail_And_Rent extends AppCompatActivity {


    TextView tv_product_id;
    TextView tv_product_name;
    TextView tv_product_price;
    TextView tv_car_cylinder;
    TextView tv_car_horse_power;
    TextView tv_car_seat_number;

    ImageView imgview1;
    ///////////////////////////
    String New_String_product_img_1;
    //////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail_and_rent);


        tv_product_id = (TextView) findViewById(R.id.tv_product_id);
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        tv_product_price = (TextView) findViewById(R.id.tv_product_price);
        tv_car_cylinder = (TextView) findViewById(R.id.tv_car_cylinder);
        tv_car_horse_power = (TextView) findViewById(R.id.tv_car_horse_power);
        tv_car_seat_number = (TextView) findViewById(R.id.tv_car_seat_number);
        tv_car_cylinder = (TextView) findViewById(R.id.tv_car_cylinder);

        final Intent intent = getIntent();
        String s_ID = intent.getStringExtra("detail_id");
        String s_NAME = intent.getStringExtra("car_detail");
        String s_PRICE = intent.getStringExtra("car_price");
        String s_IMAGE = intent.getStringExtra("car_img");
//        String s_CYLINDER = intent.getStringExtra("car_cylinder");
//        String s_HORSE_POWER = intent.getStringExtra("car_horse_power");
        String s_CAR_SEAT_NUMBER = intent.getStringExtra("car_seat_number");
        String s_CAR_CYLINDER = intent.getStringExtra("car_cylinder");
        String s_CAR_HORSE_POWER = intent.getStringExtra("car_horse_power");

        New_String_product_img_1 = intent.getStringExtra("car_img");

        tv_product_id.setText("" + s_ID);
        tv_product_name.setText("" + s_NAME);
        tv_product_price.setText("" + s_PRICE);
//        tv_car_cylinder.setText("" + s_CYLINDER);
//        tv_car_horse_power.setText("" + s_HORSE_POWER);
        tv_car_seat_number.setText("" +s_CAR_SEAT_NUMBER);
        tv_car_cylinder.setText("" +s_CAR_CYLINDER);
        tv_car_horse_power.setText("" +s_CAR_HORSE_POWER);

        /////////////////////////////////////

        imgview1 = (ImageView) findViewById(R.id.image1);
        Picasso.with(Show_Detail_And_Rent.this)
                //.load(""+MyArrList.get(position).get("product_img"))
                // .load("http://thaiprojectapp.com/kie/img/a1.jpg")
                .load("" + s_IMAGE)
                // .placeholder(R.drawable.placeholder)   // optional
                //.error(R.drawable.error)      // optional
                //.resize(400,400)                        // optional
                .into(imgview1);

        //Button
        Button goto_DialogActivity_1 = (Button) findViewById(R.id.goto_DialogActivity_1);
        goto_DialogActivity_1.setOnClickListener (new View.OnClickListener() {

            public void onClick(View V) {
                Intent intent = new Intent(Show_Detail_And_Rent.this,DialogActivity_1.class);
                // Intent intent = new Intent(Show_Category.this,Page_Detail.class);
                // intent.putExtra("product_id", position);

                //intent.putExtra("category_id", s_category_id);
                intent.putExtra("detail_id", tv_product_id.getText().toString().trim());
                intent.putExtra("car_detail", tv_product_name.getText().toString().trim());
                intent.putExtra("car_price", tv_product_price.getText().toString().trim());
                intent.putExtra("car_img", New_String_product_img_1);

//                intent.putExtra("car_cylinder", tv_car_cylinder.getText().toString().trim());
//                intent.putExtra("car_horse_power", tv_car_horse_power.getText().toString().trim());
                intent.putExtra("car_seat_number", tv_car_seat_number.getText().toString().trim());
                intent.putExtra("car_cylinder", tv_car_cylinder.getText().toString().trim());
                intent.putExtra("car_horse_power", tv_car_horse_power.getText().toString().trim());



                startActivity(intent);

            }

        });//Button


    }
}
