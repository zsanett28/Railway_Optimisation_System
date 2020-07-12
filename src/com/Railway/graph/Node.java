package com.Railway.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Node {

    private double distanceFromSource;
    private boolean visited;
    private ArrayList<Edge> edges = new ArrayList<>();
    private final String cityName;
    private Node parent;

    public Node(String cityName) {
        this.cityName = cityName;
    }

    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
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
