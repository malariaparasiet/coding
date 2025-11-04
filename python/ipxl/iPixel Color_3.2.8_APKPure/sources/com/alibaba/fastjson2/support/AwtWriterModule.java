package com.alibaba.fastjson2.support;

import androidx.autofill.HintConstants;
import com.alibaba.fastjson2.modules.ObjectWriterModule;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriters;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
public class AwtWriterModule implements ObjectWriterModule {
    public static AwtWriterModule INSTANCE = new AwtWriterModule();

    @Override // com.alibaba.fastjson2.modules.ObjectWriterModule
    public ObjectWriter getObjectWriter(Type type, Class cls) {
        if (type == Color.class) {
            return ObjectWriters.objectWriter(Color.class, ObjectWriters.fieldWriter("r", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda0
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int red;
                    red = ((Color) obj).getRed();
                    return red;
                }
            }), ObjectWriters.fieldWriter("g", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int green;
                    green = ((Color) obj).getGreen();
                    return green;
                }
            }), ObjectWriters.fieldWriter("b", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda2
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int blue;
                    blue = ((Color) obj).getBlue();
                    return blue;
                }
            }), ObjectWriters.fieldWriter("alpha", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda3
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int alpha;
                    alpha = ((Color) obj).getAlpha();
                    return alpha;
                }
            }));
        }
        if (type == Point.class) {
            return ObjectWriters.objectWriter(Point.class, ObjectWriters.fieldWriter("x", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda4
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int i;
                    i = ((Point) obj).x;
                    return i;
                }
            }), ObjectWriters.fieldWriter("y", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda5
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int i;
                    i = ((Point) obj).y;
                    return i;
                }
            }));
        }
        if (type == Font.class) {
            return ObjectWriters.objectWriter(Font.class, ObjectWriters.fieldWriter(HintConstants.AUTOFILL_HINT_NAME, new Function() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String name;
                    name = ((Font) obj).getName();
                    return name;
                }
            }), ObjectWriters.fieldWriter("style", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda7
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int style;
                    style = ((Font) obj).getStyle();
                    return style;
                }
            }), ObjectWriters.fieldWriter("size", new ToIntFunction() { // from class: com.alibaba.fastjson2.support.AwtWriterModule$$ExternalSyntheticLambda8
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int size;
                    size = ((Font) obj).getSize();
                    return size;
                }
            }));
        }
        return null;
    }
}
