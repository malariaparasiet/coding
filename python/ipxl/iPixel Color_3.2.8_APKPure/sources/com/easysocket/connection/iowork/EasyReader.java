package com.easysocket.connection.iowork;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.action.IOAction;
import com.easysocket.connection.action.SocketAction;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.exception.SocketReadExeption;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.io.IMessageProtocol;
import com.easysocket.interfaces.io.IReader;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.ThreadPoolUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class EasyReader implements IReader<EasySocketOptions> {
    private ISocketActionDispatch actionDispatch;
    private IConnectionManager connectionManager;
    private InputStream inputStream;
    private Thread readerThread;
    private ByteBuffer remainingBuf;
    private EasySocketOptions socketOptions;
    private boolean stopThread;
    OriginReadData originalData = null;
    private Runnable readerTask = new Runnable() { // from class: com.easysocket.connection.iowork.EasyReader.1
        @Override // java.lang.Runnable
        public void run() {
            ThreadPoolUtil.execute(new Runnable() { // from class: com.easysocket.connection.iowork.EasyReader.1.1
                @Override // java.lang.Runnable
                public void run() {
                    while (!EasyReader.this.stopThread) {
                        EasyReader.this.read();
                    }
                }
            });
        }
    };
    byte[] bufArray = new byte[4096];
    private boolean isReadValid = true;
    byte[] bufBodyArray = null;

    public EasyReader(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.inputStream = iConnectionManager.getInputStream();
        this.actionDispatch = iSocketActionDispatch;
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
    }

    @Override // com.easysocket.interfaces.io.IReader
    public void read() {
        if (this.originalData == null) {
            this.originalData = new OriginReadData();
        }
        IMessageProtocol messageProtocol = this.socketOptions.getMessageProtocol();
        if (messageProtocol == null) {
            readOriginDataFromSteam(this.originalData);
            return;
        }
        int headerLength = messageProtocol.getHeaderLength();
        ByteBuffer allocate = ByteBuffer.allocate(headerLength);
        allocate.order(this.socketOptions.getReadOrder());
        try {
            ByteBuffer byteBuffer = this.remainingBuf;
            if (byteBuffer != null) {
                byteBuffer.flip();
                int min = Math.min(this.remainingBuf.remaining(), headerLength);
                allocate.put(this.remainingBuf.array(), 0, min);
                if (min < headerLength) {
                    this.remainingBuf = null;
                    readHeaderFromSteam(allocate, headerLength - min);
                } else {
                    this.remainingBuf.position(headerLength);
                }
            } else {
                readHeaderFromSteam(allocate, allocate.capacity());
            }
            this.originalData.setHeaderData(allocate.array());
            int bodyLength = messageProtocol.getBodyLength(this.originalData.getHeaderData(), this.socketOptions.getReadOrder());
            if (bodyLength > 0) {
                if (bodyLength > this.socketOptions.getMaxResponseDataMb() * 1048576) {
                    throw new SocketReadExeption("解析到服务器返回的单次数据超过了规定的最大值，可能你的Socket消息的数据格式不对，本项目默认的消息格式为：Header+Body，其中Header一般保存消息长度或还有其他信息，Body保存消息内容，请规范好相关协议");
                }
                ByteBuffer allocate2 = ByteBuffer.allocate(bodyLength);
                allocate2.order(this.socketOptions.getReadOrder());
                ByteBuffer byteBuffer2 = this.remainingBuf;
                if (byteBuffer2 != null) {
                    int position = byteBuffer2.position();
                    int min2 = Math.min(this.remainingBuf.remaining(), bodyLength);
                    allocate2.put(this.remainingBuf.array(), position, min2);
                    this.remainingBuf.position(position + min2);
                    if (min2 == bodyLength) {
                        if (this.remainingBuf.remaining() > 0) {
                            ByteBuffer allocate3 = ByteBuffer.allocate(this.remainingBuf.remaining());
                            allocate3.order(this.socketOptions.getReadOrder());
                            allocate3.put(this.remainingBuf.array(), this.remainingBuf.position(), this.remainingBuf.remaining());
                            this.remainingBuf = allocate3;
                        } else {
                            this.remainingBuf = null;
                        }
                        this.originalData.setBodyData(allocate2.array());
                        LogUtil.d("接收的原始数据=" + this.originalData.getBodyString());
                        this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, this.originalData);
                        return;
                    }
                    this.remainingBuf = null;
                }
                readBodyFromStream(allocate2);
                this.originalData.setBodyData(allocate2.array());
            } else if (bodyLength == 0) {
                this.originalData.setBodyData(new byte[0]);
                ByteBuffer byteBuffer3 = this.remainingBuf;
                if (byteBuffer3 != null) {
                    if (byteBuffer3.hasRemaining()) {
                        ByteBuffer allocate4 = ByteBuffer.allocate(this.remainingBuf.remaining());
                        allocate4.order(this.socketOptions.getReadOrder());
                        allocate4.put(this.remainingBuf.array(), this.remainingBuf.position(), this.remainingBuf.remaining());
                        this.remainingBuf = allocate4;
                    } else {
                        this.remainingBuf = null;
                    }
                }
            } else if (bodyLength < 0) {
                this.connectionManager.disconnect(new Boolean(true));
                throw new SocketReadExeption("读取失败，读取到的数据长度小于0，可能是读取的过程中socket跟服务器断开了连接");
            }
            LogUtil.d("接收的原始数据=" + this.originalData.getBodyString());
            this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, this.originalData);
        } catch (Exception e) {
            e.printStackTrace();
            this.connectionManager.disconnect(new Boolean(true));
        }
    }

    @Override // com.easysocket.interfaces.io.IReader
    public void openReader() {
        if (this.readerThread == null) {
            this.inputStream = this.connectionManager.getInputStream();
            Thread thread = new Thread(this.readerTask, "reader thread");
            this.readerThread = thread;
            this.stopThread = false;
            thread.start();
        }
    }

    private void readHeaderFromSteam(ByteBuffer byteBuffer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = new byte[1];
            InputStream inputStream = this.inputStream;
            if (inputStream == null) {
                return;
            }
            int read = inputStream.read(bArr);
            if (read == -1) {
                this.connectionManager.disconnect(new Boolean(true));
                throw new SocketReadExeption("读取数据的包头失败，在" + read + "位置断开了，可能是因为socket跟服务器断开了连接");
            }
            byteBuffer.put(bArr);
        }
    }

    private void readOriginDataFromSteam(OriginReadData originReadData) {
        try {
            InputStream inputStream = this.inputStream;
            if (inputStream == null) {
                return;
            }
            int read = inputStream.read(this.bufArray);
            boolean z = read != -1;
            if (read == -1) {
                if (this.isReadValid != z) {
                    this.isReadValid = z;
                    this.actionDispatch.dispatchAction(SocketAction.ACTION_DISCONNECTION, Boolean.TRUE);
                    return;
                }
                return;
            }
            ByteBuffer allocate = ByteBuffer.allocate(read);
            allocate.put(this.bufArray, 0, read);
            originReadData.setBodyData(allocate.array());
            LogUtil.d("接收的原始数据=" + originReadData.getBodyString());
            this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, originReadData);
        } catch (IOException e) {
            e.printStackTrace();
            this.connectionManager.disconnect(Boolean.TRUE);
        }
    }

    private void readBodyFromStream(ByteBuffer byteBuffer) throws Exception {
        int read;
        while (byteBuffer.hasRemaining()) {
            if (this.bufBodyArray == null) {
                this.bufBodyArray = new byte[this.socketOptions.getMaxReadBytes()];
            }
            InputStream inputStream = this.inputStream;
            if (inputStream == null || (read = inputStream.read(this.bufBodyArray)) == -1) {
                return;
            }
            int remaining = byteBuffer.remaining();
            if (read > remaining) {
                byteBuffer.put(this.bufBodyArray, 0, remaining);
                int i = read - remaining;
                ByteBuffer allocate = ByteBuffer.allocate(i);
                this.remainingBuf = allocate;
                allocate.order(this.socketOptions.getReadOrder());
                this.remainingBuf.put(this.bufBodyArray, remaining, i);
            } else {
                byteBuffer.put(this.bufBodyArray, 0, read);
            }
        }
    }

    @Override // com.easysocket.interfaces.io.IReader
    public void closeReader() {
        try {
            InputStream inputStream = this.inputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            this.inputStream = null;
            shutDownThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.easysocket.interfaces.io.IReader
    public void setOption(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    private void shutDownThread() {
        Thread thread = this.readerThread;
        if (thread == null || !thread.isAlive() || this.readerThread.isInterrupted()) {
            return;
        }
        this.stopThread = true;
        this.readerThread.interrupt();
        this.readerThread = null;
    }
}
