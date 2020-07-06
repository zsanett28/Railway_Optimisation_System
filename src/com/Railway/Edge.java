package com.Railway;

public class Edge {

    private final int fromNodeIndex;
    private final int toNodeIndex;
    private final double time;
    private final double price;

    public Edge(int fromNodeIndex, int toNodeIndex, double time, double price) {
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.time = time;
        this.price = price;
    }

    public int getFromNodeIndex() {
        return fromNodeIndex;
    }

    public int getToNodeIndex() {
        return toNodeIndex;
    }

    public double getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Determining the neighbouring node
     *
     * @param nodeIndex
     * @return
     */
    public int getNeighbourIndex(int nodeIndex) {
        if (fromNodeIndex == nodeIndex) {
            return toNodeIndex;
        } else {
            return fromNodeIndex;
        }
    }
}
