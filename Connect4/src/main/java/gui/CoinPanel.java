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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.GRAY);
        for(Coin coin : coins){
            coin.draw(g);
        }
    }
}
