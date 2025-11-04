package com.wifiled.ipixels.ui.channel;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.text.GradientColor;
import com.wifiled.ipixels.core.text.TextEmojiBGRUtils;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;
import com.wifiled.ipixels.ui.channel.text.TextAnimRecycleView;
import com.wifiled.ipixels.ui.channel.text.TextAnimUtils;
import com.wifiled.ipixels.ui.text.XFlexboxLayoutManager;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.view.LedView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: ChannelAdapter.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000fJ\u0006\u0010#\u001a\u00020\u0018J\u0006\u0010$\u001a\u00020\u0018J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H\u0002J \u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u000e\u00100\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000fJ\u0018\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020'H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u00063"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "textData", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "mapTextAnimUtils", "", "", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimRecycleView;", "compression", "selIndex", "getSelIndex", "()I", "setSelIndex", "(I)V", "isSelAll", "", "()Z", "setSelAll", "(Z)V", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "item", "delSelAnims", "index", "checkIsSellALL", "checkIsSellAny", "initLedView", "rlBg", "Lcom/wifiled/ipixels/view/LedView;", "loadEffect", "Landroid/animation/ObjectAnimator;", "ins", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "it", "Lcom/wifiled/ipixels/event/EventText;", "rlItem", "Landroidx/recyclerview/widget/RecyclerView;", "stopAnim", "showBgColor", TypedValues.Custom.S_COLOR, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelAdapter extends RecyclerAdapter<ChannelListItem> {
    private final int compression;
    private final Context context;
    private boolean isSelAll;
    private Map<Integer, TextAnimRecycleView> mapTextAnimUtils;
    private int selIndex;
    private final List<TextEmojiVO> textData;

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ChannelAdapter(android.content.Context r2, java.util.List<com.wifiled.ipixels.ui.channel.ChannelListItem> r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.wifiled.ipixels.AppConfig r0 = com.wifiled.ipixels.AppConfig.INSTANCE
            int r0 = r0.getLedType()
            switch(r0) {
                case 1: goto L47;
                case 2: goto L13;
                case 3: goto L43;
                case 4: goto L3f;
                case 5: goto L3b;
                case 6: goto L37;
                case 7: goto L33;
                case 8: goto L2f;
                case 9: goto L3f;
                case 10: goto L3f;
                case 11: goto L2b;
                case 12: goto L43;
                case 13: goto L2b;
                case 14: goto L27;
                case 15: goto L47;
                case 16: goto L23;
                case 17: goto L1f;
                case 18: goto L1b;
                case 19: goto L17;
                default: goto L13;
            }
        L13:
            r0 = 2131493022(0x7f0c009e, float:1.8609512E38)
            goto L4a
        L17:
            r0 = 2131493034(0x7f0c00aa, float:1.8609537E38)
            goto L4a
        L1b:
            r0 = 2131493033(0x7f0c00a9, float:1.8609535E38)
            goto L4a
        L1f:
            r0 = 2131493032(0x7f0c00a8, float:1.8609533E38)
            goto L4a
        L23:
            r0 = 2131493031(0x7f0c00a7, float:1.860953E38)
            goto L4a
        L27:
            r0 = 2131493030(0x7f0c00a6, float:1.8609529E38)
            goto L4a
        L2b:
            r0 = 2131493035(0x7f0c00ab, float:1.8609539E38)
            goto L4a
        L2f:
            r0 = 2131493024(0x7f0c00a0, float:1.8609516E38)
            goto L4a
        L33:
            r0 = 2131493023(0x7f0c009f, float:1.8609514E38)
            goto L4a
        L37:
            r0 = 2131493029(0x7f0c00a5, float:1.8609527E38)
            goto L4a
        L3b:
            r0 = 2131493028(0x7f0c00a4, float:1.8609525E38)
            goto L4a
        L3f:
            r0 = 2131493025(0x7f0c00a1, float:1.8609518E38)
            goto L4a
        L43:
            r0 = 2131493026(0x7f0c00a2, float:1.860952E38)
            goto L4a
        L47:
            r0 = 2131493027(0x7f0c00a3, float:1.8609523E38)
        L4a:
            r1.<init>(r2, r3, r0)
            r1.context = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
            r1.textData = r2
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            java.util.Map r2 = (java.util.Map) r2
            r1.mapTextAnimUtils = r2
            r2 = 1
            r1.compression = r2
            r2 = -1
            r1.selIndex = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.channel.ChannelAdapter.<init>(android.content.Context, java.util.List):void");
    }

    public final int getSelIndex() {
        return this.selIndex;
    }

    public final void setSelIndex(int i) {
        this.selIndex = i;
    }

    /* renamed from: isSelAll, reason: from getter */
    public final boolean getIsSelAll() {
        return this.isSelAll;
    }

    public final void setSelAll(boolean z) {
        this.isSelAll = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, ChannelListItem item) {
        int i;
        View view;
        View view2;
        int i2;
        View view3;
        View view4;
        View view5;
        if (item instanceof ChannelListItem.TextEmojView) {
            ImageView imageView = (holder == null || (view5 = holder.itemView) == null) ? null : (ImageView) view5.findViewById(R.id.iv_chl_sel);
            Intrinsics.checkNotNull(imageView);
            if (this.selIndex == getPosition(holder)) {
                this.selIndex = -1;
                ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) item;
                textEmojView.setSel(!textEmojView.isSel());
                imageView.setSelected(textEmojView.isSel());
                this.isSelAll = checkIsSellALL();
                EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
            } else {
                boolean z = this.isSelAll;
                if (z) {
                    imageView.setSelected(z);
                } else {
                    imageView.setSelected(((ChannelListItem.TextEmojView) item).isSel());
                    this.isSelAll = checkIsSellALL();
                    EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
                }
            }
            View findViewById = holder.itemView.findViewById(R.id.tv_num);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            ChannelListItem.TextEmojView textEmojView2 = (ChannelListItem.TextEmojView) item;
            ((TextView) findViewById).setText(textEmojView2.getSerialNum());
            RelativeLayout relativeLayout = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout != null) {
                UtilsExtensionKt.show(relativeLayout);
            }
            ImageView imageView2 = (ImageView) holder.itemView.findViewById(R.id.iv_channel_imag_gif_item);
            if (imageView2 != null) {
                UtilsExtensionKt.hide(imageView2);
            }
            ImageView imageView3 = (ImageView) holder.itemView.findViewById(R.id.left_eyes_gif);
            if (imageView3 != null) {
                UtilsExtensionKt.hide(imageView3);
            }
            ImageView imageView4 = (ImageView) holder.itemView.findViewById(R.id.right_eyes_gif);
            if (imageView4 != null) {
                UtilsExtensionKt.hide(imageView4);
            }
            LedView ledView = (LedView) holder.itemView.findViewById(R.id.rl_channel_text_bg);
            ObjectarxItem objectarxItem = (ObjectarxItem) holder.itemView.findViewById(R.id.objectarxItem);
            StringBuilder sb = new StringBuilder();
            Iterator<TextEmojiVO> it = textEmojView2.getEventText().getTextEmojiVO().iterator();
            while (it.hasNext()) {
                sb.append(it.next().getText());
            }
            TextEmojiBGRUtils textEmojiBGRUtils = TextEmojiBGRUtils.INSTANCE;
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            Bitmap bitmapByTextEmojiPreview$default = TextEmojiBGRUtils.getBitmapByTextEmojiPreview$default(textEmojiBGRUtils, sb2, textEmojView2.getEventText().getTextEmojiVO().get(0), false, 4, null);
            LogUtils.vTag("ruis", "bitmap size-" + (bitmapByTextEmojiPreview$default != null ? Integer.valueOf(bitmapByTextEmojiPreview$default.getWidth()) : null));
            if (bitmapByTextEmojiPreview$default != null) {
                objectarxItem.setData(bitmapByTextEmojiPreview$default, (AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 19 || AppConfig.INSTANCE.getLedType() == 16) ? 1 : (AppConfig.INSTANCE.getLedType() == 7 || AppConfig.INSTANCE.getLedType() == 8) ? 2 : 4);
            }
            ledView.setVisibility(8);
            Unit unit = Unit.INSTANCE;
            return;
        }
        if (item instanceof ChannelListItem.ImagView) {
            ImageView imageView5 = (holder == null || (view4 = holder.itemView) == null) ? null : (ImageView) view4.findViewById(R.id.iv_chl_sel);
            Intrinsics.checkNotNull(imageView5, "null cannot be cast to non-null type android.widget.ImageView");
            if (this.selIndex == getPosition(holder)) {
                this.selIndex = -1;
                ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) item;
                imagView.setSel(!imagView.isSel());
                imageView5.setSelected(imagView.isSel());
                this.isSelAll = checkIsSellALL();
                EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
            } else {
                boolean z2 = this.isSelAll;
                if (z2) {
                    imageView5.setSelected(z2);
                } else {
                    imageView5.setSelected(((ChannelListItem.ImagView) item).isSel());
                    this.isSelAll = checkIsSellALL();
                    EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
                }
            }
            View findViewById2 = holder.itemView.findViewById(R.id.tv_num);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            ChannelListItem.ImagView imagView2 = (ChannelListItem.ImagView) item;
            ((TextView) findViewById2).setText(imagView2.getSerialNum());
            RelativeLayout relativeLayout2 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout2 != null) {
                UtilsExtensionKt.hide(relativeLayout2);
            }
            ImageView imageView6 = (ImageView) holder.itemView.findViewById(R.id.left_eyes_gif);
            if (imageView6 != null) {
                UtilsExtensionKt.hide(imageView6);
            }
            ImageView imageView7 = (ImageView) holder.itemView.findViewById(R.id.right_eyes_gif);
            if (imageView7 != null) {
                UtilsExtensionKt.hide(imageView7);
            }
            ((ImageView) holder.itemView.findViewById(R.id.iv_channel_imag_gif_item)).setVisibility(0);
            if (AppConfig.INSTANCE.getLedType() == 16) {
                imagView2.setBitmap(BGRUtils.RGB2bitmap(imagView2.getArrImagData()));
            } else if (imagView2.getBitmap() == null) {
                imagView2.setBitmap(BitmapFactory.decodeByteArray(imagView2.getArrImagData(), 0, imagView2.getArrImagData().length));
            }
            holder.setImageBitmap(R.id.iv_channel_imag_gif_item, imagView2.getBitmap());
            return;
        }
        boolean z3 = item instanceof ChannelListItem.GiftView;
        int i3 = R.drawable.image_loading_1_5;
        if (z3) {
            ImageView imageView8 = (holder == null || (view3 = holder.itemView) == null) ? null : (ImageView) view3.findViewById(R.id.iv_chl_sel);
            Intrinsics.checkNotNull(imageView8);
            if (this.selIndex == getPosition(holder)) {
                this.selIndex = -1;
                ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) item;
                giftView.setSel(!giftView.isSel());
                imageView8.setSelected(giftView.isSel());
                this.isSelAll = checkIsSellALL();
                EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
            } else {
                boolean z4 = this.isSelAll;
                if (z4) {
                    imageView8.setSelected(z4);
                } else {
                    imageView8.setSelected(((ChannelListItem.GiftView) item).isSel());
                    this.isSelAll = checkIsSellALL();
                    EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
                }
            }
            View findViewById3 = holder.itemView.findViewById(R.id.tv_num);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            ChannelListItem.GiftView giftView2 = (ChannelListItem.GiftView) item;
            ((TextView) findViewById3).setText(giftView2.getSerialNum());
            RelativeLayout relativeLayout3 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout3 != null) {
                UtilsExtensionKt.hide(relativeLayout3);
            }
            ImageView imageView9 = (ImageView) holder.itemView.findViewById(R.id.left_eyes_gif);
            if (imageView9 != null) {
                UtilsExtensionKt.hide(imageView9);
            }
            ImageView imageView10 = (ImageView) holder.itemView.findViewById(R.id.right_eyes_gif);
            if (imageView10 != null) {
                UtilsExtensionKt.hide(imageView10);
            }
            ImageView imageView11 = (ImageView) holder.itemView.findViewById(R.id.iv_channel_imag_gif_item);
            if (imageView11 != null) {
                UtilsExtensionKt.show(imageView11);
            }
            switch (AppConfig.INSTANCE.getLedType()) {
                case 1:
                case 15:
                    i2 = R.drawable.image_loading_16x96;
                    break;
                case 2:
                case 3:
                case 4:
                case 6:
                case 9:
                case 10:
                case 12:
                case 13:
                default:
                    i2 = R.drawable.image_loading_16x32;
                    break;
                case 5:
                    i2 = R.drawable.image_loading_20x64;
                    break;
                case 7:
                    i2 = R.drawable.image_loading_16x144;
                    break;
                case 8:
                    i2 = R.drawable.image_loading_16x192;
                    break;
                case 11:
                    i2 = R.drawable.image_loading_1_3;
                    break;
                case 14:
                    i2 = i3;
                    break;
                case 16:
                    i3 = R.drawable.image_loading_1_8;
                    i2 = i3;
                    break;
                case 17:
                    i3 = R.drawable.image_loading_1_10;
                    i2 = i3;
                    break;
                case 18:
                    i3 = R.drawable.image_loading_1_12;
                    i2 = i3;
                    break;
                case 19:
                    i3 = R.drawable.image_loading_1_14;
                    i2 = i3;
                    break;
            }
            if (giftView2.getArrGifData() != null) {
                Glide.with(holder.getConvertView()).asGif().load(giftView2.getArrGifData()).error(i2).into((ImageView) holder.getView(R.id.iv_channel_imag_gif_item));
                return;
            } else {
                Glide.with(holder.getConvertView()).asGif().load(giftView2.getPath()).error(i2).into((ImageView) holder.getView(R.id.iv_channel_imag_gif_item));
                return;
            }
        }
        if (item instanceof ChannelListItem.SubzoneView) {
            ImageView imageView12 = (holder == null || (view2 = holder.itemView) == null) ? null : (ImageView) view2.findViewById(R.id.iv_chl_sel);
            Intrinsics.checkNotNull(imageView12);
            if (this.selIndex == getPosition(holder)) {
                this.selIndex = -1;
                ChannelListItem.SubzoneView subzoneView = (ChannelListItem.SubzoneView) item;
                subzoneView.setSel(!subzoneView.isSel());
                imageView12.setSelected(subzoneView.isSel());
                this.isSelAll = checkIsSellALL();
                EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
            } else {
                ChannelListItem.SubzoneView subzoneView2 = (ChannelListItem.SubzoneView) item;
                LogUtils.vTag("ruis", "item.isSel----" + subzoneView2.isSel());
                boolean z5 = this.isSelAll;
                if (z5) {
                    imageView12.setSelected(z5);
                } else {
                    imageView12.setSelected(subzoneView2.isSel());
                    this.isSelAll = checkIsSellALL();
                    EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
                }
            }
            View findViewById4 = holder.itemView.findViewById(R.id.tv_num);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            ChannelListItem.SubzoneView subzoneView3 = (ChannelListItem.SubzoneView) item;
            ((TextView) findViewById4).setText(subzoneView3.getSerialNum());
            RelativeLayout relativeLayout4 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout4 != null) {
                UtilsExtensionKt.hide(relativeLayout4);
            }
            ImageView imageView13 = (ImageView) holder.itemView.findViewById(R.id.iv_channel_imag_gif_item);
            if (imageView13 != null) {
                UtilsExtensionKt.show(imageView13);
            }
            Glide.with(holder.getConvertView()).load(BitmapFactory.decodeByteArray(subzoneView3.getArrData(), 0, subzoneView3.getArrData().length)).into((ImageView) holder.getView(R.id.iv_channel_imag_gif_item));
            return;
        }
        if (!(item instanceof ChannelListItem.EyesView)) {
            Unit unit2 = Unit.INSTANCE;
            return;
        }
        ImageView imageView14 = (holder == null || (view = holder.itemView) == null) ? null : (ImageView) view.findViewById(R.id.iv_chl_sel);
        Intrinsics.checkNotNull(imageView14, "null cannot be cast to non-null type android.widget.ImageView");
        if (this.selIndex == getPosition(holder)) {
            this.selIndex = -1;
            ChannelListItem.EyesView eyesView = (ChannelListItem.EyesView) item;
            eyesView.setSel(!eyesView.isSel());
            imageView14.setSelected(eyesView.isSel());
            this.isSelAll = checkIsSellALL();
            EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
        } else {
            boolean z6 = this.isSelAll;
            if (z6) {
                imageView14.setSelected(z6);
            } else {
                imageView14.setSelected(((ChannelListItem.EyesView) item).isSel());
                this.isSelAll = checkIsSellALL();
                EventBus.getDefault().post(new CSelState(this.isSelAll, checkIsSellAny()));
            }
        }
        View findViewById5 = holder.itemView.findViewById(R.id.tv_num);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        ChannelListItem.EyesView eyesView2 = (ChannelListItem.EyesView) item;
        ((TextView) findViewById5).setText(eyesView2.getSerialNum());
        RelativeLayout relativeLayout5 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
        if (relativeLayout5 != null) {
            UtilsExtensionKt.hide(relativeLayout5);
        }
        ImageView imageView15 = (ImageView) holder.itemView.findViewById(R.id.iv_channel_imag_gif_item);
        if (imageView15 != null) {
            UtilsExtensionKt.hide(imageView15);
        }
        ImageView imageView16 = (ImageView) holder.itemView.findViewById(R.id.left_eyes_gif);
        if (imageView16 != null) {
            UtilsExtensionKt.show(imageView16);
        }
        ImageView imageView17 = (ImageView) holder.itemView.findViewById(R.id.right_eyes_gif);
        if (imageView17 != null) {
            UtilsExtensionKt.show(imageView17);
        }
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 15:
                i = R.drawable.image_loading_16x96;
                break;
            case 2:
            case 3:
            case 4:
            case 6:
            case 9:
            case 10:
            case 12:
            case 13:
            default:
                i = R.drawable.image_loading_16x32;
                break;
            case 5:
                i = R.drawable.image_loading_20x64;
                break;
            case 7:
                i = R.drawable.image_loading_16x144;
                break;
            case 8:
                i = R.drawable.image_loading_16x192;
                break;
            case 11:
                i = R.drawable.image_loading_1_3;
                break;
            case 14:
                i = i3;
                break;
            case 16:
                i3 = R.drawable.image_loading_1_8;
                i = i3;
                break;
            case 17:
                i3 = R.drawable.image_loading_1_10;
                i = i3;
                break;
            case 18:
                i3 = R.drawable.image_loading_1_12;
                i = i3;
                break;
        }
        if (eyesView2.getLeftData() != null) {
            Glide.with(holder.getConvertView()).asGif().load(eyesView2.getLeftData()).error(i).into((ImageView) holder.getView(R.id.left_eyes_gif));
        }
        if (eyesView2.getRightData() != null) {
            Glide.with(holder.getConvertView()).asGif().load(eyesView2.getRightData()).error(i).into((ImageView) holder.getView(R.id.right_eyes_gif));
        }
        Unit unit3 = Unit.INSTANCE;
    }

    public final void delSelAnims(int index) {
        TextAnimRecycleView textAnimRecycleView = this.mapTextAnimUtils.get(Integer.valueOf(index));
        if ((textAnimRecycleView != null ? textAnimRecycleView.getOjAnimator() : null) != null) {
            ObjectAnimator ojAnimator = textAnimRecycleView.getOjAnimator();
            if (ojAnimator != null) {
                ojAnimator.cancel();
            }
            ObjectAnimator ojAnimator2 = textAnimRecycleView.getOjAnimator();
            Intrinsics.checkNotNull(ojAnimator2);
            ojAnimator2.removeAllListeners();
            textAnimRecycleView.setOjAnimator(null);
        }
        this.mapTextAnimUtils.remove(Integer.valueOf(index));
    }

    public final boolean checkIsSellALL() {
        Iterator<Map.Entry<Integer, ChannelListItem>> it = ChannelIndex.INSTANCE.mapSaveChannel().entrySet().iterator();
        while (it.hasNext()) {
            ChannelListItem value = it.next().getValue();
            if (value instanceof ChannelListItem.TextEmojView) {
                if (!((ChannelListItem.TextEmojView) value).isSel()) {
                    return false;
                }
            } else if (value instanceof ChannelListItem.ImagView) {
                if (!((ChannelListItem.ImagView) value).isSel()) {
                    return false;
                }
            } else if (value instanceof ChannelListItem.GiftView) {
                if (!((ChannelListItem.GiftView) value).isSel()) {
                    return false;
                }
            } else if (value instanceof ChannelListItem.SubzoneView) {
                if (!((ChannelListItem.SubzoneView) value).isSel()) {
                    return false;
                }
            } else {
                if (!(value instanceof ChannelListItem.EyesView)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!((ChannelListItem.EyesView) value).isSel()) {
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean checkIsSellAny() {
        Iterator<Map.Entry<Integer, ChannelListItem>> it = ChannelIndex.INSTANCE.mapSaveChannel().entrySet().iterator();
        while (it.hasNext()) {
            ChannelListItem value = it.next().getValue();
            if (value instanceof ChannelListItem.TextEmojView) {
                if (((ChannelListItem.TextEmojView) value).isSel()) {
                    return true;
                }
            } else if (value instanceof ChannelListItem.ImagView) {
                if (((ChannelListItem.ImagView) value).isSel()) {
                    return true;
                }
            } else if (value instanceof ChannelListItem.GiftView) {
                if (((ChannelListItem.GiftView) value).isSel()) {
                    return true;
                }
            } else if (value instanceof ChannelListItem.SubzoneView) {
                if (((ChannelListItem.SubzoneView) value).isSel()) {
                    return true;
                }
            } else {
                if (!(value instanceof ChannelListItem.EyesView)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (((ChannelListItem.EyesView) value).isSel()) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void initLedView(LedView rlBg) {
        rlBg.init(AppConfig.INSTANCE.getLedSize().get(0).intValue() / this.compression, AppConfig.INSTANCE.getLedSize().get(1).intValue() / this.compression);
        rlBg.setEnabled(false);
        rlBg.setUnSelectedColor(ViewCompat.MEASURED_STATE_MASK);
    }

    private final ObjectAnimator loadEffect(TextAnimUtils ins, EventText it, RecyclerView rlItem) {
        rlItem.setLayoutManager(null);
        int textEffect = it.getTextEffect();
        if (textEffect == 1 || textEffect == 2) {
            final Context context = this.context;
            final boolean z = 2 == it.getTextEffect();
            Object obj = new WeakReference(new LinearLayoutManager(context, z) { // from class: com.wifiled.ipixels.ui.channel.ChannelAdapter$loadEffect$layoutManager$1
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return true;
                }
            }).get();
            Intrinsics.checkNotNull(obj);
            rlItem.setLayoutManager((RecyclerView.LayoutManager) obj);
        } else {
            final Context context2 = this.context;
            WeakReference weakReference = new WeakReference(new XFlexboxLayoutManager(context2) { // from class: com.wifiled.ipixels.ui.channel.ChannelAdapter$loadEffect$layoutManager$2
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

    public final void stopAnim(int index) {
        if (this.mapTextAnimUtils.isEmpty() || this.mapTextAnimUtils.get(Integer.valueOf(index)) == null) {
            return;
        }
        if (index != -1) {
            TextAnimRecycleView textAnimRecycleView = this.mapTextAnimUtils.get(Integer.valueOf(index));
            ObjectAnimator ojAnimator = textAnimRecycleView != null ? textAnimRecycleView.getOjAnimator() : null;
            if (ojAnimator != null) {
                ojAnimator.cancel();
            }
            if (ojAnimator != null) {
                ojAnimator.removeAllListeners();
                return;
            }
            return;
        }
        Iterator<Map.Entry<Integer, TextAnimRecycleView>> it = this.mapTextAnimUtils.entrySet().iterator();
        while (it.hasNext()) {
            ObjectAnimator ojAnimator2 = it.next().getValue().getOjAnimator();
            Intrinsics.checkNotNull(ojAnimator2);
            ojAnimator2.cancel();
            ojAnimator2.removeAllListeners();
        }
        this.mapTextAnimUtils.clear();
    }

    private final void showBgColor(int color, LedView rlItem) {
        switch (color) {
            case 2:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor1());
                break;
            case 3:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor2());
                break;
            case 4:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor3());
                break;
            case 5:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor4());
                break;
            case 6:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor5());
                break;
            case 7:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor6());
                break;
            case 8:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor7());
                break;
            case 9:
                rlItem.setTextGradientBgData(GradientColor.INSTANCE.getGradientColor8());
                break;
            default:
                rlItem.clear();
                rlItem.setUnSelectedColor(color);
                break;
        }
    }
}
