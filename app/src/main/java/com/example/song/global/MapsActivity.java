package com.example.song.global;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;


//OnMapReadyCallback
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener/*, GoogleMap.OnMapClickListener*/ {

    private GoogleMap mMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        /*mMap.setOnMapClickListener(this);*/
    }

/*
    public void onMapClick(LatLng point) {
        Point pt = mMap.getProjection().toScreenLocation(point);

        LatLng latLng = mMap.getProjection().fromScreenLocation(pt);
        mMap.addMarker(new MarkerOptions().position(latLng).title("new Marker."));

    }*/

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        /*LatLng seoul = new LatLng(37.555819, 126.969687);
        mMap.addMarker(new MarkerOptions().position(seoul).title("Marker is Seoul."));

        LatLng kazakhstan = new LatLng(48.134547, 67.975961);
        mMap.addMarker(new MarkerOptions().position(kazakhstan).title("Marker is Kazakhstan."));
        LatLng uzbekistan = new LatLng(41.983430, 63.662470);
        mMap.addMarker(new MarkerOptions().position(uzbekistan).title("Marker is Uzbekistan."));

        mMap.addPolyline(new PolylineOptions().add(
                seoul,
                new LatLng(43.119515, 131.900557),
                new LatLng(49.193168, 138.319412),
                new LatLng(59.354158, 123.530255),
                new LatLng(51.671816, 85.668485),
                kazakhstan,
                uzbekistan
        )
        .width(10)
        .color(Color.RED)
        ); // 폴리라인 전체 지도*/

        LatLng uzbekistan = new LatLng(39.696154, 66.990798); // 사마르칸트 공항 주소
        mMap.addMarker(new MarkerOptions().position(uzbekistan).title("Samarkand International Airport."));
        LatLng latLng = new LatLng(39.684296, 66.985279); // 인근 레스토랑
        mMap.addMarker(new MarkerOptions().position(latLng).title("Grand Sultan"));

        LatLng cheonan = new LatLng(36.809077, 127.146557);
        mMap.addMarker(new MarkerOptions().position(cheonan).title("Marker is Cheonan."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheonan, 8)); // 줌 : 숫자가 커질수록 확대

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker m) {
                turnTableNavigation(m);
                return true;
            }
        });

    }

    public void turnTableNavigation(Marker m)
    {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+m.getPosition().latitude+","+m.getPosition().longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public boolean onMarkerClick(Marker m) {
        turnTableNavigation(m);
        return true;
    }
}
