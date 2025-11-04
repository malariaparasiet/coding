package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.mayo.MayoParameters;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class MayoParameterSpec implements AlgorithmParameterSpec {
    public static final MayoParameterSpec mayo1;
    public static final MayoParameterSpec mayo2;
    public static final MayoParameterSpec mayo3;
    public static final MayoParameterSpec mayo5;
    private static Map parameters;
    private final String name;

    static {
        MayoParameterSpec mayoParameterSpec = new MayoParameterSpec(MayoParameters.mayo1);
        mayo1 = mayoParameterSpec;
        MayoParameterSpec mayoParameterSpec2 = new MayoParameterSpec(MayoParameters.mayo2);
        mayo2 = mayoParameterSpec2;
        MayoParameterSpec mayoParameterSpec3 = new MayoParameterSpec(MayoParameters.mayo3);
        mayo3 = mayoParameterSpec3;
        MayoParameterSpec mayoParameterSpec4 = new MayoParameterSpec(MayoParameters.mayo5);
        mayo5 = mayoParameterSpec4;
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put("MAYO_1", mayoParameterSpec);
        parameters.put("MAYO_2", mayoParameterSpec2);
        parameters.put("MAYO_3", mayoParameterSpec3);
        parameters.put("MAYO_5", mayoParameterSpec4);
    }

    private MayoParameterSpec(MayoParameters mayoParameters) {
        this.name = mayoParameters.getName();
    }

    public static MayoParameterSpec fromName(String str) {
        return (MayoParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
