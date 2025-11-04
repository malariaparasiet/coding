package androidx.compose.ui.graphics.vector.compat;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.TypedArrayUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: XmlVectorParser.android.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a'\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\rH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a'\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u0013H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0011\u001a\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a*\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0000\u001a\f\u0010\"\u001a\u00020#*\u00020\u001bH\u0000\u001a2\u0010$\u001a\u00020%*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020\u001aH\u0000\u001a<\u0010'\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u000e\b\u0002\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u0001H\u0000\u001a2\u0010)\u001a\u00020%*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020\u001aH\u0000\u001a2\u0010*\u001a\u00020%*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020\u001aH\u0000\u001a\f\u0010+\u001a\u00020\u001b*\u00020\u001bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"FILL_TYPE_WINDING", "", "LINECAP_BUTT", "LINECAP_ROUND", "LINECAP_SQUARE", "LINEJOIN_BEVEL", "LINEJOIN_MITER", "LINEJOIN_ROUND", "SHAPE_CLIP_PATH", "", "SHAPE_GROUP", "SHAPE_PATH", "getStrokeLineCap", "Landroidx/compose/ui/graphics/StrokeCap;", "id", "defValue", "getStrokeLineCap-CSYIeUk", "(II)I", "getStrokeLineJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "getStrokeLineJoin-kLtJ_vA", "obtainBrushFromComplexColor", "Landroidx/compose/ui/graphics/Brush;", "complexColor", "Landroidx/core/content/res/ComplexColorCompat;", "createVectorImageBuilder", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "Lorg/xmlpull/v1/XmlPullParser;", "res", "Landroid/content/res/Resources;", "theme", "Landroid/content/res/Resources$Theme;", "attrs", "Landroid/util/AttributeSet;", "isAtEnd", "", "parseClipPath", "", "builder", "parseCurrentVectorNode", "nestedGroups", "parseGroup", "parsePath", "seekToStartTag", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class XmlVectorParser_androidKt {
    private static final int FILL_TYPE_WINDING = 0;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";

    /* renamed from: getStrokeLineCap-CSYIeUk$default, reason: not valid java name */
    static /* synthetic */ int m1175getStrokeLineCapCSYIeUk$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = StrokeCap.INSTANCE.m941getButtKaPHkGw();
        }
        return m1174getStrokeLineCapCSYIeUk(i, i2);
    }

    /* renamed from: getStrokeLineCap-CSYIeUk, reason: not valid java name */
    private static final int m1174getStrokeLineCapCSYIeUk(int i, int i2) {
        if (i == 0) {
            return StrokeCap.INSTANCE.m941getButtKaPHkGw();
        }
        if (i != 1) {
            return i != 2 ? i2 : StrokeCap.INSTANCE.m943getSquareKaPHkGw();
        }
        return StrokeCap.INSTANCE.m942getRoundKaPHkGw();
    }

    /* renamed from: getStrokeLineJoin-kLtJ_vA$default, reason: not valid java name */
    static /* synthetic */ int m1177getStrokeLineJoinkLtJ_vA$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = StrokeJoin.INSTANCE.m952getMiterLxFBmk8();
        }
        return m1176getStrokeLineJoinkLtJ_vA(i, i2);
    }

    /* renamed from: getStrokeLineJoin-kLtJ_vA, reason: not valid java name */
    private static final int m1176getStrokeLineJoinkLtJ_vA(int i, int i2) {
        if (i == 0) {
            return StrokeJoin.INSTANCE.m952getMiterLxFBmk8();
        }
        if (i != 1) {
            return i != 2 ? i2 : StrokeJoin.INSTANCE.m951getBevelLxFBmk8();
        }
        return StrokeJoin.INSTANCE.m953getRoundLxFBmk8();
    }

    public static final boolean isAtEnd(XmlPullParser xmlPullParser) {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        return xmlPullParser.getEventType() == 1 || (xmlPullParser.getDepth() < 1 && xmlPullParser.getEventType() == 3);
    }

    public static /* synthetic */ int parseCurrentVectorNode$default(XmlPullParser xmlPullParser, Resources resources, AttributeSet attributeSet, Resources.Theme theme, ImageVector.Builder builder, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            theme = null;
        }
        return parseCurrentVectorNode(xmlPullParser, resources, attributeSet, theme, builder, i);
    }

    public static final int parseCurrentVectorNode(XmlPullParser xmlPullParser, Resources res, AttributeSet attrs, Resources.Theme theme, ImageVector.Builder builder, int i) {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        int eventType = xmlPullParser.getEventType();
        if (eventType == 2) {
            String name = xmlPullParser.getName();
            if (name == null) {
                return i;
            }
            int hashCode = name.hashCode();
            if (hashCode == -1649314686) {
                if (!name.equals(SHAPE_CLIP_PATH)) {
                    return i;
                }
                parseClipPath(xmlPullParser, res, theme, attrs, builder);
                return i + 1;
            }
            if (hashCode == 3433509) {
                if (!name.equals("path")) {
                    return i;
                }
                parsePath(xmlPullParser, res, theme, attrs, builder);
                return i;
            }
            if (hashCode != 98629247 || !name.equals(SHAPE_GROUP)) {
                return i;
            }
            parseGroup(xmlPullParser, res, theme, attrs, builder);
            return i;
        }
        if (eventType != 3 || !Intrinsics.areEqual(SHAPE_GROUP, xmlPullParser.getName())) {
            return i;
        }
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            builder.clearGroup();
        }
        return 0;
    }

    public static final XmlPullParser seekToStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        int next = xmlPullParser.next();
        while (next != 2 && next != 1) {
            next = xmlPullParser.next();
        }
        if (next == 2) {
            return xmlPullParser;
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static final ImageVector.Builder createVectorImageBuilder(XmlPullParser xmlPullParser, Resources res, Resources.Theme theme, AttributeSet attrs) {
        long m707getUnspecified0d7_KjU;
        int m620getSrcIn0nO6VwU;
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY());
        Intrinsics.checkNotNullExpressionValue(obtainAttributes, "obtainAttributes(\n        res,\n        theme,\n        attrs,\n        AndroidVectorResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY\n    )");
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "viewportWidth", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_VIEWPORT_WIDTH(), 0.0f);
        float namedFloat2 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "viewportHeight", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_VIEWPORT_HEIGHT(), 0.0f);
        if (namedFloat <= 0.0f) {
            throw new XmlPullParserException(Intrinsics.stringPlus(obtainAttributes.getPositionDescription(), "<VectorGraphic> tag requires viewportWidth > 0"));
        }
        if (namedFloat2 <= 0.0f) {
            throw new XmlPullParserException(Intrinsics.stringPlus(obtainAttributes.getPositionDescription(), "<VectorGraphic> tag requires viewportHeight > 0"));
        }
        float dimension = obtainAttributes.getDimension(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_WIDTH(), 0.0f);
        float dimension2 = obtainAttributes.getDimension(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_HEIGHT(), 0.0f);
        if (obtainAttributes.hasValue(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT())) {
            TypedValue typedValue = new TypedValue();
            obtainAttributes.getValue(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT(), typedValue);
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                m707getUnspecified0d7_KjU = ColorKt.Color(typedValue.data);
            } else {
                m707getUnspecified0d7_KjU = Color.INSTANCE.m707getUnspecified0d7_KjU();
            }
        } else {
            m707getUnspecified0d7_KjU = Color.INSTANCE.m707getUnspecified0d7_KjU();
        }
        long j = m707getUnspecified0d7_KjU;
        int i = obtainAttributes.getInt(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_TINT_MODE(), -1);
        if (i == -1) {
            m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m620getSrcIn0nO6VwU();
        } else if (i == 3) {
            m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m622getSrcOver0nO6VwU();
        } else if (i == 5) {
            m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m620getSrcIn0nO6VwU();
        } else if (i == 9) {
            m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m619getSrcAtop0nO6VwU();
        } else {
            switch (i) {
                case 14:
                    m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m611getModulate0nO6VwU();
                    break;
                case 15:
                    m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m616getScreen0nO6VwU();
                    break;
                case 16:
                    m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m614getPlus0nO6VwU();
                    break;
                default:
                    m620getSrcIn0nO6VwU = BlendMode.INSTANCE.m620getSrcIn0nO6VwU();
                    break;
            }
        }
        int i2 = m620getSrcIn0nO6VwU;
        float m2430constructorimpl = Dp.m2430constructorimpl(dimension / res.getDisplayMetrics().density);
        float m2430constructorimpl2 = Dp.m2430constructorimpl(dimension2 / res.getDisplayMetrics().density);
        obtainAttributes.recycle();
        return new ImageVector.Builder(null, m2430constructorimpl, m2430constructorimpl2, namedFloat, namedFloat2, j, i2, 1, null);
    }

    public static final void parsePath(XmlPullParser xmlPullParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH());
        Intrinsics.checkNotNullExpressionValue(obtainAttributes, "obtainAttributes(\n        res,\n        theme,\n        attrs,\n        AndroidVectorResources.STYLEABLE_VECTOR_DRAWABLE_PATH\n    )");
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            throw new IllegalArgumentException("No path data available");
        }
        String string = obtainAttributes.getString(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_NAME());
        if (string == null) {
            string = "";
        }
        String str = string;
        List<PathNode> addPathNodes = VectorKt.addPathNodes(obtainAttributes.getString(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_PATH_DATA()));
        ComplexColorCompat fillColor = TypedArrayUtils.getNamedComplexColor(obtainAttributes, xmlPullParser, theme, "fillColor", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_FILL_COLOR(), 0);
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "fillAlpha", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_FILL_ALPHA(), 1.0f);
        int m1174getStrokeLineCapCSYIeUk = m1174getStrokeLineCapCSYIeUk(TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "strokeLineCap", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_CAP(), -1), StrokeCap.INSTANCE.m941getButtKaPHkGw());
        int m1176getStrokeLineJoinkLtJ_vA = m1176getStrokeLineJoinkLtJ_vA(TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "strokeLineJoin", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_JOIN(), -1), StrokeJoin.INSTANCE.m951getBevelLxFBmk8());
        float namedFloat2 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "strokeMiterLimit", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_MITER_LIMIT(), 1.0f);
        ComplexColorCompat strokeColor = TypedArrayUtils.getNamedComplexColor(obtainAttributes, xmlPullParser, theme, "strokeColor", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_COLOR(), 0);
        float namedFloat3 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "strokeAlpha", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_ALPHA(), 1.0f);
        float namedFloat4 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "strokeWidth", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_WIDTH(), 1.0f);
        float namedFloat5 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "trimPathEnd", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_END(), 1.0f);
        float namedFloat6 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "trimPathOffset", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_OFFSET(), 0.0f);
        float namedFloat7 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "trimPathStart", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_START(), 0.0f);
        int namedInt = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "fillType", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_FILLTYPE(), FILL_TYPE_WINDING);
        obtainAttributes.recycle();
        Intrinsics.checkNotNullExpressionValue(fillColor, "fillColor");
        Brush obtainBrushFromComplexColor = obtainBrushFromComplexColor(fillColor);
        Intrinsics.checkNotNullExpressionValue(strokeColor, "strokeColor");
        builder.m1140addPathoIyEayM(addPathNodes, namedInt == 0 ? PathFillType.INSTANCE.m884getNonZeroRgk1Os() : PathFillType.INSTANCE.m883getEvenOddRgk1Os(), str, obtainBrushFromComplexColor, namedFloat, obtainBrushFromComplexColor(strokeColor), namedFloat3, namedFloat4, m1174getStrokeLineCapCSYIeUk, m1176getStrokeLineJoinkLtJ_vA, namedFloat2, namedFloat7, namedFloat5, namedFloat6);
    }

    private static final Brush obtainBrushFromComplexColor(ComplexColorCompat complexColorCompat) {
        if (complexColorCompat.willDraw()) {
            Shader shader = complexColorCompat.getShader();
            if (shader != null) {
                return BrushKt.ShaderBrush(shader);
            }
            return new SolidColor(ColorKt.Color(complexColorCompat.getColor()), null);
        }
        return null;
    }

    public static final void parseClipPath(XmlPullParser xmlPullParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray obtainStyledAttributes = theme == null ? null : theme.obtainStyledAttributes(attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH(), 0, 0);
        if (obtainStyledAttributes == null) {
            obtainStyledAttributes = res.obtainAttributes(attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH());
        }
        String string = obtainStyledAttributes.getString(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_NAME());
        if (string == null) {
            string = "";
        }
        List<PathNode> addPathNodes = VectorKt.addPathNodes(obtainStyledAttributes.getString(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_PATH_DATA()));
        obtainStyledAttributes.recycle();
        ImageVector.Builder.addGroup$default(builder, string, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, addPathNodes, 254, null);
    }

    public static final void parseGroup(XmlPullParser xmlPullParser, Resources res, Resources.Theme theme, AttributeSet attrs, ImageVector.Builder builder) {
        Intrinsics.checkNotNullParameter(xmlPullParser, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP());
        Intrinsics.checkNotNullExpressionValue(obtainAttributes, "obtainAttributes(\n        res,\n        theme,\n        attrs,\n        AndroidVectorResources.STYLEABLE_VECTOR_DRAWABLE_GROUP\n    )");
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, Key.ROTATION, AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_ROTATION(), 0.0f);
        float f = obtainAttributes.getFloat(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_X(), 0.0f);
        float f2 = obtainAttributes.getFloat(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_Y(), 0.0f);
        float namedFloat2 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "scaleX", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_X(), 1.0f);
        float namedFloat3 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "scaleY", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_Y(), 1.0f);
        float namedFloat4 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "translateX", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_X(), 0.0f);
        float namedFloat5 = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "translateY", AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_Y(), 0.0f);
        String string = obtainAttributes.getString(AndroidVectorResources.INSTANCE.getSTYLEABLE_VECTOR_DRAWABLE_GROUP_NAME());
        if (string == null) {
            string = "";
        }
        obtainAttributes.recycle();
        builder.addGroup(string, namedFloat, f, f2, namedFloat2, namedFloat3, namedFloat4, namedFloat5, VectorKt.getEmptyPath());
    }
}
