package com.wifiled.ipixels.ui.diy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DiyImageFun.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u001a\b\u0002\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u001b\u00103\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\nHÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u001b\u00105\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\nHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\u009d\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u001a\b\u0002\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\n2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\t\u0010=\u001a\u00020\u0003HÖ\u0001J\t\u0010>\u001a\u00020?HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR,\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR,\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006@"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/CPaintRunTimeItem;", "Ljava/io/Serializable;", "selColor", "", "difColor", "row", "column", "arrPointXY", "", "arrMapMarkColorPointXY", "", "arrDifColorPointXY", "arrMapDifColorPointXY", "moveType", "arrMoveDirectionNum", "", "<init>", "(IIIILjava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/Map;I[B)V", "getSelColor", "()I", "setSelColor", "(I)V", "getDifColor", "setDifColor", "getRow", "setRow", "getColumn", "setColumn", "getArrPointXY", "()Ljava/util/List;", "setArrPointXY", "(Ljava/util/List;)V", "getArrMapMarkColorPointXY", "()Ljava/util/Map;", "setArrMapMarkColorPointXY", "(Ljava/util/Map;)V", "getArrDifColorPointXY", "setArrDifColorPointXY", "getArrMapDifColorPointXY", "setArrMapDifColorPointXY", "getMoveType", "setMoveType", "getArrMoveDirectionNum", "()[B", "setArrMoveDirectionNum", "([B)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CPaintRunTimeItem implements Serializable {
    private List<Integer> arrDifColorPointXY;
    private Map<Integer, List<Integer>> arrMapDifColorPointXY;
    private Map<Integer, List<Integer>> arrMapMarkColorPointXY;
    private byte[] arrMoveDirectionNum;
    private List<Integer> arrPointXY;
    private int column;
    private int difColor;
    private int moveType;
    private int row;
    private int selColor;

    public CPaintRunTimeItem() {
        this(0, 0, 0, 0, null, null, null, null, 0, null, 1023, null);
    }

    public static /* synthetic */ CPaintRunTimeItem copy$default(CPaintRunTimeItem cPaintRunTimeItem, int i, int i2, int i3, int i4, List list, Map map, List list2, Map map2, int i5, byte[] bArr, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = cPaintRunTimeItem.selColor;
        }
        if ((i6 & 2) != 0) {
            i2 = cPaintRunTimeItem.difColor;
        }
        if ((i6 & 4) != 0) {
            i3 = cPaintRunTimeItem.row;
        }
        if ((i6 & 8) != 0) {
            i4 = cPaintRunTimeItem.column;
        }
        if ((i6 & 16) != 0) {
            list = cPaintRunTimeItem.arrPointXY;
        }
        if ((i6 & 32) != 0) {
            map = cPaintRunTimeItem.arrMapMarkColorPointXY;
        }
        if ((i6 & 64) != 0) {
            list2 = cPaintRunTimeItem.arrDifColorPointXY;
        }
        if ((i6 & 128) != 0) {
            map2 = cPaintRunTimeItem.arrMapDifColorPointXY;
        }
        if ((i6 & 256) != 0) {
            i5 = cPaintRunTimeItem.moveType;
        }
        if ((i6 & 512) != 0) {
            bArr = cPaintRunTimeItem.arrMoveDirectionNum;
        }
        int i7 = i5;
        byte[] bArr2 = bArr;
        List list3 = list2;
        Map map3 = map2;
        List list4 = list;
        Map map4 = map;
        return cPaintRunTimeItem.copy(i, i2, i3, i4, list4, map4, list3, map3, i7, bArr2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSelColor() {
        return this.selColor;
    }

    /* renamed from: component10, reason: from getter */
    public final byte[] getArrMoveDirectionNum() {
        return this.arrMoveDirectionNum;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDifColor() {
        return this.difColor;
    }

    /* renamed from: component3, reason: from getter */
    public final int getRow() {
        return this.row;
    }

    /* renamed from: component4, reason: from getter */
    public final int getColumn() {
        return this.column;
    }

    public final List<Integer> component5() {
        return this.arrPointXY;
    }

    public final Map<Integer, List<Integer>> component6() {
        return this.arrMapMarkColorPointXY;
    }

    public final List<Integer> component7() {
        return this.arrDifColorPointXY;
    }

    public final Map<Integer, List<Integer>> component8() {
        return this.arrMapDifColorPointXY;
    }

    /* renamed from: component9, reason: from getter */
    public final int getMoveType() {
        return this.moveType;
    }

    public final CPaintRunTimeItem copy(int selColor, int difColor, int row, int column, List<Integer> arrPointXY, Map<Integer, List<Integer>> arrMapMarkColorPointXY, List<Integer> arrDifColorPointXY, Map<Integer, List<Integer>> arrMapDifColorPointXY, int moveType, byte[] arrMoveDirectionNum) {
        Intrinsics.checkNotNullParameter(arrPointXY, "arrPointXY");
        Intrinsics.checkNotNullParameter(arrMapMarkColorPointXY, "arrMapMarkColorPointXY");
        Intrinsics.checkNotNullParameter(arrDifColorPointXY, "arrDifColorPointXY");
        Intrinsics.checkNotNullParameter(arrMapDifColorPointXY, "arrMapDifColorPointXY");
        Intrinsics.checkNotNullParameter(arrMoveDirectionNum, "arrMoveDirectionNum");
        return new CPaintRunTimeItem(selColor, difColor, row, column, arrPointXY, arrMapMarkColorPointXY, arrDifColorPointXY, arrMapDifColorPointXY, moveType, arrMoveDirectionNum);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CPaintRunTimeItem)) {
            return false;
        }
        CPaintRunTimeItem cPaintRunTimeItem = (CPaintRunTimeItem) other;
        return this.selColor == cPaintRunTimeItem.selColor && this.difColor == cPaintRunTimeItem.difColor && this.row == cPaintRunTimeItem.row && this.column == cPaintRunTimeItem.column && Intrinsics.areEqual(this.arrPointXY, cPaintRunTimeItem.arrPointXY) && Intrinsics.areEqual(this.arrMapMarkColorPointXY, cPaintRunTimeItem.arrMapMarkColorPointXY) && Intrinsics.areEqual(this.arrDifColorPointXY, cPaintRunTimeItem.arrDifColorPointXY) && Intrinsics.areEqual(this.arrMapDifColorPointXY, cPaintRunTimeItem.arrMapDifColorPointXY) && this.moveType == cPaintRunTimeItem.moveType && Intrinsics.areEqual(this.arrMoveDirectionNum, cPaintRunTimeItem.arrMoveDirectionNum);
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.selColor) * 31) + Integer.hashCode(this.difColor)) * 31) + Integer.hashCode(this.row)) * 31) + Integer.hashCode(this.column)) * 31) + this.arrPointXY.hashCode()) * 31) + this.arrMapMarkColorPointXY.hashCode()) * 31) + this.arrDifColorPointXY.hashCode()) * 31) + this.arrMapDifColorPointXY.hashCode()) * 31) + Integer.hashCode(this.moveType)) * 31) + Arrays.hashCode(this.arrMoveDirectionNum);
    }

    public String toString() {
        return "CPaintRunTimeItem(selColor=" + this.selColor + ", difColor=" + this.difColor + ", row=" + this.row + ", column=" + this.column + ", arrPointXY=" + this.arrPointXY + ", arrMapMarkColorPointXY=" + this.arrMapMarkColorPointXY + ", arrDifColorPointXY=" + this.arrDifColorPointXY + ", arrMapDifColorPointXY=" + this.arrMapDifColorPointXY + ", moveType=" + this.moveType + ", arrMoveDirectionNum=" + Arrays.toString(this.arrMoveDirectionNum) + ")";
    }

    public CPaintRunTimeItem(int i, int i2, int i3, int i4, List<Integer> arrPointXY, Map<Integer, List<Integer>> arrMapMarkColorPointXY, List<Integer> arrDifColorPointXY, Map<Integer, List<Integer>> arrMapDifColorPointXY, int i5, byte[] arrMoveDirectionNum) {
        Intrinsics.checkNotNullParameter(arrPointXY, "arrPointXY");
        Intrinsics.checkNotNullParameter(arrMapMarkColorPointXY, "arrMapMarkColorPointXY");
        Intrinsics.checkNotNullParameter(arrDifColorPointXY, "arrDifColorPointXY");
        Intrinsics.checkNotNullParameter(arrMapDifColorPointXY, "arrMapDifColorPointXY");
        Intrinsics.checkNotNullParameter(arrMoveDirectionNum, "arrMoveDirectionNum");
        this.selColor = i;
        this.difColor = i2;
        this.row = i3;
        this.column = i4;
        this.arrPointXY = arrPointXY;
        this.arrMapMarkColorPointXY = arrMapMarkColorPointXY;
        this.arrDifColorPointXY = arrDifColorPointXY;
        this.arrMapDifColorPointXY = arrMapDifColorPointXY;
        this.moveType = i5;
        this.arrMoveDirectionNum = arrMoveDirectionNum;
    }

    public final int getSelColor() {
        return this.selColor;
    }

    public final void setSelColor(int i) {
        this.selColor = i;
    }

    public final int getDifColor() {
        return this.difColor;
    }

    public final void setDifColor(int i) {
        this.difColor = i;
    }

    public final int getRow() {
        return this.row;
    }

    public final void setRow(int i) {
        this.row = i;
    }

    public final int getColumn() {
        return this.column;
    }

    public final void setColumn(int i) {
        this.column = i;
    }

    public /* synthetic */ CPaintRunTimeItem(int i, int i2, int i3, int i4, ArrayList arrayList, LinkedHashMap linkedHashMap, ArrayList arrayList2, LinkedHashMap linkedHashMap2, int i5, byte[] bArr, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? -16777216 : i, (i6 & 2) != 0 ? -16777216 : i2, (i6 & 4) != 0 ? -1 : i3, (i6 & 8) != 0 ? -1 : i4, (i6 & 16) != 0 ? new ArrayList() : arrayList, (i6 & 32) != 0 ? new LinkedHashMap() : linkedHashMap, (i6 & 64) != 0 ? new ArrayList() : arrayList2, (i6 & 128) != 0 ? new LinkedHashMap() : linkedHashMap2, (i6 & 256) != 0 ? DiyImageMoveType.NO_EFFECT.getMode() : i5, (i6 & 512) != 0 ? new byte[0] : bArr);
    }

    public final List<Integer> getArrPointXY() {
        return this.arrPointXY;
    }

    public final void setArrPointXY(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.arrPointXY = list;
    }

    public final Map<Integer, List<Integer>> getArrMapMarkColorPointXY() {
        return this.arrMapMarkColorPointXY;
    }

    public final void setArrMapMarkColorPointXY(Map<Integer, List<Integer>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.arrMapMarkColorPointXY = map;
    }

    public final List<Integer> getArrDifColorPointXY() {
        return this.arrDifColorPointXY;
    }

    public final void setArrDifColorPointXY(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.arrDifColorPointXY = list;
    }

    public final Map<Integer, List<Integer>> getArrMapDifColorPointXY() {
        return this.arrMapDifColorPointXY;
    }

    public final void setArrMapDifColorPointXY(Map<Integer, List<Integer>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.arrMapDifColorPointXY = map;
    }

    public final int getMoveType() {
        return this.moveType;
    }

    public final void setMoveType(int i) {
        this.moveType = i;
    }

    public final byte[] getArrMoveDirectionNum() {
        return this.arrMoveDirectionNum;
    }

    public final void setArrMoveDirectionNum(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.arrMoveDirectionNum = bArr;
    }
}
