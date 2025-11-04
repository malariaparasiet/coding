package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class NetworkFetcher {
    private final LottieNetworkFetcher fetcher;
    private final NetworkCache networkCache;

    public NetworkFetcher(NetworkCache networkCache, LottieNetworkFetcher lottieNetworkFetcher) {
        this.networkCache = networkCache;
        this.fetcher = lottieNetworkFetcher;
    }

    public LottieResult<LottieComposition> fetchSync(Context context, String str, String str2) {
        LottieComposition fetchFromCache = fetchFromCache(context, str, str2);
        if (fetchFromCache != null) {
            return new LottieResult<>(fetchFromCache);
        }
        Logger.debug("Animation for " + str + " not found in cache. Fetching from network.");
        return fetchFromNetwork(context, str, str2);
    }

    private LottieComposition fetchFromCache(Context context, String str, String str2) {
        NetworkCache networkCache;
        Pair<FileExtension, InputStream> fetch;
        LottieResult<LottieComposition> fromZipStreamSync;
        if (str2 == null || (networkCache = this.networkCache) == null || (fetch = networkCache.fetch(str)) == null) {
            return null;
        }
        FileExtension fileExtension = (FileExtension) fetch.first;
        InputStream inputStream = (InputStream) fetch.second;
        int i = AnonymousClass1.$SwitchMap$com$airbnb$lottie$network$FileExtension[fileExtension.ordinal()];
        if (i == 1) {
            fromZipStreamSync = LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), str2);
        } else if (i == 2) {
            try {
                fromZipStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), str2);
            } catch (IOException e) {
                fromZipStreamSync = new LottieResult<>(e);
            }
        } else {
            fromZipStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str2);
        }
        if (fromZipStreamSync.getValue() != null) {
            return fromZipStreamSync.getValue();
        }
        return null;
    }

    /* renamed from: com.airbnb.lottie.network.NetworkFetcher$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$network$FileExtension;

        static {
            int[] iArr = new int[FileExtension.values().length];
            $SwitchMap$com$airbnb$lottie$network$FileExtension = iArr;
            try {
                iArr[FileExtension.ZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$network$FileExtension[FileExtension.GZIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004c, code lost:
    
        if (r2 != null) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.airbnb.lottie.LottieResult<com.airbnb.lottie.LottieComposition> fetchFromNetwork(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            r10 = this;
            java.lang.String r1 = "LottieFetchResult close failed "
            java.lang.String r0 = "Completed fetch from network. Success: "
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Fetching "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r12)
            java.lang.String r2 = r2.toString()
            com.airbnb.lottie.utils.Logger.debug(r2)
            r2 = 0
            com.airbnb.lottie.network.LottieNetworkFetcher r3 = r10.fetcher     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            com.airbnb.lottie.network.LottieFetchResult r2 = r3.fetchSync(r12)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            boolean r3 = r2.isSuccessful()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r3 == 0) goto L58
            java.io.InputStream r7 = r2.bodyByteStream()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.String r8 = r2.contentType()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r4 = r10
            r5 = r11
            r6 = r12
            r9 = r13
            com.airbnb.lottie.LottieResult r11 = r4.fromInputStream(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r12.<init>(r0)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.Object r13 = r11.getValue()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r13 == 0) goto L40
            r13 = 1
            goto L41
        L40:
            r13 = 0
        L41:
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            com.airbnb.lottie.utils.Logger.debug(r12)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r2 == 0) goto L57
        L4e:
            r2.close()     // Catch: java.io.IOException -> L52
            return r11
        L52:
            r0 = move-exception
            r12 = r0
            com.airbnb.lottie.utils.Logger.warning(r1, r12)
        L57:
            return r11
        L58:
            com.airbnb.lottie.LottieResult r11 = new com.airbnb.lottie.LottieResult     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            java.lang.String r13 = r2.error()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r2 == 0) goto L69
            goto L4e
        L69:
            return r11
        L6a:
            r0 = move-exception
            r11 = r0
            goto L80
        L6d:
            r0 = move-exception
            r11 = r0
            com.airbnb.lottie.LottieResult r12 = new com.airbnb.lottie.LottieResult     // Catch: java.lang.Throwable -> L6a
            r12.<init>(r11)     // Catch: java.lang.Throwable -> L6a
            if (r2 == 0) goto L7f
            r2.close()     // Catch: java.io.IOException -> L7a
            goto L7f
        L7a:
            r0 = move-exception
            r11 = r0
            com.airbnb.lottie.utils.Logger.warning(r1, r11)
        L7f:
            return r12
        L80:
            if (r2 == 0) goto L8b
            r2.close()     // Catch: java.io.IOException -> L86
            goto L8b
        L86:
            r0 = move-exception
            r12 = r0
            com.airbnb.lottie.utils.Logger.warning(r1, r12)
        L8b:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkFetcher.fetchFromNetwork(android.content.Context, java.lang.String, java.lang.String):com.airbnb.lottie.LottieResult");
    }

    private LottieResult<LottieComposition> fromInputStream(Context context, String str, InputStream inputStream, String str2, String str3) throws IOException {
        LottieResult<LottieComposition> fromZipStream;
        FileExtension fileExtension;
        NetworkCache networkCache;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Handling zip response.");
            FileExtension fileExtension2 = FileExtension.ZIP;
            fromZipStream = fromZipStream(context, str, inputStream, str3);
            fileExtension = fileExtension2;
        } else if (str2.contains("application/gzip") || str2.contains("application/x-gzip") || str.split("\\?")[0].endsWith(".tgs")) {
            Logger.debug("Handling gzip response.");
            fileExtension = FileExtension.GZIP;
            fromZipStream = fromGzipStream(str, inputStream, str3);
        } else {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            fromZipStream = fromJsonStream(str, inputStream, str3);
        }
        if (str3 != null && fromZipStream.getValue() != null && (networkCache = this.networkCache) != null) {
            networkCache.renameTempFile(str, fileExtension);
        }
        return fromZipStream;
    }

    private LottieResult<LottieComposition> fromZipStream(Context context, String str, InputStream inputStream, String str2) throws IOException {
        NetworkCache networkCache;
        if (str2 == null || (networkCache = this.networkCache) == null) {
            return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), (String) null);
        }
        return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, FileExtension.ZIP))), str);
    }

    private LottieResult<LottieComposition> fromGzipStream(String str, InputStream inputStream, String str2) throws IOException {
        NetworkCache networkCache;
        if (str2 == null || (networkCache = this.networkCache) == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), null);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, FileExtension.GZIP))), str);
    }

    private LottieResult<LottieComposition> fromJsonStream(String str, InputStream inputStream, String str2) throws IOException {
        NetworkCache networkCache;
        if (str2 == null || (networkCache = this.networkCache) == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, FileExtension.JSON).getAbsolutePath()), str);
    }
}
