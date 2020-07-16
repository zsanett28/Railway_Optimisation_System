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

    public Node getNeighbour(Node node) {
        if (fromNode == node) {
            return toNode;
        } else if (toNode == node) {
            return fromNode;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Duration getDuration(){
        return Duration.between(startTime, endTime);
    }
}
