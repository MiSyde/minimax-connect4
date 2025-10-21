package gui;

import core.Game;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import core.Turn;

public class CoinPanel extends JPanel implements Serializable {

    Game game;
    private List<Coin> coins = new LinkedList<Coin>();
    private int turnkimentes; // <- !! can't stay 0 if we use a loaded game

    public void addCoin(Coin coin) {
        coins.add(coin);
        ++Turn.turn;
        this.repaint();
    }

    CoinPanel(Game game) {
        super();
        this.game = game;
    }

    public int getTurnkimentes() {
        return turnkimentes;
    }

    private void boardLineSetup(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color linecolor = new Color(96, 95, 95);
        g.setColor(linecolor);
        // Vertical
        for(int i = 1; i <= 7; ++ i){
            g.drawLine(i*100, 0, i*100, 600);
        }
        // Horizontal
        for(int i = 1; i <= 6; ++ i) {
            g.drawLine(0, 100 * i, 700, 100 * i);
        }
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color bg = new Color(142, 142, 142);
        setBackground(bg);
        boardCircleSetup(g);
        boardLineSetup(g);
        for(Coin coin : coins){
            coin.draw(g);
        }
        if(game.getWon()){
            // victory screen
        }
    }
}
