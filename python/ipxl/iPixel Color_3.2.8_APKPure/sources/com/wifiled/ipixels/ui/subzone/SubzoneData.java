package com.wifiled.ipixels.ui.subzone;

import com.wifiled.ipixels.ui.channel.ChannelListItem;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubzoneData.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b-\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\fHÆ\u0003J\t\u00108\u001a\u00020\fHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0011HÆ\u0003J}\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010=\u001a\u00020\u00112\b\u0010>\u001a\u0004\u0018\u00010?HÖ\u0003J\t\u0010@\u001a\u00020\u0003HÖ\u0001J\t\u0010A\u001a\u00020BHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010%\"\u0004\b)\u0010'R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0015\"\u0004\b+\u0010\u0017R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0015\"\u0004\b-\u0010\u0017R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010.\"\u0004\b/\u00100¨\u0006C"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "Ljava/io/Serializable;", "templateType", "", "mDataList", "", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "ledType", "itemBorderIndex", "itemBorderEffectIndex", "itemBorderSpeed", "imageData1", "", "imageData2", "image1Type", "image2Type", "isEdit", "", "<init>", "(ILjava/util/List;IIII[B[BIIZ)V", "getTemplateType", "()I", "setTemplateType", "(I)V", "getMDataList", "()Ljava/util/List;", "setMDataList", "(Ljava/util/List;)V", "getLedType", "setLedType", "getItemBorderIndex", "setItemBorderIndex", "getItemBorderEffectIndex", "setItemBorderEffectIndex", "getItemBorderSpeed", "setItemBorderSpeed", "getImageData1", "()[B", "setImageData1", "([B)V", "getImageData2", "setImageData2", "getImage1Type", "setImage1Type", "getImage2Type", "setImage2Type", "()Z", "setEdit", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SubzoneData implements Serializable {
    private int image1Type;
    private int image2Type;
    private byte[] imageData1;
    private byte[] imageData2;
    private boolean isEdit;
    private int itemBorderEffectIndex;
    private int itemBorderIndex;
    private int itemBorderSpeed;
    private int ledType;
    private List<? extends ChannelListItem> mDataList;
    private int templateType;

    public static /* synthetic */ SubzoneData copy$default(SubzoneData subzoneData, int i, List list, int i2, int i3, int i4, int i5, byte[] bArr, byte[] bArr2, int i6, int i7, boolean z, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i = subzoneData.templateType;
        }
        if ((i8 & 2) != 0) {
            list = subzoneData.mDataList;
        }
        if ((i8 & 4) != 0) {
            i2 = subzoneData.ledType;
        }
        if ((i8 & 8) != 0) {
            i3 = subzoneData.itemBorderIndex;
        }
        if ((i8 & 16) != 0) {
            i4 = subzoneData.itemBorderEffectIndex;
        }
        if ((i8 & 32) != 0) {
            i5 = subzoneData.itemBorderSpeed;
        }
        if ((i8 & 64) != 0) {
            bArr = subzoneData.imageData1;
        }
        if ((i8 & 128) != 0) {
            bArr2 = subzoneData.imageData2;
        }
        if ((i8 & 256) != 0) {
            i6 = subzoneData.image1Type;
        }
        if ((i8 & 512) != 0) {
            i7 = subzoneData.image2Type;
        }
        if ((i8 & 1024) != 0) {
            z = subzoneData.isEdit;
        }
        int i9 = i7;
        boolean z2 = z;
        byte[] bArr3 = bArr2;
        int i10 = i6;
        int i11 = i5;
        byte[] bArr4 = bArr;
        int i12 = i4;
        int i13 = i2;
        return subzoneData.copy(i, list, i13, i3, i12, i11, bArr4, bArr3, i10, i9, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTemplateType() {
        return this.templateType;
    }

    /* renamed from: component10, reason: from getter */
    public final int getImage2Type() {
        return this.image2Type;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final List<ChannelListItem> component2() {
        return this.mDataList;
    }

    /* renamed from: component3, reason: from getter */
    public final int getLedType() {
        return this.ledType;
    }

    /* renamed from: component4, reason: from getter */
    public final int getItemBorderIndex() {
        return this.itemBorderIndex;
    }

    /* renamed from: component5, reason: from getter */
    public final int getItemBorderEffectIndex() {
        return this.itemBorderEffectIndex;
    }

    /* renamed from: component6, reason: from getter */
    public final int getItemBorderSpeed() {
        return this.itemBorderSpeed;
    }

    /* renamed from: component7, reason: from getter */
    public final byte[] getImageData1() {
        return this.imageData1;
    }

    /* renamed from: component8, reason: from getter */
    public final byte[] getImageData2() {
        return this.imageData2;
    }

    /* renamed from: component9, reason: from getter */
    public final int getImage1Type() {
        return this.image1Type;
    }

    public final SubzoneData copy(int templateType, List<? extends ChannelListItem> mDataList, int ledType, int itemBorderIndex, int itemBorderEffectIndex, int itemBorderSpeed, byte[] imageData1, byte[] imageData2, int image1Type, int image2Type, boolean isEdit) {
        Intrinsics.checkNotNullParameter(mDataList, "mDataList");
        Intrinsics.checkNotNullParameter(imageData1, "imageData1");
        Intrinsics.checkNotNullParameter(imageData2, "imageData2");
        return new SubzoneData(templateType, mDataList, ledType, itemBorderIndex, itemBorderEffectIndex, itemBorderSpeed, imageData1, imageData2, image1Type, image2Type, isEdit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubzoneData)) {
            return false;
        }
        SubzoneData subzoneData = (SubzoneData) other;
        return this.templateType == subzoneData.templateType && Intrinsics.areEqual(this.mDataList, subzoneData.mDataList) && this.ledType == subzoneData.ledType && this.itemBorderIndex == subzoneData.itemBorderIndex && this.itemBorderEffectIndex == subzoneData.itemBorderEffectIndex && this.itemBorderSpeed == subzoneData.itemBorderSpeed && Intrinsics.areEqual(this.imageData1, subzoneData.imageData1) && Intrinsics.areEqual(this.imageData2, subzoneData.imageData2) && this.image1Type == subzoneData.image1Type && this.image2Type == subzoneData.image2Type && this.isEdit == subzoneData.isEdit;
    }

    public int hashCode() {
        return (((((((((((((((((((Integer.hashCode(this.templateType) * 31) + this.mDataList.hashCode()) * 31) + Integer.hashCode(this.ledType)) * 31) + Integer.hashCode(this.itemBorderIndex)) * 31) + Integer.hashCode(this.itemBorderEffectIndex)) * 31) + Integer.hashCode(this.itemBorderSpeed)) * 31) + Arrays.hashCode(this.imageData1)) * 31) + Arrays.hashCode(this.imageData2)) * 31) + Integer.hashCode(this.image1Type)) * 31) + Integer.hashCode(this.image2Type)) * 31) + Boolean.hashCode(this.isEdit);
    }

    public String toString() {
        return "SubzoneData(templateType=" + this.templateType + ", mDataList=" + this.mDataList + ", ledType=" + this.ledType + ", itemBorderIndex=" + this.itemBorderIndex + ", itemBorderEffectIndex=" + this.itemBorderEffectIndex + ", itemBorderSpeed=" + this.itemBorderSpeed + ", imageData1=" + Arrays.toString(this.imageData1) + ", imageData2=" + Arrays.toString(this.imageData2) + ", image1Type=" + this.image1Type + ", image2Type=" + this.image2Type + ", isEdit=" + this.isEdit + ")";
    }

    public SubzoneData(int i, List<? extends ChannelListItem> mDataList, int i2, int i3, int i4, int i5, byte[] imageData1, byte[] imageData2, int i6, int i7, boolean z) {
        Intrinsics.checkNotNullParameter(mDataList, "mDataList");
        Intrinsics.checkNotNullParameter(imageData1, "imageData1");
        Intrinsics.checkNotNullParameter(imageData2, "imageData2");
        this.templateType = i;
        this.mDataList = mDataList;
        this.ledType = i2;
        this.itemBorderIndex = i3;
        this.itemBorderEffectIndex = i4;
        this.itemBorderSpeed = i5;
        this.imageData1 = imageData1;
        this.imageData2 = imageData2;
        this.image1Type = i6;
        this.image2Type = i7;
        this.isEdit = z;
    }

    public /* synthetic */ SubzoneData(int i, List list, int i2, int i3, int i4, int i5, byte[] bArr, byte[] bArr2, int i6, int i7, boolean z, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, i2, (i8 & 8) != 0 ? 0 : i3, (i8 & 16) != 0 ? 0 : i4, (i8 & 32) != 0 ? 0 : i5, bArr, bArr2, i6, i7, (i8 & 1024) != 0 ? false : z);
    }

    public final int getTemplateType() {
        return this.templateType;
    }

    public final void setTemplateType(int i) {
        this.templateType = i;
    }

    public final List<ChannelListItem> getMDataList() {
        return this.mDataList;
    }

    public final void setMDataList(List<? extends ChannelListItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mDataList = list;
    }

    public final int getLedType() {
        return this.ledType;
    }

    public final void setLedType(int i) {
        this.ledType = i;
    }

    public final int getItemBorderIndex() {
        return this.itemBorderIndex;
    }

    public final void setItemBorderIndex(int i) {
        this.itemBorderIndex = i;
    }

    public final int getItemBorderEffectIndex() {
        return this.itemBorderEffectIndex;
    }

    public final void setItemBorderEffectIndex(int i) {
        this.itemBorderEffectIndex = i;
    }

    public final int getItemBorderSpeed() {
        return this.itemBorderSpeed;
    }

    public final void setItemBorderSpeed(int i) {
        this.itemBorderSpeed = i;
    }

    public final byte[] getImageData1() {
        return this.imageData1;
    }

    public final void setImageData1(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.imageData1 = bArr;
    }

    public final byte[] getImageData2() {
        return this.imageData2;
    }

    public final void setImageData2(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.imageData2 = bArr;
    }

    public final int getImage1Type() {
        return this.image1Type;
    }

    public final void setImage1Type(int i) {
        this.image1Type = i;
    }

    public final int getImage2Type() {
        return this.image2Type;
    }

    public final void setImage2Type(int i) {
        this.image2Type = i;
    }

    public final boolean isEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
    }
}
