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

import static android.R.attr.tag;


//OnMapReadyCallback
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    View marker_root_view;
    TextView tv_marker;

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

        setCustomMarkerView();
        mMap = googleMap;
        ArrayList<MarkerItem> markerList = new ArrayList();
        Resources res = getResources();

        LatLng latLng[] = new LatLng[] {
                new LatLng(41.138036, 69.308668) // 김병화 박물관 tag 01
                ,new LatLng(39.660730, 66.980343) // 비비하눔 사원 tag 02
                ,new LatLng(41.489135, 69.586206) // 치르치크 tag 00
        };

        for (int i = 0; i < latLng.length; i++) {
            //tmp = mMap.addMarker(new MarkerOptions().position(latLng[i]));
            int id_title;
            if (i < 10) {
                id_title = res.getIdentifier("title0"+i, "string", getPackageName());
                String title = res.getString(id_title);
                markerList.add(new MarkerItem(latLng[i], title));
                tmp = addMarker(markerList.get(i));
                tmp.setTag("0" + i);
            }
            else {
                int j = i / 10;
                id_title = res.getIdentifier("title"+j+(i%10), "string", getPackageName());
                String title = res.getString(id_title);
                markerList.add(new MarkerItem(latLng[i], title));
                tmp = addMarker(markerList.get(i));
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

    /*private void getSampleMarkerItems() {
        ArrayList<MarkerItem> sampleList = new ArrayList();


        sampleList.add(new MarkerItem(37.538523, 126.96568, 2500000));
        sampleList.add(new MarkerItem(37.527523, 126.96568, 100000));
        sampleList.add(new MarkerItem(37.549523, 126.96568, 15000));
        sampleList.add(new MarkerItem(37.538523, 126.95768, 5000));


        for (MarkerItem markerItem : sampleList) {
            addMarker(markerItem, false);
        }

    }*/

    private void setCustomMarkerView() {

        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker_layout, null);
        tv_marker = (TextView)marker_root_view.findViewById(R.id.tv_marker);
    }


    private Marker addMarker(MarkerItem markerItem) {


        LatLng position = markerItem.getLatLng();
        String title = markerItem.getName();

        tv_marker.setText(title);


        tv_marker.setBackgroundResource(R.drawable.ic_marker_phone);
        tv_marker.setTextColor(Color.BLACK);


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(title);
        markerOptions.position(position);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view)));


        return mMap.addMarker(markerOptions);

    }

    // View를 Bitmap으로 변환
    private Bitmap createDrawableFromView(Context context, View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


}
