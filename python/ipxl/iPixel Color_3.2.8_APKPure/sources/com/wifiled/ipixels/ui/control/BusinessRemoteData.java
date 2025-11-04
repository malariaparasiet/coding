package com.wifiled.ipixels.ui.control;

import com.wifiled.ipixels.ui.channel.ChannelListItem;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BusinessRemoteData.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\rHÆ\u0003JS\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010.\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u00020\u0007HÖ\u0001J\t\u00102\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0010\"\u0004\b!\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00063"}, d2 = {"Lcom/wifiled/ipixels/ui/control/BusinessRemoteData;", "Ljava/io/Serializable;", "isDefault", "", "editResourceURL", "", "editResourceType", "", "serialNum", "editByteData", "", "isSelect", "textEmojiView", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "<init>", "(ZLjava/lang/String;II[BZLcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;)V", "()Z", "setDefault", "(Z)V", "getEditResourceURL", "()Ljava/lang/String;", "setEditResourceURL", "(Ljava/lang/String;)V", "getEditResourceType", "()I", "setEditResourceType", "(I)V", "getSerialNum", "setSerialNum", "getEditByteData", "()[B", "setEditByteData", "([B)V", "setSelect", "getTextEmojiView", "()Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "setTextEmojiView", "(Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BusinessRemoteData implements Serializable {
    private byte[] editByteData;
    private int editResourceType;
    private String editResourceURL;
    private boolean isDefault;
    private boolean isSelect;
    private int serialNum;
    private ChannelListItem.TextEmojView textEmojiView;

    public static /* synthetic */ BusinessRemoteData copy$default(BusinessRemoteData businessRemoteData, boolean z, String str, int i, int i2, byte[] bArr, boolean z2, ChannelListItem.TextEmojView textEmojView, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = businessRemoteData.isDefault;
        }
        if ((i3 & 2) != 0) {
            str = businessRemoteData.editResourceURL;
        }
        if ((i3 & 4) != 0) {
            i = businessRemoteData.editResourceType;
        }
        if ((i3 & 8) != 0) {
            i2 = businessRemoteData.serialNum;
        }
        if ((i3 & 16) != 0) {
            bArr = businessRemoteData.editByteData;
        }
        if ((i3 & 32) != 0) {
            z2 = businessRemoteData.isSelect;
        }
        if ((i3 & 64) != 0) {
            textEmojView = businessRemoteData.textEmojiView;
        }
        boolean z3 = z2;
        ChannelListItem.TextEmojView textEmojView2 = textEmojView;
        byte[] bArr2 = bArr;
        int i4 = i;
        return businessRemoteData.copy(z, str, i4, i2, bArr2, z3, textEmojView2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsDefault() {
        return this.isDefault;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEditResourceURL() {
        return this.editResourceURL;
    }

    /* renamed from: component3, reason: from getter */
    public final int getEditResourceType() {
        return this.editResourceType;
    }

    /* renamed from: component4, reason: from getter */
    public final int getSerialNum() {
        return this.serialNum;
    }

    /* renamed from: component5, reason: from getter */
    public final byte[] getEditByteData() {
        return this.editByteData;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component7, reason: from getter */
    public final ChannelListItem.TextEmojView getTextEmojiView() {
        return this.textEmojiView;
    }

    public final BusinessRemoteData copy(boolean isDefault, String editResourceURL, int editResourceType, int serialNum, byte[] editByteData, boolean isSelect, ChannelListItem.TextEmojView textEmojiView) {
        Intrinsics.checkNotNullParameter(editResourceURL, "editResourceURL");
        return new BusinessRemoteData(isDefault, editResourceURL, editResourceType, serialNum, editByteData, isSelect, textEmojiView);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BusinessRemoteData)) {
            return false;
        }
        BusinessRemoteData businessRemoteData = (BusinessRemoteData) other;
        return this.isDefault == businessRemoteData.isDefault && Intrinsics.areEqual(this.editResourceURL, businessRemoteData.editResourceURL) && this.editResourceType == businessRemoteData.editResourceType && this.serialNum == businessRemoteData.serialNum && Intrinsics.areEqual(this.editByteData, businessRemoteData.editByteData) && this.isSelect == businessRemoteData.isSelect && Intrinsics.areEqual(this.textEmojiView, businessRemoteData.textEmojiView);
    }

    public int hashCode() {
        int hashCode = ((((((Boolean.hashCode(this.isDefault) * 31) + this.editResourceURL.hashCode()) * 31) + Integer.hashCode(this.editResourceType)) * 31) + Integer.hashCode(this.serialNum)) * 31;
        byte[] bArr = this.editByteData;
        int hashCode2 = (((hashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31) + Boolean.hashCode(this.isSelect)) * 31;
        ChannelListItem.TextEmojView textEmojView = this.textEmojiView;
        return hashCode2 + (textEmojView != null ? textEmojView.hashCode() : 0);
    }

    public String toString() {
        return "BusinessRemoteData(isDefault=" + this.isDefault + ", editResourceURL=" + this.editResourceURL + ", editResourceType=" + this.editResourceType + ", serialNum=" + this.serialNum + ", editByteData=" + Arrays.toString(this.editByteData) + ", isSelect=" + this.isSelect + ", textEmojiView=" + this.textEmojiView + ")";
    }

    public BusinessRemoteData(boolean z, String editResourceURL, int i, int i2, byte[] bArr, boolean z2, ChannelListItem.TextEmojView textEmojView) {
        Intrinsics.checkNotNullParameter(editResourceURL, "editResourceURL");
        this.isDefault = z;
        this.editResourceURL = editResourceURL;
        this.editResourceType = i;
        this.serialNum = i2;
        this.editByteData = bArr;
        this.isSelect = z2;
        this.textEmojiView = textEmojView;
    }

    public /* synthetic */ BusinessRemoteData(boolean z, String str, int i, int i2, byte[] bArr, boolean z2, ChannelListItem.TextEmojView textEmojView, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? null : bArr, (i3 & 32) != 0 ? false : z2, (i3 & 64) != 0 ? null : textEmojView);
    }

    public final boolean isDefault() {
        return this.isDefault;
    }

    public final void setDefault(boolean z) {
        this.isDefault = z;
    }

    public final String getEditResourceURL() {
        return this.editResourceURL;
    }

    public final void setEditResourceURL(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.editResourceURL = str;
    }

    public final int getEditResourceType() {
        return this.editResourceType;
    }

    public final void setEditResourceType(int i) {
        this.editResourceType = i;
    }

    public final int getSerialNum() {
        return this.serialNum;
    }

    public final void setSerialNum(int i) {
        this.serialNum = i;
    }

    public final byte[] getEditByteData() {
        return this.editByteData;
    }

    public final void setEditByteData(byte[] bArr) {
        this.editByteData = bArr;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final ChannelListItem.TextEmojView getTextEmojiView() {
        return this.textEmojiView;
    }

    public final void setTextEmojiView(ChannelListItem.TextEmojView textEmojView) {
        this.textEmojiView = textEmojView;
    }
}
