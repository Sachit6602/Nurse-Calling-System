package com.example.nursetrack.Callback;

import com.example.nursetrack.Model.DriverGeoModel;

public interface IFirebaseDriverInfoListener {
    void onDriverInfoLoadSuccess(DriverGeoModel driverGeoModel);
}
