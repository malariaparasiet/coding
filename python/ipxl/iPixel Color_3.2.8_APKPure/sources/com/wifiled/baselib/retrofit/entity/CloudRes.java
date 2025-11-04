package com.wifiled.baselib.retrofit.entity;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.wifiled.baselib.data.Data;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CloudRes.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/wifiled/baselib/retrofit/entity/CloudRes;", "", "resData", "Lcom/wifiled/baselib/data/Data;", NotificationCompat.CATEGORY_MESSAGE, "", NotificationCompat.CATEGORY_STATUS, "", "<init>", "(Lcom/wifiled/baselib/data/Data;Ljava/lang/String;I)V", "getResData", "()Lcom/wifiled/baselib/data/Data;", "getMsg", "()Ljava/lang/String;", "getStatus", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CloudRes {

    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    private final String msg;

    @SerializedName("data")
    private final Data resData;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private final int status;

    public static /* synthetic */ CloudRes copy$default(CloudRes cloudRes, Data data, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            data = cloudRes.resData;
        }
        if ((i2 & 2) != 0) {
            str = cloudRes.msg;
        }
        if ((i2 & 4) != 0) {
            i = cloudRes.status;
        }
        return cloudRes.copy(data, str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final Data getResData() {
        return this.resData;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* renamed from: component3, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final CloudRes copy(Data resData, String msg, int status) {
        Intrinsics.checkNotNullParameter(resData, "resData");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new CloudRes(resData, msg, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CloudRes)) {
            return false;
        }
        CloudRes cloudRes = (CloudRes) other;
        return Intrinsics.areEqual(this.resData, cloudRes.resData) && Intrinsics.areEqual(this.msg, cloudRes.msg) && this.status == cloudRes.status;
    }

    public int hashCode() {
        return (((this.resData.hashCode() * 31) + this.msg.hashCode()) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "CloudRes(resData=" + this.resData + ", msg=" + this.msg + ", status=" + this.status + ")";
    }

    public CloudRes(Data resData, String msg, int i) {
        Intrinsics.checkNotNullParameter(resData, "resData");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.resData = resData;
        this.msg = msg;
        this.status = i;
    }

    public final Data getResData() {
        return this.resData;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final int getStatus() {
        return this.status;
    }
}
