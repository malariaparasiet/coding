package com.wifiled.blelibrary.ble.exception;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class BleException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -3677084962477320584L;
    private Throwable ex;

    public BleException() {
    }

    public BleException(String str) {
        super(str);
    }

    public BleException(String str, Throwable th) {
        super(str, null);
        this.ex = th;
    }

    public BleException(Throwable th) {
        super(th);
    }

    public Throwable getException() {
        return this.ex;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.ex;
    }
}
