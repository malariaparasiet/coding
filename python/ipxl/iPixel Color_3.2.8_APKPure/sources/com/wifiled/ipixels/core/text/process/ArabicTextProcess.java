package com.wifiled.ipixels.core.text.process;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.FileUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ArabicTextProcess.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/ArabicTextProcess;", "Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "<init>", "()V", "sendData", "", "isMoreThan", "", "sendTextCount", "", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "normalMove", "", "splitBitmap", "Landroid/graphics/Bitmap;", "originalBitmap", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ArabicTextProcess extends ITextProcess {
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
        Log.v("ruis", "textStr-----" + textStr);
        if (textStr.length() > 0) {
            Bitmap charBitmapARC = TextAgreement.getCharBitmapARC(textStr, mTexts.get(0).getTextHeight(), mTexts.get(0).getTextColor());
            Log.e("ruis", "bitmap=" + charBitmapARC.getWidth() + "*" + charBitmapARC.getHeight());
            Intrinsics.checkNotNull(charBitmapARC);
            List<Bitmap> splitBitmap = splitBitmap(charBitmapARC);
            CollectionsKt.reverse(splitBitmap);
            Iterator<T> it = splitBitmap.iterator();
            while (it.hasNext()) {
                byte[] textData = TextAgreement.getTextData((Bitmap) it.next());
                Intrinsics.checkNotNull(textData);
                byte[] plus = ArraysKt.plus(new byte[0], textData);
                int textColor = mTexts.get(0).getTextColor();
                LogUtils.vTag("ruis", "mTexts[0]=" + mTexts.get(0).getTextWidth() + "*" + mTexts.get(0).getTextHeight());
                byte[] plus2 = ArraysKt.plus(new byte[]{(byte) getTextSizeType(mTexts.get(0)), (byte) Color.red(textColor), (byte) Color.green(textColor), (byte) Color.blue(textColor)}, plus);
                byte[] bArr = this.sendData;
                if (bArr.length + plus2.length < 102400) {
                    this.sendData = ArraysKt.plus(bArr, plus2);
                    this.sendTextCount++;
                } else {
                    this.isMoreThan = true;
                }
            }
        }
    }

    private final List<Bitmap> splitBitmap(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        int height2 = originalBitmap.getHeight();
        int ceil = (int) Math.ceil(width / height2);
        ArrayList arrayList = new ArrayList();
        LogUtils.vTag("ruis", "numberOfBitmaps--" + ceil);
        int i = 0;
        for (int i2 = 0; i2 < ceil; i2++) {
            Bitmap createBitmap = Bitmap.createBitmap(height2, height, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(originalBitmap, -i, 0.0f, new Paint());
            arrayList.add(createBitmap);
            i += height2;
        }
        LogUtils.vTag("ruis", "originalWidth--" + width, "currentWidth--" + i);
        return arrayList;
    }
}
