package com.projectshoponline.app_rent_car;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_Car extends AppCompatActivity {

    TextView tvView2;
    ///////////////////////
    Button b_select_Image;

    Button upload;
    ImageView imageView;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST= 99;
    Bitmap bitmap;
    String myurl = "http://androidthai.in.th/nor/rentcar_json/app_add_car.php";


    TextView tvView1;
    //ImageView to display image selected
    // ImageView imageView;

    //edittext for getting the tags input
    //EditText editTextTags;

    Button buttonSubmit;


    TextView txt_place_id;
    TextView txt_user_post_id_im;

    String strMemberID = "";

    EditText text_1;
    // EditText text_2;
    EditText txtUsername ;
    EditText txtPassword ;
    EditText txtAddress ;
    EditText txtEmail ;
    EditText txtTel ;
    EditText txtCar_color ;
    EditText txtCar_brand;
    EditText txtCar_register_number;
    EditText txtCar_body_number;
    EditText txtPolicy_number;
    EditText txtCar_cylinder;
    EditText txtCar_horse_power;
    EditText txtCar_weight;
    EditText txtCar_fuel_tank;

    /////////////
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    public static ArrayList<String> Service_Id = null;
    public static ArrayList<String> Service_Id_4 = null;
    public static ArrayList<String> Service_Id_5 = null;

    public static ArrayList<String> Service1 = null;
    public static ArrayList<String> Service2 = null;
    public static ArrayList<String> Service3 = null;
    public static ArrayList<String> Service4 = null;
    public static ArrayList<String> Service5 = null;

    public static ArrayList<String> Service_new= null;

    String service_id,service_id_4,service_id_5;
    String S_New;


    Boolean chk_work_dept1 = false;

    EditText etView1;


    Button btnSubmit;
    ///////////////
    /////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car);
//
//        //*** Permission StrictMode
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
//        //*** Get Session Login
//        final UserHelper usrHelper = new UserHelper(this);
//
//        //*** Get Login Status
//        if(!usrHelper.getLoginStatus())
//        {
//            Intent newActivity = new Intent(Register_Text_Image.this, MainActivity.class);
//            startActivity(newActivity);
//        }
//
//        //*** Get Member ID from Session
//        strMemberID = usrHelper.getMemberID();
//


        // tvView1 = (TextView) findViewById(R.id.tvView1);
        // tvView2 = (TextView) findViewById(R.id.tvView2);
        // final Intent intent = getIntent();
        // String s_ID = intent.getStringExtra("text_1");
        // String s_Name = intent.getStringExtra("text_2");

        // tvView1.setText("" + s_ID);
        // tvView2.setText("" + s_Name);
        /////////////////////////////////////



        imageView = (ImageView) findViewById(R.id.imageView);

        // txt_place_id = (TextView) findViewById(R.id.txt_place_id);
        // txt_place_id.setText("" + s_ID);
        //txt_user_post_id_im = (TextView) findViewById(R.id.txt_user_post_id_im);
        // txt_user_post_id_im.setText("" + strMemberID);

        // text_1 = (EditText) findViewById(R.id.text_1);
        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        //txtTel = (EditText)findViewById(R.id.txtTel);
        txtCar_color = (EditText)findViewById(R.id.txtCar_color);
        txtCar_brand = (EditText)findViewById(R.id.txtCar_brand);
        txtCar_register_number = (EditText)findViewById(R.id.txtCar_register_number);
        txtCar_body_number = (EditText)findViewById(R.id.txtCar_body_number);
        txtPolicy_number = (EditText)findViewById(R.id.txtPolicy_number);
        txtCar_cylinder = (EditText)findViewById(R.id.txtCar_cylinder);
        txtCar_horse_power = (EditText)findViewById(R.id.txtCar_horse_power);
        txtCar_weight = (EditText)findViewById(R.id.txtCar_weight);
        txtCar_fuel_tank = (EditText)findViewById(R.id.txtCar_fuel_tank);




        b_select_Image = (Button) findViewById(R.id.b_select_Image);
        upload = (Button) findViewById(R.id.upload);
        imageView = (ImageView) findViewById(R.id.imageView);
        //  bitmap = BitmapFactory.decodeResource(getResources(), R.id.imageview);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showFileChooser();
//            }
//        });

        b_select_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // showFileChooser();

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //if the tags edittext is empty
                //we will throw input error


                // Category ID
                if (txtUsername.getText().toString().trim().isEmpty()) {
                    txtUsername.setError("Enter tags first");
                    txtUsername.requestFocus();
                    return;
                }

                // Brand ID
                if (txtCar_brand.getText().toString().trim().isEmpty()) {
                    txtCar_brand.setError("Enter tags first");
                    txtCar_brand.requestFocus();
                    return;
                }

                // Car Body Number
                if (txtCar_body_number.getText().toString().trim().isEmpty()) {
                    txtCar_body_number.setError("Enter tags first");
                    txtCar_body_number.requestFocus();
                    return;
                }

                // Car Register Number (เลขที่ประกันภัย)
                if (txtCar_register_number.getText().toString().trim().isEmpty()) {
                    txtCar_register_number.setError("Enter tags first");
                    txtCar_register_number.requestFocus();
                    return;
                }

                // Model ID
                if (txtPassword.getText().toString().trim().isEmpty()) {
                    txtPassword.setError("Enter tags first");
                    txtPassword.requestFocus();
                    return;
                }

                // Car Policy Number
                if (txtPolicy_number.getText().toString().trim().isEmpty()) {
                    txtPolicy_number.setError("Enter tags first");
                    txtPolicy_number.requestFocus();
                    return;
                }

                // Car Color
                if (txtCar_color.getText().toString().trim().isEmpty()) {
                    txtCar_color.setError("Enter tags first");
                    txtCar_color.requestFocus();
                    return;
                }

                // Car Price
                if (txtEmail.getText().toString().trim().isEmpty()) {
                    txtEmail.setError("Enter tags first");
                    txtEmail.requestFocus();
                    return;
                }



                // Car Description
//                if (txtAddress.getText().toString().trim().isEmpty()) {
//                    txtAddress.setError("Enter tags first");
//                    txtAddress.requestFocus();
//                    return;
//                }


                uploaduserimage();


            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission();
            }
        }


        // etView1 = (EditText) findViewById(R.id.etView1);
        //////////////////////////////////////////////////////////////////

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

