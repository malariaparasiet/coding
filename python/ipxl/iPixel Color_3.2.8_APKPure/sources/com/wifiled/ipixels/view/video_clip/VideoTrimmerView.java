package com.wifiled.ipixels.view.video_clip;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.baselib.utils.FileUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.view.SpacesItemDecoration;
import com.wifiled.ipixels.view.video_clip.RangeSeekBarView;
import java.io.File;

/* loaded from: classes3.dex */
public class VideoTrimmerView extends FrameLayout implements IVideoTrimmerView {
    private static final String TAG = "VideoTrimmerView";
    private float averagePxMs;
    private boolean isFromRestore;
    private boolean isOverScaledTouchSlop;
    private boolean isSeeking;
    private int lastScrollX;
    private Handler mAnimationHandler;
    private final Runnable mAnimationRunnable;
    private float mAverageMsPx;
    private Context mContext;
    private int mDuration;
    private long mLeftProgressPos;
    private RelativeLayout mLinearVideo;
    private int mMaxWidth;
    private final RangeSeekBarView.OnRangeSeekBarChangeListener mOnRangeSeekBarChangeListener;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private VideoTrimCallback mOnTrimVideoListener;
    private ImageView mPlayView;
    private RangeSeekBarView mRangeSeekBarView;
    private ValueAnimator mRedProgressAnimator;
    private long mRedProgressBarPos;
    private ImageView mRedProgressIcon;
    private long mRightProgressPos;
    private int mScaledTouchSlop;
    private LinearLayout mSeekBarLayout;
    private Uri mSourceUri;
    private int mThumbsTotalCount;
    private TextView mVideoShootTipTv;
    private VideoTrimmerAdapter mVideoThumbAdapter;
    private RecyclerView mVideoThumbRecyclerView;
    private ZVideoView mVideoView;
    private long scrollPos;

