package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class TransactionManager {
    private static final String SAVE_POINT_PREFIX = "ORMLITE";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) TransactionManager.class);
    private static AtomicInteger savePointCounter = new AtomicInteger();
    private ConnectionSource connectionSource;

    public TransactionManager() {
    }

    public TransactionManager(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
        initialize();
    }

    public void initialize() {
        if (this.connectionSource == null) {
            throw new IllegalStateException("dataSource was not set on " + getClass().getSimpleName());
        }
    }

    public <T> T callInTransaction(Callable<T> callable) throws SQLException {
        return (T) callInTransaction(this.connectionSource, callable);
    }

    public static <T> T callInTransaction(ConnectionSource connectionSource, Callable<T> callable) throws SQLException {
        DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection();
        try {
            return (T) callInTransaction(readWriteConnection, connectionSource.saveSpecialConnection(readWriteConnection), connectionSource.getDatabaseType(), callable);
        } finally {
            connectionSource.clearSpecialConnection(readWriteConnection);
            connectionSource.releaseConnection(readWriteConnection);
        }
    }

    public static <T> T callInTransaction(DatabaseConnection databaseConnection, DatabaseType databaseType, Callable<T> callable) throws SQLException {
        return (T) callInTransaction(databaseConnection, false, databaseType, callable);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064 A[Catch: all -> 0x0072, Exception -> 0x0075, SQLException -> 0x0088, TRY_LEAVE, TryCatch #5 {Exception -> 0x0075, blocks: (B:22:0x005e, B:24:0x0064), top: B:21:0x005e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T callInTransaction(com.j256.ormlite.support.DatabaseConnection r6, boolean r7, com.j256.ormlite.db.DatabaseType r8, java.util.concurrent.Callable<T> r9) throws java.sql.SQLException {
        /*
            java.lang.String r0 = "after commit exception, rolling back to save-point also threw exception"
            java.lang.String r1 = "ORMLITE"
            java.lang.String r2 = "restored auto-commit to true"
            r3 = 1
            r4 = 0
            if (r7 != 0) goto L14
            boolean r7 = r8.isNestedSavePointsSupported()     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L11
            goto L14
        L11:
            r7 = 0
            r8 = r4
            goto L5e
        L14:
            boolean r7 = r6.isAutoCommitSupported()     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L30
            boolean r7 = r6.isAutoCommit()     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L2f
            r6.setAutoCommit(r4)     // Catch: java.lang.Throwable -> L2b
            com.j256.ormlite.logger.Logger r8 = com.j256.ormlite.misc.TransactionManager.logger     // Catch: java.lang.Throwable -> L2b
            java.lang.String r4 = "had to set auto-commit to false"
            r8.debug(r4)     // Catch: java.lang.Throwable -> L2b
            goto L2f
        L2b:
            r8 = move-exception
            r4 = r7
            r7 = r8
            goto L96
        L2f:
            r4 = r7
        L30:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L95
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L95
            java.util.concurrent.atomic.AtomicInteger r8 = com.j256.ormlite.misc.TransactionManager.savePointCounter     // Catch: java.lang.Throwable -> L95
            int r8 = r8.incrementAndGet()     // Catch: java.lang.Throwable -> L95
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L95
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L95
            java.sql.Savepoint r7 = r6.setSavePoint(r7)     // Catch: java.lang.Throwable -> L95
            if (r7 != 0) goto L51
            com.j256.ormlite.logger.Logger r8 = com.j256.ormlite.misc.TransactionManager.logger     // Catch: java.lang.Throwable -> L95
            java.lang.String r1 = "started savePoint transaction"
            r8.debug(r1)     // Catch: java.lang.Throwable -> L95
            goto L5c
        L51:
            com.j256.ormlite.logger.Logger r8 = com.j256.ormlite.misc.TransactionManager.logger     // Catch: java.lang.Throwable -> L95
            java.lang.String r1 = "started savePoint transaction {}"
            java.lang.String r5 = r7.getSavepointName()     // Catch: java.lang.Throwable -> L95
            r8.debug(r1, r5)     // Catch: java.lang.Throwable -> L95
        L5c:
            r8 = r4
            r4 = r3
        L5e:
            java.lang.Object r9 = r9.call()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75 java.sql.SQLException -> L88
            if (r4 == 0) goto L67
            commit(r6, r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75 java.sql.SQLException -> L88
        L67:
            if (r8 == 0) goto L71
            r6.setAutoCommit(r3)
            com.j256.ormlite.logger.Logger r6 = com.j256.ormlite.misc.TransactionManager.logger
            r6.debug(r2)
        L71:
            return r9
        L72:
            r7 = move-exception
            r4 = r8
            goto L96
        L75:
            r9 = move-exception
            if (r4 == 0) goto L81
            rollBack(r6, r7)     // Catch: java.lang.Throwable -> L72 java.sql.SQLException -> L7c
            goto L81
        L7c:
            com.j256.ormlite.logger.Logger r7 = com.j256.ormlite.misc.TransactionManager.logger     // Catch: java.lang.Throwable -> L72
            r7.error(r9, r0)     // Catch: java.lang.Throwable -> L72
        L81:
            java.lang.String r7 = "Transaction callable threw non-SQL exception"
            java.sql.SQLException r7 = com.j256.ormlite.misc.SqlExceptionUtil.create(r7, r9)     // Catch: java.lang.Throwable -> L72
            throw r7     // Catch: java.lang.Throwable -> L72
        L88:
            r9 = move-exception
            if (r4 == 0) goto L94
            rollBack(r6, r7)     // Catch: java.lang.Throwable -> L72 java.sql.SQLException -> L8f
            goto L94
        L8f:
            com.j256.ormlite.logger.Logger r7 = com.j256.ormlite.misc.TransactionManager.logger     // Catch: java.lang.Throwable -> L72
            r7.error(r9, r0)     // Catch: java.lang.Throwable -> L72
        L94:
            throw r9     // Catch: java.lang.Throwable -> L72
        L95:
            r7 = move-exception
        L96:
            if (r4 == 0) goto La0
            r6.setAutoCommit(r3)
            com.j256.ormlite.logger.Logger r6 = com.j256.ormlite.misc.TransactionManager.logger
            r6.debug(r2)
        La0:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.misc.TransactionManager.callInTransaction(com.j256.ormlite.support.DatabaseConnection, boolean, com.j256.ormlite.db.DatabaseType, java.util.concurrent.Callable):java.lang.Object");
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    private static void commit(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.commit(savepoint);
        if (savepointName == null) {
            logger.debug("committed savePoint transaction");
        } else {
            logger.debug("committed savePoint transaction {}", savepointName);
        }
    }

    private static void rollBack(DatabaseConnection databaseConnection, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        databaseConnection.rollback(savepoint);
        if (savepointName == null) {
            logger.debug("rolled back savePoint transaction");
        } else {
            logger.debug("rolled back savePoint transaction {}", savepointName);
        }
    }
}
