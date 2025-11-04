package com.wifiled.ipixels.view.video_clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.view.video_clip.MediaClipper;

/* loaded from: classes3.dex */
public class VideoTrimmerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_COUNT_RANGE = 10;
    public static final long MAX_SHOOT_DURATION = 10000;
    public static final long MIN_SHOOT_DURATION = 1000;
    public static final int RECYCLER_VIEW_PADDING;
    private static final int SCREEN_WIDTH_FULL;
    private static final String TAG = "VideoTrimmerUtil";
    private static final int THUMB_HEIGHT;
    public static final int THUMB_WIDTH;
    public static final int VIDEO_FRAMES_WIDTH;
    public static final int VIDEO_MAX_TIME = 10;

    static {
        int screenWidth = ScreenUtil.getScreenWidth(App.context);
        SCREEN_WIDTH_FULL = screenWidth;
        int dip2px = ScreenUtil.dip2px(35.0f);
        RECYCLER_VIEW_PADDING = dip2px;
        VIDEO_FRAMES_WIDTH = screenWidth - (dip2px * 2);
        THUMB_WIDTH = (screenWidth - (dip2px * 2)) / 10;
        THUMB_HEIGHT = ScreenUtil.dip2px(50.0f);
    }

    public static void trim(String inputFile, String outputFile, long startMs, long endMs, final VideoTrimCallback callback) {
        LogUtils.logi("$TAG>>>[clipVideo]: 原视频路径:" + inputFile + " -------裁剪后路径: " + outputFile + "-----裁剪时常: " + (endMs - startMs), new Object[0]);
        MediaClipper.clip(inputFile, outputFile, startMs, endMs, MediaClipper.MEDIA_TYPE.VIDEO, callback);
    }

    public static void shootVideoThumbInBackground(final Context context, final Uri videoUri, final int totalThumbsCount, final long startPosition, final long endPosition, final SingleCallback<Bitmap, Integer> callback) {
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoTrimmerUtil.lambda$shootVideoThumbInBackground$0(context, videoUri, endPosition, startPosition, totalThumbsCount, callback);
            }
        });
    }

    static /* synthetic */ void lambda$shootVideoThumbInBackground$0(Context context, Uri uri, long j, long j2, int i, SingleCallback singleCallback) {
        int i2;
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(context, uri);
            int i3 = i - 1;
            long j3 = (j - j2) / i3;
            try {
                i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(32));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                i2 = 30;
            }
            int i4 = i2 / i3;
            for (long j4 = 0; j4 < i; j4++) {
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(((j3 * j4) + j2) * 1000, 2);
                if (frameAtTime != null) {
                    try {
                        frameAtTime = Bitmap.createScaledBitmap(frameAtTime, THUMB_WIDTH, THUMB_HEIGHT, false);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    singleCallback.onSingleCallback(frameAtTime, Integer.valueOf((int) j3));
                }
            }
            mediaMetadataRetriever.release();
        } catch (Throwable th2) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th2);
        }
    }

    public static String getVideoFilePath(String url) {
        if (TextUtils.isEmpty(url) || url.length() < 5) {
            return "";
        }
        return url.substring(0, 4).equalsIgnoreCase("http") ? url : "file://" + url;
    }

    private static String convertSecondsToTime(long seconds) {
        if (seconds <= 0) {
            return "00:00";
        }
        int i = (int) seconds;
        int i2 = i / 60;
        if (i2 < 60) {
            return "00:" + unitFormat(i2) + ":" + unitFormat(i % 60);
        }
        int i3 = i2 / 60;
        if (i3 > 99) {
            return "99:59:59";
        }
        return unitFormat(i3) + ":" + unitFormat(i2 % 60) + ":" + unitFormat((int) ((seconds - (i3 * 3600)) - (r1 * 60)));
    }

    private static String unitFormat(int i) {
        if (i >= 0 && i < 10) {
            return "0" + Integer.toString(i);
        }
        return "" + i;
    }
}
