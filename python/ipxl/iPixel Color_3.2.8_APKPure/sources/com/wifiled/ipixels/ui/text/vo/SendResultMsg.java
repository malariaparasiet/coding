package com.wifiled.ipixels.ui.text.vo;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventbusMsg.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/SendResultMsg;", "", "byteArray", "", "<init>", "([B)V", "getByteArray", "()[B", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SendResultMsg {
    private final byte[] byteArray;

    public static /* synthetic */ SendResultMsg copy$default(SendResultMsg sendResultMsg, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = sendResultMsg.byteArray;
        }
        return sendResultMsg.copy(bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getByteArray() {
        return this.byteArray;
    }

    public final SendResultMsg copy(byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        return new SendResultMsg(byteArray);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SendResultMsg) && Intrinsics.areEqual(this.byteArray, ((SendResultMsg) other).byteArray);
    }

    public int hashCode() {
        return Arrays.hashCode(this.byteArray);
    }

    public String toString() {
        return "SendResultMsg(byteArray=" + Arrays.toString(this.byteArray) + ")";
    }

    public SendResultMsg(byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.byteArray = byteArray;
    }

    public final byte[] getByteArray() {
        return this.byteArray;
    }
}
