package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.SnovaParameterSpec;

/* loaded from: classes4.dex */
public interface SnovaKey extends Key {
    SnovaParameterSpec getParameterSpec();
}
