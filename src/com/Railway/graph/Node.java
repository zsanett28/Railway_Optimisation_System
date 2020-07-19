package com.Railway.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private Object distance;
    private final ArrayList<Edge> edges = new ArrayList<>();
    private final String cityName;
    private Node parent;

    public Node(String cityName) {
        this.cityName = cityName;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getEdges(), node.getEdges()) &&
                Objects.equals(getCityName(), node.getCityName()) &&
                Objects.equals(getParent(), node.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityName());
    }
}
