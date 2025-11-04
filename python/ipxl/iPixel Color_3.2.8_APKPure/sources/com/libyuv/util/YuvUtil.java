package com.libyuv.util;

/* loaded from: classes2.dex */
public class YuvUtil {
    public static native void yuvARGBMacToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvCompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5, int i6, boolean z);

    public static native void yuvCropI420(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5, int i6);

    public static native void yuvI420Copy(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToARGB(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToARGB1555(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToARGB4444(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToARGBMac(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToNV21(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvI420ToNv12(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvI420ToRGB24(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvI420ToRGB565(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvI420ToRGB565Android(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvI420ToRGBAIPhone(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToRGBAMac(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToUYVY(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToYUY2(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToYV12(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    @Deprecated
    public static native void yuvMirrorI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvMirrorI420LeftRight(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvMirrorI420UpDown(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvNV12ToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvNV12ToI420AndRotate(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public static native void yuvNV12ToRGB565(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvNV21ToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvNV21ToI420AndRotate(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public static native void yuvRGB24ToARGB(byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    public static native void yuvRGB24ToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvRotateI420(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public static native void yuvScaleI420(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5);

    public static native void yuvUYVYToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvYUY2ToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native void yuvYV12ToI420(byte[] bArr, int i, int i2, byte[] bArr2);

    static {
        System.loadLibrary("yuvutil");
    }
}
