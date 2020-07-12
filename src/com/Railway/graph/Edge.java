package com.Railway.graph;

public class Edge {

    private final Node fromNode;
    private final Node toNode;
    private final double time;
    private final double price;

    public Edge(Node fromNode, Node toNode, double time, double price) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.time = time;
        this.price = price;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public double getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public Node getNeighbour(Node node) {
        if (fromNode == node) {
            return toNode;
        } else {
            return fromNode;
        }
    }
}
