package com.wifiled.ipixels.vo;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CategoryVO.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/vo/CategoryVO;", "", HintConstants.AUTOFILL_HINT_NAME, "", "resId", "", "<init>", "(Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getResId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CategoryVO {
    private final String name;
    private final int resId;

    public static /* synthetic */ CategoryVO copy$default(CategoryVO categoryVO, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = categoryVO.name;
        }
        if ((i2 & 2) != 0) {
            i = categoryVO.resId;
        }
        return categoryVO.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final int getResId() {
        return this.resId;
    }

    public final CategoryVO copy(String name, int resId) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new CategoryVO(name, resId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CategoryVO)) {
            return false;
        }
        CategoryVO categoryVO = (CategoryVO) other;
        return Intrinsics.areEqual(this.name, categoryVO.name) && this.resId == categoryVO.resId;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + Integer.hashCode(this.resId);
    }

    public String toString() {
        return "CategoryVO(name=" + this.name + ", resId=" + this.resId + ")";
    }

    public CategoryVO(String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.resId = i;
    }

    public final String getName() {
        return this.name;
    }

    public final int getResId() {
        return this.resId;
    }
}
