package com.projectshoponline.app_rent_car;

import android.app.DatePickerDialog;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class DataRentCarFragment extends Fragment{

    private int yearCurrentAnInt, monthCurrentAnInt, dayCurrentAnInt;
    private int yearStartAnInt, monthStartAnInt, dayStartAnInt;
    private int yearEndAnInt, monthEndAnInt, dayEndAnInt;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Current Time
        getCurrentTime();

//        Create Toolbar
        createToolbar();

//        Setstart Controller
        setStartController();

    }// Main Method

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        yearCurrentAnInt = calendar.get(Calendar.YEAR);
        monthCurrentAnInt = calendar.get(Calendar.MONTH);
        dayCurrentAnInt = calendar.get(Calendar.DAY_OF_MONTH);
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
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarDataRentCar);
        ((ReportMain2Activity)getActivity()).setSupportActionBar(toolbar);
        ((ReportMain2Activity) getActivity()).getSupportActionBar().setTitle(getString(R.string.data_rent_car));
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_rent_car, container, false);
        return view;
    }
}
