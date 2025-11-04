package com.wifiled.ipixels.ui.projection;

import android.media.Image;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes3.dex */
public class VideoDecoder {
    private static final String TAG = "VideoDecoder";
    private CodecCallback callback;
    MediaCodec.Callback codecCallback = new MediaCodec.Callback() { // from class: com.wifiled.ipixels.ui.projection.VideoDecoder.1
        @Override // android.media.MediaCodec.Callback
        public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat format) {
        }

        @Override // android.media.MediaCodec.Callback
        public void onInputBufferAvailable(MediaCodec mediaCodec, int index) {
            int readSampleData = VideoDecoder.this.mediaExtractor.readSampleData(mediaCodec.getInputBuffer(index), 0);
            if (readSampleData > 0) {
                mediaCodec.queueInputBuffer(index, 0, readSampleData, VideoDecoder.this.mediaExtractor.getSampleTime(), 0);
                VideoDecoder.this.mediaExtractor.advance();
                return;
            }
            mediaCodec.queueInputBuffer(index, 0, 0, 0L, 4);
            if (VideoDecoder.this.callback != null) {
                VideoDecoder.this.callback.onFinish();
                VideoDecoder.this.callback = null;
            }
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputBufferAvailable(MediaCodec mediaCodec, int index, MediaCodec.BufferInfo info) {
            Image outputImage = mediaCodec.getOutputImage(index);
            if (VideoDecoder.this.callback != null) {
                VideoDecoder.this.callback.onFrame(outputImage, VideoDecoder.this.videoFormat.containsKey("rotation-degrees") ? VideoDecoder.this.videoFormat.getInteger("rotation-degrees") : 0);
            }
            mediaCodec.releaseOutputBuffer(index, true);
        }

        @Override // android.media.MediaCodec.Callback
        public void onError(MediaCodec mediaCodec, MediaCodec.CodecException e) {
            if (VideoDecoder.this.callback != null) {
                VideoDecoder.this.callback.onError(e.getMessage());
            }
        }
    };
    private MediaCodec mediaCodec;
    private MediaExtractor mediaExtractor;
    private MediaFormat videoFormat;

    public interface CodecCallback {
        void onError(String msg);

        void onFinish();

        void onFrame(Image image, int rotation);

        void onStart(MediaFormat videoFormat);
    }

    void init(String path) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.mediaExtractor = mediaExtractor;
        try {
            mediaExtractor.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int trackCount = this.mediaExtractor.getTrackCount();
        int i = 0;
        while (true) {
            if (i >= trackCount) {
                break;
            }
            MediaFormat trackFormat = this.mediaExtractor.getTrackFormat(i);
            if (trackFormat.getString("mime").contains("video")) {
                this.videoFormat = trackFormat;
                this.mediaExtractor.selectTrack(i);
                Log.e(TAG, "视频格式: " + this.videoFormat.toString());
                break;
            }
            i++;
        }
        this.videoFormat.setInteger("bitrate-mode", 2);
        this.videoFormat.setInteger("color-format", 21);
        MediaFormat mediaFormat = this.videoFormat;
        mediaFormat.setInteger("width", mediaFormat.getInteger("width") / 4);
        MediaFormat mediaFormat2 = this.videoFormat;
        mediaFormat2.setInteger("height", mediaFormat2.getInteger("height") / 4);
        this.videoFormat.setInteger("i-frame-interval", 1);
        this.videoFormat.setInteger("frame-rate", 30);
        try {
            this.mediaCodec = MediaCodec.createDecoderByType((String) Objects.requireNonNull(this.videoFormat.getString("mime")));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void start(String path, CodecCallback callback) {
        start(path, null, callback);
    }

    public void start(String path, Surface surface, CodecCallback callback) {
        try {
            init(path);
            this.callback = callback;
            MediaCodec mediaCodec = this.mediaCodec;
            if (mediaCodec != null) {
                mediaCodec.setCallback(this.codecCallback);
                this.mediaCodec.configure(this.videoFormat, surface, (MediaCrypto) null, 0);
                this.mediaCodec.start();
            }
            if (callback != null) {
                callback.onStart(this.videoFormat);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            callback.onError("异常");
        }
    }

    private ImageReader initImageReader() {
        ImageReader newInstance = ImageReader.newInstance(this.videoFormat.getInteger("width"), this.videoFormat.getInteger("height"), 35, 3);
        newInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: com.wifiled.ipixels.ui.projection.VideoDecoder$$ExternalSyntheticLambda0
            @Override // android.media.ImageReader.OnImageAvailableListener
            public final void onImageAvailable(ImageReader imageReader) {
                VideoDecoder.this.lambda$initImageReader$0(imageReader);
            }
        }, null);
        return newInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initImageReader$0(ImageReader imageReader) {
        Image acquireLatestImage = imageReader.acquireLatestImage();
        if (acquireLatestImage != null) {
            CodecCallback codecCallback = this.callback;
            if (codecCallback != null) {
                codecCallback.onFrame(acquireLatestImage, 0);
            }
            acquireLatestImage.close();
        }
    }

    public static class VideoInfo {
        long durationUs;
        int frameCount;
        int frameRate;
        int height;
        MediaFormat videoFormat;
        int width;

        public VideoInfo(MediaFormat videoFormat, int width, int height, int frameRate, int frameCount, long durationUs) {
            this.videoFormat = videoFormat;
            this.width = width;
            this.height = height;
            this.frameRate = frameRate;
            this.frameCount = frameCount;
            this.durationUs = durationUs;
        }

        public String toString() {
            return "VideoInfo{videoFormat=" + this.videoFormat + ", width=" + this.width + ", height=" + this.height + ", frameRate=" + this.frameRate + ", frameCount=" + this.frameCount + ", durationUs=" + this.durationUs + '}';
        }
    }
}
