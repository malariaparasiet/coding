package androidx.camera.core.processing;

/* loaded from: classes.dex */
public interface ShaderProvider {
    default String createFragmentShader(String str, String str2) {
        return null;
    }
}
