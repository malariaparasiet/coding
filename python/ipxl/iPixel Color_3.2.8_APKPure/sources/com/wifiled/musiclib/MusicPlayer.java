package com.wifiled.musiclib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.wifiled.musiclib.IPlayerCallback;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.List;

/* loaded from: classes3.dex */
public interface MusicPlayer extends IInterface {
    public static final String DESCRIPTOR = "com.wifiled.musiclib.MusicPlayer";

    public static class Default implements MusicPlayer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void changeMode(int i) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void changeSeek(int i) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void dataChange(List<MusicVO> list, int i, int i2) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public int getAudioSessionId() throws RemoteException {
            return 0;
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void hold() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void mute() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void next() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void pause() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void playItem(MusicVO musicVO) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void playOrPause() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void playPosition(int i) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void prev() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void registerCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void resume() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void unHold() throws RemoteException {
        }

        @Override // com.wifiled.musiclib.MusicPlayer
        public void unRegisterCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
        }
    }

    void changeMode(int i) throws RemoteException;

    void changeSeek(int i) throws RemoteException;

    void dataChange(List<MusicVO> list, int i, int i2) throws RemoteException;

    int getAudioSessionId() throws RemoteException;

    void hold() throws RemoteException;

    void mute() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void playItem(MusicVO musicVO) throws RemoteException;

    void playOrPause() throws RemoteException;

    void playPosition(int i) throws RemoteException;

    void prev() throws RemoteException;

    void registerCallback(IPlayerCallback iPlayerCallback) throws RemoteException;

    void resume() throws RemoteException;

    void unHold() throws RemoteException;

    void unRegisterCallback(IPlayerCallback iPlayerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements MusicPlayer {
        static final int TRANSACTION_changeMode = 14;
        static final int TRANSACTION_changeSeek = 15;
        static final int TRANSACTION_dataChange = 4;
        static final int TRANSACTION_getAudioSessionId = 16;
        static final int TRANSACTION_hold = 9;
        static final int TRANSACTION_mute = 11;
        static final int TRANSACTION_next = 12;
        static final int TRANSACTION_pause = 7;
        static final int TRANSACTION_playItem = 2;
        static final int TRANSACTION_playOrPause = 1;
        static final int TRANSACTION_playPosition = 3;
        static final int TRANSACTION_prev = 13;
        static final int TRANSACTION_registerCallback = 5;
        static final int TRANSACTION_resume = 8;
        static final int TRANSACTION_unHold = 10;
        static final int TRANSACTION_unRegisterCallback = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, MusicPlayer.DESCRIPTOR);
        }

        public static MusicPlayer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(MusicPlayer.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MusicPlayer)) {
                return (MusicPlayer) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(MusicPlayer.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(MusicPlayer.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    playOrPause();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    playItem((MusicVO) _Parcel.readTypedObject(parcel, MusicVO.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    playPosition(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    dataChange(parcel.createTypedArrayList(MusicVO.CREATOR), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    registerCallback(IPlayerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    unRegisterCallback(IPlayerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    resume();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    hold();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    unHold();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    mute();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    next();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    prev();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    changeMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    changeSeek(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    int audioSessionId = getAudioSessionId();
                    parcel2.writeNoException();
                    parcel2.writeInt(audioSessionId);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements MusicPlayer {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return MusicPlayer.DESCRIPTOR;
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void playOrPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void playItem(MusicVO musicVO) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, musicVO, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void playPosition(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void dataChange(List<MusicVO> list, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    _Parcel.writeTypedList(obtain, list, 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void registerCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    obtain.writeStrongInterface(iPlayerCallback);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void unRegisterCallback(IPlayerCallback iPlayerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    obtain.writeStrongInterface(iPlayerCallback);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void hold() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void unHold() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void mute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void prev() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void changeMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public void changeSeek(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.wifiled.musiclib.MusicPlayer
            public int getAudioSessionId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(MusicPlayer.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, list.get(i2), i);
            }
        }
    }
}
