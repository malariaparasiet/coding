package com.alibaba.fastjson2.support.money;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderCreator;
import com.alibaba.fastjson2.reader.ObjectReaderImplValue;
import com.alibaba.fastjson2.reader.ObjectReaderNoneDefaultConstructor;
import com.alibaba.fastjson2.support.LambdaMiscCodec;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterAdapter;
import com.alibaba.fastjson2.writer.ObjectWriterCreator;
import com.alibaba.fastjson2.writer.ObjectWriters;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class MoneySupport {
    static Class CLASS_CURRENCY_UNIT;
    static Class CLASS_DEFAULT_NUMBER_VALUE;
    static Class CLASS_MONETARY;
    static Class CLASS_MONETARY_AMOUNT;
    static Class CLASS_MONETARY_AMOUNT_FACTORY;
    static Class CLASS_NUMBER_VALUE;
    static Function<Object, Object> FUNC_CREATE;
    static Function<String, Object> FUNC_GET_CURRENCY;
    static Supplier<Object> FUNC_GET_DEFAULT_AMOUNT_FACTORY;
    static Function<Object, BigDecimal> FUNC_NUMBER_VALUE;
    static BiFunction<Object, Object, Object> FUNC_SET_CURRENCY;
    static BiFunction<Object, Object, Number> FUNC_SET_NUMBER;
    static Method METHOD_NUMBER_VALUE_OF;

    public static ObjectReader createCurrencyUnitReader() {
        if (CLASS_MONETARY == null) {
            CLASS_MONETARY = TypeUtils.loadClass("javax.money.Monetary");
        }
        if (CLASS_CURRENCY_UNIT == null) {
            CLASS_CURRENCY_UNIT = TypeUtils.loadClass("javax.money.CurrencyUnit");
        }
        if (FUNC_GET_CURRENCY == null) {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(CLASS_MONETARY);
            try {
                final BiFunction invokeExact = (BiFunction) LambdaMetafactory.metafactory(trustedLookup, "apply", TypeUtils.METHOD_TYPE_BI_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT_OBJECT, trustedLookup.findStatic(CLASS_MONETARY, "getCurrency", MethodType.methodType(CLASS_CURRENCY_UNIT, String.class, String[].class)), MethodType.methodType(CLASS_CURRENCY_UNIT, String.class, String[].class)).getTarget().invokeExact();
                FUNC_GET_CURRENCY = new Function() { // from class: com.alibaba.fastjson2.support.money.MoneySupport$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Object apply;
                        apply = invokeExact.apply((String) obj, new String[0]);
                        return apply;
                    }
                };
            } catch (Throwable th) {
                throw new JSONException("method not found : javax.money.Monetary.getCurrency", th);
            }
        }
        return ObjectReaderImplValue.of(CLASS_CURRENCY_UNIT, String.class, FUNC_GET_CURRENCY);
    }

    public static ObjectReader createMonetaryAmountReader() {
        if (CLASS_NUMBER_VALUE == null) {
            CLASS_NUMBER_VALUE = TypeUtils.loadClass("javax.money.NumberValue");
        }
        if (CLASS_CURRENCY_UNIT == null) {
            CLASS_CURRENCY_UNIT = TypeUtils.loadClass("javax.money.CurrencyUnit");
        }
        try {
            String[] strArr = {"currency", "number"};
            Function createFactoryFunction = ObjectReaderCreator.INSTANCE.createFactoryFunction(MoneySupport.class.getMethod("createMonetaryAmount", Object.class, Object.class), strArr);
            ObjectReaderCreator objectReaderCreator = ObjectReaderCreator.INSTANCE;
            Class cls = CLASS_CURRENCY_UNIT;
            FieldReader createFieldReaderParam = objectReaderCreator.createFieldReaderParam(MoneySupport.class, MoneySupport.class, "currency", 0, 0L, null, cls, cls, "currency", null, null, null);
            ObjectReaderCreator objectReaderCreator2 = ObjectReaderCreator.INSTANCE;
            Class cls2 = CLASS_DEFAULT_NUMBER_VALUE;
            return new ObjectReaderNoneDefaultConstructor(null, null, null, 0L, createFactoryFunction, null, strArr, new FieldReader[]{createFieldReaderParam, objectReaderCreator2.createFieldReaderParam(MoneySupport.class, MoneySupport.class, "number", 0, 0L, null, cls2, cls2, "number", null, null, null)}, null, null, null);
        } catch (NoSuchMethodException e) {
            throw new JSONException("createMonetaryAmountReader error", e);
        }
    }

    public static ObjectReader createNumberValueReader() {
        if (CLASS_DEFAULT_NUMBER_VALUE == null) {
            CLASS_DEFAULT_NUMBER_VALUE = TypeUtils.loadClass("org.javamoney.moneta.spi.DefaultNumberValue");
        }
        if (METHOD_NUMBER_VALUE_OF == null) {
            try {
                METHOD_NUMBER_VALUE_OF = CLASS_DEFAULT_NUMBER_VALUE.getMethod("of", Number.class);
            } catch (NoSuchMethodException e) {
                throw new JSONException("method not found : org.javamoney.moneta.spi.DefaultNumberValue.of", e);
            }
        }
        if (CLASS_NUMBER_VALUE == null) {
            CLASS_NUMBER_VALUE = TypeUtils.loadClass("javax.money.NumberValue");
        }
        return ObjectReaderImplValue.of(CLASS_NUMBER_VALUE, BigDecimal.class, METHOD_NUMBER_VALUE_OF);
    }

    public static ObjectWriter createMonetaryAmountWriter() {
        if (CLASS_MONETARY == null) {
            CLASS_MONETARY = TypeUtils.loadClass("javax.money.Monetary");
        }
        if (CLASS_MONETARY_AMOUNT == null) {
            CLASS_MONETARY_AMOUNT = TypeUtils.loadClass("javax.money.MonetaryAmount");
        }
        if (CLASS_NUMBER_VALUE == null) {
            CLASS_NUMBER_VALUE = TypeUtils.loadClass("javax.money.NumberValue");
        }
        if (CLASS_CURRENCY_UNIT == null) {
            CLASS_CURRENCY_UNIT = TypeUtils.loadClass("javax.money.CurrencyUnit");
        }
        try {
            Function createFunction = LambdaMiscCodec.createFunction(CLASS_MONETARY_AMOUNT.getMethod("getCurrency", new Class[0]));
            try {
                Function createFunction2 = LambdaMiscCodec.createFunction(CLASS_MONETARY_AMOUNT.getMethod("getNumber", new Class[0]));
                ObjectWriterCreator objectWriterCreator = ObjectWriterCreator.INSTANCE;
                Class cls = CLASS_CURRENCY_UNIT;
                FieldWriter createFieldWriter = objectWriterCreator.createFieldWriter("currency", cls, cls, createFunction);
                ObjectWriterCreator objectWriterCreator2 = ObjectWriterCreator.INSTANCE;
                Class cls2 = CLASS_NUMBER_VALUE;
                return new ObjectWriterAdapter(CLASS_MONETARY_AMOUNT, null, null, 0L, Arrays.asList(createFieldWriter, objectWriterCreator2.createFieldWriter("number", cls2, cls2, createFunction2)));
            } catch (Throwable th) {
                throw new JSONException("method not found : javax.money.Monetary.getNumber", th);
            }
        } catch (Throwable th2) {
            throw new JSONException("method not found : javax.money.Monetary.getCurrency", th2);
        }
    }

    public static ObjectWriter createNumberValueWriter() {
        if (CLASS_NUMBER_VALUE == null) {
            CLASS_NUMBER_VALUE = TypeUtils.loadClass("javax.money.NumberValue");
        }
        if (FUNC_NUMBER_VALUE == null) {
            try {
                final BiFunction createBiFunction = LambdaMiscCodec.createBiFunction(CLASS_NUMBER_VALUE.getMethod("numberValue", Class.class));
                FUNC_NUMBER_VALUE = new Function() { // from class: com.alibaba.fastjson2.support.money.MoneySupport$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return MoneySupport.lambda$createNumberValueWriter$1(createBiFunction, obj);
                    }
                };
            } catch (Throwable th) {
                throw new JSONException("method not found : javax.money.NumberValue.numberValue", th);
            }
        }
        return ObjectWriters.ofToBigDecimal(FUNC_NUMBER_VALUE);
    }

    static /* synthetic */ BigDecimal lambda$createNumberValueWriter$1(BiFunction biFunction, Object obj) {
        return (BigDecimal) biFunction.apply(obj, BigDecimal.class);
    }

    public static Object createMonetaryAmount(Object obj, Object obj2) {
        JSONException jSONException;
        if (CLASS_NUMBER_VALUE == null) {
            CLASS_NUMBER_VALUE = TypeUtils.loadClass("javax.money.NumberValue");
        }
        if (CLASS_CURRENCY_UNIT == null) {
            CLASS_CURRENCY_UNIT = TypeUtils.loadClass("javax.money.CurrencyUnit");
        }
        if (CLASS_MONETARY == null) {
            CLASS_MONETARY = TypeUtils.loadClass("javax.money.Monetary");
        }
        if (CLASS_MONETARY_AMOUNT == null) {
            CLASS_MONETARY_AMOUNT = TypeUtils.loadClass("javax.money.MonetaryAmount");
        }
        if (CLASS_MONETARY_AMOUNT_FACTORY == null) {
            CLASS_MONETARY_AMOUNT_FACTORY = TypeUtils.loadClass("javax.money.MonetaryAmountFactory");
        }
        if (FUNC_GET_DEFAULT_AMOUNT_FACTORY == null) {
            MethodHandles.Lookup trustedLookup = JDKUtils.trustedLookup(CLASS_MONETARY);
            try {
                FUNC_GET_DEFAULT_AMOUNT_FACTORY = (Supplier) LambdaMetafactory.metafactory(trustedLookup, "get", TypeUtils.METHOD_TYPE_SUPPLIER, TypeUtils.METHOD_TYPE_OBJECT, trustedLookup.findStatic(CLASS_MONETARY, "getDefaultAmountFactory", MethodType.methodType(CLASS_MONETARY_AMOUNT_FACTORY)), MethodType.methodType(CLASS_MONETARY_AMOUNT_FACTORY)).getTarget().invokeExact();
            } catch (Throwable th) {
                throw new JSONException("method not found : javax.money.Monetary.getDefaultAmountFactory", th);
            }
        }
        if (FUNC_SET_CURRENCY == null) {
            MethodHandles.Lookup trustedLookup2 = JDKUtils.trustedLookup(CLASS_MONETARY_AMOUNT_FACTORY);
            try {
                Class<?> cls = CLASS_MONETARY_AMOUNT_FACTORY;
                MethodHandle findVirtual = trustedLookup2.findVirtual(cls, "setCurrency", MethodType.methodType(cls, (Class<?>) CLASS_CURRENCY_UNIT));
                MethodType methodType = TypeUtils.METHOD_TYPE_BI_FUNCTION;
                MethodType methodType2 = TypeUtils.METHOD_TYPE_OBJECT_OBJECT_OBJECT;
                Class cls2 = CLASS_MONETARY_AMOUNT_FACTORY;
                FUNC_SET_CURRENCY = (BiFunction) LambdaMetafactory.metafactory(trustedLookup2, "apply", methodType, methodType2, findVirtual, MethodType.methodType(cls2, cls2, CLASS_CURRENCY_UNIT)).getTarget().invokeExact();
            } finally {
            }
        }
        if (FUNC_SET_NUMBER == null) {
            MethodHandles.Lookup trustedLookup3 = JDKUtils.trustedLookup(CLASS_MONETARY_AMOUNT_FACTORY);
            try {
                Class<?> cls3 = CLASS_MONETARY_AMOUNT_FACTORY;
                MethodHandle findVirtual2 = trustedLookup3.findVirtual(cls3, "setNumber", MethodType.methodType(cls3, (Class<?>) Number.class));
                MethodType methodType3 = TypeUtils.METHOD_TYPE_BI_FUNCTION;
                MethodType methodType4 = TypeUtils.METHOD_TYPE_OBJECT_OBJECT_OBJECT;
                Class cls4 = CLASS_MONETARY_AMOUNT_FACTORY;
                FUNC_SET_NUMBER = (BiFunction) LambdaMetafactory.metafactory(trustedLookup3, "apply", methodType3, methodType4, findVirtual2, MethodType.methodType(cls4, cls4, Number.class)).getTarget().invokeExact();
            } finally {
            }
        }
        if (FUNC_CREATE == null) {
            MethodHandles.Lookup trustedLookup4 = JDKUtils.trustedLookup(CLASS_MONETARY_AMOUNT_FACTORY);
            try {
                FUNC_CREATE = (Function) LambdaMetafactory.metafactory(trustedLookup4, "apply", TypeUtils.METHOD_TYPE_FUNCTION, TypeUtils.METHOD_TYPE_OBJECT_OBJECT, trustedLookup4.findVirtual(CLASS_MONETARY_AMOUNT_FACTORY, "create", MethodType.methodType(CLASS_MONETARY_AMOUNT)), MethodType.methodType((Class<?>) CLASS_MONETARY_AMOUNT, (Class<?>) CLASS_MONETARY_AMOUNT_FACTORY)).getTarget().invokeExact();
            } finally {
            }
        }
        Object obj3 = FUNC_GET_DEFAULT_AMOUNT_FACTORY.get();
        if (obj != null) {
            FUNC_SET_CURRENCY.apply(obj3, obj);
        }
        if (obj2 != null) {
            FUNC_SET_NUMBER.apply(obj3, obj2);
        }
        return FUNC_CREATE.apply(obj3);
    }
}
