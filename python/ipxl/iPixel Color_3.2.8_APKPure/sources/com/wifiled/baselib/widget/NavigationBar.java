package com.wifiled.baselib.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.wifiled.baselib.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NavigationBar extends LinearLayout implements View.OnClickListener {
    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};
    private static final String KEY_CURRENT_TAG = "NavigationBar";
    private int mCurrentSelectedTab;
    private String mCurrentTag;
    private int mDefaultSelectedTab;
    private FragmentActivity mFragmentActivity;
    private int mMainContentLayoutId;
    private ColorStateList mNormalTextColor;
    private String mRestoreTag;
    private ColorStateList mSelectedTextColor;
    private int mTabItemLayout;
    private OnTabSelectedListener mTabSelectListener;
    private float mTabTextSize;
    private List<ViewHolder> mViewHolderList;

    public interface OnTabSelectedListener {
        void onTabSelected(ViewHolder viewHolder);
    }

    public static class ViewHolder {
        public Class fragmentClass;
        public TabParam pageParam;
        public ImageView tabIcon;
        public int tabIndex;
        public TextView tabTitle;
        public String tag;
    }

    public NavigationBar(Context context) {
        this(context, null);
    }

    public NavigationBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultSelectedTab = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.NavigationBar, 0, 0);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.NavigationBar_navigateTabTextColor);
        ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.NavigationBar_navigateTabSelectedTextColor);
        this.mTabTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBar_navigateTabTextSize, 0);
        this.mMainContentLayoutId = obtainStyledAttributes.getResourceId(R.styleable.NavigationBar_containerId, 0);
        this.mTabItemLayout = obtainStyledAttributes.getResourceId(R.styleable.NavigationBar_tabItemLayout, R.layout.default_nav_tabview);
        this.mNormalTextColor = colorStateList == null ? context.getResources().getColorStateList(android.R.color.background_dark) : colorStateList;
        if (colorStateList2 != null) {
            this.mSelectedTextColor = colorStateList2;
        } else {
            checkAppCompatTheme(context);
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            this.mSelectedTextColor = context.getResources().getColorStateList(typedValue.resourceId);
        }
        this.mViewHolderList = new ArrayList();
    }

    public void addTab(Class cls, TabParam tabParam) {
        if (TextUtils.isEmpty(tabParam.title)) {
            tabParam.title = getContext().getString(tabParam.titleStringRes);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(this.mTabItemLayout, (ViewGroup) null);
        inflate.setFocusable(true);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tabIndex = this.mViewHolderList.size();
        viewHolder.fragmentClass = cls;
        viewHolder.tag = tabParam.title;
        viewHolder.pageParam = tabParam;
        viewHolder.tabIcon = (ImageView) inflate.findViewById(R.id.tab_icon);
        viewHolder.tabTitle = (TextView) inflate.findViewById(R.id.tab_title);
        if (TextUtils.isEmpty(tabParam.title)) {
            viewHolder.tabTitle.setVisibility(4);
        } else {
            viewHolder.tabTitle.setText(tabParam.title);
        }
        if (this.mTabTextSize != 0.0f) {
            viewHolder.tabTitle.setTextSize(0, this.mTabTextSize);
        }
        if (this.mNormalTextColor != null) {
            viewHolder.tabTitle.setTextColor(this.mNormalTextColor);
        }
        if (tabParam.backgroundColor > 0) {
            inflate.setBackgroundResource(tabParam.backgroundColor);
        }
        if (tabParam.iconResId > 0) {
            viewHolder.tabIcon.setImageResource(tabParam.iconResId);
        } else {
            viewHolder.tabIcon.setVisibility(4);
        }
        if (tabParam.iconResId > 0 && tabParam.iconSelectedResId > 0) {
            inflate.setTag(viewHolder);
            inflate.setOnClickListener(this);
            this.mViewHolderList.add(viewHolder);
        }
        addView(inflate, new LinearLayout.LayoutParams(-1, -1, 1.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        ViewHolder viewHolder;
        super.onAttachedToWindow();
        if (this.mMainContentLayoutId == 0) {
            throw new RuntimeException("mFrameLayoutId Cannot be 0");
        }
        if (this.mViewHolderList.size() == 0) {
            throw new RuntimeException("mViewHolderList.size Cannot be 0, Please call addTab()");
        }
        if (!(getContext() instanceof FragmentActivity)) {
            throw new RuntimeException("parent activity must is extends FragmentActivity");
        }
        this.mFragmentActivity = (FragmentActivity) getContext();
        hideAllFragment();
        if (!TextUtils.isEmpty(this.mRestoreTag)) {
            Iterator<ViewHolder> it = this.mViewHolderList.iterator();
            while (true) {
                viewHolder = null;
                if (!it.hasNext()) {
                    break;
                }
                ViewHolder next = it.next();
                if (TextUtils.equals(this.mRestoreTag, next.tag)) {
                    this.mRestoreTag = null;
                    viewHolder = next;
                    break;
                }
            }
        } else {
            viewHolder = this.mViewHolderList.get(this.mDefaultSelectedTab);
        }
        showFragment(viewHolder);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag == null || !(tag instanceof ViewHolder)) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        showFragment(viewHolder);
        OnTabSelectedListener onTabSelectedListener = this.mTabSelectListener;
        if (onTabSelectedListener != null) {
            onTabSelectedListener.onTabSelected(viewHolder);
        }
    }

    private void showFragment(ViewHolder viewHolder) {
        FragmentTransaction beginTransaction = this.mFragmentActivity.getSupportFragmentManager().beginTransaction();
        if (isFragmentShown(beginTransaction, viewHolder.tag)) {
            return;
        }
        setCurrSelectedTabByTag(viewHolder.tag);
        Fragment findFragmentByTag = this.mFragmentActivity.getSupportFragmentManager().findFragmentByTag(viewHolder.tag);
        if (findFragmentByTag == null) {
            beginTransaction.add(this.mMainContentLayoutId, getFragmentInstance(viewHolder.tag), viewHolder.tag);
        } else {
            beginTransaction.show(findFragmentByTag);
        }
        beginTransaction.commit();
        this.mCurrentSelectedTab = viewHolder.tabIndex;
    }

    private boolean isFragmentShown(FragmentTransaction fragmentTransaction, String str) {
        Fragment findFragmentByTag;
        if (TextUtils.equals(str, this.mCurrentTag)) {
            return true;
        }
        if (!TextUtils.isEmpty(this.mCurrentTag) && (findFragmentByTag = this.mFragmentActivity.getSupportFragmentManager().findFragmentByTag(this.mCurrentTag)) != null && !findFragmentByTag.isHidden()) {
            fragmentTransaction.hide(findFragmentByTag);
        }
        return false;
    }

    private void setCurrSelectedTabByTag(String str) {
        if (TextUtils.equals(this.mCurrentTag, str)) {
            return;
        }
        for (ViewHolder viewHolder : this.mViewHolderList) {
            if (TextUtils.equals(this.mCurrentTag, viewHolder.tag)) {
                viewHolder.tabIcon.setImageResource(viewHolder.pageParam.iconResId);
                viewHolder.tabTitle.setTextColor(this.mNormalTextColor);
            } else if (TextUtils.equals(str, viewHolder.tag)) {
                viewHolder.tabIcon.setImageResource(viewHolder.pageParam.iconSelectedResId);
                viewHolder.tabTitle.setTextColor(this.mSelectedTextColor);
            }
        }
        this.mCurrentTag = str;
    }

    public Fragment getFragmentInstance(String str) {
        for (ViewHolder viewHolder : this.mViewHolderList) {
            if (TextUtils.equals(str, viewHolder.tag)) {
                try {
                    return (Fragment) Class.forName(viewHolder.fragmentClass.getName()).newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (InstantiationException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    private void hideAllFragment() {
        List<ViewHolder> list = this.mViewHolderList;
        if (list == null || list.size() == 0) {
            return;
        }
        FragmentTransaction beginTransaction = this.mFragmentActivity.getSupportFragmentManager().beginTransaction();
        Iterator<ViewHolder> it = this.mViewHolderList.iterator();
        while (it.hasNext()) {
            Fragment findFragmentByTag = this.mFragmentActivity.getSupportFragmentManager().findFragmentByTag(it.next().tag);
            if (findFragmentByTag != null && !findFragmentByTag.isHidden()) {
                beginTransaction.hide(findFragmentByTag);
            }
        }
        beginTransaction.commit();
    }

    public void setSelectedTabTextColor(ColorStateList colorStateList) {
        this.mSelectedTextColor = colorStateList;
    }

    public void setSelectedTabTextColor(int i) {
        this.mSelectedTextColor = ColorStateList.valueOf(i);
    }

    public void setTabTextColor(ColorStateList colorStateList) {
        this.mNormalTextColor = colorStateList;
    }

    public void setTabTextColor(int i) {
        this.mNormalTextColor = ColorStateList.valueOf(i);
    }

    public void setFrameLayoutId(int i) {
        this.mMainContentLayoutId = i;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mRestoreTag = bundle.getString(KEY_CURRENT_TAG);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(KEY_CURRENT_TAG, this.mCurrentTag);
    }

    public static class TabParam {
        public int backgroundColor;
        public int iconResId;
        public int iconSelectedResId;
        public String title;
        public int titleStringRes;

        public TabParam(int i, int i2, String str) {
            this.backgroundColor = android.R.color.white;
            this.iconResId = i;
            this.iconSelectedResId = i2;
            this.title = str;
        }

        public TabParam(int i, int i2, int i3) {
            this.backgroundColor = android.R.color.white;
            this.iconResId = i;
            this.iconSelectedResId = i2;
            this.titleStringRes = i3;
        }

        public TabParam(int i, int i2, int i3, int i4) {
            this.backgroundColor = i;
            this.iconResId = i2;
            this.iconSelectedResId = i3;
            this.titleStringRes = i4;
        }

        public TabParam(int i, int i2, int i3, String str) {
            this.backgroundColor = i;
            this.iconResId = i2;
            this.iconSelectedResId = i3;
            this.title = str;
        }
    }

    public void setTabSelectListener(OnTabSelectedListener onTabSelectedListener) {
        this.mTabSelectListener = onTabSelectedListener;
    }

    public void setDefaultSelectedTab(int i) {
        if (i < 0 || i >= this.mViewHolderList.size()) {
            return;
        }
        this.mDefaultSelectedTab = i;
    }

    public void setCurrentSelectedTab(int i) {
        if (i < 0 || i >= this.mViewHolderList.size()) {
            return;
        }
        showFragment(this.mViewHolderList.get(i));
    }

    public int getCurrentSelectedTab() {
        return this.mCurrentSelectedTab;
    }

    public void checkAppCompatTheme(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        boolean hasValue = obtainStyledAttributes.hasValue(0);
        obtainStyledAttributes.recycle();
        if (!hasValue) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
    }
}
