package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.SelectArg;
import java.sql.SQLException;
import java.util.List;

/* loaded from: classes2.dex */
abstract class BaseComparison implements Comparison {
    private static final String NUMBER_CHARACTERS = "0123456789.-+";
    protected final String columnName;
    protected final FieldType fieldType;
    private final Object value;

    @Override // com.j256.ormlite.stmt.query.Comparison
    public abstract void appendOperation(StringBuilder sb);

    protected BaseComparison(String str, FieldType fieldType, Object obj, boolean z) throws SQLException {
        if (z && fieldType != null && !fieldType.isComparable()) {
            throw new SQLException("Field '" + str + "' is of data type " + fieldType.getDataPersister() + " which can not be compared");
        }
        this.columnName = str;
        this.fieldType = fieldType;
        this.value = obj;
    }

    @Override // com.j256.ormlite.stmt.query.Clause
    public void appendSql(DatabaseType databaseType, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        if (str != null) {
            databaseType.appendEscapedEntityName(sb, str);
            sb.append('.');
        }
        databaseType.appendEscapedEntityName(sb, this.columnName);
        sb.append(' ');
        appendOperation(sb);
        appendValue(databaseType, sb, list);
    }

    @Override // com.j256.ormlite.stmt.query.Comparison
    public String getColumnName() {
        return this.columnName;
    }

    @Override // com.j256.ormlite.stmt.query.Comparison
    public void appendValue(DatabaseType databaseType, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        appendArgOrValue(databaseType, this.fieldType, sb, list, this.value);
    }

    protected void appendArgOrValue(DatabaseType databaseType, FieldType fieldType, StringBuilder sb, List<ArgumentHolder> list, Object obj) throws SQLException {
        StringBuilder sb2;
        if (obj == null) {
            throw new SQLException("argument for '" + fieldType.getFieldName() + "' is null");
        }
        if (obj instanceof ArgumentHolder) {
            sb.append('?');
            ArgumentHolder argumentHolder = (ArgumentHolder) obj;
            argumentHolder.setMetaInfo(this.columnName, fieldType);
            list.add(argumentHolder);
        } else if (obj instanceof ColumnArg) {
            ColumnArg columnArg = (ColumnArg) obj;
            String tableName = columnArg.getTableName();
            if (tableName != null) {
                databaseType.appendEscapedEntityName(sb, tableName);
                sb.append('.');
            }
            databaseType.appendEscapedEntityName(sb, columnArg.getColumnName());
        } else if (fieldType.isArgumentHolderRequired()) {
            sb.append('?');
            SelectArg selectArg = new SelectArg();
            selectArg.setMetaInfo(this.columnName, fieldType);
            selectArg.setValue(obj);
            list.add(selectArg);
        } else {
            if (fieldType.isForeign() && fieldType.getType().isAssignableFrom(obj.getClass())) {
                FieldType foreignIdField = fieldType.getForeignIdField();
                appendArgOrValue(databaseType, foreignIdField, sb, list, foreignIdField.extractJavaFieldValue(obj));
                return;
            }
            sb2 = sb;
            if (fieldType.isEscapedValue()) {
                databaseType.appendEscapedWord(sb2, fieldType.convertJavaFieldToSqlArgValue(obj).toString());
            } else if (fieldType.isForeign()) {
                String obj2 = fieldType.convertJavaFieldToSqlArgValue(obj).toString();
                if (obj2.length() > 0 && NUMBER_CHARACTERS.indexOf(obj2.charAt(0)) < 0) {
                    throw new SQLException("Foreign field " + fieldType + " does not seem to be producing a numerical value '" + obj2 + "'. Maybe you are passing the wrong object to comparison: " + this);
                }
                sb2.append(obj2);
            } else {
                sb2.append(fieldType.convertJavaFieldToSqlArgValue(obj));
            }
            sb2.append(' ');
        }
        sb2 = sb;
        sb2.append(' ');
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.columnName).append(' ');
        appendOperation(sb);
        sb.append(' ');
        sb.append(this.value);
        return sb.toString();
    }
}
