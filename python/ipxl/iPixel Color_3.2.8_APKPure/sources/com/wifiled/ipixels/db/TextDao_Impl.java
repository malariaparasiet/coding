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
import com.wifiled.ipixels.model.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.i18n.TextBundle;

/* loaded from: classes3.dex */
public final class TextDao_Impl implements TextDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Text> __deletionAdapterOfText;
    private final EntityInsertionAdapter<Text> __insertionAdapterOfText;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter<Text> __updateAdapterOfText;

    public TextDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfText = new EntityInsertionAdapter<Text>(__db) { // from class: com.wifiled.ipixels.db.TextDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `Text` (`id`,`text`,`textSize`,`typeFace`,`rgb`,`ledType`,`bright`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Text value) {
                stmt.bindLong(1, value.getId());
                if (value.getText() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getText());
                }
                stmt.bindLong(3, value.getTextSize());
                if (value.getTypeFace() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTypeFace());
                }
                stmt.bindLong(5, value.getRgb());
                stmt.bindLong(6, value.getLedType());
                stmt.bindLong(7, value.getBright());
            }
        };
        this.__deletionAdapterOfText = new EntityDeletionOrUpdateAdapter<Text>(__db) { // from class: com.wifiled.ipixels.db.TextDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Text` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Text value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfText = new EntityDeletionOrUpdateAdapter<Text>(__db) { // from class: com.wifiled.ipixels.db.TextDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `Text` SET `id` = ?,`text` = ?,`textSize` = ?,`typeFace` = ?,`rgb` = ?,`ledType` = ?,`bright` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Text value) {
                stmt.bindLong(1, value.getId());
                if (value.getText() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getText());
                }
                stmt.bindLong(3, value.getTextSize());
                if (value.getTypeFace() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTypeFace());
                }
                stmt.bindLong(5, value.getRgb());
                stmt.bindLong(6, value.getLedType());
                stmt.bindLong(7, value.getBright());
                stmt.bindLong(8, value.getId());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.wifiled.ipixels.db.TextDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM text";
            }
        };
    }

    @Override // com.wifiled.ipixels.db.TextDao
    public long add(final Text text) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfText.insertAndReturnId(text);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.TextDao
    public int delete(final Text text) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__deletionAdapterOfText.handle(text);
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.TextDao
    public int update(final Text text) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__updateAdapterOfText.handle(text);
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wifiled.ipixels.db.TextDao
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

    @Override // com.wifiled.ipixels.db.TextDao
    public List<Text> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM text", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, TextBundle.TEXT_ENTRY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "textSize");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "typeFace");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "rgb");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "ledType");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "bright");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new Text(query.getInt(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getInt(columnIndexOrThrow7)));
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
