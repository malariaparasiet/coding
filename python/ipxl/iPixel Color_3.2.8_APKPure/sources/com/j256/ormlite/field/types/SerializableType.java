package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;

/* loaded from: classes2.dex */
public class SerializableType extends BaseDataType {
    private static final SerializableType singleTon = new SerializableType();

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public boolean isArgumentHolderRequired() {
        return true;
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public boolean isComparable() {
        return false;
    }

    @Override // com.j256.ormlite.field.BaseFieldConverter, com.j256.ormlite.field.FieldConverter
    public boolean isStreamType() {
        return true;
    }

    public static SerializableType getSingleton() {
        return singleTon;
    }

    private SerializableType() {
        super(SqlType.SERIALIZABLE, new Class[0]);
    }

    protected SerializableType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.FieldConverter
    public Object parseDefaultString(FieldType fieldType, String str) throws SQLException {
        throw new SQLException("Default values for serializable types are not supported");
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.FieldConverter
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults databaseResults, int i) throws SQLException {
        return databaseResults.getBytes(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.j256.ormlite.field.BaseFieldConverter, com.j256.ormlite.field.FieldConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object sqlArgToJava(com.j256.ormlite.field.FieldType r4, java.lang.Object r5, int r6) throws java.sql.SQLException {
        /*
            r3 = this;
            java.lang.String r4 = "Could not read serialized object from byte array: "
            byte[] r5 = (byte[]) r5
            byte[] r5 = (byte[]) r5
            r6 = 0
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            java.lang.Object r4 = r0.readObject()     // Catch: java.lang.Exception -> L19 java.lang.Throwable -> L48
            r0.close()     // Catch: java.io.IOException -> L18
        L18:
            return r4
        L19:
            r6 = move-exception
            goto L21
        L1b:
            r4 = move-exception
            goto L4a
        L1d:
            r0 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
        L21:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = java.util.Arrays.toString(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.StringBuilder r4 = r1.append(r4)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = "(len "
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch: java.lang.Throwable -> L48
            int r5 = r5.length     // Catch: java.lang.Throwable -> L48
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.String r5 = ")"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L48
            java.sql.SQLException r4 = com.j256.ormlite.misc.SqlExceptionUtil.create(r4, r6)     // Catch: java.lang.Throwable -> L48
            throw r4     // Catch: java.lang.Throwable -> L48
        L48:
            r4 = move-exception
            r6 = r0
        L4a:
            if (r6 == 0) goto L4f
            r6.close()     // Catch: java.io.IOException -> L4f
        L4f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.field.types.SerializableType.sqlArgToJava(com.j256.ormlite.field.FieldType, java.lang.Object, int):java.lang.Object");
    }

    @Override // com.j256.ormlite.field.BaseFieldConverter, com.j256.ormlite.field.FieldConverter
    public Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            throw SqlExceptionUtil.create("Could not write serialized object to byte array: " + obj, e);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public boolean isValidForField(Field field) {
        return Serializable.class.isAssignableFrom(field.getType());
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.FieldConverter
    public Object resultStringToJava(FieldType fieldType, String str, int i) throws SQLException {
        throw new SQLException("Serializable type cannot be converted from string to Java");
    }

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public Class<?> getPrimaryClass() {
        return Serializable.class;
    }
}
