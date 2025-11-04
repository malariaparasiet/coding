package com.wifiled.ipixels.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.vo.DiyAnimVO;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: DiyAnimAdapter.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B'\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00020\bj\b\u0012\u0004\u0012\u00020\u0002`\t¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0002H\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR6\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001d0\u001cj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001d`\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006'"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/DiyAnimAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/vo/DiyAnimVO;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lcom/chad/library/adapter/base/module/DraggableModule;", "layoutId", "", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "<init>", "(ILjava/util/ArrayList;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "m_iSelect", "getM_iSelect", "()I", "setM_iSelect", "(I)V", "m_bEditable", "", "getM_bEditable", "()Z", "setM_bEditable", "(Z)V", "mMapMarkItemStatus", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getMMapMarkItemStatus", "()Ljava/util/HashMap;", "setMMapMarkItemStatus", "(Ljava/util/HashMap;)V", "convert", "", "holder", "item", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyAnimAdapter extends BaseQuickAdapter<DiyAnimVO, BaseViewHolder> implements DraggableModule {
    private final String TAG;
    private HashMap<Integer, Byte> mMapMarkItemStatus;
    private boolean m_bEditable;
    private int m_iSelect;

    @Override // com.chad.library.adapter.base.module.DraggableModule
    public BaseDraggableModule addDraggableModule(BaseQuickAdapter<?, ?> baseQuickAdapter) {
        return super.addDraggableModule(baseQuickAdapter);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiyAnimAdapter(int i, ArrayList<DiyAnimVO> data) {
        super(i, data);
        Intrinsics.checkNotNullParameter(data, "data");
        this.TAG = Reflection.getOrCreateKotlinClass(DiyAnimAdapter.class).getSimpleName();
        this.m_iSelect = -1;
        this.mMapMarkItemStatus = new HashMap<>();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getM_iSelect() {
        return this.m_iSelect;
    }

    public final void setM_iSelect(int i) {
        this.m_iSelect = i;
    }

    public final boolean getM_bEditable() {
        return this.m_bEditable;
    }

    public final void setM_bEditable(boolean z) {
        this.m_bEditable = z;
    }

    public final HashMap<Integer, Byte> getMMapMarkItemStatus() {
        return this.mMapMarkItemStatus;
    }

    public final void setMMapMarkItemStatus(HashMap<Integer, Byte> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.mMapMarkItemStatus = hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, DiyAnimVO item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getBitmap() != null) {
            holder.setImageBitmap(R.id.iv_anim_preview, item.getBitmap());
        } else {
            holder.setImageResource(R.id.iv_anim_preview, 0);
            holder.setText(R.id.tv_index, String.valueOf(holder.getAdapterPosition()));
            holder.setVisible(R.id.tv_index, true);
        }
        if (this.m_iSelect == getItemPosition(item) && this.m_bEditable) {
            Byte b = this.mMapMarkItemStatus.get(Integer.valueOf(this.m_iSelect));
            Integer valueOf = b != null ? Integer.valueOf((byte) (b.byteValue() & 1)) : null;
            if (valueOf != null && valueOf.intValue() == 1) {
                holder.setBackgroundResource(R.id.rl_image_outside_frame, R.mipmap.diy_icon_item_select);
                return;
            } else {
                if (valueOf != null && valueOf.intValue() == 0) {
                    holder.setBackgroundResource(R.id.rl_image_outside_frame, R.color.transparent);
                    return;
                }
                return;
            }
        }
        holder.setBackgroundResource(R.id.rl_image_outside_frame, R.color.transparent);
    }
}
