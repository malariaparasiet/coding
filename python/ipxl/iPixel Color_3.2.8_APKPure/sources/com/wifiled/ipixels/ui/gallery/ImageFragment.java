package com.wifiled.ipixels.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.BitmapUtil;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.db.AppDatabase;
import com.wifiled.ipixels.db.ImageDao;
import com.wifiled.ipixels.model.Image;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.ByteUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.HandlerUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

/* compiled from: ImageFragment.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010;\u001a\u00020\u0018H\u0014J\b\u0010<\u001a\u00020=H\u0014J\b\u0010>\u001a\u00020=H\u0014J\b\u0010?\u001a\u00020=H\u0002J\u0010\u0010@\u001a\u00020=2\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020=H\u0016J\u0010\u0010D\u001a\u00020=2\u0006\u0010E\u001a\u00020\u0010H\u0016J\b\u0010F\u001a\u00020=H\u0016J\b\u0010G\u001a\u00020=H\u0016J\b\u0010H\u001a\u00020=H\u0016J\u0012\u0010L\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020\u0010H\u0002J\b\u0010N\u001a\u00020=H\u0002J\b\u0010Q\u001a\u00020=H\u0002J\b\u0010R\u001a\u00020=H\u0002J\u001a\u0010S\u001a\u00020=2\u0006\u0010T\u001a\u00020\u001d2\b\b\u0002\u0010U\u001a\u00020\u0010H\u0002J\u001a\u0010V\u001a\u00020=2\u0006\u0010T\u001a\u00020\u001d2\b\b\u0002\u0010U\u001a\u00020\u0010H\u0002J\b\u0010W\u001a\u00020=H\u0014J\u0010\u0010X\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020\u0010J\u0006\u0010Y\u001a\u00020=J\u0010\u0010^\u001a\u00020=2\u0006\u0010T\u001a\u00020\u001dH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0015\u0010\u0012R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0012\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010I\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0012\"\u0004\bK\u00109R\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010Z\u001a\u0013\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020=0[¢\u0006\u0002\b]X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/ImageFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "diyAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/model/Image;", "diyImages", "", "imageDao", "Lcom/wifiled/ipixels/db/ImageDao;", "getImageDao", "()Lcom/wifiled/ipixels/db/ImageDao;", "imageDao$delegate", "Lkotlin/Lazy;", "selectMode", "", "getSelectMode", "()Z", "selectMode$delegate", "routByFolderSel", "getRoutByFolderSel", "routByFolderSel$delegate", "mResourcesSize", "", "getMResourcesSize", "()I", "mResourcesSize$delegate", "bgrData", "", "mSendResourceIDList", "", "getMSendResourceIDList", "()Ljava/util/List;", "setMSendResourceIDList", "(Ljava/util/List;)V", "selImagBitmap", "Landroid/graphics/Bitmap;", "dataSendedSize", "getDataSendedSize", "setDataSendedSize", "(I)V", "mItemCLickData", "mPngData", "mSendIsDown", "iv_title_bg", "Landroid/widget/ImageView;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "diyRecyclerview", "Landroidx/recyclerview/widget/RecyclerView;", "no_data", "Landroid/widget/LinearLayout;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "isBackLoad", "setBackLoad", "(Z)V", "m_iSelect", "layoutId", "initView", "", "bindData", "saveChannelSP", "onAttach", "context", "Landroid/content/Context;", "onStart", "onHiddenChanged", "hidden", "onResume", "onDestroy", "onDetach", "mIsShowDiy", "getMIsShowDiy", "setMIsShowDiy", "showDiyImage", "isShowDiy", "initDiyImage", "fragmentList", "Lcom/wifiled/ipixels/ui/gallery/LoadFragment;", "initLocalImage", "initTemplageImage", "sendPng", "data", "isDown", "sendPng2", "bindListener", "resetSel", "sendData", "callback", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "itemClick", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageFragment extends BaseFragment {
    private int dataSendedSize;
    private RecyclerAdapter<Image> diyAdapter;
    private RecyclerView diyRecyclerview;
    private boolean isBackLoad;
    private ImageView iv_title_bg;
    private boolean mIsShowDiy;
    private byte[] mItemCLickData;
    private byte[] mPngData;
    private LinearLayout no_data;
    private Bitmap selImagBitmap;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final List<Image> diyImages = new ArrayList();

    /* renamed from: imageDao$delegate, reason: from kotlin metadata */
    private final Lazy imageDao = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ImageDao imageDao_delegate$lambda$0;
            imageDao_delegate$lambda$0 = ImageFragment.imageDao_delegate$lambda$0();
            return imageDao_delegate$lambda$0;
        }
    });

    /* renamed from: selectMode$delegate, reason: from kotlin metadata */
    private final Lazy selectMode = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean selectMode_delegate$lambda$1;
            selectMode_delegate$lambda$1 = ImageFragment.selectMode_delegate$lambda$1(ImageFragment.this);
            return Boolean.valueOf(selectMode_delegate$lambda$1);
        }
    });

    /* renamed from: routByFolderSel$delegate, reason: from kotlin metadata */
    private final Lazy routByFolderSel = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            boolean routByFolderSel_delegate$lambda$2;
            routByFolderSel_delegate$lambda$2 = ImageFragment.routByFolderSel_delegate$lambda$2(ImageFragment.this);
            return Boolean.valueOf(routByFolderSel_delegate$lambda$2);
        }
    });

    /* renamed from: mResourcesSize$delegate, reason: from kotlin metadata */
    private final Lazy mResourcesSize = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            int mResourcesSize_delegate$lambda$3;
            mResourcesSize_delegate$lambda$3 = ImageFragment.mResourcesSize_delegate$lambda$3(ImageFragment.this);
            return Integer.valueOf(mResourcesSize_delegate$lambda$3);
        }
    });
    private byte[] bgrData = new byte[0];
    private List<String> mSendResourceIDList = new ArrayList();
    private boolean mSendIsDown = true;
    private int m_iSelect = -1;
    private final List<LoadFragment> fragmentList = new ArrayList();
    private Function1<? super SendCore.CallbackBuilder, Unit> callback = new Function1() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit callback$lambda$31;
            callback$lambda$31 = ImageFragment.callback$lambda$31(ImageFragment.this, (SendCore.CallbackBuilder) obj);
            return callback$lambda$31;
        }
    };

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_image;
    }

    private final ImageDao getImageDao() {
        return (ImageDao) this.imageDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageDao imageDao_delegate$lambda$0() {
        return AppDatabase.INSTANCE.getDatabase().imageDao();
    }

    private final boolean getSelectMode() {
        return ((Boolean) this.selectMode.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean selectMode_delegate$lambda$1(ImageFragment imageFragment) {
        FragmentActivity fragmentActivity = imageFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getSelectMode();
    }

    private final boolean getRoutByFolderSel() {
        return ((Boolean) this.routByFolderSel.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean routByFolderSel_delegate$lambda$2(ImageFragment imageFragment) {
        FragmentActivity fragmentActivity = imageFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getRoutByFolderSel();
    }

    private final int getMResourcesSize() {
        return ((Number) this.mResourcesSize.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int mResourcesSize_delegate$lambda$3(ImageFragment imageFragment) {
        FragmentActivity fragmentActivity = imageFragment.mActivity;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type com.wifiled.ipixels.ui.gallery.GalleryActivity");
        return ((GalleryActivity) fragmentActivity).getMSize();
    }

    public final List<String> getMSendResourceIDList() {
        return this.mSendResourceIDList;
    }

    public final void setMSendResourceIDList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mSendResourceIDList = list;
    }

    public final int getDataSendedSize() {
        return this.dataSendedSize;
    }

    public final void setDataSendedSize(int i) {
        this.dataSendedSize = i;
    }

    /* renamed from: isBackLoad, reason: from getter */
    public final boolean getIsBackLoad() {
        return this.isBackLoad;
    }

    public final void setBackLoad(boolean z) {
        this.isBackLoad = z;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.iv_title_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_title_bg = (ImageView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.viewPager);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.viewPager = (ViewPager) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.diyRecyclerview);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.diyRecyclerview = (RecyclerView) findViewById3;
        View findViewById4 = this.mRootView.findViewById(R.id.no_data);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.no_data = (LinearLayout) findViewById4;
        View findViewById5 = this.mRootView.findViewById(R.id.tabLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tabLayout = (TabLayout) findViewById5;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        if (getMResourcesSize() == -1) {
            initLocalImage();
        } else {
            initTemplageImage();
        }
        LinearLayout linearLayout = null;
        if (AppConfig.INSTANCE.getLedType() == 18 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 19) {
            ImageView imageView = this.iv_title_bg;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
                imageView = null;
            }
            imageView.setVisibility(8);
        } else {
            initDiyImage();
            ImageView imageView2 = this.iv_title_bg;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
                imageView2 = null;
            }
            imageView2.setSelected(this.mIsShowDiy);
        }
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
        if (this.diyImages.isEmpty()) {
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

    private final void saveChannelSP() {
        Context context = getContext();
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
        SPUtils.put(context, str, ChannelIndex.INSTANCE.mapSaveChannel());
    }

    @Override // com.wifiled.baselib.base.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        LogUtils.file("ImageFragment onAttach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtils.file("ImageFragment onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.file(this.TAG + ">>>[onHiddenChanged]: " + hidden);
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
        if (this.diyImages.isEmpty()) {
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
        LogUtils.i(this.TAG + ">>>[onResume]: " + this.isBackLoad);
        if (this.isBackLoad) {
            showDiyImage(!getRoutByFolderSel());
            String prevActivityName = AppConfig.INSTANCE.getPrevActivityName();
            Intrinsics.checkNotNullExpressionValue("ChooseActivity", "getSimpleName(...)");
            if (StringsKt.contains$default((CharSequence) prevActivityName, (CharSequence) "ChooseActivity", false, 2, (Object) null)) {
                this.isBackLoad = false;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.isBackLoad = false;
        this.mActivity = null;
    }

    public final boolean getMIsShowDiy() {
        return this.mIsShowDiy;
    }

    public final void setMIsShowDiy(boolean z) {
        this.mIsShowDiy = z;
    }

    static /* synthetic */ void showDiyImage$default(ImageFragment imageFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        imageFragment.showDiyImage(z);
    }

    private final void showDiyImage(boolean isShowDiy) {
        String str;
        this.diyImages.clear();
        List<Image> all = getImageDao().getAll();
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
                str = "96";
                break;
            case 2:
                str = "32";
                break;
            case 3:
                str = "16";
                break;
            case 4:
                str = "12";
                break;
            case 5:
                str = "20";
                break;
            case 6:
                str = "128";
                break;
            case 7:
                str = "144";
                break;
            case 8:
                str = "192";
                break;
            case 9:
                str = "2448";
                break;
            case 10:
                str = "3264";
                break;
            case 11:
                str = "3296";
                break;
            case 12:
                str = "128_2";
                break;
            case 13:
                str = "3296_2";
                break;
            case 14:
                str = "32160";
                break;
            case 15:
                str = "32192";
                break;
            case 16:
                str = "32256";
                break;
            default:
                str = "64";
                break;
        }
        Iterator<T> it = all.iterator();
        while (true) {
            LinearLayout linearLayout = null;
            if (it.hasNext()) {
                Image image = (Image) it.next();
                if (StringsKt.contains$default((CharSequence) image.getUrl(), (CharSequence) ("Image/" + ((Object) str)), false, 2, (Object) null)) {
                    this.diyImages.add(image);
                }
            } else {
                CollectionsKt.reverse(this.diyImages);
                RecyclerAdapter<Image> recyclerAdapter = this.diyAdapter;
                if (recyclerAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
                    recyclerAdapter = null;
                }
                recyclerAdapter.notifyDataSetChanged();
                if (!getSelectMode() && !this.diyImages.isEmpty()) {
                    Log.v("ruis", " itemClick---------------" + this.diyImages.get(0).getUrl());
                    byte[] readFileBytes = FileUtil.readFileBytes(this.diyImages.get(0).getUrl());
                    Bitmap bitmapFromFile = BitmapUtil.getBitmapFromFile(this.diyImages.get(0).getUrl());
                    Intrinsics.checkNotNull(bitmapFromFile);
                    this.selImagBitmap = bitmapFromFile;
                    Intrinsics.checkNotNull(readFileBytes);
                    itemClick(readFileBytes);
                }
                ImageView imageView = this.iv_title_bg;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_title_bg");
                    imageView = null;
                }
                imageView.setSelected(isShowDiy);
                this.mIsShowDiy = isShowDiy;
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
                if (this.diyImages.isEmpty()) {
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
                return;
            }
        }
    }

    private final void initDiyImage() {
        String str;
        GridLayoutManager gridLayoutManager;
        List<Image> all = getImageDao().getAll();
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
                str = "96";
                break;
            case 2:
                str = "32";
                break;
            case 3:
                str = "16";
                break;
            case 4:
                str = "12";
                break;
            case 5:
                str = "20";
                break;
            case 6:
                str = "128";
                break;
            case 7:
                str = "144";
                break;
            case 8:
                str = "192";
                break;
            case 9:
                str = "2448";
                break;
            case 10:
                str = "3264";
                break;
            case 11:
                str = "3296";
                break;
            case 12:
                str = "128_2";
                break;
            case 13:
                str = "3296_2";
                break;
            case 14:
                str = "32160";
                break;
            case 15:
                str = "32192";
                break;
            case 16:
                str = "32256";
                break;
            default:
                str = "64";
                break;
        }
        Iterator<T> it = all.iterator();
        while (true) {
            RecyclerAdapter<Image> recyclerAdapter = null;
            if (it.hasNext()) {
                Image image = (Image) it.next();
                if (StringsKt.contains$default((CharSequence) image.getUrl(), (CharSequence) ("Image/" + ((Object) str)), false, 2, (Object) null)) {
                    this.diyImages.add(image);
                }
            } else {
                CollectionsKt.reverse(this.diyImages);
                final Ref.IntRef intRef = new Ref.IntRef();
                int ledType = AppConfig.INSTANCE.getLedType();
                int i = R.layout.item_image_1664;
                switch (ledType) {
                    case 1:
                    case 15:
                        i = R.layout.item_image_1696;
                        break;
                    case 2:
                    default:
                        i = R.layout.item_image;
                        break;
                    case 3:
                    case 12:
                        break;
                    case 4:
                    case 9:
                    case 10:
                        i = R.layout.item_image_1632;
                        break;
                    case 5:
                        i = R.layout.item_image_2064;
                        break;
                    case 6:
                        i = R.layout.item_image_32128;
                        break;
                    case 7:
                        i = R.layout.item_image_16144;
                        break;
                    case 8:
                        i = R.layout.item_image_16192;
                        break;
                    case 11:
                        i = R.layout.item_image_3296;
                        break;
                    case 13:
                        i = R.layout.item_image_3296_2;
                        break;
                    case 14:
                        i = R.layout.item_image_32160;
                        break;
                    case 16:
                        i = R.layout.item_image_32256;
                        break;
                }
                intRef.element = i;
                final FragmentActivity activity = getActivity();
                final List<Image> list = this.diyImages;
                this.diyAdapter = new RecyclerAdapter<Image>(intRef, this, activity, list) { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$initDiyImage$2
                    final /* synthetic */ ImageFragment this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(activity, list, intRef.element);
                        this.this$0 = this;
                    }

                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
                    public void convert(RecyclerViewHolder holder, Image image2) {
                        int i2;
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        Intrinsics.checkNotNullParameter(image2, "image");
                        ImageView imageView = (ImageView) holder.getView(R.id.iv_preview);
                        Glide.with(this.this$0).load(image2.getUrl()).into(imageView);
                        if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
                            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                            layoutParams.height = (this.mContext.getResources().getDisplayMetrics().widthPixels / 3) - UtilsExtensionKt.toDp(24);
                            imageView.setLayoutParams(layoutParams);
                        }
                        i2 = this.this$0.m_iSelect;
                        boolean z = i2 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.drawable.item_sel);
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.color.transparent);
                        }
                    }
                };
                RecyclerView recyclerView = this.diyRecyclerview;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                    recyclerView = null;
                }
                RecyclerAdapter<Image> recyclerAdapter2 = this.diyAdapter;
                if (recyclerAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
                    recyclerAdapter2 = null;
                }
                recyclerView.setAdapter(recyclerAdapter2);
                RecyclerView recyclerView2 = this.diyRecyclerview;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                    recyclerView2 = null;
                }
                switch (AppConfig.INSTANCE.getLedType()) {
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
                        gridLayoutManager = new LinearLayoutManager(getContext());
                        break;
                }
                recyclerView2.setLayoutManager(gridLayoutManager);
                RecyclerAdapter<Image> recyclerAdapter3 = this.diyAdapter;
                if (recyclerAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
                    recyclerAdapter3 = null;
                }
                recyclerAdapter3.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda25
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemLongClickListener
                    public final boolean onItemLongClick(ViewGroup viewGroup, View view, Object obj, int i2) {
                        boolean initDiyImage$lambda$7;
                        initDiyImage$lambda$7 = ImageFragment.initDiyImage$lambda$7(ImageFragment.this, viewGroup, view, (Image) obj, i2);
                        return initDiyImage$lambda$7;
                    }
                });
                RecyclerAdapter<Image> recyclerAdapter4 = this.diyAdapter;
                if (recyclerAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
                } else {
                    recyclerAdapter = recyclerAdapter4;
                }
                recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda26
                    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
                    public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i2) {
                        ImageFragment.initDiyImage$lambda$8(ImageFragment.this, viewGroup, view, (Image) obj, i2);
                    }
                });
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initDiyImage$lambda$7(final ImageFragment imageFragment, ViewGroup viewGroup, View view, final Image image, final int i) {
        LogUtils.file("ImageFragment  diyAdapter.setOnItemLongClickListener");
        String string = imageFragment.getString(R.string.is_delete);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showDialog$default(imageFragment, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initDiyImage$lambda$7$lambda$6;
                initDiyImage$lambda$7$lambda$6 = ImageFragment.initDiyImage$lambda$7$lambda$6(ImageFragment.this, i, image);
                return initDiyImage$lambda$7$lambda$6;
            }
        }, 1, null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initDiyImage$lambda$7$lambda$6(ImageFragment imageFragment, int i, Image image) {
        imageFragment.getImageDao().delete(imageFragment.diyImages.get(i));
        UtilsExtensionKt.deleteFile(image.getUrl());
        imageFragment.diyImages.remove(i);
        RecyclerAdapter<Image> recyclerAdapter = imageFragment.diyAdapter;
        LinearLayout linearLayout = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        RecyclerAdapter<Image> recyclerAdapter2 = imageFragment.diyAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
            recyclerAdapter2 = null;
        }
        LogUtils.i("data:" + recyclerAdapter2.getMData());
        if (imageFragment.diyImages.isEmpty()) {
            LinearLayout linearLayout2 = imageFragment.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout2;
            }
            UtilsExtensionKt.show(linearLayout);
        } else {
            LinearLayout linearLayout3 = imageFragment.no_data;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout3;
            }
            UtilsExtensionKt.hide(linearLayout);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDiyImage$lambda$8(ImageFragment imageFragment, ViewGroup viewGroup, View view, Image image, int i) {
        byte[] readFileBytes;
        LogUtils.file("ImageFragment  diyAdapter.setOnItemClickListener ");
        Log.v("ruis", " diyAdapter.setOnItemClickListener" + i);
        Bitmap bitmapFromFile = BitmapUtil.getBitmapFromFile(image.getUrl());
        Intrinsics.checkNotNull(bitmapFromFile);
        imageFragment.selImagBitmap = bitmapFromFile;
        Log.v("ruis", "AppConfig.bCheckOta" + AppConfig.INSTANCE.getBCheckOta());
        if (AppConfig.INSTANCE.getLedType() == 16) {
            readFileBytes = BGRUtils.bitmap2RGB(bitmapFromFile);
        } else {
            readFileBytes = FileUtil.readFileBytes(image.getUrl());
        }
        Intrinsics.checkNotNull(readFileBytes);
        imageFragment.bgrData = readFileBytes;
        imageFragment.m_iSelect = i;
        RecyclerAdapter<Image> recyclerAdapter = imageFragment.diyAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        if (imageFragment.getSelectMode()) {
            Intent intent = new Intent();
            intent.putExtra("bgr", BGRUtils.bitmap2RGB(bitmapFromFile));
            intent.putExtra("sendData", BGRUtils.bitmapToByteStream(bitmapFromFile));
            intent.putExtra("sendType", 2);
            imageFragment.mActivity.setResult(-1, intent);
            imageFragment.mActivity.finish();
            return;
        }
        if (AppConfig.INSTANCE.getMcu() > 4) {
            try {
                imageFragment.sendPng(imageFragment.bgrData, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01ff  */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void initLocalImage() {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.gallery.ImageFragment.initLocalImage():void");
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.String[]] */
    private final void initTemplageImage() {
        this.fragmentList.clear();
        switch (getMResourcesSize()) {
            case 9:
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("图片", "ALL,YK", "iPixels", 24, 24, 2));
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
                this.fragmentList.add(LoadFragment.INSTANCE.newInstance("图片", "ALL,YK", "iPixels", 32, 32, 2));
                break;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new String[]{getString(R.string.hot_spot), getString(R.string.emote), getString(R.string.steer), getString(R.string.season), getString(R.string.originality)};
        ViewPager viewPager = this.viewPager;
        ViewPager viewPager2 = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        }
        final FragmentManager childFragmentManager = getChildFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(childFragmentManager) { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$initTemplageImage$1
            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                List list;
                list = ImageFragment.this.fragmentList;
                return list.size();
            }

            @Override // androidx.fragment.app.FragmentStatePagerAdapter
            public Fragment getItem(int position) {
                List list;
                list = ImageFragment.this.fragmentList;
                return (Fragment) list.get(position);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public CharSequence getPageTitle(int position) {
                return objectRef.element[position];
            }
        });
        TabLayout tabLayout = this.tabLayout;
        if (tabLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            tabLayout = null;
        }
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager3 = null;
        }
        tabLayout.setupWithViewPager(viewPager3, true);
        if (AppConfig.INSTANCE.getLedType() == 3 || AppConfig.INSTANCE.getLedType() == 5) {
            TabLayout tabLayout2 = this.tabLayout;
            if (tabLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                tabLayout2 = null;
            }
            UtilsExtensionKt.show(tabLayout2);
        } else {
            TabLayout tabLayout3 = this.tabLayout;
            if (tabLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                tabLayout3 = null;
            }
            UtilsExtensionKt.hide(tabLayout3);
        }
        ViewPager viewPager4 = this.viewPager;
        if (viewPager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager4;
        }
        viewPager2.setOffscreenPageLimit(2);
    }

    static /* synthetic */ void sendPng$default(ImageFragment imageFragment, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        imageFragment.sendPng(bArr, z);
    }

    private final void sendPng(byte[] data, boolean isDown) {
        LogUtils.file("ImageFragment sendPng");
        this.dataSendedSize = 0;
        this.mSendIsDown = isDown;
        this.mPngData = data;
        if (AppConfig.INSTANCE.getConnectType() != -1) {
            SendCore.sendChannelImageData$default(SendCore.INSTANCE, isDown, null, data, this.callback, (byte) 0, 18, null);
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendPng$lambda$9;
                    sendPng$lambda$9 = ImageFragment.sendPng$lambda$9(ImageFragment.this);
                    return sendPng$lambda$9;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendPng$lambda$9(ImageFragment imageFragment) {
        imageFragment.toast(imageFragment.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendPng2$default(ImageFragment imageFragment, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        imageFragment.sendPng2(bArr, z);
    }

    private final void sendPng2(byte[] data, boolean isDown) {
        LogUtils.file("ImageFragment sendPng");
        this.dataSendedSize = 0;
        this.mSendIsDown = isDown;
        this.mPngData = data;
        if (AppConfig.INSTANCE.getConnectType2() != -1) {
            SendCore.sendChannelImageData2$default(SendCore.INSTANCE, isDown, null, data, this.callback, (byte) 0, 18, null);
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendPng2$lambda$10;
                    sendPng2$lambda$10 = ImageFragment.sendPng2$lambda$10(ImageFragment.this);
                    return sendPng2$lambda$10;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendPng2$lambda$10(ImageFragment imageFragment) {
        imageFragment.toast(imageFragment.getString(R.string.msg_dev_connect_null));
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageFragment.bindListener$lambda$11(Ref.BooleanRef.this, this, view);
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
    public static final void bindListener$lambda$11(Ref.BooleanRef booleanRef, ImageFragment imageFragment, View view) {
        LogUtils.file("ImageFragment  iv_title_bg.setOnClickListener");
        RecyclerView recyclerView = imageFragment.diyRecyclerview;
        LinearLayout linearLayout = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView = null;
        }
        booleanRef.element = recyclerView.getVisibility() == 0;
        booleanRef.element = !booleanRef.element;
        view.setSelected(booleanRef.element);
        imageFragment.mIsShowDiy = booleanRef.element;
        boolean z = booleanRef.element;
        if (!z) {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            ViewPager viewPager = imageFragment.viewPager;
            if (viewPager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager = null;
            }
            viewPager.setVisibility(0);
            RecyclerView recyclerView2 = imageFragment.diyRecyclerview;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(8);
            LinearLayout linearLayout2 = imageFragment.no_data;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout2;
            }
            UtilsExtensionKt.hide(linearLayout);
            return;
        }
        ViewPager viewPager2 = imageFragment.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        viewPager2.setVisibility(8);
        RecyclerView recyclerView3 = imageFragment.diyRecyclerview;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyRecyclerview");
            recyclerView3 = null;
        }
        recyclerView3.setVisibility(0);
        if (imageFragment.diyImages.isEmpty()) {
            LinearLayout linearLayout3 = imageFragment.no_data;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("no_data");
            } else {
                linearLayout = linearLayout3;
            }
            UtilsExtensionKt.show(linearLayout);
            return;
        }
        LinearLayout linearLayout4 = imageFragment.no_data;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("no_data");
        } else {
            linearLayout = linearLayout4;
        }
        UtilsExtensionKt.hide(linearLayout);
    }

    public static /* synthetic */ void resetSel$default(ImageFragment imageFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        imageFragment.resetSel(z);
    }

    public final void resetSel(boolean isShowDiy) {
        this.m_iSelect = -1;
        this.mIsShowDiy = isShowDiy;
    }

    public final void sendData() {
        ViewPager viewPager;
        Handler handler;
        LogUtils.file("ImageFragment  sendData ");
        if (this.mIsShowDiy) {
            if (-1 == this.m_iSelect || (handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get()) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda22
                @Override // java.lang.Runnable
                public final void run() {
                    ImageFragment.sendData$lambda$12(ImageFragment.this);
                }
            });
            return;
        }
        if (this.fragmentList.isEmpty() || (viewPager = this.viewPager) == null) {
            return;
        }
        List<String> list = this.mSendResourceIDList;
        List<LoadFragment> list2 = this.fragmentList;
        ViewPager viewPager2 = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        }
        list.add(list2.get(viewPager.getCurrentItem()).getMFileId());
        List<LoadFragment> list3 = this.fragmentList;
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager3;
        }
        final byte[] bgrData = list3.get(viewPager2.getCurrentItem()).getBgrData();
        if (bgrData != null) {
            LogUtils.vTag("ruis", "bgrData size " + bgrData.length);
            if (AppConfig.INSTANCE.getLedType() == 16) {
                Bitmap RGB2bitmap = BGRUtils.RGB2bitmap(bgrData);
                Intrinsics.checkNotNullExpressionValue(RGB2bitmap, "RGB2bitmap(...)");
                this.selImagBitmap = RGB2bitmap;
            } else {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bgrData, 0, bgrData.length);
                Intrinsics.checkNotNullExpressionValue(decodeByteArray, "decodeByteArray(...)");
                this.selImagBitmap = decodeByteArray;
            }
            Handler handler2 = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
            if (handler2 != null) {
                handler2.post(new Runnable() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda23
                    @Override // java.lang.Runnable
                    public final void run() {
                        ImageFragment.this.itemClick(bgrData);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendData$lambda$12(ImageFragment imageFragment) {
        imageFragment.itemClick(imageFragment.bgrData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31(final ImageFragment imageFragment, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$31$lambda$16;
                callback$lambda$31$lambda$16 = ImageFragment.callback$lambda$31$lambda$16(ImageFragment.this);
                return callback$lambda$31$lambda$16;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$31$lambda$18;
                callback$lambda$31$lambda$18 = ImageFragment.callback$lambda$31$lambda$18(ImageFragment.this, ((Integer) obj).intValue());
                return callback$lambda$31$lambda$18;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$31$lambda$23;
                callback$lambda$31$lambda$23 = ImageFragment.callback$lambda$31$lambda$23(ImageFragment.this);
                return callback$lambda$31$lambda$23;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$31$lambda$25;
                callback$lambda$31$lambda$25 = ImageFragment.callback$lambda$31$lambda$25(ImageFragment.this, ((Integer) obj).intValue());
                return callback$lambda$31$lambda$25;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit callback$lambda$31$lambda$30;
                callback$lambda$31$lambda$30 = ImageFragment.callback$lambda$31$lambda$30(ImageFragment.this, (byte[]) obj);
                return callback$lambda$31$lambda$30;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$16(final ImageFragment imageFragment) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$31$lambda$16$lambda$15;
                callback$lambda$31$lambda$16$lambda$15 = ImageFragment.callback$lambda$31$lambda$16$lambda$15(ImageFragment.this);
                return callback$lambda$31$lambda$16$lambda$15;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$16$lambda$15(ImageFragment imageFragment) {
        if (ActivityUtils.isActivityAlive((Activity) imageFragment.getActivity())) {
            String string = imageFragment.getString(R.string.msg_sending);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, string, false, 5, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$18(final ImageFragment imageFragment, final int i) {
        if (imageFragment.isAdded()) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$31$lambda$18$lambda$17;
                    callback$lambda$31$lambda$18$lambda$17 = ImageFragment.callback$lambda$31$lambda$18$lambda$17(ImageFragment.this, i);
                    return callback$lambda$31$lambda$18$lambda$17;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$18$lambda$17(ImageFragment imageFragment, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, imageFragment.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$23(final ImageFragment imageFragment) {
        Bitmap bitmap;
        LogUtils.i("onCompleted");
        if (imageFragment.mSendIsDown) {
            byte[] bArr = imageFragment.mItemCLickData;
            ChannelListItem.ImagView imagView = null;
            if (bArr != null) {
                Bitmap bitmap2 = imageFragment.selImagBitmap;
                if (bitmap2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selImagBitmap");
                    bitmap = null;
                } else {
                    bitmap = bitmap2;
                }
                imagView = new ChannelListItem.ImagView(false, bitmap, String.valueOf(ChannelIndex.INSTANCE.index()), false, bArr);
            }
            Map<Integer, ChannelListItem> mapSaveChannel = ChannelIndex.INSTANCE.mapSaveChannel();
            Integer valueOf = Integer.valueOf(ChannelIndex.INSTANCE.index());
            Intrinsics.checkNotNull(imagView);
            mapSaveChannel.put(valueOf, imagView);
            imageFragment.saveChannelSP();
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$31$lambda$23$lambda$22;
                callback$lambda$31$lambda$23$lambda$22 = ImageFragment.callback$lambda$31$lambda$23$lambda$22(ImageFragment.this);
                return callback$lambda$31$lambda$23$lambda$22;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$23$lambda$22(ImageFragment imageFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$25(final ImageFragment imageFragment, int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit callback$lambda$31$lambda$25$lambda$24;
                callback$lambda$31$lambda$25$lambda$24 = ImageFragment.callback$lambda$31$lambda$25$lambda$24(ImageFragment.this);
                return callback$lambda$31$lambda$25$lambda$24;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$25$lambda$24(ImageFragment imageFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, (String) null, false, 6, (Object) null);
        if (imageFragment.isAdded()) {
            Toast.makeText(imageFragment.getActivity(), imageFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$30(final ImageFragment imageFragment, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        LogUtils.i("strResponse: " + ByteUtils.binaryToHexString(it));
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$31$lambda$30$lambda$26;
                    callback$lambda$31$lambda$30$lambda$26 = ImageFragment.callback$lambda$31$lambda$30$lambda$26(ImageFragment.this);
                    return callback$lambda$31$lambda$30$lambda$26;
                }
            });
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 0) {
            LogUtils.i("$TAG>>>[onResult]:命令无效 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$31$lambda$30$lambda$27;
                    callback$lambda$31$lambda$30$lambda$27 = ImageFragment.callback$lambda$31$lambda$30$lambda$27(ImageFragment.this);
                    return callback$lambda$31$lambda$30$lambda$27;
                }
            });
        } else if (b == 2) {
            LogUtils.i("$TAG>>>[onResult]:空间不足 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$31$lambda$30$lambda$28;
                    callback$lambda$31$lambda$30$lambda$28 = ImageFragment.callback$lambda$31$lambda$30$lambda$28(ImageFragment.this);
                    return callback$lambda$31$lambda$30$lambda$28;
                }
            });
        } else if (b == 3) {
            LogUtils.i("$TAG>>>[onResult]:保存文件成功 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit callback$lambda$31$lambda$30$lambda$29;
                    callback$lambda$31$lambda$30$lambda$29 = ImageFragment.callback$lambda$31$lambda$30$lambda$29(ImageFragment.this);
                    return callback$lambda$31$lambda$30$lambda$29;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$30$lambda$26(ImageFragment imageFragment) {
        if (imageFragment.isAdded()) {
            Toast.makeText(imageFragment.getActivity(), imageFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$30$lambda$27(ImageFragment imageFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, (String) null, false, 6, (Object) null);
        if (imageFragment.isAdded()) {
            Toast.makeText(imageFragment.getActivity(), imageFragment.getString(R.string.msg_send_fail), 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$30$lambda$28(ImageFragment imageFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit callback$lambda$31$lambda$30$lambda$29(ImageFragment imageFragment) {
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void itemClick(byte[] data) {
        LogUtils.file("ImageFragment  itemClick ");
        this.mItemCLickData = data;
        if (ChannelIndex.INSTANCE.mapSaveChannel().size() >= 100) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit itemClick$lambda$32;
                    itemClick$lambda$32 = ImageFragment.itemClick$lambda$32(ImageFragment.this);
                    return itemClick$lambda$32;
                }
            });
            return;
        }
        if (!getSelectMode()) {
            this.dataSendedSize = 0;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.gallery.ImageFragment$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit itemClick$lambda$33;
                    itemClick$lambda$33 = ImageFragment.itemClick$lambda$33(ImageFragment.this);
                    return itemClick$lambda$33;
                }
            });
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
                    SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new ImageFragment$itemClick$3(this, data));
                }
                if (AppConfig.INSTANCE.getConnectType2() != -1) {
                    SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList), new ImageFragment$itemClick$4(this, data));
                    return;
                }
                return;
            }
            sendPng$default(this, data, false, 2, null);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("bgr", data);
        this.mActivity.setResult(-1, intent);
        this.mActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit itemClick$lambda$32(ImageFragment imageFragment) {
        ToastUtil.show(String.valueOf(imageFragment.getString(R.string.send_failed_deivce_space)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit itemClick$lambda$33(ImageFragment imageFragment) {
        String string = imageFragment.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Fragment) imageFragment, false, string, true, 1, (Object) null);
        return Unit.INSTANCE;
    }
}
