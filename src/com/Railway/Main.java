package com.Railway;

import com.Railway.graph.Graph;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<String> names = readCitiesFromFile("/resources/cities.txt");

        Graph graph = new Graph(names);

        readRoutesFromFile("/resources/routes.txt", graph);

        startGUI(graph);
    }

    /*initializing the frame of the application*/
    private static void startGUI(Graph graph) {
        JFrame frame = new RailwayGUI("Railway Optimisation System", graph);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @param path the file name, which contains the names of the cities
     * @return list with names of the cities
     */
    /*reading the names of the cities from cities.txt file and adding them to the graph*/
    private static List<String> readCitiesFromFile(String path) {
        List<String> names = new ArrayList<>();
        String cityname;
        Scanner sc;

        sc = new Scanner(Main.class.getResourceAsStream(path));
        while (sc.hasNext()) {
            cityname = sc.nextLine();
            names.add(cityname);
        }
        sc.close();
        return names;
    }

    /**
     * @param path the file name, which contains the roads between the cities
     * @param graph the graph, which is used in the whole application
     */
    /*reading the roads between the cities from routes.txt file and adding them to the graph*/
    private static void readRoutesFromFile(String path, Graph graph) {
        Scanner sc;
        sc = new Scanner(Main.class.getResourceAsStream(path));
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            String fromCity = sc.next();
            String toCity = sc.next();
            LocalTime startTime = LocalTime.parse(sc.next());
            LocalTime endTime = LocalTime.parse(sc.next());
            double price = sc.nextDouble();
            sc.nextLine();
            graph.addEdge(fromCity, toCity, startTime, endTime, price);
        }
        sc.close();
    }
}