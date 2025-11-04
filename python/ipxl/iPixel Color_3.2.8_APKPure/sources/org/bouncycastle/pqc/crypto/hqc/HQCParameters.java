package org.bouncycastle.pqc.crypto.hqc;

import androidx.compose.runtime.ComposerKt;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.pqc.crypto.KEMParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMEngine;

/* loaded from: classes4.dex */
public class HQCParameters implements KEMParameters {
    static final int GF_MUL_ORDER = 255;
    static final int PARAM_M = 8;
    public static final HQCParameters hqc128 = new HQCParameters("hqc-128", 17669, 46, MLKEMEngine.KyberPolyBytes, 16, 31, 15, 66, 75, 75, 16767881, 4, new int[]{89, 69, Opcodes.IFEQ, 116, Opcodes.ARETURN, 117, 111, 75, 73, 233, 242, 233, 65, 210, 21, Opcodes.F2I, 103, Opcodes.LRETURN, 67, 118, 105, 210, Opcodes.FRETURN, 110, 74, 69, Command.CMD_OTA_EXIT_UPDATE_MODE, 82, 255, Opcodes.PUTFIELD, 1});
    public static final HQCParameters hqc192 = new HQCParameters("hqc-192", 35851, 56, 640, 24, 33, 16, 100, 114, 114, 16742417, 5, new int[]{45, 216, 239, 24, 253, 104, 27, 40, 107, 50, Opcodes.IF_ICMPGT, 210, Command.CMD_OTA_ENTER_UPDATE_MODE, Opcodes.I2F, BERTags.FLAGS, Opcodes.IFLE, 119, 13, Opcodes.IFLE, 1, 238, Opcodes.IF_ICMPLE, 82, 43, 15, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, 246, Opcodes.D2I, 50, Opcodes.ANEWARRAY, 29, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, 1});
    public static final HQCParameters hqc256 = new HQCParameters("hqc-256", 57637, 90, 640, 32, 59, 29, Opcodes.LXOR, Opcodes.FCMPL, Opcodes.FCMPL, 16772367, 5, new int[]{49, Opcodes.GOTO, 49, 39, 200, 121, 124, 91, 240, 63, Opcodes.LCMP, 71, Opcodes.FCMPG, 123, 87, 101, 32, 215, Opcodes.IF_ICMPEQ, 71, ComposerKt.providerKey, 115, 97, 210, 186, Opcodes.INVOKESPECIAL, 141, 217, 123, 12, 31, 243, Opcodes.GETFIELD, 219, Opcodes.DCMPG, 239, 99, 141, 4, 246, Opcodes.ATHROW, 144, 8, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, 47, 27, 141, Opcodes.GETSTATIC, Opcodes.IXOR, 64, 124, 47, 39, 188, 216, 48, Opcodes.IFNONNULL, Opcodes.NEW, 1});
    private int delta;
    private int fft;
    private int g;
    private int[] generatorPoly;
    private HQCEngine hqcEngine;
    private int k;
    private int n;
    private int n1;
    private int n2;
    private final String name;
    private int utilRejectionThreshold;
    private int w;
    private int we;
    private int wr;

    private HQCParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        this.name = str;
        this.n = i;
        this.n1 = i2;
        this.n2 = i3;
        this.k = i4;
        this.delta = i6;
        this.w = i7;
        this.wr = i8;
        this.we = i9;
        this.generatorPoly = iArr;
        this.g = i5;
        this.utilRejectionThreshold = i10;
        this.fft = i11;
        this.hqcEngine = new HQCEngine(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, iArr);
    }

    int getDelta() {
        return this.delta;
    }

    HQCEngine getEngine() {
        return this.hqcEngine;
    }

    int getK() {
        return this.k;
    }

    int getN() {
        return this.n;
    }

    int getN1() {
        return this.n1;
    }

    int getN1N2_BYTES() {
        return ((this.n1 * this.n2) + 7) / 8;
    }

    int getN2() {
        return this.n2;
    }

    int getN_BYTES() {
        return (this.n + 7) / 8;
    }

    public String getName() {
        return this.name;
    }

    int getSALT_SIZE_BYTES() {
        return 16;
    }

    int getSHA512_BYTES() {
        return 64;
    }

    public int getSessionKeySize() {
        return 256;
    }

    int getW() {
        return this.w;
    }

    int getWe() {
        return this.we;
    }

    int getWr() {
        return this.wr;
    }
}
