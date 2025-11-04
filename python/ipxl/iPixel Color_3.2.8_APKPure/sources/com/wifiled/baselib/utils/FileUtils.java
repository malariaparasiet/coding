package com.wifiled.baselib.utils;

import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.jieli.jl_bt_ota.constant.Command;
import com.wifiled.baselib.CoreBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class FileUtils {
    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteDir(file2);
            }
            return true;
        }
        if (!file.exists()) {
            return true;
        }
        file.delete();
        return true;
    }

    public static void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        deleteFile(new File(str));
    }

    public static void deleteFile(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                deleteFile(file2);
            }
        }
        deleteFileSafely(file);
    }

    public static boolean deleteFileSafely(File file) {
        if (file == null) {
            return false;
        }
        File file2 = new File(file.getParent() + File.separator + System.currentTimeMillis());
        file.renameTo(file2);
        return file2.delete();
    }

    public static File getCachePath(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir();
        }
        return context.getCacheDir();
    }

    public static String getFileContent(File file) {
        String str = "";
        if (!file.isDirectory() && file.getName().endsWith("txt")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Key.STRING_CHARSET_NAME));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str = str + readLine + "\n";
                    } else {
                        fileInputStream.close();
                        return str;
                    }
                }
            } catch (FileNotFoundException unused) {
                Log.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.d("TestFile", e.getMessage());
            }
        }
        return str;
    }

    public static File getExternalFilePath(Context context, String str) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalFilesDir(str);
        }
        File file = new File(context.getFilesDir() + File.separator + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String getFileTxtContent(File file) {
        String str = "";
        if (!file.isDirectory() && file.getName().endsWith("txt")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Key.STRING_CHARSET_NAME));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str = str + readLine + "\n";
                    } else {
                        fileInputStream.close();
                        return str;
                    }
                }
            } catch (FileNotFoundException unused) {
                Log.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.d("TestFile", e.getMessage());
            }
        }
        return str;
    }

    public void setMyRingtone() {
        File file = new File("/sdcard/canon.mp3");
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("title", file.getName());
        contentValues.put("_size", (Integer) 8474325);
        contentValues.put("mime_type", "audio/mp3");
        contentValues.put("artist", "Madonna");
        contentValues.put("duration", Integer.valueOf(Command.CMD_OTA_GET_DEVICE_REFRESH_FIRMWARE_STATUS));
        contentValues.put("is_ringtone", (Boolean) true);
        contentValues.put("is_notification", (Boolean) false);
        contentValues.put("is_alarm", (Boolean) false);
        contentValues.put("is_music", (Boolean) false);
        CoreBase.getContext().getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath()), contentValues);
    }
}
