package com.wifiled.ipixels.ui.channel;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CSelState.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/CSelState;", "Ljava/io/Serializable;", "bSelAll", "", "bHasSel", "<init>", "(ZZ)V", "getBSelAll", "()Z", "setBSelAll", "(Z)V", "getBHasSel", "setBHasSel", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CSelState implements Serializable {
    private boolean bHasSel;
    private boolean bSelAll;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CSelState() {
        /*
            r3 = this;
            r0 = 3
            r1 = 0
            r2 = 0
            r3.<init>(r2, r2, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.channel.CSelState.<init>():void");
    }

    public static /* synthetic */ CSelState copy$default(CSelState cSelState, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = cSelState.bSelAll;
        }
        if ((i & 2) != 0) {
            z2 = cSelState.bHasSel;
        }
        return cSelState.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getBSelAll() {
        return this.bSelAll;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getBHasSel() {
        return this.bHasSel;
    }

    public final CSelState copy(boolean bSelAll, boolean bHasSel) {
        return new CSelState(bSelAll, bHasSel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CSelState)) {
            return false;
        }
        CSelState cSelState = (CSelState) other;
        return this.bSelAll == cSelState.bSelAll && this.bHasSel == cSelState.bHasSel;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.bSelAll) * 31) + Boolean.hashCode(this.bHasSel);
    }

    public String toString() {
        return "CSelState(bSelAll=" + this.bSelAll + ", bHasSel=" + this.bHasSel + ")";
    }

    public CSelState(boolean z, boolean z2) {
        this.bSelAll = z;
        this.bHasSel = z2;
    }

    public /* synthetic */ CSelState(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getBHasSel() {
        return this.bHasSel;
    }

    public final boolean getBSelAll() {
        return this.bSelAll;
    }

    public final void setBHasSel(boolean z) {
        this.bHasSel = z;
    }

    public final void setBSelAll(boolean z) {
        this.bSelAll = z;
    }
}
