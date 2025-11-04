package com.wifiled.ipixels.ui.channel.text;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextAnimUtils.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010%\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\rJ\u0010\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010*\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010+\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "Landroid/animation/Animator$AnimatorListener;", "<init>", "()V", "duration", "", "getDuration", "()J", "setDuration", "(J)V", "mRecycleView", "Landroidx/recyclerview/widget/RecyclerView;", "mItemIndex", "", "iStep", "iSize", "mCurDirection", "mPrevDirection", "textAdapte", "Lcom/wifiled/ipixels/ui/channel/text/ChannelTextAdapter;", "changeDuration", "", "speed", "", "loadAnim", "Landroid/animation/ObjectAnimator;", "view", "Landroid/view/View;", "type", "restore", "fixed", "left", "right", "up", "down", "flash", "breath", "start", "onAnimationStart", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAnimUtils implements Animator.AnimatorListener {
    private int mCurDirection;
    private int mItemIndex;
    private int mPrevDirection;
    private RecyclerView mRecycleView;
    private ChannelTextAdapter textAdapte;
    private long duration = VideoTrimmerUtil.MAX_SHOOT_DURATION;
    private int iStep = 1;
    private int iSize = 1;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public final void changeDuration(float speed) {
        this.duration = (long) (((1.0d - speed) * 10 * 1000) + 3000);
    }

    public final ObjectAnimator loadAnim(View view, int type) {
        List<TextEmojiVO> data;
        TextEmojiVO textEmojiVO;
        Intrinsics.checkNotNullParameter(view, "view");
        this.mPrevDirection = this.mCurDirection;
        this.mCurDirection = type;
        this.mItemIndex = 0;
        RecyclerView recyclerView = (RecyclerView) view;
        this.mRecycleView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.wifiled.ipixels.ui.channel.text.ChannelTextAdapter");
        this.textAdapte = (ChannelTextAdapter) adapter;
        Log.d("akon", "loadAnim  mPrevDirection:" + this.mPrevDirection + " curPos:" + this.mCurDirection);
        try {
            ChannelTextAdapter channelTextAdapter = this.textAdapte;
            Integer valueOf = (channelTextAdapter == null || (data = channelTextAdapter.getData()) == null || (textEmojiVO = data.get(0)) == null) ? null : Integer.valueOf(textEmojiVO.getTextWidth());
            Intrinsics.checkNotNull(valueOf);
            this.iSize = valueOf.intValue();
        } catch (Exception unused) {
            this.iSize = 8;
        }
        int i = this.iSize;
        if (i == 16) {
            this.iStep = 8;
        } else if (i == 32) {
            this.iStep = 4;
        } else if (i == 64) {
            this.iStep = 2;
        }
        if (type == 1 || type == 2) {
            this.iStep /= 2;
        }
        switch (type) {
            case 0:
                fixed(view);
                break;
            case 1:
                return left(view);
            case 2:
                return right(view);
            case 3:
                return up(view);
            case 4:
                return down(view);
            case 5:
                return flash(view);
            case 6:
                return breath(view);
        }
        return fixed(view);
    }

    public final ObjectAnimator restore(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.mItemIndex = 0;
        return fixed(view);
    }

    private final ObjectAnimator fixed(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 0.0f);
        long j = this.duration;
        if (j > 0) {
            ofFloat.setDuration(j);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        view.animate().translationY(0.0f).alpha(1.0f).setDuration(10L).start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator left(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 256.0f, 128.0f, 0.0f, -128.0f, -256.0f);
        long j = this.duration;
        if (j > 0) {
            ofFloat.setDuration(j);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setAutoCancel(true);
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator right(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", -256.0f, -128.0f, 0.0f, 128.0f, 256.0f);
        long j = this.duration;
        if (j > 0) {
            ofFloat.setDuration(j);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator up(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 256.0f, 128.0f, 0.0f, -128.0f, -256.0f);
        long j = this.duration;
        if (j > 0) {
            ofFloat.setDuration(j);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator down(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", -256.0f, -128.0f, 0.0f, 128.0f, 256.0f);
        long j = this.duration;
        if (j > 0) {
            ofFloat.setDuration(j);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator flash(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        long j = this.duration;
        long j2 = 30;
        if (j / j2 > 0) {
            ofFloat.setDuration(j / j2);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator breath(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1.0f, 0.0f);
        long j = this.duration;
        long j2 = 4;
        if (j / j2 > 0) {
            ofFloat.setDuration(j / j2);
        } else {
            ofFloat.setDuration(VideoTrimmerUtil.MAX_SHOOT_DURATION);
        }
        ofFloat.setRepeatCount(-1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    public static /* synthetic */ void start$default(TextAnimUtils textAnimUtils, RecyclerView recyclerView, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        textAnimUtils.start(recyclerView, i);
    }

    public final void start(RecyclerView view, int type) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (type == 0) {
            view.scrollToPosition(0);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        int i = this.mCurDirection;
        if (i == 2 || i == 4) {
            this.mItemIndex--;
        } else {
            this.mItemIndex++;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.mItemIndex = 0;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.mItemIndex = 0;
    }
}
