package com.wifiled.ipixels.ui.gallery;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.db.AppDatabase;
import com.wifiled.ipixels.db.GifDao;
import com.wifiled.ipixels.model.Gif;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.gallery.DevilsEyeFragment;
import com.wifiled.ipixels.ui.gallery.LoadFragment;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.HandlerUtil;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AnimFragment.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010B\u001a\u00020\u0013H\u0014J\b\u0010C\u001a\u00020DH\u0014J\b\u0010E\u001a\u00020DH\u0014J\b\u0010F\u001a\u00020DH\u0002J\b\u0010G\u001a\u00020DH\u0002J\u0010\u0010H\u001a\u00020D2\u0006\u0010I\u001a\u00020\nH\u0002J\b\u0010J\u001a\u00020DH\u0015J\u0010\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020\nH\u0016J\b\u0010M\u001a\u00020DH\u0016J\b\u0010N\u001a\u00020DH\u0016J\u0012\u0010O\u001a\u00020D2\b\b\u0002\u0010P\u001a\u00020\nH\u0002J\u0010\u0010Q\u001a\u00020D2\b\b\u0002\u0010P\u001a\u00020\nJ\u0006\u0010R\u001a\u00020DJ\u001a\u0010S\u001a\u00020D2\u0006\u0010T\u001a\u00020*2\b\b\u0002\u0010U\u001a\u00020\nH\u0002J\u001a\u0010V\u001a\u00020D2\u0006\u0010T\u001a\u00020*2\b\b\u0002\u0010U\u001a\u00020\nH\u0002J\u0010\u0010]\u001a\u00020D2\u0006\u0010T\u001a\u00020*H\u0002J\u0018\u0010^\u001a\u00020D2\u0006\u0010_\u001a\u00020*2\u0006\u0010`\u001a\u00020*H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\f\"\u0004\b/\u0010'R\u001a\u00100\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u000e\u00105\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010W\u001a\u0013\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020D0X¢\u0006\u0002\bZX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010[\u001a\u0013\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020D0X¢\u0006\u0002\bZX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\\\u001a\u0013\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020D0X¢\u0006\u0002\bZX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/AnimFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "diyAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/model/Gif;", "diyGifs", "", "selectMode", "", "getSelectMode", "()Z", "selectMode$delegate", "Lkotlin/Lazy;", "routByFolderSel", "getRoutByFolderSel", "routByFolderSel$delegate", "mResourcesSize", "", "getMResourcesSize", "()I", "mResourcesSize$delegate", "gifDao", "Lcom/wifiled/ipixels/db/GifDao;", "getGifDao", "()Lcom/wifiled/ipixels/db/GifDao;", "gifDao$delegate", "fragmentList", "Lcom/wifiled/ipixels/ui/gallery/LoadFragment;", "mSendResourceIDList", "", "getMSendResourceIDList", "()Ljava/util/List;", "setMSendResourceIDList", "(Ljava/util/List;)V", "mIsShowDiy", "getMIsShowDiy", "setMIsShowDiy", "(Z)V", "m_iSelect", "mSendGifData", "", "mSendEyeLeftData", "mSendEyeRightData", "mSendGifIsDown", "isBackLoad", "setBackLoad", "strPath", "getStrPath", "()Ljava/lang/String;", "setStrPath", "(Ljava/lang/String;)V", "bgrData", "mLeftSendOver", "mRightSendOver", "iv_title_bg", "Landroid/widget/ImageView;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "diyRecyclerview", "Landroidx/recyclerview/widget/RecyclerView;", "no_data", "Landroid/widget/LinearLayout;", "layoutId", "initView", "", "bindData", "initData", "templateModeBindData", "initDiy", "isTemplate", "bindListener", "onHiddenChanged", "hidden", "onResume", "onDetach", "showDiyImage", "isShowDiy", "resetSel", "sendData", "sendGif", "data", "isDown", "sendGif2", "sendGifCallBack", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "sendLeftEyeCallBack", "sendRightEyeCallBack", "itemClick", "dowaloadEyeToDevice", "leftData", "rightData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AnimFragment extends BaseFragment {
    private RecyclerAdapter<Gif> diyAdapter;
    private RecyclerView diyRecyclerview;
    private boolean isBackLoad;
    private ImageView iv_title_bg;
    private boolean mIsShowDiy;
    private boolean mLeftSendOver;
    private boolean mRightSendOver;
    private byte[] mSendEyeLeftData;
    private byte[] mSendEyeRightData;
    private byte[] mSendGifData;
    private LinearLayout no_data;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Gif> diyGifs = new ArrayList();

    /* renamed from: selectMode$delegate, reason: from kotlin metadata */
    private final Lazy selectMode = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean selectMode_delegate$lambda$0;
            selectMode_delegate$lambda$0 = AnimFragment.selectMode_delegate$lambda$0(AnimFragment.this);
            return Boolean.valueOf(selectMode_delegate$lambda$0);
        }
    });

    /* renamed from: routByFolderSel$delegate, reason: from kotlin metadata */
    private final Lazy routByFolderSel = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean routByFolderSel_delegate$lambda$1;
            routByFolderSel_delegate$lambda$1 = AnimFragment.routByFolderSel_delegate$lambda$1(AnimFragment.this);
            return Boolean.valueOf(routByFolderSel_delegate$lambda$1);
        }
    });

    /* renamed from: mResourcesSize$delegate, reason: from kotlin metadata */
    private final Lazy mResourcesSize = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            int mResourcesSize_delegate$lambda$2;
            mResourcesSize_delegate$lambda$2 = AnimFragment.mResourcesSize_delegate$lambda$2(AnimFragment.this);
            return Integer.valueOf(mResourcesSize_delegate$lambda$2);
        }
    });

    /* renamed from: gifDao$delegate, reason: from kotlin metadata */
    private final Lazy gifDao = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            GifDao gifDao_delegate$lambda$3;
            gifDao_delegate$lambda$3 = AnimFragment.gifDao_delegate$lambda$3();
            return gifDao_delegate$lambda$3;
        }
    });
    private final List<LoadFragment> fragmentList = new ArrayList();
    private List<String> mSendResourceIDList = new ArrayList();
    private int m_iSelect = -1;
    private boolean mSendGifIsDown = true;
    private String strPath = "";
    private byte[] bgrData = new byte[0];
    private Function1<? super SendCore.CallbackBuilder, Unit> sendGifCallBack = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit sendGifCallBack$lambda$30;
            sendGifCallBack$lambda$30 = AnimFragment.sendGifCallBack$lambda$30(AnimFragment.this, (SendCore.CallbackBuilder) obj);
            return sendGifCallBack$lambda$30;
        }
    };
    private Function1<? super SendCore.CallbackBuilder, Unit> sendLeftEyeCallBack = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit sendLeftEyeCallBack$lambda$43;
            sendLeftEyeCallBack$lambda$43 = AnimFragment.sendLeftEyeCallBack$lambda$43(AnimFragment.this, (SendCore.CallbackBuilder) obj);
            return sendLeftEyeCallBack$lambda$43;
        }
    };
    private Function1<? super SendCore.CallbackBuilder, Unit> sendRightEyeCallBack = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit sendRightEyeCallBack$lambda$56;
            sendRightEyeCallBack$lambda$56 = AnimFragment.sendRightEyeCallBack$lambda$56(AnimFragment.this, (SendCore.CallbackBuilder) obj);
            return sendRightEyeCallBack$lambda$56;
        }
    };

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_image;
    }

    private final boolean getSelectMode() {
        return ((Boolean) this.selectMode.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean selectMode_delegate$lambda$0(AnimFragment animFragment) {
        FragmentActivity fragmentActivity = animFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getSelectMode();
    }

    private final boolean getRoutByFolderSel() {
        return ((Boolean) this.routByFolderSel.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean routByFolderSel_delegate$lambda$1(AnimFragment animFragment) {
        FragmentActivity fragmentActivity = animFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getRoutByFolderSel();
    }

    private final int getMResourcesSize() {
        return ((Number) this.mResourcesSize.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int mResourcesSize_delegate$lambda$2(AnimFragment animFragment) {
        FragmentActivity fragmentActivity = animFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getMSize();
    }

    private final GifDao getGifDao() {
        return (GifDao) this.gifDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GifDao gifDao_delegate$lambda$3() {
        return AppDatabase.INSTANCE.getDatabase().gifDao();
    }

    public final List<String> getMSendResourceIDList() {
        return this.mSendResourceIDList;
    }

    public final void setMSendResourceIDList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mSendResourceIDList = list;
    }

    public final boolean getMIsShowDiy() {
        return this.mIsShowDiy;
    }

    public final void setMIsShowDiy(boolean z) {
        this.mIsShowDiy = z;
    }

    /* renamed from: isBackLoad, reason: from getter */
    public final boolean getIsBackLoad() {
        return this.isBackLoad;
    }

    public final void setBackLoad(boolean z) {
        this.isBackLoad = z;
    }

    public final String getStrPath() {
        return this.strPath;
    }

    public final void setStrPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.strPath = str;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.iv_title_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_title_bg = (ImageView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.tabLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tabLayout = (TabLayout) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.viewPager);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.viewPager = (ViewPager) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.diyRecyclerview);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.diyRecyclerview = (RecyclerView) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.no_data);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.no_data = (LinearLayout) findViewById5;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        this.fragmentList.clear();
        if (getMResourcesSize() == -1) {
            initData();
        } else {
            templateModeBindData();
        }
        if (AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 19) {
            ImageView imageView = this.iv_title_bg;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
                imageView = null;
            }
            imageView.setVisibility(8);
        }
    }

    /* JADX WARN: Type inference failed for: r2v32, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v48, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v53, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v58, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v71, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v77, types: [T, java.lang.String[]] */
    private final void initData() {
        String str;
        boolean z;
        ViewPager viewPager;
        if (AppConfig.INSTANCE.getCid().length() > 0 && AppConfig.INSTANCE.getPid().length() > 0) {
            str = "ALL,YK,Product_" + AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid() + ",CID_" + AppConfig.INSTANCE.getCid();
        } else {
            str = "ALL,YK";
        }
        String str2 = str;
        String str3 = "ALL_N";
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
                if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null && !getSelectMode()) {
                    this.fragmentList.add(DevilsEyeFragment.Companion.newInstance$default(DevilsEyeFragment.INSTANCE, "动画", "EYE_L,EYE_R", "恶魔之眼", 0, 0, 0, 56, null));
                }
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "商业", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "热点", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "表情", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "驾驶", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "时节", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "创意", 0, 0, 0, 56, null));
                TabLayout tabLayout = this.tabLayout;
                if (tabLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout = null;
                }
                tabLayout.setVisibility(0);
                break;
            case 2:
            default:
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "热点", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "表情", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "驾驶", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "时节", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "创意", 0, 0, 0, 56, null));
                TabLayout tabLayout2 = this.tabLayout;
                if (tabLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout2 = null;
                }
                tabLayout2.setVisibility(0);
                break;
            case 3:
            case 4:
            case 5:
                if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null && !getSelectMode()) {
                    this.fragmentList.add(DevilsEyeFragment.Companion.newInstance$default(DevilsEyeFragment.INSTANCE, "动画", "EYE_L,EYE_R", "恶魔之眼", 0, 0, 0, 56, null));
                }
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "热点", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "表情", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "驾驶", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "时节", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "创意", 0, 0, 0, 56, null));
                TabLayout tabLayout3 = this.tabLayout;
                if (tabLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout3 = null;
                }
                tabLayout3.setVisibility(0);
                break;
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
            case 18:
            case 19:
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "iPixels", 0, 0, 0, 56, null));
                TabLayout tabLayout4 = this.tabLayout;
                if (tabLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout4 = null;
                }
                tabLayout4.setVisibility(8);
                break;
            case 9:
            case 10:
            case 11:
            case 14:
            case 15:
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "商业", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "热点", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "表情", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "驾驶", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "时节", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str2, "创意", 0, 0, 0, 56, null));
                TabLayout tabLayout5 = this.tabLayout;
                if (tabLayout5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout5 = null;
                }
                tabLayout5.setVisibility(0);
                break;
            case 12:
                if (AppConfig.INSTANCE.getCid().length() > 0 && AppConfig.INSTANCE.getPid().length() > 0) {
                    str3 = "ALL_N,Product_" + AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid() + ",CID_" + AppConfig.INSTANCE.getCid();
                }
                String str4 = str3;
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "商业", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "热点", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "表情", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "驾驶", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "时节", 0, 0, 0, 56, null));
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str4, "创意", 0, 0, 0, 56, null));
                TabLayout tabLayout6 = this.tabLayout;
                if (tabLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout6 = null;
                }
                tabLayout6.setVisibility(0);
                break;
            case 13:
                if (AppConfig.INSTANCE.getCid().length() > 0 && AppConfig.INSTANCE.getPid().length() > 0) {
                    str3 = "ALL_N,Product_" + AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid() + ",CID_" + AppConfig.INSTANCE.getCid();
                }
                this.fragmentList.add(LoadFragment.Companion.newInstance$default(LoadFragment.INSTANCE, "动画", str3, "iPixels", 0, 0, 0, 56, null));
                TabLayout tabLayout7 = this.tabLayout;
                if (tabLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout7 = null;
                }
                tabLayout7.setVisibility(8);
                break;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (AppConfig.INSTANCE.getLedType() == 1) {
            if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null && !getSelectMode()) {
                String string = getString(R.string.devilsEye);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = getString(R.string.business);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = getString(R.string.hot_spot);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = getString(R.string.emote);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                String string5 = getString(R.string.steer);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String string6 = getString(R.string.season);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                String string7 = getString(R.string.originality);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                objectRef.element = new String[]{string, string2, string3, string4, string5, string6, string7};
            } else {
                String string8 = getString(R.string.business);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                String string9 = getString(R.string.hot_spot);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                String string10 = getString(R.string.emote);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                String string11 = getString(R.string.steer);
                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                String string12 = getString(R.string.season);
                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                String string13 = getString(R.string.originality);
                Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                objectRef.element = new String[]{string8, string9, string10, string11, string12, string13};
            }
            z = true;
        } else {
            z = true;
            if (AppConfig.INSTANCE.getLedType() == 12 || AppConfig.INSTANCE.getLedType() == 9 || AppConfig.INSTANCE.getLedType() == 10 || AppConfig.INSTANCE.getLedType() == 11 || AppConfig.INSTANCE.getLedType() == 14 || AppConfig.INSTANCE.getLedType() == 15) {
                String string14 = getString(R.string.business);
                Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
                String string15 = getString(R.string.hot_spot);
                Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
                String string16 = getString(R.string.emote);
                Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
                String string17 = getString(R.string.steer);
                Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
                String string18 = getString(R.string.season);
                Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
                String string19 = getString(R.string.originality);
                Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
                objectRef.element = new String[]{string14, string15, string16, string17, string18, string19};
            } else if (AppConfig.INSTANCE.getLedType() == 3 || AppConfig.INSTANCE.getLedType() == 4 || AppConfig.INSTANCE.getLedType() == 5) {
                if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null && !getSelectMode()) {
                    String string20 = getString(R.string.devilsEye);
                    Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
                    String string21 = getString(R.string.hot_spot);
                    Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
                    String string22 = getString(R.string.emote);
                    Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
                    String string23 = getString(R.string.steer);
                    Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
                    String string24 = getString(R.string.season);
                    Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
                    String string25 = getString(R.string.originality);
                    Intrinsics.checkNotNullExpressionValue(string25, "getString(...)");
                    objectRef.element = new String[]{string20, string21, string22, string23, string24, string25};
                } else {
                    String string26 = getString(R.string.hot_spot);
                    Intrinsics.checkNotNullExpressionValue(string26, "getString(...)");
                    String string27 = getString(R.string.emote);
                    Intrinsics.checkNotNullExpressionValue(string27, "getString(...)");
                    String string28 = getString(R.string.steer);
                    Intrinsics.checkNotNullExpressionValue(string28, "getString(...)");
                    String string29 = getString(R.string.season);
                    Intrinsics.checkNotNullExpressionValue(string29, "getString(...)");
                    String string30 = getString(R.string.originality);
                    Intrinsics.checkNotNullExpressionValue(string30, "getString(...)");
                    objectRef.element = new String[]{string26, string27, string28, string29, string30};
                }
            } else {
                String string31 = getString(R.string.hot_spot);
                Intrinsics.checkNotNullExpressionValue(string31, "getString(...)");
                String string32 = getString(R.string.emote);
                Intrinsics.checkNotNullExpressionValue(string32, "getString(...)");
                String string33 = getString(R.string.steer);
                Intrinsics.checkNotNullExpressionValue(string33, "getString(...)");
                String string34 = getString(R.string.season);
                Intrinsics.checkNotNullExpressionValue(string34, "getString(...)");
                String string35 = getString(R.string.originality);
                Intrinsics.checkNotNullExpressionValue(string35, "getString(...)");
                objectRef.element = new String[]{string31, string32, string33, string34, string35};
            }
        }
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        final FragmentManager childFragmentManager = getChildFragmentManager();
        viewPager2.setAdapter(new FragmentStatePagerAdapter(childFragmentManager) { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$initData$1
            @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup container, int position, Object object) {
                Intrinsics.checkNotNullParameter(container, "container");
                Intrinsics.checkNotNullParameter(object, "object");
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                List list;
                list = AnimFragment.this.fragmentList;
                return list.size();
            }

            @Override // androidx.fragment.app.FragmentStatePagerAdapter
            public Fragment getItem(int position) {
                List list;
                list = AnimFragment.this.fragmentList;
                return (Fragment) list.get(position);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public CharSequence getPageTitle(int position) {
                return objectRef.element[position];
            }
        });
        TabLayout tabLayout8 = this.tabLayout;
        if (tabLayout8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            tabLayout8 = null;
        }
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager3 = null;
        }
        boolean z2 = z;
        tabLayout8.setupWithViewPager(viewPager3, z2);
        ViewPager viewPager4 = this.viewPager;
        if (viewPager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        } else {
            viewPager = viewPager4;
        }
        viewPager.setOffscreenPageLimit(z2 ? 1 : 0);
        if (AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 19) {
            return;
        }
        initDiy(false);
    }

    private final void templateModeBindData() {
        String str;
        if (AppConfig.INSTANCE.getCid().length() > 0 && AppConfig.INSTANCE.getPid().length() > 0) {
            str = "ALL,YK,Product_" + AppConfig.INSTANCE.getCid() + AppConfig.INSTANCE.getPid() + ",CID_" + AppConfig.INSTANCE.getCid();
        } else {
            str = "ALL,YK";
        }
        String str2 = str;
        ViewPager viewPager = null;
        switch (getMResourcesSize()) {
            case 9:
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "iPixels", 24, 24, 2));
                TabLayout tabLayout = this.tabLayout;
                if (tabLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout = null;
                }
                tabLayout.setVisibility(8);
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "热点", 32, 32, 2));
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "表情", 32, 32, 2));
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "驾驶", 32, 32, 2));
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "时节", 32, 32, 2));
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("动画", str2, "创意", 32, 32, 2));
                TabLayout tabLayout2 = this.tabLayout;
                if (tabLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                    tabLayout2 = null;
                }
                tabLayout2.setVisibility(0);
                break;
        }
        final String[] strArr = {getString(R.string.hot_spot), getString(R.string.emote), getString(R.string.steer), getString(R.string.season), getString(R.string.originality)};
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        final FragmentManager childFragmentManager = getChildFragmentManager();
        viewPager2.setAdapter(new FragmentStatePagerAdapter(childFragmentManager) { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$templateModeBindData$1
            @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup container, int position, Object object) {
                Intrinsics.checkNotNullParameter(container, "container");
                Intrinsics.checkNotNullParameter(object, "object");
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                List list;
                list = AnimFragment.this.fragmentList;
                return list.size();
            }

            @Override // androidx.fragment.app.FragmentStatePagerAdapter
            public Fragment getItem(int position) {
                List list;
                list = AnimFragment.this.fragmentList;
                return (Fragment) list.get(position);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public CharSequence getPageTitle(int position) {
                return strArr[position];
            }
        });
        TabLayout tabLayout3 = this.tabLayout;
        if (tabLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            tabLayout3 = null;
        }
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager3 = null;
        }
        tabLayout3.setupWithViewPager(viewPager3, true);
        ViewPager viewPager4 = this.viewPager;
        if (viewPager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager = viewPager4;
        }
        viewPager.setOffscreenPageLimit(1);
        initDiy(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void initDiy(boolean r11) {
        /*
            Method dump skipped, instructions count: 632
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.gallery.AnimFragment.initDiy(boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDiy$lambda$5(AnimFragment animFragment, ViewGroup viewGroup, View view, Gif gif, int i) {
        String url = gif.getUrl();
        animFragment.strPath = url;
        byte[] readFileBytes = FileUtil.readFileBytes(url);
        Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
        animFragment.bgrData = readFileBytes;
        if (!animFragment.getSelectMode()) {
            AppConfig.INSTANCE.setSend(true);
            if (AppConfig.INSTANCE.getMcu() > 4) {
                try {
                    Log.v("ruis", "sendGifData---sendGif--");
                    animFragment.sendGif(animFragment.bgrData, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Intent intent = new Intent();
            EventBus.getDefault().postSticky(GifCore.decodeGif(animFragment.strPath));
            intent.putExtra("sendData", animFragment.bgrData);
            intent.putExtra("sendType", 1);
            animFragment.mActivity.setResult(-1, intent);
            animFragment.mActivity.finish();
        }
        RecyclerAdapter<Gif> recyclerAdapter = null;
        if (animFragment.m_iSelect != -1) {
            RecyclerAdapter<Gif> recyclerAdapter2 = animFragment.diyAdapter;
            if (recyclerAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
                recyclerAdapter2 = null;
            }
            recyclerAdapter2.notifyItemChanged(animFragment.m_iSelect);
        }
        animFragment.m_iSelect = i;
        RecyclerAdapter<Gif> recyclerAdapter3 = animFragment.diyAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
        } else {
            recyclerAdapter = recyclerAdapter3;
        }
        recyclerAdapter.notifyItemChanged(animFragment.m_iSelect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initDiy$lambda$7(final AnimFragment animFragment, ViewGroup viewGroup, View view, final Gif gif, final int i) {
        String string = animFragment.getString(R.string.is_delete);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showDialog$default(animFragment, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initDiy$lambda$7$lambda$6;
                initDiy$lambda$7$lambda$6 = AnimFragment.initDiy$lambda$7$lambda$6(AnimFragment.this, gif, i);
                return initDiy$lambda$7$lambda$6;
            }
        }, 1, null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initDiy$lambda$7$lambda$6(AnimFragment animFragment, Gif gif, int i) {
        String url = gif.getUrl();
        animFragment.strPath = url;
        byte[] readFileBytes = FileUtil.readFileBytes(url);
        Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
        animFragment.bgrData = readFileBytes;
        animFragment.getGifDao().delete(animFragment.diyGifs.get(i));
        animFragment.diyGifs.remove(i);
        RecyclerAdapter<Gif> recyclerAdapter = null;
        if (animFragment.diyGifs.isEmpty()) {
            LinearLayout linearLayout = animFragment.no_data;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
                linearLayout = null;
            }
            UtilsExtensionKt.show(linearLayout);
        } else {
            LinearLayout linearLayout2 = animFragment.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
                linearLayout2 = null;
            }
            UtilsExtensionKt.hide(linearLayout2);
        }
        RecyclerAdapter<Gif> recyclerAdapter2 = animFragment.diyAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
        } else {
            recyclerAdapter = recyclerAdapter2;
        }
        recyclerAdapter.notifyDataSetChanged();
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        ImageView imageView = this.iv_title_bg;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnimFragment.bindListener$lambda$8(Ref.BooleanRef.this, this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView3 = this.iv_title_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
        } else {
            imageView2 = imageView3;
        }
        companion.attachViewOnTouchListener(imageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$8(Ref.BooleanRef booleanRef, AnimFragment animFragment, View view) {
        RecyclerView recyclerView = animFragment.diyRecyclerview;
        LinearLayout linearLayout = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView = null;
        }
        booleanRef.element = recyclerView.getVisibility() == 0;
        booleanRef.element = !booleanRef.element;
        view.setSelected(booleanRef.element);
        animFragment.mIsShowDiy = booleanRef.element;
        boolean z = booleanRef.element;
        if (!z) {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            ViewPager viewPager = animFragment.viewPager;
            if (viewPager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager = null;
            }
            viewPager.setVisibility(0);
            RecyclerView recyclerView2 = animFragment.diyRecyclerview;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(8);
            LinearLayout linearLayout2 = animFragment.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout2;
            }
            UtilsExtensionKt.hide(linearLayout);
            return;
        }
        ViewPager viewPager2 = animFragment.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        viewPager2.setVisibility(8);
        RecyclerView recyclerView3 = animFragment.diyRecyclerview;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView3 = null;
        }
        recyclerView3.setVisibility(0);
        if (animFragment.diyGifs.isEmpty()) {
            LinearLayout linearLayout3 = animFragment.no_data;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout3;
            }
            UtilsExtensionKt.show(linearLayout);
            return;
        }
        LinearLayout linearLayout4 = animFragment.no_data;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("no_data");
        } else {
            linearLayout = linearLayout4;
        }
        UtilsExtensionKt.hide(linearLayout);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        }
        ImageView imageView = this.iv_title_bg;
        LinearLayout linearLayout = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
            imageView = null;
        }
        imageView.setSelected(this.mIsShowDiy);
        boolean z = this.mIsShowDiy;
        if (!z) {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager = null;
            }
            UtilsExtensionKt.show(viewPager);
            RecyclerView recyclerView = this.diyRecyclerview;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                recyclerView = null;
            }
            UtilsExtensionKt.hide(recyclerView);
            LinearLayout linearLayout2 = this.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout2;
            }
            UtilsExtensionKt.hide(linearLayout);
            return;
        }
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        UtilsExtensionKt.hide(viewPager2);
        RecyclerView recyclerView2 = this.diyRecyclerview;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView2 = null;
        }
        UtilsExtensionKt.show(recyclerView2);
        if (this.diyGifs.isEmpty()) {
            LinearLayout linearLayout3 = this.no_data;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout3;
            }
            UtilsExtensionKt.show(linearLayout);
            return;
        }
        LinearLayout linearLayout4 = this.no_data;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("no_data");
        } else {
            linearLayout = linearLayout4;
        }
        UtilsExtensionKt.hide(linearLayout);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isBackLoad) {
            showDiyImage(!getRoutByFolderSel());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.isBackLoad = false;
        this.diyGifs.clear();
        this.mActivity = null;
        this.fragmentList.clear();
    }

    static /* synthetic */ void showDiyImage$default(AnimFragment animFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        animFragment.showDiyImage(z);
    }

    private final void showDiyImage(boolean isShowDiy) {
        int i;
        RecyclerAdapter<Gif> recyclerAdapter = this.diyAdapter;
        LinearLayout linearLayout = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        this.mIsShowDiy = isShowDiy;
        Log.v("ruis", "!selectMode====" + (!isShowDiy));
        if (!getSelectMode()) {
            Iterator<Gif> it = this.diyGifs.iterator();
            if (it.hasNext()) {
                it.next();
                List<Gif> list = this.diyGifs;
                Iterator<Gif> it2 = list.iterator();
                if (it2.hasNext()) {
                    it2.next();
                    i = 0;
                } else {
                    i = -1;
                }
                String url = list.get(i).getUrl();
                this.strPath = url;
                byte[] readFileBytes = FileUtil.readFileBytes(url);
                Intrinsics.checkNotNullExpressionValue(readFileBytes, "readFileBytes(...)");
                this.bgrData = readFileBytes;
                itemClick(readFileBytes);
            } else {
                ToastUtil.show(getString(R.string.msg_send_fail));
                return;
            }
        }
        ImageView imageView = this.iv_title_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
            imageView = null;
        }
        imageView.setSelected(isShowDiy);
        if (!isShowDiy) {
            if (isShowDiy) {
                throw new NoWhenBranchMatchedException();
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager = null;
            }
            UtilsExtensionKt.show(viewPager);
            RecyclerView recyclerView = this.diyRecyclerview;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                recyclerView = null;
            }
            UtilsExtensionKt.hide(recyclerView);
            LinearLayout linearLayout2 = this.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout2;
            }
            UtilsExtensionKt.hide(linearLayout);
            return;
        }
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        UtilsExtensionKt.hide(viewPager2);
        RecyclerView recyclerView2 = this.diyRecyclerview;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView2 = null;
        }
        UtilsExtensionKt.show(recyclerView2);
        if (this.diyGifs.isEmpty()) {
            LinearLayout linearLayout3 = this.no_data;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout3;
            }
            UtilsExtensionKt.show(linearLayout);
            return;
        }
        LinearLayout linearLayout4 = this.no_data;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("no_data");
        } else {
            linearLayout = linearLayout4;
        }
        UtilsExtensionKt.hide(linearLayout);
    }

    public static /* synthetic */ void resetSel$default(AnimFragment animFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        animFragment.resetSel(z);
    }

    public final void resetSel(boolean isShowDiy) {
        this.m_iSelect = -1;
        this.mIsShowDiy = isShowDiy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [T, com.wifiled.ipixels.ui.gallery.DevilsEyeFragment] */
    public final void sendData() {
        ViewPager viewPager;
        if (this.mIsShowDiy) {
            if (-1 == this.m_iSelect) {
                return;
            }
            ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda41
                @Override // java.lang.Runnable
                public final void run() {
                    AnimFragment.sendData$lambda$11(AnimFragment.this);
                }
            });
            return;
        }
        if (!this.fragmentList.isEmpty() && (viewPager = this.viewPager) != null) {
            List<LoadFragment> list = this.fragmentList;
            ViewPager viewPager2 = null;
            if (viewPager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager = null;
            }
            if (list.get(viewPager.getCurrentItem()) instanceof DevilsEyeFragment) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                List<LoadFragment> list2 = this.fragmentList;
                ViewPager viewPager3 = this.viewPager;
                if (viewPager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                } else {
                    viewPager2 = viewPager3;
                }
                LoadFragment loadFragment = list2.get(viewPager2.getCurrentItem());
                Intrinsics.checkNotNull(loadFragment, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.DevilsEyeFragment");
                objectRef.element = (DevilsEyeFragment) loadFragment;
                this.mSendResourceIDList.add(((DevilsEyeFragment) objectRef.element).getMFileId1());
                this.mSendResourceIDList.add(((DevilsEyeFragment) objectRef.element).getMFileId2());
                if (((DevilsEyeFragment) objectRef.element).getMLeftRGBData() == null || ((DevilsEyeFragment) objectRef.element).getMRightRGBData2() == null) {
                    return;
                }
                ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda42
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnimFragment.sendData$lambda$12(AnimFragment.this, objectRef);
                    }
                });
                return;
            }
            List<LoadFragment> list3 = this.fragmentList;
            ViewPager viewPager4 = this.viewPager;
            if (viewPager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager4 = null;
            }
            final byte[] bgrData = list3.get(viewPager4.getCurrentItem()).getBgrData();
            if (bgrData != null) {
                ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda43
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnimFragment.this.itemClick(bgrData);
                    }
                });
            }
            List<String> list4 = this.mSendResourceIDList;
            List<LoadFragment> list5 = this.fragmentList;
            ViewPager viewPager5 = this.viewPager;
            if (viewPager5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            } else {
                viewPager2 = viewPager5;
            }
            list4.add(list5.get(viewPager2.getCurrentItem()).getMFileId());
            return;
        }
        if (isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$15;
                    sendData$lambda$15 = AnimFragment.sendData$lambda$15(AnimFragment.this);
                    return sendData$lambda$15;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendData$lambda$11(AnimFragment animFragment) {
        animFragment.itemClick(animFragment.bgrData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void sendData$lambda$12(AnimFragment animFragment, Ref.ObjectRef objectRef) {
        byte[] mLeftRGBData = ((DevilsEyeFragment) objectRef.element).getMLeftRGBData();
        Intrinsics.checkNotNull(mLeftRGBData);
        byte[] mRightRGBData2 = ((DevilsEyeFragment) objectRef.element).getMRightRGBData2();
        Intrinsics.checkNotNull(mRightRGBData2);
        animFragment.dowaloadEyeToDevice(mLeftRGBData, mRightRGBData2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$15(AnimFragment animFragment) {
        ToastUtil.show(animFragment.getString(R.string.msg_data_error));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendGif$default(AnimFragment animFragment, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        animFragment.sendGif(bArr, z);
    }

    private final void sendGif(byte[] data, boolean isDown) {
        this.mSendGifData = data;
        this.mSendGifIsDown = isDown;
        if (AppConfig.INSTANCE.getConnectType() != -1) {
            SendCore.INSTANCE.sendGifData(isDown, data, this.sendGifCallBack);
        } else if (isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGif$lambda$16;
                    sendGif$lambda$16 = AnimFragment.sendGif$lambda$16(AnimFragment.this);
                    return sendGif$lambda$16;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGif$lambda$16(AnimFragment animFragment) {
        animFragment.toast(animFragment.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendGif2$default(AnimFragment animFragment, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        animFragment.sendGif2(bArr, z);
    }

    private final void sendGif2(byte[] data, boolean isDown) {
        this.mSendGifData = data;
        this.mSendGifIsDown = isDown;
        if (AppConfig.INSTANCE.getConnectType2() != -1) {
            SendCore.INSTANCE.sendGifData2(isDown, data, this.sendGifCallBack);
        } else if (isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGif2$lambda$17;
                    sendGif2$lambda$17 = AnimFragment.sendGif2$lambda$17(AnimFragment.this);
                    return sendGif2$lambda$17;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGif2$lambda$17(AnimFragment animFragment) {
        animFragment.toast(animFragment.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30(final AnimFragment animFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$30$lambda$19;
                sendGifCallBack$lambda$30$lambda$19 = AnimFragment.sendGifCallBack$lambda$30$lambda$19(AnimFragment.this);
                return sendGifCallBack$lambda$30$lambda$19;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$30$lambda$21;
                sendGifCallBack$lambda$30$lambda$21 = AnimFragment.sendGifCallBack$lambda$30$lambda$21(AnimFragment.this, ((Integer) obj).intValue());
                return sendGifCallBack$lambda$30$lambda$21;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$30$lambda$23;
                sendGifCallBack$lambda$30$lambda$23 = AnimFragment.sendGifCallBack$lambda$30$lambda$23(AnimFragment.this);
                return sendGifCallBack$lambda$30$lambda$23;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$30$lambda$25;
                sendGifCallBack$lambda$30$lambda$25 = AnimFragment.sendGifCallBack$lambda$30$lambda$25(AnimFragment.this, ((Integer) obj).intValue());
                return sendGifCallBack$lambda$30$lambda$25;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$30$lambda$29;
                sendGifCallBack$lambda$30$lambda$29 = AnimFragment.sendGifCallBack$lambda$30$lambda$29(AnimFragment.this, (byte[]) obj);
                return sendGifCallBack$lambda$30$lambda$29;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$19(final AnimFragment animFragment) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$30$lambda$19$lambda$18;
                sendGifCallBack$lambda$30$lambda$19$lambda$18 = AnimFragment.sendGifCallBack$lambda$30$lambda$19$lambda$18(AnimFragment.this);
                return sendGifCallBack$lambda$30$lambda$19$lambda$18;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$19$lambda$18(AnimFragment animFragment) {
        String string = animFragment.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$21(final AnimFragment animFragment, final int i) {
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$30$lambda$21$lambda$20;
                    sendGifCallBack$lambda$30$lambda$21$lambda$20 = AnimFragment.sendGifCallBack$lambda$30$lambda$21$lambda$20(AnimFragment.this, i);
                    return sendGifCallBack$lambda$30$lambda$21$lambda$20;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$21$lambda$20(AnimFragment animFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, animFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$23(final AnimFragment animFragment) {
        String filePath;
        if (animFragment.mSendGifIsDown) {
            try {
                if (animFragment.mIsShowDiy) {
                    filePath = animFragment.strPath;
                } else {
                    List<LoadFragment> list = animFragment.fragmentList;
                    ViewPager viewPager = animFragment.viewPager;
                    if (viewPager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                        viewPager = null;
                    }
                    filePath = list.get(viewPager.getCurrentItem()).getFilePath();
                }
                ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.GiftView(false, filePath, String.valueOf(ChannelIndex.INSTANCE.index()), false, animFragment.mSendGifData));
                FragmentActivity activity = animFragment.getActivity();
                String str = "channel_data";
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 1:
                        str = "channel_data_96";
                        break;
                    case 2:
                        str = "channel_data_32";
                        break;
                    case 3:
                        str = "channel_data_16";
                        break;
                    case 4:
                        str = "channel_data_12";
                        break;
                    case 5:
                        str = "channel_data_20";
                        break;
                    case 6:
                        str = "channel_data_128";
                        break;
                    case 7:
                        str = "channel_data_144";
                        break;
                    case 8:
                        str = "channel_data_192";
                        break;
                    case 9:
                        str = "channel_data_24_48";
                        break;
                    case 10:
                        str = "channel_data_32_64";
                        break;
                    case 11:
                        str = "channel_data_32_96";
                        break;
                    case 12:
                        str = "channel_data_128_2";
                        break;
                    case 13:
                        str = "channel_data_32_96_2";
                        break;
                    case 14:
                        str = "channel_data_32_160";
                        break;
                    case 15:
                        str = "channel_data_32_192";
                        break;
                    case 16:
                        str = "channel_data_32_256";
                        break;
                    case 17:
                        str = "channel_data_32_320";
                        break;
                    case 18:
                        str = "channel_data_32_384";
                        break;
                    case 19:
                        str = "channel_data_32_448";
                        break;
                }
                SPUtils.put(activity, str, ChannelIndex.INSTANCE.mapSaveChannel());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$30$lambda$23$lambda$22;
                sendGifCallBack$lambda$30$lambda$23$lambda$22 = AnimFragment.sendGifCallBack$lambda$30$lambda$23$lambda$22(AnimFragment.this);
                return sendGifCallBack$lambda$30$lambda$23$lambda$22;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$23$lambda$22(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$25(final AnimFragment animFragment, final int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$30$lambda$25$lambda$24;
                    sendGifCallBack$lambda$30$lambda$25$lambda$24 = AnimFragment.sendGifCallBack$lambda$30$lambda$25$lambda$24(AnimFragment.this, i);
                    return sendGifCallBack$lambda$30$lambda$25$lambda$24;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$25$lambda$24(AnimFragment animFragment, int i) {
        ToastUtil.show(animFragment.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$29(final AnimFragment animFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.logd("strResponse: ".concat(str), new Object[0]);
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$30$lambda$29$lambda$26;
                    sendGifCallBack$lambda$30$lambda$29$lambda$26 = AnimFragment.sendGifCallBack$lambda$30$lambda$29$lambda$26(AnimFragment.this);
                    return sendGifCallBack$lambda$30$lambda$29$lambda$26;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$30$lambda$29$lambda$27;
                    sendGifCallBack$lambda$30$lambda$29$lambda$27 = AnimFragment.sendGifCallBack$lambda$30$lambda$29$lambda$27(AnimFragment.this);
                    return sendGifCallBack$lambda$30$lambda$29$lambda$27;
                }
            }, 0L, 2, null);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:空间不足 ", new Object[0]);
            UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        } else if (b == 3) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:保存文件成功 ", new Object[0]);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$30$lambda$29$lambda$28;
                    sendGifCallBack$lambda$30$lambda$29$lambda$28 = AnimFragment.sendGifCallBack$lambda$30$lambda$29$lambda$28(AnimFragment.this);
                    return sendGifCallBack$lambda$30$lambda$29$lambda$28;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$29$lambda$26(AnimFragment animFragment) {
        if (animFragment.isAdded()) {
            String string = animFragment.getString(R.string.msg_send_fail);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 1, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$29$lambda$27(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$30$lambda$29$lambda$28(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43(final AnimFragment animFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLeftEyeCallBack$lambda$43$lambda$32;
                sendLeftEyeCallBack$lambda$43$lambda$32 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$32(AnimFragment.this);
                return sendLeftEyeCallBack$lambda$43$lambda$32;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLeftEyeCallBack$lambda$43$lambda$34;
                sendLeftEyeCallBack$lambda$43$lambda$34 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$34(AnimFragment.this, ((Integer) obj).intValue());
                return sendLeftEyeCallBack$lambda$43$lambda$34;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLeftEyeCallBack$lambda$43$lambda$36;
                sendLeftEyeCallBack$lambda$43$lambda$36 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$36(AnimFragment.this);
                return sendLeftEyeCallBack$lambda$43$lambda$36;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLeftEyeCallBack$lambda$43$lambda$38;
                sendLeftEyeCallBack$lambda$43$lambda$38 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$38(AnimFragment.this, ((Integer) obj).intValue());
                return sendLeftEyeCallBack$lambda$43$lambda$38;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLeftEyeCallBack$lambda$43$lambda$42;
                sendLeftEyeCallBack$lambda$43$lambda$42 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$42(AnimFragment.this, (byte[]) obj);
                return sendLeftEyeCallBack$lambda$43$lambda$42;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$32(final AnimFragment animFragment) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLeftEyeCallBack$lambda$43$lambda$32$lambda$31;
                sendLeftEyeCallBack$lambda$43$lambda$32$lambda$31 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$32$lambda$31(AnimFragment.this);
                return sendLeftEyeCallBack$lambda$43$lambda$32$lambda$31;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$32$lambda$31(AnimFragment animFragment) {
        String string = animFragment.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$34(final AnimFragment animFragment, final int i) {
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$34$lambda$33;
                    sendLeftEyeCallBack$lambda$43$lambda$34$lambda$33 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$34$lambda$33(AnimFragment.this, i);
                    return sendLeftEyeCallBack$lambda$43$lambda$34$lambda$33;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$34$lambda$33(AnimFragment animFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, animFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$36(final AnimFragment animFragment) {
        com.blankj.utilcode.util.LogUtils.vTag("ruis", "AnimFragment -----onCompleted");
        animFragment.mLeftSendOver = true;
        if (animFragment.mRightSendOver) {
            SendCore.INSTANCE.sendSynchronization();
            animFragment.mLeftSendOver = false;
            animFragment.mRightSendOver = false;
        }
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$36$lambda$35;
                    sendLeftEyeCallBack$lambda$43$lambda$36$lambda$35 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$36$lambda$35(AnimFragment.this);
                    return sendLeftEyeCallBack$lambda$43$lambda$36$lambda$35;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$36$lambda$35(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$38(final AnimFragment animFragment, final int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$38$lambda$37;
                    sendLeftEyeCallBack$lambda$43$lambda$38$lambda$37 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$38$lambda$37(AnimFragment.this, i);
                    return sendLeftEyeCallBack$lambda$43$lambda$38$lambda$37;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$38$lambda$37(AnimFragment animFragment, int i) {
        ToastUtil.show(animFragment.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$42(final AnimFragment animFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.logd("strResponse: ".concat(str), new Object[0]);
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$39;
                    sendLeftEyeCallBack$lambda$43$lambda$42$lambda$39 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$42$lambda$39(AnimFragment.this);
                    return sendLeftEyeCallBack$lambda$43$lambda$42$lambda$39;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$40;
                    sendLeftEyeCallBack$lambda$43$lambda$42$lambda$40 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$42$lambda$40(AnimFragment.this);
                    return sendLeftEyeCallBack$lambda$43$lambda$42$lambda$40;
                }
            }, 0L, 2, null);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:空间不足 ", new Object[0]);
            UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        } else if (b == 3) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:保存文件成功 ", new Object[0]);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$41;
                    sendLeftEyeCallBack$lambda$43$lambda$42$lambda$41 = AnimFragment.sendLeftEyeCallBack$lambda$43$lambda$42$lambda$41(AnimFragment.this);
                    return sendLeftEyeCallBack$lambda$43$lambda$42$lambda$41;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$39(AnimFragment animFragment) {
        if (animFragment.isAdded()) {
            String string = animFragment.getString(R.string.msg_send_fail);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 1, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$40(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLeftEyeCallBack$lambda$43$lambda$42$lambda$41(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56(final AnimFragment animFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendRightEyeCallBack$lambda$56$lambda$45;
                sendRightEyeCallBack$lambda$56$lambda$45 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$45(AnimFragment.this);
                return sendRightEyeCallBack$lambda$56$lambda$45;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendRightEyeCallBack$lambda$56$lambda$47;
                sendRightEyeCallBack$lambda$56$lambda$47 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$47(AnimFragment.this, ((Integer) obj).intValue());
                return sendRightEyeCallBack$lambda$56$lambda$47;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendRightEyeCallBack$lambda$56$lambda$49;
                sendRightEyeCallBack$lambda$56$lambda$49 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$49(AnimFragment.this);
                return sendRightEyeCallBack$lambda$56$lambda$49;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendRightEyeCallBack$lambda$56$lambda$51;
                sendRightEyeCallBack$lambda$56$lambda$51 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$51(AnimFragment.this, ((Integer) obj).intValue());
                return sendRightEyeCallBack$lambda$56$lambda$51;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendRightEyeCallBack$lambda$56$lambda$55;
                sendRightEyeCallBack$lambda$56$lambda$55 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$55(AnimFragment.this, (byte[]) obj);
                return sendRightEyeCallBack$lambda$56$lambda$55;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$45(final AnimFragment animFragment) {
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$45$lambda$44;
                    sendRightEyeCallBack$lambda$56$lambda$45$lambda$44 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$45$lambda$44(AnimFragment.this);
                    return sendRightEyeCallBack$lambda$56$lambda$45$lambda$44;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$45$lambda$44(AnimFragment animFragment) {
        String string = animFragment.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$47(final AnimFragment animFragment, final int i) {
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$47$lambda$46;
                    sendRightEyeCallBack$lambda$56$lambda$47$lambda$46 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$47$lambda$46(AnimFragment.this, i);
                    return sendRightEyeCallBack$lambda$56$lambda$47$lambda$46;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$47$lambda$46(AnimFragment animFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, animFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$49(final AnimFragment animFragment) {
        com.blankj.utilcode.util.LogUtils.vTag("ruis", "AnimFragment -----onCompleted");
        animFragment.mRightSendOver = true;
        if (animFragment.mLeftSendOver) {
            SendCore.INSTANCE.sendSynchronization();
            animFragment.mLeftSendOver = false;
            animFragment.mRightSendOver = false;
        }
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$49$lambda$48;
                    sendRightEyeCallBack$lambda$56$lambda$49$lambda$48 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$49$lambda$48(AnimFragment.this);
                    return sendRightEyeCallBack$lambda$56$lambda$49$lambda$48;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$49$lambda$48(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$51(final AnimFragment animFragment, final int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        if (animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$51$lambda$50;
                    sendRightEyeCallBack$lambda$56$lambda$51$lambda$50 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$51$lambda$50(AnimFragment.this, i);
                    return sendRightEyeCallBack$lambda$56$lambda$51$lambda$50;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$51$lambda$50(AnimFragment animFragment, int i) {
        ToastUtil.show(animFragment.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$55(final AnimFragment animFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.logd("strResponse: ".concat(str), new Object[0]);
        String str2 = str;
        if ((StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) && animFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$52;
                    sendRightEyeCallBack$lambda$56$lambda$55$lambda$52 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$55$lambda$52(AnimFragment.this);
                    return sendRightEyeCallBack$lambda$56$lambda$55$lambda$52;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$53;
                    sendRightEyeCallBack$lambda$56$lambda$55$lambda$53 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$55$lambda$53(AnimFragment.this);
                    return sendRightEyeCallBack$lambda$56$lambda$55$lambda$53;
                }
            }, 0L, 2, null);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:空间不足 ", new Object[0]);
            UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        } else if (b == 3) {
            LogUtils.logi(animFragment.TAG + ">>>[onResult]:保存文件成功 ", new Object[0]);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$54;
                    sendRightEyeCallBack$lambda$56$lambda$55$lambda$54 = AnimFragment.sendRightEyeCallBack$lambda$56$lambda$55$lambda$54(AnimFragment.this);
                    return sendRightEyeCallBack$lambda$56$lambda$55$lambda$54;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$52(AnimFragment animFragment) {
        String string = animFragment.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$53(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendRightEyeCallBack$lambda$56$lambda$55$lambda$54(AnimFragment animFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) animFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void itemClick(byte[] data) {
        if (ChannelIndex.INSTANCE.mapSaveChannel().size() >= 100) {
            Handler handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda25
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnimFragment.itemClick$lambda$57();
                    }
                });
                return;
            }
            return;
        }
        if (!getSelectMode()) {
            if (AppConfig.INSTANCE.getMcu() > 4) {
                ArrayList arrayList = new ArrayList();
                arrayList.add((byte) 7);
                arrayList.add((byte) 0);
                arrayList.add((byte) 8);
                arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
                arrayList.add((byte) 1);
                arrayList.add((byte) 0);
                ChannelIndex.INSTANCE.inc();
                arrayList.add(Byte.valueOf((byte) ChannelIndex.INSTANCE.index()));
                if (AppConfig.INSTANCE.getConnectType() != -1) {
                    SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new AnimFragment$itemClick$2(this, data));
                }
                if (AppConfig.INSTANCE.getConnectType2() != -1) {
                    SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList), new AnimFragment$itemClick$3(this, data));
                    return;
                }
                return;
            }
            sendGif$default(this, data, false, 2, null);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("bgr", data);
        this.mActivity.setResult(-1, intent);
        this.mActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void itemClick$lambda$57() {
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "channel low space".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }

    private final void dowaloadEyeToDevice(byte[] leftData, byte[] rightData) {
        if (ChannelIndex.INSTANCE.mapSaveChannel().size() >= 100) {
            Handler handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.AnimFragment$$ExternalSyntheticLambda37
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnimFragment.dowaloadEyeToDevice$lambda$58();
                    }
                });
                return;
            }
            return;
        }
        this.mSendEyeLeftData = leftData;
        this.mSendEyeRightData = rightData;
        if (AppConfig.INSTANCE.getMcu() > 4) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((byte) 7);
            arrayList.add((byte) 0);
            arrayList.add((byte) 8);
            arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
            arrayList.add((byte) 1);
            arrayList.add((byte) 0);
            ChannelIndex.INSTANCE.inc();
            arrayList.add(Byte.valueOf((byte) ChannelIndex.INSTANCE.index()));
            ArrayList arrayList2 = arrayList;
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList2), new AnimFragment$dowaloadEyeToDevice$2(leftData, this, rightData));
            SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList2), new AnimFragment$dowaloadEyeToDevice$3(rightData, this, leftData));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dowaloadEyeToDevice$lambda$58() {
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "channel low space".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }
}
