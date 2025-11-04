package com.wifiled.ipixels.ui.text;

import android.content.Context;
import androidx.appcompat.content.res.AppCompatResources;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0013\u001a\u00020\u0002H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0002H\u0016R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0019"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAttrEmojiAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "mListData", "getMListData", "()Ljava/util/List;", "setMListData", "(Ljava/util/List;)V", "adContext", "getAdContext", "()Landroid/content/Context;", "setAdContext", "(Landroid/content/Context;)V", "layoutId", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "bean", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAttrEmojiAdapter extends RecyclerAdapter<Integer> {
    private Context adContext;
    private List<Integer> mListData;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_emji;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
        convert(recyclerViewHolder, num.intValue());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextAttrEmojiAdapter(Context context, List<Integer> data) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.mListData = data;
        this.adContext = context;
    }

    public final List<Integer> getMListData() {
        return this.mListData;
    }

    public final void setMListData(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mListData = list;
    }

    public final Context getAdContext() {
        return this.adContext;
    }

    public final void setAdContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.adContext = context;
    }

    public void convert(RecyclerViewHolder holder, int bean) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.setImageDrawable(R.id.iv_emoji, AppCompatResources.getDrawable(this.adContext, this.mListData.get(holder.getAbsoluteAdapterPosition()).intValue()));
    }
}
