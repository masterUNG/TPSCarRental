package com.projectshoponline.app_rent_car;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble;
    private int time = 0;
    private double[] pointLatDoubles = new double[2];
    private double[] pointLngDoubles = new double[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        Setup Location
        setupLocation();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } //Main Method


    @Override
    protected void onResume() {
        super.onResume();

        try {

            while (latADouble == 0.0) {

                myFindLocation();
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void myFindLocation() {

//        for Network
        Location networkLocation = findLocation(LocationManager.NETWORK_PROVIDER);
        if(networkLocation != null){
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();
        }

//        for GPS
        Location gpsLocation = findLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();
        }
        Log.d("31JulyV2","Lat ==>"+latADouble);
        Log.d("31JulyV2","Lng ==>"+lngADouble);
    }

    @Override
    protected void onStop() { //หยุด service Location
        super.onStop();

        locationManager.removeUpdates(locationListener);

    }

    public Location findLocation(String providerString) {
        Location location = null;

        if (locationManager.isProviderEnabled(providerString)) {//ทำงานได้ต่อเมื่อมือถือมีการ์ด GPS หรือต่อ net ได้
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(providerString, 1000, 10, locationListener); // minTime = หาพิกัดทุก 1วินาที
            location = locationManager.getLastKnownLocation(providerString);
        }


        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { //ถ้ามีการเปิดปิดปุ่มว่าแชร์ พิกัด

        }

        @Override
        public void onProviderEnabled(String provider) { // อับสัยยาณ

        }

        @Override
        public void onProviderDisabled(String provider) { //เจอดาวเทียมทำอะไร ไม่เจอทำอะไร

        }
    };

    private void setupLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //Fine กินพลังงานเยอะ เพราะค้นหาละเอียด เพี้ยนได้ 300เมก;
        criteria.setAltitudeRequired(false); //ไม่ค้นหาแกน Z ระดับน้ำทะเล
        criteria.setBearingRequired(false); //ไม่ค้นหาแกน Z ระดับน้ำทะเล
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (latADouble == 0.0) {
            myFindLocation();
        } else {

            LatLng centrLatLng = new LatLng(latADouble, lngADouble);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centrLatLng, 16)); //16 คือขนาด zoom

        }
        mapController();

        //Clear Controller
        clearController();

//        confirmController
        confirmController();


    } // onMapReady

    private void confirmController() {
        Button button = findViewById(R.id.btnConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (time >= 2) {
//                    confirm
                    showAlert();

                } else {
                    Toast.makeText(MapsActivity.this, "Please Point Start And End!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_end);
        builder.setTitle("Confirm Point ?");
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MapsActivity.this, DialogActivity_1.class);
                intent.putExtra("Lat",pointLatDoubles);
                intent.putExtra("Long", pointLngDoubles);
                setResult(500,intent);
             finish();
            dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();

    }


    private void clearController() {
        Button button = findViewById(R.id.btnClear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 0;
                mMap.clear();
            }
        });
    }

    private void mapController() {

        final int[] ints = new int[]{R.drawable.ic_action_start, R.drawable.ic_action_end};

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                if (time <= 1) {
                    createMarker(latLng, ints[time]);


                }


                time+=1;
            }
        });
    }

    private void createMarker(LatLng latLng, int intIcon) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(intIcon));
        mMap.addMarker(markerOptions);

        pointLatDoubles[time] = latLng.latitude;
        pointLngDoubles[time] = latLng.longitude;
    }

}
