package com.Railway.graph;

public class TimeWeight implements Weight {

    @Override
    public double getWeight(Edge edge) {
        return edge.getDuration().getSeconds();
    }
}
