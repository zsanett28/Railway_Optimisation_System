package com.Railway.graph;

public class PriceWeight implements Weight {

    @Override
    public double getWeight(Edge edge) {
        return edge.getPrice();
    }
}
