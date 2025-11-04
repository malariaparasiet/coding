package com.wifiled.baselib.permission;

import java.util.List;

/* loaded from: classes2.dex */
public abstract class IPermission {
    public void permissionDenied(int i, List<String> list) {
    }

    public abstract void permissionGranted();

    public void permissionNoAskDenied(int i, List<String> list) {
    }
}
