package com.alibaba.fastjson2.reader;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public final class ObjectReaderImplShort extends ObjectReaderPrimitive {
    static final ObjectReaderImplShort INSTANCE = new ObjectReaderImplShort();
    public static final long HASH_TYPE = Fnv.hashCode64(ExifInterface.LATITUDE_SOUTH);

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Object createInstance(long j) {
        return super.createInstance(j);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public /* bridge */ /* synthetic */ Class getObjectClass() {
        return super.getObjectClass();
    }

    public ObjectReaderImplShort() {
        super(Short.class);
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReaderPrimitive, com.alibaba.fastjson2.reader.ObjectReader
    public Object readJSONBObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Integer readInt32 = jSONReader.readInt32();
        if (readInt32 == null) {
            return null;
        }
        return Short.valueOf(readInt32.shortValue());
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        Integer readInt32 = jSONReader.readInt32();
        if (readInt32 == null) {
            return null;
        }
        return Short.valueOf(readInt32.shortValue());
    }
}
