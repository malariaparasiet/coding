package com.wifiled.ipixels.gif;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BitmapRetriever {
    private static final Boolean DEBUG = false;

    /* renamed from: μs, reason: contains not printable characters */
    private static final int f7s = 1000000;
    private List<Bitmap> bitmaps = new ArrayList();
    private int width = 0;
    private int height = 0;
    private int start = 0;
    private int end = 0;
    private int fps = 5;

    public List<Bitmap> generateBitmaps(String path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(path);
        double d = 1000000 / this.fps;
        long longValue = Long.decode(mediaMetadataRetriever.extractMetadata(9)).longValue() * 1000;
        int i = this.end;
        if (i > 0) {
            longValue = i * 1000000;
        }
        for (long j = this.start * 1000000; j < longValue; j = (long) (j + d)) {
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(j, 3);
            if (frameAtTime != null) {
                try {
                    this.bitmaps.add(scale(frameAtTime));
                    debugSaveBitmap(frameAtTime, "" + j);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }
        return this.bitmaps;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setDuration(int begin, int end) {
        this.start = begin;
        this.end = end;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }

    private Bitmap scale(Bitmap bitmap) {
        int i = this.width;
        if (i <= 0) {
            i = bitmap.getWidth();
        }
        int i2 = this.height;
        if (i2 <= 0) {
            i2 = bitmap.getHeight();
        }
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public void debugSaveBitmap(Bitmap bm, String picName) {
        if (DEBUG.booleanValue()) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file.getAbsolutePath(), "DEBUG__" + picName + ".png");
            if (file2.exists()) {
                file2.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bm.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
