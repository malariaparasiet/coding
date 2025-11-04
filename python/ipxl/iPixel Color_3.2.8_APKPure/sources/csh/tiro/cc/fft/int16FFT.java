package csh.tiro.cc.fft;

/* loaded from: classes3.dex */
public class int16FFT {
    public static native void BitReverse(short[] sArr);

    public static native void IntFFT(short[] sArr, short[] sArr2);

    public static native void WindowCalc(short[] sArr, char c);

    public static float getPowerlevel(byte[] bArr) {
        return 0.0f;
    }

    static {
        System.loadLibrary("int16fft");
    }
}
