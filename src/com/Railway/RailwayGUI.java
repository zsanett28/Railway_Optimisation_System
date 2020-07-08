package com.Railway;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RailwayGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox fromCity;

    public RailwayGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.pack();
        this.setTitle("Railway Optimisation System");
    }

    public static void main(String[] args) {
        JFrame frame = new RailwayGUI();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
