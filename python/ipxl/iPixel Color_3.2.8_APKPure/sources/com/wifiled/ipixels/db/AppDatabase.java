package com.wifiled.ipixels.db;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.wifiled.ipixels.App;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppDatabase.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&¨\u0006\r"}, d2 = {"Lcom/wifiled/ipixels/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "textDao", "Lcom/wifiled/ipixels/db/TextDao;", "imageDao", "Lcom/wifiled/ipixels/db/ImageDao;", "gifDao", "Lcom/wifiled/ipixels/db/GifDao;", "videoDao", "Lcom/wifiled/ipixels/db/VideoDao;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class AppDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AppDatabase$Companion$MIGRATION_1_2$1 MIGRATION_1_2 = new Migration() { // from class: com.wifiled.ipixels.db.AppDatabase$Companion$MIGRATION_1_2$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("alter table Text add column bright INTEGER NOT NULL DEFAULT 100");
            database.execSQL("alter table Image add column bright INTEGER NOT NULL DEFAULT 100");
            database.execSQL("alter table Gif add column bright INTEGER NOT NULL DEFAULT 100");
            database.execSQL("alter table Video add column bright INTEGER NOT NULL DEFAULT 100");
        }
    };
    private static AppDatabase instance;

    @JvmStatic
    public static final AppDatabase getDatabase() {
        return INSTANCE.getDatabase();
    }

    public abstract GifDao gifDao();

    public abstract ImageDao imageDao();

    public abstract TextDao textDao();

    public abstract VideoDao videoDao();

    /* compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/db/AppDatabase$Companion;", "", "<init>", "()V", "instance", "Lcom/wifiled/ipixels/db/AppDatabase;", "getDatabase", "MIGRATION_1_2", "com/wifiled/ipixels/db/AppDatabase$Companion$MIGRATION_1_2$1", "Lcom/wifiled/ipixels/db/AppDatabase$Companion$MIGRATION_1_2$1;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppDatabase getDatabase() {
            if (AppDatabase.instance != null) {
                AppDatabase appDatabase = AppDatabase.instance;
                Intrinsics.checkNotNull(appDatabase, "null cannot be cast to non-null type com.wifiled.ipixels.db.AppDatabase");
                return appDatabase;
            }
            return (AppDatabase) Room.databaseBuilder(App.INSTANCE.getContext(), AppDatabase.class, "wifi_led_db.db").allowMainThreadQueries().addMigrations(AppDatabase.MIGRATION_1_2).build();
        }
    }
}
