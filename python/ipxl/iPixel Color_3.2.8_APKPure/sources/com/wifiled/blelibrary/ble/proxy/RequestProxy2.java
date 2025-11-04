package com.wifiled.blelibrary.ble.proxy;

import android.content.Context;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.request.ConnectRequest2;
import com.wifiled.blelibrary.ble.request.DescriptorRequest2;
import com.wifiled.blelibrary.ble.request.MtuRequest2;
import com.wifiled.blelibrary.ble.request.NotifyRequest2;
import com.wifiled.blelibrary.ble.request.ReadRequest2;
import com.wifiled.blelibrary.ble.request.ReadRssiRequest2;
import com.wifiled.blelibrary.ble.request.Rproxy2;
import com.wifiled.blelibrary.ble.request.ScanRequest;
import com.wifiled.blelibrary.ble.request.WriteRequest2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes2.dex */
public class RequestProxy2 implements InvocationHandler {
    private static final String TAG = "RequestProxy";
    private Object receiver;

    private RequestProxy2() {
    }

    public static RequestProxy2 newProxy() {
        return new RequestProxy2();
    }

    public Object bindProxy(Context context, Object obj) {
        this.receiver = obj;
        BleLog.d(TAG, "bindProxy: Binding agent successfully");
        Rproxy2.init(ConnectRequest2.class, MtuRequest2.class, NotifyRequest2.class, ReadRequest2.class, ReadRssiRequest2.class, ScanRequest.class, WriteRequest2.class, DescriptorRequest2.class);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        return method.invoke(this.receiver, objArr);
    }
}
