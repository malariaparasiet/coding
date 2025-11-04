package com.wifiled.ipixels.ui.subzone;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextBorderAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/TextBorderAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/subzone/TextBorderData;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "layout", "", "<init>", "(I)V", "convert", "", "holder", "item", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextBorderAdapter extends BaseQuickAdapter<TextBorderData, BaseViewHolder> {
    public TextBorderAdapter(int i) {
        super(i, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, TextBorderData item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.bg_layout);
        Glide.with(holder.itemView).load(Integer.valueOf(item.getBorderRes())).into((AppCompatImageView) holder.getView(R.id.iv_border));
        constraintLayout.setSelected(item.isSelect());
    }
}
