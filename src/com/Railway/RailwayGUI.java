package com.Railway;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class RailwayGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox toCity;
    private JComboBox fromCity;
    String[] string = {"Cluj-Napoca","Târgu-Mureș","Miercurea-Ciuc", "Timișoara", "Oradea", "Craiova",
            "București", "Iași", "Constanța", "Brașov", "Sibiu", "Suceava", "Târgu-Jiu", "Alba-Iulia"};

    public RailwayGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.pack();
        this.setTitle("Railway Optimisation System");
        fromCity.setModel(new DefaultComboBoxModel<String>(string));
        toCity.setModel(new DefaultComboBoxModel<String>(string));
    }

    public static void main(String[] args) {

        JFrame frame = new RailwayGUI();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
