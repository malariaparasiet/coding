package com.wifiled.ipixels.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.ipixels.model.Gif;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class GifDao_Impl implements GifDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Gif> __deletionAdapterOfGif;
    private final EntityInsertionAdapter<Gif> __insertionAdapterOfGif;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter<Gif> __updateAdapterOfGif;

    public GifDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfGif = new EntityInsertionAdapter<Gif>(__db) { // from class: com.wifiled.ipixels.db.GifDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `Gif` (`id`,`url`,`bgr_url`,`ledType`,`bright`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Gif value) {
                stmt.bindLong(1, value.getId());
                if (value.getUrl() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUrl());
                }
                if (value.getBgr_url() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBgr_url());
                }
                stmt.bindLong(4, value.getLedType());
                stmt.bindLong(5, value.getBright());
            }
        };
        this.__deletionAdapterOfGif = new EntityDeletionOrUpdateAdapter<Gif>(__db) { // from class: com.wifiled.ipixels.db.GifDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Gif` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Gif value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfGif = new EntityDeletionOrUpdateAdapter<Gif>(__db) { // from class: com.wifiled.ipixels.db.GifDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `Gif` SET `id` = ?,`url` = ?,`bgr_url` = ?,`ledType` = ?,`bright` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Gif value) {
                stmt.bindLong(1, value.getId());
                if (value.getUrl() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUrl());
                }
                if (value.getBgr_url() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBgr_url());
                }
                stmt.bindLong(4, value.getLedType());
                stmt.bindLong(5, value.getBright());
                stmt.bindLong(6, value.getId());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.wifiled.ipixels.db.GifDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM gif";
            }
        };
    }

    @Override // com.wifiled.ipixels.db.GifDao
    public long add(final Gif gif) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfGif.insertAndReturnId(gif);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.GifDao
    public int delete(final Gif gif) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__deletionAdapterOfGif.handle(gif);
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.GifDao
    public int update(final Gif gif) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__updateAdapterOfGif.handle(gif);
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.GifDao
    public int deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // com.wifiled.ipixels.db.GifDao
    public List<Gif> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM gif", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, ImagesContract.URL);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "bgr_url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "ledType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "bright");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new Gif(query.getInt(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
