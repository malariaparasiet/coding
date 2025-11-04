package com.wifiled.ipixels;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.autofill.HintConstants;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.heaton.baselib.app.event.LiveDataBus;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.callback.CallBack;
import com.wifiled.baselib.callback.CallBackUI;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.baselib.utils.HandlerUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.baselib.utils.ViewUtils;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.customview.IOSLoadingView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.io.File;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: UtilsExtension.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\b2\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u001a\u001e\u0010\t\u001a\u00020\n*\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a\u0010\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u0012\u001a'\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u0017\"\u00020\u0018¢\u0006\u0002\u0010\u0019\u001a\u001f\u0010\u001a\u001a\u00020\n2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u0017\"\u00020\u0018¢\u0006\u0002\u0010\u001b\u001a\u0018\u0010\u001c\u001a\u00020\n*\u00020\u00182\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a-\u0010\u001e\u001a\u00020\n*\u00020\u001f2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\n0 \u001ax\u0010$\u001a\u00020\n\"\u0004\b\u0000\u0010%*\b\u0012\u0004\u0012\u0002H%0&2`\u0010\u001d\u001a\\\u0012\u0013\u0012\u00110(¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(*\u0012\u0013\u0012\u0011H%¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\n0'\u001a\n\u0010-\u001a\u00020\n*\u00020\u0018\u001a\n\u0010.\u001a\u00020\n*\u00020\u0018\u001a\n\u0010/\u001a\u00020\n*\u00020\u0018\u001a!\u00100\u001a\u00020\n\"\u0004\b\u0000\u0010%2\u0006\u00101\u001a\u00020\r2\u0006\u00102\u001a\u0002H%¢\u0006\u0002\u00103\u001a\u001f\u00104\u001a\b\u0012\u0004\u0012\u0002H%05\"\u0006\b\u0000\u0010%\u0018\u00012\u0006\u00101\u001a\u00020\rH\u0086\b\u001a*\u00106\u001a\u00020\n*\u00020\u00042\b\b\u0002\u00107\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a8\u00106\u001a\u00020\n*\u0002092\b\b\u0002\u00107\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a*\u0010A\u001a\u00020\n*\u0002092\b\b\u0002\u0010-\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010B\u001a\u00020\u0011H\u0007\u001a,\u0010A\u001a\u00020\n2\u0006\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010B\u001a\u00020\u0011\u001a\u0006\u0010F\u001a\u00020\n\u001a\r\u0010G\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010H\u001a*\u0010A\u001a\u00020\n*\u00020\u00042\b\b\u0002\u0010-\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010B\u001a\u00020\u0011H\u0007\u001a\u0014\u0010I\u001a\u00020\n2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a\u001c\u0010K\u001a\u00020\n2\u0006\u0010L\u001a\u00020\u00122\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a\u001a\u0010M\u001a\u00020\n\"\u0004\b\u0000\u0010%2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H%0N\u001a\u0014\u0010O\u001a\u00020\n2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a\u001e\u0010P\u001a\u00020\n2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\b\b\u0002\u0010Q\u001a\u00020\u0012\u001a&\u0010R\u001a\u00020\n2\b\b\u0002\u0010S\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u00122\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u001a\u0010\u0010U\u001a\u00020\n2\b\b\u0002\u0010S\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020\n*\u00020\r\u001a\n\u0010W\u001a\u00020\u000f*\u00020\u000f\u001a\n\u0010X\u001a\u00020\r*\u00020\r\"\u001c\u0010;\u001a\u0004\u0018\u00010<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@\"\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Y"}, d2 = {"appViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroidx/activity/ComponentActivity;", "toast", "", "Landroid/content/Context;", "message", "", "duration", "", "isRepeatClick", "", "", "setOnClicks", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "views", "", "Landroid/view/View;", "(Landroid/view/View$OnClickListener;[Landroid/view/View;)V", "setOnTouchClicks", "([Landroid/view/View;)V", "setOnClickFilterListener", "l", "setOnSeekBarStopChangeListener", "Landroid/widget/SeekBar;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "seekBar", "setOnItemClickFilterListener", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lkotlin/Function4;", "Landroid/view/ViewGroup;", "parent", "view", "t", PlayerFinal.PLAYER_POSITION, "show", "hide", "invisible", "post", "eventKey", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "subscribe", "Lcom/heaton/baselib/app/event/LiveDataBus$Observable;", "showDialog", "title", "positive", "Landroid/app/Activity;", "negative", "loadingDialog", "Landroidx/appcompat/app/AlertDialog;", "getLoadingDialog", "()Landroidx/appcompat/app/AlertDialog;", "setLoadingDialog", "(Landroidx/appcompat/app/AlertDialog;)V", "showLoadingDialog", "processShow", "loadingDialogStyel1", "Landroid/app/Dialog;", "context", "hideDialogStyle1", "isDialogShow", "()Ljava/lang/Boolean;", "async", "callback", "asyncDelay", "delay", "asyncCallback", "Lcom/wifiled/baselib/callback/CallBackUI;", "ui", "uiDelay", "delayMillis", "setTimeout", "id", "runnable", "removeTimeout", "deleteFile", "toDp", "copyDeletefile", "app_googleRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UtilsExtensionKt {
    private static AlertDialog loadingDialog;
    private static Dialog loadingDialogStyel1;

    public static final void showLoadingDialog(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        showLoadingDialog$default(activity, false, (String) null, false, 7, (Object) null);
    }

    public static final void showLoadingDialog(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        showLoadingDialog$default(activity, z, (String) null, false, 6, (Object) null);
    }

    public static final void showLoadingDialog(Activity activity, boolean z, String message) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        showLoadingDialog$default(activity, z, message, false, 4, (Object) null);
    }

    public static final void showLoadingDialog(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        showLoadingDialog$default(fragment, false, (String) null, false, 7, (Object) null);
    }

    public static final void showLoadingDialog(Fragment fragment, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        showLoadingDialog$default(fragment, z, (String) null, false, 6, (Object) null);
    }

    public static final void showLoadingDialog(Fragment fragment, boolean z, String message) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        showLoadingDialog$default(fragment, z, message, false, 4, (Object) null);
    }

    public static /* synthetic */ Lazy appViewModels$default(Fragment fragment, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), UtilsExtensionKt$appViewModels$1.INSTANCE, new UtilsExtensionKt$appViewModels$2(fragment), function0);
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> appViewModels(Fragment fragment, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), UtilsExtensionKt$appViewModels$1.INSTANCE, new UtilsExtensionKt$appViewModels$2(fragment), function0);
    }

    public static /* synthetic */ Lazy appViewModels$default(ComponentActivity componentActivity, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        if (function0 == null) {
            function0 = new UtilsExtensionKt$appViewModels$factoryPromise$1(componentActivity);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), UtilsExtensionKt$appViewModels$3.INSTANCE, function0, null, 8, null);
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> appViewModels(ComponentActivity componentActivity, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        if (function0 == null) {
            function0 = new UtilsExtensionKt$appViewModels$factoryPromise$1(componentActivity);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), UtilsExtensionKt$appViewModels$3.INSTANCE, function0, null, 8, null);
    }

    public static /* synthetic */ void toast$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        toast(context, str, i);
    }

    public static final void toast(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Toast.makeText(context, str, i).show();
    }

    public static /* synthetic */ boolean isRepeatClick$default(long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 500;
        }
        return isRepeatClick(j);
    }

    public static final boolean isRepeatClick(long j) {
        return ViewUtils.filter(j);
    }

    public static final void setOnClicks(View.OnClickListener listener, View... views) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(views, "views");
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    public static final void setOnTouchClicks(View... views) {
        Intrinsics.checkNotNullParameter(views, "views");
        for (View view : views) {
            CustomImageView.Companion companion = CustomImageView.INSTANCE;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.wifiled.ipixels.view.customview.CustomImageView");
            companion.attachViewOnTouchListener((CustomImageView) view);
        }
    }

    public static final void setOnClickFilterListener(View view, final Function0<Unit> l) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(l, "l");
        view.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UtilsExtensionKt.setOnClickFilterListener$lambda$0(Function0.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickFilterListener$lambda$0(Function0 function0, View view) {
        if (isRepeatClick$default(0L, 1, null)) {
            return;
        }
        function0.invoke();
    }

    public static final void setOnSeekBarStopChangeListener(SeekBar seekBar, final Function1<? super SeekBar, Unit> l) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        Intrinsics.checkNotNullParameter(l, "l");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.UtilsExtensionKt$setOnSeekBarStopChangeListener$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                if (fromUser) {
                    l.invoke(seekBar2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
                Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                l.invoke(seekBar2);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                Intrinsics.checkNotNullParameter(seekBar2, "seekBar");
                l.invoke(seekBar2);
            }
        });
    }

    public static final <T> void setOnItemClickFilterListener(RecyclerAdapter<T> recyclerAdapter, final Function4<? super ViewGroup, ? super View, ? super T, ? super Integer, Unit> l) {
        Intrinsics.checkNotNullParameter(recyclerAdapter, "<this>");
        Intrinsics.checkNotNullParameter(l, "l");
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda5
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                UtilsExtensionKt.setOnItemClickFilterListener$lambda$1(Function4.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnItemClickFilterListener$lambda$1(Function4 function4, ViewGroup viewGroup, View view, Object obj, int i) {
        if (isRepeatClick$default(0L, 1, null)) {
            return;
        }
        Intrinsics.checkNotNull(viewGroup);
        Intrinsics.checkNotNull(view);
        function4.invoke(viewGroup, view, obj, Integer.valueOf(i));
    }

    public static final void show(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void hide(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void invisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public static final <T> void post(String eventKey, T t) {
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        LiveDataBus.get().with(eventKey).postValue(t);
    }

    public static final /* synthetic */ <T> LiveDataBus.Observable<T> subscribe(String eventKey) {
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        LiveDataBus liveDataBus = LiveDataBus.get();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        LiveDataBus.Observable<T> with = liveDataBus.with(eventKey, Object.class);
        Intrinsics.checkNotNullExpressionValue(with, "with(...)");
        return with;
    }

    public static /* synthetic */ void showDialog$default(Fragment fragment, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        showDialog(fragment, str, str2, function0);
    }

    public static final void showDialog(Fragment fragment, String title, String message, final Function0<Unit> positive) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(positive, "positive");
        DialogUtils.showCommonDialog(fragment.getActivity(), title, message, new DialogUtils.DialogCall() { // from class: com.wifiled.ipixels.UtilsExtensionKt$showDialog$1
            @Override // com.wifiled.baselib.utils.DialogUtils.DialogCall
            public void onNegative() {
            }

            @Override // com.wifiled.baselib.utils.DialogUtils.DialogCall
            public void onPositive(DialogInterface dialog) {
                positive.invoke();
            }
        });
    }

    public static /* synthetic */ void showDialog$default(Activity activity, String str, String str2, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        showDialog(activity, str, str2, function0, function02);
    }

    public static final void showDialog(Activity activity, String title, String message, final Function0<Unit> positive, final Function0<Unit> negative) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(positive, "positive");
        Intrinsics.checkNotNullParameter(negative, "negative");
        DialogUtils.showCommonDialog(activity, title, message, new DialogUtils.DialogCall() { // from class: com.wifiled.ipixels.UtilsExtensionKt$showDialog$2
            @Override // com.wifiled.baselib.utils.DialogUtils.DialogCall
            public void onPositive(DialogInterface dialog) {
                positive.invoke();
            }

            @Override // com.wifiled.baselib.utils.DialogUtils.DialogCall
            public void onNegative() {
                negative.invoke();
            }
        });
    }

    public static final AlertDialog getLoadingDialog() {
        return loadingDialog;
    }

    public static final void setLoadingDialog(AlertDialog alertDialog) {
        loadingDialog = alertDialog;
    }

    public static /* synthetic */ void showLoadingDialog$default(Activity activity, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = "加载中...";
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        showLoadingDialog(activity, z, str, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void showLoadingDialog(Activity activity, boolean z, String message, boolean z2) {
        Window window;
        Window window2;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (z) {
            try {
                if (loadingDialog == null) {
                    AlertDialog show = new AlertDialog.Builder(activity).setView(R.layout.dialog_loading).setCancelable(false).show();
                    loadingDialog = show;
                    if (show != null && (window2 = show.getWindow()) != null) {
                        window2.clearFlags(2);
                    }
                }
                AlertDialog alertDialog = loadingDialog;
                IOSLoadingView iOSLoadingView = alertDialog != null ? (IOSLoadingView) alertDialog.findViewById(R.id.progBar_load) : null;
                if (z2) {
                    if (iOSLoadingView != null) {
                        show(iOSLoadingView);
                    }
                } else {
                    if (z2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (iOSLoadingView != null) {
                        hide(iOSLoadingView);
                    }
                }
                AlertDialog alertDialog2 = loadingDialog;
                TextView textView = alertDialog2 != null ? (TextView) alertDialog2.findViewById(R.id.tipTextView) : null;
                if (textView != null) {
                    textView.setText(message);
                }
                AlertDialog alertDialog3 = loadingDialog;
                if (alertDialog3 == null || (window = alertDialog3.getWindow()) == null) {
                    return;
                }
                window.setBackgroundDrawable(new ColorDrawable(0));
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Exception e) {
                e.printStackTrace();
                Unit unit2 = Unit.INSTANCE;
                return;
            }
        }
        try {
            AlertDialog alertDialog4 = loadingDialog;
            if (alertDialog4 != null) {
                alertDialog4.dismiss();
                Unit unit3 = Unit.INSTANCE;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Unit unit4 = Unit.INSTANCE;
        } finally {
            loadingDialog = null;
        }
    }

    public static /* synthetic */ void showLoadingDialog$default(Context context, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            str = "加载中...";
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        showLoadingDialog(context, z, str, z2);
    }

    public static final void showLoadingDialog(Context context, boolean z, String message, boolean z2) {
        Window window;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            if (loadingDialogStyel1 == null) {
                loadingDialogStyel1 = new Dialog(context, R.style.dialog);
                View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading, (ViewGroup) null);
                Dialog dialog = loadingDialogStyel1;
                if (dialog != null) {
                    dialog.setContentView(inflate);
                }
                Dialog dialog2 = loadingDialogStyel1;
                if (dialog2 != null) {
                    dialog2.setCancelable(false);
                }
                Dialog dialog3 = loadingDialogStyel1;
                Window window2 = dialog3 != null ? dialog3.getWindow() : null;
                if (window2 != null) {
                    window2.setGravity(17);
                }
                if (window2 != null) {
                    window2.setWindowAnimations(R.style.NoAnimationDialog);
                }
                Dialog dialog4 = loadingDialogStyel1;
                if (dialog4 != null) {
                    dialog4.show();
                }
            }
            Dialog dialog5 = loadingDialogStyel1;
            IOSLoadingView iOSLoadingView = dialog5 != null ? (IOSLoadingView) dialog5.findViewById(R.id.progBar_load) : null;
            if (z2) {
                if (iOSLoadingView != null) {
                    show(iOSLoadingView);
                }
            } else {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                if (iOSLoadingView != null) {
                    hide(iOSLoadingView);
                }
            }
            Dialog dialog6 = loadingDialogStyel1;
            TextView textView = dialog6 != null ? (TextView) dialog6.findViewById(R.id.tipTextView) : null;
            if (textView != null) {
                textView.setText(message);
            }
            Dialog dialog7 = loadingDialogStyel1;
            if (dialog7 == null || (window = dialog7.getWindow()) == null) {
                return;
            }
            window.setBackgroundDrawable(new ColorDrawable(0));
            return;
        }
        Dialog dialog8 = loadingDialogStyel1;
        if (dialog8 != null) {
            dialog8.dismiss();
        }
        loadingDialogStyel1 = null;
    }

    public static final void hideDialogStyle1() {
        Dialog dialog = loadingDialogStyel1;
        if (dialog != null) {
            if (dialog != null) {
                dialog.dismiss();
            }
            loadingDialogStyel1 = null;
        }
    }

    public static final Boolean isDialogShow() {
        AlertDialog alertDialog = loadingDialog;
        if (alertDialog == null) {
            return false;
        }
        if (alertDialog != null) {
            return Boolean.valueOf(alertDialog.isShowing());
        }
        return null;
    }

    public static /* synthetic */ void showLoadingDialog$default(Fragment fragment, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = "加载中...";
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        showLoadingDialog(fragment, z, str, z2);
    }

    public static final void showLoadingDialog(Fragment fragment, boolean z, String message, boolean z2) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            showLoadingDialog((Activity) activity, z, message, z2);
        }
    }

    public static final void async(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ThreadUtils.async(new CallBack() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda2
            @Override // com.wifiled.baselib.callback.CallBack
            public final void execute() {
                Function0.this.invoke();
            }
        });
    }

    public static final void asyncDelay(long j, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ThreadUtils.asyncDelay(j, new CallBack() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda3
            @Override // com.wifiled.baselib.callback.CallBack
            public final void execute() {
                Function0.this.invoke();
            }
        });
    }

    public static final <T> void asyncCallback(CallBackUI<T> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ThreadUtils.asyncCallback(callback);
    }

    public static final void ui(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ThreadUtils.ui(new Runnable() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    public static /* synthetic */ void uiDelay$default(Function0 function0, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 500;
        }
        uiDelay(function0, j);
    }

    public static final void uiDelay(final Function0<Unit> callback, long j) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ThreadUtils.uiDelay(new Runnable() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        }, j);
    }

    public static /* synthetic */ void setTimeout$default(int i, long j, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        setTimeout(i, j, function0);
    }

    public static final void setTimeout(final int i, final long j, final Function0<Unit> runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        ThreadUtils.ui(new Runnable() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                UtilsExtensionKt.setTimeout$lambda$7(i, j, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setTimeout$lambda$7(int i, long j, final Function0 function0) {
        HandlerUtils.setTimeout(i, j, new Runnable() { // from class: com.wifiled.ipixels.UtilsExtensionKt$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    public static /* synthetic */ void removeTimeout$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        removeTimeout(i);
    }

    public static final void removeTimeout(int i) {
        HandlerUtils.removeTimeout(i);
    }

    public static final void deleteFile(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        FileUtils.deleteFile(str);
    }

    public static final int toDp(int i) {
        return (int) ((i * App.INSTANCE.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final String copyDeletefile(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        File externalFilePath = FileUtils.getExternalFilePath(App.INSTANCE.getContext(), "Gif_bak");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null));
        File file = new File(externalFilePath, StringsKt.split$default((CharSequence) arrayList.get(arrayList.size() - 1), new String[]{"."}, false, 0, 6, (Object) null).get(0) + "_del.gif");
        FilesKt.copyTo$default(new File(str), file, false, 0, 6, null);
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }
}
