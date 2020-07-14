package com.Railway;

import com.Railway.graph.Graph;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.Railway.Main.printPath;
import static com.Railway.Main.printResult;

public class RailwayGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> toCity;
    private JComboBox<String>  fromCity;
    private JPanel routeSelectPanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JPanel mapPanel;
    private JPanel menuPanel;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JLabel routeLabel;
    private JPanel radioButtonPanel;
    private JRadioButton radioTime;
    private JRadioButton radioPrice;
    private Graph graph;

    public RailwayGUI(String title, Graph graph){
        super(title);
        this.graph = graph;
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        fromCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));
        toCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));

        groupButton();
        initializeGUI();
        this.setContentPane(mainPanel);
    }

    private void groupButton() {
        ButtonGroup radioGroup = new ButtonGroup();

        radioGroup.add(radioTime);
        radioGroup.add(radioPrice);
    }

    private void initializeGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioPrice.isSelected()){
                    graph.dijkstra(fromCity.getSelectedItem().toString(), new PriceWeight());
                    String result = printResult(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                    String route = printPath(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                    resultLabel.setText(result);
                    routeLabel.setText(route);
                }
                if(radioTime.isSelected()){
                    graph.dijkstra(fromCity.getSelectedItem().toString(), new TimeWeight());
                    String result = printResult(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                    String route = printPath(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                    resultLabel.setText(result);
                    routeLabel.setText(route);
                }

            }
        });
    }


}
