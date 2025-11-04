package com.wifiled.ipixels.core.text;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextBitmapData.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/core/text/TextBitmapData;", "", "type", "", "textStr", "", "bitmap", "Landroid/graphics/Bitmap;", "<init>", "(ILjava/lang/String;Landroid/graphics/Bitmap;)V", "getType", "()I", "setType", "(I)V", "getTextStr", "()Ljava/lang/String;", "setTextStr", "(Ljava/lang/String;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextBitmapData {
    private Bitmap bitmap;
    private String textStr;
    private int type;

    public TextBitmapData() {
        this(0, null, null, 7, null);
    }

    public static /* synthetic */ TextBitmapData copy$default(TextBitmapData textBitmapData, int i, String str, Bitmap bitmap, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = textBitmapData.type;
        }
        if ((i2 & 2) != 0) {
            str = textBitmapData.textStr;
        }
        if ((i2 & 4) != 0) {
            bitmap = textBitmapData.bitmap;
        }
        return textBitmapData.copy(i, str, bitmap);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTextStr() {
        return this.textStr;
    }

    /* renamed from: component3, reason: from getter */
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final TextBitmapData copy(int type, String textStr, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(textStr, "textStr");
        return new TextBitmapData(type, textStr, bitmap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextBitmapData)) {
            return false;
        }
        TextBitmapData textBitmapData = (TextBitmapData) other;
        return this.type == textBitmapData.type && Intrinsics.areEqual(this.textStr, textBitmapData.textStr) && Intrinsics.areEqual(this.bitmap, textBitmapData.bitmap);
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.type) * 31) + this.textStr.hashCode()) * 31;
        Bitmap bitmap = this.bitmap;
        return hashCode + (bitmap == null ? 0 : bitmap.hashCode());
    }

    public String toString() {
        return "TextBitmapData(type=" + this.type + ", textStr=" + this.textStr + ", bitmap=" + this.bitmap + ")";
    }

    public TextBitmapData(int i, String textStr, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(textStr, "textStr");
        this.type = i;
        this.textStr = textStr;
        this.bitmap = bitmap;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public /* synthetic */ TextBitmapData(int i, String str, Bitmap bitmap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? null : bitmap);
    }

    public final String getTextStr() {
        return this.textStr;
    }

    public final void setTextStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.textStr = str;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
