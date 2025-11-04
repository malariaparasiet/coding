package com.wifiled.ipixels.model;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Image.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/model/Image;", "", "id", "", ImagesContract.URL, "", "bgr_url", "ledType", "bright", "<init>", "(ILjava/lang/String;Ljava/lang/String;II)V", "getId", "()I", "getUrl", "()Ljava/lang/String;", "getBgr_url", "getLedType", "getBright", "setBright", "(I)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Image {
    private final String bgr_url;
    private int bright;
    private final int id;
    private final int ledType;
    private final String url;

    public static /* synthetic */ Image copy$default(Image image, int i, String str, String str2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = image.id;
        }
        if ((i4 & 2) != 0) {
            str = image.url;
        }
        if ((i4 & 4) != 0) {
            str2 = image.bgr_url;
        }
        if ((i4 & 8) != 0) {
            i2 = image.ledType;
        }
        if ((i4 & 16) != 0) {
            i3 = image.bright;
        }
        int i5 = i3;
        String str3 = str2;
        return image.copy(i, str, str3, i2, i5);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component3, reason: from getter */
    public final String getBgr_url() {
        return this.bgr_url;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLedType() {
        return this.ledType;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBright() {
        return this.bright;
    }

    public final Image copy(int id, String url, String bgr_url, int ledType, int bright) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(bgr_url, "bgr_url");
        return new Image(id, url, bgr_url, ledType, bright);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Image)) {
            return false;
        }
        Image image = (Image) other;
        return this.id == image.id && Intrinsics.areEqual(this.url, image.url) && Intrinsics.areEqual(this.bgr_url, image.bgr_url) && this.ledType == image.ledType && this.bright == image.bright;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.id) * 31) + this.url.hashCode()) * 31) + this.bgr_url.hashCode()) * 31) + Integer.hashCode(this.ledType)) * 31) + Integer.hashCode(this.bright);
    }

    public String toString() {
        return "Image(id=" + this.id + ", url=" + this.url + ", bgr_url=" + this.bgr_url + ", ledType=" + this.ledType + ", bright=" + this.bright + ")";
    }

    public Image(int i, String url, String bgr_url, int i2, int i3) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(bgr_url, "bgr_url");
        this.id = i;
        this.url = url;
        this.bgr_url = bgr_url;
        this.ledType = i2;
        this.bright = i3;
    }

    public /* synthetic */ Image(int i, String str, String str2, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, i2, (i4 & 16) != 0 ? 100 : i3);
    }

    public final String getBgr_url() {
        return this.bgr_url;
    }

    public final int getBright() {
        return this.bright;
    }

    public final int getId() {
        return this.id;
    }

    public final int getLedType() {
        return this.ledType;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setBright(int i) {
        this.bright = i;
    }
}
