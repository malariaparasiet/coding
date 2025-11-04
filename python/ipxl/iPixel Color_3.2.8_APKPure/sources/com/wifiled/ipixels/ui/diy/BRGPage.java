package com.wifiled.ipixels.ui.diy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BRGPage.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/BRGPage;", "Ljava/io/Serializable;", "bytes", "", TypedValues.Custom.S_COLOR, "", "<init>", "([B[I)V", "getBytes", "()[B", "getColor", "()[I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BRGPage implements Serializable {
    private final byte[] bytes;
    private final int[] color;

    public static /* synthetic */ BRGPage copy$default(BRGPage bRGPage, byte[] bArr, int[] iArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = bRGPage.bytes;
        }
        if ((i & 2) != 0) {
            iArr = bRGPage.color;
        }
        return bRGPage.copy(bArr, iArr);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getBytes() {
        return this.bytes;
    }

    /* renamed from: component2, reason: from getter */
    public final int[] getColor() {
        return this.color;
    }

    public final BRGPage copy(byte[] bytes, int[] color) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(color, "color");
        return new BRGPage(bytes, color);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BRGPage)) {
            return false;
        }
        BRGPage bRGPage = (BRGPage) other;
        return Intrinsics.areEqual(this.bytes, bRGPage.bytes) && Intrinsics.areEqual(this.color, bRGPage.color);
    }

    public int hashCode() {
        return (Arrays.hashCode(this.bytes) * 31) + Arrays.hashCode(this.color);
    }

    public String toString() {
        return "BRGPage(bytes=" + Arrays.toString(this.bytes) + ", color=" + Arrays.toString(this.color) + ")";
    }

    public BRGPage(byte[] bytes, int[] color) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(color, "color");
        this.bytes = bytes;
        this.color = color;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final int[] getColor() {
        return this.color;
    }
}
