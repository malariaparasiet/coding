package com.squareup.gifencoder;

import androidx.camera.video.AudioStats;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes2.dex */
public final class MedianCutQuantizer implements ColorQuantizer {
    public static final MedianCutQuantizer INSTANCE = new MedianCutQuantizer();

    private MedianCutQuantizer() {
    }

    @Override // com.squareup.gifencoder.ColorQuantizer
    public Set<Color> quantize(Multiset<Color> multiset, int i) {
        TreeSet treeSet = new TreeSet(new ClusterSpreadComparator());
        treeSet.add(new Cluster(multiset));
        while (treeSet.size() < i) {
            treeSet.addAll(((Cluster) treeSet.pollFirst()).split());
        }
        HashSet hashSet = new HashSet();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            hashSet.add(Color.getCentroid(((Cluster) it.next()).colors));
        }
        return hashSet;
    }

    private static final class Cluster {
        final Multiset<Color> colors;
        int componentWithLargestSpread;
        double largestSpread;

        Cluster(Multiset<Color> multiset) {
            this.colors = multiset;
            this.largestSpread = -1.0d;
            for (int i = 0; i < 3; i++) {
                double componentSpread = getComponentSpread(i);
                if (componentSpread > this.largestSpread) {
                    this.largestSpread = componentSpread;
                    this.componentWithLargestSpread = i;
                }
            }
        }

        double getComponentSpread(int i) {
            double d = Double.POSITIVE_INFINITY;
            double d2 = Double.NEGATIVE_INFINITY;
            for (Color color : this.colors) {
                d = Math.min(d, color.getComponent(i));
                d2 = Math.max(d2, color.getComponent(i));
            }
            return d2 - d;
        }

        Collection<Cluster> split() {
            ArrayList arrayList = new ArrayList(this.colors);
            Collections.sort(arrayList, new ColorComponentComparator(this.componentWithLargestSpread));
            int size = arrayList.size() / 2;
            return Arrays.asList(new Cluster(new HashMultiset(arrayList.subList(0, size))), new Cluster(new HashMultiset(arrayList.subList(size, arrayList.size()))));
        }
    }

    static final class ClusterSpreadComparator implements Comparator<Cluster> {
        ClusterSpreadComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Cluster cluster, Cluster cluster2) {
            double d = cluster2.largestSpread - cluster.largestSpread;
            if (d == AudioStats.AUDIO_AMPLITUDE_NONE) {
                return ArbitraryComparator.INSTANCE.compare(cluster, cluster2);
            }
            return (int) Math.signum(d);
        }
    }

    static final class ColorComponentComparator implements Comparator<Color> {
        final int component;

        ColorComponentComparator(int i) {
            this.component = i;
        }

        @Override // java.util.Comparator
        public int compare(Color color, Color color2) {
            double component = color.getComponent(this.component) - color2.getComponent(this.component);
            if (component == AudioStats.AUDIO_AMPLITUDE_NONE) {
                return ArbitraryComparator.INSTANCE.compare(color, color2);
            }
            return (int) Math.signum(component);
        }
    }
}
