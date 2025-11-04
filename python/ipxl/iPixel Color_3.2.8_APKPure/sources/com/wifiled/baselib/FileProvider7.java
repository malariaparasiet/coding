package com.wifiled.baselib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class FileProvider7 {
    public static Uri getUriForFile(Context context, File file) {
        return getUriForFile24(context, file);
    }

    public static Uri getUriForFile24(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".android7.fileprovider", file);
    }

    public static void setIntentDataAndType(Context context, Intent intent, String str, File file, boolean z) {
        intent.setDataAndType(getUriForFile(context, file), str);
        intent.addFlags(1);
        if (z) {
            intent.addFlags(2);
        }
    }

    public static void setIntentData(Context context, Intent intent, File file, boolean z) {
        intent.setData(getUriForFile(context, file));
        intent.addFlags(1);
        if (z) {
            intent.addFlags(2);
        }
    }

    public static void grantPermissions(Context context, Intent intent, Uri uri, boolean z) {
        int i = z ? 3 : 1;
        intent.addFlags(i);
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            context.grantUriPermission(it.next().activityInfo.packageName, uri, i);
        }
    }
}
