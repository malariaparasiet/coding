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
import com.wifiled.ipixels.ui.adapter.EyeAnimAdapter;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: EyeAnimAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001c\u001d\u001eB!\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter;", "Landroidx/paging/PagingDataAdapter;", "Lcom/wifiled/baselib/data/Record;", "Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$ViewHolder;", "ledType", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "<init>", "(ILkotlinx/coroutines/CoroutineScope;Landroid/content/Context;)V", "m_iSelect", "mLedType", "onBindViewHolder", "", "holder", PlayerFinal.PLAYER_POSITION, "callback", "Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$Callback;", "getCallback", "()Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$Callback;", "setCallback", "(Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$Callback;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "Callback", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EyeAnimAdapter extends PagingDataAdapter<Record, ViewHolder> {
    private Callback callback;
    private final Context context;
    private int mLedType;
    private int m_iSelect;
    private final CoroutineScope scope;
    private static final EyeAnimAdapter$Companion$COMPARATOR$1 COMPARATOR = new DiffUtil.ItemCallback<Record>() { // from class: com.wifiled.ipixels.ui.adapter.EyeAnimAdapter$Companion$COMPARATOR$1
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

    /* compiled from: EyeAnimAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$Callback;", "", "item", "", "itemLeft", "Lcom/wifiled/baselib/data/Record;", "itemRight", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback {
        void item(Record itemLeft, Record itemRight);
    }

    public /* synthetic */ EyeAnimAdapter(int i, CoroutineScope coroutineScope, Context context, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? AppConfig.INSTANCE.getLedType() : i, coroutineScope, context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EyeAnimAdapter(int i, CoroutineScope scope, Context context) {
        super(COMPARATOR, null, null, 6, null);
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(context, "context");
        this.scope = scope;
        this.context = context;
        this.m_iSelect = -1;
        this.mLedType = i;
    }

    /* compiled from: EyeAnimAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/EyeAnimAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "preview", "Landroid/widget/ImageView;", "getPreview", "()Landroid/widget/ImageView;", "setPreview", "(Landroid/widget/ImageView;)V", "outsideFrame", "Landroid/widget/RelativeLayout;", "getOutsideFrame", "()Landroid/widget/RelativeLayout;", "setOutsideFrame", "(Landroid/widget/RelativeLayout;)V", "selectRl", "getSelectRl", "setSelectRl", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        final ViewHolder viewHolder;
        EyeAnimAdapter eyeAnimAdapter;
        boolean z;
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
        }
        Record item = getItem(holder.getLayoutPosition());
        if (item != null) {
            if (item.getFile() == null) {
                eyeAnimAdapter = this;
                viewHolder = holder;
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new EyeAnimAdapter$onBindViewHolder$1$1(item, eyeAnimAdapter, viewHolder, intRef, null), 3, null);
            } else {
                eyeAnimAdapter = this;
                viewHolder = holder;
                Intrinsics.checkNotNull(Glide.with(viewHolder.itemView).load(item.getFile()).placeholder(intRef.element).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.getPreview()));
            }
            int i = eyeAnimAdapter.m_iSelect;
            if ((i & 1) == 0) {
                z = i == viewHolder.getLayoutPosition() || eyeAnimAdapter.m_iSelect + 1 == viewHolder.getLayoutPosition();
                if (z) {
                    viewHolder.getOutsideFrame().setBackgroundResource(R.drawable.item_sel);
                } else {
                    if (z) {
                        throw new NoWhenBranchMatchedException();
                    }
                    viewHolder.getOutsideFrame().setBackgroundResource(R.color.transparent);
                }
            } else {
                z = i == viewHolder.getLayoutPosition() || eyeAnimAdapter.m_iSelect - 1 == viewHolder.getLayoutPosition();
                if (z) {
                    viewHolder.getOutsideFrame().setBackgroundResource(R.drawable.item_sel);
                } else {
                    if (z) {
                        throw new NoWhenBranchMatchedException();
                    }
                    viewHolder.getOutsideFrame().setBackgroundResource(R.color.transparent);
                }
            }
        } else {
            viewHolder = holder;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.adapter.EyeAnimAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EyeAnimAdapter.onBindViewHolder$lambda$5(EyeAnimAdapter.ViewHolder.this, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$5(ViewHolder viewHolder, EyeAnimAdapter eyeAnimAdapter, View view) {
        Record item;
        Callback callback;
        Record item2;
        Callback callback2;
        if (ClickFilter.filter()) {
            return;
        }
        if ((viewHolder.getLayoutPosition() & 1) == 0) {
            int i = eyeAnimAdapter.m_iSelect;
            if (i != -1) {
                eyeAnimAdapter.notifyItemChanged(i);
                eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect + 1);
                int i2 = eyeAnimAdapter.m_iSelect;
                if (i2 > 0) {
                    eyeAnimAdapter.notifyItemChanged(i2 - 1);
                }
            }
            eyeAnimAdapter.m_iSelect = viewHolder.getLayoutPosition();
            Record item3 = eyeAnimAdapter.getItem(viewHolder.getLayoutPosition());
            if (item3 != null && (item2 = eyeAnimAdapter.getItem(viewHolder.getLayoutPosition() + 1)) != null && (callback2 = eyeAnimAdapter.callback) != null) {
                callback2.item(item3, item2);
            }
            eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect);
            eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect + 1);
            return;
        }
        int i3 = eyeAnimAdapter.m_iSelect;
        if (i3 != -1) {
            eyeAnimAdapter.notifyItemChanged(i3);
            eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect - 1);
            eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect + 1);
        }
        eyeAnimAdapter.m_iSelect = viewHolder.getLayoutPosition();
        Record item4 = eyeAnimAdapter.getItem(viewHolder.getLayoutPosition());
        if (item4 != null && (item = eyeAnimAdapter.getItem(viewHolder.getLayoutPosition() - 1)) != null && (callback = eyeAnimAdapter.callback) != null) {
            callback.item(item, item4);
        }
        eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect);
        eyeAnimAdapter.notifyItemChanged(eyeAnimAdapter.m_iSelect - 1);
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
        int i2 = R.layout.item_image_1632_eye;
        switch (i) {
            case 1:
            case 15:
                i2 = R.layout.item_image_1696_eye;
                break;
            case 2:
            default:
                i2 = R.layout.item_image;
                break;
            case 3:
            case 12:
                i2 = R.layout.item_image_1664_eye;
                break;
            case 4:
            case 9:
            case 10:
                break;
            case 5:
                i2 = R.layout.item_image_2064_eye;
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
        }
        View inflate = LayoutInflater.from(parent.getContext()).inflate(i2, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(inflate);
    }
}
