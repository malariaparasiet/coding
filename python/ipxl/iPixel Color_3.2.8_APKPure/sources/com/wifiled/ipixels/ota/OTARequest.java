package com.wifiled.ipixels.ota;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.tiro.jlotalibrary.util.AppUtils;
import com.wifiled.baselib.retrofit.ApiService;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.OTAInformation;
import com.wifiled.baselib.retrofit.entity.Reply;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: OTARequest.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u001a\u001a\u00020\u0011J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u0017H\u0082@¢\u0006\u0002\u0010\u001dR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/ota/OTARequest;", "", "<init>", "()V", "bleSOTAData", "Lcom/wifiled/ipixels/ota/BleSOTAData;", "getBleSOTAData", "()Lcom/wifiled/ipixels/ota/BleSOTAData;", "setBleSOTAData", "(Lcom/wifiled/ipixels/ota/BleSOTAData;)V", "data", "", "getData", "()[B", "setData", "([B)V", "checkOTA", "", "context", "Landroid/content/Context;", "version", "", "cid", "", "pid", "macAddress", "unRegister", "getHttpToStream", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OTARequest {
    public static final OTARequest INSTANCE = new OTARequest();
    private static BleSOTAData bleSOTAData;
    private static byte[] data;

    private OTARequest() {
    }

    public final BleSOTAData getBleSOTAData() {
        return bleSOTAData;
    }

    public final void setBleSOTAData(BleSOTAData bleSOTAData2) {
        bleSOTAData = bleSOTAData2;
    }

    public final byte[] getData() {
        return data;
    }

    public final void setData(byte[] bArr) {
        data = bArr;
    }

    public final void checkOTA(final Context context, final int version, String cid, String pid, final String macAddress, BleSOTAData bleSOTAData2) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cid, "cid");
        Intrinsics.checkNotNullParameter(pid, "pid");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(bleSOTAData2, "bleSOTAData");
        bleSOTAData = bleSOTAData2;
        if (200 <= version && version < 300) {
            str = "TR2104R003_02";
        } else if (300 <= version && version < 400) {
            str = "TR2204R009_03";
        } else if (400 <= version && version < 500) {
            str = "TR2104R003_04";
        } else if (500 <= version && version < 600) {
            if (version != 508) {
                str = "TR2304R004-05";
            }
            str = "TR2304R004-16";
        } else if (700 <= version && version < 800) {
            str = "TR2104R003-07";
        } else if (800 <= version && version < 900) {
            str = "R2305R021_08";
        } else if (1000 <= version && version < 1100) {
            str = "TR2304R009-10";
        } else if (1300 > version || version >= 1400) {
            if (1600 > version || version >= 1700) {
                if (2800 <= version && version < 2900) {
                    str = "TR2408R015_28_" + cid + pid;
                } else if (2900 <= version && version < 3000) {
                    str = "TR2408R015_29_" + cid + pid;
                } else if (3000 <= version && version < 3100) {
                    str = "TR2504R005_30_" + cid + pid;
                } else if (3100 <= version && version < 3200) {
                    str = "TR2504R006_31_" + cid + pid;
                } else if (3200 <= version && version < 3300) {
                    str = "TR2504R007_32_" + cid + pid;
                } else if (3300 <= version && version < 3400) {
                    str = "TR2504R008_33_" + cid + pid;
                } else {
                    str = "";
                }
            }
            str = "TR2304R004-16";
        } else {
            str = "R2305R021_13";
        }
        String versionName = AppUtils.getVersionName(context);
        Log.v("ruis", "OTA请求升级信息=appVersion=" + versionName);
        if (str.length() == 0) {
            return;
        }
        ApiService request = NetWorkManager.INSTANCE.getRequest();
        String valueOf = String.valueOf(version);
        Intrinsics.checkNotNull(versionName);
        request.firmwareInfo("137", str, valueOf, versionName).enqueue((Callback) new Callback<Reply<? extends OTAInformation>>() { // from class: com.wifiled.ipixels.ota.OTARequest$checkOTA$1
            @Override // retrofit2.Callback
            public void onFailure(Call<Reply<? extends OTAInformation>> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<Reply<? extends OTAInformation>> call, Response<Reply<? extends OTAInformation>> response) {
                OTAInformation data2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Reply<? extends OTAInformation> body = response.body();
                if (body == null || (data2 = body.getData()) == null) {
                    return;
                }
                Context context2 = context;
                String str2 = macAddress;
                int i = version;
                Reply<? extends OTAInformation> body2 = response.body();
                Log.v("ruis", "response.body()=" + (body2 != null ? body2.getData() : null));
                if (Intrinsics.areEqual((Object) data2.getNeed_update(), (Object) true)) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new OTARequest$checkOTA$1$onResponse$1$1(data2, context2, str2, i, null), 3, null);
                }
            }
        });
    }

    public final void unRegister() {
        if (bleSOTAData != null) {
            bleSOTAData = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getHttpToStream(String str, Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new OTARequest$getHttpToStream$2(str, null), continuation);
    }
}
