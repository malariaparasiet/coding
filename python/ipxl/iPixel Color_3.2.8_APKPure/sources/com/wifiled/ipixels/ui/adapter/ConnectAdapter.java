package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.ipixels.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/ConnectAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/blelibrary/ble/model/BleDevice;", "context", "Landroid/content/Context;", "data", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "layoutId", "", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "device", "getLastFourChars", "", "str", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectAdapter extends RecyclerAdapter<BleDevice> {
    private final Context context;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_connect;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectAdapter(Context context, List<? extends BleDevice> data) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.context = context;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x03eb, code lost:
    
        if (r1.equals("0007") == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0526, code lost:
    
        if (r1.equals("0004") == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0542, code lost:
    
        r1 = "LED_64*64_" + getLastFourChars(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x052f, code lost:
    
        if (r1.equals("0003") == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0538, code lost:
    
        if (r1.equals("0002") == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x053f, code lost:
    
        if (r1.equals("0001") == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0114, code lost:
    
        if (r1.equals("20") == false) goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01f9, code lost:
    
        if (r9.length() <= 8) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01fb, code lost:
    
        r1 = r9.substring(6);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "substring(...)");
        r1 = java.lang.String.valueOf(java.lang.Integer.parseInt(r1, kotlin.text.CharsKt.checkRadix(16)));
        r4 = r1.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0218, code lost:
    
        if (r4 != 1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x021a, code lost:
    
        r1 = "000" + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0264, code lost:
    
        r4 = r9.substring(0, 6);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "substring(...)");
        r1 = "uditer_" + r4 + r1;
        r3.setText((java.lang.CharSequence) com.wifiled.baselib.utils.SPUtils.get(r24.context, r26.getBleAddress() + "rename", r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x022b, code lost:
    
        if (r4 != 2) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x022d, code lost:
    
        r1 = "00" + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x023e, code lost:
    
        if (r4 != 3) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0240, code lost:
    
        r1 = "0" + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0251, code lost:
    
        if (5 > r4) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0255, code lost:
    
        if (r4 >= 101) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0257, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1.substring(r1.length() - 5), "substring(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02a3, code lost:
    
        r3.setText((java.lang.CharSequence) com.wifiled.baselib.utils.SPUtils.get(r24.context, r26.getBleAddress() + "rename", r26.getBleName()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01ef, code lost:
    
        if (r1.equals("12") == false) goto L204;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:19:0x00ca. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x00cd. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x07fa  */
    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(com.wifiled.baselib.base.recycleview.RecyclerViewHolder r25, com.wifiled.blelibrary.ble.model.BleDevice r26) {
        /*
            Method dump skipped, instructions count: 2490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.adapter.ConnectAdapter.convert(com.wifiled.baselib.base.recycleview.RecyclerViewHolder, com.wifiled.blelibrary.ble.model.BleDevice):void");
    }

    private final String getLastFourChars(String str) {
        if (str.length() < 4) {
            return str;
        }
        String substring = str.substring(str.length() - 4);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
}
