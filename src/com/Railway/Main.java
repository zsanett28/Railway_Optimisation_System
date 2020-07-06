package com.Railway;

import com.Railway.graph.Edge;
import com.Railway.graph.Graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Edge> routes = new ArrayList<>();
        Map<String, Integer> hm = new HashMap<>();
        Scanner s1, s2;
        try {
            s1 = new Scanner(new FileReader("cities.txt"));
            int i = 0;
            String cityname;

            while (s1.hasNext()) {
                cityname = s1.nextLine();
                names.add(cityname);
                hm.put(cityname, i);
                i++;
            }

            s1.close();

            s2 = new Scanner(new FileReader("routes.txt"));
            while (s2.hasNext()) {

                String fromCity = s2.next();
                String toCity = s2.next();
                int length = s2.nextInt();

                routes.add(new Edge(hm.get(fromCity), hm.get(toCity), length));
            }

            s2.close();

        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
            System.exit(1);
        }

        Graph g = new Graph(routes, names);

        /*for (int i = 0; i < names.size(); i++) {
            g.dijkstra(i);
            g.printResult(i);
        }*/

        g.dijkstra(1,4);
        g.printResult(1,4);
    }
}