package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Coin {
    private int x;
    private int y;
    private int diameter;

    public Coin(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
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

    public abstract void draw(Graphics g);


    static public class RedCoin extends Coin{


        public RedCoin(int x, int y, int diameter) {
            super(x, y, diameter);
        }

        public void draw(Graphics g){
            g.setColor(Color.RED);
            g.fillOval(super.getX(), super.getY(), super.getDiameter(), super.getDiameter());
        }

    }

    static public class YellowCoin extends Coin{

        public YellowCoin(int x, int y, int diameter) {
            super(x, y, diameter);
        }

        public void draw(Graphics g){
            g.setColor(Color.YELLOW);
            g.fillOval(super.getX(), super.getY(), super.getDiameter(), super.getDiameter());
        }

    }
}

