package com.wifiled.ipixels.ui.imgtxt;

import kotlin.Metadata;

/* compiled from: TextAnimationEffectBean.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectBean;", "", "resourceId", "", "isSelect", "", "<init>", "(IZ)V", "getResourceId", "()I", "setResourceId", "(I)V", "()Z", "setSelect", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextAnimationEffectBean {
    private boolean isSelect;
    private int resourceId;

    public static /* synthetic */ TextAnimationEffectBean copy$default(TextAnimationEffectBean textAnimationEffectBean, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = textAnimationEffectBean.resourceId;
        }
        if ((i2 & 2) != 0) {
            z = textAnimationEffectBean.isSelect;
        }
        return textAnimationEffectBean.copy(i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getResourceId() {
        return this.resourceId;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final TextAnimationEffectBean copy(int resourceId, boolean isSelect) {
        return new TextAnimationEffectBean(resourceId, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextAnimationEffectBean)) {
            return false;
        }
        TextAnimationEffectBean textAnimationEffectBean = (TextAnimationEffectBean) other;
        return this.resourceId == textAnimationEffectBean.resourceId && this.isSelect == textAnimationEffectBean.isSelect;
    }

    public int hashCode() {
        return (Integer.hashCode(this.resourceId) * 31) + Boolean.hashCode(this.isSelect);
    }

    public String toString() {
        return "TextAnimationEffectBean(resourceId=" + this.resourceId + ", isSelect=" + this.isSelect + ")";
    }

    public TextAnimationEffectBean(int i, boolean z) {
        this.resourceId = i;
        this.isSelect = z;
    }

    public final int getResourceId() {
        return this.resourceId;
    }

    public final void setResourceId(int i) {
        this.resourceId = i;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
