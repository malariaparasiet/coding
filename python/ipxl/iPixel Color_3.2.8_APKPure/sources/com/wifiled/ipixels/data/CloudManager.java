package com.wifiled.ipixels.data;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.TimeUtils;
import com.wifiled.ipixels.data.Constance;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* compiled from: CloudManager.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J.\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007J\u0016\u0010 \u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"¨\u0006#"}, d2 = {"Lcom/wifiled/ipixels/data/CloudManager;", "", "<init>", "()V", "getCloudMaterial", "Lcom/wifiled/baselib/retrofit/entity/CloudRes;", "type", "", AnnotatedPrivateKey.LABEL, "categoryName", "width", "", "height", "page", "count", Constance.SP.LANGUAGE, "filter_tags", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadInformation", "", "context", "Landroid/content/Context;", "cidStr", "pidStr", "uploadappslog", "time", "mcu", "bleVersion", "deviceType", "getHttpToStream", "Ljava/io/InputStream;", ImagesContract.URL, "getHttpToBase64", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Callback;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CloudManager {
    public static final CloudManager INSTANCE = new CloudManager();

    private CloudManager() {
    }

    public final Object getCloudMaterial(String str, String str2, String str3, int i, int i2, int i3, int i4, String str4, String str5, Continuation<? super CloudRes> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new CloudManager$getCloudMaterial$2(i3, i4, str3, str, str2, i, i2, str4, str5, null), continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void uploadInformation(final Context context, String cidStr, String pidStr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cidStr, "cidStr");
        Intrinsics.checkNotNullParameter(pidStr, "pidStr");
        if (((Boolean) SPUtils.get(context, "uploadInformation", false)).booleanValue()) {
            return;
        }
        OkHttpClient client = NetWorkManager.INSTANCE.getClient();
        FormBody.Builder builder = new FormBody.Builder(null, 1, null == true ? 1 : 0);
        builder.add("app_package", Constance.AppInformation.INSTANCE.packageName());
        String appMetaData = AppUtils.getAppMetaData(context, "HEATON_CHANNEL");
        Intrinsics.checkNotNullExpressionValue(appMetaData, "getAppMetaData(...)");
        builder.add("app_channel", appMetaData);
        builder.add("phone_system", Constance.APP.PLATFORM);
        builder.add("phone_brands", Constance.AppInformation.INSTANCE.brand());
        builder.add("phone_model", Constance.AppInformation.INSTANCE.model());
        builder.add("phone_system_version", Constance.AppInformation.INSTANCE.release());
        String nowTime = TimeUtils.getNowTime();
        Intrinsics.checkNotNullExpressionValue(nowTime, "getNowTime(...)");
        builder.add("run_time", nowTime);
        builder.add("return_symbolize", cidStr + pidStr);
        client.newCall(new Request.Builder().url("http://api.e-toys.cn/api/app/count").post(builder.build()).build()).enqueue(new Callback() { // from class: com.wifiled.ipixels.data.CloudManager$uploadInformation$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                if (StringsKt.contains$default((CharSequence) body.string(), (CharSequence) "\"status\":0", false, 2, (Object) null)) {
                    SPUtils.put(context, "uploadInformation", true);
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void uploadappslog(Context context, String time, String mcu, String bleVersion, String deviceType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(mcu, "mcu");
        Intrinsics.checkNotNullParameter(bleVersion, "bleVersion");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        OkHttpClient client = NetWorkManager.INSTANCE.getClient();
        FormBody.Builder builder = new FormBody.Builder(null, 1, 0 == true ? 1 : 0);
        builder.add("type", "连接时长");
        builder.add("log_content", time);
        builder.add("app_version", String.valueOf(AppUtils.getVersionCode(context)));
        builder.add("app_package", Constance.AppInformation.INSTANCE.packageName());
        builder.add("app_channel", Constance.AppInformation.INSTANCE.channel());
        builder.add("phone_system", Constance.APP.PLATFORM);
        builder.add("phone_brands", Constance.AppInformation.INSTANCE.brand());
        builder.add("phone_model", Constance.AppInformation.INSTANCE.model());
        builder.add("phone_system_version", Constance.AppInformation.INSTANCE.release());
        builder.add("mcu_version", mcu);
        builder.add("ble_version", bleVersion);
        builder.add("device_type", deviceType);
        String uniqueDeviceId = DeviceUtils.getUniqueDeviceId();
        Intrinsics.checkNotNullExpressionValue(uniqueDeviceId, "getUniqueDeviceId(...)");
        builder.add("phone_guid", uniqueDeviceId);
        client.newCall(new Request.Builder().url("https://manage.heaton.com.cn/api/apps/appslog").post(builder.build()).build()).enqueue(new Callback() { // from class: com.wifiled.ipixels.data.CloudManager$uploadappslog$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final InputStream getHttpToStream(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ResponseBody body = new OkHttpClient().newCall(new Request.Builder().url(url).header("Connection", "close").build()).execute().body();
        Intrinsics.checkNotNull(body);
        return body.byteStream();
    }

    public final void getHttpToBase64(String url, Callback call) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(call, "call");
        LogUtils.vTag("ruis", " getHttpToBase64 " + url);
        NetWorkManager.INSTANCE.getClient().newCall(new Request.Builder().url(url).build()).enqueue(call);
    }
}
