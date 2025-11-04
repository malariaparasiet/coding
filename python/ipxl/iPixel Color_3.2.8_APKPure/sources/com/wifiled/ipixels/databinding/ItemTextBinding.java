package com.wifiled.ipixels.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;

/* loaded from: classes3.dex */
public final class ItemTextBinding implements ViewBinding {
    public final ObjectarxItem objectarxItem;
    private final ObjectarxItem rootView;

    private ItemTextBinding(ObjectarxItem rootView, ObjectarxItem objectarxItem) {
        this.rootView = rootView;
        this.objectarxItem = objectarxItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ObjectarxItem getRoot() {
        return this.rootView;
    }

    public static ItemTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.item_text, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        ObjectarxItem objectarxItem = (ObjectarxItem) rootView;
        return new ItemTextBinding(objectarxItem, objectarxItem);
    }
}
