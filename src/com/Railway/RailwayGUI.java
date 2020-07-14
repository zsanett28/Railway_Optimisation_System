package com.Railway;

import com.Railway.graph.Graph;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JLabel infoLabel;
    private JLabel radioMessageLabel;
    private JLabel comboBoxMessageLabel;
    private Graph graph;

    public RailwayGUI(String title, Graph graph){
        super(title);
        this.graph = graph;
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        fromCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));
        fromCity.setSelectedIndex(-1);
        toCity.setModel(new DefaultComboBoxModel<String>(graph.getCities().toArray(new String[0])));
        toCity.setSelectedIndex(-1);

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
                if(fromCity.getSelectedItem() == null || toCity.getSelectedItem() == null){
                    comboBoxMessageLabel.setText("Choose the departure and arrival point!");
                }else{
                    comboBoxMessageLabel.setText("");
                    if(radioPrice.isSelected()){
                        radioMessageLabel.setText("");
                        graph.dijkstra(fromCity.getSelectedItem().toString(), new PriceWeight());
                        String result = printResult(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                        String route = printPath(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                        infoLabel.setText("The cheapest route: ");
                        resultLabel.setText(result + " lei");
                        routeLabel.setText(route);
                    }else{
                        if (radioTime.isSelected()) {
                            radioMessageLabel.setText("");
                            graph.dijkstra(fromCity.getSelectedItem().toString(), new TimeWeight());
                            String result = printResult(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                            String route = printPath(graph, fromCity.getSelectedItem().toString(), toCity.getSelectedItem().toString());
                            infoLabel.setText("The shortest route: ");
                            resultLabel.setText(result + " min");
                            routeLabel.setText(route);
                        }else{
                            radioMessageLabel.setText("Check in on of these options!");
                        }
                    }
                }
            }
        });
        fromCity.addItemListener(new ItemListener() {
            String fromItem="";
            @Override
            public void itemStateChanged(ItemEvent e) {
                toCity.addItem(fromItem);
                toCity.removeItem("");
                fromItem = fromCity.getSelectedItem().toString();
                toCity.removeItem(fromItem);
                if(!fromCity.getSelectedItem().toString().equals(fromItem)){
                    String newItem = fromCity.getSelectedItem().toString();
                    toCity.addItem(fromItem);
                    toCity.removeItem(newItem);
                }
            }
        });
    }


}
