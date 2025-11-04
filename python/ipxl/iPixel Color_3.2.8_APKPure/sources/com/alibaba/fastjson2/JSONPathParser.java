package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONPathFilter;
import com.alibaba.fastjson2.JSONPathSegment;
import com.alibaba.fastjson2.util.Fnv;
import java.util.ArrayList;
import java.util.List;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
class JSONPathParser {
    boolean dollar;
    int filterNests;
    JSONPathSegment first;
    final JSONReader jsonReader;
    boolean lax;
    boolean negative;
    final String path;
    JSONPathSegment second;
    int segmentIndex;
    List<JSONPathSegment> segments;
    boolean strict;

    static boolean isOperator(char c) {
        return c == '=' || c == '<' || c == '>' || c == '!';
    }

    public JSONPathParser(String str) {
        this.path = str;
        JSONReader of = JSONReader.of(str, JSONPath.PARSE_CONTEXT);
        this.jsonReader = of;
        if (of.ch == 'l' && of.nextIfMatchIdent('l', 'a', 'x')) {
            this.lax = true;
        } else if (of.ch == 's' && of.nextIfMatchIdent('s', 't', 'r', 'i', 'c', 't')) {
            this.strict = true;
        }
        if (of.ch == '-') {
            of.next();
            this.negative = true;
        }
        if (of.ch == '$') {
            of.next();
            this.dollar = true;
        }
    }

    JSONPath parse(JSONPath.Feature... featureArr) {
        JSONPathSegment jSONPathSegment;
        if (this.dollar && this.jsonReader.ch == 26) {
            if (this.negative) {
                return new JSONPathSingle(JSONPathFunction.FUNC_NEGATIVE, this.path, new JSONPath.Feature[0]);
            }
            return JSONPath.RootPath.INSTANCE;
        }
        if (this.jsonReader.ch == 'e' && this.jsonReader.nextIfMatchIdent('e', 'x', 'i', 's', 't', 's')) {
            if (!this.jsonReader.nextIfMatch('(')) {
                throw new JSONException("syntax error " + this.path);
            }
            if (this.jsonReader.ch == '@') {
                this.jsonReader.next();
                if (!this.jsonReader.nextIfMatch('.')) {
                    throw new JSONException("syntax error " + this.path);
                }
            }
            char c = this.jsonReader.ch;
            if ((c >= 'a' && c <= 'z') || ((c >= 'A' && c <= 'Z') || c == '_' || c == '@' || Character.isIdeographic(c))) {
                JSONPathSegment parseProperty = parseProperty();
                if (!this.jsonReader.nextIfMatch(')')) {
                    throw new JSONException("syntax error " + this.path);
                }
                return new JSONPathTwoSegment(this.path, parseProperty, JSONPathFunction.FUNC_EXISTS, new JSONPath.Feature[0]);
            }
            throw new JSONException("syntax error " + this.path);
        }
        while (this.jsonReader.ch != 26) {
            char c2 = this.jsonReader.ch;
            if (c2 == '.') {
                this.jsonReader.next();
                jSONPathSegment = parseProperty();
            } else if (this.jsonReader.ch == '[') {
                jSONPathSegment = parseArrayAccess();
            } else if ((c2 >= 'a' && c2 <= 'z') || ((c2 >= 'A' && c2 <= 'Z') || c2 == '_' || Character.isIdeographic(c2))) {
                jSONPathSegment = parseProperty();
            } else if (c2 == '?') {
                if (this.dollar && this.segmentIndex == 0) {
                    this.first = JSONPathSegment.RootSegment.INSTANCE;
                    this.segmentIndex++;
                }
                this.jsonReader.next();
                jSONPathSegment = parseFilter();
            } else if (c2 == '@') {
                this.jsonReader.next();
                jSONPathSegment = JSONPathSegment.SelfSegment.INSTANCE;
            } else {
                throw new JSONException("not support " + c2);
            }
            int i = this.segmentIndex;
            if (i == 0) {
                this.first = jSONPathSegment;
            } else if (i == 1) {
                this.second = jSONPathSegment;
            } else if (i == 2) {
                ArrayList arrayList = new ArrayList();
                this.segments = arrayList;
                arrayList.add(this.first);
                this.segments.add(this.second);
                this.segments.add(jSONPathSegment);
            } else {
                this.segments.add(jSONPathSegment);
            }
            this.segmentIndex++;
        }
        if (this.negative) {
            int i2 = this.segmentIndex;
            if (i2 == 1) {
                this.second = JSONPathFunction.FUNC_NEGATIVE;
            } else if (i2 == 2) {
                ArrayList arrayList2 = new ArrayList();
                this.segments = arrayList2;
                arrayList2.add(this.first);
                this.segments.add(this.second);
                this.segments.add(JSONPathFunction.FUNC_NEGATIVE);
            } else {
                this.segments.add(JSONPathFunction.FUNC_NEGATIVE);
            }
            this.segmentIndex++;
        }
        int i3 = this.segmentIndex;
        if (i3 != 1) {
            if (i3 == 2) {
                return new JSONPathTwoSegment(this.path, this.first, this.second, featureArr);
            }
            return new JSONPathMulti(this.path, this.segments, featureArr);
        }
        JSONPathSegment jSONPathSegment2 = this.first;
        if (jSONPathSegment2 instanceof JSONPathSegmentName) {
            return new JSONPathSingleName(this.path, (JSONPathSegmentName) this.first, featureArr);
        }
        if (jSONPathSegment2 instanceof JSONPathSegmentIndex) {
            JSONPathSegmentIndex jSONPathSegmentIndex = (JSONPathSegmentIndex) jSONPathSegment2;
            if (jSONPathSegmentIndex.index >= 0) {
                return new JSONPathSingleIndex(this.path, jSONPathSegmentIndex, featureArr);
            }
        }
        return new JSONPathSingle(this.first, this.path, featureArr);
    }

