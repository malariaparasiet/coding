package com.wifiled.baselib.utils;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class TimeUtils {
    private static Timer mTimer = getTimer();
    private static TimerTask mTimerTask = null;

    public static Timer getTimer() {
        if (mTimer == null) {
            mTimer = new Timer(true);
        }
        return mTimer;
    }

    public static void startTimer(long j) {
        TimerTask timerTask;
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() { // from class: com.wifiled.baselib.utils.TimeUtils.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                }
            };
        }
        Timer timer = mTimer;
        if (timer == null || (timerTask = mTimerTask) == null) {
            return;
        }
        timer.schedule(timerTask, j);
    }

    public static void cancelCount() {
        Timer timer = mTimer;
        if (timer != null) {
            timer.cancel();
            mTimer = null;
        }
        TimerTask timerTask = mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            mTimerTask = null;
        }
    }

    public static String getNowTime() {
        return new SimpleDateFormat(DateUtils.TIME_FORMAT).format(new Date(System.currentTimeMillis()));
    }

    public static String getTimeString() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static String dateToStamp(String str) {
        Date date;
        try {
            date = new SimpleDateFormat(DateUtils.TIME_FORMAT).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return String.valueOf(date.getTime());
    }

    public static String times(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        Long.valueOf(str).longValue();
        return simpleDateFormat.format(new Date(Integer.parseInt(str) * 1000));
    }

    public static String getLongTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, calendar.get(11) - i);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Log.v("time", simpleDateFormat.format(calendar.getTime()));
        return simpleDateFormat.format(calendar.getTime());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isDateOneBigger(java.lang.String r6, java.lang.String r7) {
        /*
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyy年MM月dd日 HH:mm:ss"
            r0.<init>(r1)
            r1 = 0
            java.util.Date r6 = r0.parse(r6)     // Catch: java.text.ParseException -> L14
            java.util.Date r1 = r0.parse(r7)     // Catch: java.text.ParseException -> L12
            goto L19
        L12:
            r7 = move-exception
            goto L16
        L14:
            r7 = move-exception
            r6 = r1
        L16:
            r7.printStackTrace()
        L19:
            long r2 = r6.getTime()
            long r4 = r1.getTime()
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L27
            r6 = 1
            goto L2e
        L27:
            r6.getTime()
            r1.getTime()
            r6 = 0
        L2e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.baselib.utils.TimeUtils.isDateOneBigger(java.lang.String, java.lang.String):boolean");
    }

    public static String Local2UTC() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("gmt"));
        return simpleDateFormat.format(new Date());
    }

    public static String utc2Local(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(DateUtils.TIME_FORMAT);
        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat2.format(Long.valueOf(date.getTime()));
    }
}
