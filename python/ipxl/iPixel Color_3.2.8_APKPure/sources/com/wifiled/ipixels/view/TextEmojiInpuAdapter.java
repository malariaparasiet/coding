package com.wifiled.ipixels.view;

import android.content.Context;
import android.widget.TextView;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.ResourceUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextEmojiViewAttr.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\"\u001a\u00020\bH\u0016J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0002H\u0016R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012¨\u0006("}, d2 = {"Lcom/wifiled/ipixels/view/TextEmojiInpuAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "context", "Landroid/content/Context;", "data", "", "type", "", "<init>", "(Landroid/content/Context;Ljava/util/List;I)V", "emojis", "", "kotlin.jvm.PlatformType", "mTextPage", "getMTextPage", "()I", "setMTextPage", "(I)V", "mItemCount", "getMItemCount", "setMItemCount", "mToatalDp", "", "getMToatalDp", "()F", "setMToatalDp", "(F)V", "dpVal", "getDpVal", "setDpVal", "emojiVal", "getEmojiVal", "setEmojiVal", "layoutId", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "textEmoji", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiInpuAdapter extends RecyclerAdapter<TextEmojiVO> {
    private int dpVal;
    private int emojiVal;
    private final int[] emojis;
    private int mItemCount;
    private int mTextPage;
    private float mToatalDp;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_text_emoji_input;
    }

    public /* synthetic */ TextEmojiInpuAdapter(Context context, List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i2 & 4) != 0 ? AppConfig.SCENES.SCENES_TEXT_RECORD.ordinal() : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextEmojiInpuAdapter(Context context, List<TextEmojiVO> data, int i) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.emojis = ResourceUtils.getResIds(context, R.array.text_emoji_64_preview);
        this.mTextPage = i;
        this.emojiVal = ScreenUtil.px2dp(ContextHolder.getContext(), 60.0f);
    }

    public final int getMTextPage() {
        return this.mTextPage;
    }

    public final void setMTextPage(int i) {
        this.mTextPage = i;
    }

    public final int getMItemCount() {
        return this.mItemCount;
    }

    public final void setMItemCount(int i) {
        this.mItemCount = i;
    }

    public final float getMToatalDp() {
        return this.mToatalDp;
    }

    public final void setMToatalDp(float f) {
        this.mToatalDp = f;
    }

    public final int getDpVal() {
        return this.dpVal;
    }

    public final void setDpVal(int i) {
        this.dpVal = i;
    }

    public final int getEmojiVal() {
        return this.emojiVal;
    }

    public final void setEmojiVal(int i) {
        this.emojiVal = i;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, TextEmojiVO textEmoji) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(textEmoji, "textEmoji");
        String text = textEmoji.getText();
        boolean z = false;
        for (int i = 0; i < text.length(); i++) {
            z = CharacterUtilsKt.isChinese(text.charAt(i));
        }
        TextView textView = (TextView) holder.getConvertView().findViewById(R.id.tv_text);
        if (this.dpVal == 0) {
            this.dpVal = ScreenUtil.px2dp(ContextHolder.getContext(), textView.getTextSize());
        }
        this.mItemCount++;
        if (textEmoji.getType() == 0 || textEmoji.getType() == -1) {
            holder.setVisible(R.id.iv_emoji, false);
            holder.setVisible(R.id.tv_text, true);
            holder.setText(R.id.tv_text, textEmoji.getText());
            float f = this.mToatalDp;
            float f2 = this.dpVal;
            if (!z) {
                f2 /= 2.0f;
            }
            this.mToatalDp = f + f2;
        } else {
            holder.setVisible(R.id.iv_emoji, true);
            holder.setVisible(R.id.tv_text, false);
            holder.setImageResource(R.id.iv_emoji, this.emojis[textEmoji.getResPosition()]);
            this.mToatalDp += this.emojiVal;
        }
        if (this.mItemCount == getItemCount()) {
            this.mItemCount = 0;
            this.mToatalDp = 0.0f;
        }
    }
}
