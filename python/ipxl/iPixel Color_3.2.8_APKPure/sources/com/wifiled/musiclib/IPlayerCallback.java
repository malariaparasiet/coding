package com.wifiled.musiclib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IPlayerCallback extends IInterface {
    public static final String DESCRIPTOR = "com.wifiled.musiclib.IPlayerCallback";

    public static class Default implements IPlayerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onModeChange(int i) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onSeekChange(int i, int i2, String str, String str2) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.IPlayerCallback
        public void onStateChange(int i, int i2, int i3) throws RemoteException {
        }
    }

    void onModeChange(int i) throws RemoteException;

    void onSeekChange(int i, int i2, String str, String str2) throws RemoteException;

    void onStateChange(int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlayerCallback {
        static final int TRANSACTION_onModeChange = 3;
        static final int TRANSACTION_onSeekChange = 2;
        static final int TRANSACTION_onStateChange = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPlayerCallback.DESCRIPTOR);
        }

        public static IPlayerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPlayerCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPlayerCallback)) {
                return (IPlayerCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPlayerCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPlayerCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStateChange(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onSeekChange(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                onModeChange(parcel.readInt());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        private static class Proxy implements IPlayerCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPlayerCallback.DESCRIPTOR;
            }

            @Override // com.wifiled.musiclib.IPlayerCallback
            public void onStateChange(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPlayerCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.IPlayerCallback
            public void onSeekChange(int i, int i2, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPlayerCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.IPlayerCallback
            public void onModeChange(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPlayerCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
