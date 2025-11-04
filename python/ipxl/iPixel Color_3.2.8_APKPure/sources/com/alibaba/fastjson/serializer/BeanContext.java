package com.alibaba.fastjson.serializer;

/* loaded from: classes2.dex */
public class BeanContext extends com.alibaba.fastjson2.filter.BeanContext {
    public BeanContext(com.alibaba.fastjson2.filter.BeanContext beanContext) {
        super(beanContext.getBeanClass(), beanContext.getMethod(), beanContext.getField(), beanContext.getName(), beanContext.getLabel(), beanContext.getFieldClass(), beanContext.getFieldType(), beanContext.getFeatures(), beanContext.getFormat());
    }
}
