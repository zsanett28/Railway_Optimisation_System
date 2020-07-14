package com.Railway.graph;

import java.util.*;

public class Graph {

    //private variables
    private final List<Node> nodes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Map<String, Node> stringToNode = new HashMap<>();

    //constructor
    public Graph(List<String> names) {
        for (String name : names) {
            Node node = new Node(name);
            nodes.add(node);
            stringToNode.put(name, node);
        }
    }

    public void addEdge(String fromCity, String toCity, double time, double price) {
        Node fromNode = stringToNode.get(fromCity);
        Node toNode = stringToNode.get(toCity);
        Edge edge = new Edge(fromNode, toNode, time, price);
        edges.add(edge);
        fromNode.addEdge(edge);
        toNode.addEdge(edge);
    }

    //Dijkstra algorithm
    public void dijkstra(String source, Weight weight) {
        dijkstra(stringToNode.get(source), weight);
    }

    private void dijkstra(Node source, Weight weight) {
        initializeDijkstra(source);

        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> nodeQueue = new PriorityQueue<>(new NodeComparator());
        nodeQueue.add(source);

        while (!nodeQueue.isEmpty()) {
            Node minNode = nodeQueue.remove();
            visitedNodes.add(minNode);

            for (Edge edge : minNode.getEdges()) {
                Node neighbour = edge.getNeighbour(minNode);
                double w = weight.getWeight(edge);

                relax(minNode, neighbour, w);

                if (!visitedNodes.contains(neighbour)) {
                    nodeQueue.add(neighbour);
                }
            }
        }
    }

    private void relax(Node current, Node neighbour, double weight) {
        if (neighbour.getDistance() > current.getDistance() + weight) {
            neighbour.setDistance(current.getDistance() + weight);
            neighbour.setParent(current);
        }
    }

    private void initializeDijkstra(Node source) {
        for (Node node : nodes) {
            node.setDistance(Double.MAX_VALUE);
            node.setParent(null);
            node.setVisited(false);
        }
        source.setDistance(0);
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

    public double getDistanceFromSource(String name) {
        return stringToNode.get(name).getDistance();
    }

    public List<String> getCities() {
        ArrayList<String> cityNames = new ArrayList<>();
        for (Node node : nodes){
            cityNames.add(node.getCityName());
        }
        return cityNames;
    }
}
