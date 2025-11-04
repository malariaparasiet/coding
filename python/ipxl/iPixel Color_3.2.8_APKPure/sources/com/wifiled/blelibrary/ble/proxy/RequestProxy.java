package com.wifiled.blelibrary.ble.proxy;

import android.content.Context;
import com.wifiled.blelibrary.ble.BleLog;
import com.wifiled.blelibrary.ble.request.ConnectRequest;
import com.wifiled.blelibrary.ble.request.DescriptorRequest;
import com.wifiled.blelibrary.ble.request.MtuRequest;
import com.wifiled.blelibrary.ble.request.NotifyRequest;
import com.wifiled.blelibrary.ble.request.ReadRequest;
import com.wifiled.blelibrary.ble.request.ReadRssiRequest;
import com.wifiled.blelibrary.ble.request.Rproxy;
import com.wifiled.blelibrary.ble.request.ScanRequest;
import com.wifiled.blelibrary.ble.request.WriteRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes2.dex */
public class RequestProxy implements InvocationHandler {
    private static final String TAG = "RequestProxy";
    private Object receiver;

    private RequestProxy() {
    }

    public static RequestProxy newProxy() {
        return new RequestProxy();
    }

    public Object bindProxy(Context context, Object obj) {
        this.receiver = obj;
        BleLog.d(TAG, "bindProxy: Binding agent successfully");
        Rproxy.init(ConnectRequest.class, MtuRequest.class, NotifyRequest.class, ReadRequest.class, ReadRssiRequest.class, ScanRequest.class, WriteRequest.class, DescriptorRequest.class);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        return method.invoke(this.receiver, objArr);
    }
}
