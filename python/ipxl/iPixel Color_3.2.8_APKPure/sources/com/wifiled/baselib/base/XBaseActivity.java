package com.wifiled.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import com.wifiled.baselib.R;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.callback.ActivityResultCallback;
import com.wifiled.baselib.utils.GlobalStatusBarUtil;
import com.wifiled.baselib.utils.SPUtils;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class XBaseActivity extends AppCompatActivity {
    public Activity mActivity;
    protected final String TAG = getClass().getSimpleName();
    private int mPermissionIdx = 16;
    private final SparseArray<GrantedResult> mPermissions = new SparseArray<>();
    boolean mAllGranted = true;

    protected abstract void bindData();

    protected void bindListener() {
    }

    public Activity getActivity() {
        return this;
    }

    protected abstract void initView();

    protected boolean isDarkFont() {
        return false;
    }

    protected abstract int layoutId();

    protected abstract void unbindData();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        EdgeToEdge.enable(this);
        super.onCreate(bundle);
        this.mActivity = this;
        setTranslucentStatus();
        setContentView(layoutId());
        initView();
        bindData();
        bindListener();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unbindData();
    }

    protected void toActivity(Class cls) {
        startActivity(new Intent(this, (Class<?>) cls));
    }

    protected void toActivity(Class cls, Bundle bundle) {
        startActivity(new Intent(this, (Class<?>) cls), bundle);
    }

    protected View inflate(int i) {
        return LayoutInflater.from(this).inflate(i, (ViewGroup) null);
    }

    public void toActivityForResult(Intent intent, ActivityResultCallback activityResultCallback) {
        new ActivityResult(this).start(intent, activityResultCallback);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        if (context == null) {
            super.attachBaseContext(context);
            return;
        }
        Locale locale = getLocale(context.getSharedPreferences(SPUtils.FILE_NAME, 0).getString(Constance.SP.LANGUAGE, Locale.getDefault().getLanguage()));
        final Configuration configuration = context.getResources().getConfiguration();
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        super.attachBaseContext(new ContextThemeWrapper(context.createConfigurationContext(configuration), R.style.Theme_AppCompat_Empty) { // from class: com.wifiled.baselib.base.XBaseActivity.1
            @Override // androidx.appcompat.view.ContextThemeWrapper
            public void applyOverrideConfiguration(Configuration configuration2) {
                if (configuration2 != null) {
                    configuration2.setTo(configuration);
                }
                super.applyOverrideConfiguration(configuration2);
            }
        });
    }

    private static Locale getLocale(String str) {
        Locale.getDefault();
        str.hashCode();
        switch (str) {
            case "de":
                return Locale.GERMANY;
            case "en":
                return Locale.ENGLISH;
            case "fr":
                return Locale.FRENCH;
            case "it":
                return Locale.ITALY;
            case "ja":
                return Locale.JAPAN;
            case "ko":
                return Locale.KOREA;
            case "zh":
                return Locale.SIMPLIFIED_CHINESE;
            case "pt-BR":
                return new Locale("pt", "BR");
            case "zh_TW":
                return Locale.TRADITIONAL_CHINESE;
            default:
                return new Locale(str);
        }
    }

    public void setTranslucentStatus() {
        requestWindowFeature(1);
        GlobalStatusBarUtil.setStatusBarDarkFont(this, isDarkFont());
    }

    public void toast(int i) {
        Toast.makeText(this, i, 0).show();
    }

    public void toast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        GrantedResult grantedResult = this.mPermissions.get(i);
        if (grantedResult == null) {
            return;
        }
        this.mAllGranted = (iArr.length > 0 && iArr[0] == 0) & this.mAllGranted;
        boolean z = true;
        for (String str : strArr) {
            z = z && checkSelfPermission(str) == 0;
        }
        grantedResult.mGranted = z;
        runOnUiThread(grantedResult);
    }

    public void requestPermission(String[] strArr, String str, GrantedResult grantedResult) {
        if (grantedResult == null) {
            return;
        }
        grantedResult.mGranted = false;
        if (strArr == null || strArr.length == 0) {
            grantedResult.mGranted = true;
            runOnUiThread(grantedResult);
            return;
        }
        int i = this.mPermissionIdx;
        this.mPermissionIdx = i + 1;
        this.mPermissions.put(i, grantedResult);
        boolean z = true;
        for (String str2 : strArr) {
            z = z && checkSelfPermission(str2) == 0;
        }
        if (z) {
            grantedResult.mGranted = true;
            runOnUiThread(grantedResult);
            return;
        }
        boolean z2 = true;
        for (String str3 : strArr) {
            z2 = z2 && !shouldShowRequestPermissionRationale(str3);
        }
        if (!z2) {
            GrantedResult grantedResult2 = this.mPermissions.get(i);
            if (grantedResult2 == null) {
                return;
            }
            grantedResult2.mGranted = false;
            runOnUiThread(grantedResult2);
            return;
        }
        requestPermissions(strArr, i);
    }

    public static abstract class GrantedResult implements Runnable {
        private boolean mGranted;

        public abstract void onResult(boolean z);

        @Override // java.lang.Runnable
        public void run() {
            onResult(this.mGranted);
        }
    }
}
