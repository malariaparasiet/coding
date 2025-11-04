package com.wifiled.ipixels.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.NotificationCompat;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomSpiner.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomSpiner;", "Landroidx/appcompat/widget/AppCompatSpinner;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomSpiner extends AppCompatSpinner {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Set<CustomSpiner> viewList = new HashSet();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSpiner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* compiled from: CustomSpiner.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomSpiner$Companion;", "Landroid/view/View$OnTouchListener;", "<init>", "()V", "viewList", "", "Lcom/wifiled/ipixels/view/customview/CustomSpiner;", "attachViewOnTouchListener", "", "viewIns", "onTouch", "", "v", "Landroid/view/View;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "isTouchPointInView", "view", "x", "", "y", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements View.OnTouchListener {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void attachViewOnTouchListener(CustomSpiner viewIns) {
            Intrinsics.checkNotNullParameter(viewIns, "viewIns");
            if (CustomSpiner.viewList.contains(viewIns)) {
                return;
            }
            SetsKt.plus((Set<? extends CustomSpiner>) CustomSpiner.viewList, viewIns);
            viewIns.setOnTouchListener(this);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            Boolean valueOf;
            Integer valueOf2 = event != null ? Integer.valueOf(event.getAction()) : null;
            if (valueOf2 != null && valueOf2.intValue() == 0) {
                if (v != null) {
                    v.setAlpha(0.5f);
                }
            } else if (valueOf2 != null && valueOf2.intValue() == 1) {
                if (v != null) {
                    v.setAlpha(1.0f);
                }
                valueOf = v != null ? Boolean.valueOf(CustomSpiner.INSTANCE.isTouchPointInView(v, (int) event.getRawX(), (int) event.getRawY())) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    v.performClick();
                }
            } else if (valueOf2 != null && valueOf2.intValue() == 2) {
                valueOf = v != null ? Boolean.valueOf(CustomSpiner.INSTANCE.isTouchPointInView(v, (int) event.getRawX(), (int) event.getRawY())) : null;
                Intrinsics.checkNotNull(valueOf);
                boolean booleanValue = valueOf.booleanValue();
                if (booleanValue) {
                    v.setAlpha(0.5f);
                } else {
                    if (booleanValue) {
                        throw new NoWhenBranchMatchedException();
                    }
                    v.setAlpha(1.0f);
                }
            }
            return true;
        }

        public final boolean isTouchPointInView(View view, int x, int y) {
            Intrinsics.checkNotNullParameter(view, "view");
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            return i2 <= y && y <= view.getMeasuredHeight() + i2 && x >= i && x <= view.getMeasuredWidth() + i;
        }
    }
}
