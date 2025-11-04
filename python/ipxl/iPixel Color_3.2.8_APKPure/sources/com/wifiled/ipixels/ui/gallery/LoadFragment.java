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
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.data.Paging3ViewModel;
import com.wifiled.ipixels.ui.adapter.AnimAdapter;
import com.wifiled.ipixels.utils.ByteUtils;
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

/* compiled from: LoadFragment.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0016\u0018\u0000 42\u00020\u0001:\u00014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010*\u001a\u00020\u001dH\u0016J\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0005H\u0002J\u0010\u0010-\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0005H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010&\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001d0'¢\u0006\u0002\b)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101¨\u00065²\u0006\n\u00106\u001a\u000207X\u008a\u0084\u0002"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/LoadFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "bgrData", "", "getBgrData", "()[B", "setBgrData", "([B)V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "mFileId", "getMFileId", "setMFileId", "type", AnnotatedPrivateKey.LABEL, "categoryName", "mWidth", "", "mHeight", "mLedType", "null_err", "Landroid/widget/ImageView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "callback", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "onDestroyView", "sendGif", "data", "sendPng", "selectMode", "", "getSelectMode", "()Z", "selectMode$delegate", "Lkotlin/Lazy;", "Companion", "app_googleRelease", "viewModel", "Lcom/wifiled/ipixels/data/Paging3ViewModel;"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public class LoadFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private byte[] bgrData;
    private int mHeight;
    private int mLedType;
    private int mWidth;
    private ImageView null_err;
    private String filePath = "";
    private String mFileId = "";
    private String type = "null";
    private String label = "null";
    private String categoryName = "null";
    private Function1<? super SendCore.CallbackBuilder, Unit> callback = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit callback$lambda$19;
            callback$lambda$19 = LoadFragment.callback$lambda$19(LoadFragment.this, (SendCore.CallbackBuilder) obj);
            return callback$lambda$19;
        }
    };

    /* renamed from: selectMode$delegate, reason: from kotlin metadata */
    private final Lazy selectMode = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean selectMode_delegate$lambda$23;
            selectMode_delegate$lambda$23 = LoadFragment.selectMode_delegate$lambda$23(LoadFragment.this);
            return Boolean.valueOf(selectMode_delegate$lambda$23);
        }
    });

    @JvmStatic
    public static final LoadFragment newInstance(String str, String str2, String str3, int i, int i2, int i3) {
        return INSTANCE.newInstance(str, str2, str3, i, i2, i3);
    }

    public final byte[] getBgrData() {
        return this.bgrData;
    }

    public final void setBgrData(byte[] bArr) {
        this.bgrData = bArr;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final void setFilePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.filePath = str;
    }

    public final String getMFileId() {
        return this.mFileId;
    }

    public final void setMFileId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFileId = str;
    }

    @Override // androidx.fragment.app.Fragment
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

    /* compiled from: LoadFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000bH\u0007¨\u0006\u000e"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/LoadFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/wifiled/ipixels/ui/gallery/LoadFragment;", "type", "", AnnotatedPrivateKey.LABEL, "categoryName", "width", "", "height", "ledType", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ LoadFragment newInstance$default(Companion companion, String str, String str2, String str3, int i, int i2, int i3, int i4, Object obj) {
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
        public final LoadFragment newInstance(String type, String label, String categoryName, int width, int height, int ledType) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(categoryName, "categoryName");
            LoadFragment loadFragment = new LoadFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            bundle.putString(AnnotatedPrivateKey.LABEL, label);
            bundle.putString("categoryName", categoryName);
            bundle.putInt("width", width);
            bundle.putInt("height", height);
            bundle.putInt("ledType", ledType);
            loadFragment.setArguments(bundle);
            return loadFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String str;
        GridLayoutManager gridLayoutManager;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        View inflate = inflater.inflate(R.layout.fragment_load, container, false);
        int i = this.mLedType;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        final AnimAdapter animAdapter = new AnimAdapter(i, CoroutineScope, requireContext);
        Lazy lazy = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Paging3ViewModel onCreateView$lambda$1;
                onCreateView$lambda$1 = LoadFragment.onCreateView$lambda$1(LoadFragment.this);
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
        String str2 = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null) ? "none,cn" : "none,en";
        if (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "09") && AppConfig.INSTANCE.getLedType() == 2) {
            str = "dis_Monest";
        } else {
            str = AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid();
        }
        recyclerView.setAdapter(animAdapter);
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
            case 19:
                gridLayoutManager = new LinearLayoutManager(getContext());
                break;
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda3
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                LoadFragment.onCreateView$lambda$3(AnimAdapter.this, refreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda4
            @Override // com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
            public final void onLoadMore(RefreshLayout refreshLayout) {
                LoadFragment.onCreateView$lambda$4(Ref.BooleanRef.this, animAdapter, refreshLayout);
            }
        });
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        try {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new LoadFragment$onCreateView$3(this, str2, str, lazy, animAdapter, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        animAdapter.setCallback(new AnimAdapter.Callback() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$onCreateView$4
            @Override // com.wifiled.ipixels.ui.adapter.AnimAdapter.Callback
            public void item(Record item) {
                Intrinsics.checkNotNullParameter(item, "item");
                LoadFragment.this.setFilePath(item.getFilePath());
                LoadFragment.this.setMFileId(item.getFileID());
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(LoadFragment.this), Dispatchers.getIO(), null, new LoadFragment$onCreateView$4$item$1(LoadFragment.this, item, null), 2, null);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new LoadFragment$onCreateView$5(animAdapter, this, smartRefreshLayout, booleanRef, null), 3, null);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paging3ViewModel onCreateView$lambda$1(LoadFragment loadFragment) {
        return (Paging3ViewModel) new ViewModelProvider(loadFragment).get(Paging3ViewModel.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paging3ViewModel onCreateView$lambda$2(Lazy<Paging3ViewModel> lazy) {
        return lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$3(AnimAdapter animAdapter, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(it, "it");
        animAdapter.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$4(Ref.BooleanRef booleanRef, AnimAdapter animAdapter, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (booleanRef.element) {
            animAdapter.retry();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19(final LoadFragment loadFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$19$lambda$6;
                callback$lambda$19$lambda$6 = LoadFragment.callback$lambda$19$lambda$6(LoadFragment.this);
                return callback$lambda$19$lambda$6;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$19$lambda$8;
                callback$lambda$19$lambda$8 = LoadFragment.callback$lambda$19$lambda$8(LoadFragment.this, ((Integer) obj).intValue());
                return callback$lambda$19$lambda$8;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$19$lambda$10;
                callback$lambda$19$lambda$10 = LoadFragment.callback$lambda$19$lambda$10(LoadFragment.this);
                return callback$lambda$19$lambda$10;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$19$lambda$13;
                callback$lambda$19$lambda$13 = LoadFragment.callback$lambda$19$lambda$13(LoadFragment.this, ((Integer) obj).intValue());
                return callback$lambda$19$lambda$13;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$19$lambda$18;
                callback$lambda$19$lambda$18 = LoadFragment.callback$lambda$19$lambda$18(LoadFragment.this, (byte[]) obj);
                return callback$lambda$19$lambda$18;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$6(final LoadFragment loadFragment) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$19$lambda$6$lambda$5;
                callback$lambda$19$lambda$6$lambda$5 = LoadFragment.callback$lambda$19$lambda$6$lambda$5(LoadFragment.this);
                return callback$lambda$19$lambda$6$lambda$5;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$6$lambda$5(LoadFragment loadFragment) {
        if (ActivityUtils.isActivityAlive((Activity) loadFragment.getActivity())) {
            String string = loadFragment.getString(R.string.msg_sending);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, string, false, 5, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$8(final LoadFragment loadFragment, final int i) {
        if (loadFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$8$lambda$7;
                    callback$lambda$19$lambda$8$lambda$7 = LoadFragment.callback$lambda$19$lambda$8$lambda$7(LoadFragment.this, i);
                    return callback$lambda$19$lambda$8$lambda$7;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$8$lambda$7(LoadFragment loadFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, loadFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$10(final LoadFragment loadFragment) {
        LogUtils.i("onCompleted");
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$19$lambda$10$lambda$9;
                callback$lambda$19$lambda$10$lambda$9 = LoadFragment.callback$lambda$19$lambda$10$lambda$9(LoadFragment.this);
                return callback$lambda$19$lambda$10$lambda$9;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$10$lambda$9(LoadFragment loadFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$13(final LoadFragment loadFragment, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$19$lambda$13$lambda$11;
                callback$lambda$19$lambda$13$lambda$11 = LoadFragment.callback$lambda$19$lambda$13$lambda$11(LoadFragment.this);
                return callback$lambda$19$lambda$13$lambda$11;
            }
        });
        if (i == 10016) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$13$lambda$12;
                    callback$lambda$19$lambda$13$lambda$12 = LoadFragment.callback$lambda$19$lambda$13$lambda$12(LoadFragment.this, i);
                    return callback$lambda$19$lambda$13$lambda$12;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$13$lambda$11(LoadFragment loadFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, (String) null, false, 6, (Object) null);
        if (loadFragment.isAdded()) {
            Toast.makeText(loadFragment.getActivity(), loadFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$13$lambda$12(LoadFragment loadFragment, int i) {
        ToastUtil.show(loadFragment.getString(R.string.send_failed_deivce_space) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$18(final LoadFragment loadFragment, final byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.i("strResponse: " + ByteUtils.binaryToHexString(it));
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$18$lambda$14;
                    callback$lambda$19$lambda$18$lambda$14 = LoadFragment.callback$lambda$19$lambda$18$lambda$14(LoadFragment.this);
                    return callback$lambda$19$lambda$18$lambda$14;
                }
            });
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 0) {
            LogUtils.i("$TAG>>>[onResult]:命令无效 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$18$lambda$15;
                    callback$lambda$19$lambda$18$lambda$15 = LoadFragment.callback$lambda$19$lambda$18$lambda$15(LoadFragment.this);
                    return callback$lambda$19$lambda$18$lambda$15;
                }
            });
        } else if (b == 2) {
            LogUtils.i("$TAG>>>[onResult]:空间不足 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$18$lambda$16;
                    callback$lambda$19$lambda$18$lambda$16 = LoadFragment.callback$lambda$19$lambda$18$lambda$16(LoadFragment.this, it);
                    return callback$lambda$19$lambda$18$lambda$16;
                }
            });
        } else if (b == 3) {
            LogUtils.i("$TAG>>>[onResult]:保存文件成功 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$19$lambda$18$lambda$17;
                    callback$lambda$19$lambda$18$lambda$17 = LoadFragment.callback$lambda$19$lambda$18$lambda$17(LoadFragment.this);
                    return callback$lambda$19$lambda$18$lambda$17;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$18$lambda$14(LoadFragment loadFragment) {
        if (loadFragment.isAdded()) {
            Toast.makeText(loadFragment.getActivity(), loadFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$18$lambda$15(LoadFragment loadFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, (String) null, false, 6, (Object) null);
        if (loadFragment.isAdded()) {
            Toast.makeText(loadFragment.getActivity(), loadFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$18$lambda$16(LoadFragment loadFragment, byte[] bArr) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(loadFragment.getString(R.string.send_failed_deivce_space) + "(" + bArr + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$19$lambda$18$lambda$17(LoadFragment loadFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.callback = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onDestroyView$lambda$20;
                onDestroyView$lambda$20 = LoadFragment.onDestroyView$lambda$20((SendCore.CallbackBuilder) obj);
                return onDestroyView$lambda$20;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDestroyView$lambda$20(SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendGif(byte[] data) {
        LogUtils.file("LoadFragment  sendGif ");
        AppConfig.INSTANCE.setSend(true);
        if (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1) {
            SendCore.INSTANCE.sendGifData(false, data, this.callback);
            SendCore.INSTANCE.sendGifData2(false, data, this.callback);
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGif$lambda$21;
                    sendGif$lambda$21 = LoadFragment.sendGif$lambda$21(LoadFragment.this);
                    return sendGif$lambda$21;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGif$lambda$21(LoadFragment loadFragment) {
        Toast.makeText(loadFragment.getActivity(), loadFragment.getString(R.string.msg_dev_connect_null), 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPng(byte[] data) {
        LogUtils.file("LoadFragment  sendPng ");
        AppConfig.INSTANCE.setSend(true);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.LoadFragment$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendPng$lambda$22;
                sendPng$lambda$22 = LoadFragment.sendPng$lambda$22(LoadFragment.this);
                return sendPng$lambda$22;
            }
        });
        SendCore.sendChannelImageData$default(SendCore.INSTANCE, false, null, data, this.callback, (byte) 0, 18, null);
        SendCore.sendChannelImageData2$default(SendCore.INSTANCE, false, null, data, this.callback, (byte) 0, 18, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendPng$lambda$22(LoadFragment loadFragment) {
        String string = loadFragment.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) loadFragment, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSelectMode() {
        return ((Boolean) this.selectMode.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean selectMode_delegate$lambda$23(LoadFragment loadFragment) {
        FragmentActivity activity = loadFragment.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) activity).getSelectMode();
    }
}
