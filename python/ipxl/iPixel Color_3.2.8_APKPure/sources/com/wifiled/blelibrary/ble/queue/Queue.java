package com.wifiled.blelibrary.ble.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
abstract class Queue {
    private final Runnable queueTask;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    protected DelayQueue<Task> delayQueue = new DelayQueue<>();
    private long lastTime = 0;

    public abstract void execute(RequestTask requestTask);

    static /* synthetic */ long access$022(Queue queue, long j) {
        long j2 = queue.lastTime - j;
        queue.lastTime = j2;
        return j2;
    }

    protected Queue() {
        Runnable runnable = new Runnable() { // from class: com.wifiled.blelibrary.ble.queue.Queue.1
            @Override // java.lang.Runnable
            public void run() {
                RequestTask requestTask;
                while (true) {
                    try {
                        Task take = Queue.this.delayQueue.take();
                        if (take != null && (requestTask = take.getRequestTask()) != null) {
                            Queue.this.execute(requestTask);
                            Queue.access$022(Queue.this, take.getRealTime());
                        }
                    } catch (Exception unused) {
                        return;
                    }
                }
            }
        };
        this.queueTask = runnable;
        this.executor.execute(runnable);
    }

    public void put(RequestTask requestTask) {
        long delay = requestTask.getDelay();
        this.lastTime += delay;
        this.delayQueue.put((DelayQueue<Task>) new Task(delay, this.lastTime, requestTask));
    }

    public void remove(Task task) {
        this.delayQueue.remove(task);
    }

    public void clear() {
        this.delayQueue.clear();
        this.lastTime = 0L;
    }

    public void shutDown() {
        ExecutorService executorService = this.executor;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.executor.shutdownNow();
        this.executor = null;
    }
}
