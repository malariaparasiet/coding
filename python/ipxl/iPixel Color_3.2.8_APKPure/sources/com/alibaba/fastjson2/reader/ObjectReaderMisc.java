package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* loaded from: classes2.dex */
public class ObjectReaderMisc implements ObjectReader {
    static final long HASH_ADDRESS = Fnv.hashCode64("address");
    static final long HASH_PORT = Fnv.hashCode64("port");
    private final Class objectClass;

    public ObjectReaderMisc(Class cls) {
        this.objectClass = cls;
    }

    @Override // com.alibaba.fastjson2.reader.ObjectReader
    public Object readObject(JSONReader jSONReader, Type type, Object obj, long j) {
        InetAddress inetAddress = null;
        if (jSONReader.nextIfNull()) {
            return null;
        }
        if (this.objectClass == InetSocketAddress.class) {
            jSONReader.nextIfObjectStart();
            int i = 0;
            while (!jSONReader.nextIfObjectEnd()) {
                long readFieldNameHashCode = jSONReader.readFieldNameHashCode();
                if (readFieldNameHashCode == HASH_ADDRESS) {
                    inetAddress = (InetAddress) jSONReader.read(InetAddress.class);
                } else if (readFieldNameHashCode == HASH_PORT) {
                    i = jSONReader.readInt32().intValue();
                } else {
                    jSONReader.skipValue();
                }
            }
            jSONReader.nextIfComma();
            return new InetSocketAddress(inetAddress, i);
        }
        throw new JSONException(jSONReader.info("not support : " + this.objectClass.getName()));
    }
}
