package com.wifiled.musiclib.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AssetsFileUtil.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0018\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ%\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tJ\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\tJ\u001e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tJ \u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\tH\u0002¨\u0006\u001f"}, d2 = {"Lcom/wifiled/musiclib/utils/AssetsFileUtil;", "", "<init>", "()V", "getImageFromAssetsFile", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "fileName", "", "getFileFromAssetsFile", "Ljava/io/File;", "getfilesFromAssets", "", DbFinal.LOCAL_PATH, "(Landroid/content/Context;Ljava/lang/String;)[Ljava/lang/String;", "putAssetsToSDCard", "", "assetsPath", "sdCardPath", "cpyAssetFile2PackFilePath", "strDir", "cpyAssetFile2StoragePath", "assetsDir", "releaseDir", "releaseAssets", "writeFile", "", "inputStream", "Ljava/io/InputStream;", "checkFolderExists", "musiclib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AssetsFileUtil {
    public static final AssetsFileUtil INSTANCE = new AssetsFileUtil();

    private AssetsFileUtil() {
    }

    public final Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        AssetManager assets = context.getResources().getAssets();
        Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
        Bitmap bitmap = null;
        try {
            Intrinsics.checkNotNull(fileName);
            InputStream open = assets.open(fileName);
            Intrinsics.checkNotNullExpressionValue(open, "open(...)");
            bitmap = BitmapFactory.decodeStream(open);
            open.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public final File getFileFromAssetsFile(Context context, String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new File("file:///android_asset/" + fileName);
    }

    public final String[] getfilesFromAssets(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "context");
        AssetManager assets = context.getAssets();
        Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
        try {
            Intrinsics.checkNotNull(path);
            return assets.list(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void putAssetsToSDCard(Context context, String assetsPath, String sdCardPath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsPath, "assetsPath");
        Intrinsics.checkNotNullParameter(sdCardPath, "sdCardPath");
        try {
            String[] list = context.getResources().getAssets().list(assetsPath);
            if (list.length == 0) {
                InputStream open = context.getAssets().open(assetsPath);
                Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                byte[] bArr = new byte[1024];
                String str = File.separator;
                String substring = assetsPath.substring(StringsKt.lastIndexOf$default((CharSequence) assetsPath, '/', 0, false, 6, (Object) null));
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                File file = new File(sdCardPath + str + substring);
                if (file.exists()) {
                    return;
                }
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        open.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            } else {
                String str2 = sdCardPath + File.separator + assetsPath;
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                for (String str3 : list) {
                    putAssetsToSDCard(context, assetsPath + File.separator + str3, str2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void cpyAssetFile2PackFilePath$default(AssetsFileUtil assetsFileUtil, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "gallery";
        }
        assetsFileUtil.cpyAssetFile2PackFilePath(context, str);
    }

    public final void cpyAssetFile2PackFilePath(Context context, String strDir) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(strDir, "strDir");
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + context.getPackageName();
        if (strDir.equals("ota") || strDir.equals("gallery") || strDir.equals("emoji") || strDir.equals("anim32") || strDir.equals("image32X32") || strDir.equals("image64x64")) {
            if (new File(str).exists()) {
                FileUtils.deleteDir(new File(str));
            }
            releaseAssets(context, strDir, str);
            return;
        }
        releaseAssets(context, strDir, str);
    }

    public final void cpyAssetFile2StoragePath(Context context, String assetsDir, String releaseDir) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsDir, "assetsDir");
        Intrinsics.checkNotNullParameter(releaseDir, "releaseDir");
        if (new File(releaseDir).exists()) {
            FileUtils.deleteDir(new File(releaseDir));
        }
        releaseAssets(context, assetsDir, releaseDir);
    }

    private final void releaseAssets(Context context, String assetsDir, String releaseDir) {
        String[] strArr;
        String str;
        char c;
        boolean z;
        String str2 = assetsDir;
        String str3 = releaseDir;
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        boolean z2 = false;
        char c2 = 2;
        Object obj = null;
        if (StringsKt.endsWith$default(str3, "/", false, 2, (Object) null)) {
            str3 = str3.substring(0, str3.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str3, "substring(...)");
        }
        String str4 = "";
        if (TextUtils.isEmpty(str2) || Intrinsics.areEqual(str2, "/")) {
            str2 = "";
        } else if (StringsKt.endsWith$default(str2, "/", false, 2, (Object) null)) {
            str2 = str2.substring(0, str2.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str2, "substring(...)");
        }
        AssetManager assets = context.getAssets();
        try {
            String[] list = assets.list(str2);
            Intrinsics.checkNotNull(list);
            if (list.length == 0) {
                InputStream open = assets.open(str2);
                Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                try {
                    writeFile(((Object) str3) + File.separator + ((Object) str2), open);
                    return;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return;
                }
            }
            int length = list.length;
            int i = 0;
            while (i < length) {
                String str5 = !TextUtils.isEmpty(str2) ? ((Object) str2) + File.separator + list[i] : str4;
                String[] list2 = assets.list(str5);
                if (!TextUtils.isEmpty(str5)) {
                    Intrinsics.checkNotNull(list2);
                    if (!(list2.length == 0 ? true : z2)) {
                        AssetsFileUtil assetsFileUtil = INSTANCE;
                        assetsFileUtil.checkFolderExists(((Object) str3) + File.separator + str5);
                        assetsFileUtil.releaseAssets(context, str5, str3);
                        strArr = list;
                        str = str4;
                        z = false;
                        c = c2;
                        i++;
                        c2 = c;
                        z2 = z;
                        obj = null;
                        list = strArr;
                        str4 = str;
                    }
                }
                AssetsFileUtil assetsFileUtil2 = INSTANCE;
                assetsFileUtil2.checkFolderExists(((Object) str3) + File.separator + ((Object) str2));
                Log.v("ruis", "assetsDir----" + ((Object) str2));
                InputStream open2 = assets.open(str5);
                Intrinsics.checkNotNullExpressionValue(open2, "open(...)");
                strArr = list;
                str = str4;
                c = 2;
                z = false;
                if (StringsKt.contains$default((CharSequence) str5, (CharSequence) ".mp3", false, 2, obj)) {
                    str5 = str5.substring(StringsKt.lastIndexOf$default((CharSequence) str5, "/", 0, false, 6, (Object) null));
                    Intrinsics.checkNotNullExpressionValue(str5, "substring(...)");
                }
                Log.v("ruis", "ins----" + open2);
                Log.v("ruis", "name----" + str5);
                Log.v("ruis", "releaseDir + File.separator----" + ((Object) str3) + File.separator);
                assetsFileUtil2.writeFile(((Object) str3) + File.separator + str5, open2);
                i++;
                c2 = c;
                z2 = z;
                obj = null;
                list = strArr;
                str4 = str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final boolean writeFile(String fileName, InputStream inputStream) throws IOException {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] bArr = new byte[4112];
            while (true) {
                Intrinsics.checkNotNull(inputStream);
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private final void checkFolderExists(String path) {
        File file = new File(path);
        if ((!file.exists() || file.isDirectory()) && file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
