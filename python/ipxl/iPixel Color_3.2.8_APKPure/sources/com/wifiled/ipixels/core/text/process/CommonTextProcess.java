package com.wifiled.ipixels.core.text.process;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.core.text.TextEmojiBGRUtils;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.StringUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CommonTextProcess.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/CommonTextProcess;", "Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "isBold", "", "<init>", "(Z)V", "mIsBold", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CommonTextProcess extends ITextProcess {
    private boolean mIsBold;

    public CommonTextProcess(boolean z) {
        this.mIsBold = z;
    }

    @Override // com.wifiled.ipixels.core.text.process.ITextProcess
    public TextData textToByteData(String textStr, List<TextEmojiVO> mTexts) {
        byte[] plus;
        Intrinsics.checkNotNullParameter(textStr, "textStr");
        Intrinsics.checkNotNullParameter(mTexts, "mTexts");
        int i = 0;
        boolean containsVietnamese = CharacterUtilsKt.containsVietnamese(mTexts);
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        byte[] plus2 = ArraysKt.plus(new byte[0], getTextHead(mTexts, eventText));
        int size = mTexts.size();
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i2 < size) {
            String replace$default = StringsKt.replace$default(mTexts.get(i2).getText(), "\n", "", false, 4, (Object) null);
            if (replace$default.length() != 0) {
                if (StringUtilsKt.isNumeric(replace$default) && eventText.getTextSize() == 16) {
                    byte[] plus3 = ArraysKt.plus(new byte[i], numberToByteArr(replace$default));
                    int textColor = mTexts.get(i2).getTextColor();
                    byte textSizeType = (byte) getTextSizeType(mTexts.get(i2));
                    byte red = (byte) Color.red(textColor);
                    int i4 = i;
                    byte green = (byte) Color.green(textColor);
                    byte blue = (byte) Color.blue(textColor);
                    byte[] bArr = new byte[4];
                    bArr[i4] = textSizeType;
                    bArr[1] = red;
                    bArr[2] = green;
                    bArr[3] = blue;
                    byte[] plus4 = ArraysKt.plus(bArr, plus3);
                    if (plus2.length + plus4.length < 102400) {
                        plus = ArraysKt.plus(plus2, plus4);
                        i3++;
                        plus2 = plus;
                    } else {
                        z = true;
                    }
                } else {
                    int i5 = i;
                    LogUtils.vTag("ruis", "isContainsVietnamese -" + containsVietnamese);
                    Bitmap bitmapByTextEmoji = TextEmojiBGRUtils.INSTANCE.getBitmapByTextEmoji(mTexts.get(i2), this.mIsBold);
                    byte[] bArr2 = new byte[i5];
                    int type = mTexts.get(i2).getType();
                    if (type == 0) {
                        byte[] textData = TextAgreement.getTextData(bitmapByTextEmoji);
                        Intrinsics.checkNotNull(textData);
                        byte[] plus5 = ArraysKt.plus(bArr2, textData);
                        int textColor2 = mTexts.get(i2).getTextColor();
                        byte[] plus6 = ArraysKt.plus(new byte[]{(byte) getTextSizeType(mTexts.get(i2)), (byte) Color.red(textColor2), (byte) Color.green(textColor2), (byte) Color.blue(textColor2)}, plus5);
                        if (plus2.length + plus6.length < 102400) {
                            plus = ArraysKt.plus(plus2, plus6);
                            i3++;
                            plus2 = plus;
                        }
                        z = true;
                    } else if (type == 1) {
                        byte[] bArr3 = new byte[i5];
                        int textSize = eventText.getTextSize();
                        if (textSize == 16) {
                            bArr2 = ArraysKt.plus(bArr2, (byte) 8);
                            bArr3 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_16_" + (mTexts.get(i2).getResPosition() + 1) + ".jpg");
                            Intrinsics.checkNotNullExpressionValue(bArr3, "assetFileDescriptorToByteArray(...)");
                        } else if (textSize == 24) {
                            bArr2 = ArraysKt.plus(bArr2, (byte) 11);
                            bArr3 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_24_" + (mTexts.get(i2).getResPosition() + 1) + ".jpg");
                            Intrinsics.checkNotNullExpressionValue(bArr3, "assetFileDescriptorToByteArray(...)");
                        } else if (textSize == 32) {
                            bArr2 = ArraysKt.plus(bArr2, (byte) 9);
                            bArr3 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_32_" + (mTexts.get(i2).getResPosition() + 1) + ".jpg");
                            Intrinsics.checkNotNullExpressionValue(bArr3, "assetFileDescriptorToByteArray(...)");
                        } else if (textSize == 64) {
                            bArr2 = ArraysKt.plus(bArr2, (byte) 10);
                            bArr3 = FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "emoji/text_emoji_64_" + (mTexts.get(i2).getResPosition() + 1) + ".jpg");
                            Intrinsics.checkNotNullExpressionValue(bArr3, "assetFileDescriptorToByteArray(...)");
                        }
                        int length = bArr3.length;
                        byte[] plus7 = ArraysKt.plus(ArraysKt.plus(bArr2, new byte[]{(byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255)}), bArr3);
                        if (plus2.length + plus7.length < 102400) {
                            plus = ArraysKt.plus(plus2, plus7);
                            i3++;
                            plus2 = plus;
                        }
                        z = true;
                    }
                }
            }
            i2++;
            i = 0;
        }
        plus2[0] = (byte) (i3 & 255);
        plus2[1] = (byte) ((i3 >> 8) & 255);
        return new TextData(z, plus2);
    }
}
