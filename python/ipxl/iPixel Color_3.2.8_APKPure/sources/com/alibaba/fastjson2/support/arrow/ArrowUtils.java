package com.alibaba.fastjson2.support.arrow;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.support.csv.CSVWriter;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;

/* loaded from: classes2.dex */
public class ArrowUtils {
    static final byte DECIMAL_TYPE_WIDTH = 16;
    static final boolean LITTLE_ENDIAN;

    static {
        LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
    }

    public static void write(CSVWriter cSVWriter, VectorSchemaRoot vectorSchemaRoot) throws IOException {
        List fieldVectors = vectorSchemaRoot.getFieldVectors();
        int rowCount = vectorSchemaRoot.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int i2 = 0; i2 < fieldVectors.size(); i2++) {
                if (i2 != 0) {
                    cSVWriter.writeComma();
                }
                IntVector intVector = (FieldVector) fieldVectors.get(i2);
                if (!intVector.isNull(i)) {
                    if (intVector instanceof IntVector) {
                        cSVWriter.writeInt32(intVector.get(i));
                    } else if (intVector instanceof BigIntVector) {
                        cSVWriter.writeInt64(((BigIntVector) intVector).get(i));
                    } else if (intVector instanceof VarCharVector) {
                        cSVWriter.writeString(((VarCharVector) intVector).get(i));
                    } else if (intVector instanceof DecimalVector) {
                        writeDecimal(cSVWriter, i, (DecimalVector) intVector);
                    } else if (intVector instanceof DateMilliVector) {
                        cSVWriter.writeDate(((DateMilliVector) intVector).get(i));
                    } else if (intVector instanceof Float8Vector) {
                        cSVWriter.writeDouble(((Float8Vector) intVector).get(i));
                    } else if (intVector instanceof Float4Vector) {
                        cSVWriter.writeFloat(((Float4Vector) intVector).get(i));
                    } else if (intVector instanceof SmallIntVector) {
                        cSVWriter.writeInt32(((SmallIntVector) intVector).get(i));
                    } else if (intVector instanceof TinyIntVector) {
                        cSVWriter.writeInt32(((TinyIntVector) intVector).get(i));
                    } else if (intVector instanceof BitVector) {
                        cSVWriter.writeInt32(((BitVector) intVector).get(i));
                    } else if (intVector instanceof Decimal256Vector) {
                        cSVWriter.writeString(intVector.getObject(i).toString());
                    } else {
                        throw new JSONException("TODO : " + intVector.getClass().getName());
                    }
                }
            }
            cSVWriter.writeLine();
        }
    }

    private static void writeDecimal(CSVWriter cSVWriter, int i, DecimalVector decimalVector) {
        long reverseBytes;
        int precision = decimalVector.getPrecision();
        decimalVector.getObject(i);
        if (precision < 20) {
            long j = i * 16;
            int scale = decimalVector.getScale();
            ArrowBuf dataBuffer = decimalVector.getDataBuffer();
            if (LITTLE_ENDIAN) {
                reverseBytes = dataBuffer.getLong(j);
            } else {
                reverseBytes = Long.reverseBytes(dataBuffer.getLong(j + 8));
            }
            cSVWriter.writeDecimal(reverseBytes, scale);
            return;
        }
        cSVWriter.writeDecimal(decimalVector.getObject(i));
    }

    public static void setValue(FieldVector fieldVector, int i, String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (fieldVector instanceof IntVector) {
            ((IntVector) fieldVector).set(i, Integer.parseInt(str));
            return;
        }
        if (fieldVector instanceof BigIntVector) {
            ((BigIntVector) fieldVector).set(i, Long.parseLong(str));
            return;
        }
        if (fieldVector instanceof DecimalVector) {
            setDecimal((DecimalVector) fieldVector, i, str);
            return;
        }
        if (fieldVector instanceof DateMilliVector) {
            ((DateMilliVector) fieldVector).set(i, DateUtils.parseMillis(str));
            return;
        }
        if (fieldVector instanceof VarCharVector) {
            setString((VarCharVector) fieldVector, i, str);
            return;
        }
        if (fieldVector instanceof Float8Vector) {
            ((Float8Vector) fieldVector).set(i, Double.parseDouble(str));
            return;
        }
        if (fieldVector instanceof Float4Vector) {
            ((Float4Vector) fieldVector).set(i, Float.parseFloat(str));
            return;
        }
        if (fieldVector instanceof TinyIntVector) {
            ((TinyIntVector) fieldVector).set(i, (byte) Integer.parseInt(str));
            return;
        }
        if (fieldVector instanceof SmallIntVector) {
            ((SmallIntVector) fieldVector).set(i, (short) Integer.parseInt(str));
            return;
        }
        if (fieldVector instanceof TimeStampMilliVector) {
            ((TimeStampMilliVector) fieldVector).set(i, DateUtils.parseMillis(str));
            return;
        }
        if (fieldVector instanceof BitVector) {
            ((BitVector) fieldVector).set(i, Boolean.parseBoolean(str) ? 1 : 0);
            return;
        }
        if (fieldVector instanceof Decimal256Vector) {
            BigDecimal bigDecimal = TypeUtils.toBigDecimal(str);
            Decimal256Vector decimal256Vector = (Decimal256Vector) fieldVector;
            int scale = decimal256Vector.getScale();
            if (bigDecimal.scale() != scale) {
                bigDecimal = bigDecimal.setScale(scale, RoundingMode.CEILING);
            }
            decimal256Vector.set(i, bigDecimal);
            return;
        }
        throw new JSONException("TODO " + fieldVector.getClass());
    }

    public static void setDecimal(DecimalVector decimalVector, int i, String str) {
        if (str == null || str.isEmpty()) {
            decimalVector.setNull(i);
            return;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            setDecimal(decimalVector, i, apply, 0, apply.length);
        }
        char[] charArray = JDKUtils.getCharArray(str);
        setDecimal(decimalVector, i, charArray, 0, charArray.length);
    }

    public static void setString(VarCharVector varCharVector, int i, String str) {
        byte[] bytes;
        if (str == null || str.length() == 0) {
            varCharVector.setNull(i);
            return;
        }
        if (JDKUtils.STRING_CODER != null && JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            bytes = JDKUtils.STRING_VALUE.apply(str);
        } else {
            bytes = str.getBytes(StandardCharsets.UTF_8);
        }
        varCharVector.set(i, bytes);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0079, code lost:
    
        if (r3 < r2) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setDecimal(org.apache.arrow.vector.DecimalVector r19, int r20, char[] r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.support.arrow.ArrowUtils.setDecimal(org.apache.arrow.vector.DecimalVector, int, char[], int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0079, code lost:
    
        if (r3 < r2) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setDecimal(org.apache.arrow.vector.DecimalVector r19, int r20, byte[] r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.support.arrow.ArrowUtils.setDecimal(org.apache.arrow.vector.DecimalVector, int, byte[], int, int):void");
    }

    public static void setDecimal(DecimalVector decimalVector, int i, BigDecimal bigDecimal) {
        int scale = decimalVector.getScale();
        if (bigDecimal.scale() != scale) {
            bigDecimal = bigDecimal.setScale(scale, RoundingMode.CEILING);
        }
        if (bigDecimal.precision() < 19 && JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET != -1) {
            long j = JDKUtils.UNSAFE.getLong(bigDecimal, JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET);
            if (j != Long.MIN_VALUE) {
                long j2 = i;
                BitVectorHelper.setBit(decimalVector.getValidityBuffer(), j2);
                ArrowBuf dataBuffer = decimalVector.getDataBuffer();
                long j3 = j2 * 16;
                if (LITTLE_ENDIAN) {
                    dataBuffer.setLong(j3, j);
                    return;
                }
                dataBuffer.setLong(j3, 0L);
                dataBuffer.setLong(j3 + 8, Long.reverseBytes(j));
                return;
            }
        }
        decimalVector.set(i, bigDecimal);
    }
}
