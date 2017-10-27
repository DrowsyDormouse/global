package com.example.song.global;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


//OnMapReadyCallback
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        LatLng Uzbekiston = new LatLng(42.015472, 64.020226); // 초기 줌 포인트

        LatLng latLng[] = new LatLng[]{new LatLng(41.138036, 69.308668) // 김병화 박물관
                                        ,new LatLng(39.660730, 66.980343) // 비비하눔 사원
                                        };
        mMap.addMarker(new MarkerOptions().position(latLng[0]).title(getString(R.string.title1)));
        mMap.addMarker(new MarkerOptions().position(latLng[1]).title(getString(R.string.title2)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Uzbekiston, 5)); // 줌 : 숫자가 커질수록 확대

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker) {
                turnTableNavigation(marker);
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
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    }


}
