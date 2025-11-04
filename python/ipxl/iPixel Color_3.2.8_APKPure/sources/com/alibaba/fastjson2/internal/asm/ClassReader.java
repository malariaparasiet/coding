package com.alibaba.fastjson2.internal.asm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class ClassReader {
    public final byte[] b;
    public final int header;
    private final int[] items;
    private final int maxStringLength;
    private final String[] strings;

    public ClassReader(InputStream inputStream) throws IOException {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            i = 0;
            if (read == -1) {
                break;
            } else if (read > 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }
        inputStream.close();
        this.b = byteArrayOutputStream.toByteArray();
        int[] iArr = new int[readUnsignedShort(8)];
        this.items = iArr;
        int length = iArr.length;
        this.strings = new String[length];
        int i2 = 10;
        int i3 = 1;
        while (i3 < length) {
            int i4 = i2 + 1;
            this.items[i3] = i4;
            byte b = this.b[i2];
            int i5 = 3;
            if (b == 1) {
                i5 = 3 + readUnsignedShort(i4);
                if (i5 > i) {
                    i = i5;
                }
            } else if (b != 15) {
                if (b != 18 && b != 3 && b != 4) {
                    if (b == 5 || b == 6) {
                        i3++;
                        i5 = 9;
                    } else {
                        switch (b) {
                        }
                    }
                }
                i5 = 5;
            } else {
                i5 = 4;
            }
            i2 += i5;
            i3++;
        }
        this.maxStringLength = i;
        this.header = i2;
    }

    public void accept(TypeCollector typeCollector) {
        char[] cArr = new char[this.maxStringLength];
        int i = this.header;
        int readUnsignedShort = readUnsignedShort(i + 6);
        int i2 = i + 8;
        for (int i3 = 0; i3 < readUnsignedShort; i3++) {
            i2 += 2;
        }
        int i4 = i2 + 2;
        int i5 = i4;
        for (int readUnsignedShort2 = readUnsignedShort(i2); readUnsignedShort2 > 0; readUnsignedShort2--) {
            i5 += 8;
            for (int readUnsignedShort3 = readUnsignedShort(i5 + 6); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i5 += readInt(i5 + 2) + 6;
            }
        }
        int i6 = i5 + 2;
        for (int readUnsignedShort4 = readUnsignedShort(i5); readUnsignedShort4 > 0; readUnsignedShort4--) {
            i6 += 8;
            for (int readUnsignedShort5 = readUnsignedShort(i6 + 6); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i6 += readInt(i6 + 2) + 6;
            }
        }
        int i7 = i6 + 2;
        for (int readUnsignedShort6 = readUnsignedShort(i6); readUnsignedShort6 > 0; readUnsignedShort6--) {
            i7 += readInt(i7 + 2) + 6;
        }
        for (int readUnsignedShort7 = readUnsignedShort(i2); readUnsignedShort7 > 0; readUnsignedShort7--) {
            i4 += 8;
            for (int readUnsignedShort8 = readUnsignedShort(i4 + 6); readUnsignedShort8 > 0; readUnsignedShort8--) {
                i4 += readInt(i4 + 2) + 6;
            }
        }
        int i8 = i4 + 2;
        for (int readUnsignedShort9 = readUnsignedShort(i4); readUnsignedShort9 > 0; readUnsignedShort9--) {
            i8 = readMethod(typeCollector, cArr, i8);
        }
    }

    private int readMethod(TypeCollector typeCollector, char[] cArr, int i) {
        int readUnsignedShort = readUnsignedShort(i);
        String readUTF8 = readUTF8(i + 2, cArr);
        String readUTF82 = readUTF8(i + 4, cArr);
        int i2 = i + 8;
        int i3 = 0;
        int i4 = 0;
        for (int readUnsignedShort2 = readUnsignedShort(i + 6); readUnsignedShort2 > 0; readUnsignedShort2--) {
            String readUTF83 = readUTF8(i2, cArr);
            int readInt = readInt(i2 + 2);
            int i5 = i2 + 6;
            if ("Code".equals(readUTF83)) {
                i4 = i5;
            }
            i2 = i5 + readInt;
        }
        MethodCollector visitMethod = typeCollector.visitMethod(readUnsignedShort, readUTF8, readUTF82);
        if (visitMethod != null && i4 != 0) {
            int readInt2 = i4 + 8 + readInt(i4 + 4);
            int i6 = readInt2 + 2;
            for (int readUnsignedShort3 = readUnsignedShort(readInt2); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i6 += 8;
            }
            int i7 = i6 + 2;
            int i8 = 0;
            for (int readUnsignedShort4 = readUnsignedShort(i6); readUnsignedShort4 > 0; readUnsignedShort4--) {
                String readUTF84 = readUTF8(i7, cArr);
                if ("LocalVariableTable".equals(readUTF84)) {
                    i3 = i7 + 6;
                } else if ("LocalVariableTypeTable".equals(readUTF84)) {
                    i8 = i7 + 6;
                }
                i7 += readInt(i7 + 2) + 6;
            }
            if (i3 != 0) {
                if (i8 != 0) {
                    for (int readUnsignedShort5 = readUnsignedShort(i8) * 3; readUnsignedShort5 > 0; readUnsignedShort5 -= 3) {
                    }
                }
                int i9 = i3 + 2;
                for (int readUnsignedShort6 = readUnsignedShort(i3); readUnsignedShort6 > 0; readUnsignedShort6--) {
                    visitMethod.visitLocalVariable(readUTF8(i9 + 4, cArr), readUnsignedShort(i9 + 8));
                    i9 += 10;
                }
            }
        }
        return i2;
    }

    private int readUnsignedShort(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 8);
    }

    private int readInt(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 24) | ((bArr[i + 1] & UByte.MAX_VALUE) << 16) | ((bArr[i + 2] & UByte.MAX_VALUE) << 8);
    }

    private String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = this.strings;
        String str = strArr[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.items[readUnsignedShort];
        String readUTF = readUTF(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = readUTF;
        return readUTF;
    }

    private String readUTF(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.b;
        int i5 = 0;
        char c = 0;
        char c2 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b = bArr[i];
            if (c != 0) {
                if (c == 1) {
                    cArr[i5] = (char) ((b & 63) | (c2 << 6));
                    i5++;
                    c = 0;
                } else if (c == 2) {
                    i3 = (b & 63) | (c2 << 6);
                    c2 = (char) i3;
                    c = 1;
                }
                i = i6;
            } else {
                int i7 = b & UByte.MAX_VALUE;
                if (i7 < 128) {
                    cArr[i5] = (char) i7;
                    i5++;
                } else if (i7 >= 224 || i7 <= 191) {
                    c2 = (char) (b & 15);
                    c = 2;
                } else {
                    i3 = b & 31;
                    c2 = (char) i3;
                    c = 1;
                }
                i = i6;
            }
        }
        return new String(cArr, 0, i5);
    }
}
