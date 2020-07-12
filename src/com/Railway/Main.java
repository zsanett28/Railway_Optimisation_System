package com.Railway;

import com.Railway.graph.Edge;
import com.Railway.graph.Graph;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<Edge> routes = new ArrayList<>();
    private static Map<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) {

        readCitiesFromFile("cities.txt");
        readRoutesFromFile("routes.txt");

        Graph g = new Graph(routes, names);

        g.dijkstra(1, new TimeWeight());
        g.printResult(1, 4);
        g.dijkstra(1, new PriceWeight());
        g.printResult(1, 4);

        startGUI();
    }

    private static void startGUI(){
        JFrame frame = new RailwayGUI("Railway Optimisation System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void readCitiesFromFile(String path) {
        int i = 0;
        String cityname;
        Scanner sc;

        try {
            sc = new Scanner(new FileReader(path));
            while (sc.hasNext()) {
                cityname = sc.nextLine();
                names.add(cityname);
                hm.put(cityname, i);
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e){
            System.out.println("Can't find file!");
            System.exit(1);
        }
    }

    private static void readRoutesFromFile(String path){
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(path));
            while (sc.hasNext()) {
                String fromCity = sc.next();
                String toCity = sc.next();
                double time = sc.nextDouble();
                double price = sc.nextDouble();
                sc.nextLine();
                routes.add(new Edge(hm.get(fromCity), hm.get(toCity), time, price));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
            System.exit(1);
        }
    }
}