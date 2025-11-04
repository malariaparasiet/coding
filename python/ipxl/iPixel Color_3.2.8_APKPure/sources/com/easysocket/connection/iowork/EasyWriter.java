package com.easysocket.connection.iowork;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.action.SocketAction;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.io.IWriter;
import com.easysocket.utils.LogUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: classes2.dex */
public class EasyWriter implements IWriter<EasySocketOptions> {
    private ISocketActionDispatch actionDispatch;
    private IConnectionManager connectionManager;
    private boolean isStop;
    private OutputStream outputStream;
    private EasySocketOptions socketOptions;
    private Thread writerThread;
    private LinkedBlockingDeque<byte[]> packetsToSend = new LinkedBlockingDeque<>();
    private Runnable writerTask = new Runnable() { // from class: com.easysocket.connection.iowork.EasyWriter.1
        @Override // java.lang.Runnable
        public void run() {
            while (!EasyWriter.this.isStop) {
                try {
                    EasyWriter.this.write((byte[]) EasyWriter.this.packetsToSend.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public EasyWriter(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        this.outputStream = iConnectionManager.getOutStream();
        this.actionDispatch = iSocketActionDispatch;
    }

    @Override // com.easysocket.interfaces.io.IWriter
    public void openWriter() {
        if (this.writerThread == null) {
            this.outputStream = this.connectionManager.getOutStream();
            this.isStop = false;
            Thread thread = new Thread(this.writerTask, "writer thread");
            this.writerThread = thread;
            thread.start();
        }
    }

    @Override // com.easysocket.interfaces.io.IWriter
    public void setOption(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    @Override // com.easysocket.interfaces.io.IWriter
    public void write(byte[] bArr) {
        if (bArr != null) {
            int i = 0;
            LogUtil.d("发送数据=".concat(new String(bArr, Charset.forName("utf-8"))));
            try {
                int maxWriteBytes = this.socketOptions.getMaxWriteBytes();
                int length = bArr.length;
                ByteBuffer allocate = ByteBuffer.allocate(maxWriteBytes);
                allocate.order(this.socketOptions.getReadOrder());
                while (length > 0) {
                    int min = Math.min(maxWriteBytes, length);
                    allocate.clear();
                    allocate.rewind();
                    allocate.put(bArr, i, min);
                    allocate.flip();
                    byte[] bArr2 = new byte[min];
                    allocate.get(bArr2);
                    this.outputStream.write(bArr2);
                    this.outputStream.flush();
                    i += min;
                    length -= min;
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.actionDispatch.dispatchAction(SocketAction.ACTION_CONN_FAIL, Boolean.FALSE);
            }
        }
    }

    @Override // com.easysocket.interfaces.io.IWriter
    public void offer(byte[] bArr) {
        if (this.isStop) {
            return;
        }
        this.packetsToSend.offer(bArr);
    }

    @Override // com.easysocket.interfaces.io.IWriter
    public void closeWriter() {
        try {
            OutputStream outputStream = this.outputStream;
            if (outputStream != null) {
                outputStream.close();
            }
            this.outputStream = null;
            shutDownThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shutDownThread() {
        Thread thread = this.writerThread;
        if (thread == null || !thread.isAlive() || this.writerThread.isInterrupted()) {
            return;
        }
        this.isStop = true;
        this.writerThread.interrupt();
        this.writerThread = null;
    }
}
