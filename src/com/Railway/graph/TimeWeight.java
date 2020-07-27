package com.Railway.graph;

import java.time.Duration;

public class TimeWeight implements Weight<Duration> {

    @Override
    public Duration getWeight(Edge edge) {
        Edge prevEdge = edge.getFromNode().getIncomingEdge();

        /*if there is a direct route between the nodes (the previous node has no incoming edge),
        this method returns the simple duration of the travelling without any waiting time
         */
        if (prevEdge == null) {
            return edge.getDuration();
        }

        /*if there is not a direct route between the nodes (the previous node has an incoming edge),
        this method returns the duration of the travelling including all of the waiting times during the route
         */
        Duration waitingTime = Duration.between(prevEdge.getEndTime(), edge.getStartTime());

        if (waitingTime.isNegative()) {
            waitingTime = waitingTime.plusDays(1);
        }

        return edge.getDuration().plus(waitingTime);
    }
}