package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONPathFilter;
import com.alibaba.fastjson2.JSONPathSegment;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/* loaded from: classes2.dex */
class JSONPathTwoSegment extends JSONPath {
    final boolean extractSupport;
    final JSONPathSegment first;
    final boolean ref;
    final JSONPathSegment second;

    JSONPathTwoSegment(String str, JSONPathSegment jSONPathSegment, JSONPathSegment jSONPathSegment2, JSONPath.Feature... featureArr) {
        super(str, featureArr);
        this.first = jSONPathSegment;
        this.second = jSONPathSegment2;
        boolean z = jSONPathSegment instanceof JSONPathSegmentIndex;
        boolean z2 = true;
        this.ref = (z || (jSONPathSegment instanceof JSONPathSegmentName)) && ((jSONPathSegment2 instanceof JSONPathSegmentIndex) || (jSONPathSegment2 instanceof JSONPathSegmentName));
        if ((jSONPathSegment instanceof JSONPathSegment.EvalSegment) || ((z && ((JSONPathSegmentIndex) jSONPathSegment).index < 0) || (jSONPathSegment2 instanceof JSONPathSegment.EvalSegment) || ((jSONPathSegment2 instanceof JSONPathSegmentIndex) && ((JSONPathSegmentIndex) jSONPathSegment2).index < 0))) {
            z2 = false;
        }
        this.extractSupport = z2;
        if ((jSONPathSegment instanceof JSONPathSegment.CycleNameSegment) && ((JSONPathSegment.CycleNameSegment) jSONPathSegment).shouldRecursive() && (jSONPathSegment2 instanceof JSONPathFilter.NameFilter)) {
            ((JSONPathFilter.NameFilter) jSONPathSegment2).excludeArray();
        }
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public boolean endsWithFilter() {
        return this.second instanceof JSONPathFilter;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public JSONPath getParent() {
        return JSONPathSingle.of(this.first);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public boolean remove(Object obj) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return false;
        }
        return this.second.remove(new JSONPath.Context(this, context, this.second, null, 0L));
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public boolean contains(Object obj) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return false;
        }
        return this.second.contains(new JSONPath.Context(this, context, this.second, null, 0L));
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public boolean isRef() {
        return this.ref;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public Object eval(Object obj) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return null;
        }
        JSONPathSegment jSONPathSegment = this.first;
        if ((jSONPathSegment instanceof JSONPathSegment.CycleNameSegment) && ((JSONPathSegment.CycleNameSegment) jSONPathSegment).shouldRecursive()) {
            JSONPathSegment jSONPathSegment2 = this.second;
            if (jSONPathSegment2 instanceof JSONPathFilter.NameFilter) {
                ((JSONPathFilter.NameFilter) jSONPathSegment2).excludeArray();
            }
        }
        JSONPath.Context context2 = new JSONPath.Context(this, context, this.second, null, 0L);
        this.second.eval(context2);
        Object obj2 = context2.value;
        if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) == 0) {
            return obj2;
        }
        if (obj2 == null) {
            return new JSONArray();
        }
        return !(obj2 instanceof List) ? JSONArray.of(obj2) : obj2;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void set(Object obj, Object obj2) {
        Object jSONObject;
        FieldReader fieldReader;
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            JSONPathSegment jSONPathSegment = this.second;
            if (jSONPathSegment instanceof JSONPathSegmentIndex) {
                if (this.readerContext != null && this.readerContext.arraySupplier != null) {
                    jSONObject = this.readerContext.arraySupplier.get();
                } else {
                    jSONObject = new JSONArray();
                }
            } else {
                if (!(jSONPathSegment instanceof JSONPathSegmentName)) {
                    return;
                }
                if (this.readerContext != null && this.readerContext.objectSupplier != null) {
                    jSONObject = this.readerContext.objectSupplier.get();
                } else {
                    jSONObject = new JSONObject();
                }
            }
            context.value = jSONObject;
            if (obj instanceof Map) {
                JSONPathSegment jSONPathSegment2 = this.first;
                if (jSONPathSegment2 instanceof JSONPathSegmentName) {
                    ((Map) obj).put(((JSONPathSegmentName) jSONPathSegment2).name, jSONObject);
                }
            }
            if (obj instanceof List) {
                JSONPathSegment jSONPathSegment3 = this.first;
                if (jSONPathSegment3 instanceof JSONPathSegmentIndex) {
                    ((List) obj).set(((JSONPathSegmentIndex) jSONPathSegment3).index, jSONObject);
                }
            }
            if (obj != null) {
                Class<?> cls = obj.getClass();
                JSONReader.Context readerContext = getReaderContext();
                ObjectReader objectReader = readerContext.getObjectReader(cls);
                JSONPathSegment jSONPathSegment4 = this.first;
                if ((jSONPathSegment4 instanceof JSONPathSegmentName) && (fieldReader = objectReader.getFieldReader(((JSONPathSegmentName) jSONPathSegment4).nameHashCode)) != null) {
                    Object createInstance = fieldReader.getObjectReader(readerContext).createInstance();
                    fieldReader.accept((FieldReader) obj, createInstance);
                    context.value = createInstance;
                }
            }
        }
        this.second.set(new JSONPath.Context(this, context, this.second, null, 0L), obj2);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void set(Object obj, Object obj2, JSONReader.Feature... featureArr) {
        long j = 0;
        for (JSONReader.Feature feature : featureArr) {
            j |= feature.mask;
        }
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, j);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return;
        }
        this.second.set(new JSONPath.Context(this, context, this.second, null, j), obj2);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void setCallback(Object obj, BiFunction biFunction) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return;
        }
        this.second.setCallback(new JSONPath.Context(this, context, this.second, null, 0L), biFunction);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void setInt(Object obj, int i) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return;
        }
        this.second.setInt(new JSONPath.Context(this, context, this.second, null, 0L), i);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public void setLong(Object obj, long j) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        context.root = obj;
        this.first.eval(context);
        if (context.value == null) {
            return;
        }
        this.second.setLong(new JSONPath.Context(this, context, this.second, null, 0L), j);
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public Object extract(JSONReader jSONReader) {
        if (jSONReader == null) {
            return null;
        }
        if (!this.extractSupport) {
            return eval(jSONReader.readAny());
        }
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        this.first.accept(jSONReader, context);
        JSONPath.Context context2 = new JSONPath.Context(this, context, this.second, null, 0L);
        if (context.eval) {
            this.second.eval(context2);
        } else {
            this.second.accept(jSONReader, context2);
        }
        Object obj = context2.value;
        if ((this.features & JSONPath.Feature.AlwaysReturnList.mask) != 0) {
            if (obj == null) {
                obj = new JSONArray();
            } else if (!(obj instanceof List)) {
                obj = JSONArray.of(obj);
            }
        }
        return obj instanceof JSONPath.Sequence ? ((JSONPath.Sequence) obj).values : obj;
    }

    @Override // com.alibaba.fastjson2.JSONPath
    public String extractScalar(JSONReader jSONReader) {
        JSONPath.Context context = new JSONPath.Context(this, null, this.first, this.second, 0L);
        this.first.accept(jSONReader, context);
        JSONPath.Context context2 = new JSONPath.Context(this, context, this.second, null, 0L);
        this.second.accept(jSONReader, context2);
        return JSON.toJSONString(context2.value);
    }
}
