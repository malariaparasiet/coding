package com.alibaba.fastjson2.stream;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReaderAdapter;
import com.alibaba.fastjson2.stream.StreamReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class JSONStreamReaderUTF16<T> extends JSONStreamReader<T> {
    char[] buf;
    final JSONReader.Context context;
    final Reader input;

    JSONStreamReaderUTF16(Reader reader, ObjectReaderAdapter objectReaderAdapter) {
        super(objectReaderAdapter);
        this.input = reader;
        this.context = JSONFactory.createReadContext();
    }

    JSONStreamReaderUTF16(Reader reader, Type[] typeArr) {
        super(typeArr);
        this.input = reader;
        this.context = JSONFactory.createReadContext();
    }

    @Override // com.alibaba.fastjson2.stream.StreamReader
    protected boolean seekLine() throws IOException {
        Reader reader;
        if (this.buf == null && (reader = this.input) != null) {
            char[] cArr = new char[524288];
            this.buf = cArr;
            int read = reader.read(cArr);
            if (read == -1) {
                this.inputEnd = true;
                return false;
            }
            this.end = read;
        }
        for (int i = 0; i < 3; i++) {
            this.lineTerminated = false;
            int i2 = this.off;
            while (true) {
                if (i2 >= this.end) {
                    break;
                }
                if (i2 + 4 < this.end) {
                    char[] cArr2 = this.buf;
                    char c = cArr2[i2];
                    char c2 = cArr2[i2 + 1];
                    char c3 = cArr2[i2 + 2];
                    int i3 = i2 + 3;
                    char c4 = cArr2[i3];
                    if (c > '\"' && c2 > '\"' && c3 > '\"' && c4 > '\"') {
                        this.lineSize += 4;
                        i2 = i3;
                        i2++;
                    }
                }
                char c5 = this.buf[i2];
                if (c5 == '\n') {
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
                } else if (c5 == '\r') {
                    if (this.lineSize > 0 || (this.features & StreamReader.Feature.IgnoreEmptyLine.mask) == 0) {
                        this.rowCount++;
                    }
                    this.lineTerminated = true;
                    this.lineSize = 0;
                    this.lineEnd = i2;
                    int i5 = i2 + 1;
                    if (i5 < this.end) {
                        if (this.buf[i5] == '\n') {
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
                if (this.input != null && !this.inputEnd) {
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
            if (this.inputEnd) {
                return null;
            }
            if (this.input == null && this.off >= this.end) {
                return null;
            }
            if (!seekLine()) {
                return null;
            }
            JSONReader of = JSONReader.of(this.buf, this.lineStart, this.lineEnd - this.lineStart, this.context);
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
