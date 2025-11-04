package com.wifiled.ipixels.core.text.process;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ITextProcess.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/TextData;", "", "isMoreThan", "", "textDataArr", "", "<init>", "(Z[B)V", "()Z", "getTextDataArr", "()[B", "equals", "other", "hashCode", "", "component1", "component2", "copy", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextData {
    private final boolean isMoreThan;
    private final byte[] textDataArr;

    public static /* synthetic */ TextData copy$default(TextData textData, boolean z, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            z = textData.isMoreThan;
        }
        if ((i & 2) != 0) {
            bArr = textData.textDataArr;
        }
        return textData.copy(z, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsMoreThan() {
        return this.isMoreThan;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getTextDataArr() {
        return this.textDataArr;
    }

    public final TextData copy(boolean isMoreThan, byte[] textDataArr) {
        Intrinsics.checkNotNullParameter(textDataArr, "textDataArr");
        return new TextData(isMoreThan, textDataArr);
    }

    public String toString() {
        return "TextData(isMoreThan=" + this.isMoreThan + ", textDataArr=" + Arrays.toString(this.textDataArr) + ")";
    }

    public TextData(boolean z, byte[] textDataArr) {
        Intrinsics.checkNotNullParameter(textDataArr, "textDataArr");
        this.isMoreThan = z;
        this.textDataArr = textDataArr;
    }

    public final byte[] getTextDataArr() {
        return this.textDataArr;
    }

    public final boolean isMoreThan() {
        return this.isMoreThan;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.wifiled.ipixels.core.text.process.TextData");
        TextData textData = (TextData) other;
        return this.isMoreThan == textData.isMoreThan && Arrays.equals(this.textDataArr, textData.textDataArr);
    }

    public int hashCode() {
        return (Boolean.hashCode(this.isMoreThan) * 31) + Arrays.hashCode(this.textDataArr);
    }
}
