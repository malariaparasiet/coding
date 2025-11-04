package com.wifiled.ipixels.ui.imgtxt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.KeyBordUtil;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.databinding.ActivityImageTextBinding;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.ui.text.TextActivity;
import com.wifiled.ipixels.utils.InputFilterUtils;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ImageTextActivity.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020\u0002H\u0016J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020$H\u0016J\b\u0010(\u001a\u00020$H\u0002J\b\u0010)\u001a\u00020$H\u0002J\b\u00103\u001a\u00020$H\u0002J\b\u00104\u001a\u00020$H\u0002J\b\u00105\u001a\u00020$H\u0002J\u0010\u00106\u001a\u00020$2\u0006\u00107\u001a\u000208H\u0016J\u001a\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\r2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020$H\u0016J\b\u0010>\u001a\u00020$H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010*\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010,0,0+¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001c\u00100\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010,0,0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00101\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010,0,0+¢\u0006\b\n\u0000\u001a\u0004\b2\u0010/¨\u0006?"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivityImageTextBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mImageTextBgAdapter", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextBgAdapter;", "mImageTextMainAdatper", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextMainAdatper;", "mColorList", "", "", "mDataList", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "mCurImageIdx", "mDialog", "Landroid/app/Dialog;", "mDialogRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mDialogAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "mDialogData", "", "mIsEdit", "", "mEditPosition", "mAdapterPosition", "mIsDataChange", "mImageTextListData", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListData;", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initData", "initToolBar", "initDialog", "mGalleryResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getMGalleryResult", "()Landroidx/activity/result/ActivityResultLauncher;", "mTextAnimationResult", "mTextResult", "getMTextResult", "showDialog", "initBgColorAdapter", "initMainAdapter", "onClick", "v", "Landroid/view/View;", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onBackPressed", "saveData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextActivity extends UiBaseActivity<TextAnimationModel, ActivityImageTextBinding> implements View.OnClickListener {
    private int mCurImageIdx;
    private Dialog mDialog;
    private IosDialogStyleAdapter<Object> mDialogAdapter;
    private RecyclerView mDialogRecyclerView;
    private final ActivityResultLauncher<Intent> mGalleryResult;
    private ImageTextBgAdapter mImageTextBgAdapter;
    private ImageTextListData mImageTextListData;
    private ImageTextMainAdatper mImageTextMainAdatper;
    private boolean mIsDataChange;
    private boolean mIsEdit;
    private final ActivityResultLauncher<Intent> mTextAnimationResult;
    private final ActivityResultLauncher<Intent> mTextResult;
    private List<Integer> mColorList = new ArrayList();
    private List<ChannelListItem> mDataList = new ArrayList();
    private final List<String> mDialogData = new ArrayList();
    private int mEditPosition = -1;
    private int mAdapterPosition = -1;

    public ImageTextActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ImageTextActivity.mGalleryResult$lambda$7(ImageTextActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.mGalleryResult = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ImageTextActivity.mTextAnimationResult$lambda$8(ImageTextActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.mTextAnimationResult = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ImageTextActivity.mTextResult$lambda$9(ImageTextActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResult(...)");
        this.mTextResult = registerForActivityResult3;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityImageTextBinding getViewBinding() {
        ActivityImageTextBinding inflate = ActivityImageTextBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public TextAnimationModel initViewModel() {
        return (TextAnimationModel) new ViewModelProvider(this).get(TextAnimationModel.class);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        LogUtils.file("ImageTextActivity  initView");
        initToolBar();
        initBgColorAdapter();
        initMainAdapter();
        initDialog();
        ImageTextActivity imageTextActivity = this;
        getBinding().ivOk.setOnClickListener(imageTextActivity);
        getBinding().ivCancel.setOnClickListener(imageTextActivity);
        InputFilterUtils inputFilterUtils = InputFilterUtils.INSTANCE;
        EditText etInputName = getBinding().etInputName;
        Intrinsics.checkNotNullExpressionValue(etInputName, "etInputName");
        inputFilterUtils.customLengthFilter(etInputName, 4);
        getBinding().etInputName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean initView$lambda$1;
                initView$lambda$1 = ImageTextActivity.initView$lambda$1(ImageTextActivity.this, textView, i, keyEvent);
                return initView$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initView$lambda$1(final ImageTextActivity imageTextActivity, TextView textView, int i, KeyEvent keyEvent) {
        LogUtils.file("ImageTextActivity  binding.etInputName.setOnEditorActionListener");
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit initView$lambda$1$lambda$0;
                initView$lambda$1$lambda$0 = ImageTextActivity.initView$lambda$1$lambda$0(ImageTextActivity.this);
                return initView$lambda$1$lambda$0;
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$1$lambda$0(ImageTextActivity imageTextActivity) {
        KeyBordUtil.hideSoftKeyboard(imageTextActivity.getBinding().etInputName);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
        super.initData();
        boolean booleanExtra = getIntent().getBooleanExtra("isEdit", false);
        this.mIsEdit = booleanExtra;
        if (booleanExtra) {
            Serializable serializableExtra = getIntent().getSerializableExtra("data");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.wifiled.ipixels.ui.imgtxt.ImageTextListData");
            this.mImageTextListData = (ImageTextListData) serializableExtra;
            this.mEditPosition = getIntent().getIntExtra(PlayerFinal.PLAYER_POSITION, -1);
            ImageTextListData imageTextListData = this.mImageTextListData;
            ImageTextMainAdatper imageTextMainAdatper = null;
            if (imageTextListData == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListData");
                imageTextListData = null;
            }
            List<ChannelListItem> mutableList = CollectionsKt.toMutableList((Collection) imageTextListData.getMDataList());
            this.mDataList = mutableList;
            Log.v("ruis", "mDataList size = " + mutableList.size());
            this.mDataList.add(new ChannelListItem.GiftView(false, "", "", false, new byte[0]));
            ImageTextMainAdatper imageTextMainAdatper2 = this.mImageTextMainAdatper;
            if (imageTextMainAdatper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper2 = null;
            }
            imageTextMainAdatper2.setList(this.mDataList);
            EditText editText = getBinding().etInputName;
            ImageTextListData imageTextListData2 = this.mImageTextListData;
            if (imageTextListData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListData");
                imageTextListData2 = null;
            }
            editText.setText(imageTextListData2.getItName());
            ImageTextListData imageTextListData3 = this.mImageTextListData;
            if (imageTextListData3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListData");
                imageTextListData3 = null;
            }
            this.mCurImageIdx = imageTextListData3.getBgColor();
            ImageTextBgAdapter imageTextBgAdapter = this.mImageTextBgAdapter;
            if (imageTextBgAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
                imageTextBgAdapter = null;
            }
            imageTextBgAdapter.setSelect(this.mCurImageIdx);
            ImageTextBgAdapter imageTextBgAdapter2 = this.mImageTextBgAdapter;
            if (imageTextBgAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
                imageTextBgAdapter2 = null;
            }
            imageTextBgAdapter2.notifyDataSetChanged();
            ImageTextMainAdatper imageTextMainAdatper3 = this.mImageTextMainAdatper;
            if (imageTextMainAdatper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper3 = null;
            }
            if (imageTextMainAdatper3.getData().size() == 7) {
                ImageTextMainAdatper imageTextMainAdatper4 = this.mImageTextMainAdatper;
                if (imageTextMainAdatper4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                } else {
                    imageTextMainAdatper = imageTextMainAdatper4;
                }
                imageTextMainAdatper.setEditStatus(true);
                return;
            }
            ImageTextMainAdatper imageTextMainAdatper5 = this.mImageTextMainAdatper;
            if (imageTextMainAdatper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            } else {
                imageTextMainAdatper = imageTextMainAdatper5;
            }
            imageTextMainAdatper.setEditStatus(false);
        }
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.img_txt_combination));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        getBinding().ivBack.setOnClickListener(this);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    private final void initDialog() {
        List<String> list = this.mDialogData;
        String string = getString(R.string.title_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        list.add(string);
        List<String> list2 = this.mDialogData;
        String string2 = getString(R.string.text_animation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        list2.add(string2);
        List<String> list3 = this.mDialogData;
        String string3 = getString(R.string.title_gallery);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        list3.add(string3);
        List<String> list4 = this.mDialogData;
        String string4 = getString(R.string.delete);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        list4.add(string4);
        ImageTextActivity imageTextActivity = this;
        this.mDialogAdapter = new IosDialogStyleAdapter<>(imageTextActivity, this.mDialogData);
        Dialog dialog = new Dialog(imageTextActivity, R.style.BottomDialogStyle);
        this.mDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(imageTextActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
        Dialog dialog2 = this.mDialog;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCanceledOnTouchOutside(true);
        Dialog dialog3 = this.mDialog;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCancelable(true);
        Dialog dialog4 = this.mDialog;
        Intrinsics.checkNotNull(dialog4);
        Window window = dialog4.getWindow();
        Intrinsics.checkNotNull(window);
        window.getAttributes().width = ScreenUtil.getScreenWidth(imageTextActivity);
        Dialog dialog5 = this.mDialog;
        Intrinsics.checkNotNull(dialog5);
        Window window2 = dialog5.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setGravity(80);
        Dialog dialog6 = this.mDialog;
        Intrinsics.checkNotNull(dialog6);
        Window window3 = dialog6.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setWindowAnimations(R.style.BottomDialogAnimation);
        Dialog dialog7 = this.mDialog;
        Intrinsics.checkNotNull(dialog7);
        TextView textView = (TextView) dialog7.findViewById(R.id.tv_cancel);
        Dialog dialog8 = this.mDialog;
        Intrinsics.checkNotNull(dialog8);
        this.mDialogRecyclerView = (RecyclerView) dialog8.findViewById(R.id.rl_actions);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageTextActivity.initDialog$lambda$2(ImageTextActivity.this, view);
            }
        });
        RecyclerView recyclerView = this.mDialogRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(CoreBase.getContext(), 1, false));
        }
        RecyclerView recyclerView2 = this.mDialogRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.mDialogRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mDialogAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.mDialogRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    ImageTextActivity.initDialog$lambda$3(ImageTextActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda6
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ImageTextActivity.initDialog$lambda$4(ImageTextActivity.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$2(ImageTextActivity imageTextActivity, View view) {
        Dialog dialog = imageTextActivity.mDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$3(ImageTextActivity imageTextActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = imageTextActivity.mDialogAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = imageTextActivity.mDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = imageTextActivity.mDialogRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$4(ImageTextActivity imageTextActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("ImageTextActivity  mDialogAdapter.setOnItemClickListener  position=" + i);
        if (i == 0) {
            Intent intent = new Intent(imageTextActivity, (Class<?>) TextActivity.class);
            intent.putExtra("fromType", 1001);
            imageTextActivity.mTextResult.launch(intent);
        } else if (i == 1) {
            imageTextActivity.mTextAnimationResult.launch(new Intent(imageTextActivity, (Class<?>) TextAnimationActivity.class));
        } else if (i == 2) {
            Intent intent2 = new Intent(imageTextActivity, (Class<?>) GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("select_mode_by_selector", true);
            bundle.putBoolean("select_mode_by_image_text", true);
            bundle.putBoolean("from_it", true);
            intent2.putExtras(bundle);
            imageTextActivity.mGalleryResult.launch(intent2);
        } else if (i == 3) {
            ImageTextMainAdatper imageTextMainAdatper = imageTextActivity.mImageTextMainAdatper;
            ImageTextMainAdatper imageTextMainAdatper2 = null;
            if (imageTextMainAdatper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper = null;
            }
            List<ChannelListItem> data = imageTextMainAdatper.getData();
            imageTextActivity.mDataList = data;
            if (imageTextActivity.mAdapterPosition == CollectionsKt.getLastIndex(data)) {
                return;
            }
            ImageTextMainAdatper imageTextMainAdatper3 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper3 = null;
            }
            List<ChannelListItem> data2 = imageTextMainAdatper3.getData();
            imageTextActivity.mDataList = data2;
            data2.remove(imageTextActivity.mAdapterPosition);
            ImageTextMainAdatper imageTextMainAdatper4 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            } else {
                imageTextMainAdatper2 = imageTextMainAdatper4;
            }
            imageTextMainAdatper2.setList(imageTextActivity.mDataList);
            imageTextActivity.mIsDataChange = true;
        }
        Dialog dialog = imageTextActivity.mDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    public final ActivityResultLauncher<Intent> getMGalleryResult() {
        return this.mGalleryResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mGalleryResult$lambda$7(ImageTextActivity imageTextActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            imageTextActivity.mIsDataChange = true;
            Intent data = activityResult.getData();
            ImageTextMainAdatper imageTextMainAdatper = null;
            Integer valueOf = data != null ? Integer.valueOf(data.getIntExtra("sendType", 0)) : null;
            Intent data2 = activityResult.getData();
            byte[] byteArrayExtra = data2 != null ? data2.getByteArrayExtra("sendData") : null;
            if (imageTextActivity.mAdapterPosition != -1) {
                if (valueOf != null && valueOf.intValue() == 1) {
                    int i = imageTextActivity.mAdapterPosition;
                    ImageTextMainAdatper imageTextMainAdatper2 = imageTextActivity.mImageTextMainAdatper;
                    if (imageTextMainAdatper2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                        imageTextMainAdatper2 = null;
                    }
                    if (i != imageTextMainAdatper2.getData().size() - 1) {
                        ImageTextMainAdatper imageTextMainAdatper3 = imageTextActivity.mImageTextMainAdatper;
                        if (imageTextMainAdatper3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                            imageTextMainAdatper3 = null;
                        }
                        imageTextMainAdatper3.removeAt(imageTextActivity.mAdapterPosition);
                    }
                    if (byteArrayExtra != null) {
                        ImageTextMainAdatper imageTextMainAdatper4 = imageTextActivity.mImageTextMainAdatper;
                        if (imageTextMainAdatper4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                            imageTextMainAdatper4 = null;
                        }
                        imageTextMainAdatper4.addData(imageTextActivity.mAdapterPosition, (int) new ChannelListItem.GiftView(false, "", "1", false, byteArrayExtra));
                    }
                } else if (valueOf != null && valueOf.intValue() == 2) {
                    int i2 = imageTextActivity.mAdapterPosition;
                    ImageTextMainAdatper imageTextMainAdatper5 = imageTextActivity.mImageTextMainAdatper;
                    if (imageTextMainAdatper5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                        imageTextMainAdatper5 = null;
                    }
                    if (i2 != imageTextMainAdatper5.getData().size() - 1) {
                        ImageTextMainAdatper imageTextMainAdatper6 = imageTextActivity.mImageTextMainAdatper;
                        if (imageTextMainAdatper6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                            imageTextMainAdatper6 = null;
                        }
                        imageTextMainAdatper6.removeAt(imageTextActivity.mAdapterPosition);
                    }
                    if (byteArrayExtra != null) {
                        ImageTextMainAdatper imageTextMainAdatper7 = imageTextActivity.mImageTextMainAdatper;
                        if (imageTextMainAdatper7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                            imageTextMainAdatper7 = null;
                        }
                        imageTextMainAdatper7.addData(imageTextActivity.mAdapterPosition, (int) new ChannelListItem.ImagView(false, null, "1", false, byteArrayExtra));
                    }
                } else {
                    ToastUtil.show(imageTextActivity.getString(R.string.msg_send_fail));
                }
                ImageTextMainAdatper imageTextMainAdatper8 = imageTextActivity.mImageTextMainAdatper;
                if (imageTextMainAdatper8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                    imageTextMainAdatper8 = null;
                }
                if (imageTextMainAdatper8.getData().size() == 7) {
                    ImageTextMainAdatper imageTextMainAdatper9 = imageTextActivity.mImageTextMainAdatper;
                    if (imageTextMainAdatper9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                    } else {
                        imageTextMainAdatper = imageTextMainAdatper9;
                    }
                    imageTextMainAdatper.setEditStatus(true);
                    return;
                }
                ImageTextMainAdatper imageTextMainAdatper10 = imageTextActivity.mImageTextMainAdatper;
                if (imageTextMainAdatper10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                } else {
                    imageTextMainAdatper = imageTextMainAdatper10;
                }
                imageTextMainAdatper.setEditStatus(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mTextAnimationResult$lambda$8(ImageTextActivity imageTextActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            imageTextActivity.mIsDataChange = true;
            Intent data = activityResult.getData();
            String stringExtra = data != null ? data.getStringExtra("gif_path") : null;
            Log.v("ruis", "trimPath=" + stringExtra);
            String str = stringExtra;
            if (str == null || str.length() == 0) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(imageTextActivity), Dispatchers.getIO(), null, new ImageTextActivity$mTextAnimationResult$1$1(stringExtra, imageTextActivity, null), 2, null);
        }
    }

    public final ActivityResultLauncher<Intent> getMTextResult() {
        return this.mTextResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mTextResult$lambda$9(ImageTextActivity imageTextActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            imageTextActivity.mIsDataChange = true;
            Intent data = activityResult.getData();
            ImageTextMainAdatper imageTextMainAdatper = null;
            Serializable serializableExtra = data != null ? data.getSerializableExtra("textData") : null;
            int i = imageTextActivity.mAdapterPosition;
            ImageTextMainAdatper imageTextMainAdatper2 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper2 = null;
            }
            if (i != imageTextMainAdatper2.getData().size() - 1) {
                ImageTextMainAdatper imageTextMainAdatper3 = imageTextActivity.mImageTextMainAdatper;
                if (imageTextMainAdatper3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                    imageTextMainAdatper3 = null;
                }
                imageTextMainAdatper3.getData().remove(imageTextActivity.mAdapterPosition);
            }
            ImageTextMainAdatper imageTextMainAdatper4 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper4 = null;
            }
            int i2 = imageTextActivity.mAdapterPosition;
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
            imageTextMainAdatper4.addData(i2, (int) serializableExtra);
            SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
            ImageTextMainAdatper imageTextMainAdatper5 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                imageTextMainAdatper5 = null;
            }
            if (imageTextMainAdatper5.getData().size() == 7) {
                ImageTextMainAdatper imageTextMainAdatper6 = imageTextActivity.mImageTextMainAdatper;
                if (imageTextMainAdatper6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
                } else {
                    imageTextMainAdatper = imageTextMainAdatper6;
                }
                imageTextMainAdatper.setEditStatus(true);
                return;
            }
            ImageTextMainAdatper imageTextMainAdatper7 = imageTextActivity.mImageTextMainAdatper;
            if (imageTextMainAdatper7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            } else {
                imageTextMainAdatper = imageTextMainAdatper7;
            }
            imageTextMainAdatper.setEditStatus(false);
        }
    }

    private final void showDialog() {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(getString(R.string.tip_it)).setMessage(R.string.it_data_no_save_tip).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ImageTextActivity.showDialog$lambda$10(ImageTextActivity.this, dialogInterface, i);
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$$ExternalSyntheticLambda8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ImageTextActivity.showDialog$lambda$11(ImageTextActivity.this, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$10(ImageTextActivity imageTextActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        imageTextActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$11(ImageTextActivity imageTextActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        imageTextActivity.saveData();
    }

    private final void initBgColorAdapter() {
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_1));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_2));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_3));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_4));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_5));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_6));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_7));
        this.mColorList.add(Integer.valueOf(R.mipmap.dc_phrase_colour_8));
        ImageTextBgAdapter imageTextBgAdapter = new ImageTextBgAdapter();
        this.mImageTextBgAdapter = imageTextBgAdapter;
        imageTextBgAdapter.setList(this.mColorList);
        ImageTextBgAdapter imageTextBgAdapter2 = this.mImageTextBgAdapter;
        ImageTextBgAdapter imageTextBgAdapter3 = null;
        if (imageTextBgAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
            imageTextBgAdapter2 = null;
        }
        imageTextBgAdapter2.setSelect(this.mCurImageIdx);
        getBinding().rcvImage.setLayoutManager(new GridLayoutManager(this, 8));
        RecyclerView recyclerView = getBinding().rcvImage;
        ImageTextBgAdapter imageTextBgAdapter4 = this.mImageTextBgAdapter;
        if (imageTextBgAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
            imageTextBgAdapter4 = null;
        }
        recyclerView.setAdapter(imageTextBgAdapter4);
        ImageTextBgAdapter imageTextBgAdapter5 = this.mImageTextBgAdapter;
        if (imageTextBgAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
        } else {
            imageTextBgAdapter3 = imageTextBgAdapter5;
        }
        imageTextBgAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$initBgColorAdapter$1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                ImageTextBgAdapter imageTextBgAdapter6;
                ImageTextBgAdapter imageTextBgAdapter7;
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                LogUtils.file("ImageTextActivity  mImageTextBgAdapter.setOnItemClickListener  position=" + position);
                ImageTextActivity.this.mCurImageIdx = position;
                imageTextBgAdapter6 = ImageTextActivity.this.mImageTextBgAdapter;
                ImageTextBgAdapter imageTextBgAdapter8 = null;
                if (imageTextBgAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
                    imageTextBgAdapter6 = null;
                }
                imageTextBgAdapter6.setSelect(position);
                imageTextBgAdapter7 = ImageTextActivity.this.mImageTextBgAdapter;
                if (imageTextBgAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextBgAdapter");
                } else {
                    imageTextBgAdapter8 = imageTextBgAdapter7;
                }
                imageTextBgAdapter8.notifyDataSetChanged();
            }
        });
    }

    private final void initMainAdapter() {
        this.mDataList.add(new ChannelListItem.GiftView(false, "", "", false, new byte[0]));
        int ledType = AppConfig.INSTANCE.getLedType();
        int i = R.layout.image_text_main_item;
        switch (ledType) {
            case 0:
            case 2:
                i = R.layout.image_text_main_item_1;
                break;
            case 1:
            case 15:
                i = R.layout.image_text_main_item_96;
                break;
            case 4:
            case 9:
            case 10:
                i = R.layout.image_text_main_item_32;
                break;
            case 5:
                i = R.layout.image_text_main_item_20;
                break;
            case 7:
                i = R.layout.image_text_main_item_144;
                break;
            case 8:
                i = R.layout.image_text_main_item_192;
                break;
            case 11:
            case 13:
                i = R.layout.image_text_main_item_3296;
                break;
            case 14:
                i = R.layout.image_text_main_item_32160;
                break;
            case 16:
                i = R.layout.image_text_main_item_32256;
                break;
        }
        if (AppConfig.INSTANCE.getLedType() == 0 || AppConfig.INSTANCE.getLedType() == 2) {
            getBinding().imgRecycle.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            getBinding().imgRecycle.setLayoutManager(new LinearLayoutManager(this));
        }
        ImageTextMainAdatper imageTextMainAdatper = new ImageTextMainAdatper(i);
        this.mImageTextMainAdatper = imageTextMainAdatper;
        imageTextMainAdatper.setList(this.mDataList);
        RecyclerView recyclerView = getBinding().imgRecycle;
        ImageTextMainAdatper imageTextMainAdatper2 = this.mImageTextMainAdatper;
        ImageTextMainAdatper imageTextMainAdatper3 = null;
        if (imageTextMainAdatper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            imageTextMainAdatper2 = null;
        }
        recyclerView.setAdapter(imageTextMainAdatper2);
        ImageTextMainAdatper imageTextMainAdatper4 = this.mImageTextMainAdatper;
        if (imageTextMainAdatper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
        } else {
            imageTextMainAdatper3 = imageTextMainAdatper4;
        }
        imageTextMainAdatper3.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextActivity$initMainAdapter$1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Dialog dialog;
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                LogUtils.file("ImageTextActivity  mImageTextMainAdatper onItemClick" + position);
                ImageTextActivity.this.mAdapterPosition = position;
                dialog = ImageTextActivity.this.mDialog;
                if (dialog != null) {
                    dialog.show();
                }
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            LogUtils.file("ImageTextActivity   binding.ivBack");
            if (this.mIsDataChange) {
                showDialog();
                return;
            } else {
                finish();
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().ivOk)) {
            LogUtils.file("ImageTextActivity   binding.ivOk");
            saveData();
        } else if (Intrinsics.areEqual(v, getBinding().ivCancel)) {
            LogUtils.file("ImageTextActivity   binding.ivCancel");
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return false;
        }
        if (this.mIsDataChange) {
            showDialog();
            return false;
        }
        finish();
        return false;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    private final void saveData() {
        LogUtils.file("ImageTextActivity   saveData");
        String obj = getBinding().etInputName.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtil.show(getString(R.string.name_not_null));
            return;
        }
        ImageTextMainAdatper imageTextMainAdatper = this.mImageTextMainAdatper;
        if (imageTextMainAdatper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextMainAdatper");
            imageTextMainAdatper = null;
        }
        List<ChannelListItem> data = imageTextMainAdatper.getData();
        if (data.size() == 1) {
            ToastUtil.show(getString(R.string.data_not_null));
            return;
        }
        data.remove(CollectionsKt.getLastIndex(data));
        ImageTextListData imageTextListData = new ImageTextListData(0, this.mCurImageIdx, obj, false, "", data, AppConfig.INSTANCE.getLedType());
        ArrayList arrayList = new ArrayList();
        if (!this.mIsEdit) {
            ImageTextActivity imageTextActivity = this;
            if (SPUtils.contains(imageTextActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType())) {
                if (SPUtils.get(imageTextActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                    Object obj2 = SPUtils.get(imageTextActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                    Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                    arrayList = (List) obj2;
                }
            }
            arrayList.add(imageTextListData);
        } else {
            Object obj3 = SPUtils.get(this, "image_text_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
            Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
            arrayList = (List) obj3;
            arrayList.set(this.mEditPosition, imageTextListData);
        }
        SPUtils.put(this, "image_text_list_" + AppConfig.INSTANCE.getLedType(), arrayList);
        setResult(-1, new Intent());
        finish();
    }
}
