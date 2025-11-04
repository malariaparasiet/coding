package org.bouncycastle.pqc.crypto.hqc;

import androidx.compose.runtime.ComposerKt;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.math.Primes;

/* loaded from: classes4.dex */
class GFCalculator {
    static int[] exp = {1, 2, 4, 8, 16, 32, 64, 128, 29, 58, 116, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, 205, Opcodes.I2D, 19, 38, 76, Opcodes.DCMPG, 45, 90, Opcodes.GETFIELD, 117, 234, ComposerKt.providerKey, Opcodes.D2L, 3, 6, 12, 24, 48, 96, 192, Opcodes.IFGT, 39, 78, Opcodes.IFGE, 37, 74, Opcodes.LCMP, 53, 106, Command.CMD_GET_DEV_MD5, Opcodes.PUTFIELD, 119, 238, Opcodes.INSTANCEOF, Opcodes.IF_ICMPEQ, 35, 70, Opcodes.F2L, 5, 10, 20, 40, 80, Opcodes.IF_ICMPNE, 93, 186, 105, 210, Opcodes.INVOKEINTERFACE, 111, 222, Opcodes.IF_ICMPLT, 95, Opcodes.ARRAYLENGTH, 97, 194, Opcodes.IFEQ, 47, 94, 188, 101, ComposerKt.compositionLocalMapKey, 137, 15, 30, 60, 120, 240, 253, Command.CMD_REBOOT_DEVICE, Primes.SMALL_FACTOR_LIMIT, Opcodes.NEW, 107, 214, Opcodes.RETURN, 127, 254, Command.CMD_OTA_GET_DEVICE_UPDATE_FILE_INFO_OFFSET, 223, Opcodes.IF_ICMPGT, 91, Opcodes.INVOKEVIRTUAL, 113, Command.CMD_OTA_INQUIRE_DEVICE_IF_CAN_UPDATE, 217, Opcodes.DRETURN, 67, Opcodes.I2F, 17, 34, 68, Opcodes.L2I, 13, 26, 52, 104, 208, Opcodes.ANEWARRAY, 103, ComposerKt.referenceKey, Opcodes.LOR, 31, 62, 124, 248, 237, Opcodes.IFNONNULL, Opcodes.I2S, 59, 118, 236, 197, Opcodes.DCMPL, 51, 102, ComposerKt.providerMapsKey, Opcodes.I2L, 23, 46, 92, Opcodes.INVOKESTATIC, 109, 218, Opcodes.RET, 79, Opcodes.IFLE, 33, 66, Opcodes.IINC, 21, 42, 84, Opcodes.JSR, 77, Opcodes.IFNE, 41, 82, Opcodes.IF_ICMPLE, 85, Opcodes.TABLESWITCH, 73, Opcodes.I2C, 57, 114, Command.CMD_OTA_EXIT_UPDATE_MODE, 213, Opcodes.INVOKESPECIAL, 115, Command.CMD_OTA_GET_DEVICE_REFRESH_FIRMWARE_STATUS, Command.CMD_SETTINGS_COMMUNICATION_MTU, Opcodes.ATHROW, 99, Opcodes.IFNULL, Opcodes.I2B, 63, 126, 252, Command.CMD_OTA_SEND_FIRMWARE_UPDATE_BLOCK, 215, Opcodes.PUTSTATIC, 123, 246, 241, 255, Command.CMD_OTA_ENTER_UPDATE_MODE, 219, Opcodes.LOOKUPSWITCH, 75, Opcodes.FCMPG, 49, 98, Command.CMD_ADV_DEV_REQUEST_OPERATION, Opcodes.FCMPL, 55, 110, 220, Opcodes.IF_ACMPEQ, 87, Opcodes.FRETURN, 65, Opcodes.IXOR, 25, 50, 100, 200, 141, 7, 14, 28, 56, 112, BERTags.FLAGS, 221, Opcodes.GOTO, 83, Opcodes.IF_ACMPNE, 81, Opcodes.IF_ICMPGE, 89, Opcodes.GETSTATIC, 121, 242, 249, 239, 195, Opcodes.IFLT, 43, 86, Opcodes.IRETURN, 69, 138, 9, 18, 36, 72, 144, 61, 122, 244, 245, 247, 243, 251, 235, ComposerKt.providerValuesKey, Opcodes.F2I, 11, 22, 44, 88, Opcodes.ARETURN, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 233, ComposerKt.reuseKey, Opcodes.LXOR, 27, 54, 108, 216, Opcodes.LRETURN, 71, Opcodes.D2I, 1, 2, 4};
    static int[] log = {0, 0, 1, 25, 2, 50, 26, Opcodes.IFNULL, 3, 223, 51, 238, 27, 104, Opcodes.IFNONNULL, 75, 4, 100, BERTags.FLAGS, 14, 52, 141, 239, Opcodes.LOR, 28, Opcodes.INSTANCEOF, 105, 248, 200, 8, 76, 113, 5, 138, 101, 47, Command.CMD_OTA_GET_DEVICE_UPDATE_FILE_INFO_OFFSET, 36, 15, 33, 53, Opcodes.I2S, Opcodes.D2I, 218, 240, 18, Opcodes.IXOR, 69, 29, Opcodes.PUTFIELD, 194, 125, 106, 39, 249, Opcodes.INVOKEINTERFACE, ComposerKt.providerKey, Opcodes.IFNE, 9, 120, 77, Command.CMD_OTA_EXIT_UPDATE_MODE, 114, Opcodes.IF_ACMPNE, 6, Opcodes.ATHROW, Opcodes.F2I, 98, 102, 221, 48, 253, Command.CMD_OTA_INQUIRE_DEVICE_IF_CAN_UPDATE, Opcodes.DCMPG, 37, Opcodes.PUTSTATIC, 16, Opcodes.I2B, 34, Opcodes.L2I, 54, 208, Opcodes.LCMP, ComposerKt.referenceKey, Opcodes.D2L, Opcodes.FCMPG, 219, Opcodes.ANEWARRAY, 241, 210, 19, 92, Opcodes.LXOR, 56, 70, 64, 30, 66, Opcodes.INVOKEVIRTUAL, Opcodes.IF_ICMPGT, 195, 72, 126, 110, 107, 58, 40, 84, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Opcodes.I2L, 186, 61, ComposerKt.compositionLocalMapKey, 94, Opcodes.IFLT, Opcodes.IF_ICMPEQ, 10, 21, 121, 43, 78, Command.CMD_GET_DEV_MD5, Command.CMD_OTA_SEND_FIRMWARE_UPDATE_BLOCK, Opcodes.IRETURN, 115, 243, Opcodes.GOTO, 87, 7, 112, 192, 247, Opcodes.F2L, 128, 99, 13, 103, 74, 222, 237, 49, 197, 254, 24, Command.CMD_OTA_ENTER_UPDATE_MODE, Opcodes.IF_ACMPEQ, Opcodes.IFEQ, 119, 38, Opcodes.INVOKESTATIC, Opcodes.GETFIELD, 124, 17, 68, Opcodes.I2C, 217, 35, 32, 137, 46, 55, 63, Command.CMD_SETTINGS_COMMUNICATION_MTU, 91, Opcodes.FCMPL, 188, ComposerKt.reuseKey, 205, 144, Opcodes.I2D, Opcodes.DCMPL, Opcodes.GETSTATIC, 220, 252, Opcodes.ARRAYLENGTH, 97, 242, 86, Primes.SMALL_FACTOR_LIMIT, Opcodes.LOOKUPSWITCH, 20, 42, 93, Opcodes.IFLE, Opcodes.IINC, 60, 57, 83, 71, 109, 65, Opcodes.IF_ICMPGE, 31, 45, 67, 216, Opcodes.INVOKESPECIAL, 123, Opcodes.IF_ICMPLE, 118, Command.CMD_ADV_DEV_REQUEST_OPERATION, 23, 73, 236, 127, 12, 111, 246, 108, Opcodes.IF_ICMPLT, 59, 82, 41, Opcodes.IFGT, 85, Opcodes.TABLESWITCH, 251, 96, Opcodes.I2F, Opcodes.RETURN, Opcodes.NEW, ComposerKt.providerMapsKey, 62, 90, ComposerKt.providerValuesKey, 89, 95, Opcodes.ARETURN, Opcodes.IFGE, Opcodes.RET, Opcodes.IF_ICMPNE, 81, 11, 245, 22, 235, 122, 117, 44, 215, 79, Opcodes.FRETURN, 213, 233, Command.CMD_OTA_GET_DEVICE_REFRESH_FIRMWARE_STATUS, Command.CMD_REBOOT_DEVICE, Opcodes.LRETURN, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, 116, 214, 244, 234, Opcodes.JSR, 80, 88, Opcodes.DRETURN};

    GFCalculator() {
    }

    static int inverse(int i) {
        return exp[255 - log[i]] & Utils.toUnsigned16Bits((-i) >> 31);
    }

    static int mod(int i) {
        int unsigned16Bits = Utils.toUnsigned16Bits(i - 255);
        return Utils.toUnsigned16Bits(unsigned16Bits + (Utils.toUnsigned8bits(-(unsigned16Bits >> 15)) & 255));
    }

    static int mult(int i, int i2) {
        int unsigned16Bits = Utils.toUnsigned16Bits((-i) >> 31) & Utils.toUnsigned16Bits((-i2) >> 31);
        int[] iArr = exp;
        int[] iArr2 = log;
        return Utils.toUnsigned16Bits(iArr[mod(iArr2[i] + iArr2[i2])] & unsigned16Bits);
    }
}
