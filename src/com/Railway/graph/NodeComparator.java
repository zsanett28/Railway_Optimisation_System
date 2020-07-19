package com.Railway.graph;

import java.util.Comparator;

public class NodeComparator<T extends Comparable<T>> implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        T distance1 = (T)o1.getDistance();
        T distance2 = (T)o2.getDistance();
        return distance1.compareTo(distance2);
    }
}
