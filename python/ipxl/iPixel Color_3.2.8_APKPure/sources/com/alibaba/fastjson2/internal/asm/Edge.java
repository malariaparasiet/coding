package com.alibaba.fastjson2.internal.asm;

/* loaded from: classes2.dex */
final class Edge {
    final Edge nextEdge;
    final Label successor;

    Edge(Label label, Edge edge) {
        this.successor = label;
        this.nextEdge = edge;
    }
}
