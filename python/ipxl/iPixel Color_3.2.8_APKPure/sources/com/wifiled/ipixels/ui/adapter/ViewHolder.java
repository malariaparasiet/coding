package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes3.dex */
public class ViewHolder {
    private View mConvertView;
    private ViewGroup mParentView;
    private int mPosition;
    private final SparseArray<View> mViews = new SparseArray<>();

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        View inflate = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView = inflate;
        this.mParentView = parent;
        inflate.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public ViewGroup getParentView() {
        return this.mParentView;
    }

    public <T extends View> T getView(int i) {
        T t = (T) this.mViews.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.mConvertView.findViewById(i);
        this.mViews.put(i, t2);
        return t2;
    }

    public ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = (TextView) getView(viewId);
        if (textView != null) {
            textView.setText(text);
        }
        return this;
    }

    public ViewHolder setText(int viewId, CharSequence text, int resId) {
        TextView textView = (TextView) getView(viewId);
        textView.setTextColor(resId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ((ImageView) getView(viewId)).setImageResource(drawableId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ((ImageView) getView(viewId)).setImageBitmap(bm);
        return this;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setVisible(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
    }
}
