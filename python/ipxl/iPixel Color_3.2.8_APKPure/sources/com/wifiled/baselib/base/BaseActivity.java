package com.wifiled.baselib.base;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.callback.ActivityResultCallback;
import com.wifiled.baselib.permission.IPermission;
import com.wifiled.baselib.permission.PermissionCompat;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.GlobalStatusBarUtil;
import com.wifiled.baselib.utils.SPUtils;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class BaseActivity extends AppCompatActivity {
    public TextView abTitle;
    public Activity mActivity;
    private AlertDialog mOtaProgressDialog;
    private AlertDialog mProgressDialog;
    public Toolbar toolbar;
    protected final String TAG = getClass().getSimpleName();
    public final int DIALOG_TYPE_OTA = 1;
    public final int DIALOG_TYPE_BLE = 2;
    private int m_iDialogType = -1;
    private int mPermissionIdx = 16;
    private SparseArray<GrantedResult> mPermissions = new SparseArray<>();

    protected void afterCreateView() {
    }

    protected abstract void bindData();

    protected void bindListener() {
    }

    public Activity getActivity() {
        return this;
    }

    protected void initView() {
    }

    protected boolean isDarkFont() {
        return false;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected boolean isHideBottomUIMenu() {
        return false;
    }

    protected boolean isPortrait() {
        return true;
    }

    protected boolean isStatusbarTransparent() {
        return true;
    }

    protected abstract int layoutId();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        try {
            EdgeToEdge.enable(this);
            setFullScreen();
            super.onCreate(bundle);
            afterCreateView();
            this.mActivity = this;
            setTranslucentStatus();
            setContentView(layoutId());
            setHideBottomUIMenu();
            initNavagation();
            initToolBar();
            initView();
            bindData();
            bindListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initNavagation() {
        if (isStatusbarTransparent()) {
            GlobalStatusBarUtil.setUpStatusBar(this, R.color.transparent, false);
        }
        GlobalStatusBarUtil.setFitsSystemWindows(this, true);
    }

    private void setFullScreen() {
        if (isFullScreen()) {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
        }
    }

    private void setHideBottomUIMenu() {
        if (isHideBottomUIMenu()) {
            hideBottomUIMenu();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        setHideBottomUIMenu();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mActivity = null;
    }

    protected void toActivity(Class cls) {
        startActivity(new Intent(this, (Class<?>) cls));
    }

    protected void toActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, (Class<?>) cls);
        intent.putExtra(cls.getSimpleName(), bundle);
        startActivity(intent);
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
        super.attachBaseContext(new ContextThemeWrapper(context.createConfigurationContext(configuration), com.wifiled.baselib.R.style.Theme_AppCompat_Empty) { // from class: com.wifiled.baselib.base.BaseActivity.1
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

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        TextView textView = this.abTitle;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    private void initToolBar() {
        ActionBar supportActionBar;
        Toolbar toolbar = (Toolbar) findViewById(com.wifiled.baselib.R.id.toolbar);
        this.toolbar = toolbar;
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            this.abTitle = (TextView) this.toolbar.findViewById(com.wifiled.baselib.R.id.toolbar_title);
        }
        if (this.abTitle == null || (supportActionBar = getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setDisplayShowTitleEnabled(false);
    }

    protected void hideBottomUIMenu() {
        getWindow().getDecorView().setSystemUiVisibility(4098);
    }

    public void setTranslucentStatus() {
        requestWindowFeature(1);
        setRequestedOrientation(isPortrait() ? 1 : 0);
        GlobalStatusBarUtil.setStatusBarDarkFont(this, isDarkFont());
    }

    protected int getStatusBarColorId() {
        return Color.parseColor("#111112");
    }

    protected void isGpsOpen() {
        if (AppUtils.isGpsOpen(this)) {
            return;
        }
        new AlertDialog.Builder(this).setCancelable(false).setTitle(com.wifiled.baselib.R.string.gps_tip).setMessage(com.wifiled.baselib.R.string.open_gps).setNegativeButton(com.wifiled.baselib.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.base.BaseActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(com.wifiled.baselib.R.string.sure, new DialogInterface.OnClickListener() { // from class: com.wifiled.baselib.base.BaseActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                BaseActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        }).create().show();
    }

    public void hideSoftKeyboard() {
        View currentFocus;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager == null || (currentFocus = getCurrentFocus()) == null || currentFocus.getWindowToken() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    public void showProgressDialog(String str) {
        showProgressDialog(str, 2, false);
    }

    public void showProgressDialog(String str, int i, boolean z) {
        if (i == 1) {
            if (z) {
                dismissProgressDialog(1);
            }
            if (this.mOtaProgressDialog == null) {
                AlertDialog create = new AlertDialog.Builder(this).setCancelable(false).create();
                this.mOtaProgressDialog = create;
                create.setCanceledOnTouchOutside(z);
                this.mOtaProgressDialog.show();
                this.mOtaProgressDialog.setContentView(com.wifiled.baselib.R.layout.progress_dialog);
            }
            ((TextView) this.mOtaProgressDialog.findViewById(com.wifiled.baselib.R.id.message)).setText(str);
            this.mOtaProgressDialog.show();
        } else if (i == 2) {
            if (this.mProgressDialog == null) {
                AlertDialog create2 = new AlertDialog.Builder(this).setCancelable(false).create();
                this.mProgressDialog = create2;
                create2.setCanceledOnTouchOutside(false);
                this.mProgressDialog.show();
                this.mProgressDialog.setContentView(com.wifiled.baselib.R.layout.progress_dialog);
            }
            ((TextView) this.mProgressDialog.findViewById(com.wifiled.baselib.R.id.message)).setText(str);
            this.mProgressDialog.show();
        }
        this.m_iDialogType = i;
    }

    public void dismissAllProgressDialog() {
        dismissProgressDialog();
        dismissProgressDialog(1);
    }

    public void dismissProgressDialog() {
        dismissProgressDialog(2);
    }

    public void dismissProgressDialog(int i) {
        if (i == 2) {
            AlertDialog alertDialog = this.mProgressDialog;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.mProgressDialog.dismiss();
            return;
        }
        AlertDialog alertDialog2 = this.mOtaProgressDialog;
        if (alertDialog2 == null || !alertDialog2.isShowing()) {
            return;
        }
        this.mOtaProgressDialog.dismiss();
        this.mOtaProgressDialog = null;
    }

    public boolean isDialogShowing() {
        AlertDialog alertDialog = this.mProgressDialog;
        return alertDialog != null && alertDialog.isShowing();
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
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (iArr[i2] != 0) {
                grantedResult.mGranted = false;
                break;
            } else {
                grantedResult.mGranted = true;
                i2++;
            }
        }
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
            LogUtils.vTag("ruis", "granted===" + z);
            LogUtils.vTag("ruis", "checkSelfPermission(permission)===" + checkSelfPermission(str2));
            z = z && checkSelfPermission(str2) == 0;
        }
        LogUtils.vTag("ruis", "granted===" + z);
        if (z) {
            grantedResult.mGranted = true;
            runOnUiThread(grantedResult);
            return;
        }
        boolean z2 = true;
        for (String str3 : strArr) {
            z2 = z2 && !shouldShowRequestPermissionRationale(str3);
        }
        LogUtils.vTag("ruis", "request===" + z2);
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

    public void requestPermissionSelfPermis(String[] strArr, String str, IPermission iPermission) {
        PermissionCompat.requestPermissions(this, strArr, str, iPermission);
    }
}
