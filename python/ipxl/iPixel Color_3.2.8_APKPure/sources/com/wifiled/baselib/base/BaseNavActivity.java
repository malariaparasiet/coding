package com.wifiled.baselib.base;

import android.os.Bundle;
import com.wifiled.baselib.app.Navigation;

/* loaded from: classes2.dex */
public abstract class BaseNavActivity extends BaseActivity {
    protected boolean addToBackStack() {
        return true;
    }

    protected abstract int containerViewId();

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        Navigation.get().init(this, containerViewId(), addToBackStack(), mode());
        super.onCreate(bundle);
    }

    protected Navigation.MODE mode() {
        return Navigation.MODE.REPLACE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (Navigation.get().pop()) {
            return;
        }
        finish();
    }
}
