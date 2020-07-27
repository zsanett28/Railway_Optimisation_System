package com.Railway.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private Object distance;
    private final ArrayList<Edge> edges;
    private final String cityName;
    private Edge incomingEdge;

    public Node(String cityName) {
        this.cityName = cityName;
        this.edges = new ArrayList<>();
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public String getCityName() {
        return cityName;
    }

    public Edge getIncomingEdge() {
        return incomingEdge;
    }

    public void setIncomingEdge(Edge incomingEdge) {
        this.incomingEdge = incomingEdge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getCityName(), node.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityName());
    }

    @Override
    public String toString() {
        return cityName;
    }
}
