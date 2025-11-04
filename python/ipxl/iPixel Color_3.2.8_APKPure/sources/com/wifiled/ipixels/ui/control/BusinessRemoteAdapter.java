package com.wifiled.ipixels.ui.control;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.text.TextEmojiBGRUtils;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;
import com.wifiled.ipixels.ui.channel.text.TextAnimUtils;
import com.wifiled.ipixels.ui.text.XFlexboxLayoutManager;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.BGRUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BusinessRemoteAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0002H\u0015J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/wifiled/ipixels/ui/control/BusinessRemoteAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/control/BusinessRemoteData;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "layout", "", "<init>", "(I)V", "mNumImgData", "", "getMNumImgData", "()[Ljava/lang/Integer;", "setMNumImgData", "([Ljava/lang/Integer;)V", "[Ljava/lang/Integer;", "mIsEdit", "", "setEditStatus", "", "isEdit", "convert", "holder", "item", "loadEffect", "Landroid/animation/ObjectAnimator;", "ins", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "it", "Lcom/wifiled/ipixels/event/EventText;", "rlItem", "Landroidx/recyclerview/widget/RecyclerView;", "setHorizontalAlign", "justifyContent", "setVerticalAlign", "align", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BusinessRemoteAdapter extends BaseQuickAdapter<BusinessRemoteData, BaseViewHolder> {
    private boolean mIsEdit;
    private Integer[] mNumImgData;

    public BusinessRemoteAdapter(int i) {
        super(i, null, 2, null);
        this.mNumImgData = new Integer[]{Integer.valueOf(R.mipmap.remote_num_1), Integer.valueOf(R.mipmap.remote_num_2), Integer.valueOf(R.mipmap.remote_num_3), Integer.valueOf(R.mipmap.remote_num_4), Integer.valueOf(R.mipmap.remote_num_5), Integer.valueOf(R.mipmap.remote_num_6), Integer.valueOf(R.mipmap.remote_num_7), Integer.valueOf(R.mipmap.remote_num_8), Integer.valueOf(R.mipmap.remote_num_9)};
    }

    public final Integer[] getMNumImgData() {
        return this.mNumImgData;
    }

    public final void setMNumImgData(Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<set-?>");
        this.mNumImgData = numArr;
    }

    public final void setEditStatus(boolean isEdit) {
        this.mIsEdit = isEdit;
        notifyItemRangeChanged(0, getData().size());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, BusinessRemoteData item) {
        EventText eventText;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.isDefault()) {
            holder.setVisible(R.id.iv_anim_preview, true);
            holder.setVisible(R.id.relayout_channel_text, false);
            Intrinsics.checkNotNull(Glide.with(holder.itemView).load(item.getEditByteData()).into((ImageView) holder.getView(R.id.iv_anim_preview)));
        } else {
            int editResourceType = item.getEditResourceType();
            if (editResourceType != 1) {
                int i = 2;
                if (editResourceType == 2) {
                    holder.setVisible(R.id.iv_anim_preview, true);
                    holder.setVisible(R.id.relayout_channel_text, false);
                    if (AppConfig.INSTANCE.getLedType() == 16) {
                        Glide.with(holder.itemView).load(BGRUtils.RGB2bitmap(item.getEditByteData())).into((ImageView) holder.getView(R.id.iv_anim_preview));
                    } else {
                        Glide.with(holder.itemView).load(item.getEditByteData()).into((ImageView) holder.getView(R.id.iv_anim_preview));
                    }
                } else if (editResourceType == 3) {
                    holder.setVisible(R.id.iv_anim_preview, false);
                    ObjectarxItem objectarxItem = (ObjectarxItem) holder.itemView.findViewById(R.id.objectarxItem);
                    ChannelListItem.TextEmojView textEmojiView = item.getTextEmojiView();
                    RelativeLayout relativeLayout = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
                    Intrinsics.checkNotNull(relativeLayout);
                    UtilsExtensionKt.show(relativeLayout);
                    StringBuilder sb = new StringBuilder();
                    List<TextEmojiVO> textEmojiVO = (textEmojiView == null || (eventText = textEmojiView.getEventText()) == null) ? null : eventText.getTextEmojiVO();
                    Intrinsics.checkNotNull(textEmojiVO);
                    Iterator<TextEmojiVO> it = textEmojiVO.iterator();
                    while (it.hasNext()) {
                        sb.append(it.next().getText());
                    }
                    TextEmojiBGRUtils textEmojiBGRUtils = TextEmojiBGRUtils.INSTANCE;
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                    Bitmap bitmapByTextEmojiPreview$default = TextEmojiBGRUtils.getBitmapByTextEmojiPreview$default(textEmojiBGRUtils, sb2, textEmojiView.getEventText().getTextEmojiVO().get(0), false, 4, null);
                    LogUtils.vTag("ruis", "bitmap size-" + (bitmapByTextEmojiPreview$default != null ? Integer.valueOf(bitmapByTextEmojiPreview$default.getWidth()) : null) + "  stringBuilder---" + ((Object) sb));
                    if (bitmapByTextEmojiPreview$default != null) {
                        int i2 = 4;
                        int i3 = AppConfig.INSTANCE.getLedType() == 1 ? 6 : 4;
                        if (AppConfig.INSTANCE.getLedType() != 7 && AppConfig.INSTANCE.getLedType() != 8) {
                            i2 = i3;
                        }
                        if (AppConfig.INSTANCE.getLedType() != 18 && AppConfig.INSTANCE.getLedType() != 17 && AppConfig.INSTANCE.getLedType() != 19 && AppConfig.INSTANCE.getLedType() != 16) {
                            i = i2;
                        }
                        objectarxItem.setData(bitmapByTextEmojiPreview$default, i);
                    }
                    relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteAdapter$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            boolean convert$lambda$1;
                            convert$lambda$1 = BusinessRemoteAdapter.convert$lambda$1(BusinessRemoteAdapter.this, holder, view, motionEvent);
                            return convert$lambda$1;
                        }
                    });
                }
            } else {
                holder.setVisible(R.id.iv_anim_preview, true);
                holder.setVisible(R.id.relayout_channel_text, false);
                Glide.with(holder.itemView).load(item.getEditByteData()).into((ImageView) holder.getView(R.id.iv_anim_preview));
            }
        }
        holder.setVisible(R.id.iv_anim_edit, this.mIsEdit);
        ((ImageView) holder.getView(R.id.iv_num)).setImageResource(this.mNumImgData[holder.getBindingAdapterPosition()].intValue());
        holder.setVisible(R.id.iv_anim_select, item.isSelect());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean convert$lambda$1(BusinessRemoteAdapter businessRemoteAdapter, BaseViewHolder baseViewHolder, View view, MotionEvent motionEvent) {
        View itemView = baseViewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        businessRemoteAdapter.setOnItemClick(itemView, baseViewHolder.getLayoutPosition());
        return false;
    }

    private final ObjectAnimator loadEffect(TextAnimUtils ins, EventText it, RecyclerView rlItem) {
        rlItem.setLayoutManager(null);
        int textEffect = it.getTextEffect();
        if (textEffect == 1 || textEffect == 2) {
            final Context context = getContext();
            final boolean z = 2 == it.getTextEffect();
            Object obj = new WeakReference(new LinearLayoutManager(context, z) { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteAdapter$loadEffect$layoutManager$1
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return true;
                }
            }).get();
            Intrinsics.checkNotNull(obj);
            rlItem.setLayoutManager((RecyclerView.LayoutManager) obj);
        } else {
            final Context context2 = getContext();
            WeakReference weakReference = new WeakReference(new XFlexboxLayoutManager(context2) { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteAdapter$loadEffect$layoutManager$2
                @Override // com.google.android.flexbox.FlexboxLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return false;
                }

                @Override // com.google.android.flexbox.FlexboxLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollVertically() {
                    return false;
                }
            });
            int textHorizontalAlign = it.getTextHorizontalAlign();
            if (textHorizontalAlign == 0) {
                FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) weakReference.get();
                if (flexboxLayoutManager != null) {
                    flexboxLayoutManager.setJustifyContent(0);
                }
            } else if (textHorizontalAlign == 1) {
                FlexboxLayoutManager flexboxLayoutManager2 = (FlexboxLayoutManager) weakReference.get();
                if (flexboxLayoutManager2 != null) {
                    flexboxLayoutManager2.setJustifyContent(2);
                }
            } else {
                FlexboxLayoutManager flexboxLayoutManager3 = (FlexboxLayoutManager) weakReference.get();
                if (flexboxLayoutManager3 != null) {
                    flexboxLayoutManager3.setJustifyContent(1);
                }
            }
            Object obj2 = weakReference.get();
            Intrinsics.checkNotNull(obj2);
            rlItem.setLayoutManager((RecyclerView.LayoutManager) obj2);
        }
        return ins.loadAnim(rlItem, it.getTextEffect());
    }

    private final void setHorizontalAlign(int justifyContent, RecyclerView rlItem) {
        XFlexboxLayoutManager xFlexboxLayoutManager = new XFlexboxLayoutManager(getContext());
        xFlexboxLayoutManager.setFlexDirection(0);
        xFlexboxLayoutManager.setJustifyContent(justifyContent);
        rlItem.setLayoutManager(xFlexboxLayoutManager);
    }

    private final void setVerticalAlign(int align, RecyclerView rlItem) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(align);
        rlItem.setLayoutParams(layoutParams);
    }
}
