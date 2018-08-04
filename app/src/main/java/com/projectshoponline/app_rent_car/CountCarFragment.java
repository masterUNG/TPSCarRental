package com.projectshoponline.app_rent_car;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CountCarFragment extends Fragment{


    private int yearCurrentAnInt, monthCurrentAnInt, dayCurrentAnInt;
    private String startString, endString;
    private boolean startABoolean = true, endABoolean = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Get Current Time
        getCurrentTime();

//        Create Toolbar
        createToolbar();

//        Setstart Controller
        setStartController();

//        SetEnd Controller
        setEndController();

//        Update Controller
        updateController();
    }

    private void updateController() {

        Button button = getView().findViewById(R.id.btnUpdate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startABoolean || endABoolean) {

                    Toast.makeText(getActivity(),
                            "Please Setup Start And End Date", Toast.LENGTH_SHORT).show();

                } else {
                    reportData();
                }

            }
        });

    }

    private void reportData() {
        Intent intent = new Intent(getActivity(), ListCountCarActivity.class);
        intent.putExtra("Start", startString);
        intent.putExtra("End", endString);
        startActivity(intent);
    }

    private void setEndController() {

        Button button = getView().findViewById(R.id.btnSetEnd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                showENd(year,month, dayOfMonth);
                                endABoolean = false;
                            }
                        },yearCurrentAnInt,monthCurrentAnInt,dayCurrentAnInt);
                datePickerDialog.show();
            }
        });

    }

    private void showENd(int year, int month, int dayOfMonth) {
        TextView textView = getView().findViewById(R.id.txtShowEnd);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        String endDate = dateFormat.format(calendar.getTime());
        textView.setText(endDate);
        endString = endDate;
    }

    private void setStartController() {


        Button button = getView().findViewById(R.id.btnSetStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                showStart(year,month, dayOfMonth);
                                startABoolean = false;
                            }
                        },yearCurrentAnInt,monthCurrentAnInt,dayCurrentAnInt);
                datePickerDialog.show();

            }
        });
    }

    private void showStart(int year, int month, int dayOfMonth) {
        TextView textView = getView().findViewById(R.id.txtShowStart);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String startDate = dateFormat.format(calendar.getTime());
        textView.setText(startDate);
        startString = startDate;
    }

    private void createToolbar() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarDataCountCar);
        ((ReportMain2Activity)getActivity()).setSupportActionBar(toolbar);
        ((ReportMain2Activity) getActivity()).getSupportActionBar().setTitle(getString(R.string.count_car));
        ((ReportMain2Activity) getActivity()).getSupportActionBar().setSubtitle("กรุฯาเลือกวันเริ่มต้น และวันสิ้นสุด");
        ((ReportMain2Activity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((ReportMain2Activity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        yearCurrentAnInt = calendar.get(Calendar.YEAR);
        monthCurrentAnInt = calendar.get(Calendar.MONTH);
        dayCurrentAnInt = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_car,container,false);
                return view;
    }
}
