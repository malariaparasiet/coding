package com.wifiled.ipixels.view.video_clip;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.VideoView;

/* loaded from: classes3.dex */
public class ZVideoView extends VideoView {
    private static final String TAG = "ZVideoView";
    private int videoRealH;
    private int videoRealW;

    public ZVideoView(Context context) {
        super(context);
        this.videoRealW = 1080;
        this.videoRealH = 1920;
    }

    public ZVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.videoRealW = 1080;
        this.videoRealH = 1920;
    }

    public ZVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.videoRealW = 1080;
        this.videoRealH = 1920;
    }

    @Override // android.widget.VideoView
    public void setVideoURI(Uri uri) {
        super.setVideoURI(uri);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(uri.getPath());
            int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            this.videoRealW = parseInt;
            this.videoRealH = parseInt2;
            if (parseInt3 != 90 && parseInt3 != 270) {
                return;
            }
            this.videoRealW = parseInt2;
            this.videoRealH = parseInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = getDefaultSize(0, widthMeasureSpec);
        int defaultSize2 = getDefaultSize(0, heightMeasureSpec);
        if (defaultSize2 > defaultSize) {
            int i = this.videoRealH;
            int i2 = this.videoRealW;
            if (i <= i2) {
                defaultSize2 = (int) (defaultSize * (i / i2));
            }
        } else {
            int i3 = this.videoRealH;
            int i4 = this.videoRealW;
            if (i3 > i4) {
                defaultSize = (int) (defaultSize2 * (i4 / i3));
            }
        }
        int i5 = this.videoRealH;
        if (i5 == this.videoRealW && i5 == 1) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(defaultSize, defaultSize2);
        }
    }

    public void resize(MediaPlayer mp, int videoRealW, int videoRealH) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int videoWidth = mp.getVideoWidth();
        int videoHeight = mp.getVideoHeight();
        if (videoHeight > videoWidth) {
            layoutParams.width = videoRealW;
            layoutParams.height = videoRealH;
        } else {
            layoutParams.width = videoRealW;
            layoutParams.height = (int) (layoutParams.width * (videoHeight / videoWidth));
        }
        setLayoutParams(layoutParams);
    }
}
