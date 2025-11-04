package com.wifiled.ipixels.ui.gallery;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.Navigation;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.BaseNavActivity;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.UpDataState;
import com.wifiled.ipixels.ui.diy.DiyAnimActivity;
import com.wifiled.ipixels.ui.diy.DiyImageActivity;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.text.vo.EventEyesChange;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: GalleryActivity.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010+\u001a\u00020\u001dH\u0014J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020/H\u0014J\u0010\u00100\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0007J\u0010\u00103\u001a\u00020/2\u0006\u00104\u001a\u000205H\u0007J\b\u00106\u001a\u00020\u001dH\u0014J\b\u00107\u001a\u00020/H\u0014J\b\u00108\u001a\u00020/H\u0014J\u001a\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020\u001d2\b\u00104\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020/H\u0002J\b\u0010=\u001a\u00020/H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/wifiled/ipixels/ui/gallery/GalleryActivity;", "Lcom/wifiled/baselib/base/BaseNavActivity;", "<init>", "()V", "imageFragment", "Lcom/wifiled/ipixels/ui/gallery/ImageFragment;", "animFragment", "Lcom/wifiled/ipixels/ui/gallery/AnimFragment;", "mCurFragment", "Lcom/wifiled/baselib/base/BaseFragment;", "mSendResourceIDList", "", "", "selectMode", "", "getSelectMode", "()Z", "setSelectMode", "(Z)V", "routByFolderSel", "getRoutByFolderSel", "setRoutByFolderSel", "mFromImageText", "getMFromImageText", "setMFromImageText", "mFromIT", "getMFromIT", "setMFromIT", "mSize", "", "getMSize", "()I", "setMSize", "(I)V", "iv_gallery_eyes_change", "Landroid/widget/ImageView;", "iv_gallery_send", "iv_nav_bg", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "containerViewId", PlayerFinal.PLAYER_MODE, "Lcom/wifiled/baselib/app/Navigation$MODE;", "initView", "", "onUpDataState", "instance", "Lcom/wifiled/ipixels/ui/UpDataState;", "isShowEyesChange", "data", "Lcom/wifiled/ipixels/ui/text/vo/EventEyesChange;", "layoutId", "bindData", "onDestroy", "onActivityReenter", "resultCode", "Landroid/content/Intent;", "initToolbar", "bindListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GalleryActivity extends BaseNavActivity {
    private CustomImageView iv_back;
    private ImageView iv_gallery_eyes_change;
    private ImageView iv_gallery_send;
    private ImageView iv_nav_bg;
    private CustomImageView iv_right;
    private BaseFragment mCurFragment;
    private boolean mFromIT;
    private boolean mFromImageText;
    private int mSize;
    private boolean routByFolderSel;
    private boolean selectMode;
    private TextView tv_title;
    private final ImageFragment imageFragment = new ImageFragment();
    private final AnimFragment animFragment = new AnimFragment();
    private List<String> mSendResourceIDList = new ArrayList();

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected int containerViewId() {
        return R.id.navigation_container;
    }

    public final boolean getSelectMode() {
        return this.selectMode;
    }

    public final void setSelectMode(boolean z) {
        this.selectMode = z;
    }

    public final boolean getRoutByFolderSel() {
        return this.routByFolderSel;
    }

    public final void setRoutByFolderSel(boolean z) {
        this.routByFolderSel = z;
    }

    public final boolean getMFromImageText() {
        return this.mFromImageText;
    }

    public final void setMFromImageText(boolean z) {
        this.mFromImageText = z;
    }

    public final boolean getMFromIT() {
        return this.mFromIT;
    }

    public final void setMFromIT(boolean z) {
        this.mFromIT = z;
    }

    public final int getMSize() {
        return this.mSize;
    }

    public final void setMSize(int i) {
        this.mSize = i;
    }

    @Override // com.wifiled.baselib.base.BaseNavActivity
    protected Navigation.MODE mode() {
        return Navigation.MODE.SHOW;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_gallery_eyes_change);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_gallery_eyes_change = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_gallery_send);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_gallery_send = (ImageView) findViewById2;
        View findViewById3 = findViewById(R.id.iv_nav_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.iv_nav_bg = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById4;
        View findViewById5 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById5;
        View findViewById6 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tv_title = (TextView) findViewById6;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onUpDataState(UpDataState instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void isShowEyesChange(EventEyesChange data) {
        Intrinsics.checkNotNullParameter(data, "data");
        LogUtils.vTag("ruis", "Gallery isShowEyesChange  ----" + data.isShow());
        ImageView imageView = null;
        if (data.isShow()) {
            ImageView imageView2 = this.iv_gallery_eyes_change;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_gallery_eyes_change");
            } else {
                imageView = imageView2;
            }
            imageView.setVisibility(0);
            return;
        }
        ImageView imageView3 = this.iv_gallery_eyes_change;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_gallery_eyes_change");
        } else {
            imageView = imageView3;
        }
        imageView.setVisibility(8);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        getWindow().setFlags(128, 128);
        return R.layout.activity_gallery;
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0285  */
    @Override // com.wifiled.baselib.base.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void bindData() {
        /*
            Method dump skipped, instructions count: 796
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.gallery.GalleryActivity.bindData():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(GalleryActivity galleryActivity, View view) {
        LogUtils.file("GalleryActivity iv_gallery_send.setOnClickListener");
        if (ClickFilter.filter()) {
            ToastUtil.show(galleryActivity.getString(R.string.show_no_click));
            return;
        }
        BaseFragment baseFragment = galleryActivity.mCurFragment;
        BaseFragment baseFragment2 = null;
        if (baseFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            baseFragment = null;
        }
        if (baseFragment instanceof ImageFragment) {
            BaseFragment baseFragment3 = galleryActivity.mCurFragment;
            if (baseFragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            } else {
                baseFragment2 = baseFragment3;
            }
            ((ImageFragment) baseFragment2).sendData();
            return;
        }
        if (baseFragment instanceof AnimFragment) {
            BaseFragment baseFragment4 = galleryActivity.mCurFragment;
            if (baseFragment4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            } else {
                baseFragment2 = baseFragment4;
            }
            ((AnimFragment) baseFragment2).sendData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(GalleryActivity galleryActivity, View view) {
        AppConfig.INSTANCE.setExchange(!AppConfig.INSTANCE.isExchange());
        ImageView imageView = null;
        if (AppConfig.INSTANCE.isExchange()) {
            ImageView imageView2 = galleryActivity.iv_gallery_eyes_change;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_gallery_eyes_change");
            } else {
                imageView = imageView2;
            }
            imageView.setImageResource(R.mipmap.eyes_change_unclick2);
            return;
        }
        ImageView imageView3 = galleryActivity.iv_gallery_eyes_change;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_gallery_eyes_change");
        } else {
            imageView = imageView3;
        }
        imageView.setImageResource(R.mipmap.eyes_change_unclick);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mSendResourceIDList.addAll(this.imageFragment.getMSendResourceIDList());
        this.mSendResourceIDList.addAll(this.animFragment.getMSendResourceIDList());
        SPUtils.put(this, "ResourceIDList", this.mSendResourceIDList);
        super.onDestroy();
        if (AppConfig.INSTANCE.isSend()) {
            SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
            AppConfig.INSTANCE.setSend(false);
        }
        BleManager.INSTANCE.get().removeSendCallBack();
        BleManager2.INSTANCE.get().removeSendCallBack();
        Navigation.get().clear();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.app.Activity
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    private final void initToolbar() {
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
        customImageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.gallery.GalleryActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GalleryActivity.initToolbar$lambda$2(GalleryActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        companion.attachViewOnTouchListener(customImageView4);
        if (AppConfig.INSTANCE.getLedType() != 18 && AppConfig.INSTANCE.getLedType() != 17 && AppConfig.INSTANCE.getLedType() != 19) {
            CustomImageView customImageView5 = this.iv_right;
            if (customImageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_right");
                customImageView5 = null;
            }
            customImageView5.setBackgroundResource(R.drawable.diy_default);
            CustomImageView customImageView6 = this.iv_right;
            if (customImageView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_right");
                customImageView6 = null;
            }
            customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.gallery.GalleryActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GalleryActivity.initToolbar$lambda$3(GalleryActivity.this, view);
                }
            });
            CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
            CustomImageView customImageView7 = this.iv_right;
            if (customImageView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_right");
                customImageView7 = null;
            }
            companion2.attachViewOnTouchListener(customImageView7);
        }
        TextView textView2 = this.tv_title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
        } else {
            textView = textView2;
        }
        textView.setText(getString(R.string.title_gallery));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$2(GalleryActivity galleryActivity, View view) {
        LogUtils.file("GalleryActivity iv_back.setOnClickListener");
        if (galleryActivity.imageFragment.getIsBackLoad() && AppConfig.INSTANCE.getPrevActivityName().equals("diy_image")) {
            galleryActivity.finish();
            return;
        }
        if (galleryActivity.animFragment.getIsBackLoad() && AppConfig.INSTANCE.getPrevActivityName().equals("diy_anim")) {
            galleryActivity.finish();
            return;
        }
        galleryActivity.imageFragment.setBackLoad(false);
        galleryActivity.animFragment.setBackLoad(false);
        AppConfig.INSTANCE.setNextActivityName("");
        galleryActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$3(GalleryActivity galleryActivity, View view) {
        LogUtils.file("GalleryActivity iv_right.setOnClickListener");
        String localClassName = galleryActivity.getLocalClassName();
        BaseFragment baseFragment = galleryActivity.mCurFragment;
        BaseFragment baseFragment2 = null;
        if (baseFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
            baseFragment = null;
        }
        Log.d(localClassName, "viewID: " + baseFragment);
        AppConfig.INSTANCE.setSend(false);
        BaseFragment baseFragment3 = galleryActivity.mCurFragment;
        if (baseFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCurFragment");
        } else {
            baseFragment2 = baseFragment3;
        }
        boolean z = baseFragment2 instanceof ImageFragment;
        if (z) {
            AppConfig.INSTANCE.setPrevActivityName("enter_diy_image");
            galleryActivity.toActivity(DiyImageActivity.class);
        } else {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            AppConfig.INSTANCE.setPrevActivityName("enter_diy_anim");
            galleryActivity.toActivity(DiyAnimActivity.class);
        }
        galleryActivity.finish();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        ImageView imageView = this.iv_nav_bg;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_nav_bg");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.gallery.GalleryActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GalleryActivity.bindListener$lambda$4(Ref.BooleanRef.this, this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView3 = this.iv_nav_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_nav_bg");
        } else {
            imageView2 = imageView3;
        }
        companion.attachViewOnTouchListener(imageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(Ref.BooleanRef booleanRef, GalleryActivity galleryActivity, View view) {
        LogUtils.file("GalleryActivity iv_nav_bg.setOnClickListener");
        booleanRef.element = view.isSelected();
        booleanRef.element = !booleanRef.element;
        view.setSelected(booleanRef.element);
        boolean z = booleanRef.element;
        if (!z) {
            galleryActivity.imageFragment.resetSel(galleryActivity.animFragment.getMIsShowDiy());
            Navigation.get().navigate(galleryActivity.imageFragment);
            galleryActivity.mCurFragment = galleryActivity.imageFragment;
        } else {
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            galleryActivity.animFragment.resetSel(galleryActivity.imageFragment.getMIsShowDiy());
            Navigation.get().navigate(galleryActivity.animFragment);
            galleryActivity.mCurFragment = galleryActivity.animFragment;
        }
    }
}
