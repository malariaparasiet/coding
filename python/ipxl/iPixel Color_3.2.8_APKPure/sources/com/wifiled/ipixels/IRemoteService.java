package com.wifiled.ipixels;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wifiled.ipixels.IRemoteCallback;

/* loaded from: classes3.dex */
public interface IRemoteService extends IInterface {
    public static final String DESCRIPTOR = "com.wifiled.ipixels.IRemoteService";

    public static class Default implements IRemoteService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wifiled.ipixels.IRemoteService
        public void registerCallback(IRemoteCallback cb) throws RemoteException {
        }

        @Override // com.wifiled.ipixels.IRemoteService
        public void unregisterCallback(IRemoteCallback cb) throws RemoteException {
        }
    }

    void registerCallback(IRemoteCallback cb) throws RemoteException;

    void unregisterCallback(IRemoteCallback cb) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteService {
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRemoteService.DESCRIPTOR);
        }

        public static IRemoteService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(IRemoteService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteService)) {
                return (IRemoteService) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IRemoteService.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IRemoteService.DESCRIPTOR);
                return true;
            }
            if (code == 1) {
                registerCallback(IRemoteCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
            } else if (code == 2) {
                unregisterCallback(IRemoteCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
            } else {
                return super.onTransact(code, data, reply, flags);
            }
            return true;
        }

        private static class Proxy implements IRemoteService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteService.DESCRIPTOR;
            }

            @Override // com.wifiled.ipixels.IRemoteService
            public void registerCallback(IRemoteCallback cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteService.DESCRIPTOR);
                    obtain.writeStrongInterface(cb);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.ipixels.IRemoteService
            public void unregisterCallback(IRemoteCallback cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteService.DESCRIPTOR);
                    obtain.writeStrongInterface(cb);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
