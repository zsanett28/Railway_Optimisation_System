package com.Railway;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private int x=0;
    private int y=0;
    private int z=0;
    private int t=0;

    public Line(int x, int y, int z, int t) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.t=t;
        setSize(500,500);
    }

    public void paint(Graphics g) {

        //super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(x,y,z,t);
    }
}
