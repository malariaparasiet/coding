package com.wifiled.baselib.app.language;

import com.wifiled.baselib.app.Constance;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: Constance.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/wifiled/baselib/app/language/Constance;", "", "<init>", "()V", "SP", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Constance {

    /* compiled from: Constance.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0086\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/app/language/Constance$SP;", "", "Companion", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SP {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* compiled from: Constance.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\bn\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001a\u0010\u0019\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001a\u0010\u001f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\tR\u001a\u0010\"\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\tR\u001a\u0010%\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0007\"\u0004\b'\u0010\tR\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR\u001a\u0010+\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\tR\u001a\u0010.\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0007\"\u0004\b0\u0010\tR\u001a\u00101\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0007\"\u0004\b3\u0010\tR\u001a\u00104\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0007\"\u0004\b6\u0010\tR\u001a\u00107\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0007\"\u0004\b9\u0010\tR\u001a\u0010:\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0007\"\u0004\b<\u0010\tR\u001a\u0010=\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0007\"\u0004\b?\u0010\tR\u001a\u0010@\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0007\"\u0004\bB\u0010\tR\u001a\u0010C\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0007\"\u0004\bE\u0010\tR\u001a\u0010F\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0007\"\u0004\bH\u0010\tR\u001a\u0010I\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0007\"\u0004\bK\u0010\tR\u001a\u0010L\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0007\"\u0004\bN\u0010\tR\u001a\u0010O\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0007\"\u0004\bQ\u0010\tR\u001a\u0010R\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0007\"\u0004\bT\u0010\tR\u001a\u0010U\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0007\"\u0004\bW\u0010\tR\u001a\u0010X\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u0007\"\u0004\bZ\u0010\tR\u001a\u0010[\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0007\"\u0004\b]\u0010\tR\u001a\u0010^\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0007\"\u0004\b`\u0010\tR\u001a\u0010a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0007\"\u0004\bc\u0010\tR\u001a\u0010d\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0007\"\u0004\bf\u0010\tR\u001a\u0010g\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u0007\"\u0004\bi\u0010\tR\u001a\u0010j\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u0007\"\u0004\bl\u0010\tR\u001a\u0010m\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\u0007\"\u0004\bo\u0010\tR\u001a\u0010p\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0007\"\u0004\br\u0010\t¨\u0006s"}, d2 = {"Lcom/wifiled/baselib/app/language/Constance$SP$Companion;", "", "<init>", "()V", "FIRST_INSTALL", "", "getFIRST_INSTALL", "()Ljava/lang/String;", "setFIRST_INSTALL", "(Ljava/lang/String;)V", "LANGUAGE", "getLANGUAGE", "setLANGUAGE", "LANGUAGE_AUTO", "getLANGUAGE_AUTO", "setLANGUAGE_AUTO", "LANGUAGE_ENGLISH", "getLANGUAGE_ENGLISH", "setLANGUAGE_ENGLISH", "LANGUAGE_CHINESE_SIMPLIFIED", "getLANGUAGE_CHINESE_SIMPLIFIED", "setLANGUAGE_CHINESE_SIMPLIFIED", "LANGUAGE_CHINESE_TRADITIONAL", "getLANGUAGE_CHINESE_TRADITIONAL", "setLANGUAGE_CHINESE_TRADITIONAL", "LANGUAGE_JA", "getLANGUAGE_JA", "setLANGUAGE_JA", "LANGUAGE_DE", "getLANGUAGE_DE", "setLANGUAGE_DE", "LANGUAGE_PT", "getLANGUAGE_PT", "setLANGUAGE_PT", "LANGUAGE_ES", "getLANGUAGE_ES", "setLANGUAGE_ES", "LANGUAGE_FR", "getLANGUAGE_FR", "setLANGUAGE_FR", "LANGUAGE_KO", "getLANGUAGE_KO", "setLANGUAGE_KO", "LANGUAGE_RU", "getLANGUAGE_RU", "setLANGUAGE_RU", "LANGUAGE_IT", "getLANGUAGE_IT", "setLANGUAGE_IT", "LANGUAGE_VI", "getLANGUAGE_VI", "setLANGUAGE_VI", "LANGUAGE_THAI", "getLANGUAGE_THAI", "setLANGUAGE_THAI", "LANGUAGE_AR", "getLANGUAGE_AR", "setLANGUAGE_AR", "LANGUAGE_BG", "getLANGUAGE_BG", "setLANGUAGE_BG", "LANGUAGE_CS", "getLANGUAGE_CS", "setLANGUAGE_CS", "LANGUAGE_EL", "getLANGUAGE_EL", "setLANGUAGE_EL", "LANGUAGE_HR", "getLANGUAGE_HR", "setLANGUAGE_HR", "LANGUAGE_NL", "getLANGUAGE_NL", "setLANGUAGE_NL", "LANGUAGE_PL", "getLANGUAGE_PL", "setLANGUAGE_PL", "LANGUAGE_RO", "getLANGUAGE_RO", "setLANGUAGE_RO", "LANGUAGE_SL", "getLANGUAGE_SL", "setLANGUAGE_SL", "LANGUAGE_SK", "getLANGUAGE_SK", "setLANGUAGE_SK", "LANGUAGE_TR", "getLANGUAGE_TR", "setLANGUAGE_TR", "LANGUAGE_HI", "getLANGUAGE_HI", "setLANGUAGE_HI", "LANGUAGE_MR", "getLANGUAGE_MR", "setLANGUAGE_MR", "LANGUAGE_TE", "getLANGUAGE_TE", "setLANGUAGE_TE", "LANGUAGE_KN", "getLANGUAGE_KN", "setLANGUAGE_KN", "LANGUAGE_TA", "getLANGUAGE_TA", "setLANGUAGE_TA", "LANGUAGE_ML", "getLANGUAGE_ML", "setLANGUAGE_ML", "LANGUAGE_GU", "getLANGUAGE_GU", "setLANGUAGE_GU", "LANGUAGE_BN", "getLANGUAGE_BN", "setLANGUAGE_BN", "LANGUAGE_PT_rBR", "getLANGUAGE_PT_rBR", "setLANGUAGE_PT_rBR", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static String FIRST_INSTALL = Constance.SP.FIRST_INSTALL;
            private static String LANGUAGE = Constance.SP.LANGUAGE;
            private static String LANGUAGE_AUTO = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
            private static String LANGUAGE_ENGLISH = "en";
            private static String LANGUAGE_CHINESE_SIMPLIFIED = "zh";
            private static String LANGUAGE_CHINESE_TRADITIONAL = "zh_TW";
            private static String LANGUAGE_JA = "ja";
            private static String LANGUAGE_DE = "de";
            private static String LANGUAGE_PT = "pt";
            private static String LANGUAGE_ES = "es";
            private static String LANGUAGE_FR = "fr";
            private static String LANGUAGE_KO = "ko";
            private static String LANGUAGE_RU = "ru";
            private static String LANGUAGE_IT = "it";
            private static String LANGUAGE_VI = "vi";
            private static String LANGUAGE_THAI = "th";
            private static String LANGUAGE_AR = "ar";
            private static String LANGUAGE_BG = "bg";
            private static String LANGUAGE_CS = "cs";
            private static String LANGUAGE_EL = "el";
            private static String LANGUAGE_HR = "hr";
            private static String LANGUAGE_NL = "nl";
            private static String LANGUAGE_PL = "pl";
            private static String LANGUAGE_RO = "ro";
            private static String LANGUAGE_SL = "sl";
            private static String LANGUAGE_SK = "sk";
            private static String LANGUAGE_TR = "tr";
            private static String LANGUAGE_HI = "hi";
            private static String LANGUAGE_MR = "mr";
            private static String LANGUAGE_TE = "te";
            private static String LANGUAGE_KN = "kn";
            private static String LANGUAGE_TA = "ta";
            private static String LANGUAGE_ML = "ml";
            private static String LANGUAGE_GU = "gu";
            private static String LANGUAGE_BN = "bn";
            private static String LANGUAGE_PT_rBR = "pt-BR";

            private Companion() {
            }

            public final String getFIRST_INSTALL() {
                return FIRST_INSTALL;
            }

            public final void setFIRST_INSTALL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                FIRST_INSTALL = str;
            }

            public final String getLANGUAGE() {
                return LANGUAGE;
            }

            public final void setLANGUAGE(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE = str;
            }

            public final String getLANGUAGE_AUTO() {
                return LANGUAGE_AUTO;
            }

            public final void setLANGUAGE_AUTO(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_AUTO = str;
            }

            public final String getLANGUAGE_ENGLISH() {
                return LANGUAGE_ENGLISH;
            }

            public final void setLANGUAGE_ENGLISH(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_ENGLISH = str;
            }

            public final String getLANGUAGE_CHINESE_SIMPLIFIED() {
                return LANGUAGE_CHINESE_SIMPLIFIED;
            }

            public final void setLANGUAGE_CHINESE_SIMPLIFIED(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_CHINESE_SIMPLIFIED = str;
            }

            public final String getLANGUAGE_CHINESE_TRADITIONAL() {
                return LANGUAGE_CHINESE_TRADITIONAL;
            }

            public final void setLANGUAGE_CHINESE_TRADITIONAL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_CHINESE_TRADITIONAL = str;
            }

            public final String getLANGUAGE_JA() {
                return LANGUAGE_JA;
            }

            public final void setLANGUAGE_JA(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_JA = str;
            }

            public final String getLANGUAGE_DE() {
                return LANGUAGE_DE;
            }

            public final void setLANGUAGE_DE(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_DE = str;
            }

            public final String getLANGUAGE_PT() {
                return LANGUAGE_PT;
            }

            public final void setLANGUAGE_PT(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_PT = str;
            }

            public final String getLANGUAGE_ES() {
                return LANGUAGE_ES;
            }

            public final void setLANGUAGE_ES(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_ES = str;
            }

            public final String getLANGUAGE_FR() {
                return LANGUAGE_FR;
            }

            public final void setLANGUAGE_FR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_FR = str;
            }

            public final String getLANGUAGE_KO() {
                return LANGUAGE_KO;
            }

            public final void setLANGUAGE_KO(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_KO = str;
            }

            public final String getLANGUAGE_RU() {
                return LANGUAGE_RU;
            }

            public final void setLANGUAGE_RU(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_RU = str;
            }

            public final String getLANGUAGE_IT() {
                return LANGUAGE_IT;
            }

            public final void setLANGUAGE_IT(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_IT = str;
            }

            public final String getLANGUAGE_VI() {
                return LANGUAGE_VI;
            }

            public final void setLANGUAGE_VI(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_VI = str;
            }

            public final String getLANGUAGE_THAI() {
                return LANGUAGE_THAI;
            }

            public final void setLANGUAGE_THAI(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_THAI = str;
            }

            public final String getLANGUAGE_AR() {
                return LANGUAGE_AR;
            }

            public final void setLANGUAGE_AR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_AR = str;
            }

            public final String getLANGUAGE_BG() {
                return LANGUAGE_BG;
            }

            public final void setLANGUAGE_BG(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_BG = str;
            }

            public final String getLANGUAGE_CS() {
                return LANGUAGE_CS;
            }

            public final void setLANGUAGE_CS(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_CS = str;
            }

            public final String getLANGUAGE_EL() {
                return LANGUAGE_EL;
            }

            public final void setLANGUAGE_EL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_EL = str;
            }

            public final String getLANGUAGE_HR() {
                return LANGUAGE_HR;
            }

            public final void setLANGUAGE_HR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_HR = str;
            }

            public final String getLANGUAGE_NL() {
                return LANGUAGE_NL;
            }

            public final void setLANGUAGE_NL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_NL = str;
            }

            public final String getLANGUAGE_PL() {
                return LANGUAGE_PL;
            }

            public final void setLANGUAGE_PL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_PL = str;
            }

            public final String getLANGUAGE_RO() {
                return LANGUAGE_RO;
            }

            public final void setLANGUAGE_RO(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_RO = str;
            }

            public final String getLANGUAGE_SL() {
                return LANGUAGE_SL;
            }

            public final void setLANGUAGE_SL(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_SL = str;
            }

            public final String getLANGUAGE_SK() {
                return LANGUAGE_SK;
            }

            public final void setLANGUAGE_SK(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_SK = str;
            }

            public final String getLANGUAGE_TR() {
                return LANGUAGE_TR;
            }

            public final void setLANGUAGE_TR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_TR = str;
            }

            public final String getLANGUAGE_HI() {
                return LANGUAGE_HI;
            }

            public final void setLANGUAGE_HI(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_HI = str;
            }

            public final String getLANGUAGE_MR() {
                return LANGUAGE_MR;
            }

            public final void setLANGUAGE_MR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_MR = str;
            }

            public final String getLANGUAGE_TE() {
                return LANGUAGE_TE;
            }

            public final void setLANGUAGE_TE(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_TE = str;
            }

            public final String getLANGUAGE_KN() {
                return LANGUAGE_KN;
            }

            public final void setLANGUAGE_KN(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_KN = str;
            }

            public final String getLANGUAGE_TA() {
                return LANGUAGE_TA;
            }

            public final void setLANGUAGE_TA(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_TA = str;
            }

            public final String getLANGUAGE_ML() {
                return LANGUAGE_ML;
            }

            public final void setLANGUAGE_ML(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_ML = str;
            }

            public final String getLANGUAGE_GU() {
                return LANGUAGE_GU;
            }

            public final void setLANGUAGE_GU(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_GU = str;
            }

            public final String getLANGUAGE_BN() {
                return LANGUAGE_BN;
            }

            public final void setLANGUAGE_BN(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_BN = str;
            }

            public final String getLANGUAGE_PT_rBR() {
                return LANGUAGE_PT_rBR;
            }

            public final void setLANGUAGE_PT_rBR(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                LANGUAGE_PT_rBR = str;
            }
        }
    }
}
