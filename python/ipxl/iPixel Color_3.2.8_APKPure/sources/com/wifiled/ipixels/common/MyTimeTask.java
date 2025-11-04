package com.wifiled.ipixels.common;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class MyTimeTask {
    private TimerTask task;
    private long time;
    private Timer timer;

    public MyTimeTask(long time, TimerTask task) {
        this.task = task;
        this.time = time;
        if (this.timer == null) {
            this.timer = new Timer();
        }
    }

    public void start() {
        this.timer.schedule(this.task, 0L, this.time);
    }

    public void stop() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            TimerTask timerTask = this.task;
            if (timerTask != null) {
                timerTask.cancel();
            }
        }
    }
}
