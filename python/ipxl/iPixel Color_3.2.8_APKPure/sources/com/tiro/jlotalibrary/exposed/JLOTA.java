package com.tiro.jlotalibrary.exposed;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiro.jlotalibrary.util.AppUtils;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.OTAInformation;
import com.wifiled.baselib.retrofit.entity.Reply;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: JLOTA.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0005J\u0018\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010!\u001a\u00020\u0012H\u0082@¢\u0006\u0002\u0010\"R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/tiro/jlotalibrary/exposed/JLOTA;", "", "<init>", "()V", "bleSOTAData", "Lcom/tiro/jlotalibrary/exposed/BleSOTAData;", "getBleSOTAData", "()Lcom/tiro/jlotalibrary/exposed/BleSOTAData;", "setBleSOTAData", "(Lcom/tiro/jlotalibrary/exposed/BleSOTAData;)V", "data", "", "getData", "()[B", "setData", "([B)V", "chache", "Ljava/util/LinkedHashSet;", "", "Lkotlin/collections/LinkedHashSet;", "getChache", "()Ljava/util/LinkedHashSet;", "setChache", "(Ljava/util/LinkedHashSet;)V", "checkOTA", "", "context", "Landroid/content/Context;", "version", "", "cid", "pid", "getHttpToStream", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "jlotalibrary_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JLOTA {
    public static BleSOTAData bleSOTAData;
    public static byte[] data;
    public static final JLOTA INSTANCE = new JLOTA();
    private static LinkedHashSet<String> chache = new LinkedHashSet<>();

    private JLOTA() {
    }

    public final BleSOTAData getBleSOTAData() {
        BleSOTAData bleSOTAData2 = bleSOTAData;
        if (bleSOTAData2 != null) {
            return bleSOTAData2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bleSOTAData");
        return null;
    }

    public final void setBleSOTAData(BleSOTAData bleSOTAData2) {
        Intrinsics.checkNotNullParameter(bleSOTAData2, "<set-?>");
        bleSOTAData = bleSOTAData2;
    }

    public final byte[] getData() {
        byte[] bArr = data;
        if (bArr != null) {
            return bArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final void setData(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        data = bArr;
    }

    public final LinkedHashSet<String> getChache() {
        return chache;
    }

    public final void setChache(LinkedHashSet<String> linkedHashSet) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<set-?>");
        chache = linkedHashSet;
    }

    public final void checkOTA(final Context context, final int version, String cid, String pid, BleSOTAData bleSOTAData2) {
        BluetoothDevice device;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cid, "cid");
        Intrinsics.checkNotNullParameter(pid, "pid");
        Intrinsics.checkNotNullParameter(bleSOTAData2, "bleSOTAData");
        setBleSOTAData(bleSOTAData2);
        BluetoothGatt gatt = bleSOTAData2.getGatt();
        if (gatt == null || (device = gatt.getDevice()) == null || chache.contains(device.getAddress())) {
            return;
        }
        if (1100 <= version && version < 1200) {
            str = "R2305R021_11_" + cid + pid;
        } else if (1200 <= version && version < 1300) {
            str = "TR2304R009-12_" + cid + pid;
        } else if (1400 <= version && version < 1500) {
            str = "R2305R021_14_" + cid + pid;
        } else if (1500 <= version && version < 1600) {
            str = "R2305R021_15_" + cid + pid;
        } else if (1700 <= version && version < 1800) {
            str = "TR2311R003_2_" + cid + pid;
        } else if (1800 <= version && version < 1900) {
            str = "TR2311R002_1_" + cid + pid;
        } else if (1900 <= version && version < 2000) {
            str = "TR2403R015_19_" + cid + pid;
        } else if (2100 <= version && version < 2200) {
            str = "TR2403R015_21_" + cid + pid;
        } else if (2200 <= version && version < 2300) {
            str = "TR2408R015_22_" + cid + pid;
        } else if (2300 <= version && version < 2400) {
            str = "TR2408R015_23_" + cid + pid;
        } else if (2400 <= version && version < 2500) {
            str = "TR2408R015_24_" + cid + pid;
        } else if (2500 <= version && version < 2600) {
            str = "TR2408R015_25_" + cid + pid;
        } else if (2600 <= version && version < 2700) {
            str = "TR2408R015_26_" + cid + pid;
        } else if (2700 <= version && version < 2800) {
            str = "TR2408R015_27_" + cid + pid;
        } else {
            str = "";
        }
        LogUtils.vTag("ruis", "startOTA projectNo=" + cid + " " + pid);
        String versionName = AppUtils.getVersionName(context);
        if (str.length() == 0) {
            return;
        }
        Map mapOf = MapsKt.mapOf(TuplesKt.to("appid", "137"), TuplesKt.to("project_no", str), TuplesKt.to("version", String.valueOf(version)), TuplesKt.to("android_version", versionName));
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String generateRandomString$default = CryptographicParsingToolKt.generateRandomString$default(0, 1, null);
        NetWorkManager.INSTANCE.getStrRequest().newFirmwareInfo(CryptographicParsingToolKt.generateSortedQueryString(mapOf, generateRandomString$default, valueOf), valueOf, generateRandomString$default, CryptographicParsingToolKt.generateSortedQueryAESString(mapOf)).enqueue(new Callback<String>() { // from class: com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3
            @Override // retrofit2.Callback
            public void onResponse(Call<String> call, Response<String> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                try {
                    String body = response.body();
                    if (body == null) {
                        body = "";
                    }
                    Object fromJson = new Gson().fromJson(CryptographicParsingToolKt.decryptQueryAESString(body), new TypeToken<Reply<? extends OTAInformation>>() { // from class: com.tiro.jlotalibrary.exposed.JLOTA$checkOTA$3$onResponse$replyType$1
                    }.getType());
                    Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
                    OTAInformation oTAInformation = (OTAInformation) ((Reply) fromJson).getData();
                    if (oTAInformation != null) {
                        Context context2 = context;
                        int i = version;
                        if (Intrinsics.areEqual((Object) oTAInformation.getNeed_update(), (Object) true)) {
                            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new JLOTA$checkOTA$3$onResponse$1$1(oTAInformation, context2, i, null), 3, null);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<String> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                LogUtils.vTag("JLOTA -onFailure  -" + call + "   " + t.getMessage(), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getHttpToStream(String str, Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new JLOTA$getHttpToStream$2(str, null), continuation);
    }
}
