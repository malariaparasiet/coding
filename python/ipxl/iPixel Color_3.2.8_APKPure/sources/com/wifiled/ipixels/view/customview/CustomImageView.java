package com.wifiled.ipixels.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomImageView.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomImageView extends AppCompatImageView {
    private static boolean isLongClickModule;
    private static float startX;
    private static float startY;
    private static Timer timer;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Set<View> viewList = new LinkedHashSet();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* compiled from: CustomImageView.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0017H\u0007J\u001c\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001e\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#J\u0010\u0010%\u001a\u00020\u00192\b\b\u0002\u0010&\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomImageView$Companion;", "Landroid/view/View$OnTouchListener;", "<init>", "()V", "isLongClickModule", "", "startX", "", "getStartX", "()F", "setStartX", "(F)V", "startY", "getStartY", "setStartY", "timer", "Ljava/util/Timer;", "getTimer", "()Ljava/util/Timer;", "setTimer", "(Ljava/util/Timer;)V", "viewList", "", "Landroid/view/View;", "detachView", "", "attachViewOnTouchListener", "viewIns", "onTouch", "v", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "isTouchPointAboveView", "view", "x", "", "y", "setViewAlpha", "brigth", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements View.OnTouchListener {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final float getStartX() {
            return CustomImageView.startX;
        }

        public final void setStartX(float f) {
            CustomImageView.startX = f;
        }

        public final float getStartY() {
            return CustomImageView.startY;
        }

        public final void setStartY(float f) {
            CustomImageView.startY = f;
        }

        public final Timer getTimer() {
            return CustomImageView.timer;
        }

        public final void setTimer(Timer timer) {
            CustomImageView.timer = timer;
        }

        public final void detachView() {
            CustomImageView.viewList.clear();
        }

        public final void attachViewOnTouchListener(View viewIns) {
            Intrinsics.checkNotNullParameter(viewIns, "viewIns");
            if (!CustomImageView.viewList.contains(viewIns)) {
                CustomImageView.viewList.add(viewIns);
            }
            viewIns.setOnTouchListener(this);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d4  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
            /*
                Method dump skipped, instructions count: 281
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.view.customview.CustomImageView.Companion.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }

        public final boolean isTouchPointAboveView(View view, int x, int y) {
            Intrinsics.checkNotNullParameter(view, "view");
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            return i2 <= y && y <= view.getMeasuredHeight() + i2 && x >= i && x <= view.getMeasuredWidth() + i;
        }

        public static /* synthetic */ void setViewAlpha$default(Companion companion, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = 1.0f;
            }
            companion.setViewAlpha(f);
        }

        public final void setViewAlpha(float brigth) {
            Iterator it = CustomImageView.viewList.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setAlpha(brigth);
            }
        }
    }
}
