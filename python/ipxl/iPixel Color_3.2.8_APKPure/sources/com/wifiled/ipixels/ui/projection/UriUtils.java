package com.wifiled.ipixels.ui.projection;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: UriUtils.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ9\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t¨\u0006\u0013"}, d2 = {"Lcom/wifiled/ipixels/ui/projection/UriUtils;", "", "<init>", "()V", "getPath", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getDataColumn", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "isExternalStorageDocument", "", "isDownloadsDocument", "isMediaDocument", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UriUtils {
    public static final UriUtils INSTANCE = new UriUtils();

    private UriUtils() {
    }

    public final String getPath(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                Intrinsics.checkNotNullExpressionValue(documentId, "getDocumentId(...)");
                String[] strArr = (String[]) new Regex(":").split(documentId, 0).toArray(new String[0]);
                if (StringsKt.equals("primary", strArr[0], true)) {
                    return Environment.getExternalStorageDirectory() + "/" + strArr[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    String documentId2 = DocumentsContract.getDocumentId(uri);
                    Intrinsics.checkNotNullExpressionValue(documentId2, "getDocumentId(...)");
                    Uri parse = Uri.parse("content://downloads/public_downloads");
                    Long valueOf = Long.valueOf(documentId2);
                    Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
                    Uri withAppendedId = ContentUris.withAppendedId(parse, valueOf.longValue());
                    Intrinsics.checkNotNullExpressionValue(withAppendedId, "withAppendedId(...)");
                    return getDataColumn(context, withAppendedId, null, null);
                }
                if (isMediaDocument(uri)) {
                    String documentId3 = DocumentsContract.getDocumentId(uri);
                    Intrinsics.checkNotNullExpressionValue(documentId3, "getDocumentId(...)");
                    String[] strArr2 = (String[]) new Regex(":").split(documentId3, 0).toArray(new String[0]);
                    String str = strArr2[0];
                    int hashCode = str.hashCode();
                    if (hashCode != 93166550) {
                        if (hashCode != 100313435) {
                            if (hashCode == 112202875 && str.equals("video")) {
                                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            }
                        } else if (str.equals("image")) {
                            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        }
                    } else if (str.equals("audio")) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{strArr2[1]});
                }
            }
        } else {
            if (StringsKt.equals("content", uri.getScheme(), true)) {
                return getDataColumn(context, uri, null, null);
            }
            if (StringsKt.equals("file", uri.getScheme(), true)) {
                return uri.getPath();
            }
        }
        return null;
    }

    public final String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Throwable th;
        Intrinsics.checkNotNullParameter(context, "context");
        String[] strArr = {"_data"};
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNull(uri);
            Cursor query = contentResolver.query(uri, strArr, selection, selectionArgs, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        query.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor == null) {
                        throw th;
                    }
                    cursor.close();
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final boolean isExternalStorageDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.externalstorage.documents", uri.getAuthority());
    }

    public final boolean isDownloadsDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.providers.downloads.documents", uri.getAuthority());
    }

    public final boolean isMediaDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.providers.media.documents", uri.getAuthority());
    }
}