//        //*** Button Next
//        final Button b_action_1 = (Button) findViewById(R.id.b_action_1);
//        b_action_1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                Intent intent = new Intent(Add_Car.this,Show_All.class);
//                intent.putExtra("text_1", etView1.getText().toString());
//
//                startActivity(intent);
//            }
//        });//*** Button Next



        Service_Id = new ArrayList<String>();
        Service_Id_4 = new ArrayList<String>();
        Service_Id_5 = new ArrayList<String>();
        Service1 = new ArrayList<String>();
        Service2 = new ArrayList<String>();
        Service3 = new ArrayList<String>();
        Service4 = new ArrayList<String>();
        Service5 = new ArrayList<String>();
        Service_new = new ArrayList<String>();


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // แสดงข้อความ id เมื่อเลือก
                // Toast.makeText(Add_Car.this,Service_Id.get(position),Toast.LENGTH_LONG).show();


                txtUsername.setText(Service_Id.get(position).toString());
                //et_place_type_id.setText(Service_Id.get(position).toString());
                // Intent intent = new Intent(this, Page_Helpdesk_Show_All.class);

                // Intent intent = new Intent();
                // Intent intent = new Intent(Page_Helpdesk_Show_All.this,Page_Helpdesk_Show_All_Detail.class);
                // intent.putExtra("text_1", Service_Id.get(position).toString());
                //intent.putExtra("text_1", etView1.getText().toString());
                //intent.putExtra("text_2", etView2.getText().toString());
                // startActivity(intent);



                if (spinner1.getSelectedItem() == "Please select Work Department") {
                    chk_work_dept1 = true;
                    //Do nothing.



                } else {
                    chk_work_dept1 = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Add_Car.this,Service_Id.get(position),Toast.LENGTH_LONG).show();



//                txtPassword.setText(Service4.get(position).toString()); //เอาข้อความ มาบันทึกลง Database
                txtPassword.setText(Service_Id_4.get(position).toString()); //ตัวเลข id มาบันทึกลงใน Database
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Add_Car.this,Service_Id.get(position),Toast.LENGTH_LONG).show();


