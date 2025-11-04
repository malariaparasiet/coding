package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson2.util.BeanUtils;

/* loaded from: classes2.dex */
public interface NameFilter extends SerializeFilter, com.alibaba.fastjson2.filter.NameFilter {
    static NameFilter of(final PropertyNamingStrategy propertyNamingStrategy) {
        return new NameFilter() { // from class: com.alibaba.fastjson.serializer.NameFilter$$ExternalSyntheticLambda1
            @Override // com.alibaba.fastjson2.filter.NameFilter
            public final String process(Object obj, String str, Object obj2) {
                String fieldName;
                fieldName = BeanUtils.fieldName(str, PropertyNamingStrategy.this.name());
                return fieldName;
            }
        };
    }

    static NameFilter compose(final NameFilter nameFilter, final NameFilter nameFilter2) {
        return new NameFilter() { // from class: com.alibaba.fastjson.serializer.NameFilter$$ExternalSyntheticLambda0
            @Override // com.alibaba.fastjson2.filter.NameFilter
            public final String process(Object obj, String str, Object obj2) {
                String process;
                process = NameFilter.this.process(obj, nameFilter.process(obj, str, obj2), obj2);
                return process;
            }
        };
    }
}
