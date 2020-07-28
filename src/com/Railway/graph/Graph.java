package com.Railway.graph;

import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;

public class Graph {

    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Map<String, Node> stringToNode;

    public Graph(List<String> names) {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        stringToNode = new HashMap<>();

        for (String name : names) {
            Node node = new Node(name);
            nodes.add(node);
            stringToNode.put(name, node);
        }
    }

    /**
     * @param fromCity the name of the departure point
     * @param toCity the name of the arrival point
     * @param startTime the time, when the train departures
     * @param endTime the time, when the train arrives
     * @param price the price of the ticket
     */
    public void addEdge(String fromCity, String toCity, LocalTime startTime, LocalTime endTime, double price) {
        Node fromNode = stringToNode.get(fromCity);
        Node toNode = stringToNode.get(toCity);
        Edge edge = new Edge(fromNode, toNode, startTime, endTime, price);
        edges.add(edge);
        fromNode.addEdge(edge);
    }

    public <T extends Comparable<T>> void dijkstra(String source, Weight<T> weight, Comparator<Node> nodeComparator,
                                                   T maxDistance, T minDistance, BinaryOperator<T> accumulator) {
        dijkstra(stringToNode.get(source), weight, nodeComparator, maxDistance, minDistance, accumulator);
    }

    /**
     * @param source the departure point, the source node
     * @param weight the distance between the nodes given in time or in price
     * @param nodeComparator the comparison of the nodes is based on this
     * @param maxDistance the max value of Double or Duration type
     * @param minDistance the min value of Double or Duration type
     * @param accumulator the binary operator
     * @param <T> the type of the distance between the nodes and the source (Duration or Double)
     */
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

    /**
     * @param current the current node
     * @param neighbour the neighbour node, the other node of the edge
     * @param edge the edge of the selected node from the queue
     * @param weight the distance between the nodes given in time or in price
     * @param accumulator the binary operator
     * @param queue priority queue for obtaining an unvisited node with the minimum distance from the source
     * @param <T> the type of the distance between the nodes and the source (Duration or Double)
     */
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

    /**
     *
     * Initializes the nodes for the dijkstra algorithm.
     *
     * @param source the departure point, the source node
     * @param maxDistance the max value of Double or Duration type
     * @param minDistance the min value of Double or Duration type
     * @param <T> the type of the distance between the nodes and the source (Duration or Double)
     */
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

    /**
     * @param target the arrival point
     * @return reverted path list, which contains the nodes from departure point to arrival point based on Dijkstra algorithm
     */
    private List<Edge> getPath(Node target) {
        List<Edge> path = new ArrayList<>();
        Edge edge = target.getIncomingEdge();

        while (edge != null) {
            path.add(edge);
            edge = edge.getFromNode().getIncomingEdge();
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * @return cityNames list, which contains the names of the cities
     */
    public List<String> getCities() {
        List<String> cityNames = new ArrayList<>();
        for (Node node : nodes) {
            cityNames.add(node.getCityName());
        }
        return cityNames;
    }
}
