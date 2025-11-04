package com.wifiled.ipixels.ui.imgtxt;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextAnimationEffectAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "layoutId", "", "<init>", "(I)V", "convert", "", "holder", "item", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAnimationEffectAdapter extends BaseQuickAdapter<TextAnimationEffectBean, BaseViewHolder> {
    public TextAnimationEffectAdapter(int i) {
        super(i, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, TextAnimationEffectBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) holder.getView(R.id.iv_anima);
        RelativeLayout relativeLayout = (RelativeLayout) holder.getView(R.id.rl_bg);
        Glide.with(holder.itemView).load(Integer.valueOf(item.getResourceId())).into(imageView);
        if (item.isSelect()) {
            relativeLayout.setBackgroundResource(R.drawable.text_animation_select);
        } else {
            relativeLayout.setBackgroundResource(0);
        }
    }
}
