package com.wifiled.baselib.uicode.inner;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IBaseViewModel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/IBaseViewModel;", "Lcom/wifiled/baselib/uicode/inner/ICoreView;", "Landroidx/lifecycle/LifecycleObserver;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface IBaseViewModel extends ICoreView, LifecycleObserver, DefaultLifecycleObserver {

    /* compiled from: IBaseViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void onCreate(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onCreate(owner);
        }

        @Deprecated
        public static void onDestroy(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onDestroy(owner);
        }

        @Deprecated
        public static void onPause(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onPause(owner);
        }

        @Deprecated
        public static void onResume(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onResume(owner);
        }

        @Deprecated
        public static void onStart(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onStart(owner);
        }

        @Deprecated
        public static void onStop(IBaseViewModel iBaseViewModel, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            IBaseViewModel.super.onStop(owner);
        }
    }
}
