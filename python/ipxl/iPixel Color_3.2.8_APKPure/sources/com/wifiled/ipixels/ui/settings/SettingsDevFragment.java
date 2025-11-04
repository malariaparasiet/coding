package com.wifiled.ipixels.ui.settings;

import android.view.View;
import android.widget.TextView;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SettingsDevFragment.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SettingsDevFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "array", "", "getArray", "()[B", "setArray", "([B)V", "set_devinfo_devtype", "Landroid/widget/TextView;", "set_devinfo_mcuver", "set_devinfo_wifiver", "layoutId", "", "initView", "", "bindData", "onResume", "setHwinfo", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsDevFragment extends BaseFragment {
    private byte[] array;
    private TextView set_devinfo_devtype;
    private TextView set_devinfo_mcuver;
    private TextView set_devinfo_wifiver;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_settings_devinfo;
    }

    public final byte[] getArray() {
        return this.array;
    }

    public final void setArray(byte[] bArr) {
        this.array = bArr;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.set_devinfo_devtype);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.set_devinfo_devtype = (TextView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.set_devinfo_mcuver);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.set_devinfo_mcuver = (TextView) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.set_devinfo_wifiver);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.set_devinfo_wifiver = (TextView) findViewById3;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        setHwinfo();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setHwinfo();
    }

    private final void setHwinfo() {
        Object valueOf;
        String string;
        Object valueOf2;
        byte[] bArr = this.array;
        if (bArr != null) {
            Intrinsics.checkNotNull(bArr);
            if (bArr.length == 0) {
                return;
            }
            byte[] bArr2 = this.array;
            Intrinsics.checkNotNull(bArr2);
            if (bArr2.length < 8) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.set_devinfo_type) + " ").append(AppConfig.INSTANCE.getLedSize().get(0).intValue()).append(" X ").append(AppConfig.INSTANCE.getLedSize().get(1).intValue());
            TextView textView = this.set_devinfo_devtype;
            TextView textView2 = null;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("set_devinfo_devtype");
                textView = null;
            }
            textView.setText(sb.toString());
            StringsKt.clear(sb);
            String string2 = getString(R.string.set_devinfo_mcuver);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            TextView textView3 = this.set_devinfo_mcuver;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("set_devinfo_mcuver");
                textView3 = null;
            }
            StringBuilder append = sb.append(string2 + " ");
            byte[] bArr3 = this.array;
            Intrinsics.checkNotNull(bArr3);
            StringBuilder append2 = append.append(Byte.valueOf(bArr3[4])).append(".");
            byte[] bArr4 = this.array;
            Intrinsics.checkNotNull(bArr4);
            if (bArr4[5] < 10) {
                byte[] bArr5 = this.array;
                Intrinsics.checkNotNull(bArr5);
                valueOf = "0" + ((int) bArr5[5]);
            } else {
                byte[] bArr6 = this.array;
                Intrinsics.checkNotNull(bArr6);
                valueOf = Byte.valueOf(bArr6[5]);
            }
            textView3.setText(append2.append(valueOf));
            if (AppConfig.INSTANCE.getLedHasWifi()) {
                string = getString(R.string.set_devinfo_wifiver);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            } else {
                string = getString(R.string.set_devinfo_blever);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            }
            TextView textView4 = this.set_devinfo_wifiver;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("set_devinfo_wifiver");
                textView4 = null;
            }
            UtilsExtensionKt.show(textView4);
            StringsKt.clear(sb);
            TextView textView5 = this.set_devinfo_wifiver;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("set_devinfo_wifiver");
            } else {
                textView2 = textView5;
            }
            StringBuilder append3 = sb.append(string + " ");
            byte[] bArr7 = this.array;
            Intrinsics.checkNotNull(bArr7);
            StringBuilder append4 = append3.append(Byte.valueOf(bArr7[6])).append(".");
            byte[] bArr8 = this.array;
            Intrinsics.checkNotNull(bArr8);
            if (bArr8[7] < 10) {
                byte[] bArr9 = this.array;
                Intrinsics.checkNotNull(bArr9);
                valueOf2 = "0" + ((int) bArr9[7]);
            } else {
                byte[] bArr10 = this.array;
                Intrinsics.checkNotNull(bArr10);
                valueOf2 = Byte.valueOf(bArr10[7]);
            }
            textView2.setText(append4.append(valueOf2));
        }
    }
}
