package com.wifiled.ipixels.core.text.process;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ArabicUtils;
import com.wifiled.ipixels.utils.FileUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NewThaiTextProcess.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/NewThaiTextProcess;", "Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "<init>", "()V", "sendData", "", "isMoreThan", "", "sendTextCount", "", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "normalMove", "", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NewThaiTextProcess extends ITextProcess {
    private boolean isMoreThan;
    private byte[] sendData = new byte[0];
    private int sendTextCount;

    @Override // com.wifiled.ipixels.core.text.process.ITextProcess
    public TextData textToByteData(String textStr, List<TextEmojiVO> mTexts) {
        Intrinsics.checkNotNullParameter(textStr, "textStr");
        Intrinsics.checkNotNullParameter(mTexts, "mTexts");
        this.sendData = new byte[0];
        this.isMoreThan = false;
        this.sendTextCount = 0;
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        this.sendData = ArraysKt.plus(this.sendData, getTextHead(mTexts, eventText));
        normalMove(textStr, mTexts, eventText);
        byte[] bArr = this.sendData;
        int i = this.sendTextCount;
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        return new TextData(this.isMoreThan, this.sendData);
    }

    private final void normalMove(String textStr, List<TextEmojiVO> mTexts, EventText eventText) {
        int i;
        Log.v("ruis", "NewThaiTextProcess textStr-----" + textStr);
        int length = textStr.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            char charAt = textStr.charAt(i3);
            int type = mTexts.get(i3).getType();
            if (type == 0) {
                i = i2;
                if (ArabicUtils.isThai(String.valueOf(charAt)) && !ArabicUtils.isSup_SubThai(String.valueOf(charAt))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(charAt);
                    int i4 = i3 + 1;
                    if (i4 < textStr.length() && ArabicUtils.isSup_SubThai(String.valueOf(textStr.charAt(i4)))) {
                        sb.append(textStr.charAt(i4));
                        int i5 = i3 + 2;
                        if (i5 < textStr.length() && ArabicUtils.isSup_SubThai(String.valueOf(textStr.charAt(i5)))) {
                            sb.append(textStr.charAt(i5));
                        }
                    }
                    Bitmap charBitmapTHAI = TextAgreement.getCharBitmapTHAI(sb.toString(), mTexts.get(i).getTextHeight(), mTexts.get(i).getTextColor());
                    int textColor = mTexts.get(i).getTextColor();
                    i = 0;
                    byte[] bArr = {ByteCompanionObject.MIN_VALUE, (byte) Color.red(textColor), (byte) Color.green(textColor), (byte) Color.blue(textColor), (byte) charBitmapTHAI.getWidth(), (byte) charBitmapTHAI.getHeight()};
                    byte[] textData = TextAgreement.getTextData(charBitmapTHAI);
                    Intrinsics.checkNotNull(textData);
                    byte[] plus = ArraysKt.plus(bArr, textData);
                    byte[] bArr2 = this.sendData;
                    if (bArr2.length + plus.length < 102400) {
                        this.sendData = ArraysKt.plus(bArr2, plus);
                        this.sendTextCount++;
                    } else {
                        this.isMoreThan = true;
                    }
                }
            } else if (type != 1) {
                i = i2;
            } else {
                byte[] bArr3 = new byte[i2];
                byte[] bArr4 = new byte[i2];
                int textSize = eventText.getTextSize();
                if (textSize == 16) {
                    i = i2;
                    bArr3 = ArraysKt.plus(bArr3, (byte) 8);
                    bArr4 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_16_" + (mTexts.get(i3).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr4, "assetFileDescriptorToByteArray(...)");
                    LogUtils.vTag("ruis", "dataBuf---" + bArr4.length);
                } else if (textSize == 20) {
                    i = i2;
                    bArr3 = ArraysKt.plus(bArr3, (byte) 12);
                    bArr4 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_20_" + (mTexts.get(i3).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr4, "assetFileDescriptorToByteArray(...)");
                } else if (textSize == 24) {
                    i = i2;
                    bArr3 = ArraysKt.plus(bArr3, (byte) 11);
                    bArr4 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_24_" + (mTexts.get(i3).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr4, "assetFileDescriptorToByteArray(...)");
                } else if (textSize == 32) {
                    i = i2;
                    bArr3 = ArraysKt.plus(bArr3, (byte) 9);
                    bArr4 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_32_" + (mTexts.get(i3).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr4, "assetFileDescriptorToByteArray(...)");
                } else if (textSize != 64) {
                    i = i2;
                } else {
                    bArr3 = ArraysKt.plus(bArr3, (byte) 10);
                    i = i2;
                    bArr4 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_64_" + (mTexts.get(i3).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr4, "assetFileDescriptorToByteArray(...)");
                }
                int length2 = bArr4.length;
                byte[] bArr5 = new byte[3];
                bArr5[i] = (byte) (length2 & 255);
                bArr5[1] = (byte) ((length2 >> 8) & 255);
                bArr5[2] = (byte) ((length2 >> 16) & 255);
                byte[] plus2 = ArraysKt.plus(ArraysKt.plus(bArr3, bArr5), bArr4);
                byte[] bArr6 = this.sendData;
                if (bArr6.length + plus2.length < 102400) {
                    this.sendData = ArraysKt.plus(bArr6, plus2);
                    this.sendTextCount++;
                } else {
                    this.isMoreThan = true;
                }
            }
            i3++;
            i2 = i;
        }
    }
}
