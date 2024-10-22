package com.example.nursecall;

import com.example.nursecall.Model.DriverGeoModel;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Common {
    public static final String DRIVERS_LOCATION_REFRENCES = "NurseLocation";
    public static final String NURSE_LOCATION_REFRENCES = "PatientLocation";
    public static Set<DriverGeoModel> driversFound =  new HashSet<DriverGeoModel>();
    public static HashMap<String, Marker> markerList = new HashMap<>();
}
