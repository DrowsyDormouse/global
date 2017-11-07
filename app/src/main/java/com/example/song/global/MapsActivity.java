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

                ,new LatLng(39.660730, 66.980343) // 비비하눔 사원 tag 13
                ,new LatLng(41.138036, 69.308668) // 김병화 박물관 tag 14
                ,new LatLng(41.489135, 69.586206) // 치르치크 tag 15
        };
        LatLng amur = new LatLng(53.962738, 123.769936);
        LatLng oblu = new LatLng(49.102856, 131.108803);

        for (int i = 0; i < latLng.length; i++) {

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
                .add(latLng[0])
                .add(latLng[4])
                .add(latLng[1])
                .add(latLng[15])
                .add(latLng[5])
                .add(latLng[2])
                .add(latLng[3])
                .add(latLng[6])
                .add(latLng[7])
                .add(latLng[8])
                .add(latLng[9])
                .add(amur)
                .add(oblu)
                .add(latLng[10])
                .add(latLng[11])
                .add(latLng[12]);
        Polyline polyline = mMap.addPolyline(rectOptions);
        polyline.setColor(0xffff0000);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[0], 10)); // 줌 : 숫자가 커질수록 확대

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
