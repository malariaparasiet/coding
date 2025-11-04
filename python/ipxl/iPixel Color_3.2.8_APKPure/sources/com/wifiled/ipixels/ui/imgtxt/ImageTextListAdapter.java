package com.wifiled.ipixels.ui.imgtxt;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageTextListAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\tJ\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListData;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "<init>", "()V", "isEditStatus", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListAdapter$SizeChangeListener;", "setEditStatus", "", "editStatus", "setSizeChangeListener", "sizeChange", "convert", "holder", "item", "getBg", "", "index", "SizeChangeListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextListAdapter extends BaseQuickAdapter<ImageTextListData, BaseViewHolder> {
    private boolean isEditStatus;
    private SizeChangeListener listener;

    /* compiled from: ImageTextListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListAdapter$SizeChangeListener;", "", "sizeChange", "", "size", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SizeChangeListener {
        void sizeChange(int size);
    }

    private final int getBg(int index) {
        switch (index) {
            case 1:
                return R.mipmap.dc_phrase_phrase_m_2;
            case 2:
                return R.mipmap.dc_phrase_phrase_m_3;
            case 3:
                return R.mipmap.dc_phrase_phrase_m_4;
            case 4:
                return R.mipmap.dc_phrase_phrase_m_5;
            case 5:
                return R.mipmap.dc_phrase_phrase_m_6;
            case 6:
                return R.mipmap.dc_phrase_phrase_m_7;
            case 7:
                return R.mipmap.dc_phrase_phrase_m_8;
            default:
                return R.mipmap.dc_phrase_phrase_m_1;
        }
    }

    public ImageTextListAdapter() {
        super(R.layout.image_text_list_item, null, 2, null);
    }

    public final void setEditStatus(boolean editStatus) {
        this.isEditStatus = editStatus;
        notifyDataSetChanged();
    }

    public final void setSizeChangeListener(SizeChangeListener sizeChange) {
        Intrinsics.checkNotNullParameter(sizeChange, "sizeChange");
        this.listener = sizeChange;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, ImageTextListData item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (holder.getLayoutPosition() == getData().size() - 1) {
            holder.setImageResource(R.id.iv_schedule, R.drawable.phrase_phrase_add);
            holder.setVisible(R.id.iv_schedule, true);
            holder.setVisible(R.id.tv_content, false);
            holder.setVisible(R.id.iv_schedule_select, false);
            SizeChangeListener sizeChangeListener = this.listener;
            if (sizeChangeListener != null) {
                sizeChangeListener.sizeChange(getData().size());
            }
        } else {
            holder.setVisible(R.id.iv_schedule, false);
            holder.setVisible(R.id.tv_content, true);
            holder.setText(R.id.tv_content, item.getItName());
            holder.setBackgroundResource(R.id.tv_content, getBg(item.getBgColor()));
            holder.setVisible(R.id.iv_schedule_select, false);
        }
        if (this.isEditStatus) {
            if (holder.getLayoutPosition() == getData().size() - 1) {
                holder.setVisible(R.id.iv_schedule_select, false);
                holder.itemView.setVisibility(8);
            } else {
                holder.setVisible(R.id.iv_schedule_select, true);
                holder.itemView.setVisibility(0);
            }
            if (item.isEdit()) {
                holder.setImageResource(R.id.iv_schedule_select, R.drawable.it_select);
                return;
            } else {
                holder.setImageResource(R.id.iv_schedule_select, R.drawable.it_unselect);
                return;
            }
        }
        holder.itemView.setVisibility(0);
        Unit unit = Unit.INSTANCE;
    }
}
