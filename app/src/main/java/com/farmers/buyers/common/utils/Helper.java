package com.farmers.buyers.common.utils;

import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Mohammad sajjad on 22-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class Helper {

    public static float getKmFromLatLong(Double lat1, Double lng1, Double lat2, Double lng2){
        Location loc1 = new Location("");
        loc1.setLatitude(lat1);
        loc1.setLongitude(lng1);
        Location loc2 = new Location("");
        loc2.setLatitude(lat2);
        loc2.setLongitude(lng2);
        float distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters/1000;
    }
}
