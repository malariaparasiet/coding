package com.wifiled.ipixels.ui.subzone;

import kotlin.Metadata;

/* compiled from: TextBorderData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/TextBorderData;", "", "borderRes", "", "isSelect", "", "<init>", "(IZ)V", "getBorderRes", "()I", "setBorderRes", "(I)V", "()Z", "setSelect", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextBorderData {
    private int borderRes;
    private boolean isSelect;

    public static /* synthetic */ TextBorderData copy$default(TextBorderData textBorderData, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = textBorderData.borderRes;
        }
        if ((i2 & 2) != 0) {
            z = textBorderData.isSelect;
        }
        return textBorderData.copy(i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getBorderRes() {
        return this.borderRes;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final TextBorderData copy(int borderRes, boolean isSelect) {
        return new TextBorderData(borderRes, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextBorderData)) {
            return false;
        }
        TextBorderData textBorderData = (TextBorderData) other;
        return this.borderRes == textBorderData.borderRes && this.isSelect == textBorderData.isSelect;
    }

    public int hashCode() {
        return (Integer.hashCode(this.borderRes) * 31) + Boolean.hashCode(this.isSelect);
    }

    public String toString() {
        return "TextBorderData(borderRes=" + this.borderRes + ", isSelect=" + this.isSelect + ")";
    }

    public TextBorderData(int i, boolean z) {
        this.borderRes = i;
        this.isSelect = z;
    }

    public final int getBorderRes() {
        return this.borderRes;
    }

    public final void setBorderRes(int i) {
        this.borderRes = i;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
