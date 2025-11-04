package com.wifiled.baselib.retrofit;

import android.content.Context;
import android.util.Base64;
import com.blankj.utilcode.util.EncryptUtils;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.ImagesContract;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* compiled from: CryptographicParsingTool.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0007\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003\u001a\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0002\u001a\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u001a*\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003\u001a\u001a\u0010\u0016\u001a\u00020\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0013\u001a\u000e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0002¨\u0006\u001a"}, d2 = {"getDecryptedFile", "Ljava/io/File;", "encryptedUrl", "", "imgType", "", "context", "Landroid/content/Context;", "safeUrlDecode", "input", "downloadEncryptedString", "urlString", "getHttpToStream", "Ljava/io/InputStream;", ImagesContract.URL, "generateRandomString", "length", "generateSortedQueryString", "actualParams", "", "mRandom", "mTime", "generateSortedQueryAESString", "decryptQueryAESString", "data", "encodeToURL", "baselib_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CryptographicParsingToolKt {
    public static final File getDecryptedFile(String encryptedUrl, int i, Context context) throws Exception {
        String safeUrlDecode;
        Intrinsics.checkNotNullParameter(encryptedUrl, "encryptedUrl");
        Intrinsics.checkNotNullParameter(context, "context");
        String str = i == 1 ? ".gif" : ".jpg";
        String downloadEncryptedString = downloadEncryptedString(encryptedUrl);
        if (downloadEncryptedString.length() <= 64) {
            return null;
        }
        String substring = downloadEncryptedString.substring(32, downloadEncryptedString.length() - 32);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String replace$default = StringsKt.replace$default(substring, "+", " ", false, 4, (Object) null);
        try {
            safeUrlDecode = URLDecoder.decode(replace$default, Key.STRING_CHARSET_NAME);
            Intrinsics.checkNotNull(safeUrlDecode);
        } catch (IllegalArgumentException unused) {
            safeUrlDecode = safeUrlDecode(replace$default);
        }
        String sb = new StringBuilder(safeUrlDecode).reverse().toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        String str2 = sb;
        int length = str2.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i2 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        byte[] decode = Base64.decode(StringsKt.replace$default(StringsKt.replace$default(str2.subSequence(i2, length + 1).toString(), "\r", "", false, 4, (Object) null), "\n", "", false, 4, (Object) null), 0);
        File createTempFile = File.createTempFile("decrypted_", str, context.getCacheDir());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            try {
                fileOutputStream.write(decode);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
                return createTempFile;
            } finally {
            }
        } finally {
            createTempFile.deleteOnExit();
        }
    }

    public static final String safeUrlDecode(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String decode = URLDecoder.decode(new Regex("%(?![0-9a-fA-F]{2})").replace(input, new Function1() { // from class: com.wifiled.baselib.retrofit.CryptographicParsingToolKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence safeUrlDecode$lambda$2;
                safeUrlDecode$lambda$2 = CryptographicParsingToolKt.safeUrlDecode$lambda$2((MatchResult) obj);
                return safeUrlDecode$lambda$2;
            }
        }), Key.STRING_CHARSET_NAME);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return decode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence safeUrlDecode$lambda$2(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Character orNull = StringsKt.getOrNull(it.getValue(), 1);
        String str = "%25";
        if (orNull != null && orNull.charValue() == ' ') {
            return "%25";
        }
        if (orNull != null) {
            str = "%25" + orNull;
        }
        return str;
    }

    private static final String downloadEncryptedString(String str) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream httpToStream = getHttpToStream(str);
            try {
                InputStream inputStream = httpToStream;
                while (true) {
                    int read = inputStream.read();
                    if (read != -1) {
                        sb.append((char) read);
                    } else {
                        inputStream.close();
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(httpToStream, null);
                        String sb2 = sb.toString();
                        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                        return sb2;
                    }
                }
            } finally {
            }
        } catch (SocketException unused) {
            return "";
        }
    }

    public static final InputStream getHttpToStream(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ResponseBody body = NetWorkManager.INSTANCE.getClient().newCall(new Request.Builder().url(url).header("Connection", "close").build()).execute().body();
        Intrinsics.checkNotNull(body);
        return body.byteStream();
    }

    public static /* synthetic */ String generateRandomString$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        return generateRandomString(i);
    }

    public static final String generateRandomString(int i) {
        List plus = CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('A', Matrix.MATRIX_TYPE_ZERO), (Iterable) new CharRange('a', 'z')), (Iterable) new CharRange('0', '9'));
        IntRange intRange = new IntRange(1, i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(Character.valueOf(((Character) CollectionsKt.random(plus, Random.INSTANCE)).charValue()));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    public static final String generateSortedQueryString(Map<String, String> actualParams, String mRandom, String mTime) {
        Intrinsics.checkNotNullParameter(actualParams, "actualParams");
        Intrinsics.checkNotNullParameter(mRandom, "mRandom");
        Intrinsics.checkNotNullParameter(mTime, "mTime");
        Map mutableMap = MapsKt.toMutableMap(actualParams);
        mutableMap.put("random", mRandom);
        mutableMap.put("timestamp", mTime);
        mutableMap.put("app_key", "Jy47rzJAgKMfrcc92PamyyukQqB7wmFu");
        Set entrySet = MapsKt.toSortedMap(mutableMap).entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "<get-entries>(...)");
        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(encodeToURL(CollectionsKt.joinToString$default(entrySet, "&", null, null, 0, null, new Function1() { // from class: com.wifiled.baselib.retrofit.CryptographicParsingToolKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence generateSortedQueryString$lambda$6;
                generateSortedQueryString$lambda$6 = CryptographicParsingToolKt.generateSortedQueryString$lambda$6((Map.Entry) obj);
                return generateSortedQueryString$lambda$6;
            }
        }, 30, null)));
        Intrinsics.checkNotNull(encryptMD5ToString);
        String lowerCase = encryptMD5ToString.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence generateSortedQueryString$lambda$6(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        return ((String) entry.getKey()) + SimpleComparison.EQUAL_TO_OPERATION + ((String) entry.getValue());
    }

    public static final String generateSortedQueryAESString(Map<String, String> actualParams) {
        Intrinsics.checkNotNullParameter(actualParams, "actualParams");
        Set entrySet = MapsKt.toSortedMap(MapsKt.toMutableMap(actualParams)).entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "<get-entries>(...)");
        String encodeToURL = encodeToURL(CollectionsKt.joinToString$default(entrySet, "&", null, null, 0, null, new Function1() { // from class: com.wifiled.baselib.retrofit.CryptographicParsingToolKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence generateSortedQueryAESString$lambda$7;
                generateSortedQueryAESString$lambda$7 = CryptographicParsingToolKt.generateSortedQueryAESString$lambda$7((Map.Entry) obj);
                return generateSortedQueryAESString$lambda$7;
            }
        }, 30, null));
        SecretKeySpec createCustomKey = AESUtils.INSTANCE.createCustomKey();
        byte[] bytes = "0000000000000000".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return AESUtils.INSTANCE.encrypt(encodeToURL, createCustomKey, bytes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence generateSortedQueryAESString$lambda$7(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        return ((String) entry.getKey()) + SimpleComparison.EQUAL_TO_OPERATION + ((String) entry.getValue());
    }

    public static final String decryptQueryAESString(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        SecretKeySpec createCustomKey = AESUtils.INSTANCE.createCustomKey();
        byte[] bytes = "0000000000000000".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return AESUtils.INSTANCE.decrypt(data, createCustomKey, bytes);
    }

    private static final String encodeToURL(String str) {
        try {
            String encode = URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
            Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(encode, "%26", "&", false, 4, (Object) null), "%3D", SimpleComparison.EQUAL_TO_OPERATION, false, 4, (Object) null), "%3F", "?", false, 4, (Object) null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
