package com.wifiled.gameview.mendown;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.card.MaterialCardViewHelper;
import com.wifiled.gameview.GameAction;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.R;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class GameLayout extends View implements GameAction, Cloneable {
    public static final float G = 9.8f;
    private static final String TAG = "GameLayout";
    private int Padding;
    private Bitmap bitmap;
    private long frameInterval;
    private GameCallback gameCallback;
    private boolean isAutoFall;
    private boolean isRunning;
    private long lastFrameTime;
    private long level;
    private Barrier mBarrier;
    private int mBarrierHeight;
    private int mBarrierInterval;
    private int mBarrierMoveSpeed;
    private int mBarrierStartY;
    private ArrayList<Integer> mBarrierXs;
    private ArrayList<Integer> mBarrierYs;
    private int mButtonHeight;
    private int mButtonWidth;
    private float mFallTime;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private Paint mPaint;
    private Person mPerson;
    private int mPersonMoveSpeed;
    private RectF mQuiteRectf;
    private RectF mRestartRectf;
    private Score mScore;
    private int mTextSize;
    private Thread mThread;
    private int mTotalScore;
    private int mTouchIndex;
    private MyHandler myHandler;
    private int radius;

    public GameLayout(Context context) {
        super(context);
        this.radius = 50;
        this.mBarrierMoveSpeed = 8;
        this.mPersonMoveSpeed = 20;
        this.mBarrierStartY = 500;
        this.mBarrierInterval = 500;
        this.mBarrierHeight = 60;
        this.mTouchIndex = -1;
        this.mFallTime = 0.0f;
        this.mTextSize = 16;
        this.mButtonWidth = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
        this.mButtonHeight = 120;
        this.Padding = 20;
        this.frameInterval = 60L;
        this.lastFrameTime = 0L;
        this.level = 20L;
        init();
    }

    public GameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radius = 50;
        this.mBarrierMoveSpeed = 8;
        this.mPersonMoveSpeed = 20;
        this.mBarrierStartY = 500;
        this.mBarrierInterval = 500;
        this.mBarrierHeight = 60;
        this.mTouchIndex = -1;
        this.mFallTime = 0.0f;
        this.mTextSize = 16;
        this.mButtonWidth = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
        this.mButtonHeight = 120;
        this.Padding = 20;
        this.frameInterval = 60L;
        this.lastFrameTime = 0L;
        this.level = 20L;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(-7829368);
        this.mPaint.setStrokeWidth(10.0f);
        this.bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img);
        this.isAutoFall = true;
        this.myHandler = new MyHandler();
        this.mBarrierXs = new ArrayList<>();
        this.mBarrierYs = new ArrayList<>();
        this.mTextSize = (int) TypedValue.applyDimension(2, this.mTextSize, getResources().getDisplayMetrics());
        this.isRunning = true;
    }

    @Override // com.wifiled.gameview.IGame
    public void frameInterval(long j) {
        this.frameInterval = j;
    }

    @Override // com.wifiled.gameview.IGame
    public void level(long j) {
        this.level = j;
    }

    @Override // com.wifiled.gameview.GameAction
    public void setGameCallback(GameCallback gameCallback) {
        this.gameCallback = gameCallback;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mLayoutWidth = getMeasuredWidth();
        this.mLayoutHeight = getMeasuredHeight();
        Barrier barrier = new Barrier(this.mLayoutWidth, this.mPaint);
        this.mBarrier = barrier;
        barrier.setHeight(this.mBarrierHeight);
        Person person = new Person(this.mPaint, this.radius, this.bitmap);
        this.mPerson = person;
        person.mPersonY = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
        this.mPerson.mPersonX = this.mLayoutWidth / 2;
        Score score = new Score(this.mPaint);
        this.mScore = score;
        score.x = (this.mLayoutWidth / 2) - (score.panelWidth / 2);
        this.mRestartRectf = new RectF(((this.mLayoutWidth / 2) - 20) - this.mButtonWidth, (this.mLayoutHeight * 3) / 5, r3 + this.mButtonWidth, r4 + this.mButtonHeight);
        this.mQuiteRectf = new RectF((this.mLayoutWidth / 2) + 20, (this.mLayoutHeight * 3) / 5, r3 + this.mButtonWidth, r4 + this.mButtonHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        GameLayout gameLayout;
        super.onDraw(canvas);
        Log.e("TAG", "onDraw: ");
        try {
            generateScore(canvas);
            generateBarrier(canvas);
            if (this.isAutoFall) {
                checkTouch();
            }
            generatePerson(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onDraw:Exception " + e.getMessage());
        }
        boolean checkIsGameOver = checkIsGameOver();
        this.isRunning = !checkIsGameOver;
        if (checkIsGameOver) {
            drawPanel(canvas);
            notifyGameOver(canvas);
            gameLayout = this;
            gameLayout.drawButton(canvas, this.mRestartRectf, "重来", Color.parseColor("#ae999999"), -1);
            gameLayout.drawButton(canvas, gameLayout.mQuiteRectf, "退出", Color.parseColor("#ae999999"), -1);
            GameCallback gameCallback = gameLayout.gameCallback;
            if (gameCallback != null) {
                gameCallback.onGameOver();
            }
        } else {
            gameLayout = this;
        }
        if (gameLayout.gameCallback == null || !isExpired()) {
            return;
        }
        gameLayout.gameCallback.onGameFrame(this);
        gameLayout.lastFrameTime = System.currentTimeMillis();
    }

    private boolean isExpired() {
        return System.currentTimeMillis() - this.lastFrameTime > this.frameInterval;
    }

    private void drawPanel(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setColor(Color.parseColor("#8e333333"));
        float f = this.mRestartRectf.left;
        int i = this.Padding;
        RectF rectF = new RectF(f - (i * 2), ((this.mLayoutHeight * 2) / 5) - i, this.mQuiteRectf.right + (this.Padding * 2), this.mQuiteRectf.bottom + this.Padding);
        int i2 = this.Padding;
        canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
    }

    private void notifyGameOver(Canvas canvas) {
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(this.mTextSize * 1.5f);
        this.mPaint.setColor(Color.parseColor("#cc0000"));
        this.mPaint.setFakeBoldText(false);
        canvas.drawText("Game over", this.mLayoutWidth / 2, this.mLayoutHeight / 2, this.mPaint);
    }

    private void drawButton(Canvas canvas, RectF rectF, String str, int i, int i2) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(i);
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, this.mPaint);
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setColor(i2);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        canvas.drawText(str, rectF.left + (this.mButtonWidth / 2), (int) (((rectF.top + ((fontMetrics.bottom - fontMetrics.top) / 2.0f)) + ((rectF.bottom - rectF.top) / 2.0f)) - fontMetrics.bottom), this.mPaint);
    }

    private void generateScore(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(Color.parseColor("#666666"));
        this.mScore.drawPanel(canvas);
        this.mPaint.setColor(-1);
        this.mPaint.setFakeBoldText(true);
        this.mPaint.setTextSize(this.mTextSize);
        this.mScore.drawScore(canvas, this.mTotalScore + "");
    }

    private void generateBarrier(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-12303292);
        this.mBarrierYs.clear();
        int i = 0;
        while (true) {
            if (i < this.mBarrierXs.size()) {
                this.mBarrier.mPositionX = this.mBarrierXs.get(i).intValue();
            } else {
                this.mBarrier.mPositionX = PositionUtil.getRangeX(this.mLayoutWidth);
                this.mBarrierXs.add(Integer.valueOf(this.mBarrier.mPositionX));
            }
            this.mBarrier.mPositionY = this.mBarrierStartY + (this.mBarrierInterval * i);
            this.mBarrierYs.add(Integer.valueOf(this.mBarrier.mPositionY));
            if (this.mBarrier.mPositionY > this.mLayoutHeight) {
                return;
            }
            this.mBarrier.drawBarrier(canvas);
            i++;
        }
    }

    private void generatePerson(Canvas canvas) {
        if (this.isAutoFall) {
            this.mFallTime += 20.0f;
            this.mPerson.mPersonY = (int) (r0.mPersonY + ((this.mFallTime / 1000.0f) * 9.8f));
            this.mPerson.draw(canvas);
            return;
        }
        Log.v("@time", (this.mFallTime / 1000.0f) + "");
        this.mFallTime = 0.0f;
        int i = this.mTouchIndex;
        if (i >= 0) {
            this.mPerson.mPersonY = this.mBarrierYs.get(i).intValue() - (this.radius * 2);
            this.mPerson.draw(canvas);
        }
    }

    private void checkTouch() {
        for (int i = 0; i < this.mBarrierYs.size(); i++) {
            if (isTouchBarrier(this.mBarrierXs.get(i).intValue(), this.mBarrierYs.get(i).intValue())) {
                this.mTouchIndex = i;
                this.isAutoFall = false;
            }
        }
    }

    private boolean checkIsGameOver() {
        return this.mPerson.mPersonY < 0 || this.mPerson.mPersonY > this.mLayoutHeight - (this.radius * 2);
    }

    private boolean isTouchBarrier(int i, int i2) {
        return ((float) Math.abs((this.mPerson.mPersonY + (this.radius * 2)) - i2)) <= Math.abs(((float) (this.mBarrierMoveSpeed + 10)) + ((this.mFallTime / 1000.0f) * 9.8f)) && this.mPerson.mPersonX + (this.radius * 2) >= i && this.mPerson.mPersonX <= i + this.mBarrier.getWidth();
    }

    @Override // com.wifiled.gameview.GameAction
    public void startGame() {
        Thread thread = new Thread() { // from class: com.wifiled.gameview.mendown.GameLayout.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                while (GameLayout.this.isRunning) {
                    GameLayout.this.mBarrierStartY -= GameLayout.this.mBarrierMoveSpeed;
                    if (GameLayout.this.mBarrierStartY <= (-GameLayout.this.mBarrierInterval) - GameLayout.this.mBarrierHeight) {
                        GameLayout gameLayout = GameLayout.this;
                        gameLayout.mBarrierStartY = -gameLayout.mBarrierHeight;
                        if (GameLayout.this.mBarrierXs.size() > 0) {
                            GameLayout.this.mBarrierXs.remove(0);
                        }
                        GameLayout.this.mTotalScore++;
                        GameLayout.this.mTouchIndex--;
                    }
                    GameLayout.this.myHandler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(GameLayout.this.level);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.mThread = thread;
        thread.start();
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameStart();
        }
    }

    @Override // com.wifiled.gameview.GameAction
    public void stopGame() {
        this.isRunning = false;
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameStop();
        }
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                GameLayout.this.invalidate();
            }
        }
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveLeft() {
        int i = this.mPerson.mPersonX - this.mPersonMoveSpeed;
        if (i < 0) {
            i = 0;
        }
        this.mPerson.mPersonX = i;
        checkIsOutSide(i);
        invalidate();
    }

    @Override // com.wifiled.gameview.GameAction
    public void moveRight() {
        int i = this.mPerson.mPersonX + this.mPersonMoveSpeed;
        int i2 = this.mLayoutWidth;
        int i3 = this.radius;
        if (i > i2 - (i3 * 2)) {
            i = i2 - (i3 * 2);
        }
        this.mPerson.mPersonX = i;
        checkIsOutSide(i);
        invalidate();
    }

    private void checkIsOutSide(int i) {
        this.isAutoFall = true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GameCallback gameCallback;
        if (motionEvent.getAction() == 0 && !this.isRunning) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.mRestartRectf.contains(x, y)) {
                restartGame();
            } else if (this.mQuiteRectf.contains(x, y) && (gameCallback = this.gameCallback) != null) {
                gameCallback.onGameExit();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.wifiled.gameview.GameAction
    public void restartGame() {
        this.mBarrierXs.clear();
        this.mBarrierYs.clear();
        this.mBarrierStartY = 500;
        this.mPerson.mPersonY = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
        this.mPerson.mPersonX = this.mLayoutWidth / 2;
        this.mTotalScore = 0;
        this.isAutoFall = true;
        this.mFallTime = 0.0f;
        this.isRunning = true;
        startGame();
    }

    @Override // com.wifiled.gameview.GameAction
    public void destroy() {
        this.isRunning = false;
        this.gameCallback = null;
    }
}
