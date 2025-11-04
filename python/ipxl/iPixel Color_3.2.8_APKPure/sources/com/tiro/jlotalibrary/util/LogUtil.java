package com.tiro.jlotalibrary.util;

import com.alibaba.fastjson2.JSONB;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogUtil.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005J\u001b\u0010\u000f\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/tiro/jlotalibrary/util/LogUtil;", "", "<init>", "()V", "className", "", "methodName", "lineName", "", "v", "", "message", "d", "i", "e", "createLogName", "stackTrace", "", "Ljava/lang/StackTraceElement;", "([Ljava/lang/StackTraceElement;)V", "binaryToHexString", "bytes", "", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LogUtil {
    public static final LogUtil INSTANCE = new LogUtil();
    private static String className;
    private static int lineName;
    private static String methodName;

    public final void d(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final void e(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final void i(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final void v(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    private LogUtil() {
    }

    private final void createLogName(StackTraceElement[] stackTrace) {
        className = "JL_OTA " + stackTrace[1].getClassName();
        methodName = stackTrace[1].getMethodName();
        lineName = stackTrace[1].getLineNumber();
    }

    public final String binaryToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "bytes is null or length < 0";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.valueOf("0123456789ABCDEF".charAt((b & JSONB.Constants.BC_INT32_NUM_MIN) >> 4)) + "0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }
}
