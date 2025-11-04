package com.alibaba.fastjson2.stream;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReaderAdapter;
import com.alibaba.fastjson2.stream.StreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
class JSONStreamReaderUTF8<T> extends JSONStreamReader<T> {
    byte[] buf;
    final Charset charset;
    final JSONReader.Context context;
    final InputStream input;

    JSONStreamReaderUTF8(InputStream inputStream, Charset charset, Type[] typeArr) {
        super(typeArr);
        this.charset = charset;
        this.input = inputStream;
        this.context = JSONFactory.createReadContext();
    }

    JSONStreamReaderUTF8(InputStream inputStream, Charset charset, ObjectReaderAdapter objectReaderAdapter) {
        super(objectReaderAdapter);
        this.charset = charset;
        this.input = inputStream;
        this.context = JSONFactory.createReadContext();
    }

    @Override // com.alibaba.fastjson2.stream.StreamReader
    protected boolean seekLine() throws IOException {
        InputStream inputStream;
        if (this.buf == null && (inputStream = this.input) != null) {
            byte[] bArr = new byte[524288];
            this.buf = bArr;
            int read = inputStream.read(bArr);
            if (read == -1) {
                this.inputEnd = true;
                return false;
            }
            this.end = read;
            if (this.end > 3) {
                byte[] bArr2 = this.buf;
                if (bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65) {
                    this.off = 3;
                    this.lineNextStart = this.off;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            this.lineTerminated = false;
            int i2 = this.off;
            while (true) {
                if (i2 >= this.end) {
                    break;
                }
                if (i2 + 4 < this.end) {
                    byte[] bArr3 = this.buf;
                    byte b = bArr3[i2];
                    byte b2 = bArr3[i2 + 1];
                    byte b3 = bArr3[i2 + 2];
                    int i3 = i2 + 3;
                    byte b4 = bArr3[i3];
                    if (b > 34 && b2 > 34 && b3 > 34 && b4 > 34) {
                        this.lineSize += 4;
                        i2 = i3;
                        i2++;
                    }
                }
                byte b5 = this.buf[i2];
                if (b5 == 10) {
                    if (this.lineSize > 0 || (this.features & StreamReader.Feature.IgnoreEmptyLine.mask) == 0) {
                        this.rowCount++;
                    }
                    this.lineTerminated = true;
                    this.lineSize = 0;
                    this.lineEnd = i2;
                    this.lineStart = this.lineNextStart;
                    int i4 = i2 + 1;
                    this.off = i4;
                    this.lineNextStart = i4;
                } else if (b5 == 13) {
                    if (this.lineSize > 0 || (this.features & StreamReader.Feature.IgnoreEmptyLine.mask) == 0) {
                        this.rowCount++;
                    }
                    this.lineTerminated = true;
                    this.lineSize = 0;
                    this.lineEnd = i2;
                    int i5 = i2 + 1;
                    if (i5 < this.end) {
                        if (this.buf[i5] == 10) {
                            i2 = i5;
                        }
                        this.lineStart = this.lineNextStart;
                        int i6 = i2 + 1;
                        this.off = i6;
                        this.lineNextStart = i6;
                    }
                } else {
                    this.lineSize++;
                    i2++;
                }
            }
            if (!this.lineTerminated) {
                if (this.input != null && (!this.inputEnd || this.off < this.end)) {
                    int i7 = this.end - this.off;
                    if (this.off > 0) {
                        if (i7 > 0) {
                            System.arraycopy(this.buf, this.off, this.buf, 0, i7);
                        }
                        this.lineNextStart = 0;
                        this.lineStart = 0;
                        this.off = 0;
                        this.end = i7;
                    }
                    int read2 = this.input.read(this.buf, this.end, this.buf.length - this.end);
                    if (read2 == -1) {
                        this.inputEnd = true;
                        if (this.off == this.end) {
                            return false;
                        }
                    } else {
                        this.end += read2;
                    }
                }
                this.lineStart = this.lineNextStart;
                this.lineEnd = this.end;
                this.rowCount++;
                this.lineSize = 0;
                this.off = this.end;
            }
            this.lineTerminated = this.off == this.end;
            return true;
        }
        return true;
    }

    @Override // com.alibaba.fastjson2.stream.StreamReader
    public <T> T readLineObject() {
        try {
            if (this.inputEnd && this.off >= this.end) {
                return null;
            }
            if (this.input == null && this.off >= this.end) {
                return null;
            }
            if (!seekLine()) {
                return null;
            }
            JSONReader of = JSONReader.of(this.buf, this.lineStart, this.lineEnd - this.lineStart, this.charset, this.context);
            if (this.objectReader != null) {
                return this.objectReader.readObject(of, null, null, this.features);
            }
            if (of.isArray() && this.types != null && this.types.length != 0) {
                return (T) of.readList(this.types);
            }
            return (T) of.readAny();
        } catch (IOException e) {
            throw new JSONException("seekLine error", e);
        }
    }
}
