package com.wifiled.ipixels.ui.text;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.ipixels.ui.adapter.TextAdapter;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextAnimUtils.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u00016B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000eJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000eJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010'\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010.\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020\u000eJ\u000e\u0010/\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\fJ\u0010\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0016J\u0010\u00104\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAnimUtils;", "Landroid/animation/Animator$AnimatorListener;", "<init>", "()V", "duration", "", "getDuration", "()J", "setDuration", "(J)V", "mRecycleView", "Ljava/lang/ref/WeakReference;", "Landroidx/recyclerview/widget/RecyclerView;", "mItemIndex", "", "iStep", "iSize", "mCurDirection", "mPrevDirection", "textAdapte", "Lcom/wifiled/ipixels/ui/adapter/TextAdapter;", "changeDuration", "", "speed", "", "setSize", "size", "loadAnim", "Landroid/animation/ObjectAnimator;", "view", "Landroid/view/View;", "type", "restore", "fixed", "left", "right", "up", "down", "flash", "breath", "TIME_AUTO_POLL", "autoPollTask", "Lcom/wifiled/ipixels/ui/text/TextAnimUtils$AutoPollTask;", "running", "", "canRun", "start", "stop", "onAnimationStart", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationCancel", "onAnimationRepeat", "AutoPollTask", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAnimUtils implements Animator.AnimatorListener {
    private static AutoPollTask autoPollTask;
    private static boolean canRun;
    private static int mCurDirection;
    private static int mItemIndex;
    private static int mPrevDirection;
    private static WeakReference<RecyclerView> mRecycleView;
    private static boolean running;
    private static TextAdapter textAdapte;
    public static final TextAnimUtils INSTANCE = new TextAnimUtils();
    private static long duration = VideoTrimmerUtil.MAX_SHOOT_DURATION;
    private static int iStep = 1;
    private static int iSize = 1;
    private static long TIME_AUTO_POLL = VideoTrimmerUtil.MAX_SHOOT_DURATION / 1000;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    private TextAnimUtils() {
    }

    public final long getDuration() {
        return duration;
    }

    public final void setDuration(long j) {
        duration = j;
    }

    public final void changeDuration(float speed) {
        duration = (long) (((1.0d - speed) * 10 * 1000) + 3000);
    }

    public final void setSize(int size) {
        iSize = size;
        if (size == 16) {
            iStep = 8;
        } else if (size == 32) {
            iStep = 4;
        } else {
            if (size != 64) {
                return;
            }
            iStep = 2;
        }
    }

    public final ObjectAnimator loadAnim(View view, int type) {
        List<TextEmojiVO> data;
        TextEmojiVO textEmojiVO;
        Intrinsics.checkNotNullParameter(view, "view");
        mPrevDirection = mCurDirection;
        mCurDirection = type;
        mItemIndex = 0;
        WeakReference<RecyclerView> weakReference = new WeakReference<>((RecyclerView) view);
        mRecycleView = weakReference;
        RecyclerView recyclerView = weakReference.get();
        Integer num = null;
        RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.wifiled.ipixels.ui.adapter.TextAdapter");
        textAdapte = (TextAdapter) adapter;
        Log.d("akon", "loadAnim  mPrevDirection:" + mPrevDirection + " curPos:" + mCurDirection);
        try {
            TextAdapter textAdapter = textAdapte;
            if (textAdapter != null && (data = textAdapter.getData()) != null && (textEmojiVO = data.get(0)) != null) {
                num = Integer.valueOf(textEmojiVO.getTextWidth());
            }
            Intrinsics.checkNotNull(num);
            iSize = num.intValue();
        } catch (Exception unused) {
            iSize = 8;
        }
        int i = iSize;
        if (i == 16) {
            iStep = 8;
        } else if (i == 32) {
            iStep = 4;
        } else if (i == 64) {
            iStep = 2;
        }
        if (type == 1 || type == 2) {
            iStep /= 2;
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
        return fixed(view);
    }

    private final ObjectAnimator fixed(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 0.0f);
        ofFloat.setDuration(duration);
        ofFloat.setRepeatCount(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        view.animate().translationY(0.0f).alpha(1.0f).setDuration(10L).start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator left(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 512.0f, 256.0f, 0.0f, -256.0f, -512.0f);
        ofFloat.setDuration(duration);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator right(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", -512.0f, -256.0f, 0.0f, 256.0f, 512.0f);
        ofFloat.setDuration(duration);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator up(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 512.0f, 256.0f, 0.0f, -256.0f, -512.0f);
        ofFloat.setDuration(duration);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        ofFloat.addListener(this);
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator down(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", -512.0f, -256.0f, 0.0f, 256.0f, 512.0f);
        ofFloat.setDuration(duration);
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
        ofFloat.setDuration(duration / 30);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    private final ObjectAnimator breath(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1.0f, 0.0f);
        ofFloat.setDuration(duration / 4);
        ofFloat.setRepeatCount(-1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        Intrinsics.checkNotNull(ofFloat);
        return ofFloat;
    }

    /* compiled from: TextAnimUtils.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextAnimUtils$AutoPollTask;", "Ljava/lang/Runnable;", TypedValues.Custom.S_REFERENCE, "Landroidx/recyclerview/widget/RecyclerView;", "<init>", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mReference", "Ljava/lang/ref/WeakReference;", "run", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AutoPollTask implements Runnable {
        private final WeakReference<RecyclerView> mReference;

        public AutoPollTask(RecyclerView recyclerView) {
            this.mReference = new WeakReference<>(recyclerView);
        }

        @Override // java.lang.Runnable
        public void run() {
            LinearLayoutManager linearLayoutManager;
            int findFirstVisibleItemPosition;
            int findLastCompletelyVisibleItemPosition;
            RecyclerView recyclerView = this.mReference.get();
            if (recyclerView != null && TextAnimUtils.running && TextAnimUtils.canRun) {
                int i = TextAnimUtils.mCurDirection;
                if (i == 1) {
                    recyclerView.scrollBy(2, 0);
                } else if (i == 2) {
                    recyclerView.scrollBy(-2, 0);
                } else if (i == 3) {
                    recyclerView.scrollBy(0, 2);
                } else if (i == 4) {
                    recyclerView.scrollBy(0, -2);
                }
                int i2 = TextAnimUtils.mCurDirection;
                if (i2 == 1 || i2 == 2) {
                    linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    Intrinsics.checkNotNull(linearLayoutManager);
                    LinearLayoutManager linearLayoutManager2 = linearLayoutManager;
                    findFirstVisibleItemPosition = linearLayoutManager2.findFirstVisibleItemPosition();
                    findLastCompletelyVisibleItemPosition = linearLayoutManager2.findLastCompletelyVisibleItemPosition();
                } else {
                    linearLayoutManager = (XFlexboxLayoutManager) recyclerView.getLayoutManager();
                    Intrinsics.checkNotNull(linearLayoutManager);
                    XFlexboxLayoutManager xFlexboxLayoutManager = linearLayoutManager;
                    findFirstVisibleItemPosition = xFlexboxLayoutManager.findFirstVisibleItemPosition();
                    findLastCompletelyVisibleItemPosition = xFlexboxLayoutManager.findLastCompletelyVisibleItemPosition();
                }
                if (findLastCompletelyVisibleItemPosition == linearLayoutManager.getItemCount() - 1 && findLastCompletelyVisibleItemPosition - findFirstVisibleItemPosition != linearLayoutManager.getItemCount() - 1) {
                    linearLayoutManager.scrollToPosition(0);
                }
                recyclerView.postDelayed(TextAnimUtils.autoPollTask, TextAnimUtils.TIME_AUTO_POLL);
            }
        }
    }

    public static /* synthetic */ void start$default(TextAnimUtils textAnimUtils, RecyclerView recyclerView, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        textAnimUtils.start(recyclerView, i);
    }

    public final void start(RecyclerView view, int type) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (running) {
            stop(view);
        }
        if (type == 0) {
            view.scrollToPosition(0);
            return;
        }
        TIME_AUTO_POLL = duration / 1000;
        canRun = true;
        running = true;
        AutoPollTask autoPollTask2 = new AutoPollTask(view);
        autoPollTask = autoPollTask2;
        view.postDelayed(autoPollTask2, TIME_AUTO_POLL);
    }

    public final void stop(RecyclerView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        running = false;
        view.removeCallbacks(autoPollTask);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        int i = mCurDirection;
        if (i == 2 || i == 4) {
            mItemIndex--;
        } else {
            mItemIndex++;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        mItemIndex = 0;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
        LinearLayoutManager linearLayoutManager;
        int findFirstVisibleItemPosition;
        int findLastCompletelyVisibleItemPosition;
        Intrinsics.checkNotNullParameter(animation, "animation");
        TextAdapter textAdapter = textAdapte;
        List<TextEmojiVO> data = textAdapter != null ? textAdapter.getData() : null;
        Intrinsics.checkNotNull(data);
        if (data.isEmpty()) {
            return;
        }
        int i = mCurDirection;
        if (i == 1 || i == 2) {
            WeakReference<RecyclerView> weakReference = mRecycleView;
            if (weakReference == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecycleView");
                weakReference = null;
            }
            RecyclerView recyclerView = weakReference.get();
            linearLayoutManager = (LinearLayoutManager) (recyclerView != null ? recyclerView.getLayoutManager() : null);
            Intrinsics.checkNotNull(linearLayoutManager);
            LinearLayoutManager linearLayoutManager2 = linearLayoutManager;
            findFirstVisibleItemPosition = linearLayoutManager2.findFirstVisibleItemPosition();
            findLastCompletelyVisibleItemPosition = linearLayoutManager2.findLastCompletelyVisibleItemPosition();
        } else {
            WeakReference<RecyclerView> weakReference2 = mRecycleView;
            if (weakReference2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecycleView");
                weakReference2 = null;
            }
            RecyclerView recyclerView2 = weakReference2.get();
            linearLayoutManager = (XFlexboxLayoutManager) (recyclerView2 != null ? recyclerView2.getLayoutManager() : null);
            Intrinsics.checkNotNull(linearLayoutManager);
            XFlexboxLayoutManager xFlexboxLayoutManager = linearLayoutManager;
            findFirstVisibleItemPosition = xFlexboxLayoutManager.findFirstVisibleItemPosition();
            findLastCompletelyVisibleItemPosition = xFlexboxLayoutManager.findLastCompletelyVisibleItemPosition();
        }
        if (findLastCompletelyVisibleItemPosition == linearLayoutManager.getItemCount() - 1) {
            mItemIndex = 0;
            linearLayoutManager.scrollToPosition(0);
            return;
        }
        if ((findLastCompletelyVisibleItemPosition - findFirstVisibleItemPosition) + findLastCompletelyVisibleItemPosition > linearLayoutManager.getItemCount() - 1) {
            mItemIndex = linearLayoutManager.getItemCount() - 1;
        } else {
            int i2 = (findLastCompletelyVisibleItemPosition + findLastCompletelyVisibleItemPosition) - findFirstVisibleItemPosition;
            mItemIndex = i2;
            if (i2 < linearLayoutManager.getItemCount() - 1 && iSize == 64) {
                mItemIndex++;
            }
        }
        linearLayoutManager.scrollToPosition(mItemIndex);
    }
}
