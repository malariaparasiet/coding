package com.wifiled.ipixels.ui.subzone;

import com.wifiled.ipixels.event.EventText;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubzoneTextData.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003J\t\u00100\u001a\u00020\fHÆ\u0003J\t\u00101\u001a\u00020\fHÆ\u0003J\t\u00102\u001a\u00020\fHÆ\u0003Jc\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fHÆ\u0001J\u0013\u00104\u001a\u00020\u00032\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\t\u00107\u001a\u00020\fHÖ\u0001J\t\u00108\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0012\"\u0004\b\u001b\u0010\u0014R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u000e\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#¨\u00069"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SubzoneTextData;", "Ljava/io/Serializable;", "isSel", "", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "serialNum", "", "isDel", "arrTextData", "", PlayerFinal.PLAYER_POSITION, "", "borderIndex", "borderEffectIndex", "borderSpeed", "<init>", "(ZLcom/wifiled/ipixels/event/EventText;Ljava/lang/String;Z[BIIII)V", "()Z", "setSel", "(Z)V", "getEventText", "()Lcom/wifiled/ipixels/event/EventText;", "getSerialNum", "()Ljava/lang/String;", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getArrTextData", "()[B", "setArrTextData", "([B)V", "getPosition", "()I", "setPosition", "(I)V", "getBorderIndex", "setBorderIndex", "getBorderEffectIndex", "setBorderEffectIndex", "getBorderSpeed", "setBorderSpeed", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SubzoneTextData implements Serializable {
    private byte[] arrTextData;
    private int borderEffectIndex;
    private int borderIndex;
    private int borderSpeed;
    private final EventText eventText;
    private boolean isDel;
    private boolean isSel;
    private int position;
    private String serialNum;

    public static /* synthetic */ SubzoneTextData copy$default(SubzoneTextData subzoneTextData, boolean z, EventText eventText, String str, boolean z2, byte[] bArr, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z = subzoneTextData.isSel;
        }
        if ((i5 & 2) != 0) {
            eventText = subzoneTextData.eventText;
        }
        if ((i5 & 4) != 0) {
            str = subzoneTextData.serialNum;
        }
        if ((i5 & 8) != 0) {
            z2 = subzoneTextData.isDel;
        }
        if ((i5 & 16) != 0) {
            bArr = subzoneTextData.arrTextData;
        }
        if ((i5 & 32) != 0) {
            i = subzoneTextData.position;
        }
        if ((i5 & 64) != 0) {
            i2 = subzoneTextData.borderIndex;
        }
        if ((i5 & 128) != 0) {
            i3 = subzoneTextData.borderEffectIndex;
        }
        if ((i5 & 256) != 0) {
            i4 = subzoneTextData.borderSpeed;
        }
        int i6 = i3;
        int i7 = i4;
        int i8 = i;
        int i9 = i2;
        byte[] bArr2 = bArr;
        String str2 = str;
        return subzoneTextData.copy(z, eventText, str2, z2, bArr2, i8, i9, i6, i7);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsSel() {
        return this.isSel;
    }

    /* renamed from: component2, reason: from getter */
    public final EventText getEventText() {
        return this.eventText;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSerialNum() {
        return this.serialNum;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsDel() {
        return this.isDel;
    }

    /* renamed from: component5, reason: from getter */
    public final byte[] getArrTextData() {
        return this.arrTextData;
    }

    /* renamed from: component6, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    /* renamed from: component7, reason: from getter */
    public final int getBorderIndex() {
        return this.borderIndex;
    }

    /* renamed from: component8, reason: from getter */
    public final int getBorderEffectIndex() {
        return this.borderEffectIndex;
    }

    /* renamed from: component9, reason: from getter */
    public final int getBorderSpeed() {
        return this.borderSpeed;
    }

    public final SubzoneTextData copy(boolean isSel, EventText eventText, String serialNum, boolean isDel, byte[] arrTextData, int position, int borderIndex, int borderEffectIndex, int borderSpeed) {
        Intrinsics.checkNotNullParameter(eventText, "eventText");
        Intrinsics.checkNotNullParameter(serialNum, "serialNum");
        Intrinsics.checkNotNullParameter(arrTextData, "arrTextData");
        return new SubzoneTextData(isSel, eventText, serialNum, isDel, arrTextData, position, borderIndex, borderEffectIndex, borderSpeed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubzoneTextData)) {
            return false;
        }
        SubzoneTextData subzoneTextData = (SubzoneTextData) other;
        return this.isSel == subzoneTextData.isSel && Intrinsics.areEqual(this.eventText, subzoneTextData.eventText) && Intrinsics.areEqual(this.serialNum, subzoneTextData.serialNum) && this.isDel == subzoneTextData.isDel && Intrinsics.areEqual(this.arrTextData, subzoneTextData.arrTextData) && this.position == subzoneTextData.position && this.borderIndex == subzoneTextData.borderIndex && this.borderEffectIndex == subzoneTextData.borderEffectIndex && this.borderSpeed == subzoneTextData.borderSpeed;
    }

    public int hashCode() {
        return (((((((((((((((Boolean.hashCode(this.isSel) * 31) + this.eventText.hashCode()) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31) + Arrays.hashCode(this.arrTextData)) * 31) + Integer.hashCode(this.position)) * 31) + Integer.hashCode(this.borderIndex)) * 31) + Integer.hashCode(this.borderEffectIndex)) * 31) + Integer.hashCode(this.borderSpeed);
    }

    public String toString() {
        return "SubzoneTextData(isSel=" + this.isSel + ", eventText=" + this.eventText + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", arrTextData=" + Arrays.toString(this.arrTextData) + ", position=" + this.position + ", borderIndex=" + this.borderIndex + ", borderEffectIndex=" + this.borderEffectIndex + ", borderSpeed=" + this.borderSpeed + ")";
    }

    public SubzoneTextData(boolean z, EventText eventText, String serialNum, boolean z2, byte[] arrTextData, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(eventText, "eventText");
        Intrinsics.checkNotNullParameter(serialNum, "serialNum");
        Intrinsics.checkNotNullParameter(arrTextData, "arrTextData");
        this.isSel = z;
        this.eventText = eventText;
        this.serialNum = serialNum;
        this.isDel = z2;
        this.arrTextData = arrTextData;
        this.position = i;
        this.borderIndex = i2;
        this.borderEffectIndex = i3;
        this.borderSpeed = i4;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ SubzoneTextData(boolean r13, com.wifiled.ipixels.event.EventText r14, java.lang.String r15, boolean r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r12 = this;
            r0 = r22
            r1 = r0 & 32
            if (r1 == 0) goto L9
            r1 = 0
            r8 = r1
            goto Lb
        L9:
            r8 = r18
        Lb:
            r1 = r0 & 64
            r2 = -1
            if (r1 == 0) goto L12
            r9 = r2
            goto L14
        L12:
            r9 = r19
        L14:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L1a
            r10 = r2
            goto L1c
        L1a:
            r10 = r20
        L1c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L2a
            r11 = r2
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r2 = r12
            goto L34
        L2a:
            r11 = r21
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
        L34:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.subzone.SubzoneTextData.<init>(boolean, com.wifiled.ipixels.event.EventText, java.lang.String, boolean, byte[], int, int, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final boolean isSel() {
        return this.isSel;
    }

    public final void setSel(boolean z) {
        this.isSel = z;
    }

    public final EventText getEventText() {
        return this.eventText;
    }

    public final String getSerialNum() {
        return this.serialNum;
    }

    public final void setSerialNum(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serialNum = str;
    }

    public final boolean isDel() {
        return this.isDel;
    }

    public final void setDel(boolean z) {
        this.isDel = z;
    }

    public final byte[] getArrTextData() {
        return this.arrTextData;
    }

    public final void setArrTextData(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.arrTextData = bArr;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public final int getBorderIndex() {
        return this.borderIndex;
    }

    public final void setBorderIndex(int i) {
        this.borderIndex = i;
    }

    public final int getBorderEffectIndex() {
        return this.borderEffectIndex;
    }

    public final void setBorderEffectIndex(int i) {
        this.borderEffectIndex = i;
    }

    public final int getBorderSpeed() {
        return this.borderSpeed;
    }

    public final void setBorderSpeed(int i) {
        this.borderSpeed = i;
    }
}
