package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.StaticLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StaticLayoutFactory.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory23;", "Landroidx/compose/ui/text/android/StaticLayoutFactoryImpl;", "()V", "create", "Landroid/text/StaticLayout;", "params", "Landroidx/compose/ui/text/android/StaticLayoutParams;", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class StaticLayoutFactory23 implements StaticLayoutFactoryImpl {
    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public StaticLayout create(StaticLayoutParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(params.getText(), params.getStart(), params.getEnd(), params.getPaint(), params.getWidth());
        obtain.setTextDirection(params.getTextDir());
        obtain.setAlignment(params.getAlignment());
        obtain.setMaxLines(params.getMaxLines());
        obtain.setEllipsize(params.getEllipsize());
        obtain.setEllipsizedWidth(params.getEllipsizedWidth());
        obtain.setLineSpacing(params.getLineSpacingExtra(), params.getLineSpacingMultiplier());
        obtain.setIncludePad(params.getIncludePadding());
        obtain.setBreakStrategy(params.getBreakStrategy());
        obtain.setHyphenationFrequency(params.getHyphenationFrequency());
        obtain.setIndents(params.getLeftIndents(), params.getRightIndents());
        StaticLayoutFactory26 staticLayoutFactory26 = StaticLayoutFactory26.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(obtain, "this");
        staticLayoutFactory26.setJustificationMode(obtain, params.getJustificationMode());
        if (Build.VERSION.SDK_INT >= 28) {
            StaticLayoutFactory28 staticLayoutFactory28 = StaticLayoutFactory28.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(obtain, "this");
            staticLayoutFactory28.setUseLineSpacingFromFallbacks(obtain, params.getUseFallbackLineSpacing());
        }
        StaticLayout build = obtain.build();
        Intrinsics.checkNotNullExpressionValue(build, "obtain(params.text, params.start, params.end, params.paint, params.width)\n            .apply {\n                setTextDirection(params.textDir)\n                setAlignment(params.alignment)\n                setMaxLines(params.maxLines)\n                setEllipsize(params.ellipsize)\n                setEllipsizedWidth(params.ellipsizedWidth)\n                setLineSpacing(params.lineSpacingExtra, params.lineSpacingMultiplier)\n                setIncludePad(params.includePadding)\n                setBreakStrategy(params.breakStrategy)\n                setHyphenationFrequency(params.hyphenationFrequency)\n                setIndents(params.leftIndents, params.rightIndents)\n                if (Build.VERSION.SDK_INT >= 26) {\n                    StaticLayoutFactory26.setJustificationMode(this, params.justificationMode)\n                }\n                if (Build.VERSION.SDK_INT >= 28) {\n                    StaticLayoutFactory28.setUseLineSpacingFromFallbacks(\n                        this,\n                        params.useFallbackLineSpacing\n                    )\n                }\n            }.build()");
        return build;
    }
}
