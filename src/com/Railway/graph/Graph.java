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


        Queue<Node> nodeQueue = new PriorityQueue<>(nodeComparator);
        nodeQueue.addAll(nodes);

        while (!nodeQueue.isEmpty()) {
            Node minNode = nodeQueue.remove();

            for (Edge edge : minNode.getEdges()) {
                Node neighbour = edge.getNeighbour(minNode);
                T w = weight.getWeight(edge);

                relax(minNode, neighbour, edge, w, accumulator, nodeQueue);
            }
        }
    }

    private <T extends Comparable<T>> void relax(Node current, Node neighbour, Edge edge, T weight,
                                                 BinaryOperator<T> accumulator, Queue<Node> queue) {
        T weightSum = accumulator.apply((T) current.getDistance(), weight);

        if (weightSum.compareTo((T) neighbour.getDistance()) < 0) {
            neighbour.setDistance(weightSum);
            neighbour.setIncomingEdge(edge);
            queue.remove(neighbour);
            queue.add(neighbour);
        }
    }

    private <T extends Comparable<T>> void initializeDijkstra(Node source, T maxDistance, T minDistance) {
        for (Node node : nodes) {
            node.setDistance(maxDistance);
            node.setIncomingEdge(null);
        }
        source.setDistance(minDistance);
    }

    public List<Edge> getPath(String target) {
        return getPath(stringToNode.get(target));
    }

    private List<Edge> getPath(Node target) {
        ArrayList<Edge> path = new ArrayList<>();
        Edge edge = target.getIncomingEdge();

        while (edge != null) {
            path.add(edge);
            edge = edge.getFromNode().getIncomingEdge();
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
