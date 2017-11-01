package com.example.song.global;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Song on 2017-11-01.
 */

public class MarkerItem {

    LatLng latLng;
    String name;
    public MarkerItem(LatLng latLng, String name) {
        this.latLng = latLng;
        this.name = name;
    }
    public void setLatLng(LatLng latLng) { this.latLng = latLng; }
    public LatLng getLatLng() { return latLng; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
