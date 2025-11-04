package com.wifiled.baselib.uicode.net;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.wifiled.baselib.uicode.net.HttpsUtils;
import com.wifiled.baselib.uicode.net.NetManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: NetManager.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\t¢\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wifiled/baselib/uicode/net/NetManager;", "", "<init>", "()V", "retrofit", "Lretrofit2/Retrofit;", "create", ExifInterface.GPS_DIRECTION_TRUE, NotificationCompat.CATEGORY_SERVICE, "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "Builer", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetManager {
    public static final NetManager INSTANCE = new NetManager();
    private static Retrofit retrofit;

    private NetManager() {
    }

    public final <T> T create(Class<T> service) {
        Intrinsics.checkNotNullParameter(service, "service");
        Retrofit retrofit3 = retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
            retrofit3 = null;
        }
        return (T) retrofit3.create(service);
    }

    /* compiled from: NetManager.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u000eJ\u0006\u0010\u0017\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/wifiled/baselib/uicode/net/NetManager$Builer;", "", "baseUrl", "", "outTime", "", "<init>", "(Ljava/lang/String;J)V", "getBaseUrl", "()Ljava/lang/String;", "getOutTime", "()J", "interceptors", "Ljava/util/ArrayList;", "Lokhttp3/Interceptor;", "Lkotlin/collections/ArrayList;", "callAdapterFactory", "Lretrofit2/CallAdapter$Factory;", "addInterceptor", "interceptor", "addCallAdapterFactory", "factory", "getCacheInterceptor", "build", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builer {
        private final String baseUrl;
        private CallAdapter.Factory callAdapterFactory;
        private ArrayList<Interceptor> interceptors;
        private final long outTime;

        public Builer(String baseUrl, long j) {
            Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
            this.baseUrl = baseUrl;
            this.outTime = j;
            this.interceptors = new ArrayList<>();
        }

        public /* synthetic */ Builer(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? 15L : j);
        }

        public final String getBaseUrl() {
            return this.baseUrl;
        }

        public final long getOutTime() {
            return this.outTime;
        }

        public final Builer addInterceptor(Interceptor interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            this.interceptors.add(interceptor);
            return this;
        }

        public final Builer addCallAdapterFactory(CallAdapter.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.callAdapterFactory = factory;
            return this;
        }

        public final Interceptor getCacheInterceptor() {
            return new Interceptor() { // from class: com.wifiled.baselib.uicode.net.NetManager$Builer$$ExternalSyntheticLambda0
                @Override // okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    Response cacheInterceptor$lambda$0;
                    cacheInterceptor$lambda$0 = NetManager.Builer.getCacheInterceptor$lambda$0(chain);
                    return cacheInterceptor$lambda$0;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Response getCacheInterceptor$lambda$0(Interceptor.Chain chain) {
            Intrinsics.checkNotNullParameter(chain, "chain");
            return chain.proceed(chain.request()).newBuilder().header("Cache-Control", "public,max-age=120").build();
        }

        public final void build() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Iterator<Interceptor> it = this.interceptors.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Interceptor next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                builder.addInterceptor(next);
            }
            builder.connectTimeout(this.outTime, TimeUnit.SECONDS);
            builder.readTimeout(this.outTime, TimeUnit.SECONDS);
            builder.writeTimeout(this.outTime, TimeUnit.SECONDS);
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory();
            SSLSocketFactory sslSocketFactory2 = sslSocketFactory.sslSocketFactory;
            Intrinsics.checkNotNullExpressionValue(sslSocketFactory2, "sslSocketFactory");
            X509TrustManager trustManager = sslSocketFactory.trustManager;
            Intrinsics.checkNotNullExpressionValue(trustManager, "trustManager");
            builder.sslSocketFactory(sslSocketFactory2, trustManager);
            OkHttpClient build = builder.build();
            if (this.callAdapterFactory != null) {
                NetManager netManager = NetManager.INSTANCE;
                Retrofit.Builder addConverterFactory = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
                CallAdapter.Factory factory = this.callAdapterFactory;
                Intrinsics.checkNotNull(factory);
                Retrofit build2 = addConverterFactory.addCallAdapterFactory(factory).baseUrl(this.baseUrl).client(build).build();
                Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
                NetManager.retrofit = build2;
                return;
            }
            NetManager netManager2 = NetManager.INSTANCE;
            Retrofit build3 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(this.baseUrl).client(build).build();
            Intrinsics.checkNotNullExpressionValue(build3, "build(...)");
            NetManager.retrofit = build3;
        }
    }
}
