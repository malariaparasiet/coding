package com.wifiled.ipixels.core.text.process;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ByteUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: ITextProcess.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u001e\u0010\u000b\u001a\u00020\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\r\u001a\u00020\u000eH\u0004J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0004J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0004J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0004J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0004J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0004¨\u0006\u0019"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "", "<init>", "()V", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "getTextHead", "", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "numberToByteArr", TextBundle.TEXT_ENTRY, "numberToByteArr32", "numberToByteArr20", "numberToByteArr24", "numberToByteArr12", "getTextSizeType", "", "textEmojiVO", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ITextProcess {
    public static final int BYTE_DATA_MAX_LENGTH = 102400;

    public abstract TextData textToByteData(String textStr, List<TextEmojiVO> mTexts);

    protected final byte[] getTextHead(List<TextEmojiVO> mTexts, EventText eventText) {
        int i;
        Intrinsics.checkNotNullParameter(mTexts, "mTexts");
        Intrinsics.checkNotNullParameter(eventText, "eventText");
        int textColor = eventText.getTextColor();
        int textBgColor = eventText.getTextBgColor();
        byte size = (byte) (mTexts.size() & 255);
        byte size2 = (byte) ((mTexts.size() >> 8) & 255);
        byte textHorizontalAlign = (byte) eventText.getTextHorizontalAlign();
        byte textVerticalAlign = (byte) eventText.getTextVerticalAlign();
        byte textEffect = (byte) eventText.getTextEffect();
        byte textSpeed = (byte) eventText.getTextSpeed();
        if (textColor > 1) {
            i = textColor;
        } else {
            i = Math.abs(textColor) > 1 ? 1 : 0;
        }
        byte[] bArr = {size, size2, textHorizontalAlign, textVerticalAlign, textEffect, textSpeed, (byte) i, (byte) Color.red(textColor), (byte) Color.green(textColor), (byte) Color.blue(textColor), (byte) (textBgColor == -16777216 ? 0 : 1), (byte) Color.red(textBgColor), (byte) Color.green(textBgColor), (byte) Color.blue(textBgColor)};
        if (AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 16 || AppConfig.INSTANCE.getLedType() == 19) {
            bArr = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(bArr, (byte) eventText.getBorderType()), (byte) eventText.getBorderSpeed()), (byte) eventText.getBorderEffects());
        }
        LogUtils.vTag("ruis", "headerArr ---" + ByteUtils.binaryToHexString(bArr));
        return bArr;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    protected final byte[] numberToByteArr(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        switch (text.hashCode()) {
            case 48:
                if (text.equals("0")) {
                    byte[] textData = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_0));
                    Intrinsics.checkNotNull(textData);
                    return textData;
                }
                break;
            case 49:
                if (text.equals("1")) {
                    byte[] textData2 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_1));
                    Intrinsics.checkNotNull(textData2);
                    return textData2;
                }
                break;
            case 50:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    byte[] textData3 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_2));
                    Intrinsics.checkNotNull(textData3);
                    return textData3;
                }
                break;
            case 51:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    byte[] textData4 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_3));
                    Intrinsics.checkNotNull(textData4);
                    return textData4;
                }
                break;
            case 52:
                if (text.equals("4")) {
                    byte[] textData5 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_4));
                    Intrinsics.checkNotNull(textData5);
                    return textData5;
                }
                break;
            case 53:
                if (text.equals("5")) {
                    byte[] textData6 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_5));
                    Intrinsics.checkNotNull(textData6);
                    return textData6;
                }
                break;
            case 54:
                if (text.equals("6")) {
                    byte[] textData7 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_6));
                    Intrinsics.checkNotNull(textData7);
                    return textData7;
                }
                break;
            case 55:
                if (text.equals("7")) {
                    byte[] textData8 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_7));
                    Intrinsics.checkNotNull(textData8);
                    return textData8;
                }
                break;
            case 56:
                if (text.equals("8")) {
                    byte[] textData9 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_8));
                    Intrinsics.checkNotNull(textData9);
                    return textData9;
                }
                break;
            case 57:
                if (text.equals("9")) {
                    byte[] textData10 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_9));
                    Intrinsics.checkNotNull(textData10);
                    return textData10;
                }
                break;
        }
        return new byte[10];
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    protected final byte[] numberToByteArr32(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        switch (text.hashCode()) {
            case 48:
                if (text.equals("0")) {
                    Bitmap bitmapFromDrawable = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_0);
                    byte[] textData = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable, bitmapFromDrawable.getWidth() * 2, bitmapFromDrawable.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData);
                    return textData;
                }
                break;
            case 49:
                if (text.equals("1")) {
                    Bitmap bitmapFromDrawable2 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_1);
                    byte[] textData2 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable2, bitmapFromDrawable2.getWidth() * 2, bitmapFromDrawable2.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData2);
                    return textData2;
                }
                break;
            case 50:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    Bitmap bitmapFromDrawable3 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_2);
                    byte[] textData3 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable3, bitmapFromDrawable3.getWidth() * 2, bitmapFromDrawable3.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData3);
                    return textData3;
                }
                break;
            case 51:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    Bitmap bitmapFromDrawable4 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_3);
                    byte[] textData4 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable4, bitmapFromDrawable4.getWidth() * 2, bitmapFromDrawable4.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData4);
                    return textData4;
                }
                break;
            case 52:
                if (text.equals("4")) {
                    Bitmap bitmapFromDrawable5 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_4);
                    byte[] textData5 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable5, bitmapFromDrawable5.getWidth() * 2, bitmapFromDrawable5.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData5);
                    return textData5;
                }
                break;
            case 53:
                if (text.equals("5")) {
                    Bitmap bitmapFromDrawable6 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_5);
                    byte[] textData6 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable6, bitmapFromDrawable6.getWidth() * 2, bitmapFromDrawable6.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData6);
                    return textData6;
                }
                break;
            case 54:
                if (text.equals("6")) {
                    Bitmap bitmapFromDrawable7 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_6);
                    byte[] textData7 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable7, bitmapFromDrawable7.getWidth() * 2, bitmapFromDrawable7.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData7);
                    return textData7;
                }
                break;
            case 55:
                if (text.equals("7")) {
                    Bitmap bitmapFromDrawable8 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_7);
                    byte[] textData8 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable8, bitmapFromDrawable8.getWidth() * 2, bitmapFromDrawable8.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData8);
                    return textData8;
                }
                break;
            case 56:
                if (text.equals("8")) {
                    Bitmap bitmapFromDrawable9 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_8);
                    byte[] textData9 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable9, bitmapFromDrawable9.getWidth() * 2, bitmapFromDrawable9.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData9);
                    return textData9;
                }
                break;
            case 57:
                if (text.equals("9")) {
                    Bitmap bitmapFromDrawable10 = CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_9);
                    byte[] textData10 = TextAgreement.getTextData(Bitmap.createScaledBitmap(bitmapFromDrawable10, bitmapFromDrawable10.getWidth() * 2, bitmapFromDrawable10.getHeight() * 2, true));
                    Intrinsics.checkNotNull(textData10);
                    return textData10;
                }
                break;
        }
        return new byte[20];
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    protected final byte[] numberToByteArr20(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        switch (text.hashCode()) {
            case 48:
                if (text.equals("0")) {
                    byte[] textData = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_0));
                    Intrinsics.checkNotNull(textData);
                    return textData;
                }
                break;
            case 49:
                if (text.equals("1")) {
                    byte[] textData2 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_1));
                    Intrinsics.checkNotNull(textData2);
                    return textData2;
                }
                break;
            case 50:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    byte[] textData3 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_2));
                    Intrinsics.checkNotNull(textData3);
                    return textData3;
                }
                break;
            case 51:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    byte[] textData4 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_3));
                    Intrinsics.checkNotNull(textData4);
                    return textData4;
                }
                break;
            case 52:
                if (text.equals("4")) {
                    byte[] textData5 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_4));
                    Intrinsics.checkNotNull(textData5);
                    return textData5;
                }
                break;
            case 53:
                if (text.equals("5")) {
                    byte[] textData6 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_5));
                    Intrinsics.checkNotNull(textData6);
                    return textData6;
                }
                break;
            case 54:
                if (text.equals("6")) {
                    byte[] textData7 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_6));
                    Intrinsics.checkNotNull(textData7);
                    return textData7;
                }
                break;
            case 55:
                if (text.equals("7")) {
                    byte[] textData8 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_7));
                    Intrinsics.checkNotNull(textData8);
                    return textData8;
                }
                break;
            case 56:
                if (text.equals("8")) {
                    byte[] textData9 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_8));
                    Intrinsics.checkNotNull(textData9);
                    return textData9;
                }
                break;
            case 57:
                if (text.equals("9")) {
                    byte[] textData10 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_20_9));
                    Intrinsics.checkNotNull(textData10);
                    return textData10;
                }
                break;
        }
        return new byte[10];
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    protected final byte[] numberToByteArr24(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        switch (text.hashCode()) {
            case 48:
                if (text.equals("0")) {
                    byte[] textData = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_0));
                    Intrinsics.checkNotNull(textData);
                    return textData;
                }
                break;
            case 49:
                if (text.equals("1")) {
                    byte[] textData2 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_1));
                    Intrinsics.checkNotNull(textData2);
                    return textData2;
                }
                break;
            case 50:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    byte[] textData3 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_2));
                    Intrinsics.checkNotNull(textData3);
                    return textData3;
                }
                break;
            case 51:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    byte[] textData4 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_3));
                    Intrinsics.checkNotNull(textData4);
                    return textData4;
                }
                break;
            case 52:
                if (text.equals("4")) {
                    byte[] textData5 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_4));
                    Intrinsics.checkNotNull(textData5);
                    return textData5;
                }
                break;
            case 53:
                if (text.equals("5")) {
                    byte[] textData6 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_5));
                    Intrinsics.checkNotNull(textData6);
                    return textData6;
                }
                break;
            case 54:
                if (text.equals("6")) {
                    byte[] textData7 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_6));
                    Intrinsics.checkNotNull(textData7);
                    return textData7;
                }
                break;
            case 55:
                if (text.equals("7")) {
                    byte[] textData8 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_7));
                    Intrinsics.checkNotNull(textData8);
                    return textData8;
                }
                break;
            case 56:
                if (text.equals("8")) {
                    byte[] textData9 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_8));
                    Intrinsics.checkNotNull(textData9);
                    return textData9;
                }
                break;
            case 57:
                if (text.equals("9")) {
                    byte[] textData10 = TextAgreement.getTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_24_9));
                    Intrinsics.checkNotNull(textData10);
                    return textData10;
                }
                break;
        }
        return new byte[10];
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    protected final byte[] numberToByteArr12(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        switch (text.hashCode()) {
            case 48:
                if (text.equals("0")) {
                    byte[] whiteTextData = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_0));
                    Intrinsics.checkNotNull(whiteTextData);
                    return whiteTextData;
                }
                break;
            case 49:
                if (text.equals("1")) {
                    byte[] whiteTextData2 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_1));
                    Intrinsics.checkNotNull(whiteTextData2);
                    return whiteTextData2;
                }
                break;
            case 50:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    byte[] whiteTextData3 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_2));
                    Intrinsics.checkNotNull(whiteTextData3);
                    return whiteTextData3;
                }
                break;
            case 51:
                if (text.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    byte[] whiteTextData4 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_3));
                    Intrinsics.checkNotNull(whiteTextData4);
                    return whiteTextData4;
                }
                break;
            case 52:
                if (text.equals("4")) {
                    byte[] whiteTextData5 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_4));
                    Intrinsics.checkNotNull(whiteTextData5);
                    return whiteTextData5;
                }
                break;
            case 53:
                if (text.equals("5")) {
                    byte[] whiteTextData6 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_5));
                    Intrinsics.checkNotNull(whiteTextData6);
                    return whiteTextData6;
                }
                break;
            case 54:
                if (text.equals("6")) {
                    byte[] whiteTextData7 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_6));
                    Intrinsics.checkNotNull(whiteTextData7);
                    return whiteTextData7;
                }
                break;
            case 55:
                if (text.equals("7")) {
                    byte[] whiteTextData8 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_7));
                    Intrinsics.checkNotNull(whiteTextData8);
                    return whiteTextData8;
                }
                break;
            case 56:
                if (text.equals("8")) {
                    byte[] whiteTextData9 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_8));
                    Intrinsics.checkNotNull(whiteTextData9);
                    return whiteTextData9;
                }
                break;
            case 57:
                if (text.equals("9")) {
                    byte[] whiteTextData10 = TextAgreement.getWhiteTextData(CharacterUtilsKt.getBitmapFromDrawable(App.INSTANCE.getContext(), R.mipmap.str_12_9));
                    Intrinsics.checkNotNull(whiteTextData10);
                    return whiteTextData10;
                }
                break;
        }
        return new byte[10];
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final int getTextSizeType(com.wifiled.ipixels.ui.text.vo.TextEmojiVO r7) {
        /*
            r6 = this;
            java.lang.String r0 = "textEmojiVO"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            int r0 = r7.getTextHeight()
            r1 = 16
            r2 = 7
            r3 = 5
            r4 = 3
            r5 = 1
            if (r0 == r1) goto L25
            r1 = 32
            if (r0 == r1) goto L23
            r1 = 48
            if (r0 == r1) goto L21
            r1 = 64
            if (r0 == r1) goto L1f
            goto L25
        L1f:
            r0 = r2
            goto L26
        L21:
            r0 = r3
            goto L26
        L23:
            r0 = r4
            goto L26
        L25:
            r0 = r5
        L26:
            int r1 = r7.getTextHeight()
            int r7 = r7.getTextWidth()
            int r1 = r1 / r7
            r7 = 2
            if (r1 != r7) goto L41
            if (r0 == r5) goto L3f
            if (r0 == r4) goto L3e
            if (r0 == r3) goto L3d
            if (r0 == r2) goto L3b
            goto L41
        L3b:
            r7 = 6
            return r7
        L3d:
            r7 = 4
        L3e:
            return r7
        L3f:
            r7 = 0
            return r7
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.text.process.ITextProcess.getTextSizeType(com.wifiled.ipixels.ui.text.vo.TextEmojiVO):int");
    }
}
