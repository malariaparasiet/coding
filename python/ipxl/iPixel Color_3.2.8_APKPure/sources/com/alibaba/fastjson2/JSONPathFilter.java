package com.alibaba.fastjson2;

import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONPathSegment;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterAdapter;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* loaded from: classes2.dex */
abstract class JSONPathFilter extends JSONPathSegment implements JSONPathSegment.EvalSegment {
    private boolean and = true;

    abstract boolean apply(JSONPath.Context context, Object obj);

    JSONPathFilter() {
    }

    public boolean isAnd() {
        return this.and;
    }

    public JSONPathFilter setAnd(boolean z) {
        this.and = z;
        return this;
    }

    /* renamed from: com.alibaba.fastjson2.JSONPathFilter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator;

        static {
            int[] iArr = new int[Operator.values().length];
            $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator = iArr;
            try {
                iArr[Operator.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.NE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.GT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.GE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.LT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.LE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.LIKE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.NOT_LIKE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.RLIKE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.NOT_RLIKE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.BETWEEN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.NOT_BETWEEN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.AND.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.OR.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.STARTS_WITH.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.ENDS_WITH.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.CONTAINS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[Operator.NOT_CONTAINS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN,
        AND,
        OR,
        REG_MATCH,
        STARTS_WITH,
        ENDS_WITH,
        CONTAINS,
        NOT_CONTAINS;

        @Override // java.lang.Enum
        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[ordinal()]) {
                case 1:
                    return "==";
                case 2:
                    return "!=";
                case 3:
                    return SimpleComparison.GREATER_THAN_OPERATION;
                case 4:
                    return SimpleComparison.GREATER_THAN_EQUAL_TO_OPERATION;
                case 5:
                    return SimpleComparison.LESS_THAN_OPERATION;
                case 6:
                    return SimpleComparison.LESS_THAN_EQUAL_TO_OPERATION;
                case 7:
                    return "like";
                case 8:
                    return "not like";
                case 9:
                    return "rlike";
                case 10:
                    return "not rlike";
                case 11:
                    return "between";
                case 12:
                    return "not between";
                case 13:
                    return "and";
                case 14:
                    return "or";
                case 15:
                    return "starts with";
                case 16:
                    return "ends with";
                case 17:
                    return "contains";
                case 18:
                    return "not contains";
                default:
                    return name();
            }
        }
    }

    static final class NameIsNull extends NameFilter {
        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return true;
        }

        public NameIsNull(String str, long j, String[] strArr, long[] jArr, Function function) {
            super(str, j, strArr, jArr, function);
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            if (this.function != null) {
                obj = this.function.apply(obj);
            }
            return obj == null;
        }
    }

    static final class NameIntOpSegment extends NameFilter {
        final Operator operator;
        final long value;

        public NameIntOpSegment(String str, long j, String[] strArr, long[] jArr, Function function, Operator operator, long j2) {
            super(str, j, strArr, jArr, function);
            this.operator = operator;
            this.value = j2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.operator == Operator.NE;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            long longValue;
            int compareTo;
            boolean z = obj instanceof Boolean;
            if (z || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                if (z) {
                    longValue = ((Boolean) obj).booleanValue() ? 1L : 0L;
                } else {
                    longValue = ((Number) obj).longValue();
                }
                switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                    case 1:
                        return longValue == this.value;
                    case 2:
                        return longValue != this.value;
                    case 3:
                        return longValue > this.value;
                    case 4:
                        return longValue >= this.value;
                    case 5:
                        return longValue < this.value;
                    case 6:
                        return longValue <= this.value;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
            if (obj instanceof BigDecimal) {
                compareTo = ((BigDecimal) obj).compareTo(BigDecimal.valueOf(this.value));
            } else if (obj instanceof BigInteger) {
                compareTo = ((BigInteger) obj).compareTo(BigInteger.valueOf(this.value));
            } else if (obj instanceof Float) {
                compareTo = ((Float) obj).compareTo(Float.valueOf(this.value));
            } else if (obj instanceof Double) {
                compareTo = ((Double) obj).compareTo(Double.valueOf(this.value));
            } else if (obj instanceof String) {
                String str = (String) obj;
                if (IOUtils.isNumber(str)) {
                    try {
                        compareTo = Long.compare(Long.parseLong(str), this.value);
                    } catch (Exception unused) {
                        compareTo = str.compareTo(Long.toString(this.value));
                    }
                } else {
                    compareTo = str.compareTo(Long.toString(this.value));
                }
            } else {
                throw new UnsupportedOperationException();
            }
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compareTo == 0;
                case 2:
                    return compareTo != 0;
                case 3:
                    return compareTo > 0;
                case 4:
                    return compareTo >= 0;
                case 5:
                    return compareTo < 0;
                case 6:
                    return compareTo <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void set(JSONPath.Context context, Object obj) {
            Object obj2;
            if (context.parent == null) {
                obj2 = context.root;
            } else {
                obj2 = context.parent.value;
            }
            if (obj2 instanceof List) {
                List list = (List) obj2;
                for (int i = 0; i < list.size(); i++) {
                    if (apply(context, list.get(i))) {
                        list.set(i, obj);
                    }
                }
                return;
            }
            throw new JSONException("UnsupportedOperation ");
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void setCallback(JSONPath.Context context, BiFunction biFunction) {
            Object obj;
            Object apply;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                for (int i = 0; i < list.size(); i++) {
                    Object obj2 = list.get(i);
                    if (apply(context, obj2) && (apply = biFunction.apply(list, obj2)) != obj2) {
                        list.set(i, apply);
                    }
                }
                return;
            }
            throw new JSONException("UnsupportedOperation ");
        }

        public String toString() {
            return "[?(" + (this.fieldName2 == null ? "@" : this.fieldName2) + '.' + this.fieldName + ' ' + this.operator + ' ' + this.value + ")]";
        }
    }

    static final class NameDecimalOpSegment extends NameFilter {
        final Operator operator;
        final BigDecimal value;

        public NameDecimalOpSegment(String str, long j, Operator operator, BigDecimal bigDecimal) {
            super(str, j);
            this.operator = operator;
            this.value = bigDecimal;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.operator == Operator.NE;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            BigDecimal valueOf;
            if (obj == null) {
                return false;
            }
            if (obj instanceof Boolean) {
                valueOf = ((Boolean) obj).booleanValue() ? BigDecimal.ONE : BigDecimal.ZERO;
            } else if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                valueOf = BigDecimal.valueOf(((Number) obj).longValue());
            } else if (obj instanceof BigDecimal) {
                valueOf = (BigDecimal) obj;
            } else if (obj instanceof BigInteger) {
                valueOf = new BigDecimal((BigInteger) obj);
            } else {
                throw new UnsupportedOperationException();
            }
            int compareTo = valueOf.compareTo(this.value);
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compareTo == 0;
                case 2:
                    return compareTo != 0;
                case 3:
                    return compareTo > 0;
                case 4:
                    return compareTo >= 0;
                case 5:
                    return compareTo < 0;
                case 6:
                    return compareTo <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    static final class NameDoubleOpSegment extends NameFilter {
        final Operator operator;
        final double value;

        public NameDoubleOpSegment(String str, long j, Operator operator, Double d) {
            super(str, j);
            this.operator = operator;
            this.value = d.doubleValue();
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.operator == Operator.NE;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            Double valueOf;
            if (obj == null) {
                return false;
            }
            if (obj instanceof Boolean) {
                valueOf = Double.valueOf(((Boolean) obj).booleanValue() ? 1.0d : AudioStats.AUDIO_AMPLITUDE_NONE);
            } else if (obj instanceof Number) {
                valueOf = Double.valueOf(((Number) obj).doubleValue());
            } else {
                throw new UnsupportedOperationException();
            }
            int compareTo = valueOf.compareTo(Double.valueOf(this.value));
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compareTo == 0;
                case 2:
                    return compareTo != 0;
                case 3:
                    return compareTo > 0;
                case 4:
                    return compareTo >= 0;
                case 5:
                    return compareTo < 0;
                case 6:
                    return compareTo <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    static final class NameRLikeSegment extends NameFilter {
        final boolean not;
        final Pattern pattern;

        public NameRLikeSegment(String str, long j, Pattern pattern, boolean z) {
            super(str, j);
            this.pattern = pattern;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            boolean matches = this.pattern.matcher(obj.toString()).matches();
            return this.not ? !matches : matches;
        }
    }

    static final class StartsWithSegment extends NameFilter {
        final String prefix;

        public StartsWithSegment(String str, long j, String str2) {
            super(str, j);
            this.prefix = str2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            String obj2 = obj.toString();
            return obj2 != null && obj2.startsWith(this.prefix);
        }
    }

    static final class EndsWithSegment extends NameFilter {
        final String prefix;

        public EndsWithSegment(String str, long j, String str2) {
            super(str, j);
            this.prefix = str2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            String obj2 = obj.toString();
            return obj2 != null && obj2.endsWith(this.prefix);
        }
    }

    static final class GroupFilter extends JSONPathSegment implements JSONPathSegment.EvalSegment {
        final List<JSONPathFilter> filters;

        public GroupFilter(List<JSONPathFilter> list) {
            this.filters = list;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            if (context.parent == null) {
                context.root = jSONReader.readAny();
            }
            eval(context);
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            List arrayList = new ArrayList();
            List<JSONPathFilter> list = this.filters;
            if (list != null) {
                arrayList = (List) list.stream().sorted(Comparator.comparing(new Function() { // from class: com.alibaba.fastjson2.JSONPathFilter$GroupFilter$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        return Boolean.valueOf(((JSONPathFilter) obj2).isAnd());
                    }
                })).collect(Collectors.toList());
            }
            boolean z = false;
            if (obj instanceof List) {
                List list2 = (List) obj;
                JSONArray jSONArray = new JSONArray(list2.size());
                for (int i = 0; i < list2.size(); i++) {
                    Object obj2 = list2.get(i);
                    Iterator it = arrayList.iterator();
                    boolean z2 = false;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        JSONPathFilter jSONPathFilter = (JSONPathFilter) it.next();
                        boolean isAnd = jSONPathFilter.isAnd();
                        boolean apply = jSONPathFilter.apply(context, obj2);
                        if (isAnd) {
                            if (!apply) {
                                z2 = false;
                                break;
                            }
                            z2 = isAnd;
                        } else {
                            if (apply) {
                                z2 = true;
                                break;
                            }
                            z2 = isAnd;
                        }
                    }
                    if (z2) {
                        jSONArray.add(obj2);
                    }
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            Iterator it2 = arrayList.iterator();
            boolean z3 = false;
            while (true) {
                if (!it2.hasNext()) {
                    z = z3;
                    break;
                }
                JSONPathFilter jSONPathFilter2 = (JSONPathFilter) it2.next();
                boolean isAnd2 = jSONPathFilter2.isAnd();
                boolean apply2 = jSONPathFilter2.apply(context, obj);
                if (!isAnd2) {
                    if (apply2) {
                        z = true;
                        break;
                    }
                    z3 = isAnd2;
                } else if (!apply2) {
                    break;
                } else {
                    z3 = isAnd2;
                }
            }
            if (z) {
                context.value = obj;
            }
            context.eval = true;
        }
    }

    static abstract class NameFilter extends JSONPathFilter {
        final String fieldName;
        final String[] fieldName2;
        final long fieldNameNameHash;
        final long[] fieldNameNameHash2;
        final Function function;
        boolean includeArray;

        abstract boolean apply(Object obj);

        protected boolean applyNull() {
            return false;
        }

        public NameFilter(String str, long j) {
            this.includeArray = true;
            this.fieldName = str;
            this.fieldNameNameHash = j;
            this.fieldName2 = null;
            this.fieldNameNameHash2 = null;
            this.function = null;
        }

        public NameFilter(String str, long j, String[] strArr, long[] jArr, Function function) {
            this.includeArray = true;
            this.fieldName = str;
            this.fieldNameNameHash = j;
            this.fieldName2 = strArr;
            this.fieldNameNameHash2 = jArr;
            this.function = function;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public final void accept(JSONReader jSONReader, JSONPath.Context context) {
            if (context.parent == null) {
                context.root = jSONReader.readAny();
            }
            eval(context);
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public boolean remove(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (apply(context, list.get(size))) {
                        list.remove(size);
                    }
                }
                return true;
            }
            throw new JSONException("UnsupportedOperation " + getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public final void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            int i = 0;
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray = new JSONArray(list.size());
                while (i < list.size()) {
                    Object obj2 = list.get(i);
                    if (apply(context, obj2)) {
                        jSONArray.add(obj2);
                    }
                    i++;
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                JSONArray jSONArray2 = new JSONArray(objArr.length);
                int length = objArr.length;
                while (i < length) {
                    Object obj3 = objArr[i];
                    if (apply(context, obj3)) {
                        jSONArray2.add(obj3);
                    }
                    i++;
                }
                context.value = jSONArray2;
                context.eval = true;
                return;
            }
            if (obj instanceof JSONPath.Sequence) {
                JSONArray jSONArray3 = new JSONArray();
                for (Object obj4 : ((JSONPath.Sequence) obj).values) {
                    if (this.includeArray && (obj4 instanceof Collection)) {
                        for (Object obj5 : (Collection) obj4) {
                            if (apply(context, obj5)) {
                                jSONArray3.add(obj5);
                            }
                        }
                    } else if (apply(context, obj4)) {
                        jSONArray3.add(obj4);
                    }
                }
                context.value = jSONArray3;
                context.eval = true;
                return;
            }
            if (apply(context, obj)) {
                context.value = obj;
                context.eval = true;
            }
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        public boolean apply(JSONPath.Context context, Object obj) {
            FieldWriter fieldWriter;
            FieldWriter fieldWriter2;
            if (obj == null) {
                return false;
            }
            JSONWriter.Context writerContext = context.path.getWriterContext();
            if (obj instanceof Map) {
                String str = this.fieldName;
                if (str != null) {
                    obj = ((Map) obj).get(str);
                }
                if (obj == null) {
                    return applyNull();
                }
                if (this.fieldName2 != null) {
                    int i = 0;
                    while (true) {
                        String[] strArr = this.fieldName2;
                        if (i >= strArr.length) {
                            break;
                        }
                        String str2 = strArr[i];
                        if (obj instanceof Map) {
                            obj = ((Map) obj).get(str2);
                        } else {
                            ObjectWriter objectWriter = writerContext.getObjectWriter(obj.getClass());
                            if (!(objectWriter instanceof ObjectWriterAdapter) || (fieldWriter2 = objectWriter.getFieldWriter(this.fieldNameNameHash2[i])) == null) {
                                return false;
                            }
                            obj = fieldWriter2.getFieldValue(obj);
                        }
                        if (obj == null) {
                            return this instanceof NameIsNull;
                        }
                        i++;
                    }
                }
                Function function = this.function;
                if (function != null) {
                    obj = function.apply(obj);
                }
                return apply(obj);
            }
            ObjectWriter objectWriter2 = writerContext.getObjectWriter(obj.getClass());
            if (objectWriter2 instanceof ObjectWriterAdapter) {
                Object fieldValue = objectWriter2.getFieldWriter(this.fieldNameNameHash).getFieldValue(obj);
                if (fieldValue == null) {
                    return false;
                }
                if (this.fieldName2 != null) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr2 = this.fieldName2;
                        if (i2 >= strArr2.length) {
                            break;
                        }
                        String str3 = strArr2[i2];
                        if (fieldValue instanceof Map) {
                            fieldValue = ((Map) fieldValue).get(str3);
                        } else {
                            ObjectWriter objectWriter3 = writerContext.getObjectWriter(fieldValue.getClass());
                            if (!(objectWriter3 instanceof ObjectWriterAdapter) || (fieldWriter = objectWriter3.getFieldWriter(this.fieldNameNameHash2[i2])) == null) {
                                return false;
                            }
                            fieldValue = fieldWriter.getFieldValue(fieldValue);
                        }
                        if (fieldValue == null) {
                            return false;
                        }
                        i2++;
                    }
                }
                Function function2 = this.function;
                if (function2 != null) {
                    fieldValue = function2.apply(fieldValue);
                }
                return apply(fieldValue);
            }
            Function function3 = this.function;
            if (function3 != null) {
                return apply(function3.apply(obj));
            }
            if (this.fieldName == null) {
                return apply(obj);
            }
            return false;
        }

        protected void excludeArray() {
            this.includeArray = false;
        }
    }

    static final class NameStringOpSegment extends NameFilter {
        final Operator operator;
        final String value;

        public NameStringOpSegment(String str, long j, String[] strArr, long[] jArr, Function function, Operator operator, String str2) {
            super(str, j, strArr, jArr, function);
            this.operator = operator;
            this.value = str2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.operator == Operator.NE;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            if (!(obj instanceof String)) {
                return false;
            }
            String str = (String) obj;
            if (this.operator == Operator.STARTS_WITH) {
                return str.startsWith(this.value);
            }
            if (this.operator == Operator.ENDS_WITH) {
                return str.endsWith(this.value);
            }
            int compareTo = str.compareTo(this.value);
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compareTo == 0;
                case 2:
                    return compareTo != 0;
                case 3:
                    return compareTo > 0;
                case 4:
                    return compareTo >= 0;
                case 5:
                    return compareTo < 0;
                case 6:
                    return compareTo <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    static final class NameArrayOpSegment extends NameFilter {
        final JSONArray array;
        final Operator operator;

        public NameArrayOpSegment(String str, long j, String[] strArr, long[] jArr, Function function, Operator operator, JSONArray jSONArray) {
            super(str, j, strArr, jArr, function);
            this.operator = operator;
            this.array = jSONArray;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            if (Objects.requireNonNull(this.operator) == Operator.EQ) {
                return this.array.equals(obj);
            }
            throw new JSONException("not support operator : " + this.operator);
        }
    }

    static final class NameObjectOpSegment extends NameFilter {
        final JSONObject object;
        final Operator operator;

        public NameObjectOpSegment(String str, long j, String[] strArr, long[] jArr, Function function, Operator operator, JSONObject jSONObject) {
            super(str, j, strArr, jArr, function);
            this.operator = operator;
            this.object = jSONObject;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            if (Objects.requireNonNull(this.operator) == Operator.EQ) {
                return this.object.equals(obj);
            }
            throw new JSONException("not support operator : " + this.operator);
        }
    }

    static final class NameStringInSegment extends NameFilter {
        private final boolean not;
        private final String[] values;

        public NameStringInSegment(String str, long j, String[] strArr, boolean z) {
            super(str, j);
            this.values = strArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.not;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            boolean z;
            for (String str : this.values) {
                if (str == obj) {
                    z = this.not;
                } else if (str != null && str.equals(obj)) {
                    z = this.not;
                }
                return !z;
            }
            return this.not;
        }
    }

    static final class NameStringContainsSegment extends NameFilter {
        private final boolean not;
        private final String[] values;

        public NameStringContainsSegment(String str, long j, String[] strArr, long[] jArr, String[] strArr2, boolean z) {
            super(str, j, strArr, jArr, null);
            this.values = strArr2;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                for (String str : this.values) {
                    if (collection.contains(str)) {
                    }
                }
                return !this.not;
            }
            return this.not;
        }
    }

    static final class NameMatchFilter extends NameFilter {
        final String[] containsValues;
        final String endsWithValue;
        final int minLength;
        final boolean not;
        final String startsWithValue;

        public NameMatchFilter(String str, long j, String str2, String str3, String[] strArr, boolean z) {
            super(str, j);
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z;
            int length = str2 != null ? str2.length() : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.minLength = length;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            int i;
            if (!(obj instanceof String)) {
                return false;
            }
            String str = (String) obj;
            if (str.length() < this.minLength) {
                return this.not;
            }
            String str2 = this.startsWithValue;
            if (str2 == null) {
                i = 0;
            } else {
                if (!str.startsWith(str2)) {
                    return this.not;
                }
                i = this.startsWithValue.length();
            }
            String[] strArr = this.containsValues;
            if (strArr != null) {
                for (String str3 : strArr) {
                    int indexOf = str.indexOf(str3, i);
                    if (indexOf == -1) {
                        return this.not;
                    }
                    i = indexOf + str3.length();
                }
            }
            String str4 = this.endsWithValue;
            if (str4 != null && !str.endsWith(str4)) {
                return this.not;
            }
            return !this.not;
        }
    }

    static final class NameExistsFilter extends JSONPathFilter {
        final String name;
        final long nameHashCode;

        public NameExistsFilter(String str, long j) {
            this.name = str;
            this.nameHashCode = j;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            if (obj instanceof List) {
                List list = (List) obj;
                while (i < list.size()) {
                    Object obj2 = list.get(i);
                    if ((obj2 instanceof Map) && ((Map) obj2).containsKey(this.name)) {
                        jSONArray.add(obj2);
                    }
                    i++;
                }
                context.value = jSONArray;
                return;
            }
            if (obj instanceof Map) {
                if (((Map) obj).get(this.name) == null) {
                    obj = null;
                }
                context.value = obj;
            } else {
                if (obj instanceof JSONPath.Sequence) {
                    List list2 = ((JSONPath.Sequence) obj).values;
                    while (i < list2.size()) {
                        Object obj3 = list2.get(i);
                        if ((obj3 instanceof Map) && ((Map) obj3).containsKey(this.name)) {
                            jSONArray.add(obj3);
                        }
                        i++;
                    }
                    if (context.next != null) {
                        context.value = new JSONPath.Sequence(jSONArray);
                        return;
                    } else {
                        context.value = jSONArray;
                        return;
                    }
                }
                throw new UnsupportedOperationException();
            }
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            eval(context);
        }

        public String toString() {
            return "?" + this.name;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        public boolean apply(JSONPath.Context context, Object obj) {
            if (obj instanceof Map) {
                return ((Map) obj).containsKey(this.name);
            }
            throw new UnsupportedOperationException();
        }
    }

    static final class NamesExistsFilter extends JSONPathFilter {
        final long[] nameHashCodes;
        final String[] names;

        public NamesExistsFilter(List<String> list) {
            String[] strArr = (String[]) list.toArray(new String[0]);
            this.names = strArr;
            int length = strArr.length;
            long[] jArr = new long[length];
            for (int i = 0; i < length; i++) {
                jArr[i] = Fnv.hashCode64(this.names[i]);
            }
            this.nameHashCodes = jArr;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            int i = 0;
            Object obj2 = obj;
            while (true) {
                String[] strArr = this.names;
                if (i >= strArr.length) {
                    return;
                }
                String str = strArr[i];
                if (obj2 instanceof Map) {
                    obj2 = ((Map) obj2).get(str);
                    if (i == this.names.length - 1 || obj2 == null) {
                        break;
                    }
                }
                i++;
            }
            if (obj2 == null) {
                obj = null;
            }
            context.value = obj;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            eval(context);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("exists(@");
            for (int i = 0; i < this.names.length; i++) {
                sb.append('.');
                sb.append(this.names[i]);
            }
            sb.append(')');
            return sb.toString();
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        public boolean apply(JSONPath.Context context, Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    static final class NameIntBetweenSegment extends NameFilter {
        private final long begin;
        private final long end;
        private final boolean not;

        public NameIntBetweenSegment(String str, long j, long j2, long j3, boolean z) {
            super(str, j);
            this.begin = j2;
            this.end = j3;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.not;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            boolean z;
            if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                long longValue = ((Number) obj).longValue();
                if (longValue >= this.begin && longValue <= this.end) {
                    z = this.not;
                } else {
                    return this.not;
                }
            } else if ((obj instanceof Float) || (obj instanceof Double)) {
                double doubleValue = ((Number) obj).doubleValue();
                if (doubleValue >= this.begin && doubleValue <= this.end) {
                    z = this.not;
                } else {
                    return this.not;
                }
            } else if (obj instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) obj;
                int compareTo = bigDecimal.compareTo(BigDecimal.valueOf(this.begin));
                int compareTo2 = bigDecimal.compareTo(BigDecimal.valueOf(this.end));
                if (compareTo >= 0 && compareTo2 <= 0) {
                    z = this.not;
                } else {
                    return this.not;
                }
            } else if (obj instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) obj;
                int compareTo3 = bigInteger.compareTo(BigInteger.valueOf(this.begin));
                int compareTo4 = bigInteger.compareTo(BigInteger.valueOf(this.end));
                if (compareTo3 >= 0 && compareTo4 <= 0) {
                    z = this.not;
                } else {
                    return this.not;
                }
            } else {
                return this.not;
            }
            return !z;
        }
    }

    static final class NameLongContainsSegment extends NameFilter {
        private final boolean not;
        private final long[] values;

        public NameLongContainsSegment(String str, long j, String[] strArr, long[] jArr, long[] jArr2, boolean z) {
            super(str, j, strArr, jArr, null);
            this.values = jArr2;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                for (long j : this.values) {
                    for (Object obj2 : collection) {
                        if (((!(obj2 instanceof Byte) && !(obj2 instanceof Short) && !(obj2 instanceof Integer) && !(obj2 instanceof Long)) || ((Number) obj2).longValue() != j) && ((!(obj2 instanceof Float) || j != ((Float) obj2).floatValue()) && (!(obj2 instanceof Double) || j != ((Double) obj2).doubleValue()))) {
                            if (obj2 instanceof BigDecimal) {
                                BigDecimal bigDecimal = (BigDecimal) obj2;
                                if (j == bigDecimal.longValue() && bigDecimal.compareTo(BigDecimal.valueOf(j)) == 0) {
                                    break;
                                }
                            }
                            if (obj2 instanceof BigInteger) {
                                BigInteger bigInteger = (BigInteger) obj2;
                                if (j == bigInteger.longValue() && bigInteger.equals(BigInteger.valueOf(j))) {
                                    break;
                                }
                            }
                        }
                    }
                }
                return !this.not;
            }
            return this.not;
        }
    }

    static final class NameIntInSegment extends NameFilter {
        private final boolean not;
        private final long[] values;

        public NameIntInSegment(String str, long j, String[] strArr, long[] jArr, Function function, long[] jArr2, boolean z) {
            super(str, j, strArr, jArr, function);
            this.values = jArr2;
            this.not = z;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        protected boolean applyNull() {
            return this.not;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        public boolean apply(Object obj) {
            boolean z;
            int i = 0;
            if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                long longValue = ((Number) obj).longValue();
                long[] jArr = this.values;
                int length = jArr.length;
                while (i < length) {
                    if (jArr[i] == longValue) {
                        z = this.not;
                    } else {
                        i++;
                    }
                }
                return this.not;
            }
            if ((obj instanceof Float) || (obj instanceof Double)) {
                double doubleValue = ((Number) obj).doubleValue();
                int length2 = this.values.length;
                while (i < length2) {
                    if (r9[i] == doubleValue) {
                        z = this.not;
                    } else {
                        i++;
                    }
                }
                return this.not;
            }
            if (obj instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) obj;
                long longValue2 = bigDecimal.longValue();
                long[] jArr2 = this.values;
                int length3 = jArr2.length;
                while (i < length3) {
                    long j = jArr2[i];
                    if (j == longValue2 && bigDecimal.compareTo(BigDecimal.valueOf(j)) == 0) {
                        z = this.not;
                    } else {
                        i++;
                    }
                }
                return this.not;
            }
            if (obj instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) obj;
                long longValue3 = bigInteger.longValue();
                long[] jArr3 = this.values;
                int length4 = jArr3.length;
                while (i < length4) {
                    long j2 = jArr3[i];
                    if (j2 == longValue3 && bigInteger.equals(BigInteger.valueOf(j2))) {
                        z = this.not;
                    } else {
                        i++;
                    }
                }
                return this.not;
            }
            return this.not;
            return !z;
        }
    }

    static final class NameName extends NameFilter {
        final String fieldName1;
        final long fieldNameName1Hash;

        public NameName(String str, long j, String str2, long j2) {
            super(str, j);
            this.fieldName1 = str2;
            this.fieldNameName1Hash = j2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter, com.alibaba.fastjson2.JSONPathFilter
        public boolean apply(JSONPath.Context context, Object obj) {
            FieldWriter fieldWriter;
            Object fieldValue;
            Object obj2;
            if (obj == null) {
                return false;
            }
            JSONWriter.Context writerContext = context.path.getWriterContext();
            if (obj instanceof Map) {
                Map map = (Map) obj;
                obj2 = map.get(this.fieldName);
                fieldValue = map.get(this.fieldName1);
            } else {
                ObjectWriter objectWriter = writerContext.getObjectWriter(obj.getClass());
                if (!(objectWriter instanceof ObjectWriterAdapter) || (fieldWriter = objectWriter.getFieldWriter(this.fieldNameNameHash)) == null) {
                    return false;
                }
                Object fieldValue2 = fieldWriter.getFieldValue(obj);
                FieldWriter fieldWriter2 = objectWriter.getFieldWriter(this.fieldNameNameHash);
                if (fieldWriter2 == null) {
                    return false;
                }
                fieldValue = fieldWriter2.getFieldValue(obj);
                obj2 = fieldValue2;
            }
            return Objects.equals(obj2, fieldValue);
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter.NameFilter
        boolean apply(Object obj) {
            throw new JSONException("TODO");
        }
    }

    static final class SegmentFilter extends JSONPathFilter {
        final JSONPathSegment expr;
        final Operator operator;
        final Object value;

        public SegmentFilter(JSONPathSegment jSONPathSegment, Operator operator, Object obj) {
            this.expr = jSONPathSegment;
            this.operator = operator;
            this.value = obj;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        boolean apply(JSONPath.Context context, Object obj) {
            if (obj == null) {
                return false;
            }
            JSONPath.Context context2 = new JSONPath.Context(null, null, this.expr, null, 0L);
            context2.root = obj;
            this.expr.eval(context2);
            int compare = TypeUtils.compare(context2.value, this.value);
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compare == 0;
                case 2:
                    return compare != 0;
                case 3:
                    return compare > 0;
                case 4:
                    return compare >= 0;
                case 5:
                    return compare < 0;
                case 6:
                    return compare <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            throw new JSONException("UnsupportedOperation " + getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray = new JSONArray(list.size());
                for (int i = 0; i < list.size(); i++) {
                    Object obj2 = list.get(i);
                    if (apply(context, obj2)) {
                        jSONArray.add(obj2);
                    }
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            throw new JSONException("UnsupportedOperation " + obj.getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public boolean remove(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (apply(context, list.get(size))) {
                        list.remove(size);
                    }
                }
                return true;
            }
            throw new JSONException("UnsupportedOperation " + getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void set(JSONPath.Context context, Object obj) {
            Object obj2;
            if (context.parent == null) {
                obj2 = context.root;
            } else {
                obj2 = context.parent.value;
            }
            if (obj2 instanceof List) {
                List list = (List) obj2;
                for (int i = 0; i < list.size(); i++) {
                    if (apply(context, list.get(i))) {
                        list.set(i, obj);
                    }
                }
                return;
            }
            throw new JSONException("UnsupportedOperation ");
        }
    }

    static final class RangeIndexSegmentFilter extends JSONPathFilter {
        final JSONPathSegment.RangeIndexSegment expr;
        final Operator operator;
        final Object value;

        public RangeIndexSegmentFilter(JSONPathSegment.RangeIndexSegment rangeIndexSegment, Operator operator, Object obj) {
            this.expr = rangeIndexSegment;
            this.operator = operator;
            this.value = obj;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        boolean apply(JSONPath.Context context, Object obj) {
            if (obj == null) {
                return false;
            }
            JSONPath.Context context2 = new JSONPath.Context(null, null, this.expr, null, 0L);
            context2.root = obj;
            this.expr.eval(context2);
            List list = (List) context2.value;
            for (int i = 0; i < list.size(); i++) {
                int compare = TypeUtils.compare(list.get(i), this.value);
                switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                    case 1:
                        if (compare != 0) {
                            return false;
                        }
                    case 2:
                        if (compare == 0) {
                            return false;
                        }
                    case 3:
                        if (compare <= 0) {
                            return false;
                        }
                    case 4:
                        if (compare < 0) {
                            return false;
                        }
                    case 5:
                        if (compare >= 0) {
                            return false;
                        }
                    case 6:
                        if (compare > 0) {
                            return false;
                        }
                    default:
                        throw new UnsupportedOperationException();
                }
            }
            return true;
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            throw new JSONException("UnsupportedOperation " + getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray = new JSONArray(list.size());
                for (int i = 0; i < list.size(); i++) {
                    Object obj2 = list.get(i);
                    if (apply(context, obj2)) {
                        jSONArray.add(obj2);
                    }
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            throw new JSONException("UnsupportedOperation " + obj.getClass());
        }
    }

    static final class Segment2Filter extends JSONPathFilter {
        final JSONPathSegment left;
        final Operator operator;
        final JSONPathSegment right;

        public Segment2Filter(JSONPathSegment jSONPathSegment, Operator operator, JSONPathSegment jSONPathSegment2) {
            this.left = jSONPathSegment;
            this.operator = operator;
            this.right = jSONPathSegment2;
        }

        @Override // com.alibaba.fastjson2.JSONPathFilter
        boolean apply(JSONPath.Context context, Object obj) {
            if (obj == null) {
                return false;
            }
            JSONPath.Context context2 = new JSONPath.Context(null, null, this.left, null, 0L);
            context2.root = obj;
            this.left.eval(context2);
            Object obj2 = context2.value;
            JSONPath.Context context3 = new JSONPath.Context(null, null, this.right, null, 0L);
            context3.root = obj;
            this.right.eval(context3);
            int compare = TypeUtils.compare(obj2, context3.value);
            switch (AnonymousClass1.$SwitchMap$com$alibaba$fastjson2$JSONPathFilter$Operator[this.operator.ordinal()]) {
                case 1:
                    return compare == 0;
                case 2:
                    return compare != 0;
                case 3:
                    return compare > 0;
                case 4:
                    return compare >= 0;
                case 5:
                    return compare < 0;
                case 6:
                    return compare <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void accept(JSONReader jSONReader, JSONPath.Context context) {
            throw new JSONException("UnsupportedOperation " + getClass());
        }

        @Override // com.alibaba.fastjson2.JSONPathSegment
        public void eval(JSONPath.Context context) {
            Object obj;
            if (context.parent == null) {
                obj = context.root;
            } else {
                obj = context.parent.value;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray = new JSONArray(list.size());
                for (int i = 0; i < list.size(); i++) {
                    Object obj2 = list.get(i);
                    if (apply(context, obj2)) {
                        jSONArray.add(obj2);
                    }
                }
                context.value = jSONArray;
                context.eval = true;
                return;
            }
            throw new JSONException("UnsupportedOperation " + obj.getClass());
        }
    }
}
