package com.wifiled.baselib.retrofit;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.uicode.application.Core;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/* compiled from: NetWorkManager.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/wifiled/baselib/retrofit/NetWorkManager;", "", "<init>", "()V", "retrofit", "Lretrofit2/Retrofit;", "client", "Lokhttp3/OkHttpClient;", "MAX_RETRY", "", "refreshDnsCache", "", "isNetworkAvailable", "", "getClient", "getCacheInterceptor", "Lokhttp3/Interceptor;", "getRequest", "Lcom/wifiled/baselib/retrofit/ApiService;", "getStrRequest", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetWorkManager {
    public static final NetWorkManager INSTANCE = new NetWorkManager();
    private static final int MAX_RETRY = 3;
    private static final OkHttpClient client;
    private static Retrofit retrofit;

    private NetWorkManager() {
    }

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: com.wifiled.baselib.retrofit.NetWorkManager$$ExternalSyntheticLambda0
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response _init_$lambda$1;
                _init_$lambda$1 = NetWorkManager._init_$lambda$1(chain);
                return _init_$lambda$1;
            }
        }).addNetworkInterceptor(httpLoggingInterceptor).connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).writeTimeout(15L, TimeUnit.SECONDS).connectionPool(new ConnectionPool(5, 2L, TimeUnit.MINUTES)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response _init_$lambda$1(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Response response = null;
        for (int i = 0; i < 3 && response == null; i++) {
            try {
                response = chain.proceed(request);
            } catch (SocketTimeoutException e) {
                if (i >= 2) {
                    throw e;
                }
            } catch (UnknownHostException e2) {
                if (INSTANCE.isNetworkAvailable()) {
                    INSTANCE.refreshDnsCache();
                }
                throw e2;
            }
        }
        if (response != null) {
            return response;
        }
        throw new IOException("请求失败，重试次数超过限制");
    }

    private final void refreshDnsCache() {
        try {
            Runtime.getRuntime().exec("ipconfig /flushdns").waitFor();
        } catch (Exception e) {
            Log.w("Network", "刷新DNS缓存失败", e);
        }
    }

    private final boolean isNetworkAvailable() {
        Object systemService = Core.INSTANCE.getContext().getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final OkHttpClient getClient() {
        return client;
    }

    public final Interceptor getCacheInterceptor() {
        return new Interceptor() { // from class: com.wifiled.baselib.retrofit.NetWorkManager$$ExternalSyntheticLambda1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Response cacheInterceptor$lambda$2;
                cacheInterceptor$lambda$2 = NetWorkManager.getCacheInterceptor$lambda$2(chain);
                return cacheInterceptor$lambda$2;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response getCacheInterceptor$lambda$2(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request()).newBuilder().header("Cache-Control", "public,max-age=120").build();
    }

    public final ApiService getRequest() {
        Retrofit build = new Retrofit.Builder().client(client).baseUrl(Constance.API.BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        retrofit = build;
        if (build == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
            build = null;
        }
        Object create = build.create(ApiService.class);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return (ApiService) create;
    }

    public final ApiService getStrRequest() {
        Retrofit build = new Retrofit.Builder().client(client).baseUrl(Constance.API.BASE_URL2).addConverterFactory(ScalarsConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        retrofit = build;
        if (build == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
            build = null;
        }
        Object create = build.create(ApiService.class);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return (ApiService) create;
    }
}
