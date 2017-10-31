package com.example.song.global;

import android.content.Intent;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


//OnMapReadyCallback
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker tmp;

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

        LatLng latLng[] = new LatLng[] {
                new LatLng(41.138036, 69.308668) // 김병화 박물관 tag 01
                ,new LatLng(39.660730, 66.980343) // 비비하눔 사원 tag 02
                ,new LatLng(41.489135, 69.586206) // 치르치크 tag 00
        };

        for (int i = 0; i < latLng.length; i++) {
            tmp = mMap.addMarker(new MarkerOptions().position(latLng[i]));

            if (i < 10) {
                tmp.setTag("0" + i);
            }
            else {
                int j = i / 10;
                tmp.setTag(j + (i%10));
            }
        }


        PolylineOptions rectOptions = new PolylineOptions()
                .add(latLng[1])
                .add(latLng[0])
                .add(latLng[2]);
        Polyline polyline = mMap.addPolyline(rectOptions);
        polyline.setColor(0xffff0000);
        

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[0], 6)); // 줌 : 숫자가 커질수록 확대

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                intent.putExtra("it_tag", marker.getTag().toString());

                double i = marker.getPosition().latitude;
                double j = marker.getPosition().longitude;
                intent.putExtra("lat", i);
                intent.putExtra("lon", j);

                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker m) {
        return true;
    }

}
