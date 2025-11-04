package com.alibaba.fastjson.serializer;

/* loaded from: classes2.dex */
public interface ValueFilter extends SerializeFilter, com.alibaba.fastjson2.filter.ValueFilter {
    Object process(Object obj, String str, Object obj2);

    @Override // com.alibaba.fastjson2.filter.ValueFilter
    default Object apply(Object obj, String str, Object obj2) {
        return process(obj, str, obj2);
    }

    static ValueFilter compose(final ValueFilter valueFilter, final ValueFilter valueFilter2) {
        return new ValueFilter() { // from class: com.alibaba.fastjson.serializer.ValueFilter$$ExternalSyntheticLambda0
            @Override // com.alibaba.fastjson.serializer.ValueFilter
            public final Object process(Object obj, String str, Object obj2) {
                Object process;
                process = ValueFilter.this.process(obj, str, valueFilter.process(obj, str, obj2));
                return process;
            }
        };
    }
}
