package com.wifiled.ipixels.ui.imgtxt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import com.wifiled.ipixels.databinding.ActivityImageTextListBinding;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog;
import com.wifiled.ipixels.ui.imgtxt.ImageTextListAdapter;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: ImageTextListActivity.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\b\u0010\u001a\u001a\u00020\u0002H\u0016J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\b\u0010 \u001a\u00020\u001cH\u0002J\b\u0010'\u001a\u00020\u001cH\u0002J\b\u0010(\u001a\u00020\u001cH\u0002J\u0010\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u001cH\u0002J\b\u0010-\u001a\u00020\u001cH\u0002J\b\u0010.\u001a\u00020\u001cH\u0002J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u000bH\u0002J\u0010\u00101\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u000bH\u0002J;\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u001c07¢\u0006\u0002\b92\b\b\u0002\u0010:\u001a\u00020\u0011H\u0002J;\u0010;\u001a\u00020\u001c2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u001c07¢\u0006\u0002\b92\b\b\u0002\u0010:\u001a\u00020\u0011H\u0002J;\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u0002042\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u001c07¢\u0006\u0002\b92\b\b\u0002\u0010:\u001a\u00020\u0011H\u0002J;\u0010?\u001a\u00020\u001c2\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u0002042\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u001c07¢\u0006\u0002\b92\b\b\u0002\u0010:\u001a\u00020\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001f\u0010!\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010#0#0\"¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006@"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivityImageTextListBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mImageTextListAdapter", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListAdapter;", "mDatas", "", "Lcom/wifiled/ipixels/ui/imgtxt/ImageTextListData;", "mIsEdit", "", "mEditSelectPosition", "", "sendData", "", "mSelectDeviceDialog", "Lcom/wifiled/ipixels/ui/dialog/SelecrDeviceDialog;", "tProcess", "getTProcess", "()I", "setTProcess", "(I)V", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initToolBar", "initList", "mResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getMResult", "()Landroidx/activity/result/ActivityResultLauncher;", "getImageTextList", "exitEditStatus", "onClick", "v", "Landroid/view/View;", "showDialog", "deleteSelect", "editImageText", "AutoSendChannel", "dataJson", "AutoSendChannel2", "sendTextData", "arrText", "", "arrTotal", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "isBulkSend", "sendTextData2", "sendImagData", "serial", "data", "sendImagData2", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextListActivity extends UiBaseActivity<TextAnimationModel, ActivityImageTextListBinding> implements View.OnClickListener {
    private ImageTextListAdapter mImageTextListAdapter;
    private boolean mIsEdit;
    private final ActivityResultLauncher<Intent> mResult;
    private SelecrDeviceDialog mSelectDeviceDialog;
    private int tProcess;
    private List<ImageTextListData> mDatas = new ArrayList();
    private int mEditSelectPosition = -1;
    private List<Byte> sendData = new ArrayList();

    public ImageTextListActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda54
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ImageTextListActivity.mResult$lambda$0(ImageTextListActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.mResult = registerForActivityResult;
    }

    public final int getTProcess() {
        return this.tProcess;
    }

    public final void setTProcess(int i) {
        this.tProcess = i;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityImageTextListBinding getViewBinding() {
        ActivityImageTextListBinding inflate = ActivityImageTextListBinding.inflate(getLayoutInflater());
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
        LogUtils.file("ImageTextListActivity initView");
        initToolBar();
        initList();
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
        getBinding().ivRight.setBackgroundResource(R.drawable.text_image_edit2);
        ImageTextListActivity imageTextListActivity = this;
        getBinding().ivBack.setOnClickListener(imageTextListActivity);
        getBinding().ivRight.setOnClickListener(imageTextListActivity);
        getBinding().ivEdit.setOnClickListener(imageTextListActivity);
        getBinding().ivDelete.setOnClickListener(imageTextListActivity);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    private final void initList() {
        getImageTextList();
        ImageTextListAdapter imageTextListAdapter = new ImageTextListAdapter();
        this.mImageTextListAdapter = imageTextListAdapter;
        imageTextListAdapter.setList(this.mDatas);
        getBinding().rvList.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerView recyclerView = getBinding().rvList;
        ImageTextListAdapter imageTextListAdapter2 = this.mImageTextListAdapter;
        ImageTextListAdapter imageTextListAdapter3 = null;
        if (imageTextListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
            imageTextListAdapter2 = null;
        }
        recyclerView.setAdapter(imageTextListAdapter2);
        ImageTextListAdapter imageTextListAdapter4 = this.mImageTextListAdapter;
        if (imageTextListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
            imageTextListAdapter4 = null;
        }
        imageTextListAdapter4.setOnItemClickListener(new ImageTextListActivity$initList$1(this));
        ImageTextListAdapter imageTextListAdapter5 = this.mImageTextListAdapter;
        if (imageTextListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
        } else {
            imageTextListAdapter3 = imageTextListAdapter5;
        }
        imageTextListAdapter3.setSizeChangeListener(new ImageTextListAdapter.SizeChangeListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$initList$2
            @Override // com.wifiled.ipixels.ui.imgtxt.ImageTextListAdapter.SizeChangeListener
            public void sizeChange(int size) {
                ActivityImageTextListBinding binding;
                ActivityImageTextListBinding binding2;
                ActivityImageTextListBinding binding3;
                ActivityImageTextListBinding binding4;
                LogUtils.file("ImageTextListActivity  mImageTextListAdapter.setSizeChangeListener");
                if (size > 1) {
                    binding3 = ImageTextListActivity.this.getBinding();
                    binding3.ivRight.setEnabled(true);
                    binding4 = ImageTextListActivity.this.getBinding();
                    binding4.ivRight.setAlpha(1.0f);
                    return;
                }
                binding = ImageTextListActivity.this.getBinding();
                binding.ivRight.setEnabled(false);
                binding2 = ImageTextListActivity.this.getBinding();
                binding2.ivRight.setAlpha(0.5f);
            }
        });
    }

    public final ActivityResultLauncher<Intent> getMResult() {
        return this.mResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mResult$lambda$0(ImageTextListActivity imageTextListActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            imageTextListActivity.getImageTextList();
            ImageTextListAdapter imageTextListAdapter = imageTextListActivity.mImageTextListAdapter;
            if (imageTextListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                imageTextListAdapter = null;
            }
            imageTextListAdapter.setList(imageTextListActivity.mDatas);
        }
    }

    private final void getImageTextList() {
        this.mDatas.clear();
        ImageTextListActivity imageTextListActivity = this;
        if (SPUtils.contains(imageTextListActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType())) {
            if (SPUtils.get(imageTextListActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                Object obj = SPUtils.get(imageTextListActivity, "image_text_list_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                this.mDatas = (List) obj;
            }
        }
        this.mDatas.add(new ImageTextListData(0, 1, "add", false, "", new ArrayList(), AppConfig.INSTANCE.getLedType()));
    }

    private final void exitEditStatus() {
        LogUtils.file("ImageTextListActivity  exitEditStatus");
        if (this.mIsEdit) {
            getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
            this.mIsEdit = false;
            ImageTextListAdapter imageTextListAdapter = this.mImageTextListAdapter;
            ImageTextListAdapter imageTextListAdapter2 = null;
            if (imageTextListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                imageTextListAdapter = null;
            }
            Iterator<ImageTextListData> it = imageTextListAdapter.getData().iterator();
            while (it.hasNext()) {
                it.next().setEdit(false);
            }
            ImageTextListAdapter imageTextListAdapter3 = this.mImageTextListAdapter;
            if (imageTextListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                imageTextListAdapter3 = null;
            }
            imageTextListAdapter3.setEditStatus(false);
            ImageTextListAdapter imageTextListAdapter4 = this.mImageTextListAdapter;
            if (imageTextListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
            } else {
                imageTextListAdapter2 = imageTextListAdapter4;
            }
            imageTextListAdapter2.notifyDataSetChanged();
            getBinding().clBtn.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            LogUtils.file("ImageTextListActivity   binding.ivBack");
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivRight)) {
            LogUtils.file("ImageTextListActivity  binding.ivRight ");
            if (this.mIsEdit) {
                exitEditStatus();
            } else {
                this.mIsEdit = true;
                getBinding().clBtn.setVisibility(0);
                getBinding().ivRight.setImageResource(R.drawable.text_image_edit);
            }
            ImageTextListAdapter imageTextListAdapter = this.mImageTextListAdapter;
            if (imageTextListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                imageTextListAdapter = null;
            }
            imageTextListAdapter.setEditStatus(this.mIsEdit);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivEdit)) {
            LogUtils.file("ImageTextListActivity  binding.ivEdit ");
            editImageText();
            exitEditStatus();
        } else if (Intrinsics.areEqual(v, getBinding().ivDelete)) {
            showDialog();
        }
    }

    private final void showDialog() {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(getString(R.string.tip_it)).setMessage(R.string.delete_data).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda15
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ImageTextListActivity.showDialog$lambda$2(ImageTextListActivity.this, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$2(ImageTextListActivity imageTextListActivity, DialogInterface dialogInterface, int i) {
        imageTextListActivity.deleteSelect();
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
                List<ImageTextListData> list = this.mDatas;
                list.remove(list.get(i));
                break;
            }
            i++;
        }
        ImageTextListAdapter imageTextListAdapter = this.mImageTextListAdapter;
        ImageTextListAdapter imageTextListAdapter2 = null;
        if (imageTextListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
            imageTextListAdapter = null;
        }
        imageTextListAdapter.setList(this.mDatas);
        ImageTextListAdapter imageTextListAdapter3 = this.mImageTextListAdapter;
        if (imageTextListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
        } else {
            imageTextListAdapter2 = imageTextListAdapter3;
        }
        imageTextListAdapter2.setEditStatus(false);
        getBinding().clBtn.setVisibility(8);
        getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mDatas);
        arrayList.remove(CollectionsKt.getLastIndex(this.mDatas));
        SPUtils.put(this, "image_text_list_" + AppConfig.INSTANCE.getLedType(), arrayList);
    }

    private final void editImageText() {
        LogUtils.file("ImageTextListActivity editImageText");
        ImageTextListAdapter imageTextListAdapter = this.mImageTextListAdapter;
        if (imageTextListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
            imageTextListAdapter = null;
        }
        int size = imageTextListAdapter.getData().size();
        for (int i = 0; i < size; i++) {
            ImageTextListAdapter imageTextListAdapter2 = this.mImageTextListAdapter;
            if (imageTextListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                imageTextListAdapter2 = null;
            }
            ImageTextListData imageTextListData = imageTextListAdapter2.getData().get(i);
            if (imageTextListData.isEdit()) {
                Intent intent = new Intent(this, (Class<?>) ImageTextActivity.class);
                intent.putExtra("data", imageTextListData);
                intent.putExtra(PlayerFinal.PLAYER_POSITION, i);
                intent.putExtra("isEdit", true);
                this.mResult.launch(intent);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r15v1, types: [T, java.util.List] */
    public final void AutoSendChannel(ImageTextListData dataJson) {
        LogUtils.file("ImageTextListActivity AutoSendChannel");
        String string = getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, string, false, 5, (Object) null);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = dataJson.getMDataList();
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda61
            @Override // java.lang.Runnable
            public final void run() {
                ImageTextListActivity.AutoSendChannel$lambda$30(Ref.ObjectRef.this, booleanRef3, booleanRef4, booleanRef, this, booleanRef2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AutoSendChannel$lambda$30(Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Ref.BooleanRef booleanRef3, ImageTextListActivity imageTextListActivity, Ref.BooleanRef booleanRef4) {
        final Ref.BooleanRef booleanRef5;
        final Ref.BooleanRef booleanRef6;
        List<Byte> mutableList;
        SendCore.INSTANCE.deleteAllData(new SendResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$AutoSendChannel$1$1
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int result) {
            }

            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                Intrinsics.checkNotNullParameter(result, "result");
            }
        });
        Thread.sleep(200L);
        final Ref.IntRef intRef = new Ref.IntRef();
        if (AppConfig.INSTANCE.getMcu() > 4) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            arrayList.add((byte) 8);
            arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            int i = 1;
            for (ChannelListItem channelListItem : (List) objectRef.element) {
                if (channelListItem instanceof ChannelListItem.TextEmojView) {
                    intRef.element++;
                    ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem;
                    textEmojView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(textEmojView.getSerialNum())));
                } else if (channelListItem instanceof ChannelListItem.ImagView) {
                    intRef.element++;
                    ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) channelListItem;
                    imagView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(imagView.getSerialNum())));
                } else if (channelListItem instanceof ChannelListItem.GiftView) {
                    intRef.element++;
                    ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) channelListItem;
                    giftView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(giftView.getSerialNum())));
                }
                i++;
            }
            arrayList.set(4, Byte.valueOf((byte) intRef.element));
            arrayList.set(0, Byte.valueOf((byte) arrayList.size()));
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new ImageTextListActivity$AutoSendChannel$1$2(imageTextListActivity));
            Thread.sleep(500L);
        } else {
            intRef.element = ((List) objectRef.element).size();
        }
        Iterator it = ((List) objectRef.element).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (booleanRef.element) {
                booleanRef.element = false;
                break;
            }
            if (booleanRef2.element) {
                booleanRef2.element = false;
                break;
            }
            if (booleanRef3.element) {
                ThreadUtils.delay(300L);
                ChannelListItem channelListItem2 = (ChannelListItem) it.next();
                if (channelListItem2 instanceof ChannelListItem.TextEmojView) {
                    imageTextListActivity.sendData.clear();
                    ChannelListItem.TextEmojView textEmojView2 = (ChannelListItem.TextEmojView) channelListItem2;
                    imageTextListActivity.sendData.addAll(ArraysKt.toMutableList(textEmojView2.getArrTextData()));
                    byte[] plus = ArraysKt.plus(new byte[0], Byte.parseByte(textEmojView2.getSerialNum()));
                    Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(textEmojView2.getSerialNum())));
                    booleanRef5 = booleanRef2;
                    final Ref.BooleanRef booleanRef7 = booleanRef3;
                    final ImageTextListActivity imageTextListActivity2 = imageTextListActivity;
                    final Ref.BooleanRef booleanRef8 = booleanRef4;
                    booleanRef6 = booleanRef7;
                    imageTextListActivity2.sendTextData(plus, CollectionsKt.toByteArray(imageTextListActivity.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda30
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit AutoSendChannel$lambda$30$lambda$10;
                            AutoSendChannel$lambda$30$lambda$10 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10(Ref.BooleanRef.this, imageTextListActivity2, intRef, booleanRef8, booleanRef5, (SendCore.CallbackBuilder) obj);
                            return AutoSendChannel$lambda$30$lambda$10;
                        }
                    }, Byte.parseByte(textEmojView2.getSerialNum()));
                    imageTextListActivity = imageTextListActivity2;
                } else {
                    booleanRef5 = booleanRef2;
                    booleanRef6 = booleanRef3;
                    final ImageTextListActivity imageTextListActivity3 = imageTextListActivity;
                    final Ref.BooleanRef booleanRef9 = booleanRef4;
                    if (channelListItem2 instanceof ChannelListItem.ImagView) {
                        imageTextListActivity3.sendData.clear();
                        ChannelListItem.ImagView imagView2 = (ChannelListItem.ImagView) channelListItem2;
                        imageTextListActivity3.sendData.addAll(ArraysKt.toMutableList(imagView2.getArrImagData()));
                        byte[] plus2 = ArraysKt.plus(new byte[0], Byte.parseByte(imagView2.getSerialNum()));
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(imagView2.getSerialNum())));
                        imageTextListActivity3.sendImagData(plus2, CollectionsKt.toByteArray(imageTextListActivity3.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda31
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit AutoSendChannel$lambda$30$lambda$18;
                                AutoSendChannel$lambda$30$lambda$18 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18(Ref.BooleanRef.this, imageTextListActivity3, intRef, booleanRef9, booleanRef5, (SendCore.CallbackBuilder) obj);
                                return AutoSendChannel$lambda$30$lambda$18;
                            }
                        }, Byte.parseByte(imagView2.getSerialNum()));
                    } else if (channelListItem2 instanceof ChannelListItem.GiftView) {
                        imageTextListActivity3.sendData.clear();
                        ChannelListItem.GiftView giftView2 = (ChannelListItem.GiftView) channelListItem2;
                        byte[] arrGifData = giftView2.getArrGifData();
                        if (arrGifData != null && (mutableList = ArraysKt.toMutableList(arrGifData)) != null) {
                            imageTextListActivity3.sendData.addAll(mutableList);
                        }
                        byte[] plus3 = ArraysKt.plus(new byte[0], Byte.parseByte(giftView2.getSerialNum()));
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(giftView2.getSerialNum())));
                        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
                            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda32
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    Unit AutoSendChannel$lambda$30$lambda$20;
                                    AutoSendChannel$lambda$30$lambda$20 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$20(ImageTextListActivity.this);
                                    return AutoSendChannel$lambda$30$lambda$20;
                                }
                            });
                        } else {
                            SendCore.INSTANCE.sendChannelGifData(plus3, CollectionsKt.toByteArray(imageTextListActivity3.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda34
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    Unit AutoSendChannel$lambda$30$lambda$29;
                                    AutoSendChannel$lambda$30$lambda$29 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29(Ref.BooleanRef.this, imageTextListActivity3, intRef, booleanRef9, booleanRef5, (SendCore.CallbackBuilder) obj);
                                    return AutoSendChannel$lambda$30$lambda$29;
                                }
                            }, Byte.parseByte(giftView2.getSerialNum()));
                            booleanRef3 = booleanRef6;
                            imageTextListActivity = imageTextListActivity3;
                            booleanRef4 = booleanRef9;
                            booleanRef2 = booleanRef5;
                        }
                    }
                    imageTextListActivity = imageTextListActivity3;
                    booleanRef4 = booleanRef9;
                }
                booleanRef3 = booleanRef6;
                booleanRef2 = booleanRef5;
            }
        }
        Ref.BooleanRef booleanRef10 = booleanRef3;
        LogUtils.e("#1.0# run here isSendOver: " + booleanRef10.element + " iSelTotalCount：" + intRef.element);
        if (booleanRef10.element && AppConfig.INSTANCE.isCancel()) {
            AppConfig.INSTANCE.setCancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendTextData) {
        Intrinsics.checkNotNullParameter(sendTextData, "$this$sendTextData");
        sendTextData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$10$lambda$3;
                AutoSendChannel$lambda$30$lambda$10$lambda$3 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$3(Ref.BooleanRef.this);
                return AutoSendChannel$lambda$30$lambda$10$lambda$3;
            }
        });
        sendTextData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$10$lambda$4;
                AutoSendChannel$lambda$30$lambda$10$lambda$4 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$4(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel$lambda$30$lambda$10$lambda$4;
            }
        });
        sendTextData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$10$lambda$6;
                AutoSendChannel$lambda$30$lambda$10$lambda$6 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$6(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel$lambda$30$lambda$10$lambda$6;
            }
        });
        sendTextData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$10$lambda$9;
                AutoSendChannel$lambda$30$lambda$10$lambda$9 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$9(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel$lambda$30$lambda$10$lambda$9;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$3(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$4(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$6(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "TextEmojView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel$lambda$30$lambda$10$lambda$6$lambda$5;
                        AutoSendChannel$lambda$30$lambda$10$lambda$6$lambda$5 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$6$lambda$5(ImageTextListActivity.this);
                        return AutoSendChannel$lambda$30$lambda$10$lambda$6$lambda$5;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$6$lambda$5(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$9(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$7;
                    AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$7 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$7(ImageTextListActivity.this);
                    return AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$7;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$8;
                    AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$8 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$8(ImageTextListActivity.this);
                    return AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$8;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$7(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$10$lambda$9$lambda$8(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendImagData) {
        Intrinsics.checkNotNullParameter(sendImagData, "$this$sendImagData");
        sendImagData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$18$lambda$11;
                AutoSendChannel$lambda$30$lambda$18$lambda$11 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$11(Ref.BooleanRef.this);
                return AutoSendChannel$lambda$30$lambda$18$lambda$11;
            }
        });
        sendImagData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$18$lambda$12;
                AutoSendChannel$lambda$30$lambda$18$lambda$12 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$12(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel$lambda$30$lambda$18$lambda$12;
            }
        });
        sendImagData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$18$lambda$14;
                AutoSendChannel$lambda$30$lambda$18$lambda$14 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$14(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel$lambda$30$lambda$18$lambda$14;
            }
        });
        sendImagData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$18$lambda$17;
                AutoSendChannel$lambda$30$lambda$18$lambda$17 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$17(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel$lambda$30$lambda$18$lambda$17;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$11(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$12(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$14(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "ImagView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda70
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel$lambda$30$lambda$18$lambda$14$lambda$13;
                        AutoSendChannel$lambda$30$lambda$18$lambda$14$lambda$13 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$14$lambda$13(ImageTextListActivity.this);
                        return AutoSendChannel$lambda$30$lambda$18$lambda$14$lambda$13;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$14$lambda$13(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$17(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda64
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$15;
                    AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$15 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$15(ImageTextListActivity.this);
                    return AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$15;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda65
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$16;
                    AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$16 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$16(ImageTextListActivity.this);
                    return AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$16;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$15(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$18$lambda$17$lambda$16(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$20(ImageTextListActivity imageTextListActivity) {
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail) + "100114");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendChannelGifData) {
        Intrinsics.checkNotNullParameter(sendChannelGifData, "$this$sendChannelGifData");
        sendChannelGifData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$21;
                AutoSendChannel$lambda$30$lambda$29$lambda$21 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$21(Ref.BooleanRef.this);
                return AutoSendChannel$lambda$30$lambda$29$lambda$21;
            }
        });
        sendChannelGifData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$22;
                AutoSendChannel$lambda$30$lambda$29$lambda$22 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$22(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel$lambda$30$lambda$29$lambda$22;
            }
        });
        sendChannelGifData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$24;
                AutoSendChannel$lambda$30$lambda$29$lambda$24 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$24(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel$lambda$30$lambda$29$lambda$24;
            }
        });
        sendChannelGifData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$26;
                AutoSendChannel$lambda$30$lambda$29$lambda$26 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$26(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel$lambda$30$lambda$29$lambda$26;
            }
        });
        sendChannelGifData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$28;
                AutoSendChannel$lambda$30$lambda$29$lambda$28 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$28(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel$lambda$30$lambda$29$lambda$28;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$21(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$22(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$24(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "GiftView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda40
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel$lambda$30$lambda$29$lambda$24$lambda$23;
                        AutoSendChannel$lambda$30$lambda$29$lambda$24$lambda$23 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$24$lambda$23(ImageTextListActivity.this);
                        return AutoSendChannel$lambda$30$lambda$29$lambda$24$lambda$23;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$24$lambda$23(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$26(final ImageTextListActivity imageTextListActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel$lambda$30$lambda$29$lambda$26$lambda$25;
                AutoSendChannel$lambda$30$lambda$29$lambda$26$lambda$25 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$26$lambda$25(ImageTextListActivity.this, i);
                return AutoSendChannel$lambda$30$lambda$29$lambda$26$lambda$25;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$26$lambda$25(ImageTextListActivity imageTextListActivity, int i) {
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail) + i);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$28(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it[4] == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel$lambda$30$lambda$29$lambda$28$lambda$27;
                    AutoSendChannel$lambda$30$lambda$29$lambda$28$lambda$27 = ImageTextListActivity.AutoSendChannel$lambda$30$lambda$29$lambda$28$lambda$27(ImageTextListActivity.this);
                    return AutoSendChannel$lambda$30$lambda$29$lambda$28$lambda$27;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel$lambda$30$lambda$29$lambda$28$lambda$27(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r15v1, types: [T, java.util.List] */
    public final void AutoSendChannel2(ImageTextListData dataJson) {
        LogUtils.file("ImageTextListActivity AutoSendChannel");
        String string = getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, string, false, 5, (Object) null);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = dataJson.getMDataList();
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                ImageTextListActivity.AutoSendChannel2$lambda$58(Ref.ObjectRef.this, booleanRef3, booleanRef4, booleanRef, this, booleanRef2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AutoSendChannel2$lambda$58(Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Ref.BooleanRef booleanRef3, ImageTextListActivity imageTextListActivity, Ref.BooleanRef booleanRef4) {
        final Ref.BooleanRef booleanRef5;
        final Ref.BooleanRef booleanRef6;
        List<Byte> mutableList;
        SendCore.INSTANCE.deleteAllData2(new SendResultCallback() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$AutoSendChannel2$1$1
            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onError(int result) {
            }

            @Override // com.wifiled.ipixels.core.send.SendResultCallback
            public void onResult(byte[] result) {
                Intrinsics.checkNotNullParameter(result, "result");
            }
        });
        Thread.sleep(200L);
        final Ref.IntRef intRef = new Ref.IntRef();
        if (AppConfig.INSTANCE.getMcu() > 4) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            arrayList.add((byte) 8);
            arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            int i = 1;
            for (ChannelListItem channelListItem : (List) objectRef.element) {
                if (channelListItem instanceof ChannelListItem.TextEmojView) {
                    intRef.element++;
                    ChannelListItem.TextEmojView textEmojView = (ChannelListItem.TextEmojView) channelListItem;
                    textEmojView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(textEmojView.getSerialNum())));
                } else if (channelListItem instanceof ChannelListItem.ImagView) {
                    intRef.element++;
                    ChannelListItem.ImagView imagView = (ChannelListItem.ImagView) channelListItem;
                    imagView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(imagView.getSerialNum())));
                } else if (channelListItem instanceof ChannelListItem.GiftView) {
                    intRef.element++;
                    ChannelListItem.GiftView giftView = (ChannelListItem.GiftView) channelListItem;
                    giftView.setSerialNum(String.valueOf(i));
                    arrayList.add(Byte.valueOf(Byte.parseByte(giftView.getSerialNum())));
                }
                i++;
            }
            arrayList.set(4, Byte.valueOf((byte) intRef.element));
            arrayList.set(0, Byte.valueOf((byte) arrayList.size()));
            SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList), new ImageTextListActivity$AutoSendChannel2$1$2(imageTextListActivity));
            Thread.sleep(500L);
        } else {
            intRef.element = ((List) objectRef.element).size();
        }
        Iterator it = ((List) objectRef.element).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (booleanRef.element) {
                booleanRef.element = false;
                break;
            }
            if (booleanRef2.element) {
                booleanRef2.element = false;
                break;
            }
            if (booleanRef3.element) {
                ThreadUtils.delay(300L);
                ChannelListItem channelListItem2 = (ChannelListItem) it.next();
                if (channelListItem2 instanceof ChannelListItem.TextEmojView) {
                    imageTextListActivity.sendData.clear();
                    ChannelListItem.TextEmojView textEmojView2 = (ChannelListItem.TextEmojView) channelListItem2;
                    imageTextListActivity.sendData.addAll(ArraysKt.toMutableList(textEmojView2.getArrTextData()));
                    byte[] plus = ArraysKt.plus(new byte[0], Byte.parseByte(textEmojView2.getSerialNum()));
                    Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(textEmojView2.getSerialNum())));
                    booleanRef5 = booleanRef2;
                    final Ref.BooleanRef booleanRef7 = booleanRef3;
                    final ImageTextListActivity imageTextListActivity2 = imageTextListActivity;
                    final Ref.BooleanRef booleanRef8 = booleanRef4;
                    booleanRef6 = booleanRef7;
                    imageTextListActivity2.sendTextData2(plus, CollectionsKt.toByteArray(imageTextListActivity.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit AutoSendChannel2$lambda$58$lambda$38;
                            AutoSendChannel2$lambda$58$lambda$38 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38(Ref.BooleanRef.this, imageTextListActivity2, intRef, booleanRef8, booleanRef5, (SendCore.CallbackBuilder) obj);
                            return AutoSendChannel2$lambda$58$lambda$38;
                        }
                    }, Byte.parseByte(textEmojView2.getSerialNum()));
                    imageTextListActivity = imageTextListActivity2;
                } else {
                    booleanRef5 = booleanRef2;
                    booleanRef6 = booleanRef3;
                    final ImageTextListActivity imageTextListActivity3 = imageTextListActivity;
                    final Ref.BooleanRef booleanRef9 = booleanRef4;
                    if (channelListItem2 instanceof ChannelListItem.ImagView) {
                        imageTextListActivity3.sendData.clear();
                        ChannelListItem.ImagView imagView2 = (ChannelListItem.ImagView) channelListItem2;
                        imageTextListActivity3.sendData.addAll(ArraysKt.toMutableList(imagView2.getArrImagData()));
                        byte[] plus2 = ArraysKt.plus(new byte[0], Byte.parseByte(imagView2.getSerialNum()));
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(imagView2.getSerialNum())));
                        imageTextListActivity3.sendImagData2(plus2, CollectionsKt.toByteArray(imageTextListActivity3.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit AutoSendChannel2$lambda$58$lambda$46;
                                AutoSendChannel2$lambda$58$lambda$46 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46(Ref.BooleanRef.this, imageTextListActivity3, intRef, booleanRef9, booleanRef5, (SendCore.CallbackBuilder) obj);
                                return AutoSendChannel2$lambda$58$lambda$46;
                            }
                        }, Byte.parseByte(imagView2.getSerialNum()));
                    } else if (channelListItem2 instanceof ChannelListItem.GiftView) {
                        imageTextListActivity3.sendData.clear();
                        ChannelListItem.GiftView giftView2 = (ChannelListItem.GiftView) channelListItem2;
                        byte[] arrGifData = giftView2.getArrGifData();
                        if (arrGifData != null && (mutableList = ArraysKt.toMutableList(arrGifData)) != null) {
                            imageTextListActivity3.sendData.addAll(mutableList);
                        }
                        byte[] plus3 = ArraysKt.plus(new byte[0], Byte.parseByte(giftView2.getSerialNum()));
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) Byte.parseByte(giftView2.getSerialNum())));
                        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
                            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda22
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    Unit AutoSendChannel2$lambda$58$lambda$48;
                                    AutoSendChannel2$lambda$58$lambda$48 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$48(ImageTextListActivity.this);
                                    return AutoSendChannel2$lambda$58$lambda$48;
                                }
                            });
                        } else {
                            SendCore.INSTANCE.sendChannelGifData2(plus3, CollectionsKt.toByteArray(imageTextListActivity3.sendData), new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda33
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    Unit AutoSendChannel2$lambda$58$lambda$57;
                                    AutoSendChannel2$lambda$58$lambda$57 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57(Ref.BooleanRef.this, imageTextListActivity3, intRef, booleanRef9, booleanRef5, (SendCore.CallbackBuilder) obj);
                                    return AutoSendChannel2$lambda$58$lambda$57;
                                }
                            }, Byte.parseByte(giftView2.getSerialNum()));
                            booleanRef3 = booleanRef6;
                            imageTextListActivity = imageTextListActivity3;
                            booleanRef4 = booleanRef9;
                            booleanRef2 = booleanRef5;
                        }
                    }
                    imageTextListActivity = imageTextListActivity3;
                    booleanRef4 = booleanRef9;
                }
                booleanRef3 = booleanRef6;
                booleanRef2 = booleanRef5;
            }
        }
        Ref.BooleanRef booleanRef10 = booleanRef3;
        LogUtils.e("#1.0# run here isSendOver: " + booleanRef10.element + " iSelTotalCount：" + intRef.element);
        if (booleanRef10.element && AppConfig.INSTANCE.isCancel()) {
            AppConfig.INSTANCE.setCancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendTextData2) {
        Intrinsics.checkNotNullParameter(sendTextData2, "$this$sendTextData2");
        sendTextData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$38$lambda$31;
                AutoSendChannel2$lambda$58$lambda$38$lambda$31 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$31(Ref.BooleanRef.this);
                return AutoSendChannel2$lambda$58$lambda$38$lambda$31;
            }
        });
        sendTextData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$38$lambda$32;
                AutoSendChannel2$lambda$58$lambda$38$lambda$32 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$32(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel2$lambda$58$lambda$38$lambda$32;
            }
        });
        sendTextData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$38$lambda$34;
                AutoSendChannel2$lambda$58$lambda$38$lambda$34 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$34(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel2$lambda$58$lambda$38$lambda$34;
            }
        });
        sendTextData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37;
                AutoSendChannel2$lambda$58$lambda$38$lambda$37 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$37(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel2$lambda$58$lambda$38$lambda$37;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$31(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$32(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$34(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "TextEmojView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda48
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel2$lambda$58$lambda$38$lambda$34$lambda$33;
                        AutoSendChannel2$lambda$58$lambda$38$lambda$34$lambda$33 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$34$lambda$33(ImageTextListActivity.this);
                        return AutoSendChannel2$lambda$58$lambda$38$lambda$34$lambda$33;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$34$lambda$33(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$35;
                    AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$35 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$35(ImageTextListActivity.this);
                    return AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$35;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$36;
                    AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$36 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$36(ImageTextListActivity.this);
                    return AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$36;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$35(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$38$lambda$37$lambda$36(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendImagData2) {
        Intrinsics.checkNotNullParameter(sendImagData2, "$this$sendImagData2");
        sendImagData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$46$lambda$39;
                AutoSendChannel2$lambda$58$lambda$46$lambda$39 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$39(Ref.BooleanRef.this);
                return AutoSendChannel2$lambda$58$lambda$46$lambda$39;
            }
        });
        sendImagData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$46$lambda$40;
                AutoSendChannel2$lambda$58$lambda$46$lambda$40 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$40(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel2$lambda$58$lambda$46$lambda$40;
            }
        });
        sendImagData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$46$lambda$42;
                AutoSendChannel2$lambda$58$lambda$46$lambda$42 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$42(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel2$lambda$58$lambda$46$lambda$42;
            }
        });
        sendImagData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45;
                AutoSendChannel2$lambda$58$lambda$46$lambda$45 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$45(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel2$lambda$58$lambda$46$lambda$45;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$39(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$40(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$42(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "ImagView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel2$lambda$58$lambda$46$lambda$42$lambda$41;
                        AutoSendChannel2$lambda$58$lambda$46$lambda$42$lambda$41 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$42$lambda$41(ImageTextListActivity.this);
                        return AutoSendChannel2$lambda$58$lambda$46$lambda$42$lambda$41;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$42$lambda$41(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$43;
                    AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$43 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$43(ImageTextListActivity.this);
                    return AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$43;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$44;
                    AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$44 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$44(ImageTextListActivity.this);
                    return AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$44;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$43(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$46$lambda$45$lambda$44(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$48(ImageTextListActivity imageTextListActivity) {
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail) + "100114");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57(final Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendChannelGifData2) {
        Intrinsics.checkNotNullParameter(sendChannelGifData2, "$this$sendChannelGifData2");
        sendChannelGifData2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$49;
                AutoSendChannel2$lambda$58$lambda$57$lambda$49 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$49(Ref.BooleanRef.this);
                return AutoSendChannel2$lambda$58$lambda$57$lambda$49;
            }
        });
        sendChannelGifData2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda57
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$50;
                AutoSendChannel2$lambda$58$lambda$57$lambda$50 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$50(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel2$lambda$58$lambda$57$lambda$50;
            }
        });
        sendChannelGifData2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$52;
                AutoSendChannel2$lambda$58$lambda$57$lambda$52 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$52(Ref.BooleanRef.this, intRef, booleanRef2, imageTextListActivity);
                return AutoSendChannel2$lambda$58$lambda$57$lambda$52;
            }
        });
        sendChannelGifData2.onError(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$54;
                AutoSendChannel2$lambda$58$lambda$57$lambda$54 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$54(ImageTextListActivity.this, ((Integer) obj).intValue());
                return AutoSendChannel2$lambda$58$lambda$57$lambda$54;
            }
        });
        sendChannelGifData2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$56;
                AutoSendChannel2$lambda$58$lambda$57$lambda$56 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$56(Ref.BooleanRef.this, imageTextListActivity, (byte[]) obj);
                return AutoSendChannel2$lambda$58$lambda$57$lambda$56;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$49(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$50(ImageTextListActivity imageTextListActivity, int i) {
        if (i != imageTextListActivity.tProcess) {
            imageTextListActivity.tProcess = i;
            if (i > 100) {
                imageTextListActivity.tProcess = 100;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$52(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final ImageTextListActivity imageTextListActivity) {
        Log.v("ruis", "GiftView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit AutoSendChannel2$lambda$58$lambda$57$lambda$52$lambda$51;
                        AutoSendChannel2$lambda$58$lambda$57$lambda$52$lambda$51 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$52$lambda$51(ImageTextListActivity.this);
                        return AutoSendChannel2$lambda$58$lambda$57$lambda$52$lambda$51;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$52$lambda$51(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_suc));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$54(final ImageTextListActivity imageTextListActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit AutoSendChannel2$lambda$58$lambda$57$lambda$54$lambda$53;
                AutoSendChannel2$lambda$58$lambda$57$lambda$54$lambda$53 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$54$lambda$53(ImageTextListActivity.this, i);
                return AutoSendChannel2$lambda$58$lambda$57$lambda$54$lambda$53;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$54$lambda$53(ImageTextListActivity imageTextListActivity, int i) {
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_send_fail) + i);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$56(Ref.BooleanRef booleanRef, final ImageTextListActivity imageTextListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it[4] == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit AutoSendChannel2$lambda$58$lambda$57$lambda$56$lambda$55;
                    AutoSendChannel2$lambda$58$lambda$57$lambda$56$lambda$55 = ImageTextListActivity.AutoSendChannel2$lambda$58$lambda$57$lambda$56$lambda$55(ImageTextListActivity.this);
                    return AutoSendChannel2$lambda$58$lambda$57$lambda$56$lambda$55;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AutoSendChannel2$lambda$58$lambda$57$lambda$56$lambda$55(ImageTextListActivity imageTextListActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) imageTextListActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(imageTextListActivity.getString(R.string.channel_tip_low_space));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendTextData$default(ImageTextListActivity imageTextListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        imageTextListActivity.sendTextData(bArr, bArr2, function1, b);
    }

    private final void sendTextData(byte[] arrText, byte[] arrTotal, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ImageTextListActivity sendTextData");
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            return;
        }
        SendCore.INSTANCE.sendTextDataInvokFun(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda67
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$65;
                sendTextData$lambda$65 = ImageTextListActivity.sendTextData$lambda$65(SendCore.CallbackBuilder.this, this, (SendCore.CallbackBuilder) obj);
                return sendTextData$lambda$65;
            }
        }, isBulkSend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65(final SendCore.CallbackBuilder callbackBuilder, final ImageTextListActivity imageTextListActivity, SendCore.CallbackBuilder sendTextDataInvokFun) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun, "$this$sendTextDataInvokFun");
        sendTextDataInvokFun.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$65$lambda$59;
                sendTextData$lambda$65$lambda$59 = ImageTextListActivity.sendTextData$lambda$65$lambda$59(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$65$lambda$59;
            }
        });
        sendTextDataInvokFun.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$65$lambda$60;
                sendTextData$lambda$65$lambda$60 = ImageTextListActivity.sendTextData$lambda$65$lambda$60(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData$lambda$65$lambda$60;
            }
        });
        sendTextDataInvokFun.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda66
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$65$lambda$61;
                sendTextData$lambda$65$lambda$61 = ImageTextListActivity.sendTextData$lambda$65$lambda$61(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$65$lambda$61;
            }
        });
        sendTextDataInvokFun.onError(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda68
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$65$lambda$62;
                sendTextData$lambda$65$lambda$62 = ImageTextListActivity.sendTextData$lambda$65$lambda$62(((Integer) obj).intValue());
                return sendTextData$lambda$65$lambda$62;
            }
        });
        sendTextDataInvokFun.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda69
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$65$lambda$64;
                sendTextData$lambda$65$lambda$64 = ImageTextListActivity.sendTextData$lambda$65$lambda$64(SendCore.CallbackBuilder.this, imageTextListActivity, (byte[]) obj);
                return sendTextData$lambda$65$lambda$64;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$59(SendCore.CallbackBuilder callbackBuilder) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$60(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$61(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$62(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$64(SendCore.CallbackBuilder callbackBuilder, final ImageTextListActivity imageTextListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda63
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendTextData$lambda$65$lambda$64$lambda$63;
                    sendTextData$lambda$65$lambda$64$lambda$63 = ImageTextListActivity.sendTextData$lambda$65$lambda$64$lambda$63(ImageTextListActivity.this);
                    return sendTextData$lambda$65$lambda$64$lambda$63;
                }
            });
        } else if (b == 3) {
            LogUtils.e(">>>[sendTextData onResult]:保存文件成功 ");
            Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
            if (completeAction$app_googleRelease != null) {
                completeAction$app_googleRelease.invoke();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$65$lambda$64$lambda$63(ImageTextListActivity imageTextListActivity) {
        ToastUtil.show(String.valueOf(imageTextListActivity.getString(R.string.send_failed_deivce_space)));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendTextData2$default(ImageTextListActivity imageTextListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        imageTextListActivity.sendTextData2(bArr, bArr2, function1, b);
    }

    private final void sendTextData2(byte[] arrText, byte[] arrTotal, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ImageTextListActivity sendTextData");
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
            return;
        }
        SendCore.INSTANCE.sendTextDataInvokFun2(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda62
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$72;
                sendTextData2$lambda$72 = ImageTextListActivity.sendTextData2$lambda$72(SendCore.CallbackBuilder.this, this, (SendCore.CallbackBuilder) obj);
                return sendTextData2$lambda$72;
            }
        }, isBulkSend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72(final SendCore.CallbackBuilder callbackBuilder, final ImageTextListActivity imageTextListActivity, SendCore.CallbackBuilder sendTextDataInvokFun2) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun2, "$this$sendTextDataInvokFun2");
        sendTextDataInvokFun2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData2$lambda$72$lambda$66;
                sendTextData2$lambda$72$lambda$66 = ImageTextListActivity.sendTextData2$lambda$72$lambda$66(SendCore.CallbackBuilder.this);
                return sendTextData2$lambda$72$lambda$66;
            }
        });
        sendTextDataInvokFun2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$72$lambda$67;
                sendTextData2$lambda$72$lambda$67 = ImageTextListActivity.sendTextData2$lambda$72$lambda$67(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData2$lambda$72$lambda$67;
            }
        });
        sendTextDataInvokFun2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData2$lambda$72$lambda$68;
                sendTextData2$lambda$72$lambda$68 = ImageTextListActivity.sendTextData2$lambda$72$lambda$68(SendCore.CallbackBuilder.this);
                return sendTextData2$lambda$72$lambda$68;
            }
        });
        sendTextDataInvokFun2.onError(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$72$lambda$69;
                sendTextData2$lambda$72$lambda$69 = ImageTextListActivity.sendTextData2$lambda$72$lambda$69(((Integer) obj).intValue());
                return sendTextData2$lambda$72$lambda$69;
            }
        });
        sendTextDataInvokFun2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData2$lambda$72$lambda$71;
                sendTextData2$lambda$72$lambda$71 = ImageTextListActivity.sendTextData2$lambda$72$lambda$71(SendCore.CallbackBuilder.this, imageTextListActivity, (byte[]) obj);
                return sendTextData2$lambda$72$lambda$71;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$66(SendCore.CallbackBuilder callbackBuilder) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$67(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$68(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$69(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$71(SendCore.CallbackBuilder callbackBuilder, final ImageTextListActivity imageTextListActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendTextData2$lambda$72$lambda$71$lambda$70;
                    sendTextData2$lambda$72$lambda$71$lambda$70 = ImageTextListActivity.sendTextData2$lambda$72$lambda$71$lambda$70(ImageTextListActivity.this);
                    return sendTextData2$lambda$72$lambda$71$lambda$70;
                }
            });
        } else if (b == 3) {
            LogUtils.e(">>>[sendTextData onResult]:保存文件成功 ");
            Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
            if (completeAction$app_googleRelease != null) {
                completeAction$app_googleRelease.invoke();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData2$lambda$72$lambda$71$lambda$70(ImageTextListActivity imageTextListActivity) {
        ToastUtil.show(String.valueOf(imageTextListActivity.getString(R.string.send_failed_deivce_space)));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendImagData$default(ImageTextListActivity imageTextListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        imageTextListActivity.sendImagData(bArr, bArr2, function1, b);
    }

    private final void sendImagData(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ImageTextListActivity sendImagData");
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendChannelImageData(true, serial, data, callbackBuilder, isBulkSend);
    }

    static /* synthetic */ void sendImagData2$default(ImageTextListActivity imageTextListActivity, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        imageTextListActivity.sendImagData2(bArr, bArr2, function1, b);
    }

    private final void sendImagData2(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        LogUtils.file("ImageTextListActivity sendImagData");
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendChannelImageData2(true, serial, data, callbackBuilder, isBulkSend);
    }
}
