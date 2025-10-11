package gui;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class CoinPanel extends JPanel {

    private List<Coin> coins = new LinkedList<Coin>();
    static int turn = 0; // <- !! can't stay 0 if we use a loaded game

    public void addCoin(Coin coin) {
        coins.add(coin);
        ++turn;
        this.repaint();
    }

    private void boardLineSetup(Graphics g){
        // Vertical
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color l = new Color(96, 95, 95);
        g.setColor(l);
        g.drawLine(0, 0, 0, 600);
        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(700, 0, 700, 600);
        // Horizontal
        g.drawLine(0, 100, 700, 100);
        g.drawLine(0, 200, 700, 200);
        g.drawLine(0, 300, 700, 300);
        g.drawLine(0, 400, 700, 400);
        g.drawLine(0, 500, 700, 500);
        g.drawLine(0, 600, 700, 600);
    }

    private void boardCircleSetup(Graphics g) {
        Color circles = new Color(92, 92, 92);
        g.setColor(circles);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(int i = 0; i<7; i++){
            for(int j = 0; j<6; j++){
                g.drawOval(i*100+10, j*100+10, 80, 80);
                g.fillOval(i*100+10, j*100+10, 80, 80);
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color bg = new Color(142, 142, 142);
        setBackground(bg);
        boardCircleSetup(g);
        boardLineSetup(g);
        for(Coin coin : coins){
            coin.draw(g);
        }
    }
}
