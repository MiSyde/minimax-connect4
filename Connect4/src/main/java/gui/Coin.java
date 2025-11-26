package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Coin {
    private int x;
    private int y;
    private int height;
    private int width;

    public Coin(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getHeight() { return height; }

    protected int getWidth() { return width; }

    public void setNewData(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public abstract void draw(Graphics g);

    static public class RedCoin extends Coin{


        public RedCoin(int x, int y, int height, int width) {
            super(x, y, height, width);
        }

        public void draw(Graphics g){
            g.setColor(Color.RED);
            g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }

    }

    static public class YellowCoin extends Coin{

        public YellowCoin(int x, int y, int height, int width) {
            super(x, y, height, width);
        }

        public void draw(Graphics g){
            g.setColor(Color.YELLOW);
            g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }

    }
}

