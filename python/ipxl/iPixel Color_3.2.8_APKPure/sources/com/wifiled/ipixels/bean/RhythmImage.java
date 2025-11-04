package com.wifiled.ipixels.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RhythmImage.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/wifiled/ipixels/bean/RhythmImage;", "", "imageRes", "", "gif", "", "gifPath", "", "<init>", "(IZLjava/lang/String;)V", "getImageRes", "()I", "getGif", "()Z", "getGifPath", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class RhythmImage {
    private final boolean gif;
    private final String gifPath;
    private final int imageRes;

    public RhythmImage() {
        this(0, false, null, 7, null);
    }

    public static /* synthetic */ RhythmImage copy$default(RhythmImage rhythmImage, int i, boolean z, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = rhythmImage.imageRes;
        }
        if ((i2 & 2) != 0) {
            z = rhythmImage.gif;
        }
        if ((i2 & 4) != 0) {
            str = rhythmImage.gifPath;
        }
        return rhythmImage.copy(i, z, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getImageRes() {
        return this.imageRes;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getGif() {
        return this.gif;
    }

    /* renamed from: component3, reason: from getter */
    public final String getGifPath() {
        return this.gifPath;
    }

    public final RhythmImage copy(int imageRes, boolean gif, String gifPath) {
        Intrinsics.checkNotNullParameter(gifPath, "gifPath");
        return new RhythmImage(imageRes, gif, gifPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RhythmImage)) {
            return false;
        }
        RhythmImage rhythmImage = (RhythmImage) other;
        return this.imageRes == rhythmImage.imageRes && this.gif == rhythmImage.gif && Intrinsics.areEqual(this.gifPath, rhythmImage.gifPath);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.imageRes) * 31) + Boolean.hashCode(this.gif)) * 31) + this.gifPath.hashCode();
    }

    public String toString() {
        return "RhythmImage(imageRes=" + this.imageRes + ", gif=" + this.gif + ", gifPath=" + this.gifPath + ")";
    }

    public RhythmImage(int i, boolean z, String gifPath) {
        Intrinsics.checkNotNullParameter(gifPath, "gifPath");
        this.imageRes = i;
        this.gif = z;
        this.gifPath = gifPath;
    }

    public /* synthetic */ RhythmImage(int i, boolean z, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? "" : str);
    }

    public final boolean getGif() {
        return this.gif;
    }

    public final String getGifPath() {
        return this.gifPath;
    }

    public final int getImageRes() {
        return this.imageRes;
    }
}
