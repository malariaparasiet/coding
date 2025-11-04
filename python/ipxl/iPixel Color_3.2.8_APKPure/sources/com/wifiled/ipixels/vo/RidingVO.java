package com.wifiled.ipixels.vo;

import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RidingVO.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/wifiled/ipixels/vo/RidingVO;", "", "resId", "", PlayerFinal.PLAYER_MODE, "data", "", "<init>", "(II[B)V", "getResId", "()I", "getMode", "getData", "()[B", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class RidingVO {
    private final byte[] data;
    private final int mode;
    private final int resId;

    public static /* synthetic */ RidingVO copy$default(RidingVO ridingVO, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = ridingVO.resId;
        }
        if ((i3 & 2) != 0) {
            i2 = ridingVO.mode;
        }
        if ((i3 & 4) != 0) {
            bArr = ridingVO.data;
        }
        return ridingVO.copy(i, i2, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final int getResId() {
        return this.resId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMode() {
        return this.mode;
    }

    /* renamed from: component3, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    public final RidingVO copy(int resId, int mode, byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new RidingVO(resId, mode, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RidingVO)) {
            return false;
        }
        RidingVO ridingVO = (RidingVO) other;
        return this.resId == ridingVO.resId && this.mode == ridingVO.mode && Intrinsics.areEqual(this.data, ridingVO.data);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.resId) * 31) + Integer.hashCode(this.mode)) * 31) + Arrays.hashCode(this.data);
    }

    public String toString() {
        return "RidingVO(resId=" + this.resId + ", mode=" + this.mode + ", data=" + Arrays.toString(this.data) + ")";
    }

    public RidingVO(int i, int i2, byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.resId = i;
        this.mode = i2;
        this.data = data;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getResId() {
        return this.resId;
    }
}
