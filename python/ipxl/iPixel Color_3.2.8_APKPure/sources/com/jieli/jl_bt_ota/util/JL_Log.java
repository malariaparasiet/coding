package com.jieli.jl_bt_ota.util;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class JL_Log {
    public static long FILE_SIZE_LIMIT = 314572800;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_WARN = 4;
    public static int SAVE_LOG_LEVEL = 1;
    private static final String a = "ota:";
    private static final String b = "logcat";
    private static boolean c = true;
    private static boolean d = false;
    private static String e = null;
    private static final SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.getDefault());
    private static SaveLogFileThread g = null;
    private static Context h = null;
    private static ILogOutput i = null;
    public static boolean isTest = false;

    public interface ILogOutput {
        void output(String str);
    }

    private static class SaveLogFileThread extends Thread {
        private final LinkedBlockingQueue<byte[]> a;
        private final Context b;
        private volatile boolean c;
        private volatile boolean d;
        private long e;
        private FileOutputStream f;

        public SaveLogFileThread(Context context) {
            super("SaveLogFileThread");
            this.a = new LinkedBlockingQueue<>();
            this.b = context;
        }

        public void addLog(byte[] bArr) {
            if (bArr != null) {
                try {
                    this.a.put(bArr);
                    a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void closeSaveFile() {
            this.d = false;
            a();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            this.d = a(this.b);
            synchronized (this.a) {
                while (this.d) {
                    if (this.a.isEmpty()) {
                        this.c = true;
                        try {
                            this.a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.c = false;
                        byte[] poll = this.a.poll();
                        if (poll != null && (fileOutputStream = this.f) != null) {
                            try {
                                fileOutputStream.write(poll);
                                this.e += poll.length;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            if (this.e >= JL_Log.FILE_SIZE_LIMIT) {
                                try {
                                    this.f.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                this.d = a(this.b);
                            }
                        }
                    }
                }
            }
            this.d = false;
            this.c = false;
            this.a.clear();
            FileOutputStream fileOutputStream2 = this.f;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            SaveLogFileThread unused = JL_Log.g = null;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.e = 0L;
            this.d = this.b != null;
            super.start();
        }

        private void a() {
            if (this.c) {
                synchronized (this.a) {
                    this.a.notify();
                }
            }
        }

        private boolean a(Context context) {
            if (context == null) {
                return false;
            }
            if (TextUtils.isEmpty(JL_Log.e)) {
                String unused = JL_Log.e = JL_Log.b(context, JL_Log.b);
            }
            try {
                this.f = new FileOutputStream(JL_Log.e + "/ota_log_app_" + JL_Log.d() + ".txt", true);
                this.e = 0L;
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void addLogOutput(String str) {
        if (d) {
            if (g == null) {
                a(h);
                SystemClock.sleep(20L);
            }
            SaveLogFileThread saveLogFileThread = g;
            if (saveLogFileThread != null) {
                saveLogFileThread.addLog(str.getBytes());
            }
        }
    }

    private static void c(int i2, String str, String str2) {
        String a2 = a(a(i2), str, str2);
        ILogOutput iLogOutput = i;
        if (iLogOutput != null) {
            iLogOutput.output(a2);
        } else {
            if (i2 < SAVE_LOG_LEVEL) {
                return;
            }
            addLogOutput(a2);
        }
    }

    public static void d(String str, String str2) {
        a(2, str, str2);
    }

    public static void e(String str, String str2) {
        a(5, str, str2);
    }

    public static boolean getSaveLogFile() {
        return d;
    }

    public static String getSaveLogPath(Context context) {
        return b(context, b);
    }

    public static void i(String str, String str2) {
        a(3, str, str2);
    }

    public static boolean isIsLog() {
        return c;
    }

    public static void setIsSaveLogFile(Context context, boolean z) {
        d = z;
        if (z) {
            a(context);
        } else {
            c();
        }
    }

    public static void setIsTest(boolean z) {
        isTest = z;
    }

    public static void setLog(boolean z) {
        c = z;
    }

    public static void setLogOutput(ILogOutput iLogOutput) {
        i = iLogOutput;
    }

    public static void v(String str, String str2) {
        a(1, str, str2);
    }

    public static void w(String str, String str2) {
        a(4, str, str2);
    }

    private static String b(String str) {
        return a + str;
    }

    public static void d(String str, String str2, String str3) {
        d(str, a(str2, str3));
    }

    public static void e(String str, String str2, String str3) {
        e(str, a(str2, str3));
    }

    public static void i(String str, String str2, String str3) {
        i(str, a(str2, str3));
    }

    public static void v(String str, String str2, String str3) {
        v(str, a(str2, str3));
    }

    public static void w(String str, String str2, String str3) {
        w(str, a(str2, str3));
    }

    private static void b(int i2, String str, String str2) {
        System.out.printf(Locale.ENGLISH, "%s\t%s\t%s%n", a(i2), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d() {
        return f.format(Calendar.getInstance().getTime());
    }

    private static String a(String str, String str2, String str3) {
        StringBuilder append = new StringBuilder().append(d()).append("   ").append(str).append("   ");
        if (str2 == null) {
            str2 = "null";
        }
        StringBuilder append2 = append.append(str2).append(" :  ");
        if (str3 == null) {
            str3 = "null";
        }
        return append2.append(str3).append("\n").toString();
    }

    private static String a(String str, String str2) {
        return String.format(Locale.ENGLISH, "[%s] >>> %s", str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, String... strArr) {
        File externalFilesDir;
        if (context == null || strArr == null || strArr.length == 0 || (externalFilesDir = context.getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(externalFilesDir.getPath());
        int i2 = 0;
        if (sb.toString().endsWith("/")) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf("/")));
        }
        int length = strArr.length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            sb.append("/").append(strArr[i2]);
            File file = new File(sb.toString());
            if ((!file.exists() || file.isFile()) && !file.mkdir()) {
                w("jieli", "create dir failed. filePath = " + ((Object) sb));
                break;
            }
            i2++;
        }
        return sb.toString();
    }

    private static String a(int i2) {
        if (i2 == 1) {
            return "v";
        }
        if (i2 == 2) {
            return "d";
        }
        if (i2 == 3) {
            return "i";
        }
        if (i2 == 4) {
            return "w";
        }
        if (i2 != 5) {
            return "";
        }
        return "e";
    }

    private static void c() {
        SaveLogFileThread saveLogFileThread = g;
        if (saveLogFileThread != null) {
            saveLogFileThread.closeSaveFile();
            g = null;
        }
        h = null;
    }

    private static void a(int i2, String str, String str2) {
        String b2 = b(str);
        if (c) {
            if (isTest) {
                b(i2, b2, str2);
            } else if (i2 == 1) {
                Log.v(b2, str2);
            } else if (i2 == 2) {
                Log.d(b2, str2);
            } else if (i2 == 3) {
                Log.i(b2, str2);
            } else if (i2 == 4) {
                Log.w(b2, str2);
            } else if (i2 != 5) {
                return;
            } else {
                Log.e(b2, str2);
            }
        }
        c(i2, b2, str2);
    }

    private static void a(Context context) {
        SaveLogFileThread saveLogFileThread = g;
        if (saveLogFileThread == null || !saveLogFileThread.d) {
            if (h == null) {
                if (context == null) {
                    context = CommonUtil.getMainContext();
                }
                h = context;
            }
            SaveLogFileThread saveLogFileThread2 = new SaveLogFileThread(h);
            g = saveLogFileThread2;
            saveLogFileThread2.start();
        }
    }
}
