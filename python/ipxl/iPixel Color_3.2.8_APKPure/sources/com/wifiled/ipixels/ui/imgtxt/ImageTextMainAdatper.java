package com.wifiled.ipixels.ui.imgtxt;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.text.GradientColor;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.channel.text.ChannelTextAdapter;
import com.wifiled.ipixels.ui.channel.text.TextAnimRecycleView;
import com.wifiled.ipixels.ui.channel.text.TextAnimUtils;
import com.wifiled.ipixels.ui.text.XFlexboxLayoutManager;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.view.LedView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageTextMainAdatper.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0002H\u0015J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0012J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J \u0010\u001c\u001a\u00020\u00142\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u0005H\u0002J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J \u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010!\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020-H\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextMainAdatper;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "layoutId", "", "<init>", "(I)V", "compression", "textAdapter", "Lcom/wifiled/ipixels/ui/channel/text/ChannelTextAdapter;", "textData", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "mapTextAnimUtils", "", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimRecycleView;", "isEditStatus", "", "convert", "", "holder", "item", "setEditStatus", "editStatus", "initRecycle", "rlItem", "Landroidx/recyclerview/widget/RecyclerView;", "showText", "textEmojiVO", "", "textColor", "changeTextAlign", "it", "loadEffect", "Landroid/animation/ObjectAnimator;", "ins", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "Lcom/wifiled/ipixels/event/EventText;", "setHorizontalAlign", "justifyContent", "setVerticalAlign", "align", "showBgColor", TypedValues.Custom.S_COLOR, "Lcom/wifiled/ipixels/view/LedView;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextMainAdatper extends BaseQuickAdapter<ChannelListItem, BaseViewHolder> {
    private final int compression;
    private boolean isEditStatus;
    private Map<Integer, TextAnimRecycleView> mapTextAnimUtils;
    private ChannelTextAdapter textAdapter;
    private final List<TextEmojiVO> textData;

    public ImageTextMainAdatper(int i) {
        super(i, null, 2, null);
        this.compression = 1;
        this.textData = new ArrayList();
        this.mapTextAnimUtils = new LinkedHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, ChannelListItem item) {
        ViewTarget<ImageView, Drawable> into;
        ViewTarget<ImageView, Drawable> into2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13) {
            holder.setGone(R.id.img_bg, false);
        } else {
            holder.setGone(R.id.img_bg, true);
        }
        if (holder.getLayoutPosition() == getData().size() - 1) {
            ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.img);
            if (imageView != null) {
                UtilsExtensionKt.show(imageView);
            }
            RelativeLayout relativeLayout = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout != null) {
                UtilsExtensionKt.hide(relativeLayout);
            }
            int ledType = AppConfig.INSTANCE.getLedType();
            if (ledType == 0 || ledType == 2) {
                holder.setImageResource(R.id.img, R.mipmap.image_text_add_64);
            } else if (ledType == 6) {
                holder.setImageResource(R.id.img, R.mipmap.image_text_add_128);
            } else if (ledType == 13) {
                holder.setImageResource(R.id.img, R.mipmap.image_text_add_32_96);
            } else {
                holder.setImageResource(R.id.img, R.mipmap.image_text_add);
            }
        } else if (item instanceof ChannelListItem.TextEmojView) {
            RelativeLayout relativeLayout2 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            Intrinsics.checkNotNull(relativeLayout2);
            UtilsExtensionKt.show(relativeLayout2);
            ImageView imageView2 = (ImageView) holder.itemView.findViewById(R.id.img);
            if (imageView2 != null) {
                UtilsExtensionKt.hide(imageView2);
            }
            View findViewById = holder.itemView.findViewById(R.id.rl_channel_text_item);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            RecyclerView recyclerView = (RecyclerView) findViewById;
            recyclerView.setLayoutFrozen(true);
            recyclerView.setNestedScrollingEnabled(false);
            ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) item;
            recyclerView.setAlpha(textEmojView.getEventText().getTextAlpha());
            recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextMainAdatper$$ExternalSyntheticLambda0
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean convert$lambda$0;
                    convert$lambda$0 = ImageTextMainAdatper.convert$lambda$0(ImageTextMainAdatper.this, holder, view, motionEvent);
                    return convert$lambda$0;
                }
            });
            relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextMainAdatper$$ExternalSyntheticLambda1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean convert$lambda$1;
                    convert$lambda$1 = ImageTextMainAdatper.convert$lambda$1(ImageTextMainAdatper.this, holder, view, motionEvent);
                    return convert$lambda$1;
                }
            });
            initRecycle(recyclerView, holder);
            showText(textEmojView.getEventText().getTextEmojiVO(), textEmojView.getEventText().getTextColor());
            changeTextAlign(textEmojView.getEventText().getTextHorizontalAlign(), recyclerView);
            changeTextAlign(textEmojView.getEventText().getTextVerticalAlign(), recyclerView);
        } else if (item instanceof ChannelListItem.ImagView) {
            RelativeLayout relativeLayout3 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout3 != null) {
                UtilsExtensionKt.hide(relativeLayout3);
            }
            ImageView imageView3 = (ImageView) holder.itemView.findViewById(R.id.img);
            if (imageView3 != null) {
                UtilsExtensionKt.show(imageView3);
            }
            if (AppConfig.INSTANCE.getLedType() == 16) {
                into2 = Glide.with(holder.itemView).load(BGRUtils.RGB2bitmap(((ChannelListItem.ImagView) item).getArrImagData())).into((ImageView) holder.getView(R.id.img));
            } else {
                into2 = Glide.with(holder.itemView).load(((ChannelListItem.ImagView) item).getArrImagData()).into((ImageView) holder.getView(R.id.img));
            }
            Intrinsics.checkNotNull(into2);
        } else if (item instanceof ChannelListItem.GiftView) {
            RelativeLayout relativeLayout4 = (RelativeLayout) holder.itemView.findViewById(R.id.relayout_channel_text);
            if (relativeLayout4 != null) {
                UtilsExtensionKt.hide(relativeLayout4);
            }
            ImageView imageView4 = (ImageView) holder.itemView.findViewById(R.id.img);
            if (imageView4 != null) {
                UtilsExtensionKt.show(imageView4);
            }
            ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) item;
            String path = giftView.getPath();
            if (path == null || path.length() == 0) {
                into = Glide.with(holder.itemView).load(giftView.getArrGifData()).into((ImageView) holder.getView(R.id.img));
            } else {
                into = Glide.with(holder.itemView).load(giftView.getPath()).into((ImageView) holder.getView(R.id.img));
            }
            Intrinsics.checkNotNull(into);
        }
        if (holder.getLayoutPosition() == 6) {
            holder.itemView.setVisibility(8);
        } else {
            holder.itemView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean convert$lambda$0(ImageTextMainAdatper imageTextMainAdatper, BaseViewHolder baseViewHolder, View view, MotionEvent motionEvent) {
        View itemView = baseViewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        imageTextMainAdatper.setOnItemClick(itemView, baseViewHolder.getLayoutPosition());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean convert$lambda$1(ImageTextMainAdatper imageTextMainAdatper, BaseViewHolder baseViewHolder, View view, MotionEvent motionEvent) {
        View itemView = baseViewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        imageTextMainAdatper.setOnItemClick(itemView, baseViewHolder.getLayoutPosition());
        return false;
    }

    public final void setEditStatus(boolean editStatus) {
        this.isEditStatus = editStatus;
        notifyDataSetChanged();
    }

    private final void initRecycle(RecyclerView rlItem, final BaseViewHolder holder) {
        int i = ScreenUtils.getScreenWidth() != 1080 ? 7 : 8;
        if (AppConfig.INSTANCE.getLedType() == 7) {
            i = 6;
        }
        if (AppConfig.INSTANCE.getLedType() == 8) {
            i = 4;
        }
        this.textAdapter = new ChannelTextAdapter(getContext(), this.textData, i);
        rlItem.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        rlItem.setAdapter(this.textAdapter);
        ChannelTextAdapter channelTextAdapter = this.textAdapter;
        Intrinsics.checkNotNull(channelTextAdapter);
        channelTextAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextMainAdatper$$ExternalSyntheticLambda2
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i2) {
                ImageTextMainAdatper.initRecycle$lambda$2(ImageTextMainAdatper.this, holder, viewGroup, view, (TextEmojiVO) obj, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRecycle$lambda$2(ImageTextMainAdatper imageTextMainAdatper, BaseViewHolder baseViewHolder, ViewGroup viewGroup, View view, TextEmojiVO textEmojiVO, int i) {
        View itemView = baseViewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        imageTextMainAdatper.setOnItemClick(itemView, baseViewHolder.getLayoutPosition());
    }

    static /* synthetic */ void showText$default(ImageTextMainAdatper imageTextMainAdatper, List list, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        imageTextMainAdatper.showText(list, i);
    }

    private final void showText(List<TextEmojiVO> textEmojiVO, int textColor) {
        ArrayList arrayList = new ArrayList();
        for (TextEmojiVO textEmojiVO2 : textEmojiVO) {
            if (textColor != textEmojiVO2.getTextColor()) {
                textEmojiVO2.setTextColor(textColor);
            }
        }
        arrayList.addAll(textEmojiVO);
        this.textData.clear();
        this.textData.addAll(arrayList);
        ChannelTextAdapter channelTextAdapter = this.textAdapter;
        if (channelTextAdapter != null) {
            channelTextAdapter.addData(arrayList);
        }
        ChannelTextAdapter channelTextAdapter2 = this.textAdapter;
        if (channelTextAdapter2 != null) {
            channelTextAdapter2.notifyDataSetChanged();
        }
    }

    private final void changeTextAlign(int it, RecyclerView rlItem) {
        if (it >= 0 && it < 3) {
            if (it == 0) {
                setHorizontalAlign(0, rlItem);
                return;
            } else if (it == 1) {
                setHorizontalAlign(2, rlItem);
                return;
            } else {
                setHorizontalAlign(1, rlItem);
                return;
            }
        }
        if (3 > it || it >= 6) {
            return;
        }
        if (it == 3) {
            setVerticalAlign(10, rlItem);
        } else if (it == 4) {
            setVerticalAlign(15, rlItem);
        } else {
            setVerticalAlign(12, rlItem);
        }
    }

    private final ObjectAnimator loadEffect(TextAnimUtils ins, EventText it, RecyclerView rlItem) {
        rlItem.setLayoutManager(null);
        int textEffect = it.getTextEffect();
        if (textEffect == 1 || textEffect == 2) {
            final Context context = getContext();
            final boolean z = 2 == it.getTextEffect();
            Object obj = new WeakReference(new LinearLayoutManager(context, z) { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextMainAdatper$loadEffect$layoutManager$1
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return true;
                }
            }).get();
            Intrinsics.checkNotNull(obj);
            rlItem.setLayoutManager((RecyclerView.LayoutManager) obj);
        } else {
            final Context context2 = getContext();
            WeakReference weakReference = new WeakReference(new XFlexboxLayoutManager(context2) { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextMainAdatper$loadEffect$layoutManager$2
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
