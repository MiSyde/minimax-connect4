package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import core.Game;

public class BoardClickListener extends MouseAdapter {

    private CoinPanel panel;
    private Game game;

    public BoardClickListener(CoinPanel panel, Game game) {
        this.panel = panel;
        this.game = game;
    }

    public void mouseClicked(MouseEvent e) {


        if(CoinPanel.turn % 2 == 0){
            if(e.getX() <= 100 && e.getX() >= 0){
                int y = 0;
                game.addToBoard(1, y, Color.RED);
            }
            panel.addCoin(new Coin.RedCoin(e.getX()-12, e.getY()-12, 24));
        } else{
            if(e.getX() <= 100 && e.getX() >= 0){
                int y = 0;
                game.addToBoard(1, y, Color.YELLOW);
            }
            panel.addCoin(new Coin.YellowCoin(e.getX()-12, e.getY()-12, 24));
        }

    }
}
