package com.wifiled.ipixels.event;

import androidx.core.view.ViewCompat;
import com.wifiled.ipixels.ui.text.TextAttrEnum;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: EventText.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\bC\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B·\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\r\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\b\b\u0002\u0010\u0016\u001a\u00020\r\u0012\b\b\u0002\u0010\u0017\u001a\u00020\r\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010J\u001a\u00020\u0006HÆ\u0003J\t\u0010K\u001a\u00020\u0006HÆ\u0003J\t\u0010L\u001a\u00020\tHÆ\u0003J\t\u0010M\u001a\u00020\u000bHÆ\u0003J\t\u0010N\u001a\u00020\rHÆ\u0003J\t\u0010O\u001a\u00020\rHÆ\u0003J\t\u0010P\u001a\u00020\rHÆ\u0003J\t\u0010Q\u001a\u00020\rHÆ\u0003J\t\u0010R\u001a\u00020\rHÆ\u0003J\t\u0010S\u001a\u00020\rHÆ\u0003J\t\u0010T\u001a\u00020\rHÆ\u0003J\t\u0010U\u001a\u00020\rHÆ\u0003J\t\u0010V\u001a\u00020\rHÆ\u0003J\t\u0010W\u001a\u00020\rHÆ\u0003J\t\u0010X\u001a\u00020\rHÆ\u0003J\t\u0010Y\u001a\u00020\u0019HÆ\u0003J¹\u0001\u0010Z\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019HÆ\u0001J\u0013\u0010[\u001a\u00020\u00192\b\u0010\\\u001a\u0004\u0018\u00010]HÖ\u0003J\t\u0010^\u001a\u00020\rHÖ\u0001J\t\u0010_\u001a\u00020\u000bHÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u000e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010/\"\u0004\b3\u00101R\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010/\"\u0004\b5\u00101R\u001a\u0010\u0010\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010/\"\u0004\b7\u00101R\u001a\u0010\u0011\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010/\"\u0004\b9\u00101R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010/\"\u0004\b;\u00101R\u001a\u0010\u0013\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010/\"\u0004\b=\u00101R\u001a\u0010\u0014\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010/\"\u0004\b?\u00101R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010/\"\u0004\bA\u00101R\u001a\u0010\u0016\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010/\"\u0004\bC\u00101R\u001a\u0010\u0017\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010/\"\u0004\bE\u00101R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010F\"\u0004\bG\u0010H¨\u0006`"}, d2 = {"Lcom/wifiled/ipixels/event/EventText;", "Ljava/io/Serializable;", "textEmojiVO", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "textAlpha", "", "textSpeed", "changeMode", "Lcom/wifiled/ipixels/ui/text/TextAttrEnum;", TextBundle.TEXT_ENTRY, "", "textSize", "", "textColor", "textBgColor", "textHorizontalAlign", "textVerticalAlign", "textTypeFace", "textEffect", "textdirction", "borderType", "borderSpeed", "borderEffects", "isBold", "", "<init>", "(Ljava/util/List;FFLcom/wifiled/ipixels/ui/text/TextAttrEnum;Ljava/lang/String;IIIIIIIIIIIZ)V", "getTextEmojiVO", "()Ljava/util/List;", "setTextEmojiVO", "(Ljava/util/List;)V", "getTextAlpha", "()F", "setTextAlpha", "(F)V", "getTextSpeed", "setTextSpeed", "getChangeMode", "()Lcom/wifiled/ipixels/ui/text/TextAttrEnum;", "setChangeMode", "(Lcom/wifiled/ipixels/ui/text/TextAttrEnum;)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getTextSize", "()I", "setTextSize", "(I)V", "getTextColor", "setTextColor", "getTextBgColor", "setTextBgColor", "getTextHorizontalAlign", "setTextHorizontalAlign", "getTextVerticalAlign", "setTextVerticalAlign", "getTextTypeFace", "setTextTypeFace", "getTextEffect", "setTextEffect", "getTextdirction", "setTextdirction", "getBorderType", "setBorderType", "getBorderSpeed", "setBorderSpeed", "getBorderEffects", "setBorderEffects", "()Z", "setBold", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EventText implements Serializable {
    private int borderEffects;
    private int borderSpeed;
    private int borderType;
    private TextAttrEnum changeMode;
    private boolean isBold;
    private String text;
    private float textAlpha;
    private int textBgColor;
    private int textColor;
    private int textEffect;
    private List<TextEmojiVO> textEmojiVO;
    private int textHorizontalAlign;
    private int textSize;
    private float textSpeed;
    private int textTypeFace;
    private int textVerticalAlign;
    private int textdirction;

    public EventText() {
        this(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null);
    }

    public static /* synthetic */ EventText copy$default(EventText eventText, List list, float f, float f2, TextAttrEnum textAttrEnum, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z, int i12, Object obj) {
        boolean z2;
        int i13;
        List list2;
        EventText eventText2;
        int i14;
        float f3;
        float f4;
        TextAttrEnum textAttrEnum2;
        String str2;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        List list3 = (i12 & 1) != 0 ? eventText.textEmojiVO : list;
        float f5 = (i12 & 2) != 0 ? eventText.textAlpha : f;
        float f6 = (i12 & 4) != 0 ? eventText.textSpeed : f2;
        TextAttrEnum textAttrEnum3 = (i12 & 8) != 0 ? eventText.changeMode : textAttrEnum;
        String str3 = (i12 & 16) != 0 ? eventText.text : str;
        int i24 = (i12 & 32) != 0 ? eventText.textSize : i;
        int i25 = (i12 & 64) != 0 ? eventText.textColor : i2;
        int i26 = (i12 & 128) != 0 ? eventText.textBgColor : i3;
        int i27 = (i12 & 256) != 0 ? eventText.textHorizontalAlign : i4;
        int i28 = (i12 & 512) != 0 ? eventText.textVerticalAlign : i5;
        int i29 = (i12 & 1024) != 0 ? eventText.textTypeFace : i6;
        int i30 = (i12 & 2048) != 0 ? eventText.textEffect : i7;
        int i31 = (i12 & 4096) != 0 ? eventText.textdirction : i8;
        int i32 = (i12 & 8192) != 0 ? eventText.borderType : i9;
        List list4 = list3;
        int i33 = (i12 & 16384) != 0 ? eventText.borderSpeed : i10;
        int i34 = (i12 & 32768) != 0 ? eventText.borderEffects : i11;
        if ((i12 & 65536) != 0) {
            i13 = i34;
            z2 = eventText.isBold;
            i14 = i33;
            f3 = f5;
            f4 = f6;
            textAttrEnum2 = textAttrEnum3;
            str2 = str3;
            i15 = i24;
            i16 = i25;
            i17 = i26;
            i18 = i27;
            i19 = i28;
            i20 = i29;
            i21 = i30;
            i22 = i31;
            i23 = i32;
            list2 = list4;
            eventText2 = eventText;
        } else {
            z2 = z;
            i13 = i34;
            list2 = list4;
            eventText2 = eventText;
            i14 = i33;
            f3 = f5;
            f4 = f6;
            textAttrEnum2 = textAttrEnum3;
            str2 = str3;
            i15 = i24;
            i16 = i25;
            i17 = i26;
            i18 = i27;
            i19 = i28;
            i20 = i29;
            i21 = i30;
            i22 = i31;
            i23 = i32;
        }
        return eventText2.copy(list2, f3, f4, textAttrEnum2, str2, i15, i16, i17, i18, i19, i20, i21, i22, i23, i14, i13, z2);
    }

    public final List<TextEmojiVO> component1() {
        return this.textEmojiVO;
    }

    /* renamed from: component10, reason: from getter */
    public final int getTextVerticalAlign() {
        return this.textVerticalAlign;
    }

    /* renamed from: component11, reason: from getter */
    public final int getTextTypeFace() {
        return this.textTypeFace;
    }

    /* renamed from: component12, reason: from getter */
    public final int getTextEffect() {
        return this.textEffect;
    }

    /* renamed from: component13, reason: from getter */
    public final int getTextdirction() {
        return this.textdirction;
    }

    /* renamed from: component14, reason: from getter */
    public final int getBorderType() {
        return this.borderType;
    }

    /* renamed from: component15, reason: from getter */
    public final int getBorderSpeed() {
        return this.borderSpeed;
    }

    /* renamed from: component16, reason: from getter */
    public final int getBorderEffects() {
        return this.borderEffects;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getIsBold() {
        return this.isBold;
    }

    /* renamed from: component2, reason: from getter */
    public final float getTextAlpha() {
        return this.textAlpha;
    }

    /* renamed from: component3, reason: from getter */
    public final float getTextSpeed() {
        return this.textSpeed;
    }

    /* renamed from: component4, reason: from getter */
    public final TextAttrEnum getChangeMode() {
        return this.changeMode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTextSize() {
        return this.textSize;
    }

    /* renamed from: component7, reason: from getter */
    public final int getTextColor() {
        return this.textColor;
    }

    /* renamed from: component8, reason: from getter */
    public final int getTextBgColor() {
        return this.textBgColor;
    }

    /* renamed from: component9, reason: from getter */
    public final int getTextHorizontalAlign() {
        return this.textHorizontalAlign;
    }

    public final EventText copy(List<TextEmojiVO> textEmojiVO, float textAlpha, float textSpeed, TextAttrEnum changeMode, String text, int textSize, int textColor, int textBgColor, int textHorizontalAlign, int textVerticalAlign, int textTypeFace, int textEffect, int textdirction, int borderType, int borderSpeed, int borderEffects, boolean isBold) {
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        Intrinsics.checkNotNullParameter(changeMode, "changeMode");
        Intrinsics.checkNotNullParameter(text, "text");
        return new EventText(textEmojiVO, textAlpha, textSpeed, changeMode, text, textSize, textColor, textBgColor, textHorizontalAlign, textVerticalAlign, textTypeFace, textEffect, textdirction, borderType, borderSpeed, borderEffects, isBold);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventText)) {
            return false;
        }
        EventText eventText = (EventText) other;
        return Intrinsics.areEqual(this.textEmojiVO, eventText.textEmojiVO) && Float.compare(this.textAlpha, eventText.textAlpha) == 0 && Float.compare(this.textSpeed, eventText.textSpeed) == 0 && this.changeMode == eventText.changeMode && Intrinsics.areEqual(this.text, eventText.text) && this.textSize == eventText.textSize && this.textColor == eventText.textColor && this.textBgColor == eventText.textBgColor && this.textHorizontalAlign == eventText.textHorizontalAlign && this.textVerticalAlign == eventText.textVerticalAlign && this.textTypeFace == eventText.textTypeFace && this.textEffect == eventText.textEffect && this.textdirction == eventText.textdirction && this.borderType == eventText.borderType && this.borderSpeed == eventText.borderSpeed && this.borderEffects == eventText.borderEffects && this.isBold == eventText.isBold;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.textEmojiVO.hashCode() * 31) + Float.hashCode(this.textAlpha)) * 31) + Float.hashCode(this.textSpeed)) * 31) + this.changeMode.hashCode()) * 31) + this.text.hashCode()) * 31) + Integer.hashCode(this.textSize)) * 31) + Integer.hashCode(this.textColor)) * 31) + Integer.hashCode(this.textBgColor)) * 31) + Integer.hashCode(this.textHorizontalAlign)) * 31) + Integer.hashCode(this.textVerticalAlign)) * 31) + Integer.hashCode(this.textTypeFace)) * 31) + Integer.hashCode(this.textEffect)) * 31) + Integer.hashCode(this.textdirction)) * 31) + Integer.hashCode(this.borderType)) * 31) + Integer.hashCode(this.borderSpeed)) * 31) + Integer.hashCode(this.borderEffects)) * 31) + Boolean.hashCode(this.isBold);
    }

    public String toString() {
        return "EventText(textEmojiVO=" + this.textEmojiVO + ", textAlpha=" + this.textAlpha + ", textSpeed=" + this.textSpeed + ", changeMode=" + this.changeMode + ", text=" + this.text + ", textSize=" + this.textSize + ", textColor=" + this.textColor + ", textBgColor=" + this.textBgColor + ", textHorizontalAlign=" + this.textHorizontalAlign + ", textVerticalAlign=" + this.textVerticalAlign + ", textTypeFace=" + this.textTypeFace + ", textEffect=" + this.textEffect + ", textdirction=" + this.textdirction + ", borderType=" + this.borderType + ", borderSpeed=" + this.borderSpeed + ", borderEffects=" + this.borderEffects + ", isBold=" + this.isBold + ")";
    }

    public EventText(List<TextEmojiVO> textEmojiVO, float f, float f2, TextAttrEnum changeMode, String text, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z) {
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        Intrinsics.checkNotNullParameter(changeMode, "changeMode");
        Intrinsics.checkNotNullParameter(text, "text");
        this.textEmojiVO = textEmojiVO;
        this.textAlpha = f;
        this.textSpeed = f2;
        this.changeMode = changeMode;
        this.text = text;
        this.textSize = i;
        this.textColor = i2;
        this.textBgColor = i3;
        this.textHorizontalAlign = i4;
        this.textVerticalAlign = i5;
        this.textTypeFace = i6;
        this.textEffect = i7;
        this.textdirction = i8;
        this.borderType = i9;
        this.borderSpeed = i10;
        this.borderEffects = i11;
        this.isBold = z;
    }

    public /* synthetic */ EventText(List list, float f, float f2, TextAttrEnum textAttrEnum, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this((i12 & 1) != 0 ? new ArrayList() : list, (i12 & 2) != 0 ? 1.0f : f, (i12 & 4) != 0 ? 80.0f : f2, (i12 & 8) != 0 ? TextAttrEnum.Other : textAttrEnum, (i12 & 16) != 0 ? "" : str, (i12 & 32) != 0 ? 16 : i, (i12 & 64) != 0 ? -1 : i2, (i12 & 128) != 0 ? ViewCompat.MEASURED_STATE_MASK : i3, (i12 & 256) != 0 ? 1 : i4, (i12 & 512) != 0 ? 4 : i5, (i12 & 1024) != 0 ? 0 : i6, (i12 & 2048) != 0 ? 0 : i7, (i12 & 4096) != 0 ? 0 : i8, (i12 & 8192) != 0 ? 0 : i9, (i12 & 16384) != 0 ? 0 : i10, (i12 & 32768) != 0 ? 0 : i11, (i12 & 65536) != 0 ? false : z);
    }

    public final List<TextEmojiVO> getTextEmojiVO() {
        return this.textEmojiVO;
    }

    public final void setTextEmojiVO(List<TextEmojiVO> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.textEmojiVO = list;
    }

    public final float getTextAlpha() {
        return this.textAlpha;
    }

    public final void setTextAlpha(float f) {
        this.textAlpha = f;
    }

    public final float getTextSpeed() {
        return this.textSpeed;
    }

    public final void setTextSpeed(float f) {
        this.textSpeed = f;
    }

    public final TextAttrEnum getChangeMode() {
        return this.changeMode;
    }

    public final void setChangeMode(TextAttrEnum textAttrEnum) {
        Intrinsics.checkNotNullParameter(textAttrEnum, "<set-?>");
        this.changeMode = textAttrEnum;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final void setTextSize(int i) {
        this.textSize = i;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
    }

    public final int getTextBgColor() {
        return this.textBgColor;
    }

    public final void setTextBgColor(int i) {
        this.textBgColor = i;
    }

    public final int getTextHorizontalAlign() {
        return this.textHorizontalAlign;
    }

    public final void setTextHorizontalAlign(int i) {
        this.textHorizontalAlign = i;
    }

    public final int getTextVerticalAlign() {
        return this.textVerticalAlign;
    }

    public final void setTextVerticalAlign(int i) {
        this.textVerticalAlign = i;
    }

    public final int getTextTypeFace() {
        return this.textTypeFace;
    }

    public final void setTextTypeFace(int i) {
        this.textTypeFace = i;
    }

    public final int getTextEffect() {
        return this.textEffect;
    }

    public final void setTextEffect(int i) {
        this.textEffect = i;
    }

    public final int getTextdirction() {
        return this.textdirction;
    }

    public final void setTextdirction(int i) {
        this.textdirction = i;
    }

    public final int getBorderType() {
        return this.borderType;
    }

    public final void setBorderType(int i) {
        this.borderType = i;
    }

    public final int getBorderSpeed() {
        return this.borderSpeed;
    }

    public final void setBorderSpeed(int i) {
        this.borderSpeed = i;
    }

    public final int getBorderEffects() {
        return this.borderEffects;
    }

    public final void setBorderEffects(int i) {
        this.borderEffects = i;
    }

    public final boolean isBold() {
        return this.isBold;
    }

    public final void setBold(boolean z) {
        this.isBold = z;
    }
}
