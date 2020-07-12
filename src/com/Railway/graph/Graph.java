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
        edges.add(new Edge(stringToNode.get(fromCity), stringToNode.get(toCity), time, price));
    }

    //Dijkstra algorithm
    public void dijkstra(int fromNode, Weight weight) {
        //initialization of the nodes
        for (Node node : nodes) {
            node.setDistanceFromSource(Double.MAX_VALUE);
            node.setParent(null);
            node.setVisited(false);
        }
        nodes[fromNode].setDistanceFromSource(0);
        int nextNode = fromNode;

        for (int i = 0; i < nodes.length; i++) {
            ArrayList<Edge> currentNodeEdges = nodes[nextNode].getEdges();
            for (Edge currentNodeEdge : currentNodeEdges) {
                int neighbourIndex = currentNodeEdge.getNeighbourIndex(nextNode);

                if (!nodes[neighbourIndex].isVisited()) {
                    double tentative = nodes[nextNode].getDistanceFromSource() + weight.getWeight(currentNodeEdge);
                    if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tentative);
                        nodes[neighbourIndex].setParent(nodes[nextNode]);
                    }
                }
            }
            nodes[nextNode].setVisited(true);
            nextNode = getNodeShortestDistanced(i);
        }

    }

    private int getNodeShortestDistanced(int j) {
        int storedNodeIndex = 0;
        double storedDist = Double.MAX_VALUE;
        for (int i = 0; i < nodes.length; i++) {
            double currentDist = nodes[i].getDistanceFromSource();
            if (!nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    public List<String> printPath(Node target) {
        ArrayList<String> path = new ArrayList<>();
        for (Node node = target; node != null; node = node.getParent()) {
            path.add(node.getCityName());
        }

        Collections.reverse(path);
        return path;
    }

    public void printResult(int fromNode, int toNode) {
        System.out.println("The shortest distance from " + nodes[fromNode].getCityName() + " to " + nodes[toNode].getCityName() + " is " + nodes[toNode].getDistanceFromSource());
        System.out.println("The route: " + printPath(nodes[toNode]).toString().replace(","," ->").replace("[","").replace("]",""));
        System.out.println();
    }

    //getters
    public Node[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }
}
