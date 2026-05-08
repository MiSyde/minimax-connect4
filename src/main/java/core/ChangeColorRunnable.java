package core;

import gui.Coin;
import gui.CoinPanel;

import java.awt.*;
import java.util.ArrayList;

public class ChangeColorRunnable implements Runnable {
    ArrayList<Coin> winningCoins;
    Color color;
    CoinPanel panel;

    public ChangeColorRunnable(ArrayList<Coin> coins, Color color, CoinPanel panel) {
        winningCoins = coins; this.color = color; this.panel = panel;
    }
    @Override
    public void run() {
        for(Coin c : winningCoins) {
            c.setColor(color);
        }

        panel.coinRedraw();
        panel.repaint();
    }
}
