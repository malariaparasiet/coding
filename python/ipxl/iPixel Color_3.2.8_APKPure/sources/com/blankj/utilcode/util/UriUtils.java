package com.blankj.utilcode.util;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public final class UriUtils {
    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Uri res2Uri(String str) {
        return Uri.parse("android.resource://" + Utils.getApp().getPackageName() + "/" + str);
    }

    public static Uri file2Uri(File file) {
        if (!UtilsBridge.isFileExists(file)) {
            return null;
        }
        return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.fileprovider", file);
    }

    public static File uri2File(Uri uri) {
        if (uri == null) {
            return null;
        }
        File uri2FileReal = uri2FileReal(uri);
        return uri2FileReal != null ? uri2FileReal : copyUri2Cache(uri);
    }

    public static File uri2FileNoCacheCopy(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri2FileReal(uri);
    }

    private static File uri2FileReal(Uri uri) {
        int i;
        Uri uri2;
        File fileFromUri;
        File file;
        String str;
        File file2;
        int i2;
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        int i3 = 2;
        boolean z = true;
        if (path != null) {
            String[] strArr = {"/external/", "/external_path/"};
            int i4 = 0;
            while (i4 < i3) {
                String str2 = strArr[i4];
                if (path.startsWith(str2)) {
                    i2 = i3;
                    File file3 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace(str2, "/"));
                    if (file3.exists()) {
                        Log.d("UriUtils", uri.toString() + " -> " + str2);
                        return file3;
                    }
                } else {
                    i2 = i3;
                }
                i4++;
                i3 = i2;
            }
            i = i3;
            if (path.startsWith("/files_path/")) {
                file2 = new File(Utils.getApp().getFilesDir().getAbsolutePath() + path.replace("/files_path/", "/"));
            } else if (path.startsWith("/cache_path/")) {
                file2 = new File(Utils.getApp().getCacheDir().getAbsolutePath() + path.replace("/cache_path/", "/"));
            } else if (path.startsWith("/external_files_path/")) {
                file2 = new File(Utils.getApp().getExternalFilesDir(null).getAbsolutePath() + path.replace("/external_files_path/", "/"));
            } else {
                file2 = path.startsWith("/external_cache_path/") ? new File(Utils.getApp().getExternalCacheDir().getAbsolutePath() + path.replace("/external_cache_path/", "/")) : null;
            }
            if (file2 != null && file2.exists()) {
                Log.d("UriUtils", uri.toString() + " -> " + path);
                return file2;
            }
        } else {
            i = 2;
        }
        if ("file".equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }
        if (DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str3 = split[0];
                if ("primary".equalsIgnoreCase(str3)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                }
                StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
                try {
                    Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                    Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
                    Method method2 = cls.getMethod("getUuid", new Class[0]);
                    Method method3 = cls.getMethod("getState", new Class[0]);
                    Method method4 = cls.getMethod("getPath", new Class[0]);
                    Method method5 = cls.getMethod("isPrimary", new Class[0]);
                    file = null;
                    try {
                        Method method6 = cls.getMethod("isEmulated", new Class[0]);
                        Object invoke = method.invoke(storageManager, new Object[0]);
                        int length = Array.getLength(invoke);
                        int i5 = 0;
                        while (i5 < length) {
                            Object obj = Array.get(invoke, i5);
                            boolean z2 = z;
                            String[] strArr2 = split;
                            if (("mounted".equals(method3.invoke(obj, new Object[0])) || "mounted_ro".equals(method3.invoke(obj, new Object[0]))) && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                                return new File(method4.invoke(obj, new Object[0]) + "/" + strArr2[z2 ? 1 : 0]);
                            }
                            i5++;
                            split = strArr2;
                            z = z2 ? 1 : 0;
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.d("UriUtils", uri.toString() + " parse failed. " + e.toString() + " -> 1_0");
                        Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                        return file;
                    }
                } catch (Exception e2) {
                    e = e2;
                    file = null;
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return file;
            }
            if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (TextUtils.isEmpty(documentId)) {
                    Log.d("UriUtils", uri.toString() + " parse failed(id is null). -> 1_1");
                    return null;
                }
                if (documentId.startsWith("raw:")) {
                    return new File(documentId.substring(4));
                }
                if (documentId.startsWith("msf:")) {
                    documentId = documentId.split(":")[1];
                }
                try {
                    long parseLong = Long.parseLong(documentId);
                    String[] strArr3 = new String[3];
                    strArr3[0] = "content://downloads/public_downloads";
                    strArr3[1] = "content://downloads/all_downloads";
                    strArr3[i] = "content://downloads/my_downloads";
                    for (int i6 = 0; i6 < 3; i6++) {
                        try {
                            fileFromUri = getFileFromUri(ContentUris.withAppendedId(Uri.parse(strArr3[i6]), parseLong), "1_1");
                        } catch (Exception unused) {
                        }
                        if (fileFromUri != null) {
                            return fileFromUri;
                        }
                    }
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                } catch (Exception unused2) {
                }
                return null;
            }
            if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str4 = split2[0];
                if ("image".equals(str4)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str4)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str4)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                }
                return getFileFromUri(uri2, "_id=?", new String[]{split2[1]}, "1_2");
            }
            if ("content".equals(scheme)) {
                return getFileFromUri(uri, "1_3");
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
            return null;
        }
        if ("content".equals(scheme)) {
            return getFileFromUri(uri, ExifInterface.GPS_MEASUREMENT_2D);
        }
        Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
        return null;
    }

    private static File getFileFromUri(Uri uri, String str) {
        return getFileFromUri(uri, null, null, str);
    }

    private static File getFileFromUri(Uri uri, String str, String[] strArr, String str2) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(Environment.getExternalStorageDirectory(), path.substring("/QQBrowser".length(), path.length()));
            }
        } else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            String path2 = uri.getPath();
            if (!TextUtils.isEmpty(path2)) {
                return new File(path2.replace("/root", ""));
            }
        }
        Cursor query = Utils.getApp().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (query == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            }
            try {
                if (!query.moveToFirst()) {
                    Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                    query.close();
                    return null;
                }
                int columnIndex = query.getColumnIndex("_data");
                if (columnIndex > -1) {
                    File file = new File(query.getString(columnIndex));
                    query.close();
                    return file;
                }
                Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
                query.close();
                return null;
            } catch (Exception unused) {
                Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
                query.close();
                return null;
            }
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.io.File copyUri2Cache(android.net.Uri r7) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "UriUtils"
            java.lang.String r2 = "copyUri2Cache() called"
            android.util.Log.d(r1, r2)
            r1 = 0
            android.app.Application r2 = com.blankj.utilcode.util.Utils.getApp()     // Catch: java.lang.Throwable -> L48 java.io.FileNotFoundException -> L4a
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L48 java.io.FileNotFoundException -> L4a
            java.io.InputStream r7 = r2.openInputStream(r7)     // Catch: java.lang.Throwable -> L48 java.io.FileNotFoundException -> L4a
            java.io.File r2 = new java.io.File     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            android.app.Application r3 = com.blankj.utilcode.util.Utils.getApp()     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            java.io.File r3 = r3.getCacheDir()     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            r4.<init>(r0)     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            java.lang.StringBuilder r0 = r4.append(r5)     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            java.lang.String r0 = r0.toString()     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            r2.<init>(r3, r0)     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            java.lang.String r0 = r2.getAbsolutePath()     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            com.blankj.utilcode.util.UtilsBridge.writeFileFromIS(r0, r7)     // Catch: java.io.FileNotFoundException -> L46 java.lang.Throwable -> L5a
            if (r7 == 0) goto L45
            r7.close()     // Catch: java.io.IOException -> L41
            return r2
        L41:
            r7 = move-exception
            r7.printStackTrace()
        L45:
            return r2
        L46:
            r0 = move-exception
            goto L4c
        L48:
            r0 = move-exception
            goto L5c
        L4a:
            r0 = move-exception
            r7 = r1
        L4c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r7 == 0) goto L59
            r7.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r7 = move-exception
            r7.printStackTrace()
        L59:
            return r1
        L5a:
            r0 = move-exception
            r1 = r7
        L5c:
            if (r1 == 0) goto L66
            r1.close()     // Catch: java.io.IOException -> L62
            goto L66
        L62:
            r7 = move-exception
            r7.printStackTrace()
        L66:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.copyUri2Cache(android.net.Uri):java.io.File");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] uri2Bytes(android.net.Uri r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()     // Catch: java.lang.Throwable -> L21 java.io.FileNotFoundException -> L26
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L21 java.io.FileNotFoundException -> L26
            java.io.InputStream r4 = r1.openInputStream(r4)     // Catch: java.lang.Throwable -> L21 java.io.FileNotFoundException -> L26
            byte[] r0 = com.blankj.utilcode.util.UtilsBridge.inputStream2Bytes(r4)     // Catch: java.io.FileNotFoundException -> L1f java.lang.Throwable -> L3d
            if (r4 == 0) goto L1e
            r4.close()     // Catch: java.io.IOException -> L1a
            return r0
        L1a:
            r4 = move-exception
            r4.printStackTrace()
        L1e:
            return r0
        L1f:
            r1 = move-exception
            goto L28
        L21:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L3e
        L26:
            r1 = move-exception
            r4 = r0
        L28:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r1 = "UriUtils"
            java.lang.String r2 = "uri to bytes failed."
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L3c
            r4.close()     // Catch: java.io.IOException -> L38
            goto L3c
        L38:
            r4 = move-exception
            r4.printStackTrace()
        L3c:
            return r0
        L3d:
            r0 = move-exception
        L3e:
            if (r4 == 0) goto L48
            r4.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r4 = move-exception
            r4.printStackTrace()
        L48:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.uri2Bytes(android.net.Uri):byte[]");
    }
}
