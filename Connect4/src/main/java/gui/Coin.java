package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Coin {
    private static int x;
    private static int y;
    private static int diameter;

    public Coin(int x, int y, int diameter) {
        Coin.x = x;
        Coin.y = y;
        Coin.diameter = diameter;
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

    public abstract Color getColor();

    static public class RedCoin extends Coin{

        private Color red;

        public RedCoin(int x, int y, int diameter) {
            super(x, y, diameter);
            red = Color.RED;
        }

        public Color getColor() {
            return red;
        }

        public void draw(Graphics g){
            g.setColor(red);
            g.fillOval(x, y, diameter, diameter);
        }

    }

    static public class YellowCoin extends Coin{

        private Color yellow;

        public YellowCoin(int x, int y, int diameter) {
            super(x, y, diameter);
            yellow = Color.YELLOW;
        }

        public Color getColor() {
            return yellow;
        }

        public void draw(Graphics g){
            g.setColor(yellow);
            g.fillOval(x, y, diameter, diameter);
        }

    }
}