    public VideoTrimmerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoTrimmerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMaxWidth = VideoTrimmerUtil.VIDEO_FRAMES_WIDTH;
        this.mDuration = 0;
        this.isFromRestore = false;
        this.mRedProgressBarPos = 0L;
        this.scrollPos = 0L;
        this.mAnimationHandler = new Handler();
        this.mOnRangeSeekBarChangeListener = new RangeSeekBarView.OnRangeSeekBarChangeListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView.3
            @Override // com.wifiled.ipixels.view.video_clip.RangeSeekBarView.OnRangeSeekBarChangeListener
            public void onRangeSeekBarValuesChanged(RangeSeekBarView bar, long minValue, long maxValue, int action, boolean isMin, RangeSeekBarView.Thumb pressedThumb) {
                Log.d(VideoTrimmerView.TAG, "-----minValue----->>>>>>" + minValue);
                Log.d(VideoTrimmerView.TAG, "-----maxValue----->>>>>>" + maxValue);
                VideoTrimmerView videoTrimmerView = VideoTrimmerView.this;
                videoTrimmerView.mLeftProgressPos = minValue + videoTrimmerView.scrollPos;
                VideoTrimmerView videoTrimmerView2 = VideoTrimmerView.this;
                videoTrimmerView2.mRedProgressBarPos = videoTrimmerView2.mLeftProgressPos;
                VideoTrimmerView videoTrimmerView3 = VideoTrimmerView.this;
                videoTrimmerView3.mRightProgressPos = maxValue + videoTrimmerView3.scrollPos;
                Log.d(VideoTrimmerView.TAG, "-----mLeftProgressPos----->>>>>>" + VideoTrimmerView.this.mLeftProgressPos);
                Log.d(VideoTrimmerView.TAG, "-----mRightProgressPos----->>>>>>" + VideoTrimmerView.this.mRightProgressPos);
                if (action == 0) {
                    VideoTrimmerView.this.isSeeking = false;
                } else if (action == 1) {
                    VideoTrimmerView.this.isSeeking = false;
                    VideoTrimmerView.this.seekTo((int) r3.mLeftProgressPos);
                } else if (action == 2) {
                    VideoTrimmerView.this.isSeeking = true;
                    VideoTrimmerView.this.seekTo((int) (pressedThumb == RangeSeekBarView.Thumb.MIN ? VideoTrimmerView.this.mLeftProgressPos : VideoTrimmerView.this.mRightProgressPos));
                }
                VideoTrimmerView.this.mRangeSeekBarView.setStartEndTime(VideoTrimmerView.this.mLeftProgressPos, VideoTrimmerView.this.mRightProgressPos);
            }
        };
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(VideoTrimmerView.TAG, "newState = " + newState);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                VideoTrimmerView.this.isSeeking = false;
                int calcScrollXDistance = VideoTrimmerView.this.calcScrollXDistance();
                if (Math.abs(VideoTrimmerView.this.lastScrollX - calcScrollXDistance) < VideoTrimmerView.this.mScaledTouchSlop) {
                    VideoTrimmerView.this.isOverScaledTouchSlop = false;
                    return;
                }
                VideoTrimmerView.this.isOverScaledTouchSlop = true;
                if (calcScrollXDistance == (-VideoTrimmerUtil.RECYCLER_VIEW_PADDING)) {
                    VideoTrimmerView.this.scrollPos = 0L;
                } else {
                    VideoTrimmerView.this.isSeeking = true;
                    VideoTrimmerView videoTrimmerView = VideoTrimmerView.this;
                    videoTrimmerView.scrollPos = (long) ((videoTrimmerView.mAverageMsPx * (VideoTrimmerUtil.RECYCLER_VIEW_PADDING + calcScrollXDistance)) / VideoTrimmerUtil.THUMB_WIDTH);
                    VideoTrimmerView videoTrimmerView2 = VideoTrimmerView.this;
                    videoTrimmerView2.mLeftProgressPos = videoTrimmerView2.mRangeSeekBarView.getSelectedMinValue() + VideoTrimmerView.this.scrollPos;
                    VideoTrimmerView videoTrimmerView3 = VideoTrimmerView.this;
                    videoTrimmerView3.mRightProgressPos = videoTrimmerView3.mRangeSeekBarView.getSelectedMaxValue() + VideoTrimmerView.this.scrollPos;
                    Log.d(VideoTrimmerView.TAG, "onScrolled >>>> mLeftProgressPos = " + VideoTrimmerView.this.mLeftProgressPos);
                    VideoTrimmerView videoTrimmerView4 = VideoTrimmerView.this;
                    videoTrimmerView4.mRedProgressBarPos = videoTrimmerView4.mLeftProgressPos;
                    if (VideoTrimmerView.this.mVideoView.isPlaying()) {
                        VideoTrimmerView.this.mVideoView.pause();
                        VideoTrimmerView.this.setPlayPauseViewIcon(false);
                    }
                    VideoTrimmerView.this.mRedProgressIcon.setVisibility(8);
                    VideoTrimmerView videoTrimmerView5 = VideoTrimmerView.this;
                    videoTrimmerView5.seekTo(videoTrimmerView5.mLeftProgressPos);
                    VideoTrimmerView.this.mRangeSeekBarView.setStartEndTime(VideoTrimmerView.this.mLeftProgressPos, VideoTrimmerView.this.mRightProgressPos);
                    VideoTrimmerView.this.mRangeSeekBarView.invalidate();
                }
                VideoTrimmerView.this.lastScrollX = calcScrollXDistance;
            }
        };
        this.mAnimationRunnable = new Runnable() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                VideoTrimmerView.this.lambda$new$3();
            }
        };
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.video_trimmer_view, (ViewGroup) this, true);
        this.mLinearVideo = (RelativeLayout) findViewById(R.id.layout_surface_view);
        this.mVideoView = (ZVideoView) findViewById(R.id.video_loader);
        this.mPlayView = (ImageView) findViewById(R.id.icon_video_play);
        this.mSeekBarLayout = (LinearLayout) findViewById(R.id.seekBarLayout);
        this.mRedProgressIcon = (ImageView) findViewById(R.id.positionIcon);
        this.mVideoShootTipTv = (TextView) findViewById(R.id.video_shoot_tip);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.video_frames_recyclerView);
        this.mVideoThumbRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        VideoTrimmerAdapter videoTrimmerAdapter = new VideoTrimmerAdapter(this.mContext);
        this.mVideoThumbAdapter = videoTrimmerAdapter;
        this.mVideoThumbRecyclerView.setAdapter(videoTrimmerAdapter);
        this.mVideoThumbRecyclerView.addOnScrollListener(this.mOnScrollListener);
        setUpListeners();
    }

    private void initRangeSeekBarView() {
        this.mLeftProgressPos = 0L;
        int i = this.mDuration;
        if (i <= VideoTrimmerUtil.MAX_SHOOT_DURATION) {
            this.mThumbsTotalCount = 10;
            this.mRightProgressPos = i;
        } else {
            this.mThumbsTotalCount = (int) (((i * 1.0f) / 10000.0f) * 10.0f);
            this.mRightProgressPos = VideoTrimmerUtil.MAX_SHOOT_DURATION;
        }
        if (this.mRangeSeekBarView == null) {
            this.mVideoThumbRecyclerView.addItemDecoration(new SpacesItemDecoration(VideoTrimmerUtil.RECYCLER_VIEW_PADDING, this.mThumbsTotalCount));
        }
        RangeSeekBarView rangeSeekBarView = new RangeSeekBarView(this.mContext, this.mLeftProgressPos, this.mRightProgressPos);
        this.mRangeSeekBarView = rangeSeekBarView;
        rangeSeekBarView.setSelectedMinValue(this.mLeftProgressPos);
        this.mRangeSeekBarView.setSelectedMaxValue(this.mRightProgressPos);
        this.mRangeSeekBarView.setStartEndTime(this.mLeftProgressPos, this.mRightProgressPos);
        this.mRangeSeekBarView.setMinShootTime(1000L);
        this.mRangeSeekBarView.setNotifyWhileDragging(true);
        this.mRangeSeekBarView.setOnRangeSeekBarChangeListener(this.mOnRangeSeekBarChangeListener);
        this.mSeekBarLayout.addView(this.mRangeSeekBarView, 0);
        if (this.mThumbsTotalCount - 10 > 0) {
            this.mAverageMsPx = (this.mDuration - VideoTrimmerUtil.MAX_SHOOT_DURATION) / (r0 - 10);
        } else {
            this.mAverageMsPx = 0.0f;
        }
        this.averagePxMs = (this.mMaxWidth * 1.0f) / (this.mRightProgressPos - this.mLeftProgressPos);
    }

    public void initVideoByURI(final Uri videoURI) {
        this.mSourceUri = videoURI;
        this.mVideoView.setVideoURI(videoURI);
        this.mVideoView.requestFocus();
        this.mVideoShootTipTv.setText(String.format(this.mContext.getResources().getString(R.string.video_shoot_tip), 10));
    }

    private void startShootVideoThumbs(final Context context, final Uri videoUri, int totalThumbsCount, long startPosition, long endPosition) {
        VideoTrimmerUtil.shootVideoThumbInBackground(context, videoUri, totalThumbsCount, startPosition, endPosition, new SingleCallback() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView$$ExternalSyntheticLambda3
            @Override // com.wifiled.ipixels.view.video_clip.SingleCallback
            public final void onSingleCallback(Object obj, Object obj2) {
                VideoTrimmerView.this.lambda$startShootVideoThumbs$1((Bitmap) obj, (Integer) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startShootVideoThumbs$0(Bitmap bitmap) {
        this.mVideoThumbAdapter.addBitmaps(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startShootVideoThumbs$1(final Bitmap bitmap, Integer num) {
        if (bitmap != null) {
            ThreadUtils.ui(new Runnable() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    VideoTrimmerView.this.lambda$startShootVideoThumbs$0(bitmap);
                }
            });
        }
    }

    private void onCancelClicked() {
        this.mOnTrimVideoListener.onCancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void videoPrepared(MediaPlayer mp) {
        this.mDuration = this.mVideoView.getDuration();
        if (!getRestoreState()) {
            seekTo((int) this.mRedProgressBarPos);
        } else {
            setRestoreState(false);
            seekTo((int) this.mRedProgressBarPos);
        }
        initRangeSeekBarView();
        this.mVideoThumbAdapter.clear();
        startShootVideoThumbs(this.mContext, this.mSourceUri, this.mThumbsTotalCount, 0L, this.mDuration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void videoCompleted() {
        seekTo(this.mLeftProgressPos);
        setPlayPauseViewIcon(false);
    }

    private void onVideoReset() {
        this.mVideoView.pause();
        setPlayPauseViewIcon(false);
    }

    private void playVideoOrPause() {
        this.mRedProgressBarPos = this.mVideoView.getCurrentPosition();
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
            pauseRedProgressAnimation();
        } else {
            this.mVideoView.start();
            playingRedProgressAnimation();
        }
        setPlayPauseViewIcon(this.mVideoView.isPlaying());
    }

    public void onVideoPause() {
        if (this.mVideoView.isPlaying()) {
            seekTo(this.mLeftProgressPos);
            this.mVideoView.pause();
            setPlayPauseViewIcon(false);
            this.mRedProgressIcon.setVisibility(8);
        }
    }

    public void setOnTrimVideoListener(VideoTrimCallback onTrimVideoListener) {
        this.mOnTrimVideoListener = onTrimVideoListener;
    }

    private void setUpListeners() {
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mp) {
                mp.setVideoScalingMode(1);
                VideoTrimmerView.this.videoPrepared(mp);
            }
        });
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mp) {
                VideoTrimmerView.this.videoCompleted();
            }
        });
        this.mLinearVideo.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoTrimmerView.this.lambda$setUpListeners$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpListeners$2(View view) {
        playVideoOrPause();
    }

    public void startCrop() {
        if (this.mRightProgressPos - this.mLeftProgressPos < 1000) {
            Toast.makeText(this.mContext, "视频长不足3秒,无法上传", 0).show();
            return;
        }
        this.mVideoView.pause();
        VideoTrimmerUtil.trim(this.mSourceUri.getPath(), new File(FileUtils.getCachePath(this.mContext), System.currentTimeMillis() + ".mp4").getAbsolutePath(), this.mLeftProgressPos, this.mRightProgressPos, this.mOnTrimVideoListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekTo(long msec) {
        this.mVideoView.seekTo((int) msec);
        Log.d(TAG, "seekTo = " + msec);
    }

    private boolean getRestoreState() {
        return this.isFromRestore;
    }

    public void setRestoreState(boolean fromRestore) {
        this.isFromRestore = fromRestore;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayPauseViewIcon(boolean isPlaying) {
        this.mPlayView.setImageResource(isPlaying ? 0 : R.mipmap.icon_video_play);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calcScrollXDistance() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mVideoThumbRecyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
        return (findFirstVisibleItemPosition * findViewByPosition.getWidth()) - findViewByPosition.getLeft();
    }

    private void playingRedProgressAnimation() {
        pauseRedProgressAnimation();
        playingAnimation();
        this.mAnimationHandler.post(this.mAnimationRunnable);
    }

    private void playingAnimation() {
        if (this.mRedProgressIcon.getVisibility() == 8) {
            this.mRedProgressIcon.setVisibility(0);
        }
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mRedProgressIcon.getLayoutParams();
        int i = (int) (VideoTrimmerUtil.RECYCLER_VIEW_PADDING + ((this.mRedProgressBarPos - this.scrollPos) * this.averagePxMs));
        float f = VideoTrimmerUtil.RECYCLER_VIEW_PADDING;
        long j = this.mRightProgressPos;
        long j2 = this.scrollPos;
        int i2 = (int) (f + ((j - j2) * this.averagePxMs));
        long j3 = (j - j2) - (this.mRedProgressBarPos - j2);
        if (j3 < 0) {
            j3 = 1000;
        }
        Log.v("ruis", "ValueAnimator setDuration" + j3);
        ValueAnimator duration = ValueAnimator.ofInt(i, i2).setDuration(j3);
        this.mRedProgressAnimator = duration;
        duration.setInterpolator(new LinearInterpolator());
        this.mRedProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wifiled.ipixels.view.video_clip.VideoTrimmerView.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.leftMargin = ((Integer) animation.getAnimatedValue()).intValue();
                VideoTrimmerView.this.mRedProgressIcon.setLayoutParams(layoutParams);
                Log.d(VideoTrimmerView.TAG, "----onAnimationUpdate--->>>>>>>" + VideoTrimmerView.this.mRedProgressBarPos);
            }
        });
        this.mRedProgressAnimator.start();
    }

    private void pauseRedProgressAnimation() {
        this.mRedProgressIcon.clearAnimation();
        ValueAnimator valueAnimator = this.mRedProgressAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mAnimationHandler.removeCallbacks(this.mAnimationRunnable);
        this.mRedProgressAnimator.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateVideoProgress, reason: merged with bridge method [inline-methods] */
    public void lambda$new$3() {
        if (this.mVideoView.getCurrentPosition() >= this.mRightProgressPos) {
            this.mRedProgressBarPos = this.mLeftProgressPos;
            pauseRedProgressAnimation();
            onVideoPause();
            return;
        }
        this.mAnimationHandler.post(this.mAnimationRunnable);
    }

    @Override // com.wifiled.ipixels.view.video_clip.IVideoTrimmerView
    public void onDestroy() {
        this.mVideoView.pause();
        pauseRedProgressAnimation();
    }
}
