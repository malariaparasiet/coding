package com.wifiled.ipixels.ui.control;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
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
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.BaseSend;
import com.wifiled.ipixels.databinding.ActivityBusinessRemoteControlBinding;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.ui.diy.DiyImageFun;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.ui.text.TextActivity;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BusinessRemoteControlActivity.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u0002H\u0016J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020 H\u0016J\b\u0010$\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020 H\u0002J\b\u0010'\u001a\u00020 H\u0002J\u001a\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u000f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020 H\u0002J\u0010\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020 H\u0002J\b\u00102\u001a\u00020 H\u0002J\b\u0010;\u001a\u00020 H\u0014J\b\u0010<\u001a\u00020 H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001f\u00103\u001a\u0010\u0012\f\u0012\n 6*\u0004\u0018\u0001050504¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001f\u00109\u001a\u0010\u0012\f\u0012\n 6*\u0004\u0018\u0001050504¢\u0006\b\n\u0000\u001a\u0004\b:\u00108¨\u0006="}, d2 = {"Lcom/wifiled/ipixels/ui/control/BusinessRemoteControlActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/control/BusinessControlModel;", "Lcom/wifiled/ipixels/databinding/ActivityBusinessRemoteControlBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mIsEdit", "", "misDataChange", "mRemoteDataEn", "", "Lcom/wifiled/ipixels/ui/control/BusinessRemoteData;", "mRemoteDataCn", "mSelectPosition", "", "mLanguage", "", "mBusinessRemoteAdapter", "Lcom/wifiled/ipixels/ui/control/BusinessRemoteAdapter;", "mDialogData", "", "mDialog", "Landroid/app/Dialog;", "mDialogRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mDialogAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "registorUIChangeLiveDataCallBack", "initData", "resetData", "initToolBar", "initList", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "showTipDialog", "showTipDialog2", "onClick", "v", "Landroid/view/View;", "exitEditStatus", "initDialog", "mTextResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getMTextResult", "()Landroidx/activity/result/ActivityResultLauncher;", "mGalleryResult", "getMGalleryResult", "onResume", "onDestroy", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BusinessRemoteControlActivity extends UiBaseActivity<BusinessControlModel, ActivityBusinessRemoteControlBinding> implements View.OnClickListener {
    private BusinessRemoteAdapter mBusinessRemoteAdapter;
    private Dialog mDialog;
    private IosDialogStyleAdapter<Object> mDialogAdapter;
    private final List<String> mDialogData;
    private RecyclerView mDialogRecyclerView;
    private final ActivityResultLauncher<Intent> mGalleryResult;
    private boolean mIsEdit;
    private final byte mLanguage;
    private final ActivityResultLauncher<Intent> mTextResult;
    private boolean misDataChange;
    private List<BusinessRemoteData> mRemoteDataEn = new ArrayList();
    private List<BusinessRemoteData> mRemoteDataCn = new ArrayList();
    private int mSelectPosition = -1;

    public BusinessRemoteControlActivity() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        this.mLanguage = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null) ? (byte) 1 : (byte) 0;
        this.mDialogData = new ArrayList();
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                BusinessRemoteControlActivity.mTextResult$lambda$13(BusinessRemoteControlActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.mTextResult = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda12
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                BusinessRemoteControlActivity.mGalleryResult$lambda$14(BusinessRemoteControlActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.mGalleryResult = registerForActivityResult2;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityBusinessRemoteControlBinding getViewBinding() {
        ActivityBusinessRemoteControlBinding inflate = ActivityBusinessRemoteControlBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public BusinessControlModel initViewModel() {
        return (BusinessControlModel) new ViewModelProvider(this).get(BusinessControlModel.class);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        LogUtils.file("BusinessRemoteControlActivity initView");
        initToolBar();
        initList();
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public void registorUIChangeLiveDataCallBack() {
        super.registorUIChangeLiveDataCallBack();
        BusinessRemoteControlActivity businessRemoteControlActivity = this;
        getViewModel().getCustomDialogSuccessEvent().observe(businessRemoteControlActivity, new Observer() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BusinessRemoteControlActivity.registorUIChangeLiveDataCallBack$lambda$0(BusinessRemoteControlActivity.this, (String) obj);
            }
        });
        getViewModel().getCustomDialogErrorEvent().observe(businessRemoteControlActivity, new Observer() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BusinessRemoteControlActivity.registorUIChangeLiveDataCallBack$lambda$1(BusinessRemoteControlActivity.this, (Integer) obj);
            }
        });
        getViewModel().getCustomGetCNDataEvent().observe(businessRemoteControlActivity, new Observer() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BusinessRemoteControlActivity.registorUIChangeLiveDataCallBack$lambda$2(BusinessRemoteControlActivity.this, (List) obj);
            }
        });
        getViewModel().getCustomGetENDataEvent().observe(businessRemoteControlActivity, new Observer() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BusinessRemoteControlActivity.registorUIChangeLiveDataCallBack$lambda$3(BusinessRemoteControlActivity.this, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registorUIChangeLiveDataCallBack$lambda$0(BusinessRemoteControlActivity businessRemoteControlActivity, String str) {
        ToastUtil.show(businessRemoteControlActivity.getString(R.string.msg_send_suc));
        UtilsExtensionKt.showLoadingDialog$default((Activity) businessRemoteControlActivity, false, (String) null, false, 6, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registorUIChangeLiveDataCallBack$lambda$1(BusinessRemoteControlActivity businessRemoteControlActivity, Integer num) {
        if (num != null && num.intValue() == 1) {
            ToastUtil.show(businessRemoteControlActivity.getString(R.string.msg_send_fail));
            UtilsExtensionKt.showLoadingDialog$default((Activity) businessRemoteControlActivity, false, (String) null, false, 6, (Object) null);
        } else if (num != null && num.intValue() == 2) {
            ToastUtil.show(businessRemoteControlActivity.getString(R.string.channel_tip_low_space));
            UtilsExtensionKt.showLoadingDialog$default((Activity) businessRemoteControlActivity, false, (String) null, false, 6, (Object) null);
        } else if (num != null && num.intValue() == 3) {
            ToastUtil.show(businessRemoteControlActivity.getString(R.string.msg_data_error));
            UtilsExtensionKt.showLoadingDialog$default((Activity) businessRemoteControlActivity, false, (String) null, false, 6, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registorUIChangeLiveDataCallBack$lambda$2(BusinessRemoteControlActivity businessRemoteControlActivity, List list) {
        LogUtils.vTag("ruis", "mRemoteDataCn ----" + businessRemoteControlActivity.mRemoteDataCn.size());
        Intrinsics.checkNotNull(list);
        businessRemoteControlActivity.mRemoteDataCn = list;
        BusinessRemoteAdapter businessRemoteAdapter = businessRemoteControlActivity.mBusinessRemoteAdapter;
        if (businessRemoteAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter = null;
        }
        businessRemoteAdapter.setList(businessRemoteControlActivity.mRemoteDataCn);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registorUIChangeLiveDataCallBack$lambda$3(BusinessRemoteControlActivity businessRemoteControlActivity, List list) {
        Intrinsics.checkNotNull(list);
        businessRemoteControlActivity.mRemoteDataEn = list;
        BusinessRemoteAdapter businessRemoteAdapter = businessRemoteControlActivity.mBusinessRemoteAdapter;
        if (businessRemoteAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter = null;
        }
        businessRemoteAdapter.setList(businessRemoteControlActivity.mRemoteDataEn);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
        super.initData();
        LogUtils.vTag("ruis", "AppConfig.ledType---" + AppConfig.INSTANCE.getLedType());
        getBinding().customImageView.setImageResource(AppConfig.INSTANCE.getBledOn() ? R.mipmap.r_c_on : R.mipmap.r_c_off);
        int ledType = AppConfig.INSTANCE.getLedType();
        if (ledType == 1) {
            if (this.mLanguage == 1) {
                getViewModel().getCnData(this, "16x96");
                return;
            } else {
                getViewModel().getEnData(this, "16x96");
                return;
            }
        }
        switch (ledType) {
            case 7:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "16x144");
                    break;
                } else {
                    getViewModel().getEnData(this, "16x144");
                    break;
                }
            case 8:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "16x192");
                    break;
                } else {
                    getViewModel().getEnData(this, "16x192");
                    break;
                }
            case 9:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "24x48");
                    break;
                } else {
                    getViewModel().getEnData(this, "24x48");
                    break;
                }
            case 10:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "32x64");
                    break;
                } else {
                    getViewModel().getEnData(this, "32x64");
                    break;
                }
            case 11:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "32x96");
                    break;
                } else {
                    getViewModel().getEnData(this, "32x96");
                    break;
                }
            case 12:
                if (this.mLanguage == 1) {
                    getViewModel().getCnData(this, "32x128");
                    break;
                } else {
                    getViewModel().getEnData(this, "32x128");
                    break;
                }
            default:
                switch (ledType) {
                    case 14:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x160");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x160");
                            break;
                        }
                    case 15:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x192");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x192");
                            break;
                        }
                    case 16:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x256");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x256");
                            break;
                        }
                    case 17:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x320");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x320");
                            break;
                        }
                    case 18:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x384");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x384");
                            break;
                        }
                    case 19:
                        if (this.mLanguage == 1) {
                            getViewModel().getCnData(this, "32x448");
                            break;
                        } else {
                            getViewModel().getEnData(this, "32x448");
                            break;
                        }
                }
        }
    }

    private final void resetData() {
        LogUtils.file("BusinessRemoteControlActivity resetData");
        int ledType = AppConfig.INSTANCE.getLedType();
        if (ledType == 1) {
            if (this.mLanguage == 1) {
                this.mRemoteDataCn.clear();
                getViewModel().resetCnData(this, "16x96");
                return;
            } else {
                this.mRemoteDataEn.clear();
                getViewModel().resetEnData(this, "16x96");
                return;
            }
        }
        switch (ledType) {
            case 7:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "16x144");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "16x144");
                    break;
                }
            case 8:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "16x192");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "16x192");
                    break;
                }
            case 9:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "24x48");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "24x48");
                    break;
                }
            case 10:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "32x64");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "32x64");
                    break;
                }
            case 11:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "32x96");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "32x96");
                    break;
                }
            case 12:
                if (this.mLanguage == 1) {
                    this.mRemoteDataCn.clear();
                    getViewModel().resetCnData(this, "32x128");
                    break;
                } else {
                    this.mRemoteDataEn.clear();
                    getViewModel().resetEnData(this, "32x128");
                    break;
                }
            default:
                switch (ledType) {
                    case 14:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x160");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x160");
                            break;
                        }
                    case 15:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x192");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x192");
                            break;
                        }
                    case 16:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x256");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x256");
                            break;
                        }
                    case 17:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x320");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x320");
                            break;
                        }
                    case 18:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x384");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x384");
                            break;
                        }
                    case 19:
                        if (this.mLanguage == 1) {
                            this.mRemoteDataCn.clear();
                            getViewModel().resetCnData(this, "32x448");
                            break;
                        } else {
                            this.mRemoteDataEn.clear();
                            getViewModel().resetEnData(this, "32x448");
                            break;
                        }
                }
        }
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.title_control));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
        BusinessRemoteControlActivity businessRemoteControlActivity = this;
        getBinding().ivBack.setOnClickListener(businessRemoteControlActivity);
        getBinding().ivRight.setOnClickListener(businessRemoteControlActivity);
        getBinding().ivSend.setOnClickListener(businessRemoteControlActivity);
        getBinding().ivReset.setOnClickListener(businessRemoteControlActivity);
        getBinding().customImageView.setOnClickListener(businessRemoteControlActivity);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView = getBinding().customImageView;
        Intrinsics.checkNotNullExpressionValue(customImageView, "customImageView");
        companion.attachViewOnTouchListener(customImageView);
    }

    private final void initList() {
        int ledType = AppConfig.INSTANCE.getLedType();
        int i = R.layout.item_rc_1696_business;
        if (ledType != 1) {
            switch (ledType) {
                case 7:
                    i = R.layout.item_rc_16144_business;
                    break;
                case 8:
                    i = R.layout.item_rc_16192_business;
                    break;
                case 9:
                case 10:
                    i = R.layout.item_rc_2_1_business;
                    break;
                case 11:
                    i = R.layout.item_rc_3_1_business;
                    break;
                default:
                    switch (ledType) {
                        case 14:
                            i = R.layout.item_rc_5_1_business;
                            break;
                        case 16:
                            i = R.layout.item_rc_8_1_business;
                            break;
                        case 17:
                            i = R.layout.item_rc_10_1_business;
                            break;
                        case 18:
                            i = R.layout.item_rc_12_1_business;
                            break;
                        case 19:
                            i = R.layout.item_rc_14_1_business;
                            break;
                    }
            }
        }
        this.mBusinessRemoteAdapter = new BusinessRemoteAdapter(i);
        if (AppConfig.INSTANCE.getLedType() == 9 || AppConfig.INSTANCE.getLedType() == 10 || AppConfig.INSTANCE.getLedType() == 11) {
            getBinding().localRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            getBinding().localRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        }
        RecyclerView recyclerView = getBinding().localRecyclerview;
        BusinessRemoteAdapter businessRemoteAdapter = this.mBusinessRemoteAdapter;
        BusinessRemoteAdapter businessRemoteAdapter2 = null;
        if (businessRemoteAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter = null;
        }
        recyclerView.setAdapter(businessRemoteAdapter);
        BusinessRemoteAdapter businessRemoteAdapter3 = this.mBusinessRemoteAdapter;
        if (businessRemoteAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
        } else {
            businessRemoteAdapter2 = businessRemoteAdapter3;
        }
        businessRemoteAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda10
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                BusinessRemoteControlActivity.initList$lambda$5(BusinessRemoteControlActivity.this, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initList$lambda$5(final BusinessRemoteControlActivity businessRemoteControlActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        LogUtils.file("BusinessRemoteControlActivity mBusinessRemoteAdapter.setOnItemClickListener mIsEdit=" + businessRemoteControlActivity.mIsEdit + " mSelectPosition=" + businessRemoteControlActivity.mSelectPosition + "  position=" + i);
        if (businessRemoteControlActivity.mIsEdit) {
            businessRemoteControlActivity.mSelectPosition = i;
            Dialog dialog = businessRemoteControlActivity.mDialog;
            if (dialog != null) {
                dialog.show();
                return;
            }
            return;
        }
        if (businessRemoteControlActivity.mSelectPosition != -1) {
            BusinessRemoteAdapter businessRemoteAdapter = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                businessRemoteAdapter = null;
            }
            businessRemoteAdapter.getData().get(businessRemoteControlActivity.mSelectPosition).setSelect(false);
        }
        businessRemoteControlActivity.mSelectPosition = i;
        BusinessRemoteAdapter businessRemoteAdapter2 = businessRemoteControlActivity.mBusinessRemoteAdapter;
        if (businessRemoteAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter2 = null;
        }
        businessRemoteAdapter2.getData().get(i).setSelect(true);
        businessRemoteControlActivity.getBinding().localRecyclerview.post(new Runnable() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BusinessRemoteControlActivity.initList$lambda$5$lambda$4(BusinessRemoteControlActivity.this);
            }
        });
        businessRemoteControlActivity.getBinding().customImageView.setImageResource(R.mipmap.r_c_on);
        SendCore.INSTANCE.sendCompat(new byte[]{6, 0, 7, ByteCompanionObject.MIN_VALUE, (byte) (i + 1), businessRemoteControlActivity.mLanguage}, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initList$lambda$5$lambda$4(BusinessRemoteControlActivity businessRemoteControlActivity) {
        BusinessRemoteAdapter businessRemoteAdapter = businessRemoteControlActivity.mBusinessRemoteAdapter;
        if (businessRemoteAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter = null;
        }
        businessRemoteAdapter.notifyDataSetChanged();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return false;
        }
        LogUtils.file("BusinessRemoteControlActivity onKeyDown misDataChange=" + this.misDataChange);
        if (this.misDataChange) {
            showTipDialog();
            return false;
        }
        finish();
        return false;
    }

    private final void showTipDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setTitle(getString(R.string.gps_tip));
        builder.setMessage(getString(R.string.remote_data_change_tip));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BusinessRemoteControlActivity.showTipDialog$lambda$6(BusinessRemoteControlActivity.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BusinessRemoteControlActivity.showTipDialog$lambda$7(BusinessRemoteControlActivity.this, dialogInterface, i);
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTipDialog$lambda$6(BusinessRemoteControlActivity businessRemoteControlActivity, DialogInterface dialogInterface, int i) {
        LogUtils.file("BusinessRemoteControlActivity showTipDialog ok");
        dialogInterface.dismiss();
        businessRemoteControlActivity.misDataChange = false;
        businessRemoteControlActivity.exitEditStatus();
        String string = businessRemoteControlActivity.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) businessRemoteControlActivity, false, string, false, 5, (Object) null);
        BusinessControlModel viewModel = businessRemoteControlActivity.getViewModel();
        BusinessRemoteAdapter businessRemoteAdapter = businessRemoteControlActivity.mBusinessRemoteAdapter;
        if (businessRemoteAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            businessRemoteAdapter = null;
        }
        viewModel.autoSendChannel(businessRemoteAdapter.getData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTipDialog$lambda$7(BusinessRemoteControlActivity businessRemoteControlActivity, DialogInterface dialogInterface, int i) {
        LogUtils.file("BusinessRemoteControlActivity showTipDialog cancel");
        dialogInterface.dismiss();
        businessRemoteControlActivity.finish();
    }

    private final void showTipDialog2() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setTitle(getString(R.string.gps_tip));
        builder.setMessage(getString(R.string.restore_default_resource));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BusinessRemoteControlActivity.showTipDialog2$lambda$8(BusinessRemoteControlActivity.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BusinessRemoteControlActivity.showTipDialog2$lambda$9(dialogInterface, i);
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTipDialog2$lambda$8(BusinessRemoteControlActivity businessRemoteControlActivity, DialogInterface dialogInterface, int i) {
        LogUtils.file("BusinessRemoteControlActivity showTipDialog2 ok");
        dialogInterface.dismiss();
        businessRemoteControlActivity.resetData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTipDialog2$lambda$9(DialogInterface dialogInterface, int i) {
        LogUtils.file("BusinessRemoteControlActivity showTipDialog2 cancel");
        dialogInterface.dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            LogUtils.file("BusinessRemoteControlActivity onClick binding.ivBack " + this.misDataChange);
            if (this.misDataChange) {
                showTipDialog();
                return;
            } else {
                finish();
                return;
            }
        }
        BusinessRemoteAdapter businessRemoteAdapter = null;
        if (Intrinsics.areEqual(v, getBinding().ivRight)) {
            LogUtils.file("BusinessRemoteControlActivity onClick binding.ivRight " + this.mIsEdit);
            if (this.mIsEdit) {
                exitEditStatus();
                BusinessRemoteAdapter businessRemoteAdapter2 = this.mBusinessRemoteAdapter;
                if (businessRemoteAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                } else {
                    businessRemoteAdapter = businessRemoteAdapter2;
                }
                businessRemoteAdapter.setEditStatus(false);
                return;
            }
            BusinessRemoteAdapter businessRemoteAdapter3 = this.mBusinessRemoteAdapter;
            if (businessRemoteAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                businessRemoteAdapter3 = null;
            }
            businessRemoteAdapter3.setEditStatus(true);
            if (this.mDialog == null) {
                initDialog();
            }
            if (this.mSelectPosition != -1) {
                BusinessRemoteAdapter businessRemoteAdapter4 = this.mBusinessRemoteAdapter;
                if (businessRemoteAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter4 = null;
                }
                businessRemoteAdapter4.getData().get(this.mSelectPosition).setSelect(false);
                BusinessRemoteAdapter businessRemoteAdapter5 = this.mBusinessRemoteAdapter;
                if (businessRemoteAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                } else {
                    businessRemoteAdapter = businessRemoteAdapter5;
                }
                businessRemoteAdapter.notifyItemChanged(this.mSelectPosition);
            }
            this.mIsEdit = true;
            getBinding().ivSend.setVisibility(8);
            getBinding().ivReset.setVisibility(8);
            getBinding().ivRight.setImageResource(R.drawable.remote_save);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivSend)) {
            LogUtils.file("BusinessRemoteControlActivity onClick binding.ivSend ");
            this.misDataChange = false;
            String string = getString(R.string.msg_sending);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, string, false, 5, (Object) null);
            BusinessControlModel viewModel = getViewModel();
            BusinessRemoteAdapter businessRemoteAdapter6 = this.mBusinessRemoteAdapter;
            if (businessRemoteAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            } else {
                businessRemoteAdapter = businessRemoteAdapter6;
            }
            viewModel.autoSendChannel(businessRemoteAdapter.getData());
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivReset)) {
            LogUtils.file("BusinessRemoteControlActivity onClick binding.ivReset ");
            showTipDialog2();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().customImageView)) {
            LogUtils.file("BusinessRemoteControlActivity onClick binding.customImageView ");
            if (ClickFilter.filter()) {
                ToastUtil.show(getString(R.string.show_no_click));
                return;
            }
            if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
                EventBus eventBus = EventBus.getDefault();
                byte[] bytes = "dev disconnect".getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                eventBus.post(new SendResultMsg(bytes));
                return;
            }
            AppConfig.INSTANCE.setBledOn(!AppConfig.INSTANCE.getBledOn());
            getBinding().customImageView.setImageResource(AppConfig.INSTANCE.getBledOn() ? R.mipmap.r_c_on : R.mipmap.r_c_off);
            BaseSend.sendLedOnOff$default(SendCore.INSTANCE, AppConfig.INSTANCE.getBledOn() ? 1 : 0, null, 2, null);
        }
    }

    private final void exitEditStatus() {
        LogUtils.file("BusinessRemoteControlActivity exitEditStatus ");
        if (this.mIsEdit) {
            getBinding().ivRight.setImageResource(R.drawable.text_image_edit2);
            getBinding().ivSend.setVisibility(0);
            getBinding().ivReset.setVisibility(0);
            this.mIsEdit = false;
            if (this.misDataChange) {
                BusinessRemoteAdapter businessRemoteAdapter = null;
                if (this.mLanguage == 1) {
                    BusinessRemoteControlActivity businessRemoteControlActivity = this;
                    String str = "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType();
                    BusinessRemoteAdapter businessRemoteAdapter2 = this.mBusinessRemoteAdapter;
                    if (businessRemoteAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    } else {
                        businessRemoteAdapter = businessRemoteAdapter2;
                    }
                    SPUtils.put(businessRemoteControlActivity, str, businessRemoteAdapter.getData());
                    return;
                }
                BusinessRemoteControlActivity businessRemoteControlActivity2 = this;
                String str2 = "remote_text_list_en_" + AppConfig.INSTANCE.getLedType();
                BusinessRemoteAdapter businessRemoteAdapter3 = this.mBusinessRemoteAdapter;
                if (businessRemoteAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                } else {
                    businessRemoteAdapter = businessRemoteAdapter3;
                }
                SPUtils.put(businessRemoteControlActivity2, str2, businessRemoteAdapter.getData());
            }
        }
    }

    private final void initDialog() {
        List<String> list = this.mDialogData;
        String string = getString(R.string.title_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        list.add(string);
        List<String> list2 = this.mDialogData;
        String string2 = getString(R.string.title_gallery);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        list2.add(string2);
        BusinessRemoteControlActivity businessRemoteControlActivity = this;
        this.mDialogAdapter = new IosDialogStyleAdapter<>(businessRemoteControlActivity, this.mDialogData);
        Dialog dialog = new Dialog(businessRemoteControlActivity, R.style.BottomDialogStyle);
        this.mDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(businessRemoteControlActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
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
        window.getAttributes().width = ScreenUtil.getScreenWidth(businessRemoteControlActivity);
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
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BusinessRemoteControlActivity.initDialog$lambda$10(BusinessRemoteControlActivity.this, view);
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
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BusinessRemoteControlActivity.initDialog$lambda$11(BusinessRemoteControlActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.control.BusinessRemoteControlActivity$$ExternalSyntheticLambda5
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                BusinessRemoteControlActivity.initDialog$lambda$12(BusinessRemoteControlActivity.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$10(BusinessRemoteControlActivity businessRemoteControlActivity, View view) {
        Dialog dialog = businessRemoteControlActivity.mDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$11(BusinessRemoteControlActivity businessRemoteControlActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = businessRemoteControlActivity.mDialogAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = businessRemoteControlActivity.mDialogAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDialogAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = businessRemoteControlActivity.mDialogRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$12(BusinessRemoteControlActivity businessRemoteControlActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("BusinessRemoteControlActivity mDialogAdapter.setOnItemClickListener position" + i + " ");
        if (i == 0) {
            Intent intent = new Intent(businessRemoteControlActivity, (Class<?>) TextActivity.class);
            intent.putExtra("fromType", 1001);
            businessRemoteControlActivity.mTextResult.launch(intent);
        } else if (i == 1) {
            Intent intent2 = new Intent(businessRemoteControlActivity, (Class<?>) GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("select_mode_by_selector", true);
            bundle.putBoolean("select_mode_by_image_text", true);
            bundle.putBoolean("from_it", true);
            intent2.putExtras(bundle);
            businessRemoteControlActivity.mGalleryResult.launch(intent2);
        }
        Dialog dialog = businessRemoteControlActivity.mDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    public final ActivityResultLauncher<Intent> getMTextResult() {
        return this.mTextResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mTextResult$lambda$13(BusinessRemoteControlActivity businessRemoteControlActivity, ActivityResult activityResult) {
        LogUtils.file("BusinessRemoteControlActivity mTextResult ");
        if (activityResult.getResultCode() == -1) {
            businessRemoteControlActivity.misDataChange = true;
            Intent data = activityResult.getData();
            BusinessRemoteAdapter businessRemoteAdapter = null;
            Serializable serializableExtra = data != null ? data.getSerializableExtra("textData") : null;
            BusinessRemoteAdapter businessRemoteAdapter2 = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                businessRemoteAdapter2 = null;
            }
            businessRemoteAdapter2.getData().get(businessRemoteControlActivity.mSelectPosition).setDefault(false);
            BusinessRemoteAdapter businessRemoteAdapter3 = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                businessRemoteAdapter3 = null;
            }
            businessRemoteAdapter3.getData().get(businessRemoteControlActivity.mSelectPosition).setEditResourceType(3);
            BusinessRemoteAdapter businessRemoteAdapter4 = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                businessRemoteAdapter4 = null;
            }
            BusinessRemoteData businessRemoteData = businessRemoteAdapter4.getData().get(businessRemoteControlActivity.mSelectPosition);
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView");
            businessRemoteData.setTextEmojiView((ChannelListItem.TextEmojView) serializableExtra);
            SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
            BusinessRemoteAdapter businessRemoteAdapter5 = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            } else {
                businessRemoteAdapter = businessRemoteAdapter5;
            }
            businessRemoteAdapter.notifyItemChanged(businessRemoteControlActivity.mSelectPosition);
        }
    }

    public final ActivityResultLauncher<Intent> getMGalleryResult() {
        return this.mGalleryResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mGalleryResult$lambda$14(BusinessRemoteControlActivity businessRemoteControlActivity, ActivityResult activityResult) {
        LogUtils.file("BusinessRemoteControlActivity mGalleryResult ");
        if (activityResult.getResultCode() == -1) {
            businessRemoteControlActivity.misDataChange = true;
            Intent data = activityResult.getData();
            BusinessRemoteAdapter businessRemoteAdapter = null;
            Integer valueOf = data != null ? Integer.valueOf(data.getIntExtra("sendType", 0)) : null;
            Intent data2 = activityResult.getData();
            byte[] byteArrayExtra = data2 != null ? data2.getByteArrayExtra("sendData") : null;
            if (valueOf != null && valueOf.intValue() == 1) {
                BusinessRemoteAdapter businessRemoteAdapter2 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter2 = null;
                }
                businessRemoteAdapter2.getData().get(businessRemoteControlActivity.mSelectPosition).setDefault(false);
                BusinessRemoteAdapter businessRemoteAdapter3 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter3 = null;
                }
                businessRemoteAdapter3.getData().get(businessRemoteControlActivity.mSelectPosition).setEditResourceType(1);
                BusinessRemoteAdapter businessRemoteAdapter4 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter4 = null;
                }
                businessRemoteAdapter4.getData().get(businessRemoteControlActivity.mSelectPosition).setEditByteData(byteArrayExtra);
            } else if (valueOf != null && valueOf.intValue() == 2) {
                BusinessRemoteAdapter businessRemoteAdapter5 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter5 = null;
                }
                businessRemoteAdapter5.getData().get(businessRemoteControlActivity.mSelectPosition).setDefault(false);
                BusinessRemoteAdapter businessRemoteAdapter6 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter6 = null;
                }
                businessRemoteAdapter6.getData().get(businessRemoteControlActivity.mSelectPosition).setEditResourceType(2);
                BusinessRemoteAdapter businessRemoteAdapter7 = businessRemoteControlActivity.mBusinessRemoteAdapter;
                if (businessRemoteAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
                    businessRemoteAdapter7 = null;
                }
                businessRemoteAdapter7.getData().get(businessRemoteControlActivity.mSelectPosition).setEditByteData(byteArrayExtra);
            } else {
                ToastUtil.show(businessRemoteControlActivity.getString(R.string.msg_send_fail));
            }
            BusinessRemoteAdapter businessRemoteAdapter8 = businessRemoteControlActivity.mBusinessRemoteAdapter;
            if (businessRemoteAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBusinessRemoteAdapter");
            } else {
                businessRemoteAdapter = businessRemoteAdapter8;
            }
            businessRemoteAdapter.notifyItemChanged(businessRemoteControlActivity.mSelectPosition);
        }
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        super.onDestroy();
    }
}
