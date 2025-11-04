package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONPathCompilerReflect;
import com.alibaba.fastjson2.internal.asm.ASMUtils;
import com.alibaba.fastjson2.internal.asm.ClassWriter;
import com.alibaba.fastjson2.internal.asm.MethodWriter;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.util.DynamicClassLoader;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
final class JSONPathCompilerReflectASM extends JSONPathCompilerReflect {
    private final DynamicClassLoader classLoader;
    private static final AtomicLong seed = new AtomicLong();
    static final JSONPathCompilerReflectASM INSTANCE = new JSONPathCompilerReflectASM(DynamicClassLoader.getInstance());
    private static final String TYPE_SINGLE_NAME_PATH_TYPED = ASMUtils.type(JSONPathCompilerReflect.SingleNamePathTyped.class);
    private static final String METHOD_SINGLE_NAME_PATH_TYPED_INIT = "(Ljava/lang/String;Ljava/lang/Class;" + ASMUtils.DESC_OBJECT_READER + ASMUtils.DESC_FIELD_READER + ASMUtils.DESC_OBJECT_WRITER + ASMUtils.DESC_FIELD_WRITER + ")V";

    public JSONPathCompilerReflectASM(DynamicClassLoader dynamicClassLoader) {
        this.classLoader = dynamicClassLoader;
    }

