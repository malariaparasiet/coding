package com.wifiled.ipixels.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;
import com.wifiled.baselib.utils.LogUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class FrameAnimation {
    private static final String TAG = "FrameAnimation";
    private AnimationListener mAnimationListener;
    private BitmapFactory.Options mBitmapOptions;
    private int mCurrentFrame;
    private int mDuration;
    private List<Bitmap> mFrames;
    private Handler mHandler;
    private ImageView mImageView;
    private boolean mIsRepeat;
    private boolean isPlaying = false;
    private Bitmap mBitmap = null;
    private Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.view.FrameAnimation.1
        @Override // java.lang.Runnable
        public void run() {
            if (!FrameAnimation.this.isPlaying) {
                if (FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationStop();
                    LogUtils.logi("FrameAnimation>>>[onAnimationStop]: ", new Object[0]);
                    return;
                }
                return;
            }
            FrameAnimation.this.setImgResource();
            if (FrameAnimation.this.mCurrentFrame >= FrameAnimation.this.mFrames.size() - 1) {
                if (FrameAnimation.this.mIsRepeat) {
                    if (FrameAnimation.this.mAnimationListener != null) {
                        FrameAnimation.this.mAnimationListener.onAnimationRepeat();
                    }
                    FrameAnimation.this.mCurrentFrame = 0;
                    FrameAnimation.this.mHandler.postDelayed(this, FrameAnimation.this.mDuration);
                    return;
                }
                if (FrameAnimation.this.mAnimationListener != null) {
                    FrameAnimation.this.mAnimationListener.onAnimationEnd();
                    return;
                }
                return;
            }
            FrameAnimation.this.mCurrentFrame++;
            FrameAnimation.this.mHandler.postDelayed(this, FrameAnimation.this.mDuration);
        }
    };

    public interface AnimationListener {
        void onAnimationEnd();

        void onAnimationRepeat();

        void onAnimationStart();

        void onAnimationStop();
    }

    public FrameAnimation(List<Bitmap> frames, int duration, boolean isRepeat, Handler handler) {
        this.mFrames = frames;
        this.mDuration = duration;
        this.mIsRepeat = isRepeat;
        this.mHandler = handler;
    }

    public void updata(List<Bitmap> frames) {
        this.mCurrentFrame = 0;
        this.mFrames = frames;
    }

    private void options() {
        this.mImageView.setImageBitmap(this.mFrames.get(0));
        Bitmap bitmap = ((BitmapDrawable) this.mImageView.getDrawable()).getBitmap();
        this.mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        BitmapFactory.Options options = new BitmapFactory.Options();
        this.mBitmapOptions = options;
        options.inBitmap = this.mBitmap;
        this.mBitmapOptions.inMutable = true;
        this.mBitmapOptions.inSampleSize = 1;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public void addFrame(Bitmap bitmap) {
        this.mFrames.add(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImgResource() {
        if (!this.mImageView.isShown() || this.mCurrentFrame >= this.mFrames.size()) {
            return;
        }
        Bitmap bitmap = this.mFrames.get(this.mCurrentFrame);
        if (this.mBitmap == null) {
            this.mImageView.setImageBitmap(bitmap);
        } else {
            if (bitmap != null) {
                this.mImageView.setImageBitmap(bitmap);
                return;
            }
            this.mImageView.setImageBitmap(bitmap);
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }

    public void setAnimationListener(AnimationListener listener) {
        this.mAnimationListener = listener;
    }

    public void stop() {
        this.isPlaying = false;
        this.mHandler.removeCallbacks(this.runnable);
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onAnimationStop();
        }
        this.mImageView = null;
    }

    public void start(ImageView imageView) {
        this.mImageView = imageView;
        options();
        this.isPlaying = true;
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onAnimationStart();
        }
        this.mHandler.removeCallbacks(this.runnable);
        this.mHandler.postDelayed(this.runnable, this.mDuration);
    }

    public void clear() {
        List<Bitmap> list = this.mFrames;
        if (list != null) {
            list.clear();
        }
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }
}
