package com.wifiled.ipixels.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.data.Record;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.data.Paging3ViewEyeModel;
import com.wifiled.ipixels.ui.adapter.EyeAnimAdapter;
import com.wifiled.ipixels.ui.text.vo.EventEyesChange;
import com.wifiled.ipixels.utils.ByteUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DevilsEyeFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\u0018\u0000 D2\u00020\u0001:\u0001DB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J&\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020$H\u0016J\b\u00103\u001a\u00020(H\u0016J\b\u00104\u001a\u00020(H\u0016J\b\u0010;\u001a\u00020(H\u0016J\u0018\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0005H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u000e\u0010\u001c\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u00107\u001a\u0013\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020(08¢\u0006\u0002\b:X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010<\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\b=\u0010>¨\u0006E²\u0006\n\u0010F\u001a\u00020GX\u008a\u0084\u0002"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/DevilsEyeFragment;", "Lcom/wifiled/ipixels/ui/gallery/LoadFragment;", "<init>", "()V", "mLeftRGBData", "", "getMLeftRGBData", "()[B", "setMLeftRGBData", "([B)V", "mRightRGBData2", "getMRightRGBData2", "setMRightRGBData2", "mLeftFilePath", "", "getMLeftFilePath", "()Ljava/lang/String;", "setMLeftFilePath", "(Ljava/lang/String;)V", "mRightFilePath", "getMRightFilePath", "setMRightFilePath", "mFileId1", "getMFileId1", "setMFileId1", "mFileId2", "getMFileId2", "setMFileId2", "type", AnnotatedPrivateKey.LABEL, "categoryName", "mWidth", "", "mHeight", "mLedType", "mIsShow", "", "null_err", "Landroid/widget/ImageView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onHiddenChanged", "hidden", "onPause", "onResume", "disposable", "Lio/reactivex/disposables/Disposable;", "callback", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "onDestroyView", "selectMode", "getSelectMode", "()Z", "selectMode$delegate", "Lkotlin/Lazy;", "sendGif", "leftData", "rightData", "Companion", "app_googleRelease", "viewModel", "Lcom/wifiled/ipixels/data/Paging3ViewEyeModel;"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DevilsEyeFragment extends LoadFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Disposable disposable;
    private int mHeight;
    private boolean mIsShow;
    private int mLedType;
    private byte[] mLeftRGBData;
    private byte[] mRightRGBData2;
    private int mWidth;
    private ImageView null_err;
    private String mLeftFilePath = "";
    private String mRightFilePath = "";
    private String mFileId1 = "";
    private String mFileId2 = "";
    private String type = "null";
    private String label = "null";
    private String categoryName = "null";
    private Function1<? super SendCore.CallbackBuilder, Unit> callback = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit callback$lambda$20;
            callback$lambda$20 = DevilsEyeFragment.callback$lambda$20(DevilsEyeFragment.this, (SendCore.CallbackBuilder) obj);
            return callback$lambda$20;
        }
    };

    /* renamed from: selectMode$delegate, reason: from kotlin metadata */
    private final Lazy selectMode = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean selectMode_delegate$lambda$22;
            selectMode_delegate$lambda$22 = DevilsEyeFragment.selectMode_delegate$lambda$22(DevilsEyeFragment.this);
            return Boolean.valueOf(selectMode_delegate$lambda$22);
        }
    });

    @JvmStatic
    public static final DevilsEyeFragment newInstance(String str, String str2, String str3, int i, int i2, int i3) {
        return INSTANCE.newInstance(str, str2, str3, i, i2, i3);
    }

    public final byte[] getMLeftRGBData() {
        return this.mLeftRGBData;
    }

    public final void setMLeftRGBData(byte[] bArr) {
        this.mLeftRGBData = bArr;
    }

    public final byte[] getMRightRGBData2() {
        return this.mRightRGBData2;
    }

    public final void setMRightRGBData2(byte[] bArr) {
        this.mRightRGBData2 = bArr;
    }

    public final String getMLeftFilePath() {
        return this.mLeftFilePath;
    }

    public final void setMLeftFilePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mLeftFilePath = str;
    }

    public final String getMRightFilePath() {
        return this.mRightFilePath;
    }

    public final void setMRightFilePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mRightFilePath = str;
    }

    public final String getMFileId1() {
        return this.mFileId1;
    }

    public final void setMFileId1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFileId1 = str;
    }

    public final String getMFileId2() {
        return this.mFileId2;
    }

    public final void setMFileId2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFileId2 = str;
    }

    @Override // com.wifiled.ipixels.ui.gallery.LoadFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("type");
            if (string == null) {
                string = "null";
            }
            this.type = string;
            String string2 = arguments.getString(AnnotatedPrivateKey.LABEL);
            if (string2 == null) {
                string2 = "null";
            }
            this.label = string2;
            String string3 = arguments.getString("categoryName");
            this.categoryName = string3 != null ? string3 : "null";
            this.mWidth = arguments.getInt("width");
            this.mHeight = arguments.getInt("height");
            this.mLedType = arguments.getInt("ledType");
        }
    }

    /* compiled from: DevilsEyeFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000bH\u0007¨\u0006\u000e"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/DevilsEyeFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/wifiled/ipixels/ui/gallery/DevilsEyeFragment;", "type", "", AnnotatedPrivateKey.LABEL, "categoryName", "width", "", "height", "ledType", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ DevilsEyeFragment newInstance$default(Companion companion, String str, String str2, String str3, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 8) != 0) {
                i = AppConfig.INSTANCE.getLedSize().get(0).intValue();
            }
            int i5 = i;
            if ((i4 & 16) != 0) {
                i2 = AppConfig.INSTANCE.getLedSize().get(1).intValue();
            }
            int i6 = i2;
            if ((i4 & 32) != 0) {
                i3 = AppConfig.INSTANCE.getLedType();
            }
            return companion.newInstance(str, str2, str3, i5, i6, i3);
        }

        @JvmStatic
        public final DevilsEyeFragment newInstance(String type, String label, String categoryName, int width, int height, int ledType) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(categoryName, "categoryName");
            DevilsEyeFragment devilsEyeFragment = new DevilsEyeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            bundle.putString(AnnotatedPrivateKey.LABEL, label);
            bundle.putString("categoryName", categoryName);
            bundle.putInt("width", width);
            bundle.putInt("height", height);
            bundle.putInt("ledType", ledType);
            devilsEyeFragment.setArguments(bundle);
            return devilsEyeFragment;
        }
    }

    @Override // com.wifiled.ipixels.ui.gallery.LoadFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        View inflate = inflater.inflate(R.layout.fragment_load, container, false);
        int i = this.mLedType;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        final EyeAnimAdapter eyeAnimAdapter = new EyeAnimAdapter(i, CoroutineScope, requireContext);
        Lazy lazy = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Paging3ViewEyeModel onCreateView$lambda$1;
                onCreateView$lambda$1 = DevilsEyeFragment.onCreateView$lambda$1(DevilsEyeFragment.this);
                return onCreateView$lambda$1;
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.localRecyclerview);
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshLayout);
        View findViewById = inflate.findViewById(R.id.null_err);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.null_err = (ImageView) findViewById;
        String saveLanguage = LanguageUtil.getSaveLanguage(getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        String str = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null) ? "none,cn" : "none,en";
        String str2 = AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid();
        recyclerView.setAdapter(eyeAnimAdapter);
        switch (this.mLedType) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                gridLayoutManager = new GridLayoutManager(getContext(), 2);
                break;
            case 2:
            default:
                gridLayoutManager = new GridLayoutManager(getContext(), 3);
                break;
            case 7:
            case 8:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                gridLayoutManager = new LinearLayoutManager(getContext());
                break;
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda12
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                DevilsEyeFragment.onCreateView$lambda$3(EyeAnimAdapter.this, refreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda13
            @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
            public final void onLoadMore(RefreshLayout refreshLayout) {
                DevilsEyeFragment.onCreateView$lambda$4(Ref.BooleanRef.this, eyeAnimAdapter, refreshLayout);
            }
        });
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        try {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new DevilsEyeFragment$onCreateView$3(this, str, str2, lazy, eyeAnimAdapter, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        eyeAnimAdapter.setCallback(new EyeAnimAdapter.Callback() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$onCreateView$4
            @Override // com.wifiled.ipixels.ui.adapter.EyeAnimAdapter.Callback
            public void item(Record item, Record righItem) {
                Intrinsics.checkNotNullParameter(item, "item");
                Intrinsics.checkNotNullParameter(righItem, "righItem");
                DevilsEyeFragment.this.setMLeftFilePath(item.getFilePath());
                DevilsEyeFragment.this.setMRightFilePath(righItem.getFilePath());
                DevilsEyeFragment.this.setMFileId1(item.getFileID());
                DevilsEyeFragment.this.setMFileId2(righItem.getFileID());
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(DevilsEyeFragment.this), Dispatchers.getIO(), null, new DevilsEyeFragment$onCreateView$4$item$1(DevilsEyeFragment.this, item, righItem, null), 2, null);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new DevilsEyeFragment$onCreateView$5(eyeAnimAdapter, this, smartRefreshLayout, booleanRef, null), 3, null);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paging3ViewEyeModel onCreateView$lambda$1(DevilsEyeFragment devilsEyeFragment) {
        return (Paging3ViewEyeModel) new ViewModelProvider(devilsEyeFragment).get(Paging3ViewEyeModel.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paging3ViewEyeModel onCreateView$lambda$2(Lazy<Paging3ViewEyeModel> lazy) {
        return lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$3(EyeAnimAdapter eyeAnimAdapter, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(it, "it");
        eyeAnimAdapter.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$4(Ref.BooleanRef booleanRef, EyeAnimAdapter eyeAnimAdapter, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (booleanRef.element) {
            eyeAnimAdapter.retry();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && this.mIsShow) {
            EventBus.getDefault().post(new EventEyesChange(true));
        } else {
            EventBus.getDefault().post(new EventEyesChange(false));
        }
        LogUtils.vTag("ruis", "DevilsEye onHiddenChanged ----" + hidden);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtils.vTag("ruis", "DevilsEye onPause ----");
        this.mIsShow = false;
        EventBus.getDefault().post(new EventEyesChange(false));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtils.vTag("ruis", "DevilsEye onResume ----");
        this.mIsShow = true;
        EventBus.getDefault().post(new EventEyesChange(true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20(final DevilsEyeFragment devilsEyeFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$20$lambda$8;
                callback$lambda$20$lambda$8 = DevilsEyeFragment.callback$lambda$20$lambda$8(DevilsEyeFragment.this);
                return callback$lambda$20$lambda$8;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$20$lambda$10;
                callback$lambda$20$lambda$10 = DevilsEyeFragment.callback$lambda$20$lambda$10(DevilsEyeFragment.this, ((Integer) obj).intValue());
                return callback$lambda$20$lambda$10;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$20$lambda$12;
                callback$lambda$20$lambda$12 = DevilsEyeFragment.callback$lambda$20$lambda$12(DevilsEyeFragment.this);
                return callback$lambda$20$lambda$12;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$20$lambda$14;
                callback$lambda$20$lambda$14 = DevilsEyeFragment.callback$lambda$20$lambda$14(DevilsEyeFragment.this, ((Integer) obj).intValue());
                return callback$lambda$20$lambda$14;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$20$lambda$19;
                callback$lambda$20$lambda$19 = DevilsEyeFragment.callback$lambda$20$lambda$19(DevilsEyeFragment.this, (byte[]) obj);
                return callback$lambda$20$lambda$19;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$8(final DevilsEyeFragment devilsEyeFragment) {
        Observable<Long> observeOn = Observable.interval(15L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$20$lambda$8$lambda$5;
                callback$lambda$20$lambda$8$lambda$5 = DevilsEyeFragment.callback$lambda$20$lambda$8$lambda$5(DevilsEyeFragment.this, (Long) obj);
                return callback$lambda$20$lambda$8$lambda$5;
            }
        };
        devilsEyeFragment.disposable = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda18
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Function1.this.invoke(obj);
            }
        });
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$20$lambda$8$lambda$7;
                callback$lambda$20$lambda$8$lambda$7 = DevilsEyeFragment.callback$lambda$20$lambda$8$lambda$7(DevilsEyeFragment.this);
                return callback$lambda$20$lambda$8$lambda$7;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$8$lambda$5(DevilsEyeFragment devilsEyeFragment, Long l) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        if (devilsEyeFragment.isAdded()) {
            Toast.makeText(devilsEyeFragment.getActivity(), devilsEyeFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$8$lambda$7(DevilsEyeFragment devilsEyeFragment) {
        if (ActivityUtils.isActivityAlive((Activity) devilsEyeFragment.getActivity())) {
            String string = devilsEyeFragment.getString(R.string.msg_sending);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, string, false, 5, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$10(final DevilsEyeFragment devilsEyeFragment, final int i) {
        if (devilsEyeFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$20$lambda$10$lambda$9;
                    callback$lambda$20$lambda$10$lambda$9 = DevilsEyeFragment.callback$lambda$20$lambda$10$lambda$9(DevilsEyeFragment.this, i);
                    return callback$lambda$20$lambda$10$lambda$9;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$10$lambda$9(DevilsEyeFragment devilsEyeFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, devilsEyeFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$12(final DevilsEyeFragment devilsEyeFragment) {
        LogUtils.i("onCompleted");
        Disposable disposable = devilsEyeFragment.disposable;
        if (disposable != null) {
            Intrinsics.checkNotNull(disposable);
            disposable.dispose();
            devilsEyeFragment.disposable = null;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$20$lambda$12$lambda$11;
                callback$lambda$20$lambda$12$lambda$11 = DevilsEyeFragment.callback$lambda$20$lambda$12$lambda$11(DevilsEyeFragment.this);
                return callback$lambda$20$lambda$12$lambda$11;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$12$lambda$11(DevilsEyeFragment devilsEyeFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$14(final DevilsEyeFragment devilsEyeFragment, int i) {
        Disposable disposable = devilsEyeFragment.disposable;
        if (disposable != null) {
            Intrinsics.checkNotNull(disposable);
            disposable.dispose();
            devilsEyeFragment.disposable = null;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$20$lambda$14$lambda$13;
                callback$lambda$20$lambda$14$lambda$13 = DevilsEyeFragment.callback$lambda$20$lambda$14$lambda$13(DevilsEyeFragment.this);
                return callback$lambda$20$lambda$14$lambda$13;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$14$lambda$13(DevilsEyeFragment devilsEyeFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        if (devilsEyeFragment.isAdded()) {
            Toast.makeText(devilsEyeFragment.getActivity(), devilsEyeFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$19(final DevilsEyeFragment devilsEyeFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.i("strResponse: " + ByteUtils.binaryToHexString(it));
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$20$lambda$19$lambda$15;
                    callback$lambda$20$lambda$19$lambda$15 = DevilsEyeFragment.callback$lambda$20$lambda$19$lambda$15(DevilsEyeFragment.this);
                    return callback$lambda$20$lambda$19$lambda$15;
                }
            });
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 0) {
            LogUtils.i("$TAG>>>[onResult]:命令无效 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$20$lambda$19$lambda$16;
                    callback$lambda$20$lambda$19$lambda$16 = DevilsEyeFragment.callback$lambda$20$lambda$19$lambda$16(DevilsEyeFragment.this);
                    return callback$lambda$20$lambda$19$lambda$16;
                }
            });
        } else if (b == 2) {
            LogUtils.i("$TAG>>>[onResult]:空间不足 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$20$lambda$19$lambda$17;
                    callback$lambda$20$lambda$19$lambda$17 = DevilsEyeFragment.callback$lambda$20$lambda$19$lambda$17(DevilsEyeFragment.this);
                    return callback$lambda$20$lambda$19$lambda$17;
                }
            });
        } else if (b == 3) {
            LogUtils.i("$TAG>>>[onResult]:保存文件成功 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$20$lambda$19$lambda$18;
                    callback$lambda$20$lambda$19$lambda$18 = DevilsEyeFragment.callback$lambda$20$lambda$19$lambda$18(DevilsEyeFragment.this);
                    return callback$lambda$20$lambda$19$lambda$18;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$19$lambda$15(DevilsEyeFragment devilsEyeFragment) {
        if (devilsEyeFragment.isAdded()) {
            Toast.makeText(devilsEyeFragment.getActivity(), devilsEyeFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$19$lambda$16(DevilsEyeFragment devilsEyeFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        if (devilsEyeFragment.isAdded()) {
            Toast.makeText(devilsEyeFragment.getActivity(), devilsEyeFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$19$lambda$17(DevilsEyeFragment devilsEyeFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$20$lambda$19$lambda$18(DevilsEyeFragment devilsEyeFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) devilsEyeFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.ui.gallery.LoadFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.callback = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onDestroyView$lambda$21;
                onDestroyView$lambda$21 = DevilsEyeFragment.onDestroyView$lambda$21((SendCore.CallbackBuilder) obj);
                return onDestroyView$lambda$21;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDestroyView$lambda$21(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSelectMode() {
        return ((Boolean) this.selectMode.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean selectMode_delegate$lambda$22(DevilsEyeFragment devilsEyeFragment) {
        FragmentActivity activity = devilsEyeFragment.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) activity).getSelectMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendGif(byte[] leftData, byte[] rightData) {
        LogUtils.file("LoadFragment  sendGif ");
        AppConfig.INSTANCE.setSend(true);
        int connectType = AppConfig.INSTANCE.getConnectType();
        if (connectType == 0 || connectType == 1) {
            if (!AppConfig.INSTANCE.isExchange()) {
                SendCore.sendEyeData$default(SendCore.INSTANCE, false, null, leftData, rightData, this.callback, (byte) 0, 34, null);
                return;
            } else {
                SendCore.sendEyeData$default(SendCore.INSTANCE, false, null, rightData, leftData, this.callback, (byte) 0, 34, null);
                return;
            }
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.DevilsEyeFragment$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGif$lambda$23;
                sendGif$lambda$23 = DevilsEyeFragment.sendGif$lambda$23(DevilsEyeFragment.this);
                return sendGif$lambda$23;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGif$lambda$23(DevilsEyeFragment devilsEyeFragment) {
        Toast.makeText(devilsEyeFragment.getActivity(), devilsEyeFragment.getString(R.string.msg_dev_connect_null), 0).show();
        return Unit.INSTANCE;
    }
}
