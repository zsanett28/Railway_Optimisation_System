package com.Railway;

import com.Railway.graph.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RailwayGUI extends JFrame {

    /*The elements of the RailwayGUI form*/
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
    private static BufferedImage BufImage1;

    public RailwayGUI(String title, Graph graph) {
        super(title);
        this.graph = graph;

        initializeGUI();
        addListeners();
    }

    /**
     * This method initializes the GUI.
     */
    private void initializeGUI() {
        //mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        menuPanel.setSize(450,600);
        mapPanel.setSize(550,600);

        imageLabel.setSize(550,600);

        try {
            BufImage1 = ImageIO.read(getClass().getResourceAsStream("/resources/train-cfr.jpg"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        ImageIcon ico = new ImageIcon(BufImage1);

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

        try {
            BufImage1 = ImageIO.read(getClass().getResourceAsStream("/resources/icon.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        ImageIcon b = new ImageIcon(BufImage1);

        Image g= b.getImage();
        Image kep=g.getScaledInstance(18,18,Image.SCALE_SMOOTH);
        ImageIcon button= new ImageIcon(kep);
        submitButton.setIcon(button);
        //submitButton.setBorder(new RoundedBorder(10));

        this.setContentPane(mainPanel);
    }

    /**
     * @return false, if the user did not select the required options in the application's panel
     *          true, if the user selected all of the required options in the application's panel
     */
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

    /**
     * In this method are added the images to the panels, is specified the functionality of the submit button,
     * are added the circles and lines to the left panel, if the button is pressed.
     * In this method is called the Dijkstra algorithm.
     */
    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateInput()) {
                    return;
                }

                Component[] components = imageLabel.getComponents();
                for (int i=0; i<components.length; i++) {
                    components[i].setVisible(false);
                }

                try {
                    BufImage1 = ImageIO.read(getClass().getResourceAsStream("/resources/map.jpg"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                ImageIcon icon = new ImageIcon(BufImage1);

                Image image = icon.getImage();
                Image size = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon map = new ImageIcon(size);
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
                    Circle c5 = new Circle(63,179,17,17);
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
                    if(path.size() != 1) {
                        if(edge.getFromNode().getCityName().equals("Cluj-Napoca"))
                        {
                            Circle circ1 = new Circle(169,213,12,12);
                            circ1.setOpaque(false);
                            imageLabel.add(circ1);
                        }
                        if(edge.getFromNode().getCityName().equals("Târgu Mureș"))
                        {
                            Circle circ2 = new Circle(224,237,12,12);
                            circ2.setOpaque(false);
                            imageLabel.add(circ2);
                        }
                        if(edge.getFromNode().getCityName().equals("Miercurea Ciuc"))
                        {
                            Circle circ3 = new Circle(300,258,12,12);
                            circ3.setOpaque(false);
                            imageLabel.add(circ3);
                        }
                        if(edge.getFromNode().getCityName().equals("Timișoara"))
                        {
                            Circle circ4 = new Circle(20,313,12,12);
                            circ4.setOpaque(false);
                            imageLabel.add(circ4);
                        }
                        if(edge.getFromNode().getCityName().equals("Oradea"))
                        {
                            Circle circ5 = new Circle(65,185,12,12);
                            circ5.setOpaque(false);
                            imageLabel.add(circ5);
                        }
                        if(edge.getFromNode().getCityName().equals("Craiova"))
                        {
                            Circle circ6 = new Circle(180,460,12,12);
                            circ6.setOpaque(false);
                            imageLabel.add(circ6);
                        }
                        if(edge.getFromNode().getCityName().equals("București"))
                        {
                            Circle circ7 = new Circle(323,450,12,12);
                            circ7.setOpaque(false);
                            imageLabel.add(circ7);
                        }
                        if(edge.getFromNode().getCityName().equals("Iași"))
                        {
                            Circle circ8 = new Circle(410,175,12,12);
                            circ8.setOpaque(false);
                            imageLabel.add(circ8);
                        }
                        if(edge.getFromNode().getCityName().equals("Constanța"))
                        {
                            Circle circ9 = new Circle(475,470,12,12);
                            circ9.setOpaque(false);
                            imageLabel.add(circ9);
                        }
                        if(edge.getFromNode().getCityName().equals("Brașov"))
                        {
                            Circle circ10 = new Circle(290,325,12,12);
                            circ10.setOpaque(false);
                            imageLabel.add(circ10);
                        }
                        if(edge.getFromNode().getCityName().equals("Sibiu"))
                        {
                            Circle circ11 = new Circle(202,312,12,12);
                            circ11.setOpaque(false);
                            imageLabel.add(circ11);
                        }
                        if(edge.getFromNode().getCityName().equals("Suceava"))
                        {
                            Circle circ12 = new Circle(327,129,12,12);
                            circ12.setOpaque(false);
                            imageLabel.add(circ12);
                        }
                        if(edge.getFromNode().getCityName().equals("Târgu Jiu"))
                        {
                            Circle circ13 = new Circle(142,386,12,12);
                            circ13.setOpaque(false);
                            imageLabel.add(circ13);
                        }
                        if(edge.getFromNode().getCityName().equals("Alba Iulia"))
                        {
                            Circle circ14 = new Circle(165,282,12,12);
                            circ14.setOpaque(false);
                            imageLabel.add(circ14);
                        }

                    }

                }


                for (Edge edge : path) {


                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin1 = new Line(169,213,224,245);
                        lin1.setOpaque(false);
                        imageLabel.add(lin1);
                    }
                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Miercurea Ciuc"))
                    {
                        Line lin2 = new Line(169,213,300,264);
                        lin2.setOpaque(false);
                        imageLabel.add(lin2);
                    }
                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Oradea"))
                    {
                        Line lin3 = new Line(169,213,65,185);
                        lin3.setOpaque(false);
                        imageLabel.add(lin3);
                    }
                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin4 = new Line(169,213,290,325);
                        lin4.setOpaque(false);
                        imageLabel.add(lin4);
                    }
                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Suceava"))
                    {
                        Line lin5 = new Line(169,218,327,138);
                        lin5.setOpaque(false);
                        imageLabel.add(lin5);
                    }
                    if(edge.getFromNode().getCityName().equals("Cluj-Napoca") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin6 = new Line(172,213,170,282);
                        lin6.setOpaque(false);
                        imageLabel.add(lin6);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin7 = new Line(224,242,169,213);
                        lin7.setOpaque(false);
                        imageLabel.add(lin7);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Miercurea Ciuc"))
                    {
                        Line lin8 = new Line(224,242,300,264);
                        lin8.setOpaque(false);
                        imageLabel.add(lin8);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Timișoara"))
                    {
                        Line lin9 = new Line(224,246,20,320);
                        lin9.setOpaque(false);
                        imageLabel.add(lin9);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin10 = new Line(224,240,290,325);
                        lin10.setOpaque(false);
                        imageLabel.add(lin10);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Sibiu"))
                    {
                        Line lin11 = new Line(230,240,210,312);
                        lin11.setOpaque(false);
                        imageLabel.add(lin11);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Mureș") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin12 = new Line(230,245,176,288);
                        lin12.setOpaque(false);
                        imageLabel.add(lin12);
                    }
                    if(edge.getFromNode().getCityName().equals("Miercurea Ciuc") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin13 = new Line(300,264,169,213);
                        lin13.setOpaque(false);
                        imageLabel.add(lin13);
                    }
                    if(edge.getFromNode().getCityName().equals("Miercurea Ciuc") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin14 = new Line(300,266,224,242);
                        lin14.setOpaque(false);
                        imageLabel.add(lin14);
                    }
                    if(edge.getFromNode().getCityName().equals("Miercurea Ciuc") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin15 = new Line(308,260,296,325);
                        lin15.setOpaque(false);
                        imageLabel.add(lin15);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin16 = new Line(28,318,174,213);
                        lin16.setOpaque(false);
                        imageLabel.add(lin16);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin17 = new Line(26,318,226,246);
                        lin17.setOpaque(false);
                        imageLabel.add(lin17);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Oradea"))
                    {
                        Line lin18 = new Line(30,313,70,185);
                        lin18.setOpaque(false);
                        imageLabel.add(lin18);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Craiova"))
                    {
                        Line lin19 = new Line(26,318,180,460);
                        lin19.setOpaque(false);
                        imageLabel.add(lin19);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Sibiu"))
                    {
                        Line lin20 = new Line(26,320,202,316);
                        lin20.setOpaque(false);
                        imageLabel.add(lin20);
                    }
                    if(edge.getFromNode().getCityName().equals("Timișoara") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin21 = new Line(26,320,165,290);
                        lin21.setOpaque(false);
                        imageLabel.add(lin21);
                    }
                    if(edge.getFromNode().getCityName().equals("Oradea") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin22 = new Line(65,185,169,213);
                        lin22.setOpaque(false);
                        imageLabel.add(lin22);
                    }
                    if(edge.getFromNode().getCityName().equals("Oradea") && edge.getToNode().getCityName().equals("Timișoara"))
                    {
                        Line lin23 = new Line(70,185,30,313);
                        lin23.setOpaque(false);
                        imageLabel.add(lin23);
                    }
                    if(edge.getFromNode().getCityName().equals("Craiova") && edge.getToNode().getCityName().equals("Timișoara"))
                    {
                        Line lin24 = new Line(180,460,30,317);
                        lin24.setOpaque(false);
                        imageLabel.add(lin24);
                    }
                    if(edge.getFromNode().getCityName().equals("Craiova") && edge.getToNode().getCityName().equals("București"))
                    {
                        Line lin25 = new Line(180,464,323,457);
                        lin25.setOpaque(false);
                        imageLabel.add(lin25);
                    }
                    if(edge.getFromNode().getCityName().equals("Craiova") && edge.getToNode().getCityName().equals("Sibiu"))
                    {
                        Line lin26 = new Line(187,464,208,312);
                        lin26.setOpaque(false);
                        imageLabel.add(lin26);
                    }
                    if(edge.getFromNode().getCityName().equals("Craiova") && edge.getToNode().getCityName().equals("Târgu Jiu"))
                    {
                        Line lin27 = new Line(187,464,142,384);
                        lin27.setOpaque(false);
                        imageLabel.add(lin27);
                    }
                    if(edge.getFromNode().getCityName().equals("București") && edge.getToNode().getCityName().equals("Craiova"))
                    {
                        Line lin28 = new Line(323,456,180,466);
                        lin28.setOpaque(false);
                        imageLabel.add(lin28);
                    }
                    if(edge.getFromNode().getCityName().equals("București") && edge.getToNode().getCityName().equals("Iași"))
                    {
                        Line lin29 = new Line(328,456,419,175);
                        lin29.setOpaque(false);
                        imageLabel.add(lin29);
                    }
                    if(edge.getFromNode().getCityName().equals("București") && edge.getToNode().getCityName().equals("Constanța"))
                    {
                        Line lin30 = new Line(328,456,475,475);
                        lin30.setOpaque(false);
                        imageLabel.add(lin30);
                    }
                    if(edge.getFromNode().getCityName().equals("București") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin31 = new Line(328,456,292,325);
                        lin31.setOpaque(false);
                        imageLabel.add(lin31);
                    }
                    if(edge.getFromNode().getCityName().equals("București") && edge.getToNode().getCityName().equals("Suceava"))
                    {
                        Line lin32 = new Line(328,456,332,129);
                        lin32.setOpaque(false);
                        imageLabel.add(lin32);
                    }
                    if(edge.getFromNode().getCityName().equals("Iași") && edge.getToNode().getCityName().equals("București"))
                    {
                        Line lin33 = new Line(418,175,330,450);
                        lin33.setOpaque(false);
                        imageLabel.add(lin33);
                    }
                    if(edge.getFromNode().getCityName().equals("Iași") && edge.getToNode().getCityName().equals("Suceava"))
                    {
                        Line lin34 = new Line(418,175,327,132);
                        lin34.setOpaque(false);
                        imageLabel.add(lin34);
                    }
                    if(edge.getFromNode().getCityName().equals("Constanța") && edge.getToNode().getCityName().equals("București"))
                    {
                        Line lin35 = new Line(475,475,328,456);
                        lin35.setOpaque(false);
                        imageLabel.add(lin35);
                    }
                    if(edge.getFromNode().getCityName().equals("Brașov") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin36 = new Line(290,325,169,213);
                        lin36.setOpaque(false);
                        imageLabel.add(lin36);
                    }
                    if(edge.getFromNode().getCityName().equals("Brașov") && edge.getToNode().getCityName().equals("Miercurea Ciuc"))
                    {
                        Line lin37 = new Line(296,325,308,260);
                        lin37.setOpaque(false);
                        imageLabel.add(lin37);
                    }
                    if(edge.getFromNode().getCityName().equals("Brașov") && edge.getToNode().getCityName().equals("București"))
                    {
                        Line lin38 = new Line(292,325,328,456);
                        lin38.setOpaque(false);
                        imageLabel.add(lin38);
                    }
                    if(edge.getFromNode().getCityName().equals("Brașov") && edge.getToNode().getCityName().equals("Sibiu"))
                    {
                        Line lin39 = new Line(292,326,202,316);
                        lin39.setOpaque(false);
                        imageLabel.add(lin39);
                    }
                    if(edge.getFromNode().getCityName().equals("Brașov") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin40 = new Line(290,325,165,288);
                        lin40.setOpaque(false);
                        imageLabel.add(lin40);
                    }
                    if(edge.getFromNode().getCityName().equals("Sibiu") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin41 = new Line(210,312,230,240);
                        lin41.setOpaque(false);
                        imageLabel.add(lin41);
                    }
                    if(edge.getFromNode().getCityName().equals("Sibiu") && edge.getToNode().getCityName().equals("Timișoara"))
                    {
                        Line lin42 = new Line(202,316,26,320);
                        lin42.setOpaque(false);
                        imageLabel.add(lin42);
                    }
                    if(edge.getFromNode().getCityName().equals("Sibiu") && edge.getToNode().getCityName().equals("Craiova"))
                    {
                        Line lin43 = new Line(208,312,187,464);
                        lin43.setOpaque(false);
                        imageLabel.add(lin43);
                    }
                    if(edge.getFromNode().getCityName().equals("Sibiu") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin44 = new Line(202,316,292,326);
                        lin44.setOpaque(false);
                        imageLabel.add(lin44);
                    }
                    if(edge.getFromNode().getCityName().equals("Sibiu") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin45 = new Line(202,312,165,288);
                        lin45.setOpaque(false);
                        imageLabel.add(lin45);
                    }
                    if(edge.getFromNode().getCityName().equals("Suceava") && edge.getToNode().getCityName().equals("București"))
                    {
                        Line lin46 = new Line(332,129,328,456);
                        lin46.setOpaque(false);
                        imageLabel.add(lin46);
                    }
                    if(edge.getFromNode().getCityName().equals("Suceava") && edge.getToNode().getCityName().equals("Iași"))
                    {
                        Line lin47 = new Line(327,132,418,175);
                        lin47.setOpaque(false);
                        imageLabel.add(lin47);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Jiu") && edge.getToNode().getCityName().equals("Craiova"))
                    {
                        Line lin48 = new Line(142,384,187,464);
                        lin48.setOpaque(false);
                        imageLabel.add(lin48);
                    }
                    if(edge.getFromNode().getCityName().equals("Târgu Jiu") && edge.getToNode().getCityName().equals("Alba Iulia"))
                    {
                        Line lin49 = new Line(150,388,170,284);
                        lin49.setOpaque(false);
                        imageLabel.add(lin49);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Cluj-Napoca"))
                    {
                        Line lin50 = new Line(170,282,172,213);
                        lin50.setOpaque(false);
                        imageLabel.add(lin50);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Târgu Mureș"))
                    {
                        Line lin51 = new Line(176,288,230,245);
                        lin51.setOpaque(false);
                        imageLabel.add(lin51);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Timișoara"))
                    {
                        Line lin52 = new Line(165,290,26,320);
                        lin52.setOpaque(false);
                        imageLabel.add(lin52);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Brașov"))
                    {
                        Line lin53 = new Line(165,288,290,325);
                        lin53.setOpaque(false);
                        imageLabel.add(lin53);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Sibiu"))
                    {
                        Line lin54 = new Line(165,288,202,312);
                        lin54.setOpaque(false);
                        imageLabel.add(lin54);
                    }
                    if(edge.getFromNode().getCityName().equals("Alba Iulia") && edge.getToNode().getCityName().equals("Târgu Jiu"))
                    {
                        Line lin55 = new Line(170,284,150,388);
                        lin55.setOpaque(false);
                        imageLabel.add(lin55);
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
