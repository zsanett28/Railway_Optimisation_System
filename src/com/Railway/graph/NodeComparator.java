package com.Railway.graph;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Double.compare(o1.getDistance(), o2.getDistance());
    }
}
