package com.Railway;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RailwayGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> toCity;
    private JComboBox<String>  fromCity;
    private JPanel routeSelectPanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JPanel mapPanel;
    private JPanel menuPanel;
    String[] string = {"Cluj-Napoca","Târgu-Mureș","Miercurea-Ciuc", "Timișoara", "Oradea", "Craiova",
            "București", "Iași", "Constanța", "Brașov", "Sibiu", "Suceava", "Târgu-Jiu", "Alba-Iulia"};

    public RailwayGUI(String title){
        super(title);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        fromCity.setModel(new DefaultComboBoxModel<>(string));
        toCity.setModel(new DefaultComboBoxModel<>(string));

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
