package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardClickListener extends MouseAdapter {

    private final CoinPanel panel;

    public BoardClickListener(CoinPanel panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        int x = -1;
        int y = -1;
        int w = Board.width / 7;
        int h = Board.height / 6;
        int w2 = w/10; // to position the coin in the middle
        int h2 = h/10;
        if(e.getX() <= w)
            x = 0;
        else if(e.getX() <= w * 2)
            x = 1;
        else if(e.getX() <=  w * 3)
            x = 2;
        else if(e.getX() <=  w * 4)
            x = 3;
        else if(e.getX() <= w * 5)
            x = 4;
        else if(e.getX() <= w * 6)
            x = 5;
        else if (e.getX() <= Board.width)
            x = 6;
        if(x != -1){
            y = panel.game.getCurrentY(x);
        }
        if(panel.getTurn() % 2 == 0){
            if(y != -1){
                panel.game.addToBoard(x, y, Color.RED);
                panel.addCoin(new Coin.RedCoin(w2 + w*x, (Board.height-h+w2)-(h*y), h-h2*2, w-w2*2, x ,y));
            }
        } else{
            if(y != -1){
                panel.game.addToBoard(x, y, Color.YELLOW);
                panel.addCoin(new Coin.YellowCoin(w2 + w*x, (Board.height-h+w2)-(h*y), h-h2*2, w-w2*2, x, y));
            }
        }
    }
}
