package com.alibaba.fastjson2.internal.asm;

import com.jieli.jl_bt_ota.constant.Command;

/* loaded from: classes2.dex */
class Frame {
    static final int APPEND_FRAME = 252;
    private static final int ARRAY_OF = 67108864;
    private static final int BOOLEAN = 4194313;
    private static final int BYTE = 4194314;
    private static final int CHAR = 4194315;
    static final int CHOP_FRAME = 248;
    static final int CONSTANT_KIND = 4194304;
    static final int DIM_MASK = -67108864;
    static final int DIM_SHIFT = 26;
    static final int DIM_SIZE = 6;
    private static final int DOUBLE = 4194307;
    static final int ELEMENT_OF = -67108864;
    static final int FLAGS_SHIFT = 20;
    static final int FLAGS_SIZE = 2;
    private static final int FLOAT = 4194306;
    static final int FULL_FRAME = 255;
    private static final int INTEGER = 4194305;
    static final int ITEM_ASM_BOOLEAN = 9;
    static final int ITEM_ASM_BYTE = 10;
    static final int ITEM_ASM_CHAR = 11;
    static final int ITEM_ASM_SHORT = 12;
    static final int ITEM_DOUBLE = 3;
    static final int ITEM_FLOAT = 2;
    static final int ITEM_INTEGER = 1;
    static final int ITEM_LONG = 4;
    static final int ITEM_NULL = 5;
    static final int ITEM_OBJECT = 7;
    static final int ITEM_TOP = 0;
    static final int ITEM_UNINITIALIZED = 8;
    static final int ITEM_UNINITIALIZED_THIS = 6;
    static final int KIND_MASK = 62914560;
    static final int KIND_SHIFT = 22;
    static final int KIND_SIZE = 4;
    static final int LOCAL_KIND = 16777216;
    private static final int LONG = 4194308;
    private static final int NULL = 4194309;
    static final int REFERENCE_KIND = 8388608;
    static final int SAME_FRAME = 0;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    private static final int SHORT = 4194316;
    static final int STACK_KIND = 20971520;
    private static final int TOP = 4194304;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
    static final int UNINITIALIZED_KIND = 12582912;
    private static final int UNINITIALIZED_THIS = 4194310;
    static final int VALUE_MASK = 1048575;
    static final int VALUE_SIZE = 20;
    private int initializationCount;
    private int[] initializations;
    private int[] inputLocals;
    int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    final Label owner;

