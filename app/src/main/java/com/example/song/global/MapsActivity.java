package com.example.song.global;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import static android.R.attr.max;

//OnMapReadyCallback
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Marker tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Resources res = getResources();
        int i = 0;

        LatLng latLngMT[] = new LatLng[] {
                new LatLng(53.242399, 50.218703) // 사마라 0
                ,new LatLng(41.300570, 69.240908) // 타슈켄트 1
                ,new LatLng(43.232465, 76.845968) // 알마티 2
                ,new LatLng(45.242487, 77.972222) // 우슈토베 3
                ,new LatLng(44.844705, 65.484732) // 크질로르다 4
                ,new LatLng(42.878289, 74.556483) // 비슈케크 5
                ,new LatLng(55.007475, 82.957625) // 노보시비르스크 6
                ,new LatLng(56.007275, 92.892656) // 크라스노야르스크 7
                ,new LatLng(52.336070, 104.322924) // 이르쿠츠크 8
                ,new LatLng(52.059708, 113.474373) // 치타 9
                //,new LatLng(53.962738, 123.769936) // 아무르스카야 9.1
                //,new LatLng(49.102856, 131.108803) // 오블루치예 9.2
                ,new LatLng(48.516396, 135.105021) // 하바롭스크 10
                ,new LatLng(43.801456, 131.965403) // 우수리스크 11
                ,new LatLng(43.124270, 131.917708) // 블라디보스토크 12

                ,new LatLng(41.489135, 69.586206) // 치르치크 tag 13
        };
        LatLng amur = new LatLng(53.962738, 123.769936);
        LatLng oblu = new LatLng(49.102856, 131.108803);

        LatLng latLngTD[] = new LatLng[] {
                new LatLng(41.138036, 69.308668) // 김병화 박물관 tag 15
                ,new LatLng(39.660730, 66.980343) // 비비하눔 사원 tag 14
        };

        int max = latLngMT.length+latLngTD.length;

        for (i = 0; i < max; i++) {
            int id_title;

            if(i < latLngMT.length) {
                if (i < 10) {
                    id_title = res.getIdentifier("title0" + i, "string", getPackageName());
                    String title = res.getString(id_title);
                    tmp = mMap.addMarker(new MarkerOptions().position(latLngMT[i]).title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    tmp.setTag("0" + i);
                } else {
                    int j = i / 10;
                    id_title = res.getIdentifier("title" + j + (i % 10), "string", getPackageName());
                    String title = res.getString(id_title);
                    tmp = mMap.addMarker(new MarkerOptions().position(latLngMT[i]).title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    tmp.setTag(i);
                }
            }else {
                if (i < 10) {
                    id_title = res.getIdentifier("title0" + i, "string", getPackageName());
                    String title = res.getString(id_title);
                    tmp = mMap.addMarker(new MarkerOptions().position(latLngTD[i-latLngMT.length])
                            .title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    tmp.setTag("0" + i);
                } else {
                    int j = i / 10;
                    id_title = res.getIdentifier("title" + j + (i % 10), "string", getPackageName());
                    String title = res.getString(id_title);
                    tmp = mMap.addMarker(new MarkerOptions().position(latLngTD[i-latLngMT.length])
                            .title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    tmp.setTag(i);
                }
            }
        }


        PolylineOptions rectOptions = new PolylineOptions()
                .add(latLngMT[0])
                .add(latLngMT[4])
                .add(latLngMT[1])
                .add(latLngMT[13])
                .add(latLngMT[5])
                .add(latLngMT[2])
                .add(latLngMT[3])
                .add(latLngMT[6])
                .add(latLngMT[7])
                .add(latLngMT[8])
                .add(latLngMT[9])
                .add(amur)
                .add(oblu)
                .add(latLngMT[10])
                .add(latLngMT[11])
                .add(latLngMT[12]);
        Polyline polyline = mMap.addPolyline(rectOptions);
        polyline.setColor(0xffff0000);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngMT[5], 8)); // 줌 : 숫자가 커질수록 확대


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                intent.putExtra("it_tag", marker.getTag().toString());

                double i = marker.getPosition().latitude;
                double j = marker.getPosition().longitude;
                intent.putExtra("lat", i);
                intent.putExtra("lon", j);

                startActivity(intent);
            }
        });

        /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
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
        });*/
    }

    @Override
    public boolean onMarkerClick(Marker m) {
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void addMarker(LatLng[] latLngs, int i){

        Resources res = getResources();
        int max = latLngs.length+i;

        for (; i < max; i++) {

            int id_title;

            if (i < 10) {
                id_title = res.getIdentifier("title0"+i, "string", getPackageName());
                String title = res.getString(id_title);
                if(latLngs.length < i) {
                    mMap.addMarker(new MarkerOptions().position(latLngs[i])
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }
                else {
                    mMap.addMarker(new MarkerOptions().position(latLngs[i])
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                tmp.setTag("0" + i);
            }
            else {
                int j = i / 10;
                id_title = res.getIdentifier("title"+j+(i%10), "string", getPackageName());
                String title = res.getString(id_title);
                if(latLngs.length < i) {
                    mMap.addMarker(new MarkerOptions().position(latLngs[i])
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }
                else {
                    mMap.addMarker(new MarkerOptions().position(latLngs[i])
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                tmp.setTag(j + (i%10));

            }
        }
    }
}
