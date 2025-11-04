package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.interfaces.CommandCallback;
import com.jieli.jl_bt_ota.model.DataInfo;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.tool.DataHandler;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class DataHandler implements IDataHandler {
    private static final String e = "DataHandler";
    private final BluetoothOTAManager a;
    private final Handler b = new Handler(Looper.getMainLooper());
    private WorkThread c;
    private DataHandlerThread d;

    /* JADX INFO: Access modifiers changed from: private */
    class DataHandlerThread extends Thread {
        private volatile boolean a;
        private volatile boolean b;
        private ArrayList<BasePacket> c;
        private final LinkedBlockingQueue<DataInfo> d;
        private final List<DataInfo> e;
        private final List<DataInfo> f;
        private TimerThread g;

        public DataHandlerThread() {
            super("DataHandlerThread");
            this.d = new LinkedBlockingQueue<>();
            this.e = Collections.synchronizedList(new ArrayList());
            this.f = Collections.synchronizedList(new ArrayList());
        }

        private void c() {
            a();
            DataInfo d = d();
            if (d != null) {
                e(d);
                return;
            }
            if (this.f.size() > 0) {
                a(500);
            } else if (this.e.size() > 0) {
                a(500);
            } else {
                e();
            }
        }

        private void d(DataInfo dataInfo) {
            final BasePacket basePacket = dataInfo.getBasePacket();
            if (basePacket == null) {
                return;
            }
            if (basePacket.getHasResponse() == 1) {
                this.f.remove(dataInfo);
            } else {
                this.e.remove(dataInfo);
            }
            final CommandCallback callback = dataInfo.getCallback();
            DataHandler.this.b.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.DataHandler$DataHandlerThread$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DataHandler.DataHandlerThread.this.a(basePacket, callback);
                }
            });
        }

        private void e() {
            TimerThread timerThread = this.g;
            if (timerThread == null || !timerThread.b) {
                return;
            }
            JL_Log.i(DataHandler.e, "-stopTimer- >>> ");
            this.g.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            if (this.b) {
                synchronized (this.d) {
                    if (this.b) {
                        JL_Log.i(DataHandler.e, "wakeUpThread:: notifyAll");
                        this.d.notifyAll();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            synchronized (this.d) {
                while (this.a) {
                    if (this.d.isEmpty()) {
                        this.b = true;
                        c();
                        JL_Log.d(DataHandler.e, "DataHandlerThread is waiting...");
                        try {
                            this.d.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.b = false;
                        c(this.d.poll());
                        c();
                    }
                }
            }
            JL_Log.e(DataHandler.e, "-DataHandlerThread- exit...");
            this.e.clear();
            this.f.clear();
            this.d.clear();
            this.a = false;
            e();
            DataHandler.this.d = null;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.a = true;
            super.start();
            JL_Log.i(DataHandler.e, "DataHandlerThread start....");
        }

        public void stopThread() {
            JL_Log.w(DataHandler.e, "-stopThread-");
            this.a = false;
            f();
        }

        public void tryToAddRecvData(DataInfo dataInfo) {
            JL_Log.d(DataHandler.e, "-tryToAddRecvData-  ret : " + a(dataInfo) + ",isWaiting = " + this.b);
        }

        public void tryToAddSendData(DataInfo dataInfo) {
            JL_Log.d(DataHandler.e, "-tryToAddSendData-  ret : " + a(dataInfo) + ",isWaiting = " + this.b);
        }

        private int b(BluetoothDevice bluetoothDevice) {
            return DataHandler.this.a.getReceiveMtu(bluetoothDevice);
        }

        private void a(int i) {
            TimerThread timerThread = this.g;
            if (timerThread != null) {
                if (timerThread.b) {
                    return;
                }
                this.g.b = true;
            } else {
                TimerThread timerThread2 = DataHandler.this.new TimerThread(i, new ThreadStateListener() { // from class: com.jieli.jl_bt_ota.tool.DataHandler.DataHandlerThread.1
                    @Override // com.jieli.jl_bt_ota.tool.DataHandler.ThreadStateListener
                    public void onFinish(long j) {
                        if (DataHandlerThread.this.g == null || DataHandlerThread.this.g.getId() != j) {
                            return;
                        }
                        DataHandlerThread.this.g = null;
                    }

                    @Override // com.jieli.jl_bt_ota.tool.DataHandler.ThreadStateListener
                    public void onStart(long j) {
                    }
                });
                this.g = timerThread2;
                timerThread2.start();
            }
        }

        private ArrayList<DataInfo> b() {
            if (this.f.size() <= 0) {
                return null;
            }
            ArrayList<DataInfo> arrayList = new ArrayList<>();
            for (DataInfo dataInfo : this.f) {
                if (dataInfo.isSend()) {
                    arrayList.add(dataInfo);
                }
            }
            return arrayList;
        }

        private void e(DataInfo dataInfo) {
            byte[] packSendBasePacket = ParseHelper.packSendBasePacket(dataInfo.getBasePacket());
            if (packSendBasePacket == null) {
                JL_Log.i(DataHandler.e, "send data :: pack data error.");
                d(dataInfo);
                return;
            }
            int a = a(dataInfo.getDevice());
            JL_Log.i(DataHandler.e, "send data : [" + CHexConver.byte2HexStr(packSendBasePacket) + "], sendMtu = " + a);
            if (packSendBasePacket.length > a + 8) {
                JL_Log.e(DataHandler.e, "send data over communication mtu [" + a + "] limit.");
                d(dataInfo);
                return;
            }
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                if (DataHandler.this.a != null) {
                    z = DataHandler.this.a.sendDataToDevice(DataHandler.this.a.getBluetoothDevice(), packSendBasePacket);
                }
                if (z) {
                    break;
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JL_Log.i(DataHandler.e, "send ret : " + z);
            if (!z) {
                d(dataInfo);
                return;
            }
            if (dataInfo.getBasePacket().getHasResponse() == 1) {
                dataInfo.setSend(true);
                dataInfo.setSendTime(Calendar.getInstance().getTimeInMillis());
            } else {
                final CommandCallback callback = dataInfo.getCallback();
                if (callback != null) {
                    DataHandler.this.b.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.DataHandler$DataHandlerThread$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            CommandCallback.this.onCommandResponse(null);
                        }
                    });
                }
                this.e.remove(dataInfo);
            }
        }

        private void b(DataInfo dataInfo) {
            final CommandCallback callback = dataInfo.getCallback();
            DataHandler.this.a.removeCacheCommand(dataInfo.getDevice(), dataInfo.getBasePacket());
            DataHandler.this.b.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.DataHandler$DataHandlerThread$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DataHandler.DataHandlerThread.this.a(callback);
                }
            });
        }

        private DataInfo d() {
            int i = 0;
            if (this.e.size() > 0) {
                while (i < this.e.size()) {
                    DataInfo dataInfo = this.e.get(i);
                    if (!dataInfo.isSend()) {
                        return dataInfo;
                    }
                    i++;
                }
                return null;
            }
            if (this.f.size() <= 0) {
                return null;
            }
            while (i < this.f.size()) {
                DataInfo dataInfo2 = this.f.get(i);
                if (!dataInfo2.isSend()) {
                    return dataInfo2;
                }
                i++;
            }
            return null;
        }

        private void c(DataInfo dataInfo) {
            if (dataInfo != null) {
                if (dataInfo.getType() == 1) {
                    ArrayList<BasePacket> findPacketData = ParseHelper.findPacketData(dataInfo.getDevice(), b(dataInfo.getDevice()), dataInfo.getRecvData());
                    if (findPacketData != null) {
                        ArrayList<BasePacket> arrayList = this.c;
                        if (arrayList != null && arrayList.size() != 0) {
                            this.c.addAll(findPacketData);
                        } else {
                            this.c = findPacketData;
                        }
                        int size = findPacketData.size();
                        int i = 0;
                        while (i < size) {
                            BasePacket basePacket = findPacketData.get(i);
                            i++;
                            JL_Log.d(DataHandler.e, "-handlerQueue- opCode : " + basePacket.getOpCode());
                        }
                        f();
                        return;
                    }
                    JL_Log.e(DataHandler.e, "-handlerQueue- findPacketData not found. ");
                    return;
                }
                if (dataInfo.getBasePacket() != null) {
                    if (dataInfo.getBasePacket().getHasResponse() == 1) {
                        if (this.f.size() < 30) {
                            this.f.add(dataInfo);
                            return;
                        } else {
                            JL_Log.i(DataHandler.e, "-handlerQueue- haveResponseDataList is busy. ");
                            DataHandler.this.a.errorEventCallback(OTAError.buildError(ErrorCode.SUB_ERR_SYS_BUSY));
                            return;
                        }
                    }
                    if (this.e.size() < 60) {
                        this.e.add(dataInfo);
                    } else {
                        JL_Log.i(DataHandler.e, "-handlerQueue- noResponseDataList is busy. ");
                        DataHandler.this.a.errorEventCallback(OTAError.buildError(ErrorCode.SUB_ERR_SYS_BUSY));
                    }
                }
            }
        }

        private int a(BluetoothDevice bluetoothDevice) {
            return DataHandler.this.a.getCommunicationMtu(bluetoothDevice);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(BasePacket basePacket, CommandCallback commandCallback) {
            BaseError buildError = OTAError.buildError(ErrorCode.SUB_ERR_SEND_FAILED);
            buildError.setOpCode(basePacket.getOpCode());
            if (commandCallback != null) {
                commandCallback.onErrCode(buildError);
            }
            DataHandler.this.a.errorEventCallback(buildError);
        }

        /* JADX WARN: Removed duplicated region for block: B:4:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean a(com.jieli.jl_bt_ota.model.DataInfo r5) {
            /*
                r4 = this;
                if (r5 == 0) goto L11
                java.util.concurrent.LinkedBlockingQueue<com.jieli.jl_bt_ota.model.DataInfo> r0 = r4.d     // Catch: java.lang.InterruptedException -> Ld
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.InterruptedException -> Ld
                r2 = 3
                boolean r5 = r0.offer(r5, r2, r1)     // Catch: java.lang.InterruptedException -> Ld
                goto L12
            Ld:
                r5 = move-exception
                r5.printStackTrace()
            L11:
                r5 = 0
            L12:
                if (r5 == 0) goto L17
                r4.f()
            L17:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.tool.DataHandler.DataHandlerThread.a(com.jieli.jl_bt_ota.model.DataInfo):boolean");
        }

        private void a() {
            ArrayList<BasePacket> arrayList = new ArrayList<>();
            ArrayList<BasePacket> arrayList2 = this.c;
            if (arrayList2 != null && arrayList2.size() > 0) {
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList(this.c);
                int size = arrayList5.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList5.get(i);
                    i++;
                    BasePacket basePacket = (BasePacket) obj;
                    byte[] packSendBasePacket = ParseHelper.packSendBasePacket(basePacket);
                    if (packSendBasePacket != null) {
                        if (DataHandler.this.a != null) {
                            DataHandler.this.a.receiveDataFromDevice(DataHandler.this.a.getBluetoothDevice(), packSendBasePacket);
                        }
                        if (basePacket.getType() == 1) {
                            arrayList3.add(basePacket);
                        } else {
                            arrayList.add(basePacket);
                        }
                    } else {
                        arrayList4.add(basePacket);
                    }
                }
                if (!arrayList3.isEmpty()) {
                    this.c.removeAll(arrayList3);
                }
                if (arrayList4.size() > 0) {
                    this.c.removeAll(arrayList4);
                }
                a(arrayList);
                return;
            }
            a((ArrayList<BasePacket>) null);
        }

        private void a(ArrayList<BasePacket> arrayList) {
            String str;
            int i;
            if (this.f.size() > 0) {
                ArrayList<DataInfo> b = b();
                JL_Log.w(DataHandler.e, "-checkHaveResponseList- waitList size : " + (b == null ? 0 : b.size()));
                if (b == null || b.size() <= 0) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                String str2 = ", data : ";
                if (arrayList == null || arrayList.size() <= 0) {
                    str = ", data : ";
                } else {
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        int i3 = i2 + 1;
                        final BasePacket basePacket = arrayList.get(i2);
                        JL_Log.w(DataHandler.e, "-checkHaveResponseList- opCode : " + basePacket.getOpCode() + ", sn : " + basePacket.getOpCodeSn());
                        int size2 = b.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size2) {
                                i = size;
                                break;
                            }
                            DataInfo dataInfo = b.get(i4);
                            int i5 = i4 + 1;
                            final DataInfo dataInfo2 = dataInfo;
                            final BasePacket basePacket2 = dataInfo2.getBasePacket();
                            if (basePacket2 != null) {
                                JL_Log.w(DataHandler.e, "-checkHaveResponseList- packet opCode : " + basePacket2.getOpCode() + ", packet sn : " + basePacket2.getOpCodeSn());
                            }
                            if (basePacket2 != null && basePacket2.getOpCode() == basePacket.getOpCode() && basePacket2.getOpCodeSn() == basePacket.getOpCodeSn()) {
                                JL_Log.w(DataHandler.e, "-checkHaveResponseList- callback");
                                int i6 = size;
                                final CommandCallback callback = dataInfo2.getCallback();
                                i = i6;
                                DataHandler.this.b.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.DataHandler$DataHandlerThread$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DataHandler.DataHandlerThread.this.a(callback, basePacket, dataInfo2, basePacket2);
                                    }
                                });
                                arrayList2.add(basePacket);
                                arrayList3.add(dataInfo2);
                                break;
                            }
                            int i7 = size;
                            if (dataInfo2.getTimeoutMs() < 500) {
                                dataInfo2.setTimeoutMs(500);
                            }
                            String str3 = str2;
                            if (timeInMillis - dataInfo2.getSendTime() > dataInfo2.getTimeoutMs()) {
                                int reSendCount = dataInfo2.getReSendCount();
                                JL_Log.e(DataHandler.e, "wait for response timeout !!! reSend count : " + reSendCount + str3 + dataInfo2);
                                if (reSendCount >= 3) {
                                    JL_Log.e(DataHandler.e, "retry count over time, callbackTimeOutError.");
                                    b(dataInfo2);
                                    arrayList2.add(basePacket);
                                    arrayList3.add(dataInfo2);
                                } else {
                                    dataInfo2.setReSendCount(reSendCount + 1);
                                    dataInfo2.setSend(false);
                                }
                            }
                            str2 = str3;
                            size = i7;
                            i4 = i5;
                        }
                        str2 = str2;
                        i2 = i3;
                        size = i;
                    }
                    str = str2;
                    if (arrayList2.size() > 0 && this.c != null) {
                        arrayList.removeAll(arrayList2);
                        this.c.removeAll(arrayList2);
                    }
                    if (arrayList.size() > 0 && this.c != null) {
                        JL_Log.e(DataHandler.e, "-checkHaveResponseList- remove unused response.");
                        this.c.removeAll(arrayList);
                    }
                    if (arrayList3.size() > 0) {
                        this.f.removeAll(arrayList3);
                        arrayList3.clear();
                        b = b();
                    }
                }
                if (b == null || b.size() <= 0) {
                    return;
                }
                int size3 = b.size();
                int i8 = 0;
                while (i8 < size3) {
                    DataInfo dataInfo3 = b.get(i8);
                    i8++;
                    DataInfo dataInfo4 = dataInfo3;
                    if (dataInfo4.getTimeoutMs() < 500) {
                        dataInfo4.setTimeoutMs(500);
                    }
                    if (timeInMillis - dataInfo4.getSendTime() > dataInfo4.getTimeoutMs()) {
                        int reSendCount2 = dataInfo4.getReSendCount();
                        JL_Log.e(DataHandler.e, "wait for response timeout 222222 !!! reSend count : " + reSendCount2 + str + dataInfo4);
                        if (reSendCount2 >= 3) {
                            JL_Log.e(DataHandler.e, "retry count over time 222222, callbackTimeOutError.");
                            b(dataInfo4);
                            arrayList3.add(dataInfo4);
                        } else {
                            dataInfo4.setReSendCount(reSendCount2 + 1);
                            dataInfo4.setSend(false);
                        }
                    }
                }
                if (arrayList3.size() > 0) {
                    this.f.removeAll(arrayList3);
                    return;
                }
                return;
            }
            if (arrayList == null || arrayList.size() <= 0 || this.c == null) {
                return;
            }
            JL_Log.e(DataHandler.e, "-checkHaveResponseList- 22222 remove unused response.");
            this.c.removeAll(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(CommandCallback commandCallback, BasePacket basePacket, DataInfo dataInfo, BasePacket basePacket2) {
            if (commandCallback != null) {
                CommandBase convert2Command = ParseHelper.convert2Command(basePacket, DataHandler.this.a.getCacheCommand(dataInfo.getDevice(), basePacket));
                if (convert2Command == null) {
                    commandCallback.onErrCode(OTAError.buildError(ErrorCode.SUB_ERR_PARSE_DATA));
                } else {
                    commandCallback.onCommandResponse(convert2Command);
                }
            }
            DataHandler.this.a.removeCacheCommand(dataInfo.getDevice(), basePacket2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(CommandCallback commandCallback) {
            BaseError buildError = OTAError.buildError(ErrorCode.SUB_ERR_SEND_TIMEOUT);
            if (commandCallback != null) {
                commandCallback.onErrCode(buildError);
            }
            DataHandler.this.a.errorEventCallback(buildError);
        }
    }

    public interface ThreadStateListener {
        void onFinish(long j);

        void onStart(long j);
    }

    private class TimerThread extends Thread {
        private final long a;
        private volatile boolean b;
        private final ThreadStateListener c;

        TimerThread(long j, ThreadStateListener threadStateListener) {
            super("TimerThread");
            this.a = j;
            this.c = threadStateListener;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.b) {
                try {
                    Thread.sleep(this.a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (DataHandler.this.d == null) {
                    break;
                } else {
                    DataHandler.this.d.f();
                }
            }
            this.b = false;
            JL_Log.w(DataHandler.e, "TimerThread is end....name : " + getName());
            ThreadStateListener threadStateListener = this.c;
            if (threadStateListener != null) {
                threadStateListener.onFinish(getId());
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.b = true;
            super.start();
            JL_Log.w(DataHandler.e, "TimerThread is start....name : " + getName());
            ThreadStateListener threadStateListener = this.c;
            if (threadStateListener != null) {
                threadStateListener.onStart(getId());
            }
        }

        synchronized void a() {
            this.b = false;
        }
    }

    public class WorkThread extends HandlerThread implements Handler.Callback {
        private static final int c = 1;
        private static final int d = 2;
        private Handler a;

        public WorkThread(String str) {
            super(str, 10);
        }

        public Handler getWorkHandler() {
            if (this.a == null) {
                this.a = new Handler(getLooper(), this);
            }
            return this.a;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                DataInfo dataInfo = (DataInfo) message.obj;
                if (DataHandler.this.d == null) {
                    return false;
                }
                DataHandler.this.d.tryToAddSendData(dataInfo);
                return false;
            }
            if (i != 2) {
                return false;
            }
            DataInfo dataInfo2 = (DataInfo) message.obj;
            if (DataHandler.this.d == null || dataInfo2 == null) {
                return false;
            }
            DataHandler.this.d.tryToAddRecvData(dataInfo2);
            return false;
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            this.a = new Handler(getLooper(), this);
        }

        public void tryToAddRecvData(DataInfo dataInfo) {
            if (this.a == null) {
                this.a = new Handler(getLooper(), this);
            }
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = dataInfo;
            this.a.sendMessage(obtainMessage);
        }

        public void tryToAddSendData(DataInfo dataInfo) {
            if (this.a == null) {
                this.a = new Handler(getLooper(), this);
            }
            Message obtainMessage = this.a.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = dataInfo;
            this.a.sendMessage(obtainMessage);
        }
    }

    public DataHandler(BluetoothOTAManager bluetoothOTAManager) {
        this.a = bluetoothOTAManager;
        a();
    }

    private void d() {
        WorkThread workThread = this.c;
        if (workThread != null) {
            workThread.quitSafely();
            this.c = null;
        }
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void addRecvData(DataInfo dataInfo) {
        if (this.c == null) {
            a();
        }
        this.c.tryToAddRecvData(dataInfo);
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void addSendData(DataInfo dataInfo) {
        if (this.c == null) {
            a();
        }
        this.c.tryToAddSendData(dataInfo);
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void release() {
        JL_Log.e(e, "-release-");
        c();
    }

    private void b() {
        if (this.c == null) {
            this.c = new WorkThread("Work_Thread");
        }
        this.c.start();
    }

    private void c() {
        DataHandlerThread dataHandlerThread = this.d;
        if (dataHandlerThread != null) {
            dataHandlerThread.stopThread();
        }
        d();
    }

    private void a() {
        if (this.d == null) {
            DataHandlerThread dataHandlerThread = new DataHandlerThread();
            this.d = dataHandlerThread;
            dataHandlerThread.start();
            b();
        }
    }
}
