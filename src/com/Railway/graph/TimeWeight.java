package com.Railway.graph;

import java.time.Duration;

public class TimeWeight implements Weight<Duration> {

    @Override
    public Duration getWeight(Edge edge) {
        return edge.getDuration();
    }
}