    Frame(Label label) {
        this.owner = label;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0163, code lost:
    
        if (r21.equals("Lcom/alibaba/fastjson2/writer/FieldWriter;") == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x019e, code lost:
    
        if (r21.equals("()Ljava/lang/String;") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0255, code lost:
    
        r11 = "java/lang/String";
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01d0, code lost:
    
        if (r21.equals("(J)Lcom/alibaba/fastjson2/reader/FieldReader;") == false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01ef, code lost:
    
        if (r21.equals("(Lcom/alibaba/fastjson2/JSONReader;)Lcom/alibaba/fastjson2/reader/ObjectReader;") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x020b, code lost:
    
        if (r21.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/Class;J)Ljava/lang/Object;") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0253, code lost:
    
        if (r22 == 62) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0260, code lost:
    
        if (r22 == 79) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0243, code lost:
    
        if (r21.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/reflect/Type;Ljava/lang/Object;J)Ljava/lang/Object;") == false) goto L168;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int getAbstractTypeFromDescriptor(com.alibaba.fastjson2.internal.asm.SymbolTable r20, java.lang.String r21, int r22) {
        /*
            Method dump skipped, instructions count: 788
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.internal.asm.Frame.getAbstractTypeFromDescriptor(com.alibaba.fastjson2.internal.asm.SymbolTable, java.lang.String, int):int");
    }

    final void setInputFrameFromDescriptor(SymbolTable symbolTable, int i, String str, int i2) {
        int i3;
        int[] iArr = new int[i2];
        this.inputLocals = iArr;
        this.inputStack = new int[0];
        if ((i & 8) == 0) {
            i3 = 1;
            if ((i & 262144) == 0) {
                iArr[0] = symbolTable.addType(symbolTable.className) | 8388608;
            } else {
                iArr[0] = UNINITIALIZED_THIS;
            }
        } else {
            i3 = 0;
        }
        for (Type type : Type.getArgumentTypes(str)) {
            int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, type.getDescriptor(), 0);
            int[] iArr2 = this.inputLocals;
            int i4 = i3 + 1;
            iArr2[i3] = abstractTypeFromDescriptor;
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                i3 += 2;
                iArr2[i4] = 4194304;
            } else {
                i3 = i4;
            }
        }
        while (i3 < i2) {
            this.inputLocals[i3] = 4194304;
            i3++;
        }
    }

    private int getLocal(int i) {
        int[] iArr = this.outputLocals;
        if (iArr == null || i >= iArr.length) {
            return i | 16777216;
        }
        int i2 = iArr[i];
        if (i2 != 0) {
            return i2;
        }
        int i3 = 16777216 | i;
        iArr[i] = i3;
        return i3;
    }

    private void setLocal(int i, int i2) {
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        int length = this.outputLocals.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.outputLocals, 0, iArr, 0, length);
            this.outputLocals = iArr;
        }
        this.outputLocals[i] = i2;
    }

    private void push(int i) {
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        int length = this.outputStack.length;
        short s = this.outputStackTop;
        if (s >= length) {
            int[] iArr = new int[Math.max(s + 1, length * 2)];
            System.arraycopy(this.outputStack, 0, iArr, 0, length);
            this.outputStack = iArr;
        }
        int[] iArr2 = this.outputStack;
        short s2 = this.outputStackTop;
        short s3 = (short) (s2 + 1);
        this.outputStackTop = s3;
        iArr2[s2] = i;
        short s4 = (short) (this.outputStackStart + s3);
        if (s4 > this.owner.outputStackMax) {
            this.owner.outputStackMax = s4;
        }
    }

    private void push(SymbolTable symbolTable, String str) {
        int i;
        str.hashCode();
        i = 20;
        switch (str) {
            case "(Ljava/lang/Enum;)V":
                i = 18;
                break;
            case "(Ljava/lang/String;)V":
            case "(Ljava/lang/Object;)V":
            case "(Ljava/lang/Object;)Z":
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;J)V":
                i = 97;
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/reflect/Type;)Lcom/alibaba/fastjson2/writer/ObjectWriter;":
                i = 60;
                break;
            case "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V":
                i = 72;
                break;
            case "()Ljava/lang/Class;":
            case "()I":
            case "()J":
            case "()V":
            case "()Z":
                i = 2;
                break;
            case "(I)V":
            case "(J)V":
            case "(J)Z":
            case "(I)Ljava/lang/Object;":
            case "(I)Ljava/lang/Integer;":
                i = 3;
                break;
            case "(Lcom/alibaba/fastjson2/writer/FieldWriter;Ljava/lang/Object;)Ljava/lang/String;":
                i = 62;
                break;
            case "(Ljava/lang/Object;Ljava/lang/reflect/Type;)Z":
                i = 44;
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;)V":
            case "(Lcom/alibaba/fastjson2/JSONWriter;)Z":
                i = 36;
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;ZLjava/util/List;)V":
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Class;)Lcom/alibaba/fastjson2/writer/ObjectWriter;":
                i = 53;
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Enum;)V":
                i = 52;
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;I)V":
            case "(Lcom/alibaba/fastjson2/JSONWriter;J)V":
                i = 37;
                break;
            default:
                if (str.charAt(0) != '(') {
                    i = 0;
                    break;
                } else {
                    int i2 = 1;
                    while (str.charAt(i2) != ')') {
                        while (str.charAt(i2) == '[') {
                            i2++;
                        }
                        int i3 = i2 + 1;
                        i2 = str.charAt(i2) == 'L' ? Math.max(i3, str.indexOf(59, i3) + 1) : i3;
                    }
                    i = i2 + 1;
                    break;
                }
        }
        int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, str, i);
        if (abstractTypeFromDescriptor != 0) {
            push(abstractTypeFromDescriptor);
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                push(4194304);
            }
        }
    }

    private int pop() {
        short s = this.outputStackTop;
        if (s > 0) {
            int[] iArr = this.outputStack;
            short s2 = (short) (s - 1);
            this.outputStackTop = s2;
            return iArr[s2];
        }
        short s3 = (short) (this.outputStackStart - 1);
        this.outputStackStart = s3;
        return (-s3) | STACK_KIND;
    }

    private void pop(int i) {
        short s = this.outputStackTop;
        if (s >= i) {
            this.outputStackTop = (short) (s - i);
        } else {
            this.outputStackStart = (short) (this.outputStackStart - (i - s));
            this.outputStackTop = (short) 0;
        }
    }

    private void pop(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            pop((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    private void addInitializedType(int i) {
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        int length = this.initializations.length;
        int i2 = this.initializationCount;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.initializations, 0, iArr, 0, length);
            this.initializations = iArr;
        }
        int[] iArr2 = this.initializations;
        int i3 = this.initializationCount;
        this.initializationCount = i3 + 1;
        iArr2[i3] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004f A[LOOP:0: B:8:0x000d->B:15:0x004f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0037 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getInitializedType(com.alibaba.fastjson2.internal.asm.SymbolTable r9, int r10) {
        /*
            r8 = this;
            r0 = 4194310(0x400006, float:5.87748E-39)
            if (r10 == r0) goto Lc
            r1 = -4194304(0xffffffffffc00000, float:NaN)
            r1 = r1 & r10
            r2 = 12582912(0xc00000, float:1.7632415E-38)
            if (r1 != r2) goto L52
        Lc:
            r1 = 0
        Ld:
            int r2 = r8.initializationCount
            if (r1 >= r2) goto L52
            int[] r2 = r8.initializations
            r2 = r2[r1]
            r3 = -67108864(0xfffffffffc000000, float:-2.658456E36)
            r3 = r3 & r2
            r4 = 62914560(0x3c00000, float:1.1284746E-36)
            r4 = r4 & r2
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r2 & r5
            r7 = 16777216(0x1000000, float:2.3509887E-38)
            if (r4 != r7) goto L2a
            int[] r2 = r8.inputLocals
            r2 = r2[r6]
        L28:
            int r2 = r2 + r3
            goto L35
        L2a:
            r7 = 20971520(0x1400000, float:3.526483E-38)
            if (r4 != r7) goto L35
            int[] r2 = r8.inputStack
            int r4 = r2.length
            int r4 = r4 - r6
            r2 = r2[r4]
            goto L28
        L35:
            if (r10 != r2) goto L4f
            r1 = 8388608(0x800000, float:1.1754944E-38)
            if (r10 != r0) goto L43
            java.lang.String r10 = r9.className
            int r9 = r9.addType(r10)
        L41:
            r9 = r9 | r1
            return r9
        L43:
            com.alibaba.fastjson2.internal.asm.Symbol[] r0 = r9.typeTable
            r10 = r10 & r5
            r10 = r0[r10]
            java.lang.String r10 = r10.value
            int r9 = r9.addType(r10)
            goto L41
        L4f:
            int r1 = r1 + 1
            goto Ld
        L52:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.internal.asm.Frame.getInitializedType(com.alibaba.fastjson2.internal.asm.SymbolTable, int):int");
    }

    void execute(int i, int i2, Symbol symbol, SymbolTable symbolTable) {
        switch (i) {
            case 0:
            case 116:
            case 117:
            case 118:
            case 119:
            case Opcodes.I2B /* 145 */:
            case Opcodes.I2C /* 146 */:
            case Opcodes.I2S /* 147 */:
            case Opcodes.GOTO /* 167 */:
            case Opcodes.RETURN /* 177 */:
                return;
            case 1:
                push(NULL);
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
            case 21:
                push(INTEGER);
                return;
            case 9:
            case 10:
            case 22:
                push(LONG);
                push(4194304);
                return;
            case 11:
            case 12:
            case 13:
            case 23:
                push(FLOAT);
                return;
            case 14:
            case 15:
            case 24:
                push(DOUBLE);
                push(4194304);
                return;
            case 18:
                int i3 = symbol.tag;
                switch (i3) {
                    case 3:
                        push(INTEGER);
                        return;
                    case 4:
                        push(FLOAT);
                        return;
                    case 5:
                        push(LONG);
                        push(4194304);
                        return;
                    case 6:
                        push(DOUBLE);
                        push(4194304);
                        return;
                    case 7:
                        push(symbolTable.addType("java/lang/Class") | 8388608);
                        return;
                    case 8:
                        push(symbolTable.addType("java/lang/String") | 8388608);
                        return;
                    default:
                        switch (i3) {
                            case 15:
                                push(symbolTable.addType("java/lang/invoke/MethodHandle") | 8388608);
                                return;
                            case 16:
                                push(symbolTable.addType("java/lang/invoke/MethodType") | 8388608);
                                return;
                            case 17:
                                push(symbolTable, symbol.value);
                                return;
                            default:
                                throw new AssertionError();
                        }
                }
            case 19:
            case 20:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 48:
            case 49:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 80:
            case 81:
            case 82:
            case 86:
            case 90:
            case 91:
            case 93:
            case 94:
            case 98:
            case 99:
            case 102:
            case 103:
            case 106:
            case 107:
            case 110:
            case 111:
            case 114:
            case 115:
            case 137:
            case 138:
            case 141:
            case 144:
            case Opcodes.JSR /* 168 */:
            case Opcodes.RET /* 169 */:
            case 186:
            case 188:
            case Command.CMD_ADV_DEV_REQUEST_OPERATION /* 196 */:
            case 197:
            default:
                throw new IllegalArgumentException();
            case 25:
                push(getLocal(i2));
                return;
            case 46:
            case 51:
            case 52:
            case 53:
            case 96:
            case 100:
            case 104:
            case 108:
            case 112:
            case 120:
            case 122:
            case 124:
            case 126:
            case 128:
            case Opcodes.IXOR /* 130 */:
            case Opcodes.L2I /* 136 */:
            case Opcodes.D2I /* 142 */:
            case Opcodes.FCMPL /* 149 */:
            case Opcodes.FCMPG /* 150 */:
                pop(2);
                push(INTEGER);
                return;
            case 47:
            case Opcodes.D2L /* 143 */:
                pop(2);
                push(LONG);
                push(4194304);
                return;
            case 50:
                pop(1);
                int pop = pop();
                if (pop != NULL) {
                    pop -= 67108864;
                }
                push(pop);
                return;
            case 54:
            case 56:
            case 58:
                setLocal(i2, pop());
                if (i2 > 0) {
                    int i4 = i2 - 1;
                    int local = getLocal(i4);
                    if (local == LONG || local == DOUBLE) {
                        setLocal(i4, 4194304);
                        return;
                    }
                    int i5 = local & KIND_MASK;
                    if (i5 == 16777216 || i5 == STACK_KIND) {
                        setLocal(i4, local | 1048576);
                        return;
                    }
                    return;
                }
                return;
            case 55:
            case 57:
                pop(1);
                setLocal(i2, pop());
                setLocal(i2 + 1, 4194304);
                if (i2 > 0) {
                    int i6 = i2 - 1;
                    int local2 = getLocal(i6);
                    if (local2 == LONG || local2 == DOUBLE) {
                        setLocal(i6, 4194304);
                        return;
                    }
                    int i7 = local2 & KIND_MASK;
                    if (i7 == 16777216 || i7 == STACK_KIND) {
                        setLocal(i6, local2 | 1048576);
                        return;
                    }
                    return;
                }
                return;
            case 79:
            case 83:
            case 84:
            case 85:
                pop(3);
                return;
            case 87:
            case Opcodes.IFEQ /* 153 */:
            case Opcodes.IFNE /* 154 */:
            case Opcodes.IFLT /* 155 */:
            case Opcodes.IFGE /* 156 */:
            case Opcodes.IFGT /* 157 */:
            case Opcodes.IFLE /* 158 */:
            case Opcodes.TABLESWITCH /* 170 */:
            case Opcodes.LOOKUPSWITCH /* 171 */:
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.ATHROW /* 191 */:
            case 194:
            case 195:
            case Opcodes.IFNULL /* 198 */:
            case Opcodes.IFNONNULL /* 199 */:
                pop(1);
                return;
            case 88:
            case Opcodes.IF_ICMPEQ /* 159 */:
            case Opcodes.IF_ICMPNE /* 160 */:
            case Opcodes.IF_ICMPLT /* 161 */:
            case Opcodes.IF_ICMPGE /* 162 */:
            case Opcodes.IF_ICMPGT /* 163 */:
            case Opcodes.IF_ICMPLE /* 164 */:
            case Opcodes.IF_ACMPEQ /* 165 */:
            case Opcodes.IF_ACMPNE /* 166 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.DRETURN /* 175 */:
                pop(2);
                return;
            case 89:
                int pop2 = pop();
                push(pop2);
                push(pop2);
                return;
            case 92:
                int pop3 = pop();
                int pop4 = pop();
                push(pop4);
                push(pop3);
                push(pop4);
                push(pop3);
                return;
            case 95:
                int pop5 = pop();
                int pop6 = pop();
                push(pop5);
                push(pop6);
                return;
            case 97:
            case 101:
            case 105:
            case 109:
            case 113:
            case 127:
            case Opcodes.LOR /* 129 */:
            case Opcodes.LXOR /* 131 */:
                pop(4);
                push(LONG);
                push(4194304);
                return;
            case 121:
            case 123:
            case 125:
                pop(3);
                push(LONG);
                push(4194304);
                return;
            case Opcodes.IINC /* 132 */:
                setLocal(i2, INTEGER);
                return;
            case Opcodes.I2L /* 133 */:
            case Opcodes.F2L /* 140 */:
                pop(1);
                push(LONG);
                push(4194304);
                return;
            case Opcodes.I2F /* 134 */:
                pop(1);
                push(FLOAT);
                return;
            case Opcodes.I2D /* 135 */:
                pop(1);
                push(DOUBLE);
                push(4194304);
                return;
            case Opcodes.F2I /* 139 */:
            case Opcodes.ARRAYLENGTH /* 190 */:
            case Opcodes.INSTANCEOF /* 193 */:
                pop(1);
                push(INTEGER);
                return;
            case Opcodes.LCMP /* 148 */:
            case Opcodes.DCMPL /* 151 */:
            case Opcodes.DCMPG /* 152 */:
                pop(4);
                push(INTEGER);
                return;
            case Opcodes.GETSTATIC /* 178 */:
                push(symbolTable, symbol.value);
                return;
            case Opcodes.PUTSTATIC /* 179 */:
                pop(symbol.value);
                return;
            case Opcodes.GETFIELD /* 180 */:
                pop(1);
                push(symbolTable, symbol.value);
                return;
            case Opcodes.PUTFIELD /* 181 */:
                pop(symbol.value);
                pop();
                return;
            case Opcodes.INVOKEVIRTUAL /* 182 */:
            case Opcodes.INVOKESPECIAL /* 183 */:
            case Opcodes.INVOKESTATIC /* 184 */:
            case Opcodes.INVOKEINTERFACE /* 185 */:
                pop(symbol.value);
                if (i != 184) {
                    int pop7 = pop();
                    if (i == 183 && symbol.name.charAt(0) == '<') {
                        addInitializedType(pop7);
                    }
                }
                push(symbolTable, symbol.value);
                return;
            case Opcodes.NEW /* 187 */:
                push(symbolTable.addUninitializedType(symbol.value, i2) | UNINITIALIZED_KIND);
                return;
            case Opcodes.ANEWARRAY /* 189 */:
                String str = symbol.value;
                pop();
                if (str.charAt(0) == '[') {
                    push(symbolTable, "[" + str);
                    return;
                } else {
                    push(symbolTable.addType(str) | 75497472);
                    return;
                }
            case 192:
                String str2 = symbol.value;
                pop();
                if (str2.charAt(0) == '[') {
                    push(symbolTable, str2);
                    return;
                } else {
                    push(symbolTable.addType(str2) | 8388608);
                    return;
                }
        }
    }

    private int getConcreteOutputType(int i, int i2) {
        int i3 = (-67108864) & i;
        int i4 = KIND_MASK & i;
        if (i4 == 16777216) {
            int i5 = i3 + this.inputLocals[i & 1048575];
            if ((i & 1048576) == 0 || !(i5 == LONG || i5 == DOUBLE)) {
                return i5;
            }
            return 4194304;
        }
        if (i4 != STACK_KIND) {
            return i;
        }
        int i6 = i3 + this.inputStack[i2 - (i & 1048575)];
        if ((i & 1048576) == 0 || !(i6 == LONG || i6 == DOUBLE)) {
            return i6;
        }
        return 4194304;
    }

    final boolean merge(SymbolTable symbolTable, Frame frame) {
        int i;
        int length = this.inputLocals.length;
        int length2 = this.inputStack.length;
        boolean z = true;
        boolean z2 = frame.inputLocals == null;
        if (z2) {
            frame.inputLocals = new int[length];
        }
        for (int i2 = 0; i2 < length; i2++) {
            int[] iArr = this.outputLocals;
            if (iArr != null && i2 < iArr.length) {
                int i3 = iArr[i2];
                if (i3 == 0) {
                    i = this.inputLocals[i2];
                } else {
                    i = getConcreteOutputType(i3, length2);
                }
            } else {
                i = this.inputLocals[i2];
            }
            if (this.initializations != null) {
                i = getInitializedType(symbolTable, i);
            }
            z2 |= merge(symbolTable, i, frame.inputLocals, i2);
        }
        int length3 = this.inputStack.length + this.outputStackStart;
        if (frame.inputStack == null) {
            frame.inputStack = new int[this.outputStackTop + length3];
        } else {
            z = z2;
        }
        for (int i4 = 0; i4 < length3; i4++) {
            int i5 = this.inputStack[i4];
            if (this.initializations != null) {
                i5 = getInitializedType(symbolTable, i5);
            }
            z |= merge(symbolTable, i5, frame.inputStack, i4);
        }
        for (int i6 = 0; i6 < this.outputStackTop; i6++) {
            int concreteOutputType = getConcreteOutputType(this.outputStack[i6], length2);
            if (this.initializations != null) {
                concreteOutputType = getInitializedType(symbolTable, concreteOutputType);
            }
            z |= merge(symbolTable, concreteOutputType, frame.inputStack, length3 + i6);
        }
        return z;
    }

    private static boolean merge(SymbolTable symbolTable, int i, int[] iArr, int i2) {
        int min;
        int addType;
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        if ((67108863 & i) == NULL) {
            if (i3 == NULL) {
                return false;
            }
            i = NULL;
        }
        if (i3 == 0) {
            iArr[i2] = i;
            return true;
        }
        int i4 = i3 & (-67108864);
        int i5 = 4194304;
        if (i4 == 0 && (i3 & KIND_MASK) != 8388608) {
            if (i3 != NULL || ((i & (-67108864)) == 0 && (i & KIND_MASK) != 8388608)) {
                i = 4194304;
            }
            i5 = i;
        } else {
            if (i == NULL) {
                return false;
            }
            if ((i & (-4194304)) != ((-4194304) & i3)) {
                int i6 = i & (-67108864);
                if (i6 != 0 || (i & KIND_MASK) == 8388608) {
                    if (i6 != 0 && (i & KIND_MASK) != 8388608) {
                        i6 -= 67108864;
                    }
                    if (i4 != 0 && (i3 & KIND_MASK) != 8388608) {
                        i4 -= 67108864;
                    }
                    min = Math.min(i6, i4) | 8388608;
                    addType = symbolTable.addType(ASMUtils.TYPE_OBJECT);
                    i5 = min | addType;
                }
            } else if ((i3 & KIND_MASK) == 8388608) {
                i5 = (i & (-67108864)) | 8388608 | symbolTable.addMergedType(i & 1048575, 1048575 & i3);
            } else {
                min = ((i & (-67108864)) - 67108864) | 8388608;
                addType = symbolTable.addType(ASMUtils.TYPE_OBJECT);
                i5 = min | addType;
            }
        }
        if (i5 == i3) {
            return false;
        }
        iArr[i2] = i5;
        return true;
    }

    final void accept(MethodWriter methodWriter) {
        int[] iArr = this.inputLocals;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = 2;
            if (i2 >= iArr.length) {
                break;
            }
            int i6 = iArr[i2];
            if (i6 != LONG && i6 != DOUBLE) {
                i5 = 1;
            }
            i2 += i5;
            if (i6 == 4194304) {
                i4++;
            } else {
                i3 += i4 + 1;
                i4 = 0;
            }
        }
        int[] iArr2 = this.inputStack;
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr2.length) {
            int i9 = iArr2[i7];
            i7 += (i9 == LONG || i9 == DOUBLE) ? 2 : 1;
            i8++;
        }
        int visitFrameStart = methodWriter.visitFrameStart(this.owner.bytecodeOffset, i3, i8);
        int i10 = 0;
        while (true) {
            int i11 = i3 - 1;
            if (i3 <= 0) {
                break;
            }
            int i12 = iArr[i10];
            i10 += (i12 == LONG || i12 == DOUBLE) ? 2 : 1;
            methodWriter.visitAbstractType(visitFrameStart, i12);
            i3 = i11;
            visitFrameStart++;
        }
        while (true) {
            int i13 = i8 - 1;
            if (i8 > 0) {
                int i14 = iArr2[i];
                i += (i14 == LONG || i14 == DOUBLE) ? 2 : 1;
                methodWriter.visitAbstractType(visitFrameStart, i14);
                visitFrameStart++;
                i8 = i13;
            } else {
                methodWriter.visitFrameEnd();
                return;
            }
        }
    }
}
