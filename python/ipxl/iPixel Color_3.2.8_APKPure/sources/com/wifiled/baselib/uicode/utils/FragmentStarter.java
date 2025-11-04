package com.wifiled.baselib.uicode.utils;

import android.R;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.wifiled.baselib.uicode.base.UiBaseFragment;

/* loaded from: classes2.dex */
public class FragmentStarter {
    public static UiBaseFragment pushFragmentToBackStack(FragmentActivity fragmentActivity, int i, Class<? extends UiBaseFragment> cls, Object obj, boolean z) {
        ExceptionUtils.checkNotNull(Boolean.valueOf(cls == null), " fragment is null");
        try {
            String cls2 = cls.toString();
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            log("before operate, stack entry count: " + supportFragmentManager.getBackStackEntryCount());
            UiBaseFragment uiBaseFragment = (UiBaseFragment) supportFragmentManager.findFragmentByTag(cls2);
            if (uiBaseFragment == null) {
                uiBaseFragment = cls.newInstance();
            }
            uiBaseFragment.onEnterWithData(obj);
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (uiBaseFragment.isAdded()) {
                log(cls2 + " has been added, will be shown again.");
                beginTransaction.show(uiBaseFragment);
            } else {
                log(cls2 + " is added.");
                if (i == 0) {
                    i = R.id.content;
                }
                beginTransaction.add(i, uiBaseFragment, cls2);
            }
            if (z) {
                beginTransaction.addToBackStack(cls2);
            }
            beginTransaction.commitAllowingStateLoss();
            return uiBaseFragment;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void popTopFragment(FragmentActivity fragmentActivity, Class<? extends UiBaseFragment> cls) {
        fragmentActivity.getSupportFragmentManager().popBackStackImmediate();
    }

    public static void goToFragment(FragmentActivity fragmentActivity, Class<? extends UiBaseFragment> cls) {
        fragmentActivity.getSupportFragmentManager().popBackStackImmediate(cls.toString(), 0);
    }

    public static void startFragment(Fragment fragment, Fragment fragment2, String str) {
        FragmentTransaction beginTransaction = fragment.getFragmentManager().beginTransaction();
        beginTransaction.hide(fragment).add(R.id.content, fragment2, str);
        beginTransaction.addToBackStack(str);
        beginTransaction.commitAllowingStateLoss();
    }

    private static void log(String str) {
        if (UICoreConfig.INSTANCE.getMode()) {
            Log.i("FragmentStarter", "FragmentStarter:" + str);
        }
    }
}
