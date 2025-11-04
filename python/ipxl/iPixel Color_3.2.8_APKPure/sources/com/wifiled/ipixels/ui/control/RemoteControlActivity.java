package com.wifiled.ipixels.ui.control;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.data.Record;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.BaseSend;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.utils.ClickFilter;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.ByteCompanionObject;
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

/* compiled from: RemoteControlActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\bH\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001aH\u0014J\b\u0010\u001c\u001a\u00020\u001aH\u0014J\b\u0010\u001d\u001a\u00020\u001aH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/ui/control/RemoteControlActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "localAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/baselib/data/Record;", "m_iSelect", "", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "strs", "", "mLanguage", "", "getMLanguage", "()B", "iv_back", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "tv_title", "Landroid/widget/TextView;", "customImageView", "localRecyclerview", "Landroidx/recyclerview/widget/RecyclerView;", "layoutId", "initView", "", "onDestroy", "onResume", "bindData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RemoteControlActivity extends BaseActivity {
    private CustomImageView customImageView;
    private CustomImageView iv_back;
    private RecyclerAdapter<Record> localAdapter;
    private RecyclerView localRecyclerview;
    private final byte mLanguage;
    private TextView tv_title;
    private int m_iSelect = -1;
    private CoroutineScope mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    private int[] strs = {R.string.emergency, R.string.high_beam, R.string.left, R.string.temporary_stop, R.string.right, R.string.smile, R.string.slow_down, R.string.keep_distance, R.string.baby, R.string.pedestrians, R.string.back_car, R.string.pregnant_woman, R.string.red_light, R.string.auto, R.string.rear_end, R.string.internship, R.string.no_honking, R.string.rainy, R.string.stop, R.string.danger};

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_remote_control;
    }

    public RemoteControlActivity() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        this.mLanguage = StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null) ? (byte) 1 : (byte) 0;
    }

    public final byte getMLanguage() {
        return this.mLanguage;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tv_title = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.customImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.customImageView = (CustomImageView) findViewById3;
        View findViewById4 = findViewById(R.id.localRecyclerview);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.localRecyclerview = (RecyclerView) findViewById4;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        CoroutineScopeKt.cancel$default(this.mScope, null, 1, null);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        if (AppConfig.INSTANCE.getMcu() == 8) {
            this.strs = new int[]{R.string.sprint, R.string.run_hom, R.string.left, R.string.temporary_stop, R.string.right, R.string.happy, R.string.split, R.string.carp, R.string.sunlight, R.string.exhausted, R.string.daze, R.string.busy, R.string.emo, R.string.random_thoughts, R.string.full_of_energy, R.string.daddy_loves_you, R.string.danger, R.string.study, R.string.mom_loves_you, R.string.surf};
        }
        super.onResume();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        String str;
        LogUtils.file("RemoteControlActivity bindData");
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        RecyclerAdapter<Record> recyclerAdapter = null;
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
        customImageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.RemoteControlActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoteControlActivity.bindData$lambda$0(RemoteControlActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        companion.attachViewOnTouchListener(customImageView4);
        TextView textView = this.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_control));
        CustomImageView customImageView5 = this.customImageView;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customImageView");
            customImageView5 = null;
        }
        customImageView5.setImageResource(AppConfig.INSTANCE.getBledOn() ? R.mipmap.r_c_on : R.mipmap.r_c_off);
        CustomImageView customImageView6 = this.customImageView;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customImageView");
            customImageView6 = null;
        }
        customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.control.RemoteControlActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoteControlActivity.bindData$lambda$1(RemoteControlActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        CustomImageView customImageView7 = this.customImageView;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customImageView");
            customImageView7 = null;
        }
        companion2.attachViewOnTouchListener(customImageView7);
        String str2 = AppConfig.INSTANCE.getMcu() == 8 ? "000401" : "YK";
        final ArrayList arrayList = new ArrayList();
        String str3 = "";
        if (Intrinsics.areEqual(str2, "000401")) {
            str = "";
        } else {
            String saveLanguage2 = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
            Intrinsics.checkNotNullExpressionValue(saveLanguage2, "getSaveLanguage(...)");
            str = StringsKt.contains$default((CharSequence) saveLanguage2, (CharSequence) "zh", false, 2, (Object) null) ? "cn" : "en";
        }
        if (Intrinsics.areEqual(AppConfig.INSTANCE.getCid(), "0001") && Intrinsics.areEqual(AppConfig.INSTANCE.getPid(), "09") && AppConfig.INSTANCE.getLedType() == 2) {
            str3 = "dis_Monest";
        }
        Map mapOf = MapsKt.mapOf(TuplesKt.to("appid", "137"), TuplesKt.to("sort", "1"), TuplesKt.to("page", "1"), TuplesKt.to("count", "20"), TuplesKt.to("category_name", "驾驶"), TuplesKt.to("type", "动画"), TuplesKt.to(AnnotatedPrivateKey.LABEL, str2), TuplesKt.to("width", String.valueOf(AppConfig.INSTANCE.getLedSize().get(0).intValue())), TuplesKt.to("height", String.valueOf(AppConfig.INSTANCE.getLedSize().get(1).intValue())), TuplesKt.to("file_lang", str), TuplesKt.to("filter_tags", str3));
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String generateRandomString$default = CryptographicParsingToolKt.generateRandomString$default(0, 1, null);
        NetWorkManager.INSTANCE.getStrRequest().newCloudMaterial(CryptographicParsingToolKt.generateSortedQueryString(mapOf, generateRandomString$default, valueOf), valueOf, generateRandomString$default, CryptographicParsingToolKt.generateSortedQueryAESString(mapOf)).enqueue(new RemoteControlActivity$bindData$3(arrayList, this));
        final Ref.IntRef intRef = new Ref.IntRef();
        int ledType = AppConfig.INSTANCE.getLedType();
        if (ledType == 1) {
            intRef.element = R.layout.item_rc_1696;
            RecyclerView recyclerView = this.localRecyclerview;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (ledType == 3) {
            intRef.element = R.layout.item_rc_1664;
            RecyclerView recyclerView2 = this.localRecyclerview;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
                recyclerView2 = null;
            }
            recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (ledType == 4) {
            intRef.element = R.layout.item_rc_1632;
            RecyclerView recyclerView3 = this.localRecyclerview;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
                recyclerView3 = null;
            }
            recyclerView3.setLayoutManager(new GridLayoutManager(this, 4));
        } else if (ledType == 5) {
            intRef.element = R.layout.item_rc_2064;
            RecyclerView recyclerView4 = this.localRecyclerview;
            if (recyclerView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
                recyclerView4 = null;
            }
            recyclerView4.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            intRef.element = R.layout.item_rc;
            RecyclerView recyclerView5 = this.localRecyclerview;
            if (recyclerView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
                recyclerView5 = null;
            }
            recyclerView5.setLayoutManager(new GridLayoutManager(this, 4));
        }
        this.localAdapter = new RecyclerAdapter<Record>(arrayList, intRef) { // from class: com.wifiled.ipixels.ui.control.RemoteControlActivity$bindData$4
            {
                RemoteControlActivity remoteControlActivity = RemoteControlActivity.this;
                int i = intRef.element;
            }

            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public void convert(RecyclerViewHolder holder, Record gif) {
                File file;
                int i;
                int[] iArr;
                int i2;
                CoroutineScope coroutineScope;
                Intrinsics.checkNotNullParameter(holder, "holder");
                if (gif != null) {
                    try {
                        file = gif.getFile();
                    } catch (FileNotFoundException e) {
                        ToastUtil.show(RemoteControlActivity.this.getString(R.string.net_time_out));
                        e.printStackTrace();
                        return;
                    } catch (SocketTimeoutException unused) {
                        ToastUtil.show(RemoteControlActivity.this.getString(R.string.net_time_out));
                        return;
                    } catch (Exception e2) {
                        ToastUtil.show(RemoteControlActivity.this.getString(R.string.net_time_out));
                        e2.printStackTrace();
                        return;
                    }
                } else {
                    file = null;
                }
                if (file == null) {
                    coroutineScope = RemoteControlActivity.this.mScope;
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new RemoteControlActivity$bindData$4$convert$1(gif, RemoteControlActivity.this, holder, null), 3, null);
                } else {
                    Intrinsics.checkNotNull(Glide.with((FragmentActivity) RemoteControlActivity.this).asGif().load(gif.getFile()).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) holder.getView(R.id.iv_anim_preview)));
                }
                ImageView imageView = (ImageView) holder.getView(R.id.iv_anim_select);
                if (AppConfig.INSTANCE.getLedType() == 0 || AppConfig.INSTANCE.getLedType() == 2) {
                    View view = holder.getView(R.id.rl_image_outside_frame);
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.width = (this.mContext.getResources().getDisplayMetrics().widthPixels / 4) - UtilsExtensionKt.toDp(20);
                    layoutParams.height = (this.mContext.getResources().getDisplayMetrics().widthPixels / 4) - UtilsExtensionKt.toDp(20);
                    view.setLayoutParams(layoutParams);
                }
                TextView textView2 = (TextView) holder.getView(R.id.tv_index);
                i = RemoteControlActivity.this.m_iSelect;
                int i3 = 0;
                boolean z = i == getPosition(holder);
                if (!z) {
                    if (z) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i3 = 8;
                }
                imageView.setVisibility(i3);
                RemoteControlActivity remoteControlActivity = RemoteControlActivity.this;
                iArr = remoteControlActivity.strs;
                textView2.setText(remoteControlActivity.getString(iArr[getPosition(holder)]));
                i2 = RemoteControlActivity.this.m_iSelect;
                textView2.setTextColor(i2 == getPosition(holder) ? Color.parseColor("#FFCC00") : Color.parseColor("#999999"));
            }
        };
        RecyclerView recyclerView6 = this.localRecyclerview;
        if (recyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localRecyclerview");
            recyclerView6 = null;
        }
        RecyclerAdapter<Record> recyclerAdapter2 = this.localAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
            recyclerAdapter2 = null;
        }
        recyclerView6.setAdapter(recyclerAdapter2);
        RecyclerAdapter<Record> recyclerAdapter3 = this.localAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
        } else {
            recyclerAdapter = recyclerAdapter3;
        }
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.control.RemoteControlActivity$$ExternalSyntheticLambda2
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                RemoteControlActivity.bindData$lambda$2(RemoteControlActivity.this, viewGroup, view, (Record) obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(RemoteControlActivity remoteControlActivity, View view) {
        LogUtils.file("RemoteControlActivity iv_back");
        remoteControlActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(RemoteControlActivity remoteControlActivity, View view) {
        LogUtils.file("RemoteControlActivity customImageView.setOnClickListener");
        if (ClickFilter.filter()) {
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
        CustomImageView customImageView = remoteControlActivity.customImageView;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customImageView");
            customImageView = null;
        }
        customImageView.setImageResource(AppConfig.INSTANCE.getBledOn() ? R.mipmap.r_c_on : R.mipmap.r_c_off);
        BaseSend.sendLedOnOff$default(SendCore.INSTANCE, AppConfig.INSTANCE.getBledOn() ? 1 : 0, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$2(RemoteControlActivity remoteControlActivity, ViewGroup viewGroup, View view, Record record, int i) {
        LogUtils.file("RemoteControlActivity localAdapter.setOnItemClickListener");
        if (ClickFilter.filter()) {
            return;
        }
        RecyclerAdapter<Record> recyclerAdapter = remoteControlActivity.localAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyItemChanged(remoteControlActivity.m_iSelect);
        remoteControlActivity.m_iSelect = i;
        RecyclerAdapter<Record> recyclerAdapter2 = remoteControlActivity.localAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
            recyclerAdapter2 = null;
        }
        recyclerAdapter2.notifyItemChanged(remoteControlActivity.m_iSelect);
        int i2 = i + 1;
        Log.d(remoteControlActivity.TAG, "bindData: 发送遥控信息" + i2);
        CustomImageView customImageView = remoteControlActivity.customImageView;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customImageView");
            customImageView = null;
        }
        customImageView.setImageResource(R.mipmap.r_c_on);
        byte b = (byte) i2;
        SendCore.INSTANCE.sendCompat(new byte[]{6, 0, 7, ByteCompanionObject.MIN_VALUE, b, remoteControlActivity.mLanguage}, null);
        if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
            SendCore.INSTANCE.sendCompat2(new byte[]{6, 0, 7, ByteCompanionObject.MIN_VALUE, b, remoteControlActivity.mLanguage}, null);
        }
    }
}
