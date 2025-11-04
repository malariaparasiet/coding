package com.wifiled.ipixels.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.autofill.HintConstants;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.callback.CallBack;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.Ble2;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.ui.ChooseActivity;
import com.wifiled.ipixels.ui.adapter.ConnectAdapter;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.ipixels.view.customview.IOSLoadingView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: ConnectDialog.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002?B\u0018\u0000 W2\u00020\u00012\u00020\u0002:\u0001WB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020*H\u0016J\b\u00101\u001a\u00020*H\u0016J\b\u00102\u001a\u00020*H\u0002J\u0006\u00103\u001a\u00020*J \u00104\u001a\u00020*2\u0006\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020#2\u0006\u00107\u001a\u00020\u0006H\u0002J\u0010\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020#H\u0002J\b\u0010:\u001a\u00020*H\u0002J\b\u0010;\u001a\u00020*H\u0002J\u0012\u0010<\u001a\u00020*2\b\u0010=\u001a\u0004\u0018\u00010\u0013H\u0002J$\u0010H\u001a\u00020*2\b\b\u0002\u0010I\u001a\u00020\u001d2\b\b\u0002\u0010J\u001a\u00020#2\b\b\u0002\u0010K\u001a\u00020\u001dJ\u000e\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020NJ\b\u0010O\u001a\u00020*H\u0002J\b\u0010P\u001a\u00020*H\u0002J$\u0010R\u001a\u00020*2\b\b\u0002\u0010I\u001a\u00020\u001d2\b\b\u0002\u0010J\u001a\u00020#2\b\b\u0002\u0010K\u001a\u00020\u001dR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@R\u0010\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0004\n\u0002\u0010CR\u000e\u0010D\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Q\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010S\u001a\u00020T8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010V¨\u0006X"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectDialog;", "Landroid/app/Dialog;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/app/Activity;", "themeResId", "", "<init>", "(Landroid/app/Activity;I)V", "getContext", "()Landroid/app/Activity;", "bleManager", "Lcom/wifiled/ipixels/core/BleManager;", "bleManager2", "Lcom/wifiled/ipixels/core/BleManager2;", "adapter", "Lcom/wifiled/ipixels/ui/adapter/ConnectAdapter;", "listDatas", "", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "anim", "Landroid/view/animation/Animation;", "handler", "Landroid/os/Handler;", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "mPrevState", "mNewState", "mIsManualOperation", "", "mPrevState2", "mNewState2", "mIsManualOperation2", "mBlueFilter", "", "", "mConnectPostion", "iv_refresh", "Landroid/widget/ImageButton;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "setDialogListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onDialogClickCallBackListener;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onAttachedToWindow", "onDetachedFromWindow", "bindData", "bindListener", "showRenameDialog", HintConstants.AUTOFILL_HINT_NAME, "key", PlayerFinal.PLAYER_POSITION, "getTextTotalSize", TextBundle.TEXT_ENTRY, "checkBleStates", "initData", "addDevice", "device", "bleCallback", "com/wifiled/ipixels/ui/dialog/ConnectDialog$bleCallback$1", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$bleCallback$1;", "bleCallback2", "com/wifiled/ipixels/ui/dialog/ConnectDialog$bleCallback2$1", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$bleCallback2$1;", "hr", "sendTimeOut", "Ljava/lang/Runnable;", "sendTimeOutDis", "showLoadingDialogPlus", "show", "message", "processShow", "hideLoadingDialog", "time", "", "startAnim", "stopAnim", "loadingDialog", "showLoadingDialog", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectDialog extends Dialog implements LifecycleOwner {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static WeakReference<Activity> activity;
    private static ConnectDialog instance;
    private static Companion.onDialogClickCallBackListener mDialogListener;
    private static Companion.onCallBackLedHasWifiListener mLedWifiListener;
    private ConnectAdapter adapter;
    private Animation anim;
    private final ConnectDialog$bleCallback$1 bleCallback;
    private final ConnectDialog$bleCallback2$1 bleCallback2;
    private final BleManager bleManager;
    private final BleManager2 bleManager2;
    private final Activity context;
    private final Handler handler;
    private final Handler hr;
    private ImageButton iv_refresh;
    private List<BleDevice> listDatas;
    private Dialog loadingDialog;
    private List<String> mBlueFilter;
    private int mConnectPostion;
    private boolean mIsManualOperation;
    private boolean mIsManualOperation2;
    private final LifecycleRegistry mLifecycleRegistry;
    private int mNewState;
    private int mNewState2;
    private int mPrevState;
    private int mPrevState2;
    private RecyclerView recyclerView;
    private final Runnable sendTimeOut;
    private final Runnable sendTimeOutDis;

    public /* synthetic */ ConnectDialog(Activity activity2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity2, i);
    }

    @Override // android.app.Dialog
    public final Activity getContext() {
        return this.context;
    }

    private ConnectDialog(Activity activity2, int i) {
        super(activity2, i);
        this.context = activity2;
        this.bleManager = BleManager.INSTANCE.get();
        this.bleManager2 = BleManager2.INSTANCE.get();
        this.listDatas = new ArrayList();
        this.handler = new Handler();
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mPrevState = -1;
        this.mNewState = -1;
        this.mPrevState2 = -1;
        this.mNewState2 = -1;
        this.bleCallback = new ConnectDialog$bleCallback$1(this);
        this.bleCallback2 = new ConnectDialog$bleCallback2$1(this);
        this.hr = new Handler(Looper.getMainLooper());
        this.sendTimeOut = new Runnable() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                ConnectDialog.sendTimeOut$lambda$11(ConnectDialog.this);
            }
        };
        this.sendTimeOutDis = new Runnable() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ConnectDialog.sendTimeOutDis$lambda$13(ConnectDialog.this);
            }
        };
    }

    /* compiled from: ConnectDialog.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion;", "", "<init>", "()V", "mLedWifiListener", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onCallBackLedHasWifiListener;", "mDialogListener", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onDialogClickCallBackListener;", "setLedWifiListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/wifiled/ipixels/ui/ChooseActivity;", "instance", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog;", "getInstance", "()Lcom/wifiled/ipixels/ui/dialog/ConnectDialog;", "setInstance", "(Lcom/wifiled/ipixels/ui/dialog/ConnectDialog;)V", "activity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "getActivity", "()Ljava/lang/ref/WeakReference;", "setActivity", "(Ljava/lang/ref/WeakReference;)V", "context", "themeResId", "", "onCallBackLedHasWifiListener", "onDialogClickCallBackListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {

        /* compiled from: ConnectDialog.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onCallBackLedHasWifiListener;", "", "onLedWifiListener", "", "isHas", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface onCallBackLedHasWifiListener {
            void onLedWifiListener(boolean isHas);
        }

        /* compiled from: ConnectDialog.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onDialogClickCallBackListener;", "", "onAdapterItemClick", "", "device", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface onDialogClickCallBackListener {
            void onAdapterItemClick(BleDevice device);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setLedWifiListener(ChooseActivity listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ConnectDialog.mLedWifiListener = listener;
        }

        public final ConnectDialog getInstance() {
            return ConnectDialog.instance;
        }

        public final void setInstance(ConnectDialog connectDialog) {
            ConnectDialog.instance = connectDialog;
        }

        public final WeakReference<Activity> getActivity() {
            WeakReference<Activity> weakReference = ConnectDialog.activity;
            if (weakReference != null) {
                return weakReference;
            }
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            return null;
        }

        public final void setActivity(WeakReference<Activity> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            ConnectDialog.activity = weakReference;
        }

        public final ConnectDialog getInstance(Activity context, int themeResId) {
            ConnectDialog companion;
            Intrinsics.checkNotNullParameter(context, "context");
            synchronized (Reflection.getOrCreateKotlinClass(ConnectDialog.class)) {
                if (ConnectDialog.INSTANCE.getInstance() == null) {
                    ConnectDialog.INSTANCE.setActivity(new WeakReference<>(context));
                    ConnectDialog.INSTANCE.setInstance(new ConnectDialog(context, themeResId, null));
                }
                companion = ConnectDialog.INSTANCE.getInstance();
                Intrinsics.checkNotNull(companion);
            }
            return companion;
        }
    }

    public final void setDialogListener(Companion.onDialogClickCallBackListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        mDialogListener = listener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View findViewById = findViewById(R.id.iv_refresh);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_refresh = (ImageButton) findViewById;
        View findViewById2 = findViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.recyclerView = (RecyclerView) findViewById2;
        initData();
        bindData();
        bindListener();
        startAnim();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Ble ble = Ble.getInstance();
        Ble2 ble2 = Ble2.getInstance();
        if (this.listDatas.size() > 0) {
            this.listDatas.clear();
        }
        if (ble.getConnectedDevices() != null) {
            List<BleDevice> list = this.listDatas;
            List connectedDevices = ble.getConnectedDevices();
            Intrinsics.checkNotNullExpressionValue(connectedDevices, "getConnectedDevices(...)");
            list.addAll(connectedDevices);
            List<BleDevice> list2 = this.listDatas;
            List connectedDevices2 = ble2.getConnectedDevices();
            Intrinsics.checkNotNullExpressionValue(connectedDevices2, "getConnectedDevices(...)");
            list2.addAll(connectedDevices2);
            ConnectAdapter connectAdapter = this.adapter;
            if (connectAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                connectAdapter = null;
            }
            connectAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.listDatas.size() > 0) {
            this.listDatas.clear();
        }
        instance = null;
        this.bleManager.stopScan();
        mDialogListener = null;
        mLedWifiListener = null;
        this.bleManager.removeBleCallback(this.bleCallback);
        this.bleManager2.removeBleCallback(this.bleCallback2);
    }

    private final void bindData() {
        ConnectDialog connectDialog = this;
        this.bleManager.setBleCallback(connectDialog, this.bleCallback);
        this.bleManager2.setBleCallback(connectDialog, this.bleCallback2);
        checkBleStates();
    }

    public final void bindListener() {
        ImageButton imageButton = this.iv_refresh;
        ConnectAdapter connectAdapter = null;
        if (imageButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_refresh");
            imageButton = null;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConnectDialog.bindListener$lambda$0(ConnectDialog.this, view);
            }
        });
        ConnectAdapter connectAdapter2 = this.adapter;
        if (connectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            connectAdapter2 = null;
        }
        connectAdapter2.bindChildClickViewIds(R.id.ic_edit, R.id.tv_state, R.id.tv_name);
        ConnectAdapter connectAdapter3 = this.adapter;
        if (connectAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            connectAdapter3 = null;
        }
        connectAdapter3.setOnItemChildClickListener(new RecyclerAdapter.OnItemChildClickListener() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda7
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemChildClickListener
            public final void onItemChildClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ConnectDialog.bindListener$lambda$2(ConnectDialog.this, viewGroup, view, (BleDevice) obj, i);
            }
        });
        ConnectAdapter connectAdapter4 = this.adapter;
        if (connectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            connectAdapter = connectAdapter4;
        }
        connectAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda8
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(ViewGroup viewGroup, View view, Object obj, int i) {
                boolean bindListener$lambda$3;
                bindListener$lambda$3 = ConnectDialog.bindListener$lambda$3(ConnectDialog.this, viewGroup, view, (BleDevice) obj, i);
                return bindListener$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(ConnectDialog connectDialog, View view) {
        Ble ble = Ble.getInstance();
        if (!ble.isBleEnable()) {
            ble.turnOnBlueTooth(connectDialog.getOwnerActivity());
            return;
        }
        if (ble.isScanning()) {
            return;
        }
        if (connectDialog.listDatas.size() > 0) {
            connectDialog.listDatas.clear();
        }
        List<BleDevice> list = connectDialog.listDatas;
        List connectedDevices = ble.getConnectedDevices();
        Intrinsics.checkNotNullExpressionValue(connectedDevices, "getConnectedDevices(...)");
        list.addAll(connectedDevices);
        ConnectAdapter connectAdapter = connectDialog.adapter;
        if (connectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            connectAdapter = null;
        }
        connectAdapter.notifyDataSetChanged();
        connectDialog.bleManager.scan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(final ConnectDialog connectDialog, ViewGroup viewGroup, View view, final BleDevice bleDevice, final int i) {
        if (ClickFilter.filter(600L)) {
            ToastUtil.show(connectDialog.context.getString(R.string.show_no_click));
            return;
        }
        if (view.getId() == R.id.tv_state) {
            if (bleDevice != null && bleDevice.isDisconnected()) {
                if (AppConfig.INSTANCE.getConnectType() == 0 && !Intrinsics.areEqual(AppConfig.INSTANCE.getCurConnectWifi(), bleDevice.getBleName())) {
                    String string = connectDialog.context.getString(R.string.msg_confirm_same_dev);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    showLoadingDialogPlus$default(connectDialog, false, string, false, 1, null);
                    connectDialog.hideLoadingDialog(DefaultReConnectHandler.DEFAULT_CONNECT_DELAY);
                    return;
                }
                if (connectDialog.bleManager.getConnectedDevice() != null) {
                    List<String> multiDeviceList = Constants.INSTANCE.getMultiDeviceList();
                    BleDevice connectedDevice = connectDialog.bleManager.getConnectedDevice();
                    Intrinsics.checkNotNull(connectedDevice);
                    if (!multiDeviceList.contains(connectedDevice.getPid())) {
                        connectDialog.bleManager.disconnect();
                    }
                    BleDevice connectedDevice2 = connectDialog.bleManager.getConnectedDevice();
                    Intrinsics.checkNotNull(connectedDevice2);
                    if (!Intrinsics.areEqual(connectedDevice2.getPid(), bleDevice.getPid())) {
                        connectDialog.bleManager.disconnect();
                    }
                }
                if (connectDialog.bleManager2.getConnectedDevice() != null) {
                    List<String> multiDeviceList2 = Constants.INSTANCE.getMultiDeviceList();
                    BleDevice connectedDevice3 = connectDialog.bleManager2.getConnectedDevice();
                    Intrinsics.checkNotNull(connectedDevice3);
                    if (!multiDeviceList2.contains(connectedDevice3.getPid())) {
                        connectDialog.bleManager2.disconnect();
                    }
                    BleDevice connectedDevice4 = connectDialog.bleManager2.getConnectedDevice();
                    Intrinsics.checkNotNull(connectedDevice4);
                    if (!Intrinsics.areEqual(connectedDevice4.getPid(), bleDevice.getPid())) {
                        connectDialog.bleManager2.disconnect();
                    }
                }
                if (connectDialog.bleManager2.getConnectedDevice() != null && connectDialog.bleManager.getConnectedDevice() != null) {
                    connectDialog.bleManager2.disconnect();
                    connectDialog.bleManager.disconnect();
                }
                connectDialog.handler.postDelayed(new Runnable() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        ConnectDialog.bindListener$lambda$2$lambda$1(BleDevice.this, connectDialog, i);
                    }
                }, 100L);
                return;
            }
            connectDialog.mConnectPostion = i;
            connectDialog.mIsManualOperation = true;
            BleDevice connectedDevice5 = connectDialog.bleManager.getConnectedDevice();
            if (Intrinsics.areEqual(connectedDevice5 != null ? connectedDevice5.getBleName() : null, bleDevice.getBleName())) {
                connectDialog.bleManager.disconnect();
            }
            BleDevice connectedDevice6 = connectDialog.bleManager2.getConnectedDevice();
            if (Intrinsics.areEqual(connectedDevice6 != null ? connectedDevice6.getBleName() : null, bleDevice.getBleName())) {
                connectDialog.bleManager2.disconnect();
            }
            SPUtils.remove(connectDialog.context, "connectionDevice");
            return;
        }
        if (view.getId() == R.id.tv_name || view.getId() == R.id.ic_edit) {
            if (SPUtils.get(connectDialog.context, bleDevice.getBleAddress() + "rename", bleDevice.getBleName()) != null) {
                Object obj = SPUtils.get(connectDialog.context, bleDevice.getBleAddress() + "rename", bleDevice.getBleName());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                String bleAddress = bleDevice.getBleAddress();
                Intrinsics.checkNotNullExpressionValue(bleAddress, "getBleAddress(...)");
                connectDialog.showRenameDialog((String) obj, bleAddress, i);
                return;
            }
            if (bleDevice.getBleName() != null) {
                String bleName = bleDevice.getBleName();
                Intrinsics.checkNotNullExpressionValue(bleName, "getBleName(...)");
                String bleAddress2 = bleDevice.getBleAddress();
                Intrinsics.checkNotNullExpressionValue(bleAddress2, "getBleAddress(...)");
                connectDialog.showRenameDialog(bleName, bleAddress2, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2$lambda$1(BleDevice bleDevice, ConnectDialog connectDialog, int i) {
        Companion.onDialogClickCallBackListener ondialogclickcallbacklistener = mDialogListener;
        if (ondialogclickcallbacklistener != null) {
            ondialogclickcallbacklistener.onAdapterItemClick(bleDevice);
        }
        if (connectDialog.bleManager.getConnectedDevice() == null) {
            connectDialog.bleManager.connect(bleDevice);
            SPUtils.put(connectDialog.context, "connectionDevice", bleDevice.getBleAddress());
        } else {
            connectDialog.bleManager2.connect(bleDevice);
        }
        connectDialog.mConnectPostion = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$3(ConnectDialog connectDialog, ViewGroup viewGroup, View view, BleDevice bleDevice, int i) {
        Object obj = SPUtils.get(connectDialog.context, bleDevice.getBleAddress() + "rename", bleDevice.getBleName());
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        String bleAddress = bleDevice.getBleAddress();
        Intrinsics.checkNotNullExpressionValue(bleAddress, "getBleAddress(...)");
        connectDialog.showRenameDialog((String) obj, bleAddress, i);
        return true;
    }

    private final void showRenameDialog(String name, final String key, final int position) {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this.context, R.layout.dialog_rename);
        final EditText editText = (EditText) showBottomDialog.findViewById(R.id.rename);
        ImageView imageView = (ImageView) showBottomDialog.findViewById(R.id.popups_no);
        ImageView imageView2 = (ImageView) showBottomDialog.findViewById(R.id.popups_ok);
        editText.setText(name);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$showRenameDialog$1
            private int selectionEnd;
            private int selectionStart;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                int textTotalSize;
                this.selectionStart = editText.getSelectionStart();
                this.selectionEnd = editText.getSelectionEnd();
                textTotalSize = this.getTextTotalSize(editText.getText().toString());
                if (textTotalSize > 19) {
                    if (s != null) {
                        s.delete(this.selectionStart - 1, this.selectionEnd);
                    }
                    editText.setText(s);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                showBottomDialog.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConnectDialog.showRenameDialog$lambda$5(showBottomDialog, editText, this, key, position, view);
            }
        });
        showBottomDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRenameDialog$lambda$5(Dialog dialog, EditText editText, ConnectDialog connectDialog, String str, int i, View view) {
        dialog.dismiss();
        if (StringsKt.trim((CharSequence) editText.getText().toString()).toString().length() > 0) {
            SPUtils.put(connectDialog.context, str + "rename", editText.getText().toString());
        } else {
            SPUtils.remove(connectDialog.context, str + "rename");
        }
        ConnectAdapter connectAdapter = connectDialog.adapter;
        if (connectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            connectAdapter = null;
        }
        connectAdapter.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTextTotalSize(String text) {
        String str = text;
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = CharacterUtilsKt.isChinese(str.charAt(i2)) ? i + 2 : i + 1;
        }
        return i;
    }

    private final void checkBleStates() {
        LogUtils.vTag("ruis", "checkBleStates");
        Ble ble = Ble.getInstance();
        if (!ble.isSupportBle(ble.getContext())) {
            dismiss();
        }
        if (!ble.isBleEnable()) {
            ble.turnOnBlueTooth(getOwnerActivity());
            return;
        }
        LogUtils.vTag("ruis", "bleManager.scan() " + ble.isScanning());
        if (ble.isScanning()) {
            this.bleManager.stopScan();
        }
        if (ble.getConnectedDevices() != null && ble.getConnectedDevices().size() > 0) {
            List<BleDevice> list = this.listDatas;
            List connectedDevices = ble.getConnectedDevices();
            Intrinsics.checkNotNullExpressionValue(connectedDevices, "getConnectedDevices(...)");
            list.addAll(connectedDevices);
            this.mNewState = 1;
            this.mPrevState = 1;
        }
        ConnectAdapter connectAdapter = this.adapter;
        if (connectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            connectAdapter = null;
        }
        connectAdapter.notifyItemChanged(this.mConnectPostion);
        ThreadUtils.asyncDelay(600L, new CallBack() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda9
            @Override // com.wifiled.baselib.callback.CallBack
            public final void execute() {
                ConnectDialog.checkBleStates$lambda$8$lambda$7(ConnectDialog.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkBleStates$lambda$8$lambda$7(ConnectDialog connectDialog) {
        connectDialog.bleManager.scan();
    }

    private final void initData() {
        this.adapter = new ConnectAdapter(this.context, this.listDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context, 1, false);
        RecyclerView recyclerView = this.recyclerView;
        ConnectAdapter connectAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        ConnectAdapter connectAdapter2 = this.adapter;
        if (connectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            connectAdapter = connectAdapter2;
        }
        recyclerView2.setAdapter(connectAdapter);
        String str = (String) SPUtils.get(this.context, "device_filter", "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        this.mBlueFilter = (List) new Gson().fromJson(str, new TypeToken<List<? extends String>>() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$initData$1
        }.getType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addDevice(BleDevice device) {
        BleDevice next;
        String bleAddress;
        String str;
        Iterator<BleDevice> it = this.listDatas.iterator();
        do {
            ConnectAdapter connectAdapter = null;
            if (!it.hasNext()) {
                if (device != null) {
                    List<String> list = this.mBlueFilter;
                    if (list != null) {
                        Intrinsics.checkNotNull(list);
                        if (!list.isEmpty()) {
                            String str2 = device.getCid() + device.getPid();
                            List<String> list2 = this.mBlueFilter;
                            if (list2 != null && list2.contains(str2)) {
                                return;
                            }
                        }
                    }
                    this.listDatas.add(device);
                    ConnectAdapter connectAdapter2 = this.adapter;
                    if (connectAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        connectAdapter = connectAdapter2;
                    }
                    connectAdapter.notifyItemInserted(this.listDatas.size() - 1);
                    return;
                }
                return;
            }
            next = it.next();
            bleAddress = device != null ? device.getBleAddress() : null;
            String bleAddress2 = next != null ? next.getBleAddress() : null;
            if (bleAddress2 == null || bleAddress2.length() == 0 || (str = bleAddress) == null || str.length() == 0) {
                return;
            }
        } while (!Intrinsics.areEqual(next.getBleAddress(), bleAddress));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendTimeOut$lambda$11(final ConnectDialog connectDialog) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTimeOut$lambda$11$lambda$10;
                sendTimeOut$lambda$11$lambda$10 = ConnectDialog.sendTimeOut$lambda$11$lambda$10(ConnectDialog.this);
                return sendTimeOut$lambda$11$lambda$10;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTimeOut$lambda$11$lambda$10(ConnectDialog connectDialog) {
        showLoadingDialog$default(connectDialog, false, null, false, 6, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendTimeOutDis$lambda$13(final ConnectDialog connectDialog) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.dialog.ConnectDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTimeOutDis$lambda$13$lambda$12;
                sendTimeOutDis$lambda$13$lambda$12 = ConnectDialog.sendTimeOutDis$lambda$13$lambda$12(ConnectDialog.this);
                return sendTimeOutDis$lambda$13$lambda$12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTimeOutDis$lambda$13$lambda$12(ConnectDialog connectDialog) {
        showLoadingDialog$default(connectDialog, false, null, false, 6, null);
        connectDialog.bleManager.disconnect();
        connectDialog.bleManager2.disconnect();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void showLoadingDialogPlus$default(ConnectDialog connectDialog, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = "加载中...";
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        connectDialog.showLoadingDialogPlus(z, str, z2);
    }

    public final void showLoadingDialogPlus(boolean show, String message, boolean processShow) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (ActivityUtils.isActivityAlive(this.context)) {
            this.hr.removeCallbacks(this.sendTimeOut);
            this.hr.removeCallbacks(this.sendTimeOutDis);
            showLoadingDialog(show, message, processShow);
        }
    }

    public final void hideLoadingDialog(long time) {
        if (ActivityUtils.isActivityAlive(this.context)) {
            this.hr.removeCallbacks(this.sendTimeOut);
            this.hr.removeCallbacks(this.sendTimeOutDis);
            this.hr.postDelayed(this.sendTimeOut, time);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnim() {
        Animation animation = null;
        if (this.anim == null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.anim = rotateAnimation;
            rotateAnimation.setDuration(1000L);
            Animation animation2 = this.anim;
            if (animation2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("anim");
                animation2 = null;
            }
            animation2.setRepeatCount(-1);
            Animation animation3 = this.anim;
            if (animation3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("anim");
                animation3 = null;
            }
            animation3.setInterpolator(new LinearInterpolator());
        }
        ImageButton imageButton = this.iv_refresh;
        if (imageButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_refresh");
            imageButton = null;
        }
        Animation animation4 = this.anim;
        if (animation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("anim");
        } else {
            animation = animation4;
        }
        imageButton.startAnimation(animation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopAnim() {
        Animation animation = this.anim;
        if (animation != null) {
            ImageButton imageButton = null;
            if (animation == null) {
                Intrinsics.throwUninitializedPropertyAccessException("anim");
                animation = null;
            }
            animation.cancel();
            ImageButton imageButton2 = this.iv_refresh;
            if (imageButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_refresh");
            } else {
                imageButton = imageButton2;
            }
            imageButton.clearAnimation();
        }
    }

    public static /* synthetic */ void showLoadingDialog$default(ConnectDialog connectDialog, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = "加载中...";
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        connectDialog.showLoadingDialog(z, str, z2);
    }

    public final void showLoadingDialog(boolean show, String message, boolean processShow) {
        Window window;
        Intrinsics.checkNotNullParameter(message, "message");
        if (show) {
            if (this.loadingDialog == null) {
                this.loadingDialog = new Dialog(this.context, R.style.dialog);
                View inflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_loading, (ViewGroup) null);
                Dialog dialog = this.loadingDialog;
                Intrinsics.checkNotNull(dialog);
                dialog.setContentView(inflate);
                Dialog dialog2 = this.loadingDialog;
                Intrinsics.checkNotNull(dialog2);
                dialog2.setCancelable(false);
                Dialog dialog3 = this.loadingDialog;
                Intrinsics.checkNotNull(dialog3);
                Window window2 = dialog3.getWindow();
                if (window2 != null) {
                    window2.setGravity(17);
                }
                if (window2 != null) {
                    window2.setWindowAnimations(R.style.NoAnimationDialog);
                }
                Dialog dialog4 = this.loadingDialog;
                Intrinsics.checkNotNull(dialog4);
                dialog4.show();
            }
            Dialog dialog5 = this.loadingDialog;
            IOSLoadingView iOSLoadingView = dialog5 != null ? (IOSLoadingView) dialog5.findViewById(R.id.progBar_load) : null;
            if (processShow) {
                if (iOSLoadingView != null) {
                    UtilsExtensionKt.show(iOSLoadingView);
                }
            } else {
                if (processShow) {
                    throw new NoWhenBranchMatchedException();
                }
                if (iOSLoadingView != null) {
                    UtilsExtensionKt.hide(iOSLoadingView);
                }
            }
            Dialog dialog6 = this.loadingDialog;
            TextView textView = dialog6 != null ? (TextView) dialog6.findViewById(R.id.tipTextView) : null;
            if (textView != null) {
                textView.setText(message);
            }
            Dialog dialog7 = this.loadingDialog;
            if (dialog7 == null || (window = dialog7.getWindow()) == null) {
                return;
            }
            window.setBackgroundDrawable(new ColorDrawable(0));
            return;
        }
        Dialog dialog8 = this.loadingDialog;
        if (dialog8 != null) {
            dialog8.dismiss();
        }
        this.loadingDialog = null;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
