package com.example.nursetrack;


import android.util.ArraySet;

import com.example.nursetrack.Model.DriverGeoModel;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Common {
    public static final String DRIVERS_LOCATION_REFRENCES = "PatientLocation";
    public static final String NURSE_LOCATION_REFRENCES = "NurseLocation";
    public static Set<DriverGeoModel> driversFound =  new HashSet<DriverGeoModel>();
    public static HashMap<String, Marker> markerList = new HashMap<>();
}
