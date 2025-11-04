package com.wifiled.ipixels.ui.channel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.baselib.widget.TextDrawable;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.ui.UpDataState;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: ChannelListActivity.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 b2\u00020\u0001:\u0001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010%\u001a\u00020&H\u0014J\b\u0010'\u001a\u00020&H\u0014J\b\u0010(\u001a\u00020&H\u0014J\u0010\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020+H\u0007J\b\u0010,\u001a\u00020&H\u0014J\b\u0010-\u001a\u00020&H\u0014J\u0010\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u000200H\u0007J\b\u0010=\u001a\u00020&H\u0002J\u0010\u0010>\u001a\u00020&2\u0006\u0010?\u001a\u00020@H\u0002J\u0010\u0010A\u001a\u00020&2\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010B\u001a\u00020&H\u0002J\b\u0010C\u001a\u00020&H\u0002J\b\u0010D\u001a\u00020&H\u0002J\u0006\u0010E\u001a\u00020&J\b\u0010F\u001a\u00020&H\u0002J\u0012\u0010G\u001a\u00020&2\b\b\u0002\u0010H\u001a\u000204H\u0002J\u0014\u0010I\u001a\u00020&2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020K0\u000bJ\u001a\u0010L\u001a\u00020&2\u0006\u0010M\u001a\u00020\u00152\b\b\u0002\u0010?\u001a\u00020@H\u0002J;\u0010N\u001a\u00020&2\u0006\u0010O\u001a\u00020@2\u0006\u0010P\u001a\u00020@2\u0017\u0010Q\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020&0R¢\u0006\u0002\bT2\b\b\u0002\u0010U\u001a\u00020KH\u0002J;\u0010V\u001a\u00020&2\u0006\u0010O\u001a\u00020@2\u0006\u0010P\u001a\u00020@2\u0017\u0010Q\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020&0R¢\u0006\u0002\bT2\b\b\u0002\u0010U\u001a\u00020KH\u0002J;\u0010\\\u001a\u00020&2\u0006\u0010]\u001a\u00020@2\u0006\u0010^\u001a\u00020@2\u0017\u0010Q\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020&0R¢\u0006\u0002\bT2\b\b\u0002\u0010U\u001a\u00020KH\u0002J;\u0010_\u001a\u00020&2\u0006\u0010]\u001a\u00020@2\u0006\u0010^\u001a\u00020@2\u0017\u0010Q\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020&0R¢\u0006\u0002\bT2\b\b\u0002\u0010U\u001a\u00020KH\u0002J\b\u0010`\u001a\u00020&H\u0002J\b\u0010a\u001a\u000202H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020\f0;X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010W\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[¨\u0006c"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/ChannelListActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "channelAdapter", "Lcom/wifiled/ipixels/ui/channel/ChannelAdapter;", "getChannelAdapter", "()Lcom/wifiled/ipixels/ui/channel/ChannelAdapter;", "setChannelAdapter", "(Lcom/wifiled/ipixels/ui/channel/ChannelAdapter;)V", "channelData", "", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "mSelectDeviceDialog", "Lcom/wifiled/ipixels/ui/dialog/SelecrDeviceDialog;", "delDialogAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "delPos", "", "layoutId", "mContext", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "iv_chl_sel_all", "Lcom/wifiled/baselib/widget/TextDrawable;", "iv_channel_send", "Landroid/widget/ImageView;", "iv_channel_del", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "rl_channel_list", "afterCreateView", "", "initView", "bindData", "onUpDataState", "instance", "Lcom/wifiled/ipixels/ui/UpDataState;", "onDestroy", "bindListener", "callBackIsSelAll", PlayerFinal.STATE, "Lcom/wifiled/ipixels/ui/channel/CSelState;", "arrSendItemInfo", "", "isCAllSendOver", "", "isCAllSendOver2", "iSelTotalCount", "iSelTotalCount2", "builder", "Lcom/wifiled/ipixels/ui/dialog/CustomDialog$Builder;", "sendQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "sendQueue2", "SendChannel", "sendDevice1", "byteArray", "", "sendDevice2", "sendData", "sendData2", "initChannelData", "initToolBar", "initChannelRecycle", "setAllItemSelState", "selectDone", "showDelDialog", "bSelArray", "", "showDialogByType", "type", "sendTextData", "arrText", "arrTotal", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "isBulkSend", "sendTextData2", "tProcess", "getTProcess", "()I", "setTProcess", "(I)V", "sendImagData", "serial", "data", "sendImagData2", "putChannelSP", "getChannelValue", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelListActivity extends BaseActivity {
    public static final int DELETE_ITEM = 1;
    public static final int FORMAT_HARDWARE = 0;
    private RecyclerView actionRecyclerView;
    public ChannelAdapter channelAdapter;
    private IosDialogStyleAdapter<Object> delDialogAdapter;
    private int iSelTotalCount;
    private int iSelTotalCount2;
    private boolean isCAllSendOver;
    private boolean isCAllSendOver2;
    private CustomImageView iv_back;
    private ImageView iv_channel_del;
    private ImageView iv_channel_send;
    private TextDrawable iv_chl_sel_all;
    private CustomImageView iv_right;
    private WeakReference<Context> mContext;
    private SelecrDeviceDialog mSelectDeviceDialog;
    private RecyclerView rl_channel_list;
    private int tProcess;
    private TextView tv_title;
    private List<ChannelListItem> channelData = new ArrayList();
    private int delPos = -1;
    private List<String> arrSendItemInfo = new ArrayList();
    private final CustomDialog.Builder builder = new CustomDialog.Builder(this);
    private LinkedBlockingQueue<ChannelListItem> sendQueue = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<ChannelListItem> sendQueue2 = new LinkedBlockingQueue<>();

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_channel;
    }

    public final ChannelAdapter getChannelAdapter() {
        ChannelAdapter channelAdapter = this.channelAdapter;
        if (channelAdapter != null) {
            return channelAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("channelAdapter");
        return null;
    }

    public final void setChannelAdapter(ChannelAdapter channelAdapter) {
        Intrinsics.checkNotNullParameter(channelAdapter, "<set-?>");
        this.channelAdapter = channelAdapter;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void afterCreateView() {
        super.afterCreateView();
        getWindow().setFlags(128, 128);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.iv_chl_sel_all);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_chl_sel_all = (TextDrawable) findViewById4;
        View findViewById5 = findViewById(R.id.iv_channel_send);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.iv_channel_send = (ImageView) findViewById5;
        View findViewById6 = findViewById(R.id.iv_channel_del);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.iv_channel_del = (ImageView) findViewById6;
        View findViewById7 = findViewById(R.id.rl_channel_list);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.rl_channel_list = (RecyclerView) findViewById7;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("ChannelListActivity  bindData");
        this.mContext = new WeakReference<>(this);
        initToolBar();
        initChannelData();
        initChannelRecycle();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onUpDataState(UpDataState instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.file("ChannelListActivity  onDestroy");
        EventBus.getDefault().unregister(this);
        getChannelAdapter().stopAnim(-1);
        AppConfig.INSTANCE.setCancel(false);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        TextDrawable textDrawable = this.iv_chl_sel_all;
        ImageView imageView = null;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda45
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelListActivity.bindListener$lambda$0(ChannelListActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        TextDrawable textDrawable2 = this.iv_chl_sel_all;
        if (textDrawable2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable2 = null;
        }
        companion.attachViewOnTouchListener(textDrawable2);
        ImageView imageView2 = this.iv_channel_del;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_del");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelListActivity.bindListener$lambda$3(ChannelListActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ImageView imageView3 = this.iv_channel_del;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_del");
            imageView3 = null;
        }
        companion2.attachViewOnTouchListener(imageView3);
        Log.v("ruis", " channelData.isNotEmpty()----" + (!this.channelData.isEmpty()));
        ImageView imageView4 = this.iv_channel_send;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_send");
            imageView4 = null;
        }
        imageView4.setEnabled(!this.channelData.isEmpty());
        ImageView imageView5 = this.iv_channel_send;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_send");
            imageView5 = null;
        }
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda47
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelListActivity.bindListener$lambda$5(ChannelListActivity.this, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        ImageView imageView6 = this.iv_channel_send;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_send");
        } else {
            imageView = imageView6;
        }
        companion3.attachViewOnTouchListener(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(ChannelListActivity channelListActivity, View view) {
        LogUtils.file("ChannelListActivity  点击了全选");
        channelListActivity.getChannelAdapter().setSelAll(!channelListActivity.getChannelAdapter().getIsSelAll());
        view.setSelected(channelListActivity.getChannelAdapter().getIsSelAll());
        ImageView imageView = channelListActivity.iv_channel_send;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_send");
            imageView = null;
        }
        imageView.setEnabled(channelListActivity.getChannelAdapter().getIsSelAll());
        channelListActivity.getChannelAdapter().setSelIndex(-1);
        channelListActivity.setAllItemSelState(channelListActivity.getChannelAdapter().getIsSelAll());
        channelListActivity.getChannelAdapter().stopAnim(-1);
        channelListActivity.getChannelAdapter().notifyItemRangeChanged(0, channelListActivity.getChannelAdapter().getItemCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(final ChannelListActivity channelListActivity, View view) {
        LogUtils.file("ChannelListActivity  点击了删除");
        if (TimeHelper.INSTANCE.allowSend(500)) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<Integer, ChannelListItem> entry : ChannelIndex.INSTANCE.mapSaveChannel().entrySet()) {
                ChannelListItem value = entry.getValue();
                if (value instanceof ChannelListItem.TextEmojView) {
                    if (((ChannelListItem.TextEmojView) value).isSel()) {
                        arrayList.add(Byte.valueOf((byte) entry.getKey().intValue()));
                    }
                } else if (value instanceof ChannelListItem.ImagView) {
                    if (((ChannelListItem.ImagView) value).isSel()) {
                        arrayList.add(Byte.valueOf((byte) entry.getKey().intValue()));
                    }
                } else if (value instanceof ChannelListItem.GiftView) {
                    if (((ChannelListItem.GiftView) value).isSel()) {
                        arrayList.add(Byte.valueOf((byte) entry.getKey().intValue()));
                    }
                } else if (value instanceof ChannelListItem.SubzoneView) {
                    if (((ChannelListItem.SubzoneView) value).isSel()) {
                        arrayList.add(Byte.valueOf((byte) entry.getKey().intValue()));
                    }
                } else if ((value instanceof ChannelListItem.EyesView) && ((ChannelListItem.EyesView) value).isSel()) {
                    arrayList.add(Byte.valueOf((byte) entry.getKey().intValue()));
                }
            }
            if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
                ArrayList arrayList2 = arrayList;
                if (!arrayList2.isEmpty()) {
                    channelListActivity.showDialogByType(1, CollectionsKt.toByteArray(arrayList2));
                    return;
                } else {
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit bindListener$lambda$3$lambda$2;
                            bindListener$lambda$3$lambda$2 = ChannelListActivity.bindListener$lambda$3$lambda$2(ChannelListActivity.this);
                            return bindListener$lambda$3$lambda$2;
                        }
                    });
                    return;
                }
            }
            if (arrayList.isEmpty()) {
                showDialogByType$default(channelListActivity, 0, null, 2, null);
            } else {
                channelListActivity.showDelDialog(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$3$lambda$2(ChannelListActivity channelListActivity) {
        channelListActivity.toast(channelListActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$5(final ChannelListActivity channelListActivity, View view) {
        Log.v("ruis", "iv_channel_send.setOnClickListener ");
        LogUtils.file("ChannelListActivity  点击了发送");
        if (TimeHelper.INSTANCE.allowSend(500)) {
            if (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1) {
                channelListActivity.SendChannel();
            } else {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit bindListener$lambda$5$lambda$4;
                        bindListener$lambda$5$lambda$4 = ChannelListActivity.bindListener$lambda$5$lambda$4(ChannelListActivity.this);
                        return bindListener$lambda$5$lambda$4;
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$5$lambda$4(ChannelListActivity channelListActivity) {
        channelListActivity.toast(channelListActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void callBackIsSelAll(final CSelState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        com.wifiled.baselib.utils.LogUtils.logi(this.TAG + ">>>[callBackIsSelAll]: " + state.getBSelAll(), new Object[0]);
        getChannelAdapter().setSelAll(state.getBSelAll());
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callBackIsSelAll$lambda$6;
                callBackIsSelAll$lambda$6 = ChannelListActivity.callBackIsSelAll$lambda$6(ChannelListActivity.this, state);
                return callBackIsSelAll$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callBackIsSelAll$lambda$6(ChannelListActivity channelListActivity, CSelState cSelState) {
        TextDrawable textDrawable = channelListActivity.iv_chl_sel_all;
        ImageView imageView = null;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setSelected(channelListActivity.getChannelAdapter().getIsSelAll());
        ImageView imageView2 = channelListActivity.iv_channel_send;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_channel_send");
        } else {
            imageView = imageView2;
        }
        imageView.setEnabled(cSelState.getBHasSel());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [T, java.util.List] */
    private final void SendChannel() {
        String str;
        String bleName;
        LogUtils.file("ChannelListActivity  AutoSendChannel");
        this.arrSendItemInfo.clear();
        List<String> list = this.arrSendItemInfo;
        String string = getString(R.string.channel_list_sending_NO);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        list.add(string);
        this.arrSendItemInfo.add("0");
        this.arrSendItemInfo.add(":");
        this.arrSendItemInfo.add("0");
        this.arrSendItemInfo.add("%");
        this.iSelTotalCount = 0;
        this.iSelTotalCount2 = 0;
        this.sendQueue.clear();
        this.sendQueue2.clear();
        this.builder.setTitle(getString(R.string.channel_list_sending_title));
        this.builder.setMessage(getString(R.string.channel_list_sending_NO));
        this.builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda50
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        this.builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda61
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ChannelListActivity.SendChannel$lambda$8(dialogInterface, i);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        ((List) objectRef.element).add((byte) 0);
        ((List) objectRef.element).add((byte) 0);
        ((List) objectRef.element).add((byte) 8);
        ((List) objectRef.element).add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
        ((List) objectRef.element).add((byte) 0);
        ((List) objectRef.element).add((byte) 0);
        Iterator<Map.Entry<Integer, ChannelListItem>> it = ChannelIndex.INSTANCE.mapSaveChannel().entrySet().iterator();
        while (it.hasNext()) {
            ChannelListItem value = it.next().getValue();
            if (value instanceof ChannelListItem.TextEmojView) {
                ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) value;
                if (textEmojView.isSel()) {
                    this.iSelTotalCount++;
                    this.iSelTotalCount2++;
                    ((List) objectRef.element).add(Byte.valueOf(Byte.parseByte(textEmojView.getSerialNum())));
                    this.sendQueue.put(value);
                    this.sendQueue2.put(value);
                }
            } else if (value instanceof ChannelListItem.ImagView) {
                ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) value;
                if (imagView.isSel()) {
                    this.iSelTotalCount++;
                    this.iSelTotalCount2++;
                    ((List) objectRef.element).add(Byte.valueOf(Byte.parseByte(imagView.getSerialNum())));
                    this.sendQueue.put(value);
                    this.sendQueue2.put(value);
                }
            } else if (value instanceof ChannelListItem.GiftView) {
                ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) value;
                if (giftView.isSel()) {
                    this.iSelTotalCount++;
                    this.iSelTotalCount2++;
                    ((List) objectRef.element).add(Byte.valueOf(Byte.parseByte(giftView.getSerialNum())));
                    this.sendQueue.put(value);
                    this.sendQueue2.put(value);
                }
            } else if (value instanceof ChannelListItem.SubzoneView) {
                ChannelListItem.SubzoneView subzoneView = (ChannelListItem.SubzoneView) value;
                if (subzoneView.isSel()) {
                    this.iSelTotalCount++;
                    this.iSelTotalCount2++;
                    ((List) objectRef.element).add(Byte.valueOf(Byte.parseByte(subzoneView.getSerialNum())));
                    this.sendQueue.put(value);
                    this.sendQueue2.put(value);
                }
            } else {
                if (!(value instanceof ChannelListItem.EyesView)) {
                    throw new NoWhenBranchMatchedException();
                }
                ChannelListItem.EyesView eyesView = (ChannelListItem.EyesView) value;
                if (eyesView.isSel()) {
                    this.iSelTotalCount++;
                    this.iSelTotalCount2++;
                    ((List) objectRef.element).add(Byte.valueOf(Byte.parseByte(eyesView.getSerialNum())));
                    this.sendQueue.put(value);
                    this.sendQueue2.put(value);
                }
            }
        }
        LogUtils.vTag("ruis", "iSelTotalCount --- " + this.iSelTotalCount);
        ((List) objectRef.element).set(4, Byte.valueOf((byte) this.iSelTotalCount));
        ((List) objectRef.element).set(0, Byte.valueOf((byte) ((List) objectRef.element).size()));
        if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            if (this.mSelectDeviceDialog == null) {
                ChannelListActivity channelListActivity = this;
                BleDevice connectedDevice = BleManager.INSTANCE.get().getConnectedDevice();
                String str2 = "";
                if (connectedDevice == null || (str = connectedDevice.getBleName()) == null) {
                    str = "";
                }
                BleDevice connectedDevice2 = BleManager2.INSTANCE.get().getConnectedDevice();
                if (connectedDevice2 != null && (bleName = connectedDevice2.getBleName()) != null) {
                    str2 = bleName;
                }
                SelecrDeviceDialog selecrDeviceDialog = new SelecrDeviceDialog(channelListActivity, str, str2);
                this.mSelectDeviceDialog = selecrDeviceDialog;
                Intrinsics.checkNotNull(selecrDeviceDialog);
                selecrDeviceDialog.setClickListener(new SelecrDeviceDialog.ClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$SendChannel$4
                    @Override // com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog.ClickListener
                    public void onCancel() {
                        SelecrDeviceDialog selecrDeviceDialog2;
                        selecrDeviceDialog2 = ChannelListActivity.this.mSelectDeviceDialog;
                        Intrinsics.checkNotNull(selecrDeviceDialog2);
                        selecrDeviceDialog2.cancel();
                    }

                    @Override // com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog.ClickListener
                    public void onOk(int num) {
                        SelecrDeviceDialog selecrDeviceDialog2;
                        CustomDialog.Builder builder;
                        CustomDialog.Builder builder2;
                        selecrDeviceDialog2 = ChannelListActivity.this.mSelectDeviceDialog;
                        Intrinsics.checkNotNull(selecrDeviceDialog2);
                        selecrDeviceDialog2.cancel();
                        builder = ChannelListActivity.this.builder;
                        builder.create().show();
                        builder2 = ChannelListActivity.this.builder;
                        builder2.setCancelBtnShow(true);
                        if (num == 1) {
                            ChannelListActivity.this.sendDevice1(CollectionsKt.toByteArray(objectRef.element));
                        } else {
                            if (num != 2) {
                                return;
                            }
                            ChannelListActivity.this.sendDevice2(CollectionsKt.toByteArray(objectRef.element));
                        }
                    }
                });
            }
            SelecrDeviceDialog selecrDeviceDialog2 = this.mSelectDeviceDialog;
            if (selecrDeviceDialog2 != null) {
                selecrDeviceDialog2.show();
                return;
            }
            return;
        }
        this.builder.create().show();
        this.builder.setCancelBtnShow(true);
        if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
            sendDevice1(CollectionsKt.toByteArray((Collection) objectRef.element));
        } else if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            sendDevice2(CollectionsKt.toByteArray((Collection) objectRef.element));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SendChannel$lambda$8(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        AppConfig.INSTANCE.setCancel(true);
        if (AppConfig.INSTANCE.getBHasSwitchedBg()) {
            AppConfig.INSTANCE.setBHasSwitchedBg(false);
            AppConfig.INSTANCE.setCancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendDevice1(final byte[] byteArray) {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() {
                ChannelListActivity.sendDevice1$lambda$10(byteArray, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendDevice1$lambda$10(byte[] bArr, ChannelListActivity channelListActivity) {
        if (AppConfig.INSTANCE.getMcu() > 4) {
            SendCore.INSTANCE.sendCompat(bArr, new ChannelListActivity$sendDevice1$1$1(channelListActivity));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendDevice2(final byte[] byteArray) {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda77
            @Override // java.lang.Runnable
            public final void run() {
                ChannelListActivity.sendDevice2$lambda$11(byteArray, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendDevice2$lambda$11(byte[] bArr, ChannelListActivity channelListActivity) {
        if (AppConfig.INSTANCE.getMcu() > 4) {
            SendCore.INSTANCE.sendCompat2(bArr, new ChannelListActivity$sendDevice2$1$1(channelListActivity));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    public final void sendData() {
        byte[] rightData;
        LogUtils.file("ChannelListActivity  sendData");
        LogUtils.vTag("ruis", "ChannelListActivity  sendData1");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.sendQueue.poll();
        ChannelListItem channelListItem = (ChannelListItem) objectRef.element;
        if (channelListItem instanceof ChannelListItem.TextEmojView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.TextEmojView");
            sendTextData(ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.TextEmojView) objectRef.element).getSerialNum())), CollectionsKt.toByteArray(ArraysKt.toMutableList(((ChannelListItem.TextEmojView) objectRef.element).getArrTextData())), new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda78
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData$lambda$20;
                    sendData$lambda$20 = ChannelListActivity.sendData$lambda$20(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData$lambda$20;
                }
            }, Byte.parseByte(((ChannelListItem.TextEmojView) objectRef.element).getSerialNum()));
            return;
        }
        if (channelListItem instanceof ChannelListItem.ImagView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.ImagView");
            sendImagData(ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.ImagView) objectRef.element).getSerialNum())), CollectionsKt.toByteArray(ArraysKt.toMutableList(((ChannelListItem.ImagView) objectRef.element).getArrImagData())), new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda79
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData$lambda$29;
                    sendData$lambda$29 = ChannelListActivity.sendData$lambda$29(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData$lambda$29;
                }
            }, Byte.parseByte(((ChannelListItem.ImagView) objectRef.element).getSerialNum()));
            return;
        }
        if (channelListItem instanceof ChannelListItem.GiftView) {
            LogUtils.vTag("ruis", " ChannelListItem.GiftView1");
            LogUtils.file("ChannelListActivity  ChannelListItem.GiftView");
            byte[] plus = ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.GiftView) objectRef.element).getSerialNum()));
            byte[] arrGifData = ((ChannelListItem.GiftView) objectRef.element).getArrGifData();
            if (arrGifData != null) {
                SendCore.INSTANCE.sendChannelGifData1(plus, arrGifData, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda80
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit sendData$lambda$40$lambda$39;
                        sendData$lambda$40$lambda$39 = ChannelListActivity.sendData$lambda$40$lambda$39(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                        return sendData$lambda$40$lambda$39;
                    }
                }, Byte.parseByte(((ChannelListItem.GiftView) objectRef.element).getSerialNum()));
                return;
            }
            return;
        }
        if (channelListItem instanceof ChannelListItem.SubzoneView) {
            LogUtils.vTag("ruis", " value.serialNum.toByte()=====" + ((int) Byte.parseByte(((ChannelListItem.SubzoneView) objectRef.element).getSerialNum())));
            SendCore.sendTemplateData1(true, ((ChannelListItem.SubzoneView) objectRef.element).getSubzoneData(), new ChannelListActivity$sendData$4(this, objectRef), Byte.parseByte(((ChannelListItem.SubzoneView) objectRef.element).getSerialNum()));
        } else if (channelListItem instanceof ChannelListItem.EyesView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.EyesView");
            byte[] plus2 = ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.EyesView) objectRef.element).getSerialNum()));
            byte[] leftData = ((ChannelListItem.EyesView) objectRef.element).getLeftData();
            if (leftData == null || (rightData = ((ChannelListItem.EyesView) objectRef.element).getRightData()) == null) {
                return;
            }
            SendCore.sendEyeData$default(SendCore.INSTANCE, false, plus2, leftData, rightData, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda81
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData$lambda$51$lambda$50$lambda$49;
                    sendData$lambda$51$lambda$50$lambda$49 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData$lambda$51$lambda$50$lambda$49;
                }
            }, Byte.parseByte(((ChannelListItem.EyesView) objectRef.element).getSerialNum()), 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$20(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendTextData) {
        Intrinsics.checkNotNullParameter(sendTextData, "$this$sendTextData");
        sendTextData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda87
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$20$lambda$12;
                sendData$lambda$20$lambda$12 = ChannelListActivity.sendData$lambda$20$lambda$12();
                return sendData$lambda$20$lambda$12;
            }
        });
        sendTextData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda88
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$20$lambda$14;
                sendData$lambda$20$lambda$14 = ChannelListActivity.sendData$lambda$20$lambda$14(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData$lambda$20$lambda$14;
            }
        });
        sendTextData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda89
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$20$lambda$16;
                sendData$lambda$20$lambda$16 = ChannelListActivity.sendData$lambda$20$lambda$16(ChannelListActivity.this);
                return sendData$lambda$20$lambda$16;
            }
        });
        sendTextData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda90
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$20$lambda$19;
                sendData$lambda$20$lambda$19 = ChannelListActivity.sendData$lambda$20$lambda$19(ChannelListActivity.this, (byte[]) obj);
                return sendData$lambda$20$lambda$19;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$20$lambda$12() {
        LogUtils.vTag("ruis", "sendTextData --- onStart");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$20$lambda$14(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.TextEmojView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$20$lambda$14$lambda$13;
                sendData$lambda$20$lambda$14$lambda$13 = ChannelListActivity.sendData$lambda$20$lambda$14$lambda$13(ChannelListActivity.this, replace$default);
                return sendData$lambda$20$lambda$14$lambda$13;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$20$lambda$14$lambda$13(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$20$lambda$16(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount - 1;
        channelListActivity.iSelTotalCount = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver = z;
        LogUtils.vTag("ruis", "sendTextData isCAllSendOver---iSelTotalCount=" + i + "     isCAllSendOver===" + z);
        if (channelListActivity.isCAllSendOver) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$20$lambda$16$lambda$15;
                    sendData$lambda$20$lambda$16$lambda$15 = ChannelListActivity.sendData$lambda$20$lambda$16$lambda$15(ChannelListActivity.this);
                    return sendData$lambda$20$lambda$16$lambda$15;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$20$lambda$16$lambda$15(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData$lambda$20$lambda$19(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? string = channelListActivity.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        objectRef.element = string;
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$20$lambda$19$lambda$17;
                    sendData$lambda$20$lambda$19$lambda$17 = ChannelListActivity.sendData$lambda$20$lambda$19$lambda$17(ChannelListActivity.this, objectRef);
                    return sendData$lambda$20$lambda$19$lambda$17;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            ?? string2 = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            objectRef.element = string2;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$20$lambda$19$lambda$18;
                    sendData$lambda$20$lambda$19$lambda$18 = ChannelListActivity.sendData$lambda$20$lambda$19$lambda$18(ChannelListActivity.this, objectRef);
                    return sendData$lambda$20$lambda$19$lambda$18;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$20$lambda$19$lambda$17(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$20$lambda$19$lambda$18(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$29(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendImagData) {
        Intrinsics.checkNotNullParameter(sendImagData, "$this$sendImagData");
        sendImagData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$29$lambda$21;
                sendData$lambda$29$lambda$21 = ChannelListActivity.sendData$lambda$29$lambda$21();
                return sendData$lambda$29$lambda$21;
            }
        });
        sendImagData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$29$lambda$23;
                sendData$lambda$29$lambda$23 = ChannelListActivity.sendData$lambda$29$lambda$23(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData$lambda$29$lambda$23;
            }
        });
        sendImagData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$29$lambda$25;
                sendData$lambda$29$lambda$25 = ChannelListActivity.sendData$lambda$29$lambda$25(ChannelListActivity.this);
                return sendData$lambda$29$lambda$25;
            }
        });
        sendImagData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$29$lambda$28;
                sendData$lambda$29$lambda$28 = ChannelListActivity.sendData$lambda$29$lambda$28(ChannelListActivity.this, (byte[]) obj);
                return sendData$lambda$29$lambda$28;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$29$lambda$21() {
        LogUtils.vTag("ruis", "sendImagData --stary");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$29$lambda$23(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.ImagView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda82
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$29$lambda$23$lambda$22;
                sendData$lambda$29$lambda$23$lambda$22 = ChannelListActivity.sendData$lambda$29$lambda$23$lambda$22(ChannelListActivity.this, replace$default);
                return sendData$lambda$29$lambda$23$lambda$22;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$29$lambda$23$lambda$22(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$29$lambda$25(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount - 1;
        channelListActivity.iSelTotalCount = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver = z;
        LogUtils.vTag("ruis", "sendImagData isCAllSendOver------iSelTotalCount=" + i + "   isCAllSendOver" + z);
        if (channelListActivity.isCAllSendOver) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$29$lambda$25$lambda$24;
                    sendData$lambda$29$lambda$25$lambda$24 = ChannelListActivity.sendData$lambda$29$lambda$25$lambda$24(ChannelListActivity.this);
                    return sendData$lambda$29$lambda$25$lambda$24;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$29$lambda$25$lambda$24(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData$lambda$29$lambda$28(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? string = channelListActivity.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        objectRef.element = string;
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$29$lambda$28$lambda$26;
                    sendData$lambda$29$lambda$28$lambda$26 = ChannelListActivity.sendData$lambda$29$lambda$28$lambda$26(ChannelListActivity.this, objectRef);
                    return sendData$lambda$29$lambda$28$lambda$26;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            ?? string2 = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            objectRef.element = string2;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$29$lambda$28$lambda$27;
                    sendData$lambda$29$lambda$28$lambda$27 = ChannelListActivity.sendData$lambda$29$lambda$28$lambda$27(ChannelListActivity.this, objectRef);
                    return sendData$lambda$29$lambda$28$lambda$27;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$29$lambda$28$lambda$26(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$29$lambda$28$lambda$27(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendChannelGifData1) {
        Intrinsics.checkNotNullParameter(sendChannelGifData1, "$this$sendChannelGifData1");
        sendChannelGifData1.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda101
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$40$lambda$39$lambda$30;
                sendData$lambda$40$lambda$39$lambda$30 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$30();
                return sendData$lambda$40$lambda$39$lambda$30;
            }
        });
        sendChannelGifData1.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda102
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$40$lambda$39$lambda$32;
                sendData$lambda$40$lambda$39$lambda$32 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$32(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData$lambda$40$lambda$39$lambda$32;
            }
        });
        sendChannelGifData1.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda103
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$40$lambda$39$lambda$34;
                sendData$lambda$40$lambda$39$lambda$34 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$34(ChannelListActivity.this);
                return sendData$lambda$40$lambda$39$lambda$34;
            }
        });
        sendChannelGifData1.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda104
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$40$lambda$39$lambda$36;
                sendData$lambda$40$lambda$39$lambda$36 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$36(ChannelListActivity.this, ((Integer) obj).intValue());
                return sendData$lambda$40$lambda$39$lambda$36;
            }
        });
        sendChannelGifData1.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$40$lambda$39$lambda$38;
                sendData$lambda$40$lambda$39$lambda$38 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$38(ChannelListActivity.this, (byte[]) obj);
                return sendData$lambda$40$lambda$39$lambda$38;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$30() {
        LogUtils.vTag("ruis", "sendChannelGifData1 --stary");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$40$lambda$39$lambda$32(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.GiftView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda72
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$40$lambda$39$lambda$32$lambda$31;
                sendData$lambda$40$lambda$39$lambda$32$lambda$31 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$32$lambda$31(ChannelListActivity.this, replace$default);
                return sendData$lambda$40$lambda$39$lambda$32$lambda$31;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$32$lambda$31(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$34(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount - 1;
        channelListActivity.iSelTotalCount = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver = z;
        LogUtils.vTag("ruis", "sendChannelGifData---iSelTotalCount=" + i + "   --isCAllSendOver" + z);
        if (channelListActivity.isCAllSendOver) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda83
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$40$lambda$39$lambda$34$lambda$33;
                    sendData$lambda$40$lambda$39$lambda$34$lambda$33 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$34$lambda$33(ChannelListActivity.this);
                    return sendData$lambda$40$lambda$39$lambda$34$lambda$33;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$34$lambda$33(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$36(ChannelListActivity channelListActivity, int i) {
        final String str = channelListActivity.getString(R.string.msg_send_fail) + i;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$40$lambda$39$lambda$36$lambda$35;
                sendData$lambda$40$lambda$39$lambda$36$lambda$35 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$36$lambda$35(str);
                return sendData$lambda$40$lambda$39$lambda$36$lambda$35;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$40$lambda$39$lambda$36$lambda$35(String str) {
        ToastUtil.show(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData$lambda$40$lambda$39$lambda$38(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? string = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            objectRef.element = string;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda70
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$40$lambda$39$lambda$38$lambda$37;
                    sendData$lambda$40$lambda$39$lambda$38$lambda$37 = ChannelListActivity.sendData$lambda$40$lambda$39$lambda$38$lambda$37(ChannelListActivity.this, objectRef);
                    return sendData$lambda$40$lambda$39$lambda$38$lambda$37;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$40$lambda$39$lambda$38$lambda$37(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendEyeData) {
        Intrinsics.checkNotNullParameter(sendEyeData, "$this$sendEyeData");
        sendEyeData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$41;
                sendData$lambda$51$lambda$50$lambda$49$lambda$41 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$41();
                return sendData$lambda$51$lambda$50$lambda$49$lambda$41;
            }
        });
        sendEyeData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$43;
                sendData$lambda$51$lambda$50$lambda$49$lambda$43 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$43(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData$lambda$51$lambda$50$lambda$49$lambda$43;
            }
        });
        sendEyeData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda62
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$45;
                sendData$lambda$51$lambda$50$lambda$49$lambda$45 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$45(ChannelListActivity.this);
                return sendData$lambda$51$lambda$50$lambda$49$lambda$45;
            }
        });
        sendEyeData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda63
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48;
                sendData$lambda$51$lambda$50$lambda$49$lambda$48 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$48(ChannelListActivity.this, ((Integer) obj).intValue());
                return sendData$lambda$51$lambda$50$lambda$49$lambda$48;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$41() {
        LogUtils.vTag("ruis", "sendEyesView --- onStart");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$43(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.EyesView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$43$lambda$42;
                sendData$lambda$51$lambda$50$lambda$49$lambda$43$lambda$42 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$43$lambda$42(ChannelListActivity.this, replace$default);
                return sendData$lambda$51$lambda$50$lambda$49$lambda$43$lambda$42;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$43$lambda$42(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$45(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount - 1;
        channelListActivity.iSelTotalCount = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver = z;
        LogUtils.vTag("ruis", "sendTextData isCAllSendOver---iSelTotalCount=" + i + "     isCAllSendOver===" + z);
        if (channelListActivity.isCAllSendOver) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$51$lambda$50$lambda$49$lambda$45$lambda$44;
                    sendData$lambda$51$lambda$50$lambda$49$lambda$45$lambda$44 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$45$lambda$44(ChannelListActivity.this);
                    return sendData$lambda$51$lambda$50$lambda$49$lambda$45$lambda$44;
                }
            });
        } else {
            Thread.sleep(500L);
            channelListActivity.sendData();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$45$lambda$44(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48(final ChannelListActivity channelListActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda57
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$46;
                sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$46 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$46(ChannelListActivity.this, i);
                return sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$46;
            }
        });
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47;
                sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47 = ChannelListActivity.sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47(ChannelListActivity.this);
                return sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$46(ChannelListActivity channelListActivity, int i) {
        ToastUtil.show(channelListActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47(ChannelListActivity channelListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) channelListActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    public final void sendData2() {
        byte[] rightData;
        LogUtils.file("ChannelListActivity  sendData2");
        LogUtils.vTag("ruis", "ChannelListActivity  sendData2");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.sendQueue2.poll();
        ChannelListItem channelListItem = (ChannelListItem) objectRef.element;
        if (channelListItem instanceof ChannelListItem.TextEmojView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.TextEmojView");
            sendTextData2(ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.TextEmojView) objectRef.element).getSerialNum())), CollectionsKt.toByteArray(ArraysKt.toMutableList(((ChannelListItem.TextEmojView) objectRef.element).getArrTextData())), new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData2$lambda$60;
                    sendData2$lambda$60 = ChannelListActivity.sendData2$lambda$60(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData2$lambda$60;
                }
            }, Byte.parseByte(((ChannelListItem.TextEmojView) objectRef.element).getSerialNum()));
            return;
        }
        if (channelListItem instanceof ChannelListItem.ImagView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.ImagView");
            sendImagData2(ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.ImagView) objectRef.element).getSerialNum())), CollectionsKt.toByteArray(ArraysKt.toMutableList(((ChannelListItem.ImagView) objectRef.element).getArrImagData())), new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData2$lambda$69;
                    sendData2$lambda$69 = ChannelListActivity.sendData2$lambda$69(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData2$lambda$69;
                }
            }, Byte.parseByte(((ChannelListItem.ImagView) objectRef.element).getSerialNum()));
            return;
        }
        if (channelListItem instanceof ChannelListItem.GiftView) {
            LogUtils.vTag("ruis", " ChannelListItem.GiftView");
            LogUtils.file("ChannelListActivity  ChannelListItem.GiftView");
            byte[] plus = ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.GiftView) objectRef.element).getSerialNum()));
            byte[] arrGifData = ((ChannelListItem.GiftView) objectRef.element).getArrGifData();
            if (arrGifData != null) {
                SendCore.INSTANCE.sendChannelGifData2(plus, arrGifData, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit sendData2$lambda$80$lambda$79;
                        sendData2$lambda$80$lambda$79 = ChannelListActivity.sendData2$lambda$80$lambda$79(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                        return sendData2$lambda$80$lambda$79;
                    }
                }, Byte.parseByte(((ChannelListItem.GiftView) objectRef.element).getSerialNum()));
                return;
            }
            return;
        }
        if (channelListItem instanceof ChannelListItem.SubzoneView) {
            LogUtils.vTag("ruis", " value.serialNum.toByte()=====" + ((int) Byte.parseByte(((ChannelListItem.SubzoneView) objectRef.element).getSerialNum())));
            SendCore.sendTemplateData2(true, ((ChannelListItem.SubzoneView) objectRef.element).getSubzoneData(), new ChannelListActivity$sendData2$4(this), Byte.parseByte(((ChannelListItem.SubzoneView) objectRef.element).getSerialNum()));
        } else if (channelListItem instanceof ChannelListItem.EyesView) {
            LogUtils.file("ChannelListActivity  ChannelListItem.EyesView");
            byte[] plus2 = ArraysKt.plus(new byte[0], Byte.parseByte(((ChannelListItem.EyesView) objectRef.element).getSerialNum()));
            byte[] leftData = ((ChannelListItem.EyesView) objectRef.element).getLeftData();
            if (leftData == null || (rightData = ((ChannelListItem.EyesView) objectRef.element).getRightData()) == null) {
                return;
            }
            SendCore.sendEyeData$default(SendCore.INSTANCE, false, plus2, leftData, rightData, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendData2$lambda$91$lambda$90$lambda$89;
                    sendData2$lambda$91$lambda$90$lambda$89 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89(ChannelListActivity.this, objectRef, (SendCore.CallbackBuilder) obj);
                    return sendData2$lambda$91$lambda$90$lambda$89;
                }
            }, Byte.parseByte(((ChannelListItem.EyesView) objectRef.element).getSerialNum()), 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$60(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendTextData2) {
        Intrinsics.checkNotNullParameter(sendTextData2, "$this$sendTextData2");
        sendTextData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda105
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$60$lambda$52;
                sendData2$lambda$60$lambda$52 = ChannelListActivity.sendData2$lambda$60$lambda$52();
                return sendData2$lambda$60$lambda$52;
            }
        });
        sendTextData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$60$lambda$54;
                sendData2$lambda$60$lambda$54 = ChannelListActivity.sendData2$lambda$60$lambda$54(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData2$lambda$60$lambda$54;
            }
        });
        sendTextData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$60$lambda$56;
                sendData2$lambda$60$lambda$56 = ChannelListActivity.sendData2$lambda$60$lambda$56(ChannelListActivity.this);
                return sendData2$lambda$60$lambda$56;
            }
        });
        sendTextData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$60$lambda$59;
                sendData2$lambda$60$lambda$59 = ChannelListActivity.sendData2$lambda$60$lambda$59(ChannelListActivity.this, (byte[]) obj);
                return sendData2$lambda$60$lambda$59;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$60$lambda$52() {
        LogUtils.vTag("ruis", "sendTextData --- onStart");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$60$lambda$54(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.TextEmojView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$60$lambda$54$lambda$53;
                sendData2$lambda$60$lambda$54$lambda$53 = ChannelListActivity.sendData2$lambda$60$lambda$54$lambda$53(ChannelListActivity.this, replace$default);
                return sendData2$lambda$60$lambda$54$lambda$53;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$60$lambda$54$lambda$53(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$60$lambda$56(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount2 - 1;
        channelListActivity.iSelTotalCount2 = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver2 = z;
        LogUtils.vTag("ruis", "sendTextData isCAllSendOver---iSelTotalCount=" + i + "     isCAllSendOver===" + z);
        if (channelListActivity.isCAllSendOver2) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda71
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$60$lambda$56$lambda$55;
                    sendData2$lambda$60$lambda$56$lambda$55 = ChannelListActivity.sendData2$lambda$60$lambda$56$lambda$55(ChannelListActivity.this);
                    return sendData2$lambda$60$lambda$56$lambda$55;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData2();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$60$lambda$56$lambda$55(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData2$lambda$60$lambda$59(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? string = channelListActivity.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        objectRef.element = string;
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$60$lambda$59$lambda$57;
                    sendData2$lambda$60$lambda$59$lambda$57 = ChannelListActivity.sendData2$lambda$60$lambda$59$lambda$57(ChannelListActivity.this, objectRef);
                    return sendData2$lambda$60$lambda$59$lambda$57;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            ?? string2 = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            objectRef.element = string2;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$60$lambda$59$lambda$58;
                    sendData2$lambda$60$lambda$59$lambda$58 = ChannelListActivity.sendData2$lambda$60$lambda$59$lambda$58(ChannelListActivity.this, objectRef);
                    return sendData2$lambda$60$lambda$59$lambda$58;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$60$lambda$59$lambda$57(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$60$lambda$59$lambda$58(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$69(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendImagData2) {
        Intrinsics.checkNotNullParameter(sendImagData2, "$this$sendImagData2");
        sendImagData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$69$lambda$61;
                sendData2$lambda$69$lambda$61 = ChannelListActivity.sendData2$lambda$69$lambda$61();
                return sendData2$lambda$69$lambda$61;
            }
        });
        sendImagData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$69$lambda$63;
                sendData2$lambda$69$lambda$63 = ChannelListActivity.sendData2$lambda$69$lambda$63(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData2$lambda$69$lambda$63;
            }
        });
        sendImagData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$69$lambda$65;
                sendData2$lambda$69$lambda$65 = ChannelListActivity.sendData2$lambda$69$lambda$65(ChannelListActivity.this);
                return sendData2$lambda$69$lambda$65;
            }
        });
        sendImagData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$69$lambda$68;
                sendData2$lambda$69$lambda$68 = ChannelListActivity.sendData2$lambda$69$lambda$68(ChannelListActivity.this, (byte[]) obj);
                return sendData2$lambda$69$lambda$68;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$69$lambda$61() {
        LogUtils.vTag("ruis", "sendImagData --stary2");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$69$lambda$63(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.ImagView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda73
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$69$lambda$63$lambda$62;
                sendData2$lambda$69$lambda$63$lambda$62 = ChannelListActivity.sendData2$lambda$69$lambda$63$lambda$62(ChannelListActivity.this, replace$default);
                return sendData2$lambda$69$lambda$63$lambda$62;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$69$lambda$63$lambda$62(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$69$lambda$65(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount2 - 1;
        channelListActivity.iSelTotalCount2 = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver2 = z;
        LogUtils.vTag("ruis", "sendImagData isCAllSendOver------iSelTotalCount=" + i + "   isCAllSendOver" + z);
        if (channelListActivity.isCAllSendOver2) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda86
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$69$lambda$65$lambda$64;
                    sendData2$lambda$69$lambda$65$lambda$64 = ChannelListActivity.sendData2$lambda$69$lambda$65$lambda$64(ChannelListActivity.this);
                    return sendData2$lambda$69$lambda$65$lambda$64;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData2();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$69$lambda$65$lambda$64(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData2$lambda$69$lambda$68(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? string = channelListActivity.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        objectRef.element = string;
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda84
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$69$lambda$68$lambda$66;
                    sendData2$lambda$69$lambda$68$lambda$66 = ChannelListActivity.sendData2$lambda$69$lambda$68$lambda$66(ChannelListActivity.this, objectRef);
                    return sendData2$lambda$69$lambda$68$lambda$66;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            ?? string2 = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            objectRef.element = string2;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda85
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$69$lambda$68$lambda$67;
                    sendData2$lambda$69$lambda$68$lambda$67 = ChannelListActivity.sendData2$lambda$69$lambda$68$lambda$67(ChannelListActivity.this, objectRef);
                    return sendData2$lambda$69$lambda$68$lambda$67;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$69$lambda$68$lambda$66(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$69$lambda$68$lambda$67(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendChannelGifData2) {
        Intrinsics.checkNotNullParameter(sendChannelGifData2, "$this$sendChannelGifData2");
        sendChannelGifData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$80$lambda$79$lambda$70;
                sendData2$lambda$80$lambda$79$lambda$70 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$70();
                return sendData2$lambda$80$lambda$79$lambda$70;
            }
        });
        sendChannelGifData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$80$lambda$79$lambda$72;
                sendData2$lambda$80$lambda$79$lambda$72 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$72(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData2$lambda$80$lambda$79$lambda$72;
            }
        });
        sendChannelGifData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$80$lambda$79$lambda$74;
                sendData2$lambda$80$lambda$79$lambda$74 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$74(ChannelListActivity.this);
                return sendData2$lambda$80$lambda$79$lambda$74;
            }
        });
        sendChannelGifData2.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$80$lambda$79$lambda$76;
                sendData2$lambda$80$lambda$79$lambda$76 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$76(ChannelListActivity.this, ((Integer) obj).intValue());
                return sendData2$lambda$80$lambda$79$lambda$76;
            }
        });
        sendChannelGifData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$80$lambda$79$lambda$78;
                sendData2$lambda$80$lambda$79$lambda$78 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$78(ChannelListActivity.this, (byte[]) obj);
                return sendData2$lambda$80$lambda$79$lambda$78;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$70() {
        LogUtils.vTag("ruis", "sendChannelGifData2 --stary");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$72(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.GiftView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$80$lambda$79$lambda$72$lambda$71;
                sendData2$lambda$80$lambda$79$lambda$72$lambda$71 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$72$lambda$71(ChannelListActivity.this, replace$default);
                return sendData2$lambda$80$lambda$79$lambda$72$lambda$71;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$72$lambda$71(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$74(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount2 - 1;
        channelListActivity.iSelTotalCount2 = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver2 = z;
        LogUtils.vTag("ruis", "sendChannelGifData2---iSelTotalCount=" + i + "   --isCAllSendOver" + z);
        if (channelListActivity.isCAllSendOver2) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda56
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$80$lambda$79$lambda$74$lambda$73;
                    sendData2$lambda$80$lambda$79$lambda$74$lambda$73 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$74$lambda$73(ChannelListActivity.this);
                    return sendData2$lambda$80$lambda$79$lambda$74$lambda$73;
                }
            });
        } else {
            Thread.sleep(200L);
            channelListActivity.sendData2();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$74$lambda$73(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$76(ChannelListActivity channelListActivity, int i) {
        final String str = channelListActivity.getString(R.string.msg_send_fail) + i;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda69
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$80$lambda$79$lambda$76$lambda$75;
                sendData2$lambda$80$lambda$79$lambda$76$lambda$75 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$76$lambda$75(str);
                return sendData2$lambda$80$lambda$79$lambda$76$lambda$75;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$76$lambda$75(String str) {
        ToastUtil.show(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.Object, java.lang.String] */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$78(final ChannelListActivity channelListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? string = channelListActivity.getString(R.string.channel_tip_low_space);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            objectRef.element = string;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda64
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$80$lambda$79$lambda$78$lambda$77;
                    sendData2$lambda$80$lambda$79$lambda$78$lambda$77 = ChannelListActivity.sendData2$lambda$80$lambda$79$lambda$78$lambda$77(ChannelListActivity.this, objectRef);
                    return sendData2$lambda$80$lambda$79$lambda$78$lambda$77;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$80$lambda$79$lambda$78$lambda$77(ChannelListActivity channelListActivity, Ref.ObjectRef objectRef) {
        channelListActivity.builder.refReshMessage((String) objectRef.element);
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89(final ChannelListActivity channelListActivity, final Ref.ObjectRef objectRef, SendCore.CallbackBuilder sendEyeData) {
        Intrinsics.checkNotNullParameter(sendEyeData, "$this$sendEyeData");
        sendEyeData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda65
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$81;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$81 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$81();
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$81;
            }
        });
        sendEyeData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda66
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$83;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$83 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$83(ChannelListActivity.this, objectRef, ((Integer) obj).intValue());
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$83;
            }
        });
        sendEyeData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda67
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$85;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$85 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$85(ChannelListActivity.this);
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$85;
            }
        });
        sendEyeData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda68
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$88 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$88(ChannelListActivity.this, ((Integer) obj).intValue());
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$88;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$81() {
        LogUtils.vTag("ruis", "sendEyesView --- onStart");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$83(final ChannelListActivity channelListActivity, Ref.ObjectRef objectRef, int i) {
        if (i != channelListActivity.tProcess) {
            channelListActivity.tProcess = i;
            if (i > 100) {
                channelListActivity.tProcess = 100;
            }
        }
        if (channelListActivity.arrSendItemInfo.size() > 1) {
            channelListActivity.arrSendItemInfo.set(1, ((ChannelListItem.EyesView) objectRef.element).getSerialNum());
        }
        if (channelListActivity.arrSendItemInfo.size() > 3) {
            channelListActivity.arrSendItemInfo.set(3, String.valueOf(channelListActivity.tProcess));
        }
        final String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(channelListActivity.arrSendItemInfo.toString(), ",", "", false, 4, (Object) null), "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$83$lambda$82;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$83$lambda$82 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$83$lambda$82(ChannelListActivity.this, replace$default);
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$83$lambda$82;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$83$lambda$82(ChannelListActivity channelListActivity, String str) {
        channelListActivity.builder.refReshMessage(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$85(final ChannelListActivity channelListActivity) {
        int i = channelListActivity.iSelTotalCount - 1;
        channelListActivity.iSelTotalCount = i;
        boolean z = i == 0;
        channelListActivity.isCAllSendOver = z;
        LogUtils.vTag("ruis", "sendTextData isCAllSendOver---iSelTotalCount=" + i + "     isCAllSendOver===" + z);
        if (channelListActivity.isCAllSendOver) {
            AppConfig.INSTANCE.setCancel(false);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$85$lambda$84;
                    sendData2$lambda$91$lambda$90$lambda$89$lambda$85$lambda$84 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$85$lambda$84(ChannelListActivity.this);
                    return sendData2$lambda$91$lambda$90$lambda$89$lambda$85$lambda$84;
                }
            });
        } else {
            Thread.sleep(500L);
            channelListActivity.sendData();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$85$lambda$84(ChannelListActivity channelListActivity) {
        channelListActivity.builder.refReshMessage(channelListActivity.getString(R.string.msg_send_suc));
        channelListActivity.builder.setCancelBtnShow(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88(final ChannelListActivity channelListActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda74
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$86;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$86 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$86(ChannelListActivity.this, i);
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$86;
            }
        });
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda75
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$87;
                sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$87 = ChannelListActivity.sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$87(ChannelListActivity.this);
                return sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$87;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$86(ChannelListActivity channelListActivity, int i) {
        ToastUtil.show(channelListActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData2$lambda$91$lambda$90$lambda$89$lambda$88$lambda$87(ChannelListActivity channelListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) channelListActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.Map] */
    private final void initChannelData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Object obj = SPUtils.get(this, getChannelValue(), new LinkedHashMap());
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            linkedHashMap = (Map) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            ((Number) entry.getKey()).intValue();
            ChannelListItem channelListItem = (ChannelListItem) entry.getValue();
            if (channelListItem instanceof ChannelListItem.GiftView) {
                ((ChannelListItem.GiftView) channelListItem).setSel(false);
            } else if (channelListItem instanceof ChannelListItem.ImagView) {
                ((ChannelListItem.ImagView) channelListItem).setSel(false);
            } else if (channelListItem instanceof ChannelListItem.TextEmojView) {
                ((ChannelListItem.TextEmojView) channelListItem).setSel(false);
            } else if (channelListItem instanceof ChannelListItem.SubzoneView) {
                ((ChannelListItem.SubzoneView) channelListItem).setSel(false);
            } else if (channelListItem instanceof ChannelListItem.EyesView) {
                ((ChannelListItem.EyesView) channelListItem).setSel(false);
            }
        }
        ChannelIndex.INSTANCE.mapSaveChannel(linkedHashMap);
    }

    public final void initToolBar() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        TextView textView = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView = this.iv_back;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView = null;
            }
            customImageView.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView2 = this.iv_back;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView2 = null;
            }
            customImageView2.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView3 = this.iv_back;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView3 = null;
        }
        customImageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda91
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelListActivity.initToolBar$lambda$93(ChannelListActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        companion.attachViewOnTouchListener(customImageView4);
        TextView textView2 = this.tv_title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
        } else {
            textView = textView2;
        }
        textView.setText(getString(R.string.title_channel_list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$93(ChannelListActivity channelListActivity, View view) {
        channelListActivity.getChannelAdapter().stopAnim(-1);
        channelListActivity.finish();
    }

    private final void initChannelRecycle() {
        Iterator<Map.Entry<Integer, ChannelListItem>> it = ChannelIndex.INSTANCE.mapSaveChannel().entrySet().iterator();
        while (it.hasNext()) {
            this.channelData.add(it.next().getValue());
        }
        TextDrawable textDrawable = this.iv_chl_sel_all;
        RecyclerView recyclerView = null;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setEnabled(!this.channelData.isEmpty());
        WeakReference<Context> weakReference = this.mContext;
        Context context = weakReference != null ? weakReference.get() : null;
        Intrinsics.checkNotNull(context);
        setChannelAdapter(new ChannelAdapter(context, this.channelData));
        WeakReference<Context> weakReference2 = this.mContext;
        final Context context2 = weakReference2 != null ? weakReference2.get() : null;
        Intrinsics.checkNotNull(context2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context2) { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$initChannelRecycle$linearLayoutManager$1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        RecyclerView recyclerView2 = this.rl_channel_list;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_channel_list");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView3 = this.rl_channel_list;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_channel_list");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setAdapter(getChannelAdapter());
        getChannelAdapter().notifyDataSetChanged();
        getChannelAdapter().bindChildClickViewIds(R.id.iv_chl_sel, R.id.iv_chl_del);
        getChannelAdapter().setOnItemChildClickListener(new RecyclerAdapter.OnItemChildClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda94
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemChildClickListener
            public final void onItemChildClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ChannelListActivity.initChannelRecycle$lambda$96(ChannelListActivity.this, viewGroup, view, (ChannelListItem) obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initChannelRecycle$lambda$96(final ChannelListActivity channelListActivity, ViewGroup viewGroup, View view, ChannelListItem channelListItem, int i) {
        int parseInt;
        LogUtils.file("ChannelListActivity  channelAdapter.setOnItemChildClickListener position" + i);
        int id = view.getId();
        int i2 = 0;
        switch (id) {
            case R.id.iv_chl_del /* 2131296713 */:
                LogUtils.file("ChannelListActivity  channelAdapter.setOnItemChildClickListener  R.id.iv_chl_del");
                ChannelListItem channelListItem2 = channelListActivity.channelData.get(i);
                if (channelListItem2 instanceof ChannelListItem.TextEmojView) {
                    ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem2;
                    channelListActivity.getChannelAdapter().delSelAnims(Integer.parseInt(textEmojView.getSerialNum()));
                    parseInt = Integer.parseInt(textEmojView.getSerialNum());
                    textEmojView.setDel(true);
                } else if (channelListItem2 instanceof ChannelListItem.ImagView) {
                    ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) channelListItem2;
                    parseInt = Integer.parseInt(imagView.getSerialNum());
                    imagView.setDel(true);
                } else if (channelListItem2 instanceof ChannelListItem.GiftView) {
                    ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) channelListItem2;
                    parseInt = Integer.parseInt(giftView.getSerialNum());
                    giftView.setDel(true);
                } else if (channelListItem2 instanceof ChannelListItem.SubzoneView) {
                    ChannelListItem.SubzoneView subzoneView = (ChannelListItem.SubzoneView) channelListItem2;
                    parseInt = Integer.parseInt(subzoneView.getSerialNum());
                    subzoneView.setDel(true);
                } else {
                    if (!(channelListItem2 instanceof ChannelListItem.EyesView)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ChannelListItem.EyesView eyesView = (ChannelListItem.EyesView) channelListItem2;
                    parseInt = Integer.parseInt(eyesView.getSerialNum());
                    eyesView.setDel(true);
                }
                channelListActivity.delPos = i;
                channelListActivity.showDialogByType(1, new byte[]{(byte) parseInt});
                return;
            case R.id.iv_chl_sel /* 2131296714 */:
                LogUtils.file("ChannelListActivity  channelAdapter.setOnItemChildClickListener  R.id.iv_chl_sel");
                if (channelListActivity.getChannelAdapter().checkIsSellALL()) {
                    channelListActivity.getChannelAdapter().setSelAll(true);
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda99
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit initChannelRecycle$lambda$96$lambda$94;
                            initChannelRecycle$lambda$96$lambda$94 = ChannelListActivity.initChannelRecycle$lambda$96$lambda$94(ChannelListActivity.this);
                            return initChannelRecycle$lambda$96$lambda$94;
                        }
                    });
                } else {
                    channelListActivity.getChannelAdapter().setSelAll(false);
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda100
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit initChannelRecycle$lambda$96$lambda$95;
                            initChannelRecycle$lambda$96$lambda$95 = ChannelListActivity.initChannelRecycle$lambda$96$lambda$95(ChannelListActivity.this);
                            return initChannelRecycle$lambda$96$lambda$95;
                        }
                    });
                }
                channelListActivity.getChannelAdapter().setSelIndex(i);
                ChannelListItem channelListItem3 = channelListActivity.channelData.get(i);
                if (channelListItem3 instanceof ChannelListItem.TextEmojView) {
                    i2 = Integer.parseInt(((ChannelListItem.TextEmojView) channelListItem3).getSerialNum());
                } else if (channelListItem3 instanceof ChannelListItem.ImagView) {
                    i2 = Integer.parseInt(((ChannelListItem.ImagView) channelListItem3).getSerialNum());
                } else if (channelListItem3 instanceof ChannelListItem.GiftView) {
                    i2 = Integer.parseInt(((ChannelListItem.GiftView) channelListItem3).getSerialNum());
                } else if (channelListItem3 instanceof ChannelListItem.SubzoneView) {
                    i2 = Integer.parseInt(((ChannelListItem.SubzoneView) channelListItem3).getSerialNum());
                } else if (channelListItem3 instanceof ChannelListItem.EyesView) {
                    i2 = Integer.parseInt(((ChannelListItem.EyesView) channelListItem3).getSerialNum());
                }
                if (ChannelIndex.INSTANCE.mapSaveChannel().containsValue(channelListItem3)) {
                    ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(i2), channelListItem3);
                }
                channelListActivity.getChannelAdapter().stopAnim(i2);
                channelListActivity.getChannelAdapter().notifyItemChanged(i);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initChannelRecycle$lambda$96$lambda$94(ChannelListActivity channelListActivity) {
        TextDrawable textDrawable = channelListActivity.iv_chl_sel_all;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setSelected(channelListActivity.getChannelAdapter().getIsSelAll());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initChannelRecycle$lambda$96$lambda$95(ChannelListActivity channelListActivity) {
        TextDrawable textDrawable = channelListActivity.iv_chl_sel_all;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setSelected(channelListActivity.getChannelAdapter().getIsSelAll());
        return Unit.INSTANCE;
    }

    static /* synthetic */ void setAllItemSelState$default(ChannelListActivity channelListActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        channelListActivity.setAllItemSelState(z);
    }

    private final void setAllItemSelState(boolean selectDone) {
        for (Map.Entry<Integer, ChannelListItem> entry : ChannelIndex.INSTANCE.mapSaveChannel().entrySet()) {
            int intValue = entry.getKey().intValue();
            ChannelListItem value = entry.getValue();
            if (value instanceof ChannelListItem.TextEmojView) {
                ((ChannelListItem.TextEmojView) value).setSel(selectDone);
            } else if (value instanceof ChannelListItem.ImagView) {
                ((ChannelListItem.ImagView) value).setSel(selectDone);
            } else if (value instanceof ChannelListItem.GiftView) {
                ((ChannelListItem.GiftView) value).setSel(selectDone);
            } else if (value instanceof ChannelListItem.SubzoneView) {
                ((ChannelListItem.SubzoneView) value).setSel(selectDone);
            } else if (value instanceof ChannelListItem.EyesView) {
                ((ChannelListItem.EyesView) value).setSel(selectDone);
            }
            ChannelIndex.INSTANCE.mapSaveChannel().replace(Integer.valueOf(intValue), value);
        }
        this.channelData.clear();
        Iterator<Map.Entry<Integer, ChannelListItem>> it = ChannelIndex.INSTANCE.mapSaveChannel().entrySet().iterator();
        while (it.hasNext()) {
            this.channelData.add(it.next().getValue());
        }
    }

    public final void showDelDialog(final List<Byte> bSelArray) {
        Intrinsics.checkNotNullParameter(bSelArray, "bSelArray");
        ChannelListActivity channelListActivity = this;
        this.delDialogAdapter = new IosDialogStyleAdapter<>(channelListActivity, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.channel_list_del_cursel), getString(R.string.set_reset)}));
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(channelListActivity, 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(channelListActivity, 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.delDialogAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("delDialogAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.actionRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    ChannelListActivity.showDelDialog$lambda$98(ChannelListActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.delDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delDialogAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda11
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ChannelListActivity.showDelDialog$lambda$101(showBottomDialog, bSelArray, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda12
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
    public static final void showDelDialog$lambda$98(ChannelListActivity channelListActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = channelListActivity.delDialogAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delDialogAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = channelListActivity.delDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delDialogAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = channelListActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDelDialog$lambda$101(Dialog dialog, List list, final ChannelListActivity channelListActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("ChannelListActivity  delDialogAdapter.setOnItemClickListener");
        dialog.cancel();
        if (i != 0) {
            if (i != 1) {
                return;
            }
            showDialogByType$default(channelListActivity, 0, null, 2, null);
            return;
        }
        int size = list.size();
        byte[] bArr = new byte[0];
        Iterator it = list.iterator();
        while (it.hasNext()) {
            byte byteValue = ((Number) it.next()).byteValue();
            Number valueOf = byteValue <= (channelListActivity.channelData.size() - size) - 1 ? Byte.valueOf(byteValue) : Integer.valueOf((byteValue - size) - 1);
            ChannelListItem channelListItem = ChannelIndex.INSTANCE.mapSaveChannel().get(Integer.valueOf(byteValue));
            if (channelListItem instanceof ChannelListItem.TextEmojView) {
                ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem;
                textEmojView.setSel(false);
                channelListActivity.getChannelAdapter().delSelAnims(Integer.parseInt(textEmojView.getSerialNum()));
                textEmojView.setDel(true);
                bArr = ArraysKt.plus(bArr, Byte.parseByte(textEmojView.getSerialNum()));
            } else if (channelListItem instanceof ChannelListItem.ImagView) {
                ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) channelListItem;
                imagView.setSel(false);
                imagView.setDel(true);
                bArr = ArraysKt.plus(bArr, Byte.parseByte(imagView.getSerialNum()));
            } else if (channelListItem instanceof ChannelListItem.GiftView) {
                ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) channelListItem;
                giftView.setSel(false);
                giftView.setDel(true);
                if (StringsKt.contains$default((CharSequence) giftView.getPath(), (CharSequence) "Gif_bak", false, 2, (Object) null)) {
                    UtilsExtensionKt.deleteFile(giftView.getPath());
                }
                bArr = ArraysKt.plus(bArr, Byte.parseByte(giftView.getSerialNum()));
            } else if (channelListItem instanceof ChannelListItem.SubzoneView) {
                ChannelListItem.SubzoneView subzoneView = (ChannelListItem.SubzoneView) channelListItem;
                subzoneView.setSel(false);
                subzoneView.setDel(true);
                bArr = ArraysKt.plus(bArr, Byte.parseByte(subzoneView.getSerialNum()));
            } else if (channelListItem instanceof ChannelListItem.EyesView) {
                ChannelListItem.EyesView eyesView = (ChannelListItem.EyesView) channelListItem;
                eyesView.setSel(false);
                eyesView.setDel(true);
                bArr = ArraysKt.plus(bArr, Byte.parseByte(eyesView.getSerialNum()));
            }
            size--;
            TypeIntrinsics.asMutableCollection(channelListActivity.channelData).remove(channelListItem);
            channelListActivity.getChannelAdapter().notifyItemRemoved(valueOf.intValue());
            channelListActivity.getChannelAdapter().notifyItemRangeChanged(0, channelListActivity.channelData.size());
        }
        channelListActivity.getChannelAdapter().setSelIndex(-1);
        ChannelIndex.INSTANCE.removeRecord(bArr);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda76
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit showDelDialog$lambda$101$lambda$100;
                showDelDialog$lambda$101$lambda$100 = ChannelListActivity.showDelDialog$lambda$101$lambda$100(ChannelListActivity.this);
                return showDelDialog$lambda$101$lambda$100;
            }
        });
        SendCore.INSTANCE.sendChannelDelIndex(bArr, new ChannelListActivity$showDelDialog$2$3(channelListActivity));
        channelListActivity.putChannelSP();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showDelDialog$lambda$101$lambda$100(ChannelListActivity channelListActivity) {
        TextDrawable textDrawable = channelListActivity.iv_chl_sel_all;
        TextDrawable textDrawable2 = null;
        if (textDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
            textDrawable = null;
        }
        textDrawable.setEnabled(!channelListActivity.channelData.isEmpty());
        TextDrawable textDrawable3 = channelListActivity.iv_chl_sel_all;
        if (textDrawable3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_chl_sel_all");
        } else {
            textDrawable2 = textDrawable3;
        }
        textDrawable2.setSelected(!channelListActivity.channelData.isEmpty());
        return Unit.INSTANCE;
    }

    static /* synthetic */ void showDialogByType$default(ChannelListActivity channelListActivity, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        channelListActivity.showDialogByType(i, bArr);
    }

    private final void showDialogByType(final int type, final byte[] byteArray) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.mActivity);
        builder.setTitle(getString(R.string.gps_tip));
        if (type == 0) {
            builder.setMessage(getString(R.string.tip));
        } else if (type == 1) {
            if (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1) {
                builder.setMessage(getString(R.string.is_delete));
            } else {
                builder.setMessage(getString(R.string.channel_list_del_cursel_no_connect));
            }
        }
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda25
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ChannelListActivity.showDialogByType$lambda$104(type, this, byteArray, dialogInterface, i);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda26
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogByType$lambda$104(int i, ChannelListActivity channelListActivity, byte[] bArr, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        LogUtils.file("ChannelListActivity  showDialogByType builder.setPositiveButton type=" + i);
        if (i == 0) {
            SendCore.INSTANCE.deleteAllData(new ChannelListActivity$showDialogByType$1$1(channelListActivity));
            SendCore.INSTANCE.deleteAllData2(new SendResultCallback() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$showDialogByType$1$2
                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onError(int result) {
                }

                @Override // com.wifiled.ipixels.core.send.SendResultCallback
                public void onResult(byte[] result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                }
            });
            return;
        }
        if (i != 1) {
            return;
        }
        channelListActivity.getChannelAdapter().setSelIndex(-1);
        for (byte b : bArr) {
            ChannelListItem channelListItem = ChannelIndex.INSTANCE.mapSaveChannel().get(Integer.valueOf(b));
            if (channelListItem instanceof ChannelListItem.TextEmojView) {
                ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem;
                textEmojView.setSel(false);
                channelListActivity.getChannelAdapter().delSelAnims(Integer.parseInt(textEmojView.getSerialNum()));
                textEmojView.setDel(true);
            } else if (channelListItem instanceof ChannelListItem.ImagView) {
                ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) channelListItem;
                imagView.setSel(false);
                imagView.setDel(true);
            } else if (channelListItem instanceof ChannelListItem.GiftView) {
                ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) channelListItem;
                giftView.setSel(false);
                giftView.setDel(true);
                if (StringsKt.contains$default((CharSequence) giftView.getPath(), (CharSequence) "Gif_bak", false, 2, (Object) null)) {
                    UtilsExtensionKt.deleteFile(giftView.getPath());
                }
            } else if (channelListItem instanceof ChannelListItem.SubzoneView) {
                ChannelListItem.SubzoneView subzoneView = (ChannelListItem.SubzoneView) channelListItem;
                subzoneView.setSel(false);
                subzoneView.setDel(true);
            } else if (channelListItem instanceof ChannelListItem.EyesView) {
                ChannelListItem.EyesView eyesView = (ChannelListItem.EyesView) channelListItem;
                eyesView.setSel(false);
                eyesView.setDel(true);
            }
            TypeIntrinsics.asMutableCollection(channelListActivity.channelData).remove(channelListItem);
        }
        ChannelIndex.INSTANCE.removeRecord(bArr);
        channelListActivity.getChannelAdapter().notifyDataSetChanged();
        SendCore.INSTANCE.sendChannelDelIndex(bArr, new ChannelListActivity$showDialogByType$1$4(channelListActivity));
        channelListActivity.putChannelSP();
    }

    static /* synthetic */ void sendTextData$default(ChannelListActivity channelListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        channelListActivity.sendTextData(bArr, bArr2, function1, b);
    }

    private final void sendTextData(byte[] arrText, byte[] arrTotal, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ChannelListActivity  sendTextData");
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            return;
        }
        SendCore.INSTANCE.sendTextDataInvokFun(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda98
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$111;
                sendTextData$lambda$111 = ChannelListActivity.sendTextData$lambda$111(SendCore.CallbackBuilder.this, this, (SendCore.CallbackBuilder) obj);
                return sendTextData$lambda$111;
            }
        }, isBulkSend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111(final SendCore.CallbackBuilder callbackBuilder, final ChannelListActivity channelListActivity, SendCore.CallbackBuilder sendTextDataInvokFun) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun, "$this$sendTextDataInvokFun");
        sendTextDataInvokFun.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$111$lambda$106;
                sendTextData$lambda$111$lambda$106 = ChannelListActivity.sendTextData$lambda$111$lambda$106(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$111$lambda$106;
            }
        });
        sendTextDataInvokFun.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$111$lambda$107;
                sendTextData$lambda$111$lambda$107 = ChannelListActivity.sendTextData$lambda$111$lambda$107(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData$lambda$111$lambda$107;
            }
        });
        sendTextDataInvokFun.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$111$lambda$108;
                sendTextData$lambda$111$lambda$108 = ChannelListActivity.sendTextData$lambda$111$lambda$108(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$111$lambda$108;
            }
        });
        sendTextDataInvokFun.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$111$lambda$109;
                sendTextData$lambda$111$lambda$109 = ChannelListActivity.sendTextData$lambda$111$lambda$109(((Integer) obj).intValue());
                return sendTextData$lambda$111$lambda$109;
            }
        });
        sendTextDataInvokFun.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$111$lambda$110;
                sendTextData$lambda$111$lambda$110 = ChannelListActivity.sendTextData$lambda$111$lambda$110(SendCore.CallbackBuilder.this, channelListActivity, (byte[]) obj);
                return sendTextData$lambda$111$lambda$110;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111$lambda$106(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> startAction$app_googleRelease = callbackBuilder.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111$lambda$107(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111$lambda$108(SendCore.CallbackBuilder callbackBuilder) {
        LogUtils.vTag("ruis", "sendTextData onCompleted");
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111$lambda$109(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$111$lambda$110(SendCore.CallbackBuilder callbackBuilder, ChannelListActivity channelListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        LogUtils.file("ChannelListActivity  sendTextData onResult " + ((int) it[4]));
        byte b = it[4];
        if (b == 2) {
            com.wifiled.baselib.utils.LogUtils.logi(channelListActivity.TAG + ">>>[sendTextData onResult]:空间不足 ", new Object[0]);
        } else if (b == 3) {
            com.wifiled.baselib.utils.LogUtils.logi(channelListActivity.TAG + ">>>[sendTextData onResult]:保存文件成功 ", new Object[0]);
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendTextData2$default(ChannelListActivity channelListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        channelListActivity.sendTextData2(bArr, bArr2, function1, b);
    }

    private final void sendTextData2(byte[] arrText, byte[] arrTotal, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ChannelListActivity  sendTextData");
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            return;
        }
        SendCore.INSTANCE.sendTextDataInvokFun2(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$117;
                sendTextData2$lambda$117 = ChannelListActivity.sendTextData2$lambda$117(SendCore.CallbackBuilder.this, this, (SendCore.CallbackBuilder) obj);
                return sendTextData2$lambda$117;
            }
        }, isBulkSend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117(final SendCore.CallbackBuilder callbackBuilder, final ChannelListActivity channelListActivity, SendCore.CallbackBuilder sendTextDataInvokFun2) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun2, "$this$sendTextDataInvokFun2");
        sendTextDataInvokFun2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda92
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData2$lambda$117$lambda$112;
                sendTextData2$lambda$117$lambda$112 = ChannelListActivity.sendTextData2$lambda$117$lambda$112(SendCore.CallbackBuilder.this);
                return sendTextData2$lambda$117$lambda$112;
            }
        });
        sendTextDataInvokFun2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda93
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$117$lambda$113;
                sendTextData2$lambda$117$lambda$113 = ChannelListActivity.sendTextData2$lambda$117$lambda$113(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData2$lambda$117$lambda$113;
            }
        });
        sendTextDataInvokFun2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda95
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData2$lambda$117$lambda$114;
                sendTextData2$lambda$117$lambda$114 = ChannelListActivity.sendTextData2$lambda$117$lambda$114(SendCore.CallbackBuilder.this);
                return sendTextData2$lambda$117$lambda$114;
            }
        });
        sendTextDataInvokFun2.onError(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda96
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$117$lambda$115;
                sendTextData2$lambda$117$lambda$115 = ChannelListActivity.sendTextData2$lambda$117$lambda$115(((Integer) obj).intValue());
                return sendTextData2$lambda$117$lambda$115;
            }
        });
        sendTextDataInvokFun2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.channel.ChannelListActivity$$ExternalSyntheticLambda97
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$117$lambda$116;
                sendTextData2$lambda$117$lambda$116 = ChannelListActivity.sendTextData2$lambda$117$lambda$116(SendCore.CallbackBuilder.this, channelListActivity, (byte[]) obj);
                return sendTextData2$lambda$117$lambda$116;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117$lambda$112(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> startAction$app_googleRelease = callbackBuilder.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117$lambda$113(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117$lambda$114(SendCore.CallbackBuilder callbackBuilder) {
        LogUtils.vTag("ruis", "sendTextData onCompleted");
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117$lambda$115(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$117$lambda$116(SendCore.CallbackBuilder callbackBuilder, ChannelListActivity channelListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        LogUtils.file("ChannelListActivity  sendTextData onResult " + ((int) it[4]));
        byte b = it[4];
        if (b == 2) {
            com.wifiled.baselib.utils.LogUtils.logi(channelListActivity.TAG + ">>>[sendTextData onResult]:空间不足 ", new Object[0]);
        } else if (b == 3) {
            com.wifiled.baselib.utils.LogUtils.logi(channelListActivity.TAG + ">>>[sendTextData onResult]:保存文件成功 ", new Object[0]);
        }
        return Unit.INSTANCE;
    }

    public final int getTProcess() {
        return this.tProcess;
    }

    public final void setTProcess(int i) {
        this.tProcess = i;
    }

    static /* synthetic */ void sendImagData$default(ChannelListActivity channelListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        channelListActivity.sendImagData(bArr, bArr2, function1, b);
    }

    private final void sendImagData(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ChannelListActivity  sendImagData");
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendChannelImageData(true, serial, data, callbackBuilder, isBulkSend);
    }

    static /* synthetic */ void sendImagData2$default(ChannelListActivity channelListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        channelListActivity.sendImagData2(bArr, bArr2, function1, b);
    }

    private final void sendImagData2(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ChannelListActivity  sendImagData2");
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendChannelImageData2(true, serial, data, callbackBuilder, isBulkSend);
    }

    private final void putChannelSP() {
        SPUtils.put(this, getChannelValue(), ChannelIndex.INSTANCE.mapSaveChannel());
    }

    private final String getChannelValue() {
        switch (AppConfig.INSTANCE.getLedType()) {
        }
        return "channel_data";
    }
}
