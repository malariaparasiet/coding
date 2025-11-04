package com.wifiled.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.wifiled.baselib.callback.HandleBackInterface;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment implements HandleBackInterface {
    protected final String TAG = getClass().getSimpleName();
    protected FragmentActivity mActivity;
    protected View mRootView;
    protected WeakReference<FragmentActivity> mWeakActivity;

    protected abstract void bindData();

    protected void bindListener() {
    }

    protected void initView() {
    }

    protected abstract int layoutId();

    @Override // com.wifiled.baselib.callback.HandleBackInterface
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(layoutId(), (ViewGroup) null);
        }
        this.mWeakActivity = new WeakReference<>(getActivity());
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        bindData();
        bindListener();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = getActivity();
    }

    protected void toActivity(Class cls) {
        startActivity(new Intent(getActivity(), (Class<?>) cls));
    }

    protected void toActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), (Class<?>) cls);
        intent.putExtra(cls.getSimpleName(), bundle);
        startActivity(intent);
    }

    public void toast(int i) {
        Toast.makeText(this.mActivity, i, 0).show();
    }

    public void toast(String str) {
        Toast.makeText(this.mActivity, str, 0).show();
    }

    protected View inflate(int i) {
        return LayoutInflater.from(getActivity()).inflate(i, (ViewGroup) null);
    }
}
