package com.wifiled.ipixels.ui.subzone;

import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFive12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewThree12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewTwo12;
import com.wifiled.ipixels.utils.ResourceUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubzoneListAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001.B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001eJ\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0016J\u000e\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020 J\u0010\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0003H\u0002J\u0018\u0010+\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0002H\u0014J\b\u0010-\u001a\u00020\"H\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SubzoneListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "layout", "", "<init>", "(I)V", "mBorder1_6", "", "mBorder1_2", "mBorder1_3", "mBorder1_4", "mBorder1_8", "mBorder1_7", "mBorder1_5", "mBorder1_9", "mBorder1_10", "mBorder1_12", "mBorder1_11", "mBorder1_13", "mBorder1_14", "mBorder1_16", "mBorder1_18", "mBorder1_20", "mBorder1_22", "mBorder1_24", "mBorder1_26", "mBorder1_28", "isEditStatus", "", "mListener", "Lcom/wifiled/ipixels/ui/subzone/SubzoneListAdapter$ItemOnClickListener;", "setEditStatus", "", "editStatus", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "setItemOnClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "goneAll", "holder", "convert", "item", "initBorderData", "ItemOnClickListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubzoneListAdapter extends BaseQuickAdapter<SubzoneData, BaseViewHolder> {
    private boolean isEditStatus;
    private int[] mBorder1_10;
    private int[] mBorder1_11;
    private int[] mBorder1_12;
    private int[] mBorder1_13;
    private int[] mBorder1_14;
    private int[] mBorder1_16;
    private int[] mBorder1_18;
    private int[] mBorder1_2;
    private int[] mBorder1_20;
    private int[] mBorder1_22;
    private int[] mBorder1_24;
    private int[] mBorder1_26;
    private int[] mBorder1_28;
    private int[] mBorder1_3;
    private int[] mBorder1_4;
    private int[] mBorder1_5;
    private int[] mBorder1_6;
    private int[] mBorder1_7;
    private int[] mBorder1_8;
    private int[] mBorder1_9;
    private ItemOnClickListener mListener;

    /* compiled from: SubzoneListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SubzoneListAdapter$ItemOnClickListener;", "", "itemClick", "", "sizeChange", "size", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ItemOnClickListener {
        void itemClick();

        void sizeChange(int size);
    }

    public SubzoneListAdapter(int i) {
        super(i, null, 2, null);
    }

    public final void setEditStatus(boolean editStatus) {
        this.isEditStatus = editStatus;
        notifyDataSetChanged();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        initBorderData();
    }

    public final void setItemOnClickListener(ItemOnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListener = listener;
    }

    private final void goneAll(BaseViewHolder holder) {
        holder.setGone(R.id.template1, true);
        holder.setGone(R.id.template2, true);
        holder.setGone(R.id.template3, true);
        holder.setGone(R.id.template4, true);
        holder.setGone(R.id.template5, true);
        holder.setGone(R.id.template6, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, SubzoneData item) {
        Integer valueOf;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        goneAll(holder);
        if (holder.getLayoutPosition() == getData().size() - 1) {
            holder.setGone(R.id.iv_schedule, false);
            holder.setGone(R.id.image, true);
            holder.setGone(R.id.iv_schedule_select, true);
            holder.setGone(R.id.template_3264, true);
            ItemOnClickListener itemOnClickListener = this.mListener;
            if (itemOnClickListener != null) {
                itemOnClickListener.sizeChange(getData().size());
                Unit unit = Unit.INSTANCE;
            }
        } else {
            holder.setGone(R.id.template_3264, false);
            int templateType = item.getTemplateType();
            if (templateType == 1) {
                TemplateViewOne12 templateViewOne12 = (TemplateViewOne12) holder.getView(R.id.template1);
                if (item.getItemBorderIndex() != 0) {
                    int[] iArr = this.mBorder1_2;
                    Integer valueOf2 = iArr != null ? Integer.valueOf(iArr[item.getItemBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    templateViewOne12.setBorder(valueOf2.intValue());
                } else {
                    templateViewOne12.setBorder(0);
                }
                holder.setGone(R.id.template1, false);
                for (ChannelListItem channelListItem : item.getMDataList()) {
                    Intrinsics.checkNotNull(channelListItem, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                    ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem;
                    if (textEmojView.getPosition() == 1) {
                        if (textEmojView.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr2 = this.mBorder1_4;
                                    Integer valueOf3 = iArr2 != null ? Integer.valueOf(iArr2[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf3);
                                    templateViewOne12.setData1(textEmojView, valueOf3.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr3 = this.mBorder1_6;
                                    Integer valueOf4 = iArr3 != null ? Integer.valueOf(iArr3[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf4);
                                    templateViewOne12.setData1(textEmojView, valueOf4.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr4 = this.mBorder1_8;
                                    Integer valueOf5 = iArr4 != null ? Integer.valueOf(iArr4[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf5);
                                    templateViewOne12.setData1(textEmojView, valueOf5.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr5 = this.mBorder1_10;
                                    Integer valueOf6 = iArr5 != null ? Integer.valueOf(iArr5[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf6);
                                    templateViewOne12.setData1(textEmojView, valueOf6.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr6 = this.mBorder1_12;
                                    Integer valueOf7 = iArr6 != null ? Integer.valueOf(iArr6[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf7);
                                    templateViewOne12.setData1(textEmojView, valueOf7.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr7 = this.mBorder1_16;
                                    Integer valueOf8 = iArr7 != null ? Integer.valueOf(iArr7[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf8);
                                    templateViewOne12.setData1(textEmojView, valueOf8.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr8 = this.mBorder1_18;
                                    Integer valueOf9 = iArr8 != null ? Integer.valueOf(iArr8[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf9);
                                    templateViewOne12.setData1(textEmojView, valueOf9.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr9 = this.mBorder1_24;
                                    Integer valueOf10 = iArr9 != null ? Integer.valueOf(iArr9[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf10);
                                    templateViewOne12.setData1(textEmojView, valueOf10.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr10 = this.mBorder1_28;
                                    Integer valueOf11 = iArr10 != null ? Integer.valueOf(iArr10[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf11);
                                    templateViewOne12.setData1(textEmojView, valueOf11.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewOne12.setData1(textEmojView, 0, 1);
                        }
                    }
                    if (textEmojView.getPosition() == 2) {
                        if (textEmojView.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr11 = this.mBorder1_4;
                                    Integer valueOf12 = iArr11 != null ? Integer.valueOf(iArr11[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf12);
                                    templateViewOne12.setData2(textEmojView, valueOf12.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr12 = this.mBorder1_6;
                                    Integer valueOf13 = iArr12 != null ? Integer.valueOf(iArr12[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf13);
                                    templateViewOne12.setData2(textEmojView, valueOf13.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr13 = this.mBorder1_8;
                                    Integer valueOf14 = iArr13 != null ? Integer.valueOf(iArr13[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf14);
                                    templateViewOne12.setData2(textEmojView, valueOf14.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr14 = this.mBorder1_10;
                                    Integer valueOf15 = iArr14 != null ? Integer.valueOf(iArr14[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf15);
                                    templateViewOne12.setData2(textEmojView, valueOf15.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr15 = this.mBorder1_12;
                                    Integer valueOf16 = iArr15 != null ? Integer.valueOf(iArr15[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf16);
                                    templateViewOne12.setData2(textEmojView, valueOf16.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr16 = this.mBorder1_16;
                                    Integer valueOf17 = iArr16 != null ? Integer.valueOf(iArr16[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf17);
                                    templateViewOne12.setData2(textEmojView, valueOf17.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr17 = this.mBorder1_18;
                                    Integer valueOf18 = iArr17 != null ? Integer.valueOf(iArr17[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf18);
                                    templateViewOne12.setData2(textEmojView, valueOf18.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr18 = this.mBorder1_24;
                                    Integer valueOf19 = iArr18 != null ? Integer.valueOf(iArr18[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf19);
                                    templateViewOne12.setData2(textEmojView, valueOf19.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr19 = this.mBorder1_28;
                                    Integer valueOf20 = iArr19 != null ? Integer.valueOf(iArr19[textEmojView.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf20);
                                    templateViewOne12.setData2(textEmojView, valueOf20.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewOne12.setData2(textEmojView, 0, 1);
                        }
                    }
                }
            } else if (templateType == 2) {
                TemplateViewTwo12 templateViewTwo12 = (TemplateViewTwo12) holder.getView(R.id.template2);
                if (item.getItemBorderIndex() != 0) {
                    int[] iArr20 = this.mBorder1_2;
                    Integer valueOf21 = iArr20 != null ? Integer.valueOf(iArr20[item.getItemBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf21);
                    templateViewTwo12.setBorder(valueOf21.intValue());
                } else {
                    templateViewTwo12.setBorder(0);
                }
                holder.setGone(R.id.template2, false);
                ChannelListItem channelListItem2 = item.getMDataList().get(0);
                Intrinsics.checkNotNull(channelListItem2, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                ChannelListItem.TextEmojView textEmojView2 = (ChannelListItem.TextEmojView) channelListItem2;
                if (textEmojView2.getBorderIndex() != 0) {
                    switch (AppConfig.INSTANCE.getLedType()) {
                        case 9:
                        case 10:
                            int[] iArr21 = this.mBorder1_2;
                            valueOf = iArr21 != null ? Integer.valueOf(iArr21[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 11:
                            int[] iArr22 = this.mBorder1_2;
                            valueOf = iArr22 != null ? Integer.valueOf(iArr22[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 12:
                            int[] iArr23 = this.mBorder1_3;
                            valueOf = iArr23 != null ? Integer.valueOf(iArr23[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 14:
                            int[] iArr24 = this.mBorder1_4;
                            valueOf = iArr24 != null ? Integer.valueOf(iArr24[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 15:
                            int[] iArr25 = this.mBorder1_5;
                            valueOf = iArr25 != null ? Integer.valueOf(iArr25[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 16:
                            int[] iArr26 = this.mBorder1_7;
                            valueOf = iArr26 != null ? Integer.valueOf(iArr26[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 17:
                            int[] iArr27 = this.mBorder1_9;
                            valueOf = iArr27 != null ? Integer.valueOf(iArr27[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 18:
                            int[] iArr28 = this.mBorder1_11;
                            valueOf = iArr28 != null ? Integer.valueOf(iArr28[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                        case 19:
                            int[] iArr29 = this.mBorder1_13;
                            valueOf = iArr29 != null ? Integer.valueOf(iArr29[textEmojView2.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewTwo12.setData1(textEmojView2, valueOf.intValue(), 1);
                            break;
                    }
                } else {
                    templateViewTwo12.setData1(textEmojView2, 0, 1);
                }
                templateViewTwo12.setData2(item.getImageData2());
            } else if (templateType == 3) {
                TemplateViewThree12 templateViewThree12 = (TemplateViewThree12) holder.getView(R.id.template3);
                if (item.getItemBorderIndex() != 0) {
                    int[] iArr30 = this.mBorder1_2;
                    Integer valueOf22 = iArr30 != null ? Integer.valueOf(iArr30[item.getItemBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf22);
                    templateViewThree12.setBorder(valueOf22.intValue());
                } else {
                    templateViewThree12.setBorder(0);
                }
                holder.setGone(R.id.template3, false);
                ChannelListItem channelListItem3 = item.getMDataList().get(0);
                Intrinsics.checkNotNull(channelListItem3, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                ChannelListItem.TextEmojView textEmojView3 = (ChannelListItem.TextEmojView) channelListItem3;
                if (textEmojView3.getBorderIndex() != 0) {
                    switch (AppConfig.INSTANCE.getLedType()) {
                        case 9:
                        case 10:
                            int[] iArr31 = this.mBorder1_2;
                            valueOf = iArr31 != null ? Integer.valueOf(iArr31[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 11:
                            int[] iArr32 = this.mBorder1_2;
                            valueOf = iArr32 != null ? Integer.valueOf(iArr32[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 12:
                            int[] iArr33 = this.mBorder1_3;
                            valueOf = iArr33 != null ? Integer.valueOf(iArr33[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 14:
                            int[] iArr34 = this.mBorder1_4;
                            valueOf = iArr34 != null ? Integer.valueOf(iArr34[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 15:
                            int[] iArr35 = this.mBorder1_5;
                            valueOf = iArr35 != null ? Integer.valueOf(iArr35[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 16:
                            int[] iArr36 = this.mBorder1_7;
                            valueOf = iArr36 != null ? Integer.valueOf(iArr36[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 17:
                            int[] iArr37 = this.mBorder1_9;
                            valueOf = iArr37 != null ? Integer.valueOf(iArr37[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 18:
                            int[] iArr38 = this.mBorder1_11;
                            valueOf = iArr38 != null ? Integer.valueOf(iArr38[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                        case 19:
                            int[] iArr39 = this.mBorder1_13;
                            valueOf = iArr39 != null ? Integer.valueOf(iArr39[textEmojView3.getBorderIndex()]) : null;
                            Intrinsics.checkNotNull(valueOf);
                            templateViewThree12.setData1(textEmojView3, valueOf.intValue(), 1);
                            break;
                    }
                } else {
                    templateViewThree12.setData1(textEmojView3, 0, 1);
                }
                templateViewThree12.setData2(item.getImageData2());
            } else if (templateType == 4) {
                TemplateViewFour12 templateViewFour12 = (TemplateViewFour12) holder.getView(R.id.template4);
                if (item.getItemBorderIndex() != 0) {
                    int[] iArr40 = this.mBorder1_2;
                    Integer valueOf23 = iArr40 != null ? Integer.valueOf(iArr40[item.getItemBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf23);
                    templateViewFour12.setBorder(valueOf23.intValue());
                } else {
                    templateViewFour12.setBorder(0);
                }
                holder.setGone(R.id.template4, false);
                for (ChannelListItem channelListItem4 : item.getMDataList()) {
                    Intrinsics.checkNotNull(channelListItem4, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                    ChannelListItem.TextEmojView textEmojView4 = (ChannelListItem.TextEmojView) channelListItem4;
                    if (textEmojView4.getPosition() == 1) {
                        if (textEmojView4.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr41 = this.mBorder1_2;
                                    Integer valueOf24 = iArr41 != null ? Integer.valueOf(iArr41[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf24);
                                    templateViewFour12.setData1(textEmojView4, valueOf24.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr42 = this.mBorder1_4;
                                    Integer valueOf25 = iArr42 != null ? Integer.valueOf(iArr42[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf25);
                                    templateViewFour12.setData1(textEmojView4, valueOf25.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr43 = this.mBorder1_6;
                                    Integer valueOf26 = iArr43 != null ? Integer.valueOf(iArr43[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf26);
                                    templateViewFour12.setData1(textEmojView4, valueOf26.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr44 = this.mBorder1_8;
                                    Integer valueOf27 = iArr44 != null ? Integer.valueOf(iArr44[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf27);
                                    templateViewFour12.setData1(textEmojView4, valueOf27.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr45 = this.mBorder1_10;
                                    Integer valueOf28 = iArr45 != null ? Integer.valueOf(iArr45[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf28);
                                    templateViewFour12.setData1(textEmojView4, valueOf28.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr46 = this.mBorder1_14;
                                    Integer valueOf29 = iArr46 != null ? Integer.valueOf(iArr46[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf29);
                                    templateViewFour12.setData1(textEmojView4, valueOf29.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr47 = this.mBorder1_18;
                                    Integer valueOf30 = iArr47 != null ? Integer.valueOf(iArr47[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf30);
                                    templateViewFour12.setData1(textEmojView4, valueOf30.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr48 = this.mBorder1_22;
                                    Integer valueOf31 = iArr48 != null ? Integer.valueOf(iArr48[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf31);
                                    templateViewFour12.setData1(textEmojView4, valueOf31.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr49 = this.mBorder1_26;
                                    Integer valueOf32 = iArr49 != null ? Integer.valueOf(iArr49[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf32);
                                    templateViewFour12.setData1(textEmojView4, valueOf32.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewFour12.setData1(textEmojView4, 0, 1);
                        }
                    }
                    if (textEmojView4.getPosition() == 2) {
                        if (textEmojView4.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr50 = this.mBorder1_2;
                                    Integer valueOf33 = iArr50 != null ? Integer.valueOf(iArr50[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf33);
                                    templateViewFour12.setData2(textEmojView4, valueOf33.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr51 = this.mBorder1_4;
                                    Integer valueOf34 = iArr51 != null ? Integer.valueOf(iArr51[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf34);
                                    templateViewFour12.setData2(textEmojView4, valueOf34.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr52 = this.mBorder1_6;
                                    Integer valueOf35 = iArr52 != null ? Integer.valueOf(iArr52[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf35);
                                    templateViewFour12.setData2(textEmojView4, valueOf35.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr53 = this.mBorder1_8;
                                    Integer valueOf36 = iArr53 != null ? Integer.valueOf(iArr53[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf36);
                                    templateViewFour12.setData2(textEmojView4, valueOf36.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr54 = this.mBorder1_10;
                                    Integer valueOf37 = iArr54 != null ? Integer.valueOf(iArr54[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf37);
                                    templateViewFour12.setData2(textEmojView4, valueOf37.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr55 = this.mBorder1_14;
                                    Integer valueOf38 = iArr55 != null ? Integer.valueOf(iArr55[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf38);
                                    templateViewFour12.setData2(textEmojView4, valueOf38.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr56 = this.mBorder1_18;
                                    Integer valueOf39 = iArr56 != null ? Integer.valueOf(iArr56[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf39);
                                    templateViewFour12.setData2(textEmojView4, valueOf39.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr57 = this.mBorder1_22;
                                    Integer valueOf40 = iArr57 != null ? Integer.valueOf(iArr57[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf40);
                                    templateViewFour12.setData2(textEmojView4, valueOf40.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr58 = this.mBorder1_26;
                                    Integer valueOf41 = iArr58 != null ? Integer.valueOf(iArr58[textEmojView4.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf41);
                                    templateViewFour12.setData2(textEmojView4, valueOf41.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewFour12.setData2(textEmojView4, 0, 1);
                        }
                    }
                }
                templateViewFour12.setImageData(item.getImageData2());
            } else if (templateType == 5) {
                TemplateViewFive12 templateViewFive12 = (TemplateViewFive12) holder.getView(R.id.template5);
                if (item.getItemBorderIndex() != 0) {
                    int[] iArr59 = this.mBorder1_2;
                    Integer valueOf42 = iArr59 != null ? Integer.valueOf(iArr59[item.getItemBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf42);
                    templateViewFive12.setBorder(valueOf42.intValue());
                } else {
                    templateViewFive12.setBorder(0);
                }
                holder.setGone(R.id.template5, false);
                for (ChannelListItem channelListItem5 : item.getMDataList()) {
                    Intrinsics.checkNotNull(channelListItem5, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                    ChannelListItem.TextEmojView textEmojView5 = (ChannelListItem.TextEmojView) channelListItem5;
                    if (textEmojView5.getPosition() == 1) {
                        if (textEmojView5.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr60 = this.mBorder1_2;
                                    Integer valueOf43 = iArr60 != null ? Integer.valueOf(iArr60[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf43);
                                    templateViewFive12.setData1(textEmojView5, valueOf43.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr61 = this.mBorder1_4;
                                    Integer valueOf44 = iArr61 != null ? Integer.valueOf(iArr61[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf44);
                                    templateViewFive12.setData1(textEmojView5, valueOf44.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr62 = this.mBorder1_6;
                                    Integer valueOf45 = iArr62 != null ? Integer.valueOf(iArr62[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf45);
                                    templateViewFive12.setData1(textEmojView5, valueOf45.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr63 = this.mBorder1_8;
                                    Integer valueOf46 = iArr63 != null ? Integer.valueOf(iArr63[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf46);
                                    templateViewFive12.setData1(textEmojView5, valueOf46.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr64 = this.mBorder1_10;
                                    Integer valueOf47 = iArr64 != null ? Integer.valueOf(iArr64[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf47);
                                    templateViewFive12.setData1(textEmojView5, valueOf47.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr65 = this.mBorder1_14;
                                    Integer valueOf48 = iArr65 != null ? Integer.valueOf(iArr65[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf48);
                                    templateViewFive12.setData1(textEmojView5, valueOf48.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr66 = this.mBorder1_18;
                                    Integer valueOf49 = iArr66 != null ? Integer.valueOf(iArr66[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf49);
                                    templateViewFive12.setData1(textEmojView5, valueOf49.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr67 = this.mBorder1_22;
                                    Integer valueOf50 = iArr67 != null ? Integer.valueOf(iArr67[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf50);
                                    templateViewFive12.setData1(textEmojView5, valueOf50.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr68 = this.mBorder1_26;
                                    Integer valueOf51 = iArr68 != null ? Integer.valueOf(iArr68[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf51);
                                    templateViewFive12.setData1(textEmojView5, valueOf51.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewFive12.setData1(textEmojView5, 0, 1);
                        }
                    }
                    if (textEmojView5.getPosition() == 2) {
                        if (textEmojView5.getBorderIndex() != 0) {
                            switch (AppConfig.INSTANCE.getLedType()) {
                                case 9:
                                case 10:
                                    int[] iArr69 = this.mBorder1_2;
                                    Integer valueOf52 = iArr69 != null ? Integer.valueOf(iArr69[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf52);
                                    templateViewFive12.setData2(textEmojView5, valueOf52.intValue(), 1);
                                    break;
                                case 11:
                                    int[] iArr70 = this.mBorder1_4;
                                    Integer valueOf53 = iArr70 != null ? Integer.valueOf(iArr70[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf53);
                                    templateViewFive12.setData2(textEmojView5, valueOf53.intValue(), 1);
                                    break;
                                case 12:
                                    int[] iArr71 = this.mBorder1_6;
                                    Integer valueOf54 = iArr71 != null ? Integer.valueOf(iArr71[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf54);
                                    templateViewFive12.setData2(textEmojView5, valueOf54.intValue(), 1);
                                    break;
                                case 14:
                                    int[] iArr72 = this.mBorder1_8;
                                    Integer valueOf55 = iArr72 != null ? Integer.valueOf(iArr72[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf55);
                                    templateViewFive12.setData2(textEmojView5, valueOf55.intValue(), 1);
                                    break;
                                case 15:
                                    int[] iArr73 = this.mBorder1_10;
                                    Integer valueOf56 = iArr73 != null ? Integer.valueOf(iArr73[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf56);
                                    templateViewFive12.setData2(textEmojView5, valueOf56.intValue(), 1);
                                    break;
                                case 16:
                                    int[] iArr74 = this.mBorder1_14;
                                    Integer valueOf57 = iArr74 != null ? Integer.valueOf(iArr74[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf57);
                                    templateViewFive12.setData2(textEmojView5, valueOf57.intValue(), 1);
                                    break;
                                case 17:
                                    int[] iArr75 = this.mBorder1_18;
                                    Integer valueOf58 = iArr75 != null ? Integer.valueOf(iArr75[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf58);
                                    templateViewFive12.setData2(textEmojView5, valueOf58.intValue(), 1);
                                    break;
                                case 18:
                                    int[] iArr76 = this.mBorder1_22;
                                    Integer valueOf59 = iArr76 != null ? Integer.valueOf(iArr76[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf59);
                                    templateViewFive12.setData2(textEmojView5, valueOf59.intValue(), 1);
                                    break;
                                case 19:
                                    int[] iArr77 = this.mBorder1_26;
                                    Integer valueOf60 = iArr77 != null ? Integer.valueOf(iArr77[textEmojView5.getBorderIndex()]) : null;
                                    Intrinsics.checkNotNull(valueOf60);
                                    templateViewFive12.setData2(textEmojView5, valueOf60.intValue(), 1);
                                    break;
                            }
                        } else {
                            templateViewFive12.setData2(textEmojView5, 0, 1);
                        }
                    }
                }
                templateViewFive12.setImageData(item.getImageData2());
            }
            Unit unit2 = Unit.INSTANCE;
        }
        if (this.isEditStatus) {
            if (holder.getLayoutPosition() == getData().size() - 1) {
                holder.setGone(R.id.iv_schedule_select, true);
                holder.itemView.setVisibility(8);
            } else {
                holder.setGone(R.id.iv_schedule_select, false);
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
        holder.setGone(R.id.iv_schedule_select, true);
    }

    private final void initBorderData() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
            case 10:
                this.mBorder1_6 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_6_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_2_1);
                this.mBorder1_4 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_4_1);
                break;
            case 11:
                this.mBorder1_6 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_6_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_3_1);
                this.mBorder1_4 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_4_1);
                break;
            case 12:
                this.mBorder1_6 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_6_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_4_1);
                this.mBorder1_3 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_3_1);
                this.mBorder1_4 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_4_1);
                this.mBorder1_8 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_8_1);
                break;
            case 14:
                this.mBorder1_5 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_5_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_5_1);
                this.mBorder1_4 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_4_1);
                this.mBorder1_10 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_10_1);
                this.mBorder1_8 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_8_1);
                break;
            case 15:
                this.mBorder1_6 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_6_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_6_1);
                this.mBorder1_12 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_12_1);
                this.mBorder1_5 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_5_1);
                this.mBorder1_10 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_10_1);
                break;
            case 16:
                this.mBorder1_8 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_8_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_8_1);
                this.mBorder1_16 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_16_1);
                this.mBorder1_7 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_7_1);
                this.mBorder1_14 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_14_1);
                break;
            case 17:
                this.mBorder1_10 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_10_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_10_1);
                this.mBorder1_20 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_20_1);
                this.mBorder1_9 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_9_1);
                this.mBorder1_18 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_18_1);
                break;
            case 18:
                this.mBorder1_12 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_12_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_12_1);
                this.mBorder1_22 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_22_1);
                this.mBorder1_24 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_24_1);
                this.mBorder1_11 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_11_1);
                break;
            case 19:
                this.mBorder1_14 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_14_1);
                this.mBorder1_2 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_14_1);
                this.mBorder1_13 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_13_1);
                this.mBorder1_26 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_26_1);
                this.mBorder1_28 = ResourceUtils.getResIds(getContext(), R.array.subzone_border_28_1);
                break;
        }
    }
}
