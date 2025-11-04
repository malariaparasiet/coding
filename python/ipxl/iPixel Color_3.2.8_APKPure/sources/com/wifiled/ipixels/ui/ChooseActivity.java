package com.wifiled.ipixels.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiro.jlotalibrary.exposed.BleSOTAData;
import com.tiro.jlotalibrary.exposed.BleStateCallBack;
import com.tiro.jlotalibrary.exposed.JLOTA;
import com.wifiled.baselib.app.cache.ACache;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.XBaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.manager.UpdateManager;
import com.wifiled.baselib.retrofit.ApiService;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.BluetoothFilterVo;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.retrofit.entity.Reply;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.BleRequestImpl2;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.gameview.utils.GridLineView;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.BuildConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.WifiStateReceiver;
import com.wifiled.ipixels.core.send.BaseSend;
import com.wifiled.ipixels.data.CloudManager;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListActivity;
import com.wifiled.ipixels.ui.clock.ColockActivity;
import com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity;
import com.wifiled.ipixels.ui.control.RemoteControlActivity;
import com.wifiled.ipixels.ui.dialog.ConnectDialog;
import com.wifiled.ipixels.ui.dialog.ConnectPwdDialog;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.ui.diy.DiyActivity;
import com.wifiled.ipixels.ui.diy.DiyAnimActivity;
import com.wifiled.ipixels.ui.diy.DiyImageActivity;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.ui.game.GameActivity;
import com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity;
import com.wifiled.ipixels.ui.projection.CameraXActivity;
import com.wifiled.ipixels.ui.projection.VideoActivity;
import com.wifiled.ipixels.ui.rhythm.MusicActivity;
import com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity;
import com.wifiled.ipixels.ui.riding.RidingModeActivity;
import com.wifiled.ipixels.ui.settings.SettingsActivity;
import com.wifiled.ipixels.ui.subzone.SubzoneListActivity;
import com.wifiled.ipixels.ui.text.TextActivity;
import com.wifiled.ipixels.ui.text.vo.CheckContent;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.ui.text.vo.TextHistoryVO;
import com.wifiled.ipixels.utils.FontUtils;
import com.wifiled.ipixels.utils.OtaUpData;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.vo.CategoryVO;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u009a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u0000 ¨\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002¨\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010Y\u001a\u00020\fH\u0014J\b\u0010Z\u001a\u00020[H\u0014J\b\u0010\\\u001a\u00020[H\u0014J\b\u0010a\u001a\u00020[H\u0002J\b\u0010f\u001a\u00020[H\u0014J\b\u0010g\u001a\u00020[H\u0014J\u0010\u0010h\u001a\u00020[2\u0006\u0010i\u001a\u00020(H\u0002J\b\u0010j\u001a\u00020[H\u0002J\b\u0010k\u001a\u00020[H\u0014J\b\u0010l\u001a\u00020[H\u0002J\b\u0010m\u001a\u00020[H\u0002J\b\u0010n\u001a\u00020[H\u0002J\b\u0010o\u001a\u00020[H\u0002J\u001b\u0010p\u001a\u00020[2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020%0rH\u0002¢\u0006\u0002\u0010sJ\b\u0010t\u001a\u00020[H\u0002J\b\u0010u\u001a\u00020[H\u0002J\b\u0010v\u001a\u00020[H\u0014J\u0018\u0010w\u001a\u00020[2\u0006\u0010x\u001a\u00020\f2\u0006\u0010y\u001a\u00020zH\u0002J\u0010\u0010{\u001a\u00020[2\u0006\u0010y\u001a\u00020zH\u0002J\u0010\u0010|\u001a\u00020[2\u0006\u0010}\u001a\u00020%H\u0002J\u0010\u0010~\u001a\u00020[2\u0006\u0010y\u001a\u00020zH\u0002J\t\u0010\u0083\u0001\u001a\u00020[H\u0002J\t\u0010\u0084\u0001\u001a\u00020[H\u0003J\t\u0010\u0085\u0001\u001a\u00020[H\u0002J%\u0010\u0086\u0001\u001a\u00020[2\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\f2\u0007\u0010\u008a\u0001\u001a\u00020\fH\u0002J\u0013\u0010\u008e\u0001\u001a\u00020[2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0007J\u001c\u0010\u0091\u0001\u001a\u00020 2\u0007\u0010\u0092\u0001\u001a\u00020\u00112\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\u001c\u0010\u0095\u0001\u001a\u00020[2\u0007\u0010\u0092\u0001\u001a\u00020\u00112\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\u0012\u0010\u0096\u0001\u001a\u00020[2\u0007\u0010\u0097\u0001\u001a\u00020 H\u0016J\u0012\u0010\u0098\u0001\u001a\u00020[2\u0007\u0010\u0099\u0001\u001a\u00020 H\u0016J\t\u0010\u009b\u0001\u001a\u00020[H\u0002J\u0012\u0010\u009c\u0001\u001a\u00020[2\u0007\u0010\u009d\u0001\u001a\u00020%H\u0002J\t\u0010\u009e\u0001\u001a\u00020[H\u0002J3\u0010\u009f\u0001\u001a\u00020[2\u0007\u0010 \u0001\u001a\u00020\f2\u000f\u0010¡\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020%0r2\b\u0010¢\u0001\u001a\u00030£\u0001H\u0016¢\u0006\u0003\u0010¤\u0001J!\u0010¥\u0001\u001a\u00020[2\u0007\u0010 \u0001\u001a\u00020\f2\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020%0QH\u0016J!\u0010§\u0001\u001a\u00020[2\u0007\u0010 \u0001\u001a\u00020\f2\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020%0QH\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020(0,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103R\u001a\u00107\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00101\"\u0004\b9\u00103R\u001a\u0010:\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\u000e\u0010=\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010L\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\b\u0012\u0004\u0012\u00020%0QX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020VX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020XX\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010]\u001a\u00020^¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`R\u001c\u0010b\u001a\u0010\u0012\f\u0012\n e*\u0004\u0018\u00010d0d0cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020%0rX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0080\u0001R\u001a\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020%0r8\u0002X\u0083\u0004¢\u0006\u0005\n\u0003\u0010\u0080\u0001R\u001a\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020%0r8\u0002X\u0083\u0004¢\u0006\u0005\n\u0003\u0010\u0080\u0001R\u000f\u0010\u008b\u0001\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009a\u0001\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006©\u0001"}, d2 = {"Lcom/wifiled/ipixels/ui/ChooseActivity;", "Lcom/wifiled/baselib/base/XBaseActivity;", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "Landroidx/lifecycle/LifecycleObserver;", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog$Companion$onCallBackLedHasWifiListener;", "Lpub/devrel/easypermissions/EasyPermissions$PermissionCallbacks;", "<init>", "()V", "adapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/vo/CategoryVO;", "RC_CAMERA_AND_LOCATION", "", "mAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mWifiStateReceiver", "Lcom/wifiled/ipixels/core/WifiStateReceiver;", "bleManager", "Lcom/wifiled/ipixels/core/BleManager;", "getBleManager", "()Lcom/wifiled/ipixels/core/BleManager;", "bleManager$delegate", "Lkotlin/Lazy;", "bleManager2", "Lcom/wifiled/ipixels/core/BleManager2;", "getBleManager2", "()Lcom/wifiled/ipixels/core/BleManager2;", "bleManager2$delegate", "isAuto", "", "time", "", "mBlueFilter", "", "", "bleRequest", "Lcom/wifiled/blelibrary/ble/BleRequestImpl;", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "getBleRequest", "()Lcom/wifiled/blelibrary/ble/BleRequestImpl;", "bleRequest2", "Lcom/wifiled/blelibrary/ble/BleRequestImpl2;", "getBleRequest2", "()Lcom/wifiled/blelibrary/ble/BleRequestImpl2;", "setMtu", "getSetMtu", "()Z", "setSetMtu", "(Z)V", "setMtu2", "getSetMtu2", "setSetMtu2", "setDescriptor", "getSetDescriptor", "setSetDescriptor", "setDescriptor2", "getSetDescriptor2", "setSetDescriptor2", "mConnectDialog", "Lcom/wifiled/ipixels/ui/dialog/ConnectDialog;", "connectPwdDialog", "Lcom/wifiled/ipixels/ui/dialog/ConnectPwdDialog;", "mtu_", "mtu_2", "bleStateCallBack", "Lcom/tiro/jlotalibrary/exposed/BleStateCallBack;", "bleStateCallBack2", "version", "mCurPwd", "disposable", "Lio/reactivex/disposables/Disposable;", "appUpdateManager", "Lcom/google/android/play/core/appupdate/AppUpdateManager;", "appUpdateInfoTask", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/play/core/appupdate/AppUpdateInfo;", "disposable2", "mSendResourceIDList", "", "iv_add", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "recyclerview", "rl_ctrl_led_onoff", "Landroid/widget/RelativeLayout;", "iv_control_led_onoff", "Landroid/widget/ImageView;", "layoutId", "initView", "", "bindData", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/google/android/play/core/install/InstallStateUpdatedListener;", "getListener", "()Lcom/google/android/play/core/install/InstallStateUpdatedListener;", "popupSnackbarForCompleteUpdate", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "kotlin.jvm.PlatformType", "onResume", "bindListener", "startOTA", "devices", "getBlueFilter", "unbindData", "initHistoryData", "wifiReceiver", "initIosDialogAdapter", "chooseImage", "requestToTakePhoto", "permissionList", "", "([Ljava/lang/String;)V", "initAdapter", "initFontCahe", "onDestroy", "showMenuType", PlayerFinal.PLAYER_POSITION, "options", "Landroid/app/ActivityOptions;", "toMusic", "showWifiActicityTipDialog", "str", "showDiyDialog", "BLUETOOTH_PERMISSIONS_OLD", "[Ljava/lang/String;", "BLUETOOTH_PERMISSIONS_TIRAMISU", "BLUETOOTH_PERMISSIONS_S", "requestBtPermissions", "locationRequest", "showConnectDialog", "showDialog", "context", "Landroid/app/Activity;", "layout", "gravity", "mSendSuc", "bTemp", "", "onCallBackResult", "sendResultMsg", "Lcom/wifiled/ipixels/ui/text/vo/SendResultMsg;", "onInterceptTouchEvent", "rv", "e", "Landroid/view/MotionEvent;", "onTouchEvent", "onRequestDisallowInterceptTouchEvent", "disallowIntercept", "onLedWifiListener", "isHas", "isInputPwd", "showPwdDialog", "verifyPwd", "pwd", "upResourceData", "onRequestPermissionsResult", "requestCode", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onPermissionsGranted", "perms", "onPermissionsDenied", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity extends XBaseActivity implements RecyclerView.OnItemTouchListener, LifecycleObserver, ConnectDialog.Companion.onCallBackLedHasWifiListener, EasyPermissions.PermissionCallbacks {
    private final String[] BLUETOOTH_PERMISSIONS_OLD;
    private final String[] BLUETOOTH_PERMISSIONS_S;
    private final String[] BLUETOOTH_PERMISSIONS_TIRAMISU;
    private RecyclerView actionRecyclerView;
    private ActivityResultLauncher<IntentSenderRequest> activityResultLauncher;
    private RecyclerAdapter<CategoryVO> adapter;
    private Task<AppUpdateInfo> appUpdateInfoTask;
    private AppUpdateManager appUpdateManager;
    private byte bTemp;
    private final BleRequestImpl<BleDevice> bleRequest;
    private final BleRequestImpl2<BleDevice> bleRequest2;
    private BleStateCallBack bleStateCallBack;
    private BleStateCallBack bleStateCallBack2;
    private ConnectPwdDialog connectPwdDialog;
    private Disposable disposable;
    private Disposable disposable2;
    private boolean isInputPwd;
    private CustomImageView iv_add;
    private ImageView iv_control_led_onoff;
    private final InstallStateUpdatedListener listener;
    private IosDialogStyleAdapter<Object> mAdapter;
    private List<String> mBlueFilter;
    private ConnectDialog mConnectDialog;
    private String mCurPwd;
    private List<String> mSendResourceIDList;
    private int mSendSuc;
    private int mtu_;
    private int mtu_2;
    private RecyclerView recyclerview;
    private RelativeLayout rl_ctrl_led_onoff;
    private boolean setDescriptor;
    private boolean setDescriptor2;
    private boolean setMtu;
    private boolean setMtu2;
    private long time;
    private int version;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArrayList<Class<? extends BaseActivity>> clzs = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, DiyActivity.class, MusicActivity.class, GameActivity.class, CameraXActivity.class, RidingModeActivity.class, ChannelListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, DiyActivity.class, MusicActivity.class, ChannelListActivity.class, ColockActivity.class, RemoteControlActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, DiyActivity.class, MusicActivity.class, RidingModeActivity.class, ChannelListActivity.class, ColockActivity.class, RemoteControlActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_4 = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, DiyActivity.class, MusicActivity.class, ChannelListActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_4 = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, DiyActivity.class, MusicActivity.class, RidingModeActivity.class, ChannelListActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_16 = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, ChannelListActivity.class, DiyImageActivity.class, DiyAnimActivity.class, RemoteControlActivity.class, MusicWidescreenActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_12_32 = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, ChannelListActivity.class, DiyImageActivity.class, DiyAnimActivity.class, MusicWidescreenActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_96 = CollectionsKt.arrayListOf(TextActivity.class, GalleryActivity.class, ChannelListActivity.class, DiyImageActivity.class, DiyAnimActivity.class, BusinessRemoteControlActivity.class, MusicWidescreenActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_zone = CollectionsKt.arrayListOf(TextActivity.class, SubzoneListActivity.class, GalleryActivity.class, ChannelListActivity.class, DiyImageActivity.class, DiyAnimActivity.class, BusinessRemoteControlActivity.class, MusicWidescreenActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_bt_ble_base_color = CollectionsKt.arrayListOf(TextActivity.class, SubzoneListActivity.class, GalleryActivity.class, ChannelListActivity.class, BusinessRemoteControlActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private static final ArrayList<Class<? extends AppCompatActivity>> clzs_256 = CollectionsKt.arrayListOf(TextActivity.class, SubzoneListActivity.class, GalleryActivity.class, DiyAnimActivity.class, ChannelListActivity.class, BusinessRemoteControlActivity.class, ColockActivity.class, ImageTextListActivity.class, SettingsActivity.class);
    private final int RC_CAMERA_AND_LOCATION = 100001;
    private final WifiStateReceiver mWifiStateReceiver = new WifiStateReceiver();

    /* renamed from: bleManager$delegate, reason: from kotlin metadata */
    private final Lazy bleManager = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            BleManager bleManager_delegate$lambda$0;
            bleManager_delegate$lambda$0 = ChooseActivity.bleManager_delegate$lambda$0();
            return bleManager_delegate$lambda$0;
        }
    });

    /* renamed from: bleManager2$delegate, reason: from kotlin metadata */
    private final Lazy bleManager2 = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            BleManager2 bleManager2_delegate$lambda$1;
            bleManager2_delegate$lambda$1 = ChooseActivity.bleManager2_delegate$lambda$1();
            return bleManager2_delegate$lambda$1;
        }
    });
    private boolean isAuto = true;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Intrinsics.checkNotNullParameter(rv, "rv");
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Intrinsics.checkNotNullParameter(rv, "rv");
        Intrinsics.checkNotNullParameter(e, "e");
    }

    public ChooseActivity() {
        BleRequestImpl<BleDevice> bleRequest = BleRequestImpl.getBleRequest();
        Intrinsics.checkNotNullExpressionValue(bleRequest, "getBleRequest(...)");
        this.bleRequest = bleRequest;
        BleRequestImpl2<BleDevice> bleRequest2 = BleRequestImpl2.getBleRequest();
        Intrinsics.checkNotNullExpressionValue(bleRequest2, "getBleRequest(...)");
        this.bleRequest2 = bleRequest2;
        this.mtu_ = 20;
        this.mtu_2 = 20;
        this.mSendResourceIDList = new ArrayList();
        this.listener = new InstallStateUpdatedListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda16
            @Override // com.google.android.play.core.listener.StateUpdatedListener
            public final void onStateUpdate(InstallState installState) {
                ChooseActivity.listener$lambda$5(ChooseActivity.this, installState);
            }
        };
        ActivityResultLauncher<IntentSenderRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda17
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ChooseActivity.activityResultLauncher$lambda$8(ChooseActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.activityResultLauncher = registerForActivityResult;
        this.BLUETOOTH_PERMISSIONS_OLD = new String[]{"android.permission.BLUETOOTH", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION"};
        this.BLUETOOTH_PERMISSIONS_TIRAMISU = new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION", "android.permission.NEARBY_WIFI_DEVICES", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"};
        this.BLUETOOTH_PERMISSIONS_S = new String[]{"android.permission.BLUETOOTH", "android.permission.BLUETOOTH_SCAN", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.BLUETOOTH_CONNECT", "android.permission.ACCESS_FINE_LOCATION"};
        this.mSendSuc = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BleManager bleManager_delegate$lambda$0() {
        return BleManager.INSTANCE.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BleManager getBleManager() {
        return (BleManager) this.bleManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BleManager2 bleManager2_delegate$lambda$1() {
        return BleManager2.INSTANCE.get();
    }

    private final BleManager2 getBleManager2() {
        return (BleManager2) this.bleManager2.getValue();
    }

    public final BleRequestImpl<BleDevice> getBleRequest() {
        return this.bleRequest;
    }

    public final BleRequestImpl2<BleDevice> getBleRequest2() {
        return this.bleRequest2;
    }

    public final boolean getSetMtu() {
        return this.setMtu;
    }

    public final void setSetMtu(boolean z) {
        this.setMtu = z;
    }

    public final boolean getSetMtu2() {
        return this.setMtu2;
    }

    public final void setSetMtu2(boolean z) {
        this.setMtu2 = z;
    }

    public final boolean getSetDescriptor() {
        return this.setDescriptor;
    }

    public final void setSetDescriptor(boolean z) {
        this.setDescriptor = z;
    }

    public final boolean getSetDescriptor2() {
        return this.setDescriptor2;
    }

    public final void setSetDescriptor2(boolean z) {
        this.setDescriptor2 = z;
    }

    /* compiled from: ChooseActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R1\u0010\u0004\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR1\u0010\u000b\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR1\u0010\u000e\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR1\u0010\u0010\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR1\u0010\u0012\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR1\u0010\u0014\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\nR1\u0010\u0016\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\nR1\u0010\u0018\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\nR1\u0010\u001a\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\nR1\u0010\u001c\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\nR1\u0010\u001e\u001a\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\n¨\u0006 "}, d2 = {"Lcom/wifiled/ipixels/ui/ChooseActivity$Companion;", "", "<init>", "()V", "clzs", "Ljava/util/ArrayList;", "Ljava/lang/Class;", "Lcom/wifiled/baselib/base/BaseActivity;", "Lkotlin/collections/ArrayList;", "getClzs", "()Ljava/util/ArrayList;", "clzs_bt", "Landroidx/appcompat/app/AppCompatActivity;", "getClzs_bt", "clzs_bt_ble", "getClzs_bt_ble", "clzs_bt_4", "getClzs_bt_4", "clzs_bt_ble_4", "getClzs_bt_ble_4", "clzs_bt_ble_16", "getClzs_bt_ble_16", "clzs_bt_ble_12_32", "getClzs_bt_ble_12_32", "clzs_bt_ble_96", "getClzs_bt_ble_96", "clzs_bt_ble_zone", "getClzs_bt_ble_zone", "clzs_bt_ble_base_color", "getClzs_bt_ble_base_color", "clzs_256", "getClzs_256", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ArrayList<Class<? extends BaseActivity>> getClzs() {
            return ChooseActivity.clzs;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt() {
            return ChooseActivity.clzs_bt;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble() {
            return ChooseActivity.clzs_bt_ble;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_4() {
            return ChooseActivity.clzs_bt_4;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_4() {
            return ChooseActivity.clzs_bt_ble_4;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_16() {
            return ChooseActivity.clzs_bt_ble_16;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_12_32() {
            return ChooseActivity.clzs_bt_ble_12_32;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_96() {
            return ChooseActivity.clzs_bt_ble_96;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_zone() {
            return ChooseActivity.clzs_bt_ble_zone;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_bt_ble_base_color() {
            return ChooseActivity.clzs_bt_ble_base_color;
        }

        public final ArrayList<Class<? extends AppCompatActivity>> getClzs_256() {
            return ChooseActivity.clzs_256;
        }
    }

    @Override // com.wifiled.baselib.base.XBaseActivity
    protected int layoutId() {
        getWindow().setFlags(128, 128);
        return R.layout.activity_choose;
    }

    @Override // com.wifiled.baselib.base.XBaseActivity
    protected void initView() {
        View findViewById = findViewById(R.id.iv_add);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_add = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.recyclerview);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.recyclerview = (RecyclerView) findViewById2;
        View findViewById3 = findViewById(R.id.rl_ctrl_led_onoff);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.rl_ctrl_led_onoff = (RelativeLayout) findViewById3;
        View findViewById4 = findViewById(R.id.iv_control_led_onoff);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_control_led_onoff = (ImageView) findViewById4;
    }

    @Override // com.wifiled.baselib.base.XBaseActivity
    protected void bindData() {
        EventBus.getDefault().register(this);
        GridLineView.INSTANCE.setLedType(AppConfig.INSTANCE.getLedType());
        getBlueFilter();
        initHistoryData();
        wifiReceiver();
        initIosDialogAdapter();
        initAdapter();
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                ChooseActivity.this.initFontCahe();
            }
        });
        String appMetaData = AppUtils.getAppMetaData(this, "HEATON_CHANNEL");
        Intrinsics.checkNotNull(appMetaData);
        if (!Intrinsics.areEqual(BuildConfig.FLAVOR, appMetaData)) {
            try {
                new UpdateManager(this).versionUpdate(LanguageUtil.getSaveLanguage(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LogUtils.v("google upApp");
            AppUpdateManager create = AppUpdateManagerFactory.create(getApplicationContext());
            this.appUpdateManager = create;
            Intrinsics.checkNotNull(create);
            this.appUpdateInfoTask = create.getAppUpdateInfo();
            AppUpdateManager appUpdateManager = this.appUpdateManager;
            Intrinsics.checkNotNull(appUpdateManager);
            appUpdateManager.registerListener(this.listener);
            Task<AppUpdateInfo> task = this.appUpdateInfoTask;
            Intrinsics.checkNotNull(task);
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit bindData$lambda$3;
                    bindData$lambda$3 = ChooseActivity.bindData$lambda$3(ChooseActivity.this, (AppUpdateInfo) obj);
                    return bindData$lambda$3;
                }
            };
            Intrinsics.checkNotNull(task.addOnSuccessListener(new OnSuccessListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda32
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Function1.this.invoke(obj);
                }
            }));
        }
        LogUtils.file("ChooseActivity bindData");
        upResourceData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$3(ChooseActivity chooseActivity, AppUpdateInfo appUpdateInfo) {
        LogUtils.v("UpdateAvailability ---- " + appUpdateInfo.updateAvailability());
        if (appUpdateInfo.updateAvailability() == 2 && appUpdateInfo.isUpdateTypeAllowed(1)) {
            AppUpdateManager appUpdateManager = chooseActivity.appUpdateManager;
            Intrinsics.checkNotNull(appUpdateManager);
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, chooseActivity.activityResultLauncher, AppUpdateOptions.newBuilder(1).build());
        }
        return Unit.INSTANCE;
    }

    public final InstallStateUpdatedListener getListener() {
        return this.listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void listener$lambda$5(ChooseActivity chooseActivity, InstallState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        state.installStatus();
        if (state.installStatus() == 11) {
            chooseActivity.popupSnackbarForCompleteUpdate();
        }
    }

    private final void popupSnackbarForCompleteUpdate() {
        Snackbar make = Snackbar.make(findViewById(R.id.activity_main_layout), "An update has just been downloaded.", -2);
        make.setAction("RESTART", new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseActivity.popupSnackbarForCompleteUpdate$lambda$7$lambda$6(ChooseActivity.this, view);
            }
        });
        make.setActionTextColor(getResources().getColor(R.color.text_color));
        make.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void popupSnackbarForCompleteUpdate$lambda$7$lambda$6(ChooseActivity chooseActivity, View view) {
        AppUpdateManager appUpdateManager = chooseActivity.appUpdateManager;
        if (appUpdateManager != null) {
            appUpdateManager.completeUpdate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void activityResultLauncher$lambda$8(ChooseActivity chooseActivity, ActivityResult activityResult) {
        AppUpdateManager appUpdateManager = chooseActivity.appUpdateManager;
        if (appUpdateManager != null) {
            appUpdateManager.unregisterListener(chooseActivity.listener);
        }
        if (activityResult.getResultCode() != -1) {
            LogUtils.vTag("ruis", "google 更新失败");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.wifiled.baselib.base.XBaseActivity
    protected void bindListener() {
        super.bindListener();
        getBleManager().setBleCallback(new ChooseActivity$bindListener$1(this));
        getBleManager2().setBleCallback(new ChooseActivity$bindListener$2(this));
        CustomImageView customImageView = this.iv_add;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_add");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseActivity.this.requestBtPermissions();
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView3 = this.iv_add;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_add");
        } else {
            customImageView2 = customImageView3;
        }
        companion.attachViewOnTouchListener(customImageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startOTA(final BleDevice devices) {
        LogUtils.file("startOTA 检查JLOTA");
        if (this.bleStateCallBack == null && this.setMtu && this.setDescriptor) {
            int i = this.version;
            String cid = devices.getCid();
            Intrinsics.checkNotNullExpressionValue(cid, "getCid(...)");
            String pid = devices.getPid();
            Intrinsics.checkNotNullExpressionValue(pid, "getPid(...)");
            JLOTA.INSTANCE.checkOTA(this, i, cid, pid, new BleSOTAData() { // from class: com.wifiled.ipixels.ui.ChooseActivity$startOTA$1
                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public boolean sendData(UUID uuidService, UUID uuidWrite, BluetoothDevice device, byte[] item) {
                    BluetoothGatt bluetoothGatt;
                    BluetoothGattService service;
                    BluetoothGattCharacteristic characteristic;
                    Intrinsics.checkNotNullParameter(uuidService, "uuidService");
                    Intrinsics.checkNotNullParameter(uuidWrite, "uuidWrite");
                    Intrinsics.checkNotNullParameter(item, "item");
                    if (device == null || (bluetoothGatt = ChooseActivity.this.getBleRequest().getBluetoothGatt(device.getAddress())) == null || (service = bluetoothGatt.getService(uuidService)) == null || (characteristic = service.getCharacteristic(uuidWrite)) == null) {
                        return false;
                    }
                    characteristic.setValue(item);
                    return bluetoothGatt.writeCharacteristic(characteristic);
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public void disconnect(BluetoothDevice device) {
                    BleManager bleManager;
                    if (device != null) {
                        bleManager = ChooseActivity.this.getBleManager();
                        bleManager.disconnect();
                    }
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public void connect(String address) {
                    BleManager bleManager;
                    if (address != null) {
                        bleManager = ChooseActivity.this.getBleManager();
                        bleManager.connect(devices);
                    }
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public void registerBleCallback(BleStateCallBack back) {
                    Intrinsics.checkNotNullParameter(back, "back");
                    ChooseActivity.this.bleStateCallBack = back;
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public void unregisterBleCallback() {
                    ChooseActivity.this.bleStateCallBack = null;
                    ChooseActivity.this.mtu_ = 20;
                    ChooseActivity.this.setSetMtu(false);
                    ChooseActivity.this.setSetDescriptor(false);
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public BluetoothGatt getGatt() {
                    return ChooseActivity.this.getBleRequest().getBluetoothGatt(devices.getBleAddress());
                }

                @Override // com.tiro.jlotalibrary.exposed.BleSOTAData
                public int getMtu() {
                    int i2;
                    i2 = ChooseActivity.this.mtu_;
                    return i2;
                }
            });
        }
    }

    private final void getBlueFilter() {
        NetWorkManager.INSTANCE.getRequest().bluetoothFilter(2, BuildConfig.APPLICATION_ID).enqueue((Callback) new Callback<Reply<? extends BluetoothFilterVo>>() { // from class: com.wifiled.ipixels.ui.ChooseActivity$getBlueFilter$1
            @Override // retrofit2.Callback
            public void onResponse(Call<Reply<? extends BluetoothFilterVo>> call, Response<Reply<? extends BluetoothFilterVo>> response) {
                BleManager bleManager;
                BluetoothFilterVo data;
                BluetoothFilterVo data2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                ChooseActivity chooseActivity = ChooseActivity.this;
                Reply<? extends BluetoothFilterVo> body = response.body();
                List<String> list = null;
                chooseActivity.mBlueFilter = (body == null || (data2 = body.getData()) == null) ? null : data2.getBt_device_filter();
                bleManager = ChooseActivity.this.getBleManager();
                bleManager.scan();
                Gson gson = new Gson();
                Reply<? extends BluetoothFilterVo> body2 = response.body();
                if (body2 != null && (data = body2.getData()) != null) {
                    list = data.getBt_device_filter();
                }
                String json = gson.toJson(list);
                String str = json;
                if (str == null || str.length() == 0) {
                    return;
                }
                SPUtils.put(ChooseActivity.this, "device_filter", json);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<Reply<? extends BluetoothFilterVo>> call, Throwable t) {
                BleManager bleManager;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                String str = (String) SPUtils.get(ChooseActivity.this, "device_filter", "");
                Intrinsics.checkNotNull(str);
                if (str.length() > 0) {
                    ChooseActivity.this.mBlueFilter = (List) new Gson().fromJson(str, new TypeToken<List<? extends String>>() { // from class: com.wifiled.ipixels.ui.ChooseActivity$getBlueFilter$1$onFailure$1
                    }.getType());
                }
                bleManager = ChooseActivity.this.getBleManager();
                bleManager.scan();
            }
        });
    }

    @Override // com.wifiled.baselib.base.XBaseActivity
    protected void unbindData() {
        unregisterReceiver(this.mWifiStateReceiver);
    }

    private final void initHistoryData() {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                ChooseActivity.initHistoryData$lambda$10(ChooseActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initHistoryData$lambda$10(ChooseActivity chooseActivity) {
        try {
            if (ACache.get(chooseActivity).getAsList("text_history", TextHistoryVO.class) != null) {
                CheckContent checkContent = CheckContent.INSTANCE;
                ArrayList<TextHistoryVO> asList = ACache.get(chooseActivity).getAsList("text_history", TextHistoryVO.class);
                Intrinsics.checkNotNullExpressionValue(asList, "getAsList(...)");
                checkContent.setCacheTextHistoryVOs(asList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void wifiReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        if (Build.VERSION.SDK_INT > 33) {
            registerReceiver(this.mWifiStateReceiver, intentFilter, 4);
        } else {
            registerReceiver(this.mWifiStateReceiver, intentFilter);
        }
    }

    private final void initIosDialogAdapter() {
        this.mAdapter = new IosDialogStyleAdapter<>(this, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.title_diy_phone_photos), getString(R.string.title_diy_phone_camera)}));
    }

    private final void chooseImage() {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        ChooseActivity chooseActivity = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(chooseActivity, 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(chooseActivity, 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.actionRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ChooseActivity.chooseImage$lambda$11(ChooseActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda3
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ChooseActivity.chooseImage$lambda$12(showBottomDialog, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                showBottomDialog.cancel();
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        Intrinsics.checkNotNull(textView);
        companion.attachViewOnTouchListener(textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$11(ChooseActivity chooseActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = chooseActivity.mAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = chooseActivity.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = chooseActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$12(Dialog dialog, ChooseActivity chooseActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        dialog.cancel();
        if (i == 0) {
            Intent intent = new Intent(chooseActivity, (Class<?>) VideoActivity.class);
            intent.putExtra("select_storage_video", true);
            chooseActivity.startActivity(intent);
        } else if (i == 1) {
            if (Build.VERSION.SDK_INT >= 33) {
                chooseActivity.requestToTakePhoto(new String[]{"android.permission.CAMERA"});
            } else {
                chooseActivity.requestToTakePhoto(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"});
            }
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = chooseActivity.mAdapter;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        iosDialogStyleAdapter.notifyDataSetChanged();
    }

    private final void requestToTakePhoto(String[] permissionList) {
        requestPermission(permissionList, getString(R.string.open_camera), new ChooseActivity$requestToTakePhoto$1(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23, types: [android.widget.RelativeLayout] */
    private final void initAdapter() {
        RecyclerView recyclerView = this.recyclerview;
        ImageView imageView = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView = null;
        }
        ChooseActivity chooseActivity = this;
        recyclerView.setLayoutManager(new GridLayoutManager(chooseActivity, 3));
        final List<CategoryVO> categoryData = Constants.INSTANCE.getCategoryData(chooseActivity);
        this.adapter = new RecyclerAdapter<CategoryVO>(categoryData) { // from class: com.wifiled.ipixels.ui.ChooseActivity$initAdapter$1
            {
                ChooseActivity chooseActivity2 = ChooseActivity.this;
            }

            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public void convert(RecyclerViewHolder holder, CategoryVO category) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(category, "category");
                holder.setText(R.id.tv_category, category.getName());
                holder.setImageResource(R.id.iv_category, category.getResId());
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ChooseActivity.this.getResources().getDisplayMetrics().widthPixels / 3, -2));
            }
        };
        RecyclerView recyclerView2 = this.recyclerview;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView2 = null;
        }
        RecyclerAdapter<CategoryVO> recyclerAdapter = this.adapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter = null;
        }
        recyclerView2.setAdapter(recyclerAdapter);
        RecyclerView recyclerView3 = this.recyclerview;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView3 = null;
        }
        recyclerView3.addOnItemTouchListener(this);
        RecyclerAdapter<CategoryVO> recyclerAdapter2 = this.adapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter2 = null;
        }
        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda0
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ChooseActivity.initAdapter$lambda$14(ChooseActivity.this, viewGroup, view, (CategoryVO) obj, i);
            }
        });
        if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13 || (AppConfig.INSTANCE.getLedType() == 3 && Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0007") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01"))) {
            RelativeLayout relativeLayout = this.rl_ctrl_led_onoff;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_ctrl_led_onoff");
                relativeLayout = null;
            }
            UtilsExtensionKt.show(relativeLayout);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            ImageView imageView2 = this.iv_control_led_onoff;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_control_led_onoff");
                imageView2 = null;
            }
            imageView2.setSelected(AppConfig.INSTANCE.getBledOn());
            ImageView imageView3 = this.iv_control_led_onoff;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_control_led_onoff");
            } else {
                imageView = imageView3;
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChooseActivity.initAdapter$lambda$15(Ref.BooleanRef.this, view);
                }
            });
            return;
        }
        ?? r0 = this.rl_ctrl_led_onoff;
        if (r0 == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_ctrl_led_onoff");
        } else {
            imageView = r0;
        }
        UtilsExtensionKt.hide(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAdapter$lambda$14(ChooseActivity chooseActivity, ViewGroup viewGroup, View view, CategoryVO categoryVO, int i) {
        ActivityOptions makeScaleUpAnimation = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
        Intrinsics.checkNotNull(makeScaleUpAnimation);
        chooseActivity.showMenuType(i, makeScaleUpAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAdapter$lambda$15(Ref.BooleanRef booleanRef, View view) {
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            EventBus eventBus = EventBus.getDefault();
            byte[] bytes = "dev disconnect".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            eventBus.post(new SendResultMsg(bytes));
            return;
        }
        booleanRef.element = !booleanRef.element;
        view.setSelected(booleanRef.element);
        BaseSend.sendLedOnOff$default(SendCore.INSTANCE, booleanRef.element ? 1 : 0, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initFontCahe() {
        int[] iArr = {0, 1, 2, 3, 4, 5, 6, 7, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29};
        for (int i = 0; i < 21; i++) {
            FontUtils.getTypeface(iArr[i]);
        }
    }

    @Override // com.wifiled.baselib.base.XBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private final void showMenuType(int position, ActivityOptions options) {
        ArrayList<Class<? extends AppCompatActivity>> arrayList;
        AppConfig.INSTANCE.setPrevActivityName(AppConfig.INSTANCE.getTopActivity());
        LogUtils.file("  MCU=" + AppConfig.INSTANCE.getMcu() + " position=" + position + "  AppConfig.ledType=" + AppConfig.INSTANCE.getLedType());
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                if (!AppConfig.INSTANCE.getLedHasWifi()) {
                    if (AppConfig.INSTANCE.getMcu() > 4 && AppConfig.INSTANCE.getMcu() != 7 && AppConfig.INSTANCE.getMcu() != 12) {
                        if (AppConfig.INSTANCE.getCid().length() == 0 && AppConfig.INSTANCE.getPid().length() == 0) {
                            arrayList = clzs_bt_ble_4;
                        } else if (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0004") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")) {
                            arrayList = clzs_bt_ble_4;
                        } else {
                            arrayList = clzs_bt_ble;
                        }
                    } else {
                        arrayList = clzs_bt_ble_4;
                    }
                    if (position == 2) {
                        showDiyDialog(options);
                        break;
                    } else if (position == 3) {
                        toMusic(options);
                        break;
                    } else if (position >= arrayList.size()) {
                        toActivity(arrayList.get(position - 1), options.toBundle());
                        break;
                    } else {
                        toActivity(arrayList.get(position), options.toBundle());
                        break;
                    }
                } else if (position == 2) {
                    showDiyDialog(options);
                    break;
                } else if (position == 3) {
                    toMusic(options);
                    break;
                } else if (position == 4 || position == 5) {
                    int connectType = AppConfig.INSTANCE.getConnectType();
                    if (connectType == -1) {
                        String string = getString(R.string.tip_only_connect_wifi_type);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        showWifiActicityTipDialog(string);
                        break;
                    } else if (connectType == 0) {
                        toActivity(clzs.get(position), options.toBundle());
                        break;
                    } else if (connectType == 1) {
                        String string2 = getString(R.string.tip_only_connect_wifi);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        showWifiActicityTipDialog(string2);
                        break;
                    }
                } else {
                    toActivity(clzs.get(position), options.toBundle());
                    break;
                }
                break;
            case 1:
                ArrayList<Class<? extends AppCompatActivity>> arrayList2 = clzs_bt_ble_16;
                if ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "08")) || ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "000f") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")) || (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0011") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01")))) {
                    arrayList2 = clzs_bt_ble_96;
                }
                if (position == 3) {
                    toActivity(arrayList2.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_image");
                    break;
                } else if (position == 4) {
                    toActivity(arrayList2.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                    break;
                } else if (position == 6) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList2.get(position), options.toBundle());
                    break;
                }
                break;
            case 2:
                ArrayList<Class<? extends AppCompatActivity>> arrayList3 = (AppConfig.INSTANCE.getMcu() <= 4 || AppConfig.INSTANCE.getMcu() == 7 || Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0000") || Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") || Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0007") || Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "02")) ? clzs_bt_4 : clzs_bt;
                if (position == 2) {
                    showDiyDialog(options);
                    break;
                } else if (position == 3) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList3.get(position), options.toBundle());
                    break;
                }
            case 3:
            case 5:
                ArrayList<Class<? extends AppCompatActivity>> arrayList4 = clzs_bt_ble_16;
                if (position == 3) {
                    toActivity(arrayList4.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_image");
                    break;
                } else if (position == 4) {
                    toActivity(arrayList4.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                    break;
                } else if (position == 6) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList4.get(position), options.toBundle());
                    break;
                }
            case 4:
                LogUtils.vTag("ruis", " AppConfig.pid ==" + AppConfig.INSTANCE.getPid() + " \" AppConfig.Cid ==" + AppConfig.INSTANCE.getCid());
                if ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "10")) || ((Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0008") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01")) || (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0012") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "01")))) {
                    ArrayList<Class<? extends AppCompatActivity>> arrayList5 = clzs_bt_ble_12_32;
                    if (position == 3) {
                        toActivity(arrayList5.get(position), options.toBundle());
                        AppConfig.INSTANCE.setPrevActivityName("diy_image");
                        break;
                    } else if (position == 4) {
                        toActivity(arrayList5.get(position), options.toBundle());
                        AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                        break;
                    } else if (position == 5) {
                        toMusic(options);
                        break;
                    } else {
                        toActivity(arrayList5.get(position), options.toBundle());
                        break;
                    }
                } else {
                    ArrayList<Class<? extends AppCompatActivity>> arrayList6 = clzs_bt_ble_16;
                    if (position == 3) {
                        toActivity(arrayList6.get(position), options.toBundle());
                        AppConfig.INSTANCE.setPrevActivityName("diy_image");
                        break;
                    } else if (position == 4) {
                        toActivity(arrayList6.get(position), options.toBundle());
                        AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                        break;
                    } else if (position == 6) {
                        toMusic(options);
                        break;
                    } else {
                        toActivity(arrayList6.get(position), options.toBundle());
                        break;
                    }
                }
                break;
            case 6:
            case 13:
                ArrayList<Class<? extends AppCompatActivity>> arrayList7 = clzs_bt_ble_12_32;
                if (position == 3) {
                    toActivity(arrayList7.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_image");
                    break;
                } else if (position == 4) {
                    toActivity(arrayList7.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                    break;
                } else if (position == 5) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList7.get(position), options.toBundle());
                    break;
                }
            case 7:
            case 8:
                ArrayList<Class<? extends AppCompatActivity>> arrayList8 = clzs_bt_ble_96;
                if (position == 3) {
                    toActivity(arrayList8.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_image");
                    break;
                } else if (position == 4) {
                    toActivity(arrayList8.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                    break;
                } else if (position == 6) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList8.get(position), options.toBundle());
                    break;
                }
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
                ArrayList<Class<? extends AppCompatActivity>> arrayList9 = clzs_bt_ble_zone;
                if (position == 4) {
                    toActivity(arrayList9.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_image");
                    break;
                } else if (position == 5) {
                    toActivity(arrayList9.get(position), options.toBundle());
                    AppConfig.INSTANCE.setPrevActivityName("diy_anim");
                    break;
                } else if (position == 7) {
                    toMusic(options);
                    break;
                } else {
                    toActivity(arrayList9.get(position), options.toBundle());
                    break;
                }
            case 16:
                toActivity(clzs_256.get(position), options.toBundle());
                break;
            case 17:
            case 18:
            case 19:
                toActivity(clzs_bt_ble_base_color.get(position), options.toBundle());
                break;
        }
    }

    private final void toMusic(ActivityOptions options) {
        if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
            toActivity(MusicActivity.class, options.toBundle());
        } else {
            toActivity(MusicWidescreenActivity.class, options.toBundle());
        }
    }

    private final void showWifiActicityTipDialog(String str) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setTitle(getString(R.string.gps_tip));
        builder.setMessage(str);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda34
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ChooseActivity.showWifiActicityTipDialog$lambda$17(ChooseActivity.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWifiActicityTipDialog$lambda$17(ChooseActivity chooseActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        BleManager.INSTANCE.get().disconnect();
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
        chooseActivity.startActivity(intent);
    }

    private final void showDiyDialog(final ActivityOptions options) {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_diy);
        ImageView imageView = (ImageView) showBottomDialog.findViewById(R.id.iv_diy_image);
        ImageView imageView2 = (ImageView) showBottomDialog.findViewById(R.id.iv_diy_anim);
        ImageView imageView3 = (ImageView) showBottomDialog.findViewById(R.id.iv_diy_projection);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseActivity.showDiyDialog$lambda$19(showBottomDialog, this, options, view);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseActivity.showDiyDialog$lambda$20(showBottomDialog, this, options, view);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseActivity.showDiyDialog$lambda$21(showBottomDialog, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDiyDialog$lambda$19(Dialog dialog, ChooseActivity chooseActivity, ActivityOptions activityOptions, View view) {
        dialog.cancel();
        chooseActivity.toActivity(DiyImageActivity.class, activityOptions.toBundle());
        AppConfig.INSTANCE.setPrevActivityName("diy_image");
        LogUtils.file("diy_image.setOnClickListener toActivity(DiyImageActivity::class.java)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDiyDialog$lambda$20(Dialog dialog, ChooseActivity chooseActivity, ActivityOptions activityOptions, View view) {
        dialog.cancel();
        chooseActivity.toActivity(DiyAnimActivity.class, activityOptions.toBundle());
        AppConfig.INSTANCE.setPrevActivityName("diy_anim");
        LogUtils.file("diy_anim.setOnClickListener toActivity(DiyAnimActivity::class.java)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDiyDialog$lambda$21(Dialog dialog, ChooseActivity chooseActivity, View view) {
        dialog.cancel();
        chooseActivity.chooseImage();
        LogUtils.file("diy_projection.setOnClickListener chooseImage)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestBtPermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermission(this.BLUETOOTH_PERMISSIONS_TIRAMISU, getString(R.string.open_bt_relative), new XBaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestBtPermissions$1
                @Override // com.wifiled.baselib.base.XBaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    if (!granted) {
                        ChooseActivity.this.locationRequest();
                    } else {
                        ChooseActivity.this.showConnectDialog();
                    }
                }
            });
        } else if (Build.VERSION.SDK_INT >= 31) {
            requestPermission(this.BLUETOOTH_PERMISSIONS_S, getString(R.string.open_bt_relative), new XBaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestBtPermissions$2
                @Override // com.wifiled.baselib.base.XBaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    if (!granted) {
                        ChooseActivity.this.locationRequest();
                    } else {
                        ChooseActivity.this.showConnectDialog();
                    }
                }
            });
        } else {
            requestPermission(this.BLUETOOTH_PERMISSIONS_OLD, "Need to enable Bluetooth related permissions", new XBaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.ChooseActivity$requestBtPermissions$3
                @Override // com.wifiled.baselib.base.XBaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    if (!granted) {
                        ChooseActivity.this.locationRequest();
                    } else {
                        ChooseActivity.this.showConnectDialog();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AfterPermissionGranted(100001)
    public final void locationRequest() {
        if (Build.VERSION.SDK_INT >= 33) {
            String[] strArr = this.BLUETOOTH_PERMISSIONS_TIRAMISU;
            if (!EasyPermissions.hasPermissions(this, (String[]) Arrays.copyOf(strArr, strArr.length))) {
                String string = getString(R.string.open_location);
                int i = this.RC_CAMERA_AND_LOCATION;
                String[] strArr2 = this.BLUETOOTH_PERMISSIONS_TIRAMISU;
                EasyPermissions.requestPermissions(this, string, i, (String[]) Arrays.copyOf(strArr2, strArr2.length));
                return;
            }
            showConnectDialog();
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            String[] strArr3 = this.BLUETOOTH_PERMISSIONS_S;
            if (!EasyPermissions.hasPermissions(this, (String[]) Arrays.copyOf(strArr3, strArr3.length))) {
                String string2 = getString(R.string.open_location);
                int i2 = this.RC_CAMERA_AND_LOCATION;
                String[] strArr4 = this.BLUETOOTH_PERMISSIONS_S;
                EasyPermissions.requestPermissions(this, string2, i2, (String[]) Arrays.copyOf(strArr4, strArr4.length));
                return;
            }
            showConnectDialog();
            return;
        }
        String[] strArr5 = this.BLUETOOTH_PERMISSIONS_OLD;
        if (!EasyPermissions.hasPermissions(this, (String[]) Arrays.copyOf(strArr5, strArr5.length))) {
            String string3 = getString(R.string.open_location);
            int i3 = this.RC_CAMERA_AND_LOCATION;
            String[] strArr6 = this.BLUETOOTH_PERMISSIONS_OLD;
            EasyPermissions.requestPermissions(this, string3, i3, (String[]) Arrays.copyOf(strArr6, strArr6.length));
            return;
        }
        showConnectDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showConnectDialog() {
        this.isAuto = false;
        showDialog(this, R.layout.dialog_connect, 53);
        ConnectDialog.INSTANCE.setLedWifiListener(this);
    }

    private final void showDialog(Activity context, int layout, int gravity) {
        ConnectDialog companion = ConnectDialog.INSTANCE.getInstance(context, R.style.dialog);
        this.mConnectDialog = companion;
        ConnectDialog connectDialog = null;
        if (companion == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            companion = null;
        }
        companion.setOwnerActivity(this);
        View inflate = LayoutInflater.from(context).inflate(layout, (ViewGroup) null);
        ConnectDialog connectDialog2 = this.mConnectDialog;
        if (connectDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog2 = null;
        }
        connectDialog2.setContentView(inflate);
        ConnectDialog connectDialog3 = this.mConnectDialog;
        if (connectDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog3 = null;
        }
        Window window = connectDialog3.getWindow();
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        ConnectDialog connectDialog4 = this.mConnectDialog;
        if (connectDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog4 = null;
        }
        connectDialog4.setCanceledOnTouchOutside(true);
        ConnectDialog connectDialog5 = this.mConnectDialog;
        if (connectDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog5 = null;
        }
        connectDialog5.setCancelable(true);
        ConnectDialog connectDialog6 = this.mConnectDialog;
        if (connectDialog6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog6 = null;
        }
        connectDialog6.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                ChooseActivity.this.isAuto = true;
            }
        });
        ConnectDialog connectDialog7 = this.mConnectDialog;
        if (connectDialog7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
            connectDialog7 = null;
        }
        connectDialog7.setDialogListener(new ConnectDialog.Companion.onDialogClickCallBackListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$showDialog$2
            @Override // com.wifiled.ipixels.ui.dialog.ConnectDialog.Companion.onDialogClickCallBackListener
            public void onAdapterItemClick(BleDevice device) {
            }
        });
        Point point = new Point();
        if (attributes != null) {
            attributes.width = -2;
        }
        getWindowManager().getDefaultDisplay().getRealSize(point);
        int i = point.y;
        if (attributes != null) {
            attributes.height = i;
        }
        if (attributes != null) {
            attributes.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8d);
        }
        if (window != null) {
            window.setAttributes(attributes);
        }
        if (window != null) {
            window.setGravity(gravity);
        }
        ConnectDialog connectDialog8 = this.mConnectDialog;
        if (connectDialog8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConnectDialog");
        } else {
            connectDialog = connectDialog8;
        }
        connectDialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCallBackResult(SendResultMsg sendResultMsg) {
        Intrinsics.checkNotNullParameter(sendResultMsg, "sendResultMsg");
        int i = (sendResultMsg.getByteArray().length == 0 ? 1 : 0) ^ 1;
        if (i == 1) {
            byte b = sendResultMsg.getByteArray()[0];
            if (this.bTemp != b) {
                this.bTemp = b;
                this.mSendSuc = -1;
            } else if (Intrinsics.areEqual((Object) UtilsExtensionKt.isDialogShow(), (Object) true)) {
                this.mSendSuc = -1;
            }
        }
        if (this.mSendSuc != i) {
            this.mSendSuc = i;
            if (i != 1) {
                String string = getString(R.string.msg_send_fail);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                UtilsExtensionKt.showLoadingDialog((Activity) this, true, string, false);
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onCallBackResult$lambda$32;
                        onCallBackResult$lambda$32 = ChooseActivity.onCallBackResult$lambda$32(ChooseActivity.this);
                        return onCallBackResult$lambda$32;
                    }
                }, 0L, 2, null);
                return;
            }
            String byteUtils = ByteUtils.toString(sendResultMsg.getByteArray());
            Intrinsics.checkNotNull(byteUtils);
            String str = byteUtils;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "channel low space", false, 2, (Object) null)) {
                ToastUtil.show(R.string.channel_tip_low_space);
                this.mSendSuc = -1;
                return;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "wifi connect", false, 2, (Object) null)) {
                AppConfig.INSTANCE.setBCheckOta(true);
                String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
                Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
                boolean contains$default = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null);
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = "";
                SendCore.INSTANCE.getLedType(contains$default ? (byte) 1 : (byte) 0, new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit onCallBackResult$lambda$25;
                        onCallBackResult$lambda$25 = ChooseActivity.onCallBackResult$lambda$25(Ref.ObjectRef.this, this, (SendCore.CallbackBuilder) obj);
                        return onCallBackResult$lambda$25;
                    }
                });
                return;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "dev disconnect", false, 2, (Object) null)) {
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onCallBackResult$lambda$26;
                        onCallBackResult$lambda$26 = ChooseActivity.onCallBackResult$lambda$26(ChooseActivity.this);
                        return onCallBackResult$lambda$26;
                    }
                }, 0L, 2, null);
                this.mSendSuc = -1;
                return;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "need_upd_ota", false, 2, (Object) null) && AppConfig.INSTANCE.getConnectType() == 1) {
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setTitle(getString(R.string.gps_tip));
                builder.setMessage(getString(R.string.msg_wifi_to_ota));
                builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda21
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        ChooseActivity.onCallBackResult$lambda$27(ChooseActivity.this, dialogInterface, i2);
                    }
                });
                builder.create().show();
                builder.refReshMessage(getString(R.string.msg_wifi_to_ota));
                return;
            }
            byte b2 = sendResultMsg.getByteArray()[0];
            if (b2 == Byte.MAX_VALUE) {
                if (!StringsKt.contains$default((CharSequence) AppConfig.INSTANCE.getTopActivity(), (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(ChooseActivity.class).getSimpleName()), false, 2, (Object) null)) {
                    String string2 = getString(R.string.msg_send_fail);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    UtilsExtensionKt.showLoadingDialog((Activity) this, true, string2, false);
                }
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onCallBackResult$lambda$28;
                        onCallBackResult$lambda$28 = ChooseActivity.onCallBackResult$lambda$28(ChooseActivity.this);
                        return onCallBackResult$lambda$28;
                    }
                }, 0L, 2, null);
                return;
            }
            if (b2 == Byte.MIN_VALUE) {
                UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onCallBackResult$lambda$29;
                        onCallBackResult$lambda$29 = ChooseActivity.onCallBackResult$lambda$29(ChooseActivity.this);
                        return onCallBackResult$lambda$29;
                    }
                }, 0L, 2, null);
                return;
            }
            if (StringsKt.contains$default((CharSequence) AppConfig.INSTANCE.getPrevActivityName(), (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(CameraXActivity.class).getSimpleName()), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) AppConfig.INSTANCE.getPrevActivityName(), (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(VideoActivity.class).getSimpleName()), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) AppConfig.INSTANCE.getTopActivity(), (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(ChooseActivity.class).getSimpleName()), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) AppConfig.INSTANCE.getTopActivity(), (CharSequence) String.valueOf(Reflection.getOrCreateKotlinClass(GalleryActivity.class).getSimpleName()), false, 2, (Object) null)) {
                return;
            }
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onCallBackResult$lambda$30;
                    onCallBackResult$lambda$30 = ChooseActivity.onCallBackResult$lambda$30(ChooseActivity.this);
                    return onCallBackResult$lambda$30;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onCallBackResult$lambda$31;
                    onCallBackResult$lambda$31 = ChooseActivity.onCallBackResult$lambda$31(ChooseActivity.this);
                    return onCallBackResult$lambda$31;
                }
            }, 0L, 2, null);
            this.bTemp = (byte) 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$25(final Ref.ObjectRef objectRef, final ChooseActivity chooseActivity, SendCore.CallbackBuilder getLedType) {
        Intrinsics.checkNotNullParameter(getLedType, "$this$getLedType");
        getLedType.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCallBackResult$lambda$25$lambda$24;
                onCallBackResult$lambda$25$lambda$24 = ChooseActivity.onCallBackResult$lambda$25$lambda$24(Ref.ObjectRef.this, chooseActivity, (byte[]) obj);
                return onCallBackResult$lambda$25$lambda$24;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$25$lambda$24(final Ref.ObjectRef objectRef, final ChooseActivity chooseActivity, byte[] result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.length >= 9) {
            byte b = result[4];
            if (b == -127) {
                AppConfig.INSTANCE.setLedType(2);
                objectRef.element = "32*32";
            } else if (b == Byte.MIN_VALUE) {
                AppConfig.INSTANCE.setLedType(0);
                objectRef.element = "64*64";
            } else if (b == -126) {
                AppConfig.INSTANCE.setLedType(4);
                objectRef.element = "16*32";
            } else if (b == -125) {
                AppConfig.INSTANCE.setLedType(3);
                objectRef.element = "16*64";
            } else if (b == -124) {
                AppConfig.INSTANCE.setLedType(1);
                objectRef.element = "16*96";
            } else if (b == -123) {
                AppConfig.INSTANCE.setLedType(5);
            } else if (b == -122) {
                AppConfig.INSTANCE.setLedType(6);
                AppConfig.INSTANCE.setLedHasWifi(false);
                AppConfig.INSTANCE.setLedTextSize(32);
                objectRef.element = "32*128";
            } else if (b == -121) {
                AppConfig.INSTANCE.setLedType(7);
                objectRef.element = "16*144";
            } else if (b == -120) {
                AppConfig.INSTANCE.setLedType(8);
                objectRef.element = "16*192";
            } else if (b == -119) {
                AppConfig.INSTANCE.setLedType(9);
                AppConfig.INSTANCE.setLedTextSize(24);
                objectRef.element = "24*48";
            } else if (b == -118) {
                AppConfig.INSTANCE.setLedType(10);
                AppConfig.INSTANCE.setLedTextSize(32);
                objectRef.element = "32*64";
            } else if (b == -117) {
                AppConfig.INSTANCE.setLedType(11);
                AppConfig.INSTANCE.setLedTextSize(32);
                objectRef.element = "32*96";
            } else if (b == -116) {
                AppConfig.INSTANCE.setLedType(12);
                AppConfig.INSTANCE.setLedTextSize(32);
                objectRef.element = "32*128";
            } else if (b == -115) {
                AppConfig.INSTANCE.setLedType(13);
                AppConfig.INSTANCE.setLedTextSize(32);
                objectRef.element = "32*96";
            } else if (b == -114) {
                AppConfig.INSTANCE.setLedType(14);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -113) {
                AppConfig.INSTANCE.setLedType(15);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -112) {
                AppConfig.INSTANCE.setLedType(16);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -111) {
                AppConfig.INSTANCE.setLedType(17);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -110) {
                AppConfig.INSTANCE.setLedType(18);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else if (b == -109) {
                AppConfig.INSTANCE.setLedType(19);
                AppConfig.INSTANCE.setLedTextSize(32);
            } else {
                AppConfig.INSTANCE.setLedType(0);
                objectRef.element = "64*64";
            }
            OtaUpData.INSTANCE.checkIsNeedOta("", "", "", chooseActivity, new Function3() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Unit onCallBackResult$lambda$25$lambda$24$lambda$23;
                    onCallBackResult$lambda$25$lambda$24$lambda$23 = ChooseActivity.onCallBackResult$lambda$25$lambda$24$lambda$23(ChooseActivity.this, objectRef, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (String) obj3);
                    return onCallBackResult$lambda$25$lambda$24$lambda$23;
                }
            });
        } else if (result.length == 1 && result[0] == Byte.MAX_VALUE) {
            AppConfig.INSTANCE.getConnectType();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onCallBackResult$lambda$25$lambda$24$lambda$23(ChooseActivity chooseActivity, Ref.ObjectRef objectRef, int i, int i2, String it3) {
        Intrinsics.checkNotNullParameter(it3, "it3");
        AppConfig.INSTANCE.setMcu(i);
        if (i == 2) {
            AppConfig.INSTANCE.setLedHasWifi(true);
        } else {
            AppConfig.INSTANCE.setLedHasWifi(false);
        }
        CloudManager.INSTANCE.uploadappslog(chooseActivity, "", String.valueOf(AppConfig.INSTANCE.getMcu()), it3, (String) objectRef.element);
        GridLineView.INSTANCE.setLedType(AppConfig.INSTANCE.getLedType());
        ChannelIndex.INSTANCE.initChannelData();
        chooseActivity.onLedWifiListener(AppConfig.INSTANCE.getLedHasWifi());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$26(ChooseActivity chooseActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) chooseActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCallBackResult$lambda$27(ChooseActivity chooseActivity, DialogInterface dialogInterface, int i) {
        chooseActivity.mSendSuc = -1;
        dialogInterface.dismiss();
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
        chooseActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$28(ChooseActivity chooseActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) chooseActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$29(ChooseActivity chooseActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) chooseActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$30(ChooseActivity chooseActivity) {
        ChooseActivity chooseActivity2 = chooseActivity;
        String string = chooseActivity.getString(R.string.msg_send_suc);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog((Activity) chooseActivity2, true, string, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$31(ChooseActivity chooseActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) chooseActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCallBackResult$lambda$32(ChooseActivity chooseActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) chooseActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onLedWifiListener$lambda$33(ChooseActivity chooseActivity) {
        chooseActivity.initAdapter();
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.ui.dialog.ConnectDialog.Companion.onCallBackLedHasWifiListener
    public void onLedWifiListener(boolean isHas) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onLedWifiListener$lambda$33;
                onLedWifiListener$lambda$33 = ChooseActivity.onLedWifiListener$lambda$33(ChooseActivity.this);
                return onLedWifiListener$lambda$33;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPwdDialog() {
        if (this.connectPwdDialog == null) {
            ConnectPwdDialog connectPwdDialog = new ConnectPwdDialog(this);
            this.connectPwdDialog = connectPwdDialog;
            Intrinsics.checkNotNull(connectPwdDialog);
            connectPwdDialog.setCanceledOnTouchOutside(false);
            ConnectPwdDialog connectPwdDialog2 = this.connectPwdDialog;
            Intrinsics.checkNotNull(connectPwdDialog2);
            connectPwdDialog2.setCancelable(false);
        }
        ConnectPwdDialog connectPwdDialog3 = this.connectPwdDialog;
        Intrinsics.checkNotNull(connectPwdDialog3);
        connectPwdDialog3.show();
        ConnectPwdDialog connectPwdDialog4 = this.connectPwdDialog;
        Intrinsics.checkNotNull(connectPwdDialog4);
        connectPwdDialog4.setClickListener(new ConnectPwdDialog.ClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$showPwdDialog$1
            @Override // com.wifiled.ipixels.ui.dialog.ConnectPwdDialog.ClickListener
            public void onCancel() {
                ConnectPwdDialog connectPwdDialog5;
                BleManager.INSTANCE.get().disconnect();
                connectPwdDialog5 = ChooseActivity.this.connectPwdDialog;
                Intrinsics.checkNotNull(connectPwdDialog5);
                connectPwdDialog5.cancel();
            }

            @Override // com.wifiled.ipixels.ui.dialog.ConnectPwdDialog.ClickListener
            public void onOk(String pwd) {
                ChooseActivity.this.mCurPwd = pwd;
                if (pwd != null) {
                    ChooseActivity.this.verifyPwd(pwd);
                }
            }
        });
        ConnectPwdDialog connectPwdDialog5 = this.connectPwdDialog;
        Intrinsics.checkNotNull(connectPwdDialog5);
        connectPwdDialog5.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                ChooseActivity.showPwdDialog$lambda$34(ChooseActivity.this, dialogInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPwdDialog$lambda$34(ChooseActivity chooseActivity, DialogInterface dialogInterface) {
        if (chooseActivity.isInputPwd) {
            return;
        }
        BleManager.INSTANCE.get().disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void verifyPwd(String pwd) {
        SendCore.INSTANCE.verifyPwd(pwd, new ChooseActivity$verifyPwd$1(this));
    }

    private final void upResourceData() {
        if (SPUtils.contains(this.mActivity, "ResourceIDList") && SPUtils.get(this.mActivity, "ResourceIDList", new ArrayList()) != null) {
            Object obj = SPUtils.get(this.mActivity, "ResourceIDList", new ArrayList());
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            this.mSendResourceIDList = (List) obj;
        }
        if (this.mSendResourceIDList.isEmpty()) {
            return;
        }
        String joinToString$default = CollectionsKt.joinToString$default(this.mSendResourceIDList, ",", null, null, 0, null, null, 62, null);
        LogUtils.vTag("ruis", "upResourceData " + joinToString$default);
        String displayCountry = new Locale("zh", Locale.getDefault().getCountry()).getDisplayCountry();
        LogUtils.vTag("ruis", "chinaName---" + displayCountry);
        ApiService request = NetWorkManager.INSTANCE.getRequest();
        String uniqueDeviceId = DeviceUtils.getUniqueDeviceId();
        Intrinsics.checkNotNullExpressionValue(uniqueDeviceId, "getUniqueDeviceId(...)");
        Intrinsics.checkNotNull(displayCountry);
        ApiService.resourceDownloadStatistics$default(request, null, uniqueDeviceId, displayCountry, joinToString$default, 1, null).enqueue(new Callback<CloudRes>() { // from class: com.wifiled.ipixels.ui.ChooseActivity$upResourceData$1
            @Override // retrofit2.Callback
            public void onFailure(Call<CloudRes> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<CloudRes> call, Response<CloudRes> response) {
                List list;
                List list2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                list = ChooseActivity.this.mSendResourceIDList;
                list.clear();
                Activity activity = ChooseActivity.this.mActivity;
                list2 = ChooseActivity.this.mSendResourceIDList;
                SPUtils.put(activity, "ResourceIDList", list2);
            }
        });
    }

    @Override // com.wifiled.baselib.base.XBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Intrinsics.checkNotNullParameter(perms, "perms");
        LogUtils.vTag("ruis", "onPermissionsGranted");
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Intrinsics.checkNotNullParameter(perms, "perms");
        LogUtils.vTag("ruis", "onPermissionsDenied");
        if (requestCode == this.RC_CAMERA_AND_LOCATION && EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            builder.setTitle(getString(R.string.gps_tip));
            builder.setMessage(getString(R.string.open_bt_relative));
            builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda12
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ChooseActivity.onPermissionsDenied$lambda$35(ChooseActivity.this, dialogInterface, i);
                }
            });
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.ChooseActivity$$ExternalSyntheticLambda13
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPermissionsDenied$lambda$35(ChooseActivity chooseActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(chooseActivity);
    }
}
