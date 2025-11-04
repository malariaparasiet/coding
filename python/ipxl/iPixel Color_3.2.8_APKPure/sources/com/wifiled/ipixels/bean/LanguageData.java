package com.wifiled.ipixels.bean;

import com.wifiled.baselib.app.Constance;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LanguageData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/bean/LanguageData;", "", Constance.SP.LANGUAGE, "", "languageCode", "isSelect", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "getLanguageCode", "setLanguageCode", "()Z", "setSelect", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class LanguageData {
    private boolean isSelect;
    private String language;
    private String languageCode;

    public static /* synthetic */ LanguageData copy$default(LanguageData languageData, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = languageData.language;
        }
        if ((i & 2) != 0) {
            str2 = languageData.languageCode;
        }
        if ((i & 4) != 0) {
            z = languageData.isSelect;
        }
        return languageData.copy(str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLanguageCode() {
        return this.languageCode;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final LanguageData copy(String language, String languageCode, boolean isSelect) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        return new LanguageData(language, languageCode, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LanguageData)) {
            return false;
        }
        LanguageData languageData = (LanguageData) other;
        return Intrinsics.areEqual(this.language, languageData.language) && Intrinsics.areEqual(this.languageCode, languageData.languageCode) && this.isSelect == languageData.isSelect;
    }

    public int hashCode() {
        return (((this.language.hashCode() * 31) + this.languageCode.hashCode()) * 31) + Boolean.hashCode(this.isSelect);
    }

    public String toString() {
        return "LanguageData(language=" + this.language + ", languageCode=" + this.languageCode + ", isSelect=" + this.isSelect + ")";
    }

    public LanguageData(String language, String languageCode, boolean z) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        this.language = language;
        this.languageCode = languageCode;
        this.isSelect = z;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.language = str;
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final void setLanguageCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.languageCode = str;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
