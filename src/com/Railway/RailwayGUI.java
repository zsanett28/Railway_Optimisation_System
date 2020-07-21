package com.Railway;

import com.Railway.graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RailwayGUI extends JFrame {
    private final Graph graph;
    private JPanel mainPanel;
    private JComboBox<String> toCity;
    private JComboBox<String> fromCity;
    private JPanel routeSelectPanel;
    private JButton submitButton;
    private JPanel mapPanel;
    private JPanel menuPanel;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JPanel radioButtonPanel;
    private JRadioButton radioTime;
    private JRadioButton radioPrice;
    private JLabel infoLabel;
    private JLabel radioMessageLabel;
    private JLabel toCityMessageLabel;
    private JLabel fromCityLabel;
    private JLabel toCityLabel;
    private JLabel fromCityMessageLabel;
    private JPanel describePanel;
    private JLabel textLabel;
    private JLabel imageLabel;
    private JPanel bottomPanel;

    public RailwayGUI(String title, Graph graph) {
        super(title);
        this.graph = graph;

        initializeGUI();
        addListeners();
    }

    private void initializeGUI() {
        //mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        menuPanel.setSize(450,600);
        mapPanel.setSize(550,600);

        imageLabel.setSize(550,600);
        ImageIcon ico= new ImageIcon("src/images/train-cfr.jpg");
        Image image = ico.getImage();
        Image siz = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon cfr= new ImageIcon(siz);
        imageLabel.setIcon(cfr);

        fromCity.setModel(new DefaultComboBoxModel<>(graph.getCities().toArray(new String[0])));
        fromCity.setSelectedIndex(-1);
        toCity.setModel(new DefaultComboBoxModel<>(graph.getCities().toArray(new String[0])));
        toCity.setSelectedIndex(-1);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioTime);
        radioGroup.add(radioPrice);

        ImageIcon b= new ImageIcon("src/images/icon.png");
        Image g= b.getImage();
        Image kep=g.getScaledInstance(18,18,Image.SCALE_SMOOTH);
        ImageIcon button= new ImageIcon(kep);
        submitButton.setIcon(button);


        this.setContentPane(mainPanel);
    }

    private boolean validateInput() {
        if (fromCity.getSelectedItem() == null && toCity.getSelectedItem() == null) {
            toCityMessageLabel.setText("Choose arrival and departure point!");
            return false;
        }
        toCityMessageLabel.setText("");
        if (fromCity.getSelectedItem() == null) {
            fromCityMessageLabel.setText("Choose departure point!");
            return false;
        }
        fromCityMessageLabel.setText("");
        if (toCity.getSelectedItem() == null) {
            toCityMessageLabel.setText("Choose arrival point!");
            return false;
        }
        toCityMessageLabel.setText("");
        if (!radioPrice.isSelected() && !radioTime.isSelected()) {
            radioMessageLabel.setText("Check in on of these options!");
            return false;
        }
        radioMessageLabel.setText("");
        return true;
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateInput()) {
                    return;
                }

                ImageIcon icon= new ImageIcon("src/images/map.jpg");
                Image image = icon.getImage();
                Image size = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon map= new ImageIcon(size);
                imageLabel.setIcon(map);

                String fromCityName = fromCity.getSelectedItem().toString();
                String toCityName = toCity.getSelectedItem().toString();

                if ( fromCityName.equals("Cluj-Napoca") || toCityName.equals("Cluj-Napoca"))
                {
                    Circle c1 = new Circle(165,208,17,17);
                    c1.setOpaque(false);
                    imageLabel.add(c1);
                }


                if (radioPrice.isSelected()) {
                    infoLabel.setText("The cheapest route: ");

                    graph.dijkstra(
                            fromCityName,
                            new PriceWeight(),
                            new NodeComparator<>(),
                            Double.MAX_VALUE,
                            0.0,
                            Double::sum
                    );
                } else {
                    infoLabel.setText("The shortest route: ");

                    graph.dijkstra(
                            fromCityName,
                            new TimeWeight(),
                            new NodeComparator<>(),
                            ChronoUnit.FOREVER.getDuration(),
                            Duration.ZERO,
                            Duration::plus
                    );
                }

                List<Edge> path = graph.getPath(toCityName);


                for (Edge edge : path) {
                    if( path.size() != 1) {
                        if(edge.getFromNode().getCityName().equals("Cluj-Napoca"))
                        {
                            Circle circ1 = new Circle(167,213,12,12);
                            circ1.setOpaque(false);
                            imageLabel.add(circ1);
                        }
                    }

                }


                for (Edge edge : path) {

                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin1 = new Line(500,200,100,60);
                        lin1.setOpaque(false);
                        imageLabel.add(lin1);
                    }


                }



                String result;

                if (path.isEmpty()) {
                    result = "There is no route between " + fromCityName + " and " + toCityName + ".";
                } else {
                    result = Formatter.formatResult(path);
                }

                resultLabel.setText(result);
            }
        });

        fromCity.addItemListener(new ItemListener() {
            String fromItem = "";

            @Override
            public void itemStateChanged(ItemEvent e) {
                toCity.addItem(fromItem);
                toCity.removeItem("");
                fromItem = fromCity.getSelectedItem().toString();
                toCity.removeItem(fromItem);
                if (!fromCity.getSelectedItem().toString().equals(fromItem)) {
                    String newItem = fromCity.getSelectedItem().toString();
                    toCity.addItem(fromItem);
                    toCity.removeItem(newItem);
                }
            }
        });
    }


}
