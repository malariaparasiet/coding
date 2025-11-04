package com.easysocket.connection.dispatcher;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.conn.ISocketActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class SocketActionDispatcher implements ISocketActionDispatch {
    private static final LinkedBlockingQueue<ActionBean> actions = new LinkedBlockingQueue<>();
    private Thread actionThread;
    private IConnectionManager connectionManager;
    private boolean isStop;
    private SocketAddress socketAddress;
    private List<ISocketActionListener> actionListeners = new ArrayList();
    private MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();

    public SocketActionDispatcher(IConnectionManager iConnectionManager, SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
        this.connectionManager = iConnectionManager;
        startDispatchThread();
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionDispatch
    public void dispatchAction(String str) {
        dispatchAction(str, null);
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionDispatch
    public void dispatchAction(String str, Serializable serializable) {
        actions.offer(new ActionBean(str, serializable, this));
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionDispatch
    public void subscribe(ISocketActionListener iSocketActionListener) {
        if (iSocketActionListener == null || this.actionListeners.contains(iSocketActionListener)) {
            return;
        }
        this.actionListeners.add(iSocketActionListener);
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionDispatch
    public void unsubscribe(ISocketActionListener iSocketActionListener) {
        this.actionListeners.remove(iSocketActionListener);
    }

    private class DispatchThread extends Thread {
        public DispatchThread() {
            super("dispatch thread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!SocketActionDispatcher.this.isStop) {
                try {
                    ActionBean actionBean = (ActionBean) SocketActionDispatcher.actions.take();
                    if (actionBean != null && actionBean.mDispatcher != null) {
                        SocketActionDispatcher socketActionDispatcher = actionBean.mDispatcher;
                        Iterator it = new ArrayList(socketActionDispatcher.actionListeners).iterator();
                        while (it.hasNext()) {
                            socketActionDispatcher.dispatchActionToListener(actionBean.mAction, actionBean.arg, (ISocketActionListener) it.next());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected static class ActionBean {
        Serializable arg;
        String mAction;
        SocketActionDispatcher mDispatcher;

        public ActionBean(String str, Serializable serializable, SocketActionDispatcher socketActionDispatcher) {
            this.mAction = str;
            this.arg = serializable;
            this.mDispatcher = socketActionDispatcher;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchActionToListener(String str, final Serializable serializable, final ISocketActionListener iSocketActionListener) {
        str.hashCode();
        switch (str) {
            case "action_read_complete":
                this.mainThreadExecutor.execute(new Runnable() { // from class: com.easysocket.connection.dispatcher.SocketActionDispatcher.4
                    @Override // java.lang.Runnable
                    public void run() {
                        iSocketActionListener.onSocketResponse(SocketActionDispatcher.this.socketAddress, (OriginReadData) serializable);
                    }
                });
                break;
            case "action_disconnection":
                this.mainThreadExecutor.execute(new Runnable() { // from class: com.easysocket.connection.dispatcher.SocketActionDispatcher.3
                    @Override // java.lang.Runnable
                    public void run() {
                        iSocketActionListener.onSocketDisconnect(SocketActionDispatcher.this.socketAddress, (Boolean) serializable);
                    }
                });
                break;
            case "action_conn_success":
                this.mainThreadExecutor.execute(new Runnable() { // from class: com.easysocket.connection.dispatcher.SocketActionDispatcher.1
                    @Override // java.lang.Runnable
                    public void run() {
                        iSocketActionListener.onSocketConnSuccess(SocketActionDispatcher.this.socketAddress);
                    }
                });
                break;
            case "action_conn_fail":
                this.mainThreadExecutor.execute(new Runnable() { // from class: com.easysocket.connection.dispatcher.SocketActionDispatcher.2
                    @Override // java.lang.Runnable
                    public void run() {
                        iSocketActionListener.onSocketConnFail(SocketActionDispatcher.this.socketAddress, (Boolean) serializable);
                    }
                });
                break;
        }
    }

    private void startDispatchThread() {
        if (this.isStop) {
            return;
        }
        this.isStop = false;
        DispatchThread dispatchThread = new DispatchThread();
        this.actionThread = dispatchThread;
        dispatchThread.start();
    }

    @Override // com.easysocket.interfaces.conn.ISocketActionDispatch
    public void stopDispatchThread() {
        Thread thread = this.actionThread;
        if (thread == null || !thread.isAlive() || this.actionThread.isInterrupted()) {
            return;
        }
        this.isStop = true;
        this.actionThread.interrupt();
        this.actionThread = null;
    }
}
