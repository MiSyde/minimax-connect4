package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardClickListener extends MouseAdapter {

    private CoinPanel panel;

    public BoardClickListener(CoinPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.addCoin(new Coin(e.getX()-12, e.getY()-12, 24, Color.RED));
    }
}
