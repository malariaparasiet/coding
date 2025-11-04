package com.wifiled.ipixels.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.i18n.TextBundle;

/* loaded from: classes3.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile GifDao _gifDao;
    private volatile ImageDao _imageDao;
    private volatile TextDao _textDao;
    private volatile VideoDao _videoDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) { // from class: com.wifiled.ipixels.db.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Text` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `text` TEXT NOT NULL, `textSize` INTEGER NOT NULL, `typeFace` TEXT NOT NULL, `rgb` INTEGER NOT NULL, `ledType` INTEGER NOT NULL, `bright` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Image` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT NOT NULL, `bgr_url` TEXT NOT NULL, `ledType` INTEGER NOT NULL, `bright` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Gif` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT NOT NULL, `bgr_url` TEXT NOT NULL, `ledType` INTEGER NOT NULL, `bright` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Video` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT NOT NULL, `bgr_url` TEXT NOT NULL, `ledType` INTEGER NOT NULL, `bright` INTEGER NOT NULL)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7b2d7d8a547b832114b6409e9b8ee6ef')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Text`");
                _db.execSQL("DROP TABLE IF EXISTS `Image`");
                _db.execSQL("DROP TABLE IF EXISTS `Gif`");
                _db.execSQL("DROP TABLE IF EXISTS `Video`");
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                AppDatabase_Impl.this.mDatabase = _db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(7);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put(TextBundle.TEXT_ENTRY, new TableInfo.Column(TextBundle.TEXT_ENTRY, "TEXT", true, 0, null, 1));
                hashMap.put("textSize", new TableInfo.Column("textSize", "INTEGER", true, 0, null, 1));
                hashMap.put("typeFace", new TableInfo.Column("typeFace", "TEXT", true, 0, null, 1));
                hashMap.put("rgb", new TableInfo.Column("rgb", "INTEGER", true, 0, null, 1));
                hashMap.put("ledType", new TableInfo.Column("ledType", "INTEGER", true, 0, null, 1));
                hashMap.put("bright", new TableInfo.Column("bright", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("Text", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(_db, "Text");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "Text(com.wifiled.ipixels.model.Text).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(5);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put(ImagesContract.URL, new TableInfo.Column(ImagesContract.URL, "TEXT", true, 0, null, 1));
                hashMap2.put("bgr_url", new TableInfo.Column("bgr_url", "TEXT", true, 0, null, 1));
                hashMap2.put("ledType", new TableInfo.Column("ledType", "INTEGER", true, 0, null, 1));
                hashMap2.put("bright", new TableInfo.Column("bright", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("Image", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(_db, "Image");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "Image(com.wifiled.ipixels.model.Image).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(5);
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap3.put(ImagesContract.URL, new TableInfo.Column(ImagesContract.URL, "TEXT", true, 0, null, 1));
                hashMap3.put("bgr_url", new TableInfo.Column("bgr_url", "TEXT", true, 0, null, 1));
                hashMap3.put("ledType", new TableInfo.Column("ledType", "INTEGER", true, 0, null, 1));
                hashMap3.put("bright", new TableInfo.Column("bright", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("Gif", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(_db, "Gif");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "Gif(com.wifiled.ipixels.model.Gif).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(5);
                hashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap4.put(ImagesContract.URL, new TableInfo.Column(ImagesContract.URL, "TEXT", true, 0, null, 1));
                hashMap4.put("bgr_url", new TableInfo.Column("bgr_url", "TEXT", true, 0, null, 1));
                hashMap4.put("ledType", new TableInfo.Column("ledType", "INTEGER", true, 0, null, 1));
                hashMap4.put("bright", new TableInfo.Column("bright", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo4 = new TableInfo("Video", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(_db, "Video");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "Video(com.wifiled.ipixels.model.Video).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "7b2d7d8a547b832114b6409e9b8ee6ef", "cb35a592f2bac6deb27b0cedb3dcca38")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Text", "Image", "Gif", "Video");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Text`");
            writableDatabase.execSQL("DELETE FROM `Image`");
            writableDatabase.execSQL("DELETE FROM `Gif`");
            writableDatabase.execSQL("DELETE FROM `Video`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(TextDao.class, TextDao_Impl.getRequiredConverters());
        hashMap.put(ImageDao.class, ImageDao_Impl.getRequiredConverters());
        hashMap.put(GifDao.class, GifDao_Impl.getRequiredConverters());
        hashMap.put(VideoDao.class, VideoDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.wifiled.ipixels.db.AppDatabase
    public TextDao textDao() {
        TextDao textDao;
        if (this._textDao != null) {
            return this._textDao;
        }
        synchronized (this) {
            if (this._textDao == null) {
                this._textDao = new TextDao_Impl(this);
            }
            textDao = this._textDao;
        }
        return textDao;
    }

    @Override // com.wifiled.ipixels.db.AppDatabase
    public ImageDao imageDao() {
        ImageDao imageDao;
        if (this._imageDao != null) {
            return this._imageDao;
        }
        synchronized (this) {
            if (this._imageDao == null) {
                this._imageDao = new ImageDao_Impl(this);
            }
            imageDao = this._imageDao;
        }
        return imageDao;
    }

    @Override // com.wifiled.ipixels.db.AppDatabase
    public GifDao gifDao() {
        GifDao gifDao;
        if (this._gifDao != null) {
            return this._gifDao;
        }
        synchronized (this) {
            if (this._gifDao == null) {
                this._gifDao = new GifDao_Impl(this);
            }
            gifDao = this._gifDao;
        }
        return gifDao;
    }

    @Override // com.wifiled.ipixels.db.AppDatabase
    public VideoDao videoDao() {
        VideoDao videoDao;
        if (this._videoDao != null) {
            return this._videoDao;
        }
        synchronized (this) {
            if (this._videoDao == null) {
                this._videoDao = new VideoDao_Impl(this);
            }
            videoDao = this._videoDao;
        }
        return videoDao;
    }
}
