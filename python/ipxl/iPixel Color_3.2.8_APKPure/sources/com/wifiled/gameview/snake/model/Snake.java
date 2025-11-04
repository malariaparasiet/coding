package com.wifiled.gameview.snake.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.wifiled.gameview.GameCallback;
import com.wifiled.gameview.snake.utils.LogUtil;
import com.wifiled.gameview.snake.view.StageView;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Snake {
    public static final int DOWN = -1;
    public static final int LEFT = -2;
    public static final int RIGHT = 2;
    public static final int UP = 1;
    private LinkedList<Point> body;
    private int currDirection;
    private GameCallback gameCallback;
    private int newDirection;
    private Point oldTail;
    private int prevDirction;
    private StageView stageView;
    private boolean isAlive = true;
    private SnakeHandler handler = new SnakeHandler();
    private long frameInterval = 150;
    private long minRefreshSec = 30;
    private long level = 20;
    private int mScore = 0;

    public boolean isInside(int i, int i2) {
        return i >= 1 && i <= 62 && i2 >= 1 && i2 <= 62;
    }

    public void setGameCallback(GameCallback gameCallback) {
        this.gameCallback = gameCallback;
    }

    public void init(StageView stageView) {
        this.isAlive = true;
        this.stageView = stageView;
        LinkedList<Point> linkedList = new LinkedList<>();
        this.body = linkedList;
        linkedList.add(new Point(32, 32));
        this.body.add(new Point(31, 32));
        this.body.add(new Point(30, 32));
        this.body.add(new Point(29, 32));
        this.body.add(new Point(28, 32));
        this.body.add(new Point(27, 32));
        this.newDirection = 2;
        this.currDirection = 2;
        this.prevDirction = 2;
    }

    public void frameInterval(long j) {
        this.frameInterval = j;
    }

    public void decreaseFrameInterval() {
        long j = this.frameInterval - 30;
        this.frameInterval = j;
        if (j <= this.minRefreshSec) {
            this.frameInterval = 30L;
        }
    }

    public void level(long j) {
        this.level = j;
    }

    public long getLevel() {
        return this.level;
    }

    public void Score(int i) {
        this.mScore = i;
    }

    public int getScore() {
        return this.mScore;
    }

    public void start() {
        this.level = 1L;
        this.handler.startWork();
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameStart();
        }
    }

    public void stop() {
        this.handler.stopWork();
        this.gameCallback.onGameStop();
    }

    public void chanageWorkState() {
        this.handler.chanageWorkState();
        this.gameCallback.onGameStop();
    }

    public void gameOver() {
        this.handler.stopWork();
        this.gameCallback.onGameOver();
    }

    public void onGameFrame(View view) {
        GameCallback gameCallback = this.gameCallback;
        if (gameCallback != null) {
            gameCallback.onGameFrame(view);
        }
    }

    public boolean isTheFoodOnMe(Food food) {
        Iterator<Point> it = this.body.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().equals(food.x, food.y)) {
                z = true;
            }
        }
        return z;
    }

    public void changeDirection(int i) {
        this.newDirection = i;
        this.prevDirction = this.currDirection;
        LogUtil.d(getClass(), NotificationCompat.CATEGORY_EVENT, "方向改变" + i);
    }

    public void move() {
        int i = this.newDirection;
        if (this.currDirection + i != 0) {
            this.currDirection = i;
        }
        this.oldTail = this.body.removeLast();
        int i2 = this.body.getFirst().x;
        int i3 = this.body.getFirst().y;
        int i4 = this.currDirection;
        if (i4 == -2) {
            i2--;
        } else if (i4 == -1) {
            i3++;
        } else if (i4 == 1) {
            i3--;
        } else if (i4 == 2) {
            i2++;
        }
        this.body.addFirst(new Point(i2, i3));
    }

    public void eatFood() {
        this.body.addLast(this.oldTail);
    }

    public boolean checkDie(Ground ground) {
        if (isEatBody()) {
            this.isAlive = false;
            return true;
        }
        if (!isEatBrick(ground)) {
            return false;
        }
        this.isAlive = false;
        return true;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    private boolean isEatBody() {
        Point first = this.body.getFirst();
        for (int i = 1; i < this.body.size(); i++) {
            if (this.body.get(i).equals(first.x, first.y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEatBrick(Ground ground) {
        Point first = this.body.getFirst();
        return ground.isAtBrick(first.x, first.y);
    }

    public void drawMe(Canvas canvas, Paint paint, int i, int i2) {
        for (byte b = 0; b < this.body.size(); b = (byte) (b + 1)) {
            Point point = this.body.get(b);
            if (b == 0) {
                paint.setColor(Color.parseColor("#ffcc00"));
            } else {
                paint.setColor(-16711936);
            }
            canvas.drawRect(point.x * i, point.y * i2, (point.x * i) + i, (point.y * i2) + i2, paint);
        }
    }

    public boolean isGetFood(Food food) {
        return this.body.getFirst().equals(food.x, food.y);
    }

    class SnakeHandler extends Handler {
        private boolean handlerWork;

        SnakeHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.handlerWork) {
                Snake.this.move();
                Snake.this.stageView.onMove();
                if (Snake.this.gameCallback != null) {
                    Snake.this.gameCallback.onGameFrame(Snake.this.stageView);
                }
                sendEmptyMessageDelayed(0, Snake.this.frameInterval);
            }
        }

        public void startWork() {
            this.handlerWork = true;
            sendEmptyMessageDelayed(0, Snake.this.frameInterval);
        }

        public void stopWork() {
            this.handlerWork = false;
            if (Snake.this.gameCallback != null) {
                Snake.this.gameCallback.onGameFrame(Snake.this.stageView);
            }
        }

        public void chanageWorkState() {
            this.handlerWork = false;
        }
    }

    public void destroy() {
        this.handler.removeCallbacksAndMessages(null);
        this.gameCallback = null;
    }
}
