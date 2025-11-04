package com.wifiled.baselib.retrofit;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.retrofit.entity.BluetoothFilterVo;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.retrofit.entity.OTAInformation;
import com.wifiled.baselib.retrofit.entity.Reply;
import com.wifiled.baselib.utils.AppUtils;
import com.wifiled.ipixels.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u0007H'J(\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0003\u0010\r\u001a\u00020\u000e2\b\b\u0003\u0010\u000f\u001a\u00020\u0007H'J6\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00072\b\b\u0001\u0010\u0014\u001a\u00020\u0007H'J6\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0001\u0010\u0017\u001a\u00020\u00072\b\b\u0001\u0010\u0018\u001a\u00020\u00072\b\b\u0001\u0010\u0019\u001a\u00020\u0007H'J6\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0001\u0010\u0017\u001a\u00020\u00072\b\b\u0001\u0010\u0018\u001a\u00020\u00072\b\b\u0001\u0010\u0019\u001a\u00020\u0007H'Jh\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00072\b\b\u0001\u0010\u001c\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00072\b\b\u0003\u0010\u001e\u001a\u00020\u00072\b\b\u0003\u0010\u001f\u001a\u00020\u00072\b\b\u0003\u0010 \u001a\u00020\u00072\b\b\u0001\u0010!\u001a\u00020\u00072\b\b\u0001\u0010\"\u001a\u00020\u00072\b\b\u0001\u0010#\u001a\u00020\u0007H'J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010&\u001a\u00020\u00072\b\b\u0001\u0010'\u001a\u00020\u0007H'¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/retrofit/ApiService;", "", "firmwareInfo", "Lretrofit2/Call;", "Lcom/wifiled/baselib/retrofit/entity/Reply;", "Lcom/wifiled/baselib/retrofit/entity/OTAInformation;", "appid", "", "projectNo", "version", "android_version", "bluetoothFilter", "Lcom/wifiled/baselib/retrofit/entity/BluetoothFilterVo;", "app_type", "", "app_package", "resourceDownloadStatistics", "Lcom/wifiled/baselib/retrofit/entity/CloudRes;", HintConstants.AUTOFILL_HINT_PHONE, "country", "imageData", "newCloudMaterial", "sign", "timestamp", "random", "body", "newFirmwareInfo", "uploadCrash", "app_channel", "phone_system", "phone_brands", "phone_model", "phone_system_version", "app_version_name", "app_version_code", "exception_info", "download", "Lokhttp3/ResponseBody;", "range", ImagesContract.URL, "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ApiService {
    @FormUrlEncoded
    @POST("https://api.e-toys.cn/api/app/bluetoothFilter")
    Call<Reply<BluetoothFilterVo>> bluetoothFilter(@Field("app_type") int app_type, @Field("app_package") String app_package);

    @Streaming
    @GET
    Call<ResponseBody> download(@Header("Range") String range, @Url String url);

    @FormUrlEncoded
    @POST("getFirmwareInfo")
    Call<Reply<OTAInformation>> firmwareInfo(@Field("appid") String appid, @Field("project_no") String projectNo, @Field("version") String version, @Field("android_version") String android_version);

    @Headers({"Content-Type: text/plain"})
    @POST("getMaterialUnderCategory")
    Call<String> newCloudMaterial(@Query("sign") String sign, @Query("timestamp") String timestamp, @Query("random") String random, @Body String body);

    @Headers({"Content-Type: text/plain"})
    @POST("getFirmwareInfo")
    Call<String> newFirmwareInfo(@Query("sign") String sign, @Query("timestamp") String timestamp, @Query("random") String random, @Body String body);

    @FormUrlEncoded
    @POST("resourceDownloadStatistics")
    Call<CloudRes> resourceDownloadStatistics(@Field("app_id") String appid, @Field("phone_identification") String phone, @Field("country") String country, @Field("images") String imageData);

    @FormUrlEncoded
    @POST("https://api.e-toys.cn/api/app/add_app_crash")
    Call<CloudRes> uploadCrash(@Field("app_package") String app_package, @Field("app_channel") String app_channel, @Field("phone_system") String phone_system, @Field("phone_brands") String phone_brands, @Field("phone_model") String phone_model, @Field("phone_system_version") String phone_system_version, @Field("app_version_name") String app_version_name, @Field("app_version_code") String app_version_code, @Field("exception_info") String exception_info);

    /* compiled from: ApiService.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Call firmwareInfo$default(ApiService apiService, String str, String str2, String str3, String str4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: firmwareInfo");
        }
        if ((i & 1) != 0) {
            str = "137";
        }
        return apiService.firmwareInfo(str, str2, str3, str4);
    }

    static /* synthetic */ Call bluetoothFilter$default(ApiService apiService, int i, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bluetoothFilter");
        }
        if ((i2 & 1) != 0) {
            i = 2;
        }
        if ((i2 & 2) != 0) {
            str = BuildConfig.APPLICATION_ID;
        }
        return apiService.bluetoothFilter(i, str);
    }

    static /* synthetic */ Call resourceDownloadStatistics$default(ApiService apiService, String str, String str2, String str3, String str4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resourceDownloadStatistics");
        }
        if ((i & 1) != 0) {
            str = "137";
        }
        return apiService.resourceDownloadStatistics(str, str2, str3, str4);
    }

    static /* synthetic */ Call uploadCrash$default(ApiService apiService, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, Object obj) {
        String str10;
        String str11;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: uploadCrash");
        }
        if ((i & 4) != 0) {
            str3 = Constance.APP.PLATFORM;
        }
        String str12 = str3;
        if ((i & 8) != 0) {
            str4 = AppUtils.getDeviceBrand();
            Intrinsics.checkNotNullExpressionValue(str4, "getDeviceBrand(...)");
        }
        String str13 = str4;
        if ((i & 16) != 0) {
            String systemModel = AppUtils.getSystemModel();
            Intrinsics.checkNotNullExpressionValue(systemModel, "getSystemModel(...)");
            str10 = systemModel;
        } else {
            str10 = str5;
        }
        if ((i & 32) != 0) {
            String systemVersion = AppUtils.getSystemVersion();
            Intrinsics.checkNotNullExpressionValue(systemVersion, "getSystemVersion(...)");
            str11 = systemVersion;
        } else {
            str11 = str6;
        }
        return apiService.uploadCrash(str, str2, str12, str13, str10, str11, str7, str8, str9);
    }
}
