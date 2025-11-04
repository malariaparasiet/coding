package com.wifiled.baselib.uicode.viewmodel;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.JsonSyntaxException;
import com.wifiled.baselib.uicode.inner.IBaseViewModel;
import com.wifiled.baselib.uicode.inner.INetView;
import com.wifiled.baselib.uicode.net.CommonResponse;
import com.wifiled.baselib.uicode.net.HttpNetCode;
import com.wifiled.baselib.uicode.utils.UICoreConfig;
import java.io.EOFException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: BaseViewModel.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000200H\u0016J\b\u00104\u001a\u000200H\u0016J\b\u00105\u001a\u000200H\u0016J\b\u00106\u001a\u000200H\u0016J\b\u00107\u001a\u000200H\u0016J\b\u00108\u001a\u000200H\u0016J\b\u00109\u001a\u000200H\u0016J\u0012\u0010:\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010<\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010:\u001a\u0002002\b\b\u0001\u0010=\u001a\u00020\u001cH\u0016J\u0010\u0010<\u001a\u0002002\u0006\u0010=\u001a\u00020\u001cH\u0016J\u0012\u0010>\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010?\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010>\u001a\u0002002\u0006\u0010=\u001a\u00020\u001cH\u0016J\u0010\u0010?\u001a\u0002002\u0006\u0010=\u001a\u00020\u001cH\u0016J\b\u0010@\u001a\u000200H\u0016JW\u0010A\u001a\u00020B2\u001c\u0010C\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002000E\u0012\u0006\u0012\u0004\u0018\u00010F0D2*\u0010G\u001a&\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002000E\u0012\u0006\u0012\u0004\u0018\u00010F0HH\u0016¢\u0006\u0002\u0010IJ¦\u0001\u0010J\u001a\u00020B\"\u0004\b\u0000\u0010K2\"\u0010C\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HK0L0E\u0012\u0006\u0012\u0004\u0018\u00010F0D2:\u0010M\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(;\u0012\u0015\u0012\u0013\u0018\u0001HK¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002000N21\u0010G\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0015\u0012\u0013\u0018\u0001HK¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002000HH\u0016¢\u0006\u0002\u0010RJW\u0010S\u001a\u00020B2\u001c\u0010C\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002000E\u0012\u0006\u0012\u0004\u0018\u00010F0D2*\u0010G\u001a&\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002000E\u0012\u0006\u0012\u0004\u0018\u00010F0HH\u0016¢\u0006\u0002\u0010IJ¦\u0001\u0010T\u001a\u00020B\"\u0004\b\u0000\u0010K2\"\u0010C\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HK0L0E\u0012\u0006\u0012\u0004\u0018\u00010F0D2:\u0010M\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(;\u0012\u0015\u0012\u0013\u0018\u0001HK¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002000N21\u0010G\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0015\u0012\u0013\u0018\u0001HK¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002000HH\u0016¢\u0006\u0002\u0010RJB\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020W2*\u0010G\u001a&\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002000E\u0012\u0006\u0012\u0004\u0018\u00010F0HH\u0082@¢\u0006\u0002\u0010XJI\u0010Y\u001a\u000200\"\u0004\b\u0000\u0010K2\u0006\u0010V\u001a\u00020W21\u0010G\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0015\u0012\u0013\u0018\u0001HK¢\u0006\f\bO\u0012\b\bP\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002000HH\u0002R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\nR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\nR\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\nR\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\nR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\nR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\nR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\nR\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010,\"\u0004\b-\u0010.¨\u0006Z"}, d2 = {"Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/wifiled/baselib/uicode/inner/IBaseViewModel;", "Lcom/wifiled/baselib/uicode/inner/INetView;", "<init>", "()V", "showProgressDialogEvent", "Landroidx/lifecycle/MutableLiveData;", "", "getShowProgressDialogEvent", "()Landroidx/lifecycle/MutableLiveData;", "hideProgressDialogEvent", "getHideProgressDialogEvent", "showEmptyLayoutEvent", "getShowEmptyLayoutEvent", "showLoadingLayoutEvent", "getShowLoadingLayoutEvent", "showLoadErrorLayoutEvent", "getShowLoadErrorLayoutEvent", "showNetDisconnectLayoutEvent", "getShowNetDisconnectLayoutEvent", "hideStatusLayoutEvent", "getHideStatusLayoutEvent", "showToastStrEvent", "getShowToastStrEvent", "showLongToastStrEvent", "getShowLongToastStrEvent", "showToastResEvent", "", "getShowToastResEvent", "showLongToastResEvent", "getShowLongToastResEvent", "showCenterToastStrEvent", "getShowCenterToastStrEvent", "showCenterLongToastStrEvent", "getShowCenterLongToastStrEvent", "showCenterToastResEvent", "getShowCenterToastResEvent", "showCenterLongToastResEvent", "getShowCenterLongToastResEvent", "finishAcEvent", "getFinishAcEvent", "isViewDestroyed", "", "()Z", "setViewDestroyed", "(Z)V", "onDestroy", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "showProgressDialog", "hideProgressDialog", "showEmptyLayout", "showLoadingLayout", "showLoadErrorLayout", "showNetDisconnectLayout", "hideStatusLayout", "showToast", NotificationCompat.CATEGORY_MESSAGE, "showLongToast", "resId", "showCenterToast", "showCenterLongToast", "finishAc", "launch", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "failed", "Lkotlin/Function3;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "netLaunch", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/wifiled/baselib/uicode/net/CommonResponse;", "success", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "d", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "ioLaunch", "ioNetLaunch", "onFailSuspend", "t", "", "(Ljava/lang/Throwable;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFailException", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseViewModel extends ViewModel implements IBaseViewModel, INetView {
    private boolean isViewDestroyed;
    private final MutableLiveData<String> showProgressDialogEvent = new MutableLiveData<>();
    private final MutableLiveData<String> hideProgressDialogEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showEmptyLayoutEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showLoadingLayoutEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showLoadErrorLayoutEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showNetDisconnectLayoutEvent = new MutableLiveData<>();
    private final MutableLiveData<String> hideStatusLayoutEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showToastStrEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showLongToastStrEvent = new MutableLiveData<>();
    private final MutableLiveData<Integer> showToastResEvent = new MutableLiveData<>();
    private final MutableLiveData<Integer> showLongToastResEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showCenterToastStrEvent = new MutableLiveData<>();
    private final MutableLiveData<String> showCenterLongToastStrEvent = new MutableLiveData<>();
    private final MutableLiveData<Integer> showCenterToastResEvent = new MutableLiveData<>();
    private final MutableLiveData<Integer> showCenterLongToastResEvent = new MutableLiveData<>();
    private final MutableLiveData<String> finishAcEvent = new MutableLiveData<>();

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onCreate(LifecycleOwner lifecycleOwner) {
        super.onCreate(lifecycleOwner);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner lifecycleOwner) {
        super.onPause(lifecycleOwner);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        super.onResume(lifecycleOwner);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
        super.onStart(lifecycleOwner);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
        super.onStop(lifecycleOwner);
    }

    public final MutableLiveData<String> getShowProgressDialogEvent() {
        return this.showProgressDialogEvent;
    }

    public final MutableLiveData<String> getHideProgressDialogEvent() {
        return this.hideProgressDialogEvent;
    }

    public final MutableLiveData<String> getShowEmptyLayoutEvent() {
        return this.showEmptyLayoutEvent;
    }

    public final MutableLiveData<String> getShowLoadingLayoutEvent() {
        return this.showLoadingLayoutEvent;
    }

    public final MutableLiveData<String> getShowLoadErrorLayoutEvent() {
        return this.showLoadErrorLayoutEvent;
    }

    public final MutableLiveData<String> getShowNetDisconnectLayoutEvent() {
        return this.showNetDisconnectLayoutEvent;
    }

    public final MutableLiveData<String> getHideStatusLayoutEvent() {
        return this.hideStatusLayoutEvent;
    }

    public final MutableLiveData<String> getShowToastStrEvent() {
        return this.showToastStrEvent;
    }

    public final MutableLiveData<String> getShowLongToastStrEvent() {
        return this.showLongToastStrEvent;
    }

    public final MutableLiveData<Integer> getShowToastResEvent() {
        return this.showToastResEvent;
    }

    public final MutableLiveData<Integer> getShowLongToastResEvent() {
        return this.showLongToastResEvent;
    }

    public final MutableLiveData<String> getShowCenterToastStrEvent() {
        return this.showCenterToastStrEvent;
    }

    public final MutableLiveData<String> getShowCenterLongToastStrEvent() {
        return this.showCenterLongToastStrEvent;
    }

    public final MutableLiveData<Integer> getShowCenterToastResEvent() {
        return this.showCenterToastResEvent;
    }

    public final MutableLiveData<Integer> getShowCenterLongToastResEvent() {
        return this.showCenterLongToastResEvent;
    }

    public final MutableLiveData<String> getFinishAcEvent() {
        return this.finishAcEvent;
    }

    /* renamed from: isViewDestroyed, reason: from getter */
    public final boolean getIsViewDestroyed() {
        return this.isViewDestroyed;
    }

    public final void setViewDestroyed(boolean z) {
        this.isViewDestroyed = z;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        this.isViewDestroyed = true;
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showProgressDialog() {
        this.showProgressDialogEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void hideProgressDialog() {
        this.hideProgressDialogEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showEmptyLayout() {
        this.showEmptyLayoutEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLoadingLayout() {
        this.showLoadingLayoutEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLoadErrorLayout() {
        this.showLoadErrorLayoutEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showNetDisconnectLayout() {
        this.showNetDisconnectLayoutEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void hideStatusLayout() {
        this.hideStatusLayoutEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showToast(String msg) {
        this.showToastStrEvent.setValue(msg);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLongToast(String msg) {
        this.showLongToastStrEvent.setValue(msg);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showToast(int resId) {
        this.showToastResEvent.setValue(Integer.valueOf(resId));
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showLongToast(int resId) {
        this.showLongToastResEvent.setValue(Integer.valueOf(resId));
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterToast(String msg) {
        this.showCenterToastStrEvent.setValue(msg);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterLongToast(String msg) {
        this.showCenterLongToastStrEvent.setValue(msg);
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterToast(int resId) {
        this.showCenterToastResEvent.setValue(Integer.valueOf(resId));
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void showCenterLongToast(int resId) {
        this.showCenterLongToastResEvent.setValue(Integer.valueOf(resId));
    }

    @Override // com.wifiled.baselib.uicode.inner.ICoreView
    public void finishAc() {
        this.finishAcEvent.setValue("");
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public Job launch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new BaseViewModel$launch$job$1(block, this, failed, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public <T> Job netLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new BaseViewModel$netLaunch$job$1(block, success, failed, this, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public Job ioLaunch(Function1<? super Continuation<? super Unit>, ? extends Object> block, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new BaseViewModel$ioLaunch$job$1(block, this, failed, null), 2, null);
        return launch$default;
    }

    @Override // com.wifiled.baselib.uicode.inner.INetView
    public <T> Job ioNetLaunch(Function1<? super Continuation<? super CommonResponse<T>>, ? extends Object> block, Function2<? super String, ? super T, Unit> success, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failed, "failed");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new BaseViewModel$ioNetLaunch$job$1(block, success, failed, this, null), 2, null);
        return launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onFailSuspend(Throwable th, Function3<? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        String message = th.getMessage();
        if (!(message != null ? StringsKt.contains$default((CharSequence) message, (CharSequence) "HTTP 401", false, 2, (Object) null) : false)) {
            LogUtils.e(th);
            if (th instanceof EOFException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常：" + th.getMessage(), continuation);
                    return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
                }
                Object invoke2 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常", continuation);
                return invoke2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke2 : Unit.INSTANCE;
            }
            if (th instanceof SocketTimeoutException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke3 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_TIMEOUT), "网络超时：" + th.getMessage(), continuation);
                    return invoke3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke3 : Unit.INSTANCE;
                }
                Object invoke4 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_TIMEOUT), "网络超时", continuation);
                return invoke4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke4 : Unit.INSTANCE;
            }
            if (th instanceof SSLException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke5 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过：" + th.getMessage(), continuation);
                    return invoke5 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke5 : Unit.INSTANCE;
                }
                Object invoke6 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过", continuation);
                return invoke6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke6 : Unit.INSTANCE;
            }
            if (th instanceof ParseException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke7 = function3.invoke(Boxing.boxInt(HttpNetCode.PARSE_ERROR), "Parse解析异常：" + th.getMessage(), continuation);
                    return invoke7 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke7 : Unit.INSTANCE;
                }
                Object invoke8 = function3.invoke(Boxing.boxInt(HttpNetCode.PARSE_ERROR), "Parse解析异常", continuation);
                return invoke8 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke8 : Unit.INSTANCE;
            }
            if (th instanceof JsonSyntaxException) {
                if (UICoreConfig.INSTANCE.getMode()) {
                    Object invoke9 = function3.invoke(Boxing.boxInt(HttpNetCode.JSON_ERROR), "Json解析异常：" + th.getMessage(), continuation);
                    return invoke9 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke9 : Unit.INSTANCE;
                }
                Object invoke10 = function3.invoke(Boxing.boxInt(HttpNetCode.JSON_ERROR), "Json解析异常", continuation);
                return invoke10 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke10 : Unit.INSTANCE;
            }
            if (UICoreConfig.INSTANCE.getMode()) {
                Object invoke11 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙：" + th.getMessage(), continuation);
                return invoke11 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke11 : Unit.INSTANCE;
            }
            Object invoke12 = function3.invoke(Boxing.boxInt(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙", continuation);
            return invoke12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke12 : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> void onFailException(Throwable t, Function3<? super Integer, ? super String, ? super T, Unit> failed) {
        String message = t.getMessage();
        if (message != null ? StringsKt.contains$default((CharSequence) message, (CharSequence) "HTTP 401", false, 2, (Object) null) : false) {
            return;
        }
        LogUtils.e(t);
        if (t instanceof EOFException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络读取异常", null);
                return;
            }
        }
        if (t instanceof SocketTimeoutException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_TIMEOUT), "网络超时：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_TIMEOUT), "网络超时", null);
                return;
            }
        }
        if (t instanceof SSLException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "SSL校验未通过", null);
                return;
            }
        }
        if (t instanceof ParseException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.PARSE_ERROR), "Parse解析异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.PARSE_ERROR), "Parse解析异常", null);
                return;
            }
        }
        if (t instanceof JsonSyntaxException) {
            if (UICoreConfig.INSTANCE.getMode()) {
                failed.invoke(Integer.valueOf(HttpNetCode.JSON_ERROR), "Json解析异常：" + t.getMessage(), null);
                return;
            } else {
                failed.invoke(Integer.valueOf(HttpNetCode.JSON_ERROR), "Json解析异常", null);
                return;
            }
        }
        if (UICoreConfig.INSTANCE.getMode()) {
            failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙：" + t.getMessage(), null);
        } else {
            failed.invoke(Integer.valueOf(HttpNetCode.NET_CONNECT_ERROR), "网络繁忙", null);
        }
    }
}
