package com.wifiled.ipixels.ui.imgtxt;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wifiled.ipixels.R;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageTextBgAdapter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0002R\u001a\u0010\u0006\u001a\u00020\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextBgAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "<init>", "()V", "colorSelected", "getColorSelected", "()I", "setColorSelected", "(I)V", "convert", "", "holder", "item", "setSelect", PlayerFinal.PLAYER_POSITION, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextBgAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    private int colorSelected;

    public ImageTextBgAdapter() {
        super(R.layout.item_text_bg, null, 2, null);
        this.colorSelected = -1;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public /* bridge */ /* synthetic */ void convert(BaseViewHolder baseViewHolder, Integer num) {
        convert(baseViewHolder, num.intValue());
    }

    public final int getColorSelected() {
        return this.colorSelected;
    }

    public final void setColorSelected(int i) {
        this.colorSelected = i;
    }

    protected void convert(BaseViewHolder holder, int item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.colorSelected == holder.getAbsoluteAdapterPosition()) {
            holder.setVisible(R.id.iv_color, true);
        } else {
            holder.setVisible(R.id.iv_color, false);
        }
        ((ImageView) holder.getView(R.id.iv_bg)).setImageResource(item);
    }

    public final void setSelect(int position) {
        this.colorSelected = position;
        notifyDataSetChanged();
    }
}
