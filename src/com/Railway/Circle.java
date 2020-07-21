package com.Railway;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private int x=0;
    private int y=0;
    private int w=0;
    private int h=0;

    public Circle(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        setSize(500,500);

    }

    public void paint(Graphics g) {

        //super.paint(g);
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.red);
        g2.fillOval(x,y,w,h);
    }
}
