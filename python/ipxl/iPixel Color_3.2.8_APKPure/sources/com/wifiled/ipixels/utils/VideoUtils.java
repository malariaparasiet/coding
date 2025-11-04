package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;

/* loaded from: classes3.dex */
public class VideoUtils {
    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(path);
        return mediaMetadataRetriever.getFrameAtTime();
    }

    public static Bitmap getVideoThumbnail(String videoPath, int width, int height) {
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
        System.out.println("w" + createVideoThumbnail.getWidth());
        System.out.println("h" + createVideoThumbnail.getHeight());
        return ThumbnailUtils.extractThumbnail(createVideoThumbnail, width, height);
    }

    public static int getVideoDuration(String path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(path);
        return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
    }

    public static int getVideoRation(String path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(path);
        return Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
    }
}
