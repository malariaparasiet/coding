package com.easysocket.connection.iowork;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.interfaces.config.IOptions;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.io.IIOManager;
import com.easysocket.interfaces.io.IReader;
import com.easysocket.interfaces.io.IWriter;

/* loaded from: classes2.dex */
public class IOManager implements IIOManager, IOptions {
    private ISocketActionDispatch actionDispatch;
    private IConnectionManager connectionManager;
    private IReader reader;
    private IWriter writer;

    public IOManager(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.connectionManager = iConnectionManager;
        this.actionDispatch = iSocketActionDispatch;
        initIO();
    }

    private void initIO() {
        this.reader = new EasyReader(this.connectionManager, this.actionDispatch);
        this.writer = new EasyWriter(this.connectionManager, this.actionDispatch);
    }

    @Override // com.easysocket.interfaces.io.IIOManager
    public void sendBytes(byte[] bArr) {
        IWriter iWriter = this.writer;
        if (iWriter != null) {
            iWriter.offer(bArr);
        }
    }

    @Override // com.easysocket.interfaces.io.IIOManager
    public void startIO() {
        IWriter iWriter = this.writer;
        if (iWriter != null) {
            iWriter.openWriter();
        }
        IReader iReader = this.reader;
        if (iReader != null) {
            iReader.openReader();
        }
    }

    @Override // com.easysocket.interfaces.io.IIOManager
    public void closeIO() {
        IWriter iWriter = this.writer;
        if (iWriter != null) {
            iWriter.closeWriter();
        }
        IReader iReader = this.reader;
        if (iReader != null) {
            iReader.closeReader();
        }
    }

    @Override // com.easysocket.interfaces.config.IOptions
    public Object setOptions(EasySocketOptions easySocketOptions) {
        IWriter iWriter = this.writer;
        if (iWriter != null) {
            iWriter.setOption(easySocketOptions);
        }
        IReader iReader = this.reader;
        if (iReader != null) {
            iReader.setOption(easySocketOptions);
        }
        return this;
    }

    @Override // com.easysocket.interfaces.config.IOptions
    public EasySocketOptions getOptions() {
        return this.connectionManager.getOptions();
    }
}
