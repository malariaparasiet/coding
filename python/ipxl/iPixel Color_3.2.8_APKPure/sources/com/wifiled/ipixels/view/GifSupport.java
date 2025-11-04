package com.wifiled.ipixels.view;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GIFView.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/wifiled/ipixels/view/GifSupport;", "", "widthNum", "", "heightNum", "seekGif", "", "pos", "data", "", "", "<init>", "(IIJILjava/util/List;)V", "getWidthNum", "()I", "getHeightNum", "getSeekGif", "()J", "getPos", "setPos", "(I)V", "getData", "()Ljava/util/List;", "next", "", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class GifSupport {
    private final List<int[]> data;
    private final int heightNum;
    private int pos;
    private final long seekGif;
    private final int widthNum;

    public static /* synthetic */ GifSupport copy$default(GifSupport gifSupport, int i, int i2, long j, int i3, List list, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = gifSupport.widthNum;
        }
        if ((i4 & 2) != 0) {
            i2 = gifSupport.heightNum;
        }
        if ((i4 & 4) != 0) {
            j = gifSupport.seekGif;
        }
        if ((i4 & 8) != 0) {
            i3 = gifSupport.pos;
        }
        if ((i4 & 16) != 0) {
            list = gifSupport.data;
        }
        long j2 = j;
        return gifSupport.copy(i, i2, j2, i3, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getWidthNum() {
        return this.widthNum;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHeightNum() {
        return this.heightNum;
    }

    /* renamed from: component3, reason: from getter */
    public final long getSeekGif() {
        return this.seekGif;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPos() {
        return this.pos;
    }

    public final List<int[]> component5() {
        return this.data;
    }

    public final GifSupport copy(int widthNum, int heightNum, long seekGif, int pos, List<int[]> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new GifSupport(widthNum, heightNum, seekGif, pos, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GifSupport)) {
            return false;
        }
        GifSupport gifSupport = (GifSupport) other;
        return this.widthNum == gifSupport.widthNum && this.heightNum == gifSupport.heightNum && this.seekGif == gifSupport.seekGif && this.pos == gifSupport.pos && Intrinsics.areEqual(this.data, gifSupport.data);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.widthNum) * 31) + Integer.hashCode(this.heightNum)) * 31) + Long.hashCode(this.seekGif)) * 31) + Integer.hashCode(this.pos)) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "GifSupport(widthNum=" + this.widthNum + ", heightNum=" + this.heightNum + ", seekGif=" + this.seekGif + ", pos=" + this.pos + ", data=" + this.data + ")";
    }

    public GifSupport(int i, int i2, long j, int i3, List<int[]> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.widthNum = i;
        this.heightNum = i2;
        this.seekGif = j;
        this.pos = i3;
        this.data = data;
    }

    public /* synthetic */ GifSupport(int i, int i2, long j, int i3, List list, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, j, (i4 & 8) != 0 ? 0 : i3, list);
    }

    public final int getWidthNum() {
        return this.widthNum;
    }

    public final int getHeightNum() {
        return this.heightNum;
    }

    public final long getSeekGif() {
        return this.seekGif;
    }

    public final int getPos() {
        return this.pos;
    }

    public final void setPos(int i) {
        this.pos = i;
    }

    public final List<int[]> getData() {
        return this.data;
    }

    public final void next() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.data.size()) {
            this.pos = 0;
        }
    }
}
