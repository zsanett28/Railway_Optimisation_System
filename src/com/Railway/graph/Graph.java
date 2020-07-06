package com.Railway.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    //private variables
    private final Node[] nodes;
    private final Edge[] edges;
    private final int noOfNodes;
    private final int noOfEdges;

    //constructor
    public Graph(List<Edge> routes, List<String> names) {
        this.edges = routes.toArray(new Edge[0]);
        //this.noOfNodes = calculateNoOfNodes(edges);
        this.noOfNodes = names.size();
        this.nodes = new Node[this.noOfNodes];
        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes[n] = new Node(names.get(n));
        }
        this.noOfEdges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
        }
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
            path.add(node.getCityname());
        }

        Collections.reverse(path);
        return path;
    }

    public void printResult(int fromNode, int toNode) {
        System.out.println("The shortest distance from " + nodes[fromNode].getCityname() + " to " + nodes[toNode].getCityname() + " is " + nodes[toNode].getDistanceFromSource());
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
