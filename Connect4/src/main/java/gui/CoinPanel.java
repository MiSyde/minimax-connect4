package gui;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class CoinPanel extends Container {

    private List<Coin> coins = new LinkedList<Coin>();

    public void addCoin(Coin coin) {
        coins.add(coin);
        this.repaint();
    }

    public void paint(Graphics g){
        for(Coin coin : coins){
            coin.draw(g);
        }
    }
}
