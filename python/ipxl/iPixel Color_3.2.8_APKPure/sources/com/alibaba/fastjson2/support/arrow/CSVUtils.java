package com.alibaba.fastjson2.support.arrow;

import com.alibaba.fastjson2.stream.StreamReader;
import com.alibaba.fastjson2.support.csv.CSVReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: classes2.dex */
public class CSVUtils {
    public static String genMaxComputeCreateTable(File file, String str) throws IOException {
        boolean z;
        CSVReader of = CSVReader.of(file, new Type[0]);
        of.readHeader();
        of.statAll();
        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(str).append(" (\n");
        List<StreamReader.ColumnStat> columnStats = of.getColumnStats();
        for (int i = 0; i < columnStats.size(); i++) {
            StreamReader.ColumnStat columnStat = columnStats.get(i);
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < columnStat.name.length(); i2++) {
                char charAt = columnStat.name.charAt(i2);
                if (charAt != 65533) {
                    if (charAt == ' ' || charAt == '-' || charAt == '+' || charAt == '.') {
                        sb2.append('_');
                    } else {
                        sb2.append(charAt);
                    }
                }
            }
            String sb3 = sb2.toString();
            for (int i3 = 0; i3 < sb3.length(); i3++) {
                char charAt2 = sb3.charAt(i3);
                boolean z2 = (charAt2 >= 'a' && charAt2 <= 'z') || (charAt2 >= 'A' && charAt2 <= 'Z') || charAt2 == '_';
                if ((i3 == 0 && !z2) || (!z2 && (charAt2 < '0' || charAt2 > '9'))) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (!z && sb3.length() > 30) {
                z = true;
            }
            sb.append('\t');
            if (z) {
                sb.append("COL_").append(i);
            } else {
                sb.append(sb3);
            }
            sb.append(' ');
            sb.append(columnStat.getInferSQLType());
            if (z) {
                sb.append(" COMMENT '");
                for (int i4 = 0; i4 < columnStat.name.length(); i4++) {
                    char charAt3 = columnStat.name.charAt(i4);
                    if (charAt3 != 65533) {
                        if (charAt3 == '\'') {
                            sb.append(charAt3);
                        }
                        sb.append(charAt3);
                    }
                }
                sb.append('\'');
            }
            if (i != columnStats.size() - 1) {
                sb.append(',');
            }
            sb.append("\n");
        }
        sb.append(");");
        return sb.toString();
    }
}
