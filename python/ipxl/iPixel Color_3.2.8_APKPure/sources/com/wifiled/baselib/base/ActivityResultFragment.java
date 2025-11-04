package com.wifiled.baselib.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.wifiled.baselib.callback.ActivityResultCallback;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class ActivityResultFragment extends Fragment {
    private static final TreeMap<Integer, ActivityResultCallback> LISTENER_MAP = new TreeMap<>();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void startActivity(Intent intent, ActivityResultCallback activityResultCallback) {
        int requestCode = getRequestCode();
        LISTENER_MAP.put(Integer.valueOf(requestCode), activityResultCallback);
        startActivityForResult(intent, requestCode);
    }

    private int getRequestCode() {
        TreeMap<Integer, ActivityResultCallback> treeMap = LISTENER_MAP;
        if (treeMap.isEmpty()) {
            return 1;
        }
        return treeMap.lastKey().intValue() + 1;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ActivityResultCallback remove = LISTENER_MAP.remove(Integer.valueOf(i));
        if (remove != null) {
            remove.onActivityResult(i2, intent);
        }
    }
}
