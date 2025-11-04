package com.wifiled.baselib.permission;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes2.dex */
public class PermissionCompat {
    public static void requestPermissions(FragmentActivity fragmentActivity, String[] strArr, String str, IPermission iPermission) {
        if (fragmentActivity == null) {
            return;
        }
        if (PermissionUtils.hasSelfPermissions(fragmentActivity, strArr)) {
            iPermission.permissionGranted();
        } else {
            PermissionFragment.newInstance().requestPermission(fragmentActivity, strArr, str, iPermission);
        }
    }
}
