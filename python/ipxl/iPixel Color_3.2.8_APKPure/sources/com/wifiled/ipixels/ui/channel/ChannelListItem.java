package com.wifiled.ipixels.ui.channel;

import android.graphics.Bitmap;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.subzone.SubzoneData;
import com.wifiled.musiclib.player.constant.DbFinal;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelListItem.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "<init>", "()V", "TextEmojView", "ImagView", "GiftView", "SubzoneView", "EyesView", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$EyesView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$GiftView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$ImagView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$SubzoneView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ChannelListItem implements Serializable {
    public /* synthetic */ ChannelListItem(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ChannelListItem() {
    }

    /* compiled from: ChannelListItem.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002BW\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010+\u001a\u00020\u0004HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\u0004HÆ\u0003J\t\u0010/\u001a\u00020\u000bHÆ\u0003J\t\u00100\u001a\u00020\rHÆ\u0003J\t\u00101\u001a\u00020\rHÆ\u0003J\t\u00102\u001a\u00020\rHÆ\u0003J\t\u00103\u001a\u00020\rHÆ\u0003Jc\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\rHÆ\u0001J\u0013\u00105\u001a\u00020\u00042\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\t\u00108\u001a\u00020\rHÖ\u0001J\t\u00109\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0013\"\u0004\b\u001c\u0010\u0015R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\"\"\u0004\b(\u0010$R\u001a\u0010\u0010\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$¨\u0006:"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "isSel", "", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "serialNum", "", "isDel", "arrTextData", "", PlayerFinal.PLAYER_POSITION, "", "borderIndex", "borderEffectIndex", "borderSpeed", "<init>", "(ZLcom/wifiled/ipixels/event/EventText;Ljava/lang/String;Z[BIIII)V", "()Z", "setSel", "(Z)V", "getEventText", "()Lcom/wifiled/ipixels/event/EventText;", "getSerialNum", "()Ljava/lang/String;", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getArrTextData", "()[B", "setArrTextData", "([B)V", "getPosition", "()I", "setPosition", "(I)V", "getBorderIndex", "setBorderIndex", "getBorderEffectIndex", "setBorderEffectIndex", "getBorderSpeed", "setBorderSpeed", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TextEmojView extends ChannelListItem implements Serializable {
        private byte[] arrTextData;
        private int borderEffectIndex;
        private int borderIndex;
        private int borderSpeed;
        private final EventText eventText;
        private boolean isDel;
        private boolean isSel;
        private int position;
        private String serialNum;

        public static /* synthetic */ TextEmojView copy$default(TextEmojView textEmojView, boolean z, EventText eventText, String str, boolean z2, byte[] bArr, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                z = textEmojView.isSel;
            }
            if ((i5 & 2) != 0) {
                eventText = textEmojView.eventText;
            }
            if ((i5 & 4) != 0) {
                str = textEmojView.serialNum;
            }
            if ((i5 & 8) != 0) {
                z2 = textEmojView.isDel;
            }
            if ((i5 & 16) != 0) {
                bArr = textEmojView.arrTextData;
            }
            if ((i5 & 32) != 0) {
                i = textEmojView.position;
            }
            if ((i5 & 64) != 0) {
                i2 = textEmojView.borderIndex;
            }
            if ((i5 & 128) != 0) {
                i3 = textEmojView.borderEffectIndex;
            }
            if ((i5 & 256) != 0) {
                i4 = textEmojView.borderSpeed;
            }
            int i6 = i3;
            int i7 = i4;
            int i8 = i;
            int i9 = i2;
            byte[] bArr2 = bArr;
            String str2 = str;
            return textEmojView.copy(z, eventText, str2, z2, bArr2, i8, i9, i6, i7);
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

        public final TextEmojView copy(boolean isSel, EventText eventText, String serialNum, boolean isDel, byte[] arrTextData, int position, int borderIndex, int borderEffectIndex, int borderSpeed) {
            Intrinsics.checkNotNullParameter(eventText, "eventText");
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            Intrinsics.checkNotNullParameter(arrTextData, "arrTextData");
            return new TextEmojView(isSel, eventText, serialNum, isDel, arrTextData, position, borderIndex, borderEffectIndex, borderSpeed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TextEmojView)) {
                return false;
            }
            TextEmojView textEmojView = (TextEmojView) other;
            return this.isSel == textEmojView.isSel && Intrinsics.areEqual(this.eventText, textEmojView.eventText) && Intrinsics.areEqual(this.serialNum, textEmojView.serialNum) && this.isDel == textEmojView.isDel && Intrinsics.areEqual(this.arrTextData, textEmojView.arrTextData) && this.position == textEmojView.position && this.borderIndex == textEmojView.borderIndex && this.borderEffectIndex == textEmojView.borderEffectIndex && this.borderSpeed == textEmojView.borderSpeed;
        }

        public int hashCode() {
            return (((((((((((((((Boolean.hashCode(this.isSel) * 31) + this.eventText.hashCode()) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31) + Arrays.hashCode(this.arrTextData)) * 31) + Integer.hashCode(this.position)) * 31) + Integer.hashCode(this.borderIndex)) * 31) + Integer.hashCode(this.borderEffectIndex)) * 31) + Integer.hashCode(this.borderSpeed);
        }

        public String toString() {
            return "TextEmojView(isSel=" + this.isSel + ", eventText=" + this.eventText + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", arrTextData=" + Arrays.toString(this.arrTextData) + ", position=" + this.position + ", borderIndex=" + this.borderIndex + ", borderEffectIndex=" + this.borderEffectIndex + ", borderSpeed=" + this.borderSpeed + ")";
        }

        public /* synthetic */ TextEmojView(boolean z, EventText eventText, String str, boolean z2, byte[] bArr, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, eventText, str, z2, bArr, (i5 & 32) != 0 ? 0 : i, (i5 & 64) != 0 ? 0 : i2, (i5 & 128) != 0 ? 0 : i3, (i5 & 256) != 0 ? 0 : i4);
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TextEmojView(boolean z, EventText eventText, String serialNum, boolean z2, byte[] arrTextData, int i, int i2, int i3, int i4) {
            super(null);
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
    }

    /* compiled from: ChannelListItem.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J=\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010$\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006*"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem$ImagView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "isSel", "", "bitmap", "Landroid/graphics/Bitmap;", "serialNum", "", "isDel", "arrImagData", "", "<init>", "(ZLandroid/graphics/Bitmap;Ljava/lang/String;Z[B)V", "()Z", "setSel", "(Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "getSerialNum", "()Ljava/lang/String;", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getArrImagData", "()[B", "setArrImagData", "([B)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ImagView extends ChannelListItem implements Serializable {
        private byte[] arrImagData;
        private transient Bitmap bitmap;
        private boolean isDel;
        private boolean isSel;
        private String serialNum;

        public static /* synthetic */ ImagView copy$default(ImagView imagView, boolean z, Bitmap bitmap, String str, boolean z2, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                z = imagView.isSel;
            }
            if ((i & 2) != 0) {
                bitmap = imagView.bitmap;
            }
            if ((i & 4) != 0) {
                str = imagView.serialNum;
            }
            if ((i & 8) != 0) {
                z2 = imagView.isDel;
            }
            if ((i & 16) != 0) {
                bArr = imagView.arrImagData;
            }
            byte[] bArr2 = bArr;
            String str2 = str;
            return imagView.copy(z, bitmap, str2, z2, bArr2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSel() {
            return this.isSel;
        }

        /* renamed from: component2, reason: from getter */
        public final Bitmap getBitmap() {
            return this.bitmap;
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
        public final byte[] getArrImagData() {
            return this.arrImagData;
        }

        public final ImagView copy(boolean isSel, Bitmap bitmap, String serialNum, boolean isDel, byte[] arrImagData) {
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            Intrinsics.checkNotNullParameter(arrImagData, "arrImagData");
            return new ImagView(isSel, bitmap, serialNum, isDel, arrImagData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImagView)) {
                return false;
            }
            ImagView imagView = (ImagView) other;
            return this.isSel == imagView.isSel && Intrinsics.areEqual(this.bitmap, imagView.bitmap) && Intrinsics.areEqual(this.serialNum, imagView.serialNum) && this.isDel == imagView.isDel && Intrinsics.areEqual(this.arrImagData, imagView.arrImagData);
        }

        public int hashCode() {
            int hashCode = Boolean.hashCode(this.isSel) * 31;
            Bitmap bitmap = this.bitmap;
            return ((((((hashCode + (bitmap == null ? 0 : bitmap.hashCode())) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31) + Arrays.hashCode(this.arrImagData);
        }

        public String toString() {
            return "ImagView(isSel=" + this.isSel + ", bitmap=" + this.bitmap + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", arrImagData=" + Arrays.toString(this.arrImagData) + ")";
        }

        public final boolean isSel() {
            return this.isSel;
        }

        public final void setSel(boolean z) {
            this.isSel = z;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
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

        public final byte[] getArrImagData() {
            return this.arrImagData;
        }

        public final void setArrImagData(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<set-?>");
            this.arrImagData = bArr;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImagView(boolean z, Bitmap bitmap, String serialNum, boolean z2, byte[] arrImagData) {
            super(null);
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            Intrinsics.checkNotNullParameter(arrImagData, "arrImagData");
            this.isSel = z;
            this.bitmap = bitmap;
            this.serialNum = serialNum;
            this.isDel = z2;
            this.arrImagData = arrImagData;
        }
    }

    /* compiled from: ChannelListItem.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003J=\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010 \u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0011\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem$GiftView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "isSel", "", DbFinal.LOCAL_PATH, "", "serialNum", "isDel", "arrGifData", "", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Z[B)V", "()Z", "setSel", "(Z)V", "getPath", "()Ljava/lang/String;", "getSerialNum", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getArrGifData", "()[B", "setArrGifData", "([B)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class GiftView extends ChannelListItem implements Serializable {
        private byte[] arrGifData;
        private boolean isDel;
        private boolean isSel;
        private final String path;
        private String serialNum;

        public static /* synthetic */ GiftView copy$default(GiftView giftView, boolean z, String str, String str2, boolean z2, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                z = giftView.isSel;
            }
            if ((i & 2) != 0) {
                str = giftView.path;
            }
            if ((i & 4) != 0) {
                str2 = giftView.serialNum;
            }
            if ((i & 8) != 0) {
                z2 = giftView.isDel;
            }
            if ((i & 16) != 0) {
                bArr = giftView.arrGifData;
            }
            byte[] bArr2 = bArr;
            String str3 = str2;
            return giftView.copy(z, str, str3, z2, bArr2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSel() {
            return this.isSel;
        }

        /* renamed from: component2, reason: from getter */
        public final String getPath() {
            return this.path;
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
        public final byte[] getArrGifData() {
            return this.arrGifData;
        }

        public final GiftView copy(boolean isSel, String path, String serialNum, boolean isDel, byte[] arrGifData) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            return new GiftView(isSel, path, serialNum, isDel, arrGifData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GiftView)) {
                return false;
            }
            GiftView giftView = (GiftView) other;
            return this.isSel == giftView.isSel && Intrinsics.areEqual(this.path, giftView.path) && Intrinsics.areEqual(this.serialNum, giftView.serialNum) && this.isDel == giftView.isDel && Intrinsics.areEqual(this.arrGifData, giftView.arrGifData);
        }

        public int hashCode() {
            int hashCode = ((((((Boolean.hashCode(this.isSel) * 31) + this.path.hashCode()) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31;
            byte[] bArr = this.arrGifData;
            return hashCode + (bArr == null ? 0 : Arrays.hashCode(bArr));
        }

        public String toString() {
            return "GiftView(isSel=" + this.isSel + ", path=" + this.path + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", arrGifData=" + Arrays.toString(this.arrGifData) + ")";
        }

        public /* synthetic */ GiftView(boolean z, String str, String str2, boolean z2, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, str2, z2, (i & 16) != 0 ? null : bArr);
        }

        public final boolean isSel() {
            return this.isSel;
        }

        public final void setSel(boolean z) {
            this.isSel = z;
        }

        public final String getPath() {
            return this.path;
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

        public final byte[] getArrGifData() {
            return this.arrGifData;
        }

        public final void setArrGifData(byte[] bArr) {
            this.arrGifData = bArr;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GiftView(boolean z, String path, String serialNum, boolean z2, byte[] bArr) {
            super(null);
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            this.isSel = z;
            this.path = path;
            this.serialNum = serialNum;
            this.isDel = z2;
            this.arrGifData = bArr;
        }
    }

    /* compiled from: ChannelListItem.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J;\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010$\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000e\"\u0004\b\u0015\u0010\u0010R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006*"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem$SubzoneView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "isSel", "", "serialNum", "", "isDel", "subzoneData", "Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "arrData", "", "<init>", "(ZLjava/lang/String;ZLcom/wifiled/ipixels/ui/subzone/SubzoneData;[B)V", "()Z", "setSel", "(Z)V", "getSerialNum", "()Ljava/lang/String;", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getSubzoneData", "()Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "setSubzoneData", "(Lcom/wifiled/ipixels/ui/subzone/SubzoneData;)V", "getArrData", "()[B", "setArrData", "([B)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SubzoneView extends ChannelListItem implements Serializable {
        private byte[] arrData;
        private boolean isDel;
        private boolean isSel;
        private String serialNum;
        private SubzoneData subzoneData;

        public static /* synthetic */ SubzoneView copy$default(SubzoneView subzoneView, boolean z, String str, boolean z2, SubzoneData subzoneData, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                z = subzoneView.isSel;
            }
            if ((i & 2) != 0) {
                str = subzoneView.serialNum;
            }
            if ((i & 4) != 0) {
                z2 = subzoneView.isDel;
            }
            if ((i & 8) != 0) {
                subzoneData = subzoneView.subzoneData;
            }
            if ((i & 16) != 0) {
                bArr = subzoneView.arrData;
            }
            byte[] bArr2 = bArr;
            boolean z3 = z2;
            return subzoneView.copy(z, str, z3, subzoneData, bArr2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSel() {
            return this.isSel;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSerialNum() {
            return this.serialNum;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsDel() {
            return this.isDel;
        }

        /* renamed from: component4, reason: from getter */
        public final SubzoneData getSubzoneData() {
            return this.subzoneData;
        }

        /* renamed from: component5, reason: from getter */
        public final byte[] getArrData() {
            return this.arrData;
        }

        public final SubzoneView copy(boolean isSel, String serialNum, boolean isDel, SubzoneData subzoneData, byte[] arrData) {
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            Intrinsics.checkNotNullParameter(subzoneData, "subzoneData");
            Intrinsics.checkNotNullParameter(arrData, "arrData");
            return new SubzoneView(isSel, serialNum, isDel, subzoneData, arrData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubzoneView)) {
                return false;
            }
            SubzoneView subzoneView = (SubzoneView) other;
            return this.isSel == subzoneView.isSel && Intrinsics.areEqual(this.serialNum, subzoneView.serialNum) && this.isDel == subzoneView.isDel && Intrinsics.areEqual(this.subzoneData, subzoneView.subzoneData) && Intrinsics.areEqual(this.arrData, subzoneView.arrData);
        }

        public int hashCode() {
            return (((((((Boolean.hashCode(this.isSel) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31) + this.subzoneData.hashCode()) * 31) + Arrays.hashCode(this.arrData);
        }

        public String toString() {
            return "SubzoneView(isSel=" + this.isSel + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", subzoneData=" + this.subzoneData + ", arrData=" + Arrays.toString(this.arrData) + ")";
        }

        public final boolean isSel() {
            return this.isSel;
        }

        public final void setSel(boolean z) {
            this.isSel = z;
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

        public final SubzoneData getSubzoneData() {
            return this.subzoneData;
        }

        public final void setSubzoneData(SubzoneData subzoneData) {
            Intrinsics.checkNotNullParameter(subzoneData, "<set-?>");
            this.subzoneData = subzoneData;
        }

        public final byte[] getArrData() {
            return this.arrData;
        }

        public final void setArrData(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<set-?>");
            this.arrData = bArr;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubzoneView(boolean z, String serialNum, boolean z2, SubzoneData subzoneData, byte[] arrData) {
            super(null);
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            Intrinsics.checkNotNullParameter(subzoneData, "subzoneData");
            Intrinsics.checkNotNullParameter(arrData, "arrData");
            this.isSel = z;
            this.serialNum = serialNum;
            this.isDel = z2;
            this.subzoneData = subzoneData;
            this.arrData = arrData;
        }
    }

    /* compiled from: ChannelListItem.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u001d\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003JS\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000f\"\u0004\b\u0018\u0010\u0011R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001c¨\u0006-"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListItem$EyesView;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Ljava/io/Serializable;", "isSel", "", "leftPath", "", "rightPath", "serialNum", "isDel", "leftData", "", "rightData", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z[B[B)V", "()Z", "setSel", "(Z)V", "getLeftPath", "()Ljava/lang/String;", "getRightPath", "getSerialNum", "setSerialNum", "(Ljava/lang/String;)V", "setDel", "getLeftData", "()[B", "setLeftData", "([B)V", "getRightData", "setRightData", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EyesView extends ChannelListItem implements Serializable {
        private boolean isDel;
        private boolean isSel;
        private byte[] leftData;
        private final String leftPath;
        private byte[] rightData;
        private final String rightPath;
        private String serialNum;

        public static /* synthetic */ EyesView copy$default(EyesView eyesView, boolean z, String str, String str2, String str3, boolean z2, byte[] bArr, byte[] bArr2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = eyesView.isSel;
            }
            if ((i & 2) != 0) {
                str = eyesView.leftPath;
            }
            if ((i & 4) != 0) {
                str2 = eyesView.rightPath;
            }
            if ((i & 8) != 0) {
                str3 = eyesView.serialNum;
            }
            if ((i & 16) != 0) {
                z2 = eyesView.isDel;
            }
            if ((i & 32) != 0) {
                bArr = eyesView.leftData;
            }
            if ((i & 64) != 0) {
                bArr2 = eyesView.rightData;
            }
            byte[] bArr3 = bArr;
            byte[] bArr4 = bArr2;
            boolean z3 = z2;
            String str4 = str2;
            return eyesView.copy(z, str, str4, str3, z3, bArr3, bArr4);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSel() {
            return this.isSel;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLeftPath() {
            return this.leftPath;
        }

        /* renamed from: component3, reason: from getter */
        public final String getRightPath() {
            return this.rightPath;
        }

        /* renamed from: component4, reason: from getter */
        public final String getSerialNum() {
            return this.serialNum;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsDel() {
            return this.isDel;
        }

        /* renamed from: component6, reason: from getter */
        public final byte[] getLeftData() {
            return this.leftData;
        }

        /* renamed from: component7, reason: from getter */
        public final byte[] getRightData() {
            return this.rightData;
        }

        public final EyesView copy(boolean isSel, String leftPath, String rightPath, String serialNum, boolean isDel, byte[] leftData, byte[] rightData) {
            Intrinsics.checkNotNullParameter(leftPath, "leftPath");
            Intrinsics.checkNotNullParameter(rightPath, "rightPath");
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            return new EyesView(isSel, leftPath, rightPath, serialNum, isDel, leftData, rightData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EyesView)) {
                return false;
            }
            EyesView eyesView = (EyesView) other;
            return this.isSel == eyesView.isSel && Intrinsics.areEqual(this.leftPath, eyesView.leftPath) && Intrinsics.areEqual(this.rightPath, eyesView.rightPath) && Intrinsics.areEqual(this.serialNum, eyesView.serialNum) && this.isDel == eyesView.isDel && Intrinsics.areEqual(this.leftData, eyesView.leftData) && Intrinsics.areEqual(this.rightData, eyesView.rightData);
        }

        public int hashCode() {
            int hashCode = ((((((((Boolean.hashCode(this.isSel) * 31) + this.leftPath.hashCode()) * 31) + this.rightPath.hashCode()) * 31) + this.serialNum.hashCode()) * 31) + Boolean.hashCode(this.isDel)) * 31;
            byte[] bArr = this.leftData;
            int hashCode2 = (hashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
            byte[] bArr2 = this.rightData;
            return hashCode2 + (bArr2 != null ? Arrays.hashCode(bArr2) : 0);
        }

        public String toString() {
            return "EyesView(isSel=" + this.isSel + ", leftPath=" + this.leftPath + ", rightPath=" + this.rightPath + ", serialNum=" + this.serialNum + ", isDel=" + this.isDel + ", leftData=" + Arrays.toString(this.leftData) + ", rightData=" + Arrays.toString(this.rightData) + ")";
        }

        public /* synthetic */ EyesView(boolean z, String str, String str2, String str3, boolean z2, byte[] bArr, byte[] bArr2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, str2, str3, z2, (i & 32) != 0 ? null : bArr, (i & 64) != 0 ? null : bArr2);
        }

        public final boolean isSel() {
            return this.isSel;
        }

        public final void setSel(boolean z) {
            this.isSel = z;
        }

        public final String getLeftPath() {
            return this.leftPath;
        }

        public final String getRightPath() {
            return this.rightPath;
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

        public final byte[] getLeftData() {
            return this.leftData;
        }

        public final void setLeftData(byte[] bArr) {
            this.leftData = bArr;
        }

        public final byte[] getRightData() {
            return this.rightData;
        }

        public final void setRightData(byte[] bArr) {
            this.rightData = bArr;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EyesView(boolean z, String leftPath, String rightPath, String serialNum, boolean z2, byte[] bArr, byte[] bArr2) {
            super(null);
            Intrinsics.checkNotNullParameter(leftPath, "leftPath");
            Intrinsics.checkNotNullParameter(rightPath, "rightPath");
            Intrinsics.checkNotNullParameter(serialNum, "serialNum");
            this.isSel = z;
            this.leftPath = leftPath;
            this.rightPath = rightPath;
            this.serialNum = serialNum;
            this.isDel = z2;
            this.leftData = bArr;
            this.rightData = bArr2;
        }
    }
}
