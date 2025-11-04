package com.wifiled.baselib.app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class Navigation {
    private static final String TAG = "Navigation";
    private static Navigation navigation;
    private WeakReference<FragmentActivity> activityRef;
    public boolean addToBackStack;
    public int containerViewId;
    public FragmentManager fragmentManager;
    private FragmentActivity mContext;
    public MODE mode;
    public MODE initMode = MODE.REPLACE;
    public boolean initAddToBackStack = true;

    public enum MODE {
        SHOW,
        REPLACE,
        ADD
    }

    public void init(FragmentActivity fragmentActivity, int i) {
        init(fragmentActivity, i, this.addToBackStack, MODE.REPLACE);
    }

    public void init(FragmentActivity fragmentActivity, int i, boolean z, MODE mode) {
        if (fragmentActivity != null) {
            this.mContext = fragmentActivity;
            this.activityRef = new WeakReference<>(fragmentActivity);
            this.fragmentManager = fragmentActivity.getSupportFragmentManager();
            this.containerViewId = i;
            this.initAddToBackStack = z;
            this.addToBackStack = z;
            this.initMode = mode;
            this.mode = mode;
        }
    }

    public static Navigation get() {
        if (navigation == null) {
            navigation = new Navigation();
        }
        return navigation;
    }

    public void navigate(Fragment fragment) {
        try {
            if (this.mode == MODE.REPLACE) {
                replace(fragment);
            } else if (this.mode == MODE.ADD) {
                add(fragment);
            } else {
                show(fragment);
            }
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean pop() {
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager == null || fragmentManager.getBackStackEntryCount() <= 0) {
            return false;
        }
        this.fragmentManager.popBackStack();
        return true;
    }

    public void clear() {
        WeakReference<FragmentActivity> weakReference = this.activityRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        navigation = null;
        this.fragmentManager = null;
    }

    void show(Fragment fragment) {
        if (this.fragmentManager == null) {
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                return;
            } else {
                this.fragmentManager = fragmentActivity.getSupportFragmentManager();
            }
        }
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        Iterator<Fragment> it = this.fragmentManager.getFragments().iterator();
        while (it.hasNext()) {
            beginTransaction.hide(it.next());
        }
        String simpleName = fragment.getClass().getSimpleName();
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(simpleName);
        if (findFragmentByTag == null) {
            beginTransaction.add(this.containerViewId, fragment, simpleName);
        } else {
            beginTransaction.show(findFragmentByTag);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    public void hideFragment(Fragment fragment) {
        if (this.fragmentManager == null) {
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                return;
            } else {
                this.fragmentManager = fragmentActivity.getSupportFragmentManager();
            }
        }
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (findFragmentByTag != null) {
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            beginTransaction.hide(findFragmentByTag);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    void add(Fragment fragment) {
        if (this.fragmentManager == null) {
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                return;
            } else {
                this.fragmentManager = fragmentActivity.getSupportFragmentManager();
            }
        }
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        beginTransaction.add(this.containerViewId, fragment);
        if (this.addToBackStack) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    void replace(Fragment fragment) {
        if (this.fragmentManager == null) {
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity == null) {
                return;
            } else {
                this.fragmentManager = fragmentActivity.getSupportFragmentManager();
            }
        }
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        beginTransaction.replace(this.containerViewId, fragment);
        if (this.addToBackStack) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    void reset() {
        this.addToBackStack = this.initAddToBackStack;
        this.mode = this.initMode;
    }

    public Navigation mode(MODE mode) {
        this.mode = mode;
        return this;
    }

    public Navigation addToBackStack(boolean z) {
        this.addToBackStack = z;
        return this;
    }

    public Navigation containerViewId(int i) {
        this.containerViewId = i;
        return this;
    }
}
