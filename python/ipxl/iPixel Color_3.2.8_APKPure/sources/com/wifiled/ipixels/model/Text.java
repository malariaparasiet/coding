package com.wifiled.ipixels.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: Text.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000e\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0015¨\u0006+"}, d2 = {"Lcom/wifiled/ipixels/model/Text;", "", "id", "", TextBundle.TEXT_ENTRY, "", "textSize", "typeFace", "rgb", "ledType", "bright", "<init>", "(ILjava/lang/String;ILjava/lang/String;III)V", "getId", "()I", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getTextSize", "setTextSize", "(I)V", "getTypeFace", "setTypeFace", "getRgb", "setRgb", "getLedType", "setLedType", "getBright", "setBright", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Text {
    private int bright;
    private final int id;
    private int ledType;
    private int rgb;
    private String text;
    private int textSize;
    private String typeFace;

    public static /* synthetic */ Text copy$default(Text text, int i, String str, int i2, String str2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = text.id;
        }
        if ((i6 & 2) != 0) {
            str = text.text;
        }
        if ((i6 & 4) != 0) {
            i2 = text.textSize;
        }
        if ((i6 & 8) != 0) {
            str2 = text.typeFace;
        }
        if ((i6 & 16) != 0) {
            i3 = text.rgb;
        }
        if ((i6 & 32) != 0) {
            i4 = text.ledType;
        }
        if ((i6 & 64) != 0) {
            i5 = text.bright;
        }
        int i7 = i4;
        int i8 = i5;
        int i9 = i3;
        int i10 = i2;
        return text.copy(i, str, i10, str2, i9, i7, i8);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTextSize() {
        return this.textSize;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTypeFace() {
        return this.typeFace;
    }

    /* renamed from: component5, reason: from getter */
    public final int getRgb() {
        return this.rgb;
    }

    /* renamed from: component6, reason: from getter */
    public final int getLedType() {
        return this.ledType;
    }

    /* renamed from: component7, reason: from getter */
    public final int getBright() {
        return this.bright;
    }

    public final Text copy(int id, String text, int textSize, String typeFace, int rgb, int ledType, int bright) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(typeFace, "typeFace");
        return new Text(id, text, textSize, typeFace, rgb, ledType, bright);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Text)) {
            return false;
        }
        Text text = (Text) other;
        return this.id == text.id && Intrinsics.areEqual(this.text, text.text) && this.textSize == text.textSize && Intrinsics.areEqual(this.typeFace, text.typeFace) && this.rgb == text.rgb && this.ledType == text.ledType && this.bright == text.bright;
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.id) * 31) + this.text.hashCode()) * 31) + Integer.hashCode(this.textSize)) * 31) + this.typeFace.hashCode()) * 31) + Integer.hashCode(this.rgb)) * 31) + Integer.hashCode(this.ledType)) * 31) + Integer.hashCode(this.bright);
    }

    public String toString() {
        return "Text(id=" + this.id + ", text=" + this.text + ", textSize=" + this.textSize + ", typeFace=" + this.typeFace + ", rgb=" + this.rgb + ", ledType=" + this.ledType + ", bright=" + this.bright + ")";
    }

    public Text(int i, String text, int i2, String typeFace, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(typeFace, "typeFace");
        this.id = i;
        this.text = text;
        this.textSize = i2;
        this.typeFace = typeFace;
        this.rgb = i3;
        this.ledType = i4;
        this.bright = i5;
    }

    public /* synthetic */ Text(int i, String str, int i2, String str2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, i2, str2, i3, i4, (i6 & 64) != 0 ? 100 : i5);
    }

    public final int getId() {
        return this.id;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final void setTextSize(int i) {
        this.textSize = i;
    }

    public final String getTypeFace() {
        return this.typeFace;
    }

    public final void setTypeFace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.typeFace = str;
    }

    public final int getRgb() {
        return this.rgb;
    }

    public final void setRgb(int i) {
        this.rgb = i;
    }

    public final int getLedType() {
        return this.ledType;
    }

    public final void setLedType(int i) {
        this.ledType = i;
    }

    public final int getBright() {
        return this.bright;
    }

    public final void setBright(int i) {
        this.bright = i;
    }
}
