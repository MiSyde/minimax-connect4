package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Coin {
    private int x;
    private int y;
    private int height;
    private int width;
    private int column;
    private int row;

    public Coin(int x, int y, int height, int width, int column, int row) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.column = column;
        this.row = row;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public void setNewData(int w, int h, int w2, int h2) {
        this.x = column * w + w2;
        this.y = (5-row) * h + h2;
        this.height = h - h2 * 2;
        this.width = w - w2 * 2;
    }

    public abstract void draw(Graphics g);

    static public class RedCoin extends Coin{


        public RedCoin(int x, int y, int height, int width, int column, int row) {
            super(x, y, height, width, column, row);
        }

        public void draw(Graphics g){
            g.setColor(Color.RED);
            g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }

    }

    static public class YellowCoin extends Coin{

        public YellowCoin(int x, int y, int height, int width, int column, int row) {
            super(x, y, height, width, column, row);
        }

        public void draw(Graphics g){
            g.setColor(Color.YELLOW);
            g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }

    }
}

