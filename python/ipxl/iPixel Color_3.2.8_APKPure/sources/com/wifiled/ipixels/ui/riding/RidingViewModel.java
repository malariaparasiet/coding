package com.wifiled.ipixels.ui.riding;

import android.content.Context;
import android.hardware.SensorEvent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.core.location.GnssStatusCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wifiled.baselib.utils.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: RidingViewModel.kt */
@Metadata(d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001a\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001b¨\u0006 "}, d2 = {"Lcom/wifiled/ipixels/ui/riding/RidingViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "locationLiveData", "Landroidx/lifecycle/MutableLiveData;", "Landroid/location/Location;", "getLocationLiveData", "()Landroidx/lifecycle/MutableLiveData;", "sensorLiveData", "Landroid/hardware/SensorEvent;", "getSensorLiveData", "setSensorLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "ramdom", "", "locationManager", "Landroid/location/LocationManager;", "init", "", "context", "Landroid/content/Context;", "initLocation", "locationListener", "Landroid/location/LocationListener;", "gnssStatusCallback", "com/wifiled/ipixels/ui/riding/RidingViewModel$gnssStatusCallback$1", "Lcom/wifiled/ipixels/ui/riding/RidingViewModel$gnssStatusCallback$1;", "getLocationCriteria", "Landroid/location/Criteria;", "onCleared", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RidingViewModel extends ViewModel {
    private static final String TAG = "RidingViewModel";
    private LocationManager locationManager;
    private MutableLiveData<SensorEvent> sensorLiveData;
    private final MutableLiveData<Location> locationLiveData = new MutableLiveData<>();
    private final int ramdom = Random.INSTANCE.nextInt(10);
    private final LocationListener locationListener = new LocationListener() { // from class: com.wifiled.ipixels.ui.riding.RidingViewModel$locationListener$1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            Intrinsics.checkNotNullParameter(location, "location");
            RidingViewModel.this.getLocationLiveData().setValue(location);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (status == 0) {
                LogUtils.logi("RidingViewModel>>>[onStatusChanged]: 当前GPS状态为服务区外状态", new Object[0]);
            } else if (status == 1) {
                LogUtils.logi("RidingViewModel>>>[onStatusChanged]: 当前GPS状态为暂停服务状态", new Object[0]);
            } else {
                if (status != 2) {
                    return;
                }
                LogUtils.logi("RidingViewModel>>>[onStatusChanged]: 当前GPS状态为可见状态", new Object[0]);
            }
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
            LocationManager locationManager;
            Intrinsics.checkNotNullParameter(provider, "provider");
            locationManager = RidingViewModel.this.locationManager;
            if (locationManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                locationManager = null;
            }
            if (locationManager.getLastKnownLocation(provider) != null) {
                LogUtils.logi("RidingViewModel>>>[onProviderEnabled]: ", new Object[0]);
            }
        }
    };
    private final RidingViewModel$gnssStatusCallback$1 gnssStatusCallback = new GnssStatusCompat.Callback() { // from class: com.wifiled.ipixels.ui.riding.RidingViewModel$gnssStatusCallback$1
        @Override // androidx.core.location.GnssStatusCompat.Callback
        public void onStarted() {
            super.onStarted();
            LogUtils.logi("RidingViewModel>>>[onStarted]: ", new Object[0]);
        }

        @Override // androidx.core.location.GnssStatusCompat.Callback
        public void onFirstFix(int ttffMillis) {
            super.onFirstFix(ttffMillis);
            LogUtils.logi("RidingViewModel>>>[onFirstFix]: " + ttffMillis, new Object[0]);
        }

        @Override // androidx.core.location.GnssStatusCompat.Callback
        public void onSatelliteStatusChanged(GnssStatusCompat status) {
            Intrinsics.checkNotNullParameter(status, "status");
            super.onSatelliteStatusChanged(status);
            LogUtils.logi("RidingViewModel>>>[onSatelliteStatusChanged]: " + status, new Object[0]);
        }

        @Override // androidx.core.location.GnssStatusCompat.Callback
        public void onStopped() {
            super.onStopped();
            LogUtils.logi("RidingViewModel>>>[onStopped]: ", new Object[0]);
        }
    };

    public final MutableLiveData<Location> getLocationLiveData() {
        return this.locationLiveData;
    }

    public final MutableLiveData<SensorEvent> getSensorLiveData() {
        return this.sensorLiveData;
    }

    public final void setSensorLiveData(MutableLiveData<SensorEvent> mutableLiveData) {
        this.sensorLiveData = mutableLiveData;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LogUtils.logi("RidingViewModel>>>[onLocationChanged]: " + this.ramdom, new Object[0]);
        Object systemService = context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
    }

    public final void initLocation(Context context) {
        LocationManager locationManager;
        Intrinsics.checkNotNullParameter(context, "context");
        LocationManager locationManager2 = this.locationManager;
        if (locationManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            locationManager2 = null;
        }
        if (LocationManagerCompat.isLocationEnabled(locationManager2)) {
            LocationManager locationManager3 = this.locationManager;
            if (locationManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                locationManager3 = null;
            }
            LogUtils.logi("RidingViewModel>>>[permissionGranted]: " + locationManager3.getBestProvider(getLocationCriteria(), true), new Object[0]);
            LocationManager locationManager4 = this.locationManager;
            if (locationManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                locationManager4 = null;
            }
            LocationManagerCompat.registerGnssStatusCallback(locationManager4, ContextCompat.getMainExecutor(context), this.gnssStatusCallback);
            LocationManager locationManager5 = this.locationManager;
            if (locationManager5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                locationManager = null;
            } else {
                locationManager = locationManager5;
            }
            locationManager.requestLocationUpdates("gps", 1L, 1.0f, this.locationListener);
        }
    }

    private final Criteria getLocationCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        LogUtils.logi("RidingViewModel>>>[onCleared]: ", new Object[0]);
        LocationManager locationManager = this.locationManager;
        LocationManager locationManager2 = null;
        if (locationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            locationManager = null;
        }
        LocationManagerCompat.unregisterGnssStatusCallback(locationManager, this.gnssStatusCallback);
        LocationManager locationManager3 = this.locationManager;
        if (locationManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
        } else {
            locationManager2 = locationManager3;
        }
        locationManager2.removeUpdates(this.locationListener);
    }
}
