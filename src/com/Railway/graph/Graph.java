package com.Railway.graph;

import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;

public class Graph {

    private final List<Node> nodes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Map<String, Node> stringToNode = new HashMap<>();

    public Graph(List<String> names) {
        for (String name : names) {
            Node node = new Node(name);
            nodes.add(node);
            stringToNode.put(name, node);
        }
    }

    public void addEdge(String fromCity, String toCity, LocalTime startTime, LocalTime endTime, double price) {
        Node fromNode = stringToNode.get(fromCity);
        Node toNode = stringToNode.get(toCity);
        Edge edge = new Edge(fromNode, toNode, startTime, endTime, price);
        edges.add(edge);
        fromNode.addEdge(edge);
    }

    //Dijkstra algorithm
    public <T extends Comparable<T>> void dijkstra(String source, Weight<T> weight, Comparator<Node> nodeComparator,
                                                   T maxDistance, T minDistance, BinaryOperator<T> accumulator) {
        dijkstra(stringToNode.get(source), weight, nodeComparator, maxDistance, minDistance, accumulator);
    }

    private <T extends Comparable<T>> void dijkstra(Node source, Weight<T> weight, Comparator<Node> nodeComparator,
                                                    T maxDistance, T minDistance, BinaryOperator<T> accumulator) {
        initializeDijkstra(source, maxDistance, minDistance);

        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> nodeQueue = new PriorityQueue<>(nodeComparator);
        nodeQueue.add(source);

        while (!nodeQueue.isEmpty()) {
            Node minNode = nodeQueue.remove();
            visitedNodes.add(minNode);

            for (Edge edge : minNode.getEdges()) {
                Node neighbour = edge.getNeighbour(minNode);
                T w = weight.getWeight(edge);

                relax(minNode, neighbour, w, accumulator);

                if (!visitedNodes.contains(neighbour)) {
                    nodeQueue.add(neighbour);
                }
            }
        }
    }

    private <T extends Comparable<T>> void relax(Node current, Node neighbour, T weight, BinaryOperator<T> accumulator) {
        T weightSum = accumulator.apply((T) current.getDistance(), weight);

        if (weightSum.compareTo((T) neighbour.getDistance()) < 0) {
            neighbour.setDistance(weightSum);
            neighbour.setParent(current);
        }
    }

    private <T extends Comparable<T>> void initializeDijkstra(Node source, T maxDistance, T minDistance) {
        for (Node node : nodes) {
            node.setDistance(maxDistance);
            node.setParent(null);
            node.setVisited(false);
        }
        source.setDistance(minDistance);
    }

    public List<String> getPath(String target) {
        return getPath(stringToNode.get(target));
    }

    private List<String> getPath(Node target) {
        ArrayList<String> path = new ArrayList<>();
        for (Node node = target; node != null; node = node.getParent()) {
            path.add(node.getCityName());
        }

        Collections.reverse(path);
        return path;
    }

    public Object getDistanceFromSource(String name) {
        return stringToNode.get(name).getDistance();
    }

    public List<String> getCities() {
        ArrayList<String> cityNames = new ArrayList<>();
        for (Node node : nodes) {
            cityNames.add(node.getCityName());
        }
        return cityNames;
    }
}
