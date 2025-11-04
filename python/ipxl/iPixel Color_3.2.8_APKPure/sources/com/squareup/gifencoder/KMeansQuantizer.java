package com.squareup.gifencoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class KMeansQuantizer implements ColorQuantizer {
    public static final KMeansQuantizer INSTANCE = new KMeansQuantizer();

    private KMeansQuantizer() {
    }

    @Override // com.squareup.gifencoder.ColorQuantizer
    public Set<Color> quantize(Multiset<Color> multiset, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Set<Color> initialCentroids = getInitialCentroids(multiset, i);
        Iterator<Color> it = initialCentroids.iterator();
        while (it.hasNext()) {
            linkedHashMap.put(it.next(), new HashMultiset());
        }
        for (Color color : multiset.getDistinctElements()) {
            ((Multiset) linkedHashMap.get(color.getNearestColor(initialCentroids))).add(color, multiset.count(color));
        }
        while (!initialCentroids.isEmpty()) {
            recomputeCentroids(linkedHashMap, initialCentroids);
            initialCentroids.clear();
            Set keySet = linkedHashMap.keySet();
            for (Color color2 : linkedHashMap.keySet()) {
                Multiset multiset2 = (Multiset) linkedHashMap.get(color2);
                Iterator it2 = new ArrayList(multiset2.getDistinctElements()).iterator();
                while (it2.hasNext()) {
                    Color color3 = (Color) it2.next();
                    Color nearestColor = color3.getNearestColor(keySet);
                    if (nearestColor != color2) {
                        int count = multiset2.count(color3);
                        Multiset multiset3 = (Multiset) linkedHashMap.get(nearestColor);
                        multiset2.remove(color3, count);
                        multiset3.add(color3, count);
                        initialCentroids.add(color2);
                        initialCentroids.add(nearestColor);
                    }
                }
            }
        }
        return linkedHashMap.keySet();
    }

    private static void recomputeCentroids(Map<Color, Multiset<Color>> map, Set<Color> set) {
        for (Color color : set) {
            Multiset<Color> multiset = map.get(color);
            Color centroid = Color.getCentroid(multiset);
            map.remove(color);
            map.put(centroid, multiset);
        }
    }

    private static Set<Color> getInitialCentroids(Multiset<Color> multiset, int i) {
        ArrayList arrayList = new ArrayList(multiset.getDistinctElements());
        Collections.shuffle(arrayList);
        return new HashSet(arrayList.subList(0, i));
    }
}
