package com.wifiled.baselib.uicode.utils;

import android.R;
import android.content.Context;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SmartRefreshLayoutUtils.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/wifiled/baselib/uicode/utils/SmartRefreshLayoutUtils;", "", "<init>", "()V", "init", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SmartRefreshLayoutUtils {
    public static final SmartRefreshLayoutUtils INSTANCE = new SmartRefreshLayoutUtils();

    private SmartRefreshLayoutUtils() {
    }

    public final void init() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() { // from class: com.wifiled.baselib.uicode.utils.SmartRefreshLayoutUtils$init$1
            @Override // com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(layout, "layout");
                layout.setPrimaryColorsId(R.color.white, R.color.white);
                ClassicsHeader timeFormat = new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
                Intrinsics.checkNotNullExpressionValue(timeFormat, "setTimeFormat(...)");
                return timeFormat;
            }
        });
    }
}
