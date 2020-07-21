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
                if ( fromCityName.equals("Târgu Mureș") || toCityName.equals("Târgu Mureș"))
                {
                    Circle c2 = new Circle(221,238,17,17);
                    c2.setOpaque(false);
                    imageLabel.add(c2);
                }
                if ( fromCityName.equals("Miercurea Ciuc") || toCityName.equals("Miercurea Ciuc"))
                {
                    Circle c3 = new Circle(298,260,17,17);
                    c3.setOpaque(false);
                    imageLabel.add(c3);
                }
                if ( fromCityName.equals("Timișoara") || toCityName.equals("Timișoara"))
                {
                    Circle c4 = new Circle(20,312,17,17);
                    c4.setOpaque(false);
                    imageLabel.add(c4);
                }
                if ( fromCityName.equals("Oradea") || toCityName.equals("Oradea"))
                {
                    Circle c5 = new Circle(63,177,17,17);
                    c5.setOpaque(false);
                    imageLabel.add(c5);
                }
                if ( fromCityName.equals("Craiova") || toCityName.equals("Craiova"))
                {
                    Circle c6 = new Circle(180,457,17,17);
                    c6.setOpaque(false);
                    imageLabel.add(c6);
                }
                if ( fromCityName.equals("București") || toCityName.equals("București"))
                {
                    Circle c7 = new Circle(320,450,17,17);
                    c7.setOpaque(false);
                    imageLabel.add(c7);
                }
                if ( fromCityName.equals("Iași") || toCityName.equals("Iași"))
                {
                    Circle c8 = new Circle(413,168,17,17);
                    c8.setOpaque(false);
                    imageLabel.add(c8);
                }
                if ( fromCityName.equals("Constanța") || toCityName.equals("Constanța"))
                {
                    Circle c9 = new Circle(475,470,17,17);
                    c9.setOpaque(false);
                    imageLabel.add(c9);
                }
                if ( fromCityName.equals("Brașov") || toCityName.equals("Brașov"))
                {
                    Circle c10 = new Circle(287,322,17,17);
                    c10.setOpaque(false);
                    imageLabel.add(c10);
                }
                if ( fromCityName.equals("Sibiu") || toCityName.equals("Sibiu"))
                {
                    Circle c11 = new Circle(200,310,17,17);
                    c11.setOpaque(false);
                    imageLabel.add(c11);
                }
                if ( fromCityName.equals("Suceava") || toCityName.equals("Suceava"))
                {
                    Circle c12 = new Circle(325,130,17,17);
                    c12.setOpaque(false);
                    imageLabel.add(c12);
                }
                if ( fromCityName.equals("Târgu Jiu") || toCityName.equals("Târgu Jiu"))
                {
                    Circle c13 = new Circle(140,385,17,17);
                    c13.setOpaque(false);
                    imageLabel.add(c13);
                }
                if ( fromCityName.equals("Alba Iulia") || toCityName.equals("Alba Iulia"))
                {
                    Circle c14 = new Circle(162,283,17,17);
                    c14.setOpaque(false);
                    imageLabel.add(c14);
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
                        if(edge.getFromNode().getCityName().equals("Târgu Mureș"))
                        {
                            Circle circ2 = new Circle(167,213,12,12);
                            circ2.setOpaque(false);
                            imageLabel.add(circ2);
                        }
                        if(edge.getFromNode().getCityName().equals("Miercurea Ciuc"))
                        {
                            Circle circ3 = new Circle(167,213,12,12);
                            circ3.setOpaque(false);
                            imageLabel.add(circ3);
                        }
                        if(edge.getFromNode().getCityName().equals("Timișoara"))
                        {
                            Circle circ4 = new Circle(167,213,12,12);
                            circ4.setOpaque(false);
                            imageLabel.add(circ4);
                        }
                        if(edge.getFromNode().getCityName().equals("Oradea"))
                        {
                            Circle circ5 = new Circle(167,213,12,12);
                            circ5.setOpaque(false);
                            imageLabel.add(circ5);
                        }
                        if(edge.getFromNode().getCityName().equals("Craiova"))
                        {
                            Circle circ6 = new Circle(167,213,12,12);
                            circ6.setOpaque(false);
                            imageLabel.add(circ6);
                        }
                        if(edge.getFromNode().getCityName().equals("București"))
                        {
                            Circle circ7 = new Circle(167,213,12,12);
                            circ7.setOpaque(false);
                            imageLabel.add(circ7);
                        }
                        if(edge.getFromNode().getCityName().equals("Iași"))
                        {
                            Circle circ8 = new Circle(167,213,12,12);
                            circ8.setOpaque(false);
                            imageLabel.add(circ8);
                        }
                        if(edge.getFromNode().getCityName().equals("Constanța"))
                        {
                            Circle circ9 = new Circle(167,213,12,12);
                            circ9.setOpaque(false);
                            imageLabel.add(circ9);
                        }
                        if(edge.getFromNode().getCityName().equals("Brașov"))
                        {
                            Circle circ10 = new Circle(167,213,12,12);
                            circ10.setOpaque(false);
                            imageLabel.add(circ10);
                        }
                        if(edge.getFromNode().getCityName().equals("Sibiu"))
                        {
                            Circle circ11 = new Circle(167,213,12,12);
                            circ11.setOpaque(false);
                            imageLabel.add(circ11);
                        }
                        if(edge.getFromNode().getCityName().equals("Suceava"))
                        {
                            Circle circ12 = new Circle(167,213,12,12);
                            circ12.setOpaque(false);
                            imageLabel.add(circ12);
                        }
                        if(edge.getFromNode().getCityName().equals("Târgu Jiu"))
                        {
                            Circle circ13 = new Circle(167,213,12,12);
                            circ13.setOpaque(false);
                            imageLabel.add(circ13);
                        }
                        if(edge.getFromNode().getCityName().equals("Alba Iulia"))
                        {
                            Circle circ14 = new Circle(167,213,12,12);
                            circ14.setOpaque(false);
                            imageLabel.add(circ14);
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