//                txtCar_brand.setText(Service5.get(position).toString()); //เอาข้อความ มาบันทึกลง Database
                txtCar_brand.setText(Service_Id_5.get(position).toString()); //ตัวเลข id มาบันทึกลงใน Database
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        getData();
        getData2();
        getData3();




    }


    private void getData() {
        StringRequest stringRequest = new StringRequest(Page_Spinner_Category_Config.SPINNER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Service");
                    Log.e("onResponse: ", jsonObject + "");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        service_id =jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_ID);
                        Service_Id.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_ID));

                        Service1.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT1));
                        Service2.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT2));
//                        Service3.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT3));

                        spinner1.setAdapter(new ArrayAdapter<String>(Add_Car.this, android.R.layout.simple_dropdown_item_1line, Service1));
//                        spinner2.setAdapter(new ArrayAdapter<String>(Add_Car.this, android.R.layout.simple_dropdown_item_1line, Service3));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //  }


    } // # End of getData

    private void getData2() {
        StringRequest stringRequest = new StringRequest(Page_Spinner_Category_Config.MODEL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Service");
                    Log.e("onResponse: ", jsonObject + "");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        service_id_4 =jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT4_ID);
                        Service_Id_4.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT4_ID));


                        Service4.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT4));
                        spinner2.setAdapter(new ArrayAdapter<String>(Add_Car.this, android.R.layout.simple_dropdown_item_1line, Service4));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //  }


    } // # End of getData2

    private void getData3() {
        StringRequest stringRequest = new StringRequest(Page_Spinner_Category_Config.BRAND_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Service");
                    Log.e("onResponse: ", jsonObject + "");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        service_id_5 =jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT5_ID);
                        Service_Id_5.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT5_ID));


                        Service5.add(jsonObject1.getString(Page_Spinner_Category_Config.SPINNER_TEXT5));
                        spinner3.setAdapter(new ArrayAdapter<String>(Add_Car.this, android.R.layout.simple_dropdown_item_1line, Service5));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //  }


    } // # End of getData3

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Add_Car.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Add_Car.this, " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Add_Car.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Add_Car.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Add_Car.this, "Permission Granted Successfully! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Add_Car.this, "Permission Denied :( ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
        //   Uri filePath = data.getData();
        // try {
        //Getting the Bitmap from Gallery
        // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        // Toast.makeText(this, ""+bitmap, Toast.LENGTH_SHORT).show();
        //Setting the Bitmap to ImageView
        // imageView.setImageBitmap(bitmap);

        bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);



        //  } catch (IOException e) {
        //      e.printStackTrace();
        //  }
        // }
    }






    public void uploaduserimage(){

        //getting the tag from the edittext




        // final String place_id = txt_place_id.getText().toString().trim();
        // final String user_post_id_im = txt_user_post_id_im.getText().toString().trim();
        // final String s_text_1 = text_1.getText().toString().trim();


        RequestQueue requestQueue = Volley.newRequestQueue(Add_Car.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Myresponse",""+response);
                Toast.makeText(Add_Car.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Mysmart",""+error);
                Toast.makeText(Add_Car.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();

                String images = getStringImage(bitmap);
                Log.i("Mynewsam",""+images);
                param.put("image",images);


                param.put("dataname1", txtUsername.getText().toString().trim());
                param.put("dataname2", txtPassword.getText().toString().trim());
                param.put("dataname3", txtAddress.getText().toString().trim());
                param.put("dataname4", txtEmail.getText().toString().trim());
                param.put("dataname5", txtCar_brand.getText().toString().trim());
                param.put("dataname6", txtCar_register_number.getText().toString().trim());
                param.put("dataname7", txtCar_body_number.getText().toString().trim());
                param.put("dataname8", txtPolicy_number.getText().toString().trim());
                param.put("dataname9", txtCar_cylinder.getText().toString().trim());
                param.put("dataname10", txtCar_horse_power.getText().toString().trim());
                param.put("dataname11", txtCar_weight.getText().toString().trim());
                param.put("dataname12", txtCar_fuel_tank.getText().toString().trim());
                param.put("dataname13", txtCar_color.getText().toString().trim());
                // Goto Activity2
                Intent newActivity = new Intent(Add_Car.this,Page_Menu_Admin.class);
                startActivity(newActivity);
                //Toast.makeText(MyPost_Edit.this, "ok", Toast.LENGTH_SHORT).show();
                finish();
                return param;
            }
        };





        requestQueue.add(stringRequest);


    }


    public String getStringImage(Bitmap bitmap){
        Log.i("MyHitesh",""+bitmap);
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,30, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);


        return temp;
    }

}

