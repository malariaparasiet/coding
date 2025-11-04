package com.wifiled.ipixels;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IRemoteCallback extends IInterface {
    public static final String DESCRIPTOR = "com.wifiled.ipixels.IRemoteCallback";

    public static class Default implements IRemoteCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wifiled.ipixels.IRemoteCallback
        public void onSensorEvent(int valueZ) throws RemoteException {
        }
    }

    void onSensorEvent(int valueZ) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteCallback {
        static final int TRANSACTION_onSensorEvent = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRemoteCallback.DESCRIPTOR);
        }

        public static IRemoteCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(IRemoteCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteCallback)) {
                return (IRemoteCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IRemoteCallback.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IRemoteCallback.DESCRIPTOR);
                return true;
            }
            if (code == 1) {
                onSensorEvent(data.readInt());
                reply.writeNoException();
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IRemoteCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteCallback.DESCRIPTOR;
            }

            @Override // com.wifiled.ipixels.IRemoteCallback
            public void onSensorEvent(int valueZ) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteCallback.DESCRIPTOR);
                    obtain.writeInt(valueZ);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
