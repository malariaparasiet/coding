package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.ipixels.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IosDialogStyleAdapter.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000f\u001a\u00020\nH\u0016J\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0017¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "iSel", "", "getISel", "()I", "setISel", "(I)V", "layoutId", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "action", "(Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;Ljava/lang/Object;)V", "adaptiveRecyclerViewHeight", "targetNum", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IosDialogStyleAdapter<T> extends RecyclerAdapter<T> {
    private int iSel;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_action;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IosDialogStyleAdapter(Context context, List<? extends T> data) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.iSel = -1;
    }

    public final int getISel() {
        return this.iSel;
    }

    public final void setISel(int i) {
        this.iSel = i;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, T action) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ((TextView) holder.getView(R.id.tv_action)).setText(String.valueOf(action));
    }

    public final void adaptiveRecyclerViewHeight(final int targetNum, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        final Context context = ContextHolder.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context) { // from class: com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter$adaptiveRecyclerViewHeight$1
            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                Intrinsics.checkNotNullParameter(recycler, "recycler");
                Intrinsics.checkNotNullParameter(state, "state");
                int itemCount = state.getItemCount();
                if (itemCount > 0) {
                    int i = targetNum;
                    if (itemCount > i) {
                        itemCount = i;
                    }
                    int i2 = 0;
                    int i3 = 0;
                    for (int i4 = 0; i4 < itemCount; i4++) {
                        View viewForPosition = recycler.getViewForPosition(0);
                        Intrinsics.checkNotNullExpressionValue(viewForPosition, "getViewForPosition(...)");
                        measureChild(viewForPosition, widthSpec, heightSpec);
                        int size = View.MeasureSpec.getSize(widthSpec);
                        int measuredHeight = viewForPosition.getMeasuredHeight();
                        if (i2 <= size) {
                            i2 = size;
                        }
                        i3 += measuredHeight;
                    }
                    setMeasuredDimension(i2, i3);
                    return;
                }
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        });
    }
}
