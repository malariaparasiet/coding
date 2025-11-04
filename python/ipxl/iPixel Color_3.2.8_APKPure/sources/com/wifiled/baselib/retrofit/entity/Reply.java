package com.wifiled.baselib.retrofit.entity;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Reply.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ,\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/wifiled/baselib/retrofit/entity/Reply;", ExifInterface.GPS_DIRECTION_TRUE, "", NotificationCompat.CATEGORY_STATUS, "", "data", "<init>", "(Ljava/lang/Integer;Ljava/lang/Object;)V", "getStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/Object;)Lcom/wifiled/baselib/retrofit/entity/Reply;", "equals", "", "other", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Reply<T> {
    private final T data;
    private final Integer status;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Reply copy$default(Reply reply, Integer num, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            num = reply.status;
        }
        if ((i & 2) != 0) {
            obj = reply.data;
        }
        return reply.copy(num, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getStatus() {
        return this.status;
    }

    public final T component2() {
        return this.data;
    }

    public final Reply<T> copy(Integer status, T data) {
        return new Reply<>(status, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Reply)) {
            return false;
        }
        Reply reply = (Reply) other;
        return Intrinsics.areEqual(this.status, reply.status) && Intrinsics.areEqual(this.data, reply.data);
    }

    public int hashCode() {
        Integer num = this.status;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        T t = this.data;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "Reply(status=" + this.status + ", data=" + this.data + ")";
    }

    public Reply(Integer num, T t) {
        this.status = num;
        this.data = t;
    }

    public final T getData() {
        return this.data;
    }

    public final Integer getStatus() {
        return this.status;
    }
}
