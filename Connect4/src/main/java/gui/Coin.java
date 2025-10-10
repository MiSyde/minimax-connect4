package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Coin {
    private int x;
    private int y;
    private int diameter;
    private Color color;

    public Coin(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }
}
