package com.wifiled.ipixels.core.text;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ResourceUtils;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextEmojiBGRUtils.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/core/text/TextEmojiBGRUtils;", "", "<init>", "()V", "emojis", "", "kotlin.jvm.PlatformType", "getEmojiBGR", "", "bitmap", "Landroid/graphics/Bitmap;", "textColor", "", "getBitmapByTextEmoji", "textEmojiVO", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "isBold", "", "getBitmapByTextEmojiPreview", "str", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiBGRUtils {
    public static final TextEmojiBGRUtils INSTANCE = new TextEmojiBGRUtils();
    private static int[] emojis = ResourceUtils.getResIds(App.INSTANCE.getContext(), R.array.text_emoji_64_preview);

    private TextEmojiBGRUtils() {
    }

    public final byte[] getEmojiBGR(Bitmap bitmap, int textColor) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int byteCount = bitmap.getByteCount();
        int width = bitmap.getWidth();
        bitmap.getHeight();
        ByteBuffer allocate = ByteBuffer.allocate(byteCount);
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        byte[] bArr = new byte[(array.length / 4) * 3];
        int length = array.length / 4;
        for (int i = 0; i < length; i++) {
            int pixel = bitmap.getPixel(i % width, i / width);
            if (pixel != 0) {
                int i2 = i * 3;
                bArr[i2] = (byte) Color.blue(pixel);
                bArr[i2 + 1] = (byte) Color.green(pixel);
                bArr[i2 + 2] = (byte) Color.red(pixel);
            }
        }
        return bArr;
    }

    public static /* synthetic */ Bitmap getBitmapByTextEmoji$default(TextEmojiBGRUtils textEmojiBGRUtils, TextEmojiVO textEmojiVO, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return textEmojiBGRUtils.getBitmapByTextEmoji(textEmojiVO, z);
    }

    public final Bitmap getBitmapByTextEmoji(TextEmojiVO textEmojiVO, boolean isBold) {
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        if (textEmojiVO.getType() == 0 || textEmojiVO.getType() == -1) {
            return TextAgreement.getCharBitmap(textEmojiVO.getText().charAt(0), textEmojiVO.getTextWidth(), textEmojiVO.getTextHeight(), textEmojiVO.getTypeFace(), textEmojiVO.getTextColor(), isBold);
        }
        return null;
    }

    public static /* synthetic */ Bitmap getBitmapByTextEmojiPreview$default(TextEmojiBGRUtils textEmojiBGRUtils, String str, TextEmojiVO textEmojiVO, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return textEmojiBGRUtils.getBitmapByTextEmojiPreview(str, textEmojiVO, z);
    }

    public final Bitmap getBitmapByTextEmojiPreview(String str, TextEmojiVO textEmojiVO, boolean isBold) {
        Intrinsics.checkNotNullParameter(str, "str");
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        if (textEmojiVO.getType() == 0 || textEmojiVO.getType() == -1) {
            return TextAgreement.getCharBitmapPreview(str, textEmojiVO.getTextWidth(), textEmojiVO.getTextHeight(), textEmojiVO.getTypeFace(), textEmojiVO.getTextColor(), isBold);
        }
        return null;
    }
}
