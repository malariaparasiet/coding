package com.wifiled.ipixels.ui.text;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyboardHeightProvider.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/ui/text/KeyboardHeightProvider;", "Landroid/widget/PopupWindow;", "activity", "Landroid/app/Activity;", "<init>", "(Landroid/app/Activity;)V", "observer", "Lcom/wifiled/ipixels/ui/text/KeyboardHeightObserver;", "keyboardLandscapeHeight", "", "keyboardPortraitHeight", "popupView", "Landroid/view/View;", "parentView", "start", "", "close", "setKeyboardHeightObserver", "screenOrientation", "getScreenOrientation", "()I", "handleOnGlobalLayout", "notifyKeyboardHeightChanged", "height", "orientation", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KeyboardHeightProvider extends PopupWindow {
    private static final String TAG = "sample_KeyboardHeightProvider";
    private final Activity activity;
    private int keyboardLandscapeHeight;
    private int keyboardPortraitHeight;
    private KeyboardHeightObserver observer;
    private final View parentView;
    private final View popupView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardHeightProvider(Activity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        Object systemService = activity.getSystemService("layout_inflater");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.keyboard_popup_window, (ViewGroup) null, false);
        this.popupView = inflate;
        setContentView(inflate);
        setSoftInputMode(21);
        setInputMethodMode(1);
        View findViewById = activity.findViewById(android.R.id.content);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.parentView = findViewById;
        setWidth(0);
        setHeight(-1);
        Intrinsics.checkNotNull(inflate);
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.wifiled.ipixels.ui.text.KeyboardHeightProvider$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                KeyboardHeightProvider.this.handleOnGlobalLayout();
            }
        });
    }

    public final void start() {
        if (isShowing() || this.parentView.getWindowToken() == null) {
            return;
        }
        setBackgroundDrawable(new ColorDrawable(0));
        showAtLocation(this.parentView, 0, 0, 0);
    }

    public final void close() {
        this.observer = null;
        dismiss();
    }

    public final void setKeyboardHeightObserver(KeyboardHeightObserver observer) {
        this.observer = observer;
    }

    private final int getScreenOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleOnGlobalLayout() {
        Point point = new Point();
        this.activity.getWindowManager().getDefaultDisplay().getSize(point);
        Rect rect = new Rect();
        View view = this.popupView;
        Intrinsics.checkNotNull(view);
        view.getWindowVisibleDisplayFrame(rect);
        int screenOrientation = getScreenOrientation();
        int i = point.y - rect.bottom;
        if (i == 0) {
            notifyKeyboardHeightChanged(0, screenOrientation);
        } else if (screenOrientation == 1) {
            this.keyboardPortraitHeight = i;
            notifyKeyboardHeightChanged(i, screenOrientation);
        } else {
            this.keyboardLandscapeHeight = i;
            notifyKeyboardHeightChanged(i, screenOrientation);
        }
    }

    private final void notifyKeyboardHeightChanged(int height, int orientation) {
        KeyboardHeightObserver keyboardHeightObserver = this.observer;
        if (keyboardHeightObserver != null) {
            keyboardHeightObserver.onKeyboardHeightChanged(height, orientation);
        }
    }
}
