package com.Railway;

public class Edge {

    //private variables
    private final int fromNodeIndex;
    private final int toNodeIndex;
    private final int length;

    //constructor
    public Edge(int fromNodeIndex, int toNodeIndex, int length) {
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.length = length;
    }

    //getters
    public int getFromNodeIndex() {
        return fromNodeIndex;
    }

    public int getToNodeIndex() {
        return toNodeIndex;
    }

    public int getLength() {
        return length;
    }

    //determining the neighbouring node
    public int getNeighbourIndex(int nodeIndex) {
        if (fromNodeIndex == nodeIndex) {
            return toNodeIndex;
        } else {
            return fromNodeIndex;
        }
    }
}
