package com.Railway.graph;

public class PriceWeight implements Weight<Double> {

    @Override
    public Double getWeight(Edge edge) {
        return edge.getPrice();
    }
}
