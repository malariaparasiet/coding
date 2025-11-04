package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnimAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001c\u001d\u001eB!\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/AnimAdapter;", "Landroidx/paging/PagingDataAdapter;", "Lcom/wifiled/baselib/data/Record;", "Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$ViewHolder;", "ledType", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "<init>", "(ILkotlinx/coroutines/CoroutineScope;Landroid/content/Context;)V", "m_iSelect", "mLedType", "onBindViewHolder", "", "holder", PlayerFinal.PLAYER_POSITION, "callback", "Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$Callback;", "getCallback", "()Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$Callback;", "setCallback", "(Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$Callback;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "Callback", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AnimAdapter extends PagingDataAdapter<Record, ViewHolder> {
    private Callback callback;
    private final Context context;
    private int mLedType;
    private int m_iSelect;
    private final CoroutineScope scope;
    private static final AnimAdapter$Companion$COMPARATOR$1 COMPARATOR = new DiffUtil.ItemCallback<Record>() { // from class: com.wifiled.ipixels.ui.adapter.AnimAdapter$Companion$COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(Record oldItem, Record newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getFilePath(), newItem.getFilePath());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(Record oldItem, Record newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getFilePath(), newItem.getFilePath());
        }
    };

    /* compiled from: AnimAdapter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0004H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$Callback;", "", "item", "", "Lcom/wifiled/baselib/data/Record;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback {
        void item(Record item);
    }

    public /* synthetic */ AnimAdapter(int i, CoroutineScope coroutineScope, Context context, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? AppConfig.INSTANCE.getLedType() : i, coroutineScope, context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimAdapter(int i, CoroutineScope scope, Context context) {
        super(COMPARATOR, null, null, 6, null);
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(context, "context");
        this.scope = scope;
        this.context = context;
        this.m_iSelect = -1;
        this.mLedType = i;
    }

    /* compiled from: AnimAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/AnimAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "preview", "Landroid/widget/ImageView;", "getPreview", "()Landroid/widget/ImageView;", "setPreview", "(Landroid/widget/ImageView;)V", "outsideFrame", "Landroid/widget/RelativeLayout;", "getOutsideFrame", "()Landroid/widget/RelativeLayout;", "setOutsideFrame", "(Landroid/widget/RelativeLayout;)V", "selectRl", "getSelectRl", "setSelectRl", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout outsideFrame;
        private ImageView preview;
        private RelativeLayout selectRl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.iv_preview);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.preview = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.rl_image_outside_frame);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.outsideFrame = (RelativeLayout) findViewById2;
            if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13) {
                this.selectRl = (RelativeLayout) view.findViewById(R.id.rl_select);
            }
        }

        public final ImageView getPreview() {
            return this.preview;
        }

        public final void setPreview(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.preview = imageView;
        }

        public final RelativeLayout getOutsideFrame() {
            return this.outsideFrame;
        }

        public final void setOutsideFrame(RelativeLayout relativeLayout) {
            Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
            this.outsideFrame = relativeLayout;
        }

        public final RelativeLayout getSelectRl() {
            return this.selectRl;
        }

        public final void setSelectRl(RelativeLayout relativeLayout) {
            this.selectRl = relativeLayout;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnimAdapter animAdapter;
        final ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = R.drawable.buffer;
        switch (this.mLedType) {
            case 0:
            case 2:
                intRef.element = R.drawable.buffer;
                break;
            case 1:
            case 15:
                intRef.element = R.drawable.image_loading_16x96;
                break;
            case 3:
            case 6:
            case 12:
                intRef.element = R.drawable.image_loading_16x64;
                break;
            case 4:
                intRef.element = R.drawable.image_loading_16x32;
                break;
            case 5:
                intRef.element = R.drawable.image_loading_20x64;
                break;
            case 7:
                intRef.element = R.drawable.image_loading_16x144;
                break;
            case 8:
                intRef.element = R.drawable.image_loading_16x192;
                break;
            case 9:
                intRef.element = R.drawable.image_loading_16x32;
                break;
            case 10:
                intRef.element = R.drawable.image_loading_16x32;
                break;
            case 11:
                intRef.element = R.drawable.image_loading_1_3;
                break;
            case 13:
                intRef.element = R.drawable.image_loading_1_3;
                break;
            case 14:
                intRef.element = R.drawable.image_loading_1_5;
                break;
            case 16:
                intRef.element = R.drawable.image_loading_1_8;
                break;
            case 17:
                intRef.element = R.drawable.image_loading_1_10;
                break;
            case 18:
                intRef.element = R.drawable.image_loading_1_12;
                break;
            case 19:
                intRef.element = R.drawable.image_loading_1_14;
                break;
        }
        final Record item = getItem(holder.getLayoutPosition());
        if (item != null) {
            if (item.getFile() == null) {
                animAdapter = this;
                viewHolder = holder;
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnimAdapter$onBindViewHolder$1$1(item, animAdapter, viewHolder, intRef, null), 3, null);
            } else {
                animAdapter = this;
                viewHolder = holder;
                Intrinsics.checkNotNull(Glide.with(viewHolder.itemView).load(item.getFile()).placeholder(intRef.element).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.getPreview()));
            }
            boolean z = animAdapter.m_iSelect == viewHolder.getLayoutPosition();
            if (z) {
                if (AppConfig.INSTANCE.getLedType() == 6) {
                    RelativeLayout selectRl = viewHolder.getSelectRl();
                    if (selectRl != null) {
                        selectRl.setBackgroundResource(R.drawable.image_128_item_sel);
                    }
                } else if (AppConfig.INSTANCE.getLedType() == 13) {
                    RelativeLayout selectRl2 = viewHolder.getSelectRl();
                    if (selectRl2 != null) {
                        selectRl2.setBackgroundResource(R.drawable.image_96_item_sel);
                    }
                } else {
                    viewHolder.getOutsideFrame().setBackgroundResource(R.drawable.item_sel);
                }
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13) {
                    RelativeLayout selectRl3 = viewHolder.getSelectRl();
                    if (selectRl3 != null) {
                        selectRl3.setBackgroundResource(R.color.transparent);
                    }
                } else {
                    viewHolder.getOutsideFrame().setBackgroundResource(R.color.transparent);
                }
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.adapter.AnimAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AnimAdapter.onBindViewHolder$lambda$1$lambda$0(AnimAdapter.this, viewHolder, item, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1$lambda$0(AnimAdapter animAdapter, ViewHolder viewHolder, Record record, View view) {
        if (ClickFilter.filter()) {
            return;
        }
        int i = animAdapter.m_iSelect;
        if (i != -1) {
            animAdapter.notifyItemChanged(i);
        }
        animAdapter.m_iSelect = viewHolder.getLayoutPosition();
        Callback callback = animAdapter.callback;
        if (callback != null) {
            callback.item(record);
        }
        animAdapter.notifyItemChanged(animAdapter.m_iSelect);
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        int i = this.mLedType;
        int i2 = R.layout.item_image_1632;
        switch (i) {
            case 1:
            case 15:
                i2 = R.layout.item_image_1696;
                break;
            case 2:
            default:
                i2 = R.layout.item_image;
                break;
            case 3:
            case 12:
                i2 = R.layout.item_image_1664;
                break;
            case 4:
            case 9:
            case 10:
                break;
            case 5:
                i2 = R.layout.item_image_2064;
                break;
            case 6:
                i2 = R.layout.item_image_32128;
                break;
            case 7:
                i2 = R.layout.item_image_16144;
                break;
            case 8:
                i2 = R.layout.item_image_16192;
                break;
            case 11:
                i2 = R.layout.item_image_3296;
                break;
            case 13:
                i2 = R.layout.item_image_3296_2;
                break;
            case 14:
                i2 = R.layout.item_image_32160;
                break;
            case 16:
                i2 = R.layout.item_image_32256;
                break;
            case 17:
                i2 = R.layout.item_image_32320;
                break;
            case 18:
                i2 = R.layout.item_image_32384;
                break;
            case 19:
                i2 = R.layout.item_image_32448;
                break;
        }
        View inflate = LayoutInflater.from(parent.getContext()).inflate(i2, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(inflate);
    }
}
