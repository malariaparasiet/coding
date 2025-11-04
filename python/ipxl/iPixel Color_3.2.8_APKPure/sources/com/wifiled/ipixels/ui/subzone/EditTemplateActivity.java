package com.wifiled.ipixels.ui.subzone;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.UriUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.SendDataCallback;
import com.wifiled.ipixels.databinding.ActivityEditTemplateBinding;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.diy.DiyImageActivity;
import com.wifiled.ipixels.ui.diy.TemplateDiyAnimActivity;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationModel;
import com.wifiled.ipixels.ui.subzone.TextBorderDiaglog;
import com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFive12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewThree12;
import com.wifiled.ipixels.ui.subzone.templateview.TemplateViewTwo12;
import com.wifiled.ipixels.ui.text.TextActivity;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.ResourceUtils;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: EditTemplateActivity.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u00109\u001a\u00020\u0003H\u0016J\b\u0010<\u001a\u00020\u0002H\u0016J\u0012\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\b\u0010A\u001a\u00020>H\u0016J\b\u0010B\u001a\u00020>H\u0002J\b\u0010C\u001a\u00020>H\u0002J\b\u0010D\u001a\u00020>H\u0002J\b\u0010E\u001a\u00020>H\u0002J\b\u0010F\u001a\u00020>H\u0002J\u0010\u0010G\u001a\u00020>2\u0006\u0010H\u001a\u00020IH\u0016J\b\u0010L\u001a\u00020>H\u0002J\"\u0010U\u001a\u00020>2\u0006\u0010V\u001a\u00020\b2\u0006\u0010W\u001a\u00020\b2\b\u0010X\u001a\u0004\u0018\u00010OH\u0014J\b\u0010Z\u001a\u00020>H\u0014J\u0010\u0010[\u001a\u00020>2\u0006\u0010X\u001a\u00020\\H\u0002J\u0010\u0010]\u001a\u00020>2\u0006\u0010X\u001a\u00020\\H\u0002J\u0010\u0010^\u001a\u00020>2\u0006\u0010X\u001a\u00020\\H\u0002J\u0010\u0010_\u001a\u00020>2\u0006\u0010X\u001a\u00020\\H\u0002J\u0010\u0010`\u001a\u00020>2\u0006\u0010X\u001a\u00020\\H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010M\u001a\u0010\u0012\f\u0012\n P*\u0004\u0018\u00010O0O0NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010Q\u001a\u0010\u0012\f\u0012\n P*\u0004\u0018\u00010O0O0NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010R\u001a\u0010\u0012\f\u0012\n P*\u0004\u0018\u00010O0O0NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010S\u001a\u0010\u0012\f\u0012\n P*\u0004\u0018\u00010T0T0NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010Y\u001a\u0010\u0012\f\u0012\n P*\u0004\u0018\u00010O0O0NX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/EditTemplateActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivityEditTemplateBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mTemplateType", "", "mAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mIsDataChange", "", "mChooseTvIndex", "mChooseIVIndex", "mTextData", "", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem;", "mBorderIndex", "mBorderEffectData", "mBorderSpeed", "misEdit", "mEditPosition", "mImageData1", "", "mImageData2", "mImage1Type", "mImage2Type", "mSubzoneData", "Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "mBorder1_1", "", "mBorder1_2", "mBorder1_3", "mBorder1_4", "mBorder1_6", "mBorder1_7", "mBorder1_8", "mBorder1_5", "mBorder1_9", "mBorder1_10", "mBorder1_12", "mBorder1_11", "mBorder1_13", "mBorder1_14", "mBorder1_16", "mBorder1_18", "mBorder1_20", "mBorder1_22", "mBorder1_24", "mBorder1_26", "mBorder1_28", "mSendDataCallback", "Lcom/wifiled/ipixels/core/SendDataCallback;", "getViewBinding", "mTextBorderDiaglog", "Lcom/wifiled/ipixels/ui/subzone/TextBorderDiaglog;", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initData", "initIosDialogAdapter", "initBorderData", "initTemplate", "initToolBar", "toEditText", "onClick", "v", "Landroid/view/View;", "dialog", "Landroid/app/Dialog;", "chooseImage", "galleryMedia", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "diyImageMedia", "diyVideoMedia", "pickMedia", "Landroidx/activity/result/PickVisualMediaRequest;", "onActivityResult", "requestCode", "resultCode", "data", "mTextResult", "onDestroy", "setTemplateData1", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "setTemplateData2", "setTemplateData3", "setTemplateData4", "setTemplateData5", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EditTemplateActivity extends UiBaseActivity<TextAnimationModel, ActivityEditTemplateBinding> implements View.OnClickListener {
    private RecyclerView actionRecyclerView;
    private Dialog dialog;
    private final ActivityResultLauncher<Intent> diyImageMedia;
    private final ActivityResultLauncher<Intent> diyVideoMedia;
    private final ActivityResultLauncher<Intent> galleryMedia;
    private IosDialogStyleAdapter<Object> mAdapter;
    private int[] mBorder1_1;
    private int[] mBorder1_10;
    private int[] mBorder1_11;
    private int[] mBorder1_12;
    private int[] mBorder1_13;
    private int[] mBorder1_14;
    private int[] mBorder1_16;
    private int[] mBorder1_18;
    private int[] mBorder1_2;
    private int[] mBorder1_20;
    private int[] mBorder1_22;
    private int[] mBorder1_24;
    private int[] mBorder1_26;
    private int[] mBorder1_28;
    private int[] mBorder1_3;
    private int[] mBorder1_4;
    private int[] mBorder1_5;
    private int[] mBorder1_6;
    private int[] mBorder1_7;
    private int[] mBorder1_8;
    private int[] mBorder1_9;
    private int mBorderEffectData;
    private int mBorderIndex;
    private int mBorderSpeed;
    private int mChooseIVIndex;
    private int mChooseTvIndex;
    private int mImage1Type;
    private int mImage2Type;
    private boolean mIsDataChange;
    private SendDataCallback mSendDataCallback;
    private SubzoneData mSubzoneData;
    private int mTemplateType;
    private TextBorderDiaglog mTextBorderDiaglog;
    private final ActivityResultLauncher<Intent> mTextResult;
    private boolean misEdit;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private List<ChannelListItem> mTextData = new ArrayList();
    private int mEditPosition = -1;
    private byte[] mImageData1 = new byte[0];
    private byte[] mImageData2 = new byte[0];

    public EditTemplateActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditTemplateActivity.galleryMedia$lambda$22(EditTemplateActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.galleryMedia = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditTemplateActivity.diyImageMedia$lambda$28(EditTemplateActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.diyImageMedia = registerForActivityResult2;
        ActivityResultLauncher<Intent> registerForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditTemplateActivity.diyVideoMedia$lambda$33(EditTemplateActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult3, "registerForActivityResult(...)");
        this.diyVideoMedia = registerForActivityResult3;
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult4 = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditTemplateActivity.pickMedia$lambda$34(EditTemplateActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult4, "registerForActivityResult(...)");
        this.pickMedia = registerForActivityResult4;
        ActivityResultLauncher<Intent> registerForActivityResult5 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditTemplateActivity.mTextResult$lambda$41(EditTemplateActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult5, "registerForActivityResult(...)");
        this.mTextResult = registerForActivityResult5;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityEditTemplateBinding getViewBinding() {
        ActivityEditTemplateBinding inflate = ActivityEditTemplateBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public TextAnimationModel initViewModel() {
        return (TextAnimationModel) new ViewModelProvider(this).get(TextAnimationModel.class);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        SubzoneData subzoneData;
        super.initView(savedInstanceState);
        this.misEdit = getIntent().getBooleanExtra("isEdit", false);
        int intExtra = getIntent().getIntExtra("template_type", 0);
        this.mTemplateType = intExtra;
        LogUtils.vTag("ruis", "mTemplateType----" + intExtra);
        EditTemplateActivity editTemplateActivity = this;
        getBinding().ivBorder.setOnClickListener(editTemplateActivity);
        getBinding().ivNext.setOnClickListener(editTemplateActivity);
        getBinding().borderCl.setOnClickListener(editTemplateActivity);
        getBinding().ivBorderNext.setOnClickListener(editTemplateActivity);
        initToolBar();
        if (this.misEdit) {
            if (Build.VERSION.SDK_INT >= 33) {
                subzoneData = (SubzoneData) getIntent().getSerializableExtra("data", SubzoneData.class);
            } else {
                subzoneData = (SubzoneData) getIntent().getSerializableExtra("data");
            }
            this.mSubzoneData = subzoneData;
            this.mEditPosition = getIntent().getIntExtra(PlayerFinal.PLAYER_POSITION, -1);
            SubzoneData subzoneData2 = this.mSubzoneData;
            Integer valueOf = subzoneData2 != null ? Integer.valueOf(subzoneData2.getTemplateType()) : null;
            Intrinsics.checkNotNull(valueOf);
            this.mTemplateType = valueOf.intValue();
        }
        initBorderData();
        initTemplate();
        initIosDialogAdapter();
        this.mSendDataCallback = new EditTemplateActivity$initView$1(this);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
        super.initData();
        if (this.misEdit) {
            return;
        }
        this.mTextData.add(new ChannelListItem.TextEmojView(false, new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null), String.valueOf(ChannelIndex.INSTANCE.index()), false, new byte[0], 0, 0, 0, 0, 480, null));
        this.mTextData.add(new ChannelListItem.TextEmojView(false, new EventText(null, 0.0f, 0.0f, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 131071, null), String.valueOf(ChannelIndex.INSTANCE.index()), false, new byte[0], 0, 0, 0, 0, 480, null));
    }

    private final void initIosDialogAdapter() {
        this.mAdapter = new IosDialogStyleAdapter<>(this, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.title_diy_app_photos), getString(R.string.title_diy_image), getString(R.string.title_diy_video), getString(R.string.title_diy_phone_photos)}));
    }

    private final void initBorderData() {
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
            case 10:
                EditTemplateActivity editTemplateActivity = this;
                this.mBorder1_1 = ResourceUtils.getResIds(editTemplateActivity, R.array.subzone_border_2_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity, R.array.subzone_border_2_1);
                this.mBorder1_4 = ResourceUtils.getResIds(editTemplateActivity, R.array.subzone_border_4_1);
                break;
            case 11:
                EditTemplateActivity editTemplateActivity2 = this;
                this.mBorder1_3 = ResourceUtils.getResIds(editTemplateActivity2, R.array.subzone_border_3_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity2, R.array.subzone_border_2_1);
                this.mBorder1_4 = ResourceUtils.getResIds(editTemplateActivity2, R.array.subzone_border_4_1);
                this.mBorder1_6 = ResourceUtils.getResIds(editTemplateActivity2, R.array.subzone_border_6_1);
                break;
            case 12:
                EditTemplateActivity editTemplateActivity3 = this;
                this.mBorder1_3 = ResourceUtils.getResIds(editTemplateActivity3, R.array.subzone_border_3_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity3, R.array.subzone_border_2_1);
                this.mBorder1_4 = ResourceUtils.getResIds(editTemplateActivity3, R.array.subzone_border_4_1);
                this.mBorder1_6 = ResourceUtils.getResIds(editTemplateActivity3, R.array.subzone_border_6_1);
                this.mBorder1_8 = ResourceUtils.getResIds(editTemplateActivity3, R.array.subzone_border_8_1);
                break;
            case 14:
                EditTemplateActivity editTemplateActivity4 = this;
                this.mBorder1_5 = ResourceUtils.getResIds(editTemplateActivity4, R.array.subzone_border_5_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity4, R.array.subzone_border_5_1);
                this.mBorder1_4 = ResourceUtils.getResIds(editTemplateActivity4, R.array.subzone_border_4_1);
                this.mBorder1_10 = ResourceUtils.getResIds(editTemplateActivity4, R.array.subzone_border_10_1);
                this.mBorder1_8 = ResourceUtils.getResIds(editTemplateActivity4, R.array.subzone_border_8_1);
                break;
            case 15:
                EditTemplateActivity editTemplateActivity5 = this;
                this.mBorder1_6 = ResourceUtils.getResIds(editTemplateActivity5, R.array.subzone_border_6_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity5, R.array.subzone_border_6_1);
                this.mBorder1_12 = ResourceUtils.getResIds(editTemplateActivity5, R.array.subzone_border_12_1);
                this.mBorder1_5 = ResourceUtils.getResIds(editTemplateActivity5, R.array.subzone_border_5_1);
                this.mBorder1_10 = ResourceUtils.getResIds(editTemplateActivity5, R.array.subzone_border_10_1);
                break;
            case 16:
                EditTemplateActivity editTemplateActivity6 = this;
                this.mBorder1_8 = ResourceUtils.getResIds(editTemplateActivity6, R.array.subzone_border_8_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity6, R.array.subzone_border_8_1);
                this.mBorder1_7 = ResourceUtils.getResIds(editTemplateActivity6, R.array.subzone_border_7_1);
                this.mBorder1_14 = ResourceUtils.getResIds(editTemplateActivity6, R.array.subzone_border_14_1);
                this.mBorder1_16 = ResourceUtils.getResIds(editTemplateActivity6, R.array.subzone_border_16_1);
                break;
            case 17:
                EditTemplateActivity editTemplateActivity7 = this;
                this.mBorder1_10 = ResourceUtils.getResIds(editTemplateActivity7, R.array.subzone_border_10_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity7, R.array.subzone_border_10_1);
                this.mBorder1_20 = ResourceUtils.getResIds(editTemplateActivity7, R.array.subzone_border_20_1);
                this.mBorder1_9 = ResourceUtils.getResIds(editTemplateActivity7, R.array.subzone_border_9_1);
                this.mBorder1_18 = ResourceUtils.getResIds(editTemplateActivity7, R.array.subzone_border_18_1);
                break;
            case 18:
                EditTemplateActivity editTemplateActivity8 = this;
                this.mBorder1_12 = ResourceUtils.getResIds(editTemplateActivity8, R.array.subzone_border_12_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity8, R.array.subzone_border_12_1);
                this.mBorder1_22 = ResourceUtils.getResIds(editTemplateActivity8, R.array.subzone_border_22_1);
                this.mBorder1_24 = ResourceUtils.getResIds(editTemplateActivity8, R.array.subzone_border_24_1);
                this.mBorder1_11 = ResourceUtils.getResIds(editTemplateActivity8, R.array.subzone_border_11_1);
                break;
            case 19:
                EditTemplateActivity editTemplateActivity9 = this;
                this.mBorder1_14 = ResourceUtils.getResIds(editTemplateActivity9, R.array.subzone_border_14_1);
                this.mBorder1_2 = ResourceUtils.getResIds(editTemplateActivity9, R.array.subzone_border_14_1);
                this.mBorder1_13 = ResourceUtils.getResIds(editTemplateActivity9, R.array.subzone_border_13_1);
                this.mBorder1_26 = ResourceUtils.getResIds(editTemplateActivity9, R.array.subzone_border_26_1);
                this.mBorder1_28 = ResourceUtils.getResIds(editTemplateActivity9, R.array.subzone_border_28_1);
                break;
        }
    }

    private final void initTemplate() {
        int[] iArr;
        Integer valueOf;
        int[] iArr2;
        byte[] imageData2;
        int[] iArr3;
        byte[] imageData22;
        byte[] imageData23;
        byte[] imageData24;
        int i = this.mTemplateType;
        if (i == 1) {
            getBinding().template1.setVisibility(0);
            getBinding().template1.setOnClickTvClickListener(new OnTvClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initTemplate$1
                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    LogUtils.vTag("ruis", "onTvClick1");
                    EditTemplateActivity.this.mChooseTvIndex = 1;
                    EditTemplateActivity.this.toEditText();
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    LogUtils.vTag("ruis", "onTvClick2");
                    EditTemplateActivity.this.mChooseTvIndex = 2;
                    EditTemplateActivity.this.toEditText();
                }
            });
            if (this.misEdit) {
                SubzoneData subzoneData = this.mSubzoneData;
                List<ChannelListItem> mDataList = subzoneData != null ? subzoneData.getMDataList() : null;
                Intrinsics.checkNotNull(mDataList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.wifiled.ipixels.ui.channel.ChannelListItem>");
                List<ChannelListItem> asMutableList = TypeIntrinsics.asMutableList(mDataList);
                this.mTextData = asMutableList;
                LogUtils.vTag("ruis", "mTextData.size ---" + asMutableList.size());
                SubzoneData subzoneData2 = this.mSubzoneData;
                if ((subzoneData2 == null || subzoneData2.getItemBorderIndex() != 0) && (iArr = this.mBorder1_2) != null) {
                    SubzoneData subzoneData3 = this.mSubzoneData;
                    valueOf = subzoneData3 != null ? Integer.valueOf(subzoneData3.getItemBorderIndex()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    getBinding().template1.setBorder(iArr[valueOf.intValue()]);
                }
                for (ChannelListItem channelListItem : this.mTextData) {
                    Intrinsics.checkNotNull(channelListItem, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                    setTemplateData1((ChannelListItem.TextEmojView) channelListItem);
                }
                return;
            }
            return;
        }
        if (i == 2) {
            getBinding().template2.setVisibility(0);
            getBinding().borderCl.setVisibility(8);
            getBinding().template2.setOnClickTvClickListener(new OnTvClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initTemplate$3
                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseTvIndex = 1;
                    EditTemplateActivity.this.toEditText();
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseIVIndex = 1;
                    EditTemplateActivity.this.chooseImage();
                }
            });
            if (this.misEdit) {
                SubzoneData subzoneData4 = this.mSubzoneData;
                List<ChannelListItem> mDataList2 = subzoneData4 != null ? subzoneData4.getMDataList() : null;
                Intrinsics.checkNotNull(mDataList2, "null cannot be cast to non-null type kotlin.collections.MutableList<com.wifiled.ipixels.ui.channel.ChannelListItem>");
                this.mTextData = TypeIntrinsics.asMutableList(mDataList2);
                SubzoneData subzoneData5 = this.mSubzoneData;
                Integer valueOf2 = subzoneData5 != null ? Integer.valueOf(subzoneData5.getImage1Type()) : null;
                Intrinsics.checkNotNull(valueOf2);
                this.mImage1Type = valueOf2.intValue();
                SubzoneData subzoneData6 = this.mSubzoneData;
                Integer valueOf3 = subzoneData6 != null ? Integer.valueOf(subzoneData6.getImage2Type()) : null;
                Intrinsics.checkNotNull(valueOf3);
                this.mImage2Type = valueOf3.intValue();
                SubzoneData subzoneData7 = this.mSubzoneData;
                byte[] imageData1 = subzoneData7 != null ? subzoneData7.getImageData1() : null;
                Intrinsics.checkNotNull(imageData1);
                this.mImageData1 = imageData1;
                SubzoneData subzoneData8 = this.mSubzoneData;
                byte[] imageData25 = subzoneData8 != null ? subzoneData8.getImageData2() : null;
                Intrinsics.checkNotNull(imageData25);
                this.mImageData2 = imageData25;
                ChannelListItem channelListItem2 = this.mTextData.get(0);
                Intrinsics.checkNotNull(channelListItem2, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem2;
                SubzoneData subzoneData9 = this.mSubzoneData;
                if ((subzoneData9 == null || subzoneData9.getItemBorderIndex() != 0) && (iArr2 = this.mBorder1_2) != null) {
                    SubzoneData subzoneData10 = this.mSubzoneData;
                    valueOf = subzoneData10 != null ? Integer.valueOf(subzoneData10.getItemBorderIndex()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    getBinding().template2.setBorder(iArr2[valueOf.intValue()]);
                }
                setTemplateData2(textEmojView);
                SubzoneData subzoneData11 = this.mSubzoneData;
                if (subzoneData11 == null || (imageData2 = subzoneData11.getImageData2()) == null) {
                    return;
                }
                getBinding().template2.setData2(imageData2);
                return;
            }
            return;
        }
        if (i == 3) {
            getBinding().template3.setVisibility(0);
            getBinding().borderCl.setVisibility(8);
            getBinding().template3.setOnClickTvClickListener(new OnTvClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initTemplate$6
                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseTvIndex = 1;
                    EditTemplateActivity.this.toEditText();
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseIVIndex = 1;
                    EditTemplateActivity.this.chooseImage();
                }
            });
            if (this.misEdit) {
                SubzoneData subzoneData12 = this.mSubzoneData;
                List<ChannelListItem> mDataList3 = subzoneData12 != null ? subzoneData12.getMDataList() : null;
                Intrinsics.checkNotNull(mDataList3, "null cannot be cast to non-null type kotlin.collections.MutableList<com.wifiled.ipixels.ui.channel.ChannelListItem>");
                this.mTextData = TypeIntrinsics.asMutableList(mDataList3);
                SubzoneData subzoneData13 = this.mSubzoneData;
                Integer valueOf4 = subzoneData13 != null ? Integer.valueOf(subzoneData13.getImage1Type()) : null;
                Intrinsics.checkNotNull(valueOf4);
                this.mImage1Type = valueOf4.intValue();
                SubzoneData subzoneData14 = this.mSubzoneData;
                Integer valueOf5 = subzoneData14 != null ? Integer.valueOf(subzoneData14.getImage2Type()) : null;
                Intrinsics.checkNotNull(valueOf5);
                this.mImage2Type = valueOf5.intValue();
                SubzoneData subzoneData15 = this.mSubzoneData;
                byte[] imageData12 = subzoneData15 != null ? subzoneData15.getImageData1() : null;
                Intrinsics.checkNotNull(imageData12);
                this.mImageData1 = imageData12;
                SubzoneData subzoneData16 = this.mSubzoneData;
                byte[] imageData26 = subzoneData16 != null ? subzoneData16.getImageData2() : null;
                Intrinsics.checkNotNull(imageData26);
                this.mImageData2 = imageData26;
                ChannelListItem channelListItem3 = this.mTextData.get(0);
                Intrinsics.checkNotNull(channelListItem3, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                ChannelListItem.TextEmojView textEmojView2 = (ChannelListItem.TextEmojView) channelListItem3;
                SubzoneData subzoneData17 = this.mSubzoneData;
                if ((subzoneData17 == null || subzoneData17.getItemBorderIndex() != 0) && (iArr3 = this.mBorder1_2) != null) {
                    SubzoneData subzoneData18 = this.mSubzoneData;
                    valueOf = subzoneData18 != null ? Integer.valueOf(subzoneData18.getItemBorderIndex()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    getBinding().template3.setBorder(iArr3[valueOf.intValue()]);
                }
                setTemplateData3(textEmojView2);
                SubzoneData subzoneData19 = this.mSubzoneData;
                if (subzoneData19 == null || (imageData22 = subzoneData19.getImageData2()) == null) {
                    return;
                }
                getBinding().template3.setData2(imageData22);
                return;
            }
            return;
        }
        if (i == 4) {
            getBinding().template4.setVisibility(0);
            getBinding().template4.setOnClickTvClickListener(new OnTvClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initTemplate$9
                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseTvIndex = 1;
                    EditTemplateActivity.this.toEditText();
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onTvClick2(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseTvIndex = 2;
                    EditTemplateActivity.this.toEditText();
                }

                @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
                public void onIvClick1(View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    EditTemplateActivity.this.mChooseIVIndex = 1;
                    EditTemplateActivity.this.chooseImage();
                }
            });
            if (this.misEdit) {
                SubzoneData subzoneData20 = this.mSubzoneData;
                List<ChannelListItem> mDataList4 = subzoneData20 != null ? subzoneData20.getMDataList() : null;
                Intrinsics.checkNotNull(mDataList4, "null cannot be cast to non-null type kotlin.collections.MutableList<com.wifiled.ipixels.ui.channel.ChannelListItem>");
                this.mTextData = TypeIntrinsics.asMutableList(mDataList4);
                SubzoneData subzoneData21 = this.mSubzoneData;
                Integer valueOf6 = subzoneData21 != null ? Integer.valueOf(subzoneData21.getImage1Type()) : null;
                Intrinsics.checkNotNull(valueOf6);
                this.mImage1Type = valueOf6.intValue();
                SubzoneData subzoneData22 = this.mSubzoneData;
                Integer valueOf7 = subzoneData22 != null ? Integer.valueOf(subzoneData22.getImage2Type()) : null;
                Intrinsics.checkNotNull(valueOf7);
                this.mImage2Type = valueOf7.intValue();
                SubzoneData subzoneData23 = this.mSubzoneData;
                byte[] imageData13 = subzoneData23 != null ? subzoneData23.getImageData1() : null;
                Intrinsics.checkNotNull(imageData13);
                this.mImageData1 = imageData13;
                SubzoneData subzoneData24 = this.mSubzoneData;
                byte[] imageData27 = subzoneData24 != null ? subzoneData24.getImageData2() : null;
                Intrinsics.checkNotNull(imageData27);
                this.mImageData2 = imageData27;
                for (ChannelListItem channelListItem4 : this.mTextData) {
                    Intrinsics.checkNotNull(channelListItem4, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                    setTemplateData4((ChannelListItem.TextEmojView) channelListItem4);
                }
                int[] iArr4 = this.mBorder1_2;
                if (iArr4 != null) {
                    SubzoneData subzoneData25 = this.mSubzoneData;
                    valueOf = subzoneData25 != null ? Integer.valueOf(subzoneData25.getItemBorderIndex()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    getBinding().template4.setBorder(iArr4[valueOf.intValue()]);
                }
                SubzoneData subzoneData26 = this.mSubzoneData;
                if (subzoneData26 == null || (imageData23 = subzoneData26.getImageData2()) == null) {
                    return;
                }
                getBinding().template4.setImageData(imageData23);
                return;
            }
            return;
        }
        if (i != 5) {
            return;
        }
        getBinding().template5.setVisibility(0);
        getBinding().template5.setOnClickTvClickListener(new OnTvClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$initTemplate$12
            @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
            public void onIvClick2(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }

            @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
            public void onTvClick1(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                EditTemplateActivity.this.mChooseTvIndex = 1;
                EditTemplateActivity.this.toEditText();
            }

            @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
            public void onTvClick2(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                EditTemplateActivity.this.mChooseTvIndex = 2;
                EditTemplateActivity.this.toEditText();
            }

            @Override // com.wifiled.ipixels.ui.subzone.templateview.OnTvClickListener
            public void onIvClick1(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                EditTemplateActivity.this.mChooseIVIndex = 1;
                EditTemplateActivity.this.chooseImage();
            }
        });
        if (this.misEdit) {
            SubzoneData subzoneData27 = this.mSubzoneData;
            List<ChannelListItem> mDataList5 = subzoneData27 != null ? subzoneData27.getMDataList() : null;
            Intrinsics.checkNotNull(mDataList5, "null cannot be cast to non-null type kotlin.collections.MutableList<com.wifiled.ipixels.ui.channel.ChannelListItem>");
            this.mTextData = TypeIntrinsics.asMutableList(mDataList5);
            SubzoneData subzoneData28 = this.mSubzoneData;
            Integer valueOf8 = subzoneData28 != null ? Integer.valueOf(subzoneData28.getImage1Type()) : null;
            Intrinsics.checkNotNull(valueOf8);
            this.mImage1Type = valueOf8.intValue();
            SubzoneData subzoneData29 = this.mSubzoneData;
            Integer valueOf9 = subzoneData29 != null ? Integer.valueOf(subzoneData29.getImage2Type()) : null;
            Intrinsics.checkNotNull(valueOf9);
            this.mImage2Type = valueOf9.intValue();
            SubzoneData subzoneData30 = this.mSubzoneData;
            byte[] imageData14 = subzoneData30 != null ? subzoneData30.getImageData1() : null;
            Intrinsics.checkNotNull(imageData14);
            this.mImageData1 = imageData14;
            SubzoneData subzoneData31 = this.mSubzoneData;
            byte[] imageData28 = subzoneData31 != null ? subzoneData31.getImageData2() : null;
            Intrinsics.checkNotNull(imageData28);
            this.mImageData2 = imageData28;
            for (ChannelListItem channelListItem5 : this.mTextData) {
                Intrinsics.checkNotNull(channelListItem5, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
                setTemplateData5((ChannelListItem.TextEmojView) channelListItem5);
            }
            int[] iArr5 = this.mBorder1_2;
            if (iArr5 != null) {
                SubzoneData subzoneData32 = this.mSubzoneData;
                valueOf = subzoneData32 != null ? Integer.valueOf(subzoneData32.getItemBorderIndex()) : null;
                Intrinsics.checkNotNull(valueOf);
                getBinding().template5.setBorder(iArr5[valueOf.intValue()]);
            }
            SubzoneData subzoneData33 = this.mSubzoneData;
            if (subzoneData33 == null || (imageData24 = subzoneData33.getImageData2()) == null) {
                return;
            }
            getBinding().template5.setImageData(imageData24);
        }
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.edit_template));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        getBinding().ivRight.setBackgroundResource(R.drawable.remote_save);
        EditTemplateActivity editTemplateActivity = this;
        getBinding().ivBack.setOnClickListener(editTemplateActivity);
        getBinding().ivRight.setOnClickListener(editTemplateActivity);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toEditText() {
        Intent intent = new Intent(this, (Class<?>) TextActivity.class);
        intent.putExtra("fromType", PointerIconCompat.TYPE_HAND);
        intent.putExtra("templateType", this.mTemplateType);
        intent.putExtra(PlayerFinal.PLAYER_POSITION, this.mChooseTvIndex);
        this.mTextResult.launch(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Bitmap view2Bitmap;
        String str;
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivRight)) {
            SubzoneData subzoneData = new SubzoneData(this.mTemplateType, this.mTextData, AppConfig.INSTANCE.getLedType(), this.mBorderIndex, this.mBorderEffectData, this.mBorderSpeed, this.mImageData1, this.mImageData2, this.mImage1Type, this.mImage2Type, false);
            ArrayList arrayList = new ArrayList();
            if (!this.misEdit) {
                EditTemplateActivity editTemplateActivity = this;
                if (SPUtils.contains(editTemplateActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType())) {
                    if (SPUtils.get(editTemplateActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                        Object obj = SPUtils.get(editTemplateActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                        arrayList = (List) obj;
                    }
                }
                arrayList.add(subzoneData);
            } else {
                Object obj2 = SPUtils.get(this, "subzone_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                arrayList = (List) obj2;
                arrayList.set(this.mEditPosition, subzoneData);
            }
            EditTemplateActivity editTemplateActivity2 = this;
            SPUtils.put(editTemplateActivity2, "subzone_list_" + AppConfig.INSTANCE.getLedType(), arrayList);
            int i = this.mTemplateType;
            if (i == 1) {
                view2Bitmap = ImageUtils.view2Bitmap(getBinding().template1);
            } else if (i == 2) {
                view2Bitmap = ImageUtils.view2Bitmap(getBinding().template2);
            } else if (i == 3) {
                view2Bitmap = ImageUtils.view2Bitmap(getBinding().template3);
            } else if (i == 4) {
                view2Bitmap = ImageUtils.view2Bitmap(getBinding().template4);
            } else {
                view2Bitmap = i != 5 ? null : ImageUtils.view2Bitmap(getBinding().template5);
            }
            byte[] bitmapToByteStream = BGRUtils.bitmapToByteStream(view2Bitmap);
            ChannelIndex.INSTANCE.inc();
            String valueOf = String.valueOf(ChannelIndex.INSTANCE.index());
            Intrinsics.checkNotNull(bitmapToByteStream);
            ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.SubzoneView(false, valueOf, false, subzoneData, bitmapToByteStream));
            switch (AppConfig.INSTANCE.getLedType()) {
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
                default:
                    str = "channel_data";
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
            SPUtils.put(editTemplateActivity2, str, ChannelIndex.INSTANCE.mapSaveChannel());
            EventBus.getDefault().postSticky(subzoneData);
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().borderCl) || Intrinsics.areEqual(v, getBinding().ivBorder) || Intrinsics.areEqual(v, getBinding().ivNext)) {
            TextBorderDiaglog textBorderDiaglog = new TextBorderDiaglog();
            this.mTextBorderDiaglog = textBorderDiaglog;
            Intrinsics.checkNotNull(textBorderDiaglog);
            textBorderDiaglog.setOnClickListener(new TextBorderDiaglog.TextBorderClickLinstener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$onClick$1
                @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
                public void onCancelClick() {
                }

                @Override // com.wifiled.ipixels.ui.subzone.TextBorderDiaglog.TextBorderClickLinstener
                public void onSubmitClick(int position, int effectPosition, int speed) {
                    int[] iArr;
                    Integer valueOf2;
                    int intValue;
                    int[] iArr2;
                    int[] iArr3;
                    int[] iArr4;
                    int[] iArr5;
                    int[] iArr6;
                    int[] iArr7;
                    int[] iArr8;
                    int[] iArr9;
                    ActivityEditTemplateBinding binding;
                    int i2;
                    int i3;
                    ActivityEditTemplateBinding binding2;
                    ActivityEditTemplateBinding binding3;
                    ActivityEditTemplateBinding binding4;
                    ActivityEditTemplateBinding binding5;
                    ActivityEditTemplateBinding binding6;
                    TextBorderDiaglog textBorderDiaglog2;
                    int i4;
                    ActivityEditTemplateBinding binding7;
                    ActivityEditTemplateBinding binding8;
                    ActivityEditTemplateBinding binding9;
                    ActivityEditTemplateBinding binding10;
                    ActivityEditTemplateBinding binding11;
                    LogUtils.vTag("ruis", "position--" + position);
                    EditTemplateActivity.this.mBorderIndex = position;
                    EditTemplateActivity.this.mBorderEffectData = effectPosition;
                    EditTemplateActivity.this.mBorderSpeed = speed;
                    switch (AppConfig.INSTANCE.getLedType()) {
                        case 9:
                        case 10:
                            iArr = EditTemplateActivity.this.mBorder1_2;
                            valueOf2 = iArr != null ? Integer.valueOf(iArr[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 11:
                            iArr2 = EditTemplateActivity.this.mBorder1_3;
                            valueOf2 = iArr2 != null ? Integer.valueOf(iArr2[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 12:
                            iArr3 = EditTemplateActivity.this.mBorder1_4;
                            valueOf2 = iArr3 != null ? Integer.valueOf(iArr3[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 13:
                        default:
                            intValue = 0;
                            break;
                        case 14:
                            iArr4 = EditTemplateActivity.this.mBorder1_5;
                            valueOf2 = iArr4 != null ? Integer.valueOf(iArr4[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 15:
                            iArr5 = EditTemplateActivity.this.mBorder1_6;
                            valueOf2 = iArr5 != null ? Integer.valueOf(iArr5[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 16:
                            iArr6 = EditTemplateActivity.this.mBorder1_8;
                            valueOf2 = iArr6 != null ? Integer.valueOf(iArr6[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 17:
                            iArr7 = EditTemplateActivity.this.mBorder1_10;
                            valueOf2 = iArr7 != null ? Integer.valueOf(iArr7[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 18:
                            iArr8 = EditTemplateActivity.this.mBorder1_12;
                            valueOf2 = iArr8 != null ? Integer.valueOf(iArr8[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                        case 19:
                            iArr9 = EditTemplateActivity.this.mBorder1_14;
                            valueOf2 = iArr9 != null ? Integer.valueOf(iArr9[position]) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            intValue = valueOf2.intValue();
                            break;
                    }
                    binding = EditTemplateActivity.this.getBinding();
                    binding.ivBorder.setImageResource(intValue);
                    i2 = EditTemplateActivity.this.mBorderIndex;
                    if (i2 != 0) {
                        i4 = EditTemplateActivity.this.mTemplateType;
                        if (i4 == 1) {
                            binding7 = EditTemplateActivity.this.getBinding();
                            binding7.template1.setBorder(intValue);
                        } else if (i4 == 2) {
                            binding8 = EditTemplateActivity.this.getBinding();
                            binding8.template2.setBorder(intValue);
                        } else if (i4 == 3) {
                            binding9 = EditTemplateActivity.this.getBinding();
                            binding9.template3.setBorder(intValue);
                        } else if (i4 == 4) {
                            binding10 = EditTemplateActivity.this.getBinding();
                            binding10.template4.setBorder(intValue);
                        } else if (i4 == 5) {
                            binding11 = EditTemplateActivity.this.getBinding();
                            binding11.template5.setBorder(intValue);
                        }
                    } else {
                        i3 = EditTemplateActivity.this.mTemplateType;
                        if (i3 == 1) {
                            binding2 = EditTemplateActivity.this.getBinding();
                            binding2.template1.setBorder(0);
                        } else if (i3 == 2) {
                            binding3 = EditTemplateActivity.this.getBinding();
                            binding3.template2.setBorder(0);
                        } else if (i3 == 3) {
                            binding4 = EditTemplateActivity.this.getBinding();
                            binding4.template3.setBorder(0);
                        } else if (i3 == 4) {
                            binding5 = EditTemplateActivity.this.getBinding();
                            binding5.template4.setBorder(0);
                        } else if (i3 == 5) {
                            binding6 = EditTemplateActivity.this.getBinding();
                            binding6.template5.setBorder(0);
                        }
                    }
                    textBorderDiaglog2 = EditTemplateActivity.this.mTextBorderDiaglog;
                    if (textBorderDiaglog2 != null) {
                        textBorderDiaglog2.dismiss();
                    }
                }
            });
            TextBorderDiaglog textBorderDiaglog2 = this.mTextBorderDiaglog;
            if (textBorderDiaglog2 != null) {
                textBorderDiaglog2.show(getSupportFragmentManager(), "border");
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivBorderNext)) {
            SubzoneData subzoneData2 = new SubzoneData(this.mTemplateType, this.mTextData, AppConfig.INSTANCE.getLedType(), this.mBorderIndex, this.mBorderEffectData, this.mBorderSpeed, this.mImageData1, this.mImageData2, this.mImage1Type, this.mImage2Type, false);
            SendDataCallback sendDataCallback = this.mSendDataCallback;
            if (sendDataCallback != null) {
                SendCore.sendTemplateData$default(false, subzoneData2, sendDataCallback, (byte) 0, 8, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void chooseImage() {
        Dialog dialog = this.dialog;
        if (dialog == null) {
            this.dialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        } else if (dialog != null) {
            dialog.show();
        }
        Dialog dialog2 = this.dialog;
        this.actionRecyclerView = dialog2 != null ? (RecyclerView) dialog2.findViewById(R.id.rl_actions) : null;
        EditTemplateActivity editTemplateActivity = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(editTemplateActivity, 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(editTemplateActivity, 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter = this.mAdapter;
            if (iosDialogStyleAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                iosDialogStyleAdapter = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter);
        }
        RecyclerView recyclerView4 = this.actionRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    EditTemplateActivity.chooseImage$lambda$10(EditTemplateActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mAdapter;
        if (iosDialogStyleAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter2 = null;
        }
        iosDialogStyleAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda6
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                EditTemplateActivity.chooseImage$lambda$11(EditTemplateActivity.this, viewGroup, view, obj, i);
            }
        });
        Dialog dialog3 = this.dialog;
        TextView textView = dialog3 != null ? (TextView) dialog3.findViewById(R.id.tv_cancel) : null;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.EditTemplateActivity$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    EditTemplateActivity.chooseImage$lambda$12(EditTemplateActivity.this, view);
                }
            });
        }
        if (textView != null) {
            CustomImageView.INSTANCE.attachViewOnTouchListener(textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$10(EditTemplateActivity editTemplateActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = editTemplateActivity.mAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = editTemplateActivity.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = editTemplateActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$11(EditTemplateActivity editTemplateActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        Dialog dialog = editTemplateActivity.dialog;
        if (dialog != null) {
            dialog.cancel();
        }
        if (i == 0) {
            Intent intent = new Intent(editTemplateActivity, (Class<?>) GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("select_mode_by_selector", true);
            bundle.putBoolean("select_mode_by_image_text", true);
            bundle.putBoolean("from_it", true);
            bundle.putInt("template_size", AppConfig.INSTANCE.getLedType());
            intent.putExtras(bundle);
            editTemplateActivity.galleryMedia.launch(intent);
            return;
        }
        if (i == 1) {
            Intent intent2 = new Intent(editTemplateActivity, (Class<?>) DiyImageActivity.class);
            intent2.putExtra("type", 1);
            editTemplateActivity.diyImageMedia.launch(intent2);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            editTemplateActivity.pickMedia.launch(PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE));
        } else {
            Intent intent3 = new Intent(editTemplateActivity, (Class<?>) TemplateDiyAnimActivity.class);
            intent3.putExtra("type", 1);
            editTemplateActivity.diyVideoMedia.launch(intent3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$12(EditTemplateActivity editTemplateActivity, View view) {
        Dialog dialog = editTemplateActivity.dialog;
        if (dialog != null) {
            dialog.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void galleryMedia$lambda$22(EditTemplateActivity editTemplateActivity, ActivityResult activityResult) {
        Intent data = activityResult.getData();
        byte[] byteArrayExtra = data != null ? data.getByteArrayExtra("sendData") : null;
        Intent data2 = activityResult.getData();
        byte[] byteArrayExtra2 = data2 != null ? data2.getByteArrayExtra("fileData") : null;
        Intent data3 = activityResult.getData();
        Integer valueOf = data3 != null ? Integer.valueOf(data3.getIntExtra("sendType", 0)) : null;
        int i = editTemplateActivity.mTemplateType;
        if (i == 2) {
            if (valueOf != null) {
                editTemplateActivity.mImage1Type = valueOf.intValue();
                if (valueOf.intValue() == 1) {
                    if (byteArrayExtra != null) {
                        editTemplateActivity.getBinding().template2.setData2(byteArrayExtra);
                        editTemplateActivity.mImageData1 = byteArrayExtra;
                        editTemplateActivity.mImageData2 = byteArrayExtra;
                        return;
                    }
                    return;
                }
                if (valueOf.intValue() != 2 || byteArrayExtra2 == null) {
                    return;
                }
                byte[] bitmap2BGR = BGRUtils.bitmap2BGR(BitmapFactory.decodeByteArray(byteArrayExtra2, 0, byteArrayExtra2.length));
                editTemplateActivity.getBinding().template2.setData2(byteArrayExtra2);
                Intrinsics.checkNotNull(bitmap2BGR);
                editTemplateActivity.mImageData1 = bitmap2BGR;
                editTemplateActivity.mImageData2 = byteArrayExtra2;
                return;
            }
            return;
        }
        if (i == 3) {
            if (valueOf != null) {
                editTemplateActivity.mImage1Type = valueOf.intValue();
                if (valueOf.intValue() == 1) {
                    if (byteArrayExtra != null) {
                        editTemplateActivity.getBinding().template3.setData2(byteArrayExtra);
                        editTemplateActivity.mImageData1 = byteArrayExtra;
                        editTemplateActivity.mImageData2 = byteArrayExtra;
                        return;
                    }
                    return;
                }
                if (valueOf.intValue() != 2 || byteArrayExtra2 == null) {
                    return;
                }
                byte[] bitmap2BGR2 = BGRUtils.bitmap2BGR(BitmapFactory.decodeByteArray(byteArrayExtra2, 0, byteArrayExtra2.length));
                editTemplateActivity.getBinding().template3.setData2(byteArrayExtra2);
                Intrinsics.checkNotNull(bitmap2BGR2);
                editTemplateActivity.mImageData1 = bitmap2BGR2;
                editTemplateActivity.mImageData2 = byteArrayExtra2;
                return;
            }
            return;
        }
        if (i == 4) {
            if (valueOf != null) {
                editTemplateActivity.mImage1Type = valueOf.intValue();
                if (valueOf.intValue() == 1) {
                    if (byteArrayExtra != null) {
                        editTemplateActivity.getBinding().template4.setImageData(byteArrayExtra);
                        editTemplateActivity.mImageData1 = byteArrayExtra;
                        editTemplateActivity.mImageData2 = byteArrayExtra;
                        return;
                    }
                    return;
                }
                if (valueOf.intValue() != 2 || byteArrayExtra2 == null) {
                    return;
                }
                byte[] bitmap2BGR3 = BGRUtils.bitmap2BGR(BitmapFactory.decodeByteArray(byteArrayExtra2, 0, byteArrayExtra2.length));
                editTemplateActivity.getBinding().template4.setImageData(byteArrayExtra2);
                Intrinsics.checkNotNull(bitmap2BGR3);
                editTemplateActivity.mImageData1 = bitmap2BGR3;
                editTemplateActivity.mImageData2 = byteArrayExtra2;
                return;
            }
            return;
        }
        if (i == 5 && valueOf != null) {
            editTemplateActivity.mImage1Type = valueOf.intValue();
            if (valueOf.intValue() == 1) {
                if (byteArrayExtra != null) {
                    editTemplateActivity.getBinding().template5.setImageData(byteArrayExtra);
                    editTemplateActivity.mImageData1 = byteArrayExtra;
                    editTemplateActivity.mImageData2 = byteArrayExtra;
                    return;
                }
                return;
            }
            if (valueOf.intValue() != 2 || byteArrayExtra2 == null) {
                return;
            }
            byte[] bitmap2BGR4 = BGRUtils.bitmap2BGR(BitmapFactory.decodeByteArray(byteArrayExtra2, 0, byteArrayExtra2.length));
            editTemplateActivity.getBinding().template5.setImageData(byteArrayExtra2);
            Intrinsics.checkNotNull(bitmap2BGR4);
            editTemplateActivity.mImageData1 = bitmap2BGR4;
            editTemplateActivity.mImageData2 = byteArrayExtra2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void diyImageMedia$lambda$28(EditTemplateActivity editTemplateActivity, ActivityResult activityResult) {
        Intent data = activityResult.getData();
        byte[] byteArrayExtra = data != null ? data.getByteArrayExtra("sendData") : null;
        byte[] bitmap2BGR = BGRUtils.bitmap2BGR(byteArrayExtra != null ? BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length) : null);
        editTemplateActivity.mImage1Type = 2;
        int i = editTemplateActivity.mTemplateType;
        if (i == 2) {
            if (byteArrayExtra != null) {
                editTemplateActivity.getBinding().template2.setData2(byteArrayExtra);
                Intrinsics.checkNotNull(bitmap2BGR);
                editTemplateActivity.mImageData1 = bitmap2BGR;
                editTemplateActivity.mImageData2 = byteArrayExtra;
                return;
            }
            return;
        }
        if (i == 3) {
            if (byteArrayExtra != null) {
                editTemplateActivity.getBinding().template3.setData2(byteArrayExtra);
                Intrinsics.checkNotNull(bitmap2BGR);
                editTemplateActivity.mImageData1 = bitmap2BGR;
                editTemplateActivity.mImageData2 = byteArrayExtra;
                return;
            }
            return;
        }
        if (i == 4) {
            if (byteArrayExtra != null) {
                editTemplateActivity.getBinding().template4.setImageData(byteArrayExtra);
                Intrinsics.checkNotNull(bitmap2BGR);
                editTemplateActivity.mImageData1 = bitmap2BGR;
                editTemplateActivity.mImageData2 = byteArrayExtra;
                return;
            }
            return;
        }
        if (i == 5 && byteArrayExtra != null) {
            editTemplateActivity.getBinding().template5.setImageData(byteArrayExtra);
            Intrinsics.checkNotNull(bitmap2BGR);
            editTemplateActivity.mImageData1 = bitmap2BGR;
            editTemplateActivity.mImageData2 = byteArrayExtra;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void diyVideoMedia$lambda$33(EditTemplateActivity editTemplateActivity, ActivityResult activityResult) {
        Intent data = activityResult.getData();
        String stringExtra = data != null ? data.getStringExtra("sendData") : null;
        Log.v("ruis", "data --- " + stringExtra);
        editTemplateActivity.mImage1Type = 1;
        int i = editTemplateActivity.mTemplateType;
        if (i == 2) {
            if (stringExtra != null) {
                File fileByPath = FileUtils.getFileByPath(stringExtra);
                TemplateViewTwo12 templateViewTwo12 = editTemplateActivity.getBinding().template2;
                Intrinsics.checkNotNull(fileByPath);
                templateViewTwo12.setData2(fileByPath);
                byte[] readFile2BytesByChannel = FileIOUtils.readFile2BytesByChannel(fileByPath);
                Intrinsics.checkNotNullExpressionValue(readFile2BytesByChannel, "readFile2BytesByChannel(...)");
                editTemplateActivity.mImageData1 = readFile2BytesByChannel;
                return;
            }
            return;
        }
        if (i == 3) {
            if (stringExtra != null) {
                File fileByPath2 = FileUtils.getFileByPath(stringExtra);
                TemplateViewThree12 templateViewThree12 = editTemplateActivity.getBinding().template3;
                Intrinsics.checkNotNull(fileByPath2);
                templateViewThree12.setData2(fileByPath2);
                byte[] readFile2BytesByChannel2 = FileIOUtils.readFile2BytesByChannel(fileByPath2);
                Intrinsics.checkNotNullExpressionValue(readFile2BytesByChannel2, "readFile2BytesByChannel(...)");
                editTemplateActivity.mImageData1 = readFile2BytesByChannel2;
                return;
            }
            return;
        }
        if (i == 4) {
            if (stringExtra != null) {
                File fileByPath3 = FileUtils.getFileByPath(stringExtra);
                TemplateViewFour12 templateViewFour12 = editTemplateActivity.getBinding().template4;
                Intrinsics.checkNotNull(fileByPath3);
                templateViewFour12.setImageData(fileByPath3);
                byte[] readFile2BytesByChannel3 = FileIOUtils.readFile2BytesByChannel(fileByPath3);
                Intrinsics.checkNotNullExpressionValue(readFile2BytesByChannel3, "readFile2BytesByChannel(...)");
                editTemplateActivity.mImageData1 = readFile2BytesByChannel3;
                return;
            }
            return;
        }
        if (i == 5 && stringExtra != null) {
            File fileByPath4 = FileUtils.getFileByPath(stringExtra);
            TemplateViewFive12 templateViewFive12 = editTemplateActivity.getBinding().template5;
            Intrinsics.checkNotNull(fileByPath4);
            templateViewFive12.setImageData(fileByPath4);
            byte[] readFile2BytesByChannel4 = FileIOUtils.readFile2BytesByChannel(fileByPath4);
            Intrinsics.checkNotNullExpressionValue(readFile2BytesByChannel4, "readFile2BytesByChannel(...)");
            editTemplateActivity.mImageData1 = readFile2BytesByChannel4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickMedia$lambda$34(EditTemplateActivity editTemplateActivity, Uri uri) {
        if (uri != null) {
            Uri fromFile = Uri.fromFile(new File(editTemplateActivity.getCacheDir(), "cropped_image.jpg"));
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.setAllowedGestures(1, 0, 1);
            UCrop.of(uri, fromFile).withAspectRatio(1.0f, 1.0f).withMaxResultSize(AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()).withOptions(options).start(editTemplateActivity);
            return;
        }
        Log.d("PhotoPicker", "No media selected");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || requestCode != 69) {
            if (resultCode == 96) {
                LogUtils.vTag("ruis", "cropError-----" + (data != null ? UCrop.getError(data) : null));
                return;
            }
            return;
        }
        Bitmap rotateBitmap = BitmapUtils.INSTANCE.getRotateBitmap(UriUtils.uri2File(data != null ? UCrop.getOutput(data) : null).getPath(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
        String path = FileUtil.getPath(1, ".jpg");
        FileUtil.bitmap2File(new File(path), rotateBitmap);
        byte[] readFileBytes = FileUtil.readFileBytes(path);
        byte[] bitmap2BGR = BGRUtils.bitmap2BGR(BitmapFactory.decodeByteArray(readFileBytes, 0, readFileBytes.length));
        this.mImage1Type = 2;
        int i = this.mTemplateType;
        if (i == 2) {
            if (readFileBytes != null) {
                getBinding().template2.setData2(readFileBytes);
                Intrinsics.checkNotNull(bitmap2BGR);
                this.mImageData1 = bitmap2BGR;
                this.mImageData2 = readFileBytes;
                return;
            }
            return;
        }
        if (i == 3) {
            if (readFileBytes != null) {
                getBinding().template3.setData2(readFileBytes);
                Intrinsics.checkNotNull(bitmap2BGR);
                this.mImageData1 = bitmap2BGR;
                this.mImageData2 = readFileBytes;
                return;
            }
            return;
        }
        if (i == 4) {
            if (readFileBytes != null) {
                getBinding().template4.setImageData(readFileBytes);
                Intrinsics.checkNotNull(bitmap2BGR);
                this.mImageData1 = bitmap2BGR;
                this.mImageData2 = readFileBytes;
                return;
            }
            return;
        }
        if (i == 5 && readFileBytes != null) {
            getBinding().template5.setImageData(readFileBytes);
            Intrinsics.checkNotNull(bitmap2BGR);
            this.mImageData1 = bitmap2BGR;
            this.mImageData2 = readFileBytes;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mTextResult$lambda$41(EditTemplateActivity editTemplateActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            editTemplateActivity.mIsDataChange = true;
            Intent data = activityResult.getData();
            Serializable serializableExtra = data != null ? data.getSerializableExtra("textData") : null;
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
            ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) serializableExtra;
            int i = editTemplateActivity.mTemplateType;
            if (i == 1) {
                if (editTemplateActivity.mChooseTvIndex == 1) {
                    textEmojView.setPosition(1);
                    editTemplateActivity.mTextData.set(0, textEmojView);
                } else {
                    textEmojView.setPosition(2);
                    editTemplateActivity.mTextData.set(1, textEmojView);
                }
                editTemplateActivity.setTemplateData1(textEmojView);
                return;
            }
            if (i == 2) {
                editTemplateActivity.setTemplateData2(textEmojView);
                editTemplateActivity.mTextData.set(0, textEmojView);
                return;
            }
            if (i == 3) {
                editTemplateActivity.setTemplateData3(textEmojView);
                editTemplateActivity.mTextData.set(0, textEmojView);
                return;
            }
            if (i == 4) {
                if (editTemplateActivity.mChooseTvIndex == 1) {
                    textEmojView.setPosition(1);
                    editTemplateActivity.mTextData.set(0, textEmojView);
                } else {
                    textEmojView.setPosition(2);
                    editTemplateActivity.mTextData.set(1, textEmojView);
                }
                editTemplateActivity.setTemplateData4(textEmojView);
                return;
            }
            if (i != 5) {
                return;
            }
            if (editTemplateActivity.mChooseTvIndex == 1) {
                textEmojView.setPosition(1);
                editTemplateActivity.mTextData.set(0, textEmojView);
            } else {
                textEmojView.setPosition(2);
                editTemplateActivity.mTextData.set(1, textEmojView);
            }
            editTemplateActivity.setTemplateData5(textEmojView);
        }
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mSendDataCallback = null;
        int i = this.mTemplateType;
        if (i == 1) {
            getBinding().template1.clean();
        } else if (i == 2) {
            getBinding().template2.clean();
        } else if (i == 3) {
            getBinding().template3.clean();
        } else if (i == 4) {
            getBinding().template4.clean();
        } else if (i == 5) {
            getBinding().template5.clean();
        }
        super.onDestroy();
    }

    private final void setTemplateData1(ChannelListItem.TextEmojView data) {
        Integer valueOf;
        if (data.getPosition() == 1) {
            if (data.getBorderIndex() != 0) {
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 9:
                    case 10:
                        TemplateViewOne12 templateViewOne12 = getBinding().template1;
                        int[] iArr = this.mBorder1_4;
                        valueOf = iArr != null ? Integer.valueOf(iArr[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne12.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 11:
                        TemplateViewOne12 templateViewOne122 = getBinding().template1;
                        int[] iArr2 = this.mBorder1_6;
                        valueOf = iArr2 != null ? Integer.valueOf(iArr2[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne122.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 12:
                        TemplateViewOne12 templateViewOne123 = getBinding().template1;
                        int[] iArr3 = this.mBorder1_8;
                        valueOf = iArr3 != null ? Integer.valueOf(iArr3[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne123.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 14:
                        TemplateViewOne12 templateViewOne124 = getBinding().template1;
                        int[] iArr4 = this.mBorder1_10;
                        valueOf = iArr4 != null ? Integer.valueOf(iArr4[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne124.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 15:
                        TemplateViewOne12 templateViewOne125 = getBinding().template1;
                        int[] iArr5 = this.mBorder1_12;
                        valueOf = iArr5 != null ? Integer.valueOf(iArr5[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne125.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 16:
                        TemplateViewOne12 templateViewOne126 = getBinding().template1;
                        int[] iArr6 = this.mBorder1_16;
                        valueOf = iArr6 != null ? Integer.valueOf(iArr6[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne126.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 17:
                        TemplateViewOne12 templateViewOne127 = getBinding().template1;
                        int[] iArr7 = this.mBorder1_20;
                        valueOf = iArr7 != null ? Integer.valueOf(iArr7[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne127.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 18:
                        TemplateViewOne12 templateViewOne128 = getBinding().template1;
                        int[] iArr8 = this.mBorder1_24;
                        valueOf = iArr8 != null ? Integer.valueOf(iArr8[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne128.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 19:
                        TemplateViewOne12 templateViewOne129 = getBinding().template1;
                        int[] iArr9 = this.mBorder1_28;
                        valueOf = iArr9 != null ? Integer.valueOf(iArr9[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewOne129.setData1(data, valueOf.intValue(), 2);
                        break;
                }
                return;
            }
            getBinding().template1.setData1(data, 0, 2);
            return;
        }
        if (data.getBorderIndex() != 0) {
            switch (AppConfig.INSTANCE.getLedType()) {
                case 9:
                case 10:
                    TemplateViewOne12 templateViewOne1210 = getBinding().template1;
                    int[] iArr10 = this.mBorder1_4;
                    valueOf = iArr10 != null ? Integer.valueOf(iArr10[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1210.setData2(data, valueOf.intValue(), 2);
                    break;
                case 11:
                    TemplateViewOne12 templateViewOne1211 = getBinding().template1;
                    int[] iArr11 = this.mBorder1_6;
                    valueOf = iArr11 != null ? Integer.valueOf(iArr11[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1211.setData2(data, valueOf.intValue(), 2);
                    break;
                case 12:
                    TemplateViewOne12 templateViewOne1212 = getBinding().template1;
                    int[] iArr12 = this.mBorder1_8;
                    valueOf = iArr12 != null ? Integer.valueOf(iArr12[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1212.setData2(data, valueOf.intValue(), 2);
                    break;
                case 14:
                    TemplateViewOne12 templateViewOne1213 = getBinding().template1;
                    int[] iArr13 = this.mBorder1_10;
                    valueOf = iArr13 != null ? Integer.valueOf(iArr13[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1213.setData2(data, valueOf.intValue(), 2);
                    break;
                case 15:
                    TemplateViewOne12 templateViewOne1214 = getBinding().template1;
                    int[] iArr14 = this.mBorder1_12;
                    valueOf = iArr14 != null ? Integer.valueOf(iArr14[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1214.setData2(data, valueOf.intValue(), 2);
                    break;
                case 16:
                    TemplateViewOne12 templateViewOne1215 = getBinding().template1;
                    int[] iArr15 = this.mBorder1_16;
                    valueOf = iArr15 != null ? Integer.valueOf(iArr15[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1215.setData2(data, valueOf.intValue(), 2);
                    break;
                case 17:
                    TemplateViewOne12 templateViewOne1216 = getBinding().template1;
                    int[] iArr16 = this.mBorder1_20;
                    valueOf = iArr16 != null ? Integer.valueOf(iArr16[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1216.setData2(data, valueOf.intValue(), 2);
                    break;
                case 18:
                    TemplateViewOne12 templateViewOne1217 = getBinding().template1;
                    int[] iArr17 = this.mBorder1_24;
                    valueOf = iArr17 != null ? Integer.valueOf(iArr17[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1217.setData2(data, valueOf.intValue(), 2);
                    break;
                case 19:
                    TemplateViewOne12 templateViewOne1218 = getBinding().template1;
                    int[] iArr18 = this.mBorder1_28;
                    valueOf = iArr18 != null ? Integer.valueOf(iArr18[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewOne1218.setData2(data, valueOf.intValue(), 2);
                    break;
            }
            return;
        }
        getBinding().template1.setData2(data, 0, 2);
    }

    private final void setTemplateData2(ChannelListItem.TextEmojView data) {
        Integer valueOf;
        if (data.getBorderIndex() != 0) {
            switch (AppConfig.INSTANCE.getLedType()) {
                case 9:
                case 10:
                    TemplateViewTwo12 templateViewTwo12 = getBinding().template2;
                    int[] iArr = this.mBorder1_1;
                    valueOf = iArr != null ? Integer.valueOf(iArr[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo12.setData1(data, valueOf.intValue(), 2);
                    break;
                case 11:
                    TemplateViewTwo12 templateViewTwo122 = getBinding().template2;
                    int[] iArr2 = this.mBorder1_2;
                    valueOf = iArr2 != null ? Integer.valueOf(iArr2[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo122.setData1(data, valueOf.intValue(), 2);
                    break;
                case 12:
                    TemplateViewTwo12 templateViewTwo123 = getBinding().template2;
                    int[] iArr3 = this.mBorder1_3;
                    valueOf = iArr3 != null ? Integer.valueOf(iArr3[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo123.setData1(data, valueOf.intValue(), 2);
                    break;
                case 14:
                    TemplateViewTwo12 templateViewTwo124 = getBinding().template2;
                    int[] iArr4 = this.mBorder1_4;
                    valueOf = iArr4 != null ? Integer.valueOf(iArr4[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo124.setData1(data, valueOf.intValue(), 2);
                    break;
                case 15:
                    TemplateViewTwo12 templateViewTwo125 = getBinding().template2;
                    int[] iArr5 = this.mBorder1_5;
                    valueOf = iArr5 != null ? Integer.valueOf(iArr5[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo125.setData1(data, valueOf.intValue(), 2);
                    break;
                case 16:
                    TemplateViewTwo12 templateViewTwo126 = getBinding().template2;
                    int[] iArr6 = this.mBorder1_7;
                    valueOf = iArr6 != null ? Integer.valueOf(iArr6[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo126.setData1(data, valueOf.intValue(), 2);
                    break;
                case 17:
                    TemplateViewTwo12 templateViewTwo127 = getBinding().template2;
                    int[] iArr7 = this.mBorder1_9;
                    valueOf = iArr7 != null ? Integer.valueOf(iArr7[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo127.setData1(data, valueOf.intValue(), 2);
                    break;
                case 18:
                    TemplateViewTwo12 templateViewTwo128 = getBinding().template2;
                    int[] iArr8 = this.mBorder1_11;
                    valueOf = iArr8 != null ? Integer.valueOf(iArr8[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo128.setData1(data, valueOf.intValue(), 2);
                    break;
                case 19:
                    TemplateViewTwo12 templateViewTwo129 = getBinding().template2;
                    int[] iArr9 = this.mBorder1_13;
                    valueOf = iArr9 != null ? Integer.valueOf(iArr9[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewTwo129.setData1(data, valueOf.intValue(), 2);
                    break;
            }
            return;
        }
        getBinding().template2.setData1(data, 0, 2);
    }

    private final void setTemplateData3(ChannelListItem.TextEmojView data) {
        Integer valueOf;
        if (data.getBorderIndex() != 0) {
            switch (AppConfig.INSTANCE.getLedType()) {
                case 9:
                case 10:
                    TemplateViewThree12 templateViewThree12 = getBinding().template3;
                    int[] iArr = this.mBorder1_1;
                    valueOf = iArr != null ? Integer.valueOf(iArr[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree12.setData1(data, valueOf.intValue(), 2);
                    break;
                case 11:
                    TemplateViewThree12 templateViewThree122 = getBinding().template3;
                    int[] iArr2 = this.mBorder1_2;
                    valueOf = iArr2 != null ? Integer.valueOf(iArr2[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree122.setData1(data, valueOf.intValue(), 2);
                    break;
                case 12:
                    TemplateViewThree12 templateViewThree123 = getBinding().template3;
                    int[] iArr3 = this.mBorder1_3;
                    valueOf = iArr3 != null ? Integer.valueOf(iArr3[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree123.setData1(data, valueOf.intValue(), 2);
                    break;
                case 14:
                    TemplateViewThree12 templateViewThree124 = getBinding().template3;
                    int[] iArr4 = this.mBorder1_4;
                    valueOf = iArr4 != null ? Integer.valueOf(iArr4[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree124.setData1(data, valueOf.intValue(), 2);
                    break;
                case 15:
                    TemplateViewThree12 templateViewThree125 = getBinding().template3;
                    int[] iArr5 = this.mBorder1_5;
                    valueOf = iArr5 != null ? Integer.valueOf(iArr5[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree125.setData1(data, valueOf.intValue(), 2);
                    break;
                case 16:
                    TemplateViewThree12 templateViewThree126 = getBinding().template3;
                    int[] iArr6 = this.mBorder1_7;
                    valueOf = iArr6 != null ? Integer.valueOf(iArr6[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree126.setData1(data, valueOf.intValue(), 2);
                    break;
                case 17:
                    TemplateViewThree12 templateViewThree127 = getBinding().template3;
                    int[] iArr7 = this.mBorder1_9;
                    valueOf = iArr7 != null ? Integer.valueOf(iArr7[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree127.setData1(data, valueOf.intValue(), 2);
                    break;
                case 18:
                    TemplateViewThree12 templateViewThree128 = getBinding().template3;
                    int[] iArr8 = this.mBorder1_11;
                    valueOf = iArr8 != null ? Integer.valueOf(iArr8[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree128.setData1(data, valueOf.intValue(), 2);
                    break;
                case 19:
                    TemplateViewThree12 templateViewThree129 = getBinding().template3;
                    int[] iArr9 = this.mBorder1_13;
                    valueOf = iArr9 != null ? Integer.valueOf(iArr9[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewThree129.setData1(data, valueOf.intValue(), 2);
                    break;
            }
            return;
        }
        getBinding().template3.setData1(data, 0, 2);
    }

    private final void setTemplateData4(ChannelListItem.TextEmojView data) {
        Integer valueOf;
        if (data.getPosition() == 1) {
            if (data.getBorderIndex() != 0) {
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 9:
                    case 10:
                        TemplateViewFour12 templateViewFour12 = getBinding().template4;
                        int[] iArr = this.mBorder1_2;
                        valueOf = iArr != null ? Integer.valueOf(iArr[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour12.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 11:
                        TemplateViewFour12 templateViewFour122 = getBinding().template4;
                        int[] iArr2 = this.mBorder1_4;
                        valueOf = iArr2 != null ? Integer.valueOf(iArr2[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour122.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 12:
                        TemplateViewFour12 templateViewFour123 = getBinding().template4;
                        int[] iArr3 = this.mBorder1_6;
                        valueOf = iArr3 != null ? Integer.valueOf(iArr3[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour123.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 14:
                        TemplateViewFour12 templateViewFour124 = getBinding().template4;
                        int[] iArr4 = this.mBorder1_8;
                        valueOf = iArr4 != null ? Integer.valueOf(iArr4[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour124.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 15:
                        TemplateViewFour12 templateViewFour125 = getBinding().template4;
                        int[] iArr5 = this.mBorder1_10;
                        valueOf = iArr5 != null ? Integer.valueOf(iArr5[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour125.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 16:
                        TemplateViewFour12 templateViewFour126 = getBinding().template4;
                        int[] iArr6 = this.mBorder1_14;
                        valueOf = iArr6 != null ? Integer.valueOf(iArr6[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour126.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 17:
                        TemplateViewFour12 templateViewFour127 = getBinding().template4;
                        int[] iArr7 = this.mBorder1_18;
                        valueOf = iArr7 != null ? Integer.valueOf(iArr7[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour127.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 18:
                        TemplateViewFour12 templateViewFour128 = getBinding().template4;
                        int[] iArr8 = this.mBorder1_22;
                        valueOf = iArr8 != null ? Integer.valueOf(iArr8[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour128.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 19:
                        TemplateViewFour12 templateViewFour129 = getBinding().template4;
                        int[] iArr9 = this.mBorder1_26;
                        valueOf = iArr9 != null ? Integer.valueOf(iArr9[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFour129.setData1(data, valueOf.intValue(), 2);
                        break;
                }
                return;
            }
            getBinding().template4.setData1(data, 0, 2);
            return;
        }
        if (data.getBorderIndex() != 0) {
            switch (AppConfig.INSTANCE.getLedType()) {
                case 9:
                case 10:
                    TemplateViewFour12 templateViewFour1210 = getBinding().template4;
                    int[] iArr10 = this.mBorder1_2;
                    valueOf = iArr10 != null ? Integer.valueOf(iArr10[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1210.setData2(data, valueOf.intValue(), 2);
                    break;
                case 11:
                    TemplateViewFour12 templateViewFour1211 = getBinding().template4;
                    int[] iArr11 = this.mBorder1_4;
                    valueOf = iArr11 != null ? Integer.valueOf(iArr11[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1211.setData2(data, valueOf.intValue(), 2);
                    break;
                case 12:
                    TemplateViewFour12 templateViewFour1212 = getBinding().template4;
                    int[] iArr12 = this.mBorder1_6;
                    valueOf = iArr12 != null ? Integer.valueOf(iArr12[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1212.setData2(data, valueOf.intValue(), 2);
                    break;
                case 14:
                    TemplateViewFour12 templateViewFour1213 = getBinding().template4;
                    int[] iArr13 = this.mBorder1_8;
                    valueOf = iArr13 != null ? Integer.valueOf(iArr13[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1213.setData2(data, valueOf.intValue(), 2);
                    break;
                case 15:
                    TemplateViewFour12 templateViewFour1214 = getBinding().template4;
                    int[] iArr14 = this.mBorder1_10;
                    valueOf = iArr14 != null ? Integer.valueOf(iArr14[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1214.setData2(data, valueOf.intValue(), 2);
                    break;
                case 16:
                    TemplateViewFour12 templateViewFour1215 = getBinding().template4;
                    int[] iArr15 = this.mBorder1_14;
                    valueOf = iArr15 != null ? Integer.valueOf(iArr15[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1215.setData2(data, valueOf.intValue(), 2);
                    break;
                case 17:
                    TemplateViewFour12 templateViewFour1216 = getBinding().template4;
                    int[] iArr16 = this.mBorder1_18;
                    valueOf = iArr16 != null ? Integer.valueOf(iArr16[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1216.setData2(data, valueOf.intValue(), 2);
                    break;
                case 18:
                    TemplateViewFour12 templateViewFour1217 = getBinding().template4;
                    int[] iArr17 = this.mBorder1_22;
                    valueOf = iArr17 != null ? Integer.valueOf(iArr17[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1217.setData2(data, valueOf.intValue(), 2);
                    break;
                case 19:
                    TemplateViewFour12 templateViewFour1218 = getBinding().template4;
                    int[] iArr18 = this.mBorder1_26;
                    valueOf = iArr18 != null ? Integer.valueOf(iArr18[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFour1218.setData2(data, valueOf.intValue(), 2);
                    break;
            }
            return;
        }
        getBinding().template4.setData2(data, 0, 2);
    }

    private final void setTemplateData5(ChannelListItem.TextEmojView data) {
        Integer valueOf;
        if (data.getPosition() == 1) {
            if (data.getBorderIndex() != 0) {
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 9:
                    case 10:
                        TemplateViewFive12 templateViewFive12 = getBinding().template5;
                        int[] iArr = this.mBorder1_2;
                        valueOf = iArr != null ? Integer.valueOf(iArr[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive12.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 11:
                        TemplateViewFive12 templateViewFive122 = getBinding().template5;
                        int[] iArr2 = this.mBorder1_4;
                        valueOf = iArr2 != null ? Integer.valueOf(iArr2[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive122.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 12:
                        TemplateViewFive12 templateViewFive123 = getBinding().template5;
                        int[] iArr3 = this.mBorder1_6;
                        valueOf = iArr3 != null ? Integer.valueOf(iArr3[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive123.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 14:
                        TemplateViewFive12 templateViewFive124 = getBinding().template5;
                        int[] iArr4 = this.mBorder1_8;
                        valueOf = iArr4 != null ? Integer.valueOf(iArr4[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive124.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 15:
                        TemplateViewFive12 templateViewFive125 = getBinding().template5;
                        int[] iArr5 = this.mBorder1_10;
                        valueOf = iArr5 != null ? Integer.valueOf(iArr5[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive125.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 16:
                        TemplateViewFive12 templateViewFive126 = getBinding().template5;
                        int[] iArr6 = this.mBorder1_14;
                        valueOf = iArr6 != null ? Integer.valueOf(iArr6[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive126.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 17:
                        TemplateViewFive12 templateViewFive127 = getBinding().template5;
                        int[] iArr7 = this.mBorder1_18;
                        valueOf = iArr7 != null ? Integer.valueOf(iArr7[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive127.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 18:
                        TemplateViewFive12 templateViewFive128 = getBinding().template5;
                        int[] iArr8 = this.mBorder1_22;
                        valueOf = iArr8 != null ? Integer.valueOf(iArr8[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive128.setData1(data, valueOf.intValue(), 2);
                        break;
                    case 19:
                        TemplateViewFive12 templateViewFive129 = getBinding().template5;
                        int[] iArr9 = this.mBorder1_26;
                        valueOf = iArr9 != null ? Integer.valueOf(iArr9[data.getBorderIndex()]) : null;
                        Intrinsics.checkNotNull(valueOf);
                        templateViewFive129.setData1(data, valueOf.intValue(), 2);
                        break;
                }
                return;
            }
            getBinding().template5.setData1(data, 0, 2);
            return;
        }
        if (data.getBorderIndex() != 0) {
            switch (AppConfig.INSTANCE.getLedType()) {
                case 9:
                case 10:
                    TemplateViewFive12 templateViewFive1210 = getBinding().template5;
                    int[] iArr10 = this.mBorder1_2;
                    valueOf = iArr10 != null ? Integer.valueOf(iArr10[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1210.setData2(data, valueOf.intValue(), 2);
                    break;
                case 11:
                    TemplateViewFive12 templateViewFive1211 = getBinding().template5;
                    int[] iArr11 = this.mBorder1_4;
                    valueOf = iArr11 != null ? Integer.valueOf(iArr11[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1211.setData2(data, valueOf.intValue(), 2);
                    break;
                case 12:
                    TemplateViewFive12 templateViewFive1212 = getBinding().template5;
                    int[] iArr12 = this.mBorder1_6;
                    valueOf = iArr12 != null ? Integer.valueOf(iArr12[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1212.setData2(data, valueOf.intValue(), 2);
                    break;
                case 14:
                    TemplateViewFive12 templateViewFive1213 = getBinding().template5;
                    int[] iArr13 = this.mBorder1_8;
                    valueOf = iArr13 != null ? Integer.valueOf(iArr13[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1213.setData2(data, valueOf.intValue(), 2);
                    break;
                case 15:
                    TemplateViewFive12 templateViewFive1214 = getBinding().template5;
                    int[] iArr14 = this.mBorder1_10;
                    valueOf = iArr14 != null ? Integer.valueOf(iArr14[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1214.setData2(data, valueOf.intValue(), 2);
                    break;
                case 16:
                    TemplateViewFive12 templateViewFive1215 = getBinding().template5;
                    int[] iArr15 = this.mBorder1_14;
                    valueOf = iArr15 != null ? Integer.valueOf(iArr15[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1215.setData2(data, valueOf.intValue(), 2);
                    break;
                case 17:
                    TemplateViewFive12 templateViewFive1216 = getBinding().template5;
                    int[] iArr16 = this.mBorder1_18;
                    valueOf = iArr16 != null ? Integer.valueOf(iArr16[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1216.setData2(data, valueOf.intValue(), 2);
                    break;
                case 18:
                    TemplateViewFive12 templateViewFive1217 = getBinding().template5;
                    int[] iArr17 = this.mBorder1_22;
                    valueOf = iArr17 != null ? Integer.valueOf(iArr17[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1217.setData2(data, valueOf.intValue(), 2);
                    break;
                case 19:
                    TemplateViewFive12 templateViewFive1218 = getBinding().template5;
                    int[] iArr18 = this.mBorder1_26;
                    valueOf = iArr18 != null ? Integer.valueOf(iArr18[data.getBorderIndex()]) : null;
                    Intrinsics.checkNotNull(valueOf);
                    templateViewFive1218.setData2(data, valueOf.intValue(), 2);
                    break;
            }
            return;
        }
        getBinding().template5.setData2(data, 0, 2);
    }
}
