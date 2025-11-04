package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextEmojiAdapter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/TextEmojiAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "t", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiAdapter extends RecyclerAdapter<Integer> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextEmojiAdapter(Context context, List<Integer> data) {
        super(context, data, R.layout.item_emoji_image);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
        convert(recyclerViewHolder, num.intValue());
    }

    public void convert(RecyclerViewHolder holder, int t) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.setImageResource(R.id.iv_emoji, t);
    }
}
