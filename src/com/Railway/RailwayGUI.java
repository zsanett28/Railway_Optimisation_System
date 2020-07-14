package com.Railway;

import com.Railway.graph.Graph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RailwayGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> toCity;
    private JComboBox<String>  fromCity;
    private JPanel routeSelectPanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JPanel mapPanel;
    private JPanel menuPanel;
    private Graph graph;

    public RailwayGUI(String title, Graph graph){
        super(title);
        this.graph = graph;
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        fromCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));
        toCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));

        initializeGUI();
        this.setContentPane(mainPanel);
    }

    private void initializeGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
