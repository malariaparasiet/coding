package com.wifiled.baselib.uicode.statuslayout;

import android.R;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReplaceLayoutHelper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0011\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/baselib/uicode/statuslayout/ReplaceLayoutHelper;", "", "contentLayout", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "params", "Landroid/view/ViewGroup$LayoutParams;", "parentLayout", "Landroid/view/ViewGroup;", "viewIndex", "", "currentLayout", "getContentLayoutParams", "", "showStatusLayout", "view", "restoreLayout", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReplaceLayoutHelper {
    private final View contentLayout;
    private View currentLayout;
    private ViewGroup.LayoutParams params;
    private ViewGroup parentLayout;
    private int viewIndex;

    public ReplaceLayoutHelper(View contentLayout) {
        Intrinsics.checkNotNullParameter(contentLayout, "contentLayout");
        this.contentLayout = contentLayout;
        getContentLayoutParams();
    }

    private final void getContentLayoutParams() {
        this.params = this.contentLayout.getLayoutParams();
        ViewParent parent = this.contentLayout.getParent();
        if (parent != null) {
            this.parentLayout = (ViewGroup) parent;
        } else {
            this.parentLayout = (ViewGroup) this.contentLayout.getRootView().findViewById(R.id.content);
        }
        ViewGroup viewGroup = this.parentLayout;
        int i = 0;
        if (viewGroup == null) {
            View view = this.contentLayout;
            if (view instanceof ViewGroup) {
                this.parentLayout = (ViewGroup) view;
                this.viewIndex = 0;
            } else {
                throw new IllegalStateException("参数错误：StatusLayoutManager#Build#with() 方法，不能传如一个非 ViewGroup 的跟布局");
            }
        } else {
            Intrinsics.checkNotNull(viewGroup);
            int childCount = viewGroup.getChildCount();
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View view2 = this.contentLayout;
                ViewGroup viewGroup2 = this.parentLayout;
                Intrinsics.checkNotNull(viewGroup2);
                if (Intrinsics.areEqual(view2, viewGroup2.getChildAt(i))) {
                    this.viewIndex = i;
                    break;
                }
                i++;
            }
        }
        View view3 = this.contentLayout;
        this.currentLayout = view3;
        Log.d("status layout", "status layout getContentLayoutParams currentLayout:" + view3 + ",parentLayout:" + this.parentLayout + ";viewIndex:" + this.viewIndex);
    }

    public final void showStatusLayout(View view) {
        if (view == null) {
            Log.e("status layout", "showStatusLayout  view is null");
            return;
        }
        if (Intrinsics.areEqual(this.currentLayout, view)) {
            Log.e("status layout", "showStatusLayout  currentLayout == view " + view);
        } else {
            this.currentLayout = view;
            ViewParent parent = view.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(view);
            }
            ViewGroup viewGroup = this.parentLayout;
            Intrinsics.checkNotNull(viewGroup);
            viewGroup.removeViewAt(this.viewIndex);
            ViewGroup viewGroup2 = this.parentLayout;
            Intrinsics.checkNotNull(viewGroup2);
            viewGroup2.addView(view, this.viewIndex, this.params);
        }
        Log.d("status layout", "status layout show currentLayout:" + this.currentLayout + ",view:" + view + ";viewIndex:" + this.viewIndex);
    }

    public final void restoreLayout() {
        showStatusLayout(this.contentLayout);
    }
}
