package com.wifiled.ipixels;

import android.app.ActivityManager;
import android.content.ComponentName;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.wifiled.baselib.CoreBase;
import com.wifiled.ipixels.ui.UpDataState;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMEngine;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AppConfig.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010!\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010Y\u001a\u00020/R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00140\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\u001a\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u000fR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010\u000fR$\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR$\u0010(\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR\u001a\u0010+\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\tR\u001a\u0010.\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103R\u001a\u00107\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0007\"\u0004\b9\u0010\tR\u001a\u0010:\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\u001a\u0010=\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0007\"\u0004\b>\u0010\tR\u001a\u0010?\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0007\"\u0004\b@\u0010\tR\u001a\u0010A\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0007\"\u0004\bC\u0010\tR\u001a\u0010D\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\r\"\u0004\bF\u0010\u000fR \u0010G\u001a\b\u0012\u0004\u0012\u00020/0HX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0016\"\u0004\bJ\u0010 R\u001a\u0010K\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\r\"\u0004\bM\u0010\u000fR$\u0010N\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\r\"\u0004\bP\u0010\u000fR$\u0010Q\u001a\u00020/2\u0006\u0010$\u001a\u00020/@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u00101\"\u0004\bS\u00103R$\u0010T\u001a\u00020/2\u0006\u0010$\u001a\u00020/@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00101\"\u0004\bV\u00103R\u001a\u0010W\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\u0007\"\u0004\bX\u0010\tR*\u0010Z\u001a\u0012\u0012\u0004\u0012\u00020\\0[j\b\u0012\u0004\u0012\u00020\\`]X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010a¨\u0006c"}, d2 = {"Lcom/wifiled/ipixels/AppConfig;", "", "<init>", "()V", "isOnly", "", "isExchange", "()Z", "setExchange", "(Z)V", "connectType", "", "getConnectType", "()I", "setConnectType", "(I)V", "connectType2", "getConnectType2", "setConnectType2", "ledSizeMap", "", "getLedSizeMap", "()Ljava/util/List;", "ledTextSize", "getLedTextSize", "setLedTextSize", "typeFace", "getTypeFace", "setTypeFace", "ledSize", "getLedSize", "setLedSize", "(Ljava/util/List;)V", "ledFrameSize", "getLedFrameSize", "setLedFrameSize", "value", "ledType", "getLedType", "setLedType", "ledHasWifi", "getLedHasWifi", "setLedHasWifi", "bledOn", "getBledOn", "setBledOn", "prevActivityName", "", "getPrevActivityName", "()Ljava/lang/String;", "setPrevActivityName", "(Ljava/lang/String;)V", "nextActivityName", "getNextActivityName", "setNextActivityName", "bCheckOta", "getBCheckOta", "setBCheckOta", "curConnectWifi", "getCurConnectWifi", "setCurConnectWifi", "isCancel", "setCancel", "isCurdataSendOver", "setCurdataSendOver", "bHasSwitchedBg", "getBHasSwitchedBg", "setBHasSwitchedBg", "pwdFlag", "getPwdFlag", "setPwdFlag", "connectList", "", "getConnectList", "setConnectList", "mcuVersion", "getMcuVersion", "setMcuVersion", "mcu", "getMcu", "setMcu", "cid", "getCid", "setCid", "pid", "getPid", "setPid", "isSend", "setSend", "getTopActivity", "arrayDevinfo", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getArrayDevinfo", "()Ljava/util/ArrayList;", "setArrayDevinfo", "(Ljava/util/ArrayList;)V", "SCENES", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AppConfig {
    private static ArrayList<Byte> arrayDevinfo;
    private static boolean bCheckOta;
    private static boolean bHasSwitchedBg;
    private static boolean bledOn;
    private static String cid;
    private static List<String> connectList;
    private static String curConnectWifi;
    private static boolean isCancel;
    private static boolean isCurdataSendOver;
    private static boolean isExchange;
    public static boolean isOnly;
    private static boolean isSend;
    private static int ledFrameSize;
    private static boolean ledHasWifi;
    private static List<Integer> ledSize;
    private static final List<List<Integer>> ledSizeMap;
    private static int ledTextSize;
    private static int ledType;
    private static int mcu;
    private static int mcuVersion;
    private static String nextActivityName;
    private static String pid;
    private static String prevActivityName;
    private static int pwdFlag;
    private static int typeFace;
    public static final AppConfig INSTANCE = new AppConfig();
    private static int connectType = -1;
    private static int connectType2 = -1;

    private AppConfig() {
    }

    public final boolean isExchange() {
        return isExchange;
    }

    public final void setExchange(boolean z) {
        isExchange = z;
    }

    static {
        List<List<Integer>> listOf = CollectionsKt.listOf((Object[]) new List[]{CollectionsKt.listOf((Object[]) new Integer[]{64, 64}), CollectionsKt.listOf((Object[]) new Integer[]{96, 16}), CollectionsKt.listOf((Object[]) new Integer[]{32, 32}), CollectionsKt.listOf((Object[]) new Integer[]{64, 16}), CollectionsKt.listOf((Object[]) new Integer[]{32, 16}), CollectionsKt.listOf((Object[]) new Integer[]{64, 20}), CollectionsKt.listOf((Object[]) new Integer[]{128, 32}), CollectionsKt.listOf((Object[]) new Integer[]{144, 16}), CollectionsKt.listOf((Object[]) new Integer[]{192, 16}), CollectionsKt.listOf((Object[]) new Integer[]{48, 24}), CollectionsKt.listOf((Object[]) new Integer[]{64, 32}), CollectionsKt.listOf((Object[]) new Integer[]{96, 32}), CollectionsKt.listOf((Object[]) new Integer[]{128, 32}), CollectionsKt.listOf((Object[]) new Integer[]{96, 32}), CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(Opcodes.IF_ICMPNE), 32}), CollectionsKt.listOf((Object[]) new Integer[]{192, 32}), CollectionsKt.listOf((Object[]) new Integer[]{256, 32}), CollectionsKt.listOf((Object[]) new Integer[]{320, 32}), CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(MLKEMEngine.KyberPolyBytes), 32}), CollectionsKt.listOf((Object[]) new Integer[]{448, 32})});
        ledSizeMap = listOf;
        ledTextSize = 32;
        List<Integer> list = listOf.get(0);
        ledSize = list;
        ledFrameSize = list.get(0).intValue() * ledSize.get(1).intValue() * 3;
        ledType = 1;
        bledOn = true;
        prevActivityName = "";
        nextActivityName = "";
        curConnectWifi = "";
        pwdFlag = -1;
        connectList = new ArrayList();
        mcu = 5;
        cid = "";
        pid = "";
        arrayDevinfo = new ArrayList<>();
    }

    public final int getConnectType() {
        return connectType;
    }

    public final void setConnectType(int i) {
        connectType = i;
    }

    public final int getConnectType2() {
        return connectType2;
    }

    public final void setConnectType2(int i) {
        connectType2 = i;
    }

    public final List<List<Integer>> getLedSizeMap() {
        return ledSizeMap;
    }

    public final int getLedTextSize() {
        return ledTextSize;
    }

    public final void setLedTextSize(int i) {
        ledTextSize = i;
    }

    public final int getTypeFace() {
        return typeFace;
    }

    public final void setTypeFace(int i) {
        typeFace = i;
    }

    public final List<Integer> getLedSize() {
        return ledSize;
    }

    public final void setLedSize(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        ledSize = list;
    }

    public final int getLedFrameSize() {
        return ledFrameSize;
    }

    public final void setLedFrameSize(int i) {
        ledFrameSize = i;
    }

    public final int getLedType() {
        return ledType;
    }

    public final void setLedType(int i) {
        ledType = i;
        List<Integer> list = ledSizeMap.get(i);
        ledSize = list;
        ledFrameSize = list.get(0).intValue() * ledSize.get(1).intValue() * 3;
        PreferenceSettings.INSTANCE.setLedType(i);
        EventBus.getDefault().post(new UpDataState());
    }

    public final boolean getLedHasWifi() {
        return ledHasWifi;
    }

    public final void setLedHasWifi(boolean z) {
        ledHasWifi = z;
        PreferenceSettings.INSTANCE.setLedHasWifi(z);
    }

    public final boolean getBledOn() {
        return bledOn;
    }

    public final void setBledOn(boolean z) {
        bledOn = z;
    }

    public final String getPrevActivityName() {
        return prevActivityName;
    }

    public final void setPrevActivityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        prevActivityName = str;
    }

    public final String getNextActivityName() {
        return nextActivityName;
    }

    public final void setNextActivityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        nextActivityName = str;
    }

    public final boolean getBCheckOta() {
        return bCheckOta;
    }

    public final void setBCheckOta(boolean z) {
        bCheckOta = z;
    }

    public final String getCurConnectWifi() {
        return curConnectWifi;
    }

    public final void setCurConnectWifi(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        curConnectWifi = str;
    }

    public final boolean isCancel() {
        return isCancel;
    }

    public final void setCancel(boolean z) {
        isCancel = z;
    }

    public final boolean isCurdataSendOver() {
        return isCurdataSendOver;
    }

    public final void setCurdataSendOver(boolean z) {
        isCurdataSendOver = z;
    }

    public final boolean getBHasSwitchedBg() {
        return bHasSwitchedBg;
    }

    public final void setBHasSwitchedBg(boolean z) {
        bHasSwitchedBg = z;
    }

    public final int getPwdFlag() {
        return pwdFlag;
    }

    public final void setPwdFlag(int i) {
        pwdFlag = i;
    }

    public final List<String> getConnectList() {
        return connectList;
    }

    public final void setConnectList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        connectList = list;
    }

    public final int getMcuVersion() {
        return mcuVersion;
    }

    public final void setMcuVersion(int i) {
        mcuVersion = i;
    }

    public final int getMcu() {
        return mcu;
    }

    public final void setMcu(int i) {
        mcu = i;
        PreferenceSettings.INSTANCE.setMcu(i);
    }

    public final String getCid() {
        return cid;
    }

    public final void setCid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        cid = value;
        PreferenceSettings.INSTANCE.setCid(value);
    }

    public final String getPid() {
        return pid;
    }

    public final void setPid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        pid = value;
        PreferenceSettings.INSTANCE.setPid(value);
    }

    public final boolean isSend() {
        return isSend;
    }

    public final void setSend(boolean z) {
        isSend = z;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: AppConfig.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/wifiled/ipixels/AppConfig$SCENES;", "", "<init>", "(Ljava/lang/String;I)V", "SCENES_TEXT_ATTR", "SCENES_TEXT_RECORD", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SCENES {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ SCENES[] $VALUES;
        public static final SCENES SCENES_TEXT_ATTR = new SCENES("SCENES_TEXT_ATTR", 0);
        public static final SCENES SCENES_TEXT_RECORD = new SCENES("SCENES_TEXT_RECORD", 1);

        private static final /* synthetic */ SCENES[] $values() {
            return new SCENES[]{SCENES_TEXT_ATTR, SCENES_TEXT_RECORD};
        }

        public static EnumEntries<SCENES> getEntries() {
            return $ENTRIES;
        }

        public static SCENES valueOf(String str) {
            return (SCENES) Enum.valueOf(SCENES.class, str);
        }

        public static SCENES[] values() {
            return (SCENES[]) $VALUES.clone();
        }

        private SCENES(String str, int i) {
        }

        static {
            SCENES[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final String getTopActivity() {
        try {
            Object systemService = CoreBase.getContext().getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ComponentName componentName = ((ActivityManager) systemService).getRunningTasks(1).get(0).topActivity;
            Intrinsics.checkNotNull(componentName);
            String className = componentName.getClassName();
            Intrinsics.checkNotNull(className);
            return className;
        } catch (Exception unused) {
            return "";
        }
    }

    public final ArrayList<Byte> getArrayDevinfo() {
        return arrayDevinfo;
    }

    public final void setArrayDevinfo(ArrayList<Byte> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        arrayDevinfo = arrayList;
    }
}
