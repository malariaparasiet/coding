package com.wifiled.blelibrary.ble.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class Task implements Delayed {
    private static final String TAG = "Task";
    private static final AtomicLong atomic = new AtomicLong(0);
    private final long realTime;
    private final RequestTask requestTask;
    private final long sequenceNum = atomic.getAndIncrement();
    private final long timeOut;

    public Task(long j, long j2, RequestTask requestTask) {
        this.realTime = j;
        this.timeOut = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(j2, TimeUnit.MILLISECONDS);
        this.requestTask = requestTask;
    }

    public long getRealTime() {
        return this.realTime;
    }

    public RequestTask getRequestTask() {
        return this.requestTask;
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(this.timeOut - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override // java.lang.Comparable
    public int compareTo(Delayed delayed) {
        if (delayed == this) {
            return 0;
        }
        if (delayed instanceof Task) {
            Task task = (Task) delayed;
            long j = this.timeOut - task.timeOut;
            if (j < 0) {
                return -1;
            }
            return (j <= 0 && this.sequenceNum < task.sequenceNum) ? -1 : 1;
        }
        long delay = getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS);
        if (delay == 0) {
            return 0;
        }
        return delay < 0 ? -1 : 1;
    }
}
