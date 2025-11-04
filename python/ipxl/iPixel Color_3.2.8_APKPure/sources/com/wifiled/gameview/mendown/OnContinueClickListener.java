package com.wifiled.gameview.mendown;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes3.dex */
public abstract class OnContinueClickListener implements View.OnTouchListener {
    public static final int interval = 20;
    private boolean isContinue;
    private volatile MyHandler mHandler;
    private Thread mThread;
    private View view;
    private int what;

    public abstract void handleClickEvent(View view);

    public OnContinueClickListener() {
        if (this.mThread == null) {
            this.mHandler = new MyHandler();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.view = view;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isContinue = true;
            Thread thread = new Thread() { // from class: com.wifiled.gameview.mendown.OnContinueClickListener.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    while (OnContinueClickListener.this.isContinue) {
                        OnContinueClickListener.this.mHandler.sendEmptyMessage(OnContinueClickListener.this.what);
                        Log.v("@msg-what", OnContinueClickListener.this.what + "");
                        try {
                            Thread.sleep(20L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            this.mThread = thread;
            thread.start();
        } else if (action == 1 || action == 3) {
            this.isContinue = false;
            this.mThread = null;
        }
        return true;
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            OnContinueClickListener onContinueClickListener = OnContinueClickListener.this;
            onContinueClickListener.handleClickEvent(onContinueClickListener.view);
        }
    }
}
