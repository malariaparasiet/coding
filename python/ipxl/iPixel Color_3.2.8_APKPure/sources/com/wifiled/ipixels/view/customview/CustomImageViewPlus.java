package com.wifiled.ipixels.view.customview;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.wifiled.ipixels.utils.VibrateHelp;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomImageViewPlus.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomImageViewPlus;", "", "<init>", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomImageViewPlus {
    private static boolean isLongClickModule;
    private static float startX;
    private static float startY;
    private static Timer timer;
    private static VibrateHelp vibrateHelp;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: boolean, reason: not valid java name */
    private static boolean f8boolean = true;
    private static Set<View> viewList = new LinkedHashSet();

    /* compiled from: CustomImageViewPlus.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001eH\u0007J\u001c\u0010#\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010\u001e2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u001e\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/wifiled/ipixels/view/customview/CustomImageViewPlus$Companion;", "Landroid/view/View$OnTouchListener;", "<init>", "()V", TypedValues.Custom.S_BOOLEAN, "", "getBoolean", "()Z", "setBoolean", "(Z)V", "isLongClickModule", "vibrateHelp", "Lcom/wifiled/ipixels/utils/VibrateHelp;", "startX", "", "getStartX", "()F", "setStartX", "(F)V", "startY", "getStartY", "setStartY", "timer", "Ljava/util/Timer;", "getTimer", "()Ljava/util/Timer;", "setTimer", "(Ljava/util/Timer;)V", "viewList", "", "Landroid/view/View;", "detachView", "", "attachViewOnTouchListener", "viewIns", "onTouch", "v", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "isTouchPointAboveView", "view", "x", "", "y", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements View.OnTouchListener {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getBoolean() {
            return CustomImageViewPlus.f8boolean;
        }

        public final void setBoolean(boolean z) {
            CustomImageViewPlus.f8boolean = z;
        }

        public final float getStartX() {
            return CustomImageViewPlus.startX;
        }

        public final void setStartX(float f) {
            CustomImageViewPlus.startX = f;
        }

        public final float getStartY() {
            return CustomImageViewPlus.startY;
        }

        public final void setStartY(float f) {
            CustomImageViewPlus.startY = f;
        }

        public final Timer getTimer() {
            return CustomImageViewPlus.timer;
        }

        public final void setTimer(Timer timer) {
            CustomImageViewPlus.timer = timer;
        }

        public final void detachView() {
            CustomImageViewPlus.viewList.clear();
        }

        public final void attachViewOnTouchListener(View viewIns) {
            Intrinsics.checkNotNullParameter(viewIns, "viewIns");
            if (!CustomImageViewPlus.viewList.contains(viewIns)) {
                CustomImageViewPlus.viewList.add(viewIns);
            }
            viewIns.setOnTouchListener(this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00c4  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouch(final android.view.View r9, android.view.MotionEvent r10) {
            /*
                Method dump skipped, instructions count: 281
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.view.customview.CustomImageViewPlus.Companion.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }

        public final boolean isTouchPointAboveView(View view, int x, int y) {
            Intrinsics.checkNotNullParameter(view, "view");
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            return i2 <= y && y <= view.getMeasuredHeight() + i2 && x >= i && x <= view.getMeasuredWidth() + i;
        }
    }
}
