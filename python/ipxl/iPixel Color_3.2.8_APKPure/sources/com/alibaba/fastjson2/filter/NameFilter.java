package com.alibaba.fastjson2.filter;

import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.util.BeanUtils;
import java.util.function.Function;

/* loaded from: classes2.dex */
public interface NameFilter extends Filter {
    String process(Object obj, String str, Object obj2);

    static NameFilter of(final PropertyNamingStrategy propertyNamingStrategy) {
        return new NameFilter() { // from class: com.alibaba.fastjson2.filter.NameFilter$$ExternalSyntheticLambda1
            @Override // com.alibaba.fastjson2.filter.NameFilter
            public final String process(Object obj, String str, Object obj2) {
                String fieldName;
                fieldName = BeanUtils.fieldName(str, PropertyNamingStrategy.this.name());
                return fieldName;
            }
        };
    }

    static NameFilter compose(final NameFilter nameFilter, final NameFilter nameFilter2) {
        return new NameFilter() { // from class: com.alibaba.fastjson2.filter.NameFilter$$ExternalSyntheticLambda0
            @Override // com.alibaba.fastjson2.filter.NameFilter
            public final String process(Object obj, String str, Object obj2) {
                String process;
                process = NameFilter.this.process(obj, nameFilter.process(obj, str, obj2), obj2);
                return process;
            }
        };
    }

    static /* synthetic */ String lambda$of$2(Function function, Object obj, String str, Object obj2) {
        return (String) function.apply(str);
    }

    static NameFilter of(final Function<String, String> function) {
        return new NameFilter() { // from class: com.alibaba.fastjson2.filter.NameFilter$$ExternalSyntheticLambda2
            @Override // com.alibaba.fastjson2.filter.NameFilter
            public final String process(Object obj, String str, Object obj2) {
                return NameFilter.lambda$of$2(function, obj, str, obj2);
            }
        };
    }
}
