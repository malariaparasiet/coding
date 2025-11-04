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
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.FileUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NewArabicTextProcess.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/NewArabicTextProcess;", "Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "<init>", "()V", "sendData", "", "isMoreThan", "", "sendTextCount", "", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "normalMove", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NewArabicTextProcess extends ITextProcess {
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
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (TextEmojiVO textEmojiVO : mTexts) {
            int i2 = i + 1;
            int type = textEmojiVO.getType();
            if (type == 0) {
                stringBuffer.append(textEmojiVO.getText());
            } else if (type == 1) {
                String stringBuffer2 = stringBuffer.toString();
                Intrinsics.checkNotNullExpressionValue(stringBuffer2, "toString(...)");
                normalMove(stringBuffer2, mTexts);
                stringBuffer = new StringBuffer();
                byte[] bArr = new byte[0];
                byte[] bArr2 = new byte[0];
                int textSize = eventText.getTextSize();
                if (textSize == 16) {
                    bArr = ArraysKt.plus(bArr, (byte) 8);
                    bArr2 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_16_" + (mTexts.get(i).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr2, "assetFileDescriptorToByteArray(...)");
                    LogUtils.vTag("ruis", "dataBuf---" + bArr2.length);
                } else if (textSize == 20) {
                    bArr = ArraysKt.plus(bArr, (byte) 12);
                    bArr2 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_20_" + (mTexts.get(i).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr2, "assetFileDescriptorToByteArray(...)");
                } else if (textSize == 24) {
                    bArr = ArraysKt.plus(bArr, (byte) 11);
                    bArr2 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_24_" + (mTexts.get(i).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr2, "assetFileDescriptorToByteArray(...)");
                } else if (textSize == 32) {
                    bArr = ArraysKt.plus(bArr, (byte) 9);
                    bArr2 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_32_" + (mTexts.get(i).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr2, "assetFileDescriptorToByteArray(...)");
                } else if (textSize == 64) {
                    bArr = ArraysKt.plus(bArr, (byte) 10);
                    bArr2 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_64_" + (mTexts.get(i).getResPosition() + 1) + ".jpg");
                    Intrinsics.checkNotNullExpressionValue(bArr2, "assetFileDescriptorToByteArray(...)");
                }
                int length = bArr2.length;
                byte[] plus = ArraysKt.plus(ArraysKt.plus(bArr, new byte[]{(byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255)}), bArr2);
                byte[] bArr3 = this.sendData;
                if (bArr3.length + plus.length < 102400) {
                    this.sendData = ArraysKt.plus(bArr3, plus);
                    this.sendTextCount++;
                } else {
                    this.isMoreThan = true;
                }
            }
            i = i2;
        }
        String stringBuffer3 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer3, "toString(...)");
        normalMove(stringBuffer3, mTexts);
        byte[] bArr4 = this.sendData;
        int i3 = this.sendTextCount;
        bArr4[0] = (byte) (i3 & 255);
        bArr4[1] = (byte) ((i3 >> 8) & 255);
        return new TextData(this.isMoreThan, this.sendData);
    }

    private final void normalMove(String textStr, List<TextEmojiVO> mTexts) {
        Log.v("ruis", "NewArabicTextProcess textStr-----" + textStr);
        if (textStr.length() > 0) {
            Bitmap charBitmapARC = TextAgreement.getCharBitmapARC(textStr, mTexts.get(0).getTextHeight(), mTexts.get(0).getTextColor());
            List<Bitmap> splitBitmap = charBitmapARC != null ? BitmapUtils.INSTANCE.splitBitmap(charBitmapARC) : null;
            if (splitBitmap != null) {
                CollectionsKt.reverse(splitBitmap);
                for (Bitmap bitmap : splitBitmap) {
                    int textColor = mTexts.get(0).getTextColor();
                    byte[] bArr = {ByteCompanionObject.MIN_VALUE, (byte) Color.red(textColor), (byte) Color.green(textColor), (byte) Color.blue(textColor), (byte) bitmap.getWidth(), (byte) bitmap.getHeight()};
                    byte[] textData = TextAgreement.getTextData(bitmap);
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
            }
        }
    }
}
