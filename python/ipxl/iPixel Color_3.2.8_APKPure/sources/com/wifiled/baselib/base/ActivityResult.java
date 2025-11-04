package com.wifiled.baselib.base;

import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.wifiled.baselib.callback.ActivityResultCallback;

/* loaded from: classes2.dex */
public class ActivityResult {
    private static final String TAG = "ActivityResult";
    private ActivityResultFragment mActivityResultFragment;

    public ActivityResult(FragmentActivity fragmentActivity) {
        this.mActivityResultFragment = getBestActivityResultFragment(fragmentActivity.getSupportFragmentManager());
    }

    private ActivityResultFragment getBestActivityResultFragment(FragmentManager fragmentManager) {
        ActivityResultFragment findActResultFragment = findActResultFragment(fragmentManager);
        if (findActResultFragment != null) {
            return findActResultFragment;
        }
        ActivityResultFragment activityResultFragment = new ActivityResultFragment();
        fragmentManager.beginTransaction().add(activityResultFragment, TAG).commitNow();
        return activityResultFragment;
    }

    private ActivityResultFragment findActResultFragment(FragmentManager fragmentManager) {
        return (ActivityResultFragment) fragmentManager.findFragmentByTag(TAG);
    }

    public void start(Intent intent, ActivityResultCallback activityResultCallback) {
        this.mActivityResultFragment.startActivity(intent, activityResultCallback);
    }

    public void start(Class<?> cls, ActivityResultCallback activityResultCallback) {
        start(new Intent(this.mActivityResultFragment.getActivity(), cls), activityResultCallback);
    }
}
