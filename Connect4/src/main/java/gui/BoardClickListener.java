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
        int x = -1;
        int y = -1;
        if(e.getX() <= 100)
            x = 0;
        else if(e.getX() <= 200)
            x = 1;
        else if(e.getX() <= 300)
            x = 2;
        else if(e.getX() <= 400)
            x = 3;
        else if(e.getX() <= 500)
            x = 4;
        else if(e.getX() <= 600)
            x = 5;
        else if (e.getX() <= 700)
            x = 6;
        if(x != -1){
            y = game.getCurrentY(x);
        }
        if(CoinPanel.turn % 2 == 0){
            if(y != -1){
                game.addToBoard(x, y, Color.RED);
                panel.addCoin(new Coin.RedCoin(10 + x*100, 510-(100*y), 80));
            }
        } else{
            if(y != -1){
                game.addToBoard(x, y, Color.YELLOW);
                panel.addCoin(new Coin.YellowCoin(10 + x*100, 510-(100*y), 80));
            }
        }
    }
}