    private JSONPathSegment parseArrayAccess() {
        JSONPathSegment multiNameSegment;
        boolean z;
        this.jsonReader.next();
        char c = this.jsonReader.ch;
        if (c != '\"') {
            if (c != '*') {
                if (c != '-') {
                    if (c == '?') {
                        this.jsonReader.next();
                        multiNameSegment = parseFilter();
                    } else if (c == 'l') {
                        String readFieldNameUnquote = this.jsonReader.readFieldNameUnquote();
                        if ("last".equals(readFieldNameUnquote)) {
                            multiNameSegment = JSONPathSegmentIndex.of(-1);
                        } else {
                            throw new JSONException("not support : " + readFieldNameUnquote);
                        }
                    } else if (c == 'r') {
                        String readFieldNameUnquote2 = this.jsonReader.readFieldNameUnquote();
                        if ("randomIndex".equals(readFieldNameUnquote2) && this.jsonReader.nextIfMatch('(') && this.jsonReader.nextIfMatch(')') && this.jsonReader.ch == ']') {
                            multiNameSegment = JSONPathSegment.RandomIndexSegment.INSTANCE;
                        } else {
                            throw new JSONException("not support : " + readFieldNameUnquote2);
                        }
                    } else if (c != '\'') {
                        if (c != '(') {
                            switch (c) {
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    break;
                                case ':':
                                    this.jsonReader.next();
                                    int readInt32Value = this.jsonReader.ch == ']' ? 0 : this.jsonReader.readInt32Value();
                                    if (readInt32Value > 0) {
                                        multiNameSegment = new JSONPathSegment.RangeIndexSegment(0, readInt32Value);
                                        break;
                                    } else {
                                        multiNameSegment = new JSONPathSegment.RangeIndexSegment(Integer.MIN_VALUE, readInt32Value);
                                        break;
                                    }
                                default:
                                    throw new JSONException("TODO : " + this.jsonReader.current());
                            }
                        } else {
                            this.jsonReader.next();
                            if (this.jsonReader.nextIfMatch('@') && this.jsonReader.nextIfMatch('.')) {
                                String readFieldNameUnquote3 = this.jsonReader.readFieldNameUnquote();
                                readFieldNameUnquote3.hashCode();
                                if (readFieldNameUnquote3.equals("length") || readFieldNameUnquote3.equals("size")) {
                                    int readInt32Value2 = this.jsonReader.readInt32Value();
                                    if (!this.jsonReader.nextIfMatch(')')) {
                                        throw new JSONException("not support : " + readFieldNameUnquote3);
                                    }
                                    if (readInt32Value2 > 0) {
                                        throw new JSONException("not support : " + readFieldNameUnquote3);
                                    }
                                    multiNameSegment = JSONPathSegmentIndex.of(readInt32Value2);
                                } else {
                                    throw new JSONException("not support : " + this.path);
                                }
                            } else {
                                throw new JSONException("not support : " + this.path);
                            }
                        }
                    }
                }
                int readInt32Value3 = this.jsonReader.readInt32Value();
                if (this.jsonReader.ch == ':') {
                    this.jsonReader.next();
                    if (this.jsonReader.ch == ']') {
                        multiNameSegment = new JSONPathSegment.RangeIndexSegment(readInt32Value3, readInt32Value3 >= 0 ? Integer.MAX_VALUE : 0);
                    } else {
                        multiNameSegment = new JSONPathSegment.RangeIndexSegment(readInt32Value3, this.jsonReader.readInt32Value());
                    }
                } else {
                    if (this.jsonReader.isNumber()) {
                        z = false;
                    } else {
                        z = this.jsonReader.nextIfMatchIdent('l', 'a', 's', 't');
                        if (!z) {
                            multiNameSegment = JSONPathSegmentIndex.of(readInt32Value3);
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(readInt32Value3));
                    if (z) {
                        arrayList.add(-1);
                        this.jsonReader.nextIfComma();
                    }
                    while (true) {
                        if (this.jsonReader.isNumber()) {
                            arrayList.add(Integer.valueOf(this.jsonReader.readInt32Value()));
                        } else if (this.jsonReader.nextIfMatchIdent('l', 'a', 's', 't')) {
                            arrayList.add(-1);
                            this.jsonReader.nextIfComma();
                        } else {
                            int[] iArr = new int[arrayList.size()];
                            while (r7 < arrayList.size()) {
                                iArr[r7] = ((Integer) arrayList.get(r7)).intValue();
                                r7++;
                            }
                            multiNameSegment = new JSONPathSegment.MultiIndexSegment(iArr);
                        }
                    }
                }
            } else {
                this.jsonReader.next();
                multiNameSegment = JSONPathSegment.AllSegment.INSTANCE_ARRAY;
            }
            while (true) {
                if (this.jsonReader.ch != '&' || this.jsonReader.ch == '|' || this.jsonReader.ch == 'a' || this.jsonReader.ch == 'o') {
                    this.filterNests--;
                    multiNameSegment = parseFilterRest(multiNameSegment);
                } else {
                    while (this.filterNests > 0) {
                        this.jsonReader.next();
                        this.filterNests--;
                    }
                    if (this.jsonReader.nextIfArrayEnd()) {
                        return multiNameSegment;
                    }
                    throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
                }
            }
        }
        String readString = this.jsonReader.readString();
        if (this.jsonReader.current() == ']') {
            multiNameSegment = new JSONPathSegmentName(readString, Fnv.hashCode64(readString));
        } else if (this.jsonReader.isString()) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(readString);
            do {
                arrayList2.add(this.jsonReader.readString());
            } while (this.jsonReader.isString());
            String[] strArr = new String[arrayList2.size()];
            arrayList2.toArray(strArr);
            multiNameSegment = new JSONPathSegment.MultiNameSegment(strArr);
        } else {
            throw new JSONException("TODO : " + this.jsonReader.current());
        }
        while (true) {
            if (this.jsonReader.ch != '&') {
            }
            this.filterNests--;
            multiNameSegment = parseFilterRest(multiNameSegment);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0197, code lost:
    
        if (r3.equals("entrySet") == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.alibaba.fastjson2.JSONPathSegment parseProperty() {
        /*
            Method dump skipped, instructions count: 804
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONPathParser.parseProperty():com.alibaba.fastjson2.JSONPathSegment");
    }

    JSONPathSegment parseFilterRest(JSONPathSegment jSONPathSegment) {
        char c = this.jsonReader.ch;
        boolean z = true;
        if (c == '&') {
            this.jsonReader.next();
            if (!this.jsonReader.nextIfMatch(Typography.amp)) {
                throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
            }
        } else {
            if (c != 'A') {
                if (c != 'O') {
                    if (c != 'a') {
                        if (c != 'o') {
                            if (c == '|') {
                                this.jsonReader.next();
                                if (!this.jsonReader.nextIfMatch('|')) {
                                    throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
                                }
                                z = false;
                            } else {
                                throw new JSONException("TODO : " + this.jsonReader.ch);
                            }
                        }
                    }
                }
                String readFieldNameUnquote = this.jsonReader.readFieldNameUnquote();
                if (!"or".equalsIgnoreCase(readFieldNameUnquote)) {
                    throw new JSONException("syntax error : " + readFieldNameUnquote);
                }
                z = false;
            }
            String readFieldNameUnquote2 = this.jsonReader.readFieldNameUnquote();
            if (!"and".equalsIgnoreCase(readFieldNameUnquote2)) {
                throw new JSONException("syntax error : " + readFieldNameUnquote2);
            }
        }
        JSONPathSegment parseFilter = parseFilter();
        if (jSONPathSegment instanceof JSONPathFilter.GroupFilter) {
            JSONPathFilter.GroupFilter groupFilter = (JSONPathFilter.GroupFilter) jSONPathSegment;
            groupFilter.filters.add(((JSONPathFilter) parseFilter).setAnd(z));
            return groupFilter;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add((JSONPathFilter) jSONPathSegment);
        if (parseFilter instanceof JSONPathFilter.GroupFilter) {
            List<JSONPathFilter> list = ((JSONPathFilter.GroupFilter) parseFilter).filters;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    JSONPathFilter jSONPathFilter = list.get(i);
                    if (i == 0) {
                        jSONPathFilter.setAnd(z);
                    }
                    arrayList.add(jSONPathFilter);
                }
            }
        } else {
            arrayList.add(((JSONPathFilter) parseFilter).setAnd(z));
        }
        return new JSONPathFilter.GroupFilter(arrayList);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x099b  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x099e  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0749  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0386 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x031c  */
    /* JADX WARN: Type inference failed for: r5v41 */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v55 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.alibaba.fastjson2.JSONPathSegment parseFilter() {
        /*
            Method dump skipped, instructions count: 2608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONPathParser.parseFilter():com.alibaba.fastjson2.JSONPathSegment");
    }

    public JSONPathSegment parseSegment() {
        Object obj;
        if (this.jsonReader.nextIfMatch('@')) {
            if (this.jsonReader.nextIfMatch('.')) {
                if (this.jsonReader.isNumber()) {
                    obj = this.jsonReader.readNumber();
                } else {
                    obj = this.jsonReader.readFieldNameUnquote();
                }
            } else if (this.jsonReader.nextIfArrayStart()) {
                if (this.jsonReader.isNumber()) {
                    obj = this.jsonReader.readNumber();
                } else if (this.jsonReader.isString()) {
                    obj = this.jsonReader.readString();
                } else {
                    throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
                }
                if (!this.jsonReader.nextIfArrayEnd()) {
                    throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
                }
            } else {
                obj = null;
            }
            if (obj instanceof String) {
                String str = (String) obj;
                return new JSONPathSegmentName(str, Fnv.hashCode64(str));
            }
            if (obj instanceof Integer) {
                return new JSONPathSegmentIndex(((Integer) obj).intValue());
            }
        }
        throw new JSONException(this.jsonReader.info("jsonpath syntax error"));
    }
}
