package com.Railway.graph;

import java.time.Duration;

public class TimeWeight implements Weight<Duration> {

    @Override
    public Duration getWeight(Edge edge) {
        Edge prevEdge = edge.getFromNode().getIncomingEdge();

        if (prevEdge == null) {
            return edge.getDuration();
        }

        Duration waitingTime = Duration.between(prevEdge.getEndTime(), edge.getStartTime());

        if (waitingTime.compareTo(Duration.ZERO) < 0) {
            waitingTime = waitingTime.plusDays(1);
        }

        return edge.getDuration().plus(waitingTime);
    }
}