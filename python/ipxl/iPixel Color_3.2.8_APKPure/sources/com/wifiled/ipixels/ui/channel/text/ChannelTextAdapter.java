package com.wifiled.ipixels.ui.channel.text;

import android.content.Context;
import android.graphics.Bitmap;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.text.TextEmojiBGRUtils;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelTextAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J\u0014\u0010\u0018\u001a\u00020\u00142\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/text/ChannelTextAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "context", "Landroid/content/Context;", "data", "", "size", "", "<init>", "(Landroid/content/Context;Ljava/util/List;I)V", "mSize", "getMSize", "()I", "setMSize", "(I)V", "mShowCount", "getMShowCount", "setMShowCount", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "textEmojiVO", "addData", "getData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelTextAdapter extends RecyclerAdapter<TextEmojiVO> {
    private int mShowCount;
    private int mSize;

    public /* synthetic */ ChannelTextAdapter(Context context, List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i2 & 4) != 0 ? 3 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelTextAdapter(Context context, List<TextEmojiVO> data, int i) {
        super(context, data, R.layout.item_text);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.mSize = i;
        this.mShowCount = 1;
    }

    public final int getMSize() {
        return this.mSize;
    }

    public final void setMSize(int i) {
        this.mSize = i;
    }

    public final int getMShowCount() {
        return this.mShowCount;
    }

    public final void setMShowCount(int i) {
        this.mShowCount = i;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, TextEmojiVO textEmojiVO) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        ObjectarxItem objectarxItem = (ObjectarxItem) holder.getView(R.id.objectarxItem);
        if (this.mShowCount > 0) {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.mData.iterator();
            while (it.hasNext()) {
                sb.append(((TextEmojiVO) it.next()).getText());
            }
            TextEmojiBGRUtils textEmojiBGRUtils = TextEmojiBGRUtils.INSTANCE;
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            Bitmap bitmapByTextEmojiPreview$default = TextEmojiBGRUtils.getBitmapByTextEmojiPreview$default(textEmojiBGRUtils, sb2, textEmojiVO, false, 4, null);
            LogUtils.vTag("ruis", "bitmap size-" + (bitmapByTextEmojiPreview$default != null ? Integer.valueOf(bitmapByTextEmojiPreview$default.getWidth()) : null));
            if (bitmapByTextEmojiPreview$default != null) {
                objectarxItem.setData(bitmapByTextEmojiPreview$default, this.mSize);
                this.mShowCount--;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addData(List<TextEmojiVO> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.mData = data;
    }

    public final List<TextEmojiVO> getData() {
        List mData = this.mData;
        Intrinsics.checkNotNullExpressionValue(mData, "mData");
        return mData;
    }
}
