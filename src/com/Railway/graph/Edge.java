package com.Railway.graph;

import java.time.Duration;
import java.time.LocalTime;

public class Edge {

    private final Node fromNode;
    private final Node toNode;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final double price;

    public Edge(Node fromNode, Node toNode, LocalTime startTime, LocalTime endTime, double price) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getPrice() {
        return price;
    }

    /**
     * @param node one of the edge's nodes
     * @return the other node of the edge
     */
    public Node getNeighbour(Node node) {
        if (fromNode == node) {
            return toNode;
        } else if (toNode == node) {
            return fromNode;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return duration between two values with LocalTime type
     */
    public Duration getDuration(){
        Duration duration = Duration.between(startTime, endTime);
        if (duration.isNegative()) {
            duration = duration.plusDays(1);
        }
        return duration;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromNode=" + fromNode +
                ", toNode=" + toNode +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }
}
