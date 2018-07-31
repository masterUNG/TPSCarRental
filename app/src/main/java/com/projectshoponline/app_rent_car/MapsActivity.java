package com.projectshoponline.app_rent_car;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
