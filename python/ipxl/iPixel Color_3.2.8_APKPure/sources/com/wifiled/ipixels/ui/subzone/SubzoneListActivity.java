package com.wifiled.ipixels.ui.subzone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.SendDataCallback;
import com.wifiled.ipixels.databinding.ActivitySubzoneListBinding;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationModel;
import com.wifiled.ipixels.ui.subzone.SubzoneListAdapter;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SubzoneListActivity.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0002H\u0016J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u001a\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0015H\u0002J\b\u0010)\u001a\u00020\u0015H\u0002J\b\u0010*\u001a\u00020\u0015H\u0002J\u0010\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u000bH\u0002J\u0010\u0010-\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\tH\u0007J\b\u0010.\u001a\u00020\u0015H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010#\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010%0%0$¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006/"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SubzoneListActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivitySubzoneListBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mDatas", "", "Lcom/wifiled/ipixels/ui/subzone/SubzoneData;", "mIsEdit", "", "mEditSelectPosition", "", "mSubzoneListAdapter", "Lcom/wifiled/ipixels/ui/subzone/SubzoneListAdapter;", "mSendDataCallback", "Lcom/wifiled/ipixels/core/SendDataCallback;", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initToolBar", "initList", "sendData", "subzoneData", "imageData", "", "getSubzoneList", "onClick", "v", "Landroid/view/View;", "editData", "mResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getMResult", "()Landroidx/activity/result/ActivityResultLauncher;", "showDialog", "deleteSelect", "setEditStatus", NotificationCompat.CATEGORY_STATUS, "onEvent", "onDestroy", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubzoneListActivity extends UiBaseActivity<TextAnimationModel, ActivitySubzoneListBinding> implements View.OnClickListener {
    private List<SubzoneData> mDatas = new ArrayList();
    private int mEditSelectPosition = -1;
    private boolean mIsEdit;
    private final ActivityResultLauncher<Intent> mResult;
    private SendDataCallback mSendDataCallback;
    private SubzoneListAdapter mSubzoneListAdapter;

    public SubzoneListActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SubzoneListActivity.mResult$lambda$3(SubzoneListActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.mResult = registerForActivityResult;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivitySubzoneListBinding getViewBinding() {
        ActivitySubzoneListBinding inflate = ActivitySubzoneListBinding.inflate(getLayoutInflater());
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
        EventBus.getDefault().register(this);
        initToolBar();
        this.mSendDataCallback = new SubzoneListActivity$initView$1(this);
        initList();
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.partition_display));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        getBinding().ivRight.setBackgroundResource(R.drawable.text_image_edit2);
        SubzoneListActivity subzoneListActivity = this;
        getBinding().ivBack.setOnClickListener(subzoneListActivity);
        getBinding().ivRight.setOnClickListener(subzoneListActivity);
        getBinding().ivEdit.setOnClickListener(subzoneListActivity);
        getBinding().ivDelete.setOnClickListener(subzoneListActivity);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    private final void initList() {
        int i;
        getSubzoneList();
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
            case 10:
                i = R.layout.subzone_list_item_2_1;
                break;
            case 11:
                i = R.layout.subzone_list_item_3_1;
                break;
            case 12:
            case 13:
            default:
                i = R.layout.subzone_list_item_4_1;
                break;
            case 14:
                i = R.layout.subzone_list_item_5_1;
                break;
            case 15:
                i = R.layout.subzone_list_item_6_1;
                break;
            case 16:
                i = R.layout.subzone_list_item_8_1;
                break;
            case 17:
                i = R.layout.subzone_list_item_10_1;
                break;
            case 18:
                i = R.layout.subzone_list_item_12_1;
                break;
            case 19:
                i = R.layout.subzone_list_item_14_1;
                break;
        }
        SubzoneListAdapter subzoneListAdapter = new SubzoneListAdapter(i);
        this.mSubzoneListAdapter = subzoneListAdapter;
        subzoneListAdapter.setList(this.mDatas);
        if (AppConfig.INSTANCE.getLedSize().get(0).intValue() > 96) {
            getBinding().rvList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            getBinding().rvList.setLayoutManager(new GridLayoutManager(this, 2));
        }
        RecyclerView recyclerView = getBinding().rvList;
        SubzoneListAdapter subzoneListAdapter2 = this.mSubzoneListAdapter;
        SubzoneListAdapter subzoneListAdapter3 = null;
        if (subzoneListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter2 = null;
        }
        recyclerView.setAdapter(subzoneListAdapter2);
        SubzoneListAdapter subzoneListAdapter4 = this.mSubzoneListAdapter;
        if (subzoneListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter4 = null;
        }
        subzoneListAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                SubzoneListActivity.initList$lambda$1(SubzoneListActivity.this, baseQuickAdapter, view, i2);
            }
        });
        SubzoneListAdapter subzoneListAdapter5 = this.mSubzoneListAdapter;
        if (subzoneListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
        } else {
            subzoneListAdapter3 = subzoneListAdapter5;
        }
        subzoneListAdapter3.setItemOnClickListener(new SubzoneListAdapter.ItemOnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$initList$2
            @Override // com.wifiled.ipixels.ui.subzone.SubzoneListAdapter.ItemOnClickListener
            public void itemClick() {
                LogUtils.vTag("ruis", "mSubzoneListAdapter   itemClick ");
            }

            @Override // com.wifiled.ipixels.ui.subzone.SubzoneListAdapter.ItemOnClickListener
            public void sizeChange(int size) {
                ActivitySubzoneListBinding binding;
                ActivitySubzoneListBinding binding2;
                ActivitySubzoneListBinding binding3;
                ActivitySubzoneListBinding binding4;
                if (size > 1) {
                    binding3 = SubzoneListActivity.this.getBinding();
                    binding3.ivRight.setEnabled(true);
                    binding4 = SubzoneListActivity.this.getBinding();
                    binding4.ivRight.setAlpha(1.0f);
                    return;
                }
                binding = SubzoneListActivity.this.getBinding();
                binding.ivRight.setEnabled(false);
                binding2 = SubzoneListActivity.this.getBinding();
                binding2.ivRight.setAlpha(0.5f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initList$lambda$1(final SubzoneListActivity subzoneListActivity, BaseQuickAdapter adapter, View view, int i) {
        Bitmap view2Bitmap;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        SubzoneListAdapter subzoneListAdapter = subzoneListActivity.mSubzoneListAdapter;
        SubzoneListAdapter subzoneListAdapter2 = null;
        if (subzoneListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter = null;
        }
        if (i == subzoneListAdapter.getData().size() - 1) {
            subzoneListActivity.startActivity(new Intent(subzoneListActivity, (Class<?>) SelectTemplateActivity.class));
            return;
        }
        if (subzoneListActivity.mIsEdit) {
            if (subzoneListActivity.mEditSelectPosition != -1) {
                SubzoneListAdapter subzoneListAdapter3 = subzoneListActivity.mSubzoneListAdapter;
                if (subzoneListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                    subzoneListAdapter3 = null;
                }
                subzoneListAdapter3.getData().get(subzoneListActivity.mEditSelectPosition).setEdit(false);
                SubzoneListAdapter subzoneListAdapter4 = subzoneListActivity.mSubzoneListAdapter;
                if (subzoneListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                    subzoneListAdapter4 = null;
                }
                subzoneListAdapter4.notifyItemChanged(subzoneListActivity.mEditSelectPosition);
            }
            SubzoneListAdapter subzoneListAdapter5 = subzoneListActivity.mSubzoneListAdapter;
            if (subzoneListAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter5 = null;
            }
            SubzoneData subzoneData = subzoneListAdapter5.getData().get(i);
            SubzoneListAdapter subzoneListAdapter6 = subzoneListActivity.mSubzoneListAdapter;
            if (subzoneListAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter6 = null;
            }
            subzoneData.setEdit(!subzoneListAdapter6.getData().get(i).isEdit());
            subzoneListActivity.mEditSelectPosition = i;
            SubzoneListAdapter subzoneListAdapter7 = subzoneListActivity.mSubzoneListAdapter;
            if (subzoneListAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            } else {
                subzoneListAdapter2 = subzoneListAdapter7;
            }
            subzoneListAdapter2.notifyItemChanged(subzoneListActivity.mEditSelectPosition);
            return;
        }
        if (BleManager.INSTANCE.get().getConnectedDevice() == null && BleManager2.INSTANCE.get().getConnectedDevice() == null) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit initList$lambda$1$lambda$0;
                    initList$lambda$1$lambda$0 = SubzoneListActivity.initList$lambda$1$lambda$0(SubzoneListActivity.this);
                    return initList$lambda$1$lambda$0;
                }
            });
            return;
        }
        SubzoneListAdapter subzoneListAdapter8 = subzoneListActivity.mSubzoneListAdapter;
        if (subzoneListAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter8 = null;
        }
        int templateType = subzoneListAdapter8.getData().get(i).getTemplateType();
        if (templateType == 1) {
            view2Bitmap = ImageUtils.view2Bitmap(adapter.getViewByPosition(i, R.id.template1));
        } else if (templateType == 2) {
            view2Bitmap = ImageUtils.view2Bitmap(adapter.getViewByPosition(i, R.id.template2));
        } else if (templateType == 3) {
            view2Bitmap = ImageUtils.view2Bitmap(adapter.getViewByPosition(i, R.id.template3));
        } else if (templateType == 4) {
            view2Bitmap = ImageUtils.view2Bitmap(adapter.getViewByPosition(i, R.id.template4));
        } else {
            view2Bitmap = templateType != 5 ? null : ImageUtils.view2Bitmap(adapter.getViewByPosition(i, R.id.template5));
        }
        byte[] bitmapToByteStream = BGRUtils.bitmapToByteStream(view2Bitmap);
        SubzoneListAdapter subzoneListAdapter9 = subzoneListActivity.mSubzoneListAdapter;
        if (subzoneListAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
        } else {
            subzoneListAdapter2 = subzoneListAdapter9;
        }
        subzoneListActivity.sendData(subzoneListAdapter2.getData().get(i), bitmapToByteStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initList$lambda$1$lambda$0(SubzoneListActivity subzoneListActivity) {
        ToastUtil.show(subzoneListActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    private final void sendData(SubzoneData subzoneData, byte[] imageData) {
        SendDataCallback sendDataCallback = this.mSendDataCallback;
        if (sendDataCallback != null) {
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
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList2), new SubzoneListActivity$sendData$1$1(subzoneData, sendDataCallback, imageData, this));
            SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList2), new SubzoneListActivity$sendData$1$2(subzoneData, sendDataCallback, imageData, this));
        }
    }

    private final void getSubzoneList() {
        this.mDatas.clear();
        SubzoneListActivity subzoneListActivity = this;
        if (SPUtils.contains(subzoneListActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType())) {
            if (SPUtils.get(subzoneListActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                Object obj = SPUtils.get(subzoneListActivity, "subzone_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                this.mDatas = (List) obj;
            }
        }
        this.mDatas.add(new SubzoneData(-1, new ArrayList(), AppConfig.INSTANCE.getLedType(), 0, 0, 0, new byte[0], new byte[0], 0, 0, false));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivRight)) {
            setEditStatus(!this.mIsEdit);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivEdit)) {
            editData();
            setEditStatus(!this.mIsEdit);
        } else if (Intrinsics.areEqual(v, getBinding().ivDelete)) {
            showDialog();
        }
    }

    private final void editData() {
        LogUtils.file("ImageTextListActivity editImageText");
        SubzoneListAdapter subzoneListAdapter = this.mSubzoneListAdapter;
        if (subzoneListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter = null;
        }
        int size = subzoneListAdapter.getData().size();
        for (int i = 0; i < size; i++) {
            SubzoneListAdapter subzoneListAdapter2 = this.mSubzoneListAdapter;
            if (subzoneListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter2 = null;
            }
            SubzoneData subzoneData = subzoneListAdapter2.getData().get(i);
            if (subzoneData.isEdit()) {
                Intent intent = new Intent(this, (Class<?>) EditTemplateActivity.class);
                intent.putExtra("data", subzoneData);
                intent.putExtra(PlayerFinal.PLAYER_POSITION, i);
                intent.putExtra("isEdit", true);
                this.mResult.launch(intent);
                return;
            }
        }
    }

    public final ActivityResultLauncher<Intent> getMResult() {
        return this.mResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mResult$lambda$3(SubzoneListActivity subzoneListActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            subzoneListActivity.getSubzoneList();
            SubzoneListAdapter subzoneListAdapter = subzoneListActivity.mSubzoneListAdapter;
            if (subzoneListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter = null;
            }
            subzoneListAdapter.setList(subzoneListActivity.mDatas);
        }
    }

    private final void showDialog() {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(getString(R.string.tip_it)).setMessage(R.string.delete_data).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.SubzoneListActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SubzoneListActivity.showDialog$lambda$5(SubzoneListActivity.this, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$5(SubzoneListActivity subzoneListActivity, DialogInterface dialogInterface, int i) {
        subzoneListActivity.deleteSelect();
        dialogInterface.dismiss();
    }

    private final void deleteSelect() {
        int size = this.mDatas.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            if (this.mDatas.get(i).isEdit()) {
                List<SubzoneData> list = this.mDatas;
                list.remove(list.get(i));
                SubzoneListAdapter subzoneListAdapter = this.mSubzoneListAdapter;
                if (subzoneListAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                    subzoneListAdapter = null;
                }
                subzoneListAdapter.removeAt(i);
            } else {
                i++;
            }
        }
        getBinding().clBtn.setVisibility(8);
        getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mDatas);
        arrayList.remove(CollectionsKt.getLastIndex(this.mDatas));
        SPUtils.put(this, "subzone_list_" + AppConfig.INSTANCE.getLedType(), arrayList);
        setEditStatus(false);
    }

    private final void setEditStatus(boolean status) {
        this.mIsEdit = status;
        SubzoneListAdapter subzoneListAdapter = this.mSubzoneListAdapter;
        if (subzoneListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
            subzoneListAdapter = null;
        }
        subzoneListAdapter.setEditStatus(status);
        if (status) {
            getBinding().ivRight.setImageResource(R.drawable.remote_save);
            getBinding().clBtn.setVisibility(0);
        } else {
            getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
            getBinding().clBtn.setVisibility(8);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onEvent(SubzoneData subzoneData) {
        Intrinsics.checkNotNullParameter(subzoneData, "subzoneData");
        if (this.mEditSelectPosition != -1) {
            SubzoneListAdapter subzoneListAdapter = this.mSubzoneListAdapter;
            if (subzoneListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter = null;
            }
            subzoneListAdapter.getData().set(this.mEditSelectPosition, subzoneData);
            SubzoneListAdapter subzoneListAdapter2 = this.mSubzoneListAdapter;
            if (subzoneListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter2 = null;
            }
            subzoneListAdapter2.notifyItemChanged(this.mEditSelectPosition);
            this.mEditSelectPosition = -1;
        } else {
            SubzoneListAdapter subzoneListAdapter3 = this.mSubzoneListAdapter;
            if (subzoneListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubzoneListAdapter");
                subzoneListAdapter3 = null;
            }
            subzoneListAdapter3.addData(this.mDatas.size() - 1, (int) subzoneData);
        }
        if (this.mSendDataCallback != null) {
            sendData(subzoneData, null);
        }
        EventBus.getDefault().removeStickyEvent(subzoneData);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        this.mSendDataCallback = null;
        super.onDestroy();
    }
}
