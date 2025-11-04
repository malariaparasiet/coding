package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

/* loaded from: classes2.dex */
public class MappedPreparedStmt<T, ID> extends BaseMappedQuery<T, ID> implements PreparedQuery<T>, PreparedDelete<T>, PreparedUpdate<T> {
    private final ArgumentHolder[] argHolders;
    private final Long limit;
    private final StatementBuilder.StatementType type;

    public MappedPreparedStmt(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, FieldType[] fieldTypeArr2, ArgumentHolder[] argumentHolderArr, Long l, StatementBuilder.StatementType statementType) {
        super(tableInfo, str, fieldTypeArr, fieldTypeArr2);
        this.argHolders = argumentHolderArr;
        this.limit = l;
        this.type = statementType;
    }

    @Override // com.j256.ormlite.stmt.PreparedStmt
    public CompiledStatement compile(DatabaseConnection databaseConnection, StatementBuilder.StatementType statementType) throws SQLException {
        return compile(databaseConnection, statementType, -1);
    }

    @Override // com.j256.ormlite.stmt.PreparedStmt
    public CompiledStatement compile(DatabaseConnection databaseConnection, StatementBuilder.StatementType statementType, int i) throws SQLException {
        if (this.type != statementType) {
            throw new SQLException("Could not compile this " + this.type + " statement since the caller is expecting a " + statementType + " statement.  Check your QueryBuilder methods.");
        }
        return assignStatementArguments(databaseConnection.compileStatement(this.statement, statementType, this.argFieldTypes, i));
    }

    @Override // com.j256.ormlite.stmt.PreparedStmt
    public String getStatement() {
        return this.statement;
    }

    @Override // com.j256.ormlite.stmt.PreparedStmt
    public StatementBuilder.StatementType getType() {
        return this.type;
    }

    @Override // com.j256.ormlite.stmt.PreparedStmt
    public void setArgumentHolderValue(int i, Object obj) throws SQLException {
        if (i < 0) {
            throw new SQLException("argument holder index " + i + " must be >= 0");
        }
        ArgumentHolder[] argumentHolderArr = this.argHolders;
        if (argumentHolderArr.length <= i) {
            throw new SQLException("argument holder index " + i + " is not valid, only " + this.argHolders.length + " in statement (index starts at 0)");
        }
        argumentHolderArr[i].setValue(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025 A[Catch: all -> 0x0062, TryCatch #0 {all -> 0x0062, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x000b, B:7:0x0015, B:9:0x001a, B:11:0x0020, B:13:0x0025, B:15:0x0031, B:16:0x003e, B:18:0x0043, B:20:0x0045, B:22:0x003a, B:24:0x0048, B:26:0x005a), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0048 A[EDGE_INSN: B:23:0x0048->B:24:0x0048 BREAK  A[LOOP:0: B:11:0x0020->B:20:0x0045], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005a A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {all -> 0x0062, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x000b, B:7:0x0015, B:9:0x001a, B:11:0x0020, B:13:0x0025, B:15:0x0031, B:16:0x003e, B:18:0x0043, B:20:0x0045, B:22:0x003a, B:24:0x0048, B:26:0x005a), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.j256.ormlite.support.CompiledStatement assignStatementArguments(com.j256.ormlite.support.CompiledStatement r6) throws java.sql.SQLException {
        /*
            r5 = this;
            java.lang.Long r0 = r5.limit     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto Lb
            int r0 = r0.intValue()     // Catch: java.lang.Throwable -> L62
            r6.setMaxRows(r0)     // Catch: java.lang.Throwable -> L62
        Lb:
            com.j256.ormlite.logger.Logger r0 = com.j256.ormlite.stmt.mapped.MappedPreparedStmt.logger     // Catch: java.lang.Throwable -> L62
            com.j256.ormlite.logger.Log$Level r1 = com.j256.ormlite.logger.Log.Level.TRACE     // Catch: java.lang.Throwable -> L62
            boolean r0 = r0.isLevelEnabled(r1)     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto L1e
            com.j256.ormlite.stmt.ArgumentHolder[] r0 = r5.argHolders     // Catch: java.lang.Throwable -> L62
            int r1 = r0.length     // Catch: java.lang.Throwable -> L62
            if (r1 <= 0) goto L1e
            int r0 = r0.length     // Catch: java.lang.Throwable -> L62
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L62
            goto L1f
        L1e:
            r0 = 0
        L1f:
            r1 = 0
        L20:
            com.j256.ormlite.stmt.ArgumentHolder[] r2 = r5.argHolders     // Catch: java.lang.Throwable -> L62
            int r3 = r2.length     // Catch: java.lang.Throwable -> L62
            if (r1 >= r3) goto L48
            r2 = r2[r1]     // Catch: java.lang.Throwable -> L62
            java.lang.Object r2 = r2.getSqlArgValue()     // Catch: java.lang.Throwable -> L62
            com.j256.ormlite.field.FieldType[] r3 = r5.argFieldTypes     // Catch: java.lang.Throwable -> L62
            r3 = r3[r1]     // Catch: java.lang.Throwable -> L62
            if (r3 != 0) goto L3a
            com.j256.ormlite.stmt.ArgumentHolder[] r3 = r5.argHolders     // Catch: java.lang.Throwable -> L62
            r3 = r3[r1]     // Catch: java.lang.Throwable -> L62
            com.j256.ormlite.field.SqlType r3 = r3.getSqlType()     // Catch: java.lang.Throwable -> L62
            goto L3e
        L3a:
            com.j256.ormlite.field.SqlType r3 = r3.getSqlType()     // Catch: java.lang.Throwable -> L62
        L3e:
            r6.setObject(r1, r2, r3)     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto L45
            r0[r1] = r2     // Catch: java.lang.Throwable -> L62
        L45:
            int r1 = r1 + 1
            goto L20
        L48:
            com.j256.ormlite.logger.Logger r1 = com.j256.ormlite.stmt.mapped.MappedPreparedStmt.logger     // Catch: java.lang.Throwable -> L62
            java.lang.String r2 = "prepared statement '{}' with {} args"
            java.lang.String r3 = r5.statement     // Catch: java.lang.Throwable -> L62
            com.j256.ormlite.stmt.ArgumentHolder[] r4 = r5.argHolders     // Catch: java.lang.Throwable -> L62
            int r4 = r4.length     // Catch: java.lang.Throwable -> L62
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L62
            r1.debug(r2, r3, r4)     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto L61
            com.j256.ormlite.logger.Logger r1 = com.j256.ormlite.stmt.mapped.MappedPreparedStmt.logger     // Catch: java.lang.Throwable -> L62
            java.lang.String r2 = "prepared statement arguments: {}"
            r1.trace(r2, r0)     // Catch: java.lang.Throwable -> L62
        L61:
            return r6
        L62:
            r0 = move-exception
            r6.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.mapped.MappedPreparedStmt.assignStatementArguments(com.j256.ormlite.support.CompiledStatement):com.j256.ormlite.support.CompiledStatement");
    }
}
