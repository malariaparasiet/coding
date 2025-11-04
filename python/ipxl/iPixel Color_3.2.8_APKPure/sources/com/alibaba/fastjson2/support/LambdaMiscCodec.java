package com.alibaba.fastjson2.support;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaders;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriters;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: classes2.dex */
public class LambdaMiscCodec {
    static volatile Throwable errorLast;
    static volatile boolean hppcError;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static ObjectWriter getObjectWriter(Type type, Class cls) {
        if (hppcError) {
            return null;
        }
        String name = cls.getName();
        name.hashCode();
        switch (name) {
            case "com.carrotsearch.hppc.LongHashSet":
            case "gnu.trove.list.array.TLongArrayList":
            case "gnu.trove.set.hash.TLongHashSet":
            case "com.carrotsearch.hppc.LongArrayList":
                try {
                    return ObjectWriters.ofToLongArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e) {
                    throw new JSONException("illegal state", e);
                }
            case "gnu.trove.set.hash.TShortHashSet":
            case "gnu.trove.list.array.TShortArrayList":
            case "com.carrotsearch.hppc.ShortArrayList":
                try {
                    return ObjectWriters.ofToShortArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e2) {
                    throw new JSONException("illegal state", e2);
                }
            case "com.carrotsearch.hppc.CharHashSet":
            case "com.carrotsearch.hppc.CharArrayList":
            case "gnu.trove.list.array.TCharArrayList":
                try {
                    return ObjectWriters.ofToCharArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e3) {
                    throw new JSONException("illegal state", e3);
                }
            case "com.carrotsearch.hppc.IntArrayList":
            case "gnu.trove.set.hash.TIntHashSet":
            case "com.carrotsearch.hppc.IntHashSet":
            case "gnu.trove.list.array.TIntArrayList":
                try {
                    return ObjectWriters.ofToIntArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e4) {
                    throw new JSONException("illegal state", e4);
                }
            case "com.carrotsearch.hppc.BitSet":
                MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(cls);
                try {
                    return ObjectWriters.ofToBooleanArray(createToLongFunction(cls.getMethod("size", new Class[0])), (BiFunction<Object, Integer, Boolean>) (BiFunction) LambdaMetafactory.metafactory(trustedLookup, "apply", MethodType.methodType(BiFunction.class), MethodType.methodType(Object.class, Object.class, Object.class), trustedLookup.findVirtual(cls, "get", MethodType.methodType((Class<?>) Boolean.TYPE, (Class<?>) Integer.TYPE)), MethodType.methodType(Boolean.class, cls, Integer.class)).getTarget().invokeExact());
                } catch (Throwable unused) {
                    hppcError = true;
                    break;
                }
            case "com.carrotsearch.hppc.DoubleArrayList":
            case "gnu.trove.list.array.TDoubleArrayList":
                try {
                    return ObjectWriters.ofToDoubleArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e5) {
                    throw new JSONException("illegal state", e5);
                }
            case "com.carrotsearch.hppc.ByteArrayList":
            case "gnu.trove.stack.array.TByteArrayStack":
            case "gnu.trove.list.array.TByteArrayList":
            case "gnu.trove.set.hash.TByteHashSet":
                try {
                    return ObjectWriters.ofToByteArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e6) {
                    throw new JSONException("illegal state", e6);
                }
            case "gnu.trove.list.array.TFloatArrayList":
            case "com.carrotsearch.hppc.FloatArrayList":
                try {
                    return ObjectWriters.ofToFloatArray(createFunction(cls.getMethod("toArray", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e7) {
                    throw new JSONException("illegal state", e7);
                }
            case "org.bson.types.Decimal128":
                try {
                    return ObjectWriters.ofToBigDecimal(createFunction(cls.getMethod("bigDecimalValue", new Class[0])));
                } catch (NoSuchMethodException | SecurityException e8) {
                    throw new JSONException("illegal state", e8);
                }
            default:
                return null;
        }
    }

    public static ObjectReader getObjectReader(Class cls) {
        if (hppcError) {
            return null;
        }
        String name = cls.getName();
        name.hashCode();
        switch (name) {
            case "com.carrotsearch.hppc.LongHashSet":
            case "com.carrotsearch.hppc.LongArrayList":
                try {
                    return ObjectReaders.fromLongArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, long[].class)));
                } catch (NoSuchMethodException | SecurityException e) {
                    throw new JSONException("illegal state", e);
                }
            case "gnu.trove.set.hash.TShortHashSet":
            case "gnu.trove.list.array.TShortArrayList":
                try {
                    return ObjectReaders.fromShortArray(createFunction(cls.getConstructor(short[].class)));
                } catch (NoSuchMethodException | SecurityException e2) {
                    throw new JSONException("illegal state", e2);
                }
            case "com.carrotsearch.hppc.CharHashSet":
            case "com.carrotsearch.hppc.CharArrayList":
                try {
                    return ObjectReaders.fromCharArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, char[].class)));
                } catch (NoSuchMethodException | SecurityException e3) {
                    throw new JSONException("illegal state", e3);
                }
            case "com.carrotsearch.hppc.IntArrayList":
            case "com.carrotsearch.hppc.IntHashSet":
                try {
                    return ObjectReaders.fromIntArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, int[].class)));
                } catch (NoSuchMethodException | SecurityException e4) {
                    throw new JSONException("illegal state", e4);
                }
            case "gnu.trove.list.array.TLongArrayList":
            case "gnu.trove.set.hash.TLongHashSet":
                try {
                    return ObjectReaders.fromLongArray(createFunction(cls.getConstructor(long[].class)));
                } catch (NoSuchMethodException | SecurityException e5) {
                    throw new JSONException("illegal state", e5);
                }
            case "gnu.trove.set.hash.TIntHashSet":
            case "gnu.trove.list.array.TIntArrayList":
                try {
                    return ObjectReaders.fromIntArray(createFunction(cls.getConstructor(int[].class)));
                } catch (NoSuchMethodException | SecurityException e6) {
                    throw new JSONException("illegal state", e6);
                }
            case "com.carrotsearch.hppc.ShortArrayList":
                try {
                    return ObjectReaders.fromShortArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, short[].class)));
                } catch (NoSuchMethodException | SecurityException e7) {
                    throw new JSONException("illegal state", e7);
                }
            case "com.carrotsearch.hppc.DoubleArrayList":
                try {
                    return ObjectReaders.fromDoubleArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, double[].class)));
                } catch (NoSuchMethodException | SecurityException e8) {
                    throw new JSONException("illegal state", e8);
                }
            case "com.carrotsearch.hppc.ByteArrayList":
                try {
                    return ObjectReaders.fromByteArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, byte[].class)));
                } catch (NoSuchMethodException | SecurityException e9) {
                    throw new JSONException("illegal state", e9);
                }
            case "gnu.trove.list.array.TCharArrayList":
                try {
                    return ObjectReaders.fromCharArray(createFunction(cls.getConstructor(char[].class)));
                } catch (NoSuchMethodException | SecurityException e10) {
                    throw new JSONException("illegal state", e10);
                }
            case "gnu.trove.list.array.TFloatArrayList":
                try {
                    return ObjectReaders.fromFloatArray(createFunction(cls.getConstructor(float[].class)));
                } catch (NoSuchMethodException | SecurityException e11) {
                    throw new JSONException("illegal state", e11);
                }
            case "gnu.trove.stack.array.TByteArrayStack":
            case "gnu.trove.list.array.TByteArrayList":
            case "gnu.trove.set.hash.TByteHashSet":
                try {
                    return ObjectReaders.fromByteArray(createFunction(cls.getConstructor(byte[].class)));
                } catch (NoSuchMethodException | SecurityException e12) {
                    throw new JSONException("illegal state", e12);
                }
            case "com.carrotsearch.hppc.FloatArrayList":
                try {
                    return ObjectReaders.fromFloatArray(createFunction(cls.getMethod(TypedValues.TransitionType.S_FROM, float[].class)));
                } catch (NoSuchMethodException | SecurityException e13) {
                    throw new JSONException("illegal state", e13);
                }
            case "org.bson.types.Decimal128":
                try {
                    return ObjectReaders.fromBigDecimal(createFunction(cls.getConstructor(BigDecimal.class)));
                } catch (NoSuchMethodException | SecurityException e14) {
                    throw new JSONException("illegal state", e14);
                }
            case "gnu.trove.list.array.TDoubleArrayList":
                try {
                    return ObjectReaders.fromDoubleArray(createFunction(cls.getConstructor(double[].class)));
                } catch (NoSuchMethodException | SecurityException e15) {
                    throw new JSONException("illegal state", e15);
                }
            default:
                return null;
        }
    }

    public static LongFunction createLongFunction(Constructor constructor) {
        try {
            Class<?> declaringClass = constructor.getDeclaringClass();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (LongFunction) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_LONG_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_LONG, trustedLookup.findConstructor(declaringClass, TypeUtils.METHOD_TYPE_VOID_LONG), MethodType.methodType(declaringClass, (Class<?>) Long.TYPE)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectLongFunction(constructor);
        }
    }

    public static ToIntFunction createToIntFunction(Method method) {
        Class<?> declaringClass = method.getDeclaringClass();
        try {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (ToIntFunction) LambdaMetafactory.metafactory(trustedLookup, "applyAsInt", TypeUtils.METHOD_TYPE_TO_INT_FUNCTION, TypeUtils.METHOD_TYPE_INT_OBJECT, trustedLookup.findVirtual(declaringClass, method.getName(), MethodType.methodType(Integer.TYPE)), MethodType.methodType((Class<?>) Integer.TYPE, declaringClass)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectToIntFunction(method);
        }
    }

    public static ToLongFunction createToLongFunction(Method method) {
        Class<?> declaringClass = method.getDeclaringClass();
        try {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (ToLongFunction) LambdaMetafactory.metafactory(trustedLookup, "applyAsLong", TypeUtils.METHOD_TYPE_TO_LONG_FUNCTION, TypeUtils.METHOD_TYPE_LONG_OBJECT, trustedLookup.findVirtual(declaringClass, method.getName(), MethodType.methodType(Long.TYPE)), MethodType.methodType((Class<?>) Long.TYPE, declaringClass)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectToLongFunction(method);
        }
    }

    public static Function createFunction(Constructor constructor) {
        try {
            Class<?> declaringClass = constructor.getDeclaringClass();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            Class<?> cls = constructor.getParameterTypes()[0];
            return (Function) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT, trustedLookup.findConstructor(declaringClass, MethodType.methodType((Class<?>) Void.TYPE, cls)), MethodType.methodType(declaringClass, box(cls))).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ConstructorFunction(constructor);
        }
    }

    public static Supplier createSupplier(Constructor constructor) {
        try {
            Class<?> declaringClass = constructor.getDeclaringClass();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (Supplier) LambdaMetafactory.metafactory(trustedLookup, "get", TypeUtils.METHOD_TYPE_SUPPLIER, TypeUtils.METHOD_TYPE_OBJECT, trustedLookup.findConstructor(declaringClass, MethodType.methodType(Void.TYPE)), MethodType.methodType(declaringClass)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ConstructorSupplier(constructor);
        }
    }

    public static Supplier createSupplier(Method method) {
        try {
            Class<?> declaringClass = method.getDeclaringClass();
            Class<?> returnType = method.getReturnType();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (Supplier) LambdaMetafactory.metafactory(trustedLookup, "get", TypeUtils.METHOD_TYPE_SUPPLIER, TypeUtils.METHOD_TYPE_OBJECT, trustedLookup.findStatic(declaringClass, method.getName(), MethodType.methodType(returnType)), MethodType.methodType(returnType)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectSupplier(method);
        }
    }

    public static BiFunction createBiFunction(Method method) {
        MethodType methodType;
        MethodHandle methodHandle;
        try {
            Class<?> declaringClass = method.getDeclaringClass();
            Class<?> returnType = method.getReturnType();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> cls = parameterTypes[0];
            if (Modifier.isStatic(method.getModifiers())) {
                Class<?> cls2 = parameterTypes[1];
                MethodHandle findStatic = trustedLookup.findStatic(declaringClass, method.getName(), MethodType.methodType(returnType, cls, cls2));
                methodType = MethodType.methodType(returnType, cls, cls2);
                methodHandle = findStatic;
            } else {
                MethodHandle findVirtual = trustedLookup.findVirtual(declaringClass, method.getName(), MethodType.methodType(returnType, cls));
                methodType = MethodType.methodType(returnType, declaringClass, box(cls));
                methodHandle = findVirtual;
            }
            return (BiFunction) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_BI_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT_OBJECT, methodHandle, methodType).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectBiFunction(method);
        }
    }

    public static BiFunction createBiFunction(Constructor constructor) {
        try {
            Class<?> declaringClass = constructor.getDeclaringClass();
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Class<?> cls = parameterTypes[0];
            Class<?> cls2 = parameterTypes[1];
            return (BiFunction) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_BI_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT_OBJECT, trustedLookup.findConstructor(declaringClass, MethodType.methodType(Void.TYPE, cls, cls2)), MethodType.methodType(declaringClass, box(cls), box(cls2))).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ConstructorBiFunction(constructor);
        }
    }

    static Class<?> box(Class cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        return cls == Double.TYPE ? Double.class : cls;
    }

    static final class ConstructorSupplier implements Supplier {
        final Constructor constructor;

        ConstructorSupplier(Constructor constructor) {
            this.constructor = constructor;
        }

        @Override // java.util.function.Supplier
        public Object get() {
            try {
                return this.constructor.newInstance(new Object[0]);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    static final class ConstructorFunction implements Function {
        final Constructor constructor;

        ConstructorFunction(Constructor constructor) {
            this.constructor = constructor;
        }

        @Override // java.util.function.Function
        public Object apply(Object obj) {
            try {
                return this.constructor.newInstance(obj);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    static final class ConstructorBiFunction implements BiFunction {
        final Constructor constructor;

        ConstructorBiFunction(Constructor constructor) {
            this.constructor = constructor;
        }

        @Override // java.util.function.BiFunction
        public Object apply(Object obj, Object obj2) {
            try {
                return this.constructor.newInstance(obj, obj2);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    static final class ReflectBiFunction implements BiFunction {
        final Method method;

        ReflectBiFunction(Method method) {
            this.method = method;
        }

        @Override // java.util.function.BiFunction
        public Object apply(Object obj, Object obj2) {
            try {
                if (Modifier.isStatic(this.method.getModifiers())) {
                    return this.method.invoke(null, obj, obj2);
                }
                return this.method.invoke(obj, obj2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    static final class ReflectSupplier implements Supplier {
        final Method method;

        ReflectSupplier(Method method) {
            this.method = method;
        }

        @Override // java.util.function.Supplier
        public Object get() {
            try {
                return this.method.invoke(null, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    public static Function createFunction(Method method) {
        Class<?> cls;
        MethodHandle findVirtual;
        Class<?> declaringClass = method.getDeclaringClass();
        int modifiers = method.getModifiers();
        Class<?>[] parameterTypes = method.getParameterTypes();
        boolean isStatic = Modifier.isStatic(modifiers);
        Class<?> returnType = method.getReturnType();
        if (parameterTypes.length == 1 && isStatic) {
            cls = parameterTypes[0];
        } else {
            if (parameterTypes.length != 0 || isStatic) {
                throw new JSONException("not support parameters " + method);
            }
            cls = declaringClass;
        }
        try {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            if (isStatic) {
                findVirtual = trustedLookup.findStatic(declaringClass, method.getName(), MethodType.methodType(returnType, cls));
            } else {
                findVirtual = trustedLookup.findVirtual(declaringClass, method.getName(), MethodType.methodType(returnType));
            }
            return (Function) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT, findVirtual, MethodType.methodType(returnType, cls)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            if (!Modifier.isStatic(method.getModifiers())) {
                return new GetterFunction(method);
            }
            return new FactoryFunction(method);
        }
    }

    public static ObjIntConsumer createObjIntConsumer(Method method) {
        Class<?> declaringClass = method.getDeclaringClass();
        try {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(declaringClass);
            return (ObjIntConsumer) LambdaMetafactory.metafactory(trustedLookup, "accept", TypeUtils.METHOD_TYPE_OBJECT_INT_CONSUMER, TypeUtils.METHOD_TYPE_VOID_OBJECT_INT, trustedLookup.findVirtual(declaringClass, method.getName(), MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) Integer.TYPE)), MethodType.methodType(Void.TYPE, declaringClass, Integer.TYPE)).getTarget().invokeExact();
        } catch (Throwable th) {
            errorLast = th;
            return new ReflectObjIntConsumer(method);
        }
    }

    static final class ReflectObjIntConsumer implements ObjIntConsumer {
        final Method method;

        public ReflectObjIntConsumer(Method method) {
            this.method = method;
        }

        @Override // java.util.function.ObjIntConsumer
        public void accept(Object obj, int i) {
            try {
                this.method.invoke(obj, Integer.valueOf(i));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new JSONException("invoke error", e);
            }
        }
    }

    static final class FactoryFunction implements Function {
        final Method method;

        FactoryFunction(Method method) {
            this.method = method;
        }

        @Override // java.util.function.Function
        public Object apply(Object obj) {
            try {
                return this.method.invoke(null, obj);
            } catch (Exception e) {
                throw new JSONException("createInstance error", e);
            }
        }
    }

    static final class GetterFunction implements Function {
        final Method method;

        GetterFunction(Method method) {
            this.method = method;
        }

        @Override // java.util.function.Function
        public Object apply(Object obj) {
            try {
                return this.method.invoke(obj, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("createInstance error", e);
            }
        }
    }

    static final class ReflectLongFunction implements LongFunction {
        final Constructor constructor;

        public ReflectLongFunction(Constructor constructor) {
            this.constructor = constructor;
        }

        @Override // java.util.function.LongFunction
        public Object apply(long j) {
            try {
                return this.constructor.newInstance(Long.valueOf(j));
            } catch (Exception e) {
                throw new JSONException("createInstance error", e);
            }
        }
    }

    static final class ReflectToIntFunction implements ToIntFunction {
        final Method method;

        public ReflectToIntFunction(Method method) {
            this.method = method;
        }

        @Override // java.util.function.ToIntFunction
        public int applyAsInt(Object obj) {
            try {
                return ((Integer) this.method.invoke(obj, new Object[0])).intValue();
            } catch (Exception e) {
                throw new JSONException("applyAsInt error", e);
            }
        }
    }

    static final class ReflectToLongFunction implements ToLongFunction {
        final Method method;

        public ReflectToLongFunction(Method method) {
            this.method = method;
        }

        @Override // java.util.function.ToLongFunction
        public long applyAsLong(Object obj) {
            try {
                return ((Long) this.method.invoke(obj, new Object[0])).longValue();
            } catch (Exception e) {
                throw new JSONException("applyAsLong error", e);
            }
        }
    }
}
