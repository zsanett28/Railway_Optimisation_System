package com.Railway;

import com.Railway.graph.Graph;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> names = readCitiesFromFile("cities.txt");

        Graph graph = new Graph(names);

        readRoutesFromFile("routes.txt", graph);

        graph.dijkstra("Cluj-Napoca", new TimeWeight());
        printResult(graph, "Cluj-Napoca", "Timișoara");
        printPath(graph, "Cluj-Napoca", "Timișoara");
        graph.dijkstra("Cluj-Napoca", new PriceWeight());
        printResult(graph, "Cluj-Napoca", "Timișoara");
        printPath(graph, "Cluj-Napoca", "Timișoara");

        startGUI(graph);
    }

    public static String printResult(Graph graph, String fromCity, String toCity) {
        String shortestDistance = "From " + fromCity + " to " + toCity + ": " + graph.getDistanceFromSource(toCity);
        return shortestDistance;
    }

    public static String printPath(Graph graph, String fromCity, String toCity) {
        String route = "The route: " + graph.getPath(toCity).toString().replace(",", " ->").replace("[", "").replace("]", "");
        return route;
    }

    private static void startGUI(Graph graph) {
        JFrame frame = new RailwayGUI("Railway Optimisation System", graph);
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static List<String> readCitiesFromFile(String path) {
        List <String> names = new ArrayList<>();
        String cityname;
        Scanner sc;

        try {
            sc = new Scanner(new FileReader(path));
            while (sc.hasNext()) {
                cityname = sc.nextLine();
                names.add(cityname);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
            System.exit(1);
        }
        return names;
    }

    private static void readRoutesFromFile(String path, Graph graph) {
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(path));
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String fromCity = sc.next();
                String toCity = sc.next();
                double time = sc.nextDouble();
                double price = sc.nextDouble();
                sc.nextLine();
                graph.addEdge(fromCity, toCity, time, price);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
            System.exit(1);
        }
    }
}