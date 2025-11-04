package com.wifiled.ipixels.view;

import android.content.Context;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ResourceUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextEmojiView.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010*\u001a\u00020\rH\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0002H\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001a\u0010!\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001a\u0010$\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001a\u0010'\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011¨\u00060"}, d2 = {"Lcom/wifiled/ipixels/view/TextEmojiAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "emojis", "", "kotlin.jvm.PlatformType", "emojiViewLen", "", "getEmojiViewLen", "()I", "setEmojiViewLen", "(I)V", "mItemCount", "getMItemCount", "setMItemCount", "mPointCount", "getMPointCount", "setMPointCount", "mToatalDp", "", "getMToatalDp", "()F", "setMToatalDp", "(F)V", "textVal", "getTextVal", "setTextVal", "pxValue", "getPxValue", "setPxValue", "dpValue", "getDpValue", "setDpValue", "emojiVal", "getEmojiVal", "setEmojiVal", "layoutId", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "textEmoji", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiAdapter extends RecyclerAdapter<TextEmojiVO> {
    private int dpValue;
    private int emojiVal;
    private int emojiViewLen;
    private final int[] emojis;
    private int mItemCount;
    private int mPointCount;
    private float mToatalDp;
    private int pxValue;
    private int textVal;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_text_emoji;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextEmojiAdapter(Context context, List<TextEmojiVO> data) {
        super(context, data);
        int dp;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.emojis = ResourceUtils.getResIds(context, R.array.text_emoji_64_preview);
        this.mPointCount = 3;
        float scale = ScreenUtil.getScale(this.mContext);
        this.pxValue = scale == 3.0f ? 65 : scale == 3.5f ? 80 : scale == 2.0f ? 70 : 75;
        float scale2 = ScreenUtil.getScale(this.mContext);
        if (scale2 == 3.0f) {
            dp = UtilsExtensionKt.toDp(90);
        } else if (scale2 == 3.5f) {
            dp = UtilsExtensionKt.toDp(80);
        } else if (scale2 == 2.0f) {
            dp = UtilsExtensionKt.toDp(70);
        } else {
            dp = UtilsExtensionKt.toDp(70);
        }
        this.dpValue = dp;
        this.emojiVal = ScreenUtil.px2dp(ContextHolder.getContext(), this.pxValue);
    }

    public final int getEmojiViewLen() {
        return this.emojiViewLen;
    }

    public final void setEmojiViewLen(int i) {
        this.emojiViewLen = i;
    }

    public final int getMItemCount() {
        return this.mItemCount;
    }

    public final void setMItemCount(int i) {
        this.mItemCount = i;
    }

    public final int getMPointCount() {
        return this.mPointCount;
    }

    public final void setMPointCount(int i) {
        this.mPointCount = i;
    }

    public final float getMToatalDp() {
        return this.mToatalDp;
    }

    public final void setMToatalDp(float f) {
        this.mToatalDp = f;
    }

    public final int getTextVal() {
        return this.textVal;
    }

    public final void setTextVal(int i) {
        this.textVal = i;
    }

    public final int getPxValue() {
        return this.pxValue;
    }

    public final void setPxValue(int i) {
        this.pxValue = i;
    }

    public final int getDpValue() {
        return this.dpValue;
    }

    public final void setDpValue(int i) {
        this.dpValue = i;
    }

    public final int getEmojiVal() {
        return this.emojiVal;
    }

    public final void setEmojiVal(int i) {
        this.emojiVal = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(com.wifiled.baselib.base.recycleview.RecyclerViewHolder r11, com.wifiled.ipixels.ui.text.vo.TextEmojiVO r12) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.view.TextEmojiAdapter.convert(com.wifiled.baselib.base.recycleview.RecyclerViewHolder, com.wifiled.ipixels.ui.text.vo.TextEmojiVO):void");
    }
}