    private boolean support(Class cls) {
        boolean isExternalClass = this.classLoader.isExternalClass(cls);
        int modifiers = cls.getModifiers();
        return Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers) || !Modifier.isPublic(modifiers) || isExternalClass;
    }

    @Override // com.alibaba.fastjson2.JSONPathCompilerReflect
    protected JSONPath compileSingleNamePath(Class cls, JSONPathSingleName jSONPathSingleName) {
        String str;
        char c;
        int i;
        if (support(cls)) {
            return super.compileSingleNamePath(cls, jSONPathSingleName);
        }
        String str2 = jSONPathSingleName.name;
        String type = ASMUtils.type(cls);
        ObjectReader objectReader = jSONPathSingleName.getReaderContext().getObjectReader(cls);
        FieldReader fieldReader = objectReader.getFieldReader(str2);
        ObjectWriter objectWriter = jSONPathSingleName.getWriterContext().getObjectWriter(cls);
        FieldWriter fieldWriter = objectWriter.getFieldWriter(str2);
        ClassWriter classWriter = new ClassWriter(null);
        String str3 = "JSONPath_" + seed.incrementAndGet();
        Package r10 = JSONPathCompilerReflectASM.class.getPackage();
        if (r10 != null) {
            String name = r10.getName();
            int length = name.length();
            int i2 = length + 1;
            char[] cArr = new char[str3.length() + i2];
            name.getChars(0, name.length(), cArr, 0);
            cArr[length] = '.';
            str3.getChars(0, str3.length(), cArr, i2);
            str3 = new String(cArr);
            char c2 = '/';
            cArr[length] = '/';
            int i3 = 0;
            while (i3 < length) {
                char c3 = c2;
                if (cArr[i3] == '.') {
                    cArr[i3] = c3;
                }
                i3++;
                c2 = c3;
            }
            str = new String(cArr);
        } else {
            str = str3;
        }
        String str4 = TYPE_SINGLE_NAME_PATH_TYPED;
        classWriter.visit(52, 49, str, str4, new String[0]);
        String str5 = METHOD_SINGLE_NAME_PATH_TYPED_INIT;
        MethodWriter visitMethod = classWriter.visitMethod(1, "<init>", str5, 64);
        visitMethod.aload(0);
        visitMethod.aload(1);
        visitMethod.aload(2);
        visitMethod.aload(3);
        visitMethod.aload(4);
        visitMethod.aload(5);
        visitMethod.aload(6);
        visitMethod.invokespecial(str4, "<init>", str5);
        visitMethod.return_();
        visitMethod.visitMaxs(3, 3);
        if (fieldReader != null) {
            Class cls2 = fieldReader.fieldClass;
            if (cls2 == Integer.TYPE) {
                MethodWriter visitMethod2 = classWriter.visitMethod(1, "setInt", "(Ljava/lang/Object;I)V", 64);
                visitMethod2.aload(1);
                visitMethod2.checkcast(type);
                c = 2;
                visitMethod2.visitVarInsn(21, 2);
                gwSetValue(visitMethod2, type, fieldReader);
                visitMethod2.return_();
                visitMethod2.visitMaxs(2, 2);
            } else {
                c = 2;
            }
            if (cls2 == Long.TYPE) {
                i = 1;
                MethodWriter visitMethod3 = classWriter.visitMethod(1, "setLong", "(Ljava/lang/Object;J)V", 64);
                visitMethod3.aload(1);
                visitMethod3.checkcast(type);
                visitMethod3.lload(2);
                gwSetValue(visitMethod3, type, fieldReader);
                visitMethod3.return_();
                visitMethod3.visitMaxs(2, 2);
            } else {
                i = 1;
            }
            MethodWriter visitMethod4 = classWriter.visitMethod(i, "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", 64);
            visitMethod4.aload(i);
            visitMethod4.checkcast(type);
            visitMethod4.aload(2);
            if (cls2 == Integer.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "intValue", "()I");
            } else if (cls2 == Long.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "longValue", "()J");
            } else if (cls2 == Float.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "floatValue", "()F");
            } else if (cls2 == Double.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "doubleValue", "()D");
            } else if (cls2 == Short.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "shortValue", "()S");
            } else if (cls2 == Byte.TYPE) {
                visitMethod4.checkcast("java/lang/Number");
                visitMethod4.invokevirtual("java/lang/Number", "byteValue", "()B");
            } else if (cls2 == Boolean.TYPE) {
                visitMethod4.checkcast("java/lang/Boolean");
                visitMethod4.invokevirtual("java/lang/Boolean", "booleanValue", "()Z");
            } else if (cls2 == Character.TYPE) {
                visitMethod4.checkcast("java/lang/Character");
                visitMethod4.invokevirtual("java/lang/Character", "charValue", "()C");
            }
            gwSetValue(visitMethod4, type, fieldReader);
            visitMethod4.return_();
            visitMethod4.visitMaxs(2, 2);
        }
        if (fieldWriter != null) {
            Class cls3 = fieldReader.fieldClass;
            MethodWriter visitMethod5 = classWriter.visitMethod(1, "eval", "(Ljava/lang/Object;)Ljava/lang/Object;", 64);
            visitMethod5.aload(1);
            visitMethod5.checkcast(type);
            gwGetValue(visitMethod5, type, fieldWriter);
            if (cls3 == Integer.TYPE) {
                visitMethod5.invokestatic("java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            } else if (cls3 == Long.TYPE) {
                visitMethod5.invokestatic("java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            } else if (cls3 == Float.TYPE) {
                visitMethod5.invokestatic("java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            } else if (cls3 == Double.TYPE) {
                visitMethod5.invokestatic("java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            } else if (cls3 == Short.TYPE) {
                visitMethod5.invokestatic("java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            } else if (cls3 == Byte.TYPE) {
                visitMethod5.invokestatic("java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            } else if (cls3 == Boolean.TYPE) {
                visitMethod5.invokestatic("java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            } else if (cls3 == Character.TYPE) {
                visitMethod5.invokestatic("java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            }
            visitMethod5.areturn();
            visitMethod5.visitMaxs(2, 2);
        }
        byte[] byteArray = classWriter.toByteArray();
        try {
            return (JSONPath) this.classLoader.defineClassPublic(str3, byteArray, 0, byteArray.length).getConstructors()[0].newInstance(jSONPathSingleName.path, cls, objectReader, fieldReader, objectWriter, fieldWriter);
        } catch (Throwable th) {
            throw new JSONException("compile jsonpath error, path " + jSONPathSingleName.path + ", objectType " + cls.getTypeName(), th);
        }
    }

    private void gwSetValue(MethodWriter methodWriter, String str, FieldReader fieldReader) {
        Method method = fieldReader.method;
        Field field = fieldReader.field;
        String desc = ASMUtils.desc(fieldReader.fieldClass);
        if (method != null) {
            Class<?> returnType = method.getReturnType();
            methodWriter.invokevirtual(str, method.getName(), "(" + desc + ')' + ASMUtils.desc(returnType));
            if (returnType != Void.TYPE) {
                methodWriter.pop();
                return;
            }
            return;
        }
        methodWriter.putfield(str, field.getName(), desc);
    }

    private void gwGetValue(MethodWriter methodWriter, String str, FieldWriter fieldWriter) {
        Method method = fieldWriter.method;
        Field field = fieldWriter.field;
        String desc = ASMUtils.desc(fieldWriter.fieldClass);
        if (method != null) {
            methodWriter.invokevirtual(str, method.getName(), "()" + desc);
        } else {
            methodWriter.getfield(str, field.getName(), desc);
        }
    }
}
