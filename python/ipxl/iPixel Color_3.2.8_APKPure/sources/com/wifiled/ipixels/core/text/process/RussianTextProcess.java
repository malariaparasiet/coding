package com.wifiled.ipixels.core.text.process;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.wifiled.ipixels.core.text.TextAgreement;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RussianTextProcess.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/core/text/process/RussianTextProcess;", "Lcom/wifiled/ipixels/core/text/process/ITextProcess;", "<init>", "()V", "sendData", "", "isMoreThan", "", "sendTextCount", "", "textToByteData", "Lcom/wifiled/ipixels/core/text/process/TextData;", "textStr", "", "mTexts", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "normalMove", "", "eventText", "Lcom/wifiled/ipixels/event/EventText;", "getBitMaps", "", "Landroid/graphics/Bitmap;", "largeBitmap", "count", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RussianTextProcess extends ITextProcess {
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
        Bitmap charBitmap = TextAgreement.getCharBitmap(textStr, mTexts.get(0).getTextWidth() * textStr.length(), mTexts.get(0).getTextHeight(), mTexts.get(0).getTypeFace(), mTexts.get(0).getTextColor());
        int textColor = mTexts.get(0).getTextColor();
        byte[] bArr = {(byte) getTextSizeType(mTexts.get(0)), (byte) Color.red(textColor), (byte) Color.green(textColor), (byte) Color.blue(textColor)};
        Log.e("ruis", "bitmap=" + charBitmap.getWidth() + "*" + charBitmap.getHeight());
        Intrinsics.checkNotNull(charBitmap);
        Iterator<T> it = getBitMaps(charBitmap, textStr.length()).iterator();
        while (it.hasNext()) {
            byte[] textData = TextAgreement.getTextData((Bitmap) it.next());
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

    private final List<Bitmap> getBitMaps(Bitmap largeBitmap, int count) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            Bitmap createBitmap = Bitmap.createBitmap(largeBitmap, i * 16, 0, 16, 16);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            arrayList.add(createBitmap);
        }
        return CollectionsKt.toList(arrayList);
    }
}
