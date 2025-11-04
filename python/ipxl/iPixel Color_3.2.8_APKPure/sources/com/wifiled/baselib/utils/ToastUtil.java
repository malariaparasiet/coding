package com.wifiled.baselib.utils;

import android.widget.Toast;
import com.wifiled.baselib.CoreBase;

/* loaded from: classes2.dex */
public class ToastUtil {
    private static Toast mToast;

    public static void show(String str) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(CoreBase.getContext(), str, 0);
        } else {
            toast.setText(str);
            mToast.setDuration(0);
        }
        mToast.show();
    }

    public static void show(int i) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(CoreBase.getContext(), i, 0);
        } else {
            toast.setText(i);
            mToast.setDuration(0);
        }
        mToast.show();
    }

    public static void cancelToast() {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
            mToast = null;
        }
    }
}
