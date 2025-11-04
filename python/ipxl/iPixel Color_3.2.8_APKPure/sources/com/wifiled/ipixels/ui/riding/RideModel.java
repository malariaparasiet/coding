package com.wifiled.ipixels.ui.riding;

import android.hardware.SensorEvent;
import androidx.core.app.NotificationCompat;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RideModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/ui/riding/RideModel;", "Ljava/io/Serializable;", NotificationCompat.CATEGORY_EVENT, "Landroid/hardware/SensorEvent;", "<init>", "(Landroid/hardware/SensorEvent;)V", "getEvent", "()Landroid/hardware/SensorEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class RideModel implements Serializable {
    private final SensorEvent event;

    public static /* synthetic */ RideModel copy$default(RideModel rideModel, SensorEvent sensorEvent, int i, Object obj) {
        if ((i & 1) != 0) {
            sensorEvent = rideModel.event;
        }
        return rideModel.copy(sensorEvent);
    }

    /* renamed from: component1, reason: from getter */
    public final SensorEvent getEvent() {
        return this.event;
    }

    public final RideModel copy(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return new RideModel(event);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RideModel) && Intrinsics.areEqual(this.event, ((RideModel) other).event);
    }

    public int hashCode() {
        return this.event.hashCode();
    }

    public String toString() {
        return "RideModel(event=" + this.event + ")";
    }

    public RideModel(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.event = event;
    }

    public final SensorEvent getEvent() {
        return this.event;
    }
}
